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
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class Borrow_Book extends Application {

    Connection dblink = DBconnect.getConnection();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage Borrow_bookStage) {

        Label title = new Label("Borrow Book");
        title.setPrefHeight(231);
        title.setPrefWidth(517);
        title.setStyle("-fx-background-color: #ee8e19;");
        title.setFont(new Font("Times New Roman Bold",18));
        title.setPadding(new Insets(0,0,0,20));


        Label title2 = new Label("Book Information");
        title2.setAlignment(Pos.CENTER);
        title2.setPrefHeight(203);
        title2.setPrefWidth(517);
        title2.setStyle("-fx-background-color: white;");
        title2.setTextAlignment(TextAlignment.CENTER);
        title2.setTextOverrun(OverrunStyle.CLIP);
        title2.setFont(new Font("Times New Roman Bold",15));



        Label book_text = new Label("Enter The Book ID : ");
        book_text.setFont(new Font("Times New Roman",14));

        TextField book_id = new TextField();
        book_id.setPrefHeight(35);
        book_id.setPrefWidth(182);
        book_id.setPromptText("Book ID");


        HBox hBox1 = new HBox();
        hBox1.setAlignment(Pos.CENTER);
        hBox1.setPrefHeight(64);
        hBox1.setPrefWidth(517);
        hBox1.setSpacing(40);
        hBox1.getChildren().addAll(book_text,book_id);


        Label borrow_book = new Label("Enter The Borrow Date : ");
        borrow_book.setFont(new Font("Times New Roman",14));

        DatePicker borrow_date = new DatePicker();
        borrow_date.setValue(LocalDate.now());
        borrow_date.setPrefHeight(26);
        borrow_date.setPrefWidth(178);


        HBox hBox2 = new HBox();
        hBox2.setAlignment(Pos.CENTER);
        hBox2.setPrefHeight(64);
        hBox2.setPrefWidth(517);
        hBox2.setSpacing(15);
        hBox2.getChildren().addAll(borrow_book,borrow_date);


        Label return_text = new Label("Enter The Return Date : ");
        return_text.setFont(new Font("Times New Roman",14));

        DatePicker return_date = new DatePicker();
        return_date.setPrefHeight(26);
        return_date.setPrefWidth(178);


        HBox hBox3 = new HBox();
        hBox3.setAlignment(Pos.CENTER);
        hBox3.setPrefHeight(64);
        hBox3.setPrefWidth(517);
        hBox3.setSpacing(15);
        hBox3.getChildren().addAll(return_text,return_date);

        Label mssg = new Label();
        mssg.setPrefHeight(17);
        mssg.setPrefWidth(467);
        mssg.setTextAlignment(TextAlignment.CENTER);
        mssg.setAlignment(Pos.CENTER);
        mssg.setTextFill(Color.RED);

        Button returnButton = new Button("BORROW");
        returnButton.setPrefHeight(57);
        returnButton.setPrefWidth(184);
        returnButton.setStyle("-fx-background-color: #ee8e19;");
        returnButton.setFont(new Font("Arial Bold",14));
        returnButton.setOnAction(e->{
            try {


                if (book_id.getText().isBlank() ||borrow_date.getValue().toString().isBlank()|| return_date.getValue().toString().isBlank()) {
                    mssg.setText("Please Enter All the information");
                } else if (Integer.parseInt(book_id.getText()) < 0 ) {
                    mssg.setText("Please Enter a valid input for the Digit Fields");
                } else if (borrow_date.getValue().isAfter(return_date.getValue())) {
                    mssg.setText("Borrow Date can't be after return date!!!");

                }
                 else {
                    Statement statement = dblink.createStatement();
                   String query = "Select avalible from Book_tbl where book_id = '"+Integer.parseInt(book_id.getText())+"';";
                   ResultSet resultSet = statement.executeQuery(query);
                   Boolean available = false;
                   while (resultSet.next()){
                      available =  resultSet.getInt(1)==1;
                   }


                    if (available){
                        String insert = "INSERT INTO `Library_db`.`Borrow_tbl` (`book_id`, `member_id`, `borrow_date`, `return_date`) VALUES ('"+Integer.parseInt(book_id.getText())+"', '"+Login.key+"', '"+borrow_date.getValue()+"', '"+return_date.getValue()+"');";
                        Boolean inserted = statement.execute(insert);
                        if(!inserted){
                            mssg.setText("Book Borrowed Successfully");
                            String update = "update Book_tbl " +
                                    "set avalible = '0' " +
                                    "where book_id = '"+Integer.parseInt(book_id.getText())+"';";
                            statement.executeUpdate(update);
                            String delete = "delete from Return_tbl where book_id = '"+Integer.parseInt(book_id.getText())+"';";
                            statement.execute(delete);
                        }
                        else {
                            mssg.setText("Somthing went wrong. Please try again!!");
                        }

                    }
                    else {
                        mssg.setText("Book with id "+book_id.getText()+" is not Available right now");
                    }

                }
            } catch (DateTimeException t){
                mssg.setText(t.getMessage());

            }catch (Exception ex){
                mssg.setText(ex.getMessage());

            }


        });





        VBox data = new VBox();
        data.setAlignment(Pos.CENTER);
        data.setPrefHeight(471);
        data.setPrefWidth(517);
        data.setSpacing(20);
        data.setStyle("-fx-background-color: white;");
        data.getChildren().addAll(hBox1,hBox2,hBox3,returnButton,mssg);

        Button exit = new Button("Exit");
        exit.setPrefWidth(30);
        exit.setPrefWidth(130);
        exit.setStyle("-fx-background-color: #ee8e19;");
        exit.setFont(new Font("Arial Bold",14));
        exit.setOnAction(e->{
            Home_Member home = new Home_Member();
            home.start(Borrow_bookStage);
        });


        HBox exitHbox = new HBox();
        exitHbox.setAlignment(Pos.BOTTOM_RIGHT);
        exitHbox.setPrefHeight(75);
        exitHbox.setPrefWidth(517);
        exitHbox.setStyle("-fx-background-color: white;");
        exitHbox.getChildren().add(exit);
        HBox.setMargin(exit,new Insets(0,10,10,0));



        VBox vBox = new VBox();
        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.setPrefHeight(484);
        vBox.setPrefWidth(517);
        vBox.setSpacing(10);
        vBox.setStyle("-fx-background-color: white;");
        vBox.getChildren().addAll(title,title2,data,exitHbox);

        Scene returnScene = new Scene(vBox);
        Borrow_bookStage.setScene(returnScene);
        Borrow_bookStage.show();



    }
}


