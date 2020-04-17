package modele;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Ellipse extends Forme {
    public Ellipse() {
    }

    public Ellipse(double x, double y) {
        super(x, y);
    }

    public Ellipse(double x, double y, double width, double height) {
        super(x, y, width, height);
    }

    public Ellipse(double x, double y, double w, double h, String text, Color clr, boolean drawable, double epaisseurBord, Color clrBord) {
        super(x, y, w, h, text, clr, drawable, epaisseurBord, clrBord);
    }

    @Override
    public boolean estDedans(double x, double y) {
        return false;
    }

    @Override
    public void draw(GraphicsContext gc) {
        Paint save = gc.getFill();

        gc.setFill(this.getClr());

        gc.strokeOval(this.getWidth(), this.getHeight(), this.getX(), this.getY());
        gc.strokeLine(this.getX(), this.getY(), this.getWidth(), this.getHeight());
        //gc.strokeLine(this.getX() - this.getWidth(), this.getY() - this.getHeight(), 2 * this.getWidth(), 2 * this.getHeight());

        gc.setFill(save);
    }

    @Override
    public Point2D getCenter() {
        return null;
    }
}

