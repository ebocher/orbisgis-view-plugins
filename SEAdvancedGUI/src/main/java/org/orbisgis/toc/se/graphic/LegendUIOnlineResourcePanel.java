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

import java.net.URISyntaxException;
import javax.swing.Icon;
import org.orbisgis.coremap.renderer.se.common.OnlineResource;
import org.orbisgis.coremap.renderer.se.graphic.WellKnownName;
import org.orbisgis.toc.se.LegendUIComponent;
import org.orbisgis.toc.se.LegendUIController;
import org.orbisgis.toc.se.components.TextInput;
import org.orbisgis.toc.se.icons.SEAdvancedIcon;

/**
 *
 * @author Maxence Laurent
 */
public class LegendUIOnlineResourcePanel extends LegendUIComponent /*implements LegendUIExternalSourceComponent*/ {

	TextInput urlInput;
    OnlineResource onlineResource;

	public LegendUIOnlineResourcePanel(LegendUIController controller, LegendUIComponent parent, OnlineResource url) {
		super("URL", controller, parent, 0, false);

        this.onlineResource = url;
        if (onlineResource == null){
            onlineResource = new OnlineResource();
        }

        String initUrl = "";
        if (onlineResource.getUri() != null){
            initUrl = onlineResource.getUri().toString();
        }

        urlInput = new TextInput("URL", initUrl, 50, false) {

            @Override
            protected void valueChanged(String s) {
                try {
                    onlineResource.setUri(s);
                } catch (URISyntaxException ex) {
                    //urlInput.setValue("");
                }
            }
        };
	}

	@Override
	public Icon getIcon() {
		return SEAdvancedIcon.getIcon("palette");
	}

	@Override
	protected void mountComponent() {
        editor.add(urlInput);
	}

	@Override
	protected void turnOff() {
        //updateOnlineResource(null);
	}

	@Override
	protected void turnOn() {
        //updateOnlineResource(this.onlineResource);
	}

	@Override
	public Class getEditedClass() {
		return WellKnownName.class;
	}

    /*@Override
    public ExternalGraphicSource getSource() {
        return onlineResource;
    }*/



    /**
     * Method to override by users
     * @param url
     */
    //public abstract void updateOnlineResqource(OnlineResource url);
}
