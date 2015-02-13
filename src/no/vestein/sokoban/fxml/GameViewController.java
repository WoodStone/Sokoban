package no.vestein.sokoban.fxml;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import no.vestein.sokoban.SokobanScene;
import no.vestein.sokoban.level.MoveController;

public class GameViewController {
	
	public static void init() {
		SokobanScene.gameView.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent keyevent) {
				if (keyevent.getCode() == KeyCode.A) {
					System.out.println("test");
				}
				MoveController.checkKeyPressed(keyevent);
			}
		});
	}
	
	@FXML
	public void menuButtonPressed() {
		SokobanScene.initStart();
	}
	
	@FXML
	public void restartButtonPressed() {
		SokobanScene.startGame(SokobanScene.gameLevel.getSelectedLevel());
	}

	@FXML
	public void mouseEntered(MouseEvent mouseEvent) {
		Text text = (Text) mouseEvent.getSource();
		text.setFill(Color.BLUE);
	}
	
	@FXML
	public void mouseExited(MouseEvent mouseEvent) {
		Text text = (Text) mouseEvent.getSource();
		text.setFill(Color.DARKBLUE);
	}
	
}
