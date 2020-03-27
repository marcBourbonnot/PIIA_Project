package vue;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class View extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        MenuApp menu = new MenuApp();

        //VBox root = new VBox(menu);


        Scene scene = new Scene(menu.getMenuBar());

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }

}
