package no.vestein.sokoban.animation;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.Animation.Status;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.shape.Ellipse;
import javafx.util.Duration;
import no.vestein.sokoban.map.Level;
import no.vestein.sokoban.map.MoveController;

public class AnimationPlayer {

	private static Timeline up;
	private static Timeline down;
	private static Timeline right;
	private static Timeline left;
	
	public static void init() {
		up();
		down();
		right();
		left();
	}
	
	public static void goUp() {
		if (up.getStatus() == Status.STOPPED) {
			up.playFromStart();
		}
	}
	
	public static void goDown() {
		if (down.getStatus() == Status.STOPPED) {
			down.playFromStart();
		}
	}
	
	public static void goRight() {
		if (right.getStatus() == Status.STOPPED) {
			right.playFromStart();
		}
	}
	
	public static void goLeft() {
		if (left.getStatus() == Status.STOPPED) {
			left.playFromStart();
		}
	}
	
	
	public static Timeline setAnimation(int dirX, int dirY) {
		Timeline timeline = new Timeline();
		Ellipse node = Level.getMap().getPlayer().getObject();
		
		timeline.getKeyFrames().clear();
		timeline.getKeyFrames().add(
			        new KeyFrame(new Duration(250),
			        	new KeyValue(node.translateXProperty(), 20 * dirX),
			            new KeyValue(node.translateYProperty(), 20 * dirY)
			        ));
		return timeline;
	}
	
	public static void up() {
		up = setAnimation( 0, -1);
		up.setOnFinished(new EventHandler<ActionEvent>() {	
			@Override
			public void handle(ActionEvent event) {
				Level.getMap().getPlayer().setXPosition(Level.getMap().getPlayer().getXPosition() + 0);
				Level.getMap().getPlayer().setYPosition(Level.getMap().getPlayer().getYPosition() - 1);
				Level.getMap().getPlayer().getObject().setTranslateX(0);
				Level.getMap().getPlayer().getObject().setTranslateY(0);
//				MoveController.movePlayer(0, -1);
			}
		});
	}
	
	public static void down() {
		down = setAnimation(0, 1);
		down.setOnFinished(new EventHandler<ActionEvent>() {	
			@Override
			public void handle(ActionEvent event) {
				Level.getMap().getPlayer().setXPosition(Level.getMap().getPlayer().getXPosition() + 0);
				Level.getMap().getPlayer().setYPosition(Level.getMap().getPlayer().getYPosition() + 1);
				Level.getMap().getPlayer().getObject().setTranslateX(0);
				Level.getMap().getPlayer().getObject().setTranslateY(0);
//				MoveController.movePlayer(0, 1);
			}
		});
	}
	
	public static void right() {
		right = setAnimation(1, 0);
		right.setOnFinished(new EventHandler<ActionEvent>() {	
			@Override
			public void handle(ActionEvent event) {
				Level.getMap().getPlayer().setXPosition(Level.getMap().getPlayer().getXPosition() + 1);
				Level.getMap().getPlayer().setYPosition(Level.getMap().getPlayer().getYPosition() + 0);
				Level.getMap().getPlayer().getObject().setTranslateX(0);
				Level.getMap().getPlayer().getObject().setTranslateY(0);
//				MoveController.movePlayer(1, 0);
			}
		});
	}
	
	public static void left() {
		left = setAnimation(-1, 0);
		left.setOnFinished(new EventHandler<ActionEvent>() {	
			@Override
			public void handle(ActionEvent event) {
				Level.getMap().getPlayer().setXPosition(Level.getMap().getPlayer().getXPosition() - 1);
				Level.getMap().getPlayer().setYPosition(Level.getMap().getPlayer().getYPosition() + 0);
				Level.getMap().getPlayer().getObject().setTranslateX(0);
				Level.getMap().getPlayer().getObject().setTranslateY(0);
//				MoveController.movePlayer(-1, 0);
			}
		});
	}
	
}
