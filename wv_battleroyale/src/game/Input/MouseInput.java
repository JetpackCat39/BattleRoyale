package game.Input;

//import java.awt.Cursor;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import game.BattleRoyale;
import game.BattleRoyale.STATE;
import game.Menus.ChampMenu;
import game.Menus.ControlChange;
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
	private ControlChange change;

	public MouseInput(BattleRoyale g, MainMenu m, ControlsMenu c, PauseMenu p, StageMenu s, ChampMenu ch, ControlChange ctrlChange)
	{	
		game = g;
		menu = m;
		controls = c;
		pause = p;
		stage = s;
		champ = ch;
		change = ctrlChange;
	}

	public void mouseClicked(MouseEvent e)
	{
	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		int myX = e.getX();
		int myY = e.getY();
		if (game.getState() == STATE.MENU)
		{
			menuChecker(myX, myY);
		}
		if (game.getState() == STATE.CONTROLS)
		{
			controlsChecker(myX, myY);
		}
		if (game.getState() == STATE.CHAMPSELECT)
		{
			game.setState(STATE.STAGESELECT);
		}
		if (game.getState() == STATE.STAGESELECT)
		{
			game.setState(STATE.GAME);
		}
		if (game.getState() == STATE.PAUSE)
		{
			pauseChecker(myX, myY);
		}
	}

	private void controlsChecker(int myX, int myY)
	{
		if (controls.getBack().contains(myX, myY))
		{
			if (game.getPreviousState() != STATE.CONTROLCHANGE)
			{
				game.setState(game.getPreviousState());
			}
			else
			{
			}
		}
		for (int i = 1; i < controls.getNumButtons(); i++)
		{
			if (controls.getKey(i).contains(myX, myY))
			{
				//change.setKeyToRebind(i);
				//game.setState(STATE.CONTROLCHANGE);
			}
		}
	}

	private void menuChecker(int myX, int myY)
	{
		if (menu.getPlay().contains(myX, myY))
		{
			game.setState(STATE.CHAMPSELECT);
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

	private void pauseChecker(int myX, int myY)
	{
		if (pause.getResume().contains(myX, myY))
		{
			game.setState(STATE.GAME);
		}
		if (pause.getControls().contains(myX, myY))
		{
			game.setState(STATE.CONTROLS);
		}
		if (pause.getTitle().contains(myX, myY))
		{
			game.setState(STATE.MENU);
		}
	}

	public void mouseReleased(MouseEvent e)
	{
	}

	public void mouseEntered(MouseEvent e)
	{
	}

	public void mouseExited(MouseEvent e)
	{
	}

}