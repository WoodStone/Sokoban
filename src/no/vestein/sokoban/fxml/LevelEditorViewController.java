package no.vestein.sokoban.fxml;

import java.io.FileNotFoundException;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import no.vestein.sokoban.SokobanScene;
import no.vestein.sokoban.leveleditor.FileHandler;
import no.vestein.sokoban.leveleditor.LevelEditor;

public class LevelEditorViewController {
	
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
		SokobanScene.initStart();
	}
	
	@FXML
	public void savePressed() throws FileNotFoundException {
		FileHandler.save(LevelEditor.level);
	}
	
	@FXML
	public void loadPressed() throws FileNotFoundException {
		char[][] level = FileHandler.load();
		LevelEditor.load(level);
	}
}
