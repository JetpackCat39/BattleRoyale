package game.Fighters;

import game.Sound;
import game.Input.PlayerControls;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Neuhaus extends Fighter
{
	private static final int SRC_WIDTH = 95;
	private static final int SRC_HEIGHT = 146;
	
	private static final int DRAW_WIDTH = 95 * 2;
	private static final int DRAW_HEIGHT = 146 * 2;
	
	// width of sprite while not attacking is 55ß
	private static final int NON_ATTACK_WIDTH = 95 * 2;
	
	private static final int PUNCH = 1000;
	private static final int BLOCKED_PUNCH = 2000;
	private static final int KICK = 2000;
	private static final int BLOCKED_KICK = 4000;
	private static final int SPEED = 20;
	
	private static final int KICK_HIT = 0;
	private static final int PUNCH_HIT = 0;	
	
	public static final int HEALTH = 100000;
	
	private static final int MAX_FRAMES = 0;
	
	// 180 = fps
	private static final int ANIMATION_COUNT = 1;

	private static final int UPDATES = 360;
	
	public Neuhaus(int newX, int newY, BufferedImage spriteSheet, BufferedImage worl, boolean isPlayer1, PlayerControls c)
	{
		super(newX, newY, spriteSheet, worl, isPlayer1, c, SPEED);
	}

	@Override
	protected int getNumImages(STATE s)
	{
		switch (s)
		{
		case IDLE:
			return ANIMATION_COUNT;
		case WALK:
			return ANIMATION_COUNT;
		case KICK:
			return ANIMATION_COUNT;
		case PUNCH:
			return ANIMATION_COUNT;
		case JUMP:
			return ANIMATION_COUNT;
		case CROUCH:
			return ANIMATION_COUNT;
		case ENTER:
			return ANIMATION_COUNT;
		case BLOCK:
			return ANIMATION_COUNT;
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
			return UPDATES;
		case WALK:
			return UPDATES;
		case KICK:
			return UPDATES;
		case PUNCH:
			return UPDATES;
		case JUMP:
			return UPDATES;
		case CROUCH:
			return UPDATES;
		case ENTER:
			return UPDATES;
		case BLOCK:
			return UPDATES;
		default:
			throw new IndexOutOfBoundsException();
		}
	}

	public int getKickHit() {
		return KICK_HIT;
	}
	
	public int getPunchHit() {
		return PUNCH_HIT;
	}
	
	@Override
	public int getWidth()
	{
		return NON_ATTACK_WIDTH;
	}

	@Override
	public int getSrcWidth()
	{
		return SRC_WIDTH;
	}

	@Override
	public int getSrcHeight()
	{
		return SRC_HEIGHT;
	}

	@Override
	protected int getMaxFrames()
	{
		return MAX_FRAMES;
	}

	@Override
	public int getDrawWidth()
	{
		return DRAW_WIDTH;
	}

	@Override
	public int getDrawHeight()
	{
		return DRAW_HEIGHT;
	}

	@Override
	public int getPunchDamage()
	{
		return PUNCH;
	}

	@Override
	public int getKickDamage()
	{
		return KICK;
	}

	@Override
	public int getBlockedPunchDamage()
	{
		return BLOCKED_PUNCH;
	}

	@Override
	public int getBlockedKickDamage()
	{
		return BLOCKED_KICK;
	}

	@Override
	public int getMaxHealth()
	{
		return HEALTH;
	}

	@Override
	public String getEntranceQuote()
	{
		return "Sounds/NeuhausEntrance.wav";
	}

	@Override
	public String getResponseQuote()
	{
		return "Sounds/NeuhausResponse.wav";
	}

	@Override
	public String getGrunt()
	{
		List<String> temp = new ArrayList<String>();
		temp.add("Sounds/NeuhausGrunt1.wav");
		temp.add("Sounds/NeuhausGrunt2.wav");
		temp.add("Sounds/NeuhausGrunt3.wav");
		temp.add("Sounds/NeuhausGrunt4.wav");
		return temp.get(randomizer.nextInt(temp.size()));
	}

	@Override
	public String getName()
	{
		return "NEUHAUS";
	}
	
	@Override
	public void punch()
	{
		new Sound(getGrunt()).play();
		getOpponent().damage(getPunchDamage());
	}

	@Override
	public void kick()
	{
		new Sound(getGrunt()).play();
		getOpponent().damage(getKickDamage());
	}

	@Override
	public int getKOWidth()
	{
		return SRC_WIDTH;
	}

	@Override
	public int getKOHeight()
	{
		return SRC_HEIGHT;
	}

	@Override
	public int getKOFrames()
	{
		return 1;
	}

	@Override
	public int getVictoryWidth()
	{
		return SRC_WIDTH;
	}

	@Override
	public int getVictoryHeight()
	{
		return SRC_HEIGHT;
	}

	@Override
	public int getVictoryFrames()
	{
		return 1;
	}
}
