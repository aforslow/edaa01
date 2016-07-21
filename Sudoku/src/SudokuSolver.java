import java.util.LinkedList;


public class SudokuSolver {
	private int[][] grid = new int[9][9];
	
	public SudokuSolver(){
		this.grid = new int[9][9];
	}
	
	/**
	 * Sets input value on the suggested sudoku coordinate
	 * @param input input value to sudoku grid
	 * @param row row coordinate
	 * @param col column coordinate
	 */
	public void set(int input, int row, int col){
			grid[row][col] = input;
	}
	
	/**
	 * returns the value on the suggested sudoku coordinate
	 * @param row row coordinate
	 * @param col column coordinate
	 * @return value
	 */
	public int get(int row, int col){
		return grid[row][col];
	}

	/**
	 * Wrapper method to solve
	 * @return true if possible to solve sudoku, else false
	 */
	public boolean solve(){
		return solve(0,0);
	}
	
	/**
	 * Writes out the sudoku in the terminal
	 * @return str The sudoku grid on a string
	 */
	public String toString(){
		String str = "";
		for (int i = 0; i < 9; i++){
			for (int j = 0; j < 9; j++){
				str += grid[i][j] + " ";
//				System.out.print(grid[i][j] + " ");
			}
			str += "\n";
		}
		return str;
	}
	
	/**
	 * Solves the sudoku with recursion
	 * @param row row coordinate
	 * @param col column coordinate
	 * @return true if the sudoku is solved, else false
	 */
	private boolean solve(int row, int col){
		int nextCol = (col + 1) % 9;
		int nextLine = (nextCol == 0) ? row + 1 : row;
		if (row > 8){
			return true;
		}
		if (grid[row][col] == 0){
			for (int k = 1; k <= 9; k++){
				if (!equalsBox(k, row, col) && !equalsLine(k, row, col)){
					set(k, row, col);
					if (solve(nextLine, nextCol)){//if returns true
						return true;
						}
					}
				}
				set(0, row, col);
			}else{ //fylld
				if (!equalsBox(get(row,col),row,col) && 
						!equalsLine(get(row,col),row,col)){
					if (solve(nextLine, nextCol)){
						return true;
					}
				}
			}
		return false;
	}
	
	/**
	 * Checks matrix for faulty values before solve-recursion
	 * @return boolean True if no faulty values, else false
	 */
	public boolean checkMatrix(){
		for (int row = 0; row < 9; row++){
			for (int col = 0; col < 9; col++){
				if (get(row,col) != 0){
					if (equalsLine(get(row, col), row, col) 
							|| equalsBox(get(row,col), row, col)){
						return false;
					}
				}
			}
		}
		return true;
	}
	
	/**
	 * If faulty values in line directions, return true
	 * @param input input value for check
	 * @param row row coordinate
	 * @param col column coordinate
	 * @return equalsLine if input value exists along row or column, return true, 
	 * 					  else false
	 */
	private boolean equalsLine(int input, int row, int col){
		boolean equalsLine = false;
		for (int k = 0; k < 9; k++){
			if (k!=col && grid[row][k] == input){
				equalsLine = true;
			} 
			if (k!=row && grid[k][col] == input ){
				equalsLine = true;
			}
		}
		return equalsLine;
	}
	
	/**
	 * Checks the box list for doublets
	 * @param input input value
	 * @param row row coordinate
	 * @param col column coordinate
	 * @return true if the box contains doublet, else false
	 */
	private boolean equalsBox(int input, int row, int col){
		LinkedList<Integer> box = getBox(row, col);
		return box.contains(input);
	}
	
	/**
	 * Creates list for box we're checking in
	 * @param row row coordinate
	 * @param col column coordinate
	 * @return box A list of box value
	 */
	private LinkedList<Integer> getBox(int row, int col){
		LinkedList<Integer> box = new LinkedList<Integer>();
		for (int k = 0; k < 3; k++){
			for (int l = 0; l < 3; l++){
				if ((k + (row - row%3) != row) && (l + (col - col%3) != col)){
					box.add(grid[k + (row - row%3)][l + (col - col%3)]);
				}
			}
		}		
		return box;
	}
//	
//	private boolean equalsBox1(int input, int i, int j){
//		for (int k = 0; k < 3; k++){
//			for (int l = 0; l < 3; l++){
//				int nextRow = k + (i - i%3);
//				int nextCol = l + (j - j%3);
//				if (nextRow != i && (nextCol != j) && get(nextRow,nextCol) == input){
//					return true;
//				}
//			}
//		}
//		return false;
//	}
	
	public static void main(String[] args){
		SudokuSolver s = new SudokuSolver();
		s.set(1, 0, 0);
		s.set(1,1,1);
		if(s.solve()){
			System.out.println(s.toString());
		}
	}
}

	