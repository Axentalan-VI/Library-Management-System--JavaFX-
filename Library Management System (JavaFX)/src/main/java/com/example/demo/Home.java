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

public class Home extends Application{

    static Scene homescene;
    Connection dblink = DBconnect.getConnection();



    @Override
    public void start(Stage homeStage) {



        MenuItem add_book = new MenuItem("Add Book");
        add_book.setMnemonicParsing(false);
        add_book.setOnAction(e->{
            Add_Book add_book1 = new Add_Book();
            add_book1.start(homeStage);
        });
        MenuItem view_book = new MenuItem("View Book");
        view_book.setMnemonicParsing(false);
        view_book.setOnAction(e->{
            View_Books view_books = new View_Books();
            view_books.start(homeStage);
        });
        MenuItem search_book = new MenuItem("Search Book");
        search_book.setMnemonicParsing(false);
        search_book.setOnAction(e->{
            Search_Book search_book1 = new Search_Book();
            search_book1.start(homeStage);
        });


        MenuItem edit_book = new MenuItem("Edit Book");
        edit_book.setMnemonicParsing(false);
        edit_book.setOnAction(e->{
            Edit_Book edit_book1 = new Edit_Book();
            edit_book1.start(homeStage);
        });
        MenuItem remove_book = new MenuItem("Remove Book");
        remove_book.setMnemonicParsing(false);
        remove_book.setOnAction(e->{
            Remove_Book remove_book1 = new Remove_Book();
            remove_book1.start(homeStage);

        });


        MenuButton book = new MenuButton("Book Management");
        book.setMnemonicParsing(false);
        book.setPrefHeight(51);
        book.setPrefWidth(164);
        book.setStyle("-fx-background-color: #ee8e19");
        book.setTextFill(Color.BLACK);
        book.getItems().addAll(add_book,view_book,search_book,edit_book,remove_book);

        MenuItem create_user = new MenuItem("Add Member");
        create_user.setMnemonicParsing(false);
        create_user.setOnAction(e->{
            Add_Member add_member = new Add_Member();
            add_member.start(homeStage);
        });
        MenuItem manage_user = new MenuItem("Edit Member");
        manage_user.setMnemonicParsing(false);
        manage_user.setOnAction(e->{
            Edit_Member edit_member = new Edit_Member();
            edit_member.start(homeStage);
        });
        MenuItem delete_user = new MenuItem("Delete Member");
        delete_user.setMnemonicParsing(false);
        delete_user.setOnAction(e->{
            Remove_Member remove_member = new Remove_Member();
            remove_member.start(homeStage);
        });
        MenuItem view_users = new MenuItem("View Members");
        view_users.setMnemonicParsing(false);
        view_users.setOnAction(e->{
            View_Member view_member = new View_Member();
            view_member.start(homeStage);
        });
        MenuItem search_member = new MenuItem("Search Member");
        search_member.setMnemonicParsing(false);
        search_member.setOnAction(e->{
            Search_Member search_member1 = new Search_Member();
            search_member1.start(homeStage);

        });

        MenuButton user = new MenuButton("Member Accounts");
        user.setMnemonicParsing(false);
        user.setPrefHeight(49);
        user.setPrefWidth(164);
        user.setStyle("-fx-background-color: #ee8e19");
        user.setTextFill(Color.BLACK);
        user.getItems().addAll(create_user,view_users,search_member,manage_user,delete_user);




        Button Return_book = new Button("Return Book");
        Return_book.setMnemonicParsing(false);
        Return_book.setPrefHeight(53);
        Return_book.setPrefWidth(164);
        Return_book.setStyle("-fx-background-color: #ee8e19");
        Return_book.setTextFill(Color.BLACK);
        Return_book.setOnAction(e->{
            Return_Book return_book = new Return_Book();
            return_book.start(homeStage);
        });

        Button exit = new Button("Exit");
        exit.setMnemonicParsing(false);
        exit.setPrefHeight(53);
        exit.setPrefWidth(164);
        exit.setStyle("-fx-background-color: #ee8e19");
        exit.setFont(new Font("SansSerif Bold",13));
        exit.setTextFill(Color.BLACK);
        exit.setOnAction(e->{
            Login login = new Login();
            login.start(homeStage);
        });


        VBox left = new VBox();
        left.setAlignment(Pos.CENTER_RIGHT);
        left.setPrefHeight(439);
        left.setPrefWidth(175);
        left.setStyle("-fx-background-color: white");
        left.getChildren().addAll(book,user,Return_book);
        VBox.setMargin(book, new Insets(-65, 0, 100, 0));
        VBox.setMargin(user, new Insets(20, 0, 100, 0));
        VBox.setMargin(Return_book, new Insets(20, 0, 0, 0));



        Label Quick_report = new Label("Quick Inventory Report");
        Quick_report.setTextFill(Color.valueOf("#162331"));
        Quick_report.setFont(new Font("Arial Bold",18));
        Quick_report.setPrefHeight(77);
        Quick_report.setPrefWidth(222);
        Quick_report.setStyle("-fx-background-color: white;");

        Label Total_books = new Label("Total Books : ");
        Total_books.setTextFill(Color.valueOf("#162331"));
        Total_books.setFont(new Font("Helvetica", 18));
        Total_books.setPrefHeight(42);
        Total_books.setPrefWidth(456);
        Total_books.setStyle("-fx-background-color: white;");


        Label Total_users = new Label("Total Users : ");
        Total_users.setTextFill(Color.valueOf("#162331"));
        Total_users.setFont(new Font("Helvetica", 18));
        Total_users.setPrefHeight(42);
        Total_users.setPrefWidth(456);
        Total_users.setStyle("-fx-background-color: white;");

        Label price_of_books = new Label("Total Price of Books : ");
        price_of_books.setTextFill(Color.valueOf("#162331"));
        price_of_books.setFont(new Font("Helvetica",18));
        price_of_books.setPrefHeight(42);
        price_of_books.setPrefWidth(456);
        price_of_books.setStyle("-fx-background-color: white;");




        try {
            String query1= "select count(book_id) from Book_tbl ;";
            String query2= "select count(member_id) from Member_tbl ;";
            String query3= "select sum(book_price) from Book_tbl ;";
            Statement statement = dblink.createStatement();
            ResultSet resultSet = statement.executeQuery(query1);
            while (resultSet.next()){
                Total_books.setText(Total_books.getText()+ resultSet.getInt(1));
            }
            resultSet = statement.executeQuery(query2);
            while (resultSet.next()){
                Total_users.setText(Total_users.getText()+ resultSet.getInt(1));
            }
            resultSet = statement.executeQuery(query3);
            while (resultSet.next()){
                price_of_books.setText(price_of_books.getText()+ resultSet.getDouble(1));
            }



        }catch (Exception e){
            System.out.println(e.getMessage());
        }


        VBox center = new VBox();
        center.setPrefHeight(200);
        center.setPrefWidth(100);
        center.getChildren().addAll(Quick_report,Total_books,Total_users,price_of_books,exit);
        VBox.setMargin(Quick_report, new Insets(200, 0, 0, 235));
        VBox.setMargin(Total_books, new Insets(15, 0, 0, 235));
        VBox.setMargin(Total_users, new Insets(0, 0, 0, 235));
        VBox.setMargin(price_of_books, new Insets(0, 0, 0, 235));
        VBox.setMargin(exit, new Insets(20, 0, 0, 600));



        Label title = new Label("Library Management System");
        title.setAlignment(Pos.CENTER);
        title.setPrefHeight(64);
        title.setPrefWidth(2000);
        title.setStyle("-fx-background-color: #ee8e19;");
        title.setTextFill(Color.BLACK);
        title.setFont(new Font("SansSerif Bold", 24));


        BorderPane homePane = new BorderPane();
        homePane.setPrefHeight(700);
        homePane.setPrefWidth(1000);
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
