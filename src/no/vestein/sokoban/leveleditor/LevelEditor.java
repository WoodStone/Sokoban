package no.vestein.sokoban.leveleditor;

import no.vestein.sokoban.Main;
import no.vestein.sokoban.Reference;
import no.vestein.sokoban.Sokoban;
import no.vestein.sokoban.leveleditor.blocks.BlockGrid;
import no.vestein.sokoban.leveleditor.blocks.BlockTool;
import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class LevelEditor {
	
	public static Group gridGroup;
	public static Group toolsGroup;
	public static Text toolTooltip;
	public static BlockTool selectedBlockTool;
	public static char[][] level = new char[20][20];
	
	private static Image toolBox = new Image(Main.class.getResourceAsStream("resources/box.png"));
	private static Image toolBoxOnGoal = new Image(Main.class.getResourceAsStream("resources/boxongoal.png"));
	private static Image toolgoal = new Image(Main.class.getResourceAsStream("resources/goal.png"));
	private static Image toolwall = new Image(Main.class.getResourceAsStream("resources/wall.png"));
	private static Image toolPlayer = new Image(Main.class.getResourceAsStream("resources/toolplayer.png"));
	private static Image toolDelete = new Image(Main.class.getResourceAsStream("resources/delete.png"));
	
	public static Image air  = new Image(Main.class.getResourceAsStream("resources/air.png"));
	
	public static void init() {
		makeGrid();
		setupGridBlocks();
		setupBlocks();
	}
	
	public static void load(char[][] loadedLevel) {
		gridGroup.getChildren().clear();
		
		for (int i = 0; i < loadedLevel.length; i++) {
			for (int j = 0; j < loadedLevel.length; j++) {
				BlockGrid blockGrid = new BlockGrid(j * Reference.blockWidth + 50, i * Reference.blockHeight + 80, air);
				if (loadedLevel[i][j] == '#') {
					blockGrid.setTag('#');
					blockGrid.setImage(toolwall);
				} else if (loadedLevel[i][j] == '$') {
					blockGrid.setTag('$');
					blockGrid.setImage(toolBox);
				} else if (loadedLevel[i][j] == '*') {
					blockGrid.setTag('*');
					blockGrid.setImage(toolBoxOnGoal);
				} else if (loadedLevel[i][j] == '.') {
					blockGrid.setTag('.');
					blockGrid.setImage(toolgoal);
				} else if (loadedLevel[i][j] == '@') {
					blockGrid.setTag('@');
					blockGrid.setImage(toolPlayer);
				}
				gridGroup.getChildren().add(blockGrid);
				level[i][j] = loadedLevel[i][j];
			}
		}
		
	}
	
	private static void setupGridBlocks() {
		gridGroup = new Group();
		for (int i = 0; i < Reference.gridHeight; i++) {
			for (int j = 0; j < Reference.gridWidth; j++) {
				BlockGrid blockGrid = new BlockGrid(j * Reference.blockWidth + 50, i * Reference.blockWidth + 80, air);
				gridGroup.getChildren().add(blockGrid);
				level[i][j] = '0';
			}
		}
		Sokoban.levelEditorView.getChildren().add(gridGroup);
	}
	
	private static void makeGrid() {
		Group gridGroup = new Group();
		for (int i = 0; i < Reference.gridHeight; i++) {
			for (int j = 0; j < Reference.gridWidth; j++) {
				Rectangle rectGrid = new Rectangle(j * Reference.blockWidth + 50, i * Reference.blockHeight + 80, Reference.blockWidth, Reference.blockHeight);
				rectGrid.setFill(Color.rgb(128, 128, 128, 0.15));
				rectGrid.setStroke(Color.DARKGRAY);
				
				gridGroup.getChildren().add(rectGrid);
			}
		}
		Sokoban.levelEditorView.getChildren().add(gridGroup);
	}
	
	
	private static void setupBlocks() {
		toolsGroup = new Group();
		
		BlockTool wall = new BlockTool(50, 40, toolwall, '#', "Wall");
		BlockTool box = new BlockTool(90, 40, toolBox, '$', "Box");
		BlockTool boxOnGoal = new BlockTool(130, 40, toolBoxOnGoal, '*', "Box on Goal");
		BlockTool goal = new BlockTool(170, 40, toolgoal, '.', "Goal");
		BlockTool player = new BlockTool(210, 40, toolPlayer, '@', "Player");
		BlockTool delete = new BlockTool(250, 40, toolDelete, '0', "Delete");
//		BlockTool air = new BlockTool(250, 40, Color.LIGHTGREY, Color.rgb(128, 128, 128, 0.15), '0', "Air");
		
		toolsGroup.getChildren().addAll(wall, box, boxOnGoal, goal, player, delete);
		
		toolTooltip = new Text(300, 55 + 7.5, "<--Select block");
		toolTooltip.setFill(Color.PURPLE);
		toolTooltip.setFont(Font.font(20));
		
		Sokoban.levelEditorView.getChildren().addAll(toolsGroup, toolTooltip);
	}
	
}
