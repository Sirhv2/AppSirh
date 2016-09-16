package co.gov.ideam.sirh.auditoria.business;

import co.gov.ideam.sirh.auditoria.model.Auditoria;
import co.gov.ideam.sirh.util.IdeamException;

import java.util.List;

import javax.ejb.Remote;


@Remote
public interface AuditoriaEJB {
  
    public List<Auditoria> getAllAuditorias() throws IdeamException;
    
    public List<Auditoria> getAllAuditoriasByObjeto(Auditoria auditoria) throws IdeamException;

    public Auditoria updateAuditoria(Auditoria auditoria) throws IdeamException;

    public void deleteAuditoria(Auditoria auditoria) throws IdeamException;

}
