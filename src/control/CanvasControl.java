package control;

import javafx.scene.canvas.Canvas;
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

        this.addActions();
    }

    public ZoneDessin getZoneDessin() {
        return zoneDessin;
    }

    public void draw() {
        g.clearRect(0, 0, ZoneDessin.WIDTH, ZoneDessin.HEIGHT);

        this.ctrl.getMdl().getFormes().forEach(e -> e.draw(this.g));
    }

    public void addActions() {
        Canvas cnvs = this.zoneDessin.getDrawArea();

        cnvs.setOnMousePressed(e -> {
//            if (this.ctrl.getMdl().getSelectedForme().isDrawable()) {
//                //Cas où il faut selectionner la forme
//            } else {
                //Il faut créer une nouvelle forme
                this.ctrl.getMdl().startDrawPoint(e);
//            }
        });

        cnvs.setOnMouseReleased(e -> {
//            if (this.ctrl.getMdl().getSelectedForme().isDrawable()) {
//                //Cas où il faut selectionner la forme
//            } else {
                //Il faut créer une nouvelle forme
                this.ctrl.getMdl().endDrawPoint(e);
//            }
        });
    }
}
