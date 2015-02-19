package no.vestein.sokoban.board;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import no.vestein.sokoban.Reference;
import no.vestein.sokoban.blocks.Block;
import no.vestein.sokoban.blocks.BlockGoal;
import no.vestein.sokoban.blocks.BlockPlayer;
import no.vestein.sokoban.blocks.BlockBox;
import no.vestein.sokoban.blocks.BlockWall;
import no.vestein.sokoban.leveleditor.blocks.BlockGrid;

public class Board {

	private int posX;
	private int posY;
	
	private Map<String, Block> objectMap;
	private char[][] level;
	private Group boxGroup;
	private Group goalGroup;
	private AnchorPane gameView;
	
	public Board(AnchorPane gameView, int posX, int posY, char[][] level) {
		this.gameView = gameView;
		this.posX = posX;
		this.posY = posY;
		this.level = level;
		this.objectMap = generateObjectMap();
		loadLevel();
	}
	
	public int getPosY() {
		return posY;
	}
	
	public int getPosX() {
		return posX;
	}
	
	public Map<String, Block> getObjectMap() {
		return objectMap;
	}
	
	public Block getBlock(int x, int y) {
		return objectMap.get(x + "." + y);
	}
	
	public BlockGoal getGoal(int x, int y) {
		return (BlockGoal) objectMap.get(x + "." + y + "goal");
	}
	
	public BlockPlayer getPlayer() {
		return (BlockPlayer) objectMap.get("player");
	}
	
	public Group getBoxGroup() {
		return boxGroup;
	}
	
	public Group getGoalGroup() {
		return goalGroup;
	}
	
	public char[][] getSelectedLevel() {
		return level;
	}
	
	public void setPlayer(BlockPlayer player) {
		objectMap.put("player", player);
	}
	
	private void loadLevel() {
		makeGrid();
		setupObjectGroup();
	}
	
	private void setupObjectGroup() {
		boxGroup = new Group();
		goalGroup = new Group();
		for (Block value : objectMap.values()) {
			if (! (value instanceof BlockPlayer)) {
				if (value instanceof BlockGoal) {
					goalGroup.getChildren().add(value.getObject());
				} else {
					boxGroup.getChildren().add(value.getObject());
				}
			}
		}
		gameView.getChildren().add(goalGroup);
		gameView.getChildren().add(boxGroup);
		gameView.getChildren().add(getPlayer().getObject());
	}
	
	private Map<String, Block> generateObjectMap() {
		Map<String, Block> map = new HashMap<>();
		for (int i = 0; i < level.length; i++) {
			for (int j = 0; j < level[0].length; j++) {
				if (level[i][j] == '#') {
					BlockWall wall = new BlockWall(posX + (j * Reference.blockWidth), posY + (i * Reference.blockHeight));
					map.put(j + "." + i, wall);
				} else if (level[i][j] == '*') {
					BlockBox box = new BlockBox(posX + (j * Reference.blockWidth), posY + (i * Reference.blockHeight));
					box.setGoal();
					map.put(j + "." + i, box);
					
					BlockGoal goal = new BlockGoal(posX + (j * Reference.blockWidth), posY + (i * Reference.blockHeight));
					map.put(j + "." + i + "goal", goal);
				} else if (level[i][j] == '$') {
					BlockBox box = new BlockBox(posX + (j * Reference.blockWidth), posY + (i * Reference.blockHeight));
					map.put(j + "." + i, box);
				} else if (level[i][j] == '.') {
					BlockGoal goal = new BlockGoal(posX + (j * Reference.blockWidth), posY + (i * Reference.blockHeight));
					map.put(j + "." + i + "goal", goal);
				} else if (level[i][j] == '@') {
					BlockPlayer player = new BlockPlayer(posX + (j * Reference.blockWidth), posY + (i * Reference.blockHeight));
					map.put("player", player);
				}
			}
		}
		return map;
	}
	
	private void makeGrid() {
		Group gridGroup = new Group();
		for (int i = 0; i < Reference.gridHeight; i++) {
			for (int j = 0; j < Reference.gridWidth; j++) {
				BlockGrid blockGrid = new BlockGrid(j * Reference.blockWidth + getPosX(), i * Reference.blockHeight + getPosY());
				blockGrid.setStroke(Color.rgb(128, 128, 128, 0.50));
				gridGroup.getChildren().add(blockGrid);
			}
		}
		gameView.getChildren().add(gridGroup);
	}
	
	private int numberOfGoals() {
		return getGoalGroup().getChildren().size();
	}
	
	private int numberOfGreenBoxes () {
		int n = 0;
		for (Node box : getBoxGroup().getChildren()) {
			if (((Ellipse)box).getFill() == Color.GREEN) {
				n++;
			}
		}
		return n;
	}
	
	public boolean checkIfGameIsDone() {
		return numberOfGoals() == numberOfGreenBoxes();
	}
	
}
