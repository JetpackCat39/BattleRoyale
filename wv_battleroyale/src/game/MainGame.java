package game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class MainGame extends Screen {
	
	BufferedImage p1, p2;
	
	public MainGame (BufferedImage background, BufferedImage player1, BufferedImage player2) {
		super(background);
		p1 = player1;
		p2 = player2;
	}
	
	public void draw(Graphics g) {
		gui = new GUIUtils();
		super.draw(g);
		gui.drawImg(p1, 100, height - 300, p1.getWidth(), p1.getHeight(), g);
		gui.drawImg(p2, width - 200, height - 300, p2.getWidth(), p2.getHeight(), g);
	}
}
