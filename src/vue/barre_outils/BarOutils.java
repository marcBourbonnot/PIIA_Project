package vue.barre_outils;


import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;

public class BarOutils {
    //Attributs
    MenuBar outils;


    //Constantes


    //Constructeurs
    /**
     * Constructeur sans param de la classe BarreOutils
     */
    public BarOutils() {
        this.outils = this.creationBarreOutils();
    }


    //Getters and Setters
    /**
     * permet de recuperer la barre d'outils
     * @return outils
     */
    public MenuBar getOutils() {
        return outils;
    }


    //Methodes
    private MenuBar creationBarreOutils() {
        MenuBar res = new MenuBar();


        return res;
    }
}
