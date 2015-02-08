package no.vestein.sokoban.leveleditor;

import no.vestein.sokoban.SokobanScene;
import no.vestein.sokoban.leveleditor.grid.BlockGrid;
import no.vestein.sokoban.leveleditor.grid.BlockTool;
import javafx.scene.Group;
import javafx.scene.paint.Color;

public class LevelEditor {
	
	public static Group gridGroup;
	public static Group blockGroup;
	
	public static BlockTool selectedBlockTool;
	
	public static char[][] level = new char[20][20];
	

	public static void init() {
		setupGrid();
		setupBlocks();
	}
	
	public static void load(char[][] loadedLevel) {
		gridGroup.getChildren().clear();
		
		for (int i = 0; i < loadedLevel.length; i++) {
			for (int j = 0; j < loadedLevel.length; j++) {
				BlockGrid blockGrid = new BlockGrid(j * 20 + 50, i * 20 + 80);
				if (loadedLevel[i][j] == '#') {
					blockGrid.setTag('#');
					blockGrid.setFill(Color.BROWN);
				} else if (loadedLevel[i][j] == '$') {
					blockGrid.setTag('$');
					blockGrid.setFill(Color.RED);
				} else if (loadedLevel[i][j] == '*') {
					blockGrid.setTag('*');
					blockGrid.setFill(Color.GREEN);
				} else if (loadedLevel[i][j] == '.') {
					blockGrid.setTag('.');
					blockGrid.setFill(Color.LIGHTBLUE);
				} else if (loadedLevel[i][j] == '@') {
					blockGrid.setTag('@');
					blockGrid.setFill(Color.YELLOW);
				}
				gridGroup.getChildren().add(blockGrid);
				level[i][j] = loadedLevel[i][j];
			}
		}
		
	}
	
	private static void setupGrid() {
		gridGroup = new Group();
		
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				BlockGrid blockGrid = new BlockGrid(j * 20 + 50, i * 20 + 80);
				gridGroup.getChildren().add(blockGrid);
				level[i][j] = '0';
			}
		}
		
		SokobanScene.levelEditorView.getChildren().add(gridGroup);
	}
	
	private static void setupBlocks() {
		blockGroup = new Group();
		
		BlockTool wall = new BlockTool(50, 40, Color.BROWN, '#');
		BlockTool box = new BlockTool(90, 40, Color.RED, '$');
		BlockTool boxOnGoal = new BlockTool(130, 40, Color.GREEN, '*');
		BlockTool goal = new BlockTool(170, 40, Color.LIGHTBLUE, '.');
		BlockTool player = new BlockTool(210, 40, Color.YELLOW, '@');
		BlockTool air = new BlockTool(250, 40, Color.LIGHTGREY, '0');
		
		blockGroup.getChildren().addAll(wall, box, boxOnGoal, goal, player, air);
		
		SokobanScene.levelEditorView.getChildren().add(blockGroup);
	}
	
}
