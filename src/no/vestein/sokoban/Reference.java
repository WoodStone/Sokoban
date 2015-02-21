package no.vestein.sokoban;

import javafx.scene.image.Image;

public class Reference {
	
	public static final int blockWidth = 32;
	public static final int blockHeight = 32;
	public static final int windowWidth = 700;
	public static final int windowHeight = 700;
	public static final int gridWidth = 20;
	public static final int gridHeight = 20;
	
	public static final Image IMAGE_BOX = new Image(Main.class.getResourceAsStream("/assets/box.png"));
	public static final Image IMAGE_BOXONGOAL = new Image(Main.class.getResourceAsStream("/assets/boxongoal.png"));
	public static final Image IMAGE_GOAL = new Image(Main.class.getResourceAsStream("/assets/goal.png"));
	public static final Image IMAGE_WALL = new Image(Main.class.getResourceAsStream("/assets/wall.png"));
	public static final Image IMAGE_PLAYER = new Image(Main.class.getResourceAsStream("/assets/player.png"));
	public static final Image IMAGE_TOOLPLAYER = new Image(Main.class.getResourceAsStream("/assets/toolplayer.png"));
	public static final Image IMAGE_DELETE = new Image(Main.class.getResourceAsStream("/assets/delete.png"));
	
	public static final String STRING_VICTORY = "VICTORY!";
	
}
