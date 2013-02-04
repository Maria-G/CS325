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
  private Board gameBoard = new Board();
  private Color playerInTurn;
  private int[][] diceRolls = { {1,2}, {3,4}, {5,6} };
  private int diceRollIdx = 3;
  
  public void newGame() {}
  public void nextTurn() {
	  playerInTurn = (playerInTurn == Color.BLACK) ? Color.RED : Color.BLACK;
	  diceRollIdx = (diceRollIdx < 3) ? diceRollIdx + 1 : 0;
  }
  public boolean move(Location from, Location to) { 
	  return gameBoard.move(from, to, playerInTurn);
  }
  
  public Color getPlayerInTurn() { return playerInTurn; }
  
  public int getNumberOfMovesLeft() { return 0; }
  public int[] diceThrown() { return diceRolls[diceRollIdx]; }
  public int[] diceValuesLeft() { return new int []{}; }
  public Color winner() { return Color.RED; }
  public Color getColor(Location location) { return Color.NONE; }
  public int getCount(Location location) { return 2; }
}
