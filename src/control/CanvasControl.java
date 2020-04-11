package control;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import vue.ZoneDessin;

public class CanvasControl {
    private Control ctrl;

    private ZoneDessin zoneDessin;
    private GraphicsContext gc;


    public CanvasControl(Control c) {
        this.ctrl = c;

        this.zoneDessin = new ZoneDessin();

        this.gc = this.zoneDessin.getDrawArea().getGraphicsContext2D();

        this.addActions();
    }

    public ZoneDessin getZoneDessin() {
        return zoneDessin;
    }

    public GraphicsContext getGC() {
        return gc;
    }

    public void setGC(GraphicsContext gc) {
        this.gc = gc;
    }

    public void draw() {
        gc.clearRect(0, 0, ZoneDessin.WIDTH, ZoneDessin.HEIGHT);
        this.ctrl.getMdl().getFormes().forEach(e -> e.draw(this.gc));
    }

    public void clear() {
        gc.clearRect(0, 0, ZoneDessin.WIDTH, ZoneDessin.HEIGHT);
    }

    public void addActions() {
        Canvas cnvs = this.zoneDessin.getDrawArea();

        cnvs.setOnMousePressed(e -> {
            this.ctrl.getMdl().startDrawPoint(e);
        });

        cnvs.setOnMouseDragged(e -> {
            this.ctrl.getMdl().tempDraw(e);
        });

        cnvs.setOnMouseReleased(e -> {
            this.ctrl.getMdl().endDrawPoint(e);
        });
    }
}
