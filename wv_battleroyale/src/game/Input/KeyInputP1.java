package game.Input;

import java.awt.event.KeyEvent;

import game.Fighter;

public class KeyInputP1 extends KeyInput
{

	private static final int P1_KICK = KeyEvent.VK_Y;
	private static final int P1_PUNCH = KeyEvent.VK_T;
	private static final int P1_CROUCH = KeyEvent.VK_S;
	private static final int P1_JUMP = KeyEvent.VK_W;
	private static final int P1_RIGHT = KeyEvent.VK_D;
	private static final int P1_LEFT = KeyEvent.VK_A;

	public KeyInputP1(Fighter player)
	{
		super(player, P1_LEFT, P1_RIGHT, P1_JUMP, P1_CROUCH, P1_PUNCH, P1_KICK);
	}
}
