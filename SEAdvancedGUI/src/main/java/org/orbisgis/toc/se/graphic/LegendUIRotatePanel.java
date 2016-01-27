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
import javax.swing.Icon;
import org.orbisgis.coremap.renderer.se.parameter.real.RealParameter;
import org.orbisgis.coremap.renderer.se.transform.Rotate;
import org.orbisgis.toc.se.LegendUIComponent;
import org.orbisgis.toc.se.LegendUIController;
import org.orbisgis.toc.se.icons.SEAdvancedIcon;
import org.orbisgis.view.toc.actions.cui.parameter.real.LegendUIMetaRealPanel;


/**
 *
 * @author Maxence Laurent
 */
class LegendUIRotatePanel extends LegendUIComponent{

    private Rotate rotate;
    private LegendUIMetaRealPanel x;
    private LegendUIMetaRealPanel y;
    private LegendUIMetaRealPanel angle;

    public LegendUIRotatePanel(LegendUIController controller, LegendUITransformPanel parent, Rotate r) {
        super("", controller, parent, 0, false);
        this.rotate = r;

        x = new LegendUIMetaRealPanel("X", controller, this, rotate.getX(), true) {

            @Override
            public void realChanged(RealParameter newReal) {
                rotate.setX(newReal);
            }
        };
        x.init();

        y = new LegendUIMetaRealPanel("Y", controller, this, rotate.getY(), true) {

            @Override
            public void realChanged(RealParameter newReal) {
                rotate.setY(newReal);
            }
        };
        y.init();

        angle = new LegendUIMetaRealPanel("Angle", controller, this, rotate.getRotation(), isNullComponent) {

            @Override
            public void realChanged(RealParameter newReal) {
                rotate.setRotation(newReal);
            }
        };
        angle.init();
    }

    @Override
    public Icon getIcon() {
        return SEAdvancedIcon.getIcon("palette");
    }

    @Override
    protected void mountComponent() {
        editor.add(x, BorderLayout.WEST);
        editor.add(y, BorderLayout.CENTER);
        editor.add(angle, BorderLayout.EAST);
    }

    @Override
    protected void turnOff() {
    }

    @Override
    protected void turnOn() {
    }

    @Override
    public Class getEditedClass() {
        return Rotate.class;
    }
}
