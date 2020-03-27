package vue;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

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
        Menu fichier = new Menu("fichier");

        MenuItem nouveau = new MenuItem("Nouveau");
        MenuItem ouvrir = new MenuItem("Ouvrir");
        MenuItem sauvegarder = new MenuItem("Enregistrer");
        MenuItem quitter = new MenuItem("Quitter");


        fichier.getItems().add(nouveau);
        fichier.getItems().add(ouvrir);
        fichier.getItems().add(sauvegarder);
        fichier.getItems().add(quitter);


        //Creation du menu d'edition
        Menu edition = new Menu("édition");

        MenuItem annuler = new MenuItem("Annuler");
        MenuItem retablir = new MenuItem("Rétablir");
        MenuItem copier = new MenuItem("Copier");
        MenuItem coller = new MenuItem("Coller");

        edition.getItems().add(annuler);
        edition.getItems().add(retablir);
        edition.getItems().add(copier);
        edition.getItems().add(coller);


        //Creation du menu insertion
        Menu insertion = new Menu("insertion");

        Menu formes = new Menu("Formes");
        MenuItem images = new MenuItem("Image");
        MenuItem zDeTxt = new MenuItem("Zone de texte");

        MenuItem ligne = new MenuItem("Ligne");
        MenuItem rectangle = new MenuItem("Rectangle");
        MenuItem triangle = new MenuItem("Triangle");
        MenuItem ellipse = new MenuItem("Ellipse");

        formes.getItems().add(ligne);
        formes.getItems().add(rectangle);
        formes.getItems().add(triangle);
        formes.getItems().add(ellipse);

        insertion.getItems().add(formes);
        insertion.getItems().add(images);
        insertion.getItems().add(zDeTxt);


        //Creation du menu modification
        Menu modification = new Menu("Modification");

        Menu contour = new Menu("Contour");
        MenuItem couleurRemp = new MenuItem("Couleur de remplissage");
        Menu rotation = new Menu("Rotation");
        Menu plan = new Menu("plan");
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

        modification.getItems().add(contour);
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
