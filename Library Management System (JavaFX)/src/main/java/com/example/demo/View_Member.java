package com.example.demo;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

public class View_Member extends Application {

    Connection dblink = DBconnect.getConnection();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage Remove_memberStage) {

        Label title = new Label("View Members");
        title.setMaxHeight(1.7976931348623157E308);
        title.setMaxWidth(1.7976931348623157E308);
        title.setPrefHeight(66);
        title.setPrefWidth(1180);
        title.setStyle("-fx-background-color: #ee8e19;");
        title.setTextFill(Color.BLACK);
        title.setFont(new Font("Times New Roman Bold",18));
        title.setPadding(new Insets(0,0,0,20));

        ObservableList<Member> members = FXCollections.observableArrayList();






        try {
            Statement statement = dblink.createStatement();
            String Query = "Select * from Member_tbl ;";
            ResultSet resultSet = statement.executeQuery(Query);
            while(resultSet.next()){
                members.add(new Member(resultSet.getInt(1), resultSet.getString(2),resultSet.getString(3), resultSet.getString(4)
                        ,resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getDate(8),resultSet.getString(9)
                        ,resultSet.getDate(10)));

            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }


        TableColumn<Member,Integer>id = new TableColumn<>("Member ID");
        id.setPrefWidth(75);
        id.setCellValueFactory(new PropertyValueFactory<>("ID"));

        TableColumn<Member,String>fname = new TableColumn<>("First Name");
        fname.setPrefWidth(204.79409790039062);
        fname.setCellValueFactory(new PropertyValueFactory<>("fname"));

        TableColumn<Member,String>lname = new TableColumn<>("Last Name");
        lname.setPrefWidth(123.99105834960938);
        lname.setCellValueFactory(new PropertyValueFactory<>("lname"));

        TableColumn<Member,String>gender = new TableColumn<>("Gender");
        gender.setPrefWidth(107.3759765625);
        gender.setCellValueFactory(new PropertyValueFactory<>("gender"));

        TableColumn<Member,String>email = new TableColumn<>("Email");
        email.setPrefWidth(96.048583984375);
        email.setCellValueFactory(new PropertyValueFactory<>("email"));

        TableColumn<Member,String>password = new TableColumn<>("Password");
        password.setPrefWidth(103.951416015625);
        password.setCellValueFactory(new PropertyValueFactory<>("password"));

        TableColumn<Member, String>address = new TableColumn<>("Address");
        address.setPrefWidth(118.82733154296875);
        address.setCellValueFactory(new PropertyValueFactory<>("address"));

        TableColumn<Member,Date>birth_date = new TableColumn<>("Birth Date");
        birth_date.setPrefWidth(84.3031005859375);
        birth_date.setCellValueFactory(new PropertyValueFactory<>("birth_date"));

        TableColumn<Member,String>fav_books = new TableColumn<>("Favourite Books");
        fav_books.setPrefWidth(120.9093017578125);
        fav_books.setCellValueFactory(new PropertyValueFactory<>("fav_books"));

        TableColumn<Member,Date>expiry_date = new TableColumn<>("Expiry Date");
        expiry_date.setPrefWidth(102.5);
        expiry_date.setCellValueFactory(new PropertyValueFactory<>("expiry_date"));


        TableView<Member> Table = new TableView<>();
        Table.setLayoutX(19);
        Table.setLayoutY(105);
        Table.setPrefHeight(487);
        Table.setPrefWidth(1140);
        Table.setMaxHeight(1.7976931348623157E308);
        Table.setMaxWidth(1.7976931348623157E308);
        Table.setItems(members);
        Table.getColumns().addAll(id,fname,lname,gender,email,password,address,birth_date,fav_books,expiry_date);

        Button exit = new Button("Exit");
        exit.setPrefWidth(30);
        exit.setPrefWidth(130);
        exit.setStyle("-fx-background-color: #ee8e19;");
        exit.setFont(new Font("Arial Bold",14));
        exit.setOnAction(e->{
            Home home = new Home();
            home.start(Remove_memberStage);
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


        Pane pane = new Pane();
        pane.setPrefHeight(661);
        pane.setPrefWidth(1180);
        pane.setStyle("-fx-background-color: white;");
        pane.getChildren().addAll(Table,title,hBox2);

        Scene view_bookScene = new Scene(pane);
        Remove_memberStage.setScene(view_bookScene);
        Remove_memberStage.show();






    }






}
