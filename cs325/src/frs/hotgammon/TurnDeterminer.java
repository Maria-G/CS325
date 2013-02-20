package frs.hotgammon;

public interface TurnDeterminer {

	public Color nextTurn();
	public void setGame(Game game);
}
