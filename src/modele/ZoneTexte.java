package modele;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class ZoneTexte extends Forme{

    private TextArea texte;

    //Constructeurs
    public ZoneTexte() {
        super();
        this.texte = new TextArea();
        this.setClr(Color.GRAY);
    }

    public ZoneTexte(double x, double y, double width, double height, String text) {
        super(x, y, width, height, text);
        this.texte = new TextArea();
    }

    public ZoneTexte(double x, double y) {
        super(x, y);
        this.texte = new TextArea();
    }

    public ZoneTexte(double x, double y, double width, double height) {
        super(x, y, width, height);
        this.texte = new TextArea();
    }


    @Override
    public boolean estDedans(double x, double y) {
        return x >= this.getX() && x <= this.getWidth() && y >= this.getY() && y <= this.getHeight();
    }

    @Override
    public void draw(GraphicsContext gc) {
        Paint save = gc.getFill();
        gc.setFill(this.getClr());
        gc.fillPolygon(new double[]{this.getX(), this.getWidth(), this.getWidth(), this.getX()}, new double[]{this.getY(), this.getY(), this.getHeight(), this.getHeight()}, 4);

        gc.setFill(save);
    }

    @Override
    public Point2D getCenter() {
        return new Point2D((this.getWidth() - this.getX()) / 2, (this.getHeight() - this.getY()) / 2);
    }
}
