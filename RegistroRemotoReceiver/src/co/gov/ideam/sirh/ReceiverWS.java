package co.gov.ideam.sirh;

import co.gov.ideam.sirh.model.EventoEntrada;
import co.gov.ideam.sirh.util.IdeamException;

import javax.ejb.Remote;

@Remote
public interface ReceiverWS {
    
    public void recibirEvento(EventoEntrada evento)throws IdeamException;
    
    
}
