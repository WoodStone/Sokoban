package no.vestein.sokoban.fxml;

import java.io.FileNotFoundException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Labeled;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import no.vestein.sokoban.SokobanScene;
import no.vestein.sokoban.leveleditor.FileHandler;

public class LevelSelectViewController {
	
	@FXML void backButtonPressed() {
		SokobanScene.initStart();
	}
	
	@FXML void customLevelButtonPressed() throws FileNotFoundException {
		char[][] level = FileHandler.loadDialog();
		SokobanScene.startGame(level);
	}
	
	@FXML void levelButtonPressed(MouseEvent mouseEvent) throws FileNotFoundException {
		String filename = ((Text) mouseEvent.getSource()).getText().toLowerCase().replaceAll("\\s", "");
		SokobanScene.startGame(FileHandler.loadLevel(filename));
	}
	
	@FXML
	public void mouseEntered(MouseEvent mouseEvent) {
		Text text = (Text) mouseEvent.getSource();
		text.setFill(Color.AQUA);
	}
	
	@FXML
	public void mouseExited(MouseEvent mouseEvent) {
		Text text = (Text) mouseEvent.getSource();
		text.setFill(Color.LIGHTBLUE);
	}
	
}
