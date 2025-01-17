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

package net.sf.dynamicreports.report.base;

import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.definition.DRIMargin;

import org.apache.commons.lang3.Validate;

/**
 * @author Ricardo Mariaca (r.mariaca@dynamicreports.org)
 */
public class DRMargin implements DRIMargin {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;
	
	private int top;
	private int left;
	private int bottom;
	private int right;

	public DRMargin() {
	}
	
	public DRMargin(int margin) {
		Validate.isTrue(margin >= 0, "margin must be >= 0");
		top = margin;
		left = margin;
		bottom = margin;
		right = margin;
	}

	@Override
	public int getTop() {
		return top;
	}

	public void setTop(int top) {
		Validate.isTrue(top >= 0, "top must be >= 0");
		this.top = top;
	}

	@Override
	public int getLeft() {
		return left;
	}

	public void setLeft(int left) {
		Validate.isTrue(left >= 0, "left must be >= 0");
		this.left = left;
	}

	@Override
	public int getBottom() {
		return bottom;
	}

	public void setBottom(int bottom) {
		Validate.isTrue(bottom >= 0, "bottom must be >= 0");
		this.bottom = bottom;
	}

	@Override
	public int getRight() {
		return right;
	}

	public void setRight(int right) {
		Validate.isTrue(right >= 0, "right must be >= 0");
		this.right = right;
	}
}
