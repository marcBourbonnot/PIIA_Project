package modele;

import control.Control;
import javafx.geometry.Point2D;
import javafx.scene.input.MouseEvent;
import vue.View;
import vue.ZoneDessin;

import java.util.ArrayList;
import java.util.Collections;

public class Model {
    View view;
    Control ctrl;

    Formes newFormeTypeSelected;
    Forme selectedForme;
    ArrayList<Forme> formes;
    boolean enDeplacement = false;
    int indexSelected;
    double x_souris;
    double y_souris;


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
            this.getCtrl().getCvsCtrl().draw();

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

    public void attrape(MouseEvent e) {
        for (int i = this.getFormes().size() - 1; i >= 0; i--) {
            Forme f= this.getFormes().get(i);
            if (f.estDedans(e.getX(), e.getY())) {
                this.selectedForme = this.getFormes().get(i);
                this.indexSelected = i;
                this.enDeplacement=true;
                this.x_souris = e.getX();
                this.y_souris = e.getY();
                break;
            }
        }
    }

    public void deplace(MouseEvent e) {
        if (this.enDeplacement) {
            double dx = e.getX() - x_souris;
            double dy = e.getY() - y_souris;

            this.getSelectedForme().setX(this.selectedForme.getX() + dx);
            this.getSelectedForme().setY(this.selectedForme.getY() + dy);
            this.getSelectedForme().setWidth(this.selectedForme.getWidth() + dx);
            this.getSelectedForme().setHeight(this.selectedForme.getHeight() + dy);

            x_souris = e.getX();
            y_souris = e.getY();

            this.ctrl.getCvsCtrl().draw();
        }
    }

    public void lache(MouseEvent e) {
        this.enDeplacement=false;
        //this.selectedForme = null;
    }

    public void movePremPlan() {
        if (this.selectedForme == null) return;

        System.out.println(this.selectedForme);

        Forme save = this.getFormes().get(this.indexSelected);
        this.getFormes().remove(this.indexSelected);
        this.getFormes().add(save);
        this.getCtrl().getCvsCtrl().draw();
    }

    public void moveArrPan() {
        if (this.selectedForme == null) return;

        Forme save = this.getFormes().get(this.indexSelected);
        this.getFormes().remove(this.indexSelected);

        Collections.reverse(this.getFormes());

        this.getFormes().add(save);

        Collections.reverse(this.getFormes());

        this.getCtrl().getCvsCtrl().draw();
    }

    private Point2D rotation(Point2D p, Point2D center, double angle) {
        angle *= Math.PI / 180;

        double xTemp = p.getX() - center.getX();
        double yTemp = p.getY() - center.getY();

        double x = xTemp * Math.cos(angle) + yTemp * Math.sin(angle) + center.getX();
        double y = - xTemp * Math.sin(angle) + yTemp * Math.cos(angle) + center.getY();

        Point2D res = new Point2D(x, y);

        return res;
    }

    public void rotateForme(double angle) {
        if (selectedForme != null) return;

        Point2D p = this.rotation(new Point2D(this.getSelectedForme().getX(), this.getSelectedForme().getY()), this.getSelectedForme().getCenter(), angle);
        this.getFormes().get(indexSelected).setX(p.getX());
        this.getFormes().get(indexSelected).setY(p.getY());

        Point2D p2 = this.rotation(new Point2D(this.getSelectedForme().getWidth(), this.getSelectedForme().getHeight()), this.getSelectedForme().getCenter(), angle);
        this.getFormes().get(indexSelected).setWidth(p2.getX());
        this.getFormes().get(indexSelected).setHeight(p2.getY());

        this.getCtrl().getCvsCtrl().draw();
    }
}
