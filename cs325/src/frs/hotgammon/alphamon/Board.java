package frs.hotgammon.alphamon;

public class Board {

	private Square[] board = new Square[28];
	
	public Board(){ 
		for(int i = 0; i < 28; i++){
			board[i] = new Square();
		}
		
		// B1 = 2 red
		board[Location.B1.ordinal()].add(Color.RED);
		board[Location.B1.ordinal()].add(Color.RED);
		// B6 = 5 black
		board[Location.B6.ordinal()].add(Color.BLACK);
		board[Location.B6.ordinal()].add(Color.BLACK);
		board[Location.B6.ordinal()].add(Color.BLACK);
		board[Location.B6.ordinal()].add(Color.BLACK);
		board[Location.B6.ordinal()].add(Color.BLACK);
		// B8 = 3 black
		board[Location.B8.ordinal()].add(Color.BLACK);
		board[Location.B8.ordinal()].add(Color.BLACK);
		board[Location.B8.ordinal()].add(Color.BLACK);
		// B12 = 5 red
		board[Location.B12.ordinal()].add(Color.RED);
		board[Location.B12.ordinal()].add(Color.RED);
		board[Location.B12.ordinal()].add(Color.RED);
		board[Location.B12.ordinal()].add(Color.RED);
		board[Location.B12.ordinal()].add(Color.RED);
		// R12 = 5 black
		board[Location.R12.ordinal()].add(Color.BLACK);
		board[Location.R12.ordinal()].add(Color.BLACK);
		board[Location.R12.ordinal()].add(Color.BLACK);
		board[Location.R12.ordinal()].add(Color.BLACK);
		board[Location.R12.ordinal()].add(Color.BLACK);
		
		// R8 = 3 red
		board[Location.R8.ordinal()].add(Color.RED);
		board[Location.R8.ordinal()].add(Color.RED);
		board[Location.R8.ordinal()].add(Color.RED);
		
		// R6 = 5 red
		board[Location.R6.ordinal()].add(Color.RED);
		board[Location.R6.ordinal()].add(Color.RED);
		board[Location.R6.ordinal()].add(Color.RED);
		board[Location.R6.ordinal()].add(Color.RED);
		board[Location.R6.ordinal()].add(Color.RED);
		
		// R1 = 2 black
		board[Location.R1.ordinal()].add(Color.BLACK);
		board[Location.R1.ordinal()].add(Color.BLACK);
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
