package no.vestein.sokoban;

import no.vestein.sokoban.animation.Background;
import no.vestein.sokoban.board.Board;
import no.vestein.sokoban.fxml.GameViewController;
import no.vestein.sokoban.fxml.LevelSelectViewController;
import no.vestein.sokoban.leveleditor.LevelEditor;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Sokoban {

	public static Stage primaryStage;
	public static BorderPane root;
	public static AnchorPane startView;
	public static AnchorPane gameView;
	public static AnchorPane levelSelectView;
	public static AnchorPane levelEditorView;
	public static Scene scene;
	public static Board board;
	public static LevelEditor levelEditor;
	
	public static void initStage(Stage stage) {
		primaryStage = stage;
		primaryStage.setTitle("Sokoban");
	}
	
	public static void initRoot() {
		try {
            root = FXMLLoader.load(Main.class.getResource("/fxml/RootView.fxml"));

			scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			// TODO: handle exception
		}
		Background.init();
	}
	
	public static void initStart() {
		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("/fxml/StartView.fxml"));
//			loader.setLocation(Main.class.getResource("fxml/StartView.fxml"));
			startView = (AnchorPane) loader.load();
			
			root.setCenter(startView);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static void levelSelect() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/fxml/LevelSelectView.fxml"));
			levelSelectView = (AnchorPane) loader.load();
			
			root.setCenter(levelSelectView);
			
			LevelSelectViewController controller = loader.getController();
			controller.postInit();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static void levelEditor() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/fxml/LevelEditorView.fxml"));
			levelEditorView = (AnchorPane) loader.load();
			
			root.setCenter(levelEditorView);
		} catch (Exception e) {
			// TODO: handle exception
		}
		levelEditor = new LevelEditor(levelEditorView);
	}
	
	public static void startGame(char[][] level) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("/fxml/GameView.fxml"));
			gameView = (AnchorPane) loader.load();
			
			root.setCenter(gameView);
			
			gameView.requestFocus();
			
			GameViewController controller = loader.getController();
			controller.postInit();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	
		board = new Board(gameView, 50, 60, level);
	}
	
}
