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

package net.sf.dynamicreports.report.base.chart.dataset;

import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.definition.DRIHyperLink;
import net.sf.dynamicreports.report.definition.chart.dataset.DRIHighLowDataset;
import net.sf.dynamicreports.report.definition.expression.DRIExpression;

/**
 * @author Ricardo Mariaca (r.mariaca@dynamicreports.org)
 */
public class DRHighLowDataset extends DRChartDataset implements DRIHighLowDataset {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

	private DRIExpression<?> seriesExpression;
	private DRIExpression<?> dateExpression;
	private DRIExpression<?> highExpression;
	private DRIExpression<?> lowExpression;
	private DRIExpression<?> openExpression;
	private DRIExpression<?> closeExpression;
	private DRIExpression<?> volumeExpression;
	private DRIHyperLink itemHyperLink;

	@Override
	public DRIExpression<?> getSeriesExpression() {
		return seriesExpression;
	}

	public void setSeriesExpression(DRIExpression<?> seriesExpression) {
		this.seriesExpression = seriesExpression;
	}

	@Override
	public DRIExpression<?> getDateExpression() {
		return dateExpression;
	}

	public void setDateExpression(DRIExpression<?> dateExpression) {
		this.dateExpression = dateExpression;
	}

	@Override
	public DRIExpression<?> getHighExpression() {
		return highExpression;
	}

	public void setHighExpression(DRIExpression<?> highExpression) {
		this.highExpression = highExpression;
	}

	@Override
	public DRIExpression<?> getLowExpression() {
		return lowExpression;
	}

	public void setLowExpression(DRIExpression<?> lowExpression) {
		this.lowExpression = lowExpression;
	}

	@Override
	public DRIExpression<?> getOpenExpression() {
		return openExpression;
	}

	public void setOpenExpression(DRIExpression<?> openExpression) {
		this.openExpression = openExpression;
	}

	@Override
	public DRIExpression<?> getCloseExpression() {
		return closeExpression;
	}

	public void setCloseExpression(DRIExpression<?> closeExpression) {
		this.closeExpression = closeExpression;
	}

	@Override
	public DRIExpression<?> getVolumeExpression() {
		return volumeExpression;
	}

	public void setVolumeExpression(DRIExpression<?> volumeExpression) {
		this.volumeExpression = volumeExpression;
	}

	@Override
	public DRIHyperLink getItemHyperLink() {
		return itemHyperLink;
	}

	public void setItemHyperLink(DRIHyperLink itemHyperLink) {
		this.itemHyperLink = itemHyperLink;
	}

}
