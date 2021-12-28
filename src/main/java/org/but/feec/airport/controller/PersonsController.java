package org.but.feec.airport.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.but.feec.airport.App;
import org.but.feec.airport.api.PersonBasicView;
import org.but.feec.airport.api.PersonDetailView;
import org.but.feec.airport.config.DataSourceConfig;
import org.but.feec.airport.data.PersonRepository;
import org.but.feec.airport.exceptions.ExceptionHandler;
import org.but.feec.airport.service.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class PersonsController{


    @FXML
    public Button addPassengerButton;
    @FXML
    public Button refreshButton;
    @FXML
    private TableColumn<PersonBasicView, Long> passenger_id;
    @FXML
    private TableColumn<PersonBasicView, String> passengerName;
    @FXML
    private TableColumn<PersonBasicView, String> passengerSurname;
    @FXML
    private TableColumn<PersonBasicView, String> passport_number;
    @FXML
    private TableColumn<PersonBasicView, String> residence;
    @FXML
    private TableView<PersonBasicView> systemPassengerTableView;

    private PersonService personService;
    private PersonRepository personRepository;

    private static final Logger logger = LoggerFactory.getLogger(PersonsController.class);

    @FXML
    private void initialize() {
        personRepository = new PersonRepository();
        personService = new PersonService(personRepository);

        passenger_id.setCellValueFactory(new PropertyValueFactory<PersonBasicView, Long>("id"));
        passengerName.setCellValueFactory(new PropertyValueFactory<PersonBasicView, String>("first_name"));
        passengerSurname.setCellValueFactory(new PropertyValueFactory<PersonBasicView, String>("last_name"));
        passport_number.setCellValueFactory(new PropertyValueFactory<PersonBasicView, String>("passport_number"));
        residence.setCellValueFactory(new PropertyValueFactory<PersonBasicView, String>("country_of_residence"));


        ObservableList<PersonBasicView> observablePersonsList = initializePersonsData();

        systemPassengerTableView.setItems(observablePersonsList);

        systemPassengerTableView.getSortOrder().add(passenger_id);

        initializeTableViewSelection();

        logger.info("PersonsController initialized");
    }

    private void initializeTableViewSelection() {
        MenuItem edit = new MenuItem("Edit passenger");
        MenuItem detailedView = new MenuItem("Detailed passenger view");
        edit.setOnAction((ActionEvent event) -> {
            PersonBasicView personView = systemPassengerTableView.getSelectionModel().getSelectedItem();
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(App.class.getResource("fxml/PersonEdit.fxml"));
                Stage stage = new Stage();
                stage.setUserData(personView);
                stage.setTitle("BDS Edit Passenger");

                PersonEditController controller = new PersonEditController();
                controller.setStage(stage);
                fxmlLoader.setController(controller);

                Scene scene = new Scene(fxmlLoader.load(), 600, 500);

                stage.setScene(scene);

                stage.show();
            } catch (IOException ex) {
                ExceptionHandler.handleException(ex);
            }
        });

        detailedView.setOnAction((ActionEvent event) -> {
            PersonBasicView personView = systemPassengerTableView.getSelectionModel().getSelectedItem();
            try {
                FXMLLoader fxmlLoader = new FXMLLoader();
                fxmlLoader.setLocation(App.class.getResource("fxml/PersonDetail.fxml"));
                Stage stage = new Stage();

                Long personId = personView.getId();
                PersonDetailView personDetailView = personService.getPersonDetailView(personId);

                stage.setUserData(personDetailView);
                stage.setTitle("BDS Passenger Detailed View");

                PersonDetailController controller = new PersonDetailController();
                controller.setStage(stage);
                fxmlLoader.setController(controller);

                Scene scene = new Scene(fxmlLoader.load(), 600, 500);

                stage.setScene(scene);

                stage.show();
            } catch (IOException ex) {
                ExceptionHandler.handleException(ex);
            }
        });


        ContextMenu menu = new ContextMenu();
        menu.getItems().add(edit);
        menu.getItems().addAll(detailedView);
        systemPassengerTableView.setContextMenu(menu);
    }

    private ObservableList<PersonBasicView> initializePersonsData() {
        List<PersonBasicView> persons = personService.getPersonBasicView();
        return FXCollections.observableArrayList(persons);
    }



    public void handleExitMenuItem(ActionEvent event) {
        System.exit(0);
    }

    public void handleAddPassengerButton(ActionEvent actionEvent) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(App.class.getResource("fxml/PersonCreate.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 500);
            Stage stage = new Stage();
            stage.setTitle("BDS Create Passenger");
            stage.setScene(scene);



            stage.show();
        } catch (IOException ex) {
            ExceptionHandler.handleException(ex);
        }
    }

    public void handleRefreshButton(ActionEvent actionEvent) throws SQLException {
        logger.info("Database refreshed!");
        DataSourceConfig.getConnection();
        ObservableList<PersonBasicView> observablePersonsList = initializePersonsData();
        systemPassengerTableView.setItems(observablePersonsList);
        systemPassengerTableView.refresh();
        systemPassengerTableView.sort();
    }
}
