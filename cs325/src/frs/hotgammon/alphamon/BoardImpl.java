package frs.hotgammon.alphamon;

import frs.hotgammon.Board;
import frs.hotgammon.Color;
import frs.hotgammon.Location;


public class BoardImpl implements Board {

	private final int SQUARES_ON_BOARD = 28;
	private Square[] board = new Square[SQUARES_ON_BOARD];
	
	public BoardImpl(){ 
		for(int i = 0; i < 28; i++){
			board[i] = new Square();
		}
	}	
	
	/* (non-Javadoc)
	 * @see frs.hotgammon.BoardInter#move(frs.hotgammon.Location, frs.hotgammon.Location, frs.hotgammon.Color)
	 */
	@Override
	public boolean move(Location from, Location to, Color playerInTurn){

		
		Square sqFrom = board[from.ordinal()];
		Square sqTo = board[to.ordinal()];
		Color sqFCol = sqFrom.getColor();
		Color sqTCol = sqTo.getColor();		
		
		if ( sqFCol == playerInTurn &&(sqFCol == sqTCol || sqTCol == Color.NONE)){
			if ( sqFrom.remove(sqFCol) ) {
				sqTo.add(sqFCol);
				return true;
			}
		}
		return false;
	}
	
	/* (non-Javadoc)
	 * @see frs.hotgammon.BoardInter#getCountAt(frs.hotgammon.Location)
	 */
	@Override
	public int getCountAt(Location loc){
		
		return board[loc.ordinal()].getCount();
		
	}

	/* (non-Javadoc)
	 * @see frs.hotgammon.BoardInter#getColorAt(frs.hotgammon.Location)
	 */
	@Override
	public Color getColorAt(Location loc){
		
		return board[loc.ordinal()].getColor();
		
	}
	
	/* (non-Javadoc)
	 * @see frs.hotgammon.BoardInter#add(frs.hotgammon.Color, frs.hotgammon.Location)
	 */
	@Override
	public boolean add(Color col, Location loc){
		
		int index = loc.ordinal();
		
		if( index > 0 && index < SQUARES_ON_BOARD){
			if(board[index].color == Color.NONE || board[index].color == col){
				board[index].color = col;
				board[index].count++;
				return true;
			}		
		}
		return false;
	}
	
	class Square{
		private int count = 0;
		private Color color = Color.NONE;
				
		public boolean add(Color col){
			
			if(this.color == Color.NONE || this.color == col){
				this.color = col;
				this.count++;
				return true;
			}			
			return false;
		}
		
		public boolean remove(Color col){

			if(this.color == col && this.count > 0){
				this.count--;
				if (this.count == 0){
					this.color = Color.NONE;
				}
				return true;
			}			
			return false;
		}
		
		public Color getColor(){
			return this.color;
		}
		
		public int getCount(){
			return this.count;
		}
		
	}
}