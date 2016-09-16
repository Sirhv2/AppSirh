package co.gov.ideam.sirh.calidad.view;

import co.gov.ideam.sirh.calidad.model.CriteriosBusquedaMuestrasTO;
import co.gov.ideam.sirh.calidad.model.CriteriosBusquedaPuntosTO;
import co.gov.ideam.sirh.calidad.model.PuntoMonitoreo;
import co.gov.ideam.sirh.fuentes.model.CriteriosBusquedaSubtTO;
import co.gov.ideam.sirh.fuentes.model.CriteriosBusquedaTO;
import co.gov.ideam.sirh.fuentes.model.FnttFuente;
import co.gov.ideam.sirh.parametros.model.Autoridades;
import co.gov.ideam.sirh.parametros.model.Divipola;
import co.gov.ideam.sirh.parametros.model.Lista;
import co.gov.ideam.sirh.parametros.model.Parametro;
import co.gov.ideam.sirh.parametros.model.PartZonificListas;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.view.StandarDashBoard;

import java.math.BigDecimal;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.faces.component.UISelectItems;

import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.layout.RichPanelBox;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;

import oracle.adf.view.rich.component.rich.output.RichSpacer;
import oracle.adf.view.rich.context.AdfFacesContext;


public class FiltrosCalidadSubt extends StandarDashBoard{
    private RichPanelBox panelFiltros;
    private UISelectItems selectItems6;
    private RichSelectOneChoice departamentos;
    private UISelectItems selectItems4;   
    private RichSelectOneChoice area;
    private UISelectItems selectItems1;
    private RichSelectOneChoice municipios;
    private UISelectItems selectItems5;
    private RichInputDate fechaFinal;
    private RichSelectOneChoice zona;
    private UISelectItems selectItems2;
    private RichSelectOneChoice fuentes;
    private UISelectItems selectItems7;
    private RichSelectOneChoice subZona;
    private UISelectItems selectItems3;
    private RichSelectOneChoice ptoMonitoreo;
    private UISelectItems selectItems9;
    //zsdg ini
    private RichSelectOneChoice tipoFuentes;
    private UISelectItems selectItems10; 
    //fin zsdg

    //new filtros para consultas Feb 2015
        private Autoridades autoridadSelect;
        private List listaDepartamentos;
        private List listaMunicipios;
        private List listaFuentes;
        private List listaPuntosMonitoreo;
        private List listaParametros;
        private List listaAnios;
        private List listaTipoFuente;
        private Boolean verListaParametros;   

        private Boolean disabledListaPuntosMonitoreo;
        private Boolean obligatorioFuente;
        private Boolean obligatorioParametro;
        private Boolean obligatorioPuntoMonitoreo;
        
        private Boolean obligatorioAnio;
        private Boolean verListaAnios;         
        private Integer IdAutoridadFuentePunto = 0;
        private CriteriosBusquedaTO criteriosFuentesPuntos = new CriteriosBusquedaTO();
        
    private RichSelectOneChoice  parametro;
    private UISelectItems selectItems8;
   
    private RichSelectOneChoice anio;
    private UISelectItems selectItems11;
    private RichSpacer fc_spacer1;


    public FiltrosCalidadSubt() {      

        try {
    //carga las listas inicales para la consulta
            this.autoridadSelect = null;          
            this.setListaTipoFuente(this.cargarTipoFuentes(Long.valueOf(19)));
            this.setListaDepartamentos(this.cargarDivipolaSinRestriccion(null));
            } catch (IdeamException e) {
                showMessage(e);
            }
        
        this.listaMunicipios = new ArrayList();     
        this.listaParametros = new ArrayList();
    } 
    

    public void autoridades_valueChangeListener(ValueChangeEvent valueChangeEvent) throws IdeamException {
        Object autoridades = valueChangeEvent.getNewValue();
        
        this.autoridadSelect = (Autoridades) autoridades;
        this.listaDepartamentos = new ArrayList();     
        this.listaMunicipios = new ArrayList(); 
        this.listaFuentes = new ArrayList();
        this.listaPuntosMonitoreo = new ArrayList();
        this.listaParametros = new ArrayList();
        departamentos.setValue(null);
        municipios.setValue(null);
        fuentes.setValue(null);
        ptoMonitoreo.setValue(null);
        parametro.setValue(null);
       
        
        
        try {
            if (autoridades != null) {      
                criteriosFuentesPuntos = new CriteriosBusquedaTO();
                this.setIdAutoridadFuentePunto(autoridadSelect.getId());                
                this.listaDepartamentos = this.getDivipola(null, autoridadSelect); 
                this.listaFuentes = this.getListaFuentesCriterios(criteriosFuentesPuntos); 
                this.listaPuntosMonitoreo = getlistaPuntosMonitoreoCriterios(criteriosFuentesPuntos);                
            }else {        
                criteriosFuentesPuntos.setAutoridad(null);
                this.setListaDepartamentos(this.cargarDivipolaSinRestriccion(null));                                                        
            } 
        } catch (IdeamException e) {
            showMessage(e);
        }
        AdfFacesContext.getCurrentInstance().addPartialTarget(area);
        AdfFacesContext.getCurrentInstance().addPartialTarget(zona);
        AdfFacesContext.getCurrentInstance().addPartialTarget(subZona);
        AdfFacesContext.getCurrentInstance().addPartialTarget(departamentos);
        AdfFacesContext.getCurrentInstance().addPartialTarget(fuentes);
        AdfFacesContext.getCurrentInstance().addPartialTarget(ptoMonitoreo);
        AdfFacesContext.getCurrentInstance().addPartialTarget(parametro);
     
      
   
    }
    



    

    
    public void departamentos_valueChangeListener(ValueChangeEvent valueChangeEvent) {
        Object departamento = valueChangeEvent.getNewValue();
        this.setListaMunicipios(new ArrayList());
        CriteriosBusquedaTO criterios = new CriteriosBusquedaTO();
        
      
        try {
            if (departamento != null) {    
                criteriosFuentesPuntos.setDepartamento((Divipola)this.departamentos.getValue());
                this.listaMunicipios = this.cargarDivipolaSinRestriccion(((Divipola)departamento).getId());                    
            }else {
                criteriosFuentesPuntos.setDepartamento(null);
                this.setListaDepartamentos(this.cargarDivipolaSinRestriccion(null));               
             } 
            this.listaFuentes = this.getListaFuentesCriterios(criteriosFuentesPuntos);     
            this.listaPuntosMonitoreo = getlistaPuntosMonitoreoCriterios(criteriosFuentesPuntos);  
        } catch (IdeamException e) {
            showMessage(e);
        }
        AdfFacesContext.getCurrentInstance().addPartialTarget(departamentos);
        AdfFacesContext.getCurrentInstance().addPartialTarget(municipios);
        AdfFacesContext.getCurrentInstance().addPartialTarget(fuentes);
        AdfFacesContext.getCurrentInstance().addPartialTarget(ptoMonitoreo);
    }

    public void municipios_valueChangeListener(ValueChangeEvent valueChangeEvent) {
        Object municipio = valueChangeEvent.getNewValue();
        CriteriosBusquedaTO criterios = new CriteriosBusquedaTO();
         
        try {
            if (municipio != null) {    
                criteriosFuentesPuntos.setMunicipio((Divipola)this.municipios.getValue());                   
            }else {
                criteriosFuentesPuntos.setMunicipio(null);            
             } 
            this.listaFuentes = this.getListaFuentesCriterios(criteriosFuentesPuntos);     
            this.listaPuntosMonitoreo = getlistaPuntosMonitoreoCriterios(criteriosFuentesPuntos);  
        } catch (IdeamException e) {
            showMessage(e);
        }
        AdfFacesContext.getCurrentInstance().addPartialTarget(municipios);
        AdfFacesContext.getCurrentInstance().addPartialTarget(fuentes);
        AdfFacesContext.getCurrentInstance().addPartialTarget(ptoMonitoreo);
    }
   
    public void fuente_valueChangeListener(ValueChangeEvent valueChangeEvent) {
        Object fuente = valueChangeEvent.getNewValue();
        this.listaPuntosMonitoreo = new ArrayList();
        this.listaParametros = new ArrayList();
        try{              
            if(fuente!=null){
               criteriosFuentesPuntos.setFuente((FnttFuente)this.fuentes.getValue());
               if(criteriosFuentesPuntos.getAutoridad()!=null){ 
                    this.listaParametros = this.getListaParametrosFuenteCalidad(autoridadSelect.getId(),((FnttFuente)fuente).getId());                      
               }else {
                    this.setIdAutoridadFuentePunto(((FnttFuente)fuente).getCodigoAutoridad().intValue()); 
                    this.listaParametros = this.getListaParametrosFuenteCalidad(this.getIdAutoridadFuentePunto(),((FnttFuente)fuente).getId());     
                   }    
               }else {
                   criteriosFuentesPuntos.setFuente(null);
                    this.listaParametros = new ArrayList();
                    ptoMonitoreo.setValue(null);
                    parametro.setValue(null);              
                } 
               this.listaPuntosMonitoreo = getlistaPuntosMonitoreoCriterios(criteriosFuentesPuntos);   
        }catch(IdeamException e){            
            showMessage(e);
        }
        AdfFacesContext.getCurrentInstance().addPartialTarget(ptoMonitoreo);
        AdfFacesContext.getCurrentInstance().addPartialTarget(parametro);
    }


    public void ptoMonitoreo_valueChangeListener(ValueChangeEvent valueChangeEvent) {
        Object puntoM = valueChangeEvent.getNewValue();
        try{
            if(puntoM!=null){
                if(autoridadSelect!=null){
                this.listaParametros = new ArrayList();
                this.listaParametros = this.getListaParametrosPuntoCalidad(autoridadSelect.getId(),((PuntoMonitoreo)puntoM).getId());
                } else{
                    this.setIdAutoridadFuentePunto(((PuntoMonitoreo)puntoM).getCodigoAutoridad().intValue());
                    this.listaParametros = this.getListaParametrosPuntoCalidad(this.getIdAutoridadFuentePunto(),((PuntoMonitoreo)puntoM).getId());  
                }   
           }
        }catch(IdeamException e){            
            showMessage(e);
        }
        AdfFacesContext.getCurrentInstance().addPartialTarget(parametro);
    }
    
    public void parametro_valueChangeListener(ValueChangeEvent valueChangeEvent) {
        Object parametro = valueChangeEvent.getNewValue();
        this.listaAnios = new ArrayList();        
        
        try{
           if(criteriosFuentesPuntos.getFuente()!=null){            
               if(parametro!=null){
                    if(autoridadSelect!=null){
                    this.listaAnios = 
                    getListaAniosFuenteParametrosCalidad(((Lista)parametro).getCodigo().longValue(),((FnttFuente)this.fuentes.getValue()).getId(),autoridadSelect.getId());               
                    } else{                  
                        this.listaAnios = 
                        getListaAniosFuenteParametrosCalidad(((Lista)parametro).getCodigo().longValue(),((FnttFuente)this.fuentes.getValue()).getId(),this.getIdAutoridadFuentePunto().intValue());   
                     }                    
                    }                       
           }
           else{
               showMessage("Fuente NULL ");
           }
        }catch(IdeamException e){            
            showMessage(e);
        }
        AdfFacesContext.getCurrentInstance().addPartialTarget(anio);
    }
    
    

    public void limpiarSeleccion() {
        this.listaDepartamentos = new ArrayList();
        this.listaMunicipios = new ArrayList();
        this.listaFuentes = new ArrayList();
        this.listaPuntosMonitoreo = new ArrayList();  
        this.listaParametros = new ArrayList();
        this.listaAnios = new ArrayList();               
        this.criteriosFuentesPuntos = new CriteriosBusquedaTO();
            
        if(departamentos!=null) departamentos.setValue(null);
        if(municipios!=null) municipios.setValue(null);
        if(fuentes!=null) fuentes.setValue(null);
        if(ptoMonitoreo!=null) ptoMonitoreo.setValue(null);
        if(parametro!=null) parametro.setValue(null);
      
      
        if(anio!=null) anio.setValue(null);
        
        if(departamentos!=null) AdfFacesContext.getCurrentInstance().addPartialTarget(departamentos);
        if(municipios!=null) AdfFacesContext.getCurrentInstance().addPartialTarget(municipios);
        if(fuentes!=null) AdfFacesContext.getCurrentInstance().addPartialTarget(fuentes);
        if(ptoMonitoreo!=null) AdfFacesContext.getCurrentInstance().addPartialTarget(ptoMonitoreo);
        if(parametro!=null) AdfFacesContext.getCurrentInstance().addPartialTarget(parametro);
       
       
        if(anio!=null) AdfFacesContext.getCurrentInstance().addPartialTarget(anio);  
    }

    public CriteriosBusquedaSubtTO getCriterios(){
        boolean existenDatos = false;
        CriteriosBusquedaSubtTO criterios = new CriteriosBusquedaSubtTO();
        
        if(departamentos.getValue()!=null){
            criterios.setDepartamento((Divipola)departamentos.getValue());           
            existenDatos = true;
        }
        
        if(municipios.getValue()!=null){
            criterios.setMunicipio((Divipola)municipios.getValue());           
            existenDatos = true;
        }
        
        if(fuentes.getValue()!=null) {
            criterios.setFuente((FnttFuente) fuentes.getValue());
            existenDatos = true;
        }      
        

        
        if(parametro.getValue()!=null){
            criterios.setUnidadhidro(parametro.getValue().toString());
            existenDatos = true;
        }
        
        if(tipoFuentes.getValue() != null){
          criterios.setTipoFuente((Lista) tipoFuentes.getValue());
          }
        if (existenDatos){
            return criterios;
        }else{
            return null;
        }
    }
    
    
    public void setPanelFiltros(RichPanelBox panelFiltros) {
        this.panelFiltros = panelFiltros;
    }

    public RichPanelBox getPanelFiltros() {
        return panelFiltros;
    }

   



    public void setSelectItems6(UISelectItems selectItems6) {
        this.selectItems6 = selectItems6;
    }

    public UISelectItems getSelectItems6() {
        return selectItems6;
    }

    public void setDepartamentos(RichSelectOneChoice departamentos) {
        this.departamentos = departamentos;
    }

    public RichSelectOneChoice getDepartamentos() {
        return departamentos;
    }

    public void setSelectItems4(UISelectItems selectItems4) {
        this.selectItems4 = selectItems4;
    }

    public UISelectItems getSelectItems4() {
        return selectItems4;
    }

    public void setSelectItems1(UISelectItems selectItems1) {
        this.selectItems1 = selectItems1;
    }

    public UISelectItems getSelectItems1() {
        return selectItems1;
    }

    public void setMunicipios(RichSelectOneChoice municipios) {
        this.municipios = municipios;
    }

    public RichSelectOneChoice getMunicipios() {
        return municipios;
    }

    public void setSelectItems5(UISelectItems selectItems5) {
        this.selectItems5 = selectItems5;
    }

    public UISelectItems getSelectItems5() {
        return selectItems5;
    }

    public void setSelectItems2(UISelectItems selectItems2) {
        this.selectItems2 = selectItems2;
    }

    public UISelectItems getSelectItems2() {
        return selectItems2;
    }

    public void setFuentes(RichSelectOneChoice fuentes) {
        this.fuentes = fuentes;
    }

    public RichSelectOneChoice getFuentes() {
        return fuentes;
    }

    public void setSelectItems7(UISelectItems selectItems7) {
        this.selectItems7 = selectItems7;
    }

    public UISelectItems getSelectItems7() {
        return selectItems7;
    }

    public void setSelectItems3(UISelectItems selectItems3) {
        this.selectItems3 = selectItems3;
    }

    public UISelectItems getSelectItems3() {
        return selectItems3;
    }


    public void setSelectItems9(UISelectItems selectItems9) {
        this.selectItems9 = selectItems9;
    }

    public UISelectItems getSelectItems9() {
        return selectItems9;
    }
    
    public void setParametro(RichSelectOneChoice parametro) {
       this.parametro = parametro;
    }

    public RichSelectOneChoice getParametro() {
       return parametro;
    }
    
    public void setSelectItems8(UISelectItems selectItems8) {
        this.selectItems8 = selectItems8;
    }

    public UISelectItems getSelectItems8() {
        return selectItems8;
    }



    public Autoridades getAutoridadSelect() {
        return autoridadSelect;
    }

    public void setAutoridadSelect(Autoridades autoridadSelect) {
        this.autoridadSelect = autoridadSelect;
    }

    public List getListaDepartamentos() {
        return listaDepartamentos;
    }

    public void setListaDepartamentos(List listaDepartamentos) {
        this.listaDepartamentos = listaDepartamentos;
    }

    public List getListaMunicipios() {
        return listaMunicipios;
    }

    public void setListaMunicipios(List listaMunicipios) {
        this.listaMunicipios = listaMunicipios;
    }

    public List getListaFuentes() {
        return listaFuentes;
    }

    public void setListaFuentes(List listaFuentes) {
        this.listaFuentes = listaFuentes;
    }

    public List getListaPuntosMonitoreo() {
        return listaPuntosMonitoreo;
    }

    public void setListaPuntosMonitoreo(List listaPuntosMonitoreo) {
        this.listaPuntosMonitoreo = listaPuntosMonitoreo;
    }

    public List getListaParametros() {
        return listaParametros;
    }

    public void setListaParametros(List listaParametros) {
        this.listaParametros = listaParametros;
    }
    
    


    public Boolean getObligatorioFuente() {
        return obligatorioFuente;
    }

    public void setObligatorioFuente(Boolean ObligatorioFuente) {   
        if (this.fuentes!=null){
            this.fuentes.setShowRequired(ObligatorioFuente);
            //this.fuentes.setRequired(ObligatorioFuente); 
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.fuentes);
        }
    }
          
          
    public Boolean getVerListaParametros() {
        return verListaParametros;
    }
    
    public void setVerListaParametros(Boolean verListaParametros) {   
        if(parametro!= null){
            parametro.setValue(null);
            this.parametro.setVisible(verListaParametros);                
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.parametro);
        }
    } 
    
     public Boolean getObligatorioParametro() {
        return obligatorioParametro;
    }    
     
    public void setObligatorioParametro(Boolean obligatorioParametro) {           
        if(this.parametro!=null){
            this.parametro.setShowRequired(obligatorioParametro);
//            this.parametro.setRequired(obligatorioParametro);                
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.parametro);
        }
    }     
    
    public Boolean getDisabledListaPuntosMonitoreo(){
        return disabledListaPuntosMonitoreo;
    }
    
    public void setDisabledListaPuntosMonitoreo(Boolean verListaPuntos) {   
        if(ptoMonitoreo!=null){
            ptoMonitoreo.setValue(null);
            this.ptoMonitoreo.setDisabled(verListaPuntos);              
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.ptoMonitoreo);
        }
    } 
    
    public Boolean getObligatorioPuntoMonitoreo() {
        return obligatorioPuntoMonitoreo;
    }
        
    public void setObligatorioPuntoMonitoreo(Boolean ObligatorioPunto) {   
        if(ptoMonitoreo!=null){
            this.ptoMonitoreo.setShowRequired(ObligatorioPunto);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.ptoMonitoreo);
        }
    }
            

    

   
     public Boolean getObligatorioAnio() {
        return obligatorioAnio;
    }
      
    public void setObligatorioAnio(Boolean ObligatorioAnio) {   
        if(anio!= null){
            this.anio.setShowRequired(ObligatorioAnio);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.anio);
        }
    }  
    
    public Boolean getVerListaAnios() {
        return verListaAnios;
    }

    public void setVerListaAnios(Boolean verListaAnios) {
        if(anio!= null){
            anio.setValue(null);
            this.anio.setVisible(verListaAnios);                
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.anio);
        }    
    }   
    public void setNullListaAnios() {
        if(anio!= null){
            anio.setValue(null);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.anio);
        }    
    }
    
    public Integer getIdAutoridadFuentePunto() {
        return IdAutoridadFuentePunto;
    }

    public void setIdAutoridadFuentePunto(Integer IdAutoridadFuentePunto) {
        this.IdAutoridadFuentePunto = IdAutoridadFuentePunto;
    }

    public List getListaAnios() {
        return listaAnios;
    }

    public void setListaAnios(List listaAnios) {
        this.listaAnios = listaAnios;
    }

    public void setAnio(RichSelectOneChoice selectOneChoice1) {
        this.anio = selectOneChoice1;
    }

    public RichSelectOneChoice getAnio() {
        return anio;
    }
    
    public void setSelectItems10(UISelectItems selectItems10) {
        this.selectItems10 = selectItems10;
    }

    public UISelectItems getSelectItems10() {
        return selectItems10;
    }


  public void setListaTipoFuente(List listaTipoFuente) {
    this.listaTipoFuente = listaTipoFuente;
  }

  public List getListaTipoFuente() {
    return listaTipoFuente;
  }


  public void setTipoFuentes(RichSelectOneChoice tipoFuentes) {
    this.tipoFuentes = tipoFuentes;
  }

  public RichSelectOneChoice getTipoFuentes() {
    return tipoFuentes;
  }


  public void setSelectItems11(UISelectItems selectItems11) {
    this.selectItems11 = selectItems11;
  }

  public UISelectItems getSelectItems11() {
    return selectItems11;
  }
}
