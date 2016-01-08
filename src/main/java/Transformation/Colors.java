package Transformation;
/**
 * Author Mark Bishop; 2014
 * License GNU v3; 
 * This class is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

import java.awt.Color;

/**
 * Class responsibility: provide a set of appropriate colors for
 * multi-resolution plots.
 *
 */
public enum Colors {
	Black, Red, Green, Blue, Cyan, Orange, Pink, Magenta, Grey;
	public Color getColor() {
		switch (this) {
		case Black:
			return Color.black;
		case Red:
			return Color.red;
		case Green:
			return Color.green;
		case Blue:
			return Color.blue;
		case Cyan:
			return Color.cyan;
		case Orange:
			return Color.orange;
		case Pink:
			return Color.pink;
		case Magenta:
			return Color.magenta;
		case Grey:
			return Color.gray;
		}
		return Color.DARK_GRAY;
	}
}
