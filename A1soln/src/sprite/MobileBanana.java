package sprite;

import mazegame.MazeConstants;

public class MobileBanana extends Banana implements Movable {

	public MobileBanana(char symbol, int row, int col, int value) {
		super(MazeConstants.MOBILE_BANANA, row, col, value);
		
	}

	@Override
	public void move(int row, int col) {
		this.row = row;
		this.col = col;
	}

}
