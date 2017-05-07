package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInputP2 implements KeyListener {

	Fighter p;
	
	public KeyInputP2(Fighter player) {
		p = player;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
	    int keyCode = e.getKeyCode();
	    switch( keyCode ) { 
	        case KeyEvent.VK_LEFT:
	            // handle left
				p.moveHoriz(-10);
	            break;
	        case KeyEvent.VK_RIGHT :
	            // handle right
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
