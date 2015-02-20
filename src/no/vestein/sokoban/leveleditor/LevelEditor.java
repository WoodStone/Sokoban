package no.vestein.sokoban.leveleditor;

import no.vestein.sokoban.Reference;
import no.vestein.sokoban.Sokoban;
import no.vestein.sokoban.leveleditor.blocks.BlockGrid;
import no.vestein.sokoban.leveleditor.blocks.BlockTool;
import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class LevelEditor {
	
	private AnchorPane levelEditorView;
	
	private Group gridGroup;
	private Text toolTooltip;
	private BlockTool selectedBlockTool;
	private BlockTool previousBlockTool;
	private char[][] level = new char[20][20];
	
	public LevelEditor(AnchorPane levelEditorView) {
		this.levelEditorView = levelEditorView;
		makeGrid();
		setupGridBlocks();
		setupBlocks();
	}
	
	public char[][] getLoadedLevel() {
		return level;
	}
	
	public BlockTool getSelectedBlockTool() {
		return selectedBlockTool;
	}
	
	public BlockTool getPreviousBlockTool() {
		return previousBlockTool;
	}
	
	public void setSelectedBlockTool(BlockTool tool) {
		selectedBlockTool = tool;
	}
	
	public void setPreviousBlockTool(BlockTool tool) {
		previousBlockTool = tool;
	}

	public Text getToolTooltip() {
		return toolTooltip;
	}
	
	public void setTag(int x, int y, char tag) {
		level[y][x] = tag;
	}
	
	public void load(char[][] loadedLevel) {
		gridGroup.getChildren().clear();
		
		for (int i = 0; i < loadedLevel.length; i++) {
			for (int j = 0; j < loadedLevel.length; j++) {
				BlockGrid blockGrid = new BlockGrid(j * Reference.blockWidth + 50, i * Reference.blockHeight + 80, Reference.IMAGE_NOTHING);
				if (loadedLevel[i][j] == '#') {
					blockGrid.setTag('#');
					blockGrid.setImage(Reference.IMAGE_WALL);
				} else if (loadedLevel[i][j] == '$') {
					blockGrid.setTag('$');
					blockGrid.setImage(Reference.IMAGE_BOX);
				} else if (loadedLevel[i][j] == '*') {
					blockGrid.setTag('*');
					blockGrid.setImage(Reference.IMAGE_BOXONGOAL);
				} else if (loadedLevel[i][j] == '.') {
					blockGrid.setTag('.');
					blockGrid.setImage(Reference.IMAGE_GOAL);
				} else if (loadedLevel[i][j] == '@') {
					blockGrid.setTag('@');
					blockGrid.setImage(Reference.IMAGE_TOOLPLAYER);
				}
				gridGroup.getChildren().add(blockGrid);
				level[i][j] = loadedLevel[i][j];
			}
		}
		
	}
	
	private void setupGridBlocks() {
		gridGroup = new Group();
		for (int i = 0; i < Reference.gridHeight; i++) {
			for (int j = 0; j < Reference.gridWidth; j++) {
				BlockGrid blockGrid = new BlockGrid(j * Reference.blockWidth + 50, i * Reference.blockWidth + 80, Reference.IMAGE_NOTHING);
				gridGroup.getChildren().add(blockGrid);
				level[i][j] = '0';
			}
		}
		Sokoban.levelEditorView.getChildren().add(gridGroup);
	}
	
	private void makeGrid() {
		Group gridGroup = new Group();
		for (int i = 0; i < Reference.gridHeight; i++) {
			for (int j = 0; j < Reference.gridWidth; j++) {
				Rectangle rectGrid = new Rectangle(j * Reference.blockWidth + 50, i * Reference.blockHeight + 80, Reference.blockWidth, Reference.blockHeight);
				rectGrid.setFill(Color.rgb(128, 128, 128, 0.15));
				rectGrid.setStroke(Color.DARKGRAY);
				
				gridGroup.getChildren().add(rectGrid);
			}
		}
		levelEditorView.getChildren().add(gridGroup);
	}
	
	
	private void setupBlocks() {
		Group toolGroup = new Group();
		
		BlockTool wall = new BlockTool(50, 40, Reference.IMAGE_WALL, '#', "Wall");
		BlockTool box = new BlockTool(90, 40, Reference.IMAGE_BOX, '$', "Box");
		BlockTool boxOnGoal = new BlockTool(130, 40, Reference.IMAGE_BOXONGOAL, '*', "Box on Goal");
		BlockTool goal = new BlockTool(170, 40, Reference.IMAGE_GOAL, '.', "Goal");
		BlockTool player = new BlockTool(210, 40, Reference.IMAGE_TOOLPLAYER, '@', "Player");
		BlockTool delete = new BlockTool(250, 40, Reference.IMAGE_DELETE, '0', "Delete");
		
		toolGroup.getChildren().addAll(wall, box, boxOnGoal, goal, player, delete);
		
		toolTooltip = new Text(300, 55 + 7.5, "<--Select block");
		toolTooltip.setFill(Color.PURPLE);
		toolTooltip.setFont(Font.font(20));
		
		levelEditorView.getChildren().addAll(toolGroup, toolTooltip);
	}
	
}
