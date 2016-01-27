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

import javax.swing.Icon;
import org.orbisgis.coremap.renderer.se.FillNode;
import org.orbisgis.coremap.renderer.se.fill.DensityFill;
import org.orbisgis.coremap.renderer.se.fill.DotMapFill;
import org.orbisgis.coremap.renderer.se.fill.Fill;
import org.orbisgis.coremap.renderer.se.fill.GraphicFill;
import org.orbisgis.coremap.renderer.se.fill.HatchedFill;
import org.orbisgis.coremap.renderer.se.fill.SolidFill;
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
public class LegendUIMetaFillPanel extends LegendUIAbstractMetaPanel {

	private FillNode fNode;
	private LegendUIComponent comp;


	private final Class[] classes = {SolidFill.class, DensityFill.class, GraphicFill.class, DotMapFill.class, HatchedFill.class};

	public LegendUIMetaFillPanel(LegendUIController controller, LegendUIComponent parent, FillNode fillNode, boolean isNullable) {
		super(null, controller, parent, 0, isNullable);

		this.fNode = fillNode;

		Fill f = fNode.getFill();

		comp = null;
		if (f != null) {
			comp = getCompForClass(f.getClass());
		}
	}

	@Override
	protected final LegendUIComponent getCompForClass(Class newClass) {
		Fill f = fNode.getFill();
		if (newClass == SolidFill.class) {
			SolidFill sFill;
			if (f instanceof SolidFill) {
				sFill = (SolidFill) f;
			} else {
				sFill = new SolidFill();
			}
			return new LegendUISolidFillPanel(controller, this, sFill, false) {

				@Override
				protected void turnOff() {
				}

				@Override
				protected void turnOn() {
				}
			};
		} else if (newClass == DensityFill.class) {
			DensityFill dFill;
			if (f instanceof DensityFill) {
				dFill = (DensityFill) f;
			} else {
				dFill = new DensityFill();
			}

			return new LegendUIDensityFillPanel(controller, this, dFill, false) {

				@Override
				protected void turnOff() {
				}

				@Override
				protected void turnOn() {
				}
			};
        } else if (newClass == GraphicFill.class){
            GraphicFill gFill;
            if (f instanceof GraphicFill){
                gFill = (GraphicFill)f;
            } else {
                gFill = new GraphicFill();
            }

            return new LegendUIGraphicFillPanel(controller, this, gFill, false) {
                @Override
                protected void turnOff() {
                }

                @Override
                protected void turnOn() {
                }
            };
        } else if (newClass == DotMapFill.class){
            DotMapFill dFill;
            if (f instanceof DotMapFill){
                dFill = (DotMapFill)f;
            } else {
                dFill = new DotMapFill();
            }

            return new LegendUIDotMapFillPanel(controller, this, dFill, false) {
                @Override
                protected void turnOff() {
                }

                @Override
                protected void turnOn() {
                }
            };
        } else if (newClass == HatchedFill.class){
            HatchedFill hFill;
            if (f instanceof HatchedFill){
                hFill = (HatchedFill)f;
            } else {
                hFill = new HatchedFill();
            }

            return new LegendUIHatchedFillPanel(controller, this, hFill, false) {
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
			Fill f = ((LegendUIFillComponent) comp).getFill();
			fNode.setFill(f);
		} else {
			fNode.setFill(null);
		}
	}

	@Override
	public Class getEditedClass() {
		return fNode.getFill().getClass();
	}
	//public abstract void fillChanged(Fill newFill);
}
