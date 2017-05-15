package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.image.BufferedImage;

public class GUIUtils
{
	private static final String DEFAULT_FONT = "arial";
	private static final Color DEFAULT_COLOR = Color.white;
	private static final int DEFAULT_FONT_STYLE = Font.BOLD; 
	// Making GUIUtils a singleton
	private static GUIUtils s_self = new GUIUtils();

	public static GUIUtils self()
	{
		return s_self;
	}

	private GUIUtils()
	{
	}

	public void drawText(int x, int y, String text, int fontSize, Graphics g)
	{
		g.setFont(new Font(DEFAULT_FONT, DEFAULT_FONT_STYLE, fontSize));
		g.setColor(DEFAULT_COLOR);
		g.drawString(text, x, y);
	}
	
	public void drawText(int x, int y, Color color, String text, int fontSize, Graphics g, int fontStyle)
	{
		g.setFont(new Font(DEFAULT_FONT, fontStyle, fontSize));
		g.setColor(color);
		g.drawString(text, x, y);
	}

	public void drawImg(BufferedImage bg, int x, int y, int w, int h, Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(bg, x, y, w, h, null);
	}

}
