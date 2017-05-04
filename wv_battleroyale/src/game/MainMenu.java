package game;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;

public class MainMenu {
	
	private int height = Game.HEIGHT;
	private int width = Game.WIDTH;
	
	private Graphics g;
	
	public static RoundRectangle2D play;
	public static RoundRectangle2D controls;
	public static RoundRectangle2D exit;
	
	public MainMenu(Graphics newG, BufferedImage background) {
		g = newG;
		drawBG(background);
		createText(width / 9, height * 2 / 9, Color.white, "WESTVIEW BATTLE ROYALE",  72);
		play = createButton(width / 3, height * 3 / 5, 220, 50, Color.white, Color.decode("#4d4d4d"), "PLAY", 32);
		controls = createButton(width / 3, height * 4 / 5, 220, 50, Color.white, Color.decode("#4d4d4d"), "CONTROLS", 32);
		exit = createButton(width / 3, height * 5 / 5, 220, 50, Color.white, Color.decode("#4d4d4d"), "EXIT", 32);
	}
	
	public RoundRectangle2D createButton(int x, int y, int width, int height, Color color) {
		RoundRectangle2D rect = new RoundRectangle2D.Double(x - (width/2), y - (width/2), width, height, 50, 50);
		g.setColor(color);
		Graphics2D g2d = (Graphics2D) g;
		g2d.draw(rect);
		g2d.fill(rect);
		
		return rect;
	}
	
	public void createText(int x, int y, Color color, String text, int fontSize) {
		g.setFont(new Font("arial", Font.BOLD, fontSize));
		g.setColor(color);
		g.drawString(text, x, y);
	}
	
	public RoundRectangle2D createButton(int x, int y, int width, int height, Color color, Color tColor,String text, int fontSize) {
		RoundRectangle2D rect = createButton(x, y, width, height, color);
		createText(x - (text.length() * 11), y - (height * 145 / 100), tColor, text, fontSize);
		return rect;
	}
	
	public void drawBG(BufferedImage bg) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(bg, 0, 0, width, height, null);
	}
}
