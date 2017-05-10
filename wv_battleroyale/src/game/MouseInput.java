package game;

//import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import game.Game.*;

public class MouseInput implements MouseListener
{

	MainMenu menu;
	Game game;
	
	public MouseInput(MainMenu m, Game g)
	{
		menu = m;
		game = g;
	}
	
	public void mouseClicked(MouseEvent e) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) 
	{
		int myX = e.getX();
		int myY = e.getY();
		
		if (game.getState() == STATE.MENU)
		{
			if (menu.getPlay().contains(myX, myY))
			{
				game.setState(STATE.GAME);
			}
			if (menu.getControls().contains(myX, myY))
			{
				game.setState(STATE.CONTROLS);
			}
			if (menu.getExit().contains(myX, myY))
			{
				game.stop();
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) 
	{
		// TODO Auto-generated method stub
		
	}

	public void mouseEntered(MouseEvent e) 
	{
		
	}

	@Override
	public void mouseExited(MouseEvent e) 
	{
		// TODO Auto-generated method stub
		
	}

}