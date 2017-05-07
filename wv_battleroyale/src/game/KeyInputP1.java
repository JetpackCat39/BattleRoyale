package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInputP1 implements KeyListener {

	Fighter p;
	
	public KeyInputP1(Fighter player) {
		p = player;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
	    int keyCode = e.getKeyCode();
	    switch( keyCode ) { 
	        case KeyEvent.VK_A :
				p.moveHoriz(-10);
	        	break;
	        case KeyEvent.VK_D :
				p.moveHoriz(10);
	        	break;
	     }
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

}
