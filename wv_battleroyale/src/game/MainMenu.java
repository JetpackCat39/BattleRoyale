package game;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;

public class MainMenu extends Screen {
	
	public static RoundRectangle2D play;
	public static RoundRectangle2D controls;
	public static RoundRectangle2D exit;
	
	public MainMenu(Graphics newG, BufferedImage background) {
		super(newG, background);
	}
	
	public void draw() {
		super.draw();
		gui.createText(width / 9, height * 2 / 9, Color.white, "WESTVIEW BATTLE ROYALE",  72);
		play = gui.createButton(width / 3, height * 3 / 5, 220, 50, Color.white, Color.decode("#4d4d4d"), "PLAY", 32);
		controls = gui.createButton(width / 3, height * 4 / 5, 220, 50, Color.white, Color.decode("#4d4d4d"), "CONTROLS", 32);
		exit = gui.createButton(width / 3, height * 5 / 5, 220, 50, Color.white, Color.decode("#4d4d4d"), "EXIT", 32);
	}
	
	
}
