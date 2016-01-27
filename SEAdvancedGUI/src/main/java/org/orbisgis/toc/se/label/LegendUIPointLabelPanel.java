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

import java.awt.BorderLayout;
import javax.swing.Icon;
import org.orbisgis.coremap.renderer.se.label.ExclusionZone;
import org.orbisgis.coremap.renderer.se.label.Label;
import org.orbisgis.coremap.renderer.se.label.PointLabel;
import org.orbisgis.coremap.renderer.se.label.StyledText;
import org.orbisgis.coremap.renderer.se.parameter.real.RealParameter;
import org.orbisgis.toc.se.LegendUIAbstractPanel;
import org.orbisgis.toc.se.LegendUIComponent;
import org.orbisgis.toc.se.LegendUIController;
import org.orbisgis.toc.se.components.ComboBoxInput;
import org.orbisgis.toc.se.components.UomInput;
import org.orbisgis.toc.se.icons.SEAdvancedIcon;
import org.orbisgis.toc.se.parameter.real.LegendUIMetaRealPanel;


/**
 *
 * @author Maxence Laurent
 */
public class LegendUIPointLabelPanel extends LegendUIComponent implements LegendUILabelComponent {

    private PointLabel label;

    private UomInput uom;
    private LegendUIStyledText text;
    private LegendUIMetaExclusionZonePanel zone;
    private LegendUIMetaRealPanel rotation;
    private ComboBoxInput vAlign;
    private ComboBoxInput hAlign;

	public LegendUIPointLabelPanel(LegendUIController controller, LegendUIComponent parent, PointLabel pl, boolean isNullable) {
        super("Point label", controller, parent, 0, isNullable);
        this.label = pl;

        uom = new UomInput(label);

        text = new LegendUIStyledText(controller, parent, label.getLabel(), isNullable) {

            @Override
            public void textChanged(StyledText newText) {
                // Unreachable
                label.setLabel(newText);
            }
        };

        zone = new LegendUIMetaExclusionZonePanel(controller, this, label.getExclusionZone(), true) {

            @Override
            public void zoneChanged(ExclusionZone zone) {
                label.setExclusionZone(zone);
            }
        };
        zone.init();

        rotation = new LegendUIMetaRealPanel("Rotation", controller, this, label.getRotation(), true) {

            @Override
            public void realChanged(RealParameter newReal) {
                label.setRotation(newReal);
            }
        };
        rotation.init();

        vAlign = new ComboBoxInput(Label.VerticalAlignment.getList(), label.getVerticalAlign().ordinal()) {
            @Override
            protected void valueChanged(int i) {
                label.setVerticalAlign(Label.VerticalAlignment.values()[i]);
            }
        };

        hAlign = new ComboBoxInput(Label.HorizontalAlignment.getList(), label.getHorizontalAlign().ordinal()) {
            @Override
            protected void valueChanged(int i) {
                label.setHorizontalAlign(Label.HorizontalAlignment.values()[i]);
            }
        };
    }

    @Override
    public Icon getIcon() {
        return SEAdvancedIcon.getIcon("palette");
    }

    @Override
    protected void mountComponent() {
        LegendUIAbstractPanel content1 = new LegendUIAbstractPanel(controller);
        LegendUIAbstractPanel content2 = new LegendUIAbstractPanel(controller);
        LegendUIAbstractPanel content3 = new LegendUIAbstractPanel(controller);

        content1.add(hAlign, BorderLayout.WEST);
        content1.add(vAlign, BorderLayout.CENTER);
        content1.add(uom, BorderLayout.EAST);

        content2.add(zone, BorderLayout.WEST);
        content2.add(rotation, BorderLayout.EAST);

        content3.add(text, BorderLayout.CENTER);

        editor.add(content1, BorderLayout.NORTH);
        editor.add(content2, BorderLayout.CENTER);
        editor.add(content3, BorderLayout.SOUTH);
    }

    @Override
    protected void turnOff() {
    }

    @Override
    protected void turnOn() {
    }

    @Override
    public Class getEditedClass() {
        return PointLabel.class;
    }

    @Override
    public Label getLabel() {
        return label;
    }
}
