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

import javax.swing.Icon;
import org.orbisgis.coremap.renderer.se.common.OnlineResource;
import org.orbisgis.coremap.renderer.se.graphic.MarkGraphic;
import org.orbisgis.coremap.renderer.se.graphic.WellKnownName;
import org.orbisgis.coremap.renderer.se.parameter.string.StringParameter;
import org.orbisgis.toc.se.LegendUIAbstractMetaPanel;
import org.orbisgis.toc.se.LegendUIComponent;
import org.orbisgis.toc.se.LegendUIController;
import org.orbisgis.toc.se.icons.SEAdvancedIcon;
import org.orbisgis.toc.se.parameter.string.LegendUIMetaStringPanel;

/**
 *
 * @author Maxence Laurent
 */
public class LegendUIMetaMarkSource extends LegendUIAbstractMetaPanel {

    private LegendUIComponent comp;
    private MarkGraphic mark;
    private final Class[] classes = {WellKnownName.class};

    public LegendUIMetaMarkSource(LegendUIController ctrl, LegendUIComponent parent, MarkGraphic mark) {
        super(null, ctrl, parent, 0, false);
        this.mark = mark;


        comp = null;
        if (mark.getWkn() != null) {
            comp = getCompForClass(WellKnownName.class);
        } else if (mark.getOnlineResource() != null) {
            comp = getCompForClass(OnlineResource.class);
        }
    }

    @Override
    protected final LegendUIComponent getCompForClass(Class newClass) {
        if (newClass == WellKnownName.class) {
            LegendUIMetaStringPanel wkn = new LegendUIMetaStringPanel("WKN", controller, this, mark.getWkn(), false) {

                @Override
                public void stringChanged(StringParameter newString) {
                    mark.setWkn(newString);
                }
            };
            wkn.init();
            return wkn;
        } else {
            return null;
        }
    }

    @Override
    public Icon getIcon() {
        return SEAdvancedIcon.getIcon("palette");
    }

    @Override
    public void init() {
        init(classes, comp);
    }

    @Override
    protected void switchTo(LegendUIComponent newActiveComp) {
        //this.mark TODO
    }

    @Override
    public Class getEditedClass() {
        if (mark.getWkn() != null) {
            return WellKnownName.class;
        } else {
            return null;
        }
    }
}
