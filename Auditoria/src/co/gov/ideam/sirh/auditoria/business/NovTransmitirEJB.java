package co.gov.ideam.sirh.auditoria.business;
// HUGO 20141030

import co.gov.ideam.sirh.auditoria.model.NovTransmitir;
import co.gov.ideam.sirh.util.IdeamException;

import java.util.List;

import javax.ejb.Remote;


@Remote
public interface NovTransmitirEJB {
  
    public List<NovTransmitir> getAllNovTransmitir() throws IdeamException;
    
    public void agregarNovTransmitir(NovTransmitir novTransmitir) throws IdeamException;

}
