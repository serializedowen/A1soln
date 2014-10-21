package sprite;

import mazegame.MazeConstants;

public class Wall extends Sprite {
	private static int count = 0;
	
	public Wall(int row, int col) {
		super(MazeConstants.WALL, row, col);
		count++;
	}
	
	public static int getCount(){
		System.out.println("wallconstructed" + count);
		return count;
	}

}
