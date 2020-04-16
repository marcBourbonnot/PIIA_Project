package modele;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Paint;

public class Ligne extends Forme {
    public final static int MARGE = 20;

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
    /*public boolean estDedans(double x, double y) {
        return x >= this.getX() && x <= this.getWidth() && y >= this.getY() && y <= this.getHeight();
    }*/
    public boolean estDedans(double x, double y){
        //calcul de l'equation de la droite
        double a = (this.getHeight()-this.getY())/(this.getWidth()-this.getX());
        double b = this.getY()-a*this.getX();
        double btest = 0;

        double bMax = b+MARGE;
        double bMin = b-MARGE;

        if(a>Double.MAX_VALUE){
            btest = y;
        }else{
            btest = y - a*x;
        }

        if(x >= this.getX()-MARGE && x<=this.getWidth()-MARGE){
            return btest >= bMin && btest <= bMax;
        }
        if(btest == y){
            if(y >= this.getY() && y <= this.getHeight()){
                return x >= this.getX()-MARGE && x <= this.getX()+MARGE;
            }
        }
        return false;
    }

    @Override
    public void draw(GraphicsContext gc) {
        Paint save = gc.getFill();

        gc.setFill(this.getClr());
        gc.strokeLine(this.getX(), this.getY(), this.getWidth(), this.getHeight());

        gc.setFill(save);
    }

    @Override
    public Point2D getCenter() {
        return new Point2D((this.getWidth() - this.getX()) / 2, (this.getHeight() - this.getY()) / 2);
    }
}
