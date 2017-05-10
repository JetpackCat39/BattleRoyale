package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;

public class Button extends RoundRectangle2D.Double implements IDrawable
{

	private static final int DEFAULT_HEIGHT = 50;
	private static final int DEFAULT_WIDTH = 220;
	private static final int ARC_WIDTH = 50;
	private String buttonText;
	private int fontStyle;
	private int fontSize;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Button(int x, int y, String text, int size, int style)
	{
		this(x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT, text,size, style);
	}
	
	public Button(int x, int y, int width, int height, String text,int size, int style)
	{
		super(x - (width / 2), y - (width / 2), width, height, ARC_WIDTH, ARC_WIDTH);
		buttonText = text;
		fontStyle = style;
		fontSize = size;
	}
	
	public String getText()
	{
		return buttonText;
	}
	
	public void setText(String text)
	{
		buttonText = text;
	}

	/* (non-Javadoc)
	 * @see game.IDrawable#draw(java.awt.Graphics)
	 */
	@Override
	public void draw(Graphics g)
	{
		draw(g, Color.white, Color.decode("#4d4d4d"), buttonText, fontSize,fontStyle);

	}
	
	public void draw(Graphics g, Color color, Color tColor, String text, int fontSize, int style)
	{
		g.setColor(color);
		Graphics2D g2d = (Graphics2D) g;
		g2d.draw(this);
		g2d.fill(this);
		GUIUtils.self().drawText((int) this.getX() + (140 / (text.length() - 2)), (int) this.getY() + ((int) this.getHeight() * 7 / 9),
				tColor, text, fontSize, g,style);
	}
	
}
