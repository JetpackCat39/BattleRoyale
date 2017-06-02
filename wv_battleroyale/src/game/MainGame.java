package game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.sound.sampled.*;

import game.Fighters.Fighter;
import game.Menus.IScreen;
import game.Menus.Screen;

public class MainGame extends Screen
{
	private Fighter p1, p2;
	private int minVal, offset, maxVal, prevKeyCode;
	private boolean lockKeys, playEntrance;

	enum STATE
	{
		p1Sound, p2Sound, Sound3, Sound2, Sound1, startSound, begin;
	}

	private STATE state = STATE.p1Sound;

	Clip clip;

	private STATE getState()
	{
		return state;
	}

	public MainGame(BufferedImage background, Fighter player1, Fighter player2)
	{
		super(background);
		p1 = player1;
		p2 = player2;
		minVal = -(background.getWidth());
		maxVal = 0;
		setOffset((minVal + width) / 2);
		p1.setLeft(p1.getLeft() - offset);
		p2.setLeft(p2.getLeft() - offset);
		lockKeys = true;
		playEntrance = true;
		try
		{
			clip = AudioSystem.getClip();
		}
		catch (LineUnavailableException e)
		{
			e.printStackTrace();
		}
	}
	
	public void drawBase(Graphics g)
	{
		GameUtils.self().drawImg(bg, offset, 0, bg.getWidth(), bg.getHeight(), g);
	}

	@Override
	public void draw(Graphics g)
	{
		GameUtils.self().drawImg(bg, offset, 0, bg.getWidth(), bg.getHeight(), g);
		p1.draw(g, offset);
		p2.draw(g, offset);
		switch (state)
		{
		case Sound3:
			GameUtils.self().drawText(Color.MAGENTA, height / 3, "3...", 100, g);
			break;
		case Sound2:
			GameUtils.self().drawText(Color.MAGENTA, height / 3, "2...", 100, g);
			break;
		case Sound1:
			GameUtils.self().drawText(Color.MAGENTA, height / 3, "1...", 100, g);
			break;
		case startSound:
			GameUtils.self().drawText(Color.MAGENTA, height / 3, "FIGHT!", 100, g);
			break;
		default:
			break;
		}
		if (playEntrance)
		{
			try
			{
				entrance();
			}
			catch (LineUnavailableException e)
			{
				e.printStackTrace();
			}
		}
	}

	private void entrance() throws LineUnavailableException
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
				case p1Sound:
					play(p2.getEntranceQuote());
					state = STATE.p2Sound;
					break;
				case p2Sound:
					play("Sounds/begincountdown.wav");
					state = STATE.Sound3;
					break;
				case Sound3:
					play("Sounds/begincountdown.wav");
					state = STATE.Sound2;
					break;
				case Sound2:
					play("Sounds/begincountdown.wav");
					state = STATE.Sound1;
					break;
				case Sound1:
					play("Sounds/fight.wav");
					state = STATE.startSound;
					break;
				case startSound:
					p1.setIdle();
					p2.setIdle();
					lockKeys = false;
					state = STATE.begin;
					break;
				case begin:
					break;
				default:
					break;
				}
			}

		};
		clip.addLineListener(l);
		play(p1.getEntranceQuote());
		playEntrance = false;
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

	public boolean move()
	{
		int p1Max = p2.getLeft() - p1.getWidth();
		int p1Min = Math.max(0, p2.getRight() - width);
		int p2Min = p1.getRight();
		int p2Max = Math.min(bg.getWidth() - p2.getWidth(), p1.getLeft() + width - p2.getWidth());

		boolean p1Moved = p1.move(p1Min, p1Max);
		boolean p2Moved = p2.move(p2Min, p2Max);

		if (p1.getLeft() + offset < 0)
		{
			setOffset(-p1.getLeft());
		}
		else if (p2.getRight() + offset > width)
		{
			setOffset(-(p2.getRight() - width));
		}
		return (p1Moved || p2Moved);
	}

	private void setOffset(int newOffset)
	{
		if (newOffset < minVal)
		{
			newOffset = minVal;
		}
		else if (newOffset > maxVal)
		{
			newOffset = maxVal;
		}
		offset = newOffset;
	}

	@Override
	public void keyPressed(IScreen screen, int keyCode)
	{
		if (p1.getPause() == keyCode)
		{
			if (screen.getScreen() == screen.getGame())
			{
				screen.setScreen(screen.getPause(), false);
			}
			keyReleased(prevKeyCode);

		}
		else
		{
			if (!lockKeys)
			{
				prevKeyCode = keyCode;
				p1.keyPressed(null, keyCode);
				p2.keyPressed(null, keyCode);
			}
		}
		if (p1.getKO())
		{
			screen.setScreen(screen.getVictory(p2), true);
		}
		else if (p2.getKO())
		{
			screen.setScreen(screen.getVictory(p1), true);
		}
	}

	@Override
	public void keyReleased(int keyCode)
	{
		p1.keyReleased(keyCode);
		p2.keyReleased(keyCode);
	}
}
