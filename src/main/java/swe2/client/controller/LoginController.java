/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swe2.client.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import swe2.client.JavaFxGui;
import swe2.client.net.ClientConnection;
import swe2.shared.model.Deliverer;
import swe2.shared.model.User;
import swe2.shared.net.Credentials;

/**
 *
 * @author akraft
 */
public class LoginController extends Controller {
    
    private JavaFxGui main;
    
    private Stage loginStage;
    private String name;
    private String pwd;
    private Class<? extends User> type = Deliverer.class;
    
    private ClientConnection client;
    
    @FXML
    private TextField nameField;
    @FXML
    private TextField pwdField;
    @FXML
    private Button loginButton;
    
    @FXML
    private void initialize() {}
    
    public void login(){
    
        name = nameField.getText();
        pwd = pwdField.getText();
        
        User user;

        
        if( name.equals("") || pwd.equals("") )
            main.showError( "Leere(s) Textfeld(er)!" );
        else
            try{
                client = new ClientConnection();
                client.connect();
                user = (User) client.getAccess( new Credentials( name, pwd, type ) );
                client.close();
                
                 if( user == null )
                    main.showError( "Anmeldedaten inkorrekt!" );
                 else{
                     main.setLoggedInUser(user);
                     loginStage.close();
                 }
            }catch( Exception e ){
                e.printStackTrace();
                main.showError( "Verbindungsfehler!" );
            }
            
    }
    
    
    public Stage getLoginStage(){
        return this.loginStage;
    }
    
    public void setLoginStage( Stage loginStage ){
        this.loginStage = loginStage;
    }
    
    public void setMain( JavaFxGui main ){
        this.main = main;
    }
    
    public void setType( Class<? extends User> type ){
        this.type = type;
    }
    
}
