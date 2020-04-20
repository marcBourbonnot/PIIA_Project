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

    /**
     * Teste si la souris est dans l'ellipse
     *
     * @param x abscisse d'un point
     * @param y ordonnee d'un point
     * @return
     */
    @Override
    public boolean estDedans(double x, double y) {
        //centre du côté AB
        double ab = this.calculDistance(this.getX(), this.getY(), this.getWidth(), this.getY());
        Point2D centreAB = new Point2D(this.getX() + (ab / 2), this.getY());

        //centre du côté AD
        double ad = this.calculDistance(this.getX(), this.getY(), this.getX(), this.getHeight());
        Point2D centreAD = new Point2D(this.getX(), this.getY() + (ad / 2));

        //centre du côté BC
        double bc = this.calculDistance(this.getWidth(), this.getY(), this.getWidth(), this.getHeight());
        Point2D centreBC = new Point2D(this.getWidth(), this.getY() + (bc / 2));

        //centre du côté CD
        double cd = this.calculDistance(this.getWidth(), this.getHeight(), this.getX(), this.getHeight());
        Point2D centreCD = new Point2D(this.getX() + (cd / 2), this.getHeight());

        //calcul de des demi-axes
        double r1 = calculDistance(centreAB.getX(), centreAB.getY(), centreCD.getX(), centreCD.getY()) / 2;
        double r2 = calculDistance(centreAD.getX(), centreAD.getY(), centreBC.getX(), centreBC.getY()) / 2;

        //l'ellipse n'est pas au centre du repère donc on doit soustraire ses coordonnées à ceux de ma souris
        double pX = x - this.getCenter().getX();
        double pY = y - this.getCenter().getY();

        //on fait le calcule
        double m1 = Math.pow(pX, 2) / Math.pow(r2, 2);
        double m2 = Math.pow(pY, 2) / Math.pow(r1, 2);
        double somme = m1 + m2;
        System.out.println("somme " + somme);
        return somme <= 1;

    }

    /**
     * Calcule la distance entre deux points
     *
     * @param x1
     * @param y1
     * @param x2
     * @param y2
     * @return
     */
    public double calculDistance(double x1, double y1, double x2, double y2) {
        return Math.sqrt(((x2 - x1) * (x2 - x1)) + ((y2 - y1) * (y2 - y1)));
    }


    /**
     * Dessine l'ellipse
     *
     * @param gc GraphicsContext du canvas de la vue
     */
    @Override
    public void draw(GraphicsContext gc) {
        Paint save = gc.getFill();

        gc.setFill(this.getClr());

        gc.fillOval(this.getX(), this.getY(), this.getWidth() - this.getX(), this.getHeight() - this.getY());

        gc.setStroke(this.getClrBord());
        gc.setLineWidth(this.getEpaisseurBord());
        gc.strokeOval(this.getX(), this.getY(), this.getWidth() - this.getX(), this.getHeight() - this.getY());


        gc.setFill(save);
    }

    /**
     * Renvoie le centre de l'ellipse
     *
     * @return
     */
    @Override
    public Point2D getCenter() {
        return new Point2D(this.getX() + ((this.getWidth() - this.getX()) / 2), this.getY() + ((this.getHeight() - this.getY()) / 2));

    }
}

