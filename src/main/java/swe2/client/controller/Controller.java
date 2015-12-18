/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swe2.client.controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import swe2.JavaFxGui;
import swe2.shared.model.Deliverer;
import swe2.shared.model.User;

/**
 *
 * @author akraft
 */
public class Controller {
	
	protected Stage stage;
    protected User loggedInUser;
    protected JavaFxGui main;
	
	@FXML
    TextField idField;
	
	
	public void setMain( JavaFxGui main ){
        this.main = main;
    }
    
    public void setLoggedInUser( User user ){
        this.loggedInUser = user;
        idField.setText( loggedInUser.getId() );
    }
    
    public void setStage( Stage stage ){
        this.stage = stage;
    }
	
	
}

