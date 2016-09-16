package co.gov.ideam.sirh.calidad.business;

import co.gov.ideam.sirh.calidad.model.CriteriosBusquedaMuestrasTO;
import co.gov.ideam.sirh.calidad.model.CriteriosBusquedaPuntos;
import co.gov.ideam.sirh.calidad.model.DatosReporteCalidadPOJO;
import co.gov.ideam.sirh.calidad.model.DatosReporteCalidadParametrosPuntoPOJO;
import co.gov.ideam.sirh.calidad.model.Medicion;
import co.gov.ideam.sirh.calidad.model.MedicionPOJO;
import co.gov.ideam.sirh.calidad.model.Muestra;
import co.gov.ideam.sirh.calidad.model.MuestraTO;
import co.gov.ideam.sirh.calidad.model.MuestrasIca;
import co.gov.ideam.sirh.calidad.model.MuestrasPOJO;
import co.gov.ideam.sirh.calidad.model.NormaLimites;
import co.gov.ideam.sirh.calidad.model.NormaUsos;
import co.gov.ideam.sirh.calidad.model.PuntoMonitoreo;
import co.gov.ideam.sirh.calidad.model.PuntoMonitoreoPOJO;
import co.gov.ideam.sirh.calidad.model.PuntoMonitoreoTO;
import co.gov.ideam.sirh.fuentes.model.CriteriosBusquedaTO;
import co.gov.ideam.sirh.fuentes.model.FnttFuente;
import co.gov.ideam.sirh.fuentes.model.FnttTramo;
import co.gov.ideam.sirh.parametros.model.Autoridades;
import co.gov.ideam.sirh.parametros.model.Lista;
import co.gov.ideam.sirh.red.ideam.model.DatosReporteIcaCroosTabIdeam;
import co.gov.ideam.sirh.usuarios.agua.model.Captacion;
import co.gov.ideam.sirh.usuarios.agua.model.PuntoVertimiento;
import co.gov.ideam.sirh.usuarios.agua.model.ws.entidades.DatosReporteWS;
import co.gov.ideam.sirh.util.IdeamException;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface CalidadEJB {
/**
     * Realiza una actualizacion masiva de todas las muestras del Ideam para las
     * cuales no se ha calculado el valor del ICA.
     * @throws IdeamException
     */
    // public void actualizarIcaIdeamMasivo()throws IdeamException;
    /**
     * Realiza el calculo de ICA para la muestra correspondiente al codigo
     * recibido como parametro.
     * @param codigoMuestra
     * @return
     * @throws IdeamException
     */
    public boolean calcularIcaMuestraIdeam ( Long codigoMuestra) throws IdeamException;
    /**
     * Retorna los parametros de ICA calculados para la muestra recibida como
     * parametro.
    
     * @return
     * @throws IdeamException
     */
     public List getAniosFuenteCalidad(Long idFuente, Integer idAutoridad) throws IdeamException ;
    
    public MuestrasIca getVariablesIcaMuestraIdeam(Long codigoMuestra)throws IdeamException;
    
    public List<DatosReporteCalidadPOJO> getNroMuestrasPorAnioFuenteDatos(CriteriosBusquedaMuestrasTO criterios,Integer idAutoridad )throws IdeamException;  

    /**
     * Retorna una lista de puntos de monitoreo
     * @return
     * @throws IdeamException
     */
    public List<PuntoMonitoreoTO>  getAllPuntosMonitoreo()throws IdeamException;    
    
    public List<NormaUsos> getNormaUsos()throws  IdeamException;
    
    public NormaLimites updateNormaLimites(NormaLimites lim)throws IdeamException;   
    
    public List getDatosCroosTab(String sql)throws IdeamException;
    
    public List<DatosReporteIcaCroosTabIdeam> getDatosCroosTabIdeam(Long idPuntoSeleccionado, List listaParametros)throws IdeamException;
    
    public Boolean actualizarIcaIdeamMasivo()throws IdeamException;
    
  /**
   * Retorna una lista con los puntos de monitoreo correspondientes a la autoridad
   * recibida como parametro
   * @param codigoAutoridad
   * @return
   * @throws IdeamException
   */
  public List<PuntoMonitoreoTO>  getAllPuntosMonitoreo(Long codigoAutoridad)throws  IdeamException;
    
  /**
   * Retorna una lista con todos los puntos de monitoreo que cumplen con los
   * criterios de busqueda relacionados
   * @param criterios

   * @return
   * @throws IdeamException
   */    
  public List<PuntoMonitoreoTO> getPuntosMonitoreo(CriteriosBusquedaPuntos criterios)throws IdeamException;
  
  /**
   * Retorna una lista con todos los puntos de monitoreo que cumplen con los
   * criterios de busqueda relacionados
   * @param criterios
   * @param codigoAutoridad
   * @return
   * @throws IdeamException
   */    
  public List getPuntosMonitoreo(CriteriosBusquedaPuntos criterios, Long codigoAutoridad)throws  IdeamException;   
       
  public PuntoMonitoreo getPuntosMonitoreoNomAut(String nombrePunto,Long codigoAutoridad)throws  IdeamException;
     
  
  /**
   * Inserta o actualiza la informacion de un punto de monitoreo en el sistema
   * @param 
 
   */
  public PuntoMonitoreo updatePuntoMonitoreo(PuntoMonitoreo puntoM)throws IdeamException;
  
  public void updatePuntoMonitoreoPlano(PuntoMonitoreoPOJO puntom)throws IdeamException;   
  
      
    /**
     * Elimina un punto de monitoreo existente en la bd. 
    
     */
    public void deletePuntoMonitoreo(PuntoMonitoreo puntoMonitoreo)throws IdeamException;
    public void deletePuntoMonitoreoPlano(PuntoMonitoreoPOJO puntom)throws IdeamException; 
    
  /**
   * Retorna el punto de monitoreo correspondiente al codigo recibido como parametro
   * @param codigo
   * @return
   * @throws IdeamException
   */
  public PuntoMonitoreo getPuntoMonitoreo(Long codigo)throws IdeamException;
  
  
     
    /**
     * Retorna el punto de monitoreo existente en base de datos
     * @param nombre, fuente y tramo
     * @return
     * @throws IdeamException
     */
    
    public PuntoMonitoreo getPuntoMonitoreoExiste(String nombre, Long idFuente,Long  idTramo)throws IdeamException;
     
     
     
    /**
     * Retorna una lista todas las muestras 
     * @return
     * @throws IdeamException
     */
    public List<MuestraTO> getAllMuestras()throws IdeamException;    
    
    
    /**
    * Retorna una lista con las muestras  correspondientes a la autoridad
    * recibida como parametro
    * @param codigoAutoridad
    * @return
    * @throws IdeamException
    */
    public List<MuestraTO>  getAllMuestras(Long codigoAutoridad)throws  IdeamException;
     
    /**
     * Retorna una lista con toda las muestras que cumplen con los
     * criterios de busqueda relacionados
     * @param criterios
     * @param 
     * @return
     * @throws IdeamException
     */    
    public List getMuestras(CriteriosBusquedaPuntos criterios)throws IdeamException;
    
    /**
     * Retorna una lista con toda las muestras que cumplen con los
     * criterios de busqueda relacionados
     * @param criterios
     * @param codigoAutoridad
     * @return
     * @throws IdeamException
     */    
    public List getMuestras(CriteriosBusquedaPuntos criterios, Long codigoAutoridad)throws  IdeamException;   
    
    /**
     * Retorna una lista con toda las muestras que cumplen con los
     * criterios de busqueda relacionados
     * @param criterios
     * @param idPunto
     * @return
     * @throws IdeamException
     */    
    public List getMuestrasPunto(CriteriosBusquedaPuntos criterios, Long idPunto)throws  IdeamException;   
    
    /**
     * Retorna las muestras de un punto de monitoreo 
    
     * @return
     * @throws IdeamException
     */    
     public List<Muestra> getMuestras(PuntoMonitoreo pm)throws IdeamException;
    
    /**
     * Inserta o actualiza la informacion de las muestras de un punto de monitoreo en el sistema
     * @param 
   
     */
    
      public Muestra updateMuestra(Muestra mm)throws IdeamException ;
    public void updateMuestraPlano(MuestrasPOJO mm)throws IdeamException;
   
  
     public Muestra getMuestraExiste(Long idPunto, Integer tipoMuestra, Integer hora, Integer min, String horario, Integer laboratorio, String fecha)throws IdeamException;
  
  
    /**
     * Retorna la muestra correspondiente al codigo recibido como parametro
     * @param id
     * @return
     * @throws IdeamException
     */
    public Muestra getMuestra(Long id)throws IdeamException;
    
    
    
    /**
     * Inserta o actualiza la informacion de las mediciones de las muestras de un punto de monitoreo en el sistema
     * @param 
   
     */
     public void updateMedicion(Medicion med)throws IdeamException;
    
    public void updateMedicionPlano(MedicionPOJO medic)throws IdeamException;
    
    
    /**
     * Retorna las mediciones de una muestra de un punto de monitoreo 
   
     * @return
     * @throws IdeamException
     */ 
    
    public List<Medicion> getMediciones(Muestra mm)throws IdeamException;
    
    
    /**
     * Retorna los  puntos de monitoreo  con muestras y mediciones 
     * @param 
     * @return
     * @throws IdeamException
     */ 
    
    public List<PuntoMonitoreo> getListaPuntosMuestra()throws IdeamException;
    public List<PuntoMonitoreo> getListaPuntosMuestra(Long idAutoridad)throws IdeamException;
    
    /**
     * Retorna la medicion correspondiente al codigo recibido como parametro
     * @param id
     * @return
     * @throws IdeamException
     */
    public Medicion getMedicion(Long id)throws IdeamException;

    
    
    
    /**
     * Elimina la muestra y sus mediciones  existente en la bd. 
   
     */
     public void deleteMuestra(Muestra muestra)throws IdeamException;
     public void deleteMuestraPlano(MuestrasPOJO medm)throws IdeamException;
    
     
     
    /**
     * Elimina un parametro de las  mediciones de una muestra existente en la bd. 
    
     */
     public void deleteMedicion(Medicion med)throws IdeamException;
    public void deleteMedicionPlano(MedicionPOJO medm)throws IdeamException; 
    
    /**
     * Retorna los puntos de monitoreo asociados a un tramo  
   
     * @return
     * @throws IdeamException
     */ 
    
    public List<PuntoMonitoreo> getPuntosMonitoreo(FnttTramo tramo)throws IdeamException;
      
    /**
    * Listado de puntos de monitoreo por tramos y criterios de busqueda
    * @param criterios y idtramo
   
    */
    public List getPuntosMonitoreoTramo(CriteriosBusquedaPuntos criterios, Long codigoTramo)throws  IdeamException;
    
    
    /**
     * Retorna los puntos de monitoreo asociados a un vertimiento  
   
     * @return
     * @throws IdeamException
     */ 
    
    public List<PuntoMonitoreo> getPuntosMonitoreoVert(PuntoVertimiento vert)throws IdeamException;
      
   
    
    /**
    * Listado de puntos de monitoreo por vertimientos y criterios de busqueda
    * @param criterios y idtramo
   
    */
    public List getPuntosMonitoreoVert(CriteriosBusquedaPuntos criterios, Long codigoVert)throws  IdeamException;
    
    /**
         * Retorna los puntos de monitoreo asociados a una captacion  
         * @param captacion
         * @return
         * @throws IdeamException
         */ 
        
        public List<PuntoMonitoreo> getPuntosMonitoreoCaptacion(Captacion captacion)throws IdeamException;
    
    public List getMuestrasVista(CriteriosBusquedaPuntos criterios, Long codigoAutoridad)throws  IdeamException;
    
    public List getMuestrasVista(CriteriosBusquedaPuntos criterios)throws  IdeamException;
     
    public PuntoMonitoreo loadLista(Integer idUbicacion)throws IdeamException;   
    
    public Muestra loadMuestra(Integer idtipoMuestra)throws IdeamException;
    
    public Medicion getMedicionExiste(Long idMuestra, Integer parametro, Integer unidad, Integer signo, Double valor)throws IdeamException;
    
  /**
   * ejecuta un qquery nativo y retorna el resultado
   * @throws IdeamException
   */
  public List<Object[]> ejecutaQueryNativo(String sql) throws IdeamException ;
    
    /**
     * Retorna los puntos de monitoreo con mediciones de parametros asociadas
     * @param 
     * @return
     * @throws IdeamException
     */ 
    
    public List<PuntoMonitoreo> getPuntosMonitoreoMediciones(Long idAutoridad)throws IdeamException;
    
    /**
     * retorna una lista con todas las autoridades asociadas al menos a una muestra
     * @return
     * @throws IdeamException
     */
    public List<Autoridades> getAutoridadesConMuestras()throws IdeamException;

    public List<Lista> getParametrosxPuntoM(Long idPunto)throws IdeamException;
    
    public List<Lista> getParametrosPuntoMCalidad(Integer idAutoridad, Long idPunto)throws IdeamException;
    
    public List<Lista> getParametrosxFuente(Long idFuente)throws IdeamException;
    
    public List<Lista> getParametrosFuenteCalidad(Integer idAutoridad, Long idFuente)throws IdeamException;
    
    public List<Object[]> getPararametroFechaPuntoM(Long idPunto, Long idParametro) throws IdeamException;
    
    public List getListaPararametroFechaPuntoM(Long idPunto, Long idParametro) throws IdeamException;
     
    public List<Lista>  getUnidadPararametro(Long idParametro,Long idAutoridad) throws IdeamException;
        
    public List<Object[]> getPararametroPromedioAnual(Long idfuente, Long idParametro, Long anio) throws IdeamException;
   
    public List getListaPararametroPromedioAnual(Long idfuente, Long idParametro, Long anio,Long anio2,Long anio3) throws IdeamException;
    
    public List<FnttFuente> getFuentesMuestras(Long idAutoridad)throws IdeamException;
    
    public List<FnttFuente> getListaFuentesTipo(Long idAutoridad)throws IdeamException;
    
    public List<FnttFuente> getFuentesCriterios(CriteriosBusquedaTO criterios)throws IdeamException;
    
    public List<PuntoMonitoreo> getPuntosMonitoreoCriterios(CriteriosBusquedaTO criterios)throws IdeamException;   
    
    public List<Lista> getListaPorCategoria(Long idcategoria)throws IdeamException;
    
    public List getAnioMuestras(Long idfuente)throws IdeamException;
    
    public List getAnioParametros(Long idparam)throws IdeamException;
    
    public List getAnioParametrosFuente(Long idparam, Long idFuente)throws IdeamException;
    
    public List getAniosFuenteParametrosCalidad(Long idparam, Long idFuente, Integer IdAutoridad)throws IdeamException;
    
    public String[][] getMuestrasFuente(Long idfuente, Integer anio,Long idAutoridad) throws IdeamException;
    
    public String[][] getCantidadTipoMuestras(Long idAutoridad) throws IdeamException;
    
    public String[][] getCantidadTipoMuestrasSubt(Long idAutoridad) throws IdeamException;
    
    public String[][] getCantidadMuestrasAnio(Long idAutoridad) throws IdeamException;
    
    public String[][] getCantidadMuestrasAnioSubt(Long idAutoridad) throws IdeamException;
    
    public String[][] getCantidadTipoMuestrasIdeam() throws IdeamException ;
    
    public String[][] getCantidadMuestrasAnioIdeam()  throws IdeamException ;
   
    public String[][] getCantidadTipoMuestrasCriterios(CriteriosBusquedaMuestrasTO criterios)throws IdeamException;
    
    public String[][] getCantidadMuestrasAnioCriterios(CriteriosBusquedaMuestrasTO criterios)throws IdeamException;
    
    public String[][] getCantidadTipoMuestrasCriteriosIdeam(CriteriosBusquedaMuestrasTO criterios)throws IdeamException;
    
    public String[][] getCantidadMuestrasAnioCriteriosIdeam(CriteriosBusquedaMuestrasTO criterios)throws IdeamException;
        
    public String[][] getNroMuestrasPorAnioFuente(CriteriosBusquedaMuestrasTO criterios, Integer IdAutoridad)throws IdeamException;
    
    public List<Object[]> getPararametroEnPuntoMonitoreo(CriteriosBusquedaMuestrasTO criterios, Integer IdAutoridad) throws IdeamException;
    
    public List<DatosReporteCalidadParametrosPuntoPOJO> getParametroEnPuntoMonitoreoDatos(CriteriosBusquedaMuestrasTO criterios, Integer IdAutoridad) throws IdeamException ;    
        
    public List<Object[]> getParametroPuntoFuente(CriteriosBusquedaMuestrasTO criterios, Integer IdAutoridad, Integer anio) throws IdeamException;        

    public List<DatosReporteCalidadParametrosPuntoPOJO> getParametroPuntoFuenteDatos(CriteriosBusquedaMuestrasTO criterios, Integer IdAutoridad) throws IdeamException ;           
        
    public NormaUsos updateNormaUsos(NormaUsos nn)throws IdeamException; 
    
    public NormaUsos getNormaUsosId(Long id)throws  IdeamException;
   
    public List<NormaUsos> getNormaUsosXAutoridad(Long idAutoridad) throws IdeamException;
 
    public NormaLimites getNormaLimitesId(Long id)throws  IdeamException;
    
    public void deleteNormaLimites(NormaLimites nl)throws IdeamException;
       
    public List<PuntoMonitoreo> getListaMedicionsPorParametro(Long idparametro, Long idAutoridad)throws IdeamException;
    
    public List<PuntoMonitoreo> getListaPuntosMedicionsPorParametro(Long idparametro, Long idAutoridad)throws IdeamException;
    
    public List<NormaLimites> getNormaLimites( Integer idNorma, Integer idUso, Integer idRiesgo)throws  IdeamException;
       
    public void deleteNormaUsos(NormaUsos nn)throws IdeamException;
    
    public List<Object[]> getListaLimitesParametro(Integer idParametro, 
                                         Integer idAutoridad) throws IdeamException;
    
    //  * Consultar capatacion por fuente  en cada autoridad ambiental 
    public List<DatosReporteWS> consultarDatosCalidad(String codAutoridadAmbiental, String tipo) throws IdeamException;
  
    
}
