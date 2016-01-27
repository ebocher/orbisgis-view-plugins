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
package org.orbisgis.toc.se.label;

import javax.swing.Icon;
import org.orbisgis.coremap.renderer.se.label.ExclusionRadius;
import org.orbisgis.coremap.renderer.se.label.ExclusionRectangle;
import org.orbisgis.coremap.renderer.se.label.ExclusionZone;
import org.orbisgis.toc.se.LegendUIAbstractMetaPanel;
import org.orbisgis.toc.se.LegendUIComponent;
import org.orbisgis.toc.se.LegendUIController;
import org.orbisgis.toc.se.icons.SEAdvancedIcon;

/**
 * Meta-Panel for fill edition
 * This panel will provide the ability to select fill type
 *
 * @author Maxence Laurent
 */
public abstract class LegendUIMetaExclusionZonePanel extends LegendUIAbstractMetaPanel {

	private ExclusionZone zone;
	private LegendUIComponent comp;


	private final Class[] classes = {ExclusionRadius.class, ExclusionRectangle.class};

	public LegendUIMetaExclusionZonePanel(LegendUIController controller, LegendUIComponent parent, ExclusionZone zone, boolean isNullable) {
		super("Exclusion zone", controller, parent, 0, isNullable);

        this.zone = zone;

		comp = null;
		if (zone != null) {
			comp = getCompForClass(zone.getClass());
		}
	}

	@Override
	protected final LegendUIComponent getCompForClass(Class newClass) {
		if (newClass == ExclusionRadius.class) {
			ExclusionRadius radius;
			if (zone instanceof ExclusionRadius) {
                radius = (ExclusionRadius) zone;
			} else {
                radius = new ExclusionRadius();
			}
			return new LegendUIExclusionRadiusPanel("point Label", controller, this, radius, false) {

                // Unreachable !
				@Override
				protected void turnOff() {
				}

				@Override
				protected void turnOn() {
				}
			};
		} else if (newClass == ExclusionRectangle.class){
			ExclusionRectangle rect;
			if (zone instanceof ExclusionRectangle) {
                rect = (ExclusionRectangle) zone;
			} else {
                rect = new ExclusionRectangle();
			}
			return new LegendUIExclusionRectanglePanel("point Label", controller, this, rect, false) {

                // Unreachable !
				@Override
				protected void turnOff() {
				}

				@Override
				protected void turnOn() {
				}
			};
		} else {
			return null;
		}
	}

	@Override
	public void init() {
		init(classes, comp);
	}

	@Override
	public Icon getIcon() {
        return SEAdvancedIcon.getIcon("palette");
	}

	@Override
	protected void switchTo(LegendUIComponent comp) {
		if (comp != null){
			this.zone = ((LegendUIExclusionZoneComponent) comp).getExclusionZone();
            zoneChanged(zone);
		} else {
            zoneChanged(null);
		}
	}

	@Override
	public Class getEditedClass() {
		return zone.getClass();
	}

    public abstract void zoneChanged(ExclusionZone zone);
}
