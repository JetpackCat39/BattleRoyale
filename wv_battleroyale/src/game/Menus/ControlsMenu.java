package game.Menus;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import game.Button;
import game.GUIUtils;
import game.Input.PlayerControls;
import game.BattleRoyale.STATE;

public class ControlsMenu extends Screen
{
	private static final int SIDE = 80;
	private PlayerControls player1, player2;
	private boolean otherMenu = true;
	private Button changingControl = null;

	public ControlsMenu(BufferedImage background, PlayerControls p1, PlayerControls p2)
	{
		super(background);
		player1 = p1;
		player2 = p2;
		buttonList.add(new Button(width * 1 / 13, height * 3 / 19, "BACK"));
		// player 1 movement controls
		buttonList.add(new Button(width * 4 / 15, height * 5 / 9, SIDE, SIDE, 0, player1.getJumpString())
				.setAction(newKey -> player1.setJump(newKey)));
		buttonList.add(new Button(width * 4 / 15, height * 25 / 36, SIDE, SIDE, 0, player1.getCrouchString())
				.setAction(newKey -> player1.setCrouch(newKey)));
		buttonList.add(new Button(width * 23 / 120, height * 25 / 36, SIDE, SIDE, 0, player1.getLeftString())
				.setAction(newKey -> player1.setLeft(newKey)));
		buttonList.add(new Button(width * 41 / 120, height * 25 / 36, SIDE, SIDE, 0, player1.getRightString())
				.setAction(newKey -> player1.setRight(newKey)));
		// player 1 punch
		buttonList.add(new Button(width * 23 / 120, height * 5 / 6, SIDE, SIDE, 0, player1.getPunchString())
				.setAction(newKey -> player1.setPunch(newKey)));
		// player 1 kick
		buttonList.add(new Button(width * 41 / 120, height * 5 / 6, SIDE, SIDE, 0, player1.getKickString())
				.setAction(newKey -> player1.setKick(newKey)));

		// player 2 movement controls
		buttonList.add(new Button(width * 5 / 8, height * 5 / 9, SIDE, SIDE, 0, player2.getJumpString())
				.setAction(newKey -> player1.setJump(newKey))); // up
		buttonList.add(new Button(width * 5 / 8, height * 25 / 36, SIDE, SIDE, 0, player2.getCrouchString())
				.setAction(newKey -> player1.setCrouch(newKey)));
		buttonList.add(new Button(width * 13 / 24, height * 25 / 36, SIDE, SIDE, 0, player2.getLeftString())
				.setAction(newKey -> player1.setLeft(newKey)));
		buttonList.add(new Button(width * 17 / 24, height * 25 / 36, SIDE, SIDE, 0, player2.getRightString())
				.setAction(newKey -> player1.setRight(newKey)));
		// player 2 punch
		buttonList.add(new Button(width * 13 / 24, height * 5 / 6, SIDE, SIDE, 0, player2.getPunchString())
				.setAction(newKey -> player1.setPunch(newKey)));
		// player 2 kick
		buttonList.add(new Button(width * 17 / 24, height * 5 / 6, SIDE, SIDE, 0, player2.getKickString())
				.setAction(newKey -> player1.setKick(newKey)));
		// pause button
		buttonList.add(new Button(width * 1 / 16, height * 5 / 9, SIDE, SIDE, 0, player1.getPauseString())
		.setAction(newKey -> player1.setPause(newKey)));
	}

	@Override
	public void draw(Graphics g)
	{
		super.draw(g);

		if (changingControl != null)
		{
			GUIUtils.self().drawText(width / 4, height / 3, "Press any key to rebind...", 50, g, false);
		}
		else
		{

			GUIUtils.self().drawText(width / 3, height * 2 / 9, "CONTROLS", 72, g, false);
			GUIUtils.self().drawText(width / 5, height * 17 / 36, "PLAYER 1", 54, g, false);
			GUIUtils.self().drawText(width * 5 / 9, height * 17 / 36, "PLAYER 2", 54, g, false);
			GUIUtils.self().drawText(width * 47 / 240, height * 39 / 40, "PUNCH", 20, g, false);
			GUIUtils.self().drawText(width * 17 / 48, height * 39 / 40, "KICK", 20, g, false);
			GUIUtils.self().drawText(width * 35 / 64, height * 39 / 40, "PUNCH", 20, g, false);
			GUIUtils.self().drawText(width * 23 / 32, height * 39 / 40, "KICK", 20, g, false);
			GUIUtils.self().drawText(width * 1 / 15, height * 19 / 27, "PAUSE", 20, g, false);
			for (int i = 0; i < buttonList.size(); i++)
			{
				getKey(i).draw(g);
			}
		}
	}

	public Button getBack()
	{
		return buttonList.get(0);
	}

	public Button getKey(int num)
	{
		return buttonList.get(num);
	}

	public int getNumButtons()
	{
		return buttonList.size();
	}

	public void rebind(int keyToRebind, char key)
	{
		Button b = buttonList.get(keyToRebind);
		b.setText(String.valueOf(key));
	}

	@Override
	public STATE mousePressed(int x, int y, STATE currentState, STATE previousState)
	{
		if (changingControl == null)
		{
			if (getBack().contains(x, y))
			{
				otherMenu = true;
				return previousState;

			}
			if (!otherMenu)
			{
				for (int i = 1; i < getNumButtons(); i++)
				{
					if (getKey(i).contains(x, y))
					{
						changingControl = getKey(i);
					}
				}
			}
			otherMenu = false;
		}
		return currentState;
	}

	@Override
	public STATE keyPressed(int keyCode, STATE currentState, STATE previousState)
	{
		if (changingControl != null)
		{
			changingControl.performAction(keyCode);
			changingControl.setText(PlayerControls.getKeyText(keyCode));
			changingControl = null;
		}
		return currentState;
	}

}
