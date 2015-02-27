package no.vestein.sokoban.util;

import java.io.FileNotFoundException;

import javafx.stage.Stage;

public interface IFileHandler<T> {
	
	public void saveDialog(T level, Stage stage) throws FileNotFoundException;
	
	public void saveLevel(T level);
	
	public T loadDialog(Stage stage);
	
	public T loadLevel(String filename) throws FileNotFoundException;
	
}
