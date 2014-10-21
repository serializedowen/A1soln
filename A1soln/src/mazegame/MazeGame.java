package mazegame;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import exception.UnsupportedLayoutFileException;
import sprite.*;

/**
 * A class that represents the basic functionality of the maze game.
 * This class is responsible for performing the following operations:
 * 1. At creation, it initializes the instance variables used to store the
 *        current state of the game.
 * 2. When a move is specified, it checks if it is a legal move and makes the
 *        move if it is legal.
 * 3. It reports information about the current state of the game when asked.
 */
public class MazeGame {

    /** A random number generator to move the MobileBananas. */
    private Random random;
    
    /** The maze grid. */
    private Grid<Sprite> maze;
    
    /** The first player. */
    private Monkey player1;
    
    /** The second player. */
    private Monkey player2;

    /** The bananas to eat. */
    private List<Banana> bananas = new ArrayList<Banana>();
    
    /**
     * @param layoutFileName
     * @throws UnsupportedLayoutFileException 
     * @throws UnsupportedCharacterException 
     */
    public MazeGame(String layoutFileName) throws IOException, UnsupportedLayoutFileException {
        random = new Random();
        
        int[] dimensions = getDimensions(layoutFileName);
        maze = new ArrayGrid<Sprite>(dimensions[0], dimensions[1]);
               
        BufferedReader br = new BufferedReader(new FileReader(layoutFileName));
        
        String nextLine;
        Banana bananaToAdd;
        
        int p1Count = 0;
        int p2Count = 0;
        
        for (int row = 0; row < dimensions[0]; row++){
        	
        	nextLine = br.readLine();
        	for (int col = 0; col < dimensions[1]; col++){    //For each column in a single row.
        		switch (nextLine.charAt(col)){
        			case MazeConstants.P1 :
        				player1 = new Monkey(MazeConstants.P1, row, col);
        				maze.setCell(row, col, player1);
        				p1Count++;
        				break;
        			case MazeConstants.P2 :
        				player2 = new Monkey(MazeConstants.P2, row, col);
        				maze.setCell(row, col, player2);
        				p2Count++;
        				break;
        			case MazeConstants.WALL :
        				maze.setCell(row, col, new Wall(row, col));
        				break;
        			case MazeConstants.VACANT :
        				maze.setCell(row, col, new UnvisitedHallWay(row, col));
        				break;
        			case MazeConstants.VISITED :
        				maze.setCell(row, col, new VisitedHallWay(row, col));
        				break;
        			case MazeConstants.BANANA :
        				bananaToAdd = new Banana(MazeConstants.BANANA, row, col, MazeConstants.BANANA_SCORE);
        				maze.setCell(row, col, bananaToAdd);
        				bananas.add(bananaToAdd);
        				break;
        			case MazeConstants.MOBILE_BANANA :
        				bananaToAdd = new MobileBanana(MazeConstants.MOBILE_BANANA, row, col, MazeConstants.MOBILE_BANANA_SCORE);
        				maze.setCell(row, col, bananaToAdd);
        				bananas.add(bananaToAdd);
        				break;
        			default :
        				throw new UnsupportedLayoutFileException("Unsupported character in this file.");
        		}
        	}
        	
        }
        br.close();
        
        if (p1Count != 1 || p2Count != 1){
        	throw new UnsupportedLayoutFileException("Incorrect number of players in this file. ");
        }
        

    }

    public Sprite get(int row, int col){
    	return this.maze.getCell(row, col);
    }
    
    /**
     * Get the total number of rows in game.
     * @return
     */
    public int getNumRows(){
    	return this.maze.getNumRows();
    }
    
    /**
     * Get the total number of columns in game.
     * @return
     */
	public int getNumCols(){
    	return this.maze.getNumCols();
    }
    public Grid<Sprite> getMaze() {
		return maze;
	}

	/**
	 * 
	 * @return An instance of player2.
	 */
	public Monkey getPlayerOne() {
		return player1;
	}

	/**
	 * 
	 * @return An instance of player2.
	 */
	public Monkey getPlayerTwo() {
		return player2;
	}


  
    
    /**
     * [0]numRows [1]numCols
     * @param layoutFileName
     * @return
     * @throws IOException
     */
    private int[] getDimensions(String layoutFileName) throws IOException {       
        
        BufferedReader br = new BufferedReader(new FileReader(layoutFileName));
    
        // find the number of columns
        String nextLine = br.readLine();
        int numCols = nextLine.length();

        int numRows = 0;
    
        // find the number of rows
        while (nextLine != null && nextLine != "") {
            numRows++;
            nextLine = br.readLine();
        }
        
        br.close();
        return new int[]{numRows, numCols};
    }


    
    /**
     * Check whether both players are blocked.
     * @return 
     */
    public boolean isBlocked(){
    	return (this.checkBlocked(player1) && this.checkBlocked(player2));
    }
    
    private boolean checkBlocked(Sprite character){
    	boolean status = true;
    	List<Sprite> neighbors = new ArrayList<Sprite>();
    	int row = character.getRow();
    	int col = character.getCol();

    	//Add neighbors into the list.
    	if(row - 1 >= 0){
    		neighbors.add(this.get(row - 1, col));
    	}
    	
    	if(col - 1 >= 0){
    		neighbors.add(this.get(row, col - 1));
    	}
    	
    	if(row + 1 < this.getNumRows()){
    		neighbors.add(this.get(row + 1, col));
    	}
    	
    	if(col + 1 < this.getNumCols()){
    		neighbors.add(this.get(row, col + 1));
    	}
    	
    	for (Sprite target : neighbors){
    		if(target.getSymbol() != MazeConstants.WALL && target.getSymbol() != MazeConstants.VISITED && target.getSymbol() != MazeConstants.P1 && target.getSymbol() != MazeConstants.P2){
    			status = false;
    		}
    	}
    	return status;
    }
    
    /**
     * Determine whether win condition is met. 
     * @return
     */
    public int hasWon(){
    	
    	//No more bananas.
    	if (this.bananas.size() == 0){
    		if (this.getPlayerOne().getScore() == this.getPlayerTwo().getScore()){
    			return 3;
    		} else if (this.getPlayerOne().getScore() > this.getPlayerTwo().getScore()){
    			return 1;
    		} else {
    			return 2;
    		}
    	} else{
    		return 0;
    	}
    }

    /**
     * Move the grid by specific key event.
     * @param move the key event sent to the game.
     */
	public void move(char move) {
		Sprite destination = new PlaceHolder(-1, -1);
		Monkey character;
		
			//If is a player.
			switch (move){
			case MazeConstants.P1_DOWN :
				character = player1;
				if (character.getRow() + 1 < this.getNumRows()){	
					destination = this.getMaze().getCell(character.getRow() + 1, character.getCol());
				}
				break;
			case MazeConstants.P1_LEFT :
				character = player1;
				if (character.getCol() - 1 >= 0 ){
					destination = this.getMaze().getCell(character.getRow(), character.getCol() - 1);
				}
				break;
			case MazeConstants.P1_RIGHT :
				character = player1;
				if (character.getCol() + 1 < this.getNumCols()){
					destination = this.getMaze().getCell(character.getRow(), character.getCol() + 1);
				}
				break;
			case MazeConstants.P1_UP :
				character = player1;
				if (character.getRow() - 1 >= 0){
					destination = this.getMaze().getCell(character.getRow() - 1, character.getCol());
				}
				break;
			case MazeConstants.P2_DOWN :
				character = player2;
				//System.out.println("sd");    
				if (character.getRow() + 1 < this.getNumRows()){
					destination = this.getMaze().getCell(character.getRow() + 1, character.getCol());
				}
				break;
			case MazeConstants.P2_LEFT :
				character = player2;
				if (character.getCol() - 1 >= 0 ){
					destination = this.getMaze().getCell(character.getRow(), character.getCol() - 1);
				}
				break;
			case MazeConstants.P2_RIGHT :
				character = player2;
				if (character.getCol() + 1 < this.getNumCols()){
					destination = this.getMaze().getCell(character.getRow(), character.getCol() + 1);
				}
				break;
			case MazeConstants.P2_UP :
				character = player2;
				if (character.getRow() - 1 >= 0){
					destination = this.getMaze().getCell(character.getRow() - 1, character.getCol());
				}
				break;	
			default :
				return;
			}

		
		//To move the character.	
		int oldRow = character.getRow();
		int oldCol = character.getCol();
		//System.out.println("destination symbol:" + destination.getSymbol()); 
		switch (destination.getSymbol()){
		case MazeConstants.BANANA :
			
			character.eatBanana(MazeConstants.BANANA_SCORE);
			this.bananas.remove(destination);
			character.move(destination.getRow(), destination.getCol());
			this.getMaze().setCell(destination.getRow(), destination.getCol(), character);
			this.getMaze().setCell(oldRow, oldCol, new VisitedHallWay(oldRow, oldCol));
		

			break;
		case MazeConstants.MOBILE_BANANA :
			character.eatBanana(MazeConstants.MOBILE_BANANA_SCORE);
			this.bananas.remove(destination);
			character.move(destination.getRow(), destination.getCol());
			this.getMaze().setCell(destination.getRow(), destination.getCol(), character);
			this.getMaze().setCell(oldRow, oldCol, new VisitedHallWay(oldRow, oldCol));
			break;
		case MazeConstants.VACANT :
			character.move(destination.getRow(), destination.getCol());
			this.getMaze().setCell(destination.getRow(), destination.getCol(), character);
			this.getMaze().setCell(oldRow, oldCol, new VisitedHallWay(oldRow, oldCol));
			break;
		default :
			return;
		}
		
		//Random Move MobileBanana
		for (int i = 0; i < bananas.size(); i++){
			if(bananas.get(i).getSymbol() == MazeConstants.MOBILE_BANANA){
				int[] ints = {0, 1, 2, 3};
				List<Integer> possibleMoves = new ArrayList<Integer> ();
				for (int toAdd : ints ) possibleMoves.add(toAdd);
				this.moveBanana((MobileBanana)bananas.get(i), possibleMoves);
			}
		}

		
	}
	
	private void moveBanana(MobileBanana banana, List<Integer> moves){
		Sprite destination = new PlaceHolder(-1, -1);
		int move;
		
		//Get a random move from all the possible moves.
		if(moves.size() == 0){
			//No possibleMoves for this banana.
			//System.out.println("banana locked");
			return;
		}else {
			move = moves.remove(this.random.nextInt(moves.size()));
		}
		
		switch (move){
		case 0 :    
			if (banana.getRow() + 1 < this.getNumRows()){
				destination = this.getMaze().getCell(banana.getRow() + 1, banana.getCol());
			}
			break;
		case 1 :
			if (banana.getCol() - 1 >= 0 ){
				destination = this.getMaze().getCell(banana.getRow(), banana.getCol() - 1);
			}
			break;
		case 2 :
			if (banana.getCol() + 1 < this.getNumCols()){
				destination = this.getMaze().getCell(banana.getRow(), banana.getCol() + 1);
			}
			break;
		case 3:
			if (banana.getRow() - 1 >= 0){
				destination = this.getMaze().getCell(banana.getRow() - 1, banana.getCol());
			}
			break;
		}
		
		//Move the banana
		int oldRow = banana.getRow();
		int oldCol = banana.getCol();
		
		if(destination.getSymbol() == MazeConstants.VACANT){
			((MobileBanana)banana).move(destination.getRow(), destination.getCol());
			this.getMaze().setCell(destination.getRow(), destination.getCol(), banana);
			this.getMaze().setCell(oldRow, oldCol, new UnvisitedHallWay(oldRow, oldCol));
		} else {
		
			this.moveBanana(banana, moves);
		}
	}
}
