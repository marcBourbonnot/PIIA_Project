package control;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.WindowEvent;
import modele.Model;
import vue.View;

public class Control {
    Model mdl;
    View view;

    CanvasControl cvsCtrl;
    MenuAppControl menuCtrl;
    BarreOutilsControl bOutilsCtrl;

    public Control(Model mdl, View view) {
        this.mdl = mdl;
        this.view = view;

        this.cvsCtrl = new CanvasControl(this);
        this.menuCtrl = new MenuAppControl(this);
        this.bOutilsCtrl = new BarreOutilsControl(this);
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

    public BarreOutilsControl getbOutilsCtrl() {
        return bOutilsCtrl;
    }

    public void setbOutilsCtrl(BarreOutilsControl bOutilsCtrl) {
        this.bOutilsCtrl = bOutilsCtrl;
    }

    //Methodes
    public void comfirmQuit(WindowEvent e){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Etes-vous sûr de vouloir quitter sans sauvegarder ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        alert.showAndWait();
        if(alert.getResult() == ButtonType.NO || alert.getResult() == ButtonType.CANCEL) {
            alert.close();
            e.consume();
        }
    }

    public void comfirmNew(ActionEvent e) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Si vous faites cela sans sauvegarde, vous perdrez tout ce que vous avez fait ! Etes vous sûr ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        alert.showAndWait();

        if (alert.getResult() == ButtonType.YES) {
            this.view.getCtrl().getMdl().clearFormes();
        }else{
            e.consume();
        }
    }

    public void Quit(ActionEvent e) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Si vous faites cela sans sauvegarde, vous perdrez tout ce que vous avez fait ! Etes vous sûr ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
        alert.showAndWait();
        if(alert.getResult() == ButtonType.NO || alert.getResult() == ButtonType.CANCEL )
            e.consume();
        else
            System.exit(0);
    }
}
