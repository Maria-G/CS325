package frs.hotgammon.variants.winnerdeterminers;

import frs.hotgammon.Color;
import frs.hotgammon.Game;
import frs.hotgammon.WinnerDeterminer;

public class SixMoveWinnerDeterminer implements WinnerDeterminer{

	@Override
	public Color winner(int turnCount) {
		return (turnCount == 6) ? Color.RED : Color.NONE;
	}

	@Override
	public void setGame(Game game) {
		// Do Nothing	
	}

}
