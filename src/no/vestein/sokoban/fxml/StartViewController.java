package no.vestein.sokoban.fxml;

import no.vestein.sokoban.SokobanScene;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class StartViewController {
	
	@FXML
	private void startButtonPressed() {
		SokobanScene.levelSelect();
	}
	
	@FXML
	private void levelEditorButtonPressed() {
		SokobanScene.levelEditor();
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
