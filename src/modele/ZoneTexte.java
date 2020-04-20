package modele;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

public class ZoneTexte extends Forme {
    private int fontSize;

    //Constructeurs
    public ZoneTexte(String s) {
        super(50, 100);
        this.fontSize = 30;

        this.setText(s);
    }

    public ZoneTexte(double x, double y, double w, double h, String text, Color clr, boolean drawable, double epaisseurBord, Color clrBord) {
        super(x, y, w, h, text, clr, drawable, epaisseurBord, clrBord);
    }


    public int getFontSize() {
        return fontSize;
    }

    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    /**
     * Teste si la souris est dans la zone de texte
     *
     * @param x abscisse d'un point
     * @param y ordonnee d'un point
     * @return
     */
    @Override
    public boolean estDedans(double x, double y) {
        return y <= this.getY() + this.fontSize && y >= this.getY() - this.fontSize && x >= this.getX() && x <= this.getX() + (this.getText().length() * (this.fontSize / 2));
    }

    /**
     * dessine la zone de texte
     *
     * @param gc GraphicsContext du canvas de la vue
     */
    @Override
    public void draw(GraphicsContext gc) {
        Paint save = gc.getFill();
        gc.setFill(this.getClr());

        gc.setFont(new Font(this.fontSize));
        gc.setStroke(this.getClrBord());
        gc.strokeText(this.getText(), this.getX(), this.getY());

        gc.setFill(save);
    }

    /**
     * calcule le centre de la zone de texte
     *
     * @return
     */
    @Override
    public Point2D getCenter() {
        //return new Point2D((this.getWidth() - this.getX()) / 2, (this.getHeight() - this.getY()) / 2);
        return new Point2D(((this.getX() + (this.getText().length() * (this.fontSize / 2))) - this.getX()) / 2, (this.getY() + this.fontSize - this.getY()) / 2);
    }

}
