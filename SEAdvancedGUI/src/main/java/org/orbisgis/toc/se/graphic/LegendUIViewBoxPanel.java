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
package org.orbisgis.toc.se.graphic;

import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import org.orbisgis.coremap.renderer.se.graphic.ViewBox;
import org.orbisgis.coremap.renderer.se.parameter.real.RealParameter;
import org.orbisgis.toc.se.LegendUIComponent;
import org.orbisgis.toc.se.LegendUIController;
import org.orbisgis.toc.se.icons.SEAdvancedIcon;
import org.orbisgis.view.toc.actions.cui.parameter.real.LegendUIMetaRealPanel;

/**
 *
 * @author Maxence Laurent
 */
public abstract class LegendUIViewBoxPanel extends LegendUIComponent {

	protected ViewBox viewbox;
	private LegendUIMetaRealPanel width;
	private LegendUIMetaRealPanel height;

	public LegendUIViewBoxPanel(LegendUIController controller, LegendUIComponent parent,
            ViewBox vbox, boolean isNullable) {
		super("View Box", controller, parent, 0, isNullable);
		this.viewbox = vbox;

		if (viewbox == null){
			viewbox = new ViewBox();
			isNullable = true;
            this.isNullComponent = true;
		}

		this.setBorder(BorderFactory.createTitledBorder(getName()));

		height = new LegendUIMetaRealPanel("h", controller, this, viewbox.getHeight(), true) {

			@Override
			public void realChanged(RealParameter newReal) {
				viewbox.setHeight(newReal);
			}
		};
		height.init();

		width = new LegendUIMetaRealPanel("w", controller, this, viewbox.getWidth(), true) {

			@Override
			public void realChanged(RealParameter newReal) {
				viewbox.setWidth(newReal);
			}
		};
		width.init();
	}

	@Override
	public Icon getIcon() {
        return SEAdvancedIcon.getIcon("palette");
	}

	@Override
	protected void mountComponent() {
		editor.add(width, BorderLayout.NORTH);
		editor.add(height, BorderLayout.SOUTH);
	}

	@Override
	public Class getEditedClass() {
		return ViewBox.class;
	}

	@Override
	protected void turnOff() {
		viewBoxChanged(null);
        this.isNullComponent = true;
	}

	@Override
	protected void turnOn() {
		viewBoxChanged(viewbox);
        this.isNullComponent = false;
	}

	public abstract void viewBoxChanged(ViewBox newViewBox);
}
