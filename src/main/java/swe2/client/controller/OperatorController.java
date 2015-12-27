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
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import swe2.client.ClientConnection;
import swe2.client.controller.operator.OperatorDeliveryController;
import swe2.client.controller.operator.OperatorTaskController;
import swe2.client.deliverer.Main;
import swe2.shared.model.Operator;
import swe2.shared.model.User;

/**
 * Controller for the menumask of the operator
 * 
 * @author pmarek
 */
public class OperatorController {

	private Stage operatorStage;
	private swe2.client.operator.Main main;
	private User loggedInUser;
	private ClientConnection client;
	private ObservableList<String> options = FXCollections.observableArrayList(
			"View Delivery Reports", "View Waste Storage", "Create Combustion",
			"End Combustion", "View Combustion Reports");

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

	@FXML
	public void submit() {
		Stage operatorDeliveryStage;
		AnchorPane operatorDelivery;
		OperatorTaskController ctrl;
		FXMLLoader loader;
		try {
			loader = getNextStage(cBoxTask.getSelectionModel()
					.getSelectedItem());
			operatorDelivery = loader.load();
			operatorDeliveryStage = new Stage(); // Muss initialisiert werden

			operatorDeliveryStage.initModality(Modality.WINDOW_MODAL);
			operatorDeliveryStage.initOwner(operatorStage);

			operatorDeliveryStage.setScene(new Scene(operatorDelivery));

			operatorDeliveryStage.getScene().getWindow()
					.setOnCloseRequest(ev -> {
						operatorStage.close();
						ev.consume();
						System.out.println("Exit");

					});

			ctrl = loader.getController();
			ctrl.setOperatorTaskStage(operatorDeliveryStage);
			operatorDeliveryStage.setTitle(cBoxTask.getSelectionModel().getSelectedItem());

			operatorDeliveryStage.showAndWait();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private FXMLLoader getNextStage(String selectedItem) {
		FXMLLoader loader = new FXMLLoader(
				Main.class
						.getResource("/view/operator_view_delivery_reports.fxml"));
		switch (selectedItem) {
		case "View Delivery Reports":
			loader = new FXMLLoader(
					Main.class
							.getResource("/view/operator_view_delivery_reports.fxml"));
			break;
		case "View Waste Storage":
			loader = new FXMLLoader(
					Main.class.getResource("/view/operator_view_waste_storage.fxml"));
			break;
		case "Create Combustion":
			loader = new FXMLLoader(
					Main.class.getResource("/view/mssg_win.fxml"));
			break;
		case "End Combustion":
			loader = new FXMLLoader(
					Main.class.getResource("/view/mssg_win.fxml"));
			break;
		case "View Combustion Reports":
			loader = new FXMLLoader(
					Main.class.getResource("/view/operator_view_combustion_reports.fxml"));
			break;
		default:
			new FXMLLoader(
					Main.class
							.getResource("/view/operator_view_delivery_reports.fxml"));
		}
		return loader;
	}

}
