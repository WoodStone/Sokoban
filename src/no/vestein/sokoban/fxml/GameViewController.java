package no.vestein.sokoban.fxml;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Text;
import no.vestein.sokoban.Sokoban;

public class GameViewController {
	
	private Polygon forward;
	private Polygon backward;
	
	public void postInit() {
		Sokoban.gameView.setOnKeyPressed(keyevent -> {
			Sokoban.board.getMoveController().checkKeyPressed(keyevent);
		});
		
		forward.setOnMouseClicked(mouseevent -> {
			Sokoban.board.getMoveController().forward();
		});
		
		backward.setOnMouseClicked(mouseevent -> {
			Sokoban.board.getMoveController().backward();
		});
		
		Sokoban.gameView.getChildren().addAll(forward, backward);
	}
	
	@FXML
	private void initialize() {
		makeTri();
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
	
	
	private void makeTri() {
		forward = new Polygon();
		forward.getPoints().addAll(new Double[] {
				0.0, 0.0,
				0.0, 30.0,
				30.0, 15.0
			});
		
		backward = new Polygon();
		backward.getPoints().addAll(new Double[] {
				30.0, 0.0,
				30.0, 30.0,
				0.0, 15.0
			});
		
		forward.setLayoutX(650);
		forward.setLayoutY(15);
		forward.setFill(Color.GREEN);
		
		backward.setLayoutX(600);
		backward.setLayoutY(15);
		backward.setFill(Color.GREEN);
		
		setEvent(forward);
		setEvent(backward);
	}
	
	private void setEvent(Polygon poly) {
		poly.setOnMouseEntered(mouseevent -> {
			poly.setFill(Color.LIGHTGREEN);
		});
		poly.setOnMouseExited(mouseevent -> {
			poly.setFill(Color.GREEN);
		});
	}
	
}
