package no.vestein.sokoban.fxml;

import java.io.FileNotFoundException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Labeled;
import no.vestein.sokoban.SokobanScene;
import no.vestein.sokoban.leveleditor.FileHandler;

public class LevelSelectViewController {
	
	@FXML void customLevelButtonPressed() throws FileNotFoundException {
		char[][] level = FileHandler.loadDialog();
		SokobanScene.startGame(level);
	}
	
	@FXML void levelButtonPressed(ActionEvent actionEvent) throws FileNotFoundException {
		String filename = ((Labeled) actionEvent.getSource()).getText().toLowerCase().replaceAll("\\s", "");
		SokobanScene.startGame(FileHandler.loadLevel(filename));
	}
	
}
