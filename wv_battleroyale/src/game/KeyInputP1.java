package game;

import java.awt.event.KeyEvent;

public class KeyInputP1 extends KeyInput
{

	public KeyInputP1(Fighter player)
	{
		super(player, KeyEvent.VK_A, KeyEvent.VK_D, KeyEvent.VK_W, KeyEvent.VK_T, KeyEvent.VK_Y);
	}
}
