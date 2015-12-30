package swe2.client.controller.operator;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import swe2.client.net.ClientConnection;
import swe2.shared.model.Deliverer;
import swe2.shared.model.Delivery;
import swe2.shared.model.Money;
import swe2.shared.model.UniformWaste;

public class ViewDeliveryController extends TaskController
		implements Initializable {
	@FXML
	ListView listViewDelivery;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Collection<Delivery> deliveryList = null;
		ObservableList<String> list = FXCollections.observableArrayList();

		try {
			client = new ClientConnection();
			client.connect();
			deliveryList = (Collection<Delivery>) client.getDeliveries();
			client.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (deliveryList != null) {
			for (Delivery d : deliveryList) {
				list.add(parseDelivery(d));
			}
		}

		listViewDelivery.setItems(list);
	}

	private String parseDelivery(Delivery d) {
		String result = "";
		result += d.getDateTime() + " - " + d.getDeliverer().getId() + ": "
				+ d.getWaste().getWasteType() + " "
				+ d.getWaste().getWasteAmount() + "kg for "
				+ d.getCost().inEuro() + " Euro";

		return result;
	}
}
