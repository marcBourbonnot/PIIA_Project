package modele;

import control.Control;
import vue.View;

import java.util.ArrayList;

public class Model {
    View view;
    Control ctrl;

    ArrayList<Forme> formes;

    public Model(View v) {
        this.view = v;

        this.formes = new ArrayList<>();
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public Control getCtrl() {
        return ctrl;
    }

    public void setCtrl(Control ctrl) {
        this.ctrl = ctrl;
    }

    public ArrayList<Forme> getFormes() {
        return formes;
    }

    public Forme getForme(int index) {
        return this.formes.get(index);
    }

    public int getNbFormes() {
        return this.formes.size();
    }

    public void addForme(Forme f) {
        this.formes.add(f);
        //this.ctrl;
    }

    public void removeForme(int index) {
        this.formes.remove(index);
    }

    public void clearFormes() {
        this.formes.forEach(e -> this.formes.remove(e));
    }
}
