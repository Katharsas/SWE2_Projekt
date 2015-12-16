/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swe2.client.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import swe2.client.ClientConnection;
import swe2.client.deliverer.Main;
import swe2.shared.model.User;

/**
 * Controller for the menumask of the operator
 * @author pmarek
 */
public class OperatorController {

	private Stage operatorStage;
	private swe2.client.operator.Main main;
	private User loggedInUser;
	private ClientConnection client;
	private ObservableList<String> options = FXCollections.observableArrayList(
				"View Delivery Reports",
				"View Waste Storage",
				"Create Combustion",
				"End Combustion",
				"View Combustion Reports"
			);
			

	@FXML
	TextField idField, amountField;
	@FXML
	ComboBox<String> cBoxTask;
	@FXML
	Button submitButton;
	
	/**
	 * Initializes the comboxbox for further use
	 */
	@FXML
	public void initialize() {
		cBoxTask.setItems(options);
	}

	public void setMain(swe2.client.operator.Main main) {
		this.main = main;
	}

	public void setLoggedInUser(User user) {
		this.loggedInUser = user;
		idField.setText(loggedInUser.getId());
	}

	public void setOperatorStage(Stage operatorStage) {
		this.operatorStage = operatorStage;
	}

}
