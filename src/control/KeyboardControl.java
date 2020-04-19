package control;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;

public class KeyboardControl implements EventHandler<KeyEvent> {
    private Control ctrl;

    public KeyboardControl(Control ctrl) {
        this.ctrl = ctrl;
    }

    public Control getCtrl() {
        return ctrl;
    }

    public void setCtrl(Control ctrl) {
        this.ctrl = ctrl;
    }

    @Override
    public void handle(KeyEvent keyEvent) {
        KeyCombination ctrlC = new KeyCodeCombination(KeyCode.C, KeyCodeCombination.CONTROL_DOWN);
        KeyCombination ctrlV = new KeyCodeCombination(KeyCode.V, KeyCodeCombination.CONTROL_DOWN);
        KeyCombination ctrlS = new KeyCodeCombination(KeyCode.S, KeyCodeCombination.CONTROL_DOWN);
        KeyCombination ctrlO = new KeyCodeCombination(KeyCode.O, KeyCodeCombination.CONTROL_DOWN);
        KeyCombination ctrlE = new KeyCodeCombination(KeyCode.E, KeyCodeCombination.CONTROL_DOWN);
        KeyCombination ctrlQ = new KeyCodeCombination(KeyCode.Q, KeyCodeCombination.CONTROL_DOWN);
        KeyCombination ctrlN = new KeyCodeCombination(KeyCode.N, KeyCodeCombination.CONTROL_DOWN);

        if (ctrlC.match(keyEvent)) {
            this.ctrl.getMenuCtrl().getMapp().getMenuBar().getMenus().get(1).getItems().get(1).fire();
        }

        if (ctrlV.match(keyEvent)) {
            this.ctrl.getMenuCtrl().getMapp().getMenuBar().getMenus().get(1).getItems().get(2).fire();
        }

        if (ctrlN.match(keyEvent)) {
            this.ctrl.getMenuCtrl().getMapp().getMenuBar().getMenus().get(0).getItems().get(0).fire();
        }

        if (ctrlO.match(keyEvent)) {
            this.ctrl.getMenuCtrl().getMapp().getMenuBar().getMenus().get(0).getItems().get(1).fire();
        }

        if (ctrlS.match(keyEvent)) {
            this.ctrl.getMenuCtrl().getMapp().getMenuBar().getMenus().get(0).getItems().get(2).fire();
        }

        if (ctrlE.match(keyEvent)) {
            this.ctrl.getMenuCtrl().getMapp().getMenuBar().getMenus().get(0).getItems().get(3).fire();
        }

        if (ctrlQ.match(keyEvent)) {
            this.ctrl.getMenuCtrl().getMapp().getMenuBar().getMenus().get(0).getItems().get(4).fire();
        }

        if (keyEvent.getCode().equals(KeyCode.DELETE)) {
            this.ctrl.getMenuCtrl().getMapp().getMenuBar().getMenus().get(3).getItems().get(6).fire();
        }

    }
}
