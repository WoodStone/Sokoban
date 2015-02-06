package no.vestein.sokoban.blocks;

import javafx.scene.shape.Shape;


public class Block{

	int x, y;
	
	public Block(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getXPosition() {
		return (x - 50) / 20;
		
	}
	
	public int getYPosition() {
		return (y - 50) / 20;
	}
	
	public Shape getObject() {
		return null;
	}
	
}
