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

public class View_Books extends Application {

    Connection dblink = DBconnect.getConnection();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage view_bookStage) {


        Label title = new Label("View Books");
        title.setMaxHeight(1.7976931348623157E308);
        title.setMaxWidth(1.7976931348623157E308);
        title.setPrefHeight(66);
        title.setPrefWidth(1180);
        title.setStyle("-fx-background-color: #ee8e19;");
        title.setTextFill(Color.BLACK);
        title.setFont(new Font("Times New Roman Bold",18));
        title.setPadding(new Insets(0,0,0,20));

        ObservableList<Book> books = FXCollections.observableArrayList();






        try {
            Statement statement = dblink.createStatement();
            String Query = "Select * from Book_tbl ;";
            ResultSet resultSet = statement.executeQuery(Query);
            while(resultSet.next()){
                books.add(new Book(resultSet.getInt(1), resultSet.getString(2),resultSet.getString(3), resultSet.getString(4)
                        ,resultSet.getString(5),resultSet.getString(6),resultSet.getDate(7),resultSet.getDouble(8),resultSet.getInt(9)
                        ,resultSet.getBoolean(10)));

            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }


        TableColumn<Book,Integer>id = new TableColumn<>("Book ID");
        id.setPrefWidth(75);
        id.setCellValueFactory(new PropertyValueFactory<>("ID"));

        TableColumn<Book,String>book_title = new TableColumn<>("Book Title");
        book_title.setPrefWidth(204.79409790039062);
        book_title.setCellValueFactory(new PropertyValueFactory<>("title"));

        TableColumn<Book,String>language = new TableColumn<>("Book Language");
        language.setPrefWidth(123.99105834960938);
        language.setCellValueFactory(new PropertyValueFactory<>("language"));

        TableColumn<Book,String>category = new TableColumn<>("Book Category");
        category.setPrefWidth(107.3759765625);
        category.setCellValueFactory(new PropertyValueFactory<>("category"));

        TableColumn<Book,String>author = new TableColumn<>("Book Author");
        author.setPrefWidth(96.048583984375);
        author.setCellValueFactory(new PropertyValueFactory<>("author"));

        TableColumn<Book,String>publisher = new TableColumn<>("Book Publisher");
        publisher.setPrefWidth(103.951416015625);
        publisher.setCellValueFactory(new PropertyValueFactory<>("publisher"));

        TableColumn<Book, Date>publish_date = new TableColumn<>("Book Publish Date");
        publish_date.setPrefWidth(118.82733154296875);
        publish_date.setCellValueFactory(new PropertyValueFactory<>("publish_date"));

        TableColumn<Book,Double>book_price = new TableColumn<>("Book Price");
        book_price.setPrefWidth(84.3031005859375);
        book_price.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<Book,Integer>number_of_pages = new TableColumn<>("Number of Pages");
        number_of_pages.setPrefWidth(120.9093017578125);
        number_of_pages.setCellValueFactory(new PropertyValueFactory<>("num_of_pages"));

        TableColumn<Book,Boolean>available = new TableColumn<>("Available");
        available.setPrefWidth(102.5);
        available.setCellValueFactory(new PropertyValueFactory<>("available"));


        TableView<Book> Table = new TableView<>();
        Table.setLayoutX(19);
        Table.setLayoutY(105);
        Table.setPrefHeight(487);
        Table.setPrefWidth(1140);
        Table.setMaxHeight(1.7976931348623157E308);
        Table.setMaxWidth(1.7976931348623157E308);
        Table.setItems(books);
        Table.getColumns().addAll(id,book_title,language,category,author,publisher,publish_date,book_price,number_of_pages,available);

        Button exit = new Button("Exit");
        exit.setPrefWidth(30);
        exit.setPrefWidth(130);
        exit.setStyle("-fx-background-color: #ee8e19;");
        exit.setFont(new Font("Arial Bold",14));
        exit.setOnAction(e->{
            Home home = new Home();
            home.start(view_bookStage);
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
        view_bookStage.setScene(view_bookScene);
        view_bookStage.show();






    }






}
