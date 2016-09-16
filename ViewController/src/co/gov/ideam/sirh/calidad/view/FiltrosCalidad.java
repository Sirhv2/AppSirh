package co.gov.ideam.sirh.calidad.view;

import co.gov.ideam.sirh.calidad.model.CriteriosBusquedaMuestrasTO;
import co.gov.ideam.sirh.calidad.model.CriteriosBusquedaPuntosTO;
import co.gov.ideam.sirh.calidad.model.PuntoMonitoreo;
import co.gov.ideam.sirh.fuentes.model.CriteriosBusquedaTO;
import co.gov.ideam.sirh.fuentes.model.FnttFuente;
import co.gov.ideam.sirh.parametros.model.Autoridades;
import co.gov.ideam.sirh.parametros.model.Divipola;
import co.gov.ideam.sirh.parametros.model.Lista;
import co.gov.ideam.sirh.parametros.model.Parametro;
import co.gov.ideam.sirh.parametros.model.PartZonificListas;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.view.FacesUtils;
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


public class FiltrosCalidad extends StandarDashBoard{
    private RichPanelBox panelFiltros;
    private RichPanelFormLayout pnl_filtros;
    private RichSelectOneChoice autoridades;
    private UISelectItems selectItems6;
    private RichSelectOneChoice departamentos;
    private UISelectItems selectItems4;
    private RichInputDate fechaInicial;
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

    //new filtros para consultas Feb 2015
        private List listaAutoridades;
        private Autoridades autoridadSelect;       
        private List listaArea;
        private List listaZona;
        private List listaSubZona;
        private List listaDepartamentos;
        private List listaMunicipios;
        private List listaFuentes;
        private List listaPuntosMonitoreo;
        private List listaParametros;
        private List listaAnios;
        private Boolean verListaParametros;   

        private Boolean disabledListaPuntosMonitoreo;
        private Boolean obligatorioFuente;
        private Boolean obligatorioParametro;
        private Boolean obligatorioPuntoMonitoreo;
        private Boolean verFechaInicial;
        private Boolean verFechaFinal;    
        private Boolean obligatorioAnio;
        private Boolean verListaAnios;         
        private Integer IdAutoridadFuentePunto = 0;
        private CriteriosBusquedaTO criteriosFuentesPuntos = new CriteriosBusquedaTO();
        
    private RichSelectOneChoice parametro;
    private UISelectItems selectItems8;
    private RichPanelGroupLayout fc_panelGroupLayout1;
    private RichSpacer fc_spacer1;
    private RichSpacer spacerf1;
    private RichSelectOneChoice anio;
    private UISelectItems selectItems10;
    private RichSpacer spacer1;
    private RichSpacer spacerf2;
    private RichSpacer spacerf3;
    private RichSpacer spacer2;


    public FiltrosCalidad() {      

        try {
    //carga las listas inicales para la consulta
            this.autoridadSelect = null;
            this.setListaAutoridades(this.cargarListaAutoridadesConMuestras());            
            this.setListaArea(this.cargarZonificacion(null));                     
            this.setListaDepartamentos(this.cargarDivipolaSinRestriccion(null));
            } catch (IdeamException e) {
                showMessage(e);
            }
        
        this.listaZona = new ArrayList();
        this.listaSubZona = new ArrayList();
        this.listaMunicipios = new ArrayList();     
        this.listaParametros = new ArrayList();
    } 
    

    public void autoridades_valueChangeListener(ValueChangeEvent valueChangeEvent) throws IdeamException {
        Object autoridades = valueChangeEvent.getNewValue();
        
        this.autoridadSelect = (Autoridades) autoridades;
        this.setListaArea(this.cargarZonificacion(null));         
        this.listaZona = new ArrayList();
        this.listaSubZona = new ArrayList();
        this.listaDepartamentos = new ArrayList();     
        this.listaMunicipios = new ArrayList(); 
        this.listaFuentes = new ArrayList();
        this.listaPuntosMonitoreo = new ArrayList();
        this.listaParametros = new ArrayList();
        /*area.setValue(null);
        zona.setValue(null);
        subZona.setValue(null);*/
        departamentos.setValue(null);
        municipios.setValue(null);
        fuentes.setValue(null);
        ptoMonitoreo.setValue(null);
        if(parametro!=null)
            parametro.setValue(null);
        if(fechaInicial!=null)
            fechaInicial.setValue(null);
        if(fechaFinal!=null)
            fechaFinal.setValue(null); 
        
        try {
            if (autoridades != null) {      
                criteriosFuentesPuntos = new CriteriosBusquedaTO();
                criteriosFuentesPuntos.setAutoridad((Autoridades)this.autoridades.getValue());
                this.setIdAutoridadFuentePunto(autoridadSelect.getId());                
                /*this.listaArea = this.getListasZonificacion(null, autoridadSelect); */                 
                this.listaDepartamentos = this.getDivipola(null, autoridadSelect); 
                this.listaFuentes = this.getListaFuentesCriterios(criteriosFuentesPuntos); 
                //this.listaPuntosMonitoreo = getlistaPuntosMonitoreoCriterios(criteriosFuentesPuntos);                
            }else {        
                criteriosFuentesPuntos.setAutoridad(null);
                /*this.setListaArea(this.cargarZonificacion(null));  */ 
                this.setListaDepartamentos(this.cargarDivipolaSinRestriccion(null));                                                        
            } 
        } catch (IdeamException e) {
            showMessage(e);
        }
        /*AdfFacesContext.getCurrentInstance().addPartialTarget(area);
        AdfFacesContext.getCurrentInstance().addPartialTarget(zona);
        AdfFacesContext.getCurrentInstance().addPartialTarget(subZona);*/
        AdfFacesContext.getCurrentInstance().addPartialTarget(departamentos);
        AdfFacesContext.getCurrentInstance().addPartialTarget(fuentes);
        AdfFacesContext.getCurrentInstance().addPartialTarget(ptoMonitoreo);
        if(parametro!=null)
        AdfFacesContext.getCurrentInstance().addPartialTarget(parametro);
        if(fechaInicial!=null)
        AdfFacesContext.getCurrentInstance().addPartialTarget(fechaInicial);
        if(fechaFinal!=null)
        AdfFacesContext.getCurrentInstance().addPartialTarget(fechaFinal);
   
    }
    

    public void area_valueChangeListener(ValueChangeEvent valueChangeEvent) {
        Object area = valueChangeEvent.getNewValue();
        this.listaZona = new ArrayList();
        this.listaSubZona = new ArrayList();    
        this.listaFuentes = new ArrayList();
        this.listaPuntosMonitoreo = new ArrayList();
        this.listaParametros = new ArrayList();
        subZona.setValue(null);
        zona.setValue(null);
        subZona.setValue(null);
        fuentes.setValue(null);
        ptoMonitoreo.setValue(null);
        parametro.setValue(null);
        setVerListaParametros(false); //Pilar 30 Nov 2015
        AdfFacesContext.getCurrentInstance().addPartialTarget(zona);
        AdfFacesContext.getCurrentInstance().addPartialTarget(subZona);
        AdfFacesContext.getCurrentInstance().addPartialTarget(fuentes);
        AdfFacesContext.getCurrentInstance().addPartialTarget(ptoMonitoreo);
        if(parametro!=null)
        AdfFacesContext.getCurrentInstance().addPartialTarget(parametro);
        
        try {
            criteriosFuentesPuntos.setZona(null);        
            if (area != null) {               
                criteriosFuentesPuntos.setArea((PartZonificListas)this.area.getValue());             
                if(criteriosFuentesPuntos.getAutoridad()!=null){   
                     this.listaZona = this.getListasZonificacion(((PartZonificListas)area).getId(),autoridadSelect);                        
                } else {
                    this.listaZona = this.cargarZonificacion(((PartZonificListas)area).getId());                     
                }
         } else{
                criteriosFuentesPuntos.setArea(null);        
                this.setListaDepartamentos(this.cargarDivipolaSinRestriccion(null)); 
            }
            this.listaFuentes = this.getListaFuentesCriterios(criteriosFuentesPuntos); 
            this.listaPuntosMonitoreo = getlistaPuntosMonitoreoCriterios(criteriosFuentesPuntos);  
        } catch (IdeamException e) {
            showMessage(e);
        }
        AdfFacesContext.getCurrentInstance().addPartialTarget(zona);
        AdfFacesContext.getCurrentInstance().addPartialTarget(subZona);
        AdfFacesContext.getCurrentInstance().addPartialTarget(fuentes);
        AdfFacesContext.getCurrentInstance().addPartialTarget(ptoMonitoreo);
        if(parametro!=null)
        AdfFacesContext.getCurrentInstance().addPartialTarget(parametro);
    }


    public void zona_valueChangeListener(ValueChangeEvent valueChangeEvent) {
        Object zona = valueChangeEvent.getNewValue();
        this.listaSubZona = new ArrayList();
        this.listaFuentes = new ArrayList();
        this.listaPuntosMonitoreo = new ArrayList();
        this.listaParametros = new ArrayList();
        subZona.setValue(null);
        fuentes.setValue(null);
        ptoMonitoreo.setValue(null);
        parametro.setValue(null);
        AdfFacesContext.getCurrentInstance().addPartialTarget(subZona);
        AdfFacesContext.getCurrentInstance().addPartialTarget(fuentes);
        AdfFacesContext.getCurrentInstance().addPartialTarget(ptoMonitoreo);
        if(parametro!=null)
        AdfFacesContext.getCurrentInstance().addPartialTarget(parametro);        
        try { 
            criteriosFuentesPuntos.setSubzona(null);
            if (zona != null) {
                criteriosFuentesPuntos.setZona((PartZonificListas)this.zona.getValue());                             
                if(criteriosFuentesPuntos.getAutoridad()!=null){   
                    this.listaSubZona = this.getListasZonificacion(((PartZonificListas)zona).getId(),autoridadSelect);                    
                } else {
                    this.listaSubZona = this.cargarZonificacion(((PartZonificListas)zona).getId());                                        
                } 
            }
            else {
                criteriosFuentesPuntos.setZona(null);                
            }
            this.listaFuentes = this.getListaFuentesCriterios(criteriosFuentesPuntos);  
            this.listaPuntosMonitoreo = getlistaPuntosMonitoreoCriterios(criteriosFuentesPuntos);  
        } catch (IdeamException e) {
            showMessage(e);
        }
        AdfFacesContext.getCurrentInstance().addPartialTarget(subZona);
        AdfFacesContext.getCurrentInstance().addPartialTarget(fuentes);
        AdfFacesContext.getCurrentInstance().addPartialTarget(ptoMonitoreo);
        if(parametro!=null)
        AdfFacesContext.getCurrentInstance().addPartialTarget(parametro);
    }

    public void subZona_valueChangeListener(ValueChangeEvent valueChangeEvent) {
        Object subzona = valueChangeEvent.getNewValue();
        this.listaFuentes = new ArrayList();
        fuentes.setValue(null);
        
        try{
            if(subzona!=null){    
                criteriosFuentesPuntos.setSubzona((PartZonificListas)this.subZona.getValue());              
            }else {
                criteriosFuentesPuntos.setSubzona(null);                
             }
            this.listaFuentes = this.getListaFuentesCriterios(criteriosFuentesPuntos);  
            this.listaPuntosMonitoreo = getlistaPuntosMonitoreoCriterios(criteriosFuentesPuntos);  
        }catch(IdeamException e){            
            showMessage(e);
        }
        AdfFacesContext.getCurrentInstance().addPartialTarget(fuentes);    
        AdfFacesContext.getCurrentInstance().addPartialTarget(ptoMonitoreo);
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
        Integer origen = (Integer)FacesUtils.getFromSession("numOrigen");
        System.out.println("-------------------origen:"+origen+"");
        try{              
            if(fuente!=null){
               criteriosFuentesPuntos.setFuente((FnttFuente)this.fuentes.getValue());
              
               if(origen.intValue()==3){
                   if(criteriosFuentesPuntos.getAutoridad()!=null){ 
                        this.listaParametros = this.getListaParametrosFuenteCalidad(autoridadSelect.getId(),((FnttFuente)fuente).getId());                      
                   }else {
                        this.setIdAutoridadFuentePunto(((FnttFuente)fuente).getCodigoAutoridad().intValue()); 
                        this.listaParametros = this.getListaParametrosFuenteCalidad(this.getIdAutoridadFuentePunto(),((FnttFuente)fuente).getId());     
                       } 
               }
            }/*else {
                   criteriosFuentesPuntos.setFuente(null);
                    this.listaParametros = new ArrayList();
                    ptoMonitoreo.setValue(null);
                    if(parametro!=null)
                        parametro.setValue(null);              
             } */
               this.listaPuntosMonitoreo = getlistaPuntosMonitoreoCriterios(criteriosFuentesPuntos);   
        }catch(IdeamException e){            
            showMessage(e);
        }
        AdfFacesContext.getCurrentInstance().addPartialTarget(ptoMonitoreo);
        //if(parametro!=null)
        //AdfFacesContext.getCurrentInstance().addPartialTarget(parametro);
        
        //PILAR-->
        this.listaAnios = new ArrayList();        
        
        try{
           if(criteriosFuentesPuntos.getFuente()!=null){                           
                this.listaAnios = 
                getListaAniosFuenteCalidad(((FnttFuente)this.fuentes.getValue()).getId(),this.getIdAutoridadFuentePunto().intValue());           
           }
            AdfFacesContext.getCurrentInstance().addPartialTarget(anio);
        }catch(IdeamException e){            
            showMessage(e);
        }
             
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
            AdfFacesContext.getCurrentInstance().addPartialTarget(anio);
        }catch(IdeamException e){            
            showMessage(e);
        }
        
    }
    
    

    public void limpiarFiltros() {      
//        this.listaArea = new ArrayList();
        this.listaZona = new ArrayList();
        this.listaSubZona = new ArrayList();
 //       this.listaDepartamentos = new ArrayList();
        this.listaMunicipios = new ArrayList();
        this.listaFuentes = new ArrayList();
        this.listaPuntosMonitoreo = new ArrayList();  
        this.listaParametros = new ArrayList();
        this.listaAnios = new ArrayList();               
        this.criteriosFuentesPuntos = new CriteriosBusquedaTO();
            
        if(autoridades!=null) autoridades.setValue(null);
        if(area!=null) area.setValue(null);
        if(zona!=null) zona.setValue(null);
        if(subZona!=null) subZona.setValue(null);    
        if(departamentos!=null) departamentos.setValue(null);
        if(municipios!=null) municipios.setValue(null);
        if(fuentes!=null) fuentes.setValue(null);
        if(ptoMonitoreo!=null) ptoMonitoreo.setValue(null);
        if(parametro!=null) parametro.setValue(null);
        if(fechaInicial!=null) fechaInicial.setValue(null);
        if(fechaFinal!=null) fechaFinal.setValue(null);
        if(anio!=null) anio.setValue(null);
        try{
            this.setListaArea(this.cargarZonificacion(null));                     
            this.setListaDepartamentos(this.cargarDivipolaSinRestriccion(null));
        }catch(IdeamException e){            
            showMessage(e);
        }        
        if(autoridades!=null) AdfFacesContext.getCurrentInstance().addPartialTarget(autoridades);
        if(area!=null) AdfFacesContext.getCurrentInstance().addPartialTarget(area);
        if(zona!=null) AdfFacesContext.getCurrentInstance().addPartialTarget(zona);
        if(subZona!=null) AdfFacesContext.getCurrentInstance().addPartialTarget(subZona);
        if(departamentos!=null) AdfFacesContext.getCurrentInstance().addPartialTarget(departamentos);
        if(municipios!=null) AdfFacesContext.getCurrentInstance().addPartialTarget(municipios);
        if(fuentes!=null) AdfFacesContext.getCurrentInstance().addPartialTarget(fuentes);
        if(ptoMonitoreo!=null) AdfFacesContext.getCurrentInstance().addPartialTarget(ptoMonitoreo);
        if(parametro!=null) AdfFacesContext.getCurrentInstance().addPartialTarget(parametro);
        if(fechaInicial!=null) AdfFacesContext.getCurrentInstance().addPartialTarget(fechaInicial);
        if(fechaFinal!=null) AdfFacesContext.getCurrentInstance().addPartialTarget(fechaFinal);
        if(anio!=null) AdfFacesContext.getCurrentInstance().addPartialTarget(anio);  
    }

    public CriteriosBusquedaMuestrasTO getCriterios(){
        boolean existenDatos = false;
        CriteriosBusquedaMuestrasTO criterios = new CriteriosBusquedaMuestrasTO();
        
        if(autoridades.getValue()!=null){
            criterios.setAutoridad((Autoridades)autoridades.getValue());
            existenDatos = true;
        }
        /*
        if(area.getValue()!=null){
            criterios.setArea((PartZonificListas)area.getValue());
            existenDatos = true;
        }

        if(zona.getValue()!=null){
            criterios.setZona((PartZonificListas)zona.getValue());
            existenDatos = true;
        }
        
        if(subZona.getValue()!=null){
            criterios.setSubZona((PartZonificListas)subZona.getValue());           
            existenDatos = true;
        }*/
        
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
        
        if(ptoMonitoreo!=null&&ptoMonitoreo.getValue()!=null) {
            criterios.setPtoMonitoreo((PuntoMonitoreo) ptoMonitoreo.getValue()); ;
            existenDatos = true;
        }
        
        if(parametro!=null&&parametro.getValue()!=null){
            criterios.setParametro((Lista) parametro.getValue());
            existenDatos = true;
        }
                
        if(fechaInicial!=null&&fechaInicial.getValue()!=null ) {
           Calendar fecha = GregorianCalendar.getInstance();
           fecha.setTime((Date)fechaInicial.getValue());
           criterios.setFechaInicial(fecha);
            existenDatos = true;
        }

        if(fechaFinal!=null&&fechaFinal.getValue()!=null ) {
            Calendar fecha = GregorianCalendar.getInstance();
            fecha.setTime((Date)fechaFinal.getValue());           
            criterios.setFechaFinal(fecha);       
            existenDatos = true;
        }
        
        if(anio!=null&&anio.getValue()!=null){            
            criterios.setAnio(((BigDecimal) anio.getValue()).intValue());
            existenDatos = true;
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

    public void setPnl_filtros(RichPanelFormLayout pnl_filtros) {
        this.pnl_filtros = pnl_filtros;
    }

    public RichPanelFormLayout getPnl_filtros() {
        return pnl_filtros;
    }


    public void setAutoridades(RichSelectOneChoice autoridades) {
        this.autoridades = autoridades;
    }

    public RichSelectOneChoice getAutoridades() {
        return autoridades;
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

    public void setFechaInicial(RichInputDate fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public RichInputDate getFechaInicial() {
        return fechaInicial;
    }

    public void setArea(RichSelectOneChoice area) {
        this.area = area;
    }

    public RichSelectOneChoice getArea() {
        return area;
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

    public void setFechaFinal(RichInputDate fechaFinal) {
        this.fechaFinal = fechaFinal;
    }

    public RichInputDate getFechaFinal() {
        return fechaFinal;
    }

    public void setZona(RichSelectOneChoice zona) {
        this.zona = zona;
    }

    public RichSelectOneChoice getZona() {
        return zona;
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


    public void setSubZona(RichSelectOneChoice subZona) {
        this.subZona = subZona;
    }

    public RichSelectOneChoice getSubZona() {
        return subZona;
    }

    public void setSelectItems3(UISelectItems selectItems3) {
        this.selectItems3 = selectItems3;
    }

    public UISelectItems getSelectItems3() {
        return selectItems3;
    }

    public void setPtoMonitoreo(RichSelectOneChoice ptoMonitoreo) {
        this.ptoMonitoreo = ptoMonitoreo;
    }

    public RichSelectOneChoice getPtoMonitoreo() {
        return ptoMonitoreo;
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


    public List getListaAutoridades() {
        return listaAutoridades;
    }

    public void setListaAutoridades(List listaAutoridades) {
        this.listaAutoridades = listaAutoridades;
    }

    public Autoridades getAutoridadSelect() {
        return autoridadSelect;
    }

    public void setAutoridadSelect(Autoridades autoridadSelect) {
        this.autoridadSelect = autoridadSelect;
    }

    public List getListaArea() {
        return listaArea;
    }

    public void setListaArea(List listaArea) {
        this.listaArea = listaArea;
    }

    public List getListaZona() {
        return listaZona;
    }

    public void setListaZona(List listaZona) {
        this.listaZona = listaZona;
    }

    public List getListaSubZona() {
        return listaSubZona;
    }

    public void setListaSubZona(List listaSubZona) {
        this.listaSubZona = listaSubZona;
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
    
    public void setFc_panelGroupLayout1(RichPanelGroupLayout panelGroupLayout1) {
        this.fc_panelGroupLayout1 = panelGroupLayout1;
    }

    public RichPanelGroupLayout getFc_panelGroupLayout1() {
        return fc_panelGroupLayout1;
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
        System.out.println("FiltrosCalidad.java verListaParametros:"+verListaParametros);
        try{
            if(parametro!= null){
                System.out.println("FiltrosCalidad.java setVerListaParametros obj parametro---"+parametro.getValue());   
                parametro.setValue(null);
                          
                
            }
            this.parametro.setVisible(verListaParametros); 
            if(parametro!= null)
                 AdfFacesContext.getCurrentInstance().addPartialTarget(this.parametro);
        }catch(Exception e){
            System.out.println( "error setVerListaParametros:"+e);
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
            

    public Boolean getVerFechaInicial() {
        return verFechaInicial;
    }

    public void setVerFechaInicial(Boolean verFechaInicial) {
        if(fechaInicial!=null){
            this.fechaInicial.setVisible(verFechaInicial);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.fechaInicial);
        }
    }

    public Boolean getVerFechaFinal() {
        return verFechaFinal;
    }

    public void setVerFechaFinal(Boolean verFechaFinal) {
        if(fechaFinal!=null){
            this.fechaFinal.setVisible(verFechaFinal);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.fechaFinal);
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
    
    public void setNullFechaInicial() {
        if(fechaInicial!=null){
            this.fechaInicial.setValue(null);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.fechaInicial);
        }
    }

    public void setNullFechaFinal() {
        if(fechaFinal!=null){
            this.fechaFinal.setValue(null);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.fechaFinal);
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


    public void setFc_spacer1(RichSpacer spacer1) {
        this.fc_spacer1 = spacer1;
    }

    public RichSpacer getFc_spacer1() {
        return fc_spacer1;
    }


    public void setSpacerf1(RichSpacer spacerf1) {
        this.spacerf1 = spacerf1;
    }

    public RichSpacer getSpacerf1() {
        return spacerf1;
    }


    public void setVerListaParametros1(Boolean verListaParametros) {
        this.verListaParametros = verListaParametros;
    }

    public void setDisabledListaPuntosMonitoreo1(Boolean disabledListaPuntosMonitoreo) {
        this.disabledListaPuntosMonitoreo = disabledListaPuntosMonitoreo;
    }

    public void setObligatorioFuente1(Boolean obligatorioFuente) {
        this.obligatorioFuente = obligatorioFuente;
    }

    public void setObligatorioParametro1(Boolean obligatorioParametro) {
        this.obligatorioParametro = obligatorioParametro;
    }

    public void setObligatorioPuntoMonitoreo1(Boolean obligatorioPuntoMonitoreo) {
        this.obligatorioPuntoMonitoreo = obligatorioPuntoMonitoreo;
    }

    public void setVerFechaInicial1(Boolean verFechaInicial) {
        this.verFechaInicial = verFechaInicial;
    }

    public void setVerFechaFinal1(Boolean verFechaFinal) {
        this.verFechaFinal = verFechaFinal;
    }

    public void setObligatorioAnio1(Boolean obligatorioAnio) {
        this.obligatorioAnio = obligatorioAnio;
    }

    public void setVerListaAnios1(Boolean verListaAnios) {
        this.verListaAnios = verListaAnios;
    }

    public void setCriteriosFuentesPuntos(CriteriosBusquedaTO criteriosFuentesPuntos) {
        this.criteriosFuentesPuntos = criteriosFuentesPuntos;
    }

    public CriteriosBusquedaTO getCriteriosFuentesPuntos() {
        return criteriosFuentesPuntos;
    }


    public void setAnio(RichSelectOneChoice anio) {
        this.anio = anio;
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


    public void setSpacer1(RichSpacer spacer1) {
        this.spacer1 = spacer1;
    }

    public RichSpacer getSpacer1() {
        return spacer1;
    }


    public void setSpacerf2(RichSpacer spacerf2) {
        this.spacerf2 = spacerf2;
    }

    public RichSpacer getSpacerf2() {
        return spacerf2;
    }


    public void setSpacerf3(RichSpacer spacerf3) {
        this.spacerf3 = spacerf3;
    }

    public RichSpacer getSpacerf3() {
        return spacerf3;
    }

    public void setSpacer2(RichSpacer spacer2) {
        this.spacer2 = spacer2;
    }

    public RichSpacer getSpacer2() {
        return spacer2;
    }
}
