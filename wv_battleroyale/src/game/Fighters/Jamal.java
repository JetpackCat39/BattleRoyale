package game.Fighters;

import java.awt.image.BufferedImage;

public class Jamal extends Fighter
{
	private static final int JAMAL_HEIGHT = 115;
	private static final int JAMAL_WIDTH = 75;
	private static final int CHARACTER_HIT_UPDATE_COUNT = 20;
	private static final int BLOCK_UPDATE_COUNT = 20;
	private static final int STAGE_ENTRANCE_UPDATE_COUNT = 20;
	private static final int CROUCH_UPDATE_COUNT = 20;
	private static final int JUMP_UPDATE_COUNT = 120/7;
	private static final int PUNCH_UPDATE_COUNT = 40;
	private static final int KICK_UPDATE_COUNT = 40;
	private static final int WALK_UPDATE_COUNT = 30;
	private static final int IDLE_UPDATE_COUNT = 20;
	private static final int CHARACTER_HIT_ANIMATION_COUNT = 4;
	private static final int BLOCK_ANIMATION_COUNT = 2;
	private static final int STAGE_ENTRANCE_ANIMATION_COUNT = 1;
	private static final int CROUCH_ANIMATION_COUNT = 2;
	private static final int JUMP_ANIMATION_COUNT = 7;
	private static final int PUNCH_ANIMATION_COUNT = 3;
	private static final int KICK_ANIMATION_COUNT = 4;
	private static final int WALK_ANIMATION_COUNT = 5;
	private static final int IDLE_ANIMATION_COUNT = 4;

	public Jamal(int newX, int newY, BufferedImage spriteSheet, BufferedImage victory, BufferedImage KO, boolean isPlayer1)
	{
		super(newX, newY, spriteSheet, victory, KO, isPlayer1);
	}

	@Override
	protected int getNumImages(STATE s)
	{
		switch (s)
		{
		case IDLE:
			return IDLE_ANIMATION_COUNT;
		case WALK:
			return WALK_ANIMATION_COUNT;
		case KICK:
			return KICK_ANIMATION_COUNT;
		case PUNCH:
			return PUNCH_ANIMATION_COUNT;
		case JUMP:
			return JUMP_ANIMATION_COUNT;
		case CROUCH:
			return CROUCH_ANIMATION_COUNT;
		case ENTER:
			return STAGE_ENTRANCE_ANIMATION_COUNT;
		case BLOCK:
			return BLOCK_ANIMATION_COUNT;
		case HIT:
			return CHARACTER_HIT_ANIMATION_COUNT;
		default:
			throw new IndexOutOfBoundsException();
		}
	}

	@Override
	protected int getAnimationSpeed(STATE s)
	{
		switch (s)
		{
		case IDLE:
			return IDLE_UPDATE_COUNT;
		case WALK:
			return WALK_UPDATE_COUNT;
		case KICK:
			return KICK_UPDATE_COUNT;
		case PUNCH:
			return PUNCH_UPDATE_COUNT;
		case JUMP:
			return JUMP_UPDATE_COUNT;
		case CROUCH:
			return CROUCH_UPDATE_COUNT;
		case ENTER:
			return STAGE_ENTRANCE_UPDATE_COUNT;
		case BLOCK:
			return BLOCK_UPDATE_COUNT;
		case HIT:
			return CHARACTER_HIT_UPDATE_COUNT;
		default:
			throw new IndexOutOfBoundsException();
		}
	}
	
	@Override
	public int getWidth()
	{
		return JAMAL_WIDTH;
	}

	@Override
	public int getHeight()
	{
		return JAMAL_HEIGHT;
	}
}
