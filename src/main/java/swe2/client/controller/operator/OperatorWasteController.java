package swe2.client.controller.operator;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import swe2.client.ClientConnection;

public class OperatorWasteController extends OperatorTaskController implements
		Initializable {
	@FXML
	ListView listViewDelivery;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ObservableList list = null;
		try {
			client = new ClientConnection();
			client.connect();
			list = FXCollections.observableArrayList(client.getWasteStorage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		listViewDelivery.setItems(list);
	}
}
