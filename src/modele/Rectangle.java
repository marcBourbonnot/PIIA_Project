package modele;

import javafx.scene.canvas.GraphicsContext;

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
    boolean estDedans(double x, double y) {
        return (x-this.getX())<this.getWidth() && (x-this.getX())>0 && (y-this.getY())<this.getHeight() && (y-this.getY())>0;
    }

    @Override
    void draw(GraphicsContext gc) {

    }
}
