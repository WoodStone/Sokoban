package no.vestein.sokoban.fxml;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import no.vestein.sokoban.SokobanScene;
import no.vestein.sokoban.map.Level;
import no.vestein.sokoban.map.Movement;

public class GameViewController {
	
	public static void init() {
		
		SokobanScene.gameView.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent keyevent) {
				if (keyevent.getCode() == KeyCode.A) {
					System.out.println("test");
				}
				Movement.checkKeyPressed(keyevent);
			}
		});
		
	}
	
	@FXML
	public void menuButtonPressed() {
		SokobanScene.initStart();
	}
	
	@FXML
	public void restartButtonPressed() {
		SokobanScene.startGame(Level.getSelectedLevel());
	}

}
