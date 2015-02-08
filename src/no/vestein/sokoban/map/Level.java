package no.vestein.sokoban.map;

public class Level {

	private static SokobanLevel objectMap;
	
	public static void loadLevel(char[][] level) {
		objectMap = new SokobanLevel(50, 50, level);
		objectMap.loadLevel();
	}
	
	public static SokobanLevel getMap() {
		return objectMap;
	}
	
	public static char[][] getSelectedLevel() {
		return objectMap.getLevel();
	}
	
}
