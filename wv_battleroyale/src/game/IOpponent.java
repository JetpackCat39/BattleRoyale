package game;

public interface IOpponent extends IDrawable
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

}