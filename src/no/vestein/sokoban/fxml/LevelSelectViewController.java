package no.vestein.sokoban.fxml;

import javafx.fxml.FXML;
import no.vestein.sokoban.SokobanScene;
import no.vestein.sokoban.map.levels.Levels;

public class LevelSelectViewController {

	@FXML
	private void levelButtonPressedLevel1() {
		SokobanScene.startGame(Levels.level);
	}
	
	@FXML
	private void levelButtonPressedLevel2() {
		SokobanScene.startGame(Levels.level2);
	}
	
	@FXML
	private void levelButtonPressedLevel3() {
		SokobanScene.startGame(Levels.level3);
	}
	
}
