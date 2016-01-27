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


/**
 *
 * @author Maxence Laurent
 */
/*
public class LegendUIStrokeAnnotationGraphicPanel extends LegendUIComponent {

    //private StrokeAnnotationGraphic anno;

    private LegendUICompositeGraphicPanel graphic;
    private LegendUIMetaRealPanel relativePosition;

	private ComboBoxInput orientation;

    private LegendUIAbstractPanel header;

    public LegendUIStrokeAnnotationGraphicPanel(LegendUIController controller, LegendUIComponent parent, StrokeAnnotationGraphic annotation){
        super("", controller, parent, 0, false);
        this.anno = annotation;


        header = new LegendUIAbstractPanel(controller);

        graphic = new LegendUICompositeGraphicPanel(controller, this, anno.getGraphic());

        relativePosition = new LegendUIMetaRealPanel("Position", controller, this, anno.getRelativePosition(), false) {

            @Override
            public void realChanged(RealParameter newReal) {
                anno.setRelativePosition(newReal);
            }
        };
        relativePosition.init();

		orientation = new ComboBoxInput(RelativeOrientation.values(), anno.getRelativeOrientation().ordinal()) {

			@Override
			protected void valueChanged(int i) {
				anno.setRelativeOrientation(RelativeOrientation.values()[i]);
			}
		};
    }

    @Override
    public Icon getIcon() {
        return OrbisGISIcon.getIcon("palette");
    }

    @Override
    protected void mountComponent() {
        header.removeAll();

        header.add(relativePosition, BorderLayout.WEST);
        header.add(orientation, BorderLayout.EAST);

        editor.add(header, BorderLayout.NORTH);
        editor.add(graphic, BorderLayout.SOUTH);
    }

    @Override
    protected void turnOff() {
        throw new UnsupportedOperationException("unreachable");
    }

    @Override
    protected void turnOn() {
        throw new UnsupportedOperationException("unreachable");
    }

    @Override
    public Class getEditedClass() {
        return StrokeElement.class;
    }


}
*/
