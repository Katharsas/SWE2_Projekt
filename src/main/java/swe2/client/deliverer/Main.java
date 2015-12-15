/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swe2.client.deliverer;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import swe2.client.controller.DelivererController;
import swe2.client.controller.LoginController;
import swe2.shared.model.Deliverer;
import swe2.shared.model.User;

/**
 *
 * @author akraft
 */

public class Main extends Application{

    private Stage primaryStage;
    private AnchorPane root;
    private User loggedInUser;
    DelivererController ctrl;
    
    
    @Override
    public void start( Stage primaryStage ){

        this.primaryStage = primaryStage;
        this.primaryStage.setTitle( "Müllverbrenner 2000 Express" );
        FXMLLoader loader = null;
                
        try{
            loader = new FXMLLoader( Main.class.getResource( "/view/delivery_mask.fxml" ) );
            root = (AnchorPane) loader.load();
            this.primaryStage.setScene( new Scene( root ) );
            this.primaryStage.show();
        }catch( Exception e ){ e.printStackTrace(); }
        
        ctrl = loader.getController();
        ctrl.setDelivererStage( this.primaryStage );
        ctrl.setMain( this );
        
        showLoginMask( this.primaryStage );
        
    }
    
    private void showLoginMask( Stage motherStage ){
        
        FXMLLoader loader;
        Stage loginStage;
        AnchorPane loginWindow;
        LoginController ctrl;
        
        try{
            loader = new FXMLLoader( Main.class.getResource( "/view/log_mask.fxml" ) );
            loginWindow = loader.load();
            loginStage = new Stage(); //Muss initialisiert werden
            
            loginStage.initModality( Modality.WINDOW_MODAL );
            loginStage.initOwner( motherStage );
            
            loginStage.setScene( new Scene( loginWindow ) );
            loginStage.getScene().getWindow().setOnCloseRequest( ev->{
                motherStage.close();
                ev.consume();
                System.out.println( "Exit" );
                
            } );
            loginStage.setTitle( "Anmeldung" );
            
            ctrl = loader.getController();
            ctrl.setLoginStage( loginStage );
            ctrl.setMain(this);
            ctrl.setType( Deliverer.class );
            
            loginStage.showAndWait();
            //Nur nach erfolgreicher Anmeldung wird hier weitergemacht
            this.pushUser( loggedInUser );
            
        }catch( Exception e ){ e.printStackTrace(); }
        
        
    }
    
    public void showError( String errorText ){
        new Alert( Alert.AlertType.WARNING, errorText, ButtonType.OK ).showAndWait();
    }
    
    public static void main( String[] args){
        launch( args );
    }
    
    public void pushUser( User user ){
        this.ctrl.setLoggedInUser( user );
    }
    
    public User getLoggedInUser(){
        return this.loggedInUser;
    }
    
    public Stage getPrimaryStage(){
        return this.primaryStage;
    }
    
    public void setLoggedInUser( User user ){
        this.loggedInUser = user;
    }
    
}

