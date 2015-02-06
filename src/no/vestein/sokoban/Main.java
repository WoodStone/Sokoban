package no.vestein.sokoban;
	
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	
	private Stage primaryStage;
	private static BorderPane root;
//	private static AnchorPane startView;
	
	
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Sokoban");
		
		initRoot();
		initStart();
		
	}
	
	private void initRoot() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("fxml/RootView.fxml"));
			root = (BorderPane) loader.load();
			
			Scene scene = new Scene(root);
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	private void initStart() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("fxml/StartView.fxml"));
			AnchorPane startView = (AnchorPane) loader.load();
			
			root.setCenter(startView);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static void initGame() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("fxml/GameView.fxml"));
			AnchorPane gameView = (AnchorPane) loader.load();
			
			root.setCenter(gameView);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
