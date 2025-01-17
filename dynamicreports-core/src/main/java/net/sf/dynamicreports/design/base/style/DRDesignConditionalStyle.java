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

package net.sf.dynamicreports.design.base.style;

import net.sf.dynamicreports.design.definition.DRIDesignDataset;
import net.sf.dynamicreports.design.definition.expression.DRIDesignExpression;
import net.sf.dynamicreports.design.definition.style.DRIDesignConditionalStyle;
import net.sf.dynamicreports.report.constant.Constants;

import org.apache.commons.lang3.builder.EqualsBuilder;

/**
 * @author Ricardo Mariaca (r.mariaca@dynamicreports.org)
 */
public class DRDesignConditionalStyle extends DRDesignBaseStyle implements DRIDesignConditionalStyle {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

	private DRIDesignExpression conditionExpression;
	private DRIDesignDataset dataset;

	public void setConditionExpression(DRIDesignExpression conditionExpression) {
		this.conditionExpression = conditionExpression;
	}

	@Override
	public DRIDesignExpression getConditionExpression() {
		return conditionExpression;
	}

	@Override
	public DRIDesignDataset getDataset() {
		return dataset;
	}

	public void setDataset(DRIDesignDataset dataset) {
		this.dataset = dataset;
	}

	@Override
	public boolean equals(Object obj) {
		EqualsBuilder equalsBuilder = new EqualsBuilder().appendSuper(super.equals(obj));
		if (equalsBuilder.isEquals()) {
			DRDesignConditionalStyle o = (DRDesignConditionalStyle) obj;
			equalsBuilder
				.append(conditionExpression, o.conditionExpression)
				.append(dataset, o.dataset);
		}
		return equalsBuilder.isEquals();
	}
}
