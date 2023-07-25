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
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class Register extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage RegistrationStage) {

        Label title = new Label("Registration");
        title.setAlignment(Pos.CENTER);
        title.setMaxHeight(1.7976931348623157E308);
        title.setMaxWidth(1.7976931348623157E308);
        title.setPrefHeight(45);
        title.setPrefWidth(625);
        title.setStyle("-fx-background-color: #ee8e19;");
        title.setFont(new Font("Times New Roman Bold",18));

        Label title2 = new Label("Member Information");
        title2.setAlignment(Pos.CENTER);
        title2.setMaxHeight(1.7976931348623157E308);
        title2.setMaxWidth(1.7976931348623157E308);
        title2.setPrefHeight(39);
        title2.setPrefWidth(264);
        title2.setStyle("-fx-background-color: white;");
        title2.setTextAlignment(TextAlignment.CENTER);
        title2.setTextOverrun(OverrunStyle.CLIP);
        title2.setFont(new Font("Times New Roman Bold",15));

        TextField id = new TextField();
        id.setPrefHeight(35);
        id.setPrefWidth(276);
        id.setPromptText("Member ID");

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


        TextArea fav_books = new TextArea();
        fav_books.setPrefHeight(50);
        fav_books.setPrefWidth(276);
        fav_books.setPromptText("Favorite Books");




        Label mssg = new Label();
        mssg.setAlignment(Pos.CENTER);
        mssg.setTextFill(Color.RED);
        mssg.setPrefHeight(23);
        mssg.setPrefWidth(391);
        mssg.setTextAlignment(TextAlignment.CENTER);




        Button add = new Button("REGISTER");
        add.setPrefHeight(42);
        add.setPrefWidth(184);
        add.setStyle("-fx-background-color: #ee8e19");
        add.setFont(new Font("Arial Bold",14));


        add.setOnAction(e-> {
            try {

                RadioButton rb = (RadioButton)toggleGroup.getSelectedToggle();
                String g = rb.getText();
                LocalDate expired_date = LocalDate.now().plusMonths(1);

                if (id.getText().isBlank() || fname.getText().isBlank() || lname.getText().isBlank() || email.getText().isBlank() || password.getText().isBlank()
                        || address.getText().isBlank() || birth_date.getValue().toString().isBlank() || g.isBlank()) {
                    mssg.setText("Please Enter All the information");
                }else if(Integer.parseInt(id.getText()) < 0 ){
                    mssg.setText("Please Enter a valid Book ID");

                }
                else {


                    if (id.getText().chars().allMatch(Character::isDigit)){
                        String insertstat =  "INSERT INTO `Library_db`.`Member_tbl` (`member_id`, `fname`, `lname`, `gender` , `email`, `password`, `address`, `Birth_date`, `fav_books`, `expiry_date`) VALUES ('"+Integer.parseInt(id.getText())+"','"+fname.getText()+"','"+lname.getText()+"','"+ g.toString()+"', '"+email.getText()+"', '"+password.getText()+"', '"+address.getText()+"', '"+birth_date.getValue()+"', '"+fav_books.getText()+"', '"+expired_date+"');";
                        Connection dblink = DBconnect.getConnection();
                        Statement statement = dblink.createStatement();
                        boolean inserted = statement.execute(insertstat);
                        if(!inserted){
                        Login login = new Login();
                        login.start(RegistrationStage);

                        }
                        else {
                            mssg.setText("Something went wrong. Please try again");
                        }



                    }

                }
            }catch (SQLException ex){
                mssg.setText(ex.getMessage());
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
        textfileds.getChildren().addAll(id,fname,lname,genderbox,email,password,address,birth_date,fav_books,add,mssg);
        VBox.setMargin(id,new Insets(0,100,0,100));
        VBox.setMargin(fname,new Insets(0,100,0,100));
        VBox.setMargin(lname,new Insets(0,100,0,100));
        VBox.setMargin(email,new Insets(0,100,0,100));
        VBox.setMargin(password,new Insets(0,100,0,100));
        VBox.setMargin(address,new Insets(0,100,0,100));
        VBox.setMargin(birth_date,new Insets(0,100,0,100));

        VBox.setMargin(fav_books,new Insets(0,100,10,100));
        VBox.setMargin(gender,new Insets(0,100,0,100));


        Button exit = new Button("Exit");
        exit.setPrefWidth(30);
        exit.setPrefWidth(130);
        exit.setStyle("-fx-background-color: #ee8e19;");
        exit.setFont(new Font("Arial Bold",14));
        exit.setOnAction(e->{
            Login login = new Login();
            login.start(RegistrationStage);
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
        RegistrationStage.setScene(add_bookScene);
        RegistrationStage.show();



    }
}

