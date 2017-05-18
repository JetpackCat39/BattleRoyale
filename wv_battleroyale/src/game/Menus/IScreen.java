package game.Menus;

public interface IScreen
{
	public Screen getGame(); 
	
	public Screen getPause();
	
	public Screen getStageSelect();
	
	public Screen getChampSelect();
	
	public Screen getControls();
	
	public Screen getStop();
	
	public Screen getMenu();

	public Screen getScreen();
	
	public Screen getPrevScreen();

	public void setScreen(Screen screen);

}
