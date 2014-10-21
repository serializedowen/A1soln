/**
 * 
 */
package sprite;

public abstract class Sprite {
	protected char symbol;  //Not sure what '#' is 
	protected int row;
	protected int col;
	
	public Sprite(char symbol, int row, int col){
		this.symbol = symbol;
		this.row = row;
		this.col = col;
	}

	public char getSymbol() {
		return symbol;
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}
	
	public String toString(){
		return String.valueOf(symbol);
	}
	
	public void printCoordinates(){
		System.out.println(this.getSymbol() + ": now at " + this.getRow() + ", " + this.getCol());
	}
}
