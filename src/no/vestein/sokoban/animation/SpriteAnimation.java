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
    private final int offsetX;
    private final int offsetY;
    private final int width;
    private final int height;

    private int lastIndex;
    private int[] test = {0, 1, 2, 3};
    private List<Integer> view;
    private int dir;

    public SpriteAnimation(
            ImageView imageView, 
            Duration duration, 
            int count,   int columns,
            int offsetX, int offsetY,
            int width,   int height,
            List<Integer> view, int dir) {
        this.imageView = imageView;
        this.count     = count;
        this.columns   = columns;
        this.offsetX   = offsetX;
        this.offsetY   = offsetY;
        this.width     = width;
        this.height    = height;
        setCycleDuration(duration);
        setInterpolator(Interpolator.LINEAR);
        setAutoReverse(true);
//        setCycleCount(2);
        this.dir = dir;
        this.view = view;
    }

    protected void interpolate(double k) {
    	
        final int index = Math.min((int) Math.floor(k * count), count - 1);
        if (index != lastIndex) {
            final int x = (view.get(index) % columns) * width  + offsetX;
            final int y = (view.get(index) / columns) * height + offsetY;
            imageView.setViewport(new Rectangle2D(x, y, width, height));
            imageView.setScaleX(0.5 * dir);
            lastIndex = index;
        }
    }
}