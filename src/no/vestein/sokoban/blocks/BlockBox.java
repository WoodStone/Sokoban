package no.vestein.sokoban.blocks;

import no.vestein.sokoban.animation.AnimationBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

public class BlockBox extends BlockEllipse{
	
	private AnimationBox animationBox;
	
	public BlockBox(int x, int y) {
		super(x, y);
		super.shape = new Ellipse(x + 10, y + 10, 10, 10);
		super.shape.setFill(Color.RED);
		
		animationBox = new AnimationBox(this);
	}
	
	public void goDir(int dirX, int dirY) {
		animationBox.goDir(dirX, dirY);
	}
}
