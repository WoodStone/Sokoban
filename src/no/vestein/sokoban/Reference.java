package no.vestein.sokoban;

import javafx.scene.image.Image;

public class Reference {
	
	public static final int BLOCK_WIDTH = 32;
	public static final int BLOCK_HEIGHT = 32;
	public static final int WINDOW_WIDTH = 700;
	public static final int WINDOW_HEIGHT = 700;
	public static final int GRID_WIDTH = 20;
	public static final int GRID_HEIGHT = 20;
	public static final int ANIMATION_TIME = 300;
	
	public static final Image IMAGE_BOX = new Image(Main.class.getResourceAsStream("/assets/box.png"));
	public static final Image IMAGE_BOXONGOAL = new Image(Main.class.getResourceAsStream("/assets/boxongoal.png"));
	public static final Image IMAGE_GOAL = new Image(Main.class.getResourceAsStream("/assets/goal.png"));
	public static final Image IMAGE_WALL = new Image(Main.class.getResourceAsStream("/assets/wall.png"));
	public static final Image IMAGE_PLAYER = new Image(Main.class.getResourceAsStream("/assets/player.png"));
	public static final Image IMAGE_TOOLPLAYER = new Image(Main.class.getResourceAsStream("/assets/toolplayer.png"));
	public static final Image IMAGE_DELETE = new Image(Main.class.getResourceAsStream("/assets/delete.png"));
	public static final Image IMAGE_PLAYERONGOAL = new Image(Main.class.getResourceAsStream("/assets/playerongoal.png"));
	
	public static final String STRING_VICTORY = "VICTORY!";
	
}
