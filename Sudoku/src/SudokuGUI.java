
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;

public class SudokuGUI {
	
		private SudokuSolver sudoku;
		private BorderPane root;
		private TilePane tilepane;
		OneLetterTextField[][] fields = new OneLetterTextField[9][9];
		
		/**
		 * Constructor that creates the GUI for the sudoku solver
		 */
		public SudokuGUI(SudokuSolver sudoku) {
			this.sudoku = sudoku;
			tilepane = new TilePane();
			tilepane.setPrefColumns(9);
			tilepane.setPrefRows(9);
			tilepane.setHgap(3);
			tilepane.setVgap(3);
			tilepane.setAlignment(Pos.CENTER);
			tilepane.setMaxWidth(300);
			tilepane.setMinWidth(300);
			tilepane.setMaxHeight(300);
			tilepane.setMinHeight(300);

			for (int row = 0; row < 9; row++){
				for (int col = 0; col < 9; col++){
					OneLetterTextField tf = new OneLetterTextField();
					int bigBoxNum = row/3 + col/3;
					boolean filledBox = (bigBoxNum % 2) == 0;
					if (filledBox){
						tf.setStyle("-fx-background-color: lightsalmon;");
					}
					fields[row][col] = tf;
					tf.setPrefColumnCount(1);
					tf.setPrefHeight(1);
					tilepane.getChildren().add(tf);
				}
			}
			
			
			root = new BorderPane();
			root.setCenter(tilepane);
			root.setBottom(bottomMenu());
			root.setPrefSize(300,300);
			
		}
		
		/**
		 * Gives the root of the GUI
		 * @return root
		 */
		public Parent getRoot(){
			return root;
		}

		/**
		 * Implements the bottom menu
		 * @return hbButtons Clear and Solve buttons
		 */
		private HBox bottomMenu() {
			Button clear = new Button("Clear");
			clear.setOnAction(e -> clear());
			Button solve = new Button("Solve");
			solve.setOnAction(e -> solve());
			HBox hbButtons = new HBox();
			hbButtons.getChildren().addAll(clear, solve);
			hbButtons.setSpacing(10);
			hbButtons.setPadding(new Insets(15,12,15,12));
			return hbButtons;
		}
		
		/**
		 * Writes out solution matrix on board, if there is a solution, 
		 * else, pops up a dialog window
		 */
		private void solve(){
			for (int row = 0; row < 9; row++){
				for (int col = 0; col < 9; col++){
					OneLetterTextField tf = fields[row][col];
					if (!tf.getText().isEmpty()){
						sudoku.set(Integer.parseInt(tf.getText()), row, col);
					} else{
						sudoku.set(0, row, col);
					}
				}
			}
			if (sudoku.checkMatrix()){
				if (sudoku.solve()){
					for (int row = 0; row < 9; row++){
						for (int col = 0; col < 9; col++){
							OneLetterTextField tf = fields[row][col];
							tf.replaceText(0,0,""+ sudoku.get(row,col));
						}
					}
				} else{
					DialogWindow();
				}
			}
			else{
				DialogWindow();
			}
		}
		
		/**
		 * Clears the board of inputs in solver and in GUI
		 */
		private void clear(){
			for (int row = 0; row < 9; row++){
				for (int col = 0; col < 9; col++){
					OneLetterTextField tf = fields[row][col];
					if (!tf.getText().isEmpty()){
						tf.replaceText(0,1,"");
						sudoku.set(0, row, col);
					}
				}
			}
		}

		/**
		 * Creates a dialog window
		 * @return alertWindow	An alert window is returned to show 
		 * 						the user that no solution exists
		 */
		private Alert DialogWindow(){
			Alert alertWindow = new Alert(AlertType.ERROR);
			alertWindow.setTitle("No solution");
			alertWindow.setHeaderText("No solutions found");
			alertWindow.setContentText("Close and start over");
			alertWindow.setResizable(true);
			alertWindow.showAndWait();
			return alertWindow;
		}
	
}
