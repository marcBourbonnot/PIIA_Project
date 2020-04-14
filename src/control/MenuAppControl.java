package control;

import com.sun.tools.javac.Main;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import modele.Formes;
import modele.Ligne;
import modele.Rectangle;
import vue.MenuApp;

import java.io.File;

public class MenuAppControl {
    private Control ctrl;

    private MenuApp mapp;
    private boolean dessinMode;
    private boolean allowModif;

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
        fichier.get(2).setOnAction(e -> System.out.println("Enregistrer"));
        fichier.get(3).setOnAction(e -> this.ctrl.Quit(e));

        //Menu edition
        ObservableList<MenuItem> edition = mapp.getMenuBar().getMenus().get(1).getItems();
        edition.get(0).setOnAction(e -> System.out.println("Annuler"));
        edition.get(1).setOnAction(e -> System.out.println("Retablir"));
        edition.get(2).setOnAction(e -> System.out.println("Copier"));
        edition.get(3).setOnAction(e -> System.out.println("coller"));

        //Menu insertion
        ObservableList<MenuItem> insertion = mapp.getMenuBar().getMenus().get(2).getItems();
        ((Menu)insertion.get(0)).getItems().get(0).setOnAction(e -> {
            this.setDessinMode(true);
            this.ctrl.getMdl().setTypeSelected(Formes.LIGNE);
            System.out.println(this.ctrl.getMdl().getTypeSelected());
            this.ctrl.getMdl().newForme();
        });
        ((Menu)insertion.get(0)).getItems().get(1).setOnAction(e -> {
            this.setDessinMode(true);
            this.ctrl.getMdl().setTypeSelected(Formes.RECTANGLE);
            System.out.println(this.ctrl.getMdl().getTypeSelected());
            this.ctrl.getMdl().newForme();
        });
        ((Menu) ((Menu) insertion.get(0)).getItems().get(2)).getItems().get(0).setOnAction(e ->{
            this.setDessinMode(true);
            this.ctrl.getMdl().setTypeSelected(Formes.TRIANGLE_ISOCELE);
            System.out.println(this.ctrl.getMdl().getTypeSelected());
            this.ctrl.getMdl().newForme();
        });
        ((Menu) ((Menu) insertion.get(0)).getItems().get(2)).getItems().get(1).setOnAction(e ->{
            this.setDessinMode(true);
            this.ctrl.getMdl().setTypeSelected(Formes.TRIANGLE_RECTANGLE);
            System.out.println(this.ctrl.getMdl().getTypeSelected());
            this.ctrl.getMdl().newForme();
        });
        ((Menu)insertion.get(0)).getItems().get(3).setOnAction(e -> {
            this.setDessinMode(true);
            this.ctrl.getMdl().setTypeSelected(Formes.ELLIPSE);
            System.out.println(this.ctrl.getMdl().getTypeSelected());
            this.ctrl.getMdl().newForme();
        });
        insertion.get(1).setOnAction(e -> {
            final FileChooser dialog = new FileChooser();
            FileChooser.ExtensionFilter filterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG", "*.jpg","*.JPEG", "*.jpeg");
            dialog.getExtensionFilters().addAll(filterJPG);
            File f = dialog.showOpenDialog(insertion.get(1).getParentPopup().getOwnerWindow());
            if(f != null){
                System.out.println("je ferais qqch d'interessant la prochaine fois !");
                System.out.println("URL : "+f.toURI().getPath());
                String s = f.toURI().getPath();
                /*Image img = new Image(f.toURI().getPath());
                System.out.println("img : "+img);
                System.out.println(f.toURI().getPath());
                ImageView imgview = new ImageView(img);
                this.ctrl.view.getMainView().getChildren().add(imgview);*/
            }

        });
        insertion.get(2).setOnAction(e -> System.out.println("Texte"));

        //Menu modification
        ObservableList<MenuItem> modification = mapp.getMenuBar().getMenus().get(3).getItems();
        modification.get(0).setOnAction(e -> {
            this.setDessinMode(false);
        });
        ((Menu)modification.get(1)).getItems().get(0).setOnAction(e -> System.out.println("ContourCouleur"));
        ((Menu)modification.get(1)).getItems().get(1).setOnAction(e -> System.out.println("ContourEpaisseur"));
        modification.get(2).setOnAction(e -> System.out.println("Couleur de remplissage"));
        ((Menu)modification.get(3)).getItems().get(0).setOnAction(e -> this.ctrl.getMdl().rotateForme(90));
        ((Menu)modification.get(3)).getItems().get(1).setOnAction(e -> this.ctrl.getMdl().rotateForme(-90));
        ((Menu)modification.get(4)).getItems().get(0).setOnAction(e -> this.ctrl.getMdl().movePremPlan());
        ((Menu)modification.get(4)).getItems().get(1).setOnAction(e -> this.ctrl.getMdl().moveArrPan());
        modification.get(5).setOnAction(e -> System.out.println("SupprimerForme"));
    }

    public MenuApp getMapp() {
        return mapp;
    }
}
