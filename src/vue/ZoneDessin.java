package vue;

import javafx.scene.canvas.Canvas;

public class ZoneDessin {
    private Canvas drawArea;

    public ZoneDessin() {
        this.drawArea = new Canvas(View.WIDTH - BarreOutils.WIDTH, View.HEIGHT);
    }

    public Canvas getDrawArea() {
        return drawArea;
    }

    public void setDrawArea(Canvas drawArea) {
        this.drawArea = drawArea;
    }
}
