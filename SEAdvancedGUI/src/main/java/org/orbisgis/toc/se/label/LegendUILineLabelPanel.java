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
import org.orbisgis.coremap.renderer.se.label.Label;
import org.orbisgis.coremap.renderer.se.label.LineLabel;
import org.orbisgis.coremap.renderer.se.label.StyledText;
import org.orbisgis.toc.se.LegendUIAbstractPanel;
import org.orbisgis.toc.se.LegendUIComponent;
import org.orbisgis.toc.se.LegendUIController;
import org.orbisgis.toc.se.components.ComboBoxInput;
import org.orbisgis.toc.se.components.UomInput;
import org.orbisgis.toc.se.icons.SEAdvancedIcon;


/**
 *
 * @author Maxence Laurent
 */
public class LegendUILineLabelPanel extends LegendUIComponent implements LegendUILabelComponent {

    private LineLabel label;

    private UomInput uom;
    private LegendUIStyledText text;
    private ComboBoxInput vAlign;
    private ComboBoxInput hAlign;

	public LegendUILineLabelPanel(LegendUIController controller, LegendUIComponent parent, LineLabel pl, boolean isNullable) {
        super("Line label", controller, parent, 0, isNullable);
        this.label = pl;

        label.getLabel();

        uom = new UomInput(label);

        text = new LegendUIStyledText(controller, parent, label.getLabel(), isNullable) {

            @Override
            public void textChanged(StyledText newText) {
                // Unreachable
                label.setLabel(newText);
            }
        };

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

        content1.add(hAlign, BorderLayout.WEST);
        content1.add(vAlign, BorderLayout.CENTER);
        content1.add(uom, BorderLayout.EAST);

        content2.add(text, BorderLayout.CENTER);

        editor.add(content1, BorderLayout.NORTH);
        editor.add(content2, BorderLayout.SOUTH);
    }

    @Override
    protected void turnOff() {
    }

    @Override
    protected void turnOn() {
    }

    @Override
    public Class getEditedClass() {
        return LineLabel.class;
    }

    @Override
    public Label getLabel() {
        return label;
    }
}
