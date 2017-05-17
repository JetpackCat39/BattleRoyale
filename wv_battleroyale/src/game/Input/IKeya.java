package game.Input;

import game.BattleRoyale;
import game.Menus.Screen;

public interface IKeya
{
	Screen keyPressed(BattleRoyale g, int keyCode, Screen currentScreen, Screen previousScreen);
	
	Screen keyReleased(int keyCode, Screen currentScreen, Screen previousScreen);
}
