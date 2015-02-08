package no.vestein.sokoban.fxml;

import no.vestein.sokoban.SokobanScene;
import javafx.fxml.FXML;

public class StartViewController {
	
	@FXML
	private void startButtonPressed() {
		System.out.println("hei");
		SokobanScene.levelSelect();
	}

}
