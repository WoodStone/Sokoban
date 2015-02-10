package no.vestein.sokoban.leveleditor;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Scanner;

import no.vestein.sokoban.Main;
import no.vestein.sokoban.SokobanScene;
import javafx.stage.FileChooser;

public class FileHandler {

	public static void saveDialog(char[][] level) throws FileNotFoundException {
		String filepath = System.getProperty("user.home");
//		FilePermission perm = new FilePermission(filepath, "write");
		
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Save Level File");
		fileChooser.setInitialDirectory(new File(filepath));

		File file = fileChooser.showSaveDialog(SokobanScene.primaryStage);
		
		PrintWriter output = new PrintWriter(new FileOutputStream(file));
		for (char[] ylevel : level) {
			for (char xlevel : ylevel) {
				output.print(xlevel);
			}
			output.println("");
		}
		output.close();
		
	}
	
	public static char[][] loadDialog() throws FileNotFoundException {
		String filepath = System.getProperty("user.home");
//		FilePermission perm = new FilePermission(filepath, "read");
		
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Open Level File");
		fileChooser.setInitialDirectory(new File(filepath));
		
		File file = fileChooser.showOpenDialog(SokobanScene.primaryStage);
		System.out.println(file.getPath());

		return readLevelFile(file);
	}
	
	public static char[][] loadLevel(String filename) throws FileNotFoundException {
		InputStream io = Main.class.getResourceAsStream("/no/vestein/sokoban/map/levels/" + filename);
		return readLevelFile(io);
	}
	
	public static char[][] readLevelFile(InputStream io) throws FileNotFoundException {
		Scanner scanner = new Scanner(io);
		return readFile(scanner);
	}
	
	public static char[][] readLevelFile(File file) throws FileNotFoundException {
		Scanner scanner = new Scanner(file);
		return readFile(scanner);
	}
	
	private static char[][] readFile(Scanner scanner) {
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
