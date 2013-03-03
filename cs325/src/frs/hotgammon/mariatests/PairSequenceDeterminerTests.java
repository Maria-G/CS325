package frs.hotgammon.mariatests;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import frs.hotgammon.Location;
import frs.hotgammon.MoveValidator;
import frs.hotgammon.RollDeterminer;
import frs.hotgammon.TurnDeterminer;
import frs.hotgammon.WinnerDeterminer;
import frs.hotgammon.common.GameImpl;
import frs.hotgammon.variants.movevalidators.CompleteMoveValidator;
import frs.hotgammon.variants.movevalidators.SimpleMoveValidator;
import frs.hotgammon.variants.rolldeterminers.PairSequenceDeterminer;
import frs.hotgammon.variants.rolldeterminers.RandomRollDeterminer;
import frs.hotgammon.variants.turndeterminers.AceyDeuceyTurnDeterminer;
import frs.hotgammon.variants.turndeterminers.AlternatingTurnDeterminer;
import frs.hotgammon.variants.winnerdeterminers.BearOffWinnerDeterminer;
import frs.hotgammon.variants.winnerdeterminers.SixMoveWinnerDeterminer;

@RunWith(value = Parameterized.class)
public class PairSequenceDeterminerTests {


	private GameImpl game;
	
	
	public PairSequenceDeterminerTests(MoveValidator validator, WinnerDeterminer winnerDeterminer, TurnDeterminer ntd, RollDeterminer diceRollDeterminer) {
		game = new GameImpl(validator, winnerDeterminer, ntd, diceRollDeterminer);
		game.newGame();		
	}
	
	 @Parameters
	 public static Collection<Object[]> data() {
	   Object[][] data = new Object[][] { 
			   // AlphaMon		
			   { new SimpleMoveValidator(), new SixMoveWinnerDeterminer(), new AlternatingTurnDeterminer() , new PairSequenceDeterminer()},
			   // BetaMon
			   { new CompleteMoveValidator(), new SixMoveWinnerDeterminer() , new AlternatingTurnDeterminer() , new PairSequenceDeterminer()},
			   // GammaMon
			   { new SimpleMoveValidator(), new BearOffWinnerDeterminer() , new AlternatingTurnDeterminer() , new PairSequenceDeterminer()},
			   // DeltaMon
			   { new SimpleMoveValidator(), new SixMoveWinnerDeterminer() , new AceyDeuceyTurnDeterminer() , new PairSequenceDeterminer()},
			  		   
	   };
	   return Arrays.asList(data);
	 }
	 

		@Test
		public void shouldBe3_4Die() {
			game.nextTurn();
			game.nextTurn();
			int[] expected = { 3, 4 };
			int[] actual = game.diceThrown();
			assertEquals("Should be a 3", expected[0], actual[0]);
			assertEquals("Should be a 4", expected[1], actual[1]);
		}
		
		@Test
		public void diceRollsShouldBeIncremental() {
			game.nextTurn();
			assertArrayEquals(game.diceThrown(), new int[] { 1, 2 });
			game.move(Location.R1, Location.R2);
			game.move(Location.R1, Location.R3);
			game.nextTurn();
			assertArrayEquals(game.diceThrown(), new int[] { 3, 4 });
			game.nextTurn();
			assertArrayEquals(game.diceThrown(), new int[] { 5, 6 });
			game.nextTurn();
			assertArrayEquals(game.diceThrown(), new int[] { 1, 2 });
			game.nextTurn();
			assertArrayEquals(game.diceThrown(), new int[] { 3, 4 });
			game.nextTurn();
			assertArrayEquals(game.diceThrown(), new int[] { 5, 6 });
		}
		

		@Test
		public void dieValuesAre34AfterNextTurnIsInvokedTheSecondTime() {

			game.nextTurn();
			game.nextTurn();
			assertTrue(game.diceThrown()[0] == 3);
			assertTrue(game.diceThrown()[1] == 4);
		}
		
		@Test
		public void eachTurnThrowsCorrectNumberOfDice() {

			game.nextTurn();
			game.nextTurn();
			game.nextTurn();
			assertTrue(game.diceThrown()[0] == 5);
			assertTrue(game.diceThrown()[1] == 6);
			game.nextTurn();
			assertTrue(game.diceThrown()[0] == 1);
			assertTrue(game.diceThrown()[1] == 2);
			game.nextTurn();
			assertTrue(game.diceThrown()[0] == 3);
			assertTrue(game.diceThrown()[1] == 4);

		}

		private boolean rollEquals(int[] roll) {
			return roll[0] == game.diceThrown()[0]
					&& roll[1] == game.diceThrown()[1];
		}
		
		@Test
		public void shouldRoll12Then34Then56Then12() {
			game.nextTurn();
			assertTrue(rollEquals(new int[] { 1, 2 }));
			game.nextTurn();
			assertTrue(rollEquals(new int[] { 3, 4 }));
			game.nextTurn();
			assertTrue(rollEquals(new int[] { 5, 6 }));
			game.nextTurn();
			assertTrue(rollEquals(new int[] { 1, 2 }));
		}
		
		
	 

}
