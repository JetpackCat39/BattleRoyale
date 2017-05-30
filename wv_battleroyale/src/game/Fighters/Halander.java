package game.Fighters;

import java.awt.image.BufferedImage;

public class Halander extends Fighter
{

	public Halander(int newX, int newY, BufferedImage spriteSheet, BufferedImage victory, BufferedImage KO,
			boolean isPlayer1)
	{
		super(newX, newY, spriteSheet, victory, KO, isPlayer1);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected int getNumImages(STATE s)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected int getAnimationSpeed(STATE s)
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getWidth()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getHeight()
	{
		// TODO Auto-generated method stub
		return 0;
	}

}
