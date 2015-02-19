package no.vestein.sokoban.leveleditor;

import no.vestein.sokoban.Reference;
import no.vestein.sokoban.Sokoban;
import no.vestein.sokoban.leveleditor.blocks.BlockGrid;
import no.vestein.sokoban.leveleditor.blocks.BlockTool;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class LevelEditor {
	
	public static Group gridGroup;
	public static Group toolsGroup;
	public static Text toolTooltip;
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
				BlockGrid blockGrid = new BlockGrid(j * Reference.blockWidth + 50, i * Reference.blockHeight + 80);
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
		for (int i = 0; i < Reference.gridHeight; i++) {
			for (int j = 0; j < Reference.gridWidth; j++) {
				BlockGrid blockGrid = new BlockGrid(j * Reference.blockWidth + 50, i * Reference.blockWidth + 80);
				gridGroup.getChildren().add(blockGrid);
				level[i][j] = '0';
			}
		}
		Sokoban.levelEditorView.getChildren().add(gridGroup);
	}
	
	private static void setupBlocks() {
		toolsGroup = new Group();
		
		BlockTool wall = new BlockTool(50, 40, Color.BROWN, '#', "Wall");
		BlockTool box = new BlockTool(90, 40, Color.RED, '$', "Box");
		BlockTool boxOnGoal = new BlockTool(130, 40, Color.GREEN, '*', "Box on Goal");
		BlockTool goal = new BlockTool(170, 40, Color.LIGHTBLUE, '.', "Goal");
		BlockTool player = new BlockTool(210, 40, Color.YELLOW, '@', "Player");
		BlockTool air = new BlockTool(250, 40, Color.LIGHTGREY, Color.rgb(128, 128, 128, 0.15), '0', "Air");
		
		toolsGroup.getChildren().addAll(wall, box, boxOnGoal, goal, player, air);
		
		toolTooltip = new Text(300, 55 + 7.5, "<--Select block");
		toolTooltip.setFill(Color.PURPLE);
		toolTooltip.setFont(Font.font(20));
		
		Sokoban.levelEditorView.getChildren().addAll(toolsGroup, toolTooltip);
	}
	
}
