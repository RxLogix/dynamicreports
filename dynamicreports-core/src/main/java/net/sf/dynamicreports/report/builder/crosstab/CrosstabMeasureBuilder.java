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

package net.sf.dynamicreports.report.builder.crosstab;

import net.sf.dynamicreports.report.base.crosstab.DRCrosstabCellStyle;
import net.sf.dynamicreports.report.base.crosstab.DRCrosstabMeasure;
import net.sf.dynamicreports.report.base.crosstab.DRCrosstabVariable;
import net.sf.dynamicreports.report.builder.AbstractBuilder;
import net.sf.dynamicreports.report.builder.FieldBuilder;
import net.sf.dynamicreports.report.builder.HyperLinkBuilder;
import net.sf.dynamicreports.report.builder.column.ValueColumnBuilder;
import net.sf.dynamicreports.report.builder.datatype.DataTypes;
import net.sf.dynamicreports.report.builder.expression.Expressions;
import net.sf.dynamicreports.report.builder.style.ReportStyleBuilder;
import net.sf.dynamicreports.report.constant.Calculation;
import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.constant.CrosstabPercentageType;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment;
import net.sf.dynamicreports.report.definition.DRICrosstabValue;
import net.sf.dynamicreports.report.definition.DRIField;
import net.sf.dynamicreports.report.definition.component.DRITextField;
import net.sf.dynamicreports.report.definition.datatype.DRIDataType;
import net.sf.dynamicreports.report.definition.expression.DRIExpression;
import net.sf.dynamicreports.report.definition.expression.DRIPropertyExpression;
import net.sf.dynamicreports.report.definition.expression.DRIValueFormatter;
import net.sf.dynamicreports.report.exception.DRReportException;

import org.apache.commons.lang3.Validate;

/**
 * @author Ricardo Mariaca (r.mariaca@dynamicreports.org)
 */
@SuppressWarnings("deprecation")
public class CrosstabMeasureBuilder<T> extends AbstractBuilder<CrosstabMeasureBuilder<T>, DRCrosstabMeasure<T>> implements DRICrosstabValue<T> {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

	protected CrosstabMeasureBuilder(ValueColumnBuilder<?, ?> column, Calculation calculation) {
		super(new DRCrosstabMeasure<T>(Crosstabs.variable(column, calculation).build()));
		if (calculation.equals(Calculation.COUNT) || calculation.equals(Calculation.DISTINCT_COUNT)) {
			setDataType(DataTypes.longType());
		}
		else if (calculation.equals(Calculation.AVERAGE) || calculation.equals(Calculation.STANDARD_DEVIATION) ||
				calculation.equals(Calculation.VARIANCE)) {
			setDataType(DataTypes.doubleType());
		}
		else {
			DRITextField<?> columnComponent = column.getColumn().getComponent();
			setDataType(columnComponent.getDataType());
			setPattern(columnComponent.getPattern());
		}
	}

	protected CrosstabMeasureBuilder(FieldBuilder<?> field, Calculation calculation) {
		super(new DRCrosstabMeasure<T>(Crosstabs.variable(field, calculation).build()));
		if (calculation.equals(Calculation.COUNT) || calculation.equals(Calculation.DISTINCT_COUNT)) {
			setDataType(DataTypes.longType());
		}
		else if (calculation.equals(Calculation.AVERAGE) || calculation.equals(Calculation.STANDARD_DEVIATION) ||
				calculation.equals(Calculation.VARIANCE)) {
			setDataType(DataTypes.doubleType());
		}
		else {
			setDataType(field.getField().getDataType());
		}
	}

	protected CrosstabMeasureBuilder(DRIExpression<?> expression, Calculation calculation) {
		super(new DRCrosstabMeasure<T>(Crosstabs.variable(expression, calculation).build()));
		if (calculation.equals(Calculation.COUNT) || calculation.equals(Calculation.DISTINCT_COUNT)) {
			setDataType(DataTypes.longType());
		}
		else if (calculation.equals(Calculation.AVERAGE) || calculation.equals(Calculation.STANDARD_DEVIATION) ||
				calculation.equals(Calculation.VARIANCE)) {
			setDataType(DataTypes.doubleType());
		}
		else if (expression instanceof DRIField) {
			setDataType(((DRIField<?>) expression).getDataType());
		}
	}

	protected CrosstabMeasureBuilder(DRIExpression<?> expression) {
		super(new DRCrosstabMeasure<T>(expression));
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public CrosstabMeasureBuilder<T> setDataType(DRIDataType dataType) {
		getObject().setDataType(dataType);
		return this;
	}

	public CrosstabMeasureBuilder<T> setPercentageType(CrosstabPercentageType percentageType) {
		if (getObject().getExpression() instanceof DRCrosstabVariable<?>) {
			DRCrosstabVariable<?> variable = ((DRCrosstabVariable<?>) getObject().getExpression());
			if (percentageType != null && percentageType.equals(CrosstabPercentageType.GRAND_TOTAL) &&
					!variable.getCalculation().equals(Calculation.COUNT) && !variable.getCalculation().equals(Calculation.DISTINCT_COUNT)) {
				setDataType(DataTypes.doubleType());
			}
			variable.setPercentageType(percentageType);
		}
		else {
			throw new DRReportException("Percentage is not supported in this type of measure");
		}
		return this;
	}

	public CrosstabMeasureBuilder<T> setPattern(String pattern) {
		getObject().setPattern(pattern);
		return this;
	}

	/**
	 * @deprecated use setHorizontalTextAlignment instead
	 */
	@Deprecated
	public CrosstabMeasureBuilder<T> setHorizontalAlignment(HorizontalAlignment horizontalAlignment) {
		if (horizontalAlignment != null) {
			getObject().setHorizontalTextAlignment(HorizontalTextAlignment.valueOf(horizontalAlignment.name()));
		}
		else {
			getObject().setHorizontalTextAlignment(null);
		}
		return this;
	}

	public CrosstabMeasureBuilder<T> setHorizontalTextAlignment(HorizontalTextAlignment horizontalTextAlignment) {
		getObject().setHorizontalTextAlignment(horizontalTextAlignment);
		return this;
	}

	public CrosstabMeasureBuilder<T> setValueFormatter(DRIValueFormatter<?, ? super T> valueFormatter) {
		getObject().setValueFormatter(valueFormatter);
		return this;
	}

	public CrosstabMeasureBuilder<T> setStretchWithOverflow(Boolean stretchWithOverflow) {
		getObject().setStretchWithOverflow(stretchWithOverflow);
		return this;
	}

	/**
	 * Sets the crosstab measure hyperlink.
	 *
	 * @param hyperLink the measure hyperlink
	 * @return a crosstab measure builder
	 */
	public CrosstabMeasureBuilder<T> setHyperLink(HyperLinkBuilder hyperLink) {
		if (hyperLink != null) {
			getObject().setHyperLink(hyperLink.getHyperLink());
		}
		else {
			getObject().setHyperLink(null);
		}
		return this;
	}

	/**
	 * Adds a jasper property to the measure.
	 *
	 * @param propertyExpression the property expression
	 * @return a crosstab measure builder
	 */
	public CrosstabMeasureBuilder<T> addProperty(DRIPropertyExpression propertyExpression) {
		getObject().addPropertyExpression(propertyExpression);
		return this;
	}

	/**
	 * Adds a jasper property to the measure.
	 *
	 * @param name the property name
	 * @param valueExpression the property value expression
	 * @return a crosstab measure builder
	 */
	public CrosstabMeasureBuilder<T> addProperty(String name, DRIExpression<String> valueExpression) {
		getObject().addPropertyExpression(Expressions.property(name, valueExpression));
		return this;
	}

	/**
	 * Adds a jasper property to the measure.
	 *
	 * @param name the property name
	 * @param value the property value
	 * @return a crosstab measure builder
	 */
	public CrosstabMeasureBuilder<T> addProperty(String name, String value) {
		getObject().addPropertyExpression(Expressions.property(name, value));
		return this;
	}

	public CrosstabMeasureBuilder<T> setStyle(ReportStyleBuilder style) {
		Validate.notNull(style, "style must not be null");
		getObject().getStyles().add(new DRCrosstabCellStyle(style.getStyle()));
		return this;
	}

	public CrosstabMeasureBuilder<T> setStyle(ReportStyleBuilder style, CrosstabRowGroupBuilder<?> rowGroup) {
		Validate.notNull(style, "style must not be null");
		Validate.notNull(rowGroup, "rowGroup must not be null");
		getObject().getStyles().add(new DRCrosstabCellStyle(style.getStyle(), rowGroup.build(), null));
		return this;
	}

	public CrosstabMeasureBuilder<T> setStyle(ReportStyleBuilder style, CrosstabColumnGroupBuilder<?> columnGroup) {
		Validate.notNull(style, "style must not be null");
		Validate.notNull(columnGroup, "columnGroup must not be null");
		getObject().getStyles().add(new DRCrosstabCellStyle(style.getStyle(), null, columnGroup.build()));
		return this;
	}

	public CrosstabMeasureBuilder<T> setStyle(ReportStyleBuilder style, CrosstabRowGroupBuilder<?> rowGroup, CrosstabColumnGroupBuilder<?> columnGroup) {
		Validate.notNull(style, "style must not be null");
		Validate.notNull(rowGroup, "rowGroup must not be null");
		Validate.notNull(columnGroup, "columnGroup must not be null");
		getObject().getStyles().add(new DRCrosstabCellStyle(style.getStyle(), rowGroup.build(), columnGroup.build()));
		return this;
	}

	public CrosstabMeasureBuilder<T> setTitle(DRIExpression<?> titleExpression) {
		getObject().setTitleExpression(titleExpression);
		return this;
	}

	public CrosstabMeasureBuilder<T> setTitle(String title) {
		getObject().setTitleExpression(Expressions.text(title));
		return this;
	}

	public CrosstabMeasureBuilder<T> setTitleStyle(ReportStyleBuilder titleStyle) {
		if (titleStyle != null) {
			getObject().setTitleStyle(titleStyle.getStyle());
		}
		else {
			getObject().setTitleStyle(null);
		}
		return this;
	}

	@Override
	public String getName() {
		return build().getExpression().getName();
	}
}
