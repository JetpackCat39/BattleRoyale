package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import game.Fighters.Fighter;
import game.Menus.IScreen;
import game.Menus.Screen;

public class VictoryScreen extends Screen
{
	IScreen itemToOverlay;
	Fighter p1, p2;
	boolean isP1, playSounds;
	Sound p1Sound, p2Sound, deathSound, victory;
	int victoryPlays, temp;

	enum STATE
	{
		playAnimation, playQuote, playVictory, end;
	}

	private STATE state = STATE.playAnimation;

	private STATE getState()
	{
		return state;
	}
	
	public VictoryScreen(IScreen s, BufferedImage background, Fighter player1, Fighter player2, boolean b)
	{
		super(background);
		victoryPlays = 0;
		itemToOverlay = s;
		p1 = player1;
		p2 = player2;
		isP1 = b;
		playSounds = true;
		buttonList.add(new Button(BUTTON_CENTER / 2, height * 7/10, "NEW GAME"));
		buttonList.add(new Button(BUTTON_CENTER * 3/2, height * 7/10, "MAIN MENU"));
		deathSound = new Sound("Sounds/Roblox Death Sound Effect.wav");
		victory = new Sound(getVictoryMusic());
		victory.play();
		if (isP1)
		{
			p1Sound = new Sound (p1.getResponseQuote());
		}
		else
		{
			p2Sound = new Sound (p2.getResponseQuote());
		}
	}
	
	public void draw(Graphics g)
	{
		if (getState() != STATE.end)
		{
			itemToOverlay.getGame().drawBase(g);
		}
		switch (state)
		{
		case playAnimation:
			if (isP1)
			{
				p1.drawStill(g);
				if(p2.drawKO(g, deathSound))
				{ 
					state = STATE.playQuote;
				}
			}
			else
			{
				p2.drawStill(g);
				if(p1.drawKO(g, deathSound))
				{
					state = STATE.playQuote;
				}
			}
			break;
		case playQuote:
			if (isP1)
			{
				p2.drawLyingDown(g);
				if(p1.drawVictory(g, p1Sound))
				{
					victoryPlays++;
				}
			}
			else
			{
				p1.drawLyingDown(g);
				if(p2.drawVictory(g, p2Sound))
				{
					victoryPlays++;
				}
			}
			if(victoryPlays > 2) 
			{
				state = STATE.end;
			}
			break;
		default:
			if (isP1)
			{
				p2.drawLyingDown(g);
			}
			else
			{
				p1.drawLyingDown(g);
			}
			String display = isP1 ? "P1 (" + p1.getName() + ") WINS" : "P2 (" + p2.getName() + ") WINS";
			Color color = isP1 ? Color.RED : Color.BLUE;
			GameUtils.self().drawText(color, height * 1/3, display, 72, g);
			super.draw(g);
			break;
		}
	}
	
	public String getVictoryMusic()
	{
		Random randomizer = new Random();
		List<String> temp = new ArrayList<String>();
		temp.add("Sounds/victory1.wav");
		temp.add("Sounds/victory2.wav");
		temp.add("Sounds/victory3.wav");
		return temp.get(randomizer.nextInt(temp.size()));
	}
	
	public Button getNewGame()
	{
		return buttonList.get(0);
	}
	
	public Button getTitle()
	{
		return buttonList.get(1);
	}
	
	@Override
	public void mousePressed(IScreen screen, int x, int y)
	{
		if (getNewGame().contains(x, y))
		{
			screen.setScreen(screen.getChampSelect(), true);
		}
		if (getTitle().contains(x, y))
		{
			screen.setScreen(screen.getMenu(), false);
		}
	}

}
