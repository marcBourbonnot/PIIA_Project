package control;

import vue.barre_outils.BarOutils;

public class BarreOutilsControl {
    Control ctrl;

    private BarOutils barreOutils;

    public BarreOutilsControl(Control ctrl) {
        this.ctrl = ctrl;

        this.barreOutils = new BarOutils();
    }

    public BarOutils getBarreOutils() {
        return barreOutils;
    }

    public void setBarreOutils(BarOutils outils) {
        this.barreOutils = outils;
    }
}
