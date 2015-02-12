package no.vestein.sokoban.level;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import no.vestein.sokoban.SokobanScene;
import no.vestein.sokoban.blocks.Block;
import no.vestein.sokoban.blocks.BlockGoal;
import no.vestein.sokoban.blocks.BlockPlayer;
import no.vestein.sokoban.blocks.BlockBox;
import no.vestein.sokoban.blocks.BlockWall;

public class SokobanLevel {

	private int posX;
	private int posY;
	private Map<String, Block> objectMap;
	private char[][] level;
	private Group boxGroup;
	private Group goalGroup;
	
	public SokobanLevel(int posX, int posY, char[][] level) {
		this.posX = posX;
		this.posY = posY;
		this.level = level;
		this.objectMap = generateObjectMap();
		
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
	
	public char[][] getLevel() {
		return level;
	}
	
	public void setPlayer(BlockPlayer player) {
		objectMap.put("player", player);
	}
	
	public void loadLevel() {
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
		SokobanScene.gameView.getChildren().add(goalGroup);
		SokobanScene.gameView.getChildren().add(boxGroup);
		SokobanScene.gameView.getChildren().add(getPlayer().getObject());
	}
	
	private Map<String, Block> generateObjectMap() {
		Map<String, Block> map = new HashMap<>();
		for (int i = 0; i < level.length; i++) {
			for (int j = 0; j < level[0].length; j++) {
				if (level[i][j] == '#') {
					BlockWall wall = new BlockWall(posX + (j * 20), posY + (i * 20));
					map.put(j + "." + i, wall);
				} else if (level[i][j] == '*') {
					BlockBox box = new BlockBox(posX + (j * 20), posY + (i * 20));
					box.getObject().setFill(Color.GREEN);
					map.put(j + "." + i, box);
					
					BlockGoal goal = new BlockGoal(posX + (j * 20), posY + (i * 20));
					map.put(j + "." + i + "goal", goal);
				} else if (level[i][j] == '$') {
					BlockBox box = new BlockBox(posX + (j * 20), posY + (i * 20));
					map.put(j + "." + i, box);
				} else if (level[i][j] == '.') {
					BlockGoal goal = new BlockGoal(posX + (j * 20), posY + (i * 20));
					map.put(j + "." + i + "goal", goal);
				} else if (level[i][j] == '@') {
					BlockPlayer player = new BlockPlayer(posX + (j * 20), posY + (i * 20));
					map.put("player", player);
				}
			}
		}
		return map;
	}
	
	
}
