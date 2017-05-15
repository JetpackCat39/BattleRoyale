package game.Input;

//import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import game.BattleRoyale;
import game.BattleRoyale.STATE;
import game.Menus.ChampMenu;
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
	private ChampMenu champ;

	public MouseInput(BattleRoyale g, MainMenu m, ControlsMenu c, PauseMenu p, StageMenu s, ChampMenu ch)
	{	
		game = g;
		menu = m;
		controls = c;
		pause = p;
		stage = s;
		champ = ch;
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
				game.setState(STATE.STAGESELECT);
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
			for (int i = 1; i < controls.getNumButtons(); i++)
			{
				if (controls.getKey(i).contains(myX, myY))
				{
					
				}
			}
		}
		if (game.getState() == STATE.STAGESELECT)
		{
			game.setState(STATE.CHAMPSELECT);
		}
		if (game.getState() == STATE.CHAMPSELECT)
		{
			game.setState(STATE.GAME);
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