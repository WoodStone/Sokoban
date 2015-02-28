package no.vestein.sokoban.blocks;

import no.vestein.sokoban.Reference;
import no.vestein.sokoban.Sokoban;
import javafx.scene.image.ImageView;

public class Block{

	protected ImageView imageView = new ImageView();
	protected int x, y;
	
	public Block(int x, int y) {
		this.x = x;
		this.y = y;
		imageView.setFitHeight(Reference.BLOCK_HEIGHT);
		imageView.setFitWidth(Reference.BLOCK_WIDTH);
		imageView.setX(x);
		imageView.setY(y);
	}
	
	public int getXPosition() {
		return (x - Sokoban.board.getPosX()) / Reference.BLOCK_WIDTH;
	}
	
	public int getYPosition() {
		return (y - Sokoban.board.getPosY()) / Reference.BLOCK_HEIGHT;
	}
	
	public ImageView getObject() {
		return imageView;
	}
	
}
