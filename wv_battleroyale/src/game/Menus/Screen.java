package game.Menus;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import game.BattleRoyale;
import game.Button;
import game.GameUtils;
import game.IDrawable;
import game.Input.IKeya;

public class Screen implements IDrawable, IMenu, IKeya
{
	protected int height = BattleRoyale.HEIGHT;
	protected int width = BattleRoyale.WIDTH;
	protected static final int BUTTON_CENTER = 530;

	protected ArrayList<Button> buttonList;
	protected BufferedImage bg;

	public Screen(BufferedImage background)
	{
		bg = background;
		buttonList = new ArrayList<Button>();
		
	}

	@Override
	public void draw(Graphics g)
	{
		GameUtils.self().drawImg(bg, 0, 0, (width + 15), (height + 15), g);
		for(int i = 0; i < buttonList.size();i++){
			buttonList.get(i).draw(g);
		}
	}

	@Override
	public void mousePressed(IScreen screen, int x, int y) throws IOException
	{
	}

	@Override
	public void keyPressed(IScreen screen, int keyCode)
	{
	}

	@Override
	public void keyReleased(int keyCode)
	{
	}
	
	public Button getKey(int num){
		return buttonList.get(num);
	}
	
	public void reset()
	{	
	}

	public void drawBase(Graphics g)
	{		
	}
}
