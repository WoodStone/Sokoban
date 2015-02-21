package no.vestein.sokoban.fxml;

import java.io.FileNotFoundException;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import no.vestein.sokoban.Sokoban;
import no.vestein.sokoban.util.FileHandler;

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
		Sokoban.initStart();
	}
	
	@FXML
	public void savePressed() throws FileNotFoundException {
		FileHandler.saveDialog(Sokoban.levelEditor.getLoadedLevel());
	}
	
	@FXML
	public void loadPressed() throws FileNotFoundException {
		char[][] level = FileHandler.loadDialog();
		Sokoban.levelEditor.load(level);
	}
}
