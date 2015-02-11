package no.vestein.sokoban.animation;

import java.util.List;

import javafx.animation.Interpolator;
import javafx.animation.Transition;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class SpriteAnimation extends Transition {

    private final ImageView imageView;
    private final int count;
    private final int columns;
    private final int width;
    private final int height;
    private final List<Integer> view;
    private final int dir;
    
    private int lastIndex;

    public SpriteAnimation(
            ImageView imageView, 
            Duration duration, 
            int count,   int columns,
            int width,   int height,
            List<Integer> view, int dir) {
    	this.imageView = imageView;
        this.count     = count;
        this.columns   = columns;
        this.width     = width;
        this.height    = height;
        this.dir = dir;
        this.view = view;
        setCycleDuration(duration);
        setInterpolator(Interpolator.LINEAR);
    }

    protected void interpolate(double k) {
    	final int index = Math.min((int) Math.floor(k * count), count - 1);
    	if (index != lastIndex) {
    		final int x = (view.get(index) % columns) * width;
    		final int y = (view.get(index) / columns) * height;
    		imageView.setViewport(new Rectangle2D(x, y, width, height));
    		imageView.setScaleX(0.5 * dir);
    		lastIndex = index;
    	}
    }
}