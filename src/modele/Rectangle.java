package modele;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;
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

    public Rectangle(double x, double y, double w, double h, String text, Color clr, boolean drawable, double epaisseurBord, Color clrBord) {
        super(x, y, w, h, text, clr, drawable, epaisseurBord, clrBord);
    }


    //Methodes

    /**
     * Teste si la souris se trouve dans le rectangle
     * @param x abscisse d'un point
     * @param y ordonnee d'un point
     * @return
     */
    @Override
    public boolean estDedans(double x, double y) {
        System.out.println("je suis dans estDedans rec");
        return x >= this.getX() && x <= this.getWidth() && y >= this.getY() && y <= this.getHeight() || x <= this.getX() && x >= this.getWidth() && y <= this.getY() && y >= this.getHeight();
    }

    /**
     * Dessine le rectangle
     * @param gc GraphicsContext du canvas de la vue
     */
    @Override
    public void draw(GraphicsContext gc) {
        Paint save = gc.getFill();

        gc.setFill(this.getClr());

        gc.fillPolygon(new double[]{this.getX(), this.getWidth(), this.getWidth(), this.getX()}, new double[]{this.getY(), this.getY(), this.getHeight(), this.getHeight()}, 4);

        gc.setStroke(this.getClrBord());
        gc.setLineWidth(this.getEpaisseurBord());
        gc.strokePolygon(new double[]{this.getX(), this.getWidth(), this.getWidth(), this.getX()}, new double[]{this.getY(), this.getY(), this.getHeight(), this.getHeight()}, 4);

        gc.setFill(save);
    }

    /**
     * Calcule le centre du rectangle
     * @return
     */
    @Override
    public Point2D getCenter() {
        return new Point2D(this.getX() + (this.getWidth() - this.getX()) / 2, this.getY() + (this.getHeight() - this.getY()) / 2);
    }
}
