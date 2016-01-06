package swe2.client.controller.operator;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import swe2.shared.model.MixedWaste;

public class CreateCombustionController extends CombustionController {

	@FXML
	TextField txtCO2;

	@FXML
	public void startCombustionClick() {
		MixedWaste toBeCombusted = new MixedWaste();
		// The user should now be able to select/ add waste to the toBeCombusted
		// waste from WasteStorage waste, not implemented yet.
		// Until then, combusted waste will just be empty
		txtCO2.setText(toBeCombusted.calculateCo2Emission()
				.calculateTaxCost().inEuro() + "€");
		boolean started = startCombustion(toBeCombusted);
		// If started is false, the combustion could not be started
		// because there is already one running.
		// The user should be informed about that, not implemented yet.
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}
}
