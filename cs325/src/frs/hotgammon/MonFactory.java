package frs.hotgammon;

public interface MonFactory {

	public MoveValidator createMoveValidator();
	public TurnDeterminer createTurnDeterminer();
	public WinnerDeterminer createWinnerDeterminer();
	public RollDeterminer createRollDeterminer();
	public void setGame(Game game);
	
}
