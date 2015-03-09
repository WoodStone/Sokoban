package no.vestein.sokoban.board;

import javafx.scene.effect.Effect;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class VText extends Text {
	
	public VText(Color color, int fontSize, int x, int y, String text, Effect effect) {
		super(text);
		setFill(color);
		setFont(Font.font(fontSize));
		setEffect(effect);
		setTranslateX(x);
		setTranslateY(y);
	}
	
	public VText(Color color, int fontSize, int x, int y, String text) {
		this(color, fontSize, x, y, text, null);
	}
	
	public VText(Color color, Color color2, int fontSize, int x, int y, String text) {
		this(color, fontSize, x, y, text);
		setOnMouseEntered(mouseEvent -> {
			this.setFill(color2);
		});
		setOnMouseExited(mouseEvent -> {
			this.setFill(color);
		});
	}
}
