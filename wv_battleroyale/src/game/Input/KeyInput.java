package game.Input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import game.Fighter;

public class KeyInput implements KeyListener
{

	protected Fighter p;
	protected int keyCodeLeft, keyCodeRight, keyCodeJump, keyCodeCrouch, keyCodePunch, keyCodeKick;

	public KeyInput(Fighter player, int left, int right, int jump, int crouch, int punch, int kick)
	{
		p = player;
		keyCodeLeft = left;
		keyCodeRight = right;
		keyCodeJump = jump;
		keyCodeCrouch = crouch;
		keyCodePunch = punch;
		keyCodeKick = kick;
	}

	public void keyPressed(KeyEvent e)
	{
		// TODO Auto-generated method stub
		int keyCode = e.getKeyCode();
		// switch( keyCode ) {
		// case keyCodeLeft:
		// // handle left
		// p.incrementSpeed(-10);
		// break;
		// case keyCodeRight :
		// // handle right
		// p.incrementSpeed(10);
		// break;
		// }
		if (keyCode == keyCodeLeft)
		{
			p.setXSpeed(-15);
		}
		else if (keyCode == keyCodeRight)
		{
			p.setXSpeed(15);
		}
		else if (keyCode == keyCodeJump)
		{
			p.jump();
		}
		else if (keyCode == keyCodePunch)
		{
			p.punch();
		}
		else if (keyCode == keyCodeKick)
		{
			p.kick();
		}
	}

	public void keyReleased(KeyEvent e)
	{
		// TODO Auto-generated method stub
		int keyCode = e.getKeyCode();
		// switch( keyCode ) {
		// case keyCodeLeft:
		// // handle left
		// p.incrementSpeed(10);
		// break;
		// case keyCodeRight :
		// // handle right
		// p.incrementSpeed(-10);
		// break;
		// }
		if (keyCode == keyCodeLeft)
		{
			if (p.getXSpeed() != 0)
				p.incrementXSpeed(15);
		}
		else if (keyCode == keyCodeRight)
		{
			if (p.getXSpeed() != 0)
				p.incrementXSpeed(-15);
		}

	}

	public void keyTyped(KeyEvent e)
	{
	}

	// public void timerDown() {
	// timer--;
	// }
}
