package modele;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

public class TriangleIsocele extends Forme {
    //Constructeurs
    public TriangleIsocele(){
        super();
    }

    public TriangleIsocele(double x, double y, double width, double height, String text) {
        super(x, y, width, height, text);
    }

    public TriangleIsocele(double x, double y) {
        super(x, y);
    }

    public TriangleIsocele(double x, double y, double width, double height) {
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

        gc.fillPolygon(new double[]{this.getX(), this.getWidth(), this.getX() - (this.getWidth() - this.getX())}, new double[]{this.getY(), this.getHeight(), this.getHeight()},3);
        gc.strokePolygon(new double[]{this.getX(), this.getWidth(), this.getX() - (this.getWidth() - this.getX())}, new double[]{this.getY(), this.getHeight(), this.getHeight()},3);

        gc.setFill(save);
    }

    @Override
    public Point2D getCenter() {
        return null;
    }
}
