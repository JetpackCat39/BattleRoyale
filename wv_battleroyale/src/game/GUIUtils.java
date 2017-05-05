package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;

public class GUIUtils {

	Graphics g;
	
	public GUIUtils(Graphics newG) {
		g = newG;
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
	
	public void drawImg(BufferedImage bg, int x, int y, int w, int h) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(bg, x, y, w, h, null);
	}
	
}
