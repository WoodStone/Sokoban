package no.vestein.sokoban.fxml;

import no.vestein.sokoban.Main;
import javafx.fxml.FXML;

public class StartViewController {
	
	@FXML
	private void startButtonPressed() {
		System.out.println("hei");
		Main.initGame();
	}

}
