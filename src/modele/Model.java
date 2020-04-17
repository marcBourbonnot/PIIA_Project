package modele;

import com.sun.source.tree.CaseTree;
import control.Control;
import javafx.geometry.Point2D;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import vue.View;

import java.io.File;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Optional;

public class Model {
    View view;
    Control ctrl;

    Formes newFormeTypeSelected;
    Forme newForme;
    ArrayList<Forme> formes;
    boolean enDeplacement = false;
    int indexSelected = -1;
    double x_souris;
    double y_souris;
    Forme copiedForme = null;


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
        return this.getForme(this.indexSelected);
    }

    public void setSelectedForme(Forme selectedForme) {
        this.getFormes().set(this.indexSelected, selectedForme);
    }

    public ArrayList<Forme> getFormes() {
        return formes;
    }

    public void setFormes(ArrayList<Forme> formes) {
        this.formes = formes;
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

    public Forme getNewForme() {
        return newForme;
    }

    public void setNewForme(Forme newForme) {
        this.newForme = newForme;
    }

    public void clearFormes() {
        this.formes = new ArrayList<>();
        this.ctrl.getCvsCtrl().clear();
    }

    public void startDrawPoint(MouseEvent e) {
        if(this.newFormeTypeSelected != null) {
            this.newForme.setX(e.getX());
            this.newForme.setY(e.getY());
        }
    }

    public void tempDraw(MouseEvent e) {
        if(this.newFormeTypeSelected != null) {

            this.getCtrl().getCvsCtrl().draw();

            this.newForme.setWidth(e.getX());
            this.newForme.setHeight(e.getY());

            this.newForme.draw(this.ctrl.getCvsCtrl().getGC());
        }
    }

    public void endDrawPoint(MouseEvent e) {
        if(this.newFormeTypeSelected != null) {
            this.newForme.setWidth(e.getX());
            this.newForme.setHeight(e.getY());
            this.newForme.setDrawable(true);

            this.getFormes().add(this.newForme);
            this.getCtrl().getCvsCtrl().draw();

            this.newForme();
        }
    }

    public void newForme() {
        this.indexSelected = -1;
        this.getCtrl().getMenuCtrl().lockSelection();
        switch (this.newFormeTypeSelected){
            case LIGNE:
                this.newForme = new Ligne();
                break;
            case RECTANGLE:
                this.newForme = new Rectangle();
                break;
            case TRIANGLE_ISOCELE:
                this.newForme = new TriangleIsocele();
                break;
            case TRIANGLE_RECTANGLE:
                this.newForme = new TriangleRectangle();
                break;
            case ELLIPSE:
                this.newForme = new Ellipse();
                break;
            case IMAGE:
                Image img = this.loadImage();
                if(img != null) {
                    this.newForme = new ImageNous(this.loadImage());
                    this.newForme.draw(this.ctrl.getCvsCtrl().getGC());
                    this.formes.add(this.newForme);
                }
                break;

            case TEXT:
                this.newForme = new ZoneTexte(this.loadText());
                this.newForme.draw(this.ctrl.getCvsCtrl().getGC());
                this.formes.add(this.newForme);
                break;
        }
    }

    public void attrape(MouseEvent e) {
        for (int i = this.getFormes().size() - 1; i >= 0; i--) {
            Forme selected = this.getFormes().get(i);
            if (selected.estDedans(e.getX(), e.getY())) {
                this.indexSelected = i;
                this.getCtrl().getMenuCtrl().unlockSelection();
                this.enDeplacement = true;
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

            this.getSelectedForme().setX(this.getSelectedForme().getX() + dx);
            this.getSelectedForme().setY(this.getSelectedForme().getY() + dy);
            this.getSelectedForme().setWidth(this.getSelectedForme().getWidth() + dx);
            this.getSelectedForme().setHeight(this.getSelectedForme().getHeight() + dy);

            x_souris = e.getX();
            y_souris = e.getY();

            this.ctrl.getCvsCtrl().draw();
        }
    }

    public void lache(MouseEvent e) {
        this.enDeplacement=false;
    }

    public void moveArrPlan() {
        if (this.indexSelected == -1) return;
        //if (this.indexSelected == this.getFormes().size() - 1) return; //Forme deja au fond

        Forme save = this.getSelectedForme();
        this.getFormes().remove(this.indexSelected);

        Collections.reverse(this.getFormes());

        this.getFormes().add(save);

        Collections.reverse(this.getFormes());

        this.getCtrl().getCvsCtrl().draw();

        this.indexSelected = 0;
    }

    public void movePremPlan() {
        if (this.indexSelected == -1) return;
        //if (this.indexSelected == 0) return;

        Forme save = this.getSelectedForme();

        this.getFormes().remove(this.indexSelected);
        this.getFormes().add(save);
        this.getCtrl().getCvsCtrl().draw();

        this.indexSelected = this.getFormes().size() - 1;
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
        if (this.indexSelected == -1) return;

        Point2D p = this.rotation(new Point2D(this.getSelectedForme().getX(), this.getSelectedForme().getY()), this.getSelectedForme().getCenter(), angle);
        this.getFormes().get(indexSelected).setX(p.getX());
        this.getFormes().get(indexSelected).setY(p.getY());

        Point2D p2 = this.rotation(new Point2D(this.getSelectedForme().getWidth(), this.getSelectedForme().getHeight()), this.getSelectedForme().getCenter(), angle);
        this.getFormes().get(indexSelected).setWidth(p2.getX());
        this.getFormes().get(indexSelected).setHeight(p2.getY());

        this.getCtrl().getCvsCtrl().draw();
    }

    public void removeForme() {
        if (this.indexSelected == -1) return;

        this.getFormes().remove(this.indexSelected);
        this.indexSelected = -1;
        this.getCtrl().getMenuCtrl().lockSelection();
        this.ctrl.getCvsCtrl().draw();
    }

    public void copier() {
        if (this.indexSelected == -1) return;

        this.copiedForme = this.getSelectedForme();
    }

    public void coller() {
        if(copiedForme == null) return;

        //Attributs
        double x = this.copiedForme.getX() + 15;
        double y = this.copiedForme.getY() + 15;

        double w = this.copiedForme.getWidth() + 15;
        double h = this.copiedForme.getHeight() + 15;

        String text = this.copiedForme.getText();
        Color clr = this.copiedForme.getClr();

        boolean drawable = this.copiedForme.isDrawable();

        double epaisseurBord = this.copiedForme.getEpaisseurBord();
        Color clrBord = this.copiedForme.getClrBord();

        switch (this.copiedForme.getClass().getSimpleName()) {
            case "Rectangle":
                this.getFormes().add(new Rectangle(x, y, w, h, text, clr, drawable, epaisseurBord, clrBord));
                break;
            case "Ligne":
                this.getFormes().add(new Ligne(x, y, w, h, text, clr, drawable, epaisseurBord, clrBord));
                break;
            case "TriangleIsocele":
                this.getFormes().add(new TriangleIsocele(x, y, w, h, text, clr, drawable, epaisseurBord, clrBord));
                break;
            case "TriangleRectangle":
                this.getFormes().add(new TriangleRectangle(x, y, w, h, text, clr, drawable, epaisseurBord, clrBord));
                break;
            case "ZoneTexte":
                this.getFormes().add(new ZoneTexte(x, y, w, h, text, clr, drawable, epaisseurBord, clrBord));
                break;
            case "ImageNous":
                this.getFormes().add(new ImageNous(((ImageNous) this.copiedForme).getImg(), x, y, w, h, text, clr, drawable, epaisseurBord, clrBord));
        }

        this.getCtrl().getCvsCtrl().draw();
    }


    public Image loadImage() {
        final FileChooser dialog = new FileChooser();
        FileChooser.ExtensionFilter filterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG", "*.jpg","*.JPEG", "*.jpeg");
        dialog.getExtensionFilters().addAll(filterJPG);
        File f = dialog.showOpenDialog(this.ctrl.getMenuCtrl().getMapp().getMenuBar().getMenus().get(2).getItems().get(1).getParentPopup().getOwnerWindow());
        if(f != null){
            String s = null;
            try {
                s = f.toURI().toURL().toExternalForm();

                Image image = new Image(s);

                return image;
            } catch (MalformedURLException malformedURLException) {
                malformedURLException.printStackTrace();
            }
        }
        return null;
    }

    public String loadText() {
        TextInputDialog in = new TextInputDialog("");

        in.setTitle("Insèrer zone de texte");
        //in.setHeaderText("Votre te");
        in.setContentText("Texte :");

        Optional<String> txtIn = in.showAndWait();

        if (txtIn.isPresent()) {
            return txtIn.get();
        }
        return null;
    }

    public void dimCtr(double value) {
        this.getForme(this.indexSelected).diminuerTailleBord(value);
        this.getCtrl().getCvsCtrl().draw();
    }

    public void augCtr(double value) {
        this.getForme(this.indexSelected).augmenterTailleBord(value);
        this.getCtrl().getCvsCtrl().draw();
    }

}

