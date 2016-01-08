package Transformation;
/**
 * Copyright 2014 Mark Bishop This program is free software: you can
 * redistribute it and/or modify it under the terms of the GNU General Public
 * License as published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details: http://www.gnu.org/licenses
 * 
 * The author makes no warranty for the accuracy, completeness, safety, or
 * usefulness of any information provided and does not represent that its use
 * would not infringe privately owned right.
 */

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

/**
 * Class responsibility: Provide a control on which to draw graphics, such as
 * function graphs, that can be updated with one command.
 * 
 * @author max *
 */

class ImagePanel extends JPanel {

	private static final long serialVersionUID = 1L;
	private Image img;

	public ImagePanel(Image img) {
		this.img = img;
	}

	public void update(Image img) {
		this.img = img;
		this.invalidate();
		this.repaint();
	}

	protected void paintComponent(Graphics g) {
		g.drawImage(img, 0, 0, null);
	}

}
