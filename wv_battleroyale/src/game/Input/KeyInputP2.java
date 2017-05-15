package game.Input;

import java.awt.event.KeyEvent;

import game.Fighter;

public class KeyInputP2 extends KeyInput
{

	private static final int P2_LEFT = KeyEvent.VK_LEFT;
	private static final int P2_RIGHT = KeyEvent.VK_RIGHT;
	private static final int P2_JUMP = KeyEvent.VK_UP;
	private static final int P2_CROUCH = KeyEvent.VK_DOWN;
	private static final int P2_PUNCH = KeyEvent.VK_N;
	private static final int P2_KICK = KeyEvent.VK_M;
	
	
	
	public KeyInputP2(Fighter player)
	{
		super(player, P2_LEFT, P2_RIGHT, P2_JUMP, P2_CROUCH, P2_PUNCH, P2_KICK);
	}
	

}
