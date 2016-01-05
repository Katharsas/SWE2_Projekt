package swe2.client.controller.operator;

import java.net.URL;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import swe2.client.net.ClientConnection;
import swe2.shared.model.Combustion;
import swe2.shared.model.Delivery;
import swe2.shared.model.MixedWaste;
import swe2.shared.model.Operator;
import swe2.shared.model.WasteAmount;
import swe2.shared.model.WasteType;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class EndCombustionController extends CombustionController{
	
	@FXML
	public void endCombustionClick(){
		Combustion combustion;
		MixedWaste mixedWaste = initMixedWaste();

		combustion = new Combustion(mixedWaste, (Operator) getLoggedInUser());
		
		try {
			client = new ClientConnection();
			client.connect();
			client.saveCombustion(combustion);
			client.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		lblStatus.setVisible(true);
		if(endCombustion()){
			setLabelText("Combustion completed without a problem!", "009933");
		} else {
			setLabelText("No combustion started!", "FF0000");
		}
	}
	
	public MixedWaste initMixedWaste(){
		MixedWaste mixedWaste = new MixedWaste();
		Collection<Delivery> deliveryCollection = null;
		try {
			client = new ClientConnection();
			client.connect();
			deliveryCollection = (Collection<Delivery>) client.getDeliveries();
			client.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for(Delivery d : deliveryCollection){
			mixedWaste.addWaste(d.getWaste().getWasteType(), d.getWaste().getWasteAmount());
		}
		
		return mixedWaste;
	}
}
