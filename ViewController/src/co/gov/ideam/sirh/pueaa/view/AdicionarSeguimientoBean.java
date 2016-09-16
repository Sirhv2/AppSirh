package co.gov.ideam.sirh.pueaa.view;

import co.gov.ideam.sirh.fuentes.view.FuenteDelegate;
import co.gov.ideam.sirh.parametros.model.Autoridades;
import co.gov.ideam.sirh.pueaa.PueaaDelegate;
import co.gov.ideam.sirh.pueaa.model.PueatProyectos;
import co.gov.ideam.sirh.pueaa.model.PueatSeguimiento;
import co.gov.ideam.sirh.seguridad.model.UsuarioConectadoTO;
import co.gov.ideam.sirh.usuarios.agua.model.UsuarioAgua;
import co.gov.ideam.sirh.usuarios.agua.view.UsuariosAguaDelegate;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.view.FacesUtils;
import co.gov.ideam.sirh.view.StandarBean;

import java.sql.Timestamp;

import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.event.ActionEvent;

import oracle.adf.view.rich.component.rich.RichDialog;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.context.AdfFacesContext;
import oracle.adf.view.rich.event.DialogEvent;

import org.apache.myfaces.trinidad.event.SelectionEvent;

public class AdicionarSeguimientoBean extends StandarBean {
    
    private RichPanelGroupLayout pgl14;
    private RichCommandButton cb_aceptar;
    private String aceptarAction;
    private RichPanelStretchLayout psl7;
    private RichOutputText ot8;


    private RichForm f1;
    private RichDocument d1;
    private RichPanelGroupLayout panelGroupLayoutGeneral;
    private RichPanelFormLayout panelFormLayoutMetas;
    private RichPanelFormLayout panelFormLayoutMetas2;
    private RichPanelFormLayout panelFormLayoutMetas3;
    private RichPanelFormLayout panelFormLayoutMetas4;
    private RichPanelFormLayout panelFormLayoutMetas5;
    private RichPanelFormLayout pfl1;
    private RichPopup p_registro_exitoso;
    private RichDialog d_registro_exitoso;

    private RichInputDate idFechaS1;
    private RichInputText itConsumoM1;
    private RichInputDate idFechaS2;
    private RichInputText itConsumoM2;
    private RichInputDate idFechaS3;
    private RichInputText itConsumoM3;
    private RichInputDate idFechaS4;
    private RichInputText itConsumoM4;
    private RichInputDate idFechaS5;
    private RichInputText itConsumoM5;

    private RichCommandButton cbGuardar;
    private RichCommandButton cbAgregarProyecto;
    private RichTable tblPred;
    private RichCommandButton cbModificarPred;
    private RichCommandButton cbEliminarPred;
    private PueatProyectos proyecto;
    private PueatSeguimiento seguimiento;
    private List listaSeguimientos;
    private RichTable t_seguimientos;
    private RichPopup p_borrar;
    private RichDialog d_borrar;
    private RichCommandButton cb_borrar;
    private RichCommandButton cb_limpiar;
    private RichCommandButton cb_regresar;
    private RichCommandButton cbEditarRow1;
    private RichCommandButton cbEditarRow2;
    private RichCommandButton cbEditarRow3;
    private RichCommandButton cbEditarRow4;
    private RichCommandButton cbEditarRow5;
    private Integer cumplioMeta1;
    private Integer cumplioMeta2;
    private Integer cumplioMeta3;
    private Integer cumplioMeta4;
    private Integer cumplioMeta5;
    private UsuarioAgua usuarioAgua;
    //Visulaliza Campos
    private boolean visualMeta1;
    private boolean visualMeta2;
    private boolean visualMeta3;
    private boolean visualMeta4;
    private boolean visualMeta5;
    private boolean visualBotonG;
    UsuarioConectadoTO usuarioConectado;
    Autoridades autoridadAmbiental;
    PueaaDelegate pueaD;

    public AdicionarSeguimientoBean() {
        
        FacesUtils.setActiveBean("adicionarSeguimientoBean",
                                 "adicionarSeguimientoBean",
                                 AdicionarSeguimientoBean.class);
        this.load();
    }

    private void load() {
        try {

            System.out.println("HCP entro a load AdicionarSeguimientoBean");

            cumplioMeta1 = 0;
            cumplioMeta2 = 0;
            cumplioMeta3 = 0;
            cumplioMeta4 = 0;
            cumplioMeta5 = 0;
            
            visualBotonG = false;

            usuarioConectado = (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
            autoridadAmbiental = usuarioConectado.getUsuario().getAutoridadAmbiental();

            proyecto = new PueatProyectos();
            seguimiento = new PueatSeguimiento();

            //usuarioAgua = (UsuarioAgua)FacesUtils.getFromSession("usuarioSeleccionado");
            
            UsuarioConectadoTO usuarioConectado = (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
            proyecto = (PueatProyectos)FacesUtils.getFromSession("proyectoSeleccionado");
            
            if (proyecto == null)
                System.out.println("HCP proyecto vacio");
            else
                System.out.println("HCP proyecto ok " +
                                   proyecto.getDescripcion());
            
            PueaaDelegate pueaaDelegate = PueaaDelegate.getInstance();
            listaSeguimientos =
                    pueaaDelegate.getSeguimientosProyecto(proyecto);

            //Validar
            visualMeta1 =
                    (proyecto.getMetaSeguimientoAno1() != null) ? true : false;
            visualMeta2 =
                    (proyecto.getMetaSeguimientoAno2() != null) ? true : false;
            visualMeta3 =
                    (proyecto.getMetaSeguimientoAno3() != null) ? true : false;
            visualMeta4 =
                    (proyecto.getMetaSeguimientoAno4() != null) ? true : false;
            visualMeta5 =
                    (proyecto.getMetaSeguimientoAno5() != null) ? true : false;

            if (visualMeta1 && visualMeta2 && visualMeta3 && visualMeta4 &&
                visualMeta5) {
                visualBotonG = true;
            }
            cargaMetas();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void cargaMetas() {

        Date fechaActual = new Date();
        Timestamp fechaActualTime = new Timestamp(fechaActual.getTime());

        if (visualMeta1) {
            if ((proyecto.getMetaConsumoAno1() <=
                 proyecto.getMetaSeguimientoAno1()) )
                cumplioMeta1 = 1;
            else
                cumplioMeta1 = 2;
        }
        if (visualMeta2) {
            if ((proyecto.getMetaConsumoAno2() <=
                 proyecto.getMetaSeguimientoAno2()) )
                cumplioMeta2 = 1;
            else
                cumplioMeta2 = 2;
        }
        if (visualMeta3) {
            if ((proyecto.getMetaConsumoAno3() <=
                 proyecto.getMetaSeguimientoAno3()) )
                cumplioMeta3 = 1;
            else
                cumplioMeta3 = 2;
        }
        if (visualMeta4) {
            if ((proyecto.getMetaConsumoAno4() <=
                 proyecto.getMetaSeguimientoAno4()))
                cumplioMeta4 = 1;
            else
                cumplioMeta4 = 2;
        }
        if (visualMeta5) {
            if ((proyecto.getMetaConsumoAno5() <=
                 proyecto.getMetaSeguimientoAno5()))
                cumplioMeta5 = 1;
            else
                cumplioMeta5 = 2;
        }

    }

    public void cbGuardar_actionListener(ActionEvent actionEvent) throws IdeamException {
        boolean errorMeta1 = false;
        boolean errorMeta2 = false;
        boolean errorMeta3 = false;
        boolean errorMeta4 = false;
        boolean errorMeta5 = false;

        //***/
        if (!visualMeta1) {
            if (itConsumoM1.getValue() == null ||
                itConsumoM1.getValue().toString().length() == 0) {
                
                errorMeta1 = true;
            }
            if (idFechaS1.getValue() == null ||
                idFechaS1.getValue().toString().length() == 0) {
                errorMeta1 = true;
            }
            
            if (itConsumoM1.getValue() != null && idFechaS1.getValue() == null ) {
                showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                            idFechaS1);
                return;
            }

            if (!errorMeta1)
                this.saveMeta1();

            /*}else{
                showMessage("Para Registrar el Seguimiento de Meta1 debe Diligenciar el Valor y La Fecha", FacesMessage.SEVERITY_ERROR, itConsumoM1);
         }*/
        }
        //***/
        if (!visualMeta2) {
            if (itConsumoM2.getValue() == null ||
                itConsumoM2.getValue().toString().length() == 0) {
                errorMeta2 = true;
            }
            if (idFechaS2.getValue() == null ||
                idFechaS2.getValue().toString().length() == 0) {
                errorMeta2 = true;
            }
            
            if (itConsumoM2.getValue() != null && idFechaS2.getValue() == null ) {
                showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                            idFechaS2);
                return;
            }

            if (!errorMeta2)
                this.saveMeta2();
            /*}else{
                 showMessage("Para Registrar el Seguimiento de Meta2 debe Diligenciar el Valor y La Fecha", FacesMessage.SEVERITY_ERROR, itConsumoM2);
          }*/
        }
        //***/
        if (!visualMeta3) {
            if (itConsumoM3.getValue() == null ||
                itConsumoM3.getValue().toString().length() == 0) {
                errorMeta3 = true;
            }
            if (idFechaS3.getValue() == null ||
                idFechaS3.getValue().toString().length() == 0) {
                errorMeta3 = true;
            }
            
            if (itConsumoM3.getValue() != null && idFechaS3.getValue() == null ) {
                showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                            idFechaS3);
                return;
            }

            if (!errorMeta3)
                this.saveMeta3();
            /*}else{
                 showMessage("Para Registrar el Seguimiento de Meta3 debe Diligenciar el Valor y La Fecha", FacesMessage.SEVERITY_ERROR, itConsumoM3);
          } */
        }
        //***/
        if (!visualMeta4) {
            if (itConsumoM4.getValue() == null ||
                itConsumoM4.getValue().toString().length() == 0) {
                errorMeta4 = true;
            }
            if (idFechaS4.getValue() == null ||
                idFechaS4.getValue().toString().length() == 0) {
                errorMeta4 = true;
            }
            
            if (itConsumoM4.getValue() != null && idFechaS4.getValue() == null ) {
                showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                            idFechaS4);
                return;
            }

            if (!errorMeta4)
                this.saveMeta4();
            /*}else{
                 showMessage("Para Registrar el Seguimiento de Meta4 debe Diligenciar el Valor y La Fecha", FacesMessage.SEVERITY_ERROR, itConsumoM4);
          } */
        }
        //***/
        if (!visualMeta5) {
            if (itConsumoM5.getValue() == null ||
                itConsumoM5.getValue().toString().length() == 0) {
                errorMeta5 = true;
            }
            if (idFechaS5.getValue() == null ||
                idFechaS5.getValue().toString().length() == 0) {
                errorMeta5 = true;
            }
            
            if (itConsumoM5.getValue() != null && idFechaS5.getValue() == null ) {
                showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                            idFechaS5);
                return;
            }

            if (!errorMeta5)
                this.saveMeta5();
            /*}else{
                 showMessage("Para Registrar el Seguimiento de Meta5 debe Diligenciar el Valor y La Fecha", FacesMessage.SEVERITY_ERROR, itConsumoM5);
          } */
        }
        
        if (errorMeta1 && 
            errorMeta2 &&
            errorMeta3 &&
            errorMeta4 &&
            errorMeta5 ) {
            showMessage("Se debe ingresar algun valor de seguimiento");
            return;
        }
        
        save();
    }

    //EditarMeta1

    public void cbEditar1_actionListener(ActionEvent actionEvent) throws IdeamException {
        visualMeta1 = false;
        // editarMeta1=true;
        //proyecto.setMetaSeguimientoAno1(null);
        visualBotonG = false;
        AdfFacesContext.getCurrentInstance().addPartialTarget(panelFormLayoutMetas);
        AdfFacesContext.getCurrentInstance().addPartialTarget(cbGuardar);

    }

    //EditarMeta2

    public void cbEditar2_actionListener(ActionEvent actionEvent) throws IdeamException {
        visualMeta2 = false;
        // editarMeta1=true;
        //proyecto.setMetaSeguimientoAno2(null);
        visualBotonG = false;
        AdfFacesContext.getCurrentInstance().addPartialTarget(panelFormLayoutMetas2);
        AdfFacesContext.getCurrentInstance().addPartialTarget(cbGuardar);

    }
    //EditarMeta3

    public void cbEditar3_actionListener(ActionEvent actionEvent) throws IdeamException {
        visualMeta3 = false;
        // editarMeta1=true;
        //proyecto.setMetaSeguimientoAno3(null);
        visualBotonG = false;
        AdfFacesContext.getCurrentInstance().addPartialTarget(panelFormLayoutMetas3);
        AdfFacesContext.getCurrentInstance().addPartialTarget(cbGuardar);

    }
    //EditarMeta4

    public void cbEditar4_actionListener(ActionEvent actionEvent) throws IdeamException {
        visualMeta4 = false;
        // editarMeta1=true;
        //proyecto.setMetaSeguimientoAno4(null);
        visualBotonG = false;
        AdfFacesContext.getCurrentInstance().addPartialTarget(panelFormLayoutMetas4);
        AdfFacesContext.getCurrentInstance().addPartialTarget(cbGuardar);

    }
    //EditarMeta5

    public void cbEditar5_actionListener(ActionEvent actionEvent) throws IdeamException {
        visualMeta5 = false;
        // editarMeta1=true;
        // proyecto.setMetaSeguimientoAno5(null);
        visualBotonG = false;
        AdfFacesContext.getCurrentInstance().addPartialTarget(panelFormLayoutMetas5);
        AdfFacesContext.getCurrentInstance().addPartialTarget(cbGuardar);

    }

    public void cb_cancelar_actionListener(ActionEvent actionEvent) {
        FacesUtils.removeManagedBeanFromSession("back_adicionarProyecto");
        FacesUtils.removeManagedBeanFromSession("adicionarPueaa");
        FacesUtils.removeManagedBeanFromSession("adicionarSeguimientoBean");
    }

    public void save() {
        try {
            pueaD = PueaaDelegate.getInstance();
            proyecto = pueaD.mergePueaPry(proyecto);
            cargaMetas();
            
            AdfFacesContext.getCurrentInstance().addPartialTarget(panelGroupLayoutGeneral);

            /*String params[] = { "del seguimiento PUEAA" };
            showMessage(getText("mensaje_registro_exitoso", params),
                        FacesMessage.SEVERITY_INFO);


            //showMessage("Se registro seguimiento con exito");*/
            showPopup(p_registro_exitoso, true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveMeta1() {
        System.out.println("visualMeta1 out proyecto :" + visualMeta1);
        if (!visualMeta1) {
            System.out.println("visualMeta1 in  proyecto :" + visualMeta1);
            proyecto.setMetaSeguimientoAno1(Double.parseDouble(itConsumoM1.getValue().toString()));
            proyecto.setMetaSeguimientoFechaAno1(new java.sql.Timestamp(((Date)this.idFechaS1.getValue()).getTime()));
        }
    }

    public void saveMeta2() {
        if (!visualMeta2) {
            proyecto.setMetaSeguimientoAno2(Double.parseDouble(itConsumoM2.getValue().toString()));
            proyecto.setMetaSeguimientoFechaAno2(new java.sql.Timestamp(((Date)this.idFechaS2.getValue()).getTime()));
        }
    }

    public void saveMeta3() {
        if (!visualMeta3) {
            proyecto.setMetaSeguimientoAno3(Double.parseDouble(itConsumoM3.getValue().toString()));
            proyecto.setMetaSeguimientoFechaAno3(new java.sql.Timestamp(((Date)this.idFechaS3.getValue()).getTime()));
        }
    }

    public void saveMeta4() {
        if (!visualMeta4) {
            proyecto.setMetaSeguimientoAno4(Double.parseDouble(itConsumoM4.getValue().toString()));
            proyecto.setMetaSeguimientoFechaAno4(new java.sql.Timestamp(((Date)this.idFechaS4.getValue()).getTime()));
        }
    }

    public void saveMeta5() {
        if (!visualMeta5) {
            proyecto.setMetaSeguimientoAno5(Double.parseDouble(itConsumoM5.getValue().toString()));
            proyecto.setMetaSeguimientoFechaAno5(new java.sql.Timestamp(((Date)this.idFechaS5.getValue()).getTime()));
        }
    }

    public void cb_borrar_actionListener(ActionEvent actionEvent) {
        showPopup(p_borrar, true);
    }

    public void d_borrar_dialogListener(DialogEvent dialogEvent) {
        try {
            FuenteDelegate fd = FuenteDelegate.getInstance();
            fd.delete(seguimiento);

            PueaaDelegate pueaaDelegate = PueaaDelegate.getInstance();
            limpiar();
            listaSeguimientos =
                    pueaaDelegate.getSeguimientosProyecto(proyecto);
            AdfFacesContext.getCurrentInstance().addPartialTarget(panelGroupLayoutGeneral);

        } catch (Exception e) {
            showMessage(e.getMessage());
        }
    }

    public void cb_limpiar_actionListener(ActionEvent actionEvent) {
        limpiar();
    }

    public void limpiar() {
        seguimiento = new PueatSeguimiento();
        //itConsumo.setValue(null);
        //idFechaVisita.setValue(null);

    }


    public void t_seguimientos_selectionListener(SelectionEvent selectionEvent) {
        seguimiento = (PueatSeguimiento)t_seguimientos.getSelectedRowData();

        //itConsumo.setValue(seguimiento.getConsumo());
        //idFechaVisita.setValue(seguimiento.getFechaVisita());

        AdfFacesContext.getCurrentInstance().addPartialTarget(panelGroupLayoutGeneral);


    }

    public void setF1(RichForm f1) {
        this.f1 = f1;
    }

    public RichForm getF1() {
        return f1;
    }

    public void setD1(RichDocument d1) {
        this.d1 = d1;
    }

    public RichDocument getD1() {
        return d1;
    }

    public void setPanelGroupLayoutGeneral(RichPanelGroupLayout panelGroupLayoutGeneral) {
        this.panelGroupLayoutGeneral = panelGroupLayoutGeneral;
    }

    public RichPanelGroupLayout getPanelGroupLayoutGeneral() {
        return panelGroupLayoutGeneral;
    }

    public void setPfl1(RichPanelFormLayout pfl1) {
        this.pfl1 = pfl1;
    }

    public RichPanelFormLayout getPfl1() {
        return pfl1;
    }

    public void setCbGuardar(RichCommandButton cbGuardar) {
        this.cbGuardar = cbGuardar;
    }

    public RichCommandButton getCbGuardar() {
        return cbGuardar;
    }

    public void setCbAgregarProyecto(RichCommandButton cbAgregarProyecto) {
        this.cbAgregarProyecto = cbAgregarProyecto;
    }

    public RichCommandButton getCbAgregarProyecto() {
        return cbAgregarProyecto;
    }

    public void setTblPred(RichTable tblPred) {
        this.tblPred = tblPred;
    }

    public RichTable getTblPred() {
        return tblPred;
    }

    public void setCbModificarPred(RichCommandButton cbModificarPred) {
        this.cbModificarPred = cbModificarPred;
    }

    public RichCommandButton getCbModificarPred() {
        return cbModificarPred;
    }

    public void setCbEliminarPred(RichCommandButton cbEliminarPred) {
        this.cbEliminarPred = cbEliminarPred;
    }

    public RichCommandButton getCbEliminarPred() {
        return cbEliminarPred;
    }

    public void setProyecto(PueatProyectos proyecto) {
        this.proyecto = proyecto;
    }

    public PueatProyectos getProyecto() {
        return proyecto;
    }

    public void setSeguimiento(PueatSeguimiento seguimiento) {
        this.seguimiento = seguimiento;
    }

    public PueatSeguimiento getSeguimiento() {
        return seguimiento;
    }

    public void setListaSeguimientos(List listaSeguimientos) {
        this.listaSeguimientos = listaSeguimientos;
    }

    public List getListaSeguimientos() {
        return listaSeguimientos;
    }

    public void setT_seguimientos(RichTable t_seguimientos) {
        this.t_seguimientos = t_seguimientos;
    }

    public RichTable getT_seguimientos() {
        return t_seguimientos;
    }

    public void setP_borrar(RichPopup p_borrar) {
        this.p_borrar = p_borrar;
    }

    public RichPopup getP_borrar() {
        return p_borrar;
    }

    public void setCb_borrar(RichCommandButton cb_borrar) {
        this.cb_borrar = cb_borrar;
    }

    public RichCommandButton getCb_borrar() {
        return cb_borrar;
    }

    public void setCb_limpiar(RichCommandButton cb_limpiar) {
        this.cb_limpiar = cb_limpiar;
    }

    public RichCommandButton getCb_limpiar() {
        return cb_limpiar;
    }

    public void setCb_regresar(RichCommandButton cb_regresar) {
        this.cb_regresar = cb_regresar;
    }

    public RichCommandButton getCb_regresar() {
        return cb_regresar;
    }

    public void setD_borrar(RichDialog d_borrar) {
        this.d_borrar = d_borrar;
    }

    public RichDialog getD_borrar() {
        return d_borrar;
    }


    public void setCumplioMeta1(Integer cumplioMeta1) {
        this.cumplioMeta1 = cumplioMeta1;
    }

    public Integer getCumplioMeta1() {
        return cumplioMeta1;
    }

    public void setCumplioMeta2(Integer cumplioMeta2) {
        this.cumplioMeta2 = cumplioMeta2;
    }

    public Integer getCumplioMeta2() {
        return cumplioMeta2;
    }

    public void setCumplioMeta3(Integer cumplioMeta3) {
        this.cumplioMeta3 = cumplioMeta3;
    }

    public Integer getCumplioMeta3() {
        return cumplioMeta3;
    }

    public void setCumplioMeta4(Integer cumplioMeta4) {
        this.cumplioMeta4 = cumplioMeta4;
    }

    public Integer getCumplioMeta4() {
        return cumplioMeta4;
    }

    public void setCumplioMeta5(Integer cumplioMeta5) {
        this.cumplioMeta5 = cumplioMeta5;
    }

    public Integer getCumplioMeta5() {
        return cumplioMeta5;
    }

    public void setUsuarioAgua(UsuarioAgua usuarioAgua) {
        this.usuarioAgua = usuarioAgua;
    }

    public UsuarioAgua getUsuarioAgua() {
        return usuarioAgua;
    }


    public void setIdFechaS1(RichInputDate idFechaS1) {
        this.idFechaS1 = idFechaS1;
    }

    public RichInputDate getIdFechaS1() {
        return idFechaS1;
    }

    public void setItConsumoM1(RichInputText itConsumoM1) {
        this.itConsumoM1 = itConsumoM1;
    }

    public RichInputText getItConsumoM1() {
        return itConsumoM1;
    }

    public void setIdFechaS2(RichInputDate idFechaS2) {
        this.idFechaS2 = idFechaS2;
    }

    public RichInputDate getIdFechaS2() {
        return idFechaS2;
    }

    public void setItConsumoM2(RichInputText itConsumoM2) {
        this.itConsumoM2 = itConsumoM2;
    }

    public RichInputText getItConsumoM2() {
        return itConsumoM2;
    }

    public void setIdFechaS3(RichInputDate idFechaS3) {
        this.idFechaS3 = idFechaS3;
    }

    public RichInputDate getIdFechaS3() {
        return idFechaS3;
    }

    public void setItConsumoM3(RichInputText itConsumoM3) {
        this.itConsumoM3 = itConsumoM3;
    }

    public RichInputText getItConsumoM3() {
        return itConsumoM3;
    }

    public void setIdFechaS4(RichInputDate idFechaS4) {
        this.idFechaS4 = idFechaS4;
    }

    public RichInputDate getIdFechaS4() {
        return idFechaS4;
    }

    public void setItConsumoM4(RichInputText itConsumoM4) {
        this.itConsumoM4 = itConsumoM4;
    }

    public RichInputText getItConsumoM4() {
        return itConsumoM4;
    }

    public void setIdFechaS5(RichInputDate idFechaS5) {
        this.idFechaS5 = idFechaS5;
    }

    public RichInputDate getIdFechaS5() {
        return idFechaS5;
    }

    public void setItConsumoM5(RichInputText itConsumoM5) {
        this.itConsumoM5 = itConsumoM5;
    }

    public RichInputText getItConsumoM5() {
        return itConsumoM5;
    }

    public void setVisualMeta1(boolean visualMeta1) {
        this.visualMeta1 = visualMeta1;
    }

    public boolean isVisualMeta1() {
        return visualMeta1;
    }

    public void setVisualMeta2(boolean visualMeta2) {
        this.visualMeta2 = visualMeta2;
    }

    public boolean isVisualMeta2() {
        return visualMeta2;
    }

    public void setVisualMeta3(boolean visualMeta3) {
        this.visualMeta3 = visualMeta3;
    }

    public boolean isVisualMeta3() {
        return visualMeta3;
    }

    public void setVisualMeta4(boolean visualMeta4) {
        this.visualMeta4 = visualMeta4;
    }

    public boolean isVisualMeta4() {
        return visualMeta4;
    }

    public void setVisualMeta5(boolean visualMeta5) {
        this.visualMeta5 = visualMeta5;
    }

    public boolean isVisualMeta5() {
        return visualMeta5;
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

    public void setVisualBotonG(boolean visualBotonG) {
        this.visualBotonG = visualBotonG;
    }

    public boolean isVisualBotonG() {
        return visualBotonG;
    }

    public void setCbEditarRow1(RichCommandButton cbEditarRow1) {
        this.cbEditarRow1 = cbEditarRow1;
    }

    public RichCommandButton getCbEditarRow1() {
        return cbEditarRow1;
    }

    public void setPanelFormLayoutMetas(RichPanelFormLayout panelFormLayoutMetas) {
        this.panelFormLayoutMetas = panelFormLayoutMetas;
    }

    public RichPanelFormLayout getPanelFormLayoutMetas() {
        return panelFormLayoutMetas;
    }

    public void setPanelFormLayoutMetas2(RichPanelFormLayout panelFormLayoutMetas2) {
        this.panelFormLayoutMetas2 = panelFormLayoutMetas2;
    }

    public RichPanelFormLayout getPanelFormLayoutMetas2() {
        return panelFormLayoutMetas2;
    }

    public void setPanelFormLayoutMetas3(RichPanelFormLayout panelFormLayoutMetas3) {
        this.panelFormLayoutMetas3 = panelFormLayoutMetas3;
    }

    public RichPanelFormLayout getPanelFormLayoutMetas3() {
        return panelFormLayoutMetas3;
    }

    public void setPanelFormLayoutMetas4(RichPanelFormLayout panelFormLayoutMetas4) {
        this.panelFormLayoutMetas4 = panelFormLayoutMetas4;
    }

    public RichPanelFormLayout getPanelFormLayoutMetas4() {
        return panelFormLayoutMetas4;
    }

    public void setPanelFormLayoutMetas5(RichPanelFormLayout panelFormLayoutMetas5) {
        this.panelFormLayoutMetas5 = panelFormLayoutMetas5;
    }

    public RichPanelFormLayout getPanelFormLayoutMetas5() {
        return panelFormLayoutMetas5;
    }


    public void setCbEditarRow2(RichCommandButton cbEditarRow2) {
        this.cbEditarRow2 = cbEditarRow2;
    }

    public RichCommandButton getCbEditarRow2() {
        return cbEditarRow2;
    }

    public void setCbEditarRow3(RichCommandButton cbEditarRow3) {
        this.cbEditarRow3 = cbEditarRow3;
    }

    public RichCommandButton getCbEditarRow3() {
        return cbEditarRow3;
    }

    public void setCbEditarRow4(RichCommandButton cbEditarRow4) {
        this.cbEditarRow4 = cbEditarRow4;
    }

    public RichCommandButton getCbEditarRow4() {
        return cbEditarRow4;
    }

    public void setCbEditarRow5(RichCommandButton cbEditarRow5) {
        this.cbEditarRow5 = cbEditarRow5;
    }

    public RichCommandButton getCbEditarRow5() {
        return cbEditarRow5;
    }

    public void setPgl14(RichPanelGroupLayout pgl14) {
        this.pgl14 = pgl14;
    }

    public RichPanelGroupLayout getPgl14() {
        return pgl14;
    }

    public void setCb_aceptar(RichCommandButton cb_aceptar) {
        this.cb_aceptar = cb_aceptar;
    }

    public RichCommandButton getCb_aceptar() {
        return cb_aceptar;
    }
    
    public void cb_aceptar_actionListener(ActionEvent actionEvent) {
        
    }

    public void setAceptarAction(String aceptarAction) {
        this.aceptarAction = aceptarAction;
    }

    public String getAceptarAction() {
        return aceptarAction;
    }

    public void setPsl7(RichPanelStretchLayout psl7) {
        this.psl7 = psl7;
    }

    public RichPanelStretchLayout getPsl7() {
        return psl7;
    }

    public void setOt8(RichOutputText ot8) {
        this.ot8 = ot8;
    }

    public RichOutputText getOt8() {
        return ot8;
    }
}
