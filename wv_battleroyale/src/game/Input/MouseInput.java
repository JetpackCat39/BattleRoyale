package game.Input;

//import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import game.BattleRoyale;
import game.BattleRoyale.STATE;
import game.Menus.ControlsMenu;
import game.Menus.MainMenu;
import game.Menus.PauseMenu;
import game.Menus.StageMenu;

public class MouseInput implements MouseListener
{

	private BattleRoyale game;
	private MainMenu menu;
	private ControlsMenu controls;
	private PauseMenu pause;
	private StageMenu stage;

	public MouseInput(BattleRoyale g, MainMenu m, ControlsMenu c, PauseMenu p, StageMenu s)
	{	
		game = g;
		menu = m;
		controls = c;
		pause = p;
		stage = s;
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
		if (game.getState() == STATE.CONTROLS)
		{
			if (controls.getBack().contains(myX, myY))
			{
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