
import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	private SudokuSolver ss; 
//	private Stage primaryStage = new Stage();
	
	public static void main(String[] args){
		Application.launch(args);
	}
	
	/**
	 * Starts the program for the user, with the use of SudokuGUI
	 * @param primaryStage	Stage object for the program window
	 */
	@Override
	public void start(Stage primaryStage) {
		ss = new SudokuSolver();
		SudokuGUI gui = new SudokuGUI(ss);
		Scene scene = new Scene(gui.getRoot());
		
		primaryStage.setTitle("Sudoku");
		primaryStage.setScene(scene);
		primaryStage.show();
//		this.primaryStage = primaryStage;
	}
	
	/**
	 * Exits the program whenever the GUI gives this command
	 */
	@Override
	public void stop() {
		System.exit(0);
	}
}
