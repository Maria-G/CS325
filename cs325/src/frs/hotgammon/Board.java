package frs.hotgammon;

public interface Board {

	public abstract boolean move(Location from, Location to, Color playerInTurn);

	public abstract int getCountAt(Location loc);

	public abstract Color getColorAt(Location loc);

	public abstract boolean place(Color col, int index);
	
	public boolean remove(Color col, int index);

}