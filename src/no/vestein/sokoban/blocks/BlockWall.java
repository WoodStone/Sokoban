package no.vestein.sokoban.blocks;

import no.vestein.sokoban.Main;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BlockWall extends Block{
	
	private ImageView imageView;
	private final Image IMAGE = new Image(Main.class.getResourceAsStream("resources/wall.png"));
	private final int WIDTH = 32;
	private final int HEIGHT = 32;
	
	public BlockWall(int x, int y) {
		super(x, y);
		imageView = new ImageView(IMAGE);
		imageView.setViewport(new Rectangle2D(0, 0, WIDTH, HEIGHT));
		imageView.setFitHeight(20);
		imageView.setFitWidth(20);
		imageView.setX(x);
		imageView.setY(y);
	}
	
	@Override
	public ImageView getObject() {
		return imageView;
	}
	
}
