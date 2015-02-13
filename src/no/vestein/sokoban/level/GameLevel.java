package no.vestein.sokoban.level;

public class GameLevel {

	private SokobanLevel objectMap;
	private GameConditionController gameConditionController;
	private char[][] level;
	
	public GameLevel(char[][] level) {
		this.level = level;
	}
	
	public void load() {
		objectMap = new SokobanLevel(50, 60, level);
		gameConditionController = new GameConditionController(objectMap);
		objectMap.loadLevel();
	}
	
	public SokobanLevel getMap() {
		return objectMap;
	}
	
	public char[][] getSelectedLevel() {
		return objectMap.getLevel();
	}
	
}
