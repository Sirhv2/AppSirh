package co.gov.ideam.sirh.parametros.business;

import co.gov.ideam.sirh.parametros.model.ActividadCIIU;
import co.gov.ideam.sirh.parametros.model.Autoridades;
import co.gov.ideam.sirh.parametros.model.Categoria;
import co.gov.ideam.sirh.parametros.model.Divipola;
import co.gov.ideam.sirh.parametros.model.Lista;
import co.gov.ideam.sirh.parametros.model.OfertaEstacSubzonas;
import co.gov.ideam.sirh.parametros.model.OfertaSubzonas;
import co.gov.ideam.sirh.parametros.model.Parametro;
import co.gov.ideam.sirh.parametros.model.PartZonificListas;
import co.gov.ideam.sirh.parametros.model.TipoParametro;
import co.gov.ideam.sirh.util.IdeamException;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface ParametrosEJB {
    /**
     * Retorna el area, zona o subzona correspondiente al nombre recibido como
     * parametro y al padre.
     * @param nombre
     * @param padre
     * @return
     * @throws IdeamException
     */
    public PartZonificListas getZonificacionByName(String nombre, PartZonificListas padre)throws IdeamException;
    /**
     * Retorna la divipola correpospondiente al nombre y padre recibidos por parametro
     * @param nombre
     * @param padre
     * @return
     * @throws IdeamException
     */
    public Divipola getDivipolaByName(String nombre, Divipola padre)throws IdeamException;
    /**
     * Retorna la  divipola correspondiente al nombre y clase recibidos por parametro
     * @param nombre
     * @param clase
     * @return
     * @throws IdeamException
     */
    public Divipola getDivipolaByName(String nombre, String clase)throws IdeamException;
    /**
     * Retorna la lista correspondiente a la categoria y descripcion 
     * recibidas por parametro
     * @param descripcion
     * @param categoria
     * @return
     * @throws IdeamException
     */
    public Lista getListaByDescripcion(String descripcion, Long categoria)throws IdeamException;
    /**
     * Retorna la actividad CIIU correspondiente al codigo recibido como 
     * parametro
     * @param codigo
     * @return
     * @throws IdeamException
     */
    public ActividadCIIU getActividadCiiu(Long codigo)throws IdeamException;
    /**
     * Retorna una lista con todasd las actividades ciiu que tienen el nombre
     * recibido por parametro
     * @param nombre
     * @return
     * @throws IdeamException
     */    
    public List getCiiuByName(String nombre)throws IdeamException;

    /**
     * Retorna una lista con todasd las actividades ciiu que tienen el codigo
     * recibido por parametro
     * @param codigo
     * @return
     * @throws IdeamException
     */
    public List getCiiuByCode(String codigo)throws IdeamException;
    /**
     * Retorna el listado de todas las divipolas cargadas en el sistema.
     * @return
     * @throws IdeamException
     */
    public List<Divipola> getAllDivipola()throws IdeamException;
    
    /**
     * Retorna la divipola correspondiente al codigo recibido
     * @param codigo
     * @return
     * @throws IdeamException
     */
    public Divipola getDivipolaById(Long codigo)throws IdeamException;
    /**
     * Retorna la lista correspondiente al codigo recibido como parametro
     * @param codigo
     * @return
     * @throws IdeamException
     */
    public Lista getLista(Integer codigo)throws IdeamException;
    /**
     * Retorna la categoria, con todas las listas asociadas a esta, correspondiente
     * al codigo recibido como parametro
     * @param codigo
     * @return
     * @throws IdeamException
     */
    public Categoria getCategoria(Long codigo)throws IdeamException;
    /**
     * Retorna una lista con todos los municipios o departamentros asoicados a 
     * una autoridad ambiental
     * @param codigo
     * @param autoridad
     * @return
     * @throws IdeamException
     */
    public List<Divipola> getDivipola(Long codigo, Autoridades autoridad)throws IdeamException;
    /**
     * Retorna una lista con los registros Divipola que son hojis del codigoi
     * recibido como parametro.
     * @param codigo Null si se desean todos los registros de primer nivel
     * @return
     * @throws IdeamException
     */
    public List<Divipola> getDivipola(Long codigo)throws IdeamException;    
    /**
     * Retorna el tipo de parametro correspondiente al codigo recibido. 
     * Internamente contiene una lista con todos los paramnetros asociados al
     * mismo
     * @param codigo
     * @return
     * @throws IdeamException
     */
    public TipoParametro getTipoParametro(Long codigo)throws IdeamException;
     
    /**
     * Retorna la autoridad correspodiente al codigo recibido como parametro
     * @param codigo
     * @return
     * @throws IdeamException
     */
    public Autoridades getAutoridad(Integer codigo)throws IdeamException;
    /**
     * retorna una lista con todas las autoridades registradas
     * @return
     * @throws IdeamException
     */
    public List getAllAutoridades()throws IdeamException;
    public List getAllAutoridadesNodos()throws IdeamException;
    /**
     * Retorna una lista con los registros de zonificacion (area, zona, subzona) que son hijos del codigo
     * recibido como parametro.
     * @param codigo Null si se desean todos los registros de primer nivel
     * @return
     * @throws IdeamException
     */
    public List<PartZonificListas> getZonificacion(Integer codigo)throws IdeamException;
    
  public List<Lista> getTipoFuentes(Long codigo)throws IdeamException;
    
    /**
     * Retorna una lista con los registros de zonificacion (area, zona, subzona) que son hijos del codigo
     * recibido como parametro y parametrizados para una autoridad ambiental especifica.
     * @param codigo Null si se desean todos los registros de primer nivel
     * @return
     * @throws IdeamException
     */
    public List<PartZonificListas> getZonificacion(Integer codigo, Autoridades autoridad)throws IdeamException;
    
    /**
     * Retorna una lista con todos los registros de zonificacion (area, zona, subzona).
     * @return
     * @throws IdeamException
     */
    public List<PartZonificListas> getAllZonificacion()throws IdeamException;
    
    /**
     * Retorna una lista con todos los registros de zonificacion de una categoria especifica (area, zona o subzona).
     * @param categoria.  La categoria a consultar.
     * @return
     * @throws IdeamException
     */
    public List<PartZonificListas> getAllZonificacionByCategoria(Integer categoria)throws IdeamException;
    
    /**
     * Retorna la zonificacion.
     * @param id.  El codigo a consultar.
     * @return
     * @throws IdeamException
     */
    public PartZonificListas getZonificacionById(Integer id)throws IdeamException;
    
    /**
     * Retorna una lista con todos los registros de listas por categoria.
     * @return
     * @throws IdeamException
     */
    public List<Lista> getAllListaByCategoria(Long categoria)throws IdeamException;
    
    /**
     * Retorna una lista con todos los registros de listas.
     * @return
     * @throws IdeamException
     */
    public List<Lista> getAllLista()throws IdeamException;
    
    /**
     * Retorna una lista con todos los registros de oferta para la subzona en ENA.
     * @return
     * @throws IdeamException
     */
    public List<OfertaSubzonas> getAllOfertaEnaBySubzona(Integer codigoSubzona)throws IdeamException;
    
    /**
     * Retorna una lista con todos la relacion de estaciones por subzona.
     * @return
     * @throws IdeamException
     */
    public List<OfertaEstacSubzonas> getAllEstacionesBySubzona(Integer codigoSubzona,Integer codigoEstacion)throws IdeamException;
    
    
    public Parametro getParametro(Long id)throws IdeamException;
    
    public Autoridades getAutoridadSigla(String sigla) throws IdeamException ;
        
    
    
}
