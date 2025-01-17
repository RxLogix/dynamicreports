/**
 * DynamicReports - Free Java reporting library for creating reports dynamically
 *
 * Copyright (C) 2010 - 2016 Ricardo Mariaca
 * http://www.dynamicreports.org
 *
 * This file is part of DynamicReports.
 *
 * DynamicReports is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * DynamicReports is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with DynamicReports. If not, see <http://www.gnu.org/licenses/>.
 */

package net.sf.dynamicreports.adhoc.test;

import java.awt.Color;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import net.sf.dynamicreports.adhoc.AdhocManager;
import net.sf.dynamicreports.adhoc.configuration.AdhocChart;
import net.sf.dynamicreports.adhoc.configuration.AdhocConfiguration;
import net.sf.dynamicreports.adhoc.configuration.AdhocGroup;
import net.sf.dynamicreports.adhoc.configuration.AdhocRestriction;
import net.sf.dynamicreports.adhoc.configuration.AdhocTextField;
import net.sf.dynamicreports.adhoc.configuration.AdhocValueOperator;
import net.sf.dynamicreports.adhoc.configuration.AdhocValueRestriction;
import net.sf.dynamicreports.adhoc.report.DefaultAdhocReportCustomizer;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.base.DRGroup;
import net.sf.dynamicreports.report.base.DRPage;
import net.sf.dynamicreports.report.base.DRReport;
import net.sf.dynamicreports.report.base.DRSort;
import net.sf.dynamicreports.report.base.DRSubtotal;
import net.sf.dynamicreports.report.base.chart.DRChart;
import net.sf.dynamicreports.report.base.chart.dataset.DRCategoryDataset;
import net.sf.dynamicreports.report.base.chart.plot.DRAxisPlot;
import net.sf.dynamicreports.report.base.column.DRColumn;
import net.sf.dynamicreports.report.base.component.DRComponent;
import net.sf.dynamicreports.report.base.component.DRDimensionComponent;
import net.sf.dynamicreports.report.base.component.DRTextField;
import net.sf.dynamicreports.report.builder.component.ComponentBuilder;
import net.sf.dynamicreports.report.constant.ChartType;
import net.sf.dynamicreports.report.constant.ComponentDimensionType;
import net.sf.dynamicreports.report.constant.GroupHeaderLayout;
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment;
import net.sf.dynamicreports.report.constant.OrderType;
import net.sf.dynamicreports.report.constant.Orientation;
import net.sf.dynamicreports.report.constant.PageOrientation;
import net.sf.dynamicreports.report.constant.VerticalTextAlignment;
import net.sf.dynamicreports.report.definition.chart.dataset.DRICategoryChartSerie;
import net.sf.dynamicreports.report.definition.style.DRIStyle;
import net.sf.dynamicreports.report.exception.DRException;

import org.junit.Assert;
import org.junit.Test;

/**
 * @author Ricardo Mariaca (r.mariaca@dynamicreports.org)
 */
public class AdhocConfigurationLoadTest {

	@Test
	public void test() {
		try {
			InputStream is = AdhocConfigurationLoadTest.class.getResourceAsStream("adhocconfiguration1.xml");
			AdhocConfiguration adhocConfiguration = AdhocManager.loadConfiguration(is);
			testConfiguration(adhocConfiguration);
		} catch (DRException e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}
	}

	private void testConfiguration(AdhocConfiguration adhocConfiguration) {
		DRReport report = null;
		ReportCustomizer customizer = new ReportCustomizer();
		try {
			JasperReportBuilder reportBuilder = AdhocManager.createReport(adhocConfiguration.getReport(), customizer);
			report = reportBuilder.getReport();
		} catch (DRException e) {
			e.printStackTrace();
			Assert.fail(e.getMessage());
		}

		Assert.assertNotNull("text style", report.getTextStyle());
		Assert.assertNotNull("column style", report.getColumnStyle());
		Assert.assertNotNull("column title style", report.getColumnTitleStyle());
		Assert.assertNotNull("group style", report.getGroupStyle());
		Assert.assertNotNull("group title style", report.getGroupTitleStyle());
		Assert.assertNotNull("subtotal style", report.getSubtotalStyle());
		Assert.assertNotNull("detail odd row style", report.getDetailOddRowStyle());
		Assert.assertTrue("highlight detail odd rows", report.getHighlightDetailOddRows());
		Assert.assertNotNull("detail even row style", report.getDetailEvenRowStyle());
		Assert.assertTrue("highlight detail even rows", report.getHighlightDetailEvenRows());

		DRIStyle style = (DRIStyle) report.getTextStyle();
		Assert.assertEquals("font name", "a", style.getFont().getFontName());
		Assert.assertEquals("font size", 5, style.getFont().getFontSize());
		Assert.assertTrue("bold", style.getFont().getBold());
		Assert.assertTrue("italic", style.getFont().getItalic());
		Assert.assertTrue("underline", style.getFont().getUnderline());
		Assert.assertTrue("strike through", style.getFont().getStrikeThrough());
		Assert.assertNotNull("top border", style.getBorder().getTopPen());
		Assert.assertNotNull("left border", style.getBorder().getLeftPen());
		Assert.assertNotNull("bottom border", style.getBorder().getBottomPen());
		Assert.assertNotNull("right border", style.getBorder().getRightPen());
		Assert.assertEquals("pen line width", 2f, style.getBorder().getTopPen().getLineWidth());
		Assert.assertEquals("pen line color", Color.CYAN, style.getBorder().getTopPen().getLineColor());
		Assert.assertEquals("foreground color", Color.WHITE, style.getForegroundColor());
		Assert.assertEquals("background color", Color.DARK_GRAY, style.getBackgroundColor());
		Assert.assertEquals("horizontal alignment", HorizontalTextAlignment.CENTER, style.getHorizontalTextAlignment());
		Assert.assertEquals("vertical alignment", VerticalTextAlignment.MIDDLE, style.getVerticalTextAlignment());
		Assert.assertEquals("pattern", "#,###.00", style.getPattern());

		DRPage page = report.getPage();
		Assert.assertEquals("page width", 100, page.getWidth());
		Assert.assertEquals("page height", 200, page.getHeight());
		Assert.assertEquals("page orientation", PageOrientation.PORTRAIT, page.getOrientation());
		Assert.assertEquals("top margin", 1, page.getMargin().getTop());
		Assert.assertEquals("bottom margin", 2, page.getMargin().getBottom());
		Assert.assertEquals("left margin", 3, page.getMargin().getLeft());
		Assert.assertEquals("right margin", 4, page.getMargin().getRight());
		Assert.assertTrue("ignore page width", page.getIgnorePageWidth());

		Assert.assertEquals("columns", 3, report.getColumns().size());
		DRColumn<?> column = report.getColumns().get(0);
		Assert.assertEquals("column name", "field1", column.getName());
		Assert.assertNotNull("column title", column.getTitleExpression());
		Assert.assertNotNull("column style", column.getComponent().getStyle());
		Assert.assertNotNull("column title style", column.getTitleStyle());
		Assert.assertEquals("column width", 50, ((DRDimensionComponent) column.getComponent()).getWidth());
		Assert.assertEquals("column width type", ComponentDimensionType.FIXED, ((DRDimensionComponent) column.getComponent()).getWidthType());

		column = report.getColumns().get(2);
		Assert.assertEquals("column name", "field3", column.getName());
		Assert.assertNotNull("column title", column.getTitleExpression());
		Assert.assertNull("column style", column.getComponent().getStyle());
		Assert.assertNull("column title style", column.getTitleStyle());
		Assert.assertNull("column width", ((DRDimensionComponent) column.getComponent()).getWidth());
		Assert.assertNull("column width type", ((DRDimensionComponent) column.getComponent()).getWidthType());

		Assert.assertEquals("groups", 2, report.getGroups().size());
		DRGroup group = report.getGroups().get(0);
		Assert.assertTrue("group start in new page", group.getStartInNewPage());
		Assert.assertEquals("group header layout", GroupHeaderLayout.TITLE_AND_VALUE, group.getHeaderLayout());
		Assert.assertNotNull("group style", group.getValueField().getStyle());
		Assert.assertNotNull("group title style", group.getTitleStyle());

		group = report.getGroups().get(1);
		Assert.assertNull("group start in new page", group.getStartInNewPage());
		Assert.assertNull("group header layout", group.getHeaderLayout());
		Assert.assertNull("group style", group.getValueField().getStyle());
		Assert.assertNull("group title style", group.getTitleStyle());

		Assert.assertEquals("sorts", 2, report.getSorts().size());
		DRSort sort = report.getSorts().get(0);
		Assert.assertNotNull("sort name", sort.getExpression());
		Assert.assertEquals("sort order", OrderType.DESCENDING, sort.getOrderType());

		Assert.assertEquals("subtotals", 2, report.getSubtotals().size());
		DRSubtotal<?> subtotal = report.getSubtotals().get(0);
		Assert.assertNotNull("subtotal label", subtotal.getLabelExpression());
		Assert.assertNotNull("subtotal style", subtotal.getValueField().getStyle());
		Assert.assertNotNull("subtotal label style", subtotal.getLabelStyle());
		subtotal = report.getSubtotals().get(1);
		Assert.assertNull("subtotal label", subtotal.getLabelExpression());
		Assert.assertNull("subtotal style", subtotal.getValueField().getStyle());
		Assert.assertNull("subtotal label style", subtotal.getLabelStyle());

		Assert.assertEquals("components", 3, customizer.getComponents().size());
		Assert.assertTrue("component", adhocConfiguration.getReport().getComponent("textField") instanceof AdhocTextField);
		Assert.assertTrue("component", adhocConfiguration.getReport().getComponent("chart1") instanceof AdhocChart);
		Assert.assertTrue("component", adhocConfiguration.getReport().getComponent("chart2") instanceof AdhocChart);

		DRComponent component = customizer.getComponents().get(0).getComponent();
		Assert.assertEquals("component key", adhocConfiguration.getReport().getComponents().get(0), adhocConfiguration.getReport().getComponent("textField"));
		Assert.assertNotNull("component style", component.getStyle());
		Assert.assertEquals("component width", 150, ((DRDimensionComponent) component).getWidth());
		Assert.assertEquals("component width type", ComponentDimensionType.FIXED, ((DRDimensionComponent) component).getWidthType());
		Assert.assertEquals("component height", 200, ((DRDimensionComponent) component).getHeight());
		Assert.assertEquals("component height type", ComponentDimensionType.FIXED, ((DRDimensionComponent) component).getHeightType());
		DRTextField<?> textField = (DRTextField<?>) component;
		Assert.assertNotNull("textField text", textField.getValueExpression());

		DRChart chart = (DRChart) customizer.getComponents().get(1).getComponent();
		Assert.assertEquals("component key", adhocConfiguration.getReport().getComponents().get(1), adhocConfiguration.getReport().getComponent("chart1"));
		Assert.assertEquals("chart type", ChartType.AREA, chart.getChartType());
		Assert.assertNotNull("chart title", chart.getTitle().getTitle());
		Assert.assertNotNull("chart title font", chart.getTitle().getFont());
		Assert.assertEquals("chart title color", Color.MAGENTA, chart.getTitle().getColor());
		Assert.assertTrue("chart show legend", chart.getLegend().getShowLegend());
		Assert.assertNotNull("chart category", ((DRCategoryDataset) chart.getDataset()).getValueExpression());
		DRICategoryChartSerie chartSerie = (DRICategoryChartSerie) ((DRCategoryDataset) chart.getDataset()).getSeries().get(0);
		Assert.assertNotNull("chart serie series", chartSerie.getSeriesExpression());
		Assert.assertNotNull("chart serie value", chartSerie.getValueExpression());
		Assert.assertNotNull("chart serie label", chartSerie.getLabelExpression());
		Assert.assertEquals("chart series color", Color.LIGHT_GRAY, ((DRAxisPlot) chart.getPlot()).getSeriesColors().get(0));
		Assert.assertNotNull("chart category axis format", ((DRAxisPlot) chart.getPlot()).getXAxisFormat());
		Assert.assertNotNull("chart value axis format", ((DRAxisPlot) chart.getPlot()).getYAxisFormat());
		Assert.assertNotNull("axis format label", ((DRAxisPlot) chart.getPlot()).getXAxisFormat().getLabelExpression());
		Assert.assertNotNull("axis format label font", ((DRAxisPlot) chart.getPlot()).getXAxisFormat().getLabelFont());
		Assert.assertEquals("axis format label color", Color.BLUE, ((DRAxisPlot) chart.getPlot()).getXAxisFormat().getLabelColor());
		Assert.assertEquals("chart orientation", Orientation.VERTICAL, ((DRAxisPlot) chart.getPlot()).getOrientation());
		Assert.assertTrue("chart use series as category", ((DRCategoryDataset) chart.getDataset()).getUseSeriesAsCategory());

		chart = (DRChart) customizer.getComponents().get(2).getComponent();
		Assert.assertEquals("component key", adhocConfiguration.getReport().getComponents().get(2), adhocConfiguration.getReport().getComponent("chart2"));
		Assert.assertNull("component style", chart.getStyle());
		Assert.assertNull("component width", chart.getWidth());
		Assert.assertNull("component width type", chart.getWidthType());
		Assert.assertNull("component height", chart.getHeight());
		Assert.assertNull("component height type", chart.getHeightType());
		Assert.assertNull("chart title", chart.getTitle().getTitle());
		Assert.assertNull("chart title font", chart.getTitle().getFont());
		Assert.assertNull("chart title color", chart.getTitle().getColor());
		Assert.assertNull("chart show legend", chart.getLegend().getShowLegend());
		Assert.assertNotNull("chart category", ((DRCategoryDataset) chart.getDataset()).getValueExpression());
		chartSerie = (DRICategoryChartSerie) ((DRCategoryDataset) chart.getDataset()).getSeries().get(0);
		Assert.assertNull("chart serie series", chartSerie.getSeriesExpression());
		Assert.assertNotNull("chart serie value", chartSerie.getValueExpression());
		Assert.assertNotNull("chart serie label", chartSerie.getLabelExpression());
		Assert.assertEquals("chart series color", 0, ((DRAxisPlot) chart.getPlot()).getSeriesColors().size());
		Assert.assertNull("axis format label", ((DRAxisPlot) chart.getPlot()).getXAxisFormat().getLabelExpression());
		Assert.assertNull("axis format label font", ((DRAxisPlot) chart.getPlot()).getXAxisFormat().getLabelFont());
		Assert.assertNull("axis format label color", ((DRAxisPlot) chart.getPlot()).getXAxisFormat().getLabelColor());
		Assert.assertNull("chart orientation", ((DRAxisPlot) chart.getPlot()).getOrientation());
		Assert.assertNull("chart use series as category", ((DRCategoryDataset) chart.getDataset()).getUseSeriesAsCategory());

		AdhocGroup adhocGroup = adhocConfiguration.getReport().getGroups().get(1);
		Assert.assertEquals("property", "value<a>&&</a>1", adhocGroup.getProperty("key1"));
		Assert.assertEquals("property", "value<a>&&</a>2", adhocGroup.getProperty("key2"));

		Assert.assertEquals("restrictions", 2, adhocConfiguration.getFilter().getRestrictions().size());
		AdhocRestriction adhocRestriction = adhocConfiguration.getFilter().getRestrictions().get(0);
		Assert.assertEquals("restriction key", adhocRestriction, adhocConfiguration.getFilter().getRestriction("restriction1"));
		Assert.assertEquals("restriction key", "restriction1", adhocRestriction.getKey());
		Assert.assertEquals("restriction property", "value", adhocRestriction.getProperty("key"));
		Assert.assertNull("restriction property", adhocRestriction.getProperty("key_empty"));
		Assert.assertTrue("restriction property", (Boolean) adhocRestriction.getProperty("key_boolean"));
		Assert.assertEquals("restriction property", 100, adhocRestriction.getProperty("key_int"));

		AdhocValueRestriction adhocValueRestriction = (AdhocValueRestriction) adhocConfiguration.getFilter().getRestrictions().get(1);
		Assert.assertEquals("restriction key", adhocValueRestriction, adhocConfiguration.getFilter().getRestriction("restriction2"));
		Assert.assertEquals("restriction key", "restriction2", adhocValueRestriction.getKey());
		Assert.assertTrue("restriction property", adhocValueRestriction.getProperties().isEmpty());
		Assert.assertEquals("restriction name", "aa", adhocValueRestriction.getName());
		Assert.assertEquals("restriction operator", AdhocValueOperator.IN, adhocValueRestriction.getOperator());
		Assert.assertEquals("restriction value", "value1", adhocValueRestriction.getValues().get(0));
		Assert.assertEquals("restriction value", "value2", adhocValueRestriction.getValues().get(1));
	}

	private class ReportCustomizer extends DefaultAdhocReportCustomizer {

		private List<ComponentBuilder<?, ?>> getComponents() {
			return new ArrayList<ComponentBuilder<?, ?>>(components.values());
		}
	}
}
