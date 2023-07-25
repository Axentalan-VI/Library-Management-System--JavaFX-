package com.example.demo;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.InputMismatchException;
import java.util.concurrent.TimeUnit;

public class Login extends Application{

    public Connection dblink= DBconnect.getConnection();
    public static int key ;



    @Override
    public void start(Stage loginStage) {

        Label login = new Label("LOGIN");
        login.setAlignment(Pos.TOP_CENTER);
        login.setFont(new Font("Arial Black", 18));
        login.setLayoutX(181.0);
        login.setLayoutY(76.0);
        login.setTextFill(Color.BLACK);
        login.setContentDisplay(ContentDisplay.CENTER);
        login.setTextOverrun(OverrunStyle.CLIP);

        TextField username = new TextField();
        username.setPromptText("Email");
        username.setLayoutX(117.0);
        username.setLayoutY(134.0);
        username.setPrefHeight(37.0);
        username.setPrefWidth(185);
        username.setFont(new Font("Apple Braille", 13));

        PasswordField password = new PasswordField();
        password.setPromptText("Password");
        password.setLayoutX(117);
        password.setLayoutY(191);
        password.setPrefHeight(37);
        password.setPrefWidth(185);

        Label errormssg = new Label();
        errormssg.setFont(new Font("Arial Red",13));
        errormssg.setAlignment(Pos.CENTER);
        errormssg.setTextFill(Color.RED);
        errormssg.setLayoutX(90);
        errormssg.setLayoutY(342);
        errormssg.setPrefWidth(266);
        errormssg.setPrefHeight(37);

        Button loginButton = new Button("LOG IN");
        loginButton.setLayoutX(116);
        loginButton.setLayoutY(264);
        loginButton.setMnemonicParsing(false);
        loginButton.setPrefHeight(26);
        loginButton.setPrefWidth(185);
        loginButton.setStyle("-fx-background-color: #ee8e19");
        loginButton.setTextFill(Color.BLACK);
        loginButton.setOnAction(e -> {
            Home homeStage = new Home();
            try {
                if(username.getText().isBlank() || password.getText().isBlank() ){
                    errormssg.setText("Please Enter the username and password");
                }
                else {
                    if (username.getText().equals("admin")) {
                        String valid = "Select * from Admin_tbl where admin_username ='" + username.getText() + "'AND  admin_password = '" + password.getText() + "'";
                        Statement statement = dblink.createStatement();
                        ResultSet resultSet = statement.executeQuery(valid);

                        while (resultSet.next()) {
                            if (resultSet.getString(2).equals(username.getText()) && resultSet.getString(3).equals(password.getText())) {
                                System.out.println("Login Successfully");
                                homeStage.start(loginStage);
                            } else {
                                errormssg.setText("invalid login. Please try again.");
                            }
                        }
                    }
                    else{
                        String valid_member= "Select * from Member_tbl where email = '"+username.getText()+"' and password = '"+password.getText()+"';";
                        Statement statement = dblink.createStatement();
                        ResultSet resultSet = statement.executeQuery(valid_member);
                        while(resultSet.next()){
                            if (resultSet.getString(5).equals(username.getText()) && resultSet.getString(6).equals(password.getText())) {
                                key = resultSet.getInt(1);
                                System.out.println("Login Successfully");
                                Home_Member home_member = new Home_Member();
                                home_member.start(loginStage);
                            } else {
                                errormssg.setText("invalid login. Please try again.");
                            }


                        }

                    }
                }


            } catch (InputMismatchException e1) {
                errormssg.setText("Invalid Input !!");

            } catch (SQLException ex) {
                errormssg.setText(ex.getMessage());
            }

        });

        Button register = new Button("REGISTER");
        register.setLayoutX(154);
        register.setLayoutY(300);
        register.setMaxHeight(1.7976931348623157E308);
        register.setMaxWidth(1.7976931348623157E308);
        register.setMnemonicParsing(false);
        register.setPrefHeight(27);
        register.setPrefWidth(111);
        register.setStyle("-fx-background-color: #ee8e19;");
        register.setOnAction(e->{
            Register register1 = new Register();
            register1.start(loginStage);
        });




        Pane loginpPane = new Pane();
        loginpPane.setMaxHeight(1.7976931348623157E308);
        loginpPane.setMaxWidth(1.7976931348623157E308);
        loginpPane.setPrefHeight(402);
        loginpPane.setPrefWidth(408.0);
        loginpPane.setStyle("-fx-background-color: White");
        loginpPane.getChildren().addAll(login,username,password,loginButton,register,errormssg);


        Scene loginScene = new Scene(loginpPane);
        loginStage.setScene(loginScene);
        loginStage.setResizable(false);
        loginStage.show();

    }


    public static void main() {
        launch();
    }

}
