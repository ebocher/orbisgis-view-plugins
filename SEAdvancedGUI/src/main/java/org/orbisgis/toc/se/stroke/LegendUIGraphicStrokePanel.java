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
package org.orbisgis.toc.se.stroke;

import java.awt.BorderLayout;
import javax.swing.Icon;
import org.orbisgis.coremap.renderer.se.common.RelativeOrientation;
import org.orbisgis.coremap.renderer.se.parameter.real.RealParameter;
import org.orbisgis.coremap.renderer.se.stroke.GraphicStroke;
import org.orbisgis.coremap.renderer.se.stroke.Stroke;
import org.orbisgis.toc.se.LegendUIAbstractPanel;
import org.orbisgis.toc.se.LegendUIComponent;
import org.orbisgis.toc.se.LegendUIController;
import org.orbisgis.toc.se.components.CheckBoxInput;
import org.orbisgis.toc.se.components.ComboBoxInput;
import org.orbisgis.toc.se.graphic.LegendUICompositeGraphicPanel;
import org.orbisgis.toc.se.icons.SEAdvancedIcon;
import org.orbisgis.toc.se.parameter.real.LegendUIMetaRealPanel;


/**
 *
 * @author Maxence Laurent
 */
public abstract class LegendUIGraphicStrokePanel extends LegendUIComponent implements LegendUIStrokeComponent {

    private GraphicStroke graphicStroke;


    private LegendUICompositeGraphicPanel gCollection;


    private LegendUIMetaRealPanel length;


    private LegendUIMetaRealPanel relPos;


    private ComboBoxInput orientation;


    private LegendUIAbstractPanel content1;

    private CheckBoxInput linearRapport;

    public LegendUIGraphicStrokePanel(LegendUIController controller,
                                      LegendUIComponent parent,
                                      GraphicStroke gStroke, boolean isNullable) {
        super("Graphic Stroke", controller, parent, 0, isNullable);

        this.graphicStroke = gStroke;


        gCollection = new LegendUICompositeGraphicPanel(controller, this, graphicStroke.getGraphicCollection());


        length = new LegendUIMetaRealPanel("Length", controller, this, graphicStroke.getLength(), true) {

            @Override
            public void realChanged(RealParameter newReal) {
                graphicStroke.setLength(newReal);
            }


        };
        length.init();

        relPos = new LegendUIMetaRealPanel("Position", controller, this, graphicStroke.getRelativePosition(), true) {

            @Override
            public void realChanged(RealParameter newReal) {
                graphicStroke.setRelativePosition(newReal);
            }


        };
        relPos.init();

        orientation = new ComboBoxInput(RelativeOrientation.values(), graphicStroke.getRelativeOrientation().ordinal()) {

            @Override
            protected void valueChanged(int i) {
                graphicStroke.setRelativeOrientation(RelativeOrientation.values()[i]);
            }


        };

        this.linearRapport = new CheckBoxInput("LinRap.", graphicStroke.isLengthRapport()) {
            @Override
            protected void valueChanged(boolean newValue) {
                graphicStroke.setLengthRapport(newValue);
            }
        };


        this.content1 = new LegendUIAbstractPanel(controller);
    }


    @Override
    public Icon getIcon() {
        return SEAdvancedIcon.getIcon("palette");
    }


    @Override
    protected void mountComponent() {
        editor.add(orientation, BorderLayout.NORTH);

        content1.removeAll();
        content1.add(length, BorderLayout.WEST);
        content1.add(relPos, BorderLayout.CENTER);
        content1.add(linearRapport, BorderLayout.EAST);

        editor.add(content1, BorderLayout.CENTER);
        editor.add(gCollection, BorderLayout.SOUTH);
    }


    @Override
    public Class getEditedClass() {
        return GraphicStroke.class;
    }


    @Override
    public Stroke getStroke() {
        return this.graphicStroke;
    }


}
