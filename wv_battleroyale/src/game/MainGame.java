package game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class MainGame extends Screen {
	
	BufferedImage p1, p2;
	
	public MainGame (Graphics newG, BufferedImage background, BufferedImage player1, BufferedImage player2) {
		super(newG, background);
		p1 = player1;
		p2 = player2;
	}
	
	public void draw() {
		super.draw();
		gui.drawImg(p1, 100, 100, p1.getWidth(), p1.getHeight());
		gui.drawImg(p2, 100, 100, p2.getWidth(), p2.getHeight());
	}
}
