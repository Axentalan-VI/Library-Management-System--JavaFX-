package com.example.demo;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Home_Member extends Application{

    static Scene homescene;
    Connection dblink = DBconnect.getConnection();



    @Override
    public void start(Stage homeStage) {


        MenuItem view_book = new MenuItem("View Book");
        view_book.setMnemonicParsing(false);
        view_book.setOnAction(e->{
            View_Books_Member view_books = new View_Books_Member();
            view_books.start(homeStage);
        });
        MenuItem search_book = new MenuItem("Search Book");
        search_book.setMnemonicParsing(false);
        search_book.setOnAction(e->{
            Search_Book_Members search_book1 = new Search_Book_Members();
            search_book1.start(homeStage);
        });
        MenuItem Borrow_book = new MenuItem("Borrow Book");
        Borrow_book.setMnemonicParsing(false);
        Borrow_book.setOnAction(e->{
            Borrow_Book borrow_book = new Borrow_Book();
            borrow_book.start(homeStage);


        });

        MenuItem Return_book = new MenuItem("Return Book");
        Return_book.setMnemonicParsing(false);
        Return_book.setOnAction(e->{
            Return_Book_Member return_book_member = new Return_Book_Member();
            return_book_member.start(homeStage);


        });


        MenuButton book = new MenuButton("Books");
        book.setAlignment(Pos.CENTER);
        book.setMnemonicParsing(false);
        book.setPrefHeight(51);
        book.setPrefWidth(164);
        book.setStyle("-fx-background-color: #ee8e19");
        book.setTextFill(Color.BLACK);
        book.getItems().addAll(view_book,search_book,Borrow_book,Return_book);
        book.setFont(new Font("SansSerif Bold",13));






        VBox left = new VBox();
        left.setAlignment(Pos.CENTER_RIGHT);
        left.setSpacing(20);
        left.setPrefHeight(439);
        left.setPrefWidth(175);
        left.setStyle("-fx-background-color: white");
        left.getChildren().addAll(book);
        VBox.setMargin(book, new Insets(-65, 0, 0, 0));



        Label name = new Label("Welcome  ");
        name.setTextFill(Color.valueOf("#162331"));
        name.setFont(new Font("Arial Bold",18));
        name.setPrefHeight(77);
        name.setPrefWidth(500);
        name.setStyle("-fx-background-color: white;");

        Label id = new Label("ID :  ");
        id.setTextFill(Color.valueOf("#162331"));
        id.setFont(new Font("Helvetica", 18));
        id.setPrefHeight(42);
        id.setPrefWidth(456);
        id.setStyle("-fx-background-color: white;");

        Label gender = new Label("Gender :  ");
        gender.setTextFill(Color.valueOf("#162331"));
        gender.setFont(new Font("Helvetica", 18));
        gender.setPrefHeight(42);
        gender.setPrefWidth(456);
        gender.setStyle("-fx-background-color: white;");



        Label email = new Label("Email : ");
        email.setTextFill(Color.valueOf("#162331"));
        email.setFont(new Font("Helvetica", 18));
        email.setPrefHeight(42);
        email.setPrefWidth(456);
        email.setStyle("-fx-background-color: white;");

        Label address = new Label("Address : ");
        address.setTextFill(Color.valueOf("#162331"));
        address.setFont(new Font("Helvetica",18));
        address.setPrefHeight(42);
        address.setPrefWidth(456);
        address.setStyle("-fx-background-color: white;");

        Label Birth = new Label("Birth Date : ");
        Birth.setTextFill(Color.valueOf("#162331"));
        Birth.setFont(new Font("Helvetica",18));
        Birth.setPrefHeight(42);
        Birth.setPrefWidth(456);
        Birth.setStyle("-fx-background-color: white;");

        Label fav_book = new Label("Favorite Books : ");
        fav_book.setTextFill(Color.valueOf("#162331"));
        fav_book.setFont(new Font("Helvetica",18));
        fav_book.setPrefHeight(42);
        fav_book.setPrefWidth(456);
        fav_book.setStyle("-fx-background-color: white;");


        Label exp_date = new Label("Expiry Date : ");
        exp_date.setTextFill(Color.valueOf("#162331"));
        exp_date.setFont(new Font("Helvetica",18));
        exp_date.setPrefHeight(42);
        exp_date.setPrefWidth(456);
        exp_date.setStyle("-fx-background-color: white;");

        Button edit = new Button("Edit");
        edit.setTextFill(Color.BLACK);
        edit.setPrefHeight(51);
        edit.setPrefWidth(164);
        edit.setStyle("-fx-background-color: #ee8e19;");
        edit.setFont(new Font("SansSerif Bold",13));
        edit.setOnAction(e->{
            Edit_Member_info edit_member_info = new Edit_Member_info();
            edit_member_info.start(homeStage);
        });

        Button exit = new Button("Exit");
        exit.setMnemonicParsing(false);
        exit.setPrefHeight(51);
        exit.setPrefWidth(164);
        exit.setStyle("-fx-background-color: #ee8e19");
        exit.setFont(new Font("SansSerif Bold",13));
        exit.setTextFill(Color.BLACK);
        exit.setOnAction(e->{
            Login login = new Login();
            login.start(homeStage);
        });



     try {
            String query1= "select * from Member_tbl where member_id = '"+Login.key+"';";

            Statement statement = dblink.createStatement();
            ResultSet resultSet = statement.executeQuery(query1);
            while (resultSet.next()){
                name.setText(name.getText()+ resultSet.getString(2) + " "+ resultSet.getString(3));
                id.setText(id.getText()+resultSet.getInt(1));
                gender.setText(gender.getText()+ resultSet.getString(4) );
                email.setText(email.getText()+ resultSet.getString(5) );
                address.setText(address.getText()+ resultSet.getString(7) );
                Birth.setText(Birth.getText()+ resultSet.getDate(8) );
                fav_book.setText(fav_book.getText()+ resultSet.getString(9) );
                exp_date.setText(exp_date.getText()+ resultSet.getDate(10) );

            }



        }catch (Exception e){
            System.out.println(e.getMessage());
        }


        VBox center = new VBox();
        center.setPrefHeight(200);
        center.setPrefWidth(100);
        center.getChildren().addAll(name,id,gender,email,address,Birth,fav_book,exp_date,edit,exit);
        VBox.setMargin(name, new Insets(100, 0, 0, 155));
        VBox.setMargin(id, new Insets(0, 0, 0, 155));
        VBox.setMargin(gender, new Insets(0, 0, 0, 155));
        VBox.setMargin(email, new Insets(0, 0, 0, 155));
        VBox.setMargin(address, new Insets(0, 0, 0, 155));
        VBox.setMargin(Birth, new Insets(0, 0, 0, 155));
        VBox.setMargin(fav_book, new Insets(0, 0, 0, 155));
        VBox.setMargin(exp_date, new Insets(0, 0, 0, 155));
        VBox.setMargin(edit, new Insets(20, 0, 0, 355));
        VBox.setMargin(exit, new Insets(20, 0, 0, 550));




        Label title = new Label("Library Management System");
        title.setAlignment(Pos.CENTER);
        title.setPrefHeight(64);
        title.setPrefWidth(2000);
        title.setStyle("-fx-background-color: #ee8e19;");
        title.setTextFill(Color.BLACK);
        title.setFont(new Font("SansSerif Bold", 24));


        BorderPane homePane = new BorderPane();
        homePane.setPrefHeight(700);
        homePane.setPrefWidth(924);
        homePane.setStyle("-fx-background-color: white");
        homePane.setLeft(left);
        homePane.setTop(title);
        homePane.setCenter(center);
        BorderPane.setAlignment(left, Pos.CENTER_LEFT);
        BorderPane.setAlignment(title, Pos.CENTER);
        BorderPane.setAlignment(center, Pos.CENTER);




        homescene = new Scene(homePane);
        homeStage.setScene(homescene);
        homeStage.show();



    }


    public static void main() {
        launch();
    }


}
