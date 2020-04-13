package modele;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

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
        return (x-this.getX())<this.getWidth() && (x-this.getX())>0 && (y-this.getY())<this.getHeight() && (y-this.getY())>0;
    }

    @Override
    public void draw(GraphicsContext gc) {
        Paint save = gc.getFill();

        gc.setFill(this.getClr());

        gc.fillPolygon(new double[]{this.getX(), this.getWidth(), this.getWidth(), this.getX()}, new double[]{this.getY(), this.getY(), this.getHeight(), this.getHeight()}, 4);
        gc.strokePolygon(new double[]{this.getX(), this.getWidth(), this.getWidth(), this.getX()}, new double[]{this.getY(), this.getY(), this.getHeight(), this.getHeight()}, 4);

        gc.setFill(save);
    }
}
