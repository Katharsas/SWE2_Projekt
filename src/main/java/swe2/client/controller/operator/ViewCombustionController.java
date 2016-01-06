package swe2.client.controller.operator;

import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import swe2.client.net.ClientConnection;
import swe2.shared.model.Combustion;

/**
 * Controller for the combustionoverview window
 * @author Philipp
 *
 */
public class ViewCombustionController extends TaskController
		implements Initializable {
	@FXML
	ListView listViewCombustion;

	/**
	 * Writes all available combustions from the storage into the listview
	 */
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

	/**
	 * Parse a combustion object into a string
	 * @param w Combustion object
	 * @return String made of a wastestorage object
	 */
	private String parseCombustion(Combustion c) {
		String result = "";
		result += c.getDateTime() + " - " + c.getOperator().getId() + ": "
				+ c.getCo2().calculateTaxCost().inEuro() + " Euro";

		return result;
	}
}
