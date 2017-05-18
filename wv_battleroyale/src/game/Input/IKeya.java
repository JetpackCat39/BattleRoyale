package game.Input;

import game.Menus.IScreen;

public interface IKeya
{
	void keyPressed(IScreen screen, int keyCode);
	
	void keyReleased(int keyCode);
}
