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

package org.orbisgis.mapcomposer.view.utils;

import java.awt.Color;
import java.awt.event.MouseListener;
import java.beans.EventHandler;
import javax.swing.*;

/**
 * Color chooser
 */
public class ColorChooser extends JFrame{
    private final JComponent label;
    private final JButton button;
    final JColorChooser jcc;
    
    public ColorChooser(JComponent label){
        this.label = label;
        jcc = new JColorChooser(Color.yellow);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        panel.add(jcc);
        this.button = new JButton("Ok");
        this.button.addMouseListener(EventHandler.create(MouseListener.class, this, "mouseClicked", "source", "mouseClicked"));
        this.button.setSize(40, 20);
        panel.add(button);
        this.add(panel);
        this.pack();
    }

   public void mouseClicked(Object o) {
        if(o==button){
            label.setBackground(jcc.getColor());
            this.setVisible(false);
        }
    }
}
