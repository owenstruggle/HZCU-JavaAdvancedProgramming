package com.owem.experiment02;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * @author Owem
 * @date 2022/9/20 14:08
 * @description TODO
 **/
public class MyJavaFX extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Button btOK = new Button("OK");
        Scene scene = new Scene(btOK, 250, 200);
        stage.setTitle("MyJavaFX");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}