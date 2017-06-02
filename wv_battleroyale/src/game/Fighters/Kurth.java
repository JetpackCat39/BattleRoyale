package game.Fighters;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import game.Input.PlayerControls;

public class Kurth extends Fighter
{

	private static final int SRC_WIDTH = 89;
	private static final int SRC_HEIGHT = 132;
	
	private static final int DRAW_WIDTH = SRC_WIDTH * 2;
	private static final int DRAW_HEIGHT = SRC_HEIGHT * 2;
	
	// width of sprite while not attacking is 70ß
	private static final int NON_ATTACK_WIDTH = 70 * 2;
	
	private static final int PUNCH = 15;
	private static final int BLOCKED_PUNCH = 7;
	private static final int KICK = 15;
	private static final int BLOCKED_KICK = 7;
	private static final int SPEED = 4;
	
	private static final int KICK_HIT = 1;
	private static final int PUNCH_HIT = 2;	
	
	public static final int HEALTH = 1600;
	
	private static final int MAX_FRAMES = 7;
	
	private static final int VICTORY_WIDTH = 72;
	private static final int VICTORY_HEIGHT = 113;
	private static final int VICTORY_ANIMATION_COUNT = 5;
	private static final int KO_WIDTH = 105;
	private static final int KO_HEIGHT = 73;
	private static final int KO_ANIMATION_COUNT = 5;
	
	// 180 = fps
	
	private static final int BLOCK_ANIMATION_COUNT = 2;
	private static final int STAGE_ENTRANCE_ANIMATION_COUNT = 1;
	private static final int CROUCH_ANIMATION_COUNT = 2;
	private static final int JUMP_ANIMATION_COUNT = 4;
	private static final int PUNCH_ANIMATION_COUNT = 5;
	private static final int KICK_ANIMATION_COUNT = 3;
	private static final int WALK_ANIMATION_COUNT = 8;
	private static final int IDLE_ANIMATION_COUNT = 4;
	
	private static final int BLOCK_UPDATE_COUNT = 180 / (5 * BLOCK_ANIMATION_COUNT); //goes to a block pose in a fifth of a second
	private static final int CROUCH_UPDATE_COUNT = 180 / (5 * CROUCH_ANIMATION_COUNT);  //goes to a crouch pose in a fifth of a second
	private static final int JUMP_UPDATE_COUNT = 180 / (1 * JUMP_ANIMATION_COUNT); // goes through a jump animation over 1 second
	private static final int PUNCH_UPDATE_COUNT = 180 / (int) ((double) 1/2 * PUNCH_ANIMATION_COUNT); // makes it punch twice in a second
	private static final int KICK_UPDATE_COUNT = 180 / (int) ((double) 1/2 * KICK_ANIMATION_COUNT); // makes it kick twice in a second
	private static final int WALK_UPDATE_COUNT = 180 / (2 * WALK_ANIMATION_COUNT); // walks 2 cycles in a second
	private static final int IDLE_UPDATE_COUNT = 180 / (2 * IDLE_ANIMATION_COUNT); // makes it cycle through an "idle" animation 2 times a second
	private static final int STAGE_ENTRANCE_UPDATE_COUNT = 20;
	
	public Kurth(int newX, int newY, BufferedImage spriteSheet, BufferedImage worl, boolean isPlayer1, PlayerControls c)
	{
		super(newX, newY, spriteSheet, worl, isPlayer1, c, SPEED);
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
		return "Sounds/KurthEntrance.wav";
	}

	@Override
	public String getResponseQuote()
	{
		return "Sounds/KurthResponse.wav";
	}

	@Override
	public String getGrunt()
	{
		List<String> temp = new ArrayList<String>();
		temp.add("Sounds/KurthGrunt1.wav");
		temp.add("Sounds/KurthGrunt2.wav");		
		temp.add("Sounds/KurthGrunt3.wav");
		temp.add("Sounds/KurthGrunt4.wav");
		temp.add("Sounds/KurthGrunt5.wav");
		temp.add("Sounds/KurthGrunt6.wav");
		temp.add("Sounds/KurthGrunt7.wav");
		temp.add("Sounds/KurthGrunt8.wav");
		temp.add("Sounds/KurthGrunt9.wav");
		temp.add("Sounds/KurthGrunt10.wav");
		temp.add("Sounds/KurthGrunt11.wav");
		return temp.get(randomizer.nextInt(temp.size()));
	}

	@Override
	public String getName()
	{
		return "KURTH";
	}
	@Override
	public int getKOWidth()
	{
		return KO_WIDTH;
	}

	@Override
	public int getKOHeight()
	{
		return KO_HEIGHT;
	}

	@Override
	public int getKOFrames()
	{
		return KO_ANIMATION_COUNT;
	}

	@Override
	public int getVictoryWidth()
	{
		return VICTORY_WIDTH;
	}

	@Override
	public int getVictoryHeight()
	{
		return VICTORY_HEIGHT;
	}

	@Override
	public int getVictoryFrames()
	{
		return VICTORY_ANIMATION_COUNT;
	}

}
