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
		xSpeed = 0;
		ySpeed= 0;
		BASE = newY;
		jumpCount = 0;
		opponent = null;
		BufferedImageLoader loader = new BufferedImageLoader();
		try {
			image = loader.loadImage("tammy.png");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void incrementXSpeed(int howMuch) {
		int temp = xSpeed + howMuch;
		if(temp > 15) {
			temp = 15;
		}
		if(temp < -15) {
			temp = -15;
		}
		xSpeed = temp;
	}
	
	public void incrementYSpeed(int howMuch)
	{
		int temp = xSpeed + howMuch;
		if(temp > 15) {
			temp = 15;
		}
		if(temp < -15) {
			temp = -15;
		}
		ySpeed = temp;
	}
	public void setXSpeed(int newSpeed) {
		xSpeed = newSpeed;
	}
	
	public void setYSpeed(int newSpeed)
	{
		ySpeed = newSpeed;
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
		moveCollisionChecker();
	}
	
	public void moveCollisionChecker()
	{
		if(compareXPosition() != 0)
		{
			if(compareYPosition() > 0)
			{
				ySpeed = 0;
			}
			else if(compareYPosition() < 0)
			{
				opponent.setYSpeed(0);
			}
			else if((y <= BASE + getHeight() && opponent.getY() <= BASE + opponent.getHeight()) || (y > BASE && opponent.getY() > BASE))
			{
				if(compareXPosition() > 0)
				{
					x = opponent.getX() + opponent.getWidth();
				}
				else
				{
					x = opponent.getX() - opponent.getWidth();
				}
			}
		}
	}
	public int compareXPosition()
	{
		//if you're to the right of them and touching them
		if(x < opponent.getX() + opponent.getWidth() && x > opponent.getX())
		{
			return 1;
		}
		//if you're to the left of them and touching them
		else if(x + getWidth() > opponent.getX() && opponent.getX() > x)
		{
			return -1;
		}
		return 0;
	}
	
	public int compareYPosition()
	{
		//if you're above them and touching them
		if((y < opponent.getY() + opponent.getHeight()) && (y > (opponent.getY() + opponent.getHeight() - 5)))
		{
			return 1;
		}
		//if you're below them and touching them
		else if((y + getHeight() > opponent.getY()) && (opponent.getY() > (y + getHeight() - 5)))
		{
			return -1;
		}
		return 0;
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
	
	public int getXSpeed() {
		return xSpeed;
	}
	
	public int getYSpeed()
	{
		return ySpeed;
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
