package swe2.client.controller.operator;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.ResourceBundle;

import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import swe2.client.net.ClientConnection;
import swe2.shared.model.Co2Amount;
import swe2.shared.model.Combustion;
import swe2.shared.model.Delivery;
import swe2.shared.model.MixedWaste;
import swe2.shared.model.Operator;

public class ViewCombustionController extends TaskController
		implements Initializable {
	@FXML
	ListView listViewCombustion;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Collection<Combustion> combustionList = null;
		ObservableList<String> list = FXCollections.observableArrayList();

		try {
			client = new ClientConnection();
			client.connect();
			combustionList = (Collection<Combustion>) client.getCombustions();
			client.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (combustionList != null) {
			for (Combustion c : combustionList) {
				list.add(parseCombustion(c));
			}
		}

		listViewCombustion.setItems(list);
	}

	private String parseCombustion(Combustion c) {

		// private Long id;
		//
		// private final Co2Amount co2;
		// @Lob
		// private final LocalDateTime dateTime;
		// @ManyToOne
		// private final Operator operator;
		// private final MixedWaste waste;

		String result = "";
		result += c.getDateTime() + " - " + c.getOperator().getId() + ": "
				+ c.getCo2().calculateTaxCost() + " Euro";

		return result;
	}
}