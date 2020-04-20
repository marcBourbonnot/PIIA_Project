package modele;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
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

    public Ligne(double x, double y, double w, double h, String text, Color clr, boolean drawable, double epaisseurBord, Color clrBord) {
        super(x, y, w, h, text, clr, drawable, epaisseurBord, clrBord);
    }

    /**
     * Teste si la souris est proche de la ligne
     *
     * @param x abscisse d'un point
     * @param y ordonnee d'un point
     * @return
     */
    @Override
    public boolean estDedans(double x, double y) {
        //ligne verticale
        if (Math.abs(this.getWidth() - this.getX()) <= MARGE) {
            return x <= (this.getX() + MARGE) && x >= (this.getX() - MARGE) && y <= this.getHeight() && y >= this.getY();
        }
        //ligne horizontale
        if (Math.abs(this.getHeight() - this.getY()) <= MARGE) {
            return x <= this.getWidth() && x >= this.getX() && y >= this.getY() - MARGE && y <= this.getY() + MARGE;
        }

        //ligne quelconque
        //calcul de l'expression de la droite
        double a = (this.getY() - this.getHeight()) / (this.getX() - this.getWidth());
        double b = this.getY() - (a * this.getX());

        //calcul du b pour la droite passant par ma souris et qui est parallèle à ma ligne
        double bSouris = y - (a * x);
        return Math.abs(b - bSouris) <= MARGE && x >= Math.min(this.getX(), this.getWidth()) && x <= Math.max(this.getX(), this.getWidth()) && y >= Math.min(this.getY(), this.getHeight()) && y <= Math.max(this.getY(), this.getHeight());
    }

    /**
     * dessine la ligne
     *
     * @param gc GraphicsContext du canvas de la vue
     */
    @Override
    public void draw(GraphicsContext gc) {
        Paint save = gc.getFill();

        gc.setStroke(this.getClr());
        gc.setLineWidth(this.getEpaisseurBord());
        gc.strokeLine(this.getX(), this.getY(), this.getWidth(), this.getHeight());

        gc.setFill(save);
    }

    /**
     * calcule le centre de la ligne
     *
     * @return
     */
    @Override
    public Point2D getCenter() {
        return new Point2D((this.getWidth() - this.getX()) / 2, (this.getHeight() - this.getY()) / 2);
    }
}
