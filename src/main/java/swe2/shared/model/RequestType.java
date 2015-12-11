/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swe2.shared.model;

import java.io.Serializable;

/**
 *
 * @author akraft
 */
public enum RequestType implements Serializable{
    
    //Lesen
    GET_DELIVERY(),
    GET_COMBUSTION(),
    GET_WASTESTORAGE(),
    DATA(),
    
    //Speichern
    PUT_DELIVERY(),
    PUT_COMBUSTION(),
    SUCCESS(),
    
    //Login
    VALIDATION(),
    GRANTED(),
    ERROR();

    
}
