package no.vestein.sokoban.fxml;

import no.vestein.sokoban.SokobanScene;
import javafx.fxml.FXML;

public class StartViewController {
	
	@FXML
	private void startButtonPressed() {
		SokobanScene.levelSelect();
	}
	
	@FXML
	private void levelEditorButtonPressed() {
		SokobanScene.levelEditor();
	}

}
