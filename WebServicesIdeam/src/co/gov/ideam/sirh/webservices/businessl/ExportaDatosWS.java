package co.gov.ideam.sirh.webservices.businessl;

import co.gov.ideam.sirh.util.IdeamException;

import co.gov.ideam.sirh.webservices.model.DataWebService;
import co.gov.ideam.sirh.webservices.model.SingleRowWebService;

import java.util.HashMap;
import java.util.List;

import java.util.Map;

import javax.ejb.Remote;

@Remote
public interface ExportaDatosWS {
    
    public DataWebService getSingleData(Long sentenciaId, Object... parameters)throws IdeamException;
    
    public SingleRowWebService getOneRow(Long sentenciaId, Object... parameters)throws IdeamException;
    
    public List<SingleRowWebService> getMultipleRows(Long sentenciaId, Object... parameters)throws IdeamException;
    

}
