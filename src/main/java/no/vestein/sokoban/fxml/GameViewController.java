package no.vestein.sokoban.fxml;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import no.vestein.sokoban.Sokoban;

public class GameViewController {
	
	public void postInit() {
		Sokoban.gameView.setOnKeyPressed(keyevent -> {
			Sokoban.board.getMoveController().checkKeyPressed(keyevent);
		});
	}
	
	@FXML
	public void menuButtonPressed() {
		Sokoban.initStart();
	}
	
	@FXML
	public void restartButtonPressed() {
		Sokoban.startGame(Sokoban.board.getSelectedLevel());
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
