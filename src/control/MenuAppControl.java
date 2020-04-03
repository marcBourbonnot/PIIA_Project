package control;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import vue.MenuApp;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MenuAppControl {
    MenuApp mapp = new MenuApp();

    public void addActions() {
        ObservableList<MenuItem> fichier = mapp.getMenuBar().getMenus().get(0).getItems();
        fichier.get(0).setOnAction(e -> System.out.println("nouveau"));
        fichier.get(3).setOnAction(e -> Platform.exit());
    }

    public MenuApp getMapp() {
        return mapp;
    }
}
