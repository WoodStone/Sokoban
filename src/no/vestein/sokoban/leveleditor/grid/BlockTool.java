package no.vestein.sokoban.leveleditor.grid;

import no.vestein.sokoban.leveleditor.LevelEditor;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class BlockTool extends Rectangle{
	
	private char tag;
	
	public BlockTool(int x, int y, Color color, char tag) {
		super(x, y, 30, 30);
		this.setFill(color);
		this.setStroke(Color.DARKGRAY);
		this.tag = tag;
		
		this.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				LevelEditor.selectedBlockTool = (BlockTool) mouseEvent.getSource();
				
				for (Node block : LevelEditor.blockGroup.getChildren()) {
					((BlockTool) block).setStroke(Color.LIGHTGRAY);
				}
				
				((BlockTool) mouseEvent.getSource()).setStroke(Color.RED);;
			}
		});
	}
	
	public char getTag() {
		return tag;
	}
	
}
