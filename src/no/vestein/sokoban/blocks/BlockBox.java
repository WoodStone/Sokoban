package no.vestein.sokoban.blocks;

import no.vestein.sokoban.Reference;
import no.vestein.sokoban.Sokoban;
import no.vestein.sokoban.animation.AnimationBox;
import no.vestein.sokoban.board.Board;
import javafx.scene.image.ImageView;

public class BlockBox extends BlockEllipse{
	
	private AnimationBox animationBox;
	
	public BlockBox(int x, int y) {
		super(x, y);
		
		imageView = new ImageView(Reference.IMAGE_BOX);
		imageView.setFitHeight(Reference.BLOCK_HEIGHT);
		imageView.setFitWidth(Reference.BLOCK_WIDTH);
		imageView.setX(x);
		imageView.setY(y);
		
		animationBox = new AnimationBox(this);
	}
	
	public void goDir(int dirX, int dirY) {
		animationBox.goDir(dirX, dirY);
	}
	
	public void updateState(Board board) {
		if (board.isGoal(getXPosition(), getYPosition())) {
			setGoal();
		} else {
			setDefault();
		}
	}
	
	public void setDefault() {
		imageView.setImage(Reference.IMAGE_BOX);
	}
	
	public void setGoal() {
		imageView.setImage(Reference.IMAGE_BOXONGOAL);
		if (Sokoban.board != null) Sokoban.board.finishGame();
	}
	
}
