package game.Menus;

import game.BattleRoyale;

public interface IMenu
{
	Screen mousePressed(BattleRoyale g, int x, int y, Screen currentScreen, Screen previousScreen);
}
