package co.gov.ideam.sirh.calidad.view;

import co.gov.ideam.sirh.calidad.business.CalidadEJB;
import co.gov.ideam.sirh.calidad.business.ReportesEJB;
//import co.gov.ideam.sirh.calidad.model.AcuiferosPOJO;
import co.gov.ideam.sirh.calidad.model.AcuiferosPOJO2;
import co.gov.ideam.sirh.calidad.model.CriteriosBusquedaMuestrasTO;
import co.gov.ideam.sirh.parametros.business.ParametrosEJB;
import co.gov.ideam.sirh.parametros.view.ParametrosDelegate;
import co.gov.ideam.sirh.servlet.ServletLocator;
import co.gov.ideam.sirh.calidad.model.CriteriosBusquedaPuntos;
import co.gov.ideam.sirh.calidad.model.CriteriosBusquedaPuntosTO;
import co.gov.ideam.sirh.calidad.model.DatosGrafico;
import co.gov.ideam.sirh.calidad.model.DatosReporteCalidadPOJO;
import co.gov.ideam.sirh.calidad.model.DatosReporteCalidadParametrosPuntoPOJO;
import co.gov.ideam.sirh.calidad.model.JSCalidadParametrosPOJO;
import co.gov.ideam.sirh.calidad.model.JSDemandaPOJO;
import co.gov.ideam.sirh.calidad.model.JSMonitoreoPiezometricoPOJO;
import co.gov.ideam.sirh.calidad.model.JSPomcaPOJO;
import co.gov.ideam.sirh.calidad.model.JSPueaPOJO;
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
import co.gov.ideam.sirh.usuarios.agua.model.CriteriosBusquedaExternasJSTO;
import co.gov.ideam.sirh.usuarios.agua.model.Predio;
import co.gov.ideam.sirh.usuarios.agua.model.PuntoVertimiento;
import co.gov.ideam.sirh.usuarios.agua.model.Representante;
import co.gov.ideam.sirh.usuarios.agua.model.UsuarioAgua;
import co.gov.ideam.sirh.util.IdeamException;

import java.util.Calendar;
import java.util.List;

public class JSReportesDelegate {

    private static JSReportesDelegate instance;
    private static ReportesEJB reportesService = null;   
    
    
    public static JSReportesDelegate getInstance() throws IdeamException {
        if (instance==null){
            instance = new JSReportesDelegate();
        }
        return instance;
    }
    
    
    private JSReportesDelegate(){
        reportesService = ServletLocator.getReportesService();
    }
    
  public List<JSDemandaPOJO> getComponenteDemanda() throws IdeamException{
      return reportesService.getComponenteDemanda();        
  } 
  public List<JSMonitoreoPiezometricoPOJO> getMonitoreoPiezometrico() throws IdeamException{
      return reportesService.getMonitoreoPiezometrico();        
  } 
  
  public List<JSMonitoreoPiezometricoPOJO> getMonitoreoPiezometricoFiltros(CriteriosBusquedaExternasJSTO criterios) throws IdeamException{
   
      return reportesService.getMonitoreoPiezometricoFiltros(criterios);        
  }
  public List<AcuiferosPOJO2> getAcuiferosFiltros(CriteriosBusquedaExternasJSTO criterios) throws IdeamException{
   
      return reportesService.getAcuiferosFiltros(criterios);        
  }
  
  
  public List<JSDemandaPOJO> getComponenteDemanda3() throws IdeamException{
      return reportesService.getComponenteDemanda3();        
  }
public List<JSDemandaPOJO> getComponenteDemanda3Filtros(CriteriosBusquedaExternasJSTO criterios) throws IdeamException{
      return reportesService.getComponenteDemanda3Filtos(criterios);        
  }
  public List<JSDemandaPOJO> getComponenteDemandaXProvincia() throws IdeamException{
        return reportesService.getComponenteDemandaXProvincia();
    }
    
    public List<JSCalidadParametrosPOJO> getComponenteCalidadXParametros() throws IdeamException{
        return reportesService.getComponenteCalidadXParametros();
    }
    
  public List<JSCalidadParametrosPOJO> getComponenteCalidadXParametrosFiltros(CriteriosBusquedaExternasJSTO criterios) throws IdeamException{
       return reportesService.getComponenteCalidadXParametrosFiltros(criterios);
   }
  
  public List<AcuiferosPOJO2> getConsultaAcuiferos() throws IdeamException{
      return reportesService.getConsultaAcuiferos();
  }
  
    public List<JSPomcaPOJO> getConsultaPomca() throws IdeamException{
        return reportesService.getConsultaPomca();  
  }
    
  public List<JSPomcaPOJO> getConsultaPomcaArea(String area) throws IdeamException{
      return reportesService.getConsultaPomcaArea(area);  
  }
  
  public List<JSPueaPOJO> getConsultaPueaEst() throws IdeamException{
      return reportesService.getConsultaPueaEst();  
  }
  
  public List<JSPueaPOJO> getConsultaPueaEstCriterios(CriteriosBusquedaExternasJSTO criterios) throws IdeamException{
      return reportesService.getConsultaPueaEstCriterios(criterios);  
  }
  
  public List<JSPueaPOJO> getConsultaPueaSeg() throws IdeamException{
      return reportesService.getConsultaPueaSeg();  
  }
  
  public List<JSPueaPOJO> getConsultaPueaSegCriterios(CriteriosBusquedaExternasJSTO criterios) throws IdeamException{
      return reportesService.getConsultaPueaSegCriterios(criterios);  
  }
}//Fin clase */
