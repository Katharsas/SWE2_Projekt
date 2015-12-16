/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swe2.client.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import swe2.client.ClientConnection;
import swe2.client.deliverer.JavaFxGui;
import swe2.shared.model.Deliverer;
import swe2.shared.model.Delivery;
import swe2.shared.model.UniformWaste;
import swe2.shared.model.User;
import swe2.shared.model.WasteAmount;
import swe2.shared.model.WasteType;

/**
 *
 * @author akraft
 */
public class DelivererController {
    
    private Stage delivererStage;
    private Deliverer loggedInUser;
    private JavaFxGui main;
    private ClientConnection client;
    
    @FXML
    TextField idField, amountField;
    @FXML
    ChoiceBox typeSelect;
    @FXML
    Button submitButton;
    
    @FXML
    public void initialize() {
        
        typeSelect.setItems( FXCollections.observableArrayList("Paper", "Plastic", "Residual") );
        
        /*
        typeSelect.setOnAction( new EventHandler() {
            @Override
            public void handle(ActionEvent e) {
                System.out.println("Shutdown");
            }
        });
                */
    }
   
    
    public void saveDelivery(){
        
        String error = "";
        
        if( typeSelect.getValue() == null ){
            error += "Es wurde kein Typ ausgewaehlt!\n";
        }
        if( amountField.getText().equals("") ){
            error += "Es wurde keine Menge angegeben!\n";
        }/*
        else if( Pattern.matches("[a-zA-Z]+", amountField.getText() ) == true ){
            error += "Nur Zahlen bei Menge (kein kg, etc.)!\n";
        }*/
        
        if( ! (error.equals("")) ){
            main.showError( error );
        }else{
            
            WasteType type = getWasteType( (String) typeSelect.getValue() );
            WasteAmount amount = null;
            try{
                amount = new WasteAmount( Integer.parseInt( amountField.getText() ) ) ;
            }catch( IllegalArgumentException e ){
                e.printStackTrace();
                main.showError( "Nur Zahlen bei Menge (kein kg, etc.)!" );
                return;
            }
            
            try{
            String feedback = client.saveDelivery(
                        new Delivery(
                                new UniformWaste(
                                        type,
                                        amount 
                                ),
                                loggedInUser
                        )
                );
            }catch( Exception e ){
                e.printStackTrace();
                main.showError( "Probleme beim Speichern" );
            }
        }
        
    }
    
    /*
    * Compare Text and decide
    * It can only be one of the three options because it's a choice box
    */
    private WasteType getWasteType( String inputFromChoice ){
        if( inputFromChoice.equals("Paper") )
            return WasteType.PAPER;
        else if( inputFromChoice.equals("Plastic") )
            return WasteType.PLASTIC;
        else
            return WasteType.RESIDUAL;
    }
    
    public void setMain( JavaFxGui main ){
        this.main = main;
    }
    
    public void setLoggedInUser( User user ){
        this.loggedInUser = (Deliverer) user;
        idField.setText( loggedInUser.getId() );
    }
    
    public void setDelivererStage( Stage deliverereStage ){
        this.delivererStage = deliverereStage;
    }
    
}
