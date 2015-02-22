package no.vestein.sokoban.blocks;

import no.vestein.sokoban.Reference;
import javafx.scene.image.ImageView;

public class BlockWall extends Block{
	
	private ImageView imageView;
	
	public BlockWall(int x, int y) {
		super(x, y);
		imageView = new ImageView(Reference.IMAGE_WALL);
		imageView.setFitHeight(Reference.BLOCK_HEIGHT);
		imageView.setFitWidth(Reference.BLOCK_WIDTH);
		imageView.setX(x);
		imageView.setY(y);
	}
	
	@Override
	public ImageView getObject() {
		return imageView;
	}
	
}
