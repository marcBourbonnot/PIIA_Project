package vue;

import javafx.scene.canvas.Canvas;

public class ZoneDessin {
    private Canvas drawArea;

    public final static int WIDTH = 1100;
    public final static int HEIGHT = 800;

    public ZoneDessin() {
        this.drawArea = new Canvas(ZoneDessin.WIDTH, ZoneDessin.HEIGHT);
    }

    public Canvas getDrawArea() {
        return drawArea;
    }

    public void setDrawArea(Canvas drawArea) {
        this.drawArea = drawArea;
    }
}
