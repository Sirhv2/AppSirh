package co.gov.ideam.sirh.fuentes.view;

import co.gov.ideam.sirh.auditoria.model.Auditoria;
import co.gov.ideam.sirh.auditoria.view.AuditoriasDelegate;

import co.gov.ideam.sirh.calidad.model.PuntoMonitoreo;
import co.gov.ideam.sirh.calidad.view.CalidadDelegate;
import co.gov.ideam.sirh.fuentes.model.ActorConflicto;
import co.gov.ideam.sirh.fuentes.model.FnttConflicto;
import co.gov.ideam.sirh.fuentes.model.FnttFuente;

import co.gov.ideam.sirh.fuentes.model.FnttTramo;
import co.gov.ideam.sirh.fuentes.model.OrigenConflicto;
import co.gov.ideam.sirh.fuentes.model.PoblacionConflicto;
import co.gov.ideam.sirh.fuentes.model.TipoConflicto;
import co.gov.ideam.sirh.parametros.model.Categoria;
import co.gov.ideam.sirh.parametros.model.Divipola;
import co.gov.ideam.sirh.parametros.model.Lista;

import co.gov.ideam.sirh.parametros.view.ParametrosDelegate;
import co.gov.ideam.sirh.seguridad.model.UsuarioConectadoTO;

import co.gov.ideam.sirh.usuarios.agua.model.PuntoVertimientoTratamiento;
import co.gov.ideam.sirh.util.ConstantesParametros;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.view.FacesUtils;
import co.gov.ideam.sirh.view.StandarBean;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.faces.event.ValueChangeEvent;

import javax.faces.model.SelectItem;

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
import oracle.adf.view.rich.component.rich.input.RichSelectManyListbox;
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
import oracle.adf.view.rich.component.rich.output.RichImage;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.component.rich.output.RichPanelCollection;
import oracle.adf.view.rich.component.rich.output.RichSpacer;
import oracle.adf.view.rich.context.AdfFacesContext;

import org.apache.myfaces.trinidad.event.SelectionEvent;


public class ActorConflictoBean extends StandarBean {

    private FnttFuente fuente;
    private String accionAdicionar;
    private FnttConflicto conflicto;
    private ActorConflicto actorSeleccionado;
    
    private List listaPoblacionConflicto;
    private List listaPoblacionConflictoAsociado;

    private List listaActoresConflicto;
    private List listaActoresSeleccionada;
    
    private RichForm formAF1;
    private RichDocument documentAF1;

    private RichPanelStretchLayout psl2;
    private RichPanelStretchLayout psl7;

    private RichPanelFormLayout pfl2;

    private RichPanelGroupLayout pgl1;
    private RichPanelGroupLayout pgl14;

    private RichPanelBox pb1;

    private RichPanelSplitter ps1;

    private RichPanelCollection panelCollection1;

    private RichCommandButton cbNext1;
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

    private RichPanelFormLayout pfl21;
    private RichInputText it_nombre1;
    private RichPanelGroupLayout pgl4;
    private RichCommandLink cl4;
    private RichInputText it_Radicado;
    private RichInputDate id_fecha_vigencia;
    private RichInputDate id_fechaCierre;
    private RichSelectBooleanRadio sbrAbierto;
    private RichSelectBooleanRadio sbrCerrado;
    private RichInputText it_nombre2;
    private RichCommandLink commandLink1;
    private RichPanelGroupLayout panelGroupLayout6;
    private RichPanelGroupLayout panelGroupLayout7;
    private RichSpacer spacer5;
    private RichSpacer spacer8;
    private RichSpacer spacer9;
    private RichPanelGroupLayout panelGroupLayout8;
    private RichMenuBar menuBar1;
    private RichMenu menu3;
    private RichCommandMenuItem commandMenuItem11;
    private RichCommandMenuItem commandMenuItem2;
    private RichCommandMenuItem commandMenuItem3;
    private RichCommandMenuItem commandMenuItem4;
    private RichCommandMenuItem commandMenuItem5;
    private RichSelectManyListbox smlbPoblacionConflicto;
    private UISelectItems siPoblacionConflicto;
    private RichInputText inputText1;
    private RichPanelBox panelBox1;
    private RichPanelStretchLayout panelStretchLayout1;
    private RichPanelGroupLayout panelGroupLayout1;
    private RichPanelGroupLayout panelGroupLayout2;
    private RichPanelFormLayout panelFormLayout1;
  private RichSpacer spacer2;
    private RichActiveOutputText activeOutputText1;
    private RichSpacer spacer3;
    private RichPanelStretchLayout panelStretchLayout2;
    private RichPanelFormLayout panelFormLayout2;
    private RichSelectOneChoice soc_actor;
    private UISelectItems si1;
    private RichSpacer spacer4;
    private RichCommandButton commandButton1;
    private RichTable tb_conflictos;
    private RichInputText inputText_nombre;
    private RichCommandButton cb_borrarActor;
    private RichPanelGroupLayout panelGroupLayout3;
    private RichSpacer spacer6;
    private RichOutputText outputText1;
    private RichSpacer spacer7;


    public ActorConflictoBean() {
        FacesUtils.setActiveBean("actorConflictoBean", "Actor Conflicto",
                                 ActorConflictoBean.class);
        FacesUtils.removeManagedBeanFromSession("FuentesBean");
        this.load();
    }

    public void load() {
        try {

            System.out.println("HCP Entro a LOAD ACTORES" );

            fuente =
                    (FnttFuente)FacesUtils.getFromSession("fuenteSeleccionada");
            conflicto =
                    (FnttConflicto)FacesUtils.getFromSession("conflictoSeleccionado");

            UsuarioConectadoTO usuarioConectado =
                (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
            
            FuenteDelegate fd = FuenteDelegate.getInstance();
            
            conflicto.setListaPoblacionConflicto(fd.getPoblacionConflictoXConflicto(conflicto.getId()));
            conflicto.setListaActorConflicto(fd.getActorConflictoXConflicto(conflicto.getId()));
            listaActoresSeleccionada = conflicto.getListaActorConflicto();

            System.out.println("HCP Lista actores " + listaActoresSeleccionada.size());
            
            ParametrosDelegate pd = ParametrosDelegate.getInstance();

            listaPoblacionConflicto = this.cargarParametro(ConstantesParametros.CATEGORIA_POBLACION_CONFLICTO);
            listaActoresConflicto = this.cargarParametro(ConstantesParametros.CATEGORIA_ACTOR_CONFLICTO);

            this.listaPoblacionConflictoAsociado = new ArrayList();

            if(this.conflicto.getListaPoblacionConflicto()!=null){
                Iterator it = this.conflicto.getListaPoblacionConflicto().iterator();
                while(it.hasNext()){
                    PoblacionConflicto tipo = (PoblacionConflicto)it.next();
                    Lista lista = pd.getLista(tipo.getIdPoblacionConflcito());
                    this.listaPoblacionConflictoAsociado.add(lista);
                }
            }


        } catch (IdeamException e) {
            showMessage(e);
        }
    }

    public void t_actores_selectionListener(SelectionEvent selectionEvent) {
         RichTable t_actores = (RichTable)selectionEvent.getSource();
         actorSeleccionado = (ActorConflicto)t_actores.getSelectedRowData();
         
         
         soc_actor.setValue(actorSeleccionado.getActorConflicto());
         inputText_nombre.setValue(actorSeleccionado.getNombre());
         
         AdfFacesContext.getCurrentInstance().addPartialTarget(soc_actor);
         AdfFacesContext.getCurrentInstance().addPartialTarget(inputText_nombre);
         
         
     }

    public void cb_relacionar_actionListener(ActionEvent actionEvent) throws IdeamException {
        try {

            validarActor();
            guardarActor();

        } catch (IdeamException e) {
            showMessage(e);
        } catch (Exception e) {
            showMessage(e.getMessage());
        }
    }

    public void cb_borrar_actionListener(ActionEvent actionEvent) throws IdeamException {
        try {

            if (actorSeleccionado == null)
                throw new Exception("Seleccione actor a borrar");
            
            this.fuente =
                    (FnttFuente)FacesUtils.getFromSession("fuenteSeleccionada");

            UsuarioConectadoTO usuarioConectado =
                (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");

            FuenteDelegate fd = FuenteDelegate.getInstance();

            guardarAuditoria("ELIMINAR", "ACTOR", getClass().getName(),
                             actorSeleccionado.getId());

            fd.deleteActorConflicto(actorSeleccionado);

            
            conflicto.setListaActorConflicto(fd.getActorConflictoXConflicto(conflicto.getId()));
            listaActoresSeleccionada = conflicto.getListaActorConflicto();

            AdfFacesContext.getCurrentInstance().addPartialTarget(tb_conflictos);

            actorSeleccionado = null;
            
            showPopup(p_registro_exitoso, true);

        } catch (IdeamException e) {
            showMessage(e);
        } catch (Exception e) {
            showMessage(e.getMessage());
        }
    }

    public void cbNext1_actionListener(ActionEvent actionEvent) throws IdeamException {
        try {

            accionAdicionar = "";

            validarForma();
            guardarConflicto();

        } catch (IdeamException e) {
            showMessage(e);
        } catch (Exception e) {
            showMessage(e.getMessage());
        }
    }

    public void validarForma() throws Exception {

        if (it_nombre2.getValue() == null)
            throw new Exception("Ingree nombre del conflicto");


    }

    public void validarActor() throws Exception {

        if(soc_actor.getValue() == null)
            throw new Exception("Seleccione tipo de actor");
        
        if (inputText_nombre.getValue() == null)
            throw new Exception("Ingrese nombre del actor");

    }

    public void guardarConflicto() {
        try {

            this.fuente =
                    (FnttFuente)FacesUtils.getFromSession("fuenteSeleccionada");

            UsuarioConectadoTO usuarioConectado =
                (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");

            FuenteDelegate fd = FuenteDelegate.getInstance();
            conflicto = fd.updateConflicto(conflicto);
            guardarAuditoria("GUARDAR", "CONFLICTO", getClass().getName(),
                             conflicto.getId());

            if(this.conflicto.getListaPoblacionConflicto()!=null){
                for(PoblacionConflicto poblacionConflicto : this.conflicto.getListaPoblacionConflicto()){
                    fd.deletePoblacionConflicto(poblacionConflicto);
                }
                this.conflicto.getListaPoblacionConflicto().clear();
            }

            if(this.smlbPoblacionConflicto.getValue()!=null){
                Iterator it= ((List)this.smlbPoblacionConflicto.getValue()).iterator();
                while(it.hasNext()){
                    Lista lis = (Lista)it.next();
                    
                    PoblacionConflicto poblacionConflicto = new PoblacionConflicto();
                    poblacionConflicto.setIdConflicto(conflicto.getId());
                    poblacionConflicto.setCodigoAutoridad( new Long(usuarioConectado.getUsuario().getAutoridadAmbiental().getId()) );
                    poblacionConflicto.setIdPoblacionConflcito(lis.getCodigo());
                    
                    fd.createPoblacionConflicto(poblacionConflicto);
                }
            }


            showPopup(p_registro_exitoso, true);

        } catch (IdeamException e) {
            showMessage(e);
        }
    }

    public void guardarActor() {
        try {

            this.fuente =
                    (FnttFuente)FacesUtils.getFromSession("fuenteSeleccionada");

            UsuarioConectadoTO usuarioConectado =
                (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");

            FuenteDelegate fd = FuenteDelegate.getInstance();

 
            ActorConflicto actor = new ActorConflicto();
            actor.setIdConflicto(conflicto.getId());
            actor.setActorConflicto((Lista)soc_actor.getValue());
            actor.setCodigoAutoridad( new Long(usuarioConectado.getUsuario().getAutoridadAmbiental().getId()) );
            actor.setNombre(inputText_nombre.getValue().toString());
            
            fd.updateActorConflicto(actor);

            guardarAuditoria("GUARDAR", "ACTOR", getClass().getName(),
                             actor.getId());

            conflicto.setListaActorConflicto(fd.getActorConflictoXConflicto(conflicto.getId()));
            listaActoresSeleccionada = conflicto.getListaActorConflicto();

            AdfFacesContext.getCurrentInstance().addPartialTarget(tb_conflictos);

            showPopup(p_registro_exitoso, true);

        } catch (IdeamException e) {
            showMessage(e);
        }
    }

    public void cb_aceptar_actionListener(ActionEvent actionEvent) {
        accionAdicionar = "actoresConflicto";
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


    public void setPb1(RichPanelBox pb1) {
        this.pb1 = pb1;
    }

    public RichPanelBox getPb1() {
        return pb1;
    }

    public void setCbNext1(RichCommandButton cbNext1) {
        this.cbNext1 = cbNext1;
    }

    public RichCommandButton getCbNext1() {
        return cbNext1;
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


    public void setIt_nombre2(RichInputText it_nombre2) {
        this.it_nombre2 = it_nombre2;
    }

    public RichInputText getIt_nombre2() {
        return it_nombre2;
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


    public void setSpacer8(RichSpacer spacer8) {
        this.spacer8 = spacer8;
    }

    public RichSpacer getSpacer8() {
        return spacer8;
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


    public void setSmlbPoblacionConflicto(RichSelectManyListbox smlbPretratamiento) {
        this.smlbPoblacionConflicto = smlbPretratamiento;
    }

    public RichSelectManyListbox getSmlbPoblacionConflicto() {
        return smlbPoblacionConflicto;
    }

    public void setSiPoblacionConflicto(UISelectItems siPretratamiento) {
        this.siPoblacionConflicto = siPretratamiento;
    }

    public UISelectItems getSiPoblacionConflicto() {
        return siPoblacionConflicto;
    }


    public void setInputText1(RichInputText inputText1) {
        this.inputText1 = inputText1;
    }

    public RichInputText getInputText1() {
        return inputText1;
    }

    public void setListaPoblacionConflicto(List listaPoblacionConflicto) {
        this.listaPoblacionConflicto = listaPoblacionConflicto;
    }

    public List getListaPoblacionConflicto() {
        return listaPoblacionConflicto;
    }

    public void setListaPoblacionConflictoAsociado(List listaPoblacionConflictoAsociado) {
        this.listaPoblacionConflictoAsociado = listaPoblacionConflictoAsociado;
    }

    public List getListaPoblacionConflictoAsociado() {
        return listaPoblacionConflictoAsociado;
    }


    public void setPanelBox1(RichPanelBox panelBox1) {
        this.panelBox1 = panelBox1;
    }

    public RichPanelBox getPanelBox1() {
        return panelBox1;
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

    public void setPanelStretchLayout2(RichPanelStretchLayout panelStretchLayout2) {
        this.panelStretchLayout2 = panelStretchLayout2;
    }

    public RichPanelStretchLayout getPanelStretchLayout2() {
        return panelStretchLayout2;
    }

    public void setPanelFormLayout2(RichPanelFormLayout panelFormLayout2) {
        this.panelFormLayout2 = panelFormLayout2;
    }

    public RichPanelFormLayout getPanelFormLayout2() {
        return panelFormLayout2;
    }

    public void setSoc_actor(RichSelectOneChoice soc_depto) {
        this.soc_actor = soc_depto;
    }

    public RichSelectOneChoice getSoc_actor() {
        return soc_actor;
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

    public void setTb_conflictos(RichTable table1) {
        this.tb_conflictos = table1;
    }

    public RichTable getTb_conflictos() {
        return tb_conflictos;
    }

    public void setInputText_nombre(RichInputText inputText2) {
        this.inputText_nombre = inputText2;
    }

    public RichInputText getInputText_nombre() {
        return inputText_nombre;
    }

    public void setListaActoresConflicto(List listaActoresConflicto) {
        this.listaActoresConflicto = listaActoresConflicto;
    }

    public List getListaActoresConflicto() {
        return listaActoresConflicto;
    }

    public void setListaActoresSeleccionada(List listaActoresSeleccionada) {
        this.listaActoresSeleccionada = listaActoresSeleccionada;
    }

    public List getListaActoresSeleccionada() {
        return listaActoresSeleccionada;
    }

    public void setActorSeleccionado(ActorConflicto actorSeleccionado) {
        this.actorSeleccionado = actorSeleccionado;
    }

    public ActorConflicto getActorSeleccionado() {
        return actorSeleccionado;
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

    public void setOutputText1(RichOutputText outputText1) {
        this.outputText1 = outputText1;
    }

    public RichOutputText getOutputText1() {
        return outputText1;
    }

    public void setSpacer7(RichSpacer spacer7) {
        this.spacer7 = spacer7;
    }

    public RichSpacer getSpacer7() {
        return spacer7;
    }
}
