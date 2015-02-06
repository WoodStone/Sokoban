package no.vestein.sokoban.blocks;

import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

public class BlockGoal extends BlockEllipse{
	
	public BlockGoal(int x, int y) {
		super(x, y);
		super.shape = new Ellipse(x + 10, y + 10, 10, 10);
		super.shape.setFill(Color.WHITE);
		super.shape.setStroke(Color.GRAY);
	}

}
