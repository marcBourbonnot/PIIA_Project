package modele;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

public class ZoneTexte extends Forme{
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

    @Override
    public boolean estDedans(double x, double y) {
        return y <= this.getY()+this.fontSize && y>= this.getY()-this.fontSize && x>= this.getX() && x<= this.getX()+( this.getText().length()*(this.fontSize/2));
    }

    @Override
    public void draw(GraphicsContext gc) {
        Paint save = gc.getFill();
        gc.setFill(this.getClr());

        gc.setFont(new Font(this.fontSize));
        gc.setStroke(this.getClrBord());
        gc.strokeText(this.getText(), this.getX(), this.getY());

        gc.setFill(save);
    }

    @Override
    public Point2D getCenter() {
        return new Point2D((this.getWidth() - this.getX()) / 2, (this.getHeight() - this.getY()) / 2);
    }



}
