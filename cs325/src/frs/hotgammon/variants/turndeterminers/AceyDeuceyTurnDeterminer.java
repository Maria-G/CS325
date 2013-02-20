package frs.hotgammon.variants.turndeterminers;

import frs.hotgammon.Color;
import frs.hotgammon.Game;
import frs.hotgammon.TurnDeterminer;

public class AceyDeuceyTurnDeterminer implements TurnDeterminer{

	private Game game;
	
	@Override
	public Color nextTurn() {
		if (this.game.diceThrown()[0] == 1 && this.game.diceThrown()[1] == 2){
			return this.game.getPlayerInTurn();
		}
		else{
			return (this.game.getPlayerInTurn() == Color.BLACK) ? Color.RED : Color.BLACK;
		}
	}

	@Override
	public void setGame(Game game) {
		this.game = game;
		
	}

}
