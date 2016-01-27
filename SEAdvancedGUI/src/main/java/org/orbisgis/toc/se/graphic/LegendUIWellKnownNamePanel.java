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

/**
 *
 * @author Maxence Laurent
 */
/*public class LegendUIWellKnownNamePanel extends LegendUIComponent {

    
	MarkGraphic mg;

	ComboBoxInput wknInput;

	WellKnownName wkn;

	public LegendUIWellKnownNamePanel(LegendUIController controller, LegendUIComponent parent, MarkGraphic m) {
		super("WKG", controller, parent, 0, false);
		this.mg = m;
		//this.wkn = (WellKnownName) mg.getSource();


		wknInput = new ComboBoxInput(WellKnownName.values(), ((WellKnownName)mg.getSource()).ordinal()) {

			@Override
			protected void valueChanged(int i) {
				wkn = WellKnownName.values()[i];
				mg.setSource(wkn);
			}
		};
	}

	@Override
	public Icon getIcon() {
		return OrbisGISIcon.getIcon("palette");
	}

	@Override
	protected void mountComponent() {
		editor.add(wknInput);
	}

	@Override
	protected void turnOff() {
		mg.setSource(null);
	}

	@Override
	protected void turnOn() {
		mg.setSource(this.wkn);
	}

	@Override
	public Class getEditedClass() {
		return WellKnownName.class;
	}

}
*/
