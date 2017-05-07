package game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class MainGame extends Screen {
	
	Fighter p1, p2;
	
	public MainGame (BufferedImage background, Fighter player1, Fighter player2) {
		super(background);
		p1 = player1;
		p2 = player2;
	}
	
	public void draw(Graphics g) {
		gui = new GUIUtils();
		super.draw(g);
	}
}
