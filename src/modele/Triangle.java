package modele;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

public class Triangle extends Forme {

    public static final int DISTPOINTS = 20;

    //Constructeurs
    public Triangle(){
        super();
    }
    public Triangle(double x, double y, double width, double height, String text) {
        super(x, y, width, height, text);
    }

    public Triangle(double x, double y) {
        super(x, y);
    }

    public Triangle(double x, double y, double width, double height) {
        super(x, y, width, height);
    }


    //Methodes
    @Override
    public boolean estDedans(double x, double y) {
        return false;
    }

    @Override
    public void draw(GraphicsContext gc) {
        Paint save = gc.getFill();

        gc.setFill(this.getClr());
        gc.fillPolygon(new double[]{this.getX(), this.getX() - DISTPOINTS, this.getX() + DISTPOINTS}, new double[]{this.getY(), this.getY() + DISTPOINTS, this.getY() + DISTPOINTS},3);
        gc.strokePolygon(new double[]{this.getX(), this.getX() - DISTPOINTS, this.getX() + DISTPOINTS}, new double[]{this.getY(), this.getY() + DISTPOINTS, this.getY() + DISTPOINTS},3);

        gc.setFill(save);
    }
}
