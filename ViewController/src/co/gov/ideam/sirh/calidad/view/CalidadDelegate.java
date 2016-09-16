package co.gov.ideam.sirh.calidad.view;

import co.gov.ideam.sirh.calidad.business.CalidadEJB;
import co.gov.ideam.sirh.calidad.model.CriteriosBusquedaMuestrasTO;
import co.gov.ideam.sirh.parametros.business.ParametrosEJB;
import co.gov.ideam.sirh.parametros.view.ParametrosDelegate;
import co.gov.ideam.sirh.servlet.ServletLocator;
import co.gov.ideam.sirh.calidad.model.CriteriosBusquedaPuntos;
import co.gov.ideam.sirh.calidad.model.CriteriosBusquedaPuntosTO;
import co.gov.ideam.sirh.calidad.model.DatosGrafico;
import co.gov.ideam.sirh.calidad.model.DatosReporteCalidadPOJO;
import co.gov.ideam.sirh.calidad.model.DatosReporteCalidadParametrosPuntoPOJO;
import co.gov.ideam.sirh.calidad.model.Medicion;
import co.gov.ideam.sirh.calidad.model.Muestra;
import co.gov.ideam.sirh.calidad.model.MuestraTO;
import co.gov.ideam.sirh.calidad.model.MuestrasIca;
import co.gov.ideam.sirh.calidad.model.NormaLimites;
import co.gov.ideam.sirh.calidad.model.NormaUsos;
import co.gov.ideam.sirh.calidad.model.PuntoMonitoreo;
import co.gov.ideam.sirh.calidad.model.PuntoMonitoreoTO;
import co.gov.ideam.sirh.fuentes.model.CriteriosBusquedaTO;
import co.gov.ideam.sirh.fuentes.model.FnttFuente;
import co.gov.ideam.sirh.fuentes.model.FnttTramo;
import co.gov.ideam.sirh.parametros.model.Autoridades;
import co.gov.ideam.sirh.parametros.model.Categoria;
import co.gov.ideam.sirh.parametros.model.Lista;
import co.gov.ideam.sirh.red.ideam.model.DatosReporteIcaCroosTabIdeam;
import co.gov.ideam.sirh.red.ideam.model.SirhvPuntoMonitoreoFq;
import co.gov.ideam.sirh.usuarios.agua.model.Captacion;
import co.gov.ideam.sirh.usuarios.agua.model.Predio;
import co.gov.ideam.sirh.usuarios.agua.model.PuntoVertimiento;
import co.gov.ideam.sirh.usuarios.agua.model.Representante;
import co.gov.ideam.sirh.usuarios.agua.model.UsuarioAgua;
import co.gov.ideam.sirh.util.IdeamException;

import java.util.Calendar;
import java.util.List;

public class CalidadDelegate {

    private static CalidadDelegate instance;
    private static CalidadEJB calidadService = null;   
    
    public static CalidadDelegate getInstance() throws IdeamException {
        if (instance==null){
            instance = new CalidadDelegate();
        }
        return instance;
    }
    
    
    /**
    * Construcutor privado para implementar singleton
    */
    private CalidadDelegate(){
        calidadService = ServletLocator.getCalidadService();
    }
/**
     * Retorna los parametros de ICA calculados para la muestra recibida como
     * parametro.
     * @param codigoMuestra
     * @return
     * @throws IdeamException
     */
    public MuestrasIca getMuestraIcaIdeam(Long codigoMuestra)throws IdeamException{
        return calidadService.getVariablesIcaMuestraIdeam(codigoMuestra);
    }

   public Boolean  actualizarIcaIdeamMasivo()throws IdeamException{
    return calidadService.actualizarIcaIdeamMasivo();
   }
   
  /**
   * Retorna una lista de puntos de monitoreo
   * @return
   * @throws IdeamException
   */
  public List<PuntoMonitoreoTO>  getAllPuntosMonitoreo()throws IdeamException{
      return calidadService.getAllPuntosMonitoreo();
  }
  public List getAniosFuenteCalidad( Long idFuente, Integer idAutoridad) throws IdeamException {
      return calidadService.getAniosFuenteCalidad( idFuente, idAutoridad);
  }
  
  
  
    public PuntoMonitoreo getPuntosMonitoreoNomAut(String nombrePunto,Long codigoAutoridad)throws  IdeamException{
        return calidadService.getPuntosMonitoreoNomAut(nombrePunto, codigoAutoridad);
    }
  /**
   * Retorna una lista con los usuarios del agua correspondientes a la autoridad
   * recibida como parametro
   * @param codigoAutoridad
   * @return
   * @throws IdeamException
   */
  public List<PuntoMonitoreoTO>  getAllPuntosMonitoreo(Long codigoAutoridad)throws IdeamException{
      return calidadService.getAllPuntosMonitoreo(codigoAutoridad);
  }
  
  /**
   * Retorna una lista con todos los usuarios del agua que cumplen con los
   * criterios de busqueda relacionados
   * @param criterios
   * @param codigoAutoridad
   * @return
   * @throws IdeamException
   */    
    public List getPuntosMonitoreo(CriteriosBusquedaPuntos criterios)throws  IdeamException{
      return calidadService.getPuntosMonitoreo(criterios);
  }
  /**
   * Retorna una lista con todos los usuarios del agua que cumplen con los
   * criterios de busqueda relacionados
   * @param criterios
   * @param codigoAutoridad
   * @return
   * @throws IdeamException
   */    
    public List getPuntosMonitoreo(CriteriosBusquedaPuntos criterios,  Long codigoAutoridad)throws  IdeamException{
      return calidadService.getPuntosMonitoreo(criterios,codigoAutoridad);
  }
  

  /**
   * Inserta o actualiza la informacion de un punto de monitoreo en el sistema
   * @param 
   * @throws FenixException
   */
  public PuntoMonitoreo updatePuntoMonitoreo(PuntoMonitoreo puntoMonitoreo)throws IdeamException{
    return calidadService.updatePuntoMonitoreo(puntoMonitoreo);
  }
  

  /**
   * Retorna el punto de monitoreo correspondiente al codigo recibido como parametro
   * @param codigo
   * @return
   * @throws IdeamException
   */
  public PuntoMonitoreo getPuntoMonitoreo(Long codigo)throws IdeamException{
      return calidadService.getPuntoMonitoreo(codigo);

  }
  
    
    /**
     * Retorna el punto de monitoreo existente en base de datos
     * @param nombre, fuente y tramo
     * @return
     * @throws IdeamException
     */
     public PuntoMonitoreo getPuntoMonitoreoExiste(String nombre, Long idFuente, Long idTramo)throws IdeamException{
         return calidadService.getPuntoMonitoreoExiste(nombre, idFuente, idTramo);

     }
    
    /**
     * Elimina un punto de monitoreo existente en la bd. 
     * @param puntomonitoreo
     * @throws FenixException
     */
    public void deletePuntoMonitoreo(PuntoMonitoreo puntoMonitoreo)throws IdeamException{
        calidadService.deletePuntoMonitoreo(puntoMonitoreo);
    }
    
   

    /**
     * Retorna una lista de muestras
     * @return
     * @throws IdeamException
     */
    public List<MuestraTO> getAllMuestras()throws IdeamException{
        return calidadService.getAllMuestras();
    }
    
    public Muestra getMuestraExiste(Long idPunto, Integer tipoMuestra, Integer hora, Integer min, String horario, Integer laboratorio, String fecha)throws IdeamException{
            return calidadService.getMuestraExiste(idPunto,  tipoMuestra,  hora,  min,  horario,  laboratorio,  fecha);
        }
    
    public Medicion getMedicionExiste(Long idMuestra, Integer parametro, Integer unidad, Integer signo, Double valor)throws IdeamException{
       return calidadService.getMedicionExiste( idMuestra,  parametro,  unidad,  signo,  valor);
    }
    
   /**
    * ejecuta un qquery nativo y retorna el resultado
    * @param QUERY
    * @throws IdeamException
    */
  public List<Object[]> ejecutaQueryNativo(String sql) throws IdeamException {
    return calidadService.ejecutaQueryNativo(sql);
  }
    public NormaUsos updateNormaUsos(NormaUsos nn)throws IdeamException{
       return calidadService.updateNormaUsos(nn);
    }
    
    public NormaUsos getNormaUsosId(Long id)throws  IdeamException{
        return calidadService.getNormaUsosId(id);
    }
    
    
    public NormaLimites getNormaLimitesId(Long id)throws  IdeamException{
        return calidadService.getNormaLimitesId(id);
    }
    
    
    
    public List<NormaUsos> getNormaUsos()throws  IdeamException{
     return calidadService.getNormaUsos();
    }
    
    public List<NormaUsos> getNormaUsosXAutoridad(Long idAutoridad) throws IdeamException{
        return calidadService.getNormaUsosXAutoridad(idAutoridad);
    }
    
    /**
     * Retorna una lista de muestras correspondientes a la autoridad
     * recibida como parametro
     * @param codigoAutoridad
     * @return
     * @throws IdeamException
     */
    public List <MuestraTO> getAllMuestras(Long codigoAutoridad)throws IdeamException{
        return calidadService.getAllMuestras(codigoAutoridad);
    }
    
    
    
    /**
     * Retorna una lista de las muestras que cumplen con los
     * criterios de busqueda relacionados
     * @param criterios
     * @return
     * @throws IdeamException
     */    
      public List getMuestras(CriteriosBusquedaPuntos criterios)throws  IdeamException{
        return calidadService.getMuestras(criterios);
    }

    /**
     * Retorna una lista de las muestras que cumplen con los
     * criterios de busqueda relacionado
     * @param criterios
     * @param codigoAutoridad
     * @return
     * @throws IdeamException
     */    
      public List getMuestras(CriteriosBusquedaPuntos criterios,  Long codigoAutoridad)throws  IdeamException{
        return calidadService.getMuestras(criterios,codigoAutoridad);
    }
    
    
    public List getMuestrasVista(CriteriosBusquedaPuntos criterios, Long codigoAutoridad)throws IdeamException{
       return calidadService.getMuestrasVista(criterios,codigoAutoridad);
    }
    
    public List getMuestrasVista(CriteriosBusquedaPuntos criterios)throws IdeamException{
       return calidadService.getMuestrasVista(criterios);
    }
    
    /**
     * Retorna una lista de las muestras que cumplen con los
     * criterios de busqueda relacionado
     * @param criterios
     * @param idPunto
     * @return
     * @throws IdeamException
     */    
      public List getMuestrasPunto(CriteriosBusquedaPuntos criterios,  Long idPunto)throws  IdeamException{
        return calidadService.getMuestrasPunto(criterios,idPunto);
    }
    
    /**
     * Retorna las muestras de un punto de monitoreo 
     * @param PuntoMonitoreo
     * @return
     * @throws IdeamException
     */    
 
    public  List<Muestra> getMuestras(PuntoMonitoreo pm)throws  IdeamException{
      return calidadService.getMuestras(pm);
    } 
    
    
    
    /**
     * Inserta o actualiza la informacion de las muestras de un punto de monitoreo en el sistema
     * @param 
     * @throws IdeamxException
     */
    
    public Muestra updateMuestra(Muestra mm)throws IdeamException{
       return  calidadService.updateMuestra(mm);
    }



    /**
     * Retorna la muestra correspondiente al codigo recibido como parametro
     * @param id
     * @return
     * @throws IdeamException
     */
    public Muestra getMuestra(Long id)throws IdeamException{
        return calidadService.getMuestra(id);

    }


    /**
     * Inserta o actualiza la informacion de las mediciones de las muestras de un punto de monitoreo en el sistema
     * @param 
     * @throws IdeamxException
     */
    
    public void updateMedicion(Medicion med)throws IdeamException{   
        calidadService.updateMedicion(med);
        }   
    
    
    public NormaLimites updateNormaLimites(NormaLimites lim)throws IdeamException{   
      return  calidadService.updateNormaLimites(lim);
    }   
    
    
    /**
     * Retorna las mediciones de una muestra de un punto de monitoreo 
     * @param muestra
     * @return
     * @throws IdeamException
     */ 
    
    public List<Medicion> getMediciones(Muestra mm)throws IdeamException{
        return calidadService.getMediciones(mm);
    }
    
    
    
    /**
     * Retorna los  puntos de monitoreo  con muestras y mediciones 
     * @param 
     * @return
     * @throws IdeamException
     */ 
    
    public List<PuntoMonitoreo> getListaPuntosMuestra()throws IdeamException{
    return calidadService.getListaPuntosMuestra();
    }
    
    
    public List<PuntoMonitoreo> getListaPuntosMuestra(Long idAutoridad)throws IdeamException{
    return calidadService.getListaPuntosMuestra(idAutoridad);
    }
    /**
     * Retorna la medicion correspondiente al codigo recibido como parametro
     * @param id
     * @return
     * @throws IdeamException
     */
    public Medicion getMedicion(Long id)throws IdeamException{
        return calidadService.getMedicion(id);
    }


    /**
     * Elimina la muestra y sus mediciones  existente en la bd. 
     * @param Muestra
     * @throws FenixException
     */
    public void deleteMuestra(Muestra muestra)throws IdeamException{
        calidadService.deleteMuestra(muestra);
        }
    
    /**
     * Elimina un parametro de las  mediciones de una muestra existente en la bd. 
     * @param Medicion
     * @throws FenixException
     */
    public void deleteMedicion(Medicion med)throws IdeamException{
       calidadService.deleteMedicion(med);
    }
    
    
    /**
     * Retorna los puntos de monitoreo asociados a un tramo  
     * @param Tramo
     * @return
     * @throws IdeamException
     */ 
    
    public List<PuntoMonitoreo> getPuntosMonitoreo(FnttTramo tramo)throws IdeamException{
       return calidadService.getPuntosMonitoreo(tramo);
    }
    
    /**
    * Listado de puntos de monitoreo por tramos y criterios de busqueda
    * @param criterios y idtramo
    * @throws FenixException
    */
    public List getPuntosMonitoreoTramo(CriteriosBusquedaPuntos criterios, Long codigoTramo)throws  IdeamException{
    return calidadService.getPuntosMonitoreoTramo(criterios,codigoTramo);
    }
    
    
    /**
     * Retorna los puntos de monitoreo asociados a un vertimiento  
     * @param vertimiento
     * @return
     * @throws IdeamException
     */ 
    
    public List<PuntoMonitoreo> getPuntosMonitoreoVert(PuntoVertimiento vert)throws IdeamException{
       return calidadService.getPuntosMonitoreoVert(vert);
        
        }
     

    /**
    * Listado de puntos de monitoreo por vertimientos y criterios de busqueda
    * @param criterios y idtramo
    * @throws FenixException
    */
    public List getPuntosMonitoreoVert(CriteriosBusquedaPuntos criterios, Long codigoVert)throws  IdeamException{
    return calidadService.getPuntosMonitoreoVert(criterios,  codigoVert);
    
    }
    
    public PuntoMonitoreo loadLista(Integer idUbicacion)throws IdeamException{
            return calidadService.loadLista(idUbicacion);
        }
    
    public Muestra loadMuestra(Integer idtipoMuestra)throws IdeamException{
        return calidadService.loadMuestra(idtipoMuestra);
    }
    
    
    
    /**
     * Retorna los puntos de monitoreo con mediciones de parametros asociadas
     * @param 
     * @return
     * @throws IdeamException
     */ 
    
    public List<PuntoMonitoreo> getPuntosMonitoreoMediciones(Long idAutoridad)throws IdeamException{
    return calidadService.getPuntosMonitoreoMediciones(idAutoridad);
    
    }
 
    /**
     * retorna una lista con todas las autoridades asociadas al menos a una muestra
     * @return
     * @throws IdeamException
     */
    public List<Autoridades> getAutoridadesConMuestras()throws IdeamException{
        return calidadService.getAutoridadesConMuestras();
    } 
  
    public List<Lista> getParametrosxPuntoM(Long idPunto)throws IdeamException{
        return calidadService.getParametrosxPuntoM(idPunto);
    }
    
    public List<Lista> getParametrosPuntoMCalidad(Integer idAutoridad,Long idPunto)throws IdeamException{
        return calidadService.getParametrosPuntoMCalidad(idAutoridad,idPunto);
    }
    
    public List<Lista> getParametrosxFuente(Long idFuente)throws IdeamException{
        return calidadService.getParametrosxFuente(idFuente);
    }
  
    public List<Lista> getParametrosFuenteCalidad(Integer idAutoridad,Long idFuente)throws IdeamException{
        return calidadService.getParametrosFuenteCalidad(idAutoridad,idFuente);
    }
    
    public List<Object[]> getPararametroFechaPuntoM(Long idPunto, Long idParametro) throws IdeamException{
            return calidadService.getPararametroFechaPuntoM(idPunto,idParametro);   
        }
    

    public List getListaPararametroFechaPuntoM(Long idPunto, Long idParametro) throws IdeamException{
            return calidadService.getListaPararametroFechaPuntoM(idPunto,idParametro);   
        }
    
    public List<Lista>  getUnidadPararametro(Long idParametro, Long idAutoridad) throws IdeamException{
            return calidadService.getUnidadPararametro(idParametro, idAutoridad);   
        }
    
    public List<Object[]> getPararametroPromedioAnual(Long idfuente, Long idParametro, Long anio) throws IdeamException{
            return calidadService.getPararametroPromedioAnual(idfuente,idParametro,anio);   
        }
    
    public List  getListaPararametroPromedioAnual(Long idfuente, Long idParametro, Long anio ,Long anio2, Long anio3 ) throws IdeamException{
            return calidadService.getListaPararametroPromedioAnual(idfuente,idParametro,anio,anio2, anio3 );   
        }
    public List<PuntoMonitoreo>  getListaMedicionsPorParametro(Long idparametro, Long idAutoridad)throws IdeamException{
           return calidadService.getListaMedicionsPorParametro(idparametro,idAutoridad);
    }
    
    public List<PuntoMonitoreo> getListaPuntosMedicionsPorParametro(Long idparametro, Long idAutoridad)throws IdeamException{
    return calidadService.getListaPuntosMedicionsPorParametro(idparametro, idAutoridad);
    }
    
           
    public List<FnttFuente> getFuentesMuestras(Long idAutoridad)throws IdeamException{
       return calidadService.getFuentesMuestras(idAutoridad);
   }
        
    public List<FnttFuente> getListaFuentesTipo(Long idAutoridad)throws IdeamException{
       return calidadService.getListaFuentesTipo(idAutoridad);
    }    
    
    public List<FnttFuente> getFuentesCriterios(CriteriosBusquedaTO criterios)throws IdeamException {
      return calidadService.getFuentesCriterios(criterios);
    }
    
    public List<PuntoMonitoreo> getPuntosMonitoreoCriterios(CriteriosBusquedaTO criterios)throws IdeamException{
       return calidadService.getPuntosMonitoreoCriterios(criterios);
    }       
         
    public List<Lista> getListaPorCategoria(Long idcategoria)throws IdeamException{
       return calidadService.getListaPorCategoria(idcategoria);
    }
  
    public List getAnioMuestras(Long idfuente )throws IdeamException{
        return calidadService.getAnioMuestras(idfuente);
    }
        
    public List getAnioParametros(Long idparam )throws IdeamException{
        return calidadService.getAnioParametros(idparam);
    }
    public List getAnioParametrosFuente(Long idparam , Long idFuente)throws IdeamException{
        return calidadService.getAnioParametrosFuente(idparam,idFuente);
    }
    
    public List getAniosFuenteParametrosCalidad(Long idparam, Long idFuente, Integer IdAutoridad)throws IdeamException{
        return calidadService.getAniosFuenteParametrosCalidad(idparam,idFuente,IdAutoridad);
    }
    
    public String[][] getMuestrasFuente(Long idfuente, Integer anio, Long idAutoridad) throws IdeamException{
        return calidadService.getMuestrasFuente(idfuente, anio, idAutoridad);  
    }
    
    public String[][] getCantidadTipoMuestras(Long idAutoridad) throws IdeamException{
        return calidadService.getCantidadTipoMuestras( idAutoridad);   
    }
    
  public String[][] getCantidadTipoMuestrasSubt(Long idAutoridad) throws IdeamException{
      return calidadService.getCantidadTipoMuestrasSubt( idAutoridad);   
  }
    
    public String[][] getCantidadMuestrasAnio(Long idAutoridad)  throws IdeamException{
        return calidadService.getCantidadMuestrasAnio(idAutoridad);    
    }
    
  public String[][] getCantidadMuestrasAnioSubt(Long idAutoridad)  throws IdeamException{
      return calidadService.getCantidadMuestrasAnioSubt(idAutoridad);    
  }
    
    public String[][] getCantidadTipoMuestrasIdeam() throws IdeamException{
        return calidadService.getCantidadTipoMuestrasIdeam();   
    }
    
    public String[][] getCantidadMuestrasAnioIdeam()  throws IdeamException{
        return calidadService.getCantidadMuestrasAnioIdeam();    
    }  
    
    public String[][] getCantidadTipoMuestrasCriterios(CriteriosBusquedaMuestrasTO criterios)throws IdeamException{
        return calidadService.getCantidadTipoMuestrasCriterios(criterios);        
    }
    
    public String[][] getCantidadMuestrasAnioCriterios(CriteriosBusquedaMuestrasTO criterios)throws IdeamException{
        return calidadService.getCantidadMuestrasAnioCriterios(criterios);        
    }    
    
    public String[][] getCantidadTipoMuestrasCriteriosIdeam(CriteriosBusquedaMuestrasTO criterios)throws IdeamException{
        return calidadService.getCantidadTipoMuestrasCriteriosIdeam(criterios);        
    }
    
    public String[][] getCantidadMuestrasAnioCriteriosIdeam(CriteriosBusquedaMuestrasTO criterios)throws IdeamException{
        return calidadService.getCantidadMuestrasAnioCriteriosIdeam(criterios);        
    }  
        
    public String[][] getNroMuestrasPorAnioFuente(CriteriosBusquedaMuestrasTO criterios, Integer IdAutoridad)throws IdeamException{
        return calidadService.getNroMuestrasPorAnioFuente(criterios,IdAutoridad);        
    }    
    
    public List<DatosReporteCalidadPOJO> getNroMuestrasPorAnioFuenteDatos(CriteriosBusquedaMuestrasTO criterios, Integer idAutoridad ) throws  IdeamException{
        return calidadService.getNroMuestrasPorAnioFuenteDatos(criterios,idAutoridad);
    }
      
    public List<Object[]> getPararametroEnPuntoMonitoreo(CriteriosBusquedaMuestrasTO criterios, Integer IdAutoridad) throws IdeamException{
        return calidadService.getPararametroEnPuntoMonitoreo(criterios,IdAutoridad);        
    }    
    
    public List<DatosReporteCalidadParametrosPuntoPOJO> getParametroEnPuntoMonitoreoDatos(CriteriosBusquedaMuestrasTO criterios, Integer IdAutoridad) throws IdeamException {
        return calidadService.getParametroEnPuntoMonitoreoDatos(criterios,IdAutoridad);        
    }
           
    public List<Object[]> getParametroPuntoFuente(CriteriosBusquedaMuestrasTO criterios, Integer IdAutoridad, Integer anio) throws IdeamException{
        return calidadService.getParametroPuntoFuente(criterios,IdAutoridad, anio);        
    }    
    public List<DatosReporteCalidadParametrosPuntoPOJO> getParametroPuntoFuenteDatos(CriteriosBusquedaMuestrasTO criterios, Integer IdAutoridad) throws IdeamException {
        return calidadService.getParametroPuntoFuenteDatos(criterios,IdAutoridad);        
    }    
    public List getDatosCroosTab(String sql)throws IdeamException{
        return calidadService.getDatosCroosTab(sql);
    }    
    
    public List<DatosReporteIcaCroosTabIdeam> getDatosCroosTabIdeam(Long idPuntoSeleccionado, List listaParametros)throws IdeamException{
        return calidadService.getDatosCroosTabIdeam(idPuntoSeleccionado,listaParametros);
    }   
    
    /**
     * Retorna los puntos de monitoreo asociados a una captacion  
     * @param captacion
     * @return
     * @throws IdeamException
     */ 
    
    public List<PuntoMonitoreo> getPuntosMonitoreoCaptacion(Captacion captacion)throws IdeamException{
        return calidadService.getPuntosMonitoreoCaptacion(captacion);
    }
    
    
    public List<NormaLimites> getNormaLimites( Integer idNorma, Integer idUso,Integer idRiesgo)throws  IdeamException{
        return calidadService.getNormaLimites(idNorma,idUso, idRiesgo);  
    }
    
    public void deleteNormaUsos(NormaUsos nn)throws IdeamException{  
        calidadService.deleteNormaUsos(nn);
    }
    
    
  public void deleteNormaLimites(NormaLimites nl)throws IdeamException{     
        calidadService.deleteNormaLimites(nl);
    }

  public List<Object[]> getListaLimitesParametro(Integer idParametro, 
                                         Integer idAutoridad) throws IdeamException {
      return calidadService.getListaLimitesParametro(idParametro,idAutoridad);
  }

}//Fin clase
