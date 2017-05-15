package game.Input;

import java.awt.event.KeyEvent;

import game.Fighter;

public class KeyInputP2 extends KeyInput
{

	public KeyInputP2(Fighter player)
	{
		super(player, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT, KeyEvent.VK_UP, KeyEvent.VK_1, KeyEvent.VK_2);
	}

}
