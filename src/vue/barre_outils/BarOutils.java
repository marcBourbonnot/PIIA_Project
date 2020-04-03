package vue.barre_outils;

import javafx.geometry.Orientation;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.VBox;
import vue.View;

import javax.tools.Tool;

public class BarOutils {
    //Attributs
    private View view;

    private VBox outils;

    private int width;
    private int height;

    //Constantes


    //Constructeurs
    /**
     * Constructeur sans param de la classe BarreOutils
     */
    public BarOutils(View v) {
        this.view = v;
        this.outils = new VBox();
        this.width = 75;
        this.height = view.getHeight();
        this.outils.setPrefWidth(this.width);
        this.outils.setPrefWidth(this.height);
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
