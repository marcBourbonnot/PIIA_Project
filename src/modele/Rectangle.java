package modele;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

import java.awt.*;

public class Rectangle extends Forme {

    //Constructeurs
    public Rectangle() {
        super();
    }

    public Rectangle(double x, double y, double width, double height, String text) {
        super(x, y, width, height, text);
    }

    public Rectangle(double x, double y) {
        super(x, y);
    }

    public Rectangle(double x, double y, double width, double height) {
        super(x, y, width, height);
    }


    //Methodes
    @Override
    public boolean estDedans(double x, double y) {
        return x >= this.getX() && x <= this.getWidth() && y >= this.getY() && y <= this.getHeight();
    }

    @Override
    public void draw(GraphicsContext gc) {
        Paint save = gc.getFill();

        gc.setFill(this.getClr());

        gc.fillPolygon(new double[]{this.getX(), this.getWidth(), this.getWidth(), this.getX()}, new double[]{this.getY(), this.getY(), this.getHeight(), this.getHeight()}, 4);
        gc.strokePolygon(new double[]{this.getX(), this.getWidth(), this.getWidth(), this.getX()}, new double[]{this.getY(), this.getY(), this.getHeight(), this.getHeight()}, 4);

        gc.setFill(save);
    }

    @Override
    public Point2D getCenter() {
        return new Point2D((this.getWidth() - this.getX()) / 2, (this.getHeight() - this.getY()) / 2);
    }
}
