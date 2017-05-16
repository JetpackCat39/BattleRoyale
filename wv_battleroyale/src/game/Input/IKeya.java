package game.Input;

import game.BattleRoyale.STATE;

public interface IKeya
{
	STATE keyPressed(int keyCode, STATE currentState, STATE previousState);
	
	STATE keyReleased(int keyCode, STATE currentState, STATE previousState);
}
