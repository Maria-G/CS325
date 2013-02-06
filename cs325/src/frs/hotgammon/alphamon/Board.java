package frs.hotgammon.alphamon;

public class Board {

	private final int SQUARES_ON_BOARD = 28;
	private Square[] board = new Square[SQUARES_ON_BOARD];
	
	public Board(){ 
		for(int i = 0; i < 28; i++){
			board[i] = new Square();
		}
	}	
	
	public boolean move(Location from, Location to, Color playerInTurn){
		Square sqFrom = board[from.ordinal()];
		Square sqTo = board[to.ordinal()];
		Color sqFCol = sqFrom.getColor();
		Color sqTCol = sqTo.getColor();		
		
		if ( sqFCol == playerInTurn &&(sqFCol == sqTCol || sqTCol == null)){
			if ( sqFrom.remove(sqFCol) ) {
				sqTo.add(sqFCol);
				return true;
			}
		}
		return false;
	}
	
	public int getCountAt(Location loc){
		
		return board[loc.ordinal()].getCount();
		
	}

	public Color getColorAt(Location loc){
		
		return board[loc.ordinal()].getColor();
		
	}
	
	public boolean add(Color col, Location loc){
		
		int index = loc.ordinal();
		
		if( index > 0 && index < SQUARES_ON_BOARD){
			if(board[index].color == null || board[index].color == col){
				board[index].color = col;
				board[index].count++;
				return true;
			}		
		}
		return false;
	}
	
	class Square{
		private int count = 0;
		private Color color = null;
				
		public boolean add(Color col){
			
			if(this.color == null || this.color == col){
				this.color = col;
				this.count++;
				return true;
			}			
			return false;
		}
		
		public boolean remove(Color col){

			if(this.color == col && this.count > 0){
				this.count--;
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
