package game.Menus;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import game.Input.PlayerControls;

import game.Button;
import game.GUIUtils;
import game.Fighters.*;

public class ChampMenu extends Screen
{
	private boolean isSelected1, isSelected2;
	private PlayerControls p1Controls, p2Controls;
	private static final Color P1COLOR = Color.red;
	private static final Color P2COLOR = Color.blue;
	private static final int PLAYERX = 300;
	private static final int PLAYERY = 190;
	private static final Color BOTH = Color.MAGENTA;
	private static final Color NEXT = Color.GREEN;
	private int p1Index, p2Index;
	private Fighter p1, p2;

	public ChampMenu(BufferedImage background, PlayerControls p1, PlayerControls p2)
	{
		super(background);
		p1Controls = p1;
		p2Controls = p2;
		reset();
		buttonList.add(new Button(width * 1 / 8, height / 5, "JAMAL"));
		buttonList.add(new Button(width * 5 / 16, height / 5, "CASSEN"));
		buttonList.add(new Button(width / 2, height / 5, "TOMBOC"));
		buttonList.add(new Button(width * 11 / 16, height / 5, "KURTH"));
		buttonList.add(new Button(width * 1 / 8, height * 2 / 5, "BOB"));
		buttonList.add(new Button(width * 5 / 16, height * 2 / 5, "NGUYEN"));
		buttonList.add(new Button(width / 2, height * 2 / 5, "HALANDER"));
		buttonList.add(new Button(width * 11 / 16, height * 2 / 5, "WAY"));
		// exit button
		buttonList.add(new Button(width * 1 / 8, height * 4 / 5, "BACK"));
		// next button
		buttonList.add(new Button(width * 11 / 16, height * 4 / 5, "NEXT"));
	}

	@Override
	public void reset()
	{
		p1Index = 0;
		p2Index = 0;
		isSelected1 = false;
		isSelected2 = false;
	}

	@Override
	public void draw(Graphics g)
	{
		super.draw(g);

		GUIUtils.self().drawText(width / 10, height * 3 / 5,
				"Movement to select, Punch to lock in, kick to cancel, block to view character description", 25, g, false);
		if (p1Index == p2Index)
		{
			if (!(isSelected1 && isSelected2))
			{
				GUIUtils.self().drawSelector(g, buttonList.get(p1Index), BOTH);
			}
			else
			{
				getSelectedP1().fill(g, BOTH);
			}
		}
		else
		{
			if (!isSelected1)
			{
				GUIUtils.self().drawSelector(g, buttonList.get(p1Index), P1COLOR);
			}
			else
			{
				getSelectedP1().fill(g, P1COLOR);
			}
			if (!isSelected2)
			{
				GUIUtils.self().drawSelector(g, buttonList.get(p2Index), P2COLOR);
			}
			else
			{
				getSelectedP2().fill(g, P2COLOR);
			}
		}
		if (isSelected1 && isSelected2)
		{
			getNext().fill(g, NEXT);
		}
	}

	public Button getJamal()
	{
		return buttonList.get(0);
	}

	public Button getCassen()
	{
		return buttonList.get(1);
	}

	public Button getTomboc()
	{
		return buttonList.get(2);
	}

	public Button getKurth()
	{
		return buttonList.get(3);
	}

	public Button getBob()
	{
		return buttonList.get(4);
	}

	public Button getNguyen()
	{
		return buttonList.get(5);
	}

	public Button getHalander()
	{
		return buttonList.get(6);
	}

	public Button getWay()
	{
		return buttonList.get(7);
	}

	public Button getBack()
	{
		return buttonList.get(8);
	}

	public Button getNext()
	{
		return buttonList.get(9);
	}

	public Button getSelectedP1()
	{
		return buttonList.get(p1Index);
	}

	public Button getSelectedP2()
	{
		return buttonList.get(p2Index);
	}

	public int p1Left()
	{
		p1Index--;
		if (p1Index < 0)
		{
			p1Index = 7;
		}
		return p1Index;
	}

	public int p1Right()
	{
		p1Index++;
		if (p1Index > 7)
		{
			p1Index = 0;
		}
		return p1Index;
	}

	public int p1Up()
	{
		if (p1Index > 3)
		{
			p1Index -= 4;
		}
		else
		{
			p1Index += 4;
		}
		return p1Index;
	}

	public int p2Left()
	{
		p2Index--;
		if (p2Index < 0)
		{
			p2Index = 7;
		}
		return p2Index;
	}

	public int p2Right()
	{
		p2Index++;
		if (p2Index > 7)
		{
			p2Index = 0;
		}
		return p2Index;
	}

	public int p2Up()
	{
		if (p2Index > 3)
		{
			p2Index -= 4;
		}
		else
		{
			p2Index += 4;
		}
		return p2Index;
	}

	public Fighter getP1()
	{
		return p1;
	}

	public void setP1() throws IOException
	{
		isSelected1 = true;
		switch (getSelectedP1().getText())
		{
		case "JAMAL":
			p1 = new Jamal(PLAYERX, PLAYERY, GUIUtils.self().loadImage("Images/Jamal-Ingame.png"), GUIUtils.self()
					.loadImage("Images/Jamal-Victory.png"), true, p1Controls);
			break;
		case "CASSEN":
			break;
		case "TOMBOC":
			p1 = new Tomboc(PLAYERX, PLAYERY, GUIUtils.self().loadImage("Images/Tomboc-Ingame.png"), null, true,
					p1Controls);
			break;
		case "KURTH":
			p1 = new Kurth(PLAYERX, PLAYERY, GUIUtils.self().loadImage("Images/Kurth-Ingame.png"), null, true, p1Controls);
			break;
		case "BOB":
			break;
		case "NGUYEN":
			break;
		case "HALANDER":
			break;
		case "WAY":
			break;
		default:
			break;
		}
	}

	public Fighter getP2()
	{
		return p2;
	}

	public void setP2() throws IOException
	{
		isSelected2 = true;
		switch (getSelectedP2().getText())
		{
		case "JAMAL":
			p2 = new Jamal(width - PLAYERX, PLAYERY, GUIUtils.self().loadImage("Images/Jamal-Ingame.png"), GUIUtils
					.self().loadImage("Images/Jamal-Victory.png"), false, p2Controls);
			break;
		case "CASSEN":
			break;
		case "TOMBOC":
			p2 = new Tomboc(width - PLAYERX, PLAYERY, GUIUtils.self().loadImage("Images/Tomboc-Ingame.png"), null,
					false, p2Controls);
			break;
		case "KURTH":
			p2 = new Kurth(width - PLAYERX, PLAYERY, GUIUtils.self().loadImage("Images/Kurth-Ingame.png"), null,
					false, p2Controls);
			break;
		case "BOB":
			break;
		case "NGUYEN":
			break;
		case "HALANDER":
			break;
		case "WAY":
			break;
		default:
			break;
		}
	}

	@Override
	public void mousePressed(IScreen screen, int x, int y)
	{
		if (isSelected1 && isSelected2)
		{
			if (getNext().contains(x, y))
			{
				screen.setScreen(screen.getStageSelect(), true);
			}
		}
		if (getBack().contains(x, y))
		{
			screen.getPrevScreen();
		}
	}

	@Override
	public void keyPressed(IScreen screen, int keyCode)
	{

		if (!isSelected1)
		{
			if (keyCode == p1Controls.getLeft())
			{
				p1Left();
			}
			else if (keyCode == p1Controls.getRight())
			{
				p1Right();
			}
			else if (keyCode == p1Controls.getJump() || keyCode == p1Controls.getCrouch())
			{
				p1Up();
			}
			else if (keyCode == p1Controls.getPunch())
			{
				try
				{
					setP1();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
		else if (keyCode == p1Controls.getKick())
		{
			isSelected1 = false;
		}
		if (!isSelected2)
		{
			if (keyCode == p2Controls.getLeft())
			{
				p2Left();
			}
			else if (keyCode == p2Controls.getRight())
			{
				p2Right();
			}
			else if (keyCode == p2Controls.getJump() || keyCode == p2Controls.getCrouch())
			{
				p2Up();
			}
			else if (keyCode == p2Controls.getPunch())
			{
				try
				{
					setP2();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}

		else if (keyCode == p2Controls.getKick())
		{
			isSelected2 = false;
		}

		if (keyCode == p1Controls.getBlock())
		{
			if (p1Index == 0)
			{
				screen.setScreen(screen.getJamal(), false);
			}
			else if (p1Index == 1)
			{
				screen.setScreen(screen.getCassen(), false);
			}
			else if (p1Index == 2)
			{
				screen.setScreen(screen.getTomboc(), false);
			}
			else if (p1Index == 3)
			{
				screen.setScreen(screen.getKurth(), false);
			}
			else if (p1Index == 4)
			{
				screen.setScreen(screen.getBob(), false);
			}
			else if (p1Index == 5)
			{
				screen.setScreen(screen.getNguyen(), false);
			}
			else if (p1Index == 6)
			{
				screen.setScreen(screen.getHalander(), false);
			}
			else if (p1Index == 7)
			{
				screen.setScreen(screen.getWay(), false);
			}
		}
		else if (keyCode == p2Controls.getBlock())
		{
			if (p2Index == 0)
			{
				screen.setScreen(screen.getJamal(), false);
			}
			else if (p2Index == 1)
			{
				screen.setScreen(screen.getCassen(), false);
			}
			else if (p2Index == 2)
			{
				screen.setScreen(screen.getTomboc(), false);
			}
			else if (p2Index == 3)
			{
				screen.setScreen(screen.getKurth(), false);
			}
			else if (p2Index == 4)
			{
				screen.setScreen(screen.getBob(), false);
			}
			else if (p2Index == 5)
			{
				screen.setScreen(screen.getNguyen(), false);
			}
			else if (p2Index == 6)
			{
				screen.setScreen(screen.getHalander(), false);
			}
			else if (p2Index == 7)
			{
				screen.setScreen(screen.getWay(), false);
			}
		}

	}
}
