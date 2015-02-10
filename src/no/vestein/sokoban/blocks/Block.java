package no.vestein.sokoban.blocks;

import no.vestein.sokoban.level.Level;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Shape;


public class Block{

	int x, y;
	
	public Block(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public int getXPosition() {
		return (x - Level.getMap().getPosX()) / 20;
		
	}
	
	public int getYPosition() {
		return (y - Level.getMap().getPosY()) / 20;
	}
	
	public Node getObject() {
		return null;
	}
	
}
