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
import swe2.shared.model.WasteType;
/**
 * Controller for the wasteoverview window
 * @author Philipp
 *
 */
public class ViewWasteController extends TaskController implements
		Initializable {
	@FXML
	ListView listViewWasteStorage;
	
	/**
	 * Writes all available waste from the storage into the listview
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		WasteStorage wasteList = null;
		ObservableList<String> list = FXCollections.observableArrayList();

		try {
			client = new ClientConnection();
			client.connect();
			wasteList = client.getWasteStorage();
			client.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (wasteList != null) {
			for( WasteType t : wasteList.keySet() ){
				list.add( t.name() + ": " + wasteList.get(t) );
			}
		}
		listViewWasteStorage.setItems(list);
	}
	
	/**
	 * Parse a wastestorage object into a string
	 * @param w Wastestorage object
	 * @return String made of a wastestorage object
	 */
	/*private String parseWaste(WasteStorage w) {
		String result = "";

		result = "Paper: " + w.get(WasteType.PAPER)
				+ "Plastic: " + w.get(WasteType.PLASTIC)
				+ "Residual" + w.get(WasteType.RESIDUAL);

		return result;
	}*/
}
