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

import java.awt.BorderLayout;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

/**
 *
 * @author Maxence Laurent
 */

public class LegendUIAbstractPanel extends JPanel {
	protected LegendUIController controller;

	public LegendUIAbstractPanel(LegendUIController controller) {
		super(new BorderLayout());
		this.controller = controller;
	}


	public void add(LegendUIComponent comp) {
		if (comp != null && !comp.isNullComponent) {
			if (comp.isNested()) {
				LegendUILinkToComplexPanel link = new LegendUILinkToComplexPanel(controller, comp);
				super.add(link);
			} else {
				super.add(comp);
			}
		}
	}

	public void add(LegendUIComponent comp, Object constraints) {
		if (comp != null && !comp.isNullComponent) {
			if (comp.isNested()) {
				LegendUILinkToComplexPanel link = new LegendUILinkToComplexPanel(controller, comp);
				super.add(link, constraints);
			} else {
				super.add(comp, constraints);
			}
		}
	}

	public void pack(){
		this.revalidate();

		JDialog dlg = (JDialog) SwingUtilities.getAncestorOfClass(JDialog.class, this);

		if (dlg != null){
			dlg.pack();
		}
		this.updateUI();
	}
}