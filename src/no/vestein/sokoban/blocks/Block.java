package no.vestein.sokoban.blocks;

import no.vestein.sokoban.SokobanScene;
import javafx.scene.Node;

public class Block{

	int x, y;
	
	public Block(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getXPosition() {
		return (x - SokobanScene.gameLevel.getMap().getPosX()) / 20;
	}
	
	public int getYPosition() {
		return (y - SokobanScene.gameLevel.getMap().getPosY()) / 20;
	}
	
	public Node getObject() {
		return null;
	}
	
}
