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
import org.orbisgis.coremap.renderer.se.common.Halo;
import org.orbisgis.coremap.renderer.se.graphic.ExternalGraphic;
import org.orbisgis.coremap.renderer.se.graphic.Graphic;
import org.orbisgis.coremap.renderer.se.graphic.MarkGraphic;
import org.orbisgis.coremap.renderer.se.graphic.ViewBox;
import org.orbisgis.coremap.renderer.se.parameter.real.RealParameter;
import org.orbisgis.toc.se.LegendUIAbstractPanel;
import org.orbisgis.toc.se.LegendUIComponent;
import org.orbisgis.toc.se.LegendUIController;
import org.orbisgis.toc.se.components.UomInput;
import org.orbisgis.toc.se.icons.SEAdvancedIcon;
import org.orbisgis.view.toc.actions.cui.parameter.real.LegendUIMetaRealPanel;

/**
 *
 * @author Maxence Laurent
 */
public class LegendUIExternalGraphicPanel extends LegendUIComponent
        implements LegendUIGraphicComponent {

	private ExternalGraphic extG;
	private LegendUIViewBoxPanel vBox;
    private LegendUITransformPanel transform;

	private LegendUIMetaExternalGraphicSource exSource;

    private LegendUIMetaRealPanel opacity;

	private LegendUIHaloPanel halo;
	private UomInput uomInput;

	public LegendUIExternalGraphicPanel(LegendUIController controller, LegendUIComponent parent, ExternalGraphic ext) {
		super("External Graphic", controller, parent, 0, false);
        this.extG = ext;


        this.transform = new LegendUITransformPanel(controller, this, ext);

		this.halo = new LegendUIHaloPanel(controller, this, extG.getHalo()) {

			@Override
			protected void haloChanged(Halo halo) {
				extG.setHalo(halo);
				controller.structureChanged(this);
			}
		};

		this.vBox = new LegendUIViewBoxPanel(controller, this, extG.getViewBox(), true) {

			@Override
			public void viewBoxChanged(ViewBox newViewBox) {
				extG.setViewBox(viewbox);
				controller.structureChanged(this);
			}

		};

        this.opacity = new LegendUIMetaRealPanel("Opacity", controller, this,
                extG.getOpacity(), true) {

            @Override
            public void realChanged(RealParameter newReal) {
                extG.setOpacity(newReal);
            }
        };
        opacity.init();

		this.exSource = new LegendUIMetaExternalGraphicSource(controller, parent, extG);
		exSource.init();

		this.uomInput = new UomInput(extG);
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

		content1.add(exSource, BorderLayout.WEST);
		content1.add(uomInput, BorderLayout.EAST);

		content2.add(vBox, BorderLayout.WEST);
        content2.add(transform, BorderLayout.EAST);

		content3.add(opacity, BorderLayout.EAST);
		content3.add(halo, BorderLayout.WEST);

		editor.add(content1, BorderLayout.NORTH);
		editor.add(content2, BorderLayout.CENTER);
		editor.add(content3, BorderLayout.SOUTH);
	}

	@Override
	public Graphic getGraphic() {
		return extG;
	}

	@Override
	public Class getEditedClass() {
		return MarkGraphic.class;
	}

	@Override
	protected void turnOff() {
		throw new UnsupportedOperationException("Unreachable code.");
	}

	@Override
	protected void turnOn() {
		throw new UnsupportedOperationException("Unreachable code.");
	}
}
