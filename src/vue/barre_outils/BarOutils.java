package vue.barre_outils;

import javafx.scene.layout.VBox;
import vue.View;

import javax.tools.Tool;

public class BarOutils {
    //Attributs
    private VBox outils;


    //Constantes
    public final static int WIDTH = 75;

    //Constructeurs
    /**
     * Constructeur sans param de la classe BarreOutils
     */
    public BarOutils() {
        this.outils = new VBox();

        this.outils.setPrefWidth(BarOutils.WIDTH);
        this.outils.setPrefHeight(View.HEIGHT);
    }


    //Getters and Setters
    /**
     * permet de recuperer la barre d'outils
     * @return outils
     */
    public VBox getOutils() {
        return outils;
    }


    //Methodes

}
