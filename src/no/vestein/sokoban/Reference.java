package no.vestein.sokoban;

import javafx.scene.image.Image;

public class Reference {
	
	public static final int blockWidth = 32;
	public static final int blockHeight = 32;
	public static final int windowWidth = 700;
	public static final int windowHeight = 700;
	public static final int gridWidth = 20;
	public static final int gridHeight = 20;
	
	public static final Image IMAGE_BOX = new Image(Main.class.getResourceAsStream("resources/box.png"));
	public static final Image IMAGE_BOXONGOAL = new Image(Main.class.getResourceAsStream("resources/boxongoal.png"));
	public static final Image IMAGE_GOAL = new Image(Main.class.getResourceAsStream("resources/goal.png"));
	public static final Image IMAGE_WALL = new Image(Main.class.getResourceAsStream("resources/wall.png"));
	public static final Image IMAGE_PLAYER = new Image(Main.class.getResourceAsStream("resources/player.png"));
	public static final Image IMAGE_TOOLPLAYER = new Image(Main.class.getResourceAsStream("resources/toolplayer.png"));
	public static final Image IMAGE_DELETE = new Image(Main.class.getResourceAsStream("resources/delete.png"));
	public static final Image IMAGE_NOTHING  = new Image(Main.class.getResourceAsStream("resources/air.png"));
	
}
