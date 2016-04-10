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
package org.orbisgis.toc.se;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.EventHandler;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.KeyStroke;
import org.h2gis.utilities.SFSUtilities;
import org.h2gis.utilities.TableLocation;
import org.orbisgis.coremap.layerModel.ILayer;
import org.orbisgis.coremap.layerModel.MapContext;
import org.orbisgis.coremap.renderer.se.SeExceptions;
import org.orbisgis.coremap.renderer.se.Style;
import org.orbisgis.mapeditorapi.MapElement;
import org.orbisgis.sif.UIFactory;
import org.orbisgis.sif.UIPanel;
import org.orbisgis.sif.edition.EditableElement;
import org.orbisgis.sif.edition.EditorDockable;
import org.orbisgis.tocapi.StyleAction;
import org.orbisgis.tocapi.TocActionFactory;
import org.orbisgis.tocapi.TocExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xnap.commons.i18n.I18n;
import org.xnap.commons.i18n.I18nFactory;

/**
 * Add a rigth-click menu in the TOC to display the advanced Symbology Encoding GUI.
 * @author Erwan Bocher
 */
public class SEAdvancedGUIExt implements TocActionFactory{
    
    private static final I18n I18N = I18nFactory.getI18n(SEAdvancedGUIExt.class);
    private static final Logger LOGGER = LoggerFactory.getLogger(SEAdvancedGUIExt.class);
    public static final String A_ADVANCED_STYLE_EDITION = "A_ADVANCED_STYLE_EDITION";

    @Override
    public List<Action> createActions(TocExt tocExt) {
        return Arrays.asList(new Action[]{new StyleAction(tocExt,A_ADVANCED_STYLE_EDITION,
                    I18N.tr("Advanced style editor"), I18N.tr("Open the advanced editor for SE styles"),
                    new ImageIcon(SEAdvancedGUIExt.class.getResource("palette_edit.png")),
                    EventHandler.create(ActionListener.class, this, "onAdvancedEditor"),null).setOnSingleStyleSelection(true)});
    }

    @Override
    public void disposeActions(TocExt target, List<Action> actions) {
 
    }
    
    /**
     * 
     */
    public class AdvancedStyleEditor extends StyleAction{

        public AdvancedStyleEditor(TocExt toc, String actionId, String actionLabel, String actionToolTip, Icon icon, ActionListener actionListener, KeyStroke keyStroke) {
            super(toc, actionId, actionLabel, actionToolTip, icon, actionListener, keyStroke);
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (toc instanceof EditorDockable) {                
                EditableElement editableElement = ((EditorDockable) toc).getEditableElement();
                if (editableElement instanceof MapElement) {
                    MapContext mapContext = ((MapElement) editableElement).getMapContext();
                    try {
                        Style[] styles = mapContext.getSelectedStyles();
                        if (styles.length == 1) {
                            Style style = styles[0];
                            ILayer layer = style.getLayer();
                            if (layer.getTableReference() != null && !layer.getTableReference().isEmpty()) {
                                int index = layer.indexOf(style); 
                                int geometryType = 0;
                                TableLocation tableLocation = TableLocation.parse(layer.getTableReference());
                                try (Connection connection = mapContext.getDataManager().getDataSource().getConnection()) {
                                    geometryType = SFSUtilities.getGeometryType(connection, tableLocation, "");
                                } catch (SQLException ex) {
                                    LOGGER.error(I18N.tr("Cannot find the geometry type"), ex);
                                }

                                LegendUIController controller = new LegendUIController(index, style, geometryType);
                                if (UIFactory.showDialog((UIPanel) controller.getMainPanel())) {
                                    layer.setStyle(index, controller.getEditedFeatureTypeStyle());
                                }
                            } else {
                                LOGGER.info("This functionality is not supported.");
                            }
                        }
                    } catch (SeExceptions.InvalidStyle ex) {
                        LOGGER.error(I18N.tr("Error while editing the legend"), ex);
                    }
                }
            }
        } 
        
        
    }    
    
}
