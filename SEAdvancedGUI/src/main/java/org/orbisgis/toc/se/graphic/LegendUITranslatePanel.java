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
import org.orbisgis.coremap.renderer.se.parameter.real.RealParameter;
import org.orbisgis.coremap.renderer.se.transform.Translate;
import org.orbisgis.toc.se.LegendUIComponent;
import org.orbisgis.toc.se.LegendUIController;
import org.orbisgis.toc.se.icons.SEAdvancedIcon;
import org.orbisgis.view.toc.actions.cui.parameter.real.LegendUIMetaRealPanel;

/**
 *
 * @author Maxence Laurent
 */
public abstract class LegendUITranslatePanel extends LegendUIComponent {

    private Translate translate;


    private LegendUIMetaRealPanel x;


    private LegendUIMetaRealPanel y;


    public LegendUITranslatePanel(String name, LegendUIController controller,
                                  LegendUIComponent parent, Translate t,
                                  boolean nullable) {
        super(name, controller, parent, 0, nullable);

        if (name != null && !name.isEmpty()) {
            this.setBorder(BorderFactory.createTitledBorder(getName()));
        }

        this.translate = t;

        if (translate == null) {
            translate = new Translate(null, null);
            this.isNullComponent = true;
        }

        x = new LegendUIMetaRealPanel("X", controller, this, translate.getX(), true) {

            @Override
            public void realChanged(RealParameter newReal) {
                translate.setX(newReal);
            }


        };
        x.init();

        y = new LegendUIMetaRealPanel("Y", controller, this, translate.getY(), true) {

            @Override
            public void realChanged(RealParameter newReal) {
                translate.setY(newReal);
            }


        };
        y.init();
    }


    public LegendUITranslatePanel(LegendUIController controller,
                                  LegendUITransformPanel parent, Translate t) {
        this("", controller, parent, t, false);
    }


    @Override
    public Icon getIcon() {
        return SEAdvancedIcon.getIcon("palette");
    }


    @Override
    protected void mountComponent() {
        editor.add(x, BorderLayout.WEST);
        editor.add(y, BorderLayout.EAST);
    }


    @Override
    protected void turnOff() {
        this.isNullComponent = true;
        translateChange(null);
    }


    @Override
    protected void turnOn() {
        this.isNullComponent = false;
        translateChange(translate);
    }


    @Override
    public Class getEditedClass() {
        return Translate.class;
    }


    public abstract void translateChange(Translate newTranslate);


}
