package modele;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

public class Ligne extends Forme {

    public Ligne() {
        super();
    }

    public Ligne(double x, double y, double width, double height, String text) {
        super(x, y, 0, 0, "");
    }

    public Ligne(double x, double y) {
        super(x, y);
    }

    public Ligne(double x, double y, double width, double height) {
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
        gc.strokeLine(this.getX(), this.getY(), this.getWidth(), this.getHeight());

        gc.setFill(save);
    }
}
