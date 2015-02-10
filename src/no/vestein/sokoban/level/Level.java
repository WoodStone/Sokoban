package no.vestein.sokoban.level;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import no.vestein.sokoban.SokobanScene;
import no.vestein.sokoban.leveleditor.grid.BlockGrid;

public class Level {

	private static SokobanLevel objectMap;
	
	public static void loadLevel(char[][] level) {
		objectMap = new SokobanLevel(50, 60, level);
		makeGrid();
		objectMap.loadLevel();
		
//		System.out.println(objectMap.getPlayer().getXPosition() + ":" + objectMap.getPlayer().getYPosition());
//		System.out.println(objectMap.getBlock(0, 0).getXPosition());
	}
	
	public static SokobanLevel getMap() {
		return objectMap;
	}
	
	public static char[][] getSelectedLevel() {
		return objectMap.getLevel();
	}
	
	private static void makeGrid() {
		Group gridGroup = new Group();
		
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				BlockGrid blockGrid = new BlockGrid(j * 20 + objectMap.getPosX(), i * 20 + objectMap.getPosY());
				blockGrid.setStroke(Color.rgb(169, 169, 169, 0.20));
				gridGroup.getChildren().add(blockGrid);
			}
		}
		
		SokobanScene.gameView.getChildren().add(gridGroup);
	}
	
}