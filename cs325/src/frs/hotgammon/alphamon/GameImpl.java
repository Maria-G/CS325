package frs.hotgammon.alphamon;

/** Skeleton implementation of HotGammon.
 
   This source code is from the book 
     "Flexible, Reliable Software:
       Using Patterns and Agile Development"
     published 2010 by CRC Press.
   Author: 
     Henrik B Christensen 
     Computer Science Department
     Aarhus University
   
   This source code is provided WITHOUT ANY WARRANTY either 
   expressed or implied. You may study, use, modify, and 
   distribute it for non-commercial purposes. For any 
   commercial use, see http://www.baerbak.com/
*/

public class GameImpl implements Game {
  private Board gameBoard;
  private Color playerInTurn;
  private final int[][] DICE_ROLLS = { {1,2}, {3,4}, {5,6} };
  private int diceRollIdx;
  private int turns;
  
  public void newGame() {
	  gameBoard = new Board();
	  playerInTurn = null;
	  diceRollIdx = 3;
	  turns = 0;
	  setBoard();
  }
  public void nextTurn() {
	  playerInTurn = (playerInTurn == Color.BLACK) ? Color.RED : Color.BLACK;
	  diceRollIdx = (diceRollIdx < 3) ? diceRollIdx + 1 : 0;
	  turns++;
  }
  public boolean move(Location from, Location to) { 
	  return gameBoard.move(from, to, playerInTurn);
  }
  
  public Color getPlayerInTurn() { return playerInTurn; }
  
  public int getNumberOfMovesLeft() { return 0; }
  public int[] diceThrown() { return DICE_ROLLS[diceRollIdx]; }
  public int[] diceValuesLeft() { return new int []{}; }
  public Color winner() { return (turns == 6) ? Color.RED : null; }
  public Color getColor(Location location) { return gameBoard.getColorAt(location); }
  public int getCount(Location location) { return gameBoard.getCountAt(location); }
  
  
  public void setBoard(){

		// B1 = 2 red
		gameBoard.add(Color.RED,Location.B1);
		gameBoard.add(Color.RED,Location.B1);
		// B6 = 5 black
		gameBoard.add(Color.BLACK,Location.B6);
		gameBoard.add(Color.BLACK,Location.B6);
		gameBoard.add(Color.BLACK,Location.B6);
		gameBoard.add(Color.BLACK,Location.B6);
		gameBoard.add(Color.BLACK,Location.B6);
		// B8 = 3 black
		gameBoard.add(Color.BLACK,Location.B8);
		gameBoard.add(Color.BLACK,Location.B8);
		gameBoard.add(Color.BLACK,Location.B8);
		// B12 = 5 red
		gameBoard.add(Color.RED,Location.B12);
		gameBoard.add(Color.RED,Location.B12);
		gameBoard.add(Color.RED,Location.B12);
		gameBoard.add(Color.RED,Location.B12);
		gameBoard.add(Color.RED,Location.B12);
		// R12 = 5 black
		gameBoard.add(Color.BLACK,Location.R12);
		gameBoard.add(Color.BLACK,Location.R12);
		gameBoard.add(Color.BLACK,Location.R12);
		gameBoard.add(Color.BLACK,Location.R12);
		gameBoard.add(Color.BLACK,Location.R12);		
		// R8 = 3 red
		gameBoard.add(Color.RED,Location.R8);
		gameBoard.add(Color.RED,Location.R8);
		gameBoard.add(Color.RED,Location.R8);		
		// R6 = 5 red
		gameBoard.add(Color.RED,Location.R6);
		gameBoard.add(Color.RED,Location.R6);
		gameBoard.add(Color.RED,Location.R6);
		gameBoard.add(Color.RED,Location.R6);
		gameBoard.add(Color.RED,Location.R6);		
		// R1 = 2 black
		gameBoard.add(Color.BLACK,Location.R1);
		gameBoard.add(Color.BLACK,Location.R1);
  }
}

