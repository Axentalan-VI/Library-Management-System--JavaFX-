package com.example.demo;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Add_Book extends Application {



    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage add_bookStage) {

        Label title = new Label("Add Book");
        title.setMaxHeight(1.7976931348623157E308);
        title.setMaxWidth(1.7976931348623157E308);
        title.setPrefHeight(45);
        title.setPrefWidth(625);
        title.setStyle("-fx-background-color: #ee8e19;");
        title.setFont(new Font("Times New Roman Bold",18));
        title.setPadding(new Insets(0,0,0,20));

        Label title2 = new Label("Book Information");
        title2.setAlignment(Pos.CENTER);
        title2.setMaxHeight(1.7976931348623157E308);
        title2.setMaxWidth(1.7976931348623157E308);
        title2.setPrefHeight(39);
        title2.setPrefWidth(264);
        title2.setStyle("-fx-background-color: white;");
        title2.setTextAlignment(TextAlignment.CENTER);
        title2.setFont(new Font("Times New Roman Bold",15));

        TextField id = new TextField();
        id.setPrefHeight(35);
        id.setPrefWidth(276);
        id.setPromptText("Book ID");

        TextField book_title = new TextField();
       book_title.setPrefHeight(35);
       book_title.setPrefWidth(276);
       book_title.setPromptText("Book Title");

        TextField language = new TextField();
        language.setPrefHeight(35);
        language.setPrefWidth(276);
        language.setPromptText("Book Language");

        TextField category = new TextField();
        category.setPrefHeight(35);
        category.setPrefWidth(276);
        category.setPromptText("Book Category");

        TextField author = new TextField();
       author.setPrefHeight(35);
       author.setPrefWidth(276);
       author.setPromptText("Book Author");

        TextField publisher = new TextField();
        publisher.setPrefHeight(35);
        publisher.setPrefWidth(276);
        publisher.setPromptText("Book Publisher");

        DatePicker publish_date = new DatePicker();
        publish_date.setPrefHeight(35);
        publish_date.setPrefWidth(376);
        publish_date.setPromptText("Book Publish Date");

        TextField price = new TextField();
        price.setPrefHeight(35);
        price.setPrefWidth(276);
        price.setPromptText("Book Price");

        TextField num_of_pages = new TextField();
       num_of_pages.setPrefHeight(35);
       num_of_pages.setPrefWidth(276);
       num_of_pages.setPromptText("Number of Pages");

        CheckBox avaliabe = new CheckBox("Available");
        avaliabe.setPrefHeight(29);
        avaliabe.setPrefWidth(276);
        avaliabe.setFont(new Font("Arial",14));

        Label mssg = new Label();
        mssg.setAlignment(Pos.CENTER);
        mssg.setTextFill(Color.RED);
        mssg.setPrefHeight(23);
        mssg.setPrefWidth(391);
        mssg.setTextAlignment(TextAlignment.CENTER);




        Button add = new Button("ADD");
        add.setPrefHeight(42);
        add.setPrefWidth(184);
        add.setStyle("-fx-background-color: #ee8e19");
        add.setFont(new Font("Arial Bold",14));
        add.setOnAction(e-> {
            try {

            if (id.getText().isBlank() || book_title.getText().isBlank() || language.getText().isBlank() || category.getText().isBlank() || author.getText().isBlank()
                    || publisher.getText().isBlank() || price.getText().isBlank() || num_of_pages.getText().isBlank() || publish_date.getValue().toString().isBlank()) {
                mssg.setText("Please Enter All the information");
            }else if(Integer.parseInt(id.getText()) < 0 || Double.parseDouble(price.getText())<0||Integer.parseInt(num_of_pages.getText())<0){
                mssg.setText("Please Enter a valid input for the Digit Fields");

            }
            else {
                if (id.getText().chars().allMatch(Character::isDigit) || price.getText().chars().allMatch(Character::isDigit) || num_of_pages.getText().chars().allMatch(Character::isDigit)) {
                    String status = avaliabe.isSelected() ? "1" : "0";
                    String insertstat = "INSERT INTO `Library_db`.`Book_tbl` (`book_id`, `book_title`, `book_language`, `book_category`, `book_author`, `book_publisher`, `book_publish_date`, `book_price`, `num_of_pages`, `avalible`) VALUES ('" + Integer.parseInt(id.getText()) + "', '" + book_title.getText() + "', '" + language.getText() + "', '" + category.getText() + "', '" + author.getText() + "', '" + publisher.getText() + "', '" + publish_date.getValue() + "', '" + Double.parseDouble(price.getText()) + "', '" + Integer.parseInt(num_of_pages.getText()) + "', '" + status + "');";
                    Connection dblink = DBconnect.getConnection();

                    Statement statement = dblink.createStatement();
                    boolean inserted = statement.execute(insertstat);
                    if(!inserted){
                        mssg.setText("Book Inserted Successfully");
                    }
                    else {
                        mssg.setText("Something went wrong. Please try again");
                    }


                }

            }
        }catch (SQLException ex){
                mssg.setText(ex.getMessage());
                System.out.println(ex.getMessage());
            }catch (Exception f){
                mssg.setText(f.getMessage());
            }

        });






        VBox textfileds = new VBox();
        textfileds.setAlignment(Pos.CENTER);

        textfileds.setPrefWidth(485);
        textfileds.setPrefHeight(471);
        textfileds.setSpacing(10);
        textfileds.setStyle("-fx-background-color: white;");
        textfileds.getChildren().addAll(id,book_title,language,category,author,publisher,publish_date,price,num_of_pages,avaliabe,add,mssg);
        VBox.setMargin(id,new Insets(0,100,0,100));
        VBox.setMargin(book_title,new Insets(0,100,0,100));
        VBox.setMargin(language,new Insets(0,100,0,100));
        VBox.setMargin(category,new Insets(0,100,0,100));
        VBox.setMargin(author,new Insets(0,100,0,100));
        VBox.setMargin(publisher,new Insets(0,100,0,100));
        VBox.setMargin(publish_date,new Insets(0,100,0,100));
        VBox.setMargin(price,new Insets(0,100,0,100));
        VBox.setMargin(num_of_pages,new Insets(0,100,0,100));


        Button exit = new Button("Exit");
        exit.setPrefWidth(30);
        exit.setPrefWidth(130);
        exit.setStyle("-fx-background-color: #ee8e19;");
        exit.setFont(new Font("Arial Bold",14));
        exit.setOnAction(e->{
            Home home = new Home();
            home.start(add_bookStage);
        });


        HBox hBox = new HBox();
        hBox.setAlignment(Pos.BOTTOM_RIGHT);
        hBox.setMaxHeight(1.7976931348623157E308);
        hBox.setMaxWidth(1.7976931348623157E308);
        hBox.setPrefHeight(48);
        hBox.setPrefWidth(517);
        hBox.setStyle("-fx-background-color: white;");
        hBox.getChildren().add(exit);
        HBox.setMargin(exit,new Insets(0,10,10,0));






        VBox vBox = new VBox();
        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.setMaxHeight(1.7976931348623157E308);
        vBox.setMaxWidth(1.7976931348623157E308);
        vBox.setPrefHeight(626);
        vBox.setPrefWidth(517);
        vBox.setSpacing(10);
        vBox.setStyle("-fx-background-color: white;");
        vBox.getChildren().addAll(title,title2,textfileds,hBox);


        Scene add_bookScene = new Scene(vBox);
        add_bookStage.setScene(add_bookScene);
        add_bookStage.show();



    }
}
