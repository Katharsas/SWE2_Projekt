package swe2.client.controller.operator;

import java.math.BigDecimal;
import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;

import swe2.client.net.ClientConnection;
import swe2.shared.model.Combustion;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class CreateCombustionController extends TaskController {

	@FXML
	TextField txtCO2;

	@FXML
	public void startCombustion() {
		try{
			client = new ClientConnection();
			client.connect();
			
			client.close();
		} catch(Exception e){
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		BigDecimal cost = new BigDecimal(0);
		try{
			client = new ClientConnection();
			client.connect();
			cost = calculateCO2Cost((Collection<Combustion>)client.getCombustions());
			client.close();
		} catch (Exception e){
			e.printStackTrace();
		}
		txtCO2.setText(cost.toString() + "€");
	}
	
	public BigDecimal calculateCO2Cost(Collection<Combustion> cc){
		BigDecimal cost = new BigDecimal(0);
		
		for(Combustion c : cc){
			cost.add(c.getCo2().calculateTaxCost().inEuro());
		}
		
		return cost;
	}
}
