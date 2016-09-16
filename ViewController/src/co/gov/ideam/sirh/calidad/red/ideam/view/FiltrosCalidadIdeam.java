package co.gov.ideam.sirh.calidad.red.ideam.view;

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
    import co.gov.ideam.sirh.view.StandarDashBoard;

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


    public class FiltrosCalidadIdeam extends StandarDashBoard{
        private RichPanelBox panelFiltros;
        private RichPanelFormLayout pnl_filtros;
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

    private Autoridades autoridadSelect = null;       
        private List listaArea;
        private List listaZona;
        private List listaSubZona;
        private List listaDepartamentos;
        private List listaMunicipios;
        private List listaFuentes;
        private Boolean obligatorioFuente;
        private Integer IdAutoridadFuentePunto = 0;
        private CriteriosBusquedaTO criteriosFiltrosIdeam = new CriteriosBusquedaTO();
        

        private RichPanelGroupLayout fc_panelGroupLayout1;
    private RichSpacer spacer101;


    public FiltrosCalidadIdeam() {      

            try {
                this.autoridadSelect = new Autoridades();
                this.autoridadSelect.setId(1);              
                criteriosFiltrosIdeam.setAutoridad(autoridadSelect);           
                this.setListaArea(this.cargarZonificacion(null));           
                this.listaFuentes = this.getListaFuentesCriterios(criteriosFiltrosIdeam);               
                this.setListaDepartamentos(this.cargarDivipolaSinRestriccion(null));
                } catch (IdeamException e) {
                    showMessage(e);
                }
            
            this.listaZona = new ArrayList();
            this.listaSubZona = new ArrayList();
            this.listaMunicipios = new ArrayList();     
        }        

        public void area_valueChangeListener(ValueChangeEvent valueChangeEvent) {
            Object area = valueChangeEvent.getNewValue();
            this.listaZona = new ArrayList();
            this.listaSubZona = new ArrayList();    
            this.listaFuentes = new ArrayList();
            zona.setValue(null);
            subZona.setValue(null);
            fuentes.setValue(null);

            AdfFacesContext.getCurrentInstance().addPartialTarget(zona);
            AdfFacesContext.getCurrentInstance().addPartialTarget(subZona);
            AdfFacesContext.getCurrentInstance().addPartialTarget(fuentes);
 
            
            try {
                criteriosFiltrosIdeam.setZona(null);        
                if (area != null) {               
                    criteriosFiltrosIdeam.setArea((PartZonificListas)this.area.getValue());             
                    //if(criteriosFiltrosIdeam.getAutoridad()!=null){   
                         this.listaZona = this.getListasZonificacion(((PartZonificListas)area).getId(),autoridadSelect);  
                   // } else {
                    //    this.listaZona = this.cargarZonificacion(((PartZonificListas)area).getId()); 
                   // }
             } else{
                    criteriosFiltrosIdeam.setArea(null);        
                    this.setListaDepartamentos(this.cargarDivipolaSinRestriccion(null)); 
                }
               // this.listaFuentes = this.getListaFuentesCriterios(criteriosFiltrosIdeam); 
 
            } catch (IdeamException e) {
                showMessage(e);
            }
            AdfFacesContext.getCurrentInstance().addPartialTarget(zona);
            AdfFacesContext.getCurrentInstance().addPartialTarget(subZona);
            //AdfFacesContext.getCurrentInstance().addPartialTarget(fuentes);

        }


        public void zona_valueChangeListener(ValueChangeEvent valueChangeEvent) {
            Object zona = valueChangeEvent.getNewValue();
            this.listaSubZona = new ArrayList();
            this.listaFuentes = new ArrayList();
            subZona.setValue(null);
            fuentes.setValue(null);

            
            try { 
                criteriosFiltrosIdeam.setSubzona(null);
                if (zona != null) {
                    criteriosFiltrosIdeam.setZona((PartZonificListas)this.zona.getValue());                             
                   // if(criteriosFiltrosIdeam.getAutoridad()!=null){   
                        this.listaSubZona = this.getListasZonificacion(((PartZonificListas)zona).getId(),autoridadSelect);   
                   // } else {
                    //    this.listaSubZona = this.cargarZonificacion(((PartZonificListas)zona).getId()); 
                    //} 
                }
                else {
                    criteriosFiltrosIdeam.setZona(null);                
                }
                //this.listaFuentes = this.getListaFuentesCriterios(criteriosFiltrosIdeam);  
 
            } catch (IdeamException e) {
                showMessage(e);
            }
            AdfFacesContext.getCurrentInstance().addPartialTarget(subZona);
            AdfFacesContext.getCurrentInstance().addPartialTarget(fuentes);

        }

        public void subZona_valueChangeListener(ValueChangeEvent valueChangeEvent) {
            Object subzona = valueChangeEvent.getNewValue();
            this.listaFuentes = new ArrayList();
            fuentes.setValue(null);
            
            try{
                if(subzona!=null){    
                    criteriosFiltrosIdeam.setSubzona((PartZonificListas)this.subZona.getValue());              
                }else {
                    criteriosFiltrosIdeam.setSubzona(null);                
                 }
                this.listaFuentes = this.getListaFuentesCriterios(criteriosFiltrosIdeam);  

            }catch(IdeamException e){            
                showMessage(e);
            }
            AdfFacesContext.getCurrentInstance().addPartialTarget(fuentes);    
 
        }
       
        public void departamentos_valueChangeListener(ValueChangeEvent valueChangeEvent) {
            Object departamento = valueChangeEvent.getNewValue();
            this.setListaMunicipios(new ArrayList());
            CriteriosBusquedaTO criterios = new CriteriosBusquedaTO();
            
          
            try {
                if (departamento != null) {    
                    criteriosFiltrosIdeam.setDepartamento((Divipola)this.departamentos.getValue());
                    this.listaMunicipios = this.cargarDivipolaSinRestriccion(((Divipola)departamento).getId());                                                  
                }else {
                    criteriosFiltrosIdeam.setDepartamento(null);
                    this.setListaDepartamentos(this.cargarDivipolaSinRestriccion(null));               
                 } 
                this.listaFuentes = this.getListaFuentesCriterios(criteriosFiltrosIdeam);     

            } catch (IdeamException e) {
                showMessage(e);
            }
            AdfFacesContext.getCurrentInstance().addPartialTarget(departamentos);
            AdfFacesContext.getCurrentInstance().addPartialTarget(municipios);
            AdfFacesContext.getCurrentInstance().addPartialTarget(fuentes);
      
        }

       
        public void fuente_valueChangeListener(ValueChangeEvent valueChangeEvent) {
            Object fuente = valueChangeEvent.getNewValue();
           
                if(fuente!=null){
                   criteriosFiltrosIdeam.setFuente((FnttFuente)this.fuentes.getValue());
                   if(criteriosFiltrosIdeam.getAutoridad()!=null){  
                        
                   }else {
                        this.setIdAutoridadFuentePunto(((FnttFuente)fuente).getCodigoAutoridad().intValue()); 
                       }
               }else {
                       criteriosFiltrosIdeam.setFuente(null);
               }  
        }

        public void limpiarFiltros() {          
            this.listaArea = new ArrayList();
            this.listaZona = new ArrayList();
            this.listaSubZona = new ArrayList();
            this.listaDepartamentos = new ArrayList();
            this.listaMunicipios = new ArrayList();
            this.listaFuentes = new ArrayList();
 
            if(area!=null) area.setValue(null);
            if(zona!=null) zona.setValue(null);
            if(subZona!=null) subZona.setValue(null);    
            if(departamentos!=null) departamentos.setValue(null);
            if(municipios!=null) municipios.setValue(null);
            if(fuentes!=null) fuentes.setValue(null);
            if(fechaInicial!=null) fechaInicial.setValue(null);
            if(fechaFinal!=null) fechaFinal.setValue(null);
            this.criteriosFiltrosIdeam = new CriteriosBusquedaTO();
                
            try{
                criteriosFiltrosIdeam.setAutoridad(autoridadSelect);
                this.setListaArea(this.cargarZonificacion(null));              
                this.listaFuentes = this.getListaFuentesCriterios(criteriosFiltrosIdeam);                
                this.setListaDepartamentos(this.cargarDivipolaSinRestriccion(null));             
            }  catch(IdeamException e){
                 showMessage(e);
            }
            if(area!=null) AdfFacesContext.getCurrentInstance().addPartialTarget(area);
            if(zona!=null) AdfFacesContext.getCurrentInstance().addPartialTarget(zona);
            if(subZona!=null) AdfFacesContext.getCurrentInstance().addPartialTarget(subZona);
            if(departamentos!=null) AdfFacesContext.getCurrentInstance().addPartialTarget(departamentos);
            if(municipios!=null) AdfFacesContext.getCurrentInstance().addPartialTarget(municipios);
            if(fuentes!=null) AdfFacesContext.getCurrentInstance().addPartialTarget(fuentes);
            if(fechaInicial!=null) AdfFacesContext.getCurrentInstance().addPartialTarget(fechaInicial);
            if(fechaFinal!=null) AdfFacesContext.getCurrentInstance().addPartialTarget(fechaFinal);
        }

        public CriteriosBusquedaMuestrasTO getCriterios(){
                   
            CriteriosBusquedaMuestrasTO criterios = new CriteriosBusquedaMuestrasTO();
                   
            criterios.setAutoridad(autoridadSelect);
           
            if(area.getValue()!=null){
                criterios.setArea((PartZonificListas)area.getValue());
            }

            if(zona.getValue()!=null){
                criterios.setZona((PartZonificListas)zona.getValue());
            }
            
            if(subZona.getValue()!=null){
                criterios.setSubZona((PartZonificListas)subZona.getValue());           
            }
            
            if(departamentos.getValue()!=null){
                criterios.setDepartamento((Divipola)departamentos.getValue());           
            }
            
            if(municipios.getValue()!=null){
                criterios.setMunicipio((Divipola)municipios.getValue());           
            }
            
            if(fuentes.getValue()!=null) {
                criterios.setFuente((FnttFuente) fuentes.getValue());
            }      
/*            
            if(ptoMonitoreo.getValue()!=null) {
                criterios.setPtoMonitoreo((PuntoMonitoreo) ptoMonitoreo.getValue()); ;
            }
            
            if(parametro.getValue()!=null){
                criterios.setParametro((Lista) parametro.getValue());
            }
*/                    
            if(fechaInicial.getValue()!=null ) {
               Calendar fecha = GregorianCalendar.getInstance();
               fecha.setTime((Date)fechaInicial.getValue());
               criterios.setFechaInicial(fecha);
            }

            if(fechaFinal.getValue()!=null ) {
                Calendar fecha = GregorianCalendar.getInstance();
                fecha.setTime((Date)fechaFinal.getValue());           
                criterios.setFechaFinal(fecha);       
            }
            
             
            return criterios;
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
            this.fuentes.setShowRequired(ObligatorioFuente);
            this.fuentes.setRequired(ObligatorioFuente); 
                    
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.fuentes);
        }

        public Integer getIdAutoridadFuentePunto() {
            return IdAutoridadFuentePunto;
        }

        public void setIdAutoridadFuentePunto(Integer IdAutoridadFuentePunto) {
            this.IdAutoridadFuentePunto = IdAutoridadFuentePunto;
        }

    public void setSpacer101(RichSpacer spacer101) {
        this.spacer101 = spacer101;
    }

    public RichSpacer getSpacer101() {
        return spacer101;
    }
}
