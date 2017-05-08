package game;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Fighter {
	
	private int x, y, xSpeed, ySpeed;
	private BufferedImage image;
	private int height = Game.HEIGHT;
	private int width = Game.WIDTH;
	private final int BASE;
	private int jumpCount;
	private Fighter opponent;
	
	public Fighter(int newX, int newY) {
		x = newX;
		y = newY;
		ySpeed= 0;
		BASE = newY;
		jumpCount = 0;
		BufferedImageLoader loader = new BufferedImageLoader();
		try {
			image = loader.loadImage("tammy.png");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void incrementSpeed(int howMuch) {
		int temp = xSpeed + howMuch;
		if(temp > 20) {
			temp = 20;
		}
		if(temp < -20) {
			temp = -20;
		}
		xSpeed = temp;
	}
	public void setSpeed(int newSpeed) {
		xSpeed = newSpeed;
	}
	
	public void move() {
		x += xSpeed;
		if(x > width - image.getWidth())
			x = width - image.getWidth();
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
	
	public int getY() {
		return y;
	}
	
	public int getWidth() {
		return image.getWidth();
	}
	
	public int getHeight() {
		return image.getHeight();
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
		gui.drawImg(image, x, height - y, image.getWidth(), image.getHeight(), g);
	}
	
	public void setOpponent(Fighter fighter) {
		opponent = fighter;
	}
}
