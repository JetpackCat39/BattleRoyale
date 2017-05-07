package game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Fighter {
	
	private int x, y, xSpeed;
	BufferedImage tammy;
	int height = Game.HEIGHT;
	int width = Game.WIDTH;
	
	public Fighter(int newX, int newY) {
		x = newX;
		y = newY;
		BufferedImageLoader loader = new BufferedImageLoader();
		try {
			tammy = loader.loadImage("tammy.png");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void incrementSpeed(int howMuch) {
		int temp = xSpeed + howMuch;
		if(temp > 10) {
			temp = 10;
		}
		if(temp < -10) {
			temp = -10;
		}
		xSpeed = temp;
	}
	public void setSpeed(int newSpeed) {
		xSpeed = newSpeed;
	}
	
	public void move() {
		x += xSpeed;
	}
	
	public int getX() {
		return x;
	}
	
	public int getSpeed() {
		return xSpeed;
	}
	
	public void draw(Graphics g) {
		GUIUtils gui = new GUIUtils();
		gui.drawImg(tammy, x, height - y, tammy.getWidth(), tammy.getHeight(), g);
	}
}
