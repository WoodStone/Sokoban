package no.vestein.sokoban.animation;

import no.vestein.sokoban.map.Level;
import no.vestein.sokoban.map.MoveController;
import javafx.animation.Animation.Status;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.shape.Ellipse;
import javafx.util.Duration;

public class SokobanAnimation {

	private static Timeline timeline;
	
	public static void init() {
		timeline = new Timeline();
		Ellipse node = Level.getMap().getPlayer().getObject();
		System.out.println("node start pos: " + node.getCenterY());
		System.out.println("player start pos: " + Level.getMap().getPlayer().getYPosition());
		timeline.getKeyFrames().clear();
		timeline.getKeyFrames().add(
			        new KeyFrame(new Duration(250),
			        	new KeyValue(node.translateXProperty(), 0),
			            new KeyValue(node.translateYProperty(), 20)
			        ));
		
		timeline.setOnFinished(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				Level.getMap().getPlayer().setYPosition(Level.getMap().getPlayer().getYPosition() + 1);
				Level.getMap().getPlayer().getObject().setTranslateY(0);
				MoveController.movePlayer(0, 1);
			}
		});
		
	}
	
	public static void test() {
		if (timeline.getStatus() == Status.STOPPED) {
			timeline.playFromStart();
		}
	}
	
}
