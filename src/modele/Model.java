package modele;

import control.Control;
import javafx.scene.input.MouseEvent;
import vue.View;
import vue.ZoneDessin;

import java.util.ArrayList;

public class Model {
    View view;
    Control ctrl;

    Formes newFormeTypeSelected;
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

    public Formes getTypeSelected() {
        return newFormeTypeSelected;
    }

    public void setTypeSelected(Formes typeSelected) {
        this.newFormeTypeSelected = typeSelected;
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
        this.formes = new ArrayList<>();
        this.ctrl.getCvsCtrl().clear();
    }

    public void startDrawPoint(MouseEvent e) {
        if(this.newFormeTypeSelected != null) {
            this.getSelectedForme().setX(e.getX());
            this.getSelectedForme().setY(e.getY());
        }
    }

    public void tempDraw(MouseEvent e) {
        if(this.newFormeTypeSelected != null) {
            this.getCtrl().getCvsCtrl().getZoneDessin().getDrawArea().getGraphicsContext2D().clearRect(0, 0, ZoneDessin.WIDTH, ZoneDessin.HEIGHT);
            this.getCtrl().getCvsCtrl().draw();

            this.selectedForme.setWidth(e.getX());
            this.selectedForme.setHeight(e.getY());

            this.getSelectedForme().draw(this.ctrl.getCvsCtrl().getGC());
        }
    }

    public void endDrawPoint(MouseEvent e) {
        if(this.newFormeTypeSelected != null) {
            this.getSelectedForme().setWidth(e.getX());
            this.getSelectedForme().setHeight(e.getY());
            this.getSelectedForme().setDrawable(true);

            this.getFormes().add(this.selectedForme);
            this.getFormes().forEach( em -> System.out.println("Formes: " + em.coordsToString()));
            this.getCtrl().getCvsCtrl().draw();

            System.out.println(this.selectedForme.coordsToString());

            this.newForme();
        }
    }

    public void newForme() {
        switch (this.newFormeTypeSelected){
            case LIGNE:
                this.setSelectedForme(new Ligne());
                break;
            case RECTANGLE:
                this.setSelectedForme(new Rectangle());
                break;
            case TRIANGLE_ISOCELE:
                this.setSelectedForme(new TriangleIsocele());
                break;
            case TRIANGLE_RECTANGLE:
                this.setSelectedForme(new TriangleRectangle());
                break;
            case ELLIPSE:
                this.setSelectedForme(new Ellipse());
                break;
        }
    }
}
