package modele;

import control.Control;
import javafx.scene.input.MouseEvent;
import vue.View;

import java.util.ArrayList;

public class Model {
    View view;
    Control ctrl;

    Forme selectedForme;
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

    public Forme getSelectedForme() {
        return selectedForme;
    }

    public void setSelectedForme(Forme selectedForme) {
        this.selectedForme = selectedForme;
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

    public void startDrawPoint(MouseEvent e) {
        System.out.println("start draw point");
        System.out.println("x: " + e.getX() + " y: " + e.getY());

        System.out.println(this.ctrl.getMdl().getSelectedForme());
        this.getSelectedForme().setX(10);
        this.getSelectedForme().setY(10);
//        this.getSelectedForme().setX((int) e.getX());
//        this.getSelectedForme().setY((int) e.getY());
    }

    public void endDrawPoint(MouseEvent e) {
        System.out.println("end draw point");
        System.out.println("x: " + e.getX() + " y: " + e.getY());
        this.getSelectedForme().setX(20);
        this.getSelectedForme().setY(20);

//        this.getSelectedForme().setWidth((int) e.getX());
//        this.getSelectedForme().setHeight((int) e.getY());
//        this.getSelectedForme().setDrawable(true);
//
//        this.getFormes().add(this.selectedForme);
//        System.out.println(this.getForme(0));
//        this.getCtrl().getCvsCtrl().draw();
    }
}
