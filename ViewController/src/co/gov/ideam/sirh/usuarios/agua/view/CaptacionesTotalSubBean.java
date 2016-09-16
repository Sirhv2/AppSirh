package co.gov.ideam.sirh.usuarios.agua.view;

import co.gov.ideam.sirh.fuentes.model.FnttFuente;
import co.gov.ideam.sirh.parametros.model.Divipola;
import co.gov.ideam.sirh.parametros.model.Lista;
import co.gov.ideam.sirh.parametros.model.PartZonificListas;
import co.gov.ideam.sirh.parametros.view.ParametrosDelegate;
import co.gov.ideam.sirh.reportes.view.ReporteDelegate;
import co.gov.ideam.sirh.seguridad.model.UsuarioConectadoTO;
import co.gov.ideam.sirh.usuarios.agua.model.CaptacionSubTO;
import co.gov.ideam.sirh.usuarios.agua.model.CaptacionTO;

import co.gov.ideam.sirh.usuarios.agua.model.CriteriosBusquedaTO;

import co.gov.ideam.sirh.util.ConstantesCaptaciones;
import co.gov.ideam.sirh.util.ConstantesParametros;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.view.FacesUtils;

import co.gov.ideam.sirh.view.StandarBean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


import javax.faces.component.UISelectItems;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.RichMenu;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelSplitter;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.component.rich.nav.RichCommandLink;
import oracle.adf.view.rich.component.rich.nav.RichCommandMenuItem;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.component.rich.output.RichPanelCollection;
import oracle.adf.view.rich.component.rich.output.RichSpacer;
import oracle.adf.view.rich.context.AdfFacesContext;



public class CaptacionesTotalSubBean extends StandarBean {
    private CaptacionTO captacionTOSeleccionada;
    private List listaDepartamentos;
    private List listaMunicipios;
    private List listaCaptacionesSub;
    private List listaUsos; //usos en bd.
    private List listaArea;
    private List listaZona;
    private List listaSubzona;
    private List listaFuentes;

    private RichSelectOneChoice socProvinciaHidro;
    private UISelectItems siProvinciaHidro;
    private RichForm form1;
    private RichDocument document1;
    private RichPanelStretchLayout panelStretchLayout1;
    private RichPanelGroupLayout panelGroupLayout1;
    private RichPanelGroupLayout panelGroupLayout2;
    private RichCommandLink clTodos;
    private RichSpacer spacer1;
    private RichPanelCollection panelCollection1;
    private RichMenu menu1;
    private RichCommandMenuItem cmi_imprimir;
    private RichTable t_captaciones;
    private String detalleCaptacion;
    private RichSpacer spacer2;
    private RichCommandLink clUsuario;
    private RichSpacer spacer3;
    private RichCommandLink clPredio;
    private RichSpacer spacer4;
    private RichCommandLink clConcesiones;
    private RichPanelSplitter panelSplitter1;
    private RichPanelFormLayout panelFormLayout1;
    private RichSelectOneChoice socFiltroDepartamento;
    private UISelectItems siFiltroDep;
    private RichSelectOneChoice socFiltroMun;
    private UISelectItems siFiltroMun;
    private RichCommandButton cmdBuscar;
    private RichSelectOneChoice selectOneChoiceUsos;
    private UISelectItems selectItems1;
    private RichSelectOneChoice selectOneChoiceArea;
    private UISelectItems selectItems2Area;
    private RichSelectOneChoice selectOneChoiceZona;
    private UISelectItems selectItems3Zona;
    private RichSelectOneChoice selectOneChoiceSubzona;
    private UISelectItems selectItems4Subzona;
    private RichSelectOneChoice selectOneChoice1Fuente;
    private UISelectItems selectItems2;
    private RichPanelGroupLayout panelGroupLayout3;
    private RichOutputText outputText1Titulo;
    //Lista de provincias hidrogeologicas
    private List listaProvincias;
    
    public CaptacionesTotalSubBean() {
        FacesUtils.setActiveBean("captacionesTotalSubBean", "Captaciones Sub Total",
                                 UsuariosAguaDelegate.class);

        //FacesUtils.removeManagedBeanFromSession("UsoTreeHandler");
        this.load();
    }

    private void load() {
        try {
            UsuariosAguaDelegate uad = UsuariosAguaDelegate.getInstance();

            this.listaDepartamentos = this.cargarDivipola(null);
            this.listaMunicipios = new ArrayList();
            this.listaUsos =
                    this.cargarParametro(ConstantesCaptaciones.USOS_AGUA);
            this.listaArea = this.cargarZonificacion(null);

            this.listaZona = new ArrayList();
            this.listaSubzona = new ArrayList();
            this.listaFuentes = new ArrayList();
           this.listaProvincias = this.cargarParametro(ConstantesParametros.
                                                  CATEGORIA_PROVINCIA_HIDROGEOLOGICA);
           
            CriteriosBusquedaTO criterios = new CriteriosBusquedaTO();
            UsuarioConectadoTO usuarioConectado =
                (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
            criterios.setAutoridad(usuarioConectado.getUsuario().getAutoridadAmbiental());
            this.listaCaptacionesSub =
                    uad.getAllCaptacionesBusquedaSub(criterios); //todas las captaciones
                    System.out.println("datos a listar:"+listaCaptacionesSub.size());
            
            ParametrosDelegate pd = ParametrosDelegate.getInstance();
            Lista fs = pd.getLista(ConstantesParametros.
                                   LISTA_FUENTE_SUBTERRANEA.intValue());
            
            
            
            co.gov.ideam.sirh.fuentes.model.CriteriosBusquedaTO criteriosTo = new co.gov.ideam.sirh.fuentes.model.CriteriosBusquedaTO();
            
            
            criteriosTo.setAutoridad(usuarioConectado.getUsuario().getAutoridadAmbiental());
            criteriosTo.setTipoFuente(fs);
            
            this.listaFuentes = this.cargarFuentes(criteriosTo);            
       
           
           //Cabril 04/03/2015
           if (listaCaptacionesSub != null) {
               
               Integer i=1;
               
               Iterator<CaptacionSubTO> it = listaCaptacionesSub.iterator();
               while (it.hasNext()) {
                   CaptacionSubTO usu = it.next();
                   usu.setNum(i);
                   i++;
               } 
           }
       
       
       } catch (IdeamException e) {
            showMessage(e);
        }
    }
    ///////////////////////////

    public void predio_actionListener(ActionEvent actionEvent) {
        Long codigoUsuario =
            (Long)actionEvent.getComponent().getAttributes().get("codigoUsuario");
        Long codigoPredio =
            (Long)actionEvent.getComponent().getAttributes().get("codigoPredio");
        FacesUtils.setInSession("usuarioSeleccionado", codigoUsuario);
        FacesUtils.setInSession("predioSeleccionado", codigoPredio);
    }

    public void usuario_actionListener(ActionEvent actionEvent) {
        Long codigoUsuario =
            (Long)actionEvent.getComponent().getAttributes().get("codigoUsuario");
        FacesUtils.setInSession("usuarioSeleccionado", codigoUsuario);
    }

    public void concesion_actionListener(ActionEvent actionEvent) {
        Long codigoUsuario =
            (Long)actionEvent.getComponent().getAttributes().get("codigoUsuario");
        Long codigoPredio =
            (Long)actionEvent.getComponent().getAttributes().get("codigoPredio");
        Long codigo =
            (Long)actionEvent.getComponent().getAttributes().get("codigo");

        FacesUtils.setInSession("concesionSeleccionada", codigo);
        FacesUtils.setInSession("usuarioSeleccionado", codigoUsuario);
        FacesUtils.setInSession("predioSeleccionado", codigoPredio);
        FacesUtils.setInSession("paginaOrigen", "captacionesTotal");
    }

    public void captacion_actionListener(ActionEvent actionEvent) {
        Long codigoCaptacion =
            (Long)actionEvent.getComponent().getAttributes().get("codigoCaptacion");
        Long codigoUsuario =
            (Long)actionEvent.getComponent().getAttributes().get("codigoUsuario");
        Long codigoPredio =
            (Long)actionEvent.getComponent().getAttributes().get("codigoPredio");
        Long codigo =
            (Long)actionEvent.getComponent().getAttributes().get("codigo");
        FacesUtils.setInSession("concesionSeleccionada", codigo);
        FacesUtils.setInSession("usuarioSeleccionado", codigoUsuario);
        FacesUtils.setInSession("predioSeleccionado", codigoPredio);
        FacesUtils.setInSession("captacionSeleccionada", codigoCaptacion);
        FacesUtils.setInSession("paginaOrigen", "captacionesTotal");
    }

    public void cmi_imprimir_actionListener(ActionEvent actionEvent) {
        try {
            HashMap parametros = new HashMap();
            parametros.put("p_modulo", "Registro de Usuarios del Agua");
            parametros.put("p_titulo", "Captaciones Subterraneas");
            this.generateReport("CaptacionesSubt.jasper", this.listaCaptacionesSub,
                                parametros, ReporteDelegate.EXCEL);
        } catch (IdeamException e) {
            showMessage(e);
        }
    }

    public void socFiltroDepartamento_valueChangeListener(ValueChangeEvent event) throws IdeamException {
        Object departamento = event.getNewValue();
        this.listaMunicipios = new ArrayList();
        try {
            if (departamento != null) {
                this.listaMunicipios =
                        this.cargarDivipola(((Divipola)departamento).getId());
            }
        } catch (IdeamException e) {
            showMessage(e);
        }
        AdfFacesContext.getCurrentInstance().addPartialTarget(socFiltroMun);
    }
    
    
    public void selectOneChoiceArea_valueChangeListener(ValueChangeEvent valueChangeEvent) {
        Object area = valueChangeEvent.getNewValue();
        this.listaZona = new ArrayList();
        try {
            if (area != null) {
                this.listaZona =
                        this.cargarZonificacion(((PartZonificListas)area).getId());
            }
        } catch (IdeamException e) {
            showMessage(e);
        }
        AdfFacesContext.getCurrentInstance().addPartialTarget(selectOneChoiceZona);
    }

    public void selectOneChoiceZona_valueChangeListener(ValueChangeEvent valueChangeEvent) {
        Object zona = valueChangeEvent.getNewValue();
        this.listaSubzona = new ArrayList();
        try {
            if (zona != null) {
                this.listaSubzona =
                        this.cargarZonificacion(((PartZonificListas)zona).getId());
            }
        } catch (IdeamException e) {
            showMessage(e);
        }
        AdfFacesContext.getCurrentInstance().addPartialTarget(selectOneChoiceSubzona);
    }

   

    //Buscar

    public void cmdBuscar_actionListener(ActionEvent actionEvent) {
        CriteriosBusquedaTO criterios = new CriteriosBusquedaTO();
        this.listaCaptacionesSub = new ArrayList();
        int i = 0;
        if (this.socFiltroDepartamento.getValue() != null) {
            criterios.setDepartamento((Divipola)this.socFiltroDepartamento.getValue());
            i++;
        }
        if (this.socFiltroMun.getValue() != null) {
            criterios.setMunicipio((Divipola)this.socFiltroMun.getValue());
            i++;
        }
        if (this.selectOneChoiceUsos.getValue() != null) {
            criterios.setUso((Lista)this.selectOneChoiceUsos.getValue());
            i++;
        } 
        if (this.selectOneChoiceArea.getValue() != null) {
            criterios.setArea((PartZonificListas)selectOneChoiceArea.getValue());
            i++;
        } 
        if (this.selectOneChoiceZona.getValue() != null) {
            criterios.setZona((PartZonificListas)selectOneChoiceZona.getValue());
            i++;
        } 
        if (this.selectOneChoiceSubzona.getValue() != null) {
            criterios.setSubzona( (PartZonificListas)selectOneChoiceSubzona.getValue());
            i++;
        } 
        if (this.selectOneChoice1Fuente.getValue() != null) {
            FnttFuente f = (FnttFuente)selectOneChoice1Fuente.getValue();
            criterios.setIdFuente( new Integer(f.getId().intValue()));
            i++;
        } 
        if (this.socProvinciaHidro.getValue() != null) {
            criterios.setProvinciahidro((Lista)this.socProvinciaHidro.getValue());
            i++;
        } 
        
        

        try {
            UsuarioConectadoTO usuarioConectado =
                (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
            criterios.setAutoridad(usuarioConectado.getUsuario().getAutoridadAmbiental());
            UsuariosAguaDelegate uad = UsuariosAguaDelegate.getInstance();
            this.listaCaptacionesSub =
                    uad.getAllCaptacionesBusquedaSub(criterios); //todas las captaciones
                    System.out.println("datos a listar:"+listaCaptacionesSub.size());
            
            
            
            //Cabril 04/03/2015
            if (listaCaptacionesSub != null) {
                
                Integer j=1;
                
                Iterator<CaptacionSubTO> it = listaCaptacionesSub.iterator();
                while (it.hasNext()) {
                    CaptacionSubTO usu = it.next();
                    usu.setNum(j);
                    j++;
                } 
            }
            
            
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.t_captaciones); //solo las captaciones de la autoridad
        } catch (IdeamException e) {
            showMessage(e);
        }
    }

    ///////////////////////

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

    public void setPanelGroupLayout1(RichPanelGroupLayout panelGroupLayout1) {
        this.panelGroupLayout1 = panelGroupLayout1;
    }

    public RichPanelGroupLayout getPanelGroupLayout1() {
        return panelGroupLayout1;
    }

    public void setPanelGroupLayout2(RichPanelGroupLayout panelGroupLayout2) {
        this.panelGroupLayout2 = panelGroupLayout2;
    }

    public RichPanelGroupLayout getPanelGroupLayout2() {
        return panelGroupLayout2;
    }

    public void setClTodos(RichCommandLink commandLink1) {
        this.clTodos = commandLink1;
    }

    public RichCommandLink getClTodos() {
        return clTodos;
    }

    public void setSpacer1(RichSpacer spacer1) {
        this.spacer1 = spacer1;
    }

    public RichSpacer getSpacer1() {
        return spacer1;
    }

    public void setPanelCollection1(RichPanelCollection panelCollection1) {
        this.panelCollection1 = panelCollection1;
    }

    public RichPanelCollection getPanelCollection1() {
        return panelCollection1;
    }

    public void setMenu1(RichMenu menu1) {
        this.menu1 = menu1;
    }

    public RichMenu getMenu1() {
        return menu1;
    }

    public void setCmi_imprimir(RichCommandMenuItem commandMenuItem1) {
        this.cmi_imprimir = commandMenuItem1;
    }

    public RichCommandMenuItem getCmi_imprimir() {
        return cmi_imprimir;
    }

    public void setT_captaciones(RichTable table1) {
        this.t_captaciones = table1;
    }

    public RichTable getT_captaciones() {
        return t_captaciones;
    }

    public void setlistaCaptacionesSub(List listaCaptacionesSub) {
        this.listaCaptacionesSub = listaCaptacionesSub;
    }

    public List getlistaCaptacionesSub() {
        return listaCaptacionesSub;
    }

    public void setDetalleCaptacion(String detalleCaptacion) {
        this.detalleCaptacion = detalleCaptacion;
    }

    public String getDetalleCaptacion() {
        return detalleCaptacion;
    }

    public void setListaDepartamentos(List listaDepartamentos) {
        this.listaDepartamentos = listaDepartamentos;
    }

    public List getListaDepartamentos() {
        return listaDepartamentos;
    }

    public void setListaMunicipios(List listaMunicipios) {
        this.listaMunicipios = listaMunicipios;
    }

    public List getListaMunicipios() {
        return listaMunicipios;
    }

    public void setSpacer2(RichSpacer spacer2) {
        this.spacer2 = spacer2;
    }

    public RichSpacer getSpacer2() {
        return spacer2;
    }

    public void setClUsuario(RichCommandLink commandLink2) {
        this.clUsuario = commandLink2;
    }

    public RichCommandLink getClUsuario() {
        return clUsuario;
    }

    public void setSpacer3(RichSpacer spacer3) {
        this.spacer3 = spacer3;
    }

    public RichSpacer getSpacer3() {
        return spacer3;
    }

    public void setClPredio(RichCommandLink commandLink2) {
        this.clPredio = commandLink2;
    }

    public RichCommandLink getClPredio() {
        return clPredio;
    }

    public void setSpacer4(RichSpacer spacer4) {
        this.spacer4 = spacer4;
    }

    public RichSpacer getSpacer4() {
        return spacer4;
    }

    public void setClConcesiones(RichCommandLink commandLink1) {
        this.clConcesiones = commandLink1;
    }

    public RichCommandLink getClConcesiones() {
        return clConcesiones;
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

    public void setSocFiltroDepartamento(RichSelectOneChoice selectOneChoice1) {
        this.socFiltroDepartamento = selectOneChoice1;
    }

    public RichSelectOneChoice getSocFiltroDepartamento() {
        return socFiltroDepartamento;
    }

    public void setSiFiltroDep(UISelectItems selectItems1) {
        this.siFiltroDep = selectItems1;
    }

    public UISelectItems getSiFiltroDep() {
        return siFiltroDep;
    }

    public void setSocFiltroMun(RichSelectOneChoice selectOneChoice2) {
        this.socFiltroMun = selectOneChoice2;
    }

    public RichSelectOneChoice getSocFiltroMun() {
        return socFiltroMun;
    }

    public void setSiFiltroMun(UISelectItems selectItems2) {
        this.siFiltroMun = selectItems2;
    }

    public UISelectItems getSiFiltroMun() {
        return siFiltroMun;
    }

    public void setCmdBuscar(RichCommandButton commandButton1) {
        this.cmdBuscar = commandButton1;
    }

    public RichCommandButton getCmdBuscar() {
        return cmdBuscar;
    }

    public void setListaUsos(List listaUsos) {
        this.listaUsos = listaUsos;
    }

    public List getListaUsos() {
        return listaUsos;
    }

    public void setSelectOneChoiceUsos(RichSelectOneChoice selectOneChoice1) {
        this.selectOneChoiceUsos = selectOneChoice1;
    }

    public RichSelectOneChoice getSelectOneChoiceUsos() {
        return selectOneChoiceUsos;
    }

    public void setSelectItems1(UISelectItems selectItems1) {
        this.selectItems1 = selectItems1;
    }

    public UISelectItems getSelectItems1() {
        return selectItems1;
    }

    public void selectOneChoiceUsos_valueChangeListener(ValueChangeEvent valueChangeEvent) {
        // Add event code here...
    }

    public void setListaArea(List listaArea) {
        this.listaArea = listaArea;
    }

    public List getListaArea() {
        return listaArea;
    }

    public void setListaZona(List listaZona) {
        this.listaZona = listaZona;
    }

    public List getListaZona() {
        return listaZona;
    }

    public void setListaSubzona(List listaSubzona) {
        this.listaSubzona = listaSubzona;
    }

    public List getListaSubzona() {
        return listaSubzona;
    }

    public void setSelectOneChoiceArea(RichSelectOneChoice selectOneChoice1) {
        this.selectOneChoiceArea = selectOneChoice1;
    }

    public RichSelectOneChoice getSelectOneChoiceArea() {
        return selectOneChoiceArea;
    }

    public void setSelectItems2Area(UISelectItems selectItems2) {
        this.selectItems2Area = selectItems2;
    }

    public UISelectItems getSelectItems2Area() {
        return selectItems2Area;
    }

    public void setSelectOneChoiceZona(RichSelectOneChoice selectOneChoice2) {
        this.selectOneChoiceZona = selectOneChoice2;
    }

    public RichSelectOneChoice getSelectOneChoiceZona() {
        return selectOneChoiceZona;
    }

    public void setSelectItems3Zona(UISelectItems selectItems3) {
        this.selectItems3Zona = selectItems3;
    }

    public UISelectItems getSelectItems3Zona() {
        return selectItems3Zona;
    }

    public void setSelectOneChoiceSubzona(RichSelectOneChoice selectOneChoice1) {
        this.selectOneChoiceSubzona = selectOneChoice1;
    }

    public RichSelectOneChoice getSelectOneChoiceSubzona() {
        return selectOneChoiceSubzona;
    }

    public void setSelectItems4Subzona(UISelectItems selectItems4) {
        this.selectItems4Subzona = selectItems4;
    }

    public UISelectItems getSelectItems4Subzona() {
        return selectItems4Subzona;
    }

    public void setListaFuentes(List listaFuentes) {
        this.listaFuentes = listaFuentes;
    }

    public List getListaFuentes() {
        return listaFuentes;
    }

    public void setSelectOneChoice1Fuente(RichSelectOneChoice selectOneChoice1) {
        this.selectOneChoice1Fuente = selectOneChoice1;
    }

    public RichSelectOneChoice getSelectOneChoice1Fuente() {
        return selectOneChoice1Fuente;
    }

    public void setSelectItems2(UISelectItems selectItems2) {
        this.selectItems2 = selectItems2;
    }

    public UISelectItems getSelectItems2() {
        return selectItems2;
    }


    public void setPanelGroupLayout3(RichPanelGroupLayout panelGroupLayout3) {
        this.panelGroupLayout3 = panelGroupLayout3;
    }

    public RichPanelGroupLayout getPanelGroupLayout3() {
        return panelGroupLayout3;
    }

    public void setOutputText1Titulo(RichOutputText outputText1) {
        this.outputText1Titulo = outputText1;
    }

    public RichOutputText getOutputText1Titulo() {
        return outputText1Titulo;
    }

    public void setSocProvinciaHidro(RichSelectOneChoice socProvinciaHidro) {
        this.socProvinciaHidro = socProvinciaHidro;
    }

    public RichSelectOneChoice getSocProvinciaHidro() {
        return socProvinciaHidro;
    }

    public void setSiProvinciaHidro(UISelectItems siProvinciaHidro) {
        this.siProvinciaHidro = siProvinciaHidro;
    }

    public UISelectItems getSiProvinciaHidro() {
        return siProvinciaHidro;
    }

    public void setListaProvincias(List listaProvincias) {
        this.listaProvincias = listaProvincias;
    }

    public List getListaProvincias() {
        return listaProvincias;
    }
}
