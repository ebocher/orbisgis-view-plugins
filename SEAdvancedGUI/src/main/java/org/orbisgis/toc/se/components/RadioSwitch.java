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
package org.orbisgis.toc.se.components;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 *
 * @author Maxence Laurent
 */
public abstract class RadioSwitch extends JPanel implements ActionListener {

	public RadioSwitch(String[] options, int initialChoice) {

		super(new FlowLayout());

		JRadioButton[] rbs = new JRadioButton[options.length];
		int i = 0;
		ButtonGroup group = new ButtonGroup();
		for (String opt : options) {
			rbs[i] = new JRadioButton(opt);
			rbs[i].setActionCommand(Integer.toString(i));
			if (i == initialChoice) {
				rbs[i].setSelected(true);
			}
			group.add(rbs[i]);
			rbs[i].addActionListener(this);
			this.add(rbs[i]);
			i++;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		int choice = -1;
		try{
		  choice = Integer.parseInt(e.getActionCommand());
		}
		catch (Exception ex){
		}
		valueChanged(choice);
	}

	/**
	 * This method will be called each time the selection change
	 * When errors occur or nothing (should never happen...), -1 is sent,
	 * otherwise, the id of the selection is sent
	 * @param choice
	 */
	protected abstract void valueChanged(int choice);
}
