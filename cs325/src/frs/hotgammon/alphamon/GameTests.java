package frs.hotgammon.alphamon;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GameTests {

	  GameImpl game;
	  /** Fixture */
	  @Before
	  public void setUp() {
	    game = new GameImpl();
	    game.newGame();
	  }

	  @Test public void shouldBeNonePlayerInTurnAfterNewGame() {
		    assertEquals(Color.NONE, game.getPlayerInTurn());
		  }
	  
	  @Test public void shouldBeBlackInTurnAfterFirstNextTurn() {
	    game.nextTurn();
	    assertEquals(Color.BLACK, game.getPlayerInTurn());
	  }

	  @Test public void shouldBeTwoBlackCheckersOnR1() {
		final int CHECKERS_ON_R1_AT_START = 2;
	    assertEquals(CHECKERS_ON_R1_AT_START, game.getCount(Location.R1));
	  }

	  @Test public void shouldBeValidToMoveFromR1ToR2AtStartOfGame() {
	      game.nextTurn();
		  assertTrue(game.move(Location.R1, Location.R2));
	  }

	  @Test public void shouldBeInvalidToMoveFromR1ToB1AtStartOfGame() {
		  game.nextTurn();
		  assertFalse(game.move(Location.R1, Location.B1));
	  }
	  
	  @Test public void shouldBeNoMovesLeftAfterMovingTwoBlackCheckersFromR1toR2() {
		game.nextTurn();
		game.move(Location.R1, Location.R2);
		game.move(Location.R1, Location.R2);
		 
		assertEquals(0,game.getNumberOfMovesLeft());
	  }

	  @Test public void shouldBeRedPlayerTurnAfterSecondNextTurn() {
		game.nextTurn();
		game.nextTurn();

		assertEquals(Color.RED,game.getPlayerInTurn());
	  }

	  @Test public void shouldBe3_4Die() {
		game.nextTurn();
		game.nextTurn();

		int[] expected = {3,4};
		int[] actual = game.diceThrown();
		
		assertEquals("Should be a 3", expected[0], actual[0]);
		assertEquals("Should be a 4", expected[1], actual[1]);
	  }
	  
	  @Test public void shouldEndGameAfterSixTurns() {
			game.nextTurn();
			game.nextTurn();
			game.nextTurn();
			game.nextTurn();
			game.nextTurn();
			game.nextTurn();
			
			assertEquals("Winner should be Red", Color.RED, game.winner());
	  
	  }
	  
	  @Test public void shouldNotEndGameAfterFiveTurns() {
			game.nextTurn();
			game.nextTurn();
			game.nextTurn();
			game.nextTurn();
			game.nextTurn();
			
			assertEquals("Winner should be null", Color.NONE, game.winner());
	  
	  }
	  

	  @Test public void shouldHaveRedIsWinner() {
			game.nextTurn();
			game.nextTurn();
			game.nextTurn();
			game.nextTurn();
			game.nextTurn();
			game.nextTurn();
			
			assertEquals("Winner should be Red", Color.RED, game.winner());
	  
	  }
	  
	  @Test public void shouldNotBeAbleToPlaceTwoDifferentColorsOnSameSquare() {
		  game.nextTurn();
		  game.move(Location.B6, Location.B2);
		  game.nextTurn();
		  assertFalse(game.move(Location.B1, Location.B2));
	}

	  @Test public void shouldBeAbleToPlaceTwoSameColorPiecesOnSameSquare() {
		  game.nextTurn();
		  game.move(Location.R1, Location.R2);
		  assertTrue(game.move(Location.R1, Location.R2));
	}

	  @Test public void shouldReturnProperCountForGivenSquare() {
		  game.nextTurn();
		  assertEquals("Count should be 2", 2, game.getCount(Location.B1));
	}

	  @Test public void shouldNotBeAbleToRemovePlayerOfWrongColor() {
		  	game.nextTurn();
			assertFalse("Should not be able to remove Red pieces.", game.move(Location.B1, Location.B2));
	}

	  @Test public void shouldBeAbleToRemovePlayerOfRightColor() {
		  	game.nextTurn();
		  	game.nextTurn();
			assertTrue("Should be able to remove Red pieces.", game.move(Location.B1, Location.B2));
	}
	  
	  

}
