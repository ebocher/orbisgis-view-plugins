/**
 * OrbisGIS is a GIS application dedicated to scientific spatial analysis.
 * This cross-platform GIS is developed at the Lab-STICC laboratory by the DECIDE 
 * team located in University of South Brittany, Vannes.
 * 
 * OrbisGIS is distributed under GPL 3 license.
 *
 * Copyright (C) 2007-2014 IRSTV (FR CNRS 2488)
 * Copyright (C) 2015-2016 CNRS (UMR CNRS 6285)
 *
 * This file is part of OrbisGIS.
 *
 * OrbisGIS is free software: you can redistribute it and/or modify it under the
 * terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * OrbisGIS is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
 * A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with
 * OrbisGIS. If not, see <http://www.gnu.org/licenses/>.
 *
 * For more information, please consult: <http://www.orbisgis.org/>
 * or contact directly:
 * info_at_ orbisgis.org
 */
package org.orbisgis.toc.se.fill;

import java.awt.BorderLayout;
import javax.swing.Icon;
import org.orbisgis.coremap.renderer.se.fill.Fill;
import org.orbisgis.coremap.renderer.se.fill.SolidFill;
import org.orbisgis.coremap.renderer.se.parameter.color.ColorParameter;
import org.orbisgis.coremap.renderer.se.parameter.real.RealParameter;
import org.orbisgis.toc.se.LegendUIComponent;
import org.orbisgis.toc.se.LegendUIController;
import org.orbisgis.toc.se.icons.SEAdvancedIcon;
import org.orbisgis.toc.se.parameter.color.LegendUIMetaColorPanel;
import org.orbisgis.toc.se.parameter.real.LegendUIMetaRealPanel;

/**
 *
 * @author Maxence Laurent
 */
public abstract class LegendUISolidFillPanel extends LegendUIComponent implements LegendUIFillComponent {

	private SolidFill sFill;
	private LegendUIMetaColorPanel mColor;
	private LegendUIMetaRealPanel opacity;

	public LegendUISolidFillPanel(LegendUIController controller, LegendUIComponent parent, final SolidFill sFill, boolean isNullable) {
		super("solid fill", controller, parent, 0, isNullable);
		this.sFill = sFill;
		this.mColor = new LegendUIMetaColorPanelImpl(controller, this, sFill.getColor(), sFill);

		this.opacity = new LegendUIMetaRealPanelImpl(controller, this, sFill.getOpacity(), sFill);
	}

	@Override
	public Fill getFill() {
		return sFill;
	}

	@Override
	public Icon getIcon() {
		return SEAdvancedIcon.getIcon("palette");
	}

	@Override
	protected void mountComponent() {
		editor.add(mColor, BorderLayout.NORTH);
		editor.add(opacity, BorderLayout.SOUTH);
	}

	private static class LegendUIMetaColorPanelImpl extends LegendUIMetaColorPanel {

		private final SolidFill sFill;

		public LegendUIMetaColorPanelImpl(LegendUIController controller, LegendUIComponent parent, ColorParameter c, SolidFill sFill) {
			super("color", controller, parent, c, true);
			this.sFill = sFill;
			init();
		}

		@Override
		public void colorChanged(ColorParameter newColor) {
			sFill.setColor(newColor);
		}
	}

	private static class LegendUIMetaRealPanelImpl extends LegendUIMetaRealPanel {

		private final SolidFill sFill;

		public LegendUIMetaRealPanelImpl(LegendUIController controller, LegendUIComponent parent, RealParameter r, SolidFill sFill) {
			super("opacity", controller, parent, r, true);
			this.sFill = sFill;
			init();
		}

		@Override
		public void realChanged(RealParameter newReal) {
			sFill.setOpacity(newReal);
		}
	}

	@Override
	public Class getEditedClass() {
		return SolidFill.class;
	}


}
