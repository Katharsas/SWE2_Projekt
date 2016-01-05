package swe2.client.controller.operator;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class CombustionController extends TaskController{
	@FXML
	Label lblStatus;
	
	private static boolean combustionFlag = false;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		lblStatus.setVisible(false);
	}
	
	public boolean startCombustion(){
		if(combustionFlag)
			return false;
		combustionFlag = true;
		return true;
	}
	
	public boolean endCombustion(){
		if(!combustionFlag)
			return false;
		combustionFlag = false;
		return true;
	}
	
	public boolean getCombustionState(){
		return combustionFlag;
	}
	
	public void setLabelText(String text, String hexcolor){
		String css = "";
		String hex = createHex(hexcolor);
		css = "-fx-text-fill: #" + hex + ";";
		lblStatus.setText(text);
		lblStatus.setStyle(css);
	}
	
	public void setLabelText(String text, boolean isError){
		lblStatus.setText(text);
		lblStatus.setStyle(isError ? "-fx-text-fill:red;" : "-fx-text-fill:green;");
	}
	
	private String createHex(String hex){
		return ((hex.matches("[0-9a-fA-F]{6}")) ? hex : "000000");
	}
}
