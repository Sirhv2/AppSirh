package co.gov.ideam.sirh.publicaciones.view;

import co.gov.ideam.sirh.documentos.business.DocumentoEJB;

import java.util.List;
import co.gov.ideam.sirh.publicaciones.business.PublicacionEJB;
import co.gov.ideam.sirh.publicaciones.model.ArchivosXPublicacion;
import co.gov.ideam.sirh.publicaciones.model.Publicacion;
import co.gov.ideam.sirh.servlet.ServletLocator;
import co.gov.ideam.sirh.util.IdeamException;



/**
 * Concentra el llamado a todos los metodos del EJB del modulo Publicaciones
 */
public class PublicacionesDelegate {
    /**
     * Referencia al EJB
     */
    
    private PublicacionEJB publicacionService = null;
    private DocumentoEJB documentoService = null; 

    /**
     * Utilizada para implementar singleton
     */
    private PublicacionesDelegate instance;

    /**
     * Retorna una instancia a la clase
     * @return
     */
    public static PublicacionesDelegate getInstance() throws IdeamException {
        //if (instance == null) {
        // instance = new PomcaDelegate();
        //}
        //return instance;
        return new PublicacionesDelegate();
    }

    /**
     * Retorna el plan de ordenamiento para la fuente y autoridad ambiental
     * recibidos como parametro
     * @param codigoFuente
     * @param codigoAutoridad
     * @return
     * @throws IdeamException
     */
    public Publicacion updatePomtPublicacion(Publicacion publicacion) throws IdeamException {
        return publicacionService.updatePomtPublicacion(publicacion);
    }
    
    public List<Publicacion> getAllPomtPublicaciones() throws IdeamException {
        return publicacionService.getAllPomtPublicaciones();
    }
    
    public List<Publicacion> getAllPomtPublicacionByNombre(Publicacion publicacion) throws IdeamException{
        return publicacionService.getAllPomtPublicacionByNombre(publicacion);
    }
 
    public List<Publicacion> getAllPomtPublicacionByTipoRecurso(Integer tipoRecurso) throws IdeamException{
      return publicacionService.getAllPomtPublicacionByTipoRecurso(tipoRecurso);
    }
    public void updateArchivosXPublicacion(ArchivosXPublicacion archipub)throws IdeamException{
        publicacionService.updateArchivosXPublicacion(archipub);
    }
    
  public List<Publicacion> getAllPomtPublicacionByAH(String ah) throws IdeamException{
    return publicacionService.getAllPomtPublicacionByAH(ah);
  }
  
  public List<Publicacion> getAllPomtPublicacionBySistemaAcuifero(String sistemaAcuifero) throws IdeamException{
    return publicacionService.getAllPomtPublicacionBySistemaAcuifero(sistemaAcuifero);
  }
    
    public void removePomtPublicacion(Publicacion publicacion) throws IdeamException{
        publicacionService.removePomtPublicacion(publicacion);
    }

    /**
     * Construcutor privado para implementar singleton
     */
    private PublicacionesDelegate() throws IdeamException {
        publicacionService = ServletLocator.getPublicacionesService();
        documentoService = ServletLocator.getDocumentoService();
    }
}
