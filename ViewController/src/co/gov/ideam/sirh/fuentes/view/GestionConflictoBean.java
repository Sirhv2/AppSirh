package co.gov.ideam.sirh.fuentes.view;


import co.gov.ideam.sirh.fuentes.model.FnttConflicto;
import co.gov.ideam.sirh.fuentes.model.FnttFuente;
import co.gov.ideam.sirh.fuentes.model.GestionConflicto;
import co.gov.ideam.sirh.parametros.model.Lista;
import co.gov.ideam.sirh.parametros.view.ParametrosDelegate;
import co.gov.ideam.sirh.seguridad.model.UsuarioConectadoTO;
import co.gov.ideam.sirh.util.ConstantesParametros;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.view.FacesUtils;
import co.gov.ideam.sirh.view.StandarBean;

import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.faces.component.UISelectItems;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import oracle.adf.view.rich.component.rich.RichDialog;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.RichMenu;
import oracle.adf.view.rich.component.rich.RichMenuBar;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectBooleanRadio;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelSplitter;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.component.rich.nav.RichCommandLink;
import oracle.adf.view.rich.component.rich.nav.RichCommandMenuItem;
import oracle.adf.view.rich.component.rich.output.RichActiveOutputText;
import oracle.adf.view.rich.component.rich.output.RichImage;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.component.rich.output.RichPanelCollection;
import oracle.adf.view.rich.component.rich.output.RichSpacer;
import oracle.adf.view.rich.context.AdfFacesContext;

import org.apache.myfaces.trinidad.event.SelectionEvent;


public class GestionConflictoBean extends StandarBean {

    private FnttFuente fuente;
    private String accionAdicionar;
    private FnttConflicto conflicto;
    private GestionConflicto gestionSeleccionada;

    private List listaGestionRealizada;

    private List listaTipoGestion;
    private List listaSubTipoGestion;

    private RichForm formAF1;
    private RichDocument documentAF1;

    private RichPanelStretchLayout psl2;
    private RichPanelStretchLayout psl7;

    private RichPanelFormLayout pfl2;

    private RichPanelGroupLayout pgl1;
    private RichPanelGroupLayout pgl14;


    private RichPanelSplitter ps1;

    private RichPanelCollection panelCollection1;

    private RichCommandButton cbRelacion;
    private RichCommandButton cbAceptar;


    private RichSelectOneChoice soc_Tramos;
    private UISelectItems selectItems7;

    private RichInputText it_nombre;
    private RichInputText it_descripcion;


    private RichMenu menu1;
    private RichCommandMenuItem commandMenuItem1;


    private RichOutputText ot8;
    private RichDialog d_detalle;


    private RichImage i1;

    private RichPopup p_registro_exitoso;

    private RichDialog d_registro_exitoso;

    private RichPanelGroupLayout pgl4;
    private RichCommandLink cl4;
    private RichInputText it_Radicado;
    private RichInputDate id_fecha_vigencia;
    private RichInputDate id_fechaCierre;
    private RichSelectBooleanRadio sbrAbierto;
    private RichSelectBooleanRadio sbrCerrado;
    private RichCommandLink commandLink1;
    private RichPanelGroupLayout panelGroupLayout6;
    private RichPanelGroupLayout panelGroupLayout7;
    private RichSpacer spacer5;
    private RichSpacer spacer9;
    private RichPanelGroupLayout panelGroupLayout8;
    private RichMenuBar menuBar1;
    private RichMenu menu3;
    private RichCommandMenuItem commandMenuItem11;
    private RichCommandMenuItem commandMenuItem2;
    private RichCommandMenuItem commandMenuItem3;
    private RichCommandMenuItem commandMenuItem4;
    private RichCommandMenuItem commandMenuItem5;
    private RichPanelFormLayout panelFormLayout1;
    private RichSpacer spacer2;
    private RichActiveOutputText activeOutputText1;
    private RichSpacer spacer3;
    private RichSelectOneChoice soc_tipo;
    private UISelectItems si1;
    private RichSpacer spacer4;
    private RichCommandButton commandButton1;
    private RichTable tb_gestion;
    private RichInputText inputText_nombre;
    private RichCommandButton cb_borrarActor;
    private RichPanelGroupLayout panelGroupLayout3;
    private RichSpacer spacer6;
    private RichInputDate id_fecha_reg;
    private RichInputText inputText_descripcion;
    private RichInputText inputText_gestionador;
    private RichSelectOneChoice soc_subtipo;
    private UISelectItems selectItems1;
    private RichPanelGroupLayout panelGroupLayout4;

    private UISelectItems si_tipoGestion;
    private UISelectItems si_subtipoGestion;
    private RichPanelGroupLayout panelGroupLayout31;
    private RichCommandButton commandButton11;
    private RichSpacer spacer61;
    private RichCommandButton cb_borrarActor1;
    private RichPanelSplitter panelSplitter1;
    private RichPanelFormLayout panelFormLayout2;
    private RichPanelFormLayout panelFormLayout11;
    private RichActiveOutputText activeOutputText11;
    private RichSpacer spacer31;
    private RichPanelFormLayout panelFormLayout21;
    private RichPanelGroupLayout panelGroupLayout311;
    private RichCommandButton commandButton111;
    private RichSpacer spacer611;
    private RichCommandButton cb_borrarActor11;
    private RichPanelGroupLayout panelGroupLayout41;
    private RichOutputText outputText1;
    private RichSpacer spacer1;


    public GestionConflictoBean() {
        FacesUtils.setActiveBean("gestionConflictoBean", "Gestion Conflicto",
                                 GestionConflictoBean.class);
        FacesUtils.removeManagedBeanFromSession("FuentesBean");
        this.load();
    }

    public void load() {
        try {

            fuente =
                    (FnttFuente)FacesUtils.getFromSession("fuenteSeleccionada");
            conflicto =
                    (FnttConflicto)FacesUtils.getFromSession("conflictoSeleccionado");

            UsuarioConectadoTO usuarioConectado =
                (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");

            FuenteDelegate fd = FuenteDelegate.getInstance();

            conflicto.setListaGestionConflicto(fd.getGestionConflictoXConflicto(conflicto.getId()));
            this.listaGestionRealizada = conflicto.getListaGestionConflicto();

            ParametrosDelegate pd = ParametrosDelegate.getInstance();

            listaTipoGestion =
                    this.cargarParametro(ConstantesParametros.CATEGORIA_TIPO_GESTION);
            this.listaSubTipoGestion = new ArrayList();


        } catch (IdeamException e) {
            showMessage(e);
        }
    }

    public void soc_TipoGestion_valueChangeListener(ValueChangeEvent event) throws IdeamException {

        Lista tipo = (Lista)event.getNewValue();
        this.listaSubTipoGestion = new ArrayList();

        try {

            if (tipo.getCodigo().compareTo(ConstantesParametros.CATEGORIA_TIPO_GESTION_ADM) ==
                0)
                listaSubTipoGestion =
                        this.cargarParametro(ConstantesParametros.CATEGORIA_SUBTIPO_GESTION_ADM);

            if (tipo.getCodigo().compareTo(ConstantesParametros.CATEGORIA_TIPO_GESTION_JUD) ==
                0)
                listaSubTipoGestion =
                        this.cargarParametro(ConstantesParametros.CATEGORIA_SUBTIPO_GESTION_JUD);

            if (tipo.getCodigo().compareTo(ConstantesParametros.CATEGORIA_TIPO_GESTION_POL) ==
                0)
                listaSubTipoGestion =
                        this.cargarParametro(ConstantesParametros.CATEGORIA_SUBTIPO_GESTION_POL);

            if (tipo.getCodigo().compareTo(ConstantesParametros.CATEGORIA_TIPO_GESTION_SOC) ==
                0)
                listaSubTipoGestion =
                        this.cargarParametro(ConstantesParametros.CATEGORIA_SUBTIPO_GESTION_SOC);

        } catch (IdeamException e) {
            showMessage(e);
        }
        
        AdfFacesContext.getCurrentInstance().addPartialTarget(soc_subtipo);
    }

    public void t_gestion_selectionListener(SelectionEvent selectionEvent) {
        try {
            
            RichTable t_gestion = (RichTable)selectionEvent.getSource();
            gestionSeleccionada = (GestionConflicto)t_gestion.getSelectedRowData();
    
            id_fecha_reg.setValue(gestionSeleccionada.getFechaGestion());
            inputText_nombre.setValue(gestionSeleccionada.getNombre());
            inputText_descripcion.setValue(gestionSeleccionada.getDescripcion());
            inputText_gestionador.setValue(gestionSeleccionada.getNombreGestionador());
            soc_tipo.setValue(gestionSeleccionada.getTipoGestion());
    
            this.listaSubTipoGestion = new ArrayList();
            if (gestionSeleccionada.getTipoGestion().getCodigo().compareTo(ConstantesParametros.CATEGORIA_TIPO_GESTION_ADM) == 0)
                listaSubTipoGestion = this.cargarParametro(ConstantesParametros.CATEGORIA_SUBTIPO_GESTION_ADM);
    
            if (gestionSeleccionada.getTipoGestion().getCodigo().compareTo(ConstantesParametros.CATEGORIA_TIPO_GESTION_JUD) == 0)
                listaSubTipoGestion = this.cargarParametro(ConstantesParametros.CATEGORIA_SUBTIPO_GESTION_JUD);
    
            if (gestionSeleccionada.getTipoGestion().getCodigo().compareTo(ConstantesParametros.CATEGORIA_TIPO_GESTION_POL) == 0)
                listaSubTipoGestion = this.cargarParametro(ConstantesParametros.CATEGORIA_SUBTIPO_GESTION_POL);
    
            if (gestionSeleccionada.getTipoGestion().getCodigo().compareTo(ConstantesParametros.CATEGORIA_TIPO_GESTION_SOC) == 0)
                listaSubTipoGestion = this.cargarParametro(ConstantesParametros.CATEGORIA_SUBTIPO_GESTION_SOC);
            
            AdfFacesContext.getCurrentInstance().addPartialTarget(soc_subtipo);
            
            soc_subtipo.setValue(gestionSeleccionada.getSubTipoGestion());
    
            AdfFacesContext.getCurrentInstance().addPartialTarget(id_fecha_reg);
            AdfFacesContext.getCurrentInstance().addPartialTarget(inputText_nombre);
            AdfFacesContext.getCurrentInstance().addPartialTarget(inputText_descripcion);
            AdfFacesContext.getCurrentInstance().addPartialTarget(inputText_gestionador);
            AdfFacesContext.getCurrentInstance().addPartialTarget(soc_tipo);
            AdfFacesContext.getCurrentInstance().addPartialTarget(soc_subtipo);

        } catch (IdeamException e) {
            showMessage(e);
        }

    }

    public void cb_relacionar_actionListener(ActionEvent actionEvent) throws IdeamException {
        try {

            validarForma();

            guardarGestion();

        } catch (IdeamException e) {
            showMessage(e);
        } catch (Exception e) {
            showMessage(e.getMessage());
        }
    }

    public void validarForma() throws Exception {

        if (id_fecha_reg.getValue() == null)
            throw new Exception("Ingrese fecha de gestion");

        if (inputText_nombre.getValue() == null)
            throw new Exception("Ingrese nombre de gestion");

        if (soc_tipo.getValue() == null)
            throw new Exception("Seleccione tipo de gestion");

        if (soc_subtipo.getValue() == null)
            throw new Exception("Seleccione subtipo de gestion");
    }

    public void guardarGestion() throws IdeamException {

            this.fuente =
                    (FnttFuente)FacesUtils.getFromSession("fuenteSeleccionada");

            UsuarioConectadoTO usuarioConectado =
                (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");

            FuenteDelegate fd = FuenteDelegate.getInstance();

            GestionConflicto gestion = new GestionConflicto();

            gestion.setIdConflicto(conflicto.getId());
            gestion.setCodigoAutoridad(new Long(usuarioConectado.getUsuario().getAutoridadAmbiental().getId()));
            
            Date fecha =  (Date)id_fecha_reg.getValue();
                                          
            gestion.setFechaGestion(new Timestamp(fecha.getTime()));
            
            gestion.setNombre(inputText_nombre.getValue().toString());
            gestion.setDescripcion(inputText_descripcion.getValue().toString());
            gestion.setNombreGestionador(inputText_gestionador.getValue().toString());

            gestion.setTipoGestion((Lista)soc_tipo.getValue());
            gestion.setSubTipoGestion((Lista)soc_subtipo.getValue());

            fd.createGestionConflicto(gestion);

            conflicto.setListaGestionConflicto(fd.getGestionConflictoXConflicto(conflicto.getId()));
            this.listaGestionRealizada = conflicto.getListaGestionConflicto();

            AdfFacesContext.getCurrentInstance().addPartialTarget(tb_gestion);

            showPopup(p_registro_exitoso, true);

    }

    public void cb_borrar_actionListener(ActionEvent actionEvent) throws IdeamException {
        try {

            if (gestionSeleccionada == null)
                throw new Exception("Seleccione gestion a borrar");

            this.fuente =
                    (FnttFuente)FacesUtils.getFromSession("fuenteSeleccionada");

            UsuarioConectadoTO usuarioConectado =
                (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");

            FuenteDelegate fd = FuenteDelegate.getInstance();

            guardarAuditoria("ELIMINAR", "GESTION", getClass().getName(),
                             gestionSeleccionada.getId());

            fd.deleteGestionConflicto(gestionSeleccionada);


            conflicto.setListaGestionConflicto(fd.getGestionConflictoXConflicto(conflicto.getId()));
            this.listaGestionRealizada = conflicto.getListaGestionConflicto();

            AdfFacesContext.getCurrentInstance().addPartialTarget(tb_gestion);

            gestionSeleccionada = null;

            showPopup(p_registro_exitoso, true);

        } catch (IdeamException e) {
            showMessage(e);
        } catch (Exception e) {
            showMessage(e.getMessage());
        }
    }

    public void cb_aceptar_actionListener(ActionEvent actionEvent) {
        accionAdicionar = "gestionConflicto";
    }

    public void lk_todosConflictos_actionListener(ActionEvent actionEvent) {
        accionAdicionar = "listarConflictos";
    }

    public String getAceptarAction() {
        return accionAdicionar;
    }

    public void setFormAF1(RichForm formAF1) {
        this.formAF1 = formAF1;
    }

    public RichForm getFormAF1() {
        return formAF1;
    }

    public void setDocumentAF1(RichDocument documentAF1) {
        this.documentAF1 = documentAF1;
    }

    public RichDocument getDocumentAF1() {
        return documentAF1;
    }

    public void setD_detalle(RichDialog d_detalle) {
        this.d_detalle = d_detalle;
    }

    public RichDialog getD_detalle() {
        return d_detalle;
    }

    public void setPsl2(RichPanelStretchLayout psl2) {
        this.psl2 = psl2;
    }

    public RichPanelStretchLayout getPsl2() {
        return psl2;
    }

    public void setPfl2(RichPanelFormLayout pfl2) {
        this.pfl2 = pfl2;
    }

    public RichPanelFormLayout getPfl2() {
        return pfl2;
    }


    public void setIt_nombre(RichInputText it_nombre) {
        this.it_nombre = it_nombre;
    }

    public RichInputText getIt_nombre() {
        return it_nombre;
    }

    public void setIt_descripcion(RichInputText it_descripcion) {
        this.it_descripcion = it_descripcion;
    }

    public RichInputText getIt_descripcion() {
        return it_descripcion;
    }


    public void setPgl1(RichPanelGroupLayout pgl1) {
        this.pgl1 = pgl1;
    }

    public RichPanelGroupLayout getPgl1() {
        return pgl1;
    }


    public void setSoc_Tramos(RichSelectOneChoice selectOneChoice7) {
        this.soc_Tramos = selectOneChoice7;
    }

    public RichSelectOneChoice getSoc_Tramos() {
        return soc_Tramos;
    }

    public void setSelectItems7(UISelectItems selectItems7) {
        this.selectItems7 = selectItems7;
    }

    public UISelectItems getSelectItems7() {
        return selectItems7;
    }


    public void setCbRelacion(RichCommandButton cbRelacion) {
        this.cbRelacion = cbRelacion;
    }

    public RichCommandButton getCbRelacion() {
        return cbRelacion;
    }


    public void setPs1(RichPanelSplitter ps1) {
        this.ps1 = ps1;
    }

    public RichPanelSplitter getPs1() {
        return ps1;
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

    public void setCommandMenuItem1(RichCommandMenuItem commandMenuItem1) {
        this.commandMenuItem1 = commandMenuItem1;
    }

    public RichCommandMenuItem getCommandMenuItem1() {
        return commandMenuItem1;
    }

    public void setPsl7(RichPanelStretchLayout psl7) {
        this.psl7 = psl7;
    }

    public RichPanelStretchLayout getPsl7() {
        return psl7;
    }

    public void setPgl14(RichPanelGroupLayout pgl14) {
        this.pgl14 = pgl14;
    }

    public RichPanelGroupLayout getPgl14() {
        return pgl14;
    }

    public void setCbAceptar(RichCommandButton cbAceptar) {
        this.cbAceptar = cbAceptar;
    }

    public RichCommandButton getCbAceptar() {
        return cbAceptar;
    }

    public void setOt8(RichOutputText ot8) {
        this.ot8 = ot8;
    }

    public RichOutputText getOt8() {
        return ot8;
    }

    public void setI1(RichImage i1) {
        this.i1 = i1;
    }

    public RichImage getI1() {
        return i1;
    }

    public void setP_registro_exitoso(RichPopup p_registro_exitoso) {
        this.p_registro_exitoso = p_registro_exitoso;
    }

    public RichPopup getP_registro_exitoso() {
        return p_registro_exitoso;
    }

    public void setD_registro_exitoso(RichDialog d_registro_exitoso) {
        this.d_registro_exitoso = d_registro_exitoso;
    }

    public RichDialog getD_registro_exitoso() {
        return d_registro_exitoso;
    }


    public void setFuente(FnttFuente fuente) {
        this.fuente = fuente;
    }

    public FnttFuente getFuente() {
        return fuente;
    }


    public void setPgl4(RichPanelGroupLayout pgl4) {
        this.pgl4 = pgl4;
    }

    public RichPanelGroupLayout getPgl4() {
        return pgl4;
    }

    public void setCl4(RichCommandLink cl4) {
        this.cl4 = cl4;
    }

    public RichCommandLink getCl4() {
        return cl4;
    }

    public void setIt_Radicado(RichInputText inputText1) {
        this.it_Radicado = inputText1;
    }

    public RichInputText getIt_Radicado() {
        return it_Radicado;
    }

    public void setId_fecha_vigencia(RichInputDate id_fecha_vigencia) {
        this.id_fecha_vigencia = id_fecha_vigencia;
    }

    public RichInputDate getId_fecha_vigencia() {
        return id_fecha_vigencia;
    }


    public void setId_fechaCierre(RichInputDate inputDate1) {
        this.id_fechaCierre = inputDate1;
    }

    public RichInputDate getId_fechaCierre() {
        return id_fechaCierre;
    }


    public void setSbrAbierto(RichSelectBooleanRadio sbr1) {
        this.sbrAbierto = sbr1;
    }

    public RichSelectBooleanRadio getSbrAbierto() {
        return sbrAbierto;
    }

    public void setSbrCerrado(RichSelectBooleanRadio sbr2) {
        this.sbrCerrado = sbr2;
    }

    public RichSelectBooleanRadio getSbrCerrado() {
        return sbrCerrado;
    }

    public void setAccionAdicionar(String accionAdicionar) {
        this.accionAdicionar = accionAdicionar;
    }

    public String getAccionAdicionar() {
        return accionAdicionar;
    }


    public void setConflicto(FnttConflicto conflicto) {
        this.conflicto = conflicto;
    }

    public FnttConflicto getConflicto() {
        return conflicto;
    }

    public void setCommandLink1(RichCommandLink commandLink1) {
        this.commandLink1 = commandLink1;
    }

    public RichCommandLink getCommandLink1() {
        return commandLink1;
    }


    public void setPanelGroupLayout6(RichPanelGroupLayout panelGroupLayout6) {
        this.panelGroupLayout6 = panelGroupLayout6;
    }

    public RichPanelGroupLayout getPanelGroupLayout6() {
        return panelGroupLayout6;
    }

    public void setPanelGroupLayout7(RichPanelGroupLayout panelGroupLayout7) {
        this.panelGroupLayout7 = panelGroupLayout7;
    }

    public RichPanelGroupLayout getPanelGroupLayout7() {
        return panelGroupLayout7;
    }


    public void setSpacer5(RichSpacer spacer5) {
        this.spacer5 = spacer5;
    }

    public RichSpacer getSpacer5() {
        return spacer5;
    }


    public void setSpacer9(RichSpacer spacer9) {
        this.spacer9 = spacer9;
    }

    public RichSpacer getSpacer9() {
        return spacer9;
    }

    public void setPanelGroupLayout8(RichPanelGroupLayout panelGroupLayout8) {
        this.panelGroupLayout8 = panelGroupLayout8;
    }

    public RichPanelGroupLayout getPanelGroupLayout8() {
        return panelGroupLayout8;
    }

    public void setMenuBar1(RichMenuBar menuBar1) {
        this.menuBar1 = menuBar1;
    }

    public RichMenuBar getMenuBar1() {
        return menuBar1;
    }

    public void setMenu3(RichMenu menu3) {
        this.menu3 = menu3;
    }

    public RichMenu getMenu3() {
        return menu3;
    }

    public void setCommandMenuItem11(RichCommandMenuItem commandMenuItem11) {
        this.commandMenuItem11 = commandMenuItem11;
    }

    public RichCommandMenuItem getCommandMenuItem11() {
        return commandMenuItem11;
    }

    public void setCommandMenuItem2(RichCommandMenuItem commandMenuItem2) {
        this.commandMenuItem2 = commandMenuItem2;
    }

    public RichCommandMenuItem getCommandMenuItem2() {
        return commandMenuItem2;
    }

    public void setCommandMenuItem3(RichCommandMenuItem commandMenuItem3) {
        this.commandMenuItem3 = commandMenuItem3;
    }

    public RichCommandMenuItem getCommandMenuItem3() {
        return commandMenuItem3;
    }

    public void setCommandMenuItem4(RichCommandMenuItem commandMenuItem4) {
        this.commandMenuItem4 = commandMenuItem4;
    }

    public RichCommandMenuItem getCommandMenuItem4() {
        return commandMenuItem4;
    }

    public void setCommandMenuItem5(RichCommandMenuItem commandMenuItem5) {
        this.commandMenuItem5 = commandMenuItem5;
    }

    public RichCommandMenuItem getCommandMenuItem5() {
        return commandMenuItem5;
    }


    public void setPanelFormLayout1(RichPanelFormLayout panelFormLayout1) {
        this.panelFormLayout1 = panelFormLayout1;
    }

    public RichPanelFormLayout getPanelFormLayout1() {
        return panelFormLayout1;
    }


    public void setSpacer2(RichSpacer spacer2) {
        this.spacer2 = spacer2;
    }

    public RichSpacer getSpacer2() {
        return spacer2;
    }

    public void setActiveOutputText1(RichActiveOutputText activeOutputText1) {
        this.activeOutputText1 = activeOutputText1;
    }

    public RichActiveOutputText getActiveOutputText1() {
        return activeOutputText1;
    }

    public void setSpacer3(RichSpacer spacer3) {
        this.spacer3 = spacer3;
    }

    public RichSpacer getSpacer3() {
        return spacer3;
    }


    public void setSoc_tipo(RichSelectOneChoice soc_depto) {
        this.soc_tipo = soc_depto;
    }

    public RichSelectOneChoice getSoc_tipo() {
        return soc_tipo;
    }

    public void setSi1(UISelectItems si1) {
        this.si1 = si1;
    }

    public UISelectItems getSi1() {
        return si1;
    }


    public void setSpacer4(RichSpacer spacer4) {
        this.spacer4 = spacer4;
    }

    public RichSpacer getSpacer4() {
        return spacer4;
    }

    public void setCommandButton1(RichCommandButton commandButton1) {
        this.commandButton1 = commandButton1;
    }

    public RichCommandButton getCommandButton1() {
        return commandButton1;
    }

    public void setTb_gestion(RichTable table1) {
        this.tb_gestion = table1;
    }

    public RichTable getTb_gestion() {
        return tb_gestion;
    }

    public void setInputText_nombre(RichInputText inputText2) {
        this.inputText_nombre = inputText2;
    }

    public RichInputText getInputText_nombre() {
        return inputText_nombre;
    }

    public void setCb_borrarActor(RichCommandButton commandButton2) {
        this.cb_borrarActor = commandButton2;
    }

    public RichCommandButton getCb_borrarActor() {
        return cb_borrarActor;
    }

    public void setPanelGroupLayout3(RichPanelGroupLayout panelGroupLayout3) {
        this.panelGroupLayout3 = panelGroupLayout3;
    }

    public RichPanelGroupLayout getPanelGroupLayout3() {
        return panelGroupLayout3;
    }

    public void setSpacer6(RichSpacer spacer6) {
        this.spacer6 = spacer6;
    }

    public RichSpacer getSpacer6() {
        return spacer6;
    }

    public void setId_fecha_reg(RichInputDate id_fecha_vigencia1) {
        this.id_fecha_reg = id_fecha_vigencia1;
    }

    public RichInputDate getId_fecha_reg() {
        return id_fecha_reg;
    }

    public void setInputText_descripcion(RichInputText inputText1) {
        this.inputText_descripcion = inputText1;
    }

    public RichInputText getInputText_descripcion() {
        return inputText_descripcion;
    }

    public void setInputText_gestionador(RichInputText inputText1) {
        this.inputText_gestionador = inputText1;
    }

    public RichInputText getInputText_gestionador() {
        return inputText_gestionador;
    }

    public void setSoc_subtipo(RichSelectOneChoice selectOneChoice1) {
        this.soc_subtipo = selectOneChoice1;
    }

    public RichSelectOneChoice getSoc_subtipo() {
        return soc_subtipo;
    }

    public void setSelectItems1(UISelectItems selectItems1) {
        this.selectItems1 = selectItems1;
    }

    public UISelectItems getSelectItems1() {
        return selectItems1;
    }

    public void setPanelGroupLayout4(RichPanelGroupLayout panelGroupLayout4) {
        this.panelGroupLayout4 = panelGroupLayout4;
    }

    public RichPanelGroupLayout getPanelGroupLayout4() {
        return panelGroupLayout4;
    }


    public void setGestionSeleccionada(GestionConflicto gestionSeleccionada) {
        this.gestionSeleccionada = gestionSeleccionada;
    }

    public GestionConflicto getGestionSeleccionada() {
        return gestionSeleccionada;
    }

    public void setListaGestionRealizada(List listaGestionRealizada) {
        this.listaGestionRealizada = listaGestionRealizada;
    }

    public List getListaGestionRealizada() {
        return listaGestionRealizada;
    }

    public void setListaTipoGestion(List listaTipoGestion) {
        this.listaTipoGestion = listaTipoGestion;
    }

    public List getListaTipoGestion() {
        return listaTipoGestion;
    }

    public void setListaSubTipoGestion(List listaSubTipoGestion) {
        this.listaSubTipoGestion = listaSubTipoGestion;
    }

    public List getListaSubTipoGestion() {
        return listaSubTipoGestion;
    }


    public void setSi_tipoGestion(UISelectItems si11) {
        this.si_tipoGestion = si11;
    }

    public UISelectItems getSi_tipoGestion() {
        return si_tipoGestion;
    }


    public void setSi_subtipoGestion(UISelectItems selectItems11) {
        this.si_subtipoGestion = selectItems11;
    }

    public UISelectItems getSi_subtipoGestion() {
        return si_subtipoGestion;
    }

    public void setPanelGroupLayout31(RichPanelGroupLayout panelGroupLayout31) {
        this.panelGroupLayout31 = panelGroupLayout31;
    }

    public RichPanelGroupLayout getPanelGroupLayout31() {
        return panelGroupLayout31;
    }

    public void setCommandButton11(RichCommandButton commandButton11) {
        this.commandButton11 = commandButton11;
    }

    public RichCommandButton getCommandButton11() {
        return commandButton11;
    }

    public void setSpacer61(RichSpacer spacer61) {
        this.spacer61 = spacer61;
    }

    public RichSpacer getSpacer61() {
        return spacer61;
    }

    public void setCb_borrarActor1(RichCommandButton cb_borrarActor1) {
        this.cb_borrarActor1 = cb_borrarActor1;
    }

    public RichCommandButton getCb_borrarActor1() {
        return cb_borrarActor1;
    }


    public void setPanelSplitter1(RichPanelSplitter panelSplitter1) {
        this.panelSplitter1 = panelSplitter1;
    }

    public RichPanelSplitter getPanelSplitter1() {
        return panelSplitter1;
    }

    public void setPanelFormLayout2(RichPanelFormLayout panelFormLayout2) {
        this.panelFormLayout2 = panelFormLayout2;
    }

    public RichPanelFormLayout getPanelFormLayout2() {
        return panelFormLayout2;
    }

    public void setPanelFormLayout11(RichPanelFormLayout panelFormLayout11) {
        this.panelFormLayout11 = panelFormLayout11;
    }

    public RichPanelFormLayout getPanelFormLayout11() {
        return panelFormLayout11;
    }


    public void setActiveOutputText11(RichActiveOutputText activeOutputText11) {
        this.activeOutputText11 = activeOutputText11;
    }

    public RichActiveOutputText getActiveOutputText11() {
        return activeOutputText11;
    }

    public void setSpacer31(RichSpacer spacer31) {
        this.spacer31 = spacer31;
    }

    public RichSpacer getSpacer31() {
        return spacer31;
    }

    public void setPanelFormLayout21(RichPanelFormLayout panelFormLayout21) {
        this.panelFormLayout21 = panelFormLayout21;
    }

    public RichPanelFormLayout getPanelFormLayout21() {
        return panelFormLayout21;
    }

    public void setPanelGroupLayout311(RichPanelGroupLayout panelGroupLayout311) {
        this.panelGroupLayout311 = panelGroupLayout311;
    }

    public RichPanelGroupLayout getPanelGroupLayout311() {
        return panelGroupLayout311;
    }

    public void setCommandButton111(RichCommandButton commandButton111) {
        this.commandButton111 = commandButton111;
    }

    public RichCommandButton getCommandButton111() {
        return commandButton111;
    }

    public void setSpacer611(RichSpacer spacer611) {
        this.spacer611 = spacer611;
    }

    public RichSpacer getSpacer611() {
        return spacer611;
    }

    public void setCb_borrarActor11(RichCommandButton cb_borrarActor11) {
        this.cb_borrarActor11 = cb_borrarActor11;
    }

    public RichCommandButton getCb_borrarActor11() {
        return cb_borrarActor11;
    }

    public void setPanelGroupLayout41(RichPanelGroupLayout panelGroupLayout41) {
        this.panelGroupLayout41 = panelGroupLayout41;
    }

    public RichPanelGroupLayout getPanelGroupLayout41() {
        return panelGroupLayout41;
    }

    public void setOutputText1(RichOutputText outputText1) {
        this.outputText1 = outputText1;
    }

    public RichOutputText getOutputText1() {
        return outputText1;
    }

    public void setSpacer1(RichSpacer spacer1) {
        this.spacer1 = spacer1;
    }

    public RichSpacer getSpacer1() {
        return spacer1;
    }
}
