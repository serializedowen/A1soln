package sprite;

public class Banana extends Sprite {
	private int value;
	
	public Banana(char symbol, int row, int col, int value) {
		super(symbol, row, col);
		this.value = value;
	}

	public int getValue() {
		return value;
	}


}
