package control;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import modele.Formes;
import modele.Ligne;
import modele.Rectangle;
import vue.MenuApp;

public class MenuAppControl {
    Control ctrl;

    MenuApp mapp;

    public MenuAppControl(Control c) {
        this.ctrl = c;

        this.mapp = new MenuApp();
    }

    public void addActions() {
        //Menu fichier
        ObservableList<MenuItem> fichier = mapp.getMenuBar().getMenus().get(0).getItems();
        fichier.get(0).setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Si vous faites cela sans sauvegarde, vous perdrez tout ce que vous avez fait ! Etes vous sûr ?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                this.ctrl.getMdl().clearFormes();
            }

        });
        fichier.get(1).setOnAction(e -> System.out.println("Ouvrir"));
        fichier.get(2).setOnAction(e -> System.out.println("Enregistrer"));
        fichier.get(3).setOnAction(e -> Platform.exit());

        //Menu edition
        ObservableList<MenuItem> edition = mapp.getMenuBar().getMenus().get(1).getItems();
        edition.get(0).setOnAction(e -> System.out.println("Annuler"));
        edition.get(1).setOnAction(e -> System.out.println("Retablir"));
        edition.get(2).setOnAction(e -> System.out.println("Copier"));
        edition.get(3).setOnAction(e -> System.out.println("coller"));

        //Menu insertion
        ObservableList<MenuItem> insertion = mapp.getMenuBar().getMenus().get(2).getItems();
        ((Menu)insertion.get(0)).getItems().get(0).setOnAction(e -> {
            this.ctrl.getMdl().setTypeSelected(Formes.LIGNE);
            System.out.println(this.ctrl.getMdl().getTypeSelected());
            this.ctrl.getMdl().newForme();
        });
        ((Menu)insertion.get(0)).getItems().get(1).setOnAction(e -> {
            this.ctrl.getMdl().setTypeSelected(Formes.RECTANGLE);
            System.out.println(this.ctrl.getMdl().getTypeSelected());
            this.ctrl.getMdl().newForme();
        });
        //Triangles
        ((Menu) ((Menu) insertion.get(0)).getItems().get(2)).getItems().get(0).setOnAction(e ->{
            this.ctrl.getMdl().setTypeSelected(Formes.TRIANGLE_ISOCELE);
            System.out.println(this.ctrl.getMdl().getTypeSelected());
            this.ctrl.getMdl().newForme();
        });
        ((Menu) ((Menu) insertion.get(0)).getItems().get(2)).getItems().get(1).setOnAction(e ->{
            this.ctrl.getMdl().setTypeSelected(Formes.TRIANGLE_RECTANGLE);
            System.out.println(this.ctrl.getMdl().getTypeSelected());
            this.ctrl.getMdl().newForme();
        });
//        ((Menu)((Menu) insertion.get(0)).getItems().get(2).setOnAction(e ->{
//            this.ctrl.getMdl().setTypeSelected(Formes.TRIANGLE);
//            System.out.println(this.ctrl.getMdl().getTypeSelected());
//            this.ctrl.getMdl().newForme();
//        });
        ((Menu)insertion.get(0)).getItems().get(3).setOnAction(e -> {
            this.ctrl.getMdl().setTypeSelected(Formes.ELLIPSE);
            System.out.println(this.ctrl.getMdl().getTypeSelected());
            this.ctrl.getMdl().newForme();
        });
        insertion.get(1).setOnAction(e -> System.out.println("Image"));
        insertion.get(2).setOnAction(e -> System.out.println("Texte"));

        //Menu modification
        ObservableList<MenuItem> modification = mapp.getMenuBar().getMenus().get(3).getItems();
        ((Menu)modification.get(0)).getItems().get(0).setOnAction(e -> System.out.println("ContourCouleur"));
        ((Menu)modification.get(0)).getItems().get(1).setOnAction(e -> System.out.println("ContourEpaisseur"));
        modification.get(1).setOnAction(e -> System.out.println("Couleur de remplissage"));
        ((Menu)modification.get(2)).getItems().get(0).setOnAction(e -> System.out.println("Rot90D"));
        ((Menu)modification.get(2)).getItems().get(1).setOnAction(e -> System.out.println("Rot90G"));
        ((Menu)modification.get(3)).getItems().get(0).setOnAction(e -> System.out.println("PlanPremier"));
        ((Menu)modification.get(3)).getItems().get(1).setOnAction(e -> System.out.println("PlanArriere"));
        modification.get(4).setOnAction(e -> System.out.println("SupprimerForme"));
    }

    public MenuApp getMapp() {
        return mapp;
    }
}
