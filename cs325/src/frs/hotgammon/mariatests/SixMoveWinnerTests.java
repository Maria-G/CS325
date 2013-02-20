package frs.hotgammon.mariatests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import frs.hotgammon.Color;
import frs.hotgammon.MoveValidator;
import frs.hotgammon.TurnDeterminer;
import frs.hotgammon.WinnerDeterminer;
import frs.hotgammon.common.GameImpl;
import frs.hotgammon.variants.movevalidators.CompleteMoveValidator;
import frs.hotgammon.variants.movevalidators.SimpleMoveValidator;
import frs.hotgammon.variants.turndeterminers.AceyDeuceyTurnDeterminer;
import frs.hotgammon.variants.turndeterminers.AlternatingTurnDeterminer;
import frs.hotgammon.variants.winnerdeterminers.BearOffWinnerDeterminer;
import frs.hotgammon.variants.winnerdeterminers.SixMoveWinnerDeterminer;

@RunWith(value = Parameterized.class)
public class SixMoveWinnerTests {
private GameImpl game;
	
	public SixMoveWinnerTests(MoveValidator mValidator, WinnerDeterminer wDeterminer, TurnDeterminer tDeterminer) {
		game = new GameImpl(mValidator, wDeterminer, tDeterminer);
		game.newGame();
		
	}
	
	@Parameters
	 public static Collection<Object[]> data() {
	   Object[][] data = new Object[][] { { new SimpleMoveValidator(), new SixMoveWinnerDeterminer(), new AlternatingTurnDeterminer() },
			                              { new CompleteMoveValidator(), new SixMoveWinnerDeterminer() , new AlternatingTurnDeterminer()},
			                              { new SimpleMoveValidator(), new SixMoveWinnerDeterminer() , new AceyDeuceyTurnDeterminer()},
	   };
	   return Arrays.asList(data);
	 }
	
	@Test
	public void shouldBeRedWinnerAfterSixTurns() {
		for (int i = 0; i < 6; i++) {
			game.nextTurn();
		}
		assertTrue(game.winner() == Color.RED);
	}
	
	@Test
	public void shouldEndGameAfterSixTurns() {
		game.nextTurn();
		game.nextTurn();
		game.nextTurn();
		game.nextTurn();
		game.nextTurn();
		game.nextTurn();
		assertEquals("Winner should be Red", Color.RED, game.winner());

	}
	
	@Test
	public void shouldHaveRedIsWinner() {
		game.nextTurn();
		game.nextTurn();
		game.nextTurn();
		game.nextTurn();
		game.nextTurn();
		game.nextTurn();
		assertEquals("Winner should be Red", Color.RED, game.winner());

	}
	
	@Test
	public void shouldEndGameAfter6Rolls() {

		game.nextTurn();

		game.nextTurn();

		assertEquals("should not be a winner ", Color.NONE, game.winner());

		game.nextTurn();

		game.nextTurn();

		game.nextTurn();

		game.nextTurn();

		assertEquals("should not be a winner ", Color.RED, game.winner());

	}
	
	@Test
	public void shouldNotEndGameAfter5Rolls() {

		game.nextTurn();

		assertEquals("should not be a winner ", Color.NONE, game.winner());

		game.nextTurn();

		game.nextTurn();

		game.nextTurn();

		assertEquals("should not be a winner ", Color.NONE, game.winner());

	}

	@Test
	public void redWinsAfter6Turns() {
		game.nextTurn();
		game.nextTurn();
		game.nextTurn();
		game.nextTurn();
		game.nextTurn();
		assertTrue(game.winner() == Color.NONE);
		game.nextTurn();
		assertTrue(game.winner() == Color.RED);
	}
}
