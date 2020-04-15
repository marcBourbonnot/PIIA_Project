package vue;

import control.BarreOutilsControl;
import control.CanvasControl;
import control.Control;
import control.MenuAppControl;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import modele.Model;
import vue.barre_outils.BarOutils;


public class View {

    private Model mdl;
    private Control ctrl;

    private MenuAppControl menu;
    private CanvasControl dessin;
    private BarreOutilsControl bOutils;

    private VBox mainView;

    public final static int WIDTH = 1000;
    public final static int HEIGHT = 750;


    //Constructeur
    public View() {
        this.mdl = new Model(this);
        this.ctrl = new Control(this.mdl, this);
        this.mdl.setCtrl(this.ctrl);

        this.menu = this.ctrl.getMenuCtrl();
        this.dessin = this.ctrl.getCvsCtrl();
        this.bOutils = this.ctrl.getbOutilsCtrl();

        menu.addActions();

        this.mainView = new VBox(this.menu.getMapp().getMenuBar(), new HBox(this.dessin.getZoneDessin().getDrawArea(),this.bOutils.getBarreOutils().getOutils()));
    }


    //Getters and Setters


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

    public BarreOutilsControl getbOutils() {
        return bOutils;
    }

    public void setbOutils(BarreOutilsControl bOutils) {
        this.bOutils = bOutils;
    }

    public VBox getMainView() {
        return mainView;
    }

    public Model getMdl() {
        return mdl;
    }

    public void setMdl(Model mdl) {
        this.mdl = mdl;
    }

    public Control getCtrl() {
        return ctrl;
    }

    public void setCtrl(Control ctrl) {
        this.ctrl = ctrl;
    }
}
