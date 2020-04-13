import control.Control;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import modele.Model;
import vue.View;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        View view = new View();

        Scene scene = new Scene(view.getMainView());

        primaryStage.setTitle("Dessin facile");
        primaryStage.setScene(scene);
        primaryStage.setOnCloseRequest(e ->{
            view.getCtrl().comfirmQuit(e);
        });
        primaryStage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}
