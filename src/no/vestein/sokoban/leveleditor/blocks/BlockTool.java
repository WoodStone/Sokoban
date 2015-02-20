package no.vestein.sokoban.leveleditor.blocks;

import no.vestein.sokoban.leveleditor.LevelEditor;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class BlockTool extends ImageView {
	
	private char tag;
	private String tooltip;
	
	@SuppressWarnings("static-access")
	public BlockTool(int x, int y, Image image, char tag, String tooltip) {
		super(image);
		this.setX(x);
		this.setY(y);
		this.setFitHeight(32);
		this.setFitWidth(32);
		this.setPickOnBounds(true);
		this.tag = tag;
		this.tooltip = tooltip;
		
		Tooltip t = new Tooltip(tooltip);
		t.install(this, t);
		
		this.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				LevelEditor.selectedBlockTool = (BlockTool) mouseEvent.getSource();
				
				for (Node block : LevelEditor.toolsGroup.getChildren()) {
					((BlockTool) block).setEffect(null);
				}
				((BlockTool) mouseEvent.getSource()).setEffect(new DropShadow());
				
				LevelEditor.toolTooltip.setText(((BlockTool) mouseEvent.getSource()).getTooltip());
			}
		});
	}
	
	public char getTag() {
		return tag;
	}
	
	public String getTooltip() {
		return tooltip;
	}
	
}
