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

package net.sf.dynamicreports.design.definition.chart.plot;

import java.awt.Color;
import java.io.Serializable;

import net.sf.dynamicreports.design.definition.expression.DRIDesignExpression;
import net.sf.dynamicreports.design.definition.style.DRIDesignFont;

/**
 * @author Ricardo Mariaca (r.mariaca@dynamicreports.org)
 */
public interface DRIDesignAxisFormat extends Serializable {

	public DRIDesignExpression getLabelExpression();

	public DRIDesignFont getLabelFont();

	public Color getLabelColor();

	public DRIDesignFont getTickLabelFont();

	public Color getTickLabelColor();

	public String getTickLabelMask();

	public Boolean getVerticalTickLabels();

	public Double getTickLabelRotation();

	public Color getLineColor();

	public DRIDesignExpression getRangeMinValueExpression();

	public DRIDesignExpression getRangeMaxValueExpression();
}
