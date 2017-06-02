package game;

import java.awt.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.io.IOException;
import java.text.DecimalFormat;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class GameUtils
{
	// various defaults
	private static final String DEFAULT_FONT = "arial";
	private static final Color DEFAULT_COLOR = Color.white;
	private static final int DEFAULT_FONT_STYLE = Font.BOLD;
	public static final int CENTER = 530;
	public static final int BORDER = 5;
	// Making GUIUtils a singleton
	private static GameUtils s_self = new GameUtils();

	public static GameUtils self()
	{
		return s_self;
	}

	private GameUtils()
	{
	}

	// Drawing text in the center of a screen
	public void drawText (Color c, int height, String text, int fontSize, Graphics g)
	{
		FontMetrics fontMetrics = new JFrame().getFontMetrics(new Font(DEFAULT_FONT, DEFAULT_FONT_STYLE, fontSize));
		drawText(CENTER - (fontMetrics.stringWidth(text))/4, height, c, text, fontSize, g, DEFAULT_FONT_STYLE);
	}
	
	// Draws text at a custom x and y
	public void drawText(int x, int y, String text, int fontSize, Graphics g)
	{
		drawText(x, y, DEFAULT_COLOR, text, fontSize, g, DEFAULT_FONT_STYLE);
	}

	// Draw text at custom x and y with custom fontstyle and fontsize
	public void drawText(int x, int y, Color color, String text, int fontSize, Graphics g, int fontStyle)
	{
		Graphics2D g2d = (Graphics2D) g;
		g2d.setFont(new Font(DEFAULT_FONT, fontStyle, fontSize));
		g2d.setColor(color);
		g2d.drawString(text, x, y);
	}

	// Draws an image at custom x, y, width, and height
	public void drawImg(BufferedImage img, int x, int y, int w, int h, Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(img, x, y, w, h, null);
	}

	// Draws an image by copying it from one location to another location
	public void drawImg(BufferedImage img, int xSrc, int ySrc, int xDest, int yDest, int wSrc, int hSrc, int wDest,
			int hDest, Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(img, xDest, yDest, xDest + wDest, yDest + hDest, xSrc, ySrc, xSrc + wSrc, ySrc + hSrc, null);
	}
	
	// Draws an hp bar given currentHealth and maxHealth
	public void drawHP(int x, int y, int w, int h, int currentHealth, int maxHealth, Color c, Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.gray);
		g2d.fillRect(x - BORDER, y - BORDER, w + BORDER * 2, h + BORDER * 2);
		g2d.setColor(c);
		g2d.fillRect(x, y, w * currentHealth / maxHealth, h);
		double healthPercent = (double) currentHealth / maxHealth * 100;
		DecimalFormat df = new DecimalFormat("##0.00");
		String num = df.format(healthPercent);
		FontMetrics fontMetrics = new JFrame().getFontMetrics(new Font(DEFAULT_FONT, DEFAULT_FONT_STYLE, 24));
		drawText(x + w / 2 - (fontMetrics.stringWidth(num + "%")) / 2, y + fontMetrics.getAscent(), Color.WHITE,
				num + "%", 24, g2d, DEFAULT_FONT_STYLE);
	}

	// Loads an image from a file
	public BufferedImage loadImage(String path) throws IOException
	{
		BufferedImage image = ImageIO.read(getClass().getResource(path));
		return image;
	}

	// Flips an image horizontally
	public BufferedImage flipImage(BufferedImage img)
	{
		AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
		tx.translate(-img.getWidth(), 0);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		img = op.filter(img, null);
		return img;
	}

	// Creates an overlay with a degree of shading
	public BufferedImage createOverlay(int width, int height, float alpha)
	{
		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics g = img.getGraphics();
		g.setColor(new Color(0f, 0f, 0f, alpha));
		g.fillRect(0, 0, width, height);
		return img;
	}

	// Draws a selector around a button
	public void drawSelector(Graphics g, Button b, Color c)
	{
		RoundRectangle2D r = new RoundRectangle2D.Double(b.getX() - BORDER, b.getY() - BORDER,
				b.getWidth() + 2 * BORDER, b.getHeight() + 2 * BORDER, b.getArcHeight(), b.getArcWidth());
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(c);
		g2d.draw(r);
		g2d.fill(r);
		b.draw(g2d);
	}
}
