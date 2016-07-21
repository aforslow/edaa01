package phonebook;


import java.util.Iterator;
import java.util.LinkedList;
import java.util.Optional;
import java.util.Set;

import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.util.Callback;

public class PhoneBookGUI{
	private PhoneBook phoneBook;
	private BorderPane root;
	private TextArea textArea;
	
	public PhoneBookGUI(PhoneBook pb) {
		phoneBook = pb;

		textArea = new TextArea();
		textArea.setEditable(false);
		textArea.setWrapText(true);
		textArea.setPrefColumnCount(50);
		textArea.setPrefRowCount(20);
		
		root = new BorderPane();
		root.setTop(makeMenu());
		root.setCenter(textArea);
		root.setBottom(bottomMenu());
		
	}
	
	public Parent getRoot(){
		return root;
	}

	
	private MenuBar makeMenu(){
		final Menu menuEdit = new Menu("Edit");
		final MenuItem menuRemoveNumber = new MenuItem("Remove Number");
		menuRemoveNumber.setOnAction(e -> removeNumber());
		final MenuItem menuAdd = new MenuItem("Add");
		menuAdd.setOnAction(e -> add());
		final MenuItem menuRemove = new MenuItem("Remove Person");
		menuRemove.setOnAction(e -> remove());
		menuEdit.getItems().addAll(menuAdd, menuRemove, menuRemoveNumber);
	
		final Menu menuFind = new Menu("Find");
		final MenuItem menuFindNums = new MenuItem("Find Number(s)");
		menuFindNums.setOnAction(e -> findNumbers());
		final MenuItem menuFindNames = new MenuItem("Find Name(s)");
		menuFindNames.setOnAction(e -> findNames());
		menuFind.getItems().addAll(menuFindNums, menuFindNames);
		
		final Menu menuView = new Menu("View");
		final MenuItem menuShowAll = new MenuItem("Show all");
		menuShowAll.setOnAction(e -> showAll());
		menuView.getItems().addAll(menuShowAll);
	    MenuBar menuBar = new MenuBar();
	    menuBar.getMenus().addAll(menuEdit, menuFind, menuView);
	    
	    
	 // menuBar.setUseSystemMenuBar(true);  // if you want operating system rendered menus, uncomment this line
		return menuBar;
	}
	
	private Button bottomMenu() {
		Button menuQuit = new Button("Quit");
		menuQuit.setOnAction(e -> quit());
//		menuQuit.addActionListener(e -> quit());
//		MenuBar bottomMenu = new MenuBar();
//		bottomMenu.getMenus().addAll(menuQuit);
		return menuQuit;
	}
	
	private void quit() {
//		Platform.exit();
		Platform.exit();
	}
	private void showAll() {
		textArea.appendText(phoneBook.toString() + "\n");
	}
	private void findNames() {
		Optional<String> a = oneInputDialog("Find Name", "Enter number to see its names in phone book", "Number");
		
		if (a.isPresent()){
			String number = a.get();
			Set<String> names = phoneBook.findNames(number);
			Iterator<String> namelst = names.iterator();
			String str = "Names associated with number: \n";
			while(namelst.hasNext()){
				str += namelst.next() + "\n";
			}
			textArea.appendText(str);
		}
	}
	private void findNumbers() {
		Optional<String> a = oneInputDialog("Find number", "Enter name to see numbers", "Name");
		if (a.isPresent()){
			String name = a.get();
			Set<String> numbers = phoneBook.findNumber(name);
			Iterator<String> itr = numbers.iterator();
			String str = "Numbers associated with name: \n";
			while(itr.hasNext()){
				str += itr.next() + "\n";
			}
			textArea.appendText(str);
		}
	}

	private void removeNumber() {
		String[] lblstr = new String[2];
		lblstr[0] = "Name";
		lblstr[1] = "Number";
		Optional<String[]> a = twoInputsDialog("Remove number", "Enter number to remove", lblstr);
		
		if (a.isPresent()){
			String[] removelst = a.get();
			String name = removelst[0];
			String number = removelst[1];
			String str = "Removed " + number + " from list \n";
			phoneBook.removeNumber(name, number);
			textArea.appendText(str);
		}
	}

	private void add() {	
		String[] str = new String[2];
		str[0] = "Namn";
		str[1] = "Nummer";
		Optional<String[]> a = twoInputsDialog("Add number", "Adds a number for person", str);
		
		
		if (a.isPresent()){
			String[] input = a.get();
			String name = input[0];
			String nbr = input[1];
			//Put input name into phone book
			phoneBook.put(name, nbr);
			Set<String> nbrList = phoneBook.findNumber(name);
			Iterator<String> itr = nbrList.iterator();
			String output = "Added: " + "\n" + name + "\n";
			while (itr.hasNext()){
				output += itr.next() + "\n";
			}
			output += "\n";
			textArea.appendText(output);
		}
	}
	
	private void remove() {
		Optional<String> a = oneInputDialog("Remove person", "Removes person from phone book", "Name");
		if (a.isPresent()){
			String name = a.get();
			String str = "Removed " + name + " from phone book \n";
			phoneBook.remove(name);
			textArea.appendText(str);
		}
	}
	
	private Optional<String> oneInputDialog(String title, String headerText, String label) {
		TextInputDialog dialog = new TextInputDialog();
		// add content to dialog
		dialog.setTitle(title);
		dialog.setHeaderText(headerText);
		dialog.setResizable(true);
		dialog.setContentText("Name: ");
		Optional<String> result = dialog.showAndWait();
		String input = null;
		if (result.isPresent()){
			input = result.get();
		}
		return Optional.ofNullable(input);
	}
	
	private Optional<String[]> twoInputsDialog(String title, String headerText, String[] labels) {
		Dialog<String> dialog = new Dialog<>();
		dialog.setTitle(title);
		dialog.setHeaderText(headerText);
		dialog.setResizable(true);
		Label label1 = new Label(labels[0] + ':');
		Label label2 = new Label(labels[1] + ':');
		TextField tf1 = new TextField();
		TextField tf2 = new TextField();
		GridPane grid = new GridPane();
		grid.add(label1, 1, 1);
		grid.add(tf1, 2, 1);
		grid.add(label2, 1, 2);
		grid.add(tf2, 2, 2);
		dialog.getDialogPane().setContent(grid);
		ButtonType buttonTypeOk = new ButtonType("Ok", ButtonData.OK_DONE);
		ButtonType buttonTypeCancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
		dialog.getDialogPane().getButtonTypes().addAll(buttonTypeCancel, buttonTypeOk);
		dialog.setResultConverter(new Callback<ButtonType, String>() {
			@Override
			public String call(ButtonType b) {
				String inputs = null;
				if (b == buttonTypeOk) {
					inputs = tf1.getText() + ":" + tf2.getText();
					
				}
				return inputs;
			}
		});
		tf1.requestFocus();

		Optional<String> result = dialog.showAndWait();
		String[] input = null;
		if (result.isPresent()) {
			input = result.get().split(":");
		}
		return Optional.ofNullable(input);
	}
}
