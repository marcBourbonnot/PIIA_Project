package modele;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class Rectangle extends Forme {

    //Constructeurs
    public Rectangle(int x, int y, int width, int height, String text) {
        super(x, y, width, height, text);
    }

    public Rectangle(int x, int y) {
        super(x, y);
    }

    public Rectangle(int x, int y, int width, int height) {
        super(x, y, width, height);
    }


    //Methodes
    @Override
    public boolean estDedans(double x, double y) {
        return (x-this.getX())<this.getWidth() && (x-this.getX())>0 && (y-this.getY())<this.getHeight() && (y-this.getY())>0;
    }

    @Override
    public void draw(GraphicsContext gc) {
        Paint save = gc.getFill();

        gc.setFill(this.clr);
        gc.fillRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());

        gc.setFill(save);
    }
}
