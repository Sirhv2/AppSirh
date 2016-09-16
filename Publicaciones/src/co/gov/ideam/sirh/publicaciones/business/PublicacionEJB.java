package co.gov.ideam.sirh.publicaciones.business;


import co.gov.ideam.sirh.fuentes.model.FnttFuente;

import co.gov.ideam.sirh.publicaciones.model.ArchivosXPublicacion;
import co.gov.ideam.sirh.publicaciones.model.Publicacion;
import co.gov.ideam.sirh.util.IdeamException;

import java.util.List;

import javax.ejb.Remote;


@Remote
public interface PublicacionEJB {
  
    public List<Publicacion> getAllPomtPublicaciones() throws IdeamException;
    
    public List<Publicacion> getAllPomtPublicacionByNombre(Publicacion publicacion) throws IdeamException;

  public List<Publicacion> getAllPomtPublicacionByTipoRecurso(Integer tipoRecurso) throws IdeamException;  
 
  public List<Publicacion> getAllPomtPublicacionByAH(String ah) throws IdeamException;  

  public List<Publicacion> getAllPomtPublicacionBySistemaAcuifero(String sistemasAcuiferos) throws IdeamException;  

    public Publicacion updatePomtPublicacion(Publicacion publicacion) throws IdeamException;
    
    public void removePomtPublicacion(Publicacion publicacion) throws IdeamException;

    public void updateArchivosXPublicacion(ArchivosXPublicacion archipubli) throws IdeamException;
}
