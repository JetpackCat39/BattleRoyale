package game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class Screen {
	protected int height = Game.HEIGHT;
	protected int width = Game.WIDTH;
	
	protected Graphics g;
	protected GUIUtils gui;
	protected BufferedImage bg;
	
	public Screen(Graphics newG, BufferedImage background) {
		g = newG;
		gui = new GUIUtils(g);
		bg = background;
	}
	
	public void draw() {
		gui.drawImg(bg, 0, 0, width, height);
	}
}
