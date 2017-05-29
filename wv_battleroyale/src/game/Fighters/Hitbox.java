package game.Fighters;

public class Hitbox
{

	private int leftBound, rightBound, topBound, bottomBound;

	public Hitbox(int x, int y, int width, int height)
	{
		leftBound = x;
		rightBound = x + width;
		topBound = y + height;
		bottomBound = y;
	}

	public int getLeftBound()
	{
		return leftBound;
	}

	public int getRightBound()
	{
		return rightBound;
	}

	public int getTopBound()
	{
		return topBound;
	}

	public int getBottomBound()
	{
		return bottomBound;
	}

	public int compareLeftBound(Hitbox other)
	{
		return other.getLeftBound() - leftBound;
	}

	public int compareRightBound(Hitbox other)
	{
		return other.getRightBound() - rightBound;
	}

	public int compareTopBound(Hitbox other)
	{
		return other.getTopBound() - topBound;
	}

	public int compareBottomBound(Hitbox other)
	{
		return other.getBottomBound() - bottomBound;
	}
}
