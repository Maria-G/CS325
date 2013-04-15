package frs.hotgammon.common;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import frs.hotgammon.Board;
import frs.hotgammon.MonFactory;
import frs.hotgammon.MoveValidator;
import frs.hotgammon.RollDeterminer;
import frs.hotgammon.TurnDeterminer;
import frs.hotgammon.WinnerDeterminer;
import frs.hotgammon.framework.Color;
import frs.hotgammon.framework.Game;
import frs.hotgammon.framework.GameObserver;
import frs.hotgammon.framework.Location;



public class GameImpl implements Game {
  private Board gameBoard;
  private Color playerInTurn;

  private List<Integer> diceRoll;
  private int movesLeft;
  private int turns;
  private MoveValidator moveValidator;
  private WinnerDeterminer winnerDeterminer;
  private TurnDeterminer turnDeterminer;
  private RollDeterminer diceRollDeterminer;
  private ArrayList<GameObserver> observers = new ArrayList<GameObserver>();

  
  public GameImpl(MoveValidator mValidator, WinnerDeterminer wDeterminer, TurnDeterminer tDeterminer, RollDeterminer drDeterminer){
	  mValidator.setGame(this);
	  wDeterminer.setGame(this);
	  tDeterminer.setGame(this);
	  moveValidator = mValidator;
	  winnerDeterminer = wDeterminer;
	  turnDeterminer = tDeterminer;
	  diceRollDeterminer = drDeterminer;
  }
  
  public GameImpl(MonFactory factory){
	  setFactory(factory);
  }
  
  public void setFactory(MonFactory factory){
	  factory.setGame(this);
	  moveValidator = factory.createMoveValidator();
	  winnerDeterminer = factory.createWinnerDeterminer();
	  turnDeterminer = factory.createTurnDeterminer();
	  diceRollDeterminer = factory.createRollDeterminer();
  }
  
  public void newGame() {
	  gameBoard = new BoardImpl();
	  diceRollDeterminer.reset();
	  playerInTurn = Color.NONE;
	  turns = 0;

	  configure(new Placement[] {
	    		// B1 = 2 red
	    		new Placement(Color.RED,Location.B1),
	    		new Placement(Color.RED,Location.B1),
	    		// B6 = 5 black
	    		new Placement(Color.BLACK,Location.B6),
	    		new Placement(Color.BLACK,Location.B6),
	    		new Placement(Color.BLACK,Location.B6),
	    		new Placement(Color.BLACK,Location.B6),
	    		new Placement(Color.BLACK,Location.B6),
	    		// B8 = 3 black
	    		new Placement(Color.BLACK,Location.B8),
	    		new Placement(Color.BLACK,Location.B8),
	    		new Placement(Color.BLACK,Location.B8),
	    		// B12 = 5 red
	    		new Placement(Color.RED,Location.B12),
	    		new Placement(Color.RED,Location.B12),
	    		new Placement(Color.RED,Location.B12),
	    		new Placement(Color.RED,Location.B12),
	    		new Placement(Color.RED,Location.B12),
	    		// R12 = 5 black
	    		new Placement(Color.BLACK,Location.R12),
	    		new Placement(Color.BLACK,Location.R12),
	    		new Placement(Color.BLACK,Location.R12),
	    		new Placement(Color.BLACK,Location.R12),
	    		new Placement(Color.BLACK,Location.R12),		
	    		// R8 = 3 red
	    		new Placement(Color.RED,Location.R8),
	    		new Placement(Color.RED,Location.R8),
	    		new Placement(Color.RED,Location.R8),		
	    		// R6 = 5 red
	    		new Placement(Color.RED,Location.R6),
	    		new Placement(Color.RED,Location.R6),
	    		new Placement(Color.RED,Location.R6),
	    		new Placement(Color.RED,Location.R6),
	    		new Placement(Color.RED,Location.R6),		
	    		// R1 = 2 black
	    		new Placement(Color.BLACK,Location.R1),
	    		new Placement(Color.BLACK,Location.R1),
	    });

  }
  
  public void nextTurn() {

	  playerInTurn = turnDeterminer.nextTurn();
	  
	  //Roll Dice
	  diceRollDeterminer.rollDice();
	  int[] dRoll = diceRollDeterminer.getDiceRoll();
	  
	  if(turns == 0){
		  while(dRoll.length > 2){
			  diceRollDeterminer.rollDice();
			  dRoll = diceRollDeterminer.getDiceRoll();
		  }
		  playerInTurn = (dRoll[0] > dRoll[1]) ? Color.RED : Color.BLACK;		  
	  }
	  
	  //Create diceRoll 
	  if(dRoll.length == 2){
		  diceRoll = new ArrayList<Integer>(Arrays.asList(dRoll[0], dRoll[1])) ;
	  }
	  else{
		  diceRoll = new ArrayList<Integer>(Arrays.asList(dRoll[0], dRoll[1], dRoll[2], dRoll[3])) ;
	  }
	  
	  turns++;
	  movesLeft = diceRoll.size();
	  
	  //Notify Observers
	  for( GameObserver gO : this.observers ){
		  gO.diceRolled(diceThrown());
	  }
	  
  }
  public boolean move(Location from, Location to) { 
	  ///
	  if(from == Location.R_BEAR_OFF || from == Location.B_BEAR_OFF){
		  Color checkerColor = (from == Location.R_BEAR_OFF) ? Color.RED : Color.BLACK;
		  gameBoard.move(from, to, checkerColor);
		//Notify Observers
		  for( GameObserver gO : this.observers ){
			  System.out.println("told observers to move from bear off.  ");
			  gO.checkerMove(from, to);
		  }
		  return true;
	  }
	  ///
	  System.out.println("inside move  ");
	  if (movesLeft == 0){
		  System.out.println("movesLeft == 0");
		  return false;
	  }
	  if (moveValidator.isValid(from, to)){
		  System.out.println("moveValidator.isValid(from, to) == true");
		  
		  if(gameBoard.getCountAt(to) == 1 && gameBoard.getColorAt(to) != playerInTurn){
			  moveOpponentToBar(to);
		  }
		  boolean moveValue = gameBoard.move(from, to, playerInTurn);
		  movesLeft--;
		  removeDiceValueUsed(from, to);
		  if(moveValue == true){
			  //Notify Observers
			  for( GameObserver gO : this.observers ){
				  System.out.println("told observers to move.  " + moveValue);
				  gO.checkerMove(from, to);
			  }
		  }
		  return moveValue;
	  }
	  System.out.println("moveValidator.isValid(from, to) == false");
	  return false;
	  
  }
  
  public Color getPlayerInTurn() { return playerInTurn; }
  
  public int getNumberOfMovesLeft() { return movesLeft; }
  public int[] diceThrown() { return diceRollDeterminer.getDiceRoll(); }//DICE_ROLLS[diceRollIdx]; }
  public int[] diceValuesLeft() { 
	  int[] diceRollArr = new int[diceRoll.size()];
	  for (int i = 0; i < diceRollArr.length; i++){
		  diceRollArr[i] = diceRoll.get(i);
	  }
	  return diceRollArr;}
  public Color winner() { return winnerDeterminer.winner(turns);}
  public Color getColor(Location location) { return gameBoard.getColorAt(location); }
  public int getCount(Location location) { return gameBoard.getCountAt(location); }
  
  private void removeDiceValueUsed(Location from, Location to){
	  int diceValueUsed = (playerInTurn == Color.BLACK) ? Location.distance(from, to) : (-1 * Location.distance(from, to));//(playerInTurn == Color.BLACK) ? rawDistanceTravelled : -1 * (rawDistanceTravelled);
	  int remIdx = diceRoll.indexOf(diceValueUsed);
	  if (remIdx < 0){
		  remIdx = 0;
	  }
	  diceRoll.remove(remIdx);
  }
  
  private void moveOpponentToBar(Location opponentLoc){
	  Color colorOfOpponent = opponentColor();

	  Location otherPlayerBar = (colorOfOpponent == Color.BLACK) ? Location.B_BAR : Location.R_BAR;
	  gameBoard.place(colorOfOpponent, otherPlayerBar.ordinal());
	  gameBoard.remove(colorOfOpponent, opponentLoc.ordinal());
	  
	//Notify Observers
	  for( GameObserver gO : this.observers ){
		  System.out.println("told observers to move opponent to bar");
		  gO.checkerMove(opponentLoc, otherPlayerBar);
	  }
  
  }
  
  private Color opponentColor(){
	  if(playerInTurn == Color.NONE){
		  return Color.NONE;
	  }
	  return (playerInTurn == Color.RED) ? Color.BLACK : Color.RED;
  }
  
  	static public class Placement {
	    public Location location;
	    public Color    player;
	    public Placement(Color player, Location location) {
	        this.player = player;
	        this.location = location;
	    }
	}
  	
	public void configure(Placement[] placements) {
		gameBoard = new BoardImpl();
	    for (int i = 0; i < placements.length; i++) {
	        //gameBoard.place(placements[i].player, placements[i].location.ordinal());	
	    	Location from = getPlayerBearOff(placements[i].player);
	    	gameBoard.place(placements[i].player, from.ordinal());
	        move(from, placements[i].location);	        
	    }
	}
	
	private Location getPlayerBearOff(Color player){
		return ( player == Color.BLACK) ? Location.B_BEAR_OFF : Location.R_BEAR_OFF;
	}

	@Override
	public void addObserver(GameObserver observer) {
		this.observers.add(observer);		
	}
}

