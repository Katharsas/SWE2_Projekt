package swe2.client.controller.operator;

import javafx.fxml.FXML;
import swe2.client.net.ClientConnection;
import swe2.shared.model.Combustion;
import swe2.shared.model.MixedWaste;
import swe2.shared.model.Operator;

public class EndCombustionController extends CombustionController{
	
	@FXML
	public void endCombustionClick(){
		// get the combustion the user created when starting the combustion
		MixedWaste combusted = endCombustion();
		if(combusted != null) {
			Combustion combustion =
					new Combustion(combusted, (Operator) getLoggedInUser());
			try {
				client = new ClientConnection();
				client.connect();
				client.saveCombustion(combustion);
				client.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			setLabelText("Combustion completed without a problem!", "009933");
		} else {
			setLabelText("No combustion started!", "FF0000");
		}
		lblStatus.setVisible(true);
	}
}
