import control.KeyboardControl;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import vue.View;

import javax.imageio.ImageIO;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        View view = new View();

        Scene scene = new Scene(view.getMainView());

        scene.setOnKeyPressed(view.getCtrl().getKbCtrl());

        primaryStage.setTitle("Dessin facile");
        primaryStage.setScene(scene);
        primaryStage.setOnCloseRequest(e -> view.getCtrl().comfirmQuit(e));
        primaryStage.setResizable(false);

        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
