package game.Menus;

import game.BattleRoyale.STATE;

public interface IMenu
{
	STATE mousePressed(int x, int y, STATE currentState, STATE previousState);
}
