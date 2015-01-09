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

package org.orbisgis.mapcomposer.view.configurationattribute;

import org.orbisgis.mapcomposer.model.configurationattribute.interfaces.ConfigurationAttribute;
import org.orbisgis.mapcomposer.model.configurationattribute.attribute.ColorCA;
import org.orbisgis.mapcomposer.view.utils.ColorChooser;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.beans.EventHandler;
import java.beans.PropertyChangeListener;
import javax.swing.*;

/**
 * Renderer associated to the ColorCA ConfigurationAttribute.
 * The JComponent returned by the createJComponentFromCA method look like :
 *  _______________________________________________
 * |                                  _________    |
 * | NameOfTheConfigurationAttribute |Button   |   |
 * |                                 |_________|   |
 * |_______________________________________________|
 *
 * The color is chosen by clicking on a button. It opens a ColorChooser and the color is saved in the background of the button
 * @see org.orbisgis.mapcomposer.model.configurationattribute.attribute.ColorCA
 */
public class ColorRenderer implements CARenderer{

    @Override
    public JComponent createJComponentFromCA(ConfigurationAttribute ca) {
    //Create the component
        JComponent component = new JPanel();
        component.setLayout(new FlowLayout(FlowLayout.LEFT));

    //Add to the component all the swing components
        final ColorCA colorCA = (ColorCA)ca;
        //Add the name of the ConfigurationAttribute
        component.add(new JLabel(colorCA.getName()));

        JButton button = new JButton("Text demo");
        //Display the color in the button background
        button.setBackground(colorCA.getValue());
        //On clicking on the button, open a color chooser
        button.addActionListener(EventHandler.create(ActionListener.class, this, "open", "source"));
        button.addPropertyChangeListener(EventHandler.create(PropertyChangeListener.class, colorCA, "setValue", "source.background"));
        //Add the JButton
        component.add(button);
        return component;
    }

    /**
     * Open a ColorChooser and show it.
     * @param component The chosen color will be saved in the background of the component
     */
    public void open(JComponent component){
        ColorChooser cc = new ColorChooser(component);
        cc.setVisible(true);
    }
    
}
