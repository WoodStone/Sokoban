package no.vestein.sokoban.blocks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import no.vestein.sokoban.Main;
import no.vestein.sokoban.SokobanScene;
import no.vestein.sokoban.animation.SpriteAnimation;
import no.vestein.sokoban.level.Level;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.util.Duration;

public class BlockPlayer extends Block {

	private ImageView imageView;
	private final Image IMAGE = new Image(Main.class.getResourceAsStream("Player.png"));
	private final int WIDTH = 64;
	private final int HEIGHT = 64;
	private final int COLUMNS = 4;
	private final List<Integer> viewRightLeft = Arrays.asList(1, 2, 3, 0);
	private final int COUNTRIGHTLEFT = viewRightLeft.size();
	
	private SpriteAnimation right;
	private SpriteAnimation left;
	
	public BlockPlayer(int x, int y) {
		super(x, y);
		
		imageView = new ImageView(IMAGE);
		imageView.setViewport(new Rectangle2D(0, 0, WIDTH, HEIGHT));
//		imageView.setFitHeight(32);
//		imageView.setFitWidth(32);
		imageView.setScaleX(0.50);
		imageView.setScaleY(0.50);
		imageView.setX(x - 22);
		imageView.setY(y - 27);

		
		
		this.right = new SpriteAnimation(imageView, Duration.millis(400), COUNTRIGHTLEFT, COLUMNS, 0, 0, WIDTH, HEIGHT, viewRightLeft, 1);
		this.left = new SpriteAnimation(imageView, Duration.millis(400), COUNTRIGHTLEFT, COLUMNS, 0, 0, WIDTH, HEIGHT, viewRightLeft, -1);
		
	}
	
	private ImageView makeImageView() {
		ImageView imageView = new ImageView(IMAGE);
		imageView.setViewport(new Rectangle2D(0, 0, WIDTH, HEIGHT));
		imageView.setScaleX(0.50);
		imageView.setScaleY(0.50);
		return imageView;
	}
	
	public void setXPosition(int x) {
		imageView.setX(x * 20 + Level.getMap().getPosX() - 22);
		super.x = x * 20 + Level.getMap().getPosX();
	}
	
	public void setYPosition(int y) {
		imageView.setY(y * 20 + Level.getMap().getPosY() - 27);
		super.y = y * 20 + Level.getMap().getPosY();
	}
	
	@Override
	public int getXPosition() {
		return (x - Level.getMap().getPosX()) / 20;
		
	}
	
	@Override
	public int getYPosition() {
		return (y - Level.getMap().getPosY()) / 20;
	}

	public void playRight() {
		right.play();
	}
	
	public void playLeft() {
		left.play();
	}
	
	@Override
	public ImageView getObject() {
		return imageView;
	}
	
}
