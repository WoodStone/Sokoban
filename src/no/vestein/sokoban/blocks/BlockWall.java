package no.vestein.sokoban.blocks;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class BlockWall extends Block{
	
	private Rectangle shape;
	
	public BlockWall(int x, int y) {
		super(x, y);
		shape = new Rectangle(x, y, 20, 20);
		shape.setFill(Color.BROWN);
	}
	
	@Override
	public Rectangle getObject() {
		return shape;
	}
	
}
