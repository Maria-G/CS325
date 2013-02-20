package frs.hotgammon;

public interface WinnerDeterminer {

	public Color winner(int turnCount);
	public void setGame(Game game);
}
