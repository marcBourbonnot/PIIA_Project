package modele;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

public class Ellipse extends Forme {
    public Ellipse() {
    }

    public Ellipse(double x, double y, double width, double height, String text) {
        super(x, y, width, height, text);
    }

    public Ellipse(double x, double y) {
        super(x, y);
    }

    public Ellipse(double x, double y, double width, double height) {
        super(x, y, width, height);
    }

    @Override
    public boolean estDedans(double x, double y) {
        return false;
    }

    @Override
    public void draw(GraphicsContext gc) {
        Paint save = gc.getFill();

        gc.setFill(this.getClr());
        gc.strokeOval(this.getX(), this.getY(), this.getWidth(), this.getHeight());

        gc.setFill(save);
    }
}
