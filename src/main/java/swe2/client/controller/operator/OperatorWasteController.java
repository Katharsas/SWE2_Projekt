package swe2.client.controller.operator;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import swe2.client.net.ClientConnection;
import swe2.shared.model.Delivery;
import swe2.shared.model.WasteStorage;

public class OperatorWasteController extends OperatorTaskController implements
		Initializable {
	@FXML
	ListView listViewWasteStorage;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Collection<WasteStorage> wasteList = null;
		ObservableList<String> list = FXCollections.observableArrayList();

		try {
			client = new ClientConnection();
			client.connect();
			wasteList = (Collection<WasteStorage>) client.getWasteStorage();
			client.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (wasteList != null) {
			for (WasteStorage w : wasteList) {
				list.add(parseWaste(w));
			}
		}

		listViewWasteStorage.setItems(list);
	}

	private String parseWaste(WasteStorage w) {
		String result = "";

		result += w.getId();

		return result;
	}
}
