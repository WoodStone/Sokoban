package no.vestein.sokoban.blocks;

import no.vestein.sokoban.Sokoban;
import javafx.scene.shape.Ellipse;

public class BlockEllipse extends Block {

	protected Ellipse shape;
	
	public BlockEllipse(int x, int y) {
		super(x, y);
	}
	
	public void setXPosition(int x) {
		shape.setCenterX(x * 20 + Sokoban.board.getPosX() + 10);
		super.x = x * 20 + Sokoban.board.getPosX();
	}
	
	public void setYPosition(int y) {
		shape.setCenterY(y * 20 + Sokoban.board.getPosY() + 10);
		super.y = y * 20 + Sokoban.board.getPosY();
	}
	
	@Override
	public Ellipse getObject() {
		return shape;
	}
	
}
