package game.Fighters;

import java.awt.image.BufferedImage;

public class Nguyen extends Fighter
{

	public Nguyen(int newX, int newY, BufferedImage spriteSheet, BufferedImage victory, BufferedImage KO,
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
	public int getSrcWidth()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getSrcHeight()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected int getMaxFrames()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getDrawWidth()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getDrawHeight()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getPunchDamage()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getBlockedPunchDamage()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getKickDamage()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getBlockedKickDamage()
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMaxHealth()
	{
		// TODO Auto-generated method stub
		return 0;
	}

}
