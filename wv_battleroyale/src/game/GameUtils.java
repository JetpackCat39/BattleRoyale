package game;

import java.awt.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.DecimalFormat;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
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

	public void drawText (Color c, int height, String text, int fontSize, Graphics g)
	{
		FontMetrics fontMetrics = new JFrame().getFontMetrics(new Font(DEFAULT_FONT, DEFAULT_FONT_STYLE, fontSize));
		drawText(CENTER - (fontMetrics.stringWidth(text))/4, height, c, text, fontSize, g, DEFAULT_FONT_STYLE);
	}
	
	public void drawText(int x, int y, String text, int fontSize, Graphics g)
	{
		drawText(x, y, DEFAULT_COLOR, text, fontSize, g, DEFAULT_FONT_STYLE);
	}

	public void drawText(int x, int y, Color color, String text, int fontSize, Graphics g, int fontStyle)
	{
		Graphics2D g2d = (Graphics2D) g;
		g2d.setFont(new Font(DEFAULT_FONT, fontStyle, fontSize));
		g2d.setColor(color);
		g2d.drawString(text, x, y);
	}

	public void drawImg(BufferedImage img, int x, int y, int w, int h, Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(img, x, y, w, h, null);
	}

	public void drawImg(BufferedImage img, int xSrc, int ySrc, int xDest, int yDest, int wSrc, int hSrc, int wDest,
			int hDest, Graphics g)
	{
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(img, xDest, yDest, xDest + wDest, yDest + hDest, xSrc, ySrc, xSrc + wSrc, ySrc + hSrc, null);
	}
	
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

	public BufferedImage loadImage(String path) throws IOException
	{
		BufferedImage image = ImageIO.read(getClass().getResource(path));
		return image;
	}

	public BufferedImage flipImage(BufferedImage img)
	{
		// Flips the image horizontally
		AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
		tx.translate(-img.getWidth(null), 0);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		img = op.filter(img, null);
		return img;
	}

	public BufferedImage createOverlay(int width, int height, float alpha)
	{
		BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics g = img.getGraphics();
		g.setColor(new Color(0f, 0f, 0f, alpha));
		g.fillRect(0, 0, width, height);
		return img;
	}

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

	public void playSound(String path)
			throws MalformedURLException, LineUnavailableException, UnsupportedAudioFileException, IOException
	{
		Clip clip = AudioSystem.getClip();
		AudioInputStream ais = AudioSystem.getAudioInputStream(getClass().getResource(path));
		clip.open(ais);
		clip.start();
	}

	public long getSoundFileLength(String path) throws UnsupportedAudioFileException, IOException, LineUnavailableException
	{
		AudioInputStream stream;
		stream = AudioSystem.getAudioInputStream(getClass().getResource(path));
		AudioFormat format = stream.getFormat();
		if (format.getEncoding() != AudioFormat.Encoding.PCM_SIGNED)
		{
			format = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED, format.getSampleRate(),
					format.getSampleSizeInBits() * 2, format.getChannels(), format.getFrameSize() * 2,
					format.getFrameRate(), true);
			stream = AudioSystem.getAudioInputStream(format, stream);
		}
		DataLine.Info info = new DataLine.Info(Clip.class, stream.getFormat(),
				((int) stream.getFrameLength() * format.getFrameSize()));
		Clip clip = (Clip) AudioSystem.getLine(info);
		clip.close();
		return  (long) (clip.getBufferSize() / (clip.getFormat().getFrameSize() * clip.getFormat().getFrameRate()));
	}

}
