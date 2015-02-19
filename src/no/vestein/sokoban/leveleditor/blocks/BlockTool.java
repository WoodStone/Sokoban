package no.vestein.sokoban.leveleditor.blocks;

import no.vestein.sokoban.leveleditor.LevelEditor;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class BlockTool extends Rectangle{
	
	private char tag;
	private String tooltip;
	private Color color;
	
	@SuppressWarnings("static-access")
	public BlockTool(int x, int y, Color color, char tag, String tooltip) {
		super(x, y, 30, 30);
		this.setFill(color);
		this.setStroke(Color.DARKGRAY);
		this.tag = tag;
		this.color = color;
		this.tooltip = tooltip;
		
		Tooltip t = new Tooltip(tooltip);
		t.install(this, t);
		
		this.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				LevelEditor.selectedBlockTool = (BlockTool) mouseEvent.getSource();
				
				for (Node block : LevelEditor.toolsGroup.getChildren()) {
					((BlockTool) block).setStroke(Color.DARKGRAY);
					((BlockTool) block).setStrokeWidth(1);
				}
				((BlockTool) mouseEvent.getSource()).setStroke(Color.AQUA);;
				((BlockTool) mouseEvent.getSource()).setStrokeWidth(2);
				
				LevelEditor.toolTooltip.setText(((BlockTool) mouseEvent.getSource()).getTooltip());
			}
		});
	}
	
	public BlockTool(int x, int y, Color color, Color color2, char tag, String tooltip) {
		this(x, y, color, tag, tooltip);
		this.color = color2;
	}
	
	public char getTag() {
		return tag;
	}
	
	public Color getColor() {
		return color;
	}
	
	public String getTooltip() {
		return tooltip;
	}
	
}
