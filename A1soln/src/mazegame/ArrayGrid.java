package mazegame;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArrayGrid<T> implements Grid<T> {
	private int NumRows;
	private int NumCols;
	private Map<Integer, List<T>> data = new HashMap<Integer, List<T>>();
	
	public ArrayGrid(int numRows, int numCols){
		this.NumCols = numCols;
		this.NumRows = numRows;
		
		
		//Construct the ArrayGrid. 
		for (int row = 0; row < this.getNumRows(); row++){
			List<T> initial = new ArrayList<T>();   
			data.put(row, initial);
		}
	}
	

	private void initializeCell(int row, T item){
		this.data.get(row).add(item);
	}

	@Override
	public void setCell(int row, int col, T item) {
		if (data.get(row).size() <= col){
			initializeCell(row, item);
		}else{
			this.data.get(row).set(col, item);
		}
	}

	@Override
	public T getCell(int row, int col) {
		return this.data.get(row).get(col);
	}

	@Override
	public int getNumRows() {
		return this.NumRows;
	}

	@Override
	public int getNumCols() {
		return this.NumCols;
	}

	@Override
	public boolean equals(Grid<T> other) {
		if (this.toString().equals(other.toString())){
			return true;
		} else{
			return false;
		}
	}

	@Override
	public String toString(){
		String result = "";
		int wallcount = 0;
		for (int row = 0; row < this.getNumRows(); row++){
			String lineData = "";
			for (int col = 0; col < this.getNumCols(); col++){
				if (this.getCell(row, col).toString().equals("X")){
					wallcount++;	
				}			
				lineData += this.getCell(row, col).toString();		
			}
			lineData += "\n";
			result += lineData;
		}
		System.out.println("wallcount2 = " + wallcount);
		return result;
	}
}
