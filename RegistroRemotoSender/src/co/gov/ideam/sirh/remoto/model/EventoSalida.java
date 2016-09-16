package co.gov.ideam.sirh.remoto.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;


public class EventoSalida implements Serializable{
    private String serviceName;
    private String methodName;    
    private List listData;;
    
    public EventoSalida(){
        
    }
    public EventoSalida(String serviceName, String methodName, Object... data) {
        this.serviceName = serviceName;
        this.methodName = methodName;
        this.listData = new ArrayList();
        for(int i=0; i<data.length;i++){
            this.listData.add(data[i]);
        }
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
    
    public void addData(Object data){
        if(this.listData==null){
            this.listData = new ArrayList();
        }
        this.listData.add(data);
    }

    public List getListData() {
        return listData;
    }

    public void setListData(List listData) {
        this.listData = listData;
    }
}
