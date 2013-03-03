package frs.hotgammon.mariatests;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import frs.hotgammon.Location;
import frs.hotgammon.MonFactory;
import frs.hotgammon.MoveValidator;
import frs.hotgammon.RollDeterminer;
import frs.hotgammon.TurnDeterminer;
import frs.hotgammon.WinnerDeterminer;
import frs.hotgammon.common.GameImpl;
import frs.hotgammon.variants.factories.HandicapMonFactory;
import frs.hotgammon.variants.movevalidators.SimpleMoveValidator;
import frs.hotgammon.variants.rolldeterminers.PairSequenceDeterminer;
import frs.hotgammon.variants.turndeterminers.AlternatingTurnDeterminer;
import frs.hotgammon.variants.winnerdeterminers.BearOffWinnerDeterminer;
import frs.hotgammon.variants.winnerdeterminers.SixMoveWinnerDeterminer;

@RunWith(value = Parameterized.class)
public class HandicapMonTests {

	private GameImpl game;
	
	public HandicapMonTests(MonFactory factory) {
		game = new GameImpl(factory);
		game.newGame();		
	}
	
	 @Parameters
	 public static Collection<Object[]> data() {
	   Object[][] data = new Object[][] { { new HandicapMonFactory()},		                            
	   };
	   return Arrays.asList(data);
	 }
	 
	 
	@Test
	public void testAlphamonRulesWhenBlackTurn() {
		game.nextTurn();
		assertTrue(game.move(Location.R1, Location.R10));
	}
	
	 
	@Test
	public void testBetamonRulesWhenRedTurn() {
		game.nextTurn();
		game.nextTurn();
		assertFalse(game.move(Location.B12, Location.B1));
		assertFalse(game.move(Location.B12, Location.B8));
		assertTrue(game.move(Location.B1, Location.B4));
	}

}
