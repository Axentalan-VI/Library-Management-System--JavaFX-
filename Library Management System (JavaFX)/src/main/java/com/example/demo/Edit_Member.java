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

public class Edit_Member extends Application {
    Connection dblink = DBconnect.getConnection();

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage edit_bookStage) {

        Label edit_title = new Label("Edit Member");
        edit_title.setMaxHeight(1.7976931348623157E308);
        edit_title.setMaxWidth(1.7976931348623157E308);
        edit_title.setPrefHeight(140);
        edit_title.setPrefWidth(517);
        edit_title.setStyle("-fx-background-color: #ee8e19;");
        edit_title.setFont(new Font("Times New Roman Bold",18));
        edit_title.setPadding(new Insets(0,0,0,20));

        Label enter_id = new Label("Enter The ID of The Member : ");
        enter_id.setPrefWidth(200);
        enter_id.setPrefHeight(34);


        TextField id = new TextField();
        id.setPrefHeight(35);
        id.setPrefWidth(200);
        id.setPromptText("Member ID");


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
        HBox.setMargin(enter_id,new Insets(0,10,0,10));


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



        Label edit_title2 = new Label("Member Information");
        edit_title2.setAlignment(Pos.CENTER);
        edit_title2.setMaxHeight(1.7976931348623157E308);
        edit_title2.setMaxWidth(1.7976931348623157E308);
        edit_title2.setPrefHeight(39);
        edit_title2.setPrefWidth(200);
        edit_title2.setStyle("-fx-background-color: white;");
        edit_title2.setTextAlignment(TextAlignment.CENTER);
        edit_title2.setTextOverrun(OverrunStyle.CLIP);
        edit_title2.setFont(new Font("Times New Roman Bold",15));



        TextField fname = new TextField();
        fname.setPrefHeight(35);
        fname.setPrefWidth(276);
        fname.setPromptText("First Name");

        TextField lname = new TextField();
        lname.setPrefHeight(35);
        lname.setPrefWidth(276);
        lname.setPromptText("Last Name");

        Label gender = new Label("Gender : ");
        gender.setFont(new Font("Arial",14));

        RadioButton male = new RadioButton("Male");
        RadioButton female = new RadioButton("Female");

        ToggleGroup toggleGroup = new ToggleGroup();

        male.setToggleGroup(toggleGroup);
        female.setToggleGroup(toggleGroup);


        HBox genderbox= new HBox();
        genderbox.setAlignment(Pos.CENTER);
        genderbox.setSpacing(20);
        genderbox.setPrefHeight(35);
        genderbox.setPrefWidth(276);
        genderbox.getChildren().addAll(gender,male,female);
        HBox.setMargin(gender,new Insets(0,100,0,0));


        TextField email = new TextField();
        email.setPrefHeight(35);
        email.setPrefWidth(276);
        email.setPromptText("Email");

        PasswordField password = new PasswordField();
        password.setPrefHeight(35);
        password.setPrefWidth(276);
        password.setPromptText("Password");

        TextField address = new TextField();
        address.setPrefHeight(35);
        address.setPrefWidth(276);
        address.setPromptText("Address");

        DatePicker birth_date = new DatePicker();
        birth_date.setPrefHeight(35);
        birth_date.setPrefWidth(376);
        birth_date.setPromptText("Birth Date");

        DatePicker expired_date = new DatePicker();
        expired_date.setPrefHeight(35);
        expired_date.setPrefWidth(376);
        expired_date.setPromptText("Expiry Date of Membership");

        TextArea fav_books = new TextArea();
        fav_books.setPrefHeight(50);
        fav_books.setPrefWidth(276);
        fav_books.setPromptText("Favorite Books");


        editButton.setOnAction(e->{
            try {


                if (id.getText().isBlank()) {mssg.setText("Please Enter the ID");}
                else {
                    mssg.setText(" ");
                    String query = "Select * from Member_tbl where member_id ='" + Integer.parseInt(id.getText()) + "';";
                    Statement statement = dblink.createStatement();

                    ResultSet resultSet = statement.executeQuery(query);


                    while (resultSet.next()){
                        fname.setText(resultSet.getString(2));
                        lname.setText(resultSet.getString(3));
                        String g =  resultSet.getString(4);
                        RadioButton select_gender = (g.equals("Male")?male:female);
                        toggleGroup.selectToggle(select_gender);

                        email.setText(resultSet.getString(5));
                        password.setText(resultSet.getString(6));
                        address.setText(resultSet.getString(7));
                        Date date = resultSet.getDate(8);


                        birth_date.setValue(Instant.ofEpochMilli(date.getTime())
                                .atZone(ZoneId.systemDefault())
                                .toLocalDate());

                        fav_books.setText(resultSet.getString(9));

                        Date date2 = resultSet.getDate(10);
                        expired_date.setValue(Instant.ofEpochMilli(date2.getTime())
                                .atZone(ZoneId.systemDefault())
                                .toLocalDate());


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

               RadioButton rb = (RadioButton)toggleGroup.getSelectedToggle();
               String g = rb.getText();

                if (fname.getText().isBlank() || lname.getText().isBlank() || email.getText().isBlank() || password.getText().isBlank()
                        || address.getText().isBlank() || birth_date.getValue().toString().isBlank() || expired_date.getValue().toString().isBlank() || toggleGroup.equals(null)) {
                    mssg.setText("Please Enter All the information");
                } else {
                    if (id.getText().chars().allMatch(Character::isDigit)) {

                        String update_data = "update Member_tbl " +
                                "set fname ='"+fname.getText()+"', lname ='"+lname.getText()+"', gender ='"+g.toString()+"' , email ='"+email.getText()+"', password ='"+password.getText()+"', address ='"+address.getText()+"' , Birth_date ='"+birth_date.getValue()+"' , fav_books ='"+fav_books.getText()+"', expiry_date ='"+expired_date.getValue()+"'" +
                                "where member_id ='"+Integer.parseInt(id.getText())+"';";
                        Connection dblink = DBconnect.getConnection();

                        Statement statement = dblink.createStatement();
                        int result = statement.executeUpdate(update_data,Integer.parseInt(id.getText()));
                        if(result > 0){
                            mssg.setTextFill(Color.BLACK);
                            mssg.setText("Member updated Successfully");
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
        textfileds.getChildren().addAll(fname,lname,genderbox,email,password,address,birth_date,expired_date,fav_books,update,mssg);
        VBox.setMargin(fname,new Insets(0,100,0,100));
        VBox.setMargin(lname,new Insets(0,100,0,100));
        VBox.setMargin(email,new Insets(0,100,0,100));
        VBox.setMargin(password,new Insets(0,100,0,100));
        VBox.setMargin(address,new Insets(0,100,0,100));
        VBox.setMargin(birth_date,new Insets(0,100,0,100));
        VBox.setMargin(expired_date,new Insets(0,100,0,100));
        VBox.setMargin(fav_books,new Insets(0,100,10,100));
        VBox.setMargin(gender,new Insets(0,100,0,100));


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
