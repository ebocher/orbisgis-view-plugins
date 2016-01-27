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
import org.orbisgis.coremap.renderer.se.fill.HatchedFill;
import org.orbisgis.coremap.renderer.se.parameter.real.RealParameter;
import org.orbisgis.toc.se.LegendUIAbstractPanel;
import org.orbisgis.toc.se.LegendUIComponent;
import org.orbisgis.toc.se.LegendUIController;
import org.orbisgis.toc.se.icons.SEAdvancedIcon;
import org.orbisgis.toc.se.parameter.real.LegendUIMetaRealPanel;
import org.orbisgis.toc.se.stroke.LegendUIMetaStrokePanel;

/**
 *
 * @author Maxence Laurent
 */
public abstract class LegendUIHatchedFillPanel extends LegendUIComponent implements LegendUIFillComponent {

    private HatchedFill hFill;
    private LegendUIMetaStrokePanel stroke;
    private LegendUIMetaRealPanel angle;
    private LegendUIMetaRealPanel distance;
    private LegendUIMetaRealPanel offset;
    
    public LegendUIHatchedFillPanel(LegendUIController ctrl, LegendUIComponent parent, final HatchedFill hFill, boolean isNullable) {
        super("Hatched fill", ctrl, parent, 0, isNullable);
        this.hFill = hFill;

        stroke = new LegendUIMetaStrokePanel(controller, this, hFill, false);
        stroke.init();

        angle = new LegendUIMetaRealPanel("Angle", controller, this, hFill.getAngle(), true) {

            @Override
            public void realChanged(RealParameter newReal) {
                hFill.setAngle(newReal);
            }
        };
        angle.init();

        distance = new LegendUIMetaRealPanel("Distance", controller, this, hFill.getDistance(), true) {

            @Override
            public void realChanged(RealParameter newReal) {
                hFill.setDistance(newReal);
            }
        };
        distance.init();

        offset = new LegendUIMetaRealPanel("Offset", controller, this, hFill.getOffset(), true) {

            @Override
            public void realChanged(RealParameter newReal) {
                hFill.setOffset(newReal);
            }
        };
        offset.init();
    }

    @Override
    public Fill getFill() {
        return hFill;
    }

    @Override
    public Icon getIcon() {
        return SEAdvancedIcon.getIcon("palette");
    }

    @Override
    protected void mountComponent() {
        LegendUIAbstractPanel header = new LegendUIAbstractPanel(controller);

        header.add(angle, BorderLayout.WEST);
        header.add(distance, BorderLayout.CENTER);
        header.add(offset, BorderLayout.EAST);

        editor.add(header, BorderLayout.NORTH);
        editor.add(stroke, BorderLayout.SOUTH);
    }

    @Override
    public Class getEditedClass() {
        return HatchedFill.class;
    }
}
