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
public class Credentials implements Serializable {
    
    private final String userId;
    private final String password;
    
    public Credentials(String userId, String password){
        this.userId = userId;
        this.password = password;
    }
    
    public String getUserId(){
        return this.userId;
    }
    
    public String getPassword(){
        return this.password;
    }
    
}
