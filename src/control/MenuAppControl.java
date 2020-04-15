package control;

import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import modele.Formes;
import modele.Model;
import vue.MenuApp;

import java.io.File;
import java.net.MalformedURLException;

public class MenuAppControl {
    private Control ctrl;

    private MenuApp mapp;
    private boolean dessinMode;
    private boolean allowModif;
    private Model save;

    public MenuAppControl(Control c) {
        this.ctrl = c;

        this.mapp = new MenuApp();
        this.dessinMode = false;
        this.allowModif = false;
    }

    public boolean isDessinMode() {
        return dessinMode;
    }

    public void setDessinMode(boolean dessinMode) {
        this.dessinMode = dessinMode;
    }

    public void addActions() {
        //Menu fichier
        ObservableList<MenuItem> fichier = mapp.getMenuBar().getMenus().get(0).getItems();
        fichier.get(0).setOnAction(e -> {
            this.ctrl.comfirmNew(e);

        });
        fichier.get(1).setOnAction(e -> System.out.println("Ouvrir"));
        fichier.get(2).setOnAction(e -> {//pas fini
            FileChooser fc = new FileChooser();
            fc.setTitle("Save file");
            fc.setInitialFileName("unnamedFile");

            FileChooser.ExtensionFilter filterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG", "*.jpg","*.JPEG", "*.jpeg");
            FileChooser.ExtensionFilter filterTXT = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
            fc.getExtensionFilters().addAll(filterJPG,filterTXT);


            File savedFile = fc.showSaveDialog(fichier.get(1).getParentPopup().getOwnerWindow());
            if(savedFile != null){

            }
            System.out.println("Enregistrer");

        });
        fichier.get(3).setOnAction(e -> this.ctrl.Quit(e));

        //Menu edition
        ObservableList<MenuItem> edition = mapp.getMenuBar().getMenus().get(1).getItems();
        edition.get(0).setOnAction(e -> this.undo());
        edition.get(1).setOnAction(e -> System.out.println("Retablir"));
        edition.get(2).setOnAction(e -> this.ctrl.getMdl().copier());
        edition.get(3).setOnAction(e -> this.ctrl.getMdl().coller());

        //Menu insertion
        ObservableList<MenuItem> insertion = mapp.getMenuBar().getMenus().get(2).getItems();
        ((Menu)insertion.get(0)).getItems().get(0).setOnAction(e -> {
            saveModel();
            this.setDessinMode(true);
            this.ctrl.getMdl().setTypeSelected(Formes.LIGNE);
            System.out.println(this.ctrl.getMdl().getTypeSelected());
            this.ctrl.getMdl().newForme();
        });
        ((Menu)insertion.get(0)).getItems().get(1).setOnAction(e -> {
            saveModel();
            this.setDessinMode(true);
            this.ctrl.getMdl().setTypeSelected(Formes.RECTANGLE);
            System.out.println(this.ctrl.getMdl().getTypeSelected());
            this.ctrl.getMdl().newForme();
        });
        ((Menu) ((Menu) insertion.get(0)).getItems().get(2)).getItems().get(0).setOnAction(e ->{
            saveModel();
            this.setDessinMode(true);
            this.ctrl.getMdl().setTypeSelected(Formes.TRIANGLE_ISOCELE);
            System.out.println(this.ctrl.getMdl().getTypeSelected());
            this.ctrl.getMdl().newForme();
        });
        ((Menu) ((Menu) insertion.get(0)).getItems().get(2)).getItems().get(1).setOnAction(e ->{
            saveModel();
            this.setDessinMode(true);
            this.ctrl.getMdl().setTypeSelected(Formes.TRIANGLE_RECTANGLE);
            System.out.println(this.ctrl.getMdl().getTypeSelected());
            this.ctrl.getMdl().newForme();
        });
        ((Menu)insertion.get(0)).getItems().get(3).setOnAction(e -> {
            saveModel();
            this.setDessinMode(true);
            this.ctrl.getMdl().setTypeSelected(Formes.ELLIPSE);
            System.out.println(this.ctrl.getMdl().getTypeSelected());
            this.ctrl.getMdl().newForme();
        });
        insertion.get(1).setOnAction(e -> {
            saveModel();
            final FileChooser dialog = new FileChooser();
            FileChooser.ExtensionFilter filterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG", "*.jpg","*.JPEG", "*.jpeg");
            dialog.getExtensionFilters().addAll(filterJPG);
            File f = dialog.showOpenDialog(insertion.get(1).getParentPopup().getOwnerWindow());
            if(f != null){
                String s = null;
                try {
                    s = f.toURI().toURL().toExternalForm();
                } catch (MalformedURLException malformedURLException) {
                    malformedURLException.printStackTrace();
                }
                Image image = new Image(s);
                System.out.println("img : "+image);

                double widthZD = this.ctrl.view.getDessin().getZoneDessin().getDrawArea().getWidth();
                double heightZD = this.ctrl.view.getDessin().getZoneDessin().getDrawArea().getHeight();

                this.ctrl.getView().getDessin().getGC().drawImage(image,widthZD/2-image.getWidth()/2,heightZD/2-image.getHeight()/2);
                System.out.println("j'affiche");
            }

        });
        insertion.get(2).setOnAction(e -> {
            saveModel();
            System.out.println("Texte");
        });

        //Menu modification
        ObservableList<MenuItem> modification = mapp.getMenuBar().getMenus().get(3).getItems();
        modification.get(0).setOnAction(e -> {
            this.setDessinMode(false);
        });
        ((Menu)modification.get(1)).getItems().get(0).setOnAction(e -> {
            saveModel();
            System.out.println("ContourCouleur");
        });
        ((Menu)modification.get(1)).getItems().get(1).setOnAction(e -> {
            saveModel();
            System.out.println("ContourEpaisseur");
        });
        modification.get(2).setOnAction(e -> {
            saveModel();
            System.out.println("Couleur de remplissage");
        });
        ((Menu)modification.get(3)).getItems().get(0).setOnAction(e -> {
            saveModel();
            this.ctrl.getMdl().rotateForme(90);
        });
        ((Menu)modification.get(3)).getItems().get(1).setOnAction(e -> {
            saveModel();
            this.ctrl.getMdl().rotateForme(-90);
        });
        ((Menu)modification.get(4)).getItems().get(0).setOnAction(e -> {
            saveModel();
            this.ctrl.getMdl().movePremPlan();
        });
        ((Menu)modification.get(4)).getItems().get(1).setOnAction(e -> {
            saveModel();
            this.ctrl.getMdl().moveArrPlan();
        });
        modification.get(5).setOnAction(e -> {
            saveModel();
            this.ctrl.getMdl().removeForme();
        });

        this.lockSelection();
    }

    public MenuApp getMapp() {
        return mapp;
    }

    public void lockSelection() {
        ObservableList<MenuItem> modification = mapp.getMenuBar().getMenus().get(3).getItems();
        modification.get(1).setVisible(false);
        modification.get(2).setVisible(false);
        modification.get(3).setVisible(false);
        modification.get(4).setVisible(false);
        modification.get(5).setVisible(false);

        ObservableList<MenuItem> edition = mapp.getMenuBar().getMenus().get(1).getItems();
        edition.get(2).setVisible(false);
        edition.get(3).setVisible(false);
    }

    public void unlockSelection() {
        ObservableList<MenuItem> modification = mapp.getMenuBar().getMenus().get(3).getItems();
        modification.get(1).setVisible(true);
        modification.get(2).setVisible(true);
        modification.get(3).setVisible(true);
        modification.get(4).setVisible(true);
        modification.get(5).setVisible(true);

        ObservableList<MenuItem> edition = mapp.getMenuBar().getMenus().get(1).getItems();
        edition.get(2).setVisible(true);
        edition.get(3).setVisible(true);
    }

    public void saveModel() {
        this.save = this.ctrl.getMdl();
    }

    public void undo() {
        if (this.save == null) return;
        this.ctrl.setMdl(this.save);
        this.save = null;
    }
}
