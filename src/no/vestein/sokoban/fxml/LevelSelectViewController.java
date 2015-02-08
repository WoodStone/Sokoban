package no.vestein.sokoban.fxml;

import java.io.FileNotFoundException;

import javafx.fxml.FXML;
import no.vestein.sokoban.SokobanScene;
import no.vestein.sokoban.leveleditor.FileHandler;
import no.vestein.sokoban.map.levels.Levels;

public class LevelSelectViewController {

	@FXML
	private void levelButtonPressedLevel1() {
		SokobanScene.startGame(Levels.level1);
	}
	
	@FXML
	private void levelButtonPressedLevel2() {
		SokobanScene.startGame(Levels.level2);
	}
	
	@FXML
	private void levelButtonPressedLevel3() {
		SokobanScene.startGame(Levels.level3);
	}
	
	@FXML void customLevelButtonPressed() throws FileNotFoundException {
		char[][] level = FileHandler.load();
		SokobanScene.startGame(level);
	}
	
}
