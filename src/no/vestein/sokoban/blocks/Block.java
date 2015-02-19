package no.vestein.sokoban.blocks;

import no.vestein.sokoban.Sokoban;
import javafx.scene.Node;

public class Block{

	int x, y;
	
	public Block(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getXPosition() {
		return (x - Sokoban.board.getPosX()) / 20;
	}
	
	public int getYPosition() {
		return (y - Sokoban.board.getPosY()) / 20;
	}
	
	public Node getObject() {
		return null;
	}
	
}
