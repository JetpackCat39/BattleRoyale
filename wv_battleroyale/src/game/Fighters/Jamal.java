package game.Fighters;

import java.awt.image.BufferedImage;

import game.Input.PlayerControls;

public class Jamal extends Fighter
{


	public Jamal(int newX, int newY, BufferedImage spriteSheet, BufferedImage victory, BufferedImage KO, PlayerControls ctrls)
	{
		super(newX, newY, spriteSheet, victory, KO, ctrls);
	}

	@Override
	protected int getNumImages(STATE s)
	{
		switch (s)
		{
		case IDLE:
			return 4;
		case WALK:
			return 5;
		case KICK:
			return 4;
		case PUNCH:
			return 3;
		case JUMP:
			return 7;
		case CROUCH:
			return 2;
		case ENTER:
			return 1;
		case BLOCK:
			return 2;
		case HIT:
			return 4;
		default:
			throw new IndexOutOfBoundsException();
		}
	}

	@Override
	public int getWidth()
	{
		return 75;
	}

	@Override
	public int getHeight()
	{
		return 115;
	}
	
}
