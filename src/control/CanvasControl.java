package control;

import javafx.scene.canvas.GraphicsContext;
import modele.Model;
import vue.View;
import vue.ZoneDessin;

public class CanvasControl {
    Control ctrl;

    ZoneDessin zoneDessin;
    private GraphicsContext g;


    public CanvasControl(Control c) {
        this.ctrl = c;

        this.zoneDessin = new ZoneDessin();
    }

    public ZoneDessin getZoneDessin() {
        return zoneDessin;
    }

    public void draw() {
        g.clearRect(0, 0, ZoneDessin.WIDTH, ZoneDessin.HEIGHT);

        this.ctrl.getMdl().getFormes().forEach(e -> e.draw(this.g));
    }

}
