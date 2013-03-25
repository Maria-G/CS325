package frs.hotgammon.tests;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import frs.hotgammon.MonFactory;
import frs.hotgammon.common.GameImpl;
import frs.hotgammon.tests.stubs.Fixed_BlackStarts_EpsilonMonFactory;
import frs.hotgammon.tests.stubs.Fixed_RedStarts_EpsilonMonFactory;

@RunWith(value = Parameterized.class)
public class EpsilonMonCoreTests {
	
	private GameImpl game;

	public EpsilonMonCoreTests(MonFactory factory){
		game = new GameImpl(factory);
		game.newGame();			
	}
	
	 @Parameters
	 public static Collection<MonFactory[]> data() {
		   MonFactory[][] data = new MonFactory[][] { 
			   {new Fixed_BlackStarts_EpsilonMonFactory()},
			   {new Fixed_RedStarts_EpsilonMonFactory()},			   			   
		   };
		   return Arrays.asList(data); 
	 }

	@Test
	public void shouldDoubleMovesIfDoublesRolled() {
		game.nextTurn();
		game.nextTurn();
		game.nextTurn();
		assertEquals(4, game.getNumberOfMovesLeft());
	}
}
