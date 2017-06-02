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
	
	public Screen getBob();
	
	public Screen getCassen();
	
	public Screen getHalander();
	
	public Screen getJamal();
	
	public Screen getKurth();
	
	public Screen getNguyen();
	
	public Screen getTomboc();
	
	public Screen getWay();
	
	public Screen getCredits();
	
	public Screen getExit();
	
	public Screen getVictory(Fighter player);
	
	public Screen getMenu();

	public Screen getScreen();
	
	public Screen getPrevScreen();

	public void setScreen(Screen screen, boolean doReset);
		
	public void setBackground(BufferedImage b);
	
	

}
