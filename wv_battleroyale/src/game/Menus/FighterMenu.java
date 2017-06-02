package game.Menus;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import game.Input.PlayerControls;

import game.Button;
import game.GameUtils;
import game.Konami;
import game.Fighters.*;

// Menu to select which fighter each player wants to use
public class FighterMenu extends Screen
{
	private static final int SECOND_ROW_START = 4;
	private static final int FIRST_ROW_END = 3;
	private static final int SECOND_ROW_END = 7;
	private static final int NEUHAUS = 10;
	private static final int FIRST_ROW_START = 0;
	private boolean isSelected1, isSelected2, displayNeuhaus;
	private PlayerControls p1Controls, p2Controls;
	private static final Color P1COLOR = Color.red;
	private static final Color P2COLOR = Color.blue;
	private static final int PLAYERX = 300;
	private static final int PLAYERY = 190;
	private static final Color BOTH = Color.MAGENTA;
	private static final Color NEXT = Color.GREEN;
	private int p1Index, p2Index;
	private Fighter p1, p2;
	private Konami konami;
	private Button neuhaus;

	public FighterMenu(BufferedImage background, PlayerControls p1, PlayerControls p2)
	{
		super(background);
		p1Controls = p1;
		p2Controls = p2;
		p1Index = 0;
		p2Index = 0;
		isSelected1 = false;
		isSelected2 = false;
		displayNeuhaus = false;		
		buttonList.add(new Button(width * 1 / 8, height / 5, "JAMAL"));
		buttonList.add(new Button(width * 5 / 16, height / 5, "CASSEN"));
		buttonList.add(new Button(width / 2, height / 5, "TOMBOC"));
		buttonList.add(new Button(width * 11 / 16, height / 5, "KURTH"));
		buttonList.add(new Button(width * 1 / 8, height * 2 / 5, "BOB"));
		buttonList.add(new Button(width * 5 / 16, height * 2 / 5, "NGUYEN"));
		buttonList.add(new Button(width / 2, height * 2 / 5, "HALANDER"));
		buttonList.add(new Button(width * 11 / 16, height * 2 / 5, "WAY"));
		// exit button
		buttonList.add(new Button(width * 1 / 8, height * 4 / 5, "TITLE"));
		// next button
		buttonList.add(new Button(width * 11 / 16, height * 4 / 5, "NEXT"));
		neuhaus = new Button(BUTTON_CENTER, height * 4 / 5, "NEUHAUS");
		konami = new Konami();
	}

	@Override
	public void draw(Graphics g)
	{
		if (displayNeuhaus)
		{
			buttonList.add(10, neuhaus);
		}
		super.draw(g);

		GameUtils.self().drawText(width / 10, height * 3 / 5,
				"Movement to select, Punch to lock in, kick to cancel, block to view character description", 25, g);
		if (p1Index == p2Index)
		{
			drawBothSelectors(g);
		}
		else
		{
			drawOneSelector(g);
		}
		if (isSelected1 && isSelected2)
		{
			getNext().fill(g, NEXT);
		}
	}

	private void drawOneSelector(Graphics g) 
	{
		if (!isSelected1)
		{
			GameUtils.self().drawSelector(g, buttonList.get(p1Index), P1COLOR);
		}
		else
		{
			getSelectedP1().fill(g, P1COLOR);
		}
		if (!isSelected2)
		{
			GameUtils.self().drawSelector(g, buttonList.get(p2Index), P2COLOR);
		}
		else
		{
			getSelectedP2().fill(g, P2COLOR);
		}
	}

	private void drawBothSelectors(Graphics g) 
	{
		if (!(isSelected1 && isSelected2))
		{
			GameUtils.self().drawSelector(g, buttonList.get(p1Index), BOTH);
		}
		else
		{
			getSelectedP1().fill(g, BOTH);
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

	public Button getNeuhaus()
	{
		if (displayNeuhaus)
		{
			return buttonList.get(NEUHAUS);
		}
		return null;
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
		if (displayNeuhaus)
		{
			if (p1Index < FIRST_ROW_START)
			{
				p1Index = NEUHAUS;
			}
			else if (p1Index < NEUHAUS && p1Index > SECOND_ROW_END)
			{
				p1Index = SECOND_ROW_END;
			}
		}
		else
		{
			if (p1Index < FIRST_ROW_START)
			{
				p1Index = SECOND_ROW_END;
			}
		}
		return p1Index;
	}

	public int p1Right()
	{
		p1Index++;
		if (displayNeuhaus)
		{
			if (p1Index > SECOND_ROW_END && p1Index < NEUHAUS)
			{
				p1Index = NEUHAUS;
			}
			else if (p1Index > NEUHAUS)
			{
				p1Index = FIRST_ROW_START;
			}
		}
		else
		{
			if (p1Index > SECOND_ROW_END)
			{
				p1Index = FIRST_ROW_START;
			}
		}
		return p1Index;
	}

	public int p1Down()
	{
		if (displayNeuhaus)
		{
			if (p1Index > FIRST_ROW_END && p1Index != NEUHAUS)
			{
				p1Index = NEUHAUS;
			}
			else if (p1Index == NEUHAUS)
			{
				p1Index = 1;
			}
			else
			{
				p1Index += SECOND_ROW_START;
			}
		}
		else
		{
			if (p1Index > FIRST_ROW_END)
			{
				p1Index -= SECOND_ROW_START;
			}
			else
			{
				p1Index += SECOND_ROW_START;
			}
		}
		return p1Index;
	}
	
	public int p1Up()
	{
		if (displayNeuhaus)
		{
			if (p1Index < SECOND_ROW_START && p1Index != NEUHAUS)
			{
				p1Index = NEUHAUS;
			}
			else if (p1Index == NEUHAUS)
			{
				p1Index = 5;
			}
			else
			{
				p1Index -= SECOND_ROW_START;
			}
		}
		else
		{
			if (p1Index > FIRST_ROW_END)
			{
				p1Index -= SECOND_ROW_START;
			}
			else
			{
				p1Index += SECOND_ROW_START;
			}
		}
		return p1Index;
	}

	public int p2Left()
	{
		p2Index--;
		if (displayNeuhaus)
		{
			if (p2Index < FIRST_ROW_START)
			{
				p2Index = NEUHAUS;
			}
			else if (p2Index < NEUHAUS && p2Index > SECOND_ROW_END)
			{
				p2Index = SECOND_ROW_END;
			}
		}
		else
		{
			if (p2Index < FIRST_ROW_START)
			{
				p2Index = SECOND_ROW_END;
			}
		}
		return p2Index;
	}

	public int p2Right()
	{
		p2Index++;
		if (displayNeuhaus)
		{
			if (p2Index > SECOND_ROW_END && p2Index < NEUHAUS)
			{
				p2Index = NEUHAUS;
			}
			else if (p2Index > NEUHAUS)
			{
				p2Index = FIRST_ROW_START;
			}
		}
		else
		{
			if (p2Index > SECOND_ROW_END)
			{
				p2Index = FIRST_ROW_START;
			}
		}
		return p2Index;
	}

	public int p2Down()
	{
		if (displayNeuhaus)
		{
			if (p2Index > FIRST_ROW_END && p2Index != NEUHAUS)
			{
				p2Index = NEUHAUS;
			}
			else if (p2Index == NEUHAUS)
			{
				p2Index = 1;
			}
			else
			{
				p2Index += SECOND_ROW_START;
			}
		}
		else
		{
			if (p2Index > FIRST_ROW_END)
			{
				p2Index -= SECOND_ROW_START;
			}
			else
			{
				p2Index += SECOND_ROW_START;
			}
		}
		return p2Index;
	}
	
	public int p2Up()
	{
		if (displayNeuhaus)
		{
			if (p2Index < SECOND_ROW_START && p2Index != NEUHAUS)
			{
				p2Index = NEUHAUS;
			}
			else if (p2Index == NEUHAUS)
			{
				p2Index = 5;
			}
			else
			{
				p2Index -= SECOND_ROW_START;
			}
		}
		else
		{
			if (p2Index > FIRST_ROW_END)
			{
				p2Index -= SECOND_ROW_START;
			}
			else
			{
				p2Index += SECOND_ROW_START;
			}
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
			p1 = new Jamal(PLAYERX, PLAYERY, GameUtils.self().loadImage("Images/Jamal-Ingame.png"),
					GameUtils.self().loadImage("Images/Jamal-Victory-KO.png"), true, p1Controls);
			break;
		case "CASSEN":
			p1 = new Cassen(PLAYERX, PLAYERY, GameUtils.self().loadImage("Images/Cassen-Ingame1.png"), 
					GameUtils.self().loadImage("Images/Cassen-Victory-KO.png"), true, p1Controls);
			break;
		case "TOMBOC":
			p1 = new Tomboc(PLAYERX, PLAYERY, GameUtils.self().loadImage("Images/tomboc-ingame.png"), 
					GameUtils.self().loadImage("Images/Tomboc-Victory-KO.png"), true, p1Controls);
			break;
		case "KURTH":
			p1 = new Kurth(PLAYERX, PLAYERY, GameUtils.self().loadImage("Images/Kurth-Ingame.png"), 
					GameUtils.self().loadImage("Images/Kurth-Victory-KO.png"), true,p1Controls);
			break;
		case "BOB":
			p1 = new Bob(PLAYERX, PLAYERY, GameUtils.self().loadImage("Images/Bob-Ingame.png"), 
					GameUtils.self().loadImage("Images/Bob-Victory-KO.png"), true, p1Controls);
			break;
		case "NGUYEN":
			p1 = new Nguyen(PLAYERX, PLAYERY, GameUtils.self().loadImage("Images/Nguyen-Ingame.png"), 
					GameUtils.self().loadImage("Images/Nguyen-Victory-KO.png"), true, p1Controls);
			break;
		case "HALANDER":
			p1 = new Halander(PLAYERX, PLAYERY, GameUtils.self().loadImage("Images/halander-ingame.png"), 
					GameUtils.self().loadImage("Images/Halander-Victory-KO.png"), true, p1Controls);
			break;
		case "WAY":
			p1 = new Way(PLAYERX, PLAYERY, GameUtils.self().loadImage("Images/Way-Ingame.png"), 
					GameUtils.self().loadImage("Images/Way-Victory-KO.png"), true, p1Controls);
			break;
		case "NEUHAUS":
			p1 = new Neuhaus(PLAYERX, PLAYERY, GameUtils.self().loadImage("Images/tammy.png"), 
					GameUtils.self().loadImage("Images/tammy2.png"), true, p1Controls);
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
			p2 = new Jamal(width - PLAYERX, PLAYERY, GameUtils.self().loadImage("Images/Jamal-Ingame.png"),
					GameUtils.self().loadImage("Images/Jamal-Victory-KO.png"), false, p2Controls);
			break;
		case "CASSEN":
			p2 = new Cassen(width - PLAYERX, PLAYERY, GameUtils.self().loadImage("Images/Cassen-Ingame1.png"), 
					GameUtils.self().loadImage("Images/Cassen-Victory-KO.png"), false, p2Controls);
			break;
		case "TOMBOC":
			p2 = new Tomboc(width - PLAYERX, PLAYERY, GameUtils.self().loadImage("Images/tomboc-ingame.png"), 
					GameUtils.self().loadImage("Images/Tomboc-Victory-KO.png"), false, p2Controls);
			break;
		case "KURTH":
			p2 = new Kurth(width - PLAYERX, PLAYERY, GameUtils.self().loadImage("Images/Kurth-Ingame1.png"), 
					GameUtils.self().loadImage("Images/Kurth-Victory-KO.png"), false, p2Controls);
			break;
		case "BOB":
			p2 = new Bob(width - PLAYERX, PLAYERY, GameUtils.self().loadImage("Images/Bob-Ingame.png"), 
					GameUtils.self().loadImage("Images/Bob-Victory-KO.png"), false,
					p2Controls);
			break;
		case "NGUYEN":
			p2 = new Nguyen(width - PLAYERX, PLAYERY, GameUtils.self().loadImage("Images/Nguyen-Ingame.png"), 
					GameUtils.self().loadImage("Images/Nguyen-Victory-KO.png"), false, p2Controls);
			break;
		case "HALANDER":
			p2 = new Halander(width - PLAYERX, PLAYERY, GameUtils.self().loadImage("Images/halander-ingame.png"), 
					GameUtils.self().loadImage("Images/Halander-Victory-KO.png"), false, p2Controls);
			break;
		case "WAY":
			p2 = new Way(width - PLAYERX, PLAYERY, GameUtils.self().loadImage("Images/Way-Ingame.png"), 
					GameUtils.self().loadImage("Images/Way-Victory-KO.png"), false, p2Controls);
			break;
		case "NEUHAUS":
			p2 = new Neuhaus(width - PLAYERX, PLAYERY, GameUtils.self().loadImage("Images/tammy.png"), 
					GameUtils.self().loadImage("Images/tammy2.png"), false,p2Controls);
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
			screen.setScreen(screen.getMenu(), true);
		}
	}

	@Override
	public void keyPressed(IScreen screen, int keyCode)
	{
		if (konami.checkKonami(keyCode))
		{
			displayNeuhaus = true;
		}
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
			else if (keyCode == p1Controls.getJump())
			{
				p1Up();
			}
			else if(keyCode == p1Controls.getCrouch())
			{
				p1Down();
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
			else if (keyCode == p2Controls.getJump())
			{
				p2Up();
			}
			else if(keyCode == p2Controls.getCrouch())
			{
				p2Down();
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
