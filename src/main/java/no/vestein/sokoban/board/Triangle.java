package no.vestein.sokoban.board;

import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

public class Triangle extends Polygon {
	
	public Triangle(double point1x, double point1y, double point2x, double point2y, double point3x, double point3y, int x, int y) {
		getPoints().addAll(new Double[] {
				point1x, point1y,
				point2x, point2y,
				point3x, point3y
			});
		setLayoutX(x);
		setLayoutY(y);
		setFill(Color.GREEN);
		setEffect(new DropShadow(5, Color.DARKGREEN));
		
		setOnMouseEntered(mouseevent -> {
			setFill(Color.LIGHTGREEN);
		});
		setOnMouseExited(mouseevent -> {
			setFill(Color.GREEN);
		});
	}
	
}
