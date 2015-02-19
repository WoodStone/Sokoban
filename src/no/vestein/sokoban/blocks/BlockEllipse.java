package no.vestein.sokoban.blocks;

import no.vestein.sokoban.Reference;
import no.vestein.sokoban.Sokoban;
import javafx.scene.image.ImageView;

public class BlockEllipse extends Block {

	protected ImageView imageView;
	
	public BlockEllipse(int x, int y) {
		super(x, y);
	}
	
	public void setXPosition(int x) {
		imageView.setX(x * Reference.blockWidth + Sokoban.board.getPosX());
		super.x = x * Reference.blockWidth + Sokoban.board.getPosX();
	}
	
	public void setYPosition(int y) {
		imageView.setY(y * Reference.blockHeight + Sokoban.board.getPosY());
		super.y = y * Reference.blockHeight + Sokoban.board.getPosY();
	}
	
	@Override
	public ImageView getObject() {
		return imageView;
	}
	
}
