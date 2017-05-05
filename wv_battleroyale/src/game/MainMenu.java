package game;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;

public class MainMenu extends Screen {
	
	public MainMenu(BufferedImage background) {
		super(background);
		gui = new GUIUtils();
		
		//play button
		buttonList.add(gui.createButton(width / 3, height * 3 / 5, 220, 50));
//		controls button
		buttonList.add(gui.createButton(width / 3, height * 4 / 5, 220, 50));
//		exit button
		buttonList.add(gui.createButton(width / 3, height * 5 / 5, 220, 50));
	}
	
	public void draw(Graphics g) {
		super.draw(g);
		gui.drawText(width / 9, height * 2 / 9, Color.white, "WESTVIEW BATTLE ROYALE",  72, g);
		gui.drawButton(getPlay(), Color.white, Color.decode("#4d4d4d"), "PLAY", 32, g);
		gui.drawButton(getControls(), Color.white, Color.decode("#4d4d4d"), "CONTROLS", 32, g);
		gui.drawButton(getExit(), Color.white, Color.decode("#4d4d4d"), "EXIT", 32, g);
	}
	
	public RoundRectangle2D getPlay() {
		return buttonList.get(0);
	}
	
	public RoundRectangle2D getControls() {
		return buttonList.get(1);
	}
	
	public RoundRectangle2D getExit() {
		return buttonList.get(2);
	}
	
}
