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

package net.sf.dynamicreports.report.base.chart.plot;

import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.definition.chart.plot.DRIHighLowPlot;

/**
 * @author Ricardo Mariaca (r.mariaca@dynamicreports.org)
 */
public class DRHighLowPlot extends DRAxisPlot implements DRIHighLowPlot {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

	private Boolean showOpenTicks;
	private Boolean showCloseTicks;

	@Override
	public Boolean getShowOpenTicks() {
		return showOpenTicks;
	}

	public void setShowOpenTicks(Boolean showOpenTicks) {
		this.showOpenTicks = showOpenTicks;
	}

	@Override
	public Boolean getShowCloseTicks() {
		return showCloseTicks;
	}

	public void setShowCloseTicks(Boolean showCloseTicks) {
		this.showCloseTicks = showCloseTicks;
	}
}
