package game;

//import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import game.Game.*;

public class MouseInput implements MouseListener
{

	MainMenu menu;
	Game game;
	ControlsMenu controls;
	public MouseInput(MainMenu m, Game g, ControlsMenu c)
	{
		menu = m;
		game = g;
		controls = c;
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
		if(game.getState()==STATE.CONTROLS){
			if(controls.getBack().contains(myX,myY)){
				game.setState(STATE.MENU);
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