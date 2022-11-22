package com.owem.experiment02.login;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * @author Owem
 * @date 2022/9/20 15:40
 * @description TODO
 **/
public class LoginUI extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        pane.setHgap(5.5);
        pane.setVgap(5.5);

        pane.add(new Label("用户名"), 0, 0);
        pane.add(new TextField(), 1, 0);
        pane.add(new Label("密码"), 0, 1);
        pane.add(new TextField(), 1, 1);
        Button btLogin = new Button("登录");
        Button btCancel = new Button("取消");
        pane.add(btLogin, 0, 2);
        pane.add(btCancel, 1, 2);
        GridPane.setHalignment(btLogin, HPos.RIGHT);
        GridPane.setHalignment(btCancel, HPos.RIGHT);

        Scene scene = new Scene(pane);
        stage.setTitle("登录界面");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}