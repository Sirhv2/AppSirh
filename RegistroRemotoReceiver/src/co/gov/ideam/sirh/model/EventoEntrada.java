package co.gov.ideam.sirh.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class EventoEntrada  implements Serializable{
    private String serviceName;
    private String methodName;
    private HashMap data;
    
    public EventoEntrada(){
        
    }
    
    public EventoEntrada(String serviceName, String methodName, HashMap data) {
        this.serviceName = serviceName;
        this.methodName = methodName;
        this.data = data;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public HashMap getData() {
        return data;
    }

    public void setData(HashMap data) {
        this.data = data;
    }
}
