package no.vestein.sokoban.blocks;

import javafx.scene.shape.Ellipse;

public class BlockEllipse extends Block {

	protected Ellipse shape;
	
	public BlockEllipse(int x, int y) {
		super(x, y);
	}
	
	public void setXPosition(int x) {
		shape.setCenterX(x * 20 + 60);
		super.x = x * 20 + 50;
	}
	
	public void setYPosition(int y) {
		shape.setCenterY(y * 20 + 60);
		super.y = y * 20 + 50;
	}
	
	@Override
	public Ellipse getObject() {
		return shape;
	}
	
}
