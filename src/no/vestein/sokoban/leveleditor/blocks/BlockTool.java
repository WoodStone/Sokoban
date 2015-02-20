package no.vestein.sokoban.leveleditor.blocks;

import no.vestein.sokoban.Sokoban;
import javafx.event.EventHandler;
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
				Sokoban.levelEditor.setPreviousBlockTool(Sokoban.levelEditor.getSelectedBlockTool());
				Sokoban.levelEditor.setSelectedBlockTool((BlockTool) mouseEvent.getSource()); 

				if (Sokoban.levelEditor.getPreviousBlockTool() != null) {
					Sokoban.levelEditor.getPreviousBlockTool().setEffect(null);
				}
				((BlockTool) mouseEvent.getSource()).setEffect(new DropShadow());
				
				Sokoban.levelEditor.getToolTooltip().setText(((BlockTool) mouseEvent.getSource()).getTooltip());
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
