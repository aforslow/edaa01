package Sudoku;

public class Sudoku {
	private int[][] grid; 
	
	public Sudoku() {
		grid = new int[9][9];
	}
	
	public Sudoku(int[][] grid) {
		this.grid = grid;
	}
	
	public void set(int i, int j, int value) {
		grid[i][j] = value;
	}
	
	public int get(int i, int j) {
		return grid[i][j];
	}
	
	public boolean solve() { //wrapper method
		return solve(0,0);
	}
	
	private boolean checkNumber(int i, int j, int k) {//kollar om talet k går att skriva in
		int iniRow = (i/3)*3;
		int iniCol = (j/3)*3; // startar i upp-vänster i regionen
		for(int x = 0; x < 9; x++) {
			int nextRow = iniRow +(x/3);
			int nextCol = iniCol + (x%3);
			if((nextRow!=i && nextRow!=j) ) { //region
				if(get(nextRow, nextCol) == k) {
					return false;
				} 
			} 
			if(x!=j || x!=i) {//rad och kolonn
				if(get(i, x) == k || get(x, j) == k) {
					return false;
				}
			}
		}
		return true;
	}
	
	private boolean firstNumber(int i, int j) { //testar 1-9 och ändrar om det funkar
		for(int x = 1; x <= 9; x++) {
			if(checkNumber(i,j,x)) {
				set(i, j, x);
				return true;
			}
		}
		return false;
	}
	
	private boolean solve(int i, int j) {
		int nextCol = j % 8;
		int nextRow = (nextCol == 0) ? j+1 : j;
		if(i > 8) { // utanför löst
			return true;
		} 
		if(get(i ,j) == 0) {//icke-fylld
			return firstNumber(i,j) ? solve(nextRow, nextCol) : false; //om firstnumber true, returneras solve
		} else {//fylld
			return checkNumber(i, j, get(i ,j)) ? solve(nextRow, nextCol) : false;
		}
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < 9; i++) {
			for(int j = 0; j < 9; j++) {
				sb.append(grid[i][j]);
				String temp = (j==2 || j==5) ? "\t": " ";
				sb.append(temp);
			}
			String temp1 = (i==2 || i==5) ? "\n\n": "\n";
			sb.append(temp1);
		}
		return sb.toString();
	}
	
	public static void main(String[] args) {
		Sudoku s = new Sudoku();
		s.set(0, 2, 8);
		s.set(0, 5, 9);
		s.set(0, 7, 6);
		s.set(0, 8, 2);
		s.set(1, 8, 5);
		s.set(2, 0, 1);
		s.set(2, 2, 2);
		s.set(2, 3, 5);
		s.set(3, 3, 2);
		s.set(3, 4, 1);
		s.set(3, 7, 9);
		s.set(4, 1, 5);
		s.set(4, 6, 6);
		s.set(5, 0, 6);
		s.set(5, 7, 2);
		s.set(5, 8, 8);
		s.set(6, 0, 4);
		s.set(6, 1, 1);
		s.set(6, 3, 6);
		s.set(6, 5, 8);
		s.set(7, 0, 8);
		s.set(7, 1, 6);
		s.set(7, 4, 3);
		s.set(7, 6, 1);
		s.set(8, 6, 4);
		System.out.println(s);
		System.out.println();
		if(s.solve()) {
			System.out.println(s);
		} else {
			System.out.println(s);
		}
	}
}
