package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Main extends Application {



    @Override
        public void start(Stage primaryStage) {
        Button send = new Button("Button");
        send.setLayoutX(404);
        send.setLayoutY(338);

        TextField textField = new TextField();
        textField.setLayoutX(30);
        textField.setLayoutY(338);
        textField.setPrefHeight(26);
        textField.setPrefWidth(361);

        VBox vBox = new VBox();
        vBox.setPrefHeight(248);
        vBox.setPrefWidth(412);

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setLayoutY(70);
        scrollPane.setLayoutX(30);
        scrollPane.setPrefHeight(256);
        scrollPane.setPrefWidth(418);
        scrollPane.setFitToWidth(true);
        scrollPane.setContent(vBox);

        Label title = new Label("Chat System");
        title.setLayoutX(114);
        title.setLayoutY(26);
        title.setFont(new Font("Arial Rounded MT Bold",23));


        Pane pane = new Pane();
        pane.setPrefHeight(396);
        pane.setPrefWidth(478);
        pane.getChildren().addAll(send,textField,scrollPane,textField);

        Scene scene = new Scene(pane,478,396);
        primaryStage.setScene(scene);
        primaryStage.show();


        }
    public static void main(String[] args) {
        launch(args);
    }

    }

