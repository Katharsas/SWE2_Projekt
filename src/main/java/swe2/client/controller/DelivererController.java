/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swe2.client.controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import swe2.client.ClientConnection;
import swe2.client.deliverer.Main;
import swe2.shared.model.User;

/**
 *
 * @author akraft
 */
public class DelivererController {
    
    private Stage delivererStage;
    private Main main;
    private User loggedInUser;
    private ClientConnection client;
    
    @FXML
    TextField idField, amountField;
    @FXML
    SplitMenuButton typeSelect;
    @FXML
    Label typeLabel;
    @FXML
    Button submitButton;
    
    @FXML
    public void initialize() {
        
        /*
        typeSelect.setOnAction( new EventHandler() {
            @Override
            public void handle(ActionEvent e) {
                System.out.println("Shutdown");
            }
        });
                */
    }
    
    public void selectType(){
        
        System.out.println( "ggg" + typeSelect.getText() ) ;
        typeLabel.setText( typeSelect.getText() );
    }
    
    public void saveDelivery(){
    
    
    }
    
    
    public void setMain( Main main ){
        this.main = main;
    }
    
    public void setLoggedInUser( User user ){
        this.loggedInUser = user;
        idField.setText( loggedInUser.getId() );
    }
    
    public void setDelivererStage( Stage deliverereStage ){
        this.delivererStage = deliverereStage;
    }
    
}
