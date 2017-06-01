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
	Fighter player1, player2;
	boolean isP1;

	public VictoryScreen(IScreen s, BufferedImage background, Fighter p1, Fighter p2, boolean b)
	{
		super(background);
		itemToOverlay = s;
		player1 = p1;
		player2 = p2;
		isP1 = b;
		buttonList.add(new Button(BUTTON_CENTER / 2, height * 7/10, "NEW GAME"));
		buttonList.add(new Button(BUTTON_CENTER * 3/2, height * 7/10, "MAIN MENU"));
		GameUtils.self().playSound(getVictoryMusic());
	}
	
	public void draw(Graphics g)
	{
		itemToOverlay.getGame().draw(g);
		super.draw(g);
		String display = isP1 ? "P1 (" + player1.getName() + ") WINS" : "P2 (" + player2.getName() + ") WINS";
		Color color = isP1 ? Color.RED : Color.BLUE;
		GameUtils.self().drawText(color, height * 1/3, display, 72, g);
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
