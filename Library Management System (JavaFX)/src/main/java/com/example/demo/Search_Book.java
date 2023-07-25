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

public class Search_Book extends Application {

    Connection dblink = DBconnect.getConnection();



    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage view_bookStage) {




        Label title = new Label("Search Book");
        title.setMaxHeight(1.7976931348623157E308);
        title.setMaxWidth(1.7976931348623157E308);
        title.setPrefHeight(66);
        title.setPrefWidth(1180);
        title.setStyle("-fx-background-color: #ee8e19;");
        title.setTextFill(Color.BLACK);
        title.setFont(new Font("Times New Roman Bold",18));
        title.setPadding(new Insets(0,0,0,20));

        ObservableList<Book> books = FXCollections.observableArrayList();



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
        Table.setLayoutX(20);
        Table.setLayoutY(258);
        Table.setPrefHeight(369);
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
        AtomicBoolean titleclicked= new AtomicBoolean(false);
        AtomicBoolean Languageclicked= new AtomicBoolean(false);
        AtomicBoolean Categoryclicked= new AtomicBoolean(false);
        AtomicBoolean Authorclicked= new AtomicBoolean(false);
        AtomicBoolean Publisherclicked= new AtomicBoolean(false);
        AtomicBoolean Publisher_Dateclicked= new AtomicBoolean(false);
        AtomicBoolean Priceclicked= new AtomicBoolean(false);
        AtomicBoolean Number_of_Pagesclicked= new AtomicBoolean(false);
        AtomicBoolean Availableclicked= new AtomicBoolean(false);

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
        MenuItem Title = new MenuItem("Title");
        Title.setOnAction(e->{
            titleclicked.set(true);
            menuButton.setText("Title");


        });

        MenuItem Language = new MenuItem("Language");
        Language.setOnAction(e->{
            Languageclicked.set(true);
            menuButton.setText("Language");


        });
        MenuItem Category = new MenuItem("Category");
        Category.setOnAction(e->{
            Categoryclicked.set(true);
            menuButton.setText("Category");


        });
        MenuItem Author = new MenuItem("Author");
        Author.setOnAction(e->{
            Authorclicked.set(true);
            menuButton.setText("Author");


        });
        MenuItem Publisher = new MenuItem("Publisher");
        Publisher.setOnAction(e->{
            Publisherclicked.set(true);
            menuButton.setText("Publisher");


        });
        MenuItem Publisher_Date = new MenuItem("Publisher Date");
        Publisher_Date.setOnAction(e->{
            Publisher_Dateclicked.set(true);
            menuButton.setText("Publisher Date");
            mssg.setText("YYYY/MM/DD");


        });
        MenuItem Price = new MenuItem("Price");
        Price.setOnAction(e->{
            Priceclicked.set(true);
            menuButton.setText("Price");


        });
        MenuItem Number_of_Pages = new MenuItem("Number of Pages");
        Number_of_Pages.setOnAction(e->{
            Number_of_Pagesclicked.set(true);
            menuButton.setText("Number of Pages");


        });
        MenuItem Available = new MenuItem("Available");
        Available.setOnAction(e->{
            Availableclicked.set(true);
            mssg.setText("Please Enter 1 for Available and 0 for not Available !!!");
            menuButton.setText("Available");


        });


        menuButton.getItems().addAll(ID,Title,Language,Category,Author,Publisher,Publisher_Date,Price,Number_of_Pages,Available);






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
                            String Query = "Select * from Book_tbl WHERE book_id = '" + Integer.parseInt(search.getText()) + "';";
                            ResultSet resultSet = statement.executeQuery(Query);
                            books.clear();
                            while (resultSet.next()) {

                                books.add(new Book(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4)
                                        , resultSet.getString(5), resultSet.getString(6), resultSet.getDate(7), resultSet.getDouble(8), resultSet.getInt(9)
                                        , resultSet.getBoolean(10)));


                            }
                            IDclicked.set(false);
                            menuButton.setText("Search By");
                            mssg.setText(" ");

                        }


                    }
                    else if (titleclicked.get()) {


                            String Query = "Select * from Book_tbl WHERE book_title = '" + search.getText() + "';";
                            ResultSet resultSet = statement.executeQuery(Query);
                            books.clear();
                            while (resultSet.next()) {

                                books.add(new Book(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4)
                                        , resultSet.getString(5), resultSet.getString(6), resultSet.getDate(7), resultSet.getDouble(8), resultSet.getInt(9)
                                        , resultSet.getBoolean(10)));


                            }
                        titleclicked.set(false);
                        menuButton.setText("Search By");
                        mssg.setText(" ");



                    }
                    else if (Languageclicked.get()) {


                        String Query = "Select * from Book_tbl WHERE book_language = '" + search.getText() + "';";
                        ResultSet resultSet = statement.executeQuery(Query);
                        books.clear();
                        while (resultSet.next()) {

                            books.add(new Book(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4)
                                    , resultSet.getString(5), resultSet.getString(6), resultSet.getDate(7), resultSet.getDouble(8), resultSet.getInt(9)
                                    , resultSet.getBoolean(10)));


                        }
                        Languageclicked.set(false);
                        menuButton.setText("Search By");
                        mssg.setText(" ");



                    }
                    else if (Categoryclicked.get()) {


                        String Query = "Select * from Book_tbl WHERE book_category = '" + search.getText() + "';";
                        ResultSet resultSet = statement.executeQuery(Query);
                        books.clear();
                        while (resultSet.next()) {

                            books.add(new Book(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4)
                                    , resultSet.getString(5), resultSet.getString(6), resultSet.getDate(7), resultSet.getDouble(8), resultSet.getInt(9)
                                    , resultSet.getBoolean(10)));


                        }
                        Categoryclicked.set(false);
                        menuButton.setText("Search By");
                        mssg.setText(" ");




                    }
                    else if (Authorclicked.get()) {


                        String Query = "Select * from Book_tbl WHERE book_author = '" + search.getText() + "';";
                        ResultSet resultSet = statement.executeQuery(Query);
                        books.clear();
                        while (resultSet.next()) {
                            books.add(new Book(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4)
                                    , resultSet.getString(5), resultSet.getString(6), resultSet.getDate(7), resultSet.getDouble(8), resultSet.getInt(9)
                                    , resultSet.getBoolean(10)));


                        }
                        Authorclicked.set(false);
                        menuButton.setText("Search By");
                        mssg.setText(" ");



                    }
                    else if (Publisherclicked.get()) {

                        String Query = "Select * from Book_tbl WHERE book_publisher = '" + search.getText() + "';";
                        ResultSet resultSet = statement.executeQuery(Query);
                        books.clear();
                        while (resultSet.next()) {
                            books.add(new Book(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4)
                                    , resultSet.getString(5), resultSet.getString(6), resultSet.getDate(7), resultSet.getDouble(8), resultSet.getInt(9)
                                    , resultSet.getBoolean(10)));


                        }
                        Publisherclicked.set(false);
                        menuButton.setText("Search By");
                        mssg.setText(" ");



                    }
                    else if (Publisher_Dateclicked.get()) {

                        String Query = "Select * from Book_tbl WHERE book_publish_date = '" + search.getText() + "';";
                        ResultSet resultSet = statement.executeQuery(Query);
                        books.clear();
                        while (resultSet.next()) {
                            books.add(new Book(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4)
                                    , resultSet.getString(5), resultSet.getString(6), resultSet.getDate(7), resultSet.getDouble(8), resultSet.getInt(9)
                                    , resultSet.getBoolean(10)));


                        }
                        Publisher_Dateclicked.set(false);
                        menuButton.setText("Search By");
                        mssg.setText(" ");

                    }
                    else if (Priceclicked.get()) {

                        String Query = "Select * from Book_tbl WHERE book_publish_date = '" + search.getText() + "';";
                        ResultSet resultSet = statement.executeQuery(Query);
                        books.clear();
                        while (resultSet.next()) {
                            books.add(new Book(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4)
                                    , resultSet.getString(5), resultSet.getString(6), resultSet.getDate(7), resultSet.getDouble(8), resultSet.getInt(9)
                                    , resultSet.getBoolean(10)));


                        }
                        Priceclicked.set(false);
                        menuButton.setText("Search By");
                        mssg.setText(" ");

                    }
                    else if (Priceclicked.get()) {
                        if (Double.parseDouble(search.getText())<0){
                            mssg.setText("Please Enter a valid Price ");

                        }
                        else {
                            String Query = "Select * from Book_tbl WHERE book_price = '" +Double.parseDouble(search.getText()) + "';";
                            ResultSet resultSet = statement.executeQuery(Query);
                            books.clear();
                            while (resultSet.next()) {

                                books.add(new Book(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4)
                                        , resultSet.getString(5), resultSet.getString(6), resultSet.getDate(7), resultSet.getDouble(8), resultSet.getInt(9)
                                        , resultSet.getBoolean(10)));


                            }
                            Priceclicked.set(false);
                            menuButton.setText("Search By");
                            mssg.setText(" ");




                        }


                    }
                    else if (Number_of_Pagesclicked.get()) {
                        if (Integer.parseInt(search.getText())<0){
                            mssg.setText("Please Enter a valid Price ");

                        }
                        else {
                            String Query = "Select * from Book_tbl WHERE num_of_pages = '" +Integer.parseInt(search.getText()) + "';";
                            ResultSet resultSet = statement.executeQuery(Query);
                            books.clear();
                            while (resultSet.next()) {

                                books.add(new Book(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4)
                                        , resultSet.getString(5), resultSet.getString(6), resultSet.getDate(7), resultSet.getDouble(8), resultSet.getInt(9)
                                        , resultSet.getBoolean(10)));


                            }
                            Number_of_Pagesclicked.set(false);
                            menuButton.setText("Search By");
                            mssg.setText(" ");


                        }

                    }
                    else if (Availableclicked.get()) {

                        if (Integer.parseInt(search.getText()) == 1){
                            String Query = "Select * from Book_tbl WHERE avalible = 1;";
                            ResultSet resultSet = statement.executeQuery(Query);
                            books.clear();
                            while (resultSet.next()) {
                                books.add(new Book(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4)
                                        , resultSet.getString(5), resultSet.getString(6), resultSet.getDate(7), resultSet.getDouble(8), resultSet.getInt(9)
                                        , resultSet.getBoolean(10)));


                            }
                            Availableclicked.set(false);
                            menuButton.setText("Search By");

                        }
                        else  {
                            String Query = "Select * from Book_tbl WHERE avalible = 0;";
                            ResultSet resultSet = statement.executeQuery(Query);
                            books.clear();
                            while (resultSet.next()) {
                                books.add(new Book(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4)
                                        , resultSet.getString(5), resultSet.getString(6), resultSet.getDate(7), resultSet.getDouble(8), resultSet.getInt(9)
                                        , resultSet.getBoolean(10)));


                            }
                            Availableclicked.set(false);
                            menuButton.setText("Search By");
                            mssg.setText(" ");

                        }

                    }
                    else {
                        mssg.setText("Please choose the type of data ");
                        String Query = "Select * from Book_tbl ;";
                        ResultSet resultSet = statement.executeQuery(Query);
                        books.clear();
                        while(resultSet.next()){
                            books.add(new Book(resultSet.getInt(1), resultSet.getString(2),resultSet.getString(3), resultSet.getString(4)
                                    ,resultSet.getString(5),resultSet.getString(6),resultSet.getDate(7),resultSet.getDouble(8),resultSet.getInt(9)
                                    ,resultSet.getBoolean(10)));

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
