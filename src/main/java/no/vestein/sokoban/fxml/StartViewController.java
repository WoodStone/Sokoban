package no.vestein.sokoban.fxml;

import no.vestein.sokoban.Sokoban;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class StartViewController {
	
	@FXML
	private void startButtonPressed() {
		Sokoban.levelSelect();
	}
	
	@FXML
	private void levelEditorButtonPressed() {
		Sokoban.levelEditor();
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
