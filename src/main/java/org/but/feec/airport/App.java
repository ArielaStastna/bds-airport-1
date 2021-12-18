package org.but.feec.airport;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.but.feec.airport.exceptions.ExceptionHandler;

public class App extends Application {
    private FXMLLoader loader;
    private VBox mainStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        try { //v aktuálnej triede loaduje xml
            loader = new FXMLLoader(getClass().getResource("Login.fxml"));
            mainStage = loader.load();

            primaryStage.setTitle("BDS Airport");
            Scene scene = new Scene(mainStage);
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception ex) {
           ExceptionHandler.handleException(ex);
        }
    }

}

