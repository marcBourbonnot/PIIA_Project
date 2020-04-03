package vue;

import control.MenuAppControl;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import vue.barre_outils.BarOutils;


public class View extends Application {
    MenuAppControl menu;
    ZoneDessin dessin;
    BarOutils bOutils;
    int width = 1000;
    int height = 750;

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.menu = new MenuAppControl();
        this.dessin = new ZoneDessin(width, height);
        this.bOutils = new BarOutils(this);

        menu.addActions();

        VBox vbox = new VBox(this.menu.getMapp().getMenuBar(), new HBox(this.dessin.getDrawArea(),this.bOutils.getOutils()));

        Scene scene = new Scene(vbox);

        primaryStage.setTitle("Dessin facile");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public int getHeight() {
        return height;
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

}
