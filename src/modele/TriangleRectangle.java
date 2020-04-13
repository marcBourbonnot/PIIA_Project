package modele;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

public class TriangleRectangle extends Forme {
    //Constructeurs
    public TriangleRectangle(){
        super();
    }

    public TriangleRectangle(double x, double y, double width, double height, String text) {
        super(x, y, width, height, text);
    }

    public TriangleRectangle(double x, double y) {
        super(x, y);
    }

    public TriangleRectangle(double x, double y, double width, double height) {
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

        gc.fillPolygon(new double[]{this.getX(), this.getWidth(), this.getX()}, new double[]{this.getY(), this.getHeight(), this.getHeight()},3);
        gc.strokePolygon(new double[]{this.getX(), this.getWidth(), this.getX()}, new double[]{this.getY(), this.getHeight(), this.getHeight()},3);

        gc.setFill(save);
    }
}
