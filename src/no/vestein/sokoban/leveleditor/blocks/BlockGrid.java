package no.vestein.sokoban.leveleditor.blocks;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import no.vestein.sokoban.Reference;
import no.vestein.sokoban.Sokoban;

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
			public void handle(MouseEvent mouseEvent) {
				BlockGrid block = (BlockGrid) mouseEvent.getSource();
				try {
					block.setImage(Sokoban.levelEditor.getSelectedBlockTool().getImage());
					if (Sokoban.levelEditor.getSelectedBlockTool().getTag() == '0') {
						block.setImage(null);
					}
					block.setTag(Sokoban.levelEditor.getSelectedBlockTool().getTag());
					
					int xpos = (int) (block.getX() - 50) / Reference.BLOCK_WIDTH;
					int ypos = (int) (block.getY() - 80) / Reference.BLOCK_HEIGHT;
					
					Sokoban.levelEditor.setTag(xpos, ypos, block.getTag());
				} catch (NullPointerException e) {
					return;
				}
				
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
