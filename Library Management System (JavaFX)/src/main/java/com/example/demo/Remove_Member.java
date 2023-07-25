package com.example.demo;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.OverrunStyle;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Remove_Member extends Application {

    Connection dblink = DBconnect.getConnection();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage remove_bookStage) {


        Label title = new Label("Remove Member");
        title.setMaxHeight(1.7976931348623157E308);
        title.setMaxWidth(1.7976931348623157E308);
        title.setPrefHeight(340);
        title.setPrefWidth(517);
        title.setStyle("-fx-background-color: #ee8e19;");
        title.setTextFill(Color.BLACK);
        title.setFont(new Font("Times New Roman Bold",18));
        title.setPadding(new Insets(0,0,0,20));


        Label title2 = new Label("Member Information");
        title2.setAlignment(Pos.CENTER);
        title2.setMaxHeight(1.7976931348623157E308);
        title2.setMaxWidth(1.7976931348623157E308);
        title2.setPrefHeight(343);
        title2.setPrefWidth(517);
        title2.setStyle("-fx-background-color: white;");
        title2.setTextAlignment(TextAlignment.CENTER);
        title2.setTextOverrun(OverrunStyle.CLIP);
        title2.setFont(new Font("Times New Roman Bold",15));


        Label enter_id = new Label("Enter The ID of The Member : ");



        TextField id = new TextField();
        id.setPrefHeight(35);
        id.setPrefWidth(182);
        id.setPromptText("Book ID");


        Label mssg = new Label();
        mssg.setAlignment(Pos.CENTER);
        mssg.setTextFill(Color.RED);
        mssg.setPrefHeight(23);
        mssg.setPrefWidth(517);
        mssg.setStyle("-fx-background-color: white;");
        mssg.setTextAlignment(TextAlignment.CENTER);

        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.setPrefHeight(64);
        hBox.setPrefWidth(517);
        hBox.setSpacing(30);
        hBox.setStyle("-fx-background-color: white;");
        hBox.getChildren().addAll(enter_id,id);
        HBox.setMargin(enter_id,new Insets(0,0,0,10));


        Button remove = new Button("Remove");
        remove.setPrefHeight(42);
        remove.setPrefWidth(184);
        remove.setStyle("-fx-background-color: #ee8e19;");
        remove.setFont(new Font("Arial Bold",14));
        remove.setOnAction(e->{
            try{

                if(id.getText().isBlank()){
                    mssg.setText("Please Enter Book ID");
                }
                else if(Integer.parseInt(id.getText()) < 0){
                    mssg.setText("Please Enter a valid Book ID");

                }
                else {
                    String delete = "Delete from Member_tbl where member_id = '"+Integer.parseInt(id.getText())+"';";
                    Statement statement = dblink.createStatement();
                    boolean resultSet = statement.execute(delete);
                    if(!resultSet){

                        mssg.setText("Member Removed Successfully");
                    }
                    else {
                        mssg.setText("Something went wrong. Please try again");
                    }

                }


            }catch (Exception ex){
                mssg.setText(ex.getMessage());
            }
        });


        VBox remove_vbox = new VBox();
        remove_vbox.setAlignment(Pos.CENTER);
        remove_vbox.setMaxHeight(1.7976931348623157E308);
        remove_vbox.setMaxWidth(1.7976931348623157E308);
        remove_vbox.setPrefHeight(471);
        remove_vbox.setPrefWidth(517);
        remove_vbox.setSpacing(20);
        remove_vbox.setStyle("-fx-background-color: white;");
        remove_vbox.getChildren().addAll(hBox,remove);
        VBox.setMargin(remove,new Insets(20,0,0,0));

        Button exit = new Button("Exit");
        exit.setPrefWidth(30);
        exit.setPrefWidth(130);
        exit.setStyle("-fx-background-color: #ee8e19;");
        exit.setFont(new Font("Arial Bold",14));
        exit.setOnAction(e->{
            Home home = new Home();
            home.start(remove_bookStage);
        });
        HBox hBox2 = new HBox();
        hBox2.setAlignment(Pos.BOTTOM_RIGHT);
        hBox2.setMaxHeight(1.7976931348623157E308);
        hBox2.setMaxWidth(1.7976931348623157E308);
        hBox2.setPrefHeight(48);
        hBox2.setPrefWidth(148);
        hBox2.setLayoutX(1011);
        hBox2.setLayoutY(599);
        hBox2.setStyle("-fx-background-color: white;");
        hBox2.getChildren().add(exit);
        HBox.setMargin(exit,new Insets(0,10,10,0));




        VBox vBox = new VBox();
        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.setMaxHeight(1.7976931348623157E308);
        vBox.setMaxWidth(1.7976931348623157E308);
        vBox.setPrefHeight(326);
        vBox.setPrefWidth(517);
        vBox.setStyle("fx-background-color: white;");
        vBox.getChildren().addAll(title,title2,remove_vbox,mssg,hBox2);

        Scene remove_bookScene = new Scene(vBox,517,326);
        remove_bookStage.setScene(remove_bookScene);
        remove_bookStage.show();



    }
}
