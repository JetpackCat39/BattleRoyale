package game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Fighter {
	
	private int x, y, xSpeed, ySpeed;
	private BufferedImage tammy;
	private int height = Game.HEIGHT;
	private int width = Game.WIDTH;
	private final int BASE;
	private int jumpCount;
	
	public Fighter(int newX, int newY) {
		x = newX;
		y = newY;
		ySpeed= 0;
		BASE = newY;
		jumpCount = 0;
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
		if(temp > 15) {
			temp = 15;
		}
		if(temp < -15) {
			temp = -15;
		}
		xSpeed = temp;
	}
	public void setSpeed(int newSpeed) {
		xSpeed = newSpeed;
	}
	
	public void move() {
		x += xSpeed;
		if(x > width - tammy.getWidth())
			x = width - tammy.getWidth();
		if(x < 0)
			x = 0;
		y += ySpeed;
		if(height - y > height - BASE) {
			y = BASE;
			jumpCount = 0;
		}
		if(height - y < height - BASE) {
			ySpeed -= 1;
		}
		if(height - y < 0) {
			y = height;
			ySpeed *= -1;
		}
	}
	
	public int getX() {
		return x;
	}
	
	public int getSpeed() {
		return xSpeed;
	}
	
	public void jump() {
		if(jumpCount < 2)
			ySpeed = 20;
		jumpCount++;
	}
	
	public void draw(Graphics g) {
		GUIUtils gui = new GUIUtils();
		gui.drawImg(tammy, x, height - y, tammy.getWidth(), tammy.getHeight(), g);
	}
}
