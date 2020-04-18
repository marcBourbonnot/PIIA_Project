package modele;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class ZoneTexte extends Forme{
    //Constructeurs
    public ZoneTexte(String s) {
        super(10, 10);
        this.setText(s);
    }

    public ZoneTexte(double x, double y, double w, double h, String text, Color clr, boolean drawable, double epaisseurBord, Color clrBord) {
        super(x, y, w, h, text, clr, drawable, epaisseurBord, clrBord);
    }

    @Override
    public boolean estDedans(double x, double y) {
        return x >= this.getX() && x <= this.getWidth() && y >= this.getY() && y <= this.getHeight();
    }

    @Override
    public void draw(GraphicsContext gc) {
        Paint save = gc.getFill();
        gc.setFill(this.getClr());

        gc.setStroke(this.getClrBord());
        gc.strokeText(this.getText(), this.getX(), this.getY());

        gc.setFill(save);
    }

    @Override
    public Point2D getCenter() {
        return new Point2D((this.getWidth() - this.getX()) / 2, (this.getHeight() - this.getY()) / 2);
    }
}
