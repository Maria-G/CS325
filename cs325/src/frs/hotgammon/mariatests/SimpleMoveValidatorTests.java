package frs.hotgammon.mariatests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import frs.hotgammon.Location;
import frs.hotgammon.MoveValidator;
import frs.hotgammon.TurnDeterminer;
import frs.hotgammon.WinnerDeterminer;
import frs.hotgammon.common.GameImpl;
import frs.hotgammon.variants.movevalidators.SimpleMoveValidator;
import frs.hotgammon.variants.turndeterminers.AceyDeuceyTurnDeterminer;
import frs.hotgammon.variants.turndeterminers.AlternatingTurnDeterminer;
import frs.hotgammon.variants.winnerdeterminers.BearOffWinnerDeterminer;
import frs.hotgammon.variants.winnerdeterminers.SixMoveWinnerDeterminer;

@RunWith(value = Parameterized.class)
public class SimpleMoveValidatorTests {

	private GameImpl game;
	
	public SimpleMoveValidatorTests(MoveValidator mValidator, WinnerDeterminer wDeterminer, TurnDeterminer tDeterminer) {
		game = new GameImpl(mValidator, wDeterminer, tDeterminer);
		game.newGame();		
	}
	
	 @Parameters
	 public static Collection<Object[]> data() {
	   Object[][] data = new Object[][] { { new SimpleMoveValidator(), new SixMoveWinnerDeterminer(), new AlternatingTurnDeterminer() },
			                              { new SimpleMoveValidator(), new BearOffWinnerDeterminer() , new AlternatingTurnDeterminer()},		                            
	   };
	   return Arrays.asList(data);
	 }
	 

		@Test
		public void shouldBeNoMovesLeftAfterMovingTwoBlackCheckersFromR1toR2() {
			game.nextTurn();
			game.move(Location.R1, Location.R2);
			game.move(Location.R1, Location.R2);

			assertEquals(0, game.getNumberOfMovesLeft());
		}

		@Test
		public void shouldBeAbleToPlaceTheSameCheckersInOneContainer() {
			game.nextTurn();
			game.nextTurn();
			assertTrue(game.move(Location.R8, Location.R6));
		}
		
}
