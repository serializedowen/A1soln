/**
 * 
 */
package ui;

import javax.swing.JFrame;
import mazegame.MazeGame;

public class TextUI extends JFrame implements UI {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1699060768394946920L;
	private MazeGame game;
	
	public TextUI(MazeGame game){
		this.game = game;
	}
	
	public MazeGame getGame() {
		return game;
	}

	public void setGame(MazeGame game) {
		this.game = game;
	}

	/* (non-Javadoc)
	 * @see ui.UI#launchGame()
	 */
	@Override
	public void launchGame() {
        TextUIListener listener = new TextUIListener(this);
        this.addKeyListener(listener);
        this.getGame().getMaze().toString();

	}


    /** Update the grid display. */
    public void updateLabels() {
    	System.out.println(this.getGame().getMaze().toString());
    	this.displayWinner();
    }
	
	/* (non-Javadoc)
	 * @see ui.UI#displayWinner()
	 */
	@Override
	public void displayWinner() {

        int won = game.hasWon();        
        //int moves = 0;
        String message;
        
        if (game.isBlocked()) { // no winners
            message = "Game over! Both players are stuck.";
        } else {
            if (won == 0) { // game is still on
                return;
            } else if (won == 1) {
                message = "Congratulations Player 1! You won the maze in " + 
                          game.getPlayerOne().getNumMoves() + " moves.";
            } else if (won == 2) { 
                message = "Congratulations Player 2! You won the maze in " + 
                          game.getPlayerTwo().getNumMoves() + " moves.";
            } else { // it's a tie
                message = "It's a tie!";
            }
        }
        System.out.println(message);
	}

}
