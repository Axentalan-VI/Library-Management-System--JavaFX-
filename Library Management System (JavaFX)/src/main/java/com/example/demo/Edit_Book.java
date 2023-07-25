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
import java.sql.Statement;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Edit_Book extends Application {
    Connection dblink = DBconnect.getConnection();

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage edit_bookStage) {

        Label edit_title = new Label("Edit Book");
        edit_title.setMaxHeight(1.7976931348623157E308);
        edit_title.setMaxWidth(1.7976931348623157E308);
        edit_title.setPrefHeight(140);
        edit_title.setPrefWidth(517);
        edit_title.setStyle("-fx-background-color: #ee8e19;");
        edit_title.setFont(new Font("Times New Roman Bold",18));
        edit_title.setPadding(new Insets(0,0,0,20));

       Label enter_id = new Label("Enter The ID of The Book : ");
       enter_id.setPrefWidth(150);
       enter_id.setPrefHeight(34);


        TextField id = new TextField();
        id.setPrefHeight(35);
        id.setPrefWidth(188);
        id.setPromptText("Book ID");


        Label mssg = new Label();
        mssg.setAlignment(Pos.CENTER);
        mssg.setTextFill(Color.RED);
        mssg.setPrefHeight(23);
        mssg.setPrefWidth(391);
        mssg.setTextAlignment(TextAlignment.CENTER);

        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER_LEFT);
        hBox.setPrefHeight(168);
        hBox.setPrefWidth(517);
        hBox.setSpacing(20);
        hBox.getChildren().addAll(enter_id,id);
        HBox.setMargin(enter_id,new Insets(0,0,0,10));


        Button editButton = new Button("Edit");
        editButton.setPrefHeight(58);
        editButton.setPrefWidth(145);
        editButton.setStyle("-fx-background-color: #ee8e19;");
        editButton.setFont(new Font("Arial Bold",14));





        VBox edit = new VBox();
        edit.setAlignment(Pos.CENTER);
        edit.setPrefHeight(117);
        edit.setPrefWidth(517);
        edit.setStyle("-fx-background-color: white;");
        edit.getChildren().add(editButton);
        VBox.setMargin(editButton,new Insets(0,20,0,0));



        Label edit_title2 = new Label("Book Information");
        edit_title2.setAlignment(Pos.CENTER);
        edit_title2.setMaxHeight(1.7976931348623157E308);
        edit_title2.setMaxWidth(1.7976931348623157E308);
        edit_title2.setPrefHeight(39);
        edit_title2.setPrefWidth(200);
        edit_title2.setStyle("-fx-background-color: white;");
        edit_title2.setTextAlignment(TextAlignment.CENTER);
        edit_title2.setTextOverrun(OverrunStyle.CLIP);
        edit_title2.setFont(new Font("Times New Roman Bold",15));



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
        avaliabe.setMnemonicParsing(false);
        avaliabe.setFont(new Font("Arial",14));


        editButton.setOnAction(e->{
            try {


                if (id.getText().isBlank()) {mssg.setText("Please Enter the ID");}
                else {
                    mssg.setText(" ");
                    String query = "Select * from Book_tbl where book_id ='" + Integer.parseInt(id.getText()) + "';";
                    Statement statement = dblink.createStatement();

                    ResultSet resultSet = statement.executeQuery(query);


                    while (resultSet.next()){
                        book_title.setText(resultSet.getString(2));
                        language.setText(resultSet.getString(3));
                        category.setText(resultSet.getString(4));
                        author.setText(resultSet.getString(5));
                        publisher.setText(resultSet.getString(6));
                        Date date = resultSet.getDate(7);


                        publish_date.setValue(Instant.ofEpochMilli(date.getTime())
                                .atZone(ZoneId.systemDefault())
                                .toLocalDate());
                        price.setText(resultSet.getString(8));
                        num_of_pages.setText(resultSet.getString(9));
                        boolean status = resultSet.getString(10).equals("1");
                        avaliabe.setSelected(status);


                    }

                }
            }
            catch (Exception ex){
                mssg.setText(ex.getMessage());

            }

        });





        Button update = new Button("UPDATE");
        update.setPrefHeight(42);
        update.setPrefWidth(184);
        update.setStyle("-fx-background-color: #ee8e19");
        update.setFont(new Font("Arial Bold",14));
        update.setOnAction(e-> {
            try {


                if (book_title.getText().isBlank() || language.getText().isBlank() || category.getText().isBlank() || author.getText().isBlank()
                        || publisher.getText().isBlank() || price.getText().isBlank() || num_of_pages.getText().isBlank() || publish_date.getValue().toString().isBlank()) {
                    mssg.setText("Please Enter All the information");
                } else {
                    if (id.getText().chars().allMatch(Character::isDigit) || price.getText().chars().allMatch(Character::isDigit) || num_of_pages.getText().chars().allMatch(Character::isDigit)) {
                        String status = avaliabe.isSelected() ? "1" : "0";
                        String update_data = "update Book_tbl " +
                                "set book_title ='"+book_title.getText()+"', book_language ='"+language.getText()+"', book_category ='"+category.getText()+"' , book_author ='"+author.getText()+"', book_publisher ='"+publisher.getText()+"', book_publish_date ='"+publish_date.getValue()+"' , book_price ='"+Double.parseDouble(price.getText())+"' , num_of_pages ='"+num_of_pages.getText()+"', avalible ='"+status+"' " +
                                "where book_id ='"+Integer.parseInt(id.getText())+"';";
                        Connection dblink = DBconnect.getConnection();

                        Statement statement = dblink.createStatement();
                        int result = statement.executeUpdate(update_data,Integer.parseInt(id.getText()));
                        if(result > 0){
                            mssg.setTextFill(Color.BLACK);
                            mssg.setText("Book updated Successfully");
                        }
                        else {
                            mssg.setTextFill(Color.RED);
                            mssg.setText("Something went wrong. Please try again");
                        }



                    }

                }
            }catch (Exception ex){
                mssg.setText(ex.getMessage());
                System.out.println(ex.getMessage());
            }

        });








        VBox textfileds = new VBox();
        textfileds.setAlignment(Pos.CENTER);
        textfileds.setPrefWidth(485);
        textfileds.setPrefHeight(471);
        textfileds.setSpacing(10);
        textfileds.setStyle("-fx-background-color: white;");
        textfileds.getChildren().addAll(book_title,language,category,author,publisher,publish_date,price,num_of_pages,avaliabe,update,mssg);
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
            home.start(edit_bookStage);
        });


        HBox hBox2 = new HBox();
        hBox2.setAlignment(Pos.BOTTOM_RIGHT);
        hBox2.setMaxHeight(1.7976931348623157E308);
        hBox2.setMaxWidth(1.7976931348623157E308);
        hBox2.setPrefHeight(48);
        hBox2.setPrefWidth(517);
        hBox2.setStyle("-fx-background-color: white;");
        hBox2.getChildren().add(exit);
        HBox.setMargin(exit,new Insets(0,10,10,0));






        VBox vBox = new VBox();
        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.setMaxHeight(1.7976931348623157E308);
        vBox.setMaxWidth(1.7976931348623157E308);
        vBox.setPrefHeight(658);
        vBox.setPrefWidth(517);
        vBox.setSpacing(10);
        vBox.setStyle("-fx-background-color: white;");
        vBox.getChildren().addAll(edit_title,hBox,edit,edit_title2,textfileds,hBox2);


        Scene add_bookScene = new Scene(vBox);
        edit_bookStage.setScene(add_bookScene);
        edit_bookStage.show();



    }
}
