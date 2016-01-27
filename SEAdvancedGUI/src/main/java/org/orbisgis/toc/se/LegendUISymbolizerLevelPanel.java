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

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import org.orbisgis.coremap.renderer.se.Rule;
import org.orbisgis.coremap.renderer.se.Symbolizer;
import org.orbisgis.toc.se.components.RealLiteralInput;

/**
 *
 * @author Maxence Laurent
 * @author Erwan Bocher
 */
public class LegendUISymbolizerLevelPanel extends LegendUIAbstractPanel {

	List<RealLiteralInput> levels;

	public LegendUISymbolizerLevelPanel(LegendUIController controller){
		super(controller);
		this.setLayout(new GridLayout(0,1));

		List<Rule> rules = controller.getEditedFeatureTypeStyle().getRules();

		levels = new ArrayList<RealLiteralInput>();

		int sum = 0;
		int maxLevel = 1;
		for (Rule r : rules){
			sum += r.getCompositeSymbolizer().getSymbolizerList().size();
			for (Symbolizer s : r.getCompositeSymbolizer().getSymbolizerList()){
				if (s.getLevel() > maxLevel){
					maxLevel = (int) s.getLevel();
				}
			}
		}

		int max = Math.max(sum, maxLevel);
		for (Rule r : rules){
			List<Symbolizer> ss = r.getCompositeSymbolizer().getSymbolizerList();
			for (Symbolizer s: ss){
				levels.add(new RealLiteralInputImpl(s.getName(), (double)s.getLevel(), 0.0, (double)max, s));
			}
		}

		for (RealLiteralInput input : levels){
			this.add(input);
		}
	}

	private static class RealLiteralInputImpl extends RealLiteralInput {

		private final Symbolizer s;

		public RealLiteralInputImpl(String name, Double initialValue, Double min, Double max, Symbolizer s) {
			super(name, initialValue, min, max);
			this.s = s;
		}

		@Override
		protected void valueChanged(Double v) {
			s.setLevel(v.intValue());
		}
	}
}
