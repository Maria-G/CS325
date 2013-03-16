package frs.hotgammon.mariatests;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import frs.hotgammon.Color;
import frs.hotgammon.Location;
import frs.hotgammon.MonFactory;
import frs.hotgammon.common.GameImpl;
import frs.hotgammon.common.GameImpl.Placement;
import frs.hotgammon.variants.factories.AlphaMonFactory;
import frs.hotgammon.variants.factories.BetaMonFactory;
import frs.hotgammon.variants.factories.DeltaMonFactory;
import frs.hotgammon.variants.factories.EpsilonMonFactory;
import frs.hotgammon.variants.factories.GammaMonFactory;
import frs.hotgammon.variants.factories.HandicapMonFactory;

@RunWith(value = Parameterized.class)
public class FactoryCoreTests {

	private GameImpl game;
	
	
	public FactoryCoreTests(MonFactory factory) {
		game = new GameImpl(factory);
		game.newGame();		
	}
	
	 @Parameters
	 public static Collection<MonFactory[]> data() {
		   MonFactory[][] data = new MonFactory[][] { 
			   // AlphaMon		
			   {new AlphaMonFactory()},
			   // BetaMon
			   {new BetaMonFactory()},
			   // GammaMon
			   {new GammaMonFactory()},
			   // DeltaMon
			   {new DeltaMonFactory()},
			   // EpsilonMon
			   {new EpsilonMonFactory()},
			   // ZetaMon -- Identical to AlphaMon with DIFFERENT STARTING POSITION (p434)
			   //{ new SimpleMoveValidator(), new SixMoveWinnerDeterminer() , new AceyDeuceyTurnDeterminer()},
			   // HandicapMon?
			   {new HandicapMonFactory()},
				   			   
		   };
		   return Arrays.asList(data); 
	 }
	 
	 @Test
		public void shouldHaveNoPlayerInTurnAfterNewGame() {
			assertEquals(Color.NONE, game.getPlayerInTurn());
		}

		@Test
		public void shouldHaveBlackPlayerInTurnAfterFirstRoll() {
			game.nextTurn(); // will throw [1,2] and thus black starts
			assertEquals(Color.BLACK, game.getPlayerInTurn());
		}
		


		@Test
		public void shoudlBeTwoBlackCheckersOnR1() {
			assertEquals(2, game.getCount(Location.R1));
			assertEquals(Color.BLACK, game.getColor(Location.R1));
		}

		@Test
		public void shouldHaveBlackOnR1andBlackOnB2AndOneMoreLeft() {
			game.configure( new Placement[] { 
					new Placement(Color.BLACK, Location.R1),
					new Placement(Color.BLACK, Location.R1),
					
				});

			assertEquals(2, game.getCount(Location.R1));
			assertEquals(Color.BLACK, game.getColor(Location.R1));
			game.nextTurn();
			assertTrue(game.move(Location.R1, Location.R2));
			assertEquals(1, game.getCount(Location.R2));
			assertEquals(Color.BLACK, game.getColor(Location.R2));
			assertEquals(1, game.getCount(Location.R1));
			assertEquals(Color.BLACK, game.getColor(Location.R1));

			assertEquals(1, game.getNumberOfMovesLeft());
		}

		@Test
		public void shouldNotBeAbleToPlaceBlackOnRedOccupiedSquare() {
			game.nextTurn();
			int prevCount = game.getCount(Location.R1);
			if (occupiedBy(Color.RED, Location.B1)) {
				assertFalse(game.move(Location.R1, Location.B1));
				assertEquals(game.getCount(Location.R1), prevCount);
			} else {
				assertTrue(game.move(Location.R1, Location.B1));
				assertEquals(game.getCount(Location.R1), prevCount - 1);

			}
		}

	

		

		@Test
		public void shouldNotBeAbleToMoveIfNotInTurn() {
			game.nextTurn();
			assertFalse(game.move(Location.B1, Location.B2));
			assertEquals(game.getNumberOfMovesLeft(), 2);
		}


		
		@Test
		public void shouldNotBeAbleToPlaceRedOnBlackOccupiedSquare() {
			game.nextTurn();
			game.nextTurn();
			assertFalse(game.move(Location.B1, Location.R1));

		}


		private boolean occupiedBy(Color color, Location loc) {
			return game.getCount(loc) > 0 && game.getColor(loc) == color;
		}

		// Maria's Tests
		@Test
		public void shouldBeTwoBlackCheckersOnR1() {
			final int CHECKERS_ON_R1_AT_START = 2;
			assertEquals(CHECKERS_ON_R1_AT_START, game.getCount(Location.R1));
		}

		@Test
		public void shouldBeValidToMoveFromR1ToR2AtStartOfGame() {
			game.nextTurn();
			assertTrue(game.move(Location.R1, Location.R2));
		}

		@Test
		public void shouldBeInvalidToMoveFromR1ToB1AtStartOfGame() {
			game.nextTurn();
			assertFalse(game.move(Location.R1, Location.B1));
		}

		@Test
		public void shouldBeNoMovesLeftAfterMovingTwoBlackCheckersFromR1toR2() {
			game.nextTurn();
			game.move(Location.R1, Location.R2);
			game.move(Location.R1, Location.R3);

			assertEquals(0, game.getNumberOfMovesLeft());
		}




	
		@Test
		public void shouldNotBeAbleToPlaceTwoDifferentColorsOnSameSquare() {
			game.nextTurn();
			game.move(Location.B6, Location.B2);
			game.nextTurn();
			assertFalse(game.move(Location.B1, Location.B2));
		}

		@Test
		public void shouldBeAbleToPlaceTwoSameColorPiecesOnSameSquare() {
			//Added
			game.configure(new Placement[]{
	    		new Placement(Color.BLACK,Location.R1),
	    		new Placement(Color.BLACK,Location.R2)
			});
			//
			game.nextTurn();
			game.move(Location.R1, Location.R3);
			assertTrue(game.move(Location.R2, Location.R3));
		}

		@Test
		public void shouldReturnProperCountForGivenSquare() {
			game.nextTurn();
			assertEquals("Count should be 2", 2, game.getCount(Location.B1));
		}

		@Test
		public void shouldNotBeAbleToRemovePlayerOfWrongColor() {
			game.nextTurn();
			assertFalse("Should not be able to remove Red pieces.",
					game.move(Location.B1, Location.B2));
		}



		// Dan's Test
		@Test
		public void shouldHaveTwoBlackCheckersOnR1() {
			game.nextTurn();
			assertTrue(game.getCount(Location.R1) == 2);
			assertTrue(game.getColor(Location.R1) == Color.BLACK);
		}

		@Test
		public void shouldBeAbleToMoveBlackR1toR2() {
			game.configure(new Placement[] {
				new Placement(Color.BLACK, Location.R1),	
				new Placement(Color.BLACK, Location.R1)
			});
			game.nextTurn();
			assertEquals(2, game.getCount(Location.R1));
			assertTrue(game.move(Location.R1, Location.R2));
			assertTrue(game.getCount(Location.R1) == 1);
			assertTrue(game.getCount(Location.R2) == 1);
			assertTrue(game.getNumberOfMovesLeft() == 1);

		}

		@Test
		public void shouldNotBeAbleToMoveBlackR1toB1() {
			game.nextTurn();
			assertFalse(game.move(Location.R1, Location.B1));
		}

		@Test
		public void shouldHaveNoMoveLeft() {
			game.nextTurn();
			game.move(Location.R1, Location.R2);
			game.move(Location.R1, Location.R3);
			assertTrue(game.getNumberOfMovesLeft() == 0);
		}



	

		@Test
		public void shouldNotBeAbleToMoveWithNoMovesLeft() {
			game.nextTurn();
			game.move(Location.R1, Location.R2);
			game.move(Location.R1, Location.R3);
			assertFalse(game.move(Location.R2, Location.R3));
		}

		/*
		 * @Test public void redShouldBeWinnerAfter6Turns() { game.nextTurn();
		 * assertTrue(game.winner() == Color.NONE); game.nextTurn();
		 * game.nextTurn(); game.nextTurn(); game.nextTurn(); game.nextTurn();
		 * game.nextTurn(); assertTrue(game.winner() == Color.RED); }
		 */



		@Test
		public void shouldNotBeAbleToMoveFromASpotWithoutCheckers() {
			game.nextTurn();
			assertFalse(game.move(Location.R2, Location.R3));
		}

		// Joe's Test
		@Test
		public void ShouldBeBlackToGoFirst() {

			game.nextTurn();

			assertEquals("Black is first to go", Color.BLACK,
					game.getPlayerInTurn());

		}

		@Test
		public void shouldRunOutOfMoves() {
			// Needed to add nextTurn call
			game.configure(new Placement[] {
					new Placement(Color.BLACK, Location.R1),
					new Placement(Color.BLACK, Location.R1)
			});
			game.nextTurn();
			game.move(Location.R1, Location.R2);

			game.move(Location.R1, Location.R3);

			assertEquals(" no moves should be left ", 0,
					game.getNumberOfMovesLeft());

		}

		/*
		 * This test is wrong.
		 * 
		 * @Test
		 * 
		 * public void shouldGetNextTurnAndDiceBe34(){
		 * 
		 * game.nextTurn();
		 * 
		 * game.nextTurn();
		 * 
		 * int [] currentDice = game.diceValuesLeft();
		 * 
		 * int firstDie = currentDice[0];
		 * 
		 * int secondDie = currentDice[1];
		 * 
		 * System.out.println(firstDie + " " + secondDie);
		 * 
		 * assertEquals( "dice should be 3-4", 3, secondDie);
		 * 
		 * assertEquals( "dice should be 3-4", 4, firstDie);
		 * 
		 * }
		 */

		/*
		 * Bad Test : Test diceThrown, not diceValuesLeft()
		 * 
		 * @Test
		 * 
		 * public void shouldGetNextTurnThreeTimesAndDiceBe56(){
		 * 
		 * game.nextTurn();
		 * 
		 * game.nextTurn();
		 * 
		 * game.nextTurn();
		 * 
		 * int [] currentDice = game.diceValuesLeft();
		 * 
		 * int firstDie = currentDice[0];
		 * 
		 * int secondDie = currentDice[1];
		 * 
		 * System.out.println(firstDie + " " + secondDie);
		 * 
		 * assertEquals( "dice should be 5-6", 5, secondDie);
		 * 
		 * assertEquals( "dice should be 5-6", 6, firstDie);
		 * 
		 * }
		 */

		/*
		 * @Test
		 * 
		 * public void shouldGetDice1And2OnOpeningTurn(){
		 * 
		 * game.nextTurn();
		 * 
		 * int [] currentDice = game.diceValuesLeft();
		 * 
		 * int firstDie = currentDice[0];
		 * 
		 * int secondDie = currentDice[1];
		 * 
		 * assertEquals( "dice should be 1-2", 1, secondDie);
		 * 
		 * assertEquals( "dice should be 1-2", 2, firstDie);
		 * 
		 * }
		 */

	
		// Marta's Tests
		@Test
		public void shouldHaveTwoBlackCheckersOnR1WhenNewGameStarts() {
			game.nextTurn();
			assertTrue(game.getCount(Location.R1) == 2);
		}

		@Test
		public void shouldHave1BlackCheckerOnR1and1BlackCheckerOnR2AfterMoveFromR1toR2() {
			game.configure(new Placement[] {
				new Placement(Color.BLACK, Location.R1),
				new Placement(Color.BLACK, Location.R1)
			});
			game.nextTurn();
			game.move(Location.R1, Location.R2);
			assertTrue(game.getCount(Location.R1) == 1);
			assertTrue(game.getCount(Location.R2) == 1);
			assertTrue(game.getColor(Location.R1) == Color.BLACK);
			assertTrue(game.getColor(Location.R2) == Color.BLACK);
		}

		@Test
		public void shouldHaveOneMoveLeftForBlackPlayerAfterMoveFromR1ToR2() {

			game.nextTurn();
			game.move(Location.R1, Location.R2);
			assertTrue(game.getPlayerInTurn() == Color.BLACK);
			System.out.println(game.getNumberOfMovesLeft());
			assertTrue(game.getNumberOfMovesLeft() == 1);
		}

		@Test
		public void shouldHave0NumberOfMovesForBlackPlayerAfterTheBlackMovedTwice() {

			game.nextTurn();
			game.move(Location.R1, Location.R2);
			game.move(Location.R1, Location.R3);

			assertTrue(game.getNumberOfMovesLeft() == 0);

		}




		@Test
		public void moveR1toB1isInvalidAsThereIsAnOpponentThere() {
			game.nextTurn();
			assertFalse(game.move(Location.R1, Location.B1));
			assertTrue(game.getColor(Location.B1) == Color.RED);
		}

		@Test
		public void shouldHaveNoPlayerInTurnBeforeTheGameStarted() {
			assertTrue(game.getPlayerInTurn() == Color.NONE);
		}



		@Test
		public void R1ShouldBeNoneAndR3BlackAfterMoveFromR1ToR3() {

			game.nextTurn();
			assertTrue(game.move(Location.R1, Location.R2));
			assertTrue(game.move(Location.R1, Location.R3));
			assertTrue(game.getColor(Location.R1) == Color.NONE);
			assertTrue(game.getColor(Location.R3) == Color.BLACK);

		}

		

		@Test
		public void newGameResetsTheBoardToInitial() {
			game.nextTurn();
			game.nextTurn();
			game.nextTurn();
			game.newGame();

			assertTrue(game.getCount(Location.R1) == 2);
			assertTrue(game.getCount(Location.B1) == 2);

			assertTrue(game.getColor(Location.R1) == Color.BLACK);
			assertTrue(game.getColor(Location.B1) == Color.RED);
		}

		@Test
		public void newGameResetsPlayerToBlack() {

			game.nextTurn();
			game.nextTurn();
			game.nextTurn();
			game.newGame();
			game.nextTurn();
			assertTrue(game.getPlayerInTurn() == Color.BLACK);
		}


		@Test
		public void shouldNotBeAbleToPlaceTwoDifferentCheckersInTheSameContainer() {

			game.nextTurn();
			assertFalse(game.move(Location.R12, Location.B1));
		}

		@Test
		public void shouldBeAbleToPlaceTheSameCheckersInOneContainer() {
			game.configure(new Placement[] {
					new Placement(Color.BLACK, Location.R1),
					new Placement(Color.BLACK, Location.R2)
			});
			game.nextTurn();
			
			assertTrue(game.move(Location.R1, Location.R3));
			assertTrue(game.move(Location.R2, Location.R3));
		}





}
