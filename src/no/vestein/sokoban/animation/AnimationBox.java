package no.vestein.sokoban.animation;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.animation.Animation.Status;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.util.Duration;
import no.vestein.sokoban.blocks.BlockBox;
import no.vestein.sokoban.level.Level;

public class AnimationBox {

	private Timeline up;
	private Timeline down;
	private Timeline right;
	private Timeline left;
	private BlockBox box;
	
	public AnimationBox(BlockBox box) {
		this.box = box;
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
	
	public void goDir(int dirX, int dirY) {
		box.getObject().setFill(Color.RED);
		if (dirX == 1) {
			goRight();
		} else if (dirX == -1) {
			goLeft();
		} else if (dirY == 1) {
			goDown();
		} else if (dirY == -1) {
			goUp();
		}
	}
	
	public void goUp() {
		up.playFromStart();
	}
	
	public void goDown() {
		down.playFromStart();
	}
	
	public void goRight() {
		right.playFromStart();
	}
	
	public void goLeft() {
		left.playFromStart();
	}
	
	private Timeline setAnimation(int dirX, int dirY) {
		Timeline timeline = new Timeline();
		Ellipse node = box.getObject();
		
		timeline.getKeyFrames().clear();
		timeline.getKeyFrames().add(
			        new KeyFrame(new Duration(400),
			        	new KeyValue(node.translateXProperty(), 20 * dirX),
			            new KeyValue(node.translateYProperty(), 20 * dirY)
			        ));
		return timeline;
	}
	
	private void up() {
		up = setAnimation(0, -1);
		up.setOnFinished(new EventHandler<ActionEvent>() {	
			@Override
			public void handle(ActionEvent event) {
				box.setXPosition(box.getXPosition() + 0);
				box.setYPosition(box.getYPosition() - 1);
				box.getObject().setTranslateX(0);
				box.getObject().setTranslateY(0);
				if (Level.getMap().getGoal(box.getXPosition(), box.getYPosition()) != null) {
					box.getObject().setFill(Color.GREEN);
				}
			}
		});
	}
	
	private void down() {
		down = setAnimation(0, 1);
		down.setOnFinished(new EventHandler<ActionEvent>() {	
			@Override
			public void handle(ActionEvent event) {
				box.setXPosition(box.getXPosition() + 0);
				box.setYPosition(box.getYPosition() + 1);
				box.getObject().setTranslateX(0);
				box.getObject().setTranslateY(0);
				if (Level.getMap().getGoal(box.getXPosition(), box.getYPosition()) != null) {
					box.getObject().setFill(Color.GREEN);
				}
			}
		});
	}
	
	private void right() {
		right = setAnimation(1, 0);
		right.setOnFinished(new EventHandler<ActionEvent>() {	
			@Override
			public void handle(ActionEvent event) {
				box.setXPosition(box.getXPosition() + 1);
				box.setYPosition(box.getYPosition() + 0);
				box.getObject().setTranslateX(0);
				box.getObject().setTranslateY(0);
				if (Level.getMap().getGoal(box.getXPosition(), box.getYPosition()) != null) {
					box.getObject().setFill(Color.GREEN);
				}
			}
		});
	}
	
	private void left() {
		left = setAnimation(-1, 0);
		left.setOnFinished(new EventHandler<ActionEvent>() {	
			@Override
			public void handle(ActionEvent event) {
				box.setXPosition(box.getXPosition() - 1);
				box.setYPosition(box.getYPosition() + 0);
				box.getObject().setTranslateX(0);
				box.getObject().setTranslateY(0);
				if (Level.getMap().getGoal(box.getXPosition(), box.getYPosition()) != null) {
					box.getObject().setFill(Color.GREEN);
				}
			}
		});
	}
	
}
