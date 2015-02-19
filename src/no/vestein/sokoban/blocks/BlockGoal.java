package no.vestein.sokoban.blocks;

import no.vestein.sokoban.Main;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BlockGoal extends BlockEllipse{
	
	private final Image IMAGE = new Image(Main.class.getResourceAsStream("resources/goal.png"));
	private final int WIDTH = 32;
	private final int HEIGHT = 32;
	
	public BlockGoal(int x, int y) {
		super(x, y);
		
		imageView = new ImageView(IMAGE);
		imageView.setViewport(new Rectangle2D(0, 0, WIDTH, HEIGHT));
		imageView.setFitHeight(20);
		imageView.setFitWidth(20);
		imageView.setX(x);
		imageView.setY(y);
		
	}

}
