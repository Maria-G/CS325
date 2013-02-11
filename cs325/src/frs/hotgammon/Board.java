package frs.hotgammon;

public interface Board {

	public abstract boolean move(Location from, Location to, Color playerInTurn);

	public abstract int getCountAt(Location loc);

	public abstract Color getColorAt(Location loc);

	public abstract boolean add(Color col, Location loc);

}