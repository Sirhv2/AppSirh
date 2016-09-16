package co.gov.ideam.sirh.calidad.view;

import co.gov.ideam.sirh.auditoria.model.Auditoria;
import co.gov.ideam.sirh.auditoria.view.AuditoriasDelegate;
import co.gov.ideam.sirh.calidad.model.Muestra;
import co.gov.ideam.sirh.calidad.model.PuntoMonitoreo;
import co.gov.ideam.sirh.calidad.model.PuntoMonitoreoTO;
import co.gov.ideam.sirh.fuentes.model.CriteriosBusquedaTO;
import co.gov.ideam.sirh.fuentes.model.FnttFuente;
import co.gov.ideam.sirh.fuentes.model.FnttTramo;

import co.gov.ideam.sirh.fuentes.view.FuenteDelegate;
import co.gov.ideam.sirh.parametros.model.Autoridades;
import co.gov.ideam.sirh.parametros.model.Divipola;
import co.gov.ideam.sirh.parametros.model.Lista;
import co.gov.ideam.sirh.parametros.model.PartZonificListas;
import co.gov.ideam.sirh.parametros.view.ParametrosDelegate;
import co.gov.ideam.sirh.seguridad.model.UsuarioConectadoTO;

import co.gov.ideam.sirh.usuarios.agua.model.Captacion;
import co.gov.ideam.sirh.usuarios.agua.model.CaptacionTO;
import co.gov.ideam.sirh.usuarios.agua.model.Concesion;
import co.gov.ideam.sirh.usuarios.agua.model.PermisoVertimiento;
import co.gov.ideam.sirh.usuarios.agua.model.PuntoVertimiento;
import co.gov.ideam.sirh.usuarios.agua.model.PuntoVertimientoTO;
import co.gov.ideam.sirh.usuarios.agua.view.UsuariosAguaDelegate;
import co.gov.ideam.sirh.util.Constantes;
import co.gov.ideam.sirh.view.StandarBean;
import co.gov.ideam.sirh.util.ConstantesCalidad;
import co.gov.ideam.sirh.util.ConstantesParametros;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.view.FacesUtils;

import co.gov.ideam.sirh.view.util.TreeNode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UISelectItems;

import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import oracle.adf.view.rich.component.rich.RichDialog;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTree;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.layout.RichDecorativeBox;
import oracle.adf.view.rich.component.rich.layout.RichPanelBox;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelSplitter;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.component.rich.nav.RichCommandLink;
import oracle.adf.view.rich.component.rich.output.RichActiveOutputText;
import oracle.adf.view.rich.component.rich.output.RichImage;
import oracle.adf.view.rich.component.rich.output.RichPanelCollection;
import oracle.adf.view.rich.component.rich.output.RichSpacer;
import oracle.adf.view.rich.context.AdfFacesContext;

import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.view.util.SpecialTreeModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Calendar;
import java.util.Date;

import java.util.GregorianCalendar;

import oracle.adf.view.rich.component.rich.RichMenu;

import oracle.adf.view.rich.component.rich.nav.RichCommandMenuItem;

import oracle.adf.view.rich.component.rich.output.RichOutputLabel;

import org.apache.myfaces.trinidad.model.TreeModel;

public class DetallePuntoMonitoreoBean extends StandarBean {
    private RichForm form1;
    private RichDocument document1;
    private List listaDepartamentos;
    private List listaMunicipios;
    private List listaArea;
    private List listaZona;
    private List listaSubzona;
    private List listaFuentes;
    private List listaTramos;
    private List listaTipoPuntos;
    private List listaUbicacion;
    private List listaSistRef;
    private PuntoMonitoreo puntoMonitoreo;
    private PuntoMonitoreoTO puntoMonitoreoTO;
    private PuntoVertimientoTO vertimiento;
    private CaptacionTO captacion;
    private String accionAdicionar;
    private RichPanelStretchLayout panelStretchLayout1;
    private RichPanelSplitter panelSplitter1;
    private RichPanelStretchLayout panelStretchLayout2;

    private RichPanelGroupLayout panelGroupLayout1;
    private RichPanelBox panelBox1;
    private RichPanelFormLayout panelFormLayout1;
    private RichPanelGroupLayout panelGroupLayout3;
    private RichSpacer spacer1;
    private RichCommandLink link_lista;
    private RichSpacer spacer2;
    private RichPanelStretchLayout panelStretchLayout3;
    private RichInputText tnombre;
    private RichSelectOneChoice selectOneChoice1;
    private UISelectItems selectItems1;
    private RichSelectOneChoice selectOneChoice2;
    private UISelectItems selectItems2;
    private RichSelectOneChoice selectOneChoice3;
    private UISelectItems selectItems3;
    private RichSelectOneChoice selectOneChoice4;
    private UISelectItems selectItems4;
    private RichSelectOneChoice selectOneChoice5;
    private UISelectItems selectItems5;
    private RichSelectOneChoice selectOneChoice6;
    private UISelectItems selectItems6;
    private RichSelectOneChoice selectOneChoice7;
    private UISelectItems selectItems7;
    private RichSelectOneChoice selectOneChoice8;
    private UISelectItems selectItems8;
    private RichSelectOneChoice selectOneChoice9;
    private UISelectItems selectItems9;
    private RichDecorativeBox decorativeBox1;
    private RichPanelFormLayout panelFormLayout2;
    private RichDecorativeBox decorativeBox2;
    private RichPanelFormLayout panelFormLayout3;
    private RichDecorativeBox decorativeBox3;
    private RichPanelGroupLayout panelGroupLayout2;
    private RichSpacer spacer3;
    private RichActiveOutputText activeOutputText2;
    private RichPanelGroupLayout panelGroupLayout4;
    private RichPanelFormLayout panelFormLayout4;
    private RichSelectOneChoice selectOneChoice10;
    private UISelectItems selectItems10;
    private RichInputText it_lat_grados;
    private RichInputText it_lat_min;
    private RichInputText it_lat_seg;
    private RichInputText it_long_grados;
    private RichInputText it_long_min;
    private RichInputText it_long_seg;
    private RichPanelFormLayout panelFormLayout7;
    private RichInputText it_altitud;
    private RichInputText it_descripcion;
    private RichDecorativeBox decorativeBox4;
    private RichPanelGroupLayout panelGroupLayout8;
    private RichCommandButton cb_aceptar;
    private RichSpacer spacer6;
    private RichCommandButton cb_borrar;
    private RichPopup p_resgistro_punto;
    private List listaMuestras;
    private RichDialog d_registro_exitoso;
    private RichActiveOutputText t_registro_punto;
    private RichImage image1;
    private RichPanelGroupLayout panelGroupLayout9;
    private RichCommandButton aceptar_punto;
    private RichPopup popup_borrar;
    private RichDialog d_confirmar_borrado;
    private RichPanelGroupLayout panelGroupLayout10;
    private RichCommandButton cb_si_borrar;
    private RichSpacer spacer7;
    private RichCommandButton cb_no_borrar;
    private RichPanelGroupLayout panelGroupLayout11;
    private RichActiveOutputText ot_borrar1;
    private RichSpacer spacer8;
    private RichActiveOutputText ot_borrar2;
    private RichActiveOutputText activeOutputText5;
    private RichPanelGroupLayout panelGroupLayout12;
    private RichSpacer spacer10;
    private RichCommandLink link_detalle;
    private RichPanelStretchLayout panelStretchLayout4;
    private RichPanelBox pbMuestras;
    private RichPanelCollection panelCollection1;
    private RichTree tree1;

    private TreeModel muestraTreeModel;
    private RichCommandLink clink;
    private RichMenu menu1;
    private RichCommandMenuItem cmi_adicionar;
    private RichSpacer spacer11;
    private RichPanelGroupLayout panelGroupLayout13;
    private RichSpacer spacer9;
    private RichSpacer spacer4;
    private RichSpacer spacer5;
    private RichSpacer spacer12;
    private RichActiveOutputText activeOutputText4;
    private RichSpacer spacer13;
    private RichSpacer spacer14;
    private RichPanelGroupLayout panelGroupLayout7;
    private RichPanelFormLayout panelFormLayout10;
    private RichSpacer spacer15;
    private RichOutputLabel outputLabel1;
    private RichPanelFormLayout panelFormLayout11;
    private RichSpacer spacer16;
    private RichOutputLabel outputLabel2;
    private RichSpacer spacer17;
    private RichCommandLink link_vertimientos;
    private String visibleLabel = null;
    private String visibleCapLabel = null;
    private RichSpacer spacer18;
    private RichSpacer spacer19;
    private RichCommandLink clink_captacion;
    private int puntoDeCaptacion = 0;
    private RichPanelFormLayout panelFormLayout5;
    private RichPanelGroupLayout panelGroupLayout5;


    public DetallePuntoMonitoreoBean() {
        FacesUtils.setActiveBean("detallePuntoMonitoreo",
                                 "Detalle Punto de Monitoreo ",
                                 CalidadDelegate.class);

        FacesUtils.removeManagedBeanFromSession("muestrasPunto");
        FacesUtils.removeManagedBeanFromSession("adicionarPuntosMonitoreo");
        FacesUtils.removeManagedBeanFromSession("MuestrasTreeHandler");


        this.load();
    }


    public void load() {
        it_descripcion = new RichInputText();
        try {
            CalidadDelegate cald = CalidadDelegate.getInstance();
            UsuariosAguaDelegate usu = UsuariosAguaDelegate.getInstance();
            FuenteDelegate fd = FuenteDelegate.getInstance();
            ParametrosDelegate pd = ParametrosDelegate.getInstance();
            Object obj = FacesUtils.getFromSession("puntoSeleccionado");


            if (obj instanceof PuntoMonitoreo) {
                this.puntoMonitoreo = (PuntoMonitoreo)obj;
                Long codigoPunto = this.puntoMonitoreo.getId();
                System.out.println("------------------punto en el load:" +
                                   this.puntoMonitoreo.getId());
                this.puntoMonitoreo = cald.getPuntoMonitoreo(codigoPunto);

            } else if (obj instanceof PuntoMonitoreoTO) {
                this.puntoMonitoreoTO = (PuntoMonitoreoTO)obj;
                System.out.println("------------------punto TO en el load:" +
                                   this.puntoMonitoreoTO.getId());
                Long codigoPunto = this.puntoMonitoreoTO.getId();
                this.puntoMonitoreo = cald.getPuntoMonitoreo(codigoPunto);
            }
            UsuarioConectadoTO usuarioConectado =
                (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");


            //Punto de un vertimiento
            if (this.puntoMonitoreo.getIdVertimiento() != null) {
                this.listaUbicacion =
                        this.getListaPorCategoria(ConstantesCalidad.UBICACION_PUNTO_MONITOREO_VERT);
                this.vertimiento =
                        usu.getVertimiento(this.puntoMonitoreo.getIdVertimiento(),
                                           usuarioConectado.getUsuario().getAutoridadAmbiental().getId().longValue());

                visibleLabel = "true";

            } else if (this.puntoMonitoreo.getIdCaptacion() != null) {
                //Punto de una captacion
                puntoDeCaptacion = 1;
                this.listaUbicacion =
                        this.getListaPorCategoria(ConstantesCalidad.UBICACION_PUNTO_MONITOREO_CAPT);


                this.captacion =
                        usu.getCaptacion(this.puntoMonitoreo.getIdCaptacion(),
                                         usuarioConectado.getUsuario().getAutoridadAmbiental().getId().longValue());

                visibleCapLabel = "true";

            } else {

                this.listaUbicacion =
                        this.getListaPorCategoria(ConstantesCalidad.UBICACION_PUNTO_MONITOREO);
                visibleCapLabel = "false";
            }


            System.out.println("--------Prepara combos de formulario de Punto de monitoreo----------");

            this.listaTipoPuntos =
                    this.getListaPorCategoria(ConstantesCalidad.TIPOS_PUNTO_MONITOREO);
            this.listaSistRef =
                    this.getListaPorCategoria(ConstantesCalidad.SISTEMA_REFERENCIA);
            this.listaDepartamentos = this.cargarDivipola(null);
            this.listaArea = this.cargarZonificacion(null);
            this.listaMunicipios = new ArrayList();
            this.listaZona = new ArrayList();
            this.listaSubzona = new ArrayList();
            this.listaFuentes = new ArrayList();
            this.listaTramos = new ArrayList();

            if (this.puntoMonitoreo.getIdArea() != null) {
                this.listaZona =
                        (List<PartZonificListas>)this.cargarZonificacion(this.puntoMonitoreo.getIdArea().getId());
            }
            if (this.puntoMonitoreo.getIdZona() != null) {
                this.listaSubzona =
                        (List<PartZonificListas>)this.cargarZonificacion(this.puntoMonitoreo.getIdZona().getId());
            }
            if (this.puntoMonitoreo.getIdSubzona() != null) {
                this.listaFuentes =
                        (List<FnttFuente>)this.getListaFuentes(this.puntoMonitoreo.getIdSubzona().getId());
            }
            if (puntoDeCaptacion == 1) {
                if (this.captacion.getTipoFuenteCaptacion().longValue() ==
                    ConstantesParametros.LISTA_AGUAS_SUBTERRANEAS) {
                    CriteriosBusquedaTO criterios = new CriteriosBusquedaTO();
                    criterios.setAutoridad(usuarioConectado.getUsuario().getAutoridadAmbiental());
                    Lista fs =
                        pd.getLista(ConstantesParametros.LISTA_FUENTE_SUBTERRANEA.intValue());
                    criterios.setTipoFuente(fs);
                    this.listaFuentes = this.cargarFuentes(criterios);
                }
            }

            if (this.puntoMonitoreo.getIdFuente() != null) {
                this.listaTramos =
                        (List<FnttTramo>)this.getListaTramos(this.puntoMonitoreo.getIdFuente().getId().intValue());
            }

            if (this.puntoMonitoreo.getDepartamento() != null) {
                this.listaMunicipios =
                        this.cargarDivipola(this.puntoMonitoreo.getDepartamento().getId());
            } else {
                this.listaMunicipios = new ArrayList();
            }
            System.out.println("--------Termina combos de formulario de Punto de monitoreo----------");

            //Construir el arbol.
            List listaNodos = new ArrayList();
            try {
                listaMuestras = cald.getMuestras(this.puntoMonitoreo);
            } catch (Exception e) {
                System.out.println("--ERROR AL CARGAR MUESTRAS DEL PUNTO---" +
                                   e);
            }
            this.puntoMonitoreo.setListaMuestras(listaMuestras);

            TreeNode nodoMuestras =
                new TreeNode(getText("MODULO_MUESTRAS"), "Muestras");
            nodoMuestras.setData("Muestras");
            listaNodos.add(nodoMuestras);


            if (listaMuestras != null) {
                Iterator it = listaMuestras.iterator();

                while (it.hasNext()) {
                    Muestra muestra = (Muestra)it.next();
                    Date fecham = muestra.getFechaMuestreo().getTime();

                    String fechamuestra =
                        new SimpleDateFormat("dd/MM/yy").format(fecham);

                    if (fechamuestra.length() == 8) {
                        fechamuestra = fechamuestra;
                    } else {
                        fechamuestra = "0" + fechamuestra;
                    }


                    TreeNode nodoMuestra =
                        new TreeNode("Muestra " + muestra.getTipoMuestra().getValor() +
                                     " - " + fechamuestra, "muestra", true,
                                     false);
                    nodoMuestra.setData(muestra);
                    nodoMuestras.getChildren().add(nodoMuestra);
                }
            } else {
                TreeNode nodoMuestra =
                    new TreeNode(getText("NO_HAY_REGISTROS"), "muestra", false,
                                 true);
                nodoMuestras.getChildren().add(nodoMuestra);
            }
            muestraTreeModel = new SpecialTreeModel(listaNodos, "children");

        } catch (IdeamException e) {
            showMessage(e);
        }
    }
    //ChangeListener Departamentos

    public void selectOneChoice3_valueChangeListener(ValueChangeEvent valueChangeEvent) throws IdeamException {
        Divipola depto = (Divipola)valueChangeEvent.getNewValue();
        try {
            if (depto != null) {
                this.listaMunicipios = this.cargarDivipola(depto.getId());
            } else {
                this.listaMunicipios = new ArrayList();
            }
        } catch (IdeamException e) {
            showMessage(e);
        }
        AdfFacesContext.getCurrentInstance().addPartialTarget(selectOneChoice4);
    }

    //ChangeListener Area

    public void selectOneChoice5_valueChangeListener(ValueChangeEvent event) {
        Object area = event.getNewValue();
        this.listaZona = new ArrayList();

        try {
            if (area != null) {
                this.listaZona =
                        this.cargarZonificacion(((PartZonificListas)area).getId());

            }
        } catch (IdeamException e) {
            showMessage(e);
        }
        AdfFacesContext.getCurrentInstance().addPartialTarget(selectOneChoice6);


    }
    //ChangeListener Zona

    public void selectOneChoice6_valueChangeListener(ValueChangeEvent event) {
        Object zona = event.getNewValue();
        this.listaSubzona = new ArrayList();

        try {
            if (zona != null) {
                this.listaSubzona =
                        this.cargarZonificacion(((PartZonificListas)zona).getId());

            }
        } catch (IdeamException e) {
            showMessage(e);
        }
        AdfFacesContext.getCurrentInstance().addPartialTarget(selectOneChoice7);

    }

    //ChangeListener Subzona

    public void selectOneChoice7_valueChangeListener(ValueChangeEvent event) {
        Object subzona = event.getNewValue();
        this.listaFuentes = new ArrayList();

        try {
            if (subzona != null) {
                this.listaFuentes =
                        this.getListaFuentes(((PartZonificListas)subzona).getId());

            }
        } catch (IdeamException e) {
            showMessage(e);
        }
        AdfFacesContext.getCurrentInstance().addPartialTarget(selectOneChoice8);

    }

    //ChangeListener Fuentes

    public void selectOneChoice8_valueChangeListener(ValueChangeEvent event) {
        Object fuenteid = event.getNewValue();
        this.listaTramos = new ArrayList();
        try {
            if (fuenteid != null) {
                this.listaTramos =
                        this.getListaTramos(((FnttFuente)fuenteid).getId().intValue());

            }
        } catch (IdeamException e) {
            showMessage(e);
        }
        AdfFacesContext.getCurrentInstance().addPartialTarget(selectOneChoice9);

    }


    public void cmi_adicionar_muestra_actionListener(ActionEvent actionEvent) {
        FacesUtils.setInSession("puntoSeleccionado", this.puntoMonitoreo);
        accionAdicionar = "adicionarMuestra";
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

    public void setListaFuentes(List listaFuentes) {
        this.listaFuentes = listaFuentes;
    }

    public List getListaFuentes() {
        return listaFuentes;
    }

    public void setListaTramos(List listaTramos) {
        this.listaTramos = listaTramos;
    }

    public List getListaTramos() {
        return listaTramos;
    }

    public void setListaTipoPuntos(List listaTipoPuntos) {
        this.listaTipoPuntos = listaTipoPuntos;
    }

    public List getListaTipoPuntos() {
        return listaTipoPuntos;
    }

    public void setListaUbicacion(List listaUbicacion) {
        this.listaUbicacion = listaUbicacion;
    }

    public List getListaUbicacion() {
        return listaUbicacion;
    }

    public void setListaSistRef(List listaSistRef) {
        this.listaSistRef = listaSistRef;
    }

    public List getListaSistRef() {
        return listaSistRef;
    }

    public void setPuntoMonitoreo(PuntoMonitoreo puntoMonitoreo) {
        this.puntoMonitoreo = puntoMonitoreo;
    }

    public PuntoMonitoreo getPuntoMonitoreo() {
        return puntoMonitoreo;
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

    public void setPanelStretchLayout2(RichPanelStretchLayout panelStretchLayout2) {
        this.panelStretchLayout2 = panelStretchLayout2;
    }

    public RichPanelStretchLayout getPanelStretchLayout2() {
        return panelStretchLayout2;
    }

    public void setPanelGroupLayout1(RichPanelGroupLayout panelGroupLayout1) {
        this.panelGroupLayout1 = panelGroupLayout1;
    }

    public RichPanelGroupLayout getPanelGroupLayout1() {
        return panelGroupLayout1;
    }

    public void setPanelBox1(RichPanelBox panelBox1) {
        this.panelBox1 = panelBox1;
    }

    public RichPanelBox getPanelBox1() {
        return panelBox1;
    }

    public void setPanelFormLayout1(RichPanelFormLayout panelFormLayout1) {
        this.panelFormLayout1 = panelFormLayout1;
    }

    public RichPanelFormLayout getPanelFormLayout1() {
        return panelFormLayout1;
    }


    public void setPanelGroupLayout3(RichPanelGroupLayout panelGroupLayout3) {
        this.panelGroupLayout3 = panelGroupLayout3;
    }

    public RichPanelGroupLayout getPanelGroupLayout3() {
        return panelGroupLayout3;
    }

    public void setSpacer1(RichSpacer spacer1) {
        this.spacer1 = spacer1;
    }

    public RichSpacer getSpacer1() {
        return spacer1;
    }

    public void setLink_lista(RichCommandLink commandLink1) {
        this.link_lista = commandLink1;
    }

    public RichCommandLink getLink_lista() {
        return link_lista;
    }

    public void setSpacer2(RichSpacer spacer2) {
        this.spacer2 = spacer2;
    }

    public RichSpacer getSpacer2() {
        return spacer2;
    }


    public void setPanelStretchLayout3(RichPanelStretchLayout panelStretchLayout3) {
        this.panelStretchLayout3 = panelStretchLayout3;
    }

    public RichPanelStretchLayout getPanelStretchLayout3() {
        return panelStretchLayout3;
    }


    public void setTnombre(RichInputText inputText1) {
        this.tnombre = inputText1;
    }

    public RichInputText getTnombre() {
        return tnombre;
    }

    public void setSelectOneChoice1(RichSelectOneChoice selectOneChoice1) {
        this.selectOneChoice1 = selectOneChoice1;
    }

    public RichSelectOneChoice getSelectOneChoice1() {
        return selectOneChoice1;
    }

    public void setSelectItems1(UISelectItems selectItems1) {
        this.selectItems1 = selectItems1;
    }

    public UISelectItems getSelectItems1() {
        return selectItems1;
    }

    public void setSelectOneChoice2(RichSelectOneChoice selectOneChoice2) {
        this.selectOneChoice2 = selectOneChoice2;
    }

    public RichSelectOneChoice getSelectOneChoice2() {
        return selectOneChoice2;
    }

    public void setSelectItems2(UISelectItems selectItems2) {
        this.selectItems2 = selectItems2;
    }

    public UISelectItems getSelectItems2() {
        return selectItems2;
    }

    public void setSelectOneChoice3(RichSelectOneChoice selectOneChoice3) {
        this.selectOneChoice3 = selectOneChoice3;
    }

    public RichSelectOneChoice getSelectOneChoice3() {
        return selectOneChoice3;
    }

    public void setSelectItems3(UISelectItems selectItems3) {
        this.selectItems3 = selectItems3;
    }

    public UISelectItems getSelectItems3() {
        return selectItems3;
    }

    public void setSelectOneChoice4(RichSelectOneChoice selectOneChoice4) {
        this.selectOneChoice4 = selectOneChoice4;
    }

    public RichSelectOneChoice getSelectOneChoice4() {
        return selectOneChoice4;
    }

    public void setSelectItems4(UISelectItems selectItems4) {
        this.selectItems4 = selectItems4;
    }

    public UISelectItems getSelectItems4() {
        return selectItems4;
    }

    public void setSelectOneChoice5(RichSelectOneChoice selectOneChoice5) {
        this.selectOneChoice5 = selectOneChoice5;
    }

    public RichSelectOneChoice getSelectOneChoice5() {
        return selectOneChoice5;
    }

    public void setSelectItems5(UISelectItems selectItems5) {
        this.selectItems5 = selectItems5;
    }

    public UISelectItems getSelectItems5() {
        return selectItems5;
    }

    public void setSelectOneChoice6(RichSelectOneChoice selectOneChoice6) {
        this.selectOneChoice6 = selectOneChoice6;
    }

    public RichSelectOneChoice getSelectOneChoice6() {
        return selectOneChoice6;
    }

    public void setSelectItems6(UISelectItems selectItems6) {
        this.selectItems6 = selectItems6;
    }

    public UISelectItems getSelectItems6() {
        return selectItems6;
    }

    public void setSelectOneChoice7(RichSelectOneChoice selectOneChoice7) {
        this.selectOneChoice7 = selectOneChoice7;
    }

    public RichSelectOneChoice getSelectOneChoice7() {
        return selectOneChoice7;
    }

    public void setSelectItems7(UISelectItems selectItems7) {
        this.selectItems7 = selectItems7;
    }

    public UISelectItems getSelectItems7() {
        return selectItems7;
    }

    public void setSelectOneChoice8(RichSelectOneChoice selectOneChoice8) {
        this.selectOneChoice8 = selectOneChoice8;
    }

    public RichSelectOneChoice getSelectOneChoice8() {
        return selectOneChoice8;
    }

    public void setSelectItems8(UISelectItems selectItems8) {
        this.selectItems8 = selectItems8;
    }

    public UISelectItems getSelectItems8() {
        return selectItems8;
    }

    public void setSelectOneChoice9(RichSelectOneChoice selectOneChoice9) {
        this.selectOneChoice9 = selectOneChoice9;
    }

    public RichSelectOneChoice getSelectOneChoice9() {
        return selectOneChoice9;
    }

    public void setSelectItems9(UISelectItems selectItems9) {
        this.selectItems9 = selectItems9;
    }

    public UISelectItems getSelectItems9() {
        return selectItems9;
    }


    public void setDecorativeBox1(RichDecorativeBox decorativeBox1) {
        this.decorativeBox1 = decorativeBox1;
    }

    public RichDecorativeBox getDecorativeBox1() {
        return decorativeBox1;
    }

    public void setPanelFormLayout2(RichPanelFormLayout panelFormLayout2) {
        this.panelFormLayout2 = panelFormLayout2;
    }

    public RichPanelFormLayout getPanelFormLayout2() {
        return panelFormLayout2;
    }

    public void setDecorativeBox2(RichDecorativeBox decorativeBox2) {
        this.decorativeBox2 = decorativeBox2;
    }

    public RichDecorativeBox getDecorativeBox2() {
        return decorativeBox2;
    }

    public void setPanelFormLayout3(RichPanelFormLayout panelFormLayout3) {
        this.panelFormLayout3 = panelFormLayout3;
    }

    public RichPanelFormLayout getPanelFormLayout3() {
        return panelFormLayout3;
    }

    public void setDecorativeBox3(RichDecorativeBox decorativeBox3) {
        this.decorativeBox3 = decorativeBox3;
    }

    public RichDecorativeBox getDecorativeBox3() {
        return decorativeBox3;
    }


    public void setPanelGroupLayout2(RichPanelGroupLayout panelGroupLayout2) {
        this.panelGroupLayout2 = panelGroupLayout2;
    }

    public RichPanelGroupLayout getPanelGroupLayout2() {
        return panelGroupLayout2;
    }

    public void setSpacer3(RichSpacer spacer3) {
        this.spacer3 = spacer3;
    }

    public RichSpacer getSpacer3() {
        return spacer3;
    }

    public void setActiveOutputText2(RichActiveOutputText activeOutputText2) {
        this.activeOutputText2 = activeOutputText2;
    }

    public RichActiveOutputText getActiveOutputText2() {
        return activeOutputText2;
    }

    public void setPanelGroupLayout4(RichPanelGroupLayout panelGroupLayout4) {
        this.panelGroupLayout4 = panelGroupLayout4;
    }

    public RichPanelGroupLayout getPanelGroupLayout4() {
        return panelGroupLayout4;
    }


    public void setPanelFormLayout4(RichPanelFormLayout panelFormLayout4) {
        this.panelFormLayout4 = panelFormLayout4;
    }

    public RichPanelFormLayout getPanelFormLayout4() {
        return panelFormLayout4;
    }

    public void setSelectOneChoice10(RichSelectOneChoice selectOneChoice10) {
        this.selectOneChoice10 = selectOneChoice10;
    }

    public RichSelectOneChoice getSelectOneChoice10() {
        return selectOneChoice10;
    }

    public void setSelectItems10(UISelectItems selectItems10) {
        this.selectItems10 = selectItems10;
    }

    public UISelectItems getSelectItems10() {
        return selectItems10;
    }


    public void setIt_lat_grados(RichInputText inputText1) {
        this.it_lat_grados = inputText1;
    }

    public RichInputText getIt_lat_grados() {
        return it_lat_grados;
    }

    public void setIt_lat_min(RichInputText inputText1) {
        this.it_lat_min = inputText1;
    }

    public RichInputText getIt_lat_min() {
        return it_lat_min;
    }

    public void setIt_lat_seg(RichInputText inputText1) {
        this.it_lat_seg = inputText1;
    }

    public RichInputText getIt_lat_seg() {
        return it_lat_seg;
    }


    public void setIt_long_grados(RichInputText inputText1) {
        this.it_long_grados = inputText1;
    }

    public RichInputText getIt_long_grados() {
        return it_long_grados;
    }

    public void setIt_long_min(RichInputText inputText1) {
        this.it_long_min = inputText1;
    }

    public RichInputText getIt_long_min() {
        return it_long_min;
    }

    public void setIt_long_seg(RichInputText inputText1) {
        this.it_long_seg = inputText1;
    }

    public RichInputText getIt_long_seg() {
        return it_long_seg;
    }


    public void setPanelFormLayout7(RichPanelFormLayout panelFormLayout7) {
        this.panelFormLayout7 = panelFormLayout7;
    }

    public RichPanelFormLayout getPanelFormLayout7() {
        return panelFormLayout7;
    }


    public void setIt_altitud(RichInputText inputText1) {
        this.it_altitud = inputText1;
    }

    public RichInputText getIt_altitud() {
        return it_altitud;
    }

    public void setIt_descripcion(RichInputText inputText1) {
        this.it_descripcion = inputText1;
    }

    public RichInputText getIt_descripcion() {
        return it_descripcion;
    }

    public void setDecorativeBox4(RichDecorativeBox decorativeBox4) {
        this.decorativeBox4 = decorativeBox4;
    }

    public RichDecorativeBox getDecorativeBox4() {
        return decorativeBox4;
    }

    public void setPanelGroupLayout8(RichPanelGroupLayout panelGroupLayout8) {
        this.panelGroupLayout8 = panelGroupLayout8;
    }

    public RichPanelGroupLayout getPanelGroupLayout8() {
        return panelGroupLayout8;
    }

    public void setCb_aceptar(RichCommandButton commandButton1) {
        this.cb_aceptar = commandButton1;
    }

    public RichCommandButton getCb_aceptar() {
        return cb_aceptar;
    }

    public void setSpacer6(RichSpacer spacer6) {
        this.spacer6 = spacer6;
    }

    public RichSpacer getSpacer6() {
        return spacer6;
    }

    public void setCb_borrar(RichCommandButton commandButton2) {
        this.cb_borrar = commandButton2;
    }

    public RichCommandButton getCb_borrar() {
        return cb_borrar;
    }


    public void cb_aceptar_actionListener(ActionEvent actionEvent) {

        boolean continuar = true;

        if (this.tnombre.getValue() == null ||
            this.tnombre.getValue().toString().length() == 0) {
            continuar = false;
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                        this.tnombre);
        }


        if (this.selectOneChoice1.getValue() == null) {
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                        this.selectOneChoice1);
            continuar = false;
        }


        if (this.selectOneChoice2.getValue() == null) {
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                        this.selectOneChoice2);
            continuar = false;
        }


        if (this.selectOneChoice3.getValue() == null) {
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                        this.selectOneChoice3);
            continuar = false;
        }


        if (this.selectOneChoice4.getValue() == null) {
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                        this.selectOneChoice4);
            continuar = false;
        }

        if (this.selectOneChoice5.getValue() == null) {
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                        this.selectOneChoice5);
            continuar = false;
        }

        if (this.selectOneChoice6.getValue() == null) {
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                        this.selectOneChoice6);
            continuar = false;
        }

        if (this.selectOneChoice7.getValue() == null) {
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                        this.selectOneChoice7);
            continuar = false;
        }

        if (this.selectOneChoice8.getValue() == null) {
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                        this.selectOneChoice8);
            continuar = false;
        }

        if (this.selectOneChoice9.getValue() == null) {
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                        this.selectOneChoice9);
            continuar = false;
        }


        if (this.selectOneChoice10.getValue() == null) {
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                        this.selectOneChoice10);
            continuar = false;
        }


        if (this.it_lat_grados.getValue() == null ||
            this.it_lat_grados.getValue().toString().length() == 0) {
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                        this.it_lat_grados);
            continuar = false;
        }

        if (this.it_lat_min.getValue() == null ||
            this.it_lat_min.getValue().toString().length() == 0) {
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                        this.it_lat_min);
            continuar = false;
        }

        if (this.it_lat_seg.getValue() == null ||
            this.it_lat_seg.getValue().toString().length() == 0) {
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                        this.it_lat_seg);
            continuar = false;
        }

        if (this.it_long_grados.getValue() == null ||
            this.it_long_grados.getValue().toString().length() == 0) {
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                        this.it_long_grados);
            continuar = false;
        }

        if (this.it_long_min.getValue() == null ||
            this.it_long_min.getValue().toString().length() == 0) {
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                        this.it_long_min);
            continuar = false;
        }

        if (this.it_long_seg.getValue() == null ||
            this.it_long_seg.getValue().toString().length() == 0) {
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                        this.it_long_seg);
            continuar = false;
        }

        if (this.it_altitud.getValue() == null ||
            this.it_altitud.getValue().toString().length() == 0) {
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                        this.it_altitud);
            continuar = false;
        }

        if (continuar) {
            this.save();
        }
    }


    public void save() {
        try {
            CalidadDelegate cld = CalidadDelegate.getInstance();
            UsuarioConectadoTO usuarioConectado =
                (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
            puntoMonitoreo.setNombre(this.tnombre.getValue().toString().toUpperCase());

            puntoMonitoreo.setDescripcion_acceso((this.it_descripcion.getValue() !=
                                                  null) ?
                                                 this.it_descripcion.getValue().toString() :
                                                 null);


            puntoMonitoreo.setTipoPunto((Lista)this.selectOneChoice1.getValue());
            puntoMonitoreo.setUbicacion((Lista)this.selectOneChoice2.getValue());
            puntoMonitoreo.setDepartamento((Divipola)this.selectOneChoice3.getValue());
            puntoMonitoreo.setMunicipio((Divipola)this.selectOneChoice4.getValue());
            puntoMonitoreo.setSistemaRef((Lista)this.selectOneChoice10.getValue());
            puntoMonitoreo.setIdTipo_punto(this.puntoMonitoreo.getTipoPunto().getCodigo());
            puntoMonitoreo.setIdubicacion(this.puntoMonitoreo.getUbicacion().getCodigo());
            puntoMonitoreo.setIdDepartamento(this.puntoMonitoreo.getDepartamento().getId());
            puntoMonitoreo.setIdmunicipio(this.puntoMonitoreo.getMunicipio().getId());
            puntoMonitoreo.setIdFuente((FnttFuente)this.selectOneChoice8.getValue());
            puntoMonitoreo.setIdTramo((FnttTramo)this.selectOneChoice9.getValue());
            puntoMonitoreo.setIdArea((PartZonificListas)this.selectOneChoice5.getValue());
            puntoMonitoreo.setIdZona((PartZonificListas)this.selectOneChoice6.getValue());
            puntoMonitoreo.setIdSubzona((PartZonificListas)this.selectOneChoice7.getValue());
            puntoMonitoreo.setIdSist_ref(this.puntoMonitoreo.getSistemaRef().getCodigo());
            puntoMonitoreo.setCodigoAutoridad(usuarioConectado.getUsuario().getAutoridadAmbiental().getId().longValue());
            puntoMonitoreo.setAltitud(Double.parseDouble(this.it_altitud.getValue().toString()));
            puntoMonitoreo.setLatitud_grados(Integer.parseInt(this.it_lat_grados.getValue().toString()));
            puntoMonitoreo.setLatitud_minutos(Integer.parseInt(this.it_lat_min.getValue().toString()));
            puntoMonitoreo.setLatitud_segundos(Double.parseDouble(this.it_lat_seg.getValue().toString()));
            puntoMonitoreo.setLongitud_grados(Integer.parseInt(this.it_long_grados.getValue().toString()));
            puntoMonitoreo.setLongitud_minutos(Integer.parseInt(this.it_long_min.getValue().toString()));
            puntoMonitoreo.setLongitud_segundos(Double.parseDouble(this.it_long_seg.getValue().toString()));
            puntoMonitoreo.setCodigoAutoridad(usuarioConectado.getUsuario().getAutoridadAmbiental().getId().longValue());

            PuntoMonitoreo existe =
                cld.getPuntoMonitoreoExiste(this.puntoMonitoreo.getNombre(),
                                            this.puntoMonitoreo.getIdFuente().getId(),
                                            this.puntoMonitoreo.getIdTramo().getId());
            if (existe != null &&
                existe.getId().longValue() != this.puntoMonitoreo.getId().longValue()) {
                showMessage(getText("PTO_EXISTE"),
                            FacesMessage.SEVERITY_ERROR);
                return;
            }
            PuntoMonitoreo pp = cld.updatePuntoMonitoreo(this.puntoMonitoreo);


            try {

                /**
                            * Estas variables son necesarias para llenar los datos intrinsecos de la auditoria.
                            */

                Auditoria auditoria = new Auditoria();
                auditoria.setIdUsuario(usuarioConectado.getUsuario().getCodigo());
                auditoria.setOperacion("MODIFICAR");
                auditoria.setObjeto("PUNTOS_MONITOREO");
                auditoria.setMetodo(Thread.currentThread().getStackTrace()[1].getMethodName());
                auditoria.setClase(this.getClass().getName());
                //Objeto Afectado (usuario modificado, eliminado o agregado)
                auditoria.setIdObjeto(this.puntoMonitoreo.getId());

                AuditoriasDelegate audDelegate =
                    AuditoriasDelegate.getInstance();
                audDelegate.setAuditoria(auditoria);

            } catch (Exception e) {
                System.out.println("No se pudo Almacenar la auditoria: ---------------------------------- ");
                System.out.println(e.getMessage() +
                                   "------------------------------------------------------ ");
            }


            showPopup(p_resgistro_punto, true);
        } catch (IdeamException e) {
            showMessage(e);
        }
    }


    public void cbBorrar_actionListener(ActionEvent actionEvent) {
        String nombre = this.puntoMonitoreo.getNombre();

        String params[] = { nombre };
        String texto = getText("PTO_ELIMINAR", params);
        ot_borrar1.setValue(texto);
        ot_borrar2.setValue(nombre);
        try {
            CalidadDelegate cld = CalidadDelegate.getInstance();
            List<Muestra> mm = cld.getMuestras(this.puntoMonitoreo);


            if (mm != null && mm.size() > 0) {
                showMessage(getText("PTO_MUESTRAS_ASOCIADAS"));

            } else {
                AdfFacesContext.getCurrentInstance().addPartialTarget(popup_borrar);
                showPopup(popup_borrar, true);
            }


        } catch (IdeamException e) {

            showMessage(e.getMessage());
        }


    }

    public void cb_no_borrar_actionListener(ActionEvent actionEvent) {
        showPopup(popup_borrar, false);
    }

    public void cb_si_borrar_actionListener(ActionEvent actionEvent) {
        try {
            CalidadDelegate cld = CalidadDelegate.getInstance();
            cld.deletePuntoMonitoreo(this.puntoMonitoreo);

            try {

                /**
                 * Estas variables son necesarias para llenar los datos intrinsecos de la auditoria.
                 */
                UsuarioConectadoTO usuarioConectado =
                    (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");

                Auditoria auditoria = new Auditoria();
                auditoria.setIdUsuario(usuarioConectado.getUsuario().getCodigo());
                auditoria.setOperacion("BORRAR");
                auditoria.setObjeto("PUNTOS_MONITOREO");
                auditoria.setMetodo(Thread.currentThread().getStackTrace()[1].getMethodName());
                auditoria.setClase(this.getClass().getName());
                //Objeto Afectado (usuario modificado, eliminado o agregado)
                auditoria.setIdObjeto(this.puntoMonitoreo.getId());

                AuditoriasDelegate audDelegate =
                    AuditoriasDelegate.getInstance();
                audDelegate.setAuditoria(auditoria);

            } catch (Exception e) {
                System.out.println("No se pudo Almacenar la auditoria: ---------------------------------- ");
                System.out.println(e.getMessage() +
                                   "------------------------------------------------------ ");
            }


            showMessage(getText("PTO_ELIMINADO"));
        } catch (IdeamException e) {

            showMessage(e.getMessage());
        }
    }


    public void setP_resgistro_punto(RichPopup p_resgistro_punto) {
        this.p_resgistro_punto = p_resgistro_punto;
    }

    public RichPopup getP_resgistro_punto() {
        return p_resgistro_punto;
    }

    public void setD_registro_exitoso(RichDialog d_registro_exitoso) {
        this.d_registro_exitoso = d_registro_exitoso;
    }

    public RichDialog getD_registro_exitoso() {
        return d_registro_exitoso;
    }


    public void setT_registro_punto(RichActiveOutputText t_registro_punto) {
        this.t_registro_punto = t_registro_punto;
    }

    public RichActiveOutputText getT_registro_punto() {
        return t_registro_punto;
    }

    public void setImage1(RichImage image1) {
        this.image1 = image1;
    }

    public RichImage getImage1() {
        return image1;
    }

    public void setPanelGroupLayout9(RichPanelGroupLayout panelGroupLayout9) {
        this.panelGroupLayout9 = panelGroupLayout9;
    }

    public RichPanelGroupLayout getPanelGroupLayout9() {
        return panelGroupLayout9;
    }

    public void setAceptar_punto(RichCommandButton aceptar_punto) {
        this.aceptar_punto = aceptar_punto;
    }

    public RichCommandButton getAceptar_punto() {
        return aceptar_punto;
    }

    public void setPopup_borrar(RichPopup popup1) {
        this.popup_borrar = popup1;
    }

    public RichPopup getPopup_borrar() {
        return popup_borrar;
    }

    public void setD_confirmar_borrado(RichDialog dialog1) {
        this.d_confirmar_borrado = dialog1;
    }

    public RichDialog getD_confirmar_borrado() {
        return d_confirmar_borrado;
    }


    public void setPanelGroupLayout10(RichPanelGroupLayout panelGroupLayout10) {
        this.panelGroupLayout10 = panelGroupLayout10;
    }

    public RichPanelGroupLayout getPanelGroupLayout10() {
        return panelGroupLayout10;
    }

    public void setCb_si_borrar(RichCommandButton commandButton1) {
        this.cb_si_borrar = commandButton1;
    }

    public RichCommandButton getCb_si_borrar() {
        return cb_si_borrar;
    }

    public void setSpacer7(RichSpacer spacer7) {
        this.spacer7 = spacer7;
    }

    public RichSpacer getSpacer7() {
        return spacer7;
    }

    public void setCb_no_borrar(RichCommandButton commandButton2) {
        this.cb_no_borrar = commandButton2;
    }

    public RichCommandButton getCb_no_borrar() {
        return cb_no_borrar;
    }

    public void setPanelGroupLayout11(RichPanelGroupLayout panelGroupLayout11) {
        this.panelGroupLayout11 = panelGroupLayout11;
    }

    public RichPanelGroupLayout getPanelGroupLayout11() {
        return panelGroupLayout11;
    }

    public void setOt_borrar1(RichActiveOutputText activeOutputText5) {
        this.ot_borrar1 = activeOutputText5;
    }

    public RichActiveOutputText getOt_borrar1() {
        return ot_borrar1;
    }

    public void setSpacer8(RichSpacer spacer8) {
        this.spacer8 = spacer8;
    }

    public RichSpacer getSpacer8() {
        return spacer8;
    }

    public void setOt_borrar2(RichActiveOutputText activeOutputText6) {
        this.ot_borrar2 = activeOutputText6;
    }

    public RichActiveOutputText getOt_borrar2() {
        return ot_borrar2;
    }


    public void setActiveOutputText5(RichActiveOutputText activeOutputText5) {
        this.activeOutputText5 = activeOutputText5;
    }

    public RichActiveOutputText getActiveOutputText5() {
        return activeOutputText5;
    }

    public void setPanelGroupLayout12(RichPanelGroupLayout panelGroupLayout12) {
        this.panelGroupLayout12 = panelGroupLayout12;
    }

    public RichPanelGroupLayout getPanelGroupLayout12() {
        return panelGroupLayout12;
    }

    public void setSpacer10(RichSpacer spacer10) {
        this.spacer10 = spacer10;
    }

    public RichSpacer getSpacer10() {
        return spacer10;
    }


    public void setLink_detalle(RichCommandLink commandLink2) {
        this.link_detalle = commandLink2;
    }

    public RichCommandLink getLink_detalle() {
        return link_detalle;
    }


    public void setPanelStretchLayout4(RichPanelStretchLayout panelStretchLayout4) {
        this.panelStretchLayout4 = panelStretchLayout4;
    }

    public RichPanelStretchLayout getPanelStretchLayout4() {
        return panelStretchLayout4;
    }

    public void setPbMuestras(RichPanelBox panelBox2) {
        this.pbMuestras = panelBox2;
    }

    public RichPanelBox getPbMuestras() {
        return pbMuestras;
    }

    public void setPanelCollection1(RichPanelCollection panelCollection1) {
        this.panelCollection1 = panelCollection1;
    }

    public RichPanelCollection getPanelCollection1() {
        return panelCollection1;
    }

    public void setTree1(RichTree tree1) {
        this.tree1 = tree1;
    }

    public RichTree getTree1() {
        return tree1;
    }


    public void setListaMuestras(List listaMuestras) {
        this.listaMuestras = listaMuestras;
    }

    public List getListaMuestras() {
        return listaMuestras;
    }

    public void setMuestraTreeModel(TreeModel muestraTreeModel) {
        this.muestraTreeModel = muestraTreeModel;
    }

    public TreeModel getMuestraTreeModel() {
        return muestraTreeModel;
    }

    public void setClink(RichCommandLink commandLink1) {
        this.clink = commandLink1;
    }

    public RichCommandLink getClink() {
        return clink;
    }


    public void clink_actionListener(ActionEvent actionEvent) {
        String nombreParametro =
            (String)actionEvent.getComponent().getAttributes().get("nombreParametro");
        Object data =
            actionEvent.getComponent().getAttributes().get("valorParametro");
        if (nombreParametro != null && data != null) {
            FacesUtils.setInSession(nombreParametro, data);
        }
    }


    public void setMenu1(RichMenu menu1) {
        this.menu1 = menu1;
    }

    public RichMenu getMenu1() {
        return menu1;
    }


    public void setAccionAdicionar(String accionAdicionar) {
        this.accionAdicionar = accionAdicionar;
    }

    public String getAccionAdicionar() {
        return accionAdicionar;
    }

    public void setCmi_adicionar(RichCommandMenuItem cmi_adicionar) {
        this.cmi_adicionar = cmi_adicionar;
    }

    public RichCommandMenuItem getCmi_adicionar() {
        return cmi_adicionar;
    }

    public void setSpacer11(RichSpacer spacer11) {
        this.spacer11 = spacer11;
    }

    public RichSpacer getSpacer11() {
        return spacer11;
    }

    public void setPanelGroupLayout13(RichPanelGroupLayout panelGroupLayout13) {
        this.panelGroupLayout13 = panelGroupLayout13;
    }

    public RichPanelGroupLayout getPanelGroupLayout13() {
        return panelGroupLayout13;
    }

    public void setSpacer9(RichSpacer spacer9) {
        this.spacer9 = spacer9;
    }

    public RichSpacer getSpacer9() {
        return spacer9;
    }


    public void setSpacer4(RichSpacer spacer4) {
        this.spacer4 = spacer4;
    }

    public RichSpacer getSpacer4() {
        return spacer4;
    }

    public void setSpacer5(RichSpacer spacer5) {
        this.spacer5 = spacer5;
    }

    public RichSpacer getSpacer5() {
        return spacer5;
    }

    public void setSpacer12(RichSpacer spacer12) {
        this.spacer12 = spacer12;
    }

    public RichSpacer getSpacer12() {
        return spacer12;
    }

    public void setActiveOutputText4(RichActiveOutputText activeOutputText4) {
        this.activeOutputText4 = activeOutputText4;
    }

    public RichActiveOutputText getActiveOutputText4() {
        return activeOutputText4;
    }

    public void setSpacer13(RichSpacer spacer13) {
        this.spacer13 = spacer13;
    }

    public RichSpacer getSpacer13() {
        return spacer13;
    }

    public void setSpacer14(RichSpacer spacer14) {
        this.spacer14 = spacer14;
    }

    public RichSpacer getSpacer14() {
        return spacer14;
    }

    public void setPanelGroupLayout7(RichPanelGroupLayout panelGroupLayout7) {
        this.panelGroupLayout7 = panelGroupLayout7;
    }

    public RichPanelGroupLayout getPanelGroupLayout7() {
        return panelGroupLayout7;
    }

    public void setPanelFormLayout10(RichPanelFormLayout panelFormLayout10) {
        this.panelFormLayout10 = panelFormLayout10;
    }

    public RichPanelFormLayout getPanelFormLayout10() {
        return panelFormLayout10;
    }

    public void setSpacer15(RichSpacer spacer15) {
        this.spacer15 = spacer15;
    }

    public RichSpacer getSpacer15() {
        return spacer15;
    }

    public void setOutputLabel1(RichOutputLabel outputLabel1) {
        this.outputLabel1 = outputLabel1;
    }

    public RichOutputLabel getOutputLabel1() {
        return outputLabel1;
    }

    public void setPanelFormLayout11(RichPanelFormLayout panelFormLayout11) {
        this.panelFormLayout11 = panelFormLayout11;
    }

    public RichPanelFormLayout getPanelFormLayout11() {
        return panelFormLayout11;
    }

    public void setSpacer16(RichSpacer spacer16) {
        this.spacer16 = spacer16;
    }

    public RichSpacer getSpacer16() {
        return spacer16;
    }

    public void setOutputLabel2(RichOutputLabel outputLabel2) {
        this.outputLabel2 = outputLabel2;
    }

    public RichOutputLabel getOutputLabel2() {
        return outputLabel2;
    }

    public void setSpacer17(RichSpacer spacer17) {
        this.spacer17 = spacer17;
    }

    public RichSpacer getSpacer17() {
        return spacer17;
    }

    public void setPuntoMonitoreoTO(PuntoMonitoreoTO puntoMonitoreoTO) {
        this.puntoMonitoreoTO = puntoMonitoreoTO;
    }

    public PuntoMonitoreoTO getPuntoMonitoreoTO() {
        return puntoMonitoreoTO;
    }

    public void setLink_vertimientos(RichCommandLink commandLink1) {
        this.link_vertimientos = commandLink1;
    }

    public RichCommandLink getLink_vertimientos() {
        return link_vertimientos;
    }


    public void setVisibleLabel(String visibleLabel) {
        this.visibleLabel = visibleLabel;
    }

    public String getVisibleLabel() {
        return visibleLabel;
    }

    public void setVertimiento(PuntoVertimientoTO vertimiento) {
        this.vertimiento = vertimiento;
    }

    public PuntoVertimientoTO getVertimiento() {
        return vertimiento;
    }

    public void setSpacer18(RichSpacer spacer18) {
        this.spacer18 = spacer18;
    }

    public RichSpacer getSpacer18() {
        return spacer18;
    }


    public void setCaptacion(CaptacionTO captacion) {
        this.captacion = captacion;
    }

    public CaptacionTO getCaptacion() {
        return captacion;
    }

    public void setVisibleCapLabel(String visibleCapLabel) {
        this.visibleCapLabel = visibleCapLabel;
    }

    public String getVisibleCapLabel() {
        return visibleCapLabel;
    }

    public void setSpacer19(RichSpacer spacer19) {
        this.spacer19 = spacer19;
    }

    public RichSpacer getSpacer19() {
        return spacer19;
    }

    public void setClink_captacion(RichCommandLink commandLink1) {
        this.clink_captacion = commandLink1;
    }

    public RichCommandLink getClink_captacion() {
        return clink_captacion;
    }

    public void setPuntoDeCaptacion(int puntoDeCaptacion) {
        this.puntoDeCaptacion = puntoDeCaptacion;
    }

    public int getPuntoDeCaptacion() {
        return puntoDeCaptacion;
    }


    public void setPanelFormLayout5(RichPanelFormLayout panelFormLayout5) {
        this.panelFormLayout5 = panelFormLayout5;
    }

    public RichPanelFormLayout getPanelFormLayout5() {
        return panelFormLayout5;
    }

    public void setPanelGroupLayout5(RichPanelGroupLayout panelGroupLayout5) {
        this.panelGroupLayout5 = panelGroupLayout5;
    }

    public RichPanelGroupLayout getPanelGroupLayout5() {
        return panelGroupLayout5;
    }
} //Fin
