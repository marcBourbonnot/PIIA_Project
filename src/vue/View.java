package vue;

import control.CanvasControl;
import control.Control;
import control.MenuAppControl;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import modele.Model;
import vue.barre_outils.BarOutils;


public class View {
    private Model mdl;
    private Control ctrl;

    private MenuAppControl menu;
    private CanvasControl dessin;
    private BarOutils bOutils;
    private int width = 1000;
    private int height = 750;

    private VBox mainView;

    public View() {
        this.mdl = new Model(this);
        this.ctrl = new Control(this.mdl, this);
        this.mdl.setCtrl(this.ctrl);

        this.menu = this.ctrl.getMenuCtrl();
        this.dessin = this.ctrl.getCvsCtrl();
        this.bOutils = new BarOutils(this);

        menu.addActions();

        this.mainView = new VBox(this.menu.getMapp().getMenuBar(), new HBox(this.dessin.getZoneDessin().getDrawArea(),this.bOutils.getOutils()));
    }

    public MenuAppControl getMenu() {
        return menu;
    }

    public void setMenu(MenuAppControl menu) {
        this.menu = menu;
    }

    public CanvasControl getDessin() {
        return dessin;
    }

    public void setDessin(CanvasControl dessin) {
        this.dessin = dessin;
    }

    public BarOutils getbOutils() {
        return bOutils;
    }

    public void setbOutils(BarOutils bOutils) {
        this.bOutils = bOutils;
    }

    public int getHeight() {
        return height;
    }

    public VBox getMainView() {
        return mainView;
    }
}
