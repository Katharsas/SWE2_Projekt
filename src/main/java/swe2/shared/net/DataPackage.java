/*
 * Paket in dem Daten und Anfrage gespeichert wereden.
 * Wird vom Client als auch Server benutzt.
 * Hat keine weiteren Sinn und Nutzen.
 */
package swe2.shared.net;

import java.io.Serializable;

/**
 *
 * @author akraft
 */
@SuppressWarnings("serial")
public class DataPackage implements Serializable {
    
    private final RequestType requestType;
    private final Serializable data;
    
    public DataPackage(RequestType requestType, Serializable data){
        this.requestType = requestType;
        this.data = data;
    }
    
    public RequestType getRequestType(){
        return this.requestType;
    }
    
    public Serializable getData(){
        return this.data;
    }
    
    
}
