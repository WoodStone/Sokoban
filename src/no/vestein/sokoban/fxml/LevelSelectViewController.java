package no.vestein.sokoban.fxml;

import java.io.FileNotFoundException;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import no.vestein.sokoban.Sokoban;
import no.vestein.sokoban.leveleditor.FileHandler;

public class LevelSelectViewController {
	
	@FXML void backButtonPressed() {
		Sokoban.initStart();
	}
	
	@FXML void customLevelButtonPressed() throws FileNotFoundException {
		char[][] level = FileHandler.loadDialog();
		Sokoban.startGame(level);
	}
	
	@FXML void levelButtonPressed(MouseEvent mouseEvent) throws FileNotFoundException {
		String filename = ((Text) mouseEvent.getSource()).getText().toLowerCase().replaceAll("\\s", "");
		Sokoban.startGame(FileHandler.loadLevel(filename));
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
