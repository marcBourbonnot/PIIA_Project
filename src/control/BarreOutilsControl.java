package control;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.*;
import vue.BarreOutils;

public class BarreOutilsControl {
    private Control ctrl;

    private BarreOutils barreOutils;

    public BarreOutilsControl(Control ctrl) {
        this.ctrl = ctrl;

        this.barreOutils = new BarreOutils();

        this.addActions();
    }

    public BarreOutils getBarreOutils() {
        return barreOutils;
    }

    private void addActions() {
        ObservableList<Node> tb = this.getBarreOutils().getOutils().getItems();

        ButtonBase btn = (ToggleButton) tb.get(2);
        btn.setOnAction(e -> {
            RadioMenuItem select = (RadioMenuItem) this.ctrl.getMenuCtrl().getMapp().getMenuBar().getMenus().get(3).getItems().get(0);
            select.fire();
        });

        btn = (ToggleButton) tb.get(4);
        btn.setOnAction(e -> {
            CheckMenuItem select = (CheckMenuItem) this.ctrl.getMenuCtrl().getMapp().getMenuBar().getMenus().get(3).getItems().get(1);
            select.fire();
        });

        btn = (ToggleButton) tb.get(7);
        btn.setOnAction(e -> {
            RadioMenuItem select = (RadioMenuItem) ((Menu) this.ctrl.getMenuCtrl().getMapp().getMenuBar().getMenus().get(2).getItems().get(0)).getItems().get(0);
            select.fire();
        });

        btn = (ToggleButton) tb.get(8);
        btn.setOnAction(e -> {
            RadioMenuItem select = (RadioMenuItem) ((Menu) this.ctrl.getMenuCtrl().getMapp().getMenuBar().getMenus().get(2).getItems().get(0)).getItems().get(1);
            select.fire();
        });

        btn = (ToggleButton) tb.get(9);
        btn.setOnAction(e -> {
            RadioMenuItem select = (RadioMenuItem) ((Menu) ((Menu) this.ctrl.getMenuCtrl().getMapp().getMenuBar().getMenus().get(2).getItems().get(0)).getItems().get(2)).getItems().get(0);
            select.fire();
        });

        btn = (ToggleButton) tb.get(10);
        btn.setOnAction(e -> {
            RadioMenuItem select = (RadioMenuItem) ((Menu) ((Menu) this.ctrl.getMenuCtrl().getMapp().getMenuBar().getMenus().get(2).getItems().get(0)).getItems().get(2)).getItems().get(1);
            select.fire();
        });

        btn = (ToggleButton) tb.get(11);
        btn.setOnAction(e -> {
            RadioMenuItem select = (RadioMenuItem) ((Menu) this.ctrl.getMenuCtrl().getMapp().getMenuBar().getMenus().get(2).getItems().get(0)).getItems().get(3);
            select.fire();
        });

        btn = (Button) tb.get(13);
        btn.setOnAction(e -> {
            MenuItem select = this.ctrl.getMenuCtrl().getMapp().getMenuBar().getMenus().get(2).getItems().get(1);
            select.fire();
        });

        btn = (Button) tb.get(14);
        btn.setOnAction(e -> {
            MenuItem select = this.ctrl.getMenuCtrl().getMapp().getMenuBar().getMenus().get(2).getItems().get(2);
            select.fire();
        });
    }
}
