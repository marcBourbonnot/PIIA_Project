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

    /**
     * Getter du graphic context
     *
     * @return
     */
    public GraphicsContext getGC() {
        return gc;
    }

    public void setGC(GraphicsContext gc) {
        this.gc = gc;
    }

    /**
     * Methode qui permet de faire le dessin de toutes les formes du modèle
     */
    public void draw() {
        this.clear();
        this.ctrl.getMdl().getFormes().forEach(e -> e.draw(this.gc));
    }

    /**
     * Methode qui permet de clear la zone de dessin
     */
    public void clear() {
        gc.clearRect(0, 0, this.zoneDessin.getDrawArea().getWidth(), this.zoneDessin.getDrawArea().getHeight());
    }

    /**
     * Permet d'aajouter les actions au canvas lors des événements souris
     */
    public void addActions() {
        Canvas cnvs = this.zoneDessin.getDrawArea();

        cnvs.setOnMousePressed(e -> {
            if (this.ctrl.getMenuCtrl().isDessinMode()) {
                //etat dessin
                this.ctrl.getMdl().startDrawPoint(e);
            } else {
                //etat selection
                this.ctrl.getMdl().attrape(e);
            }
        });

        cnvs.setOnMouseDragged(e -> {
            if (this.ctrl.getMenuCtrl().isDessinMode()) {
                //etat dessin
                this.ctrl.getMdl().tempDraw(e);
            } else {
                //etat redimension
                if (this.ctrl.getMdl().isRedimMode())
                    this.ctrl.getMdl().redimension(e);

                //etat selection
                this.ctrl.getMdl().deplace(e);
            }
        });

        cnvs.setOnMouseReleased(e -> {
            if (this.ctrl.getMenuCtrl().isDessinMode()) {
                //etat dessin
                this.ctrl.getMdl().endDrawPoint(e);
            } else {
                //etat selection
                this.ctrl.getMdl().lache(e);
            }
        });
    }
}
