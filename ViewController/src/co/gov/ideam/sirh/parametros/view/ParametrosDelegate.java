package co.gov.ideam.sirh.parametros.view;

import co.gov.ideam.sirh.parametros.business.ParametrosEJB;
import co.gov.ideam.sirh.parametros.model.Autoridades;
import co.gov.ideam.sirh.parametros.model.Categoria;
import co.gov.ideam.sirh.parametros.model.Divipola;
import co.gov.ideam.sirh.parametros.model.Lista;
import co.gov.ideam.sirh.parametros.model.OfertaEstacSubzonas;
import co.gov.ideam.sirh.parametros.model.OfertaSubzonas;
import co.gov.ideam.sirh.parametros.model.Parametro;
import co.gov.ideam.sirh.parametros.model.PartZonificListas;
import co.gov.ideam.sirh.parametros.model.TipoParametro;
import co.gov.ideam.sirh.seguridad.business.SeguridadEJB;
import co.gov.ideam.sirh.seguridad.view.SeguridadDelegate;
import co.gov.ideam.sirh.servlet.ServletLocator;
import co.gov.ideam.sirh.util.IdeamException;


import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 * Clase que permite conectar los metodos del EJB del modulo con los
 * Bean de la capa de presentacion
 */
public class ParametrosDelegate {
    /**
     * Referencia al EJB de Seguridad
     */
    private static ParametrosEJB parametrosService = null;    
    /**
    * Utilizada para implementar singleton
    */
    private static ParametrosDelegate instance;
    /**
     * Retorna una instancia a la clase
     * @return
     */
    public static ParametrosDelegate getInstance() throws IdeamException {
        if (instance==null){
            instance = new ParametrosDelegate();
        }
        return instance;
    }
    /**
     * Retorna la lista correspondiente a la categoria y descripcion 
     * recibidas por parametro
     * @param descripcion
     * @param categoria
     * @return
     * @throws IdeamException
     */
    public Lista getListaByDescripcion(String descripcion, Long categoria)throws IdeamException{
        return parametrosService.getListaByDescripcion(descripcion, categoria);
    }
    /**
     * Retorna una lista con todasd las actividades ciiu que tienen el nombre
     * recibido por parametro
     * @param nombre
     * @return
     * @throws IdeamException
     */    
    public List getCiiuByName(String nombre)throws IdeamException{
        return parametrosService.getCiiuByName(nombre);
    }

    public Autoridades getAutoridad(Integer id)throws IdeamException{
        return parametrosService.getAutoridad(id);
    }
    /**
     * Retorna una lista con todasd las actividades ciiu que tienen el codigo
     * recibido por parametro
     * @param codigo
     * @return
     * @throws IdeamException
     */
    public List getCiiuByCode(String codigo)throws IdeamException{
        return parametrosService.getCiiuByCode(codigo);
    }
    
    /**
     * Retorna la lista con todas las divipolas del sistema.
     * @return
     * @throws IdeamException
     */
    public List<Divipola> getAllDivipola()throws IdeamException{
        return parametrosService.getAllDivipola();
    }    
   
    /**
     * Retorna la divipola correspondiente al codigo recibido
     * @param codigo
     * @return
     * @throws IdeamException
     */
    public Divipola getDivipolaById(Long codigo)throws IdeamException{
        return parametrosService.getDivipolaById(codigo);
    }
    
    /**
     * Retorna la lista correspondiente al codigo recibido como parametro
     * @param codigo
     * @return
     * @throws IdeamException
     */
    public Lista getLista(Integer codigo)throws IdeamException{
        return parametrosService.getLista(codigo);
    }
    /**
     * Retorna la categoria, con todas las listas asociadas a esta, correspondiente
     * al codigo recibido como parametro
     * @param codigo
     * @return
     * @throws IdeamException
     */
    public Categoria getCategoria(Long codigo)throws IdeamException{
        return parametrosService.getCategoria(codigo);
    }
    public List<Divipola> getDivipola(Long codigo, Autoridades autoridad)throws IdeamException{
        return parametrosService.getDivipola(codigo, autoridad);
    }
    /**
     * Retorna una lista con los registros Divipola que son hojis del codigoi
     * recibido como parametro.
     * @param codigo Null si se desean todos los registros de primer nivel
     * @return
     * @throws IdeamException
     */
    public List<Divipola> getDivipola(Long codigo)throws IdeamException{
        return parametrosService.getDivipola(codigo);
    }
    /**
     * Retorna el tipo de parametro correpondiente al codigo recibido
     * @param codigo
     * @return
     * @throws IdeamException
     */
    public TipoParametro getTipoParametro(Long codigo)throws IdeamException{
        return parametrosService.getTipoParametro(codigo)    ;
    }
    /**
     * retorna una lista con todas las autoridades registradas
     * @return
     * @throws IdeamException
     */
    public List getAllAutoridades()throws IdeamException{
        return parametrosService.getAllAutoridades();
    }
    public List getAllAutoridadesNodos()throws IdeamException{
        return parametrosService.getAllAutoridadesNodos();
    }
    /**
    * Construcutor privado para implementar singleton
    */
    private ParametrosDelegate(){
        parametrosService = ServletLocator.getParametrosService();
    }
    /**
     * Retorna una lista con los registros de zonificacion (area, zona, subzona) que son hijos del codigo
     * recibido como parametro.
     * @param codigo Null si se desean todos los registros de primer nivel
     * @return
     * @throws IdeamException
     */
    public List<PartZonificListas> getZonificacion(Integer codigo)throws IdeamException{
        return parametrosService.getZonificacion(codigo);
    }
    
  public List<Lista> getTipoFuentes(Long codigo)throws IdeamException{
      return parametrosService.getAllListaByCategoria(codigo);
  }
    
    /**
     * Retorna una lista con los registros de zonificacion (area, zona, subzona) que son hijos del codigo
     * recibido como parametro y parametrizados para una autoridad ambiental especifica.
     * @param codigo Null si se desean todos los registros de primer nivel
     * @return
     * @throws IdeamException
     */
    public List<PartZonificListas> getZonificacion(Integer codigo, Autoridades autoridad)throws IdeamException{
        return parametrosService.getZonificacion(codigo, autoridad);
    }
    /**
     * Retorna una lista con todos los registros de listas por categoria.
     * @return
     * @throws IdeamException
     */
    public List<Lista> getAllListaByCategoria(Long categoria)throws IdeamException{
        return parametrosService.getAllListaByCategoria(categoria);
    }
    
    /**
     * Retorna una lista con todos los registros de listas.
     * @return
     * @throws IdeamException
     */
    public List<Lista> getAllLista()throws IdeamException{
        return parametrosService.getAllLista();
    }
    
    /**
     * Retorna una lista con todos los registros de oferta para la subzona en ENA.
     * @return
     * @throws IdeamException
     */
    public List<OfertaSubzonas> getAllOfertaEnaBySubzona(Integer codigoSubzona)throws IdeamException{
        return parametrosService.getAllOfertaEnaBySubzona(codigoSubzona);
    }
    
    /**
     * Retorna una lista con todos la relacion de estaciones por subzona.
     * @return
     * @throws IdeamException
     */
    public List<OfertaEstacSubzonas> getAllEstacionesBySubzona(Integer codigoSubzona,Integer codigoEstacion)throws IdeamException{
        return parametrosService.getAllEstacionesBySubzona(codigoSubzona,codigoEstacion);
    }
    
    public Parametro getParametro(Long id)throws IdeamException{
        return parametrosService.getParametro(id);
    }
    
    public Autoridades getAutoridadSigla(String sigla) throws IdeamException {
      return parametrosService.getAutoridadSigla(sigla);
    }

    
}
