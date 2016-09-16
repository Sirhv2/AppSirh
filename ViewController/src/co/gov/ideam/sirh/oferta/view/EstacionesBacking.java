package co.gov.ideam.sirh.oferta.view;



import co.gov.ideam.sirh.oferta.model.SiovEstaciones;
import co.gov.ideam.sirh.parametros.model.Divipola;
import co.gov.ideam.sirh.parametros.model.PartZonificListas;

import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.view.FacesUtils;

import javax.faces.application.FacesMessage;
import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.faces.event.ValueChangeEvent;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpSession;

import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.RichMenu;

import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.layout.RichPanelBox;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelSplitter;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.component.rich.nav.RichCommandLink;
import oracle.adf.view.rich.component.rich.nav.RichCommandMenuItem;
import oracle.adf.view.rich.component.rich.output.RichActiveOutputText;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.component.rich.output.RichPanelCollection;
import oracle.adf.view.rich.component.rich.output.RichSpacer;
import oracle.adf.view.rich.context.AdfFacesContext;

import org.apache.myfaces.trinidad.event.SelectionEvent;


public class EstacionesBacking extends Estaciones {
    private RichForm form1;
    private RichDocument document1;
    private String accion;
    private String tituloPanel;
    private String labelAyuda;

    
    
   
    private RichPanelStretchLayout panelStretchLayout1;
    private RichPanelCollection pcEstaciones;  
    private RichSpacer spacer2;
    private RichSpacer spacer3;
    private RichActiveOutputText activeOutputText1;
    private RichMenu menu1;
    private RichCommandMenuItem detalleEstacion;
    private RichCommandMenuItem borrarEstacion;
    private RichTable testaciones;
    private RichPanelGroupLayout panelGroupLayout2;
    private RichPanelSplitter panelSplitter1;
    private RichPanelFormLayout panelFormLayout1;
    private RichSelectOneChoice socArea;
    private UISelectItems selectItems1;
    private RichSelectOneChoice socZona;
    private UISelectItems selectItems2;
    private RichSelectOneChoice socSubzona;
    private UISelectItems selectItems3;
    private RichCommandButton cmdBuscar;
    private RichPanelGroupLayout panelGroupLayout3;
    private RichMenu menu11;
    private RichCommandMenuItem detalleEstacion1;
    private RichTable testaciones1;
    private RichPanelCollection pcReferencia;
    private RichTable tena;
    
    //CDONCEL
    private RichSelectOneChoice filtro_depto;
    private RichSelectOneChoice filtro_mun;
    private RichPanelFormLayout panelFormLayout3;
    private RichPanelFormLayout panelFormLayout4;
    private RichCommandButton cmdBuscarMun;
    private Boolean flagHidro = true;
    private Boolean flagIDF = false;
    private RichSelectOneChoice filtro_munIDF;
    private RichSelectOneChoice filtro_EstIDF;
    private RichSpacer spaceridf;
    //FIN_CDONCEL
    
    //miga pan
    private RichPanelGroupLayout panelGroupLayout1;
    private RichCommandLink commandLink1;
    private RichSpacer spacer1;
    private RichSpacer spacer4;
    private RichOutputText outputText1;
    private RichPanelGroupLayout panelGroupLayout4;
    private RichPanelBox panelBox1;
    private RichActiveOutputText aot_area;
    private RichSpacer spacer31;
    private RichPanelFormLayout panelFormLayout2;
    private RichInputText codigoEstacion;
    
    

    public EstacionesBacking()  {
        FacesUtils.setActiveBean("estacionesBacking", "Estaciones",
                                 OfertaDelegate.class);
        this.load();
        FacesContext ctx = FacesContext.getCurrentInstance();
        HttpServletRequest req = (HttpServletRequest)ctx.getExternalContext().getRequest();

        this.setTituloPanel("Pronostico y alertas FEWS");
        this.setLabelAyuda("Usted encontrara las estaciones que se encuentran generando pronosticos y alertas FEWS");
        this.setVisibleCol(true);
        
        if (req.getParameter("origen")==null||req.getParameter("origen").equals("oferta")) {
            this.setTituloPanel("Estadisticas Hidrologicas");
            this.setLabelAyuda("Usted podra consultar la informacion hidrologica de las estaciones de monitoreo IDEAM");
            this.setVisibleCol(false);
        }

    }
    
    public void load() {
        try{
            OfertaDelegate od = OfertaDelegate.getInstance();
            this.loadArea();
            this.loadDep();
        } catch (IdeamException e) {
            showMessage(e);
        }
    }
    
    public void testaciones_selectionListener(SelectionEvent selectionEvent) {        
        RichTable testaciones1 = (RichTable)selectionEvent.getSource();
        this.estacionSeleccionada = (SiovEstaciones) testaciones1.getSelectedRowData();
        
        FacesUtils.removeFromSession("estacion");
        FacesUtils.setInSession("estacion",  this.estacionSeleccionada);
    }
    
    public void detalleEstacion_actionListener(ActionEvent actionEvent) {
        this.accion = "";
        if (this.estacionSeleccionada == null) {
            showMessage(getText("seleccionar_registro"),
                        FacesMessage.SEVERITY_ERROR);
            return;
        }
        this.accion = "seriesEstacion";
    }
    
    public void socArea_valueChangeListener(ValueChangeEvent valueChangeEvent) {
        Object area = valueChangeEvent.getNewValue();
        try {
            if (area != null) {
                this.loadZona((PartZonificListas)area);
            }
        } catch (IdeamException e) {
            showMessage(e);
        }
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.socZona);
    }

    public void socZona_valueChangeListener(ValueChangeEvent valueChangeEvent) {
        Object zona = valueChangeEvent.getNewValue();
        try {
            if (zona != null) {
                this.loadSubzona((PartZonificListas)zona);
            }
        } catch (IdeamException e) {
            showMessage(e);
        }
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.socSubzona);
    }

    public void cmdBuscar_actionListener(ActionEvent actionEvent) {
        try {  
            
            /*if(this.socSubzona.getValue() == null){
                showMessage("Debe seleccionar una subzona",
                            FacesMessage.SEVERITY_ERROR);
                return;
            }else{*/
            Divipola mun = (Divipola) this.filtro_mun.getValue();
            int opt =2; //opcion para estacion meteorologica
            if(getFlagHidro())
              opt=1; //opcion para estacion hidrologica
            if(this.filtro_mun.getValue()!=null &&!this.filtro_mun.getValue().equals("") )
                this.buscarEstacionesByMun(mun.getNombre(), opt);
            else if(this.codigoEstacion.getValue()!=null &&!this.codigoEstacion.getValue().equals("") )
                  this.buscarEstaciones((PartZonificListas)this.socSubzona.getValue(), new Integer(this.codigoEstacion.getValue().toString()));
                else{
                    this.buscarEstaciones((PartZonificListas)this.socSubzona.getValue(),null);
                    this.buscarDatosReferencia((PartZonificListas)this.socSubzona.getValue());
                }   
                AdfFacesContext.getCurrentInstance().addPartialTarget(this.pcReferencia); 
                AdfFacesContext.getCurrentInstance().addPartialTarget(this.pcEstaciones); 
                AdfFacesContext.getCurrentInstance().addPartialTarget(this.tena); 
                AdfFacesContext.getCurrentInstance().addPartialTarget(this.testaciones); 
            
            //}
            
        } catch (IdeamException e) {
            showMessage(e);
        }
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

    public void setPcEstaciones(RichPanelCollection panelCollection1) {
        this.pcEstaciones = panelCollection1;
    }

    public RichPanelCollection getPcEstaciones() {
        return pcEstaciones;
    }

    public void setSpacer1(RichSpacer spacer1) {
        this.spacer1 = spacer1;
    }

    public RichSpacer getSpacer1() {
        return spacer1;
    }

    public void setPanelGroupLayout1(RichPanelGroupLayout panelGroupLayout1) {
        this.panelGroupLayout1 = panelGroupLayout1;
    }

    public RichPanelGroupLayout getPanelGroupLayout1() {
        return panelGroupLayout1;
    }

    public void setSpacer3(RichSpacer spacer3) {
        this.spacer3 = spacer3;
    }

    public RichSpacer getSpacer3() {
        return spacer3;
    }

    public void setActiveOutputText1(RichActiveOutputText activeOutputText1) {
        this.activeOutputText1 = activeOutputText1;
    }

    public RichActiveOutputText getActiveOutputText1() {
        return activeOutputText1;
    }

    public void setMenu1(RichMenu menu1) {
        this.menu1 = menu1;
    }

    public RichMenu getMenu1() {
        return menu1;
    }

    public void setDetalleEstacion(RichCommandMenuItem commandMenuItem2) {
        this.detalleEstacion = commandMenuItem2;
    }

    public RichCommandMenuItem getDetalleEstacion() {
        return detalleEstacion;
    }

    public void setBorrarEstacion(RichCommandMenuItem commandMenuItem3) {
        this.borrarEstacion = commandMenuItem3;
    }

    public RichCommandMenuItem getBorrarEstacion() {
        return borrarEstacion;
    }

    public void setTestaciones(RichTable table1) {
        this.testaciones = table1;
    }

    public RichTable getTestaciones() {
        return testaciones;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public String getAccion() {
        return accion;
    }

    public void setOutputText1(RichOutputText outputText1) {
        this.outputText1 = outputText1;
    }

    public RichOutputText getOutputText1() {
        return outputText1;
    }


    public void setPanelGroupLayout2(RichPanelGroupLayout panelGroupLayout2) {
        this.panelGroupLayout2 = panelGroupLayout2;
    }

    public RichPanelGroupLayout getPanelGroupLayout2() {
        return panelGroupLayout2;
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

    public void setSocArea(RichSelectOneChoice selectOneChoice1) {
        this.socArea = selectOneChoice1;
    }

    public RichSelectOneChoice getSocArea() {
        return socArea;
    }

    public void setSelectItems1(UISelectItems selectItems1) {
        this.selectItems1 = selectItems1;
    }

    public UISelectItems getSelectItems1() {
        return selectItems1;
    }

    public void setSocZona(RichSelectOneChoice selectOneChoice1) {
        this.socZona = selectOneChoice1;
    }

    public RichSelectOneChoice getSocZona() {
        return socZona;
    }

    public void setSelectItems2(UISelectItems selectItems2) {
        this.selectItems2 = selectItems2;
    }

    public UISelectItems getSelectItems2() {
        return selectItems2;
    }

    public void setSocSubzona(RichSelectOneChoice selectOneChoice1) {
        this.socSubzona = selectOneChoice1;
    }

    public RichSelectOneChoice getSocSubzona() {
        return socSubzona;
    }

    public void setSelectItems3(UISelectItems selectItems3) {
        this.selectItems3 = selectItems3;
    }

    public UISelectItems getSelectItems3() {
        return selectItems3;
    }

    public void setCmdBuscar(RichCommandButton commandButton1) {
        this.cmdBuscar = commandButton1;
    }

    public RichCommandButton getCmdBuscar() {
        return cmdBuscar;
    }

    public void setPanelGroupLayout3(RichPanelGroupLayout panelGroupLayout3) {
        this.panelGroupLayout3 = panelGroupLayout3;
    }

    public RichPanelGroupLayout getPanelGroupLayout3() {
        return panelGroupLayout3;
    }

    public void setMenu11(RichMenu menu11) {
        this.menu11 = menu11;
    }

    public RichMenu getMenu11() {
        return menu11;
    }

    public void setDetalleEstacion1(RichCommandMenuItem detalleEstacion1) {
        this.detalleEstacion1 = detalleEstacion1;
    }

    public RichCommandMenuItem getDetalleEstacion1() {
        return detalleEstacion1;
    }

    public void setTestaciones1(RichTable testaciones1) {
        this.testaciones1 = testaciones1;
    }

    public RichTable getTestaciones1() {
        return testaciones1;
    }

    public void setPcReferencia(RichPanelCollection panelCollection2) {
        this.pcReferencia = panelCollection2;
    }

    public RichPanelCollection getPcReferencia() {
        return pcReferencia;
    }

    public void setTena(RichTable table1) {
        this.tena = table1;
    }

    public RichTable getTena() {
        return tena;
    }

    public void setSpacer2(RichSpacer spacer2) {
        this.spacer2 = spacer2;
    }

    public RichSpacer getSpacer2() {
        return spacer2;
    }

    public void setCommandLink1(RichCommandLink commandLink1) {
        this.commandLink1 = commandLink1;
    }

    public RichCommandLink getCommandLink1() {
        return commandLink1;
    }

    public void setSpacer4(RichSpacer spacer4) {
        this.spacer4 = spacer4;
    }

    public RichSpacer getSpacer4() {
        return spacer4;
    }
    // HUGO 20150115
    public void consultaFews_actionListener(ActionEvent actionEvent) {
        try {
            String urlFews =
                (String)actionEvent.getComponent().getAttributes().get("urlFews");

            redirect(urlFews,false);
            
        } catch (Exception e) {
            showMessage(e.getMessage());
        }
        
    }
    public void setPanelGroupLayout4(RichPanelGroupLayout panelGroupLayout4) {
        this.panelGroupLayout4 = panelGroupLayout4;
    }

    public RichPanelGroupLayout getPanelGroupLayout4() {
        return panelGroupLayout4;
    }
    public void setPanelBox1(RichPanelBox panelBox1) {
        this.panelBox1 = panelBox1;
    }

    public RichPanelBox getPanelBox1() {
        return panelBox1;
    }

    public void setTituloPanel(String tituloPanel) {
        this.tituloPanel = tituloPanel;
    }

    public String getTituloPanel() {
        return tituloPanel;
    }

    public void setAot_area(RichActiveOutputText aot_area) {
        this.aot_area = aot_area;
    }

    public RichActiveOutputText getAot_area() {
        return aot_area;
    }

    public void setLabelAyuda(String labelAyuda) {
        this.labelAyuda = labelAyuda;
    }

    public String getLabelAyuda() {
        return labelAyuda;
    }

    public void setSpacer31(RichSpacer spacer31) {
        this.spacer31 = spacer31;
    }

    public RichSpacer getSpacer31() {
        return spacer31;
    }


    public void setPanelFormLayout2(RichPanelFormLayout panelFormLayout2) {
        this.panelFormLayout2 = panelFormLayout2;
    }

    public RichPanelFormLayout getPanelFormLayout2() {
        return panelFormLayout2;
    }

    public void setCodigoEstacion(RichInputText inputText1) {
        this.codigoEstacion = inputText1;
    }

    public RichInputText getCodigoEstacion() {
        return codigoEstacion;
    }

  public RichSelectOneChoice getFiltro_depto() {
    return filtro_depto;
  }

  public void setFiltro_depto(RichSelectOneChoice filtro_depto) {
    this.filtro_depto = filtro_depto;
  }

  public RichSelectOneChoice getFiltro_mun() {
    return filtro_mun;
  }

  public void setFiltro_mun(RichSelectOneChoice filtro_mun) {
    this.filtro_mun = filtro_mun;
  }

  public RichPanelFormLayout getPanelFormLayout3() {
    return panelFormLayout3;
  }

  public void setPanelFormLayout3(RichPanelFormLayout panelFormLayout3) {
    this.panelFormLayout3 = panelFormLayout3;
  }
  
  //CDONCEL
  public void depto_valueChangeListener(ValueChangeEvent valueChangeEvent) {
    Object dpt = valueChangeEvent.getNewValue();
    try {
        if (dpt != null) {
          this.loadMun(((Divipola)dpt).getId());
        }
    } catch (IdeamException e) {
        showMessage(e);
    }
    AdfFacesContext.getCurrentInstance().addPartialTarget(this.filtro_mun);
  }
  
  public void munIDF_valueChangeListener(ValueChangeEvent valueChangeEvent) {
    //System.out.println("HOLA VALUE");
    Object mIDF = valueChangeEvent.getNewValue();
    try {
        if (mIDF != null) {
          this.loadEstIDF(((SiovEstaciones)mIDF).getDescDivipola());
        }
    } catch (IdeamException e) {
        showMessage(e);
    }
    AdfFacesContext.getCurrentInstance().addPartialTarget(this.filtro_EstIDF);
  }
  
  public void cmdBuscarMun_actionListener(ActionEvent actionEvent) {
      try {  
        Divipola mun = (Divipola) this.filtro_mun.getValue();
        int opt =2; //opcion para estacion meteorologica
        if(getFlagHidro())
          opt=1; //opcion para estacion hidrologica
        if(this.filtro_mun.getValue()!=null &&!this.filtro_mun.getValue().equals("") )
            this.buscarEstacionesByMun(mun.getNombre(), opt);
              
              AdfFacesContext.getCurrentInstance().addPartialTarget(this.pcReferencia); 
              AdfFacesContext.getCurrentInstance().addPartialTarget(this.pcEstaciones); 
              AdfFacesContext.getCurrentInstance().addPartialTarget(this.tena); 
              AdfFacesContext.getCurrentInstance().addPartialTarget(this.testaciones); 
         
      } catch (IdeamException e) {
          showMessage(e);
      }
  }
  
  public  String preparaEstaciones(int opt) throws IdeamException {
      FacesContext ctx = FacesContext.getCurrentInstance();
      HttpServletRequest req = (HttpServletRequest)ctx.getExternalContext().getRequest();
      flagIDF = false;
      flagHidro = true;
      if (opt == 2){
        flagHidro = false;
        if (req.getParameter("origen")==null||req.getParameter("origen").equals("oferta")) {
            this.setTituloPanel("Estadisticas Meteorológicas");
            this.setLabelAyuda("Usted podrá consultar la informacion meteorológica de las estaciones de monitoreo IDEAM");
            this.setVisibleCol(false);
        }
      }
      if (opt == 3){
        flagIDF = true;
        if (req.getParameter("origen")==null||req.getParameter("origen").equals("oferta")) {
            this.setTituloPanel("Curvas Intensidad Duración Frecuencia - IDF");
            this.setLabelAyuda("Usted podrá consultar las Curvas Intensidad Duración Frecuencia - IDF de las estaciones de monitoreo IDEAM");
            this.setVisibleCol(false);
            this.loadIDF();
        }
      }
      
      return "oferta";
    }
  

  public void cmdBuscarMeteo_actionListener(ActionEvent actionEvent) {
      try {
        Divipola mun = (Divipola) this.filtro_mun.getValue();
        int opt =2; //opcion para estacion meteorologica
        if(getFlagHidro())
          opt=1; //opcion para estacion hidrologica
        if(this.filtro_mun.getValue()!=null &&!this.filtro_mun.getValue().equals("") )
            this.buscarEstacionesByMun(mun.getNombre(), opt);
        else {
          PartZonificListas subZona = (PartZonificListas)this.socSubzona.getValue();
        
          this.buscarEstacionesMeteo(subZona.getId());  
        }
       // System.out.println("+++++++++++++++");
       // System.out.println("+++++++++++++++");
       // System.out.println("+++++++++++++++");
       // System.out.println("+++++++++++++++");
       // System.out.println("+++++++++++++++");
       // System.out.println("SUBZONA    =   " + subZona.getId());
              AdfFacesContext.getCurrentInstance().addPartialTarget(this.pcReferencia); 
              AdfFacesContext.getCurrentInstance().addPartialTarget(this.pcEstaciones); 
              AdfFacesContext.getCurrentInstance().addPartialTarget(this.tena); 
              AdfFacesContext.getCurrentInstance().addPartialTarget(this.testaciones); 
          
          //}
          
      } catch (Exception e) {
          //showMessage(e);
      }
  }
  
  public void preparaIDF_actionListener(ActionEvent actionEvent){
      FacesContext context = FacesContext.getCurrentInstance();
      HttpServletRequest req = (HttpServletRequest)context.getExternalContext().getRequest();
      HttpSession session = req.getSession(true);
      SiovEstaciones s = (SiovEstaciones) this.filtro_EstIDF.getValue();
      if(this.filtro_EstIDF.getValue()!=null &&!this.filtro_EstIDF.getValue().equals("") ){
        String cs = s.getCodCatalogoEs();
          int cod = Integer.parseInt(cs);
          this.cargarDatosIDF(cod);
      }
      if (this.getLsDatosIDF().size()>0){
          session.setAttribute("datos", this.getLsDatosIDF()); 
          String baseURL = context.getExternalContext().getRequestContextPath();
          String url = baseURL + "/reporteidf";

          try {
                      context.getExternalContext().redirect(url);
          } catch (Exception e) {
                      e.printStackTrace();
                      // Handle the exception here ...
          } finally {
                      context.responseComplete();
          }
        }
    }

  //FIN_CDONCEL

  public RichCommandButton getCmdBuscarMun() {
    return cmdBuscarMun;
  }

  public void setCmdBuscarMun(RichCommandButton cmdBuscarMun) {
    this.cmdBuscarMun = cmdBuscarMun;
  }

  public Boolean getFlagHidro() {
    return flagHidro;
  }

  public void setFlagHidro(Boolean flagHidro) {
    this.flagHidro = flagHidro;
  }

  public Boolean getFlagIDF() {
    return flagIDF;
  }

  public void setFlagIDF(Boolean flagIDF) {
    this.flagIDF = flagIDF;
  }

  public RichSelectOneChoice getFiltro_munIDF() {
    return filtro_munIDF;
  }

  public void setFiltro_munIDF(RichSelectOneChoice filtro_munIDF) {
    this.filtro_munIDF = filtro_munIDF;
  }

  public RichSelectOneChoice getFiltro_EstIDF() {
    return filtro_EstIDF;
  }

  public void setFiltro_EstIDF(RichSelectOneChoice filtro_EstIDF) {
    this.filtro_EstIDF = filtro_EstIDF;
  }

  public RichPanelFormLayout getPanelFormLayout4() {
    return panelFormLayout4;
  }

  public void setPanelFormLayout4(RichPanelFormLayout panelFormLayout4) {
    this.panelFormLayout4 = panelFormLayout4;
  }

  public RichSpacer getSpaceridf() {
    return spaceridf;
  }

  public void setSpaceridf(RichSpacer spaceridf) {
    this.spaceridf = spaceridf;
  }
}
