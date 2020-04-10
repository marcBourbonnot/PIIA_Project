package control;

import modele.Model;
import vue.View;

public class Control {
    Model mdl;
    View view;

    CanvasControl cvsCtrl;
    MenuAppControl menuCtrl;

    public Control(Model mdl, View view) {
        this.mdl = mdl;
        this.view = view;

        this.cvsCtrl = new CanvasControl(this);
        this.menuCtrl = new MenuAppControl(this);
    }

    public Model getMdl() {
        return mdl;
    }

    public void setMdl(Model mdl) {
        this.mdl = mdl;
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public CanvasControl getCvsCtrl() {
        return cvsCtrl;
    }

    public void setCvsCtrl(CanvasControl cvsCtrl) {
        this.cvsCtrl = cvsCtrl;
    }

    public MenuAppControl getMenuCtrl() {
        return menuCtrl;
    }

    public void setMenuCtrl(MenuAppControl menuCtrl) {
        this.menuCtrl = menuCtrl;
    }
}
