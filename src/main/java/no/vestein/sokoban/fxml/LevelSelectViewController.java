package no.vestein.sokoban.fxml;

import java.io.FileNotFoundException;

import javafx.fxml.FXML;
import javafx.scene.Group;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import no.vestein.sokoban.Sokoban;
import no.vestein.sokoban.util.FileHandler;
import no.vestein.sokoban.util.IFileHandler;

public class LevelSelectViewController {
	
	private IFileHandler<char[][]> fileHandler = new FileHandler();
	
	public void postInit() {
		makeButtons();
	}
	
	@FXML
	public void backButtonPressed() {
		Sokoban.initStart();
	}
	
	@FXML
	public void customLevelButtonPressed() throws FileNotFoundException {
		char[][] level = fileHandler.loadDialog(Sokoban.primaryStage);
		Sokoban.startGame(level);
	}
	
	@FXML 
	public void levelButtonPressed(MouseEvent mouseEvent) throws FileNotFoundException {
		String filename = ((Text) mouseEvent.getSource()).getText().toLowerCase().replaceAll("\\s", "");
		Sokoban.startGame(new FileHandler().loadLevel(filename));
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
	
	private void makeButtons() {
		Group group = new Group();
		int startx = 130;
		int starty = 100;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				LevelSelectStackPane pane = new LevelSelectStackPane(startx, starty, j, i);
				setEvent(pane);
				group.getChildren().add(pane);
			}
		}
		Sokoban.levelSelectView.getChildren().add(group);
	}
	
	private void setEvent(LevelSelectStackPane rect) {
		rect.setOnMouseClicked(mouseEvent -> {
			int levelnumber = ((LevelSelectStackPane) mouseEvent.getSource()).getTag();
			String filename = "level" + Integer.toString(levelnumber);
			try  {
				Sokoban.startGame(fileHandler.loadLevel(filename));
			} catch (FileNotFoundException e) {
				// TODO: add popup!!
			}
			
		});
	}
	
}
