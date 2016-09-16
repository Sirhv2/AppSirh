package co.gov.ideam.sirh.usuarios.agua.view;


import co.gov.ideam.sirh.parametros.model.Autoridades;
import co.gov.ideam.sirh.parametros.model.Categoria;
import co.gov.ideam.sirh.parametros.model.Divipola;
import co.gov.ideam.sirh.parametros.model.Lista;
import co.gov.ideam.sirh.parametros.view.ParametrosDelegate;
import co.gov.ideam.sirh.pueaa.PueaaDelegate;
import co.gov.ideam.sirh.pueaa.model.PueatProyectos;
import co.gov.ideam.sirh.pueaa.model.PueatPuea;
import co.gov.ideam.sirh.seguridad.model.UsuarioConectadoTO;
import co.gov.ideam.sirh.usuarios.agua.model.UsuarioAgua;
import co.gov.ideam.sirh.usuarios.agua.model.UsuarioAguaTO;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.view.FacesUtils;
import co.gov.ideam.sirh.view.StandarBean;
import co.gov.ideam.sirh.view.util.SpecialTreeModel;
import co.gov.ideam.sirh.view.util.TreeNode;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UINamingContainer;
import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;

import oracle.adf.view.rich.component.rich.RichDialog;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.RichMenu;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.data.RichTree;
import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.layout.RichPanelBox;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.component.rich.nav.RichCommandLink;
import oracle.adf.view.rich.component.rich.nav.RichCommandMenuItem;
import oracle.adf.view.rich.component.rich.output.RichImage;
import oracle.adf.view.rich.component.rich.output.RichMessage;
import oracle.adf.view.rich.component.rich.output.RichOutputLabel;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.component.rich.output.RichSpacer;

import org.apache.myfaces.trinidad.event.SelectionEvent;
import org.apache.myfaces.trinidad.model.TreeModel;


public class AdicionarPueaaBean extends StandarBean {
    private RichForm f1;
    private RichDocument d1;
    private RichPanelStretchLayout psl12;
    private RichPanelGroupLayout pgl115;
    private List listaDepartamentos;
    private List listaMunicipios;
    private List listaClasificacionSuelo;
    private List listaSistemasReferencia;
    private UsuarioAgua usuarioAgua;
    private PueatPuea pueaa;
    private Divipola departamentoSeleccionado;
    private Divipola municipioSeleccionado;
    private List listaDeptosPredio;
    private List listaMunPredio;
    private List listaTipoCentroPoblado;
    private String accionAdicionar;
    private RichCommandMenuItem cmi_adicionar_proyecto_pueaa;
    private RichCommandMenuItem cmi_adicionar_seguimiento;
    private RichPanelGroupLayout pgl6;
    private RichPanelGroupLayout pgl7;
    private RichMenu m_archivo;
    private RichPanelGroupLayout pgl11;
    private RichOutputText ot3;
    private RichPanelFormLayout pfl3;
    private RichPanelStretchLayout psl5;
    private RichOutputText ot4;
    private RichPanelFormLayout pfl4;
    private RichPanelBox pb_paso_predio;

    private RichPanelStretchLayout psl6;
    private RichPanelGroupLayout pgl10;

    private RichCommandButton cb_prev_predio;

    private RichCommandButton cb_terminar;
    private RichSpacer s17;
    private RichSpacer s18;
    private RichSpacer s19;
    private RichOutputText ot5;
    private RichPanelFormLayout pfl5;
    private RichInputText it_nombre_pueaa;
    private RichInputText it_observaciones;
    private RichInputText it_direccion_predio;
    private RichInputText it_cedula_predio;
    private RichSelectOneChoice soc_depto_predio;
    private UISelectItems si9;
    private RichSelectOneChoice soc_municipio_predio;
    private UISelectItems si10;
    private RichSelectOneChoice soc_estado_pueaa;
    private UISelectItems si11;
    private RichSelectOneChoice soc_sistema_referencia;
    private UISelectItems si12;
    private RichInputText it_grados;
    private RichInputText it_minutos;
    private RichInputText it_segundos;
    private RichInputText it_altitud;
    private RichPanelFormLayout pfl6;
    private RichCommandButton cb_cancelar_predio;
    private RichSpacer s20;
    private RichPopup p_cancelar;
    private RichDialog d_cancelar;
    private RichOutputText ot6;

    private RichCommandButton cb_si_cancelar;
    private RichSpacer s21;
    private RichCommandButton cb_no_cancelar;

    private RichSpacer s22;
    private RichPopup p_registro_exitoso;
    private RichDialog d_registro_exitoso;
    private RichPanelGroupLayout pgl14;
    private RichCommandButton cb_aceptar;
    private RichPanelStretchLayout psl7;
    private RichImage i1;
    private RichOutputText ot8;

    private RichPanelStretchLayout psl8;
    private RichPanelGroupLayout pgl15;
    private RichPanelGroupLayout pgl16;
    private RichPanelGroupLayout pgl13;
    private RichSpacer s23;

    private RichSpacer s24;
    private RichOutputText ot9;
    private RichSpacer s25;
    private RichSpacer s26;
    private RichPanelFormLayout pfl7;


    private UISelectItems si15;


    private RichInputText it_matricula;
    private RichPopup p_ciiu;
    private RichDialog d_ciiu;
    private RichPanelGroupLayout pgl19;
    private RichSpacer s29;
    private RichCommandButton cb_mostrar_ciiu;
    private RichInputText it_desc_ciiu;
    private UINamingContainer s27;
    private RichPanelStretchLayout psl9;
    private RichPanelFormLayout pfl8;
    private RichOutputLabel ol_invisible;
    private RichOutputLabel ol_mensaje;
    private RichMessage m2;
    private RichMessage m3;
    private RichSpacer s28;
    private String aceptarAction;
    private RichPanelGroupLayout pgl17;
    private UsuarioAguaTO usuarioAguaTo;
    private UISelectItems si16;
    private RichSelectOneChoice soc_mun_tipo_suelo;
    private RichSelectOneChoice soc_tipo_centro;
    private UISelectItems si17;
    private UISelectItems si5;
    private RichPanelFormLayout pfl9;
    private RichOutputLabel ol1;
    private RichOutputLabel ol2;
    private RichInputText it1;
    private RichInputText it2;
    private RichInputText it3;
    private RichSpacer s30;
    private RichInputText it_numero_resolucion;
    private RichInputText it_area;
    private RichInputDate fecha_presenta;
    private RichInputDate fecha_aprueba;
    private RichInputDate fecha_vigencia_ini;
    private RichInputDate fecha_vigencia_fin;
    private RichTable t1;
    private RichCommandLink clPueaaPry;
    private boolean pueaaLoad;
    private RichTree treepry;
    private TreeModel pueaaPryTreeModel;
    PueaaDelegate pueaD;
    List<PueatPuea> lstPueaa;
    PueatPuea pueaaSelected;
    private Integer estadoSelected;


    public AdicionarPueaaBean() {
        //FacesUtils.setActiveBean("AdicionarPueaaBean", "AdicionarPueaaBean",
        //                         AdicionarPueaaBean.class);
        
        FacesUtils.removeManagedBeanFromSession("PueaaPryTreeHandler");
        FacesUtils.removeManagedBeanFromSession("back_adicionarProyecto");

        ///FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("back_adicionarProyecto", null);
        //FacesUtils.removeFromSession("proyectoSeleccionado");
        this.load();

    }

    public void load() {
        try {
            System.out.println("HCP entro a load AdicionarPueaaBean");
            
            Categoria categoria = new Categoria();
            ParametrosDelegate pd = ParametrosDelegate.getInstance();
            pueaD = PueaaDelegate.getInstance();
            pueaaSelected = new PueatPuea();
            pueaaLoad = true;


            List<SelectItem> lstSelect = new ArrayList<SelectItem>();
            List<Lista> lista = new ArrayList(); //carga el selectItem
            lista = pd.getAllListaByCategoria(95L);
            for (Lista list : lista) {
                SelectItem si =
                    new SelectItem(list.getCodigo(), list.getValor());
                lstSelect.add(si);
            }
            listaClasificacionSuelo = lstSelect;

            //Pueaa Seleccionado
            Object obj = FacesUtils.getFromSession("pueaaSeleccionado");
            
            if (obj instanceof PueatPuea) {
                this.pueaaSelected = (PueatPuea)obj;
       
                pueaaLoad = false;
                estadoSelected =
                        Integer.valueOf((pueaaSelected.getIdListas()).intValue());

                if (estadoSelected == 1051)
                    pueaaLoad = true;


            }
            Object objC = FacesUtils.getFromSession("pueaaCreado");
            if (objC instanceof PueatPuea) {
                this.pueaaSelected = (PueatPuea)obj;
                pueaaLoad = false;
                estadoSelected =
                        Integer.valueOf((pueaaSelected.getIdListas()).intValue());

                if (estadoSelected == 1051)
                    pueaaLoad = true;


            }

            create_tree_pueaapry();
            //Lista de Pueaa
            lstPueaa = new ArrayList<PueatPuea>();


        } catch (IdeamException e) {
            showMessage(e);

        }

    }

    public String cb_regresar_actionListener(ActionEvent actionEvent) {
        FacesUtils.removeManagedBeanFromSession("detalleUsuarioAgua");
        FacesUtils.removeManagedBeanFromSession("adicionarPueaa");
        return "regresarUsuarioAgua";
    }

    public String cb_terminar_actionListener(ActionEvent actionEvent) {
        boolean continuar = validarPueaa();
        if (continuar) {
            if (this.pueaaSelected.getId() != null)
                actualizar();
            else
                guardar();
        }
        return "adicionarPueaa";
    }

    /**
     * Metodo que selecciona un programa pueaa para su posterior uso
     * @param selectionEvent
     */
    public void table_selectionListener(SelectionEvent selectionEvent) {
        RichTable t1 = (RichTable)selectionEvent.getSource();
        pueaaSelected = (PueatPuea)t1.getSelectedRowData();
        FacesUtils.removeFromSession("pueaaSelected");
        FacesUtils.setInSession("pueaaSelected", pueaaSelected);
        llenarFormaPueaa();

    }

    /**
     * Metodo para llenar el formulario pueaa con objeto del back
     */
    public void llenarFormaPueaa() {
        this.it_nombre_pueaa.setValue(pueaaSelected.getNumeroExpediente());
        this.it_numero_resolucion.setValue(pueaaSelected.getNumResolucion());
        this.fecha_presenta.setValue(pueaaSelected.getFechaExpedicion());

    }

    public boolean validarPueaa() {
        boolean continuar = true;
        // validar datos obligatorios
        if (this.it_nombre_pueaa == null ||
            this.it_nombre_pueaa.getValue() == null ||
            this.it_nombre_pueaa.getValue().toString().length() == 0) {
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                        it_nombre_pueaa);
            continuar = false;
        }
        if (this.fecha_presenta == null ||
            this.fecha_presenta.getValue() == null ||
            this.fecha_presenta.getValue().toString().length() == 0) {
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                        fecha_presenta);
            continuar = false;
        }
        if (this.soc_estado_pueaa == null ||
            this.soc_estado_pueaa.getValue() == null) {
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                        soc_estado_pueaa);
            continuar = false;
        }
        if (this.it_numero_resolucion.getValue() == null ||
            this.it_numero_resolucion.getValue().toString().length() == 0) {
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                        it_numero_resolucion);
            continuar = false;
        }
        if (this.fecha_aprueba.getValue() == null ||
            this.fecha_aprueba.getValue().toString().length() == 0) {
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                        fecha_aprueba);
            continuar = false;
        }
        if (this.fecha_vigencia_ini.getValue() == null ||
            this.fecha_vigencia_ini.getValue().toString().length() == 0) {
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                        fecha_vigencia_ini);
            continuar = false;
        }
        if (this.fecha_vigencia_fin.getValue() == null ||
            this.fecha_vigencia_fin.getValue().toString().length() == 0) {
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                        fecha_vigencia_fin);
            continuar = false;
        }

        Date fecApr = (Date)this.fecha_aprueba.getValue();
        Date fecIni = (Date)this.fecha_vigencia_ini.getValue();
        Date fecFin = (Date)this.fecha_vigencia_fin.getValue();

        if (fecApr != null && fecIni != null)
            if (fecIni.before(fecApr)) {
                showMessage("fecha_ini_errada", FacesMessage.SEVERITY_ERROR,
                            fecha_vigencia_ini);
                continuar = false;
            }

        if (fecFin != null && fecIni != null)
            if (fecFin.before(fecIni)) {
                showMessage("fecha_fin_errada", FacesMessage.SEVERITY_ERROR,
                            fecha_vigencia_fin);
                continuar = false;
            }

        if (it_observaciones != null && it_observaciones.getValue() != null && it_observaciones.getValue().toString().length() > 200  ){
            showMessage("longitud_errada", FacesMessage.SEVERITY_ERROR,
                        it_observaciones);
            continuar = false;
        }
        
        return continuar;
    }

    /**
     * Metodo para guardar pueaa
     */
    public void guardar() {
        pueaa = new PueatPuea();
        try {
            //Usuario Autenticacion y el usuario del agua seleccionado
            UsuarioConectadoTO usuarioConectado =
                (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
            Autoridades autoridadAmbiental =
                usuarioConectado.getUsuario().getAutoridadAmbiental();
            UsuariosAguaDelegate uad = UsuariosAguaDelegate.getInstance();

            Object obj = FacesUtils.getFromSession("usuarioSeleccionado");
            
            UsuarioAgua usuarioAgua = null;
            UsuarioAguaTO usuarioAguaTO = null;

            Long idUsuario = 0L;
            
            if (obj instanceof UsuarioAgua) {
                usuarioAgua = (UsuarioAgua)FacesUtils.getFromSession("usuarioSeleccionado");
                idUsuario = usuarioAgua.getCodigo();
            }

            if (obj instanceof UsuarioAguaTO) {
                usuarioAguaTO = (UsuarioAguaTO)FacesUtils.getFromSession("usuarioSeleccionado");
                idUsuario = usuarioAguaTO.getCodigo();
            }


            //pueaa usuarioConectado.getUsuario().getAutoridadAmbiental().getId().longValue()
            pueaa.setCodigoAutoridad(autoridadAmbiental.getId().longValue());
            //pueaa.setId(4L);
            pueaa.setIdUsuario(idUsuario);
            //pueaa.setNumeroExpediente(Long.parseLong(it_nombre_pueaa.getValue().toString()));
            pueaa.setNumeroExpediente((String)it_nombre_pueaa.getValue());
            pueaa.setFechaExpedicion(new java.sql.Timestamp(((Date)this.fecha_presenta.getValue()).getTime()));
            pueaa.setIdListas((new Long((Integer)soc_estado_pueaa.getValue())));
            //Resolucion
            //pueaa.setNumResolucion(Long.parseLong(it_numero_resolucion.getValue().toString()));
            pueaa.setNumResolucion((String)it_numero_resolucion.getValue());
            pueaa.setFechaAprobacion(new java.sql.Timestamp(((Date)this.fecha_aprueba.getValue()).getTime()));
            pueaa.setVigenciaInicial(new java.sql.Timestamp(((Date)this.fecha_vigencia_ini.getValue()).getTime()));
            pueaa.setVigenciaFinal(new java.sql.Timestamp(((Date)this.fecha_vigencia_fin.getValue()).getTime()));
            pueaa.setObservaciones((String)it_observaciones.getValue());
            PueatPuea pueaaCreated = pueaD.registrarPueaa(pueaa);
            FacesUtils.setInSession("pueaaSeleccionado", pueaaCreated);
            pueaaLoad = false;
            showPopup(p_registro_exitoso, true);
            // AdfFacesContext.getCurrentInstance().addPartialTarget(t1);

        } catch (IdeamException e) {
            showMessage(e);
        }
    }

    /**
     * Metodo para actualizar pueaa
     */
    public void actualizar() {
        pueaa = new PueatPuea();
        try {
            //Usuario Autenticacion y el usuario del agua seleccionado
            UsuarioConectadoTO usuarioConectado =
                (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
            Autoridades autoridadAmbiental =
                usuarioConectado.getUsuario().getAutoridadAmbiental();
            UsuariosAguaDelegate uad = UsuariosAguaDelegate.getInstance();
            
            Object obj = FacesUtils.getFromSession("usuarioSeleccionado");
            
            UsuarioAgua usuarioAgua = null;
            UsuarioAguaTO usuarioAguaTO = null;
            
            Long idUsuario = 0L;
            
            if (obj instanceof UsuarioAgua) {
                usuarioAgua = (UsuarioAgua)FacesUtils.getFromSession("usuarioSeleccionado");
                idUsuario = usuarioAgua.getCodigo();
            }

            if (obj instanceof UsuarioAguaTO) {
                usuarioAguaTO = (UsuarioAguaTO)FacesUtils.getFromSession("usuarioSeleccionado");
                idUsuario = usuarioAguaTO.getCodigo();
            }

            pueaa.setCodigoAutoridad(autoridadAmbiental.getId().longValue());
            pueaa.setId(pueaaSelected.getId());
            pueaa.setIdUsuario(idUsuario);
            //pueaa.setNumeroExpediente(Long.parseLong(it_nombre_pueaa.getValue().toString()));
            pueaa.setNumeroExpediente((String)it_nombre_pueaa.getValue());
            pueaa.setFechaExpedicion(new java.sql.Timestamp(((Date)this.fecha_presenta.getValue()).getTime()));
            pueaa.setIdListas((new Long((Integer)soc_estado_pueaa.getValue())));
            //Resolucion
            //pueaa.setNumResolucion(Long.parseLong(it_numero_resolucion.getValue().toString()));
            pueaa.setNumResolucion((String)it_numero_resolucion.getValue());
            pueaa.setFechaAprobacion(new java.sql.Timestamp(((Date)this.fecha_aprueba.getValue()).getTime()));
            pueaa.setVigenciaInicial(new java.sql.Timestamp(((Date)this.fecha_vigencia_ini.getValue()).getTime()));
            pueaa.setVigenciaFinal(new java.sql.Timestamp(((Date)this.fecha_vigencia_fin.getValue()).getTime()));
            pueaa.setObservaciones((String)it_observaciones.getValue());

            pueaD.mergePueatPuea(pueaa);
            showPopup(p_registro_exitoso, true);


        } catch (IdeamException e) {
            showMessage(e);
        }
    }

    /**
     * Metodo que arma las listas y los tree de los pueaa y proyectos
     */
    public void create_tree_pueaapry() {

        try {
            PueaaDelegate pueaD;
            pueaD = PueaaDelegate.getInstance();


            List listaNodosPry = new ArrayList();
            List<PueatProyectos> listaProyectos =
                pueaD.getPueatProyectosFindByPueaa(pueaaSelected);
            TreeNode nodoProyectos =
                new TreeNode(getText("PROYECTOS"), "Proyectos");
            nodoProyectos.setData("Proyectos");
            listaNodosPry.add(nodoProyectos);

            for (PueatProyectos proyecto : listaProyectos) {
                TreeNode nodoProyecto =
                    new TreeNode(getText("NOMBRE_PROYECTO") + "  " +
                                 proyecto.getNombreProyecto(), "Proyecto",
                                 false, false);
                nodoProyecto.setData(proyecto);
                nodoProyecto.setExtraData(proyecto);
                nodoProyecto.setExtraData2(proyecto);
                nodoProyectos.getChildren().add(nodoProyecto);
            }

            pueaaPryTreeModel =
                    new SpecialTreeModel(listaNodosPry, "children");


        } catch (IdeamException e) {
            showMessage(e);
        }
    }

    public void clPueaaPry_actionListener(ActionEvent actionEvent) {
        String nombreParametro =
            (String)actionEvent.getComponent().getAttributes().get("nombreParametro");
        Object data =
            actionEvent.getComponent().getAttributes().get("valorParametro");
        
        if (nombreParametro != null && data != null) {
            FacesUtils.setInSession(nombreParametro, data);
            if (data instanceof String) {
                if (data.toString().equals("Proyectos")) {
                    Object proyectostr =
                        actionEvent.getComponent().getAttributes().get("extraInfo");
                    if (pueaa != null) {
                        FacesUtils.setInSession("pueaaSeleccionado",
                                                proyectostr);
                    }
                }

            }

            if (data instanceof PueatProyectos) {
                Object proyecto =
                    actionEvent.getComponent().getAttributes().get("extraInfo");
                if (pueaa != null) {
                    System.out.println("HUGO Va a editar proyecto 1");
                    FacesUtils.removeManagedBeanFromSession("back_adicionarProyecto");
                    FacesUtils.setInSession("proyectoSeleccionado", proyecto);
                }

                Object proyecto2 =
                    actionEvent.getComponent().getAttributes().get("extraInfo2");
                if (proyecto != null) {
                    System.out.println("HUGO Va a editar proyecto 2");
                    FacesUtils.removeManagedBeanFromSession("back_adicionarProyecto");
                    FacesUtils.setInSession("proyectoSeleccionado", proyecto2);
                }

                FacesUtils.setInSession("paginaOrigen", "detalleUsuario");
            }
        }
    }


    //Metodo Get y set

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

    public void setPsl12(RichPanelStretchLayout psl1) {
        this.psl12 = psl1;
    }

    public RichPanelStretchLayout getPsl12() {
        return psl12;
    }

    public void setPgl115(RichPanelGroupLayout pgl1) {
        this.pgl115 = pgl1;
    }

    public RichPanelGroupLayout getPgl115() {
        return pgl115;
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

    public UsuarioAgua getUsuarioAgua() {
        return usuarioAgua;
    }

    public void setUsuarioAgua(UsuarioAgua usuarioAgua) {
        this.usuarioAgua = usuarioAgua;
    }

    public void setPgl6(RichPanelGroupLayout pgl6) {
        this.pgl6 = pgl6;
    }

    public RichPanelGroupLayout getPgl6() {
        return pgl6;
    }

    public void setPgl7(RichPanelGroupLayout pgl7) {
        this.pgl7 = pgl7;
    }

    public RichPanelGroupLayout getPgl7() {
        return pgl7;
    }


    public void setOt3(RichOutputText ot3) {
        this.ot3 = ot3;
    }

    public RichOutputText getOt3() {
        return ot3;
    }

    public void setPfl3(RichPanelFormLayout pfl3) {
        this.pfl3 = pfl3;
    }

    public RichPanelFormLayout getPfl3() {
        return pfl3;
    }

    public void setPsl5(RichPanelStretchLayout psl5) {
        this.psl5 = psl5;
    }

    public RichPanelStretchLayout getPsl5() {
        return psl5;
    }

    public void setOt4(RichOutputText ot4) {
        this.ot4 = ot4;
    }

    public RichOutputText getOt4() {
        return ot4;
    }

    public void setPfl4(RichPanelFormLayout pfl4) {
        this.pfl4 = pfl4;
    }

    public RichPanelFormLayout getPfl4() {
        return pfl4;
    }


    public void setPb_paso_predio(RichPanelBox pb1) {
        this.pb_paso_predio = pb1;
    }

    public RichPanelBox getPb_paso_predio() {
        return pb_paso_predio;
    }

    public void setPsl6(RichPanelStretchLayout psl6) {
        this.psl6 = psl6;
    }

    public RichPanelStretchLayout getPsl6() {
        return psl6;
    }

    public void setPgl10(RichPanelGroupLayout pgl10) {
        this.pgl10 = pgl10;
    }

    public RichPanelGroupLayout getPgl10() {
        return pgl10;
    }

    public void setPgl11(RichPanelGroupLayout pgl11) {
        this.pgl11 = pgl11;
    }

    public RichPanelGroupLayout getPgl11() {
        return pgl11;
    }

    public void setCb_prev_predio(RichCommandButton cb1) {
        this.cb_prev_predio = cb1;
    }

    public RichCommandButton getCb_prev_predio() {
        return cb_prev_predio;
    }


    public void setCb_terminar(RichCommandButton cb2) {
        this.cb_terminar = cb2;
    }

    public RichCommandButton getCb_terminar() {
        return cb_terminar;
    }

    public void setS17(RichSpacer s17) {
        this.s17 = s17;
    }

    public RichSpacer getS17() {
        return s17;
    }

    public void setS18(RichSpacer s18) {
        this.s18 = s18;
    }

    public RichSpacer getS18() {
        return s18;
    }

    public void setS19(RichSpacer s19) {
        this.s19 = s19;
    }

    public RichSpacer getS19() {
        return s19;
    }

    public void setOt5(RichOutputText ot5) {
        this.ot5 = ot5;
    }

    public RichOutputText getOt5() {
        return ot5;
    }

    public void setPfl5(RichPanelFormLayout pfl5) {
        this.pfl5 = pfl5;
    }

    public RichPanelFormLayout getPfl5() {
        return pfl5;
    }

    public void setIt_nombre_pueaa(RichInputText it1) {
        this.it_nombre_pueaa = it1;
    }

    public RichInputText getIt_nombre_pueaa() {
        return it_nombre_pueaa;
    }

    public void setIt_direccion_predio(RichInputText it2) {
        this.it_direccion_predio = it2;
    }

    public RichInputText getIt_direccion_predio() {
        return it_direccion_predio;
    }

    public void setIt_cedula_predio(RichInputText it1) {
        this.it_cedula_predio = it1;
    }

    public RichInputText getIt_cedula_predio() {
        return it_cedula_predio;
    }


    public void setSoc_depto_predio(RichSelectOneChoice soc1) {
        this.soc_depto_predio = soc1;
    }

    public RichSelectOneChoice getSoc_depto_predio() {
        return soc_depto_predio;
    }

    public void setSi9(UISelectItems si9) {
        this.si9 = si9;
    }

    public UISelectItems getSi9() {
        return si9;
    }

    public void setSoc_municipio_predio(RichSelectOneChoice soc2) {
        this.soc_municipio_predio = soc2;
    }

    public RichSelectOneChoice getSoc_municipio_predio() {
        return soc_municipio_predio;
    }

    public void setSi10(UISelectItems si10) {
        this.si10 = si10;
    }

    public UISelectItems getSi10() {
        return si10;
    }


    public void setSi11(UISelectItems si11) {
        this.si11 = si11;
    }

    public UISelectItems getSi11() {
        return si11;
    }

    public List getListaClasificacionSuelo() {
        return listaClasificacionSuelo;
    }

    public void setListaClasificacionSuelo(List listaClasificacionSuelo) {
        this.listaClasificacionSuelo = listaClasificacionSuelo;
    }


    public List getListaSistemasReferencia() {
        return listaSistemasReferencia;
    }

    public void setListaSistemasReferencia(List listaSistemasReferencia) {
        this.listaSistemasReferencia = listaSistemasReferencia;
    }

    public void setSoc_sistema_referencia(RichSelectOneChoice soc1) {
        this.soc_sistema_referencia = soc1;
    }

    public RichSelectOneChoice getSoc_sistema_referencia() {
        return soc_sistema_referencia;
    }

    public void setSi12(UISelectItems si12) {
        this.si12 = si12;
    }

    public UISelectItems getSi12() {
        return si12;
    }

    public void setIt_grados(RichInputText it1) {
        this.it_grados = it1;
    }

    public RichInputText getIt_grados() {
        return it_grados;
    }

    public void setIt_minutos(RichInputText it2) {
        this.it_minutos = it2;
    }

    public RichInputText getIt_minutos() {
        return it_minutos;
    }

    public void setIt_segundos(RichInputText it3) {
        this.it_segundos = it3;
    }

    public RichInputText getIt_segundos() {
        return it_segundos;
    }

    public void setIt_altitud(RichInputText it4) {
        this.it_altitud = it4;
    }

    public RichInputText getIt_altitud() {
        return it_altitud;
    }

    public void setPfl6(RichPanelFormLayout pfl6) {
        this.pfl6 = pfl6;
    }

    public RichPanelFormLayout getPfl6() {
        return pfl6;
    }

    public void setCb_cancelar_predio(RichCommandButton cb1) {
        this.cb_cancelar_predio = cb1;
    }

    public RichCommandButton getCb_cancelar_predio() {
        return cb_cancelar_predio;
    }

    public void setS20(RichSpacer s20) {
        this.s20 = s20;
    }

    public RichSpacer getS20() {
        return s20;
    }

    public void setP_cancelar(RichPopup p1) {
        this.p_cancelar = p1;
    }

    public RichPopup getP_cancelar() {
        return p_cancelar;
    }

    public void setD_cancelar(RichDialog d1) {
        this.d_cancelar = d1;
    }

    public RichDialog getD_cancelar() {
        return d_cancelar;
    }

    public void setOt6(RichOutputText ot6) {
        this.ot6 = ot6;
    }

    public RichOutputText getOt6() {
        return ot6;
    }


    public void setCb_si_cancelar(RichCommandButton cb1) {
        this.cb_si_cancelar = cb1;
    }

    public RichCommandButton getCb_si_cancelar() {
        return cb_si_cancelar;
    }

    public void setS21(RichSpacer s21) {
        this.s21 = s21;
    }

    public RichSpacer getS21() {
        return s21;
    }

    public void setCb_no_cancelar(RichCommandButton cb2) {
        this.cb_no_cancelar = cb2;
    }

    public RichCommandButton getCb_no_cancelar() {
        return cb_no_cancelar;
    }

    public void setS22(RichSpacer s22) {
        this.s22 = s22;
    }

    public RichSpacer getS22() {
        return s22;
    }


    public void setP_registro_exitoso(RichPopup p1) {
        this.p_registro_exitoso = p1;
    }

    public RichPopup getP_registro_exitoso() {
        return p_registro_exitoso;
    }

    public void setD_registro_exitoso(RichDialog d1) {
        this.d_registro_exitoso = d1;
    }

    public RichDialog getD_registro_exitoso() {
        return d_registro_exitoso;
    }

    public void setPgl14(RichPanelGroupLayout pgl14) {
        this.pgl14 = pgl14;
    }

    public RichPanelGroupLayout getPgl14() {
        return pgl14;
    }

    public void setCb_aceptar(RichCommandButton cb1) {
        this.cb_aceptar = cb1;
    }

    public RichCommandButton getCb_aceptar() {
        return cb_aceptar;
    }

    public void setPsl7(RichPanelStretchLayout psl7) {
        this.psl7 = psl7;
    }

    public RichPanelStretchLayout getPsl7() {
        return psl7;
    }


    public void setI1(RichImage i1) {
        this.i1 = i1;
    }

    public RichImage getI1() {
        return i1;
    }

    public void setOt8(RichOutputText ot8) {
        this.ot8 = ot8;
    }

    public RichOutputText getOt8() {
        return ot8;
    }

    public void cb_aceptar_actionListener(ActionEvent actionEvent) {
        this.setAceptarAction("adicionarPueaa");
    }

    //adicionar Proyecto

    public void cmi_adicionar_proyecto_actionListener(ActionEvent actionEvent) {
        FacesUtils.setInSession("proyectoSeleccionado", null);
        FacesUtils.removeManagedBeanFromSession("back_adicionarProyecto");
        accionAdicionar = "adicionarProyectoAddPueaa";
    }

    //adicionar Seguimiento Proyecto

    public void cmi_adicionar_seguimiento_actionListener(ActionEvent actionEvent) {
        //FacesUtils.setInSession("usuarioSeleccionado", this.usuarioAgua);
        //accionAdicionar = "adicionarProyectoPueaa";
        this.setAceptarAction("adicionarSeguimiento");
    }


    public Divipola getDepartamentoSeleccionado() {
        return departamentoSeleccionado;
    }

    public void setDepartamentoSeleccionado(Divipola departamentoSeleccionado) {
        this.departamentoSeleccionado = departamentoSeleccionado;
    }

    public Divipola getMunicipioSeleccionado() {
        return municipioSeleccionado;
    }

    public void setMunicipioSeleccionado(Divipola municipioSeleccionado) {
        this.municipioSeleccionado = municipioSeleccionado;
    }


    public void setPsl8(RichPanelStretchLayout psl8) {
        this.psl8 = psl8;
    }

    public RichPanelStretchLayout getPsl8() {
        return psl8;
    }

    public void setPgl15(RichPanelGroupLayout pgl15) {
        this.pgl15 = pgl15;
    }

    public RichPanelGroupLayout getPgl15() {
        return pgl15;
    }

    public void setPgl16(RichPanelGroupLayout pgl16) {
        this.pgl16 = pgl16;
    }

    public RichPanelGroupLayout getPgl16() {
        return pgl16;
    }


    public void setS23(RichSpacer s23) {
        this.s23 = s23;
    }

    public RichSpacer getS23() {
        return s23;
    }


    public void setS24(RichSpacer s24) {
        this.s24 = s24;
    }

    public RichSpacer getS24() {
        return s24;
    }

    public void setOt9(RichOutputText ot9) {
        this.ot9 = ot9;
    }

    public RichOutputText getOt9() {
        return ot9;
    }

    public void setS25(RichSpacer s25) {
        this.s25 = s25;
    }

    public RichSpacer getS25() {
        return s25;
    }

    public void setS26(RichSpacer s26) {
        this.s26 = s26;
    }

    public RichSpacer getS26() {
        return s26;
    }

    public void setPfl7(RichPanelFormLayout pfl7) {
        this.pfl7 = pfl7;
    }

    public RichPanelFormLayout getPfl7() {
        return pfl7;
    }


    public void setSi15(UISelectItems si15) {
        this.si15 = si15;
    }

    public UISelectItems getSi15() {
        return si15;
    }

    public void setIt_matricula(RichInputText it1) {
        this.it_matricula = it1;
    }

    public RichInputText getIt_matricula() {
        return it_matricula;
    }

    public void setP_ciiu(RichPopup p1) {
        this.p_ciiu = p1;
    }

    public RichPopup getP_ciiu() {
        return p_ciiu;
    }

    public void setD_ciiu(RichDialog d1) {
        this.d_ciiu = d1;
    }

    public RichDialog getD_ciiu() {
        return d_ciiu;
    }

    public void setPgl19(RichPanelGroupLayout pgl19) {
        this.pgl19 = pgl19;
    }

    public RichPanelGroupLayout getPgl19() {
        return pgl19;
    }

    public void setS29(RichSpacer s29) {
        this.s29 = s29;
    }

    public RichSpacer getS29() {
        return s29;
    }

    public void setCb_mostrar_ciiu(RichCommandButton cb2) {
        this.cb_mostrar_ciiu = cb2;
    }

    public RichCommandButton getCb_mostrar_ciiu() {
        return cb_mostrar_ciiu;
    }

    public void setIt_desc_ciiu(RichInputText it3) {
        this.it_desc_ciiu = it3;
    }

    public RichInputText getIt_desc_ciiu() {
        return it_desc_ciiu;
    }

    public void setS27(UINamingContainer s27) {
        this.s27 = s27;
    }

    public UINamingContainer getS27() {
        return s27;
    }


    public void setPsl9(RichPanelStretchLayout psl9) {
        this.psl9 = psl9;
    }

    public RichPanelStretchLayout getPsl9() {
        return psl9;
    }

    public void setPfl8(RichPanelFormLayout pfl8) {
        this.pfl8 = pfl8;
    }

    public RichPanelFormLayout getPfl8() {
        return pfl8;
    }

    public void setOl_invisible(RichOutputLabel ol1) {
        this.ol_invisible = ol1;
    }

    public RichOutputLabel getOl_invisible() {
        return ol_invisible;
    }


    public void setOl_mensaje(RichOutputLabel ol1) {
        this.ol_mensaje = ol1;
    }

    public RichOutputLabel getOl_mensaje() {
        return ol_mensaje;
    }

    public void setM2(RichMessage m2) {
        this.m2 = m2;
    }

    public RichMessage getM2() {
        return m2;
    }


    public void setM3(RichMessage m3) {
        this.m3 = m3;
    }

    public RichMessage getM3() {
        return m3;
    }


    public void setS28(RichSpacer s28) {
        this.s28 = s28;
    }

    public RichSpacer getS28() {
        return s28;
    }


    public void setPgl17(RichPanelGroupLayout pgl17) {
        this.pgl17 = pgl17;
    }

    public RichPanelGroupLayout getPgl17() {
        return pgl17;
    }

    public List getListaDeptosPredio() {
        return listaDeptosPredio;
    }

    public void setListaDeptosPredio(List listaDeptosPredio) {
        this.listaDeptosPredio = listaDeptosPredio;
    }

    public List getListaMunPredio() {
        return listaMunPredio;
    }

    public void setListaMunPredio(List listaMunPredio) {
        this.listaMunPredio = listaMunPredio;
    }
    //

    public void setSi16(UISelectItems si16) {
        this.si16 = si16;
    }

    public UISelectItems getSi16() {
        return si16;
    }

    public void setSoc_mun_tipo_suelo(RichSelectOneChoice soc1) {
        this.soc_mun_tipo_suelo = soc1;
    }

    public RichSelectOneChoice getSoc_mun_tipo_suelo() {
        return soc_mun_tipo_suelo;
    }

    public void setSi17(UISelectItems si17) {
        this.si17 = si17;
    }

    public UISelectItems getSi17() {
        return si17;
    }

    public void setPfl9(RichPanelFormLayout pfl9) {
        this.pfl9 = pfl9;
    }

    public RichPanelFormLayout getPfl9() {
        return pfl9;
    }

    public void setOl1(RichOutputLabel ol1) {
        this.ol1 = ol1;
    }

    public RichOutputLabel getOl1() {
        return ol1;
    }

    public void setOl2(RichOutputLabel ol2) {
        this.ol2 = ol2;
    }

    public RichOutputLabel getOl2() {
        return ol2;
    }

    public void setIt1(RichInputText it1) {
        this.it1 = it1;
    }

    public RichInputText getIt1() {
        return it1;
    }

    public void setIt2(RichInputText it2) {
        this.it2 = it2;
    }

    public RichInputText getIt2() {
        return it2;
    }

    public void setIt3(RichInputText it3) {
        this.it3 = it3;
    }

    public RichInputText getIt3() {
        return it3;
    }

    public void setS30(RichSpacer s30) {
        this.s30 = s30;
    }

    public RichSpacer getS30() {
        return s30;
    }

    public void setIt_observaciones(RichInputText it_observaciones) {
        this.it_observaciones = it_observaciones;
    }

    public RichInputText getIt_observaciones() {
        return it_observaciones;
    }

    public void setPgl13(RichPanelGroupLayout pgl13) {
        this.pgl13 = pgl13;
    }

    public RichPanelGroupLayout getPgl13() {
        return pgl13;
    }

    public void setUsuarioAguaTo(UsuarioAguaTO usuarioAguaTo) {
        this.usuarioAguaTo = usuarioAguaTo;
    }

    public UsuarioAguaTO getUsuarioAguaTo() {
        return usuarioAguaTo;
    }


    public String getAceptarAction() {
        return aceptarAction;
    }

    public void setAceptarAction(String aceptarAction) {
        this.aceptarAction = aceptarAction;
    }

    public void setSoc_tipo_centro(RichSelectOneChoice soc_tipo_centro) {
        this.soc_tipo_centro = soc_tipo_centro;
    }

    public RichSelectOneChoice getSoc_tipo_centro() {
        return soc_tipo_centro;
    }


    public void setSi5(UISelectItems si5) {
        this.si5 = si5;
    }

    public UISelectItems getSi5() {
        return si5;
    }

    public void setListaTipoCentroPoblado(List listaTipoCentroPoblado) {
        this.listaTipoCentroPoblado = listaTipoCentroPoblado;
    }

    public List getListaTipoCentroPoblado() {
        return listaTipoCentroPoblado;
    }


    public void setIt_area(RichInputText inputText1) {
        this.it_area = inputText1;
    }

    public RichInputText getIt_area() {
        return it_area;
    }

    public void setFecha_presenta(RichInputDate fecha_presenta) {
        this.fecha_presenta = fecha_presenta;
    }

    public RichInputDate getFecha_presenta() {
        return fecha_presenta;
    }

    public void setFecha_aprueba(RichInputDate fecha_aprueba) {
        this.fecha_aprueba = fecha_aprueba;
    }

    public RichInputDate getFecha_aprueba() {
        return fecha_aprueba;
    }

    public void setFecha_vigencia_ini(RichInputDate fecha_vigencia_ini) {
        this.fecha_vigencia_ini = fecha_vigencia_ini;
    }

    public RichInputDate getFecha_vigencia_ini() {
        return fecha_vigencia_ini;
    }

    public void setFecha_vigencia_fin(RichInputDate fecha_vigencia_fin) {
        this.fecha_vigencia_fin = fecha_vigencia_fin;
    }

    public RichInputDate getFecha_vigencia_fin() {
        return fecha_vigencia_fin;
    }

    public void setPueaa(PueatPuea pueaa) {
        this.pueaa = pueaa;
    }

    public PueatPuea getPueaa() {
        return pueaa;
    }

    public void setSoc_estado_pueaa(RichSelectOneChoice soc_estado_pueaa) {
        this.soc_estado_pueaa = soc_estado_pueaa;
    }

    public RichSelectOneChoice getSoc_estado_pueaa() {
        return soc_estado_pueaa;
    }

    public void setIt_numero_resolucion(RichInputText it_numero_resolucion) {
        this.it_numero_resolucion = it_numero_resolucion;
    }

    public RichInputText getIt_numero_resolucion() {
        return it_numero_resolucion;
    }

    public void setAccionAdicionar(String accionAdicionar) {
        this.accionAdicionar = accionAdicionar;
    }

    public String getAccionAdicionar() {
        return accionAdicionar;
    }

    public void setArchivo(RichMenu m_archivo) {
        this.m_archivo = m_archivo;
    }

    public RichMenu getArchivo() {
        return m_archivo;
    }


    public void setCmi_adicionar_proyecto_pueaa(RichCommandMenuItem cmi_adicionar_proyecto_pueaa) {
        this.cmi_adicionar_proyecto_pueaa = cmi_adicionar_proyecto_pueaa;
    }

    public RichCommandMenuItem getCmi_adicionar_proyecto_pueaa() {
        return cmi_adicionar_proyecto_pueaa;
    }

    public void setCmi_adicionar_seguimiento(RichCommandMenuItem cmi_adicionar_seguimiento) {
        this.cmi_adicionar_seguimiento = cmi_adicionar_seguimiento;
    }

    public RichCommandMenuItem getCmi_adicionar_seguimiento() {
        return cmi_adicionar_seguimiento;
    }


    public void setLstPueaa(List<PueatPuea> lstPueaa) {
        this.lstPueaa = lstPueaa;
    }

    public List<PueatPuea> getLstPueaa() {
        return lstPueaa;
    }

    public void setT1(RichTable t1) {
        this.t1 = t1;
    }

    public RichTable getT1() {
        return t1;
    }


    public void setPueaaSelected(PueatPuea pueaaSelected) {
        this.pueaaSelected = pueaaSelected;
    }

    public PueatPuea getPueaaSelected() {
        return pueaaSelected;
    }

    public void setTreepry(RichTree treepry) {
        this.treepry = treepry;
    }

    public RichTree getTreepry() {
        return treepry;
    }

    public void setPueaaPryTreeModel(TreeModel pueaaPryTreeModel) {
        this.pueaaPryTreeModel = pueaaPryTreeModel;
    }

    public TreeModel getPueaaPryTreeModel() {
        return pueaaPryTreeModel;
    }

    public void setClPueaaPry(RichCommandLink clPueaaPry) {
        this.clPueaaPry = clPueaaPry;
    }

    public RichCommandLink getClPueaaPry() {
        return clPueaaPry;
    }

    public void setPueaaLoad(boolean pueaaLoad) {
        this.pueaaLoad = pueaaLoad;
    }

    public boolean isPueaaLoad() {
        return pueaaLoad;
    }

    public void setEstadoSelected(Integer estadoSelected) {
        this.estadoSelected = estadoSelected;
    }

    public Integer getEstadoSelected() {
        return estadoSelected;
    }
}
