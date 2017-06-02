package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;
import java.util.function.Consumer;

public class Button extends RoundRectangle2D.Double implements IDrawable
{

	private static final Color DEFAULT_BACKGROUND = Color.white;
	private static final Color DEFAULT_TCOLOR = Color.black;
	private static final int DEFAULT_HEIGHT = 50;
	private static final int DEFAULT_WIDTH = 220;
	private static final int ARC_WIDTH = 50;
	private static final int DEFAULT_FONT_SIZE = 32;
	private static final int DEFAULT_FONT_STYLE = Font.BOLD;
	private String buttonText;
	private int fontStyle, fontSize;
	private Color backColor, textColor, fillColor;
	
	private Consumer<Integer> action = null;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	// Simple button
	public Button(int x, int y, String text)
	{
		this(x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT, text, DEFAULT_FONT_SIZE, DEFAULT_FONT_STYLE, 
				ARC_WIDTH, DEFAULT_BACKGROUND, DEFAULT_TCOLOR);
	}
	
	// Button w/ smaller text
	public Button(int x, int y, String text, int fontSize)
	{
		this(x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT, text, fontSize, DEFAULT_FONT_STYLE, 
				ARC_WIDTH, DEFAULT_BACKGROUND, DEFAULT_TCOLOR);
	}
	
	// Button with custom width and height
	public Button(int x, int y, int w, int h, String text, int fontSize)
	{
		this(x, y, w, h, text, fontSize, DEFAULT_FONT_STYLE, ARC_WIDTH, DEFAULT_BACKGROUND, DEFAULT_TCOLOR);
	}

	// Button with customized font size and font style
	public Button(int x, int y, String text, int size, int style)
	{
		this(x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT, text, size, style, ARC_WIDTH, DEFAULT_BACKGROUND, DEFAULT_TCOLOR);
	}

	// Button with custom dimensions
	public Button(int x, int y, int width, int height, int arcWidth, String text)
	{
		this(x, y, width, height, text, DEFAULT_FONT_SIZE, DEFAULT_FONT_STYLE, arcWidth, DEFAULT_BACKGROUND, DEFAULT_TCOLOR);
	}

	// Button input options
	public Button(int x, int y, int width, int height, String text, int size, int style, int arcWidth, Color background,
			Color tcolor)
	{
		super(x, y, width, height, arcWidth, arcWidth);
		buttonText = text;
		fontStyle = style;
		fontSize = size;
		backColor = background;
		textColor = tcolor;
		fillColor = DEFAULT_BACKGROUND;
	}
	
	public Button setAction(Consumer<Integer> action)
	{
		this.action = action;
		return this;
	}
	
	public void performAction(int param)
	{
		if (action != null)
		{
			action.accept(param);
		}
	}
	
	public String getText()
	{
		return buttonText;
	}

	public void setText(String text)
	{
		buttonText = text;
	}

	public int getTextSize()
	{
		return fontSize;
	}

	public void setTextSize(int size)
	{
		fontSize = size;
	}

	public Color getBackgroundColor()
	{
		return backColor;
	}

	public void setBackgroundColor(Color color)
	{
		backColor = color;
	}

	public Color getTextColor()
	{
		return textColor;
	}

	public void setTextColor(Color color)
	{
		textColor = color;
	}

	public void draw(Graphics g)
	{
		g.setColor(backColor);
		Graphics2D g2d = (Graphics2D) g;
		g2d.draw(this);
		g2d.fill(this);
		Font font = new Font(buttonText, fontStyle, fontSize);

		FontMetrics metric = g.getFontMetrics(font);
		
		GameUtils.self().drawText((int) (this.getX() + this.getWidth() / 2 - metric.stringWidth(buttonText) / 2),
				(int) (this.getY() + this.getHeight() / 2 + metric.getAscent() / 2), textColor, buttonText, fontSize, g, fontStyle);
	}

	public void fill(Graphics g, Color c)
	{
		g.setColor(c);
		Graphics2D g2d = (Graphics2D) g;
		g2d.draw(this);
		g2d.fill(this);
		
		Font font = new Font(buttonText, fontStyle, fontSize);

		FontMetrics metric = g.getFontMetrics(font);
		
		GameUtils.self().drawText((int) (this.getX() + this.getWidth() / 2 - metric.stringWidth(buttonText) / 2),
				(int) (this.getY() + this.getHeight() / 2 + metric.getAscent() / 2), fillColor, buttonText, fontSize, g, fontStyle);
	}

}
