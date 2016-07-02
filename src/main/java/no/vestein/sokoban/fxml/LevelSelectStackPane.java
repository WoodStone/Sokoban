package no.vestein.sokoban.fxml;

import javafx.scene.effect.DropShadow;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class LevelSelectStackPane extends StackPane {

	private int tag;
	private Rectangle rect;
	
	public LevelSelectStackPane(int startx, int starty, int x, int y) {
		super();
		setLayoutX(startx + x * 100);
		setLayoutY(starty + y * 100);

		Rectangle rect = new Rectangle(75, 75);
		rect.setFill(Color.rgb(128, 128, 128, 0.25));
		rect.setEffect(new DropShadow(5, Color.GRAY));
		
		Text number = new Text(Integer.toString(5 * y + x + 1));
		number.setFill(Color.AQUAMARINE);
		number.setFont(Font.font(50));
		number.setEffect(new DropShadow(5, Color.BLACK));
		
		this.getChildren().addAll(rect, number);
		this.tag = 5 * y + x + 1;
		this.rect = rect;
		
		setEvents();
	}
	
	public int getTag() {
		return tag;
	}
	
	public Rectangle getRectangle() {
		return rect;
	}
	
	private void setEvents() {
		this.setOnMouseEntered(mouseEvent -> {
			rect.setEffect(new DropShadow(5, Color.AQUA));
		});
		this.setOnMouseExited(mouseEvent -> {
			rect.setEffect(new DropShadow(5, Color.GREY));
		});
	}
	
}
