package no.vestein.sokoban.animation;

import java.util.Random;

import no.vestein.sokoban.Sokoban;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.effect.BoxBlur;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.util.Duration;

public class Background {
	
	public static Group root;
	public static Group rectangles;
	public static Timeline timeline;

	public static void init() {
		
		root = new Group();

		rectangles = new Group();
		Random r = new Random();
		for (int i = 0; i < 30; i++) {
			Rectangle rect = new Rectangle(100, 100, Color.web("white", 0.05));
			rect.setStrokeType(StrokeType.OUTSIDE);
			rect.setStroke(Color.web("white", 0.16));
			rect.setStrokeWidth(4);
			
			int col1 = r.nextInt(256);
			int col2 = r.nextInt(256);
			int col3 = r.nextInt(256);
			
			rect.setStroke(Color.rgb(col1, col2, col3));
			rectangles.getChildren().add(rect);
		}
		rectangles.setEffect(new BoxBlur(10, 10, 3));	
		
		root.getChildren().addAll(new Rectangle(Sokoban.scene.getWidth(), Sokoban.scene.getHeight(), Color.WHITESMOKE), rectangles);
		
		timeline = new Timeline();
		for (Node circle: rectangles.getChildren()) {
		    timeline.getKeyFrames().addAll(
		        new KeyFrame(Duration.ZERO,
		            new KeyValue(circle.translateXProperty(), r.nextInt(500)),
		            new KeyValue(circle.translateYProperty(), r.nextInt(500))
		        ),
		        new KeyFrame(new Duration(r.nextInt(4) * 5000),
		            new KeyValue(circle.translateXProperty(), r.nextInt(500)),
		            new KeyValue(circle.translateYProperty(), r.nextInt(500))
		        ),
		        new KeyFrame(new Duration(20000 + r.nextInt(4) * 5000 + 5000),
		        	new KeyValue(circle.translateXProperty(), r.nextInt(500)),
		        	new KeyValue(circle.translateYProperty(), r.nextInt(500))
		        ),
		        new KeyFrame(new Duration(45000),
		        	new KeyValue(circle.translateXProperty(), r.nextInt(500)),
		        	new KeyValue(circle.translateYProperty(), r.nextInt(500))
		        )
		    );
		}
		timeline.setOnFinished(event());
		timeline.play();
		Sokoban.root.getChildren().add(root);
	}
	
	public static EventHandler<ActionEvent> event() {
		return new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				timeline = new Timeline();
				Random r = new Random();
				for (Node rect : rectangles.getChildren()) {
					timeline.getKeyFrames().addAll(
						new KeyFrame(new Duration(r.nextInt(4) * 5000),
							new KeyValue(rect.translateXProperty(), r.nextInt(500)),
							new KeyValue(rect.translateYProperty(), r.nextInt(500))
						),
						new KeyFrame(new Duration(20000 + r.nextInt(4) * 5000 + 5000),
							new KeyValue(rect.translateXProperty(), r.nextInt(500)),
					        new KeyValue(rect.translateYProperty(), r.nextInt(500))
						),
						new KeyFrame(new Duration(45000),
							new KeyValue(rect.translateXProperty(), r.nextInt(500)),
							new KeyValue(rect.translateYProperty(), r.nextInt(500))
						)
					);
				}
				timeline.setOnFinished(event());
				timeline.playFromStart();
			}
		};
	}
	
}
