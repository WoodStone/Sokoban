package no.vestein.sokoban.blocks;

import no.vestein.sokoban.Reference;

public class BlockGoal extends Block{
	
	public BlockGoal(int x, int y) {
		super(x, y);
		imageView.setImage(Reference.IMAGE_GOAL);
	}

}
