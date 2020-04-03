package vue;

import javafx.scene.canvas.Canvas;

public class ZoneDessin {
    private Canvas drawArea;

    private int width;
    private int height;

    public ZoneDessin(int width, int height) {
        this.width = width;
        this.height = height;
        this.drawArea = new Canvas(width, height);
    }

    public Canvas getDrawArea() {
        return drawArea;
    }

    public void setDrawArea(Canvas drawArea) {
        this.drawArea = drawArea;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
}
