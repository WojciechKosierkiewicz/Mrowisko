package org.app.MainApp;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import org.app.menager.Menager;
import javafx.scene.paint.Color;
import org.app.menager.config.Config;


public class MainAppController {
    @FXML
    Pane world;

    Config settings;
    Menager sim;

    @FXML
    public void initialize() {

        world.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));

    }

    @FXML
    public void reset() {

        world.getChildren().clear();
        sim = new Menager(settings);

    }
}
