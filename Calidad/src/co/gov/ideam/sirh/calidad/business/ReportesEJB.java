package co.gov.ideam.sirh.calidad.business;


import co.gov.ideam.sirh.calidad.model.AcuiferosPOJO2;
import co.gov.ideam.sirh.calidad.model.CriteriosBusquedaMuestrasTO;
import co.gov.ideam.sirh.calidad.model.JSCalidadParametrosPOJO;
import co.gov.ideam.sirh.calidad.model.JSDemandaPOJO;
import co.gov.ideam.sirh.calidad.model.JSMonitoreoPiezometricoPOJO;
import co.gov.ideam.sirh.calidad.model.JSPomcaPOJO;
import co.gov.ideam.sirh.calidad.model.JSPueaPOJO;
import co.gov.ideam.sirh.usuarios.agua.model.CriteriosBusquedaExternasJSTO;
import co.gov.ideam.sirh.util.IdeamException;

import java.util.Calendar;
import java.util.List;

import javax.ejb.Remote;

@Remote
public interface ReportesEJB {
  public List<JSDemandaPOJO> getComponenteDemanda() throws IdeamException;

  public List<JSDemandaPOJO> getComponenteDemanda3() throws IdeamException;
  
  public List<JSDemandaPOJO> getComponenteDemanda3Filtos(CriteriosBusquedaExternasJSTO criterios) throws IdeamException;

  public List<JSMonitoreoPiezometricoPOJO> getMonitoreoPiezometrico() throws IdeamException;
  
  public List<JSMonitoreoPiezometricoPOJO> getMonitoreoPiezometricoFiltros(CriteriosBusquedaExternasJSTO criterios) throws IdeamException;

  public List<AcuiferosPOJO2>  getAcuiferosFiltros(CriteriosBusquedaExternasJSTO criterios) throws IdeamException;

  public List<JSDemandaPOJO> getComponenteDemandaXProvincia() throws IdeamException;

  public List<JSCalidadParametrosPOJO> getComponenteCalidadXParametros() throws IdeamException;
  
  public List<JSCalidadParametrosPOJO> getComponenteCalidadXParametrosFiltros(CriteriosBusquedaExternasJSTO criterios) throws IdeamException;

  public List<AcuiferosPOJO2> getConsultaAcuiferos() throws IdeamException;

  public List<JSPomcaPOJO> getConsultaPomca() throws IdeamException;

  public List<JSPomcaPOJO> getConsultaPomcaArea(String area) throws IdeamException;
  
  public List<JSPueaPOJO> getConsultaPueaEst() throws IdeamException;
  
  public List<JSPueaPOJO> getConsultaPueaEstCriterios(CriteriosBusquedaExternasJSTO criterios) throws IdeamException;
  
  public List<JSPueaPOJO> getConsultaPueaSeg() throws IdeamException;
  
  public List<JSPueaPOJO> getConsultaPueaSegCriterios(CriteriosBusquedaExternasJSTO criterios) throws IdeamException;
}

