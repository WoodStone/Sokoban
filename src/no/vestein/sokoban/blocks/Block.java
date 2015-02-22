package no.vestein.sokoban.blocks;

import no.vestein.sokoban.Reference;
import no.vestein.sokoban.Sokoban;
import javafx.scene.Node;

public class Block{

	int x, y;
	
	public Block(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getXPosition() {
		return (x - Sokoban.board.getPosX()) / Reference.BLOCK_WIDTH;
	}
	
	public int getYPosition() {
		return (y - Sokoban.board.getPosY()) / Reference.BLOCK_HEIGHT;
	}
	
	public Node getObject() {
		return null;
	}
	
}
