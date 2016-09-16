package co.gov.ideam.sirh.fuentes.view;

import co.gov.ideam.sirh.fuentes.model.FnttConflicto;
import co.gov.ideam.sirh.fuentes.model.FnttFuente;
import co.gov.ideam.sirh.fuentes.model.FnttTramo;
import co.gov.ideam.sirh.parametros.model.Divipola;
import co.gov.ideam.sirh.parametros.model.Lista;
import co.gov.ideam.sirh.red.ideam.model.SirhvPuntoMonitoreoFq;
import co.gov.ideam.sirh.util.ConstantesParametros;
import co.gov.ideam.sirh.util.IdeamException;

import co.gov.ideam.sirh.view.FacesUtils;

import co.gov.ideam.sirh.view.StandarBean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UISelectItems;
import javax.faces.event.ActionEvent;

import javax.faces.event.ValueChangeEvent;

import javax.faces.model.SelectItem;

import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectBooleanRadio;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.layout.RichPanelBox;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelSplitter;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandLink;
import oracle.adf.view.rich.component.rich.output.RichActiveOutputText;
import oracle.adf.view.rich.component.rich.output.RichOutputLabel;
import oracle.adf.view.rich.component.rich.output.RichSpacer;
import oracle.adf.view.rich.context.AdfFacesContext;

import org.apache.myfaces.trinidad.event.SelectionEvent;

public class ConflictosXFuenteBean extends ConflictosXFuente{

    private List listaFuentes;
    private List listaConflictos;
    private FnttConflicto conflicto;
    private FnttFuente fuente;
    
    private List listaTramos;
    private List sistemasReferencia;
    private List listaDepartamentos;
    private List listaMunicipios;
    private Boolean estadoAbiertoAct;
    private Boolean estadoCerradoAct;
    private List listaGestionRealizada;

    private Lista sistemaRefAct;
    private FnttTramo tramoAct;
    private Divipola deptoAct;
    private Divipola municAct;
   
    private RichForm form1;
    private RichDocument document1;
    private RichPanelStretchLayout panelStretchLayout1;
    private RichPanelSplitter panelSplitter1;
    private RichPanelFormLayout panelFormLayout1;
    private RichPanelGroupLayout panelGroupLayout1;
    private RichSpacer spacer1;
    private RichSpacer spacer2;
    private RichCommandLink commandLink1;
    private RichSpacer spacer3;
    private RichCommandLink commandLink2;
    private RichSelectOneChoice sc_fuentes;
    private UISelectItems selectFuentes;
    private RichPanelGroupLayout panelGroupLayout2;
    private RichPanelFormLayout panelFormLayout2;
    private RichSpacer spacer4;
    private RichOutputLabel otNota;
    private RichActiveOutputText otNota2;
    private RichSpacer spacer5;
    private RichSelectOneChoice soc_conflicto;
    private UISelectItems si_conflicto;
    private RichPanelBox pb1;
    private RichPanelFormLayout pfl21;
    private RichInputText it_nombre1;
    private RichPanelStretchLayout psl1;
    private RichPanelGroupLayout panelGroupLayout3;
    private RichPanelFormLayout pfl1;
    private RichSelectOneChoice soc_Tramos1;
    private UISelectItems selectItems71;
    private RichInputText it_nombre2;
    private RichInputText it_Radicado1;
    private RichInputDate id_fecha_vigencia1;
    private RichInputText it_descripcion1;
    private RichSelectBooleanRadio sbrAbierto1;
    private RichSelectBooleanRadio sbrCerrado1;
    private RichInputDate id_fechaCierre1;
    private RichPanelGroupLayout panelGroupLayout4;
    private RichPanelGroupLayout panelGroupLayout5;
    private RichPanelFormLayout panelFormLayout3;
    private RichSpacer spacer6;
    private RichActiveOutputText activeOutputText2;
    private RichSpacer spacer10;
    private RichSelectOneChoice soc_sistema;
    private UISelectItems selectItems8;
    private RichPanelGroupLayout panelGroupLayout6;
    private RichPanelFormLayout panelFormLayout5;
    private RichSpacer spacer7;
    private RichOutputLabel outputLabel3;
    private RichPanelFormLayout panelFormLayout4;
    private RichSpacer spacer8;
    private RichOutputLabel outputLabel1;
    private RichInputText it_grados_pi;
    private RichInputText it_minutos_pi;
    private RichInputText it_segundos_pi;
    private RichPanelFormLayout panelFormLayout6;
    private RichSpacer spacer9;
    private RichOutputLabel outputLabel2;
    private RichInputText it_grad_long_pi;
    private RichInputText it_minutos_long_pi;
    private RichInputText it_segundos_long_pi;
    private RichPanelFormLayout panelFormLayout7;
    private RichInputText it_altitud_pi;
    private RichPanelFormLayout pfl22;
    private RichSelectOneChoice soc_departamento;
    private UISelectItems selectItems4;
    private RichSelectOneChoice soc_municipio;
    private UISelectItems selectItems5;
    private RichPanelFormLayout panelFormLayout8;
    private RichSpacer spacer11;
    private RichPanelFormLayout panelFormLayout9;
    private RichPanelGroupLayout panelGroupLayout41;
    private RichTable tb_gestion;
    private RichActiveOutputText activeOutputText1;

    public ConflictosXFuenteBean() {
        FacesUtils.setActiveBean("ConflictosXFuenteBean", "ConflictosXFuenteBean",
                                 ConflictosXFuenteBean.class);
        this.load();
    }
    
    public void load() {
        try{
            
            listaFuentes = getListaFuentesConflicto(null);

            listaConflictos = new ArrayList();
            conflicto = new FnttConflicto();
            fuente = new FnttFuente();
            
            listaTramos = new ArrayList();;
            sistemasReferencia = new ArrayList();
            listaDepartamentos = new ArrayList();;
            listaMunicipios = new ArrayList();;
            estadoAbiertoAct = true;
            estadoCerradoAct = true;

            sistemaRefAct = new Lista();
            tramoAct = new FnttTramo();
            deptoAct = new Divipola();
            municAct = new Divipola() ;

            listaGestionRealizada = new ArrayList();
            
        } catch (IdeamException e) {
            showMessage(e);
        }
    }
    
    public void soc_Fuentes_valueChangeListener(ValueChangeEvent event) throws IdeamException {

                
        FnttFuente fuenteAct = (FnttFuente)event.getNewValue();
        this.listaConflictos = new ArrayList();
        try {
            this.fuente = fuenteAct;
        
                
            if (fuente != null)
                this.listaConflictos = getListaConflictosXFuente(fuente.getId());
            
          
            
        } catch (IdeamException e) {
            showMessage(e);
        }
        
        AdfFacesContext.getCurrentInstance().addPartialTarget(soc_conflicto);
    }

    public void soc_Conflcitos_valueChangeListener(ValueChangeEvent event) throws IdeamException {


        FnttConflicto conflictoAct = (FnttConflicto)event.getNewValue();
        try {

            this.conflicto = conflictoAct;

            
            listaTramos = getListaTramos(fuente.getId().intValue());

            FnttTramo t = new FnttTramo();
            t.setId(0L);

            SelectItem si = new SelectItem(t, "SIN TRAMO ESPECIFICO");
            listaTramos.add(si);


            sistemasReferencia =
                    cargarParametro(ConstantesParametros.CATEGORIA_SISTEMA_REFERENCIA);


            listaDepartamentos = cargarDivipola(null);
            listaMunicipios =
                    cargarDivipola(new Long(conflicto.getIdDepartamento()));


            Iterator it = sistemasReferencia.iterator();
            while (it.hasNext()) {
                SelectItem sr = (SelectItem)it.next();
                if (conflicto.getSistemaReferencia() != null)
                    if (((Lista)sr.getValue()).getCodigo().compareTo(conflicto.getSistemaReferencia().intValue()) == 0)
                        sistemaRefAct = (Lista)sr.getValue();
            }


            it = listaTramos.iterator();
            while (it.hasNext()) {
                SelectItem tr = (SelectItem)it.next();

                if (((FnttTramo)tr.getValue()).getId().compareTo(conflicto.getIdTramo()) == 0) {
                    tramoAct = (FnttTramo)tr.getValue();
                }
            }


            it = listaDepartamentos.iterator();
            while (it.hasNext()) {
                SelectItem dp = (SelectItem)it.next();
                if (((Divipola)dp.getValue()).getId().compareTo(new Long(conflicto.getIdDepartamento())) == 0)
                    deptoAct = ((Divipola)dp.getValue());
            }

            it = listaMunicipios.iterator();
            while (it.hasNext()) {
                SelectItem mu = (SelectItem)it.next();
                if (((Divipola)mu.getValue()).getId().compareTo(new Long(conflicto.getIdMunicipio())) == 0)
                    municAct = (Divipola)mu.getValue();
            }

            estadoAbiertoAct = false;

            if (conflicto.getEstadoConflicto() == 0)
                estadoAbiertoAct = true;

            estadoCerradoAct = !estadoAbiertoAct;

            System.out.println("HCP paso 9");
            
            FuenteDelegate fd = FuenteDelegate.getInstance();
            conflicto.setListaGestionConflicto(fd.getGestionConflictoXConflicto(conflicto.getId()));
            this.listaGestionRealizada = conflicto.getListaGestionConflicto();

            AdfFacesContext.getCurrentInstance().addPartialTarget(soc_Tramos1);
            AdfFacesContext.getCurrentInstance().addPartialTarget(it_nombre2);
            AdfFacesContext.getCurrentInstance().addPartialTarget(it_Radicado1);
            AdfFacesContext.getCurrentInstance().addPartialTarget(id_fecha_vigencia1);
            AdfFacesContext.getCurrentInstance().addPartialTarget(it_descripcion1);
            AdfFacesContext.getCurrentInstance().addPartialTarget(sbrAbierto1);
            AdfFacesContext.getCurrentInstance().addPartialTarget(sbrCerrado1);
            AdfFacesContext.getCurrentInstance().addPartialTarget(id_fechaCierre1);
            AdfFacesContext.getCurrentInstance().addPartialTarget(soc_sistema);
            AdfFacesContext.getCurrentInstance().addPartialTarget(it_grados_pi);
            AdfFacesContext.getCurrentInstance().addPartialTarget(it_minutos_pi);
            AdfFacesContext.getCurrentInstance().addPartialTarget(it_segundos_pi);
            AdfFacesContext.getCurrentInstance().addPartialTarget(it_grad_long_pi);
            AdfFacesContext.getCurrentInstance().addPartialTarget(it_minutos_long_pi);
            AdfFacesContext.getCurrentInstance().addPartialTarget(it_segundos_long_pi);
            AdfFacesContext.getCurrentInstance().addPartialTarget(it_altitud_pi);
            AdfFacesContext.getCurrentInstance().addPartialTarget(soc_departamento);
            AdfFacesContext.getCurrentInstance().addPartialTarget(soc_municipio);
            AdfFacesContext.getCurrentInstance().addPartialTarget(tb_gestion);


        } catch (IdeamException e) {
            showMessage(e);
        }

        AdfFacesContext.getCurrentInstance().addPartialTarget(soc_conflicto);
    }
    
    public void tresult_selectionListener(SelectionEvent selectionEvent) {
        RichTable tresult1 = (RichTable)selectionEvent.getSource();
    }
    
    public void cmiConsultar_actionListener(ActionEvent actionEvent) {
        accion = "detalleConflicto";
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

    public void setSpacer1(RichSpacer spacer1) {
        this.spacer1 = spacer1;
    }

    public RichSpacer getSpacer1() {
        return spacer1;
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

    public void setSpacer3(RichSpacer spacer3) {
        this.spacer3 = spacer3;
    }

    public RichSpacer getSpacer3() {
        return spacer3;
    }

    public void setCommandLink2(RichCommandLink commandLink2) {
        this.commandLink2 = commandLink2;
    }

    public RichCommandLink getCommandLink2() {
        return commandLink2;
    }

    public void setSc_fuentes(RichSelectOneChoice sc_fuentes) {
        this.sc_fuentes = sc_fuentes;
    }

    public RichSelectOneChoice getSc_fuentes() {
        return sc_fuentes;
    }

    public void setSelectFuentes(UISelectItems selectFuentes) {
        this.selectFuentes = selectFuentes;
    }

    public UISelectItems getSelectFuentes() {
        return selectFuentes;
    }

    public void setPanelGroupLayout2(RichPanelGroupLayout panelGroupLayout2) {
        this.panelGroupLayout2 = panelGroupLayout2;
    }

    public RichPanelGroupLayout getPanelGroupLayout2() {
        return panelGroupLayout2;
    }

    public void setPanelFormLayout2(RichPanelFormLayout panelFormLayout2) {
        this.panelFormLayout2 = panelFormLayout2;
    }

    public RichPanelFormLayout getPanelFormLayout2() {
        return panelFormLayout2;
    }

    public void setSpacer4(RichSpacer spacer4) {
        this.spacer4 = spacer4;
    }

    public RichSpacer getSpacer4() {
        return spacer4;
    }

    public void setOtNota(RichOutputLabel otNota) {
        this.otNota = otNota;
    }

    public RichOutputLabel getOtNota() {
        return otNota;
    }

    public void setOtNota2(RichActiveOutputText otNota2) {
        this.otNota2 = otNota2;
    }

    public RichActiveOutputText getOtNota2() {
        return otNota2;
    }

    public void setListaFuentes(List listaFuentes) {
        this.listaFuentes = listaFuentes;
    }

    public List getListaFuentes() {
        return listaFuentes;
    }

    public void setSpacer5(RichSpacer spacer5) {
        this.spacer5 = spacer5;
    }

    public RichSpacer getSpacer5() {
        return spacer5;
    }

    public void setSoc_conflicto(RichSelectOneChoice selectOneChoice1) {
        this.soc_conflicto = selectOneChoice1;
    }

    public RichSelectOneChoice getSoc_conflicto() {
        return soc_conflicto;
    }

    public void setSi_conflicto(UISelectItems selectItems1) {
        this.si_conflicto = selectItems1;
    }

    public UISelectItems getSi_conflicto() {
        return si_conflicto;
    }

    public void setListaConflictos(List listaConflictos) {
        this.listaConflictos = listaConflictos;
    }

    public List getListaConflictos() {
        return listaConflictos;
    }


    public void setPb1(RichPanelBox pb1) {
        this.pb1 = pb1;
    }

    public RichPanelBox getPb1() {
        return pb1;
    }

    public void setPfl21(RichPanelFormLayout pfl21) {
        this.pfl21 = pfl21;
    }

    public RichPanelFormLayout getPfl21() {
        return pfl21;
    }

    public void setIt_nombre1(RichInputText it_nombre1) {
        this.it_nombre1 = it_nombre1;
    }

    public RichInputText getIt_nombre1() {
        return it_nombre1;
    }

    public void setPsl1(RichPanelStretchLayout psl1) {
        this.psl1 = psl1;
    }

    public RichPanelStretchLayout getPsl1() {
        return psl1;
    }


    public void setPanelGroupLayout3(RichPanelGroupLayout panelGroupLayout3) {
        this.panelGroupLayout3 = panelGroupLayout3;
    }

    public RichPanelGroupLayout getPanelGroupLayout3() {
        return panelGroupLayout3;
    }

    public void setPfl1(RichPanelFormLayout pfl1) {
        this.pfl1 = pfl1;
    }

    public RichPanelFormLayout getPfl1() {
        return pfl1;
    }

    public void setSoc_Tramos1(RichSelectOneChoice soc_Tramos1) {
        this.soc_Tramos1 = soc_Tramos1;
    }

    public RichSelectOneChoice getSoc_Tramos1() {
        return soc_Tramos1;
    }

    public void setSelectItems71(UISelectItems selectItems71) {
        this.selectItems71 = selectItems71;
    }

    public UISelectItems getSelectItems71() {
        return selectItems71;
    }

    public void setIt_nombre2(RichInputText it_nombre2) {
        this.it_nombre2 = it_nombre2;
    }

    public RichInputText getIt_nombre2() {
        return it_nombre2;
    }

    public void setIt_Radicado1(RichInputText it_Radicado1) {
        this.it_Radicado1 = it_Radicado1;
    }

    public RichInputText getIt_Radicado1() {
        return it_Radicado1;
    }

    public void setId_fecha_vigencia1(RichInputDate id_fecha_vigencia1) {
        this.id_fecha_vigencia1 = id_fecha_vigencia1;
    }

    public RichInputDate getId_fecha_vigencia1() {
        return id_fecha_vigencia1;
    }

    public void setIt_descripcion1(RichInputText it_descripcion1) {
        this.it_descripcion1 = it_descripcion1;
    }

    public RichInputText getIt_descripcion1() {
        return it_descripcion1;
    }

    public void setSbrAbierto1(RichSelectBooleanRadio sbrAbierto1) {
        this.sbrAbierto1 = sbrAbierto1;
    }

    public RichSelectBooleanRadio getSbrAbierto1() {
        return sbrAbierto1;
    }

    public void setSbrCerrado1(RichSelectBooleanRadio sbrCerrado1) {
        this.sbrCerrado1 = sbrCerrado1;
    }

    public RichSelectBooleanRadio getSbrCerrado1() {
        return sbrCerrado1;
    }

    public void setId_fechaCierre1(RichInputDate id_fechaCierre1) {
        this.id_fechaCierre1 = id_fechaCierre1;
    }

    public RichInputDate getId_fechaCierre1() {
        return id_fechaCierre1;
    }

    public void setPanelGroupLayout4(RichPanelGroupLayout panelGroupLayout4) {
        this.panelGroupLayout4 = panelGroupLayout4;
    }

    public RichPanelGroupLayout getPanelGroupLayout4() {
        return panelGroupLayout4;
    }

    public void setPanelGroupLayout5(RichPanelGroupLayout panelGroupLayout5) {
        this.panelGroupLayout5 = panelGroupLayout5;
    }

    public RichPanelGroupLayout getPanelGroupLayout5() {
        return panelGroupLayout5;
    }

    public void setPanelFormLayout3(RichPanelFormLayout panelFormLayout3) {
        this.panelFormLayout3 = panelFormLayout3;
    }

    public RichPanelFormLayout getPanelFormLayout3() {
        return panelFormLayout3;
    }

    public void setSpacer6(RichSpacer spacer6) {
        this.spacer6 = spacer6;
    }

    public RichSpacer getSpacer6() {
        return spacer6;
    }

    public void setActiveOutputText2(RichActiveOutputText activeOutputText2) {
        this.activeOutputText2 = activeOutputText2;
    }

    public RichActiveOutputText getActiveOutputText2() {
        return activeOutputText2;
    }

    public void setSpacer10(RichSpacer spacer10) {
        this.spacer10 = spacer10;
    }

    public RichSpacer getSpacer10() {
        return spacer10;
    }

    public void setSoc_sistema(RichSelectOneChoice soc_sistema) {
        this.soc_sistema = soc_sistema;
    }

    public RichSelectOneChoice getSoc_sistema() {
        return soc_sistema;
    }

    public void setSelectItems8(UISelectItems selectItems8) {
        this.selectItems8 = selectItems8;
    }

    public UISelectItems getSelectItems8() {
        return selectItems8;
    }

    public void setPanelGroupLayout6(RichPanelGroupLayout panelGroupLayout6) {
        this.panelGroupLayout6 = panelGroupLayout6;
    }

    public RichPanelGroupLayout getPanelGroupLayout6() {
        return panelGroupLayout6;
    }

    public void setPanelFormLayout5(RichPanelFormLayout panelFormLayout5) {
        this.panelFormLayout5 = panelFormLayout5;
    }

    public RichPanelFormLayout getPanelFormLayout5() {
        return panelFormLayout5;
    }

    public void setSpacer7(RichSpacer spacer7) {
        this.spacer7 = spacer7;
    }

    public RichSpacer getSpacer7() {
        return spacer7;
    }

    public void setOutputLabel3(RichOutputLabel outputLabel3) {
        this.outputLabel3 = outputLabel3;
    }

    public RichOutputLabel getOutputLabel3() {
        return outputLabel3;
    }

    public void setPanelFormLayout4(RichPanelFormLayout panelFormLayout4) {
        this.panelFormLayout4 = panelFormLayout4;
    }

    public RichPanelFormLayout getPanelFormLayout4() {
        return panelFormLayout4;
    }

    public void setSpacer8(RichSpacer spacer8) {
        this.spacer8 = spacer8;
    }

    public RichSpacer getSpacer8() {
        return spacer8;
    }

    public void setOutputLabel1(RichOutputLabel outputLabel1) {
        this.outputLabel1 = outputLabel1;
    }

    public RichOutputLabel getOutputLabel1() {
        return outputLabel1;
    }

    public void setIt_grados_pi(RichInputText it_grados_pi) {
        this.it_grados_pi = it_grados_pi;
    }

    public RichInputText getIt_grados_pi() {
        return it_grados_pi;
    }

    public void setIt_minutos_pi(RichInputText it_minutos_pi) {
        this.it_minutos_pi = it_minutos_pi;
    }

    public RichInputText getIt_minutos_pi() {
        return it_minutos_pi;
    }

    public void setIt_segundos_pi(RichInputText it_segundos_pi) {
        this.it_segundos_pi = it_segundos_pi;
    }

    public RichInputText getIt_segundos_pi() {
        return it_segundos_pi;
    }

    public void setPanelFormLayout6(RichPanelFormLayout panelFormLayout6) {
        this.panelFormLayout6 = panelFormLayout6;
    }

    public RichPanelFormLayout getPanelFormLayout6() {
        return panelFormLayout6;
    }

    public void setSpacer9(RichSpacer spacer9) {
        this.spacer9 = spacer9;
    }

    public RichSpacer getSpacer9() {
        return spacer9;
    }

    public void setOutputLabel2(RichOutputLabel outputLabel2) {
        this.outputLabel2 = outputLabel2;
    }

    public RichOutputLabel getOutputLabel2() {
        return outputLabel2;
    }

    public void setIt_grad_long_pi(RichInputText it_grad_long_pi) {
        this.it_grad_long_pi = it_grad_long_pi;
    }

    public RichInputText getIt_grad_long_pi() {
        return it_grad_long_pi;
    }

    public void setIt_minutos_long_pi(RichInputText it_minutos_long_pi) {
        this.it_minutos_long_pi = it_minutos_long_pi;
    }

    public RichInputText getIt_minutos_long_pi() {
        return it_minutos_long_pi;
    }

    public void setIt_segundos_long_pi(RichInputText it_segundos_long_pi) {
        this.it_segundos_long_pi = it_segundos_long_pi;
    }

    public RichInputText getIt_segundos_long_pi() {
        return it_segundos_long_pi;
    }

    public void setPanelFormLayout7(RichPanelFormLayout panelFormLayout7) {
        this.panelFormLayout7 = panelFormLayout7;
    }

    public RichPanelFormLayout getPanelFormLayout7() {
        return panelFormLayout7;
    }

    public void setIt_altitud_pi(RichInputText it_altitud_pi) {
        this.it_altitud_pi = it_altitud_pi;
    }

    public RichInputText getIt_altitud_pi() {
        return it_altitud_pi;
    }


    public void setPfl22(RichPanelFormLayout pfl22) {
        this.pfl22 = pfl22;
    }

    public RichPanelFormLayout getPfl22() {
        return pfl22;
    }

    public void setSoc_departamento(RichSelectOneChoice soc_departamento) {
        this.soc_departamento = soc_departamento;
    }

    public RichSelectOneChoice getSoc_departamento() {
        return soc_departamento;
    }

    public void setSelectItems4(UISelectItems selectItems4) {
        this.selectItems4 = selectItems4;
    }

    public UISelectItems getSelectItems4() {
        return selectItems4;
    }

    public void setSoc_municipio(RichSelectOneChoice soc_municipio) {
        this.soc_municipio = soc_municipio;
    }

    public RichSelectOneChoice getSoc_municipio() {
        return soc_municipio;
    }

    public void setSelectItems5(UISelectItems selectItems5) {
        this.selectItems5 = selectItems5;
    }

    public UISelectItems getSelectItems5() {
        return selectItems5;
    }

    public void setConflicto(FnttConflicto conflicto) {
        this.conflicto = conflicto;
    }

    public FnttConflicto getConflicto() {
        return conflicto;
    }

    public void setFuente(FnttFuente fuente) {
        this.fuente = fuente;
    }

    public FnttFuente getFuente() {
        return fuente;
    }

    public void setSistemasReferencia(List sistemasReferencia) {
        this.sistemasReferencia = sistemasReferencia;
    }

    public List getSistemasReferencia() {
        return sistemasReferencia;
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

    public void setEstadoAbiertoAct(Boolean estadoAbiertoAct) {
        this.estadoAbiertoAct = estadoAbiertoAct;
    }

    public Boolean getEstadoAbiertoAct() {
        return estadoAbiertoAct;
    }

    public void setEstadoCerradoAct(Boolean estadoCerradoAct) {
        this.estadoCerradoAct = estadoCerradoAct;
    }

    public Boolean getEstadoCerradoAct() {
        return estadoCerradoAct;
    }

    public void setSistemaRefAct(Lista sistemaRefAct) {
        this.sistemaRefAct = sistemaRefAct;
    }

    public Lista getSistemaRefAct() {
        return sistemaRefAct;
    }

    public void setTramoAct(FnttTramo tramoAct) {
        this.tramoAct = tramoAct;
    }

    public FnttTramo getTramoAct() {
        return tramoAct;
    }

    public void setDeptoAct(Divipola deptoAct) {
        this.deptoAct = deptoAct;
    }

    public Divipola getDeptoAct() {
        return deptoAct;
    }

    public void setMunicAct(Divipola municAct) {
        this.municAct = municAct;
    }

    public Divipola getMunicAct() {
        return municAct;
    }

    public void setListaTramos(List listaTramos) {
        this.listaTramos = listaTramos;
    }

    public List getListaTramos() {
        return listaTramos;
    }

    public void setPanelFormLayout8(RichPanelFormLayout panelFormLayout8) {
        this.panelFormLayout8 = panelFormLayout8;
    }

    public RichPanelFormLayout getPanelFormLayout8() {
        return panelFormLayout8;
    }

    public void setSpacer11(RichSpacer spacer11) {
        this.spacer11 = spacer11;
    }

    public RichSpacer getSpacer11() {
        return spacer11;
    }


    public void setPanelFormLayout9(RichPanelFormLayout panelFormLayout9) {
        this.panelFormLayout9 = panelFormLayout9;
    }

    public RichPanelFormLayout getPanelFormLayout9() {
        return panelFormLayout9;
    }


    public void setPanelGroupLayout41(RichPanelGroupLayout panelGroupLayout41) {
        this.panelGroupLayout41 = panelGroupLayout41;
    }

    public RichPanelGroupLayout getPanelGroupLayout41() {
        return panelGroupLayout41;
    }

    public void setTb_gestion(RichTable tb_gestion) {
        this.tb_gestion = tb_gestion;
    }

    public RichTable getTb_gestion() {
        return tb_gestion;
    }

    public void setListaGestionRealizada(List listaGestionRealizada) {
        this.listaGestionRealizada = listaGestionRealizada;
    }

    public List getListaGestionRealizada() {
        return listaGestionRealizada;
    }


    public void setActiveOutputText1(RichActiveOutputText activeOutputText1) {
        this.activeOutputText1 = activeOutputText1;
    }

    public RichActiveOutputText getActiveOutputText1() {
        return activeOutputText1;
    }
}
