/**
 * 
 */
package sprite;


public class Monkey extends Sprite implements Movable {
	public int score;
	public int numMoves;
	
	
	public Monkey(char symbol, int row, int col) {
		super(symbol, row, col);
		this.score = 0;
		this.numMoves = 0;
	}

	
	public void eatBanana(int score){
		this.score += score;
	}
	
	
	public int getScore() {
		return score;
	}


	public int getNumMoves() {
		return numMoves;
	}


	/* (non-Javadoc)
	 * @see sprite.Movable#move(int, int)
	 */
	@Override
	public void move(int row, int col) {
		this.row = row;
		this.col = col;
		this.numMoves++;
	}

}
