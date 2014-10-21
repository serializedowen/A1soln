package mazegame;

import java.io.IOException;

import exception.UnsupportedLayoutFileException;
import ui.GUI;
import ui.TextUI;
import ui.UI;

public class Play {

    public static void main(String[] args) throws IOException{
         
        MazeGame game;
        
		try {
			game = new MazeGame(MazeConstants.FILENAME);
		} catch (UnsupportedLayoutFileException e) {	
			return;
		}
		
        UI gameUI;
        
        if (MazeConstants.UI_TYPE.equals("text")) {
            gameUI = new TextUI(game);
        }
        else {
            gameUI = new GUI(game);
        }
        
        gameUI.launchGame(); 
        gameUI.displayWinner();
    }
}
