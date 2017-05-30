package game.Menus;

import java.awt.image.BufferedImage;

import game.Fighters.Fighter;

public interface IScreen
{
	public Screen getGame(); 
	
	public Screen getNewGame();
	
	public Screen getPause();
	
	public Screen getStageSelect();
	
	public Screen getChampSelect();
	
	public Screen getControls();
	
	//public Screen getCharacters();
	
	public Screen getCredits();
	
	public Screen getExit();
	
	
	public Screen getMenu();

	public Screen getScreen();
	
	public Screen getPrevScreen();

	public void setScreen(Screen screen, boolean doReset);
	
	public void setPlayer(Fighter p);
	
	public void setBackground(BufferedImage b);

}
