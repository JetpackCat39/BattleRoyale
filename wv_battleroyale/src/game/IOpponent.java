package game;

public interface IOpponent extends IDrawable
{

	int getXSpeed();

	int getYSpeed();

	int getX();

	int getY();

	int getWidth();

	int getHeight();

	void damage(int damage);

	void setX(int val);

}