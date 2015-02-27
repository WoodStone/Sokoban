package no.vestein.sokoban.fxml;

import java.io.FileNotFoundException;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import no.vestein.sokoban.Sokoban;
import no.vestein.sokoban.util.FileHandler;
import no.vestein.sokoban.util.IFileHandler;

public class LevelEditorViewController {
	
	private IFileHandler<char[][]> fileHandler = new FileHandler();
	
	@FXML
	public void mouseEntered(MouseEvent mouseEvent) {
		((Text)mouseEvent.getSource()).setFill(Color.RED);
	}
	
	@FXML
	public void mouseExited(MouseEvent mouseEvent) {
		((Text)mouseEvent.getSource()).setFill(Color.BLACK);
	}
	
	@FXML
	public void returnPressed() {
		Sokoban.initStart();
	}
	
	@FXML
	public void savePressed() throws FileNotFoundException {
		new FileHandler().saveDialog(Sokoban.levelEditor.getLoadedLevel(), Sokoban.primaryStage);
	}
	
	@FXML
	public void loadPressed() throws FileNotFoundException {
		char[][] level = fileHandler.loadDialog(Sokoban.primaryStage);
		Sokoban.levelEditor.load(level);
	}
}
