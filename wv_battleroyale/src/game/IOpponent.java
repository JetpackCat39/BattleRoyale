package game;

import java.awt.Graphics;

import game.Input.IKeya;

public interface IOpponent extends IKeya
{

	public int getXSpeed();

	public int getYSpeed();

	public void setXSpeed(int val);

	public void setYSpeed(int val);

	public int getX();

	public int getY();

	public int getWidth();

	public int getHeight();

	public void damage(int damage);

	public void setX(int val);

	public void setY(int val);

	public boolean move(int minX, int maxX);

	public void draw(Graphics g, int offset);
	
	public int getPause();

	
		
	

}