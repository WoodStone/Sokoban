package no.vestein.sokoban.board;

import java.io.FileNotFoundException;
import java.util.Arrays;

import no.vestein.sokoban.Reference;
import no.vestein.sokoban.Sokoban;
import no.vestein.sokoban.blocks.Block;
import no.vestein.sokoban.blocks.BlockBox;
import no.vestein.sokoban.blocks.BlockGoal;
import no.vestein.sokoban.blocks.BlockPlayer;
import no.vestein.sokoban.blocks.BlockWall;
import no.vestein.sokoban.util.FileHandler;
import no.vestein.sokoban.util.IFileHandler;
import javafx.geometry.Pos;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class BoardGraphics {

	private Polygon forward;
	private Polygon backward;
	private VText movesAndPushes;
	private Text save;
	private Text load;
	private Board board;
	private MoveController moveController;
	
	public BoardGraphics(Board board, MoveController moveController) {
		this.board = board;
		this.moveController = moveController;
		
		makeTri();
		makeSaveButton();
		Sokoban.gameView.getChildren().addAll(forward, backward, save, load);
	}
	
	private void makeSaveButton() {
		save = new VText(Color.DARKBLUE, Color.BLUE, 25, 185, 40, "Save");
		
		save.setOnMouseClicked(mouseEvent -> {
			saveGame();
		});
		
		load = new VText(Color.DARKBLUE, Color.BLUE, 25, 255, 40, "Load");
		
		load.setOnMouseClicked(mouseEvent -> {
			loadGame();
		});
	}
	
	private void loadGame() {
		IFileHandler<char[][]> fileHandler = new FileHandler();
		char[][] level = fileHandler.loadDialog(Sokoban.primaryStage);
		Sokoban.startGame(level);
	}
	
	private void saveGame() {
		char[][] level = new char[20][20];
		for (char[] a : level) {
			Arrays.fill(a, '_');
		}
		for (Block block : board.getObjectMap().values()) {
			if (block instanceof BlockWall) {
				block = (BlockWall) block;
				int xpos = block.getXPosition();
				int ypos = block.getYPosition();
				
				level[ypos][xpos] = '#';
			} else if (block instanceof BlockGoal) {
				block = (BlockGoal) block;
				int xpos = block.getXPosition();
				int ypos = block.getYPosition();
				
				level[ypos][xpos] = '.';
			}
		}
		
		for (Block block : board.getObjectMap().values()) {
			if (block instanceof BlockBox) {
				block = (BlockBox) block;
				int xpos = block.getXPosition();
				int ypos = block.getYPosition();
				
				if (level[ypos][xpos] == '.') {
					level[ypos][xpos] = '*';
				} else {
					level[ypos][xpos] = '$';
				}
			}
		}
		
		BlockPlayer player = board.getPlayer();
		int xpos = player.getXPosition();
		int ypos = player.getYPosition();
		
		if (level[ypos][xpos] == '.') {
			level[ypos][xpos] = '+';
		} else {
			level[ypos][xpos] = '@';
		}
		
		IFileHandler<char[][]> fileHandler = new FileHandler();
		
		try {
			fileHandler.saveDialog(level, Sokoban.primaryStage);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	private void makeTri() {
		forward = new Triangle(0, 0, 0, 30, 30, 15, 650, 15);
		forward.setOnMouseClicked(mouseevent -> {
			moveController.forward();
		});
		
		backward = new Triangle(30, 0, 30, 30, 0, 15, 600, 15);
		backward.setOnMouseClicked(mouseevent -> {
			moveController.backward();
		});
	}
	
	public void makeTextMovesAndPushes(AnchorPane gameView) {
		movesAndPushes = new VText(Color.GREENYELLOW, 30, 495, 40, moveController.nMoves() + "/" + moveController.nBlockPushes());
		movesAndPushes.setEffect(new DropShadow(5, Color.GREEN));
		
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
			
			VText victory = new VText(Color.AQUA, 70, 0, -30, Reference.STRING_VICTORY, new DropShadow());
			VText nMoves = new VText(Color.AQUAMARINE, 50, 0, 40, "Moves: " + moveController.nMoves(), new DropShadow());
			VText nPushes = new VText(Color.AQUAMARINE, 50, 0, 100, "Pushes: " + moveController.nBlockPushes(), new DropShadow());
			
			pane.getChildren().addAll(rect, victory, nMoves, nPushes);
			pane.setAlignment(Pos.CENTER);
			
			gameView.getChildren().add(pane);
			pane.requestFocus();
		}
	}

}
