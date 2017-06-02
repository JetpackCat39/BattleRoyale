package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import game.Fighters.Fighter;
import game.Menus.IScreen;
import game.Menus.Screen;

public class VictoryScreen extends Screen
{
	IScreen itemToOverlay;
	Fighter player1, player2;
	boolean isP1, playSounds;

	enum STATE
	{
		playAnimation, playQuote, playVictory, end;
	}

	private STATE state = STATE.playAnimation;

	Clip clip;

	private STATE getState()
	{
		return state;
	}
	
	public VictoryScreen(IScreen s, BufferedImage background, Fighter p1, Fighter p2, boolean b)
	{
		super(background);
		itemToOverlay = s;
		player1 = p1;
		player2 = p2;
		isP1 = b;
		playSounds = true;
		buttonList.add(new Button(BUTTON_CENTER / 2, height * 7/10, "NEW GAME"));
		buttonList.add(new Button(BUTTON_CENTER * 3/2, height * 7/10, "MAIN MENU"));
		try
		{
			clip = AudioSystem.getClip();
		}
		catch (LineUnavailableException e)
		{
			e.printStackTrace();
		}
	}
	
	public void draw(Graphics g)
	{
		if (getState() != STATE.end)
		{
			itemToOverlay.getGame().draw(g);
		}
		switch (state)
		{
		case playAnimation:
			if (isP1)
			{
				player2.playKOAnimation(g);
			}
			else
			{
				player1.playKOAnimation(g);
			}
			break;
		case playQuote:
			if (isP1)
			{
				player1.playVictoryAnimation(g);
			}
			else
			{
				player2.playVictoryAnimation(g);
			}
			break;
		default:
			String display = isP1 ? "P1 (" + player1.getName() + ") WINS" : "P2 (" + player2.getName() + ") WINS";
			Color color = isP1 ? Color.RED : Color.BLUE;
			GameUtils.self().drawText(color, height * 1/3, display, 72, g);
			super.draw(g);
			break;
		}
		if (playSounds)
		{
			try
			{
				exit();
			}
			catch (LineUnavailableException e)
			{
				e.printStackTrace();
			}
		}
	}
	
	private void exit() throws LineUnavailableException
	{
		LineListener l = new LineListener()
		{
			@Override
			public void update(LineEvent event)
			{
				if (event.getType() != LineEvent.Type.STOP)
				{
					return;
				}
				switch (getState())
				{
				case playAnimation:
					play(isP1 ? player1.getResponseQuote() : player2.getResponseQuote());
					state = STATE.playQuote;
					break;
				case playQuote:
					play(getVictoryMusic());
					state = STATE.playVictory;
					break;
				case playVictory:
					state = STATE.end;
					break;
				default:
					break;
				}
			}

		};
		clip.addLineListener(l);
		play("Sounds/Roblox Death Sound Effect.wav");
		playSounds = false;
	}
	
	private void play(String path)
	{
		try
		{
			if (clip.isOpen())
			{
				clip.close();
			}
			AudioInputStream ais = AudioSystem.getAudioInputStream(getClass().getResource(path));
			clip.open(ais);
			clip.start();
		}
		catch (UnsupportedAudioFileException | IOException | LineUnavailableException e)
		{
			e.printStackTrace();
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
