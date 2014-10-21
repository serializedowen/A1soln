package mazegame;

//Final
public interface Grid<T> {
	void setCell(int row, int col, T item);
	T getCell(int row, int col);
	int getNumRows();
	int getNumCols();
	boolean equals(Grid<T> other);
	String toString();

}
