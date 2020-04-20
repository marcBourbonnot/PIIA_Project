package vue;

import javafx.geometry.Orientation;
import javafx.scene.control.*;

import java.util.ArrayList;

public class BarreOutils {
    public static final int WIDTH = 125;
    private ToolBar outils = new ToolBar();

    public BarreOutils() {
        this.initBarre();
    }

    public ToolBar getOutils() {
        return outils;
    }

    private void initBarre() {
        this.getOutils().setOrientation(Orientation.VERTICAL);
        this.getOutils().setPrefWidth(BarreOutils.WIDTH);

        ArrayList<Control> bouttons = new ArrayList<>();

        bouttons.add(new Label("Raccourcis"));
        bouttons.add(new Separator());
        bouttons.add(new ToggleButton("Sélectionner"));
        bouttons.add(new Separator());
        bouttons.add(new ToggleButton("Redimessionner"));
        bouttons.add(new Separator());
        bouttons.add(new Label("Formes"));
        bouttons.add(new ToggleButton("Ligne"));
        bouttons.add(new ToggleButton("Rectangle"));
        bouttons.add(new ToggleButton("Triangle isocèle"));
        bouttons.add(new ToggleButton("Triangle rectangle"));
        bouttons.add(new ToggleButton("ellipse"));
        bouttons.add(new Separator());
        bouttons.add(new Button("Image"));
        bouttons.add(new Button("Zone de texte"));
        bouttons.add(new Separator());
        bouttons.add(new Button("Supprimer"));

        ToggleGroup tglG = new ToggleGroup();

        bouttons.forEach(e -> {
            if (e.getClass().getSimpleName().equals("ToggleButton") && !((ToggleButton) e).getText().equals("Redimessionner")) {
                ((ToggleButton) e).setToggleGroup(tglG);
            }
            e.setMinWidth(BarreOutils.WIDTH - 10);
        });

        this.getOutils().getItems().addAll(bouttons);
    }
}
