package frs.hotgammon;

public interface MonFactory {

	public MoveValidator getMoveValidator();
	public TurnDeterminer getTurnDeterminer();
	public WinnerDeterminer getWinnerDeterminer();
	public RollDeterminer getRollDeterminer();
	
}
