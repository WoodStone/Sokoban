package no.vestein.sokoban.blocks;

import java.util.Arrays;
import java.util.List;

import no.vestein.sokoban.Reference;
import no.vestein.sokoban.Sokoban;
import no.vestein.sokoban.animation.AnimationPlayer;
import no.vestein.sokoban.animation.SpriteAnimation;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class BlockPlayer extends Block {
	
	private final int WIDTH = 64;
	private final int HEIGHT = 64;
	private final int COLUMNS = 4;
	private final List<Integer> FRAMESLEFTRIGHT = Arrays.asList(1, 2, 3, 0);
	private final int COUNTRIGHTLEFT = FRAMESLEFTRIGHT.size();
	
	private SpriteAnimation right;
	private SpriteAnimation left;
	private SpriteAnimation up;
	private SpriteAnimation down;
	private ImageView imageView;
	private AnimationPlayer animationPlayer;
	private boolean moving = false;
	
	public BlockPlayer(int x, int y) {
		super(x, y);
		
		imageView = new ImageView(Reference.IMAGE_PLAYER);
		imageView.setViewport(new Rectangle2D(0, 0, WIDTH, HEIGHT));
		imageView.setScaleX(0.75);
		imageView.setScaleY(0.75);
		imageView.setX(x - 17);
		imageView.setY(y - 22);

		animationPlayer = new AnimationPlayer(this);
		
		this.right = new SpriteAnimation(imageView, Duration.millis(Reference.ANIMATION_TIME), COUNTRIGHTLEFT, COLUMNS, WIDTH, HEIGHT, FRAMESLEFTRIGHT, 1);
		this.left = new SpriteAnimation(imageView, Duration.millis(Reference.ANIMATION_TIME), COUNTRIGHTLEFT, COLUMNS, WIDTH, HEIGHT, FRAMESLEFTRIGHT, -1);
		this.up = new SpriteAnimation(imageView, Duration.millis(Reference.ANIMATION_TIME), COUNTRIGHTLEFT, COLUMNS, WIDTH, HEIGHT, FRAMESLEFTRIGHT, 1);
		this.down = new SpriteAnimation(imageView, Duration.millis(Reference.ANIMATION_TIME), COUNTRIGHTLEFT, COLUMNS, WIDTH, HEIGHT, FRAMESLEFTRIGHT, 1);
	}
	
	public BlockPlayer() {
		this(0, 0);
	}
	
	@SuppressWarnings("unused")
	private ImageView makeImageView() {
		ImageView imageView = new ImageView(Reference.IMAGE_PLAYER);
		imageView.setViewport(new Rectangle2D(0, 0, WIDTH, HEIGHT));
		imageView.setScaleX(0.75);
		imageView.setScaleY(0.75);
		return imageView;
	}
	
	public void setXPosition(int x) {
		imageView.setX(x * Reference.BLOCK_WIDTH + Sokoban.board.getPosX() - 17);
		super.x = x * Reference.BLOCK_WIDTH + Sokoban.board.getPosX();
	}
	
	public void setYPosition(int y) {
		imageView.setY(y * Reference.BLOCK_HEIGHT + Sokoban.board.getPosY() - 22);
		super.y = y * Reference.BLOCK_HEIGHT + Sokoban.board.getPosY();
	}
	
	@Override
	public int getXPosition() {
		return (x - Sokoban.board.getPosX()) / Reference.BLOCK_WIDTH;
		
	}
	
	@Override
	public int getYPosition() {
		return (y - Sokoban.board.getPosY()) / Reference.BLOCK_HEIGHT;
	}
	
	public void goDir(int dirX, int dirY) {
		if (dirX == 1) {
			goRight();
		} else if (dirX == -1) {
			goLeft();
		} else if (dirY == 1) {
			goDown();
		} else if (dirY == -1) {
			goUp();
		}
	}

	public void goRight() {
		animationPlayer.goRight();
		right.play();
	}
	
	public void goLeft() {
		animationPlayer.goLeft();
		left.play();
	}
	
	public void goUp() {
		animationPlayer.goUp();
		up.setDir((int) (imageView.getScaleX() * 2));
		up.play();
	}
	
	public void goDown() {
		animationPlayer.goDown();
		down.setDir((int) (imageView.getScaleX() * 2));
		down.play();
	}
	
	public void setMoving(boolean moving) {
		this.moving = moving;
	}
	
	public boolean getMoving() {
		return moving;
	}
	
	@Override
	public ImageView getObject() {
		return imageView;
	}
	
}
