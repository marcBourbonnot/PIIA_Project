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

        gc.fillOval(this.getX(), this.getY(), this.getWidth() - this.getX(), this.getHeight() - this.getY());
        gc.strokeOval(this.getX(), this.getY(), this.getWidth() - this.getX(), this.getHeight() - this.getY());

        gc.setFill(save);
    }

    @Override
    public Point2D getCenter() {
        return new Point2D(this.getX() + ((this.getWidth() - this.getX()) / 2), this.getY() + ((this.getHeight() - this.getY()) / 2));
    }
}

