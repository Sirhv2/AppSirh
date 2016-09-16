package co.gov.ideam.sirh.pueaa.view;


import co.gov.ideam.sirh.parametros.model.Autoridades;

import co.gov.ideam.sirh.parametros.model.Lista;
import co.gov.ideam.sirh.parametros.view.ParametrosDelegate;
import co.gov.ideam.sirh.pueaa.PueaaDelegate;
import co.gov.ideam.sirh.pueaa.model.PueatProyectoConcesiones;
import co.gov.ideam.sirh.pueaa.model.PueatProyectos;
import co.gov.ideam.sirh.pueaa.model.PueatPuea;
import co.gov.ideam.sirh.seguridad.model.UsuarioConectadoTO;
import co.gov.ideam.sirh.usuarios.agua.model.Concesion;
import co.gov.ideam.sirh.usuarios.agua.model.Predio;
import co.gov.ideam.sirh.usuarios.agua.model.UsuarioAgua;
import co.gov.ideam.sirh.usuarios.agua.model.UsuarioAguaTO;
import co.gov.ideam.sirh.usuarios.agua.view.UsuariosAguaDelegate;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.view.FacesUtils;
import co.gov.ideam.sirh.view.StandarBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import oracle.adf.view.rich.component.rich.RichDialog;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectManyChoice;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.component.rich.nav.RichCommandMenuItem;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.context.AdfFacesContext;


public class AdicionarProyecto extends StandarBean {

    private RichPanelGroupLayout pgl14;
    private RichCommandButton cb_aceptar;
    private String aceptarAction;
    private RichPanelStretchLayout psl7;
    private RichOutputText ot8;
    
    private RichForm f1;
    private RichDocument d1;
    private RichPanelGroupLayout pgl1;
    private RichPanelFormLayout pfl1;
    private RichSelectOneChoice socProyecto;
    //private RichInputDate idFechaInicio;
    //private RichInputDate idFechaFin;
    private RichInputText itDescripcion;
    private RichInputText itNombre;
    private RichInputText itPresupuesto;
    private RichInputText itLineaBase;
    private RichInputText itIndicador;
    private RichSelectOneChoice smcPredios;
    private RichSelectOneChoice smcConcesiones;
    private RichInputText itAnno1;
    private RichInputText itAnno2;
    private RichInputText itAnno3;
    private RichInputText itAnno4;
    private RichInputText itAnno5;
    private RichInputDate idFecha1;
    private RichInputDate idFecha2;
    private RichInputDate idFecha3;
    private RichInputDate idFecha4;
    private RichInputDate idFecha5;
    private RichCommandButton cbGuardar;
    private RichCommandButton cbCancelar;

    private RichCommandButton cbAgregarProyecto;
    private RichCommandMenuItem cmi_adicionar_seguimiento;
    private RichTable tblPred;
    private RichCommandButton cbModificarPred;
    private RichCommandButton cbEliminarPred;
    private RichPopup p_registro_exitoso;
    private RichDialog d_registro_exitoso;
    private UISelectItems sipry;
    private UISelectItems siprd;
    private UISelectItems siccs;
    PueaaDelegate pueaD;
    UsuariosAguaDelegate uad;
    UsuarioAgua usuarioAgua;
    UsuarioAguaTO usuarioAguaTO;
    private PueatProyectos pueaaPry;
    private PueatProyectos pueaaPryCreated;
    private Integer currentSelectedIndex;
    //Listas
    private List listaProyecto;
    private List<Predio> listaPredios;
    List<SelectItem> lstSelectPrd;
    private List<Concesion> listaConcesiones;
    List<SelectItem> lstSelectCcs;
    private List<Concesion> listaCcsAdd;
    private PueatProyectos proyectoSelected;
    private Integer nomPrySelected;

    public AdicionarProyecto() {
        //   FacesUtils.setActiveBean("adicionarProyecto", "adicionarProyecto",
        //   UsuariosAguaDelegate.class);
        //FacesUtils.setActiveBean("AdicionarProyecto", "AdicionarProyecto",
        //                         AdicionarProyecto.class);
        this.load();
    }

    public void load() {
        //System.out.println("HCP entro a load de AdicionarProyecto ");

        Object obj = FacesUtils.getFromSession("usuarioSeleccionado");
        listaCcsAdd = new ArrayList();
        try {

            UsuariosAguaDelegate uad = UsuariosAguaDelegate.getInstance();
            if (obj instanceof UsuarioAgua) {
                this.usuarioAgua = (UsuarioAgua)obj;
                Long codigoUSU = this.usuarioAgua.getCodigo();
                this.usuarioAgua = uad.getUsuarioAgua(codigoUSU);
            } else if (obj instanceof UsuarioAguaTO) {
                this.usuarioAguaTO = (UsuarioAguaTO)obj;
                Long codigoUSU = this.usuarioAguaTO.getCodigo();
                this.usuarioAgua = uad.getUsuarioAgua(codigoUSU);
            }


            ParametrosDelegate pd = ParametrosDelegate.getInstance();
            pueaD = PueaaDelegate.getInstance();
            proyectoSelected = new PueatProyectos();

            //Lista de nombre proyectos
            List<SelectItem> lstSelect = new ArrayList<SelectItem>();
            List<Lista> lista = new ArrayList(); //carga el selectItem
            lista = pd.getAllListaByCategoria(105L);
            for (Lista list : lista) {
                SelectItem si =
                    new SelectItem(list.getCodigo(), list.getValor());
                lstSelect.add(si);
            }
            listaProyecto = lstSelect;

            //Lista Predios

            lstSelectPrd = new ArrayList<SelectItem>();
            listaPredios = new ArrayList();

            listaPredios = uad.getPredios(usuarioAgua);
            for (Predio objp : listaPredios) {
                SelectItem si =
                    new SelectItem((Integer)objp.getCodigo().intValue(),
                                   objp.getNombre());
                lstSelectPrd.add(si);
            }

            lstSelectCcs = new ArrayList<SelectItem>();
            listaConcesiones = new ArrayList();

            //Proyecto Seleccionado
            Object obj2 = FacesUtils.getFromSession("proyectoSeleccionado");

            if (obj2 instanceof PueatProyectos) {
                proyectoSelected = (PueatProyectos)obj2;

                nomPrySelected =
                        Integer.valueOf((proyectoSelected.getIdListas()).intValue());
                //llena la lista de concesiones que tiene el proyecto para mostrar en la tabala de la presentacion
                List<PueatProyectoConcesiones> lstPryConcesion =
                    pueaD.getPueatProyectoConcesionesFindAll(proyectoSelected);
                for (PueatProyectoConcesiones pueatProyectoConcesiones :
                     lstPryConcesion) {
                    Concesion concesion = new Concesion();
                    concesion =
                            uad.getConcesion(pueatProyectoConcesiones.getRurtConcesionesId());
                    listaCcsAdd.add(concesion);
                }
            }

        } catch (IdeamException e) {
            e.printStackTrace();

        }

    }


    /**
     * Metodo smc_predios_valueChangeListener
     * @param actionEvent
     */
    public void smc_predios_valueChangeListener(ValueChangeEvent event) {

        //System.out.println("HCP entro smc_predios_valueChangeListener ");

        FacesContext.getCurrentInstance().renderResponse();
        Object objEvent = event.getNewValue();

        try {
            uad = UsuariosAguaDelegate.getInstance();

            lstSelectCcs = new ArrayList<SelectItem>();
            listaConcesiones = new ArrayList();

            Predio predio = new Predio();

            predio.setCodigo(new Long((Integer)objEvent));

            listaConcesiones = uad.getConcesiones(predio);
            //listaConcesiones = uad.getConcesiones(((Predio)objEvent));
            for (Concesion obj : listaConcesiones) {
                SelectItem si =
                    new SelectItem(obj.getCodigo(), obj.getNumeroExpediente());
               // System.out.println("HCP Agrego concesion " + obj.getCodigo());
                lstSelectCcs.add(si);
            }

            AdfFacesContext.getCurrentInstance().addPartialTarget(this.smcConcesiones);

        } catch (IdeamException e) {

            showMessage("Error " + e.getMessage());
        }
    }

    public void smc_concesiones_valueChangeListener(ValueChangeEvent event) {

        //System.out.println("HCP entro smc_concesiones_valueChangeListener ");

        FacesContext.getCurrentInstance().renderResponse();
        Object objEvent = event.getNewValue();

        try {

            Long concesion = (Long)objEvent;

           // System.out.println("HCP Selecciono concesion " + concesion);

        } catch (Exception e) {
            showMessage("Error " + e.getMessage());
        }
    }

    /**
     * Metodo Argrega los predios y concesiones seleccionados a la tabla y lista para posterio almacenamiento
     * @param actionEvent
     */
    public void cb_agregar_a_listactionListener(ActionEvent actionEvent) {
        /*FacesContext.getCurrentInstance().renderResponse();
        List<Integer> lstCodConcesion = new ArrayList();
        lstCodConcesion = (List<Integer>)smcConcesiones.getValue();
        try {
            UsuariosAguaDelegate uad = UsuariosAguaDelegate.getInstance();

            for (Integer objInt : lstCodConcesion) {
                Concesion concesion = new Concesion();
                concesion = uad.getConcesion(objInt.longValue());
                listaCcsAdd.add(concesion);
            }
        } catch (IdeamException e) {
            e.printStackTrace();
        }*/
        try {
            //System.out.println("HCP entro cb_agregar_a_listactionListener ");

            Integer predio = (Integer)smcPredios.getValue();
            Long concesion = (Long)smcConcesiones.getValue();

            if (predio == null)
                throw new IdeamException("Seleccione predio");

            if (concesion == null)
                throw new IdeamException("Seleccione concesión");

            //System.out.println("HCP predio " + predio);
            //System.out.println("HCP concesion " + concesion);

            boolean esta = false;
            for (Concesion c : listaCcsAdd) {
               // System.out.println("HCP esta Concesion " + c.getCodigo());
                if (c.getCodigo().toString().equals(concesion.toString())) {
                    esta = true;
                    //System.out.println("HCP SI ESTA ");
                }

            }

            if (!esta) {
                Concesion conc = new Concesion();
                conc = uad.getConcesion(concesion);
                listaCcsAdd.add(conc);
            } else
                showMessage("Ya esta registrada concesion");

            AdfFacesContext.getCurrentInstance().addPartialTarget(this.tblPred);

        } catch (IdeamException e) {
            showMessage("Error " + e.getMessage());
        }
    }

    public void cb_guardar_pry_actionListener(ActionEvent actionEvent) {
        boolean continuar = validarPueaa();
        if (continuar) {
            if (this.proyectoSelected.getId() != null)
                actualizar();
            else
                guardar();
        }
        // system.out.println("Testin Guardar PRY");
    }

    public void cb_cancelar_actionListener(ActionEvent actionEvent) {
        FacesUtils.removeManagedBeanFromSession("back_adicionarProyecto");
        FacesUtils.removeManagedBeanFromSession("adicionarPueaa");
        FacesUtils.removeManagedBeanFromSession("detalleUsuarioAgua");
    }

    //Guardar y validar proyecto

    public boolean validarPueaa() {
        boolean continuar = true;

        /*if (this.idFechaInicio == null ||
            this.idFechaInicio.toString().length() == 0) {
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                        idFechaInicio);
            continuar = false;
        }*/


        if (this.socProyecto == null || this.socProyecto.getValue() == null) {
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                        socProyecto);
            continuar = false;
        }

        if (this.itNombre == null ||
            this.itNombre.toString().length() == 0) {
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                        itNombre);
            continuar = false;
        }

        if (itNombre != null && itNombre.getValue() != null &&
            itNombre.getValue().toString().length() > 30) {
            showMessage("longitud_errada_30", FacesMessage.SEVERITY_ERROR,
                        itNombre);
            continuar = false;
        }

        if (this.itDescripcion == null ||
            this.itDescripcion.toString().length() == 0) {
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                        itDescripcion);
            continuar = false;
        }

        if (itDescripcion != null && itDescripcion.getValue() != null &&
            itDescripcion.getValue().toString().length() > 200) {
            showMessage("longitud_errada", FacesMessage.SEVERITY_ERROR,
                        itDescripcion);
            continuar = false;
        }


        if (this.itPresupuesto == null ||
            this.itPresupuesto.getValue() == null ||
            this.itPresupuesto.getValue().toString().length() == 0) {
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                        itPresupuesto);
            continuar = false;
        }

        System.out.println("HCP 2**** " + itPresupuesto.getValue());

        if (this.itLineaBase == null || this.itLineaBase.getValue() == null ||
            this.itLineaBase.getValue().toString().length() == 0) {
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                        itLineaBase);
            continuar = false;
        }

        //System.out.println("HCP 3**** " + itLineaBase.getValue());

        if (this.itIndicador == null || this.itIndicador.getValue() == null ||
            this.itIndicador.getValue().toString().length() == 0) {
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                        itIndicador);
            continuar = false;
        }

        if (this.itAnno1.getValue() == null ||
            this.itAnno1.getValue().toString().length() == 0) {
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR, itAnno1);
            continuar = false;
        }
        if (this.idFecha1 == null || this.idFecha1.toString().length() == 0) {
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR, idFecha1);
            continuar = false;
        }
        if (this.itAnno2.getValue() == null ||
            this.itAnno2.getValue().toString().length() == 0) {
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR, itAnno2);
            continuar = false;
        }
        if (this.idFecha2 == null || this.idFecha2.toString().length() == 0) {
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR, idFecha2);
            continuar = false;
        }
        if (this.itAnno3.getValue() == null ||
            this.itAnno3.getValue().toString().length() == 0) {
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR, itAnno3);
            continuar = false;
        }
        if (this.idFecha3 == null || this.idFecha3.toString().length() == 0) {
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR, idFecha3);
            continuar = false;
        }
        if (this.itAnno4.getValue() == null ||
            this.itAnno4.getValue().toString().length() == 0) {
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR, itAnno4);
            continuar = false;
        }
        if (this.idFecha4 == null || this.idFecha4.toString().length() == 0) {
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR, idFecha4);
            continuar = false;
        }
        if (this.itAnno5.getValue() == null ||
            this.itAnno5.getValue().toString().length() == 0) {
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR, itAnno5);
            continuar = false;
        }
        if (this.idFecha5 == null || this.idFecha5.toString().length() == 0) {
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR, idFecha5);
            continuar = false;
        }

        /*if (this.idFechaFin == null ||
            this.idFechaFin.toString().length() == 0) {
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                        idFechaFin);
            continuar = false;
        }*/

        Date fec1 = (Date)this.idFecha1.getValue();
        Date fec2 = (Date)this.idFecha2.getValue();
        Date fec3 = (Date)this.idFecha3.getValue();
        Date fec4 = (Date)this.idFecha4.getValue();
        Date fec5 = (Date)this.idFecha5.getValue();

        if (fec2 != null && fec1 != null)
            if (fec2.before(fec1)) {
                showMessage("fecha_errada_ant", FacesMessage.SEVERITY_ERROR,
                            idFecha2);
                continuar = false;

            }

        if (fec3 != null && fec2 != null && fec1 != null)
            if (fec3.before(fec2) || fec3.before(fec1)) {
                showMessage("fecha_errada_ant", FacesMessage.SEVERITY_ERROR,
                            idFecha3);
                continuar = false;

            }

        if (fec4 != null && fec3 != null && fec2 != null && fec1 != null)
            if (fec4.before(fec3) || fec4.before(fec2) || fec4.before(fec1)) {
                showMessage("fecha_errada_ant", FacesMessage.SEVERITY_ERROR,
                            idFecha4);
                continuar = false;

            }

        if (fec5 != null && fec4 != null && fec3 != null && fec2 != null &&
            fec1 != null)
            if (fec5.before(fec4) || fec5.before(fec3) || fec5.before(fec2) ||
                fec5.before(fec1)) {
                showMessage("fecha_errada_ant", FacesMessage.SEVERITY_ERROR,
                            idFecha5);
                continuar = false;

            }

        return continuar;
    }

    public void guardar() {
        pueaaPry = new PueatProyectos();
        try {


            //System.out.println("GUARDAR");
            //Usuario Autenticacion y el usuario del agua seleccionado
            UsuarioConectadoTO usuarioConectado =
                (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
            Autoridades autoridadAmbiental =
                usuarioConectado.getUsuario().getAutoridadAmbiental();
            UsuariosAguaDelegate uad = UsuariosAguaDelegate.getInstance();
            Date fechaActual = new Date();

            PueatPuea pueaa =
                (PueatPuea)FacesUtils.getFromSession("pueaaSeleccionado");
            //pueaa usuarioConectado.getUsuario().getAutoridadAmbiental().getId().longValue()
            //pueaaPry.setId(2L);

            /*List<PueatProyectos> listaProy =
                pueaD.getPueatProyectosFindByPueaa(pueaa);

            for (PueatProyectos proyPuea : listaProy) {
                if (proyPuea.getDescripcion().toUpperCase().equals(itDescripcion.getValue().toString().toUpperCase()))
                    throw new IdeamException("PUEA ya registrado");
            }*/
            
            pueaaPry.setPueatPuea(pueaa);
            pueaaPry.setCodigoAutoridad(autoridadAmbiental.getId().longValue());
            //pueaaPry.setFechaInicio(new java.sql.Timestamp(((Date)this.idFechaInicio.getValue()).getTime()));
            pueaaPry.setIdListas(((Integer)socProyecto.getValue()).longValue()); //nombre del proyecto
            pueaaPry.setDescripcion((String)itDescripcion.getValue());
            pueaaPry.setNombreProyecto((String)itNombre.getValue()); //campo que se visualiza en el Tree de Navegación
            //pueaaPry.setPresupuesto(Float.parseFloat((String)itPresupuesto.getValue()));
            pueaaPry.setPresupuesto((Long)itPresupuesto.getValue());
            pueaaPry.setLineaBase(Long.parseLong(itLineaBase.getValue().toString()));
            pueaaPry.setIndicador((String)itIndicador.getValue());
            //pueaaPry.setMetaConsumoAno1(Float.parseFloat((String)itAnno1.getValue()));
            pueaaPry.setMetaConsumoAno1((Double)itAnno1.getValue());
            pueaaPry.setMetaFechaAno1(new java.sql.Timestamp(((Date)this.idFecha1.getValue()).getTime()));
            //pueaaPry.setMetaConsumoAno2(Float.parseFloat((String)itAnno2.getValue()));
            pueaaPry.setMetaConsumoAno2((Double)itAnno2.getValue());
            pueaaPry.setMetaFechaAno2(new java.sql.Timestamp(((Date)this.idFecha2.getValue()).getTime()));
            //pueaaPry.setMetaConsumoAno3(Float.parseFloat((String)itAnno3.getValue()));
            pueaaPry.setMetaConsumoAno3((Double)itAnno3.getValue());
            pueaaPry.setMetaFechaAno3(new java.sql.Timestamp(((Date)this.idFecha3.getValue()).getTime()));
            //pueaaPry.setMetaConsumoAno4(Float.parseFloat((String)itAnno4.getValue()));
            pueaaPry.setMetaConsumoAno4((Double)itAnno4.getValue());
            pueaaPry.setMetaFechaAno4(new java.sql.Timestamp(((Date)this.idFecha4.getValue()).getTime()));
            //pueaaPry.setMetaConsumoAno5(Float.parseFloat((String)itAnno5.getValue()));
            pueaaPry.setMetaConsumoAno5((Double)itAnno5.getValue());
            pueaaPry.setMetaFechaAno5(new java.sql.Timestamp(((Date)this.idFecha5.getValue()).getTime()));
            //pueaaPry.setFechaFin(new java.sql.Timestamp(((Date)this.idFechaFin.getValue()).getTime()));
            //Valores seguimientos por defecto
            /* pueaaPry.setMetaConsumoAno1(0F);
            pueaaPry.setMetaConsumoAno2(0F);
            pueaaPry.setMetaConsumoAno3(0F);
            pueaaPry.setMetaConsumoAno4(0F);
            pueaaPry.setMetaConsumoAno5(0F);

            pueaaPry.setMetaSeguimientoFechaAno1(new java.sql.Timestamp(((Date)fechaActual).getTime()));
            pueaaPry.setMetaSeguimientoFechaAno2(new java.sql.Timestamp(((Date)fechaActual).getTime()));
            pueaaPry.setMetaSeguimientoFechaAno3(new java.sql.Timestamp(((Date)fechaActual).getTime()));
            pueaaPry.setMetaSeguimientoFechaAno4(new java.sql.Timestamp(((Date)fechaActual).getTime()));
            pueaaPry.setMetaSeguimientoFechaAno5(new java.sql.Timestamp(((Date)fechaActual).getTime()));*/
            
            
            //Registra Proyecto
            pueaaPryCreated = pueaD.registrarPueaaPry(pueaaPry);
            
            
            //Resgistrar concesiones del Proyecto
            guardarConcesiones(pueaaPryCreated);

            String params[] = { "del PROYECTO PUEAA" };
            showMessage(getText("mensaje_registro_exitoso", params),
                        FacesMessage.SEVERITY_INFO);

            proyectoSelected = pueaaPryCreated;
            
            //showPopup(p_registro_exitoso, true);

        } catch (IdeamException e) {
            showMessage(e);
        }
    }


    public void actualizar() {
        pueaaPry = new PueatProyectos();
        try {
            System.out.println("ACTUALIZAR");
            //Usuario Autenticacion y el usuario del agua seleccionado
            UsuarioConectadoTO usuarioConectado =
                (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
            Autoridades autoridadAmbiental =
                usuarioConectado.getUsuario().getAutoridadAmbiental();
            UsuariosAguaDelegate uad = UsuariosAguaDelegate.getInstance();


            PueatPuea pueaa =
                (PueatPuea)FacesUtils.getFromSession("pueaaSeleccionado");
            //pueaa usuarioConectado.getUsuario().getAutoridadAmbiental().getId().longValue()
            //pueaaPry.setId(2L);
            pueaaPry.setPueatPuea(pueaa);
            pueaaPry.setCodigoAutoridad(autoridadAmbiental.getId().longValue());
            pueaaPry.setId(proyectoSelected.getId());
            //pueaaPry.setFechaInicio(new java.sql.Timestamp(((Date)this.idFechaInicio.getValue()).getTime()));
            pueaaPry.setIdListas(((Integer)socProyecto.getValue()).longValue()); //nombre del proyecto
            pueaaPry.setDescripcion((String)itDescripcion.getValue());
            pueaaPry.setNombreProyecto((String)itNombre.getValue()); //campo que se visualiza en el Tree de Navegación
            //pueaaPry.setPresupuesto(Float.parseFloat((String)itPresupuesto.getValue()));
            pueaaPry.setPresupuesto((Long)itPresupuesto.getValue());
            pueaaPry.setLineaBase(Long.parseLong(itLineaBase.getValue().toString()));
            pueaaPry.setIndicador((String)itIndicador.getValue());

            pueaaPry.setMetaConsumoAno1((Double)itAnno1.getValue());
            pueaaPry.setMetaFechaAno1(new java.sql.Timestamp(((Date)this.idFecha1.getValue()).getTime()));

            pueaaPry.setMetaConsumoAno2((Double)itAnno2.getValue());
            pueaaPry.setMetaFechaAno2(new java.sql.Timestamp(((Date)this.idFecha2.getValue()).getTime()));

            pueaaPry.setMetaConsumoAno3((Double)itAnno3.getValue());
            pueaaPry.setMetaFechaAno3(new java.sql.Timestamp(((Date)this.idFecha3.getValue()).getTime()));

            pueaaPry.setMetaConsumoAno4((Double)itAnno4.getValue());
            pueaaPry.setMetaFechaAno4(new java.sql.Timestamp(((Date)this.idFecha4.getValue()).getTime()));

            pueaaPry.setMetaConsumoAno5((Double)itAnno5.getValue());
            pueaaPry.setMetaFechaAno5(new java.sql.Timestamp(((Date)this.idFecha5.getValue()).getTime()));
            //pueaaPry.setFechaFin(new java.sql.Timestamp(((Date)this.idFechaFin.getValue()).getTime()));
            pueaaPryCreated = pueaD.mergePueaPry(pueaaPry);
            //Borra las  concesiones que estan en bd y registra las cobncesiones actuales(Actualiza Concesiones)
            borrarConcesiones(pueaaPryCreated);
            guardarConcesiones(pueaaPryCreated);

            String params[] = { "del PROYECTO PUEAA" };
            showMessage(getText("mensaje_registro_exitoso", params),
                        FacesMessage.SEVERITY_INFO);


            //showPopup(p_registro_exitoso, true);
            // AdfFacesContext.getCurrentInstance().addPartialTarget(t1);

        } catch (IdeamException e) {
            showMessage(e);
        }
    }

    public void cmi_adicionar_seguimiento_actionListener(ActionEvent actionEvent) {
        try {

            if (pueaaPryCreated == null) {
                //System.out.println("HCP proyecto ya existente " + proyectoSelected.getDescripcion() );
                FacesUtils.setInSession("proyectoSeleccionado",
                                        proyectoSelected);
            }
            else {
                //System.out.println("HCP proyecto creado " + pueaaPryCreated.getDescripcion() );
                
                FacesUtils.setInSession("proyectoSeleccionado",
                                        pueaaPryCreated);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Metodo Guarda las concesiones para un proyecto
     * @param pueaaPryCreated
     * @throws IdeamException
     */
    public void guardarConcesiones(PueatProyectos pueaaPryCreated) throws IdeamException {
        //Usuario Autenticacion y el usuario del agua seleccionado
        UsuarioConectadoTO usuarioConectado =
            (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
        Autoridades autoridadAmbiental =
            usuarioConectado.getUsuario().getAutoridadAmbiental();
        PueatProyectoConcesiones pryConcesion;
        for (Concesion concesion : listaCcsAdd) {
            pryConcesion = new PueatProyectoConcesiones();
            pryConcesion.setCodigoAutoridad(autoridadAmbiental.getId().longValue());
            pryConcesion.setRurtPrediosId(concesion.getCodigoPredio());
            pryConcesion.setRurtConcesionesId(concesion.getCodigo());
            pryConcesion.setPueatProyectos(pueaaPryCreated);
            pueaD.registrarPryConsecion(pryConcesion);
        }
    }

    /**
     * Metodo Borrar las concesion de un proyecto
     * @param pueaaPryCreated
     * @throws IdeamException
     */
    public void borrarConcesiones(PueatProyectos pueaaPryCreated) throws IdeamException {
        List<PueatProyectoConcesiones> listaCcsDel =
            new ArrayList<PueatProyectoConcesiones>();
        listaCcsDel =
                pueaD.getPueatProyectoConcesionesFindAll(pueaaPryCreated);
        for (PueatProyectoConcesiones pueatProyectoConcesiones : listaCcsDel) {
            pueaD.removePueatProyectoConcesiones(pueatProyectoConcesiones);
        }
    }

    /**
     * Metodo que selecciona un elemento de la tabla para eliminarlo para su posterior uso
     * @param selectionEvent
     */
    //public void table_del_selectionListener(SelectionEvent selectionEvent) {
    public void table_del_selectionListener(ActionEvent actionEvent) {
        Integer currentIndex = getCurrentSelectedIndex();
        System.out.println("Removing with index : >> " + currentIndex);
        System.out.println("Removing with size : >> " + listaCcsAdd.size());
        listaCcsAdd.remove(currentIndex.intValue());
        System.out.println("size after removing : >> " + listaCcsAdd.size());
        UIComponent ui = (UIComponent)actionEvent.getSource();
        AdfFacesContext.getCurrentInstance().addPartialTarget(ui.getParent().getParent());


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

    public void setPgl1(RichPanelGroupLayout pgl1) {
        this.pgl1 = pgl1;
    }

    public RichPanelGroupLayout getPgl1() {
        return pgl1;
    }

    public void setPfl1(RichPanelFormLayout pfl1) {
        this.pfl1 = pfl1;
    }

    public RichPanelFormLayout getPfl1() {
        return pfl1;
    }

    public void setSocProyecto(RichSelectOneChoice socProyecto) {
        this.socProyecto = socProyecto;
    }

    public RichSelectOneChoice getSocProyecto() {
        return socProyecto;
    }

    public void setItDescripcion(RichInputText itDescripcion) {
        this.itDescripcion = itDescripcion;
    }

    public RichInputText getItDescripcion() {
        return itDescripcion;
    }

    public void setItLineaBase(RichInputText itLineaBase) {
        this.itLineaBase = itLineaBase;
    }

    public RichInputText getItLineaBase() {
        return itLineaBase;
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

    public void setListaProyecto(List listaProyecto) {
        this.listaProyecto = listaProyecto;
    }

    public List getListaProyecto() {
        return listaProyecto;
    }


    public void setSipry(UISelectItems sipry) {
        this.sipry = sipry;
    }

    public UISelectItems getSipry() {
        return sipry;
    }

    public void setItPresupuesto(RichInputText itPresupuesto) {
        this.itPresupuesto = itPresupuesto;
    }

    public RichInputText getItPresupuesto() {
        return itPresupuesto;
    }

    public void setItIndicador(RichInputText itIndicador) {
        this.itIndicador = itIndicador;
    }

    public RichInputText getItIndicador() {
        return itIndicador;
    }

    /*public void setIdFechaInicio(RichInputDate idFechaInicio) {
        this.idFechaInicio = idFechaInicio;
    }

    public RichInputDate getIdFechaInicio() {
        return idFechaInicio;
    }

    public void setIdFechaFin(RichInputDate idFechaFin) {
        this.idFechaFin = idFechaFin;
    }

    public RichInputDate getIdFechaFin() {
        return idFechaFin;
    }*/

    public void setCmi_adicionar_seguimiento(RichCommandMenuItem cmi_adicionar_seguimiento) {
        this.cmi_adicionar_seguimiento = cmi_adicionar_seguimiento;
    }

    public RichCommandMenuItem getCmi_adicionar_seguimiento() {
        return cmi_adicionar_seguimiento;
    }


    public void setLstSelectPrd(List<SelectItem> lstSelectPrd) {
        this.lstSelectPrd = lstSelectPrd;
    }

    public List<SelectItem> getLstSelectPrd() {
        return lstSelectPrd;
    }


    public void setSmcPredios(RichSelectOneChoice smcPredios) {
        this.smcPredios = smcPredios;
    }

    public RichSelectOneChoice getSmcPredios() {
        return smcPredios;
    }

    public void setSiprd(UISelectItems siprd) {
        this.siprd = siprd;
    }

    public UISelectItems getSiprd() {
        return siprd;
    }


    public void setLstSelectCcs(List<SelectItem> lstSelectCcs) {
        this.lstSelectCcs = lstSelectCcs;
    }

    public List<SelectItem> getLstSelectCcs() {
        return lstSelectCcs;
    }

    public void setSiccs(UISelectItems siccs) {
        this.siccs = siccs;
    }

    public UISelectItems getSiccs() {
        return siccs;
    }

    public void setListaCcsAdd(List<Concesion> listaCcsAdd) {
        this.listaCcsAdd = listaCcsAdd;
    }

    public List<Concesion> getListaCcsAdd() {
        return listaCcsAdd;
    }

    public void setCurrentSelectedIndex(Integer currentSelectedIndex) {
        this.currentSelectedIndex = currentSelectedIndex;
    }

    public Integer getCurrentSelectedIndex() {
        return currentSelectedIndex;
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

    public void setItAnno1(RichInputText itAnno1) {
        this.itAnno1 = itAnno1;
    }

    public RichInputText getItAnno1() {
        return itAnno1;
    }

    public void setItAnno2(RichInputText itAnno2) {
        this.itAnno2 = itAnno2;
    }

    public RichInputText getItAnno2() {
        return itAnno2;
    }

    public void setItAnno3(RichInputText itAnno3) {
        this.itAnno3 = itAnno3;
    }

    public RichInputText getItAnno3() {
        return itAnno3;
    }

    public void setItAnno4(RichInputText itAnno4) {
        this.itAnno4 = itAnno4;
    }

    public RichInputText getItAnno4() {
        return itAnno4;
    }

    public void setIdFecha1(RichInputDate idFecha1) {
        this.idFecha1 = idFecha1;
    }

    public RichInputDate getIdFecha1() {
        return idFecha1;
    }

    public void setIdFecha2(RichInputDate idFecha2) {
        this.idFecha2 = idFecha2;
    }

    public RichInputDate getIdFecha2() {
        return idFecha2;
    }

    public void setIdFecha3(RichInputDate idFecha3) {
        this.idFecha3 = idFecha3;
    }

    public RichInputDate getIdFecha3() {
        return idFecha3;
    }

    public void setIdFecha4(RichInputDate idFecha4) {
        this.idFecha4 = idFecha4;
    }

    public RichInputDate getIdFecha4() {
        return idFecha4;
    }

    public void setItAnno5(RichInputText itAnno5) {
        this.itAnno5 = itAnno5;
    }

    public RichInputText getItAnno5() {
        return itAnno5;
    }

    public void setIdFecha5(RichInputDate idFecha5) {
        this.idFecha5 = idFecha5;
    }

    public RichInputDate getIdFecha5() {
        return idFecha5;
    }

    public void setProyectoSelected(PueatProyectos proyectoSelected) {
        this.proyectoSelected = proyectoSelected;
    }

    public PueatProyectos getProyectoSelected() {
        return proyectoSelected;
    }

    public void setNomPrySelected(Integer nomPrySelected) {
        this.nomPrySelected = nomPrySelected;
    }

    public Integer getNomPrySelected() {
        return nomPrySelected;
    }

    public void setCbCancelar(RichCommandButton cbCancelar) {
        this.cbCancelar = cbCancelar;
    }

    public RichCommandButton getCbCancelar() {
        return cbCancelar;
    }

    public void setSmcConcesiones(RichSelectOneChoice smcConcesiones) {
        this.smcConcesiones = smcConcesiones;
    }

    public RichSelectOneChoice getSmcConcesiones() {
        return smcConcesiones;
    }

    public void setItNombre(RichInputText itNombre) {
        this.itNombre = itNombre;
    }

    public RichInputText getItNombre() {
        return itNombre;
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
    public void cb_aceptar_actionListener(ActionEvent actionEvent) {
        
    }

}
