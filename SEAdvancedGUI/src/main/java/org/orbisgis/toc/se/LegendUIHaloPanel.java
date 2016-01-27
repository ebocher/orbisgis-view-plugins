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
package org.orbisgis.toc.se;

import java.awt.BorderLayout;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import org.orbisgis.coremap.renderer.se.common.Halo;
import org.orbisgis.coremap.renderer.se.parameter.real.RealParameter;
import org.orbisgis.toc.se.components.UomInput;
import org.orbisgis.toc.se.fill.LegendUIMetaFillPanel;
import org.orbisgis.toc.se.icons.SEAdvancedIcon;
import org.orbisgis.toc.se.parameter.real.LegendUIMetaRealPanel;

/**
 *
 * @author Maxence Laurent
 */
public abstract class LegendUIHaloPanel extends LegendUIComponent {

	protected Halo halo;
	private UomInput uomInput;
	private LegendUIMetaRealPanel radius;
	private LegendUIMetaFillPanel fill;

	public LegendUIHaloPanel(LegendUIController ctrl, LegendUIComponent parent, Halo h) {
		super("halo", ctrl, parent, 0, true);

		this.setBorder(BorderFactory.createTitledBorder(getName()));

		this.halo = h;

		if (this.halo == null) {
			this.halo = new Halo();
			this.isNullComponent = true;
		}

		this.radius = new LegendUIMetaRealPanel("Radius", controller, this, halo.getRadius(), false) {

			@Override
			public void realChanged(RealParameter newReal) {
				halo.setRadius(newReal);
			}
		};

		radius.init();

		uomInput = new UomInput(halo);

		fill = new LegendUIMetaFillPanel(controller, this, halo, false);
		fill.init();
	}

	@Override
	public Icon getIcon() {
		return SEAdvancedIcon.getIcon("palette");
	}

	@Override
	protected void mountComponent() {
		editor.add(uomInput, BorderLayout.EAST);
		editor.add(radius, BorderLayout.CENTER);
		editor.add(fill, BorderLayout.SOUTH);
	}

	@Override
	public Class getEditedClass() {
		return Halo.class;
	}

	@Override
	protected void turnOff() {
        this.isNullComponent = true;
		haloChanged(null);
	}

	@Override
	protected void turnOn() {
        this.isNullComponent = false;
		haloChanged(halo);
	}

	protected abstract void haloChanged(Halo halo);
}
