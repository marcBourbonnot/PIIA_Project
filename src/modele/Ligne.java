package modele;

import javafx.scene.canvas.GraphicsContext;

public class Ligne extends Forme {

    public Ligne(int x, int y, int width, int height, String text) {
        super(x, y, 0, 0, "");
    }

    public Ligne(int x, int y) {
        super(x, y);
    }

    public Ligne(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public boolean estDedans(double x, double y) {
        return false;
    }

    @Override
    public void draw(GraphicsContext gc) {
    }
}
