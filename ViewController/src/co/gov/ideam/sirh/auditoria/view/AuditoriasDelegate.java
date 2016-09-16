package co.gov.ideam.sirh.auditoria.view;

import co.gov.ideam.sirh.auditoria.business.AuditoriaEJB;
import co.gov.ideam.sirh.auditoria.model.Auditoria;
import co.gov.ideam.sirh.documentos.business.DocumentoEJB;

import java.util.List;
import co.gov.ideam.sirh.publicaciones.business.PublicacionEJB;
import co.gov.ideam.sirh.publicaciones.model.ArchivosXPublicacion;
import co.gov.ideam.sirh.publicaciones.model.Publicacion;
import co.gov.ideam.sirh.servlet.ServletLocator;
import co.gov.ideam.sirh.util.IdeamException;

import javax.ejb.EJB;

/**
 * Concentra el llamado a todos los metodos del EJB del modulo Publicaciones
 */
public class AuditoriasDelegate {
    /**
     * Referencia al EJB
     */
    
    private AuditoriaEJB auditoriaService = null;
    
    /**
     * Utilizada para implementar singleton
     */
    private AuditoriasDelegate instance;

    /**
     * Retorna una instancia a la clase
     * @return
     */
    public static AuditoriasDelegate getInstance() throws IdeamException {
        //if (instance == null) {
        // instance = new PomcaDelegate();
        //}
        //return instance;
        return new AuditoriasDelegate();
    }

    /**
     * Retorna el plan de ordenamiento para la fuente y autoridad ambiental
     * recibidos como parametro
     * @param codigoFuente
     * @param codigoAutoridad
     * @return
     * @throws IdeamException
     */
    public Auditoria setAuditoria(Auditoria auditoria) throws IdeamException {
        return auditoriaService.updateAuditoria(auditoria);
    }
    
    public List<Auditoria> getAllAuditorias() throws IdeamException {
        return auditoriaService.getAllAuditorias();
    }
    
    public List<Auditoria> getAllAuditoriasByObjeto(Auditoria auditoria) throws IdeamException{
        return auditoriaService.getAllAuditoriasByObjeto(auditoria);
    }
 
   
    public void removeAuditoria(Auditoria auditoria) throws IdeamException{
        auditoriaService.deleteAuditoria(auditoria);
    }

    /**
     * Construcutor privado para implementar singleton
     */
    private AuditoriasDelegate() throws IdeamException {
        auditoriaService = ServletLocator.getAuditoriaService();
    }
}
