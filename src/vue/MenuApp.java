package vue;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.paint.Color;


public class MenuApp {
    //Attributs
    private MenuBar menuBar;


    //Constantes


    //Constructeurs
    /**
     * Constructeur sans parametres pour faire le menu
     */
    public MenuApp() {
        this.menuBar = this.createMenu();
    }


    //Getters and Setters
    /**
     * Getter pour le menu
     * @return menuBar
     */
    public MenuBar getMenuBar() {
        return menuBar;
    }


    //Methodes
    /**
     * Methode qui permet la creation du menu
     * @return menuBar cree
     */
    private MenuBar createMenu() {
        MenuBar res = new MenuBar();

        //Creation du menu fichier
        Menu fichier = new Menu("Fichier");

        MenuItem nouveau = new MenuItem("Nouveau");
        MenuItem ouvrir = new MenuItem("Ouvrir");
        MenuItem sauvegarder = new MenuItem("Enregistrer");
        MenuItem export = new MenuItem("Exporter en png");
        MenuItem quitter = new MenuItem("Quitter");


        quitter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Platform.exit();
            }
        });


        fichier.getItems().add(nouveau);
        fichier.getItems().add(ouvrir);
        fichier.getItems().add(sauvegarder);
        fichier.getItems().add(export);
        fichier.getItems().add(quitter);


        //Creation du menu d'edition
        Menu edition = new Menu("Edition");

        MenuItem annuler = new MenuItem("Annuler");
        MenuItem retablir = new MenuItem("Rétablir");
        MenuItem copier = new MenuItem("Copier");
        MenuItem coller = new MenuItem("Coller");

        edition.getItems().add(annuler);
        edition.getItems().add(retablir);
        edition.getItems().add(copier);
        edition.getItems().add(coller);


        //Creation du menu insertion
        Menu insertion = new Menu("Insertion");

        Menu formes = new Menu("Formes");
        MenuItem images = new MenuItem("Image");
        MenuItem zDeTxt = new MenuItem("Zone de texte");

        final ToggleGroup groupFormes = new ToggleGroup();
        RadioMenuItem ligne = new RadioMenuItem("Ligne");
        ligne.setToggleGroup(groupFormes);
        RadioMenuItem rectangle = new RadioMenuItem("Rectangle");
        rectangle.setToggleGroup(groupFormes);
        Menu triangle = new Menu("Triangle");
        RadioMenuItem ellipse = new RadioMenuItem("Ellipse");
        ellipse.setToggleGroup(groupFormes);

        RadioMenuItem trglIso = new RadioMenuItem("Isocele");
        trglIso.setToggleGroup(groupFormes);
        RadioMenuItem trglRect = new RadioMenuItem("Rectangle");
        trglRect.setToggleGroup(groupFormes);

        triangle.getItems().add(trglIso);
        triangle.getItems().add(trglRect);

        formes.getItems().add(ligne);
        formes.getItems().add(rectangle);
        formes.getItems().add(triangle);
        formes.getItems().add(ellipse);

        insertion.getItems().add(formes);
        insertion.getItems().add(images);
        insertion.getItems().add(zDeTxt);


        //Creation du menu modification
        Menu modification = new Menu("Modification");

        RadioMenuItem select = new RadioMenuItem("Selection");
        select.setToggleGroup(groupFormes);
        Menu contour = new Menu("Contour");
        MenuItem couleurRemp = new MenuItem("Couleur de remplissage");
        Menu rotation = new Menu("Rotation");
        Menu plan = new Menu("Plan");
        MenuItem suppr = new MenuItem("Supprimer");

        //Menu contour
        MenuItem couleur = new MenuItem("Couleur");
        MenuItem epaisseur = new MenuItem("Epaisseur");

        contour.getItems().add(couleur);
        contour.getItems().add(epaisseur);

        //Menu rotation
        MenuItem droite = new MenuItem("Rotation 90° vers la droite");
        MenuItem gauche = new MenuItem("Rotation 90° vers la gauche");

        rotation.getItems().add(droite);
        rotation.getItems().add(gauche);

        //Menu plan
        MenuItem arrPlan = new MenuItem("Mettre en arrière plan");
        MenuItem devPlan = new MenuItem("Mettre au premier plan");

        plan.getItems().add(devPlan);
        plan.getItems().add(arrPlan);

        modification.getItems().add(select);
        modification.getItems().add(contour);
        //TEST
        couleurRemp.setOnAction(e -> {
            ColorPicker pck = new ColorPicker();
            Color clr = pck.getValue();
        });
        //FIN TEST
        modification.getItems().add(couleurRemp);
        modification.getItems().add(rotation);
        modification.getItems().add(plan);
        modification.getItems().add(suppr);


        //Ajout au MenuBar
        res.getMenus().add(fichier);
        res.getMenus().add(edition);
        res.getMenus().add(insertion);
        res.getMenus().add(modification);

        return res;
    }

}
