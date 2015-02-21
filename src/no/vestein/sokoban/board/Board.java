package no.vestein.sokoban.board;

import java.util.HashMap;
import java.util.Map;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
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
	
	public Board(AnchorPane gameView, int posX, int posY, char[][] level) {
		this.gameView = gameView;
		this.posX = posX;
		this.posY = posY;
		this.level = level;
		this.objectMap = generateObjectMap();
		this.moveController = new MoveController(this);
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
	
	public MoveController getMoveController() {
		return moveController;
	}
	
	public void setPlayer(BlockPlayer player) {
		objectMap.put("player", player);
	}
	
	public void finishGame() {
		if (checkIfGameIsDone()) {
			StackPane pane = new StackPane();
			pane.setLayoutX(50);
			pane.setLayoutY(60);
			
			Rectangle rect = new Rectangle(660, 660);
			rect.setTranslateX(-10);
			rect.setTranslateY(-10);
			rect.setFill(Color.rgb(128, 128, 128, 0.50));
			Text victory = new Text(Reference.STRING_VICTORY);
			victory.setFill(Color.AQUA);
			victory.setFont(Font.font(70));
			victory.setEffect(new DropShadow());
			victory.setTranslateY(-30);
			
			
			pane.getChildren().addAll(rect, victory);
			pane.setAlignment(Pos.CENTER);
			
			gameView.getChildren().add(pane);
			pane.requestFocus();
		}
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
				Rectangle rectGrid = new Rectangle(j * Reference.blockWidth + getPosX(), i * Reference.blockHeight + getPosY(), Reference.blockWidth, Reference.blockHeight);
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
	
	public boolean checkIfGameIsDone() {
		return numberOfGoals() == numberOfGreenBoxes();
	}
	
}
