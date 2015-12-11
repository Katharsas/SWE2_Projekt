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
    private final Class type;
    
    public Credentials(String userId, String password, Class type){
        this.userId = userId;
        this.password = password;
        this.type = type;
    }
    
    public String getUserId(){
        return this.userId;
    }
    
    public String getPassword(){
        return this.password;
    }
    
    public Class getType(){
        return this.type;
    }
    
}
