package no.vestein.sokoban.level;

import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;

public class GameConditionController {

	private SokobanLevel sokobanLevel;
	
	public GameConditionController(SokobanLevel sokobanLevel) {
		this.sokobanLevel = sokobanLevel;
	}
	
	private int numberOfGoals() {
		return sokobanLevel.getGoalGroup().getChildren().size();
	}
	
	private int numberOfGreenBoxes () {
		int n = 0;
		for (Node box : sokobanLevel.getBoxGroup().getChildren()) {
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
