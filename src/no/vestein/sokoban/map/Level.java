package no.vestein.sokoban.map;

import javafx.scene.layout.Pane;
import no.vestein.sokoban.blocks.Block;
import no.vestein.sokoban.blocks.BlockPlayer;
import no.vestein.sokoban.SokobanScene;

public class Level {

	private static SokobanMap map;
	private static char[][] selectedLevel;
	
	public static void init(char[][] level) {
		map = new SokobanMap(50, 50, level);
		selectedLevel = level;
		genMap(SokobanScene.gameView, map);
	}
	
	public static SokobanMap getMap() {
		return map;
	}
	
	public static char[][] getSelectedLevel() {
		return selectedLevel;
	}
	
	private static void genMap(Pane shapesPane, SokobanMap map) {
		map.generate();
		for (Block value : map.getMap().values()) {
			if (! (value instanceof BlockPlayer)) {
				shapesPane.getChildren().add(value.getObject());
			}
		}
		shapesPane.getChildren().add(map.getPlayer().getObject());	
	}
	
}
