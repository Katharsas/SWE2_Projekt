/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swe2.control.deliverer;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import swe2.control.loginController;
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
    
    
    @Override
    public void start( Stage primaryStage ){

        this.primaryStage = primaryStage;
        this.primaryStage.setTitle( "Müllverbrenner 2000 Express" );
        FXMLLoader loader = null;
                
        try{
            loader = new FXMLLoader( Main.class.getResource( "/view/delivery_mask.fxml" ) );
            root = (AnchorPane) loader.load();
            primaryStage.setScene( new Scene( root ) );
            primaryStage.show();
        }catch( Exception e ){ e.printStackTrace(); }
        
        delivererController ctrl = loader.getController();
        showLoginMask( this.primaryStage );
        
    }
    
    private void showLoginMask( Stage motherStage ){
        
        FXMLLoader loader;
        Stage loginStage;
        AnchorPane loginWindow;
        loginController ctrl;
        
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
            
        }catch( Exception e ){ e.printStackTrace(); }
        
        
    }
    
    public void showError( String errorText ){
        new Alert( Alert.AlertType.WARNING, errorText, ButtonType.OK ).showAndWait();
    }
    
    public static void main( String[] args){
        launch( args );
    }
    
    public User getLoggedInUser(){
        return this.loggedInUser;
    }
    
    public void setLoggedInUser( User user ){
        this.loggedInUser = user;
    }
    
}

