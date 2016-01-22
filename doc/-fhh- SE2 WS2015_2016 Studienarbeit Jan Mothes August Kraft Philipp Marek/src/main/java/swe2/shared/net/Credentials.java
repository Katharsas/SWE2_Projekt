/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package swe2.shared.net;

import java.io.Serializable;

import swe2.shared.model.User;

/**
 *
 * @author akraft
 */
@SuppressWarnings("serial")
public class Credentials implements Serializable {
    
    private final String userId;
    private final String password;
    private final Class<? extends User> type;
    
    public Credentials(String userId, String password,
    		Class<? extends User> type) {
        this.userId = userId;
        this.password = password;
        this.type = type;
    }
    
    public String getUserId() {
        return this.userId;
    }
    
    public String getPassword() {
        return this.password;
    }
    
    public Class<? extends User> getType() {
        return this.type;
    }
    
}
