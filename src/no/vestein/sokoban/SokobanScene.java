package no.vestein.sokoban;

import no.vestein.sokoban.animation.SokobanAnimation;
import no.vestein.sokoban.fxml.GameViewController;
import no.vestein.sokoban.map.Level;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class SokobanScene {

	public static Stage primaryStage;
	public static BorderPane root;
	public static AnchorPane startView;
	public static AnchorPane gameView;
	public static AnchorPane levelSelectView;
	public static Scene scene;

	
	public static void initStage(Stage stage) {
		primaryStage = stage;
		primaryStage.setTitle("Sokoban");
		
	}
	
	public static void initRoot() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("fxml/RootView.fxml"));
			root = (BorderPane) loader.load();
			
			scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static void initStart() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("fxml/StartView.fxml"));
			startView = (AnchorPane) loader.load();
			
			root.setCenter(startView);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static void levelSelect() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("fxml/LevelSelectView.fxml"));
			levelSelectView = (AnchorPane) loader.load();
			
			root.setCenter(levelSelectView);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static void startGame(char[][] level) {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("fxml/GameView.fxml"));
			gameView = (AnchorPane) loader.load();
			
			root.setCenter(gameView);
			
			gameView.requestFocus();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		GameViewController.init();
		Level.init(level);
//		SokobanAnimation.init();
		
	}
	
}
