package no.vestein.sokoban.board;

import no.vestein.sokoban.Reference;
import no.vestein.sokoban.Sokoban;
import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class BoardGraphics {

	private Polygon forward;
	private Polygon backward;
	private Text movesAndPushes;
	private Board board;
	private MoveController moveController;
	
	public BoardGraphics(Board board, MoveController moveController) {
		this.board = board;
		this.moveController = moveController;
		
		makeTri();
		Sokoban.gameView.getChildren().addAll(forward, backward);
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
		forward.setEffect(new DropShadow(5, Color.DARKGREEN));
		forward.setOnMouseClicked(mouseevent -> {
			moveController.forward();
		});
		
		backward.setLayoutX(600);
		backward.setLayoutY(15);
		backward.setFill(Color.GREEN);
		backward.setEffect(new DropShadow(10, Color.DARKGREEN));
		backward.setOnMouseClicked(mouseevent -> {
			moveController.backward();
		});
		
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
	
	public void makeTextMovesAndPushes(AnchorPane gameView) {
		movesAndPushes = new Text();
		movesAndPushes.setText(moveController.nMoves() + "/" + moveController.nBlockPushes());
		
		movesAndPushes.setFont(Font.font(30));
		movesAndPushes.setFill(Color.GREENYELLOW);
		movesAndPushes.setEffect(new DropShadow(5, Color.GREEN));
		movesAndPushes.setLayoutX(495);
		movesAndPushes.setLayoutY(40);
		
		gameView.getChildren().add(movesAndPushes);
	}
	
	public void updateText() {
		movesAndPushes.setText(moveController.nMoves() + "/" + moveController.nBlockPushes());
	}
	
	public void finishGame(Board board, AnchorPane gameView) {
		if (board.isGameIsDone()) {
			StackPane pane = new StackPane();
			pane.setLayoutX(50);
			pane.setLayoutY(60);
			
			Rectangle rect = new Rectangle(660, 660);
			rect.setTranslateX(-10);
			rect.setTranslateY(-10);
			rect.setFill(Color.rgb(128, 128, 128, 0.50));
			Text victory = new Text(Reference.STRING_VICTORY);
			victory.setFill(Color.AQUA);
			victory.setFont(Font.font(70));
			victory.setEffect(new DropShadow());
			victory.setTranslateY(-30);
			
			Text nMoves = new Text("Moves: " + moveController.nMoves());
			nMoves.setFill(Color.AQUAMARINE);
			nMoves.setFont(Font.font(50));
			nMoves.setEffect(new DropShadow());
			nMoves.setTranslateY(40);
			
			Text nPushes = new Text("Pushes: " + moveController.nBlockPushes());
			nPushes.setFill(Color.AQUAMARINE);
			nPushes.setFont(Font.font(50));
			nPushes.setEffect(new DropShadow());
			nPushes.setTranslateY(100);
			
			
			pane.getChildren().addAll(rect, victory, nMoves, nPushes);
			pane.setAlignment(Pos.CENTER);
			
			gameView.getChildren().add(pane);
			pane.requestFocus();
		}
	}
}
