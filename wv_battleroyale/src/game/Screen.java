package game;

import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Screen {
	protected int height = Game.HEIGHT;
	protected int width = Game.WIDTH;
	
	protected ArrayList<RoundRectangle2D> buttonList;
	protected GUIUtils gui;
	protected BufferedImage bg;
	
	public Screen(BufferedImage background) {
		bg = background;
		buttonList = new ArrayList<RoundRectangle2D>();
	}
	
	public void draw() {
		gui.drawImg(bg, 0, 0, width, height);
	}
}
