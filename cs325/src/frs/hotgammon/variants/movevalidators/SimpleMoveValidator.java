package frs.hotgammon.variants.movevalidators;

import frs.hotgammon.*;

public class SimpleMoveValidator implements MoveValidator{

	private Game game;
	
	public SimpleMoveValidator(){
	}
	
	@Override
	public boolean isValid(Location from, Location to) {
		int fromCount = game.getCount(from);
		int toCount = game.getCount(to);
		Color fromColor = game.getColor(from);
		Color toColor = game.getColor(to);
		
		Color playerInTurn = game.getPlayerInTurn();
			
		if ( fromColor != playerInTurn ){ //Can't move opponent's piece
			return false;
		}
		if ( fromCount == 0 ){ //Can't move piece if no pieces
			return false;
		}
		if ( toCount > 0 ){ //toSquare is not empty
			if (toColor != playerInTurn && toColor != Color.NONE){ //If to square is occupied by the opponent
				return false;
			}
		}
		return true;
		
	}

	@Override
	public void setGame(Game game) {
		this.game = game;
		
	}

	
}
