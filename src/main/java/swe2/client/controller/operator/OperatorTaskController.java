package swe2.client.controller.operator;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import swe2.client.ClientConnection;

public class OperatorTaskController implements Initializable{
	protected ClientConnection client;
	Stage operatorTaskStage;
	@FXML
	Button backButton;
	
	@FXML
	public void back(){
		operatorTaskStage.close();
	}
	
	public void setOperatorTaskStage(Stage operatorTaskStage){
		this.operatorTaskStage = operatorTaskStage;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		client = new ClientConnection();
	}
	
	
}
