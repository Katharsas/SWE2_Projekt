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
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author akraft
 */

public class Main extends Application{

    private Stage primaryStage;
    private AnchorPane root;
    
    
    @Override
    public void start( Stage primaryStage ){

        this.primaryStage = primaryStage;
        this.primaryStage.setTitle( "Müllverbrenner 2000 Express" );
        try{
            FXMLLoader loader = new FXMLLoader( Main.class.getResource( "/view/delivery_mask.fxml" ) );
            root = (AnchorPane) loader.load();
            primaryStage.setScene( new Scene( root ) );
            primaryStage.show();
        }catch( Exception e ){ e.printStackTrace(); }
        
        
    }
    
    public static void main( String[] args){
        launch( args );
    }
    
}

