package no.vestein.sokoban.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Scanner;

import no.vestein.sokoban.Main;
import no.vestein.sokoban.Sokoban;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class FileHandler implements IFileHandler<char[][]> {
	
	public void saveDialog(char[][] level, Stage stage) throws FileNotFoundException {
		String filepath = System.getProperty("user.home");
		
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Save Level File");
		fileChooser.setInitialDirectory(new File(filepath));

		File file = fileChooser.showSaveDialog(Sokoban.primaryStage);
		
		PrintWriter output = new PrintWriter(new FileOutputStream(file));
		for (char[] ylevel : level) {
			for (char xlevel : ylevel) {
				output.print(xlevel);
			}
			output.println("");
		}
		output.close();
	}
	
	public char[][] loadDialog(Stage stage) {
		String filepath = System.getProperty("user.home");
		
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Level File");
		fileChooser.setInitialDirectory(new File(filepath));
		
		File file = fileChooser.showOpenDialog(Sokoban.primaryStage);

		try {
			return readLevelFile(file);
		} catch (FileNotFoundException e) {
			return null;
		}
		
	}
	
	public char[][] loadLevel(String filename) throws FileNotFoundException {
		InputStream io = Main.class.getResourceAsStream("/assets/levels/" + filename);
		return readLevelFile(io);
	}
	
	public void saveLevel(char[][] level) {
		
	}
	
	private char[][] readLevelFile(InputStream io) throws FileNotFoundException {
		Scanner scanner = new Scanner(io);
		return readFile(scanner);
	}
	
	private char[][] readLevelFile(File file) throws FileNotFoundException {
		Scanner scanner = new Scanner(file);
		return readFile(scanner);
	}
	
	private char[][] readFile(Scanner scanner) {
		char[][] newLevel = new char[20][20];
		int yPos = 0;
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			for (int i = 0; i < line.length(); i++) {
				newLevel[yPos][i] = line.charAt(i);
			}
			yPos++;
		}
		scanner.close();
		return newLevel;
	}

}
