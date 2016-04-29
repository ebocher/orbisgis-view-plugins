/*
 * OrbisGIS is an open source GIS application dedicated to research for
 * all geographic information science.
 * 
 * OrbisGIS is distributed under GPL 3 license. It is developped by the "GIS"
 * team of the Lab-STICC laboratory <http://www.lab-sticc.fr/>.
 *
 * Copyright (C) 2007-2014 IRSTV (FR CNRS 2488)
 * Copyright (C) 2015-2016 Lab-STICC CNRS, UMR 6285.
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
package org.orbisgis.r.console;


import org.orbisgis.sif.components.actions.ActionFactoryService;

/**
 * @author Nicolas Fortin
 */
public abstract class RConsoleActions implements ActionFactoryService<RConsolePanel> {
    // Action, MENU IDs
    public static final String A_EXECUTE = "M_EXECUTE";
    public static final String A_CLEAR = "M_CLEAR";
    public static final String A_OPEN = "M_OPEN";
    public static final String A_SAVE = "M_SAVE";
    public static final String A_SEARCH = "M_SEARCH";
    public static final String A_COMMENT = "M_COMMENT";
    public static final String A_BLOCKCOMMENT = "M_BLOCKCOMMENT";
}
