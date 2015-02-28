package no.vestein.sokoban.blocks;

import no.vestein.sokoban.Reference;

public class BlockWall extends Block{
	
	public BlockWall(int x, int y) {
		super(x, y);
		imageView.setImage(Reference.IMAGE_WALL);
	}

}
