package game.Menus;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import game.BattleRoyale;
import game.BattleRoyale.STATE;
import game.GUIUtils;
import game.IDrawable;

public class ControlChange extends Screen implements IDrawable, KeyListener
{
	private BattleRoyale game;
	IDrawable itemToOverlay;
	private int newKey;
	public int keyToRebind;

	public ControlChange(BattleRoyale br, IDrawable d, BufferedImage background)
	{
		super(background);
		game = br;
		itemToOverlay = d;
	}
	
	public void draw(Graphics g)
	{
		itemToOverlay.draw(g);
		super.draw(g);
		GUIUtils.self().drawText(width/4, height/3, "Press any key to rebind...", 50, g, false);
	}

	public void keyTyped(KeyEvent e)
	{
	}

	public void keyPressed(KeyEvent e)
	{		
		game.setState(STATE.CONTROLS);
	}

	public void keyReleased(KeyEvent e)
	{		
	}
	
	public void setKeyToRebind(int i)
	{
		keyToRebind = i;
	}

}
