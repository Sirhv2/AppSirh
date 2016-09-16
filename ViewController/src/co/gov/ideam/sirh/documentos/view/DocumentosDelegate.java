package co.gov.ideam.sirh.documentos.view;

import co.gov.ideam.sirh.documentos.business.DocumentoEJB;
import co.gov.ideam.sirh.documentos.model.Archivo;
import co.gov.ideam.sirh.documentos.model.Nodo;
import co.gov.ideam.sirh.parametros.business.ParametrosEJB;
import java.util.List;
import co.gov.ideam.sirh.servlet.ServletLocator;
import co.gov.ideam.sirh.util.IdeamException;
import javax.ejb.EJB;

/**
 * Cponcentra e llamado a todos los metodos del EJB del modulo Pomca
 */
public class DocumentosDelegate {
    /**
     * Referencia al EJB
     */
    
    private DocumentoEJB documentoService = null;
    @EJB
    private ParametrosEJB parametrosService;


    /**
     * Utilizada para implementar singleton
     */
    private DocumentosDelegate instance;

    /**
     * Retorna una instancia a la clase
     * @return
     */
    public static DocumentosDelegate getInstance() throws IdeamException {
        //if (instance == null) {
        // instance = new PomcaDelegate();
        //}
        //return instance;
        return new DocumentosDelegate();
    }

    public Long cargarArchivo(Nodo nodo, Archivo archivo) throws IdeamException {
        return documentoService.cargarArchivo(nodo, archivo);
    }

    public Nodo buscarNodo(Long idArchivo) throws IdeamException {
        return documentoService.buscarNodo(idArchivo);
    }
    
    public Archivo descargarArchivo(Nodo nodo) throws IdeamException {
        return documentoService.buscarArchivo(nodo);
    }

    /**
     * Construcutor privado para implementar singleton
     */
    private DocumentosDelegate() throws IdeamException {
        documentoService = ServletLocator.getDocumentoService();
    }
}
