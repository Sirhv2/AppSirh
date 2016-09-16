package co.gov.ideam.sirh.webservices.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;

public class SingleRowWebService implements Serializable{
    
    private List<DataWebService> fields;
    
    public SingleRowWebService() {
        this.setFields(new ArrayList());
    }
    
    public SingleRowWebService(List fields) {
        this.setFields(fields);
    }

    public List getFields() {
        return fields;
    }

    public void setFields(List fields) {
        this.fields = fields;
    }
    
    public void addData(DataWebService data){
        if(this.fields==null){
            this.fields = new ArrayList();
        }
        this.fields.add(data);
    }
}
