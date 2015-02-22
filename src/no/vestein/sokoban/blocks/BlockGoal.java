package no.vestein.sokoban.blocks;

import no.vestein.sokoban.Reference;
import javafx.scene.image.ImageView;

public class BlockGoal extends BlockEllipse{
	
	public BlockGoal(int x, int y) {
		super(x, y);
		
		imageView = new ImageView(Reference.IMAGE_GOAL);
		imageView.setFitHeight(Reference.BLOCK_HEIGHT);
		imageView.setFitWidth(Reference.BLOCK_WIDTH);
		imageView.setX(x);
		imageView.setY(y);
		
	}

}
