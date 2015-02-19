package no.vestein.sokoban.blocks;

import no.vestein.sokoban.Main;
import no.vestein.sokoban.Reference;
import no.vestein.sokoban.animation.AnimationBox;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class BlockBox extends BlockEllipse{
	
	private final Image IMAGE = new Image(Main.class.getResourceAsStream("resources/box.png"));
	private final Image ONGOAL = new Image(Main.class.getResourceAsStream("resources/boxongoal.png"));
	private final int WIDTH = 32;
	private final int HEIGHT = 32;
	private AnimationBox animationBox;
	
	public BlockBox(int x, int y) {
		super(x, y);
		
		imageView = new ImageView(IMAGE);
		imageView.setViewport(new Rectangle2D(0, 0, WIDTH, HEIGHT));
		imageView.setFitHeight(Reference.blockHeight);
		imageView.setFitWidth(Reference.blockWidth);
		imageView.setX(x);
		imageView.setY(y);
		
		animationBox = new AnimationBox(this);
	}
	
	public void goDir(int dirX, int dirY) {
		animationBox.goDir(dirX, dirY);
	}
	
	public void setDefault() {
		imageView.setImage(IMAGE);
	}
	
	public void setGoal() {
		imageView.setImage(ONGOAL);
	}
	
}
