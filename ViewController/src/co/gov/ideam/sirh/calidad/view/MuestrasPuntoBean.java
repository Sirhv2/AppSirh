package co.gov.ideam.sirh.calidad.view;

import co.gov.ideam.sirh.calidad.model.CriteriosBusquedaPuntos;
import co.gov.ideam.sirh.calidad.model.Muestra;
import co.gov.ideam.sirh.calidad.model.PuntoMonitoreo;
import co.gov.ideam.sirh.calidad.model.PuntoMonitoreoTO;
import co.gov.ideam.sirh.parametros.model.Autoridades;
import co.gov.ideam.sirh.parametros.model.Categoria;
import co.gov.ideam.sirh.parametros.model.Lista;
import co.gov.ideam.sirh.seguridad.model.UsuarioConectadoTO;
import co.gov.ideam.sirh.util.Constantes;
import co.gov.ideam.sirh.util.ConstantesCalidad;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.view.FacesUtils;

import co.gov.ideam.sirh.view.StandarBean;

import java.text.DateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UISelectItems;
import javax.faces.event.ActionEvent;

import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.RichMenu;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelSplitter;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.component.rich.nav.RichCommandLink;
import oracle.adf.view.rich.component.rich.nav.RichCommandMenuItem;
import oracle.adf.view.rich.component.rich.output.RichPanelCollection;
import oracle.adf.view.rich.component.rich.output.RichSpacer;
import oracle.adf.view.rich.context.AdfFacesContext;

public class MuestrasPuntoBean extends StandarBean{
    private RichForm form1;
    private RichDocument document1;
    private RichPanelStretchLayout panelStretchLayout1;
    private RichPanelSplitter panelSplitter1;
    private RichPanelFormLayout panelFormLayout1;
    private RichPanelGroupLayout panelGroupLayout1;
    private RichSelectOneChoice tipoMuestra;
    private UISelectItems selectItems2;
    private RichSpacer spacer1;
    private RichCommandButton cb_filtrar;
    private RichPanelCollection panelCollection1;
    private RichMenu m_archivo;
    private RichCommandMenuItem cmi_adicionar;
    private RichCommandMenuItem cmi_editar;
    private String accionAdicionar;
    private List listaTipoMuestra;
    private List listaPuntos;
    private List listaMuestras;
    private RichTable t_muestrasPunto;
    private RichInputDate fmuestra;
    private PuntoMonitoreo puntoMonitoreo;
    private PuntoMonitoreoTO puntoMonitoreoTO;
    
    private RichSpacer spacer2;
    private RichPanelGroupLayout panelGroupLayout2;
    private RichCommandLink link_detalle;
    private RichCommandLink link_lista;
    private RichSpacer spacer4;

    public MuestrasPuntoBean(){
         FacesUtils.setActiveBean("muestrasPunto",
                                  "muestraspunto", CalidadDelegate.class);
     

         this.load();        
     }
     public void load(){
       
         try{      
           CalidadDelegate cald = CalidadDelegate.getInstance();
           this.listaTipoMuestra = this.getListaPorCategoria(ConstantesCalidad.TIPO_MUESTRA);
           Object obj = FacesUtils.getFromSession("puntoSeleccionado");
             if(obj instanceof PuntoMonitoreo){
                    this.puntoMonitoreo = (PuntoMonitoreo)obj;
                    Long codigoPunto=  this.puntoMonitoreo.getId();
                    System.out.println("puntoMonitoreo----------------"+codigoPunto);
                    this.puntoMonitoreo = cald.getPuntoMonitoreo(codigoPunto); 
             }  else if(obj instanceof PuntoMonitoreoTO){
                    this.puntoMonitoreoTO = (PuntoMonitoreoTO)obj;
                    Long codigoPunto=  this.puntoMonitoreoTO.getId();
                    System.out.println("puntoMonitoreoTO------punto----------"+codigoPunto);
                    this.puntoMonitoreo = cald.getPuntoMonitoreo(codigoPunto); 
             }      
           listaMuestras = cald.getMuestras(this.puntoMonitoreo);
                    
         }catch(IdeamException e){
             showMessage(e);
         }
     }


    public void cb_filtrar_actionListener(ActionEvent actionEvent) {
        CriteriosBusquedaPuntos criterios = new CriteriosBusquedaPuntos();
        if(this.tipoMuestra.getValue()!=null){
            criterios.setTipoMuestra(
                    (Lista)tipoMuestra.getValue());
        }
    
      if(this.fmuestra.getValue()!=null &&
         this.fmuestra.getValue().toString().length()>0){
          Date fecham =(Date)this.fmuestra.getValue();
          DateFormat df = DateFormat.getDateInstance();
          String fechamuestra = df.format(fecham);
          
           if(fechamuestra.length()==9){
              String fechaCompleta = "0"+fechamuestra;
               criterios.setFechaMuestra(fechaCompleta);  
           }else{
               criterios.setFechaMuestra(fechamuestra);  
           }
          
          
       }
      
    try{
         CalidadDelegate cald = CalidadDelegate.getInstance();
          this.listaMuestras= new ArrayList();
          this.listaMuestras = cald.getMuestrasPunto(criterios, this.puntoMonitoreo.getId());
           AdfFacesContext.getCurrentInstance().addPartialTarget(t_muestrasPunto);
        }catch(IdeamException e){
            showMessage(e);
        }
    }
    
    
    public void cmi_adicionar_actionListener(ActionEvent actionEvent) {
       accionAdicionar = "adicionarmuestra";
      
    }
    
    
    
    public void cmi_editar_actionListener(ActionEvent actionEvent) {
        accionAdicionar = "";
        if (t_muestrasPunto.getSelectedRowData()==null){
            showMessage(getText("seleccionar_registro"),
                        FacesMessage.SEVERITY_ERROR);
            return;
        }
        
       
        Muestra mm = (Muestra)t_muestrasPunto.getSelectedRowData();
        FacesUtils.setInSession("muestraSeleccionada", mm);
        accionAdicionar = "detallemuestra";
    
    }
    public String getAccionAdicionar() {
        return accionAdicionar;
    }

    public void setAccionAdicionar(String accionAdicionar) {
        this.accionAdicionar = accionAdicionar;
    }

    public void setForm1(RichForm form1) {
        this.form1 = form1;
    }

    public RichForm getForm1() {
        return form1;
    }

    public void setDocument1(RichDocument document1) {
        this.document1 = document1;
    }

    public RichDocument getDocument1() {
        return document1;
    }

    public void setPanelStretchLayout1(RichPanelStretchLayout panelStretchLayout1) {
        this.panelStretchLayout1 = panelStretchLayout1;
    }

    public RichPanelStretchLayout getPanelStretchLayout1() {
        return panelStretchLayout1;
    }

    public void setPanelSplitter1(RichPanelSplitter panelSplitter1) {
        this.panelSplitter1 = panelSplitter1;
    }

    public RichPanelSplitter getPanelSplitter1() {
        return panelSplitter1;
    }

    public void setPanelFormLayout1(RichPanelFormLayout panelFormLayout1) {
        this.panelFormLayout1 = panelFormLayout1;
    }

    public RichPanelFormLayout getPanelFormLayout1() {
        return panelFormLayout1;
    }

    public void setPanelGroupLayout1(RichPanelGroupLayout panelGroupLayout1) {
        this.panelGroupLayout1 = panelGroupLayout1;
    }

    public RichPanelGroupLayout getPanelGroupLayout1() {
        return panelGroupLayout1;
    }


    public void setTipoMuestra(RichSelectOneChoice selectOneChoice1) {
        this.tipoMuestra = selectOneChoice1;
    }

    public RichSelectOneChoice getTipoMuestra() {
        return tipoMuestra;
    }

    public void setSelectItems2(UISelectItems selectItems2) {
        this.selectItems2 = selectItems2;
    }

    public UISelectItems getSelectItems2() {
        return selectItems2;
    }

    public void setSpacer1(RichSpacer spacer1) {
        this.spacer1 = spacer1;
    }

    public RichSpacer getSpacer1() {
        return spacer1;
    }

    public void setCb_filtrar(RichCommandButton commandButton1) {
        this.cb_filtrar = commandButton1;
    }

    public RichCommandButton getCb_filtrar() {
        return cb_filtrar;
    }


    public void setPanelCollection1(RichPanelCollection panelCollection1) {
        this.panelCollection1 = panelCollection1;
    }

    public RichPanelCollection getPanelCollection1() {
        return panelCollection1;
    }

    public void setM_archivo(RichMenu menu1) {
        this.m_archivo = menu1;
    }

    public RichMenu getM_archivo() {
        return m_archivo;
    }

    public void setCmi_adicionar(RichCommandMenuItem commandMenuItem1) {
        this.cmi_adicionar = commandMenuItem1;
    }

    public RichCommandMenuItem getCmi_adicionar() {
        return cmi_adicionar;
    }

    public void setCmi_editar(RichCommandMenuItem commandMenuItem2) {
        this.cmi_editar = commandMenuItem2;
    }

    public RichCommandMenuItem getCmi_editar() {
        return cmi_editar;
    }

    public void setListaTipoMuestra(List listaTipoMuestra) {
        this.listaTipoMuestra = listaTipoMuestra;
    }

    public List getListaTipoMuestra() {
        return listaTipoMuestra;
    }

    public void setListaPuntos(List listaPuntos) {
        this.listaPuntos = listaPuntos;
    }

    public List getListaPuntos() {
        return listaPuntos;
    }

    public void setListaMuestras(List listaMuestras) {
        this.listaMuestras = listaMuestras;
    }

    public List getListaMuestras() {
        return listaMuestras;
    }


    public void setFmuestra(RichInputDate inputDate1) {
        this.fmuestra = inputDate1;
    }

    public RichInputDate getFmuestra() {
        return fmuestra;
    }

    public void setT_muestrasPunto(RichTable t_muestrasPunto) {
        this.t_muestrasPunto = t_muestrasPunto;
    }

    public RichTable getT_muestrasPunto() {
        return t_muestrasPunto;
    }

    public void setPuntoMonitoreo(PuntoMonitoreo puntoMonitoreo) {
        this.puntoMonitoreo = puntoMonitoreo;
    }

    public PuntoMonitoreo getPuntoMonitoreo() {
        return puntoMonitoreo;
    }

    public void setSpacer2(RichSpacer spacer2) {
        this.spacer2 = spacer2;
    }

    public RichSpacer getSpacer2() {
        return spacer2;
    }

    public void setPanelGroupLayout2(RichPanelGroupLayout panelGroupLayout2) {
        this.panelGroupLayout2 = panelGroupLayout2;
    }

    public RichPanelGroupLayout getPanelGroupLayout2() {
        return panelGroupLayout2;
    }

    public void setLink_detalle(RichCommandLink commandLink1) {
        this.link_detalle = commandLink1;
    }

    public RichCommandLink getLink_detalle() {
        return link_detalle;
    }


    public void setLink_lista(RichCommandLink commandLink2) {
        this.link_lista = commandLink2;
    }

    public RichCommandLink getLink_lista() {
        return link_lista;
    }

    public void setSpacer4(RichSpacer spacer4) {
        this.spacer4 = spacer4;
    }

    public RichSpacer getSpacer4() {
        return spacer4;
    }


    public void setPuntoMonitoreoTO(PuntoMonitoreoTO puntoMonitoreoTO) {
        this.puntoMonitoreoTO = puntoMonitoreoTO;
    }

    public PuntoMonitoreoTO getPuntoMonitoreoTO() {
        return puntoMonitoreoTO;
    }
}
