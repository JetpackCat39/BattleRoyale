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
	private int health;
	private Fighter opponent;
	
	public Fighter(int newX, int newY) {
		x = newX;
		y = newY;
		xSpeed = 0;
		ySpeed= 0;
		BASE = newY;
		jumpCount = 0;
		opponent = null;
		health = 20;
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
		if(temp > 20) {
			temp = 20;
		}
		if(temp < -20) {
			temp = -20;
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
	
	public int getXSpeed() {
		return xSpeed;
	}
	
	public int getYSpeed()
	{
		return ySpeed;
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
			if(compareYPosition() > 0 && y - ySpeed > opponent.getY() + opponent.getHeight())
			{
				ySpeed = 0;
				jumpCount = 0;
				y = opponent.getY() + opponent.getHeight();
			}
			else if(compareYPosition() < 0) {
				ySpeed = -1;
				jumpCount = 2;
			}
			else if((y < BASE + getHeight() && opponent.getY() < BASE + opponent.getHeight()) || ((y > BASE && opponent.getY() > BASE) && compareYPosition() != 0))
			{
				x = x - xSpeed;
				opponent.setX(opponent.getX() - opponent.getXSpeed());
//				if(compareXPosition() > 0)
//					x = x - xSpeed;
////					x = opponent.getX() + opponent.getWidth();
//				else
//					x = x + xSpeed;
////					x = opponent.getX() - opponent.getWidth();
			}
		}
	}
	//will only return non-0 values if fighters are touching
	public int compareXPosition()
	{
		//if you're to the right of them
		if(x < opponent.getX() + opponent.getWidth() && x > opponent.getX())
			return 1;
		//if you're to the left of them
		else if(x + getWidth() > opponent.getX() && opponent.getX() > x)
			return -1;
		return 0;
	}
	//will only return non-0 values if fighters are touching
	public int compareYPosition()
	{
		//if you're above them
		if((y <= opponent.getY() + opponent.getHeight()) && (y > (opponent.getY())))
			return 1;
		//if you're below them
		else if(y + getHeight() >= opponent.getY() && opponent.getY() > y)
			return -1;
		return 0;
	}
	
	public void setX(int val) {
		x = val;
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
	
	public int getJumpCount()
	{
		return jumpCount;
	}
	
	public void setJumpCount(int count)
	{
		jumpCount = count;
	}
	public void jump() {
		if(jumpCount < 2)
			ySpeed = 20;
		jumpCount++;
	}
	
	public void punch()
	{
		if(compareXPosition() != 0)
		{
			opponent.incrementHealth(20);
		}
	}
	
	public void kick()
	{
		if(compareXPosition() != 0)
		{
			opponent.incrementHealth(2);
		}
	}
	
	public int getHealth()
	{
		return health;
	}
	
	public void setHealth(int newHealth)
	{
		health = newHealth;
	}
	
	public void incrementHealth(int damage)
	{
		health -= damage;
		if(health < 0)
		{
			setHealth(0);
		}
		if(health > 20)
		{
			setHealth(20);
		}
		if(health == 0)
		{
			
		}
	}
	public void draw(Graphics g) {
		GUIUtils gui = new GUIUtils();
		gui.drawImg(image, x, height - y, image.getWidth(), image.getHeight(), g);
	}
	
	public void setOpponent(Fighter fighter) {
		opponent = fighter;
	}
}