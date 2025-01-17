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

package net.sf.dynamicreports.report.base.component;

import net.sf.dynamicreports.report.constant.Constants;
import net.sf.dynamicreports.report.constant.HorizontalImageAlignment;
import net.sf.dynamicreports.report.constant.ImageScale;
import net.sf.dynamicreports.report.definition.component.DRIImage;
import net.sf.dynamicreports.report.definition.expression.DRIExpression;

import org.apache.commons.lang3.Validate;

/**
 * @author Ricardo Mariaca (r.mariaca@dynamicreports.org)
 */
public class DRImage extends DRHyperLinkComponent implements DRIImage {
	private static final long serialVersionUID = Constants.SERIAL_VERSION_UID;

	private ImageScale imageScale;
	private DRIExpression<?> imageExpression;
	private Boolean usingCache;
	private Boolean lazy;
	private HorizontalImageAlignment horizontalImageAlignment;

	@Override
	public DRIExpression<?> getImageExpression() {
		return imageExpression;
	}

	public void setImageExpression(DRIExpression<?> imageExpression) {
		Validate.notNull(imageExpression, "imageExpression must not be null");
		this.imageExpression = imageExpression;
	}

	@Override
	public ImageScale getImageScale() {
		return imageScale;
	}

	public void setImageScale(ImageScale imageScale) {
		this.imageScale = imageScale;
	}

	@Override
	public Boolean getUsingCache() {
		return usingCache;
	}

	public void setUsingCache(Boolean usingCache) {
		this.usingCache = usingCache;
	}

	@Override
	public Boolean getLazy() {
		return lazy;
	}

	public void setLazy(Boolean lazy) {
		this.lazy = lazy;
	}

	@Override
	public HorizontalImageAlignment getHorizontalImageAlignment() {
		return horizontalImageAlignment;
	}

	public void setHorizontalImageAlignment(HorizontalImageAlignment horizontalImageAlignment) {
		this.horizontalImageAlignment = horizontalImageAlignment;
	}
}

