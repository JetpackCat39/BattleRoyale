package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.image.BufferedImage;

public class GUIUtils
{
	//dont declare an instance variable?
	private static GUIUtils s_self = new GUIUtils();
	
	public static GUIUtils self()
	{
		return s_self;
	}
	
	private GUIUtils()
	{
	}

	public void drawText(int x, int y, Color color, String text, int fontSize, Graphics g, int fontStyle)
	{
		g.setFont(new Font("arial", fontStyle, fontSize));
		g.setColor(color);
		g.drawString(text, x, y);
	}

	public void drawImg(BufferedImage bg, int x, int y, int w, int h, Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(bg, x, y, w, h, null);
	}

	
	

}
