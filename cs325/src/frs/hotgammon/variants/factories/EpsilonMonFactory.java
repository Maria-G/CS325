package frs.hotgammon.variants.factories;

import frs.hotgammon.MonFactory;
import frs.hotgammon.MoveValidator;
import frs.hotgammon.RollDeterminer;
import frs.hotgammon.TurnDeterminer;
import frs.hotgammon.WinnerDeterminer;
import frs.hotgammon.variants.movevalidators.SimpleMoveValidator;
import frs.hotgammon.variants.rolldeterminers.RandomRollDeterminer;
import frs.hotgammon.variants.turndeterminers.AlternatingTurnDeterminer;
import frs.hotgammon.variants.winnerdeterminers.SixMoveWinnerDeterminer;

public class EpsilonMonFactory implements MonFactory {
	   
	@Override
	public MoveValidator getMoveValidator() {
		return new SimpleMoveValidator();
	}

	@Override
	public TurnDeterminer getTurnDeterminer() {
		return new AlternatingTurnDeterminer();
	}

	@Override
	public WinnerDeterminer getWinnerDeterminer() {
		return new SixMoveWinnerDeterminer();
	}

	@Override
	public RollDeterminer getRollDeterminer() {
		return new RandomRollDeterminer();
	}

}
