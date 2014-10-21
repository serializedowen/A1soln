package mazegame;

import java.io.IOException;

import exception.UnsupportedLayoutFileException;

public class playGround {

	public static void main(String[] args) throws IOException, UnsupportedLayoutFileException{
		  
		
        MazeGame game = new MazeGame(MazeConstants.FILENAME);
        System.out.println(game.getMaze().toString());
        
        game.move(MazeConstants.P2_LEFT);
        System.out.println(game.getMaze().toString());
        game.move(MazeConstants.P2_LEFT);
        System.out.println(game.getMaze().toString());
        game.move(MazeConstants.P2_DOWN);
        System.out.println(game.getMaze().toString());
        game.move(MazeConstants.P2_LEFT);
        System.out.println(game.getMaze().toString());
        game.move(MazeConstants.P2_LEFT);
        System.out.println(game.getMaze().toString());
        game.move(MazeConstants.P2_UP);
        System.out.println(game.getMaze().toString());
        game.move(MazeConstants.P2_LEFT);
        System.out.println(game.getMaze().toString());
        game.move(MazeConstants.P2_LEFT);
        System.out.println(game.getMaze().toString());
        game.move(MazeConstants.P2_LEFT);
        System.out.println(game.getMaze().toString());
        
     //   game.move(MazeConstants.P1_RIGHT);
        System.out.println(game.getMaze().toString());
        System.out.println("number moves " + game.getPlayerTwo().getNumMoves());
        System.out.println("score" + game.getPlayerTwo().getScore());
        System.out.println("coordinate" + game.getPlayerTwo().getRow() + game.getPlayerTwo().getCol());
	}

}
