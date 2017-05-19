package game.Menus;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import game.Input.PlayerControls;

import game.Button;
import game.Fighter;
import game.GUIUtils;

public class ChampMenu extends Screen 
{
	private boolean isSelected;
	private PlayerControls p1Controls;
	private PlayerControls p2Controls;
	private static final Color P1COLOR = Color.red;
	private static final Color P2COLOR = Color.blue;
	private int p1Index;
	private int p2Index;
	
	public ChampMenu(BufferedImage background, PlayerControls p1, PlayerControls p2)
	{
		super(background);
		// TODO Auto-generated constructor stub
		p1Controls = p1;
		p2Controls = p2;
		p1Index = 0;
		p2Index = 0;
		buttonList.add(new Button(width * 1 / 8, height / 5, "NEUHAUS"));
		buttonList.add(new Button(width * 5 / 16, height / 5, "JUSTIN"));
		buttonList.add(new Button(width / 2, height / 5, "SPIESS"));
		buttonList.add(new Button(width * 11 / 16, height / 5, "KURTH"));
		buttonList.add(new Button(width * 1 / 8, height * 2 / 5, "WEBB"));
		buttonList.add(new Button(width * 5 / 16, height * 2 / 5, "WAY"));
		buttonList.add(new Button(width / 2, height * 2 / 5, "WHITNEY"));
		buttonList.add(new Button(width * 11 / 16, height * 2 / 5, "HESTER"));
		// exit button
		buttonList.add(new Button(width * 1 / 8, height * 4 / 5, "RETURN"));
		// next button
		buttonList.add(new Button(width * 11 / 16, height * 4 / 5, "NEXT"));
		isSelected = false;
	}

	@Override
	public void draw(Graphics g)
	{
		// GUIUtils.self().drawText(width/2, height/2, "CLICK THE MOUSE", 72, g,
		// false);
		super.draw(g);
		getNeuhaus().draw(g);
		getJustin().draw(g);
		getSpiess().draw(g);
		getKurth().draw(g);
		getWebb().draw(g);
		getWay().draw(g);
		getWhitney().draw(g);
		getHester().draw(g);
		getReturn().draw(g);
		getNext().draw(g);
		GUIUtils.self().drawSelector(g, buttonList.get(p1Index), P1COLOR);
		GUIUtils.self().drawSelector(g, buttonList.get(p2Index), P2COLOR);
	}

	public Button getNeuhaus()
	{
		return buttonList.get(0);
	}

	public Button getJustin()
	{
		return buttonList.get(1);
	}
	
	public Button getSpiess()
	{
		return buttonList.get(2);
	}
	
	public Button getKurth()
	{
		return buttonList.get(3);
	}
	
	public Button getWebb()
	{
		return buttonList.get(4);
	}

	public Button getWay()
	{
		return buttonList.get(5);
	}
	
	public Button getWhitney()
	{
		return buttonList.get(6);
	}
	
	public Button getHester()
	{
		return buttonList.get(7);
	}

	public Button getReturn()
	{
		return buttonList.get(8);
	}

	public Button getNext()
	{
		return buttonList.get(9);
	}
	
	public int goLeftOne()
	{
		p1Index --;
		if(p1Index < 0)
		{
			p1Index = 7;
		}
		return p1Index;
	}
	
	public int goRightOne()
	{
		p1Index ++;
		if(p1Index > 7)
		{
			p1Index = 0;
		}
		return p1Index;
	}
	
	public int goUpOne()
	{
		if(p1Index > 3)
		{
			p1Index -= 4;
		}
		else
		{
			p1Index += 4;
		}
		return p1Index;
	}
	
	public int goLeftTwo()
	{
		p2Index --;
		if(p2Index < 0)
		{
			p2Index = 7;
		}
		return p2Index;
	}
	
	public int goRightTwo()
	{
		p2Index ++;
		if(p2Index > 7)
		{
			p2Index = 0;
		}
		return p2Index;
	}
	
	public int goUpTwo()
	{
		if(p2Index > 3)
		{
			p2Index -= 4;
		}
		else
		{
			p2Index += 4;
		}
		return p2Index;
	}

	public Fighter setPlayerOne()
	{
		return null;
	}
	
	public Fighter setPlayerTwo()
	{
		return null;
	}
	@Override
	public void mousePressed(IScreen screen, int x, int y)
	{
		if (isSelected)
		{
			if (getNext().contains(x, y))
			{
				screen.setScreen(screen.getStageSelect());
			}
		}
		if(getReturn().contains(x, y))
		{
			screen.getPrevScreen();
		}
	}
	
	@Override
	public void keyPressed(IScreen screen, int keyCode)
	{
		if (keyCode == p1Controls.getLeft())
		{
			goLeftOne();
		}
		else if (keyCode == p1Controls.getRight())
		{
			goRightOne();
		}
		else if (keyCode == p1Controls.getJump() || keyCode == p1Controls.getCrouch())
		{
			goUpOne();
		}
		else if (keyCode == p1Controls.getPunch())
		{
			setPlayerOne();
		}
		if (keyCode == p2Controls.getLeft())
		{
			goLeftTwo();
		}
		else if (keyCode == p2Controls.getRight())
		{
			goRightTwo();
		}
		else if (keyCode == p2Controls.getJump() || keyCode == p2Controls.getCrouch())
		{
			goUpTwo();
		}
		else if (keyCode == p2Controls.getPunch())
		{
			setPlayerTwo();
		}
	}
}
