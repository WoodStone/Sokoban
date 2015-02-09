package no.vestein.sokoban.leveleditor.grid;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import no.vestein.sokoban.leveleditor.LevelEditor;

public class BlockGrid extends Rectangle{

	private char tag;
	
	public BlockGrid(int x, int y) {
		super(x, y, 20, 20);
//		this.setFill(Color.LIGHTGRAY);
		this.setFill(Color.rgb(227, 227, 227, 0.15));
		this.setStroke(Color.DARKGRAY);
		this.tag = '0';
		
		this.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) throws NullPointerException {
				BlockGrid block = (BlockGrid) mouseEvent.getSource();
				
				block.setFill(LevelEditor.selectedBlockTool.getColor());
				block.setTag(LevelEditor.selectedBlockTool.getTag());
				
				int xpos = (int) (block.getX() - 50) / 20;
				int ypos = (int) (block.getY() - 80) / 20;
				
				LevelEditor.level[ypos][xpos] = block.getTag();
			}
		});
	}
	
	public void setTag(char tag) {
		this.tag = tag;
	}
	
	public char getTag() {
		return tag;
	}
}
