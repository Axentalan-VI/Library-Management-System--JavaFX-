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
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.concurrent.atomic.AtomicBoolean;

public class Search_Member extends Application {

    Connection dblink = DBconnect.getConnection();



    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage view_bookStage) {




        Label title = new Label("Search Member");
        title.setMaxHeight(1.7976931348623157E308);
        title.setMaxWidth(1.7976931348623157E308);
        title.setPrefHeight(66);
        title.setPrefWidth(1180);
        title.setStyle("-fx-background-color: #ee8e19;");
        title.setTextFill(Color.BLACK);
        title.setFont(new Font("Times New Roman Bold",18));
        title.setPadding(new Insets(0,0,0,20));

        ObservableList<Member> members = FXCollections.observableArrayList();



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
        Table.setLayoutX(20);
        Table.setLayoutY(258);
        Table.setPrefHeight(369);
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
            home.start(view_bookStage);
        });
        HBox hBox2 = new HBox();
        hBox2.setAlignment(Pos.BOTTOM_RIGHT);
        hBox2.setMaxHeight(1.7976931348623157E308);
        hBox2.setMaxWidth(1.7976931348623157E308);
        hBox2.setPrefHeight(48);
        hBox2.setPrefWidth(148);
        hBox2.setLayoutX(1027);
        hBox2.setLayoutY(654);
        hBox2.setStyle("-fx-background-color: white;");
        hBox2.getChildren().add(exit);
        HBox.setMargin(exit,new Insets(0,10,10,0));

        Label mssg = new Label();
        mssg.setAlignment(Pos.CENTER);
        mssg.setTextFill(Color.RED);
        mssg.setPrefHeight(17);
        mssg.setPrefWidth(666);
        mssg.setLayoutX(234);
        mssg.setLayoutY(231);
        mssg.setStyle("-fx-background-color: white;");
        mssg.setTextAlignment(TextAlignment.CENTER);


        Label label = new Label("Choose the Element you want to search by");
        label.setLayoutX(329);
        label.setLayoutY(113);
        label.setPrefHeight(37);
        label.setPrefWidth(238);

        TextField search = new TextField();
        search.setLayoutX(329);
        search.setLayoutY(170);
        search.setPrefHeight(37);
        search.setPrefWidth(238);
        search.setPromptText("Enter Data");

        AtomicBoolean IDclicked = new AtomicBoolean(false);
        AtomicBoolean fnameclicked= new AtomicBoolean(false);
        AtomicBoolean lnameclicked= new AtomicBoolean(false);
        AtomicBoolean genderclicked= new AtomicBoolean(false);
        AtomicBoolean emailclicked= new AtomicBoolean(false);

        AtomicBoolean addressclicked= new AtomicBoolean(false);
        AtomicBoolean birth_dateclicked= new AtomicBoolean(false);
        AtomicBoolean fav_booksclicked= new AtomicBoolean(false);
        AtomicBoolean expiry_dateclicked= new AtomicBoolean(false);

        MenuButton menuButton = new MenuButton();
        menuButton.setLayoutX(590);
        menuButton.setLayoutY(113);
        menuButton.setPrefHeight(37);
        menuButton.setPrefWidth(183);
        menuButton.setText("Search By");

        MenuItem ID = new MenuItem("ID");
        ID.setOnAction(e->{
            IDclicked.set(true);
            menuButton.setText("ID");


        });
        MenuItem Fname = new MenuItem("First Name");
        Fname.setOnAction(e->{
            fnameclicked.set(true);
            menuButton.setText("First Name");


        });

        MenuItem Lname = new MenuItem("Last Name");
        Lname.setOnAction(e->{
            lnameclicked.set(true);
            menuButton.setText("Last Name");


        });
        MenuItem Gender = new MenuItem("Gender");
        Gender.setOnAction(e->{
            genderclicked.set(true);
            mssg.setText("Please Enter Male or Female !!!");
            menuButton.setText("Gender");


        });
        MenuItem Email = new MenuItem("Email");
        Email.setOnAction(e->{
            emailclicked.set(true);
            menuButton.setText("Email");


        });
        MenuItem Address = new MenuItem("Address");
        Address.setOnAction(e->{
            addressclicked.set(true);
            menuButton.setText("Address");


        });
        MenuItem Birth_Date = new MenuItem("Birth Date");
        Birth_Date.setOnAction(e->{
            birth_dateclicked.set(true);
            menuButton.setText("Birth Date");
            mssg.setText("YYYY/MM/DD");



        });
        MenuItem Expiry_date = new MenuItem("Expiry Date");
        Expiry_date.setOnAction(e->{
            expiry_dateclicked.set(true);
            menuButton.setText("Expiry Date");
            mssg.setText("YYYY/MM/DD");


        });
        MenuItem Fav_books = new MenuItem("Favorite Books");
        Fav_books.setOnAction(e->{
            fav_booksclicked.set(true);
            menuButton.setText("Favorite Books");



        });


        menuButton.getItems().addAll(ID,Fname,Lname,Gender,Email,Address,Birth_Date,Fav_books,Expiry_date);



        Button searchButton = new Button("Search");
        searchButton.setLayoutX(590);
        searchButton.setLayoutY(170);
        searchButton.setPrefHeight(37);
        searchButton.setPrefWidth(183);
        searchButton.setStyle("-fx-background-color: #ee8e19;");
        searchButton.setFont(new Font("Arial Bold",14));
        searchButton.setOnAction(e-> {
            try {
                Statement statement = dblink.createStatement();
                if (search.getText().isBlank()) {
                    mssg.setText(("Please Enter the Data"));}
                else {

                    if (IDclicked.get()) {


                        if (Integer.parseInt(search.getText())<0){
                            mssg.setText("Please Enter a valid ID ");

                        }
                        else {
                            String Query = "Select * from Member_tbl WHERE member_id = '" + Integer.parseInt(search.getText()) + "';";
                            ResultSet resultSet = statement.executeQuery(Query);
                            members.clear();
                            while (resultSet.next()) {
                                members.add(new Member(resultSet.getInt(1), resultSet.getString(2),resultSet.getString(3), resultSet.getString(4)
                                        ,resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getDate(8),resultSet.getString(9)
                                        ,resultSet.getDate(10)));

                            }
                            IDclicked.set(false);
                            menuButton.setText("Search By");
                            mssg.setText(" ");

                        }


                    }
                    else if (fnameclicked.get()) {


                        String Query = "Select * from Member_tbl WHERE fname = '" + search.getText() + "';";
                        ResultSet resultSet = statement.executeQuery(Query);
                        members.clear();
                        while (resultSet.next()) {

                            members.add(new Member(resultSet.getInt(1), resultSet.getString(2),resultSet.getString(3), resultSet.getString(4)
                                    ,resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getDate(8),resultSet.getString(9)
                                    ,resultSet.getDate(10)));


                        }
                        fnameclicked.set(false);
                        menuButton.setText("Search By");
                        mssg.setText(" ");



                    }
                    else if (lnameclicked.get()) {


                        String Query = "Select * from Member_tbl WHERE lname = '" + search.getText() + "';";
                        ResultSet resultSet = statement.executeQuery(Query);
                        members.clear();
                        while (resultSet.next()) {

                            members.add(new Member(resultSet.getInt(1), resultSet.getString(2),resultSet.getString(3), resultSet.getString(4)
                                    ,resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getDate(8),resultSet.getString(9)
                                    ,resultSet.getDate(10)));


                        }
                        lnameclicked.set(false);
                        menuButton.setText("Search By");
                        mssg.setText(" ");



                    }
                    else if (genderclicked.get()) {

                        String g = "";
                            if(search.getText().equals("Male")||search.getText().equals("male")||search.getText().equals("m")||search.getText().equals("M")){
                                g="Male";
                                String Query = "Select * from Member_tbl WHERE gender = '" + g + "';";
                                ResultSet resultSet = statement.executeQuery(Query);
                                members.clear();
                                while (resultSet.next()) {

                                    members.add(new Member(resultSet.getInt(1), resultSet.getString(2),resultSet.getString(3), resultSet.getString(4)
                                            ,resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getDate(8),resultSet.getString(9)
                                            ,resultSet.getDate(10)));

                                }
                                genderclicked.set(false);
                                menuButton.setText("Search By");
                                mssg.setText(" ");


                        }
                        else if(search.getText().equals("Female")||search.getText().equals("female")||search.getText().equals("f")||search.getText().equals("F")){
                            g="Female";
                                menuButton.setText("Gender");
                                String Query = "Select * from Member_tbl WHERE gender = '" + g + "';";
                                ResultSet resultSet = statement.executeQuery(Query);
                                members.clear();
                                while (resultSet.next()) {

                                    members.add(new Member(resultSet.getInt(1), resultSet.getString(2),resultSet.getString(3), resultSet.getString(4)
                                            ,resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getDate(8),resultSet.getString(9)
                                            ,resultSet.getDate(10)));

                                }
                                genderclicked.set(false);
                                menuButton.setText("Search By");
                                mssg.setText(" ");

                        }
                        else {
                                mssg.setText("Please Enter Male or Female !!!");
                                genderclicked.set(false);
                                menuButton.setText("Search By");

                            }


                    }
                    else if (emailclicked.get()) {


                        String Query = "Select * from Member_tbl WHERE email = '" + search.getText() + "';";
                        ResultSet resultSet = statement.executeQuery(Query);
                        members.clear();
                        while (resultSet.next()) {
                            members.add(new Member(resultSet.getInt(1), resultSet.getString(2),resultSet.getString(3), resultSet.getString(4)
                                    ,resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getDate(8),resultSet.getString(9)
                                    ,resultSet.getDate(10)));


                        }
                        emailclicked.set(false);
                        menuButton.setText("Search By");
                        mssg.setText(" ");



                    }

                    else if (addressclicked.get()) {

                        String Query = "Select * from Member_tbl WHERE address = '" + search.getText() + "';";
                        ResultSet resultSet = statement.executeQuery(Query);
                        members.clear();
                        while (resultSet.next()) {
                            members.add(new Member(resultSet.getInt(1), resultSet.getString(2),resultSet.getString(3), resultSet.getString(4)
                                    ,resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getDate(8),resultSet.getString(9)
                                    ,resultSet.getDate(10)));


                        }
                        emailclicked.set(false);
                        menuButton.setText("Search By");
                        mssg.setText(" ");



                    }
                    else if (birth_dateclicked.get()) {

                        String Query = "Select * from Member_tbl WHERE Birth_date = '" + search.getText() + "';";
                        ResultSet resultSet = statement.executeQuery(Query);
                        members.clear();
                        while (resultSet.next()) {
                            members.add(new Member(resultSet.getInt(1), resultSet.getString(2),resultSet.getString(3), resultSet.getString(4)
                                    ,resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getDate(8),resultSet.getString(9)
                                    ,resultSet.getDate(10)));


                        }
                        birth_dateclicked.set(false);
                        menuButton.setText("Search By");
                        mssg.setText(" ");

                    }
                    else if (fav_booksclicked.get()) {


                            String Query = "Select * from Member_tbl WHERE fav_books = '" +search.getText() + "';";
                            ResultSet resultSet = statement.executeQuery(Query);
                            members.clear();
                            while (resultSet.next()) {

                                members.add(new Member(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4)
                                        , resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getDate(8), resultSet.getString(9)
                                        , resultSet.getDate(10)));
                            }



                            fav_booksclicked.set(false);
                        menuButton.setText("Search By");





                        }

                    else if (expiry_dateclicked.get()) {


                            String Query = "Select * from Member_tbl WHERE expiry_date = '" +search.getText() + "';";
                            ResultSet resultSet = statement.executeQuery(Query);
                            members.clear();
                            while (resultSet.next()) {

                                members.add(new Member(resultSet.getInt(1), resultSet.getString(2),resultSet.getString(3), resultSet.getString(4)
                                        ,resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getDate(8),resultSet.getString(9)
                                        ,resultSet.getDate(10)));



                            }
                            expiry_dateclicked.set(false);
                        menuButton.setText("Search By");
                        mssg.setText(" ");





                    }
                    else {
                        mssg.setText("Please choose the type of data ");
                        String Query = "Select * from  Member_tbl;";
                        ResultSet resultSet = statement.executeQuery(Query);
                        members.clear();
                        while(resultSet.next()){
                            members.add(new Member(resultSet.getInt(1), resultSet.getString(2),resultSet.getString(3), resultSet.getString(4)
                                    ,resultSet.getString(5),resultSet.getString(6),resultSet.getString(7),resultSet.getDate(8),resultSet.getString(9)
                                    ,resultSet.getDate(10)));

                        }
                        menuButton.setText("Search By");
                        mssg.setText(" ");
                    }
                }
            }
            catch(Exception ex){
                mssg.setText(ex.getMessage());
            }


        });











        Pane pane = new Pane();
        pane.setPrefHeight(698);
        pane.setPrefWidth(1180);
        pane.setStyle("-fx-background-color: white;");
        pane.getChildren().addAll(Table,title,hBox2,menuButton,label,search,searchButton,mssg);

        Scene view_bookScene = new Scene(pane);
        view_bookStage.setScene(view_bookScene);
        view_bookStage.show();






    }






}
