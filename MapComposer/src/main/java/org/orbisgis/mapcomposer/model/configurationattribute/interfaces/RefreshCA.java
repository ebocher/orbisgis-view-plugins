/*
* MapComposer is an OrbisGIS plugin dedicated to the creation of cartographic
* documents based on OrbisGIS results.
*
* The MapComposer plugin is distributed under GPL 3 license. It is produced by the "Atelier SIG"
* team of the IRSTV Institute <http://www.irstv.fr/> CNRS FR 2488.
*
* Copyright (C) 2007-2014 IRSTV (FR CNRS 2488)
*
* This file is part of the MapComposer plugin.
*
* The MapComposer plugin is free software: you can redistribute it and/or modify it under the
* terms of the GNU General Public License as published by the Free Software
* Foundation, either version 3 of the License, or (at your option) any later
* version.
*
* The MapComposer plugin is distributed in the hope that it will be useful, but WITHOUT ANY
* WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
* A PARTICULAR PURPOSE. See the GNU General Public License for more details <http://www.gnu.org/licenses/>.
*/

package org.orbisgis.mapcomposer.model.configurationattribute.interfaces;

import org.orbisgis.mapcomposer.controller.UIController;

/**
 * This interface for the ConfigurationAttribute allows to define a refresh method.
 * It will be called to refresh the value contained by the GraphicalElement and to verify if it still right.
 */
public interface RefreshCA {
    /**
     * Refresh function.
     * @param uic UIController of the application. It permits to have an access to all the information necessary to verify the ConfigurationAttribute value.
     */
    public void refresh(UIController uic);
}
