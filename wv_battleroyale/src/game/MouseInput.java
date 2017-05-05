package game;

//import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import game.Game.*;

public class MouseInput implements MouseListener
{

	MainMenu menu;
	
	public MouseInput(MainMenu m)
	{
		menu = m;
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
		
		if (Game.State == STATE.MENU)
		{
			if (menu.getPlay().contains(myX, myY))
			{
				Game.State = STATE.GAME;
			}
			if (menu.getControls().contains(myX, myY))
			{
				Game.State = STATE.CONTROLS;
			}
			if (menu.getExit().contains(myX, myY))
			{
				System.exit(1);
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