package no.vestein.sokoban.board;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import no.vestein.sokoban.Reference;
import no.vestein.sokoban.blocks.Block;
import no.vestein.sokoban.blocks.BlockGoal;
import no.vestein.sokoban.blocks.BlockPlayer;
import no.vestein.sokoban.blocks.BlockBox;
import no.vestein.sokoban.blocks.BlockWall;

public class Board {

	private int posX;
	private int posY;
	
	private Map<String, Block> objectMap;
	private char[][] level;
	private Group boxGroup;
	private Group goalGroup;
	private AnchorPane gameView;
	private MoveController moveController;
	private BoardGraphics boardGraphics;
	
	public Board(AnchorPane gameView, int posX, int posY, char[][] level) {
		this.gameView = gameView;
		this.posX = posX;
		this.posY = posY;
		this.level = level;
		this.moveController = new MoveController(this);
		this.boardGraphics = new BoardGraphics(this, moveController);
		this.objectMap = generateObjectMap();
		
		loadLevel();
		boardGraphics.makeTextMovesAndPushes(gameView);
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
	
	public boolean isGoal(int x, int y) {
		return getGoal(x, y) != null;
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
	
	public MoveController getMoveController() {
		return moveController;
	}
	
	public void setPlayer(BlockPlayer player) {
		objectMap.put("player", player);
	}
	
	public void updateText() {
		boardGraphics.updateText();
	}
	
	public void updateBox(BlockBox box) {
		if (isGoal(box.getXPosition(), box.getYPosition())) {
			setBoxGoal(box);
			boardGraphics.finishGame(this, gameView);
		} else {
			setBoxDefault(box);
		}
	}
	
	public void setBoxDefault(BlockBox box) {
		box.getObject().setImage(Reference.IMAGE_BOX);
	}
	
	public void setBoxGoal(BlockBox box) {
		box.getObject().setImage(Reference.IMAGE_BOXONGOAL);
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
					BlockWall wall = new BlockWall(posX + (j * Reference.BLOCK_WIDTH), posY + (i * Reference.BLOCK_HEIGHT));
					map.put(j + "." + i, wall);
				} else if (level[i][j] == '*') {
					BlockBox box = new BlockBox(posX + (j * Reference.BLOCK_WIDTH), posY + (i * Reference.BLOCK_HEIGHT));
					setBoxGoal(box);
					map.put(j + "." + i, box);
					
					BlockGoal goal = new BlockGoal(posX + (j * Reference.BLOCK_WIDTH), posY + (i * Reference.BLOCK_HEIGHT));
					map.put(j + "." + i + "goal", goal);
				} else if (level[i][j] == '$') {
					BlockBox box = new BlockBox(posX + (j * Reference.BLOCK_WIDTH), posY + (i * Reference.BLOCK_HEIGHT));
					map.put(j + "." + i, box);
				} else if (level[i][j] == '.') {
					BlockGoal goal = new BlockGoal(posX + (j * Reference.BLOCK_WIDTH), posY + (i * Reference.BLOCK_HEIGHT));
					map.put(j + "." + i + "goal", goal);
				} else if (level[i][j] == '@') {
					BlockPlayer player = new BlockPlayer(posX + (j * Reference.BLOCK_WIDTH), posY + (i * Reference.BLOCK_HEIGHT));
					map.put("player", player);
				}
			}
		}
		return map;
	}
	
	private void makeGrid() {
		Group gridGroup = new Group();
		for (int i = 0; i < Reference.GRID_HEIGHT; i++) {
			for (int j = 0; j < Reference.GRID_WIDTH; j++) {
				Rectangle rectGrid = new Rectangle(j * Reference.BLOCK_WIDTH + getPosX(), i * Reference.BLOCK_HEIGHT + getPosY(), Reference.BLOCK_WIDTH, Reference.BLOCK_HEIGHT);
				rectGrid.setFill(Color.rgb(128, 128, 128, 0.15));
				rectGrid.setStroke(Color.DARKGRAY);
				
				gridGroup.getChildren().add(rectGrid);
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
			if (((ImageView)box).getImage() == Reference.IMAGE_BOXONGOAL) {
				n++;
			}
		}
		return n;
	}
	
	public boolean isGameIsDone() {
		return numberOfGoals() == numberOfGreenBoxes();
	}
	
}
