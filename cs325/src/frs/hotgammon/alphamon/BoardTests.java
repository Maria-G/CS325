package frs.hotgammon.alphamon;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import frs.hotgammon.alphamon.Board.Square;

public class BoardTests {

	Board board;
	
	@Before
	public void setUp(){
		board = new Board();
	}
	
	@Test
	public void shouldGetCount0AtStartOfGame() {

		assertEquals("Count should have been 0.", 0, board.getCountAt(Location.R1));
	}
	
	@Test
	public void shouldGetNoColorAtStartOfGame() {

		assertEquals("Color should have been null.", null, board.getColorAt(Location.R1));
	}
	
	@Test
	public void shouldAddOneRedPieceToSquareAtIndex1() {
		boolean wasAdded = board.add(Color.RED, Location.R1);
		assertTrue("Should have been able to add a red piece to a new board", wasAdded);
		assertEquals("Count should have been 1.", 1, board.getCountAt(Location.R1));
		assertEquals("Color should have been Red.", Color.RED, board.getColorAt(Location.R1));
	}
	
	@Test public void shouldBeAbleToMoveToNewLocation() {
	      board.add(Color.RED, Location.R1);
		  assertTrue(board.move(Location.R1, Location.R2, Color.RED));
	  }

}