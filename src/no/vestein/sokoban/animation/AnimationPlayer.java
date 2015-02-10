package no.vestein.sokoban.animation;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.Animation.Status;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import no.vestein.sokoban.blocks.BlockPlayer;
import no.vestein.sokoban.level.Level;

public class AnimationPlayer {

	private Timeline up;
	private Timeline down;
	private Timeline right;
	private Timeline left;
	private BlockPlayer player;
	
	public AnimationPlayer(BlockPlayer player) {
		this.player = player;
		up();
		down();
		right();
		left();
	}
	
	public boolean getStatus() {
		return up.getStatus() == Status.STOPPED &&
				down.getStatus() == Status.STOPPED &&
				right.getStatus() == Status.STOPPED &&
				left.getStatus() == Status.STOPPED;
	}
	
	public void goUp() {
		if (getStatus()) {
			up.playFromStart();
		}
	}
	
	public void goDown() {
		if (getStatus()) {
			down.playFromStart();
		}
	}
	
	public void goRight() {
		if (getStatus()) {
			right.playFromStart();
		}
	}
	
	public void goLeft() {
		if (getStatus()) {
			left.playFromStart();
		}
	}
	
	
	private Timeline setAnimation(int dirX, int dirY) {
		Timeline timeline = new Timeline();
		ImageView node = player.getObject();
		
		timeline.getKeyFrames().clear();
		timeline.getKeyFrames().add(
			        new KeyFrame(new Duration(400),
			        	new KeyValue(node.translateXProperty(), 20 * dirX),
			            new KeyValue(node.translateYProperty(), 20 * dirY)
			        ));
		return timeline;
	}
	
	private void up() {
		up = setAnimation( 0, -1);
		up.setOnFinished(new EventHandler<ActionEvent>() {	
			@Override
			public void handle(ActionEvent event) {
				Level.getMap().getPlayer().setXPosition(Level.getMap().getPlayer().getXPosition() + 0);
				Level.getMap().getPlayer().setYPosition(Level.getMap().getPlayer().getYPosition() - 1);
				Level.getMap().getPlayer().getObject().setTranslateX(0);
				Level.getMap().getPlayer().getObject().setTranslateY(0);
			}
		});
	}
	
	private void down() {
		down = setAnimation(0, 1);
		down.setOnFinished(new EventHandler<ActionEvent>() {	
			@Override
			public void handle(ActionEvent event) {
				Level.getMap().getPlayer().setXPosition(Level.getMap().getPlayer().getXPosition() + 0);
				Level.getMap().getPlayer().setYPosition(Level.getMap().getPlayer().getYPosition() + 1);
				Level.getMap().getPlayer().getObject().setTranslateX(0);
				Level.getMap().getPlayer().getObject().setTranslateY(0);
			}
		});
	}
	
	private void right() {
		right = setAnimation(1, 0);
		right.setOnFinished(new EventHandler<ActionEvent>() {	
			@Override
			public void handle(ActionEvent event) {
				Level.getMap().getPlayer().setXPosition(Level.getMap().getPlayer().getXPosition() + 1);
				Level.getMap().getPlayer().setYPosition(Level.getMap().getPlayer().getYPosition() + 0);
				Level.getMap().getPlayer().getObject().setTranslateX(0);
				Level.getMap().getPlayer().getObject().setTranslateY(0);
			}
		});
	}
	
	private void left() {
		left = setAnimation(-1, 0);
		left.setOnFinished(new EventHandler<ActionEvent>() {	
			@Override
			public void handle(ActionEvent event) {
				Level.getMap().getPlayer().setXPosition(Level.getMap().getPlayer().getXPosition() - 1);
				Level.getMap().getPlayer().setYPosition(Level.getMap().getPlayer().getYPosition() + 0);
				Level.getMap().getPlayer().getObject().setTranslateX(0);
				Level.getMap().getPlayer().getObject().setTranslateY(0);
			}
		});
	}
	
}
