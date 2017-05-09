package game;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyInput implements KeyListener {
	
	protected Fighter p;
	protected int keyCodeLeft, keyCodeRight, keyCodeJump;
	
	public KeyInput(Fighter player, int left, int right, int jump) {
		p = player;
		keyCodeLeft = left;
		keyCodeRight = right;
		keyCodeJump = jump;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
	    int keyCode = e.getKeyCode();
//	    switch( keyCode ) { 
//	        case keyCodeLeft:
//	            // handle left
//				p.incrementSpeed(-10);
//	            break;
//	        case keyCodeRight :
//	            // handle right
//				p.incrementSpeed(10);
//	            break;
//	     }
	    if(keyCode == keyCodeLeft) {
	    	p.incrementXSpeed(-20);
	    } 
	    else if (keyCode == keyCodeRight) {
	    	p.incrementXSpeed(20);

	    } 
	    else if (keyCode == keyCodeJump) {
	    	p.jump();
	    }
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
	    int keyCode = e.getKeyCode();
//	    switch( keyCode ) { 
//	        case keyCodeLeft:
//	            // handle left
//				p.incrementSpeed(10);
//	            break;
//	        case keyCodeRight :
//	            // handle right
//				p.incrementSpeed(-10);
//	            break;
//	     }
	    if(keyCode == keyCodeLeft) {
	    	p.incrementXSpeed(20);
	    } 
	    else if (keyCode == keyCodeRight) {
	    	p.incrementXSpeed(-20);
	    } 
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
//	public void timerDown() {
//		timer--;
//	}
}