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

    @Override
    /*public boolean estDedans(double x, double y) {
        return x >= this.getX() && x <= this.getWidth() && y >= this.getY() && y <= this.getHeight();
    }*/
    public boolean estDedans(double x, double y){
        //calcul de l'equation de la droite
        /*double a =(this.getHeight()-this.getY())/(this.getWidth()-this.getX());
        double b = this.getY()-a*this.getX();
        double btest = 0;

        System.out.println("coef directeur : "+a);
        double bMax = b+MARGE;
        double bMin = b-MARGE;*/

        /*if(a>30){
            btest = y;
        }else{
            btest = y - a*x;
        }

        if(x >= this.getX()-MARGE && x<=this.getWidth()+MARGE){
            return btest >= bMin && btest <= bMax;
        }
        if(btest == y){
            if(y >= this.getY()-MARGE && y <= this.getHeight()+MARGE){
                return x >= this.getX()-MARGE && x <= this.getWidth()+MARGE;
            }
        }*/

        //return (x-this.getX())<this.getWidth() && (x-this.getX())>0 && (y-this.getY())<this.getHeight() && (y-this.getY())>0;

        //ligne verticale
        if(Math.abs(this.getWidth()-this.getX()) <= MARGE){
            System.out.println("ligne verticale");
            return x<=(this.getX()+MARGE) && x>=(this.getX()-MARGE) && y<=this.getHeight() && y>=this.getY() ;
        }
        //ligne horizontale
        if(Math.abs(this.getHeight()-this.getY())<= MARGE){
            System.out.println("ligne horizontale");
            return x<=this.getWidth() && x>= this.getX() && y >=this.getY()-MARGE && y<=this.getY()+MARGE;
        }

        //ligne quelconque
        //calcul de l'expression de la droite
        double a =  (this.getY()-this.getHeight())/(this.getX()-this.getWidth());
        double b = this.getY() -(a*this.getX());

        //calcul du b pour la droite passant par ma souris et qui est parallèle à ma ligne
        double bSouris = y - (a*x);


        return Math.abs(b-bSouris)<= MARGE && x>=Math.min(this.getX(), this.getWidth()) && x<= Math.max(this.getX(), this.getWidth()) && y>=Math.min(this.getY(), this.getHeight()) && y<=Math.max(this.getY(), this.getHeight());


        //return false;




    }

    @Override
    public void draw(GraphicsContext gc) {
        Paint save = gc.getFill();

        gc.setStroke(this.getClr());
        gc.setLineWidth(this.getEpaisseurBord());
        gc.strokeLine(this.getX(), this.getY(), this.getWidth(), this.getHeight());

        gc.setFill(save);
    }

    @Override
    public Point2D getCenter() {
        return new Point2D((this.getWidth() - this.getX()) / 2, (this.getHeight() - this.getY()) / 2);
    }
}
