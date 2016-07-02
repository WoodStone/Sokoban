package no.vestein.sokoban.blocks;

import no.vestein.sokoban.Reference;
import no.vestein.sokoban.Sokoban;
import no.vestein.sokoban.animation.AnimationBox;

public class BlockBox extends Block implements IMoveable {
	
	private AnimationBox animationBox;
	
	public BlockBox(int x, int y) {
		super(x, y);
		imageView.setImage(Reference.IMAGE_BOX);
		animationBox = new AnimationBox(this);
	}
	
	public void goDir(int dirX, int dirY) {
		animationBox.goDir(dirX, dirY);
	}
	
	public void setXPosition(int x) {
		imageView.setX(x * Reference.BLOCK_WIDTH + Sokoban.board.getPosX());
		super.x = x * Reference.BLOCK_WIDTH + Sokoban.board.getPosX();
	}
	
	public void setYPosition(int y) {
		imageView.setY(y * Reference.BLOCK_HEIGHT + Sokoban.board.getPosY());
		super.y = y * Reference.BLOCK_HEIGHT + Sokoban.board.getPosY();
	}

}
