package org.app.Gui;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

/**
 * Odpowiada za inicjalizację,
 * konfigurację i wyświetlanie interfejsu użytkownika.
 */
public class Gui extends Application {
    /**
     * Metoda uruchamiająca aplikację,
     * inicjująca start interfejsu graficznego.
     */
    public static void main(String[] args) {
        launch();
    }

    /**
     * Metoda startowa interfejsu graficznego,
     * odpowiedzialna za wczytanie pliku FXML,
     * utworzenie sceny i wyświetlenie jej na podanym etapie (stage).
     */
    @Override
    public void start(Stage stage) throws IOException {
        URL url = getClass().getResource("MainApp.fxml");
        FXMLLoader fxmlLoader = new FXMLLoader(url);
        Scene scene = new Scene(fxmlLoader.load(), 1280, 720);
        stage.setTitle("Mrowisko");
        stage.setScene(scene);
        stage.show();
    }

}