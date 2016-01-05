package swe2.client.controller.operator;

import java.math.BigDecimal;
import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;

import swe2.client.net.ClientConnection;
import swe2.shared.model.Combustion;
import swe2.shared.model.Delivery;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class CreateCombustionController extends CombustionController {

	@FXML
	TextField txtCO2;

	@FXML
	public void startCombustionClick() {
		startCombustion();
		try {
			client = new ClientConnection();
			client.connect();

			client.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		BigDecimal cost = new BigDecimal(0);
		try {
			client = new ClientConnection();
			client.connect();
			cost = calculateCO2Cost((Collection<Delivery>) client
					.getDeliveries());
			client.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		txtCO2.setText(cost.toString() + "€");
	}

	public BigDecimal calculateCO2Cost(Collection<Delivery> collectionDelivery) {
		BigDecimal cost = new BigDecimal(0);
		BigDecimal tmpCost;
		for (Delivery delivery : collectionDelivery) {
			cost = cost.add(delivery.getCost().inEuro());
			// cost.add(c.getCo2().calculateTaxCost().inEuro());
		}

		return cost;
	}
}
