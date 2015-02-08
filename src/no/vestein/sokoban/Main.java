package no.vestein.sokoban;
	
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
	
	@Override
	public void start(Stage primaryStage) {
		SokobanScene.initStage(primaryStage);
		
		SokobanScene.initRoot();
		SokobanScene.initStart();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
