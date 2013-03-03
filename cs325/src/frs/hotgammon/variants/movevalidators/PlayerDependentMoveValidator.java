package frs.hotgammon.variants.movevalidators;

import frs.hotgammon.Color;
import frs.hotgammon.Game;
import frs.hotgammon.Location;
import frs.hotgammon.MoveValidator;

public class PlayerDependentMoveValidator implements MoveValidator{
	private MoveValidator blackMoveValidator = new SimpleMoveValidator();
	private MoveValidator redMoveValidator = new CompleteMoveValidator();
	
	private Game game;
	
	@Override
	public boolean isValid(Location from, Location to) {
		Color player = this.game.getPlayerInTurn();
		
		if(player == Color.BLACK){
			return blackMoveValidator.isValid(from, to);
		}
		if(player == Color.RED){
			return redMoveValidator.isValid(from, to);			
		}
		
		return false;
	}
	
	@Override
	public void setGame(Game game) {
		this.game = game;
		blackMoveValidator.setGame(game);
		redMoveValidator.setGame(game);
	}

}
