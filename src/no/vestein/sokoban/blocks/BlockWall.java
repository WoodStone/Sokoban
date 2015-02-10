package no.vestein.sokoban.blocks;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

public class BlockWall extends Block{
	
	private Rectangle shape;
	
	public BlockWall(int x, int y) {
		super(x, y);
		shape = new Rectangle(x, y, 20, 20);
		shape.setFill(Color.BROWN);
		shape.setStroke(Color.rgb(227, 227, 227, 0.5));
		shape.setStrokeType(StrokeType.CENTERED);
		shape.setStrokeWidth(0.5);
	}
	
	@Override
	public Rectangle getObject() {
		return shape;
	}
	
}
