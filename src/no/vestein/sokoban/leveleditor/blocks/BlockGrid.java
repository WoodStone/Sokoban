package no.vestein.sokoban.leveleditor.blocks;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import no.vestein.sokoban.Reference;
import no.vestein.sokoban.leveleditor.LevelEditor;

public class BlockGrid extends ImageView{

	private char tag;
	
	public BlockGrid(int x, int y, Image image) {
		super(image);
		this.setX(x);
		this.setY(y);
		this.setFitHeight(32);
		this.setFitWidth(32);
		this.setPickOnBounds(true);
		this.tag = '0';
		
		this.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) throws NullPointerException {
				BlockGrid block = (BlockGrid) mouseEvent.getSource();
				block.setImage(LevelEditor.selectedBlockTool.getImage());
				if (LevelEditor.selectedBlockTool.getTag() == '0') {
					block.setImage(LevelEditor.air);
				}
				block.setTag(LevelEditor.selectedBlockTool.getTag());
				
				int xpos = (int) (block.getX() - 50) / Reference.blockWidth;
				int ypos = (int) (block.getY() - 80) / Reference.blockHeight;
				
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
