package co.gov.ideam.sirh.usuarios.agua.view;


import co.gov.ideam.sirh.auditoria.model.Auditoria;
import co.gov.ideam.sirh.auditoria.view.AuditoriasDelegate;
import co.gov.ideam.sirh.parametros.model.ActividadCIIU;
import co.gov.ideam.sirh.parametros.model.Categoria;
import co.gov.ideam.sirh.parametros.model.Divipola;
import co.gov.ideam.sirh.pueaa.PueaaDelegate;
import co.gov.ideam.sirh.pueaa.model.PueatProyectos;
import co.gov.ideam.sirh.pueaa.model.PueatPuea;
import co.gov.ideam.sirh.seguridad.model.UsuarioConectadoTO;
import co.gov.ideam.sirh.usuarios.agua.model.Concesion;
import co.gov.ideam.sirh.usuarios.agua.model.PermisoVertimiento;
import co.gov.ideam.sirh.usuarios.agua.model.Predio;
import co.gov.ideam.sirh.usuarios.agua.model.UsuarioAgua;
import co.gov.ideam.sirh.usuarios.agua.model.UsuarioAguaTO;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.view.FacesUtils;
import co.gov.ideam.sirh.view.StandarBean;
import co.gov.ideam.sirh.view.util.EmailValidator;
import co.gov.ideam.sirh.view.util.NombreValidator;
import co.gov.ideam.sirh.view.util.SpecialTreeModel;
import co.gov.ideam.sirh.view.util.TreeNode;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UINamingContainer;
import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import oracle.adf.view.rich.component.rich.RichDialog;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.RichMenu;
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
import oracle.adf.view.rich.component.rich.nav.RichCommandImageLink;
import oracle.adf.view.rich.component.rich.nav.RichCommandLink;
import oracle.adf.view.rich.component.rich.nav.RichCommandMenuItem;
import oracle.adf.view.rich.component.rich.output.RichMessage;
import oracle.adf.view.rich.component.rich.output.RichOutputLabel;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.component.rich.output.RichPanelCollection;
import oracle.adf.view.rich.component.rich.output.RichSpacer;
import oracle.adf.view.rich.context.AdfFacesContext;
import oracle.adf.view.rich.event.DialogEvent;

import org.apache.myfaces.trinidad.model.TreeModel;


public class DetalleUsuarioBean extends StandarBean {
    private RichForm f2;
    private RichDocument d2;
    private RichPanelStretchLayout psl1;


    private List listaTiposDocumento;
    private List listaDepartamentos;
    private List listaMunicipios;
    private List listaDeptosRepresentante;
    private List listaMunicipiosRespresentante;
    private String accionAdicionar;
    private UsuarioAgua usuarioAgua;
    private UsuarioAguaTO usuarioAguaTo;
    private Divipola deptoSeleccionado;
    private Divipola municipioSeleccionado;
    private Divipola deptoJuridicaSeleccionado;
    private Divipola municipioJuridicaSeleccionado;
    private Divipola deptoMunicipioSeleccionado;
    private Divipola municipioMunicipioSeleccionado;

    private Divipola deptoRespresentnate;
    private Divipola municipioRepresentante;
    private List listaPredios;
    private List<PueatPuea> listaPueaa;
    private TreeModel prediosTreeModel;
    private TreeModel pueaaTreeModel;
    private String codigoCiiu;
    private String descripcionCiiu;
    private ActividadCIIU actividadCiiu;
    private String accionBorrar;

    private RichPanelSplitter ps1;
    private RichPanelStretchLayout psl2;
    private RichCommandLink cl1;
    private RichPanelGroupLayout pgl1;
    private RichSpacer s1;
    private RichSpacer s2;
    private RichPanelGroupLayout pgl256;
    private RichPanelBox pb_natural;
    private RichPanelFormLayout pfl1;
    private RichSelectOneChoice soc_nat_tipo_id;
    private UISelectItems si1;
    private RichInputText it_nat_numero_id;
    private RichInputText it_nat_nombres;
    private RichInputText it_nat_apellidos;
    private RichInputText it_nat_direccion;
    private RichSelectOneChoice soc_nat_depto;
    private UISelectItems si2;
    private RichSelectOneChoice soc_nat_municipio;
    private UISelectItems si3;
    private RichInputText it_nat_telefono;
    private RichInputText it_nat_email;
    private RichPanelGroupLayout pgl3;
    private RichPanelGroupLayout pgl4;
    private RichCommandButton cb_nat_aceptar;
    private RichSpacer s3;
    private RichPanelStretchLayout psl3;
    private RichPanelBox pb_predios;
    private RichPanelCollection pc1;
    private RichPanelCollection pc2p;
    private RichTree t1;
    private RichTree t2;
    private RichCommandImageLink cil1;
    private RichOutputText ot1;
    private RichOutputText otapellido;
    private RichCommandLink cl2;
    private RichCommandLink clPueaa;
    private RichSpacer s5;
    private RichCommandButton cb_nat_borrar;
    private RichMenu m_archivo;
    private RichCommandMenuItem cmi_adicionar_predio;
    private RichCommandMenuItem cmi_adicionar_pueaa;
    private RichCommandMenuItem cmi_adicionar_proyecto_pueaa;
    private RichPanelBox pb_juridico;
    private RichPanelGroupLayout pgl5;
    private RichDecorativeBox db1;
    private RichPanelFormLayout pfl2;
    private RichSelectOneChoice soc_jur_tipo_id;
    private UISelectItems si4;
    private RichInputText it1_jur_id;
    private RichInputText it1_jur_nombre;
    private RichInputText it_jur_direccion;
    private RichSelectOneChoice soc_jur_depto;
    private UISelectItems si5;
    private RichSelectOneChoice soc_jur_mun;
    private UISelectItems si6;
    private RichInputText it_jur_telefono;
    private RichInputText it_jur_email;
    private RichInputText it_jur_ciiu;
    private RichPanelGroupLayout pgl6;
    private RichInputText it_jur_ciiu_desc;
    private RichSpacer s7;
    private RichCommandButton cb_jur_buscar_ciiu;
    private RichOutputText ot3;
    private RichDecorativeBox db2;
    private RichPanelFormLayout pfl3;
    private RichPanelGroupLayout pgl7;
    private RichPanelGroupLayout pgl8;
    private RichCommandButton cb_jur_aceptar;
    private RichSpacer s9;
    private RichCommandButton cb_jur_borrar;
    private RichSelectOneChoice soc_rep_tipo_id;
    private UISelectItems si7;
    private RichInputText it_rep_id;
    private RichInputText it_rep_nombre;
    private RichInputText it_rep_apellidos;
    private RichInputText it_rep_direccion;
    private RichSelectOneChoice soc_rep_depto;
    private UISelectItems si8;
    private RichSelectOneChoice soc_rep_mun;
    private UISelectItems si9;
    private RichInputText it_rep_telefono;
    private RichInputText it_rep_email;
    private RichOutputText ot4;
    private RichPanelGroupLayout pgl9;
    private RichPanelGroupLayout pgl10;
    private RichSpacer s11;
    private RichSpacer s12;
    private RichPanelGroupLayout pgl11;
    private RichMessage m2;
    private RichPopup p_ciiu;
    private RichDialog d_ciiu;
    private RichPanelStretchLayout psl4;
    private RichPanelFormLayout pfl4;
    private RichMessage m3;
    private RichOutputLabel ol_mensaje;
    private RichOutputLabel ol_invisible;
    private UINamingContainer s4;
    private RichPanelBox pb_municipio;
    private RichPanelGroupLayout pgl12;
    private RichPanelGroupLayout pgl13;
    private RichDecorativeBox db4;
    private RichPanelGroupLayout pgl14;
    private RichSpacer s6;
    private RichOutputText ot5;
    private RichPanelFormLayout pfl5;
    private RichSelectOneChoice soc_mun_tipo_id;
    private UISelectItems si10;
    private RichInputText it_mun_id;
    private RichInputText it_mun_nombre;
    private RichInputText it_mun_direccion;
    private RichSelectOneChoice soc_mun_depto;
    private UISelectItems si11;
    private RichSelectOneChoice soc_mun_municipio;
    private UISelectItems si12;
    private RichInputText it_mun_telefono;
    private RichInputText it_mun_email;
    private RichDecorativeBox db5;
    private RichSpacer s8;
    private RichPanelGroupLayout pgl15;
    private RichPanelGroupLayout pgl16;
    private RichCommandButton cb_mun_guardar;
    private RichSpacer s13;
    private RichCommandButton cb_mun_borrar;
    private RichPanelGroupLayout pgl17;
    private RichSpacer s14;
    private RichOutputText ot6;
    private RichPanelFormLayout pfl6;
    private RichSelectOneChoice soc_mun_rep_tipo_id;
    private UISelectItems si13;
    private RichInputText it_mun_rep_id;
    private RichInputText it_mun_rep_nombre;
    private RichInputText it_mun_rep_apellido;
    private RichInputText it_mun_rep_direccion;
    private RichSelectOneChoice soc_mun_rep_depto;
    private UISelectItems si14;
    private RichSelectOneChoice soc_mun_rep_municipio;
    private UISelectItems si15;
    private RichInputText it_mun_rep_telefono;
    private RichInputText it_mun_rep_email;
    private RichPopup p_borrar;
    private RichDialog d_borrar;
    private RichPanelGroupLayout pgl18;
    private RichOutputText ot_borrar_1;
    private RichSpacer s15;
    private RichOutputText ot_borrar_2;
    private RichPanelGroupLayout pgl19;
    private RichCommandButton cb_si_borrar;
    private RichSpacer s16;
    private RichCommandButton cb_no_borrar;
    private RichSpacer s17;
    private RichSpacer s18;

    private RichSpacer s20;


    public DetalleUsuarioBean() {

        FacesUtils.setActiveBean("detalleUsuarioAgua", "detalleUsuarioAgua",
                                 DetalleUsuarioBean.class);

        FacesUtils.removeManagedBeanFromSession("AdicionarPueaaBean");
        FacesUtils.removeManagedBeanFromSession("back_adicionarProyecto");
        FacesUtils.removeManagedBeanFromSession("PueaaTreeHandler");
        this.load();
    }

    public void load() {

        try {
            UsuariosAguaDelegate uad = UsuariosAguaDelegate.getInstance();

            this.listaTiposDocumento =
                    this.cargarParametro(Categoria.TIPO_DOCUMENTO);
            this.listaDepartamentos = this.cargarDivipolaSinRestriccion(null);
            this.listaDeptosRepresentante =
                    this.cargarDivipolaSinRestriccion(null);

            Object obj = FacesUtils.getFromSession("usuarioSeleccionado");

            if (obj instanceof UsuarioAgua) {
                this.usuarioAgua = (UsuarioAgua)obj;
                Long codigoUsuario = this.usuarioAgua.getCodigo();
                System.out.println("usuarioAgua----------------" +
                                   codigoUsuario);


            } else if (obj instanceof UsuarioAguaTO) {
                this.usuarioAguaTo = (UsuarioAguaTO)obj;
                Long codigoUsuario = this.usuarioAguaTo.getCodigo();
                this.usuarioAgua = uad.getUsuarioAgua(codigoUsuario);
                System.out.println("usuarioAguaTo----------------" +
                                   codigoUsuario);

            } else {
                Long codigoUsuario =
                    (Long)FacesUtils.getFromSession("usuarioSeleccionado");
                System.out.println("usuarioSeleccionado es un ID----------------" +
                                   codigoUsuario);
                this.usuarioAgua = uad.getUsuarioAgua(codigoUsuario);
            }

            this.deptoSeleccionado = this.usuarioAgua.getDepartamento();
            this.municipioSeleccionado = this.usuarioAgua.getMunicipio();
            if (this.deptoSeleccionado != null) {
                this.listaMunicipios =
                        this.cargarDivipolaSinRestriccion(this.deptoSeleccionado.getId());
            } else {
                this.listaMunicipios = new ArrayList();
            }

            if (this.usuarioAgua.getTipoUsuario().getCodigo().intValue() ==
                1 ||
                this.usuarioAgua.getTipoUsuario().getCodigo().intValue() ==
                3 ||
                this.usuarioAgua.getTipoUsuario().getCodigo().intValue() ==
                4) {

                this.deptoRespresentnate =
                        this.usuarioAgua.getRepresentanteLegal().getDepartamento();

                this.listaMunicipiosRespresentante =
                        this.cargarDivipolaSinRestriccion(this.deptoRespresentnate.getId());

                this.municipioRepresentante =
                        this.usuarioAgua.getRepresentanteLegal().getMunicipio();

                this.deptoJuridicaSeleccionado =
                        this.usuarioAgua.getDepartamento();
                this.municipioJuridicaSeleccionado =
                        this.usuarioAgua.getMunicipio();

                if (this.usuarioAgua.getActividadCiiu() != null) {
                    this.codigoCiiu =
                            this.usuarioAgua.getActividadCiiu().getCodigo();
                    this.descripcionCiiu =
                            this.usuarioAgua.getActividadCiiu().getDescripcion();

                    this.actividadCiiu = this.usuarioAgua.getActividadCiiu();
                }
            }

            if (this.usuarioAgua.getTipoUsuario().getCodigo() == 2 ||
                this.usuarioAgua.getTipoUsuario().getCodigo() == 136 ||
                this.usuarioAgua.getTipoUsuario().getCodigo() == 956) {


                System.out.println("****************************************eeeeee******++++++" +
                                   this.usuarioAgua.getTipoUsuario().getCodigo());
                this.deptoRespresentnate =
                        this.usuarioAgua.getRepresentanteLegal().getDepartamento();
                System.out.println("****************************************dpto******++++++" +
                                   this.usuarioAgua.getRepresentanteLegal().getDepartamento());

                this.listaMunicipiosRespresentante =
                        this.cargarDivipola(this.deptoRespresentnate.getId());

                this.municipioRepresentante =
                        this.usuarioAgua.getRepresentanteLegal().getMunicipio();

                this.deptoMunicipioSeleccionado =
                        this.usuarioAgua.getDepartamento();
                this.municipioMunicipioSeleccionado =
                        this.usuarioAgua.getMunicipio();

            }

            List listaNodos = new ArrayList();

            listaPredios = uad.getPredios(this.usuarioAgua);
            if (listaPredios != null) {
                Iterator it = listaPredios.iterator();
                while (it.hasNext()) {
                    Predio predio = (Predio)it.next();

                    List listaConcesiones = uad.getConcesiones(predio);
                    predio.setListaConcesiones(listaConcesiones);

                    List listaPermisos = uad.getPermisosVertimiento(predio);
                    predio.setListaPermisos(listaPermisos);
                    FacesUtils.setInSession("predioSeleccionado", predio);
                    TreeNode nodoPredio =
                        new TreeNode(getText("PREDIO") + " " +
                                     predio.getNombre(), "Predio", true,
                                     false);
                    nodoPredio.setData(predio);
                    listaNodos.add(nodoPredio);

                    TreeNode nodoConcesiones =
                        new TreeNode(getText("CONCESIONES"), "Concesiones");
                    nodoConcesiones.setData("Concesiones");
                    nodoConcesiones.setExtraData(predio);
                    nodoPredio.getChildren().add(nodoConcesiones);
                    if (predio.getListaConcesiones() != null &&
                        predio.getListaConcesiones().size() > 0) {
                        Iterator itConcesiones =
                            predio.getListaConcesiones().iterator();
                        while (itConcesiones.hasNext()) {
                            Concesion con = (Concesion)itConcesiones.next();


                            TreeNode nodoConsesion =
                                new TreeNode(getText("NUMERO_DE_EXPEDIENTE") +
                                             "  " + con.getNumeroExpediente(),
                                             "Concesion", false, false);
                            nodoConsesion.setData(con);


                            nodoConsesion.setExtraData(predio);
                            nodoConsesion.setExtraData2(con);
                            nodoConcesiones.getChildren().add(nodoConsesion);
                        }
                    } else {
                        TreeNode nodoConsesion =
                            new TreeNode(getText("NO_HAY_REGISTROS"),
                                         "Concesion", false, true);
                        nodoConcesiones.getChildren().add(nodoConsesion);
                    }

                    TreeNode nodoPermisos =
                        new TreeNode(getText("PERMISOS_VERTIMIENTO"),
                                     "Permisos");
                    nodoPermisos.setData("Permisos");
                    nodoPermisos.setExtraData(predio);
                    nodoPredio.getChildren().add(nodoPermisos);
                    if (predio.getListaPermisos() != null &&
                        predio.getListaPermisos().size() > 0) {
                        Iterator itPermisos =
                            predio.getListaPermisos().iterator();
                        while (itPermisos.hasNext()) {
                            PermisoVertimiento per =
                                (PermisoVertimiento)itPermisos.next();
                            TreeNode nodoPermiso =
                                new TreeNode(getText("NUMERO_DE_EXPEDIENTE") +
                                             "  " + per.getNumeroExpediente(),
                                             "Permiso", false, false);
                            nodoPermiso.setData(per);
                            nodoPermiso.setExtraData(predio);
                            nodoPermiso.setExtraData2(per);
                            nodoPermisos.getChildren().add(nodoPermiso);
                        }
                    } else {
                        TreeNode nodoPermiso =
                            new TreeNode(getText("NO_HAY_REGISTROS"),
                                         "Permisos", false, true);
                        nodoPermisos.getChildren().add(nodoPermiso);
                    }

                }
            }

            prediosTreeModel = new SpecialTreeModel(listaNodos, "children");


            //Construir el arbol PUEAA.

            PueaaDelegate pueaD = PueaaDelegate.getInstance();
            listaPueaa = pueaD.getPueatPueaFindAll(this.usuarioAgua);
            TreeNode nodoPueaas = new TreeNode(getText("PUEAA"), "Pueaas");
            nodoPueaas.setData("Pueaas");
            listaNodos.add(nodoPueaas);


            if (listaPueaa != null) {
                Iterator it = listaPueaa.iterator();
                while (it.hasNext()) {
                    PueatPuea pueaa = (PueatPuea)it.next();


                    TreeNode nodoPueaa =
                        new TreeNode(getText("PUEAA") + " " + pueaa.getNumeroExpediente(),
                                     "Pueaa", true, false);
                    nodoPueaa.setData(pueaa);
                    nodoPueaas.getChildren().add(nodoPueaa);

                }
            } else {
                TreeNode nodoPueaa =
                    new TreeNode(getText("NO_HAY_REGISTROS"), "Pueaa", false,
                                 true);
                nodoPueaas.getChildren().add(nodoPueaa);
            }
            pueaaTreeModel = new SpecialTreeModel(listaNodos, "children");


        } catch (IdeamException e) {
            showMessage(e);
        }
        // create_tree_pueaa();
    }

    /**
     * Metodo que arma las listas y los tree de los pueaa y proyectos
     */
    /*  public void create_tree_pueaa(){

       try {

           // FacesUtils.setInSession("usuarioSeleccionado", this.usuarioAgua);
            PueaaDelegate pueaD;
            pueaD = PueaaDelegate.getInstance();


            //List listaNodosPueaa = new ArrayList();
            listaPueaa = pueaD.getPueatPueaFindAll(this.usuarioAgua);
            TreeNode nodoPueaas = new TreeNode(getText("PUEAA"),
                                               "Pueaas");
            nodoPueaas.setData("Pueaas");
            listaNodos.add(nodoPueaas);

               for(PueatPuea pueaa : listaPueaa) {

                    List listaProyectos = pueaD.getPueatProyectosFindByPueaa(pueaa);
                    pueaa.setPueatProyectosList(listaProyectos);

                    TreeNode nodoPueaa = new TreeNode(getText("PUEAA")+" "+pueaa.getNumeroExpediente(),
                                                       "Pueaa",
                                                       true,
                                                       false);
                    nodoPueaa.setData(pueaa);
                    nodoPueaas.getChildren().add(nodoPueaa);
                    //listaNodosPueaa.add(nodoPueaa);

                    TreeNode nodoProyectos = new TreeNode(getText("PROYECTOS"),
                                                       "Proyectos");
                    nodoProyectos.setData("Proyectos");
                    nodoProyectos.setExtraData(pueaa);
                    nodoPueaa.getChildren().add(nodoProyectos);

                     for(PueatProyectos proyecto : pueaa.getPueatProyectosList())      {
                            TreeNode nodoProyecto = new TreeNode(getText("NOMBRE_PROYECTO") + "  " +
                                                                  proyecto.getNombreProyecto(),
                                                                  "Proyecto",
                                                                  false,
                                                                  false);
                            nodoProyecto.setData(proyecto);
                            nodoProyecto.setExtraData(pueaa);
                            nodoProyecto.setExtraData2(proyecto);
                            nodoProyectos.getChildren().add(nodoProyecto);
                        }


                }
             //listaNodosPueaa.add(nodoPredio);
             pueaaTreeModel = new SpecialTreeModel(listaNodos, "children");


        } catch (IdeamException e) {
            showMessage(e);
        }

    }*/

    public void setF2(RichForm f2) {
        this.f2 = f2;
    }

    public RichForm getF2() {
        return f2;
    }

    public void setD2(RichDocument d2) {
        this.d2 = d2;
    }

    public RichDocument getD2() {
        return d2;
    }

    public void setPsl1(RichPanelStretchLayout psl1) {
        this.psl1 = psl1;
    }

    public RichPanelStretchLayout getPsl1() {
        return psl1;
    }

    public List getListaTiposDocumento() {
        return listaTiposDocumento;
    }

    public void setListaTiposDocumento(List listaTiposDocumento) {
        this.listaTiposDocumento = listaTiposDocumento;
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


    public void setPs1(RichPanelSplitter ps1) {
        this.ps1 = ps1;
    }

    public RichPanelSplitter getPs1() {
        return ps1;
    }

    public void setPsl2(RichPanelStretchLayout psl2) {
        this.psl2 = psl2;
    }

    public RichPanelStretchLayout getPsl2() {
        return psl2;
    }


    public void setCl1(RichCommandLink cl1) {
        this.cl1 = cl1;
    }

    public RichCommandLink getCl1() {
        return cl1;
    }

    public void setPgl1(RichPanelGroupLayout pgl1) {
        this.pgl1 = pgl1;
    }

    public RichPanelGroupLayout getPgl1() {
        return pgl1;
    }

    public void setS1(RichSpacer s1) {
        this.s1 = s1;
    }

    public RichSpacer getS1() {
        return s1;
    }


    public void setS2(RichSpacer s2) {
        this.s2 = s2;
    }

    public RichSpacer getS2() {
        return s2;
    }


    public void setPgl256(RichPanelGroupLayout pgl2) {
        this.pgl256 = pgl2;
    }

    public RichPanelGroupLayout getPgl256() {
        return pgl256;
    }

    public void setPb_natural(RichPanelBox pb1) {
        this.pb_natural = pb1;
    }

    public RichPanelBox getPb_natural() {
        return pb_natural;
    }

    public Divipola getMunicipioSeleccionado() {
        return municipioSeleccionado;
    }

    public void setMunicipioSeleccionado(Divipola municipioSeleccionado) {
        this.municipioSeleccionado = municipioSeleccionado;
    }


    public void setPfl1(RichPanelFormLayout pfl1) {
        this.pfl1 = pfl1;
    }

    public RichPanelFormLayout getPfl1() {
        return pfl1;
    }

    public void setSoc_nat_tipo_id(RichSelectOneChoice soc1) {
        this.soc_nat_tipo_id = soc1;
    }

    public RichSelectOneChoice getSoc_nat_tipo_id() {
        return soc_nat_tipo_id;
    }

    public void setSi1(UISelectItems si1) {
        this.si1 = si1;
    }

    public UISelectItems getSi1() {
        return si1;
    }

    public void setIt_nat_numero_id(RichInputText it1) {
        this.it_nat_numero_id = it1;
    }

    public RichInputText getIt_nat_numero_id() {
        return it_nat_numero_id;
    }

    public void setIt_nat_nombres(RichInputText it2) {
        this.it_nat_nombres = it2;
    }

    public RichInputText getIt_nat_nombres() {
        return it_nat_nombres;
    }

    public void setIt_nat_apellidos(RichInputText it3) {
        this.it_nat_apellidos = it3;
    }

    public RichInputText getIt_nat_apellidos() {
        return it_nat_apellidos;
    }

    public void setIt_nat_direccion(RichInputText it4) {
        this.it_nat_direccion = it4;
    }

    public RichInputText getIt_nat_direccion() {
        return it_nat_direccion;
    }

    public void setSoc_nat_depto(RichSelectOneChoice soc2) {
        this.soc_nat_depto = soc2;
    }

    public RichSelectOneChoice getSoc_nat_depto() {
        return soc_nat_depto;
    }

    public void setSi2(UISelectItems si2) {
        this.si2 = si2;
    }

    public UISelectItems getSi2() {
        return si2;
    }

    public void setSoc_nat_municipio(RichSelectOneChoice soc3) {
        this.soc_nat_municipio = soc3;
    }

    public RichSelectOneChoice getSoc_nat_municipio() {
        return soc_nat_municipio;
    }

    public void setSi3(UISelectItems si3) {
        this.si3 = si3;
    }

    public UISelectItems getSi3() {
        return si3;
    }

    public void setIt_nat_telefono(RichInputText it5) {
        this.it_nat_telefono = it5;
    }

    public RichInputText getIt_nat_telefono() {
        return it_nat_telefono;
    }

    public void setIt_nat_email(RichInputText it6) {
        this.it_nat_email = it6;
    }

    public RichInputText getIt_nat_email() {
        return it_nat_email;
    }

    public void setPgl3(RichPanelGroupLayout pgl3) {
        this.pgl3 = pgl3;
    }

    public RichPanelGroupLayout getPgl3() {
        return pgl3;
    }


    public void setPgl4(RichPanelGroupLayout pgl4) {
        this.pgl4 = pgl4;
    }

    public RichPanelGroupLayout getPgl4() {
        return pgl4;
    }

    public void setCb_nat_aceptar(RichCommandButton cb1) {
        this.cb_nat_aceptar = cb1;
    }

    public RichCommandButton getCb_nat_aceptar() {
        return cb_nat_aceptar;
    }

    public Divipola getDeptoSeleccionado() {
        return deptoSeleccionado;
    }

    public void setDeptoSeleccionado(Divipola deptoSeleccionado) {
        this.deptoSeleccionado = deptoSeleccionado;
    }

    public void setS3(RichSpacer s3) {
        this.s3 = s3;
    }

    public RichSpacer getS3() {
        return s3;
    }


    public void setPsl3(RichPanelStretchLayout psl3) {
        this.psl3 = psl3;
    }

    public RichPanelStretchLayout getPsl3() {
        return psl3;
    }

    public void setPb_predios(RichPanelBox pb1) {
        this.pb_predios = pb1;
    }

    public RichPanelBox getPb_predios() {
        return pb_predios;
    }

    public void setPc1(RichPanelCollection pc1) {
        this.pc1 = pc1;
    }

    public RichPanelCollection getPc1() {
        return pc1;
    }

    public List getListaPredios() {
        return listaPredios;
    }

    public void setListaPredios(List listaPredios) {
        this.listaPredios = listaPredios;
    }

    public TreeModel getPrediosTreeModel() {
        return prediosTreeModel;
    }

    public void setPrediosTreeModel(TreeModel prediosTreeModel) {
        this.prediosTreeModel = prediosTreeModel;
    }

    public void setT1(RichTree t1) {
        this.t1 = t1;
    }

    public RichTree getT1() {
        return t1;
    }


    public void setCil1(RichCommandImageLink cil1) {
        this.cil1 = cil1;
    }

    public RichCommandImageLink getCil1() {
        return cil1;
    }


    public void setOt1(RichOutputText ot1) {
        this.ot1 = ot1;
    }

    public RichOutputText getOt1() {
        return ot1;
    }

    public void setCl2(RichCommandLink cl2) {
        this.cl2 = cl2;
    }

    public RichCommandLink getCl2() {
        return cl2;
    }

    public void cl1_actionListener(ActionEvent actionEvent) {
        String nombreParametro =
            (String)actionEvent.getComponent().getAttributes().get("nombreParametro");
        Object data =
            actionEvent.getComponent().getAttributes().get("valorParametro");
        if (nombreParametro != null && data != null) {
            FacesUtils.setInSession(nombreParametro, data);
            if (data instanceof String) {
                if (data.toString().equals("Concesiones")) {
                    Object predio =
                        actionEvent.getComponent().getAttributes().get("extraInfo");
                    if (predio != null) {
                        FacesUtils.setInSession("predioSeleccionado", predio);
                    }
                }
                if (data.toString().equals("Permisos")) {
                    Object predio =
                        actionEvent.getComponent().getAttributes().get("extraInfo");
                    if (predio != null) {
                        FacesUtils.setInSession("predioSeleccionado", predio);
                    }
                }
            }

            if (data instanceof Concesion) {
                Object predio =
                    actionEvent.getComponent().getAttributes().get("extraInfo");
                if (predio != null) {
                    FacesUtils.setInSession("predioSeleccionado", predio);
                }

                Object concesion =
                    actionEvent.getComponent().getAttributes().get("extraInfo2");
                if (concesion != null) {
                    FacesUtils.setInSession("concesionSeleccionada",
                                            concesion);
                }

                FacesUtils.setInSession("paginaOrigen", "detalleUsuario");
            }

            if (data instanceof PermisoVertimiento) {
                Object predio =
                    actionEvent.getComponent().getAttributes().get("extraInfo");
                if (predio != null) {
                    FacesUtils.setInSession("predioSeleccionado", predio);
                }

                Object permiso =
                    actionEvent.getComponent().getAttributes().get("extraInfo2");
                if (permiso != null) {
                    FacesUtils.setInSession("permisoSeleccionado", permiso);
                }

                FacesUtils.setInSession("paginaOrigen", "detalleUsuario");
            }
        }
    }


    public void clPueaa_actionListener(ActionEvent actionEvent) {
        
        System.out.println("HCP entro a clPueaa_actionListener");
        FacesUtils.removeManagedBeanFromSession("adicionarPueaa");
        
        String nombreParametro =
            (String)actionEvent.getComponent().getAttributes().get("nombreParametro");
        Object data =
            actionEvent.getComponent().getAttributes().get("valorParametro");
        
        if (nombreParametro != null && data != null) {
            FacesUtils.setInSession(nombreParametro, data);
            if (data instanceof String) {
                if (data.toString().equals("Proyectos")) {
                    Object pueaa =
                        actionEvent.getComponent().getAttributes().get("extraInfo");
                    if (pueaa != null) {
                        FacesUtils.removeManagedBeanFromSession("adicionarPueaa");
                        FacesUtils.removeFromSession("pueaaSeleccionado");
                        System.out.println("HCP selecciono "+ pueaa);
                        FacesUtils.setInSession("pueaaSeleccionado", pueaa);
                    }
                }
                /*if(data.toString().equals("Permisos")){
                    Object predio =
                        actionEvent.getComponent().getAttributes().get("extraInfo");
                    if(predio!=null){
                        FacesUtils.setInSession("predioSeleccionado", predio);
                    }
                }  */
            }

            if (data instanceof PueatProyectos) {
                Object pueaa =
                    actionEvent.getComponent().getAttributes().get("extraInfo");
                if (pueaa != null) {
                    FacesUtils.removeManagedBeanFromSession("adicionarPueaa");
                    FacesUtils.removeFromSession("pueaaSeleccionado");
                    FacesUtils.setInSession("pueaaSeleccionado", pueaa);
                }

                Object proyecto =
                    actionEvent.getComponent().getAttributes().get("extraInfo2");
                if (proyecto != null) {
                    //FacesUtils.setInSession("proyectoSeleccionada", proyecto);
                    FacesUtils.setInSession("proyectoSeleccionado", proyecto);
                }

                FacesUtils.setInSession("paginaOrigen", "detalleUsuario");
            }

            /* if(data instanceof PermisoVertimiento){
                Object predio =
                    actionEvent.getComponent().getAttributes().get("extraInfo");
                if(predio!=null){
                    FacesUtils.setInSession("predioSeleccionado", predio);
                }

                Object permiso =
                    actionEvent.getComponent().getAttributes().get("extraInfo2");
                if(permiso!=null){
                    FacesUtils.setInSession("permisoSeleccionado", permiso);
                }

                FacesUtils.setInSession("paginaOrigen","detalleUsuario");
            }  */
        }
    }


    public void setS5(RichSpacer s5) {
        this.s5 = s5;
    }

    public RichSpacer getS5() {
        return s5;
    }

    public void setCb_nat_borrar(RichCommandButton cb2) {
        this.cb_nat_borrar = cb2;
    }

    public RichCommandButton getCb_nat_borrar() {
        return cb_nat_borrar;
    }


    public void setM_archivo(RichMenu m2) {
        this.m_archivo = m2;
    }

    public RichMenu getM_archivo() {
        return m_archivo;
    }

    public void setCmi_adicionar_predio(RichCommandMenuItem cmi2) {
        this.cmi_adicionar_predio = cmi2;
    }

    public RichCommandMenuItem getCmi_adicionar_predio() {
        return cmi_adicionar_predio;
    }

    //adicionar pueaa

    public void setCmi_adicionar_pueaa(RichCommandMenuItem cmi2) {
        this.cmi_adicionar_pueaa = cmi2;
    }

    public RichCommandMenuItem getCmi_adicionar_pueaa() {
        return cmi_adicionar_pueaa;
    }

    //proyecto


    public void cb_aceptar_actionListener(ActionEvent actionEvent) {
        // Validar campos obligatorios
        boolean continuar = true;
        if (soc_nat_tipo_id.getValue() == null) {
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                        soc_nat_tipo_id);
            continuar = false;
        }
        if (it_nat_numero_id.getValue() == null ||
            it_nat_numero_id.getValue().toString().length() == 0) {
            //showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,it_nat_numero_id);
            //continuar = false;
        }
        if (it_nat_nombres.getValue() == null ||
            it_nat_nombres.getValue().toString().length() == 0) {
            //showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,it_nat_nombres);
            //continuar = false;
        } else {
            NombreValidator val = new NombreValidator();
            val.validate(FacesContext.getCurrentInstance(),
                         this.it_nat_nombres, this.it_nat_nombres.getValue());
            this.it_nat_nombres.setValid(val.isValid());
            if (!val.isValid()) {
                continuar = false;
            }
        }
        if (it_nat_apellidos.getValue() == null ||
            it_nat_apellidos.getValue().toString().length() == 0) {
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                        it_nat_apellidos);
            continuar = false;
        } else {
            NombreValidator val = new NombreValidator();
            val.validate(FacesContext.getCurrentInstance(),
                         this.it_nat_apellidos,
                         this.it_nat_apellidos.getValue());
            this.it_nat_apellidos.setValid(val.isValid());
            if (!val.isValid()) {
                continuar = false;
            }
        }
        if (it_nat_direccion.getValue() == null ||
            it_nat_direccion.getValue().toString().length() == 0) {
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                        it_nat_direccion);
            continuar = false;
        }
        if (soc_nat_depto.getValue() == null) {
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                        soc_nat_depto);
            continuar = false;
        }
        if (soc_nat_municipio.getValue() == null) {
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                        soc_nat_municipio);
            continuar = false;
        }
        // Se elimina validacion por solicitud del linre tecnico
        /*
        if(it_nat_telefono.getValue()==null ||
           it_nat_telefono.getValue().toString().length()==0){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,it_nat_telefono);
            continuar = false;
        }                */
        if (it_nat_email.getValue() == null ||
            it_nat_email.getValue().toString().length() == 0) {
            //showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,it_nat_email);
            //continuar = false;
        } else {
            EmailValidator val = new EmailValidator();
            val.validate(FacesContext.getCurrentInstance(), it_nat_email,
                         it_nat_email.getValue());
            it_nat_email.setValid(val.isValid());
            if (!val.isValid()) {
                continuar = false;
            }
        }

        if (continuar) {
            this.usuarioAgua.setDepartamento(this.deptoSeleccionado);
            this.usuarioAgua.setMunicipio(this.municipioSeleccionado);
            this.usuarioAgua.setCodigoDepartamento(this.deptoSeleccionado.getId());
            this.usuarioAgua.setCodigoMunicipio(this.municipioSeleccionado.getId());
            guardar();
        }
    }

    private void guardar() {
        try {
            UsuariosAguaDelegate uad = UsuariosAguaDelegate.getInstance();

            //  Validar si existe otro usuario con el mismo tipo y numero de id
            UsuarioAgua existe =
                uad.getUsuario(this.usuarioAgua.getTipoDocumento().getCodigo(),
                               this.usuarioAgua.getNumeroDocumento(),
                               this.usuarioAgua.getAutoridadAmbiental().getId().longValue());
            if (existe != null &&
                existe.getCodigo().longValue() != this.usuarioAgua.getCodigo().longValue()) {
                showMessage(getText("EXISTE_USUARIO"),
                            FacesMessage.SEVERITY_ERROR);
                return;
            }

            uad.updateUsuario(this.usuarioAgua);

            try {

                /**
                 * Estas variables son necesarias para llenar los datos intrinsecos de la auditoria.
                 */
                UsuarioConectadoTO usuarioConectado =
                    (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");

                Auditoria auditoria = new Auditoria();
                auditoria.setIdUsuario(usuarioConectado.getUsuario().getCodigo());
                auditoria.setOperacion("MODIFICAR");
                auditoria.setObjeto("USUARIOS");
                auditoria.setMetodo(Thread.currentThread().getStackTrace()[1].getMethodName());
                auditoria.setClase(this.getClass().getName());
                //Objeto Afectado (usuario modificado, eliminado o agregado)
                auditoria.setIdObjeto(this.usuarioAgua.getCodigo());

                AuditoriasDelegate audDelegate =
                    AuditoriasDelegate.getInstance();
                audDelegate.setAuditoria(auditoria);

            } catch (Exception e) {
                System.out.println("No se pudo Almacenar la auditoria: ---------------------------------- ");
                System.out.println(e.getMessage() +
                                   "------------------------------------------------------ ");
            }


            String nombre = this.usuarioAgua.getNombre();

            nombre +=
                    this.usuarioAgua.getApellido() != null ? " " + this.usuarioAgua.getApellido() :
                    "";

            String params[] = { "del usuario " + nombre };
            showMessage(getText("mensaje_registro_exitoso", params),
                        FacesMessage.SEVERITY_INFO);
        } catch (IdeamException e) {
            showMessage(e);
        }
    }

    public void soc_nat_depto_valueChangeListener(ValueChangeEvent valueChangeEvent) {
        Object obj = valueChangeEvent.getNewValue();
        try {
            if (obj != null) {
                this.listaMunicipios =
                        this.cargarDivipolaSinRestriccion(((Divipola)obj).getId());
            } else {
                this.listaMunicipios = new ArrayList();
            }
            AdfFacesContext.getCurrentInstance().addPartialTarget(soc_nat_municipio);
            AdfFacesContext.getCurrentInstance().addPartialTarget(soc_jur_mun);
            AdfFacesContext.getCurrentInstance().addPartialTarget(soc_mun_municipio);
        } catch (IdeamException e) {
            showMessage(e);
        }
    }


    public List getListaDeptosRepresentante() {
        return listaDeptosRepresentante;
    }

    public void setListaDeptosRepresentante(List listaDeptosRepresentante) {
        this.listaDeptosRepresentante = listaDeptosRepresentante;
    }

    public List getListaMunicipiosRespresentante() {
        return listaMunicipiosRespresentante;
    }

    public void setListaMunicipiosRespresentante(List listaMunicipiosRespresentante) {
        this.listaMunicipiosRespresentante = listaMunicipiosRespresentante;
    }

    public Divipola getDeptoRespresentnate() {
        return deptoRespresentnate;
    }

    public void setDeptoRespresentnate(Divipola deptoRespresentnate) {
        this.deptoRespresentnate = deptoRespresentnate;
    }

    public Divipola getMunicipioRepresentante() {
        return municipioRepresentante;
    }

    public void setMunicipioRepresentante(Divipola municipioRepresentante) {
        this.municipioRepresentante = municipioRepresentante;
    }


    public void setPb_juridico(RichPanelBox pb2) {
        this.pb_juridico = pb2;
    }

    public RichPanelBox getPb_juridico() {
        return pb_juridico;
    }

    public void setPgl5(RichPanelGroupLayout pgl5) {
        this.pgl5 = pgl5;
    }

    public RichPanelGroupLayout getPgl5() {
        return pgl5;
    }

    public void setDb1(RichDecorativeBox db1) {
        this.db1 = db1;
    }

    public RichDecorativeBox getDb1() {
        return db1;
    }

    public void setPfl2(RichPanelFormLayout pfl2) {
        this.pfl2 = pfl2;
    }

    public RichPanelFormLayout getPfl2() {
        return pfl2;
    }


    public void setSoc_jur_tipo_id(RichSelectOneChoice soc7) {
        this.soc_jur_tipo_id = soc7;
    }

    public RichSelectOneChoice getSoc_jur_tipo_id() {
        return soc_jur_tipo_id;
    }

    public void setSi4(UISelectItems si4) {
        this.si4 = si4;
    }

    public UISelectItems getSi4() {
        return si4;
    }

    public void setIt1_jur_id(RichInputText it14) {
        this.it1_jur_id = it14;
    }

    public RichInputText getIt1_jur_id() {
        return it1_jur_id;
    }

    public void setIt1_jur_nombre(RichInputText it15) {
        this.it1_jur_nombre = it15;
    }

    public RichInputText getIt1_jur_nombre() {
        return it1_jur_nombre;
    }

    public void setIt_jur_direccion(RichInputText it16) {
        this.it_jur_direccion = it16;
    }

    public RichInputText getIt_jur_direccion() {
        return it_jur_direccion;
    }

    public void setSoc_jur_depto(RichSelectOneChoice soc8) {
        this.soc_jur_depto = soc8;
    }

    public RichSelectOneChoice getSoc_jur_depto() {
        return soc_jur_depto;
    }

    public void setSi5(UISelectItems si5) {
        this.si5 = si5;
    }

    public UISelectItems getSi5() {
        return si5;
    }

    public void setSoc_jur_mun(RichSelectOneChoice soc9) {
        this.soc_jur_mun = soc9;
    }

    public RichSelectOneChoice getSoc_jur_mun() {
        return soc_jur_mun;
    }

    public void setSi6(UISelectItems si6) {
        this.si6 = si6;
    }

    public UISelectItems getSi6() {
        return si6;
    }

    public void setIt_jur_telefono(RichInputText it17) {
        this.it_jur_telefono = it17;
    }

    public RichInputText getIt_jur_telefono() {
        return it_jur_telefono;
    }

    public void setIt_jur_email(RichInputText it18) {
        this.it_jur_email = it18;
    }

    public RichInputText getIt_jur_email() {
        return it_jur_email;
    }

    public void setIt_jur_ciiu(RichInputText it19) {
        this.it_jur_ciiu = it19;
    }

    public RichInputText getIt_jur_ciiu() {
        return it_jur_ciiu;
    }

    public void setPgl6(RichPanelGroupLayout pgl6) {
        this.pgl6 = pgl6;
    }

    public RichPanelGroupLayout getPgl6() {
        return pgl6;
    }

    public void setIt_jur_ciiu_desc(RichInputText it20) {
        this.it_jur_ciiu_desc = it20;
    }

    public RichInputText getIt_jur_ciiu_desc() {
        return it_jur_ciiu_desc;
    }

    public void setS7(RichSpacer s7) {
        this.s7 = s7;
    }

    public RichSpacer getS7() {
        return s7;
    }

    public void setCb_jur_buscar_ciiu(RichCommandButton cb1) {
        this.cb_jur_buscar_ciiu = cb1;
    }

    public RichCommandButton getCb_jur_buscar_ciiu() {
        return cb_jur_buscar_ciiu;
    }

    public void setOt3(RichOutputText ot3) {
        this.ot3 = ot3;
    }

    public RichOutputText getOt3() {
        return ot3;
    }

    public void setDb2(RichDecorativeBox db2) {
        this.db2 = db2;
    }

    public RichDecorativeBox getDb2() {
        return db2;
    }

    public void setPfl3(RichPanelFormLayout pfl3) {
        this.pfl3 = pfl3;
    }

    public RichPanelFormLayout getPfl3() {
        return pfl3;
    }

    public void setPgl7(RichPanelGroupLayout pgl7) {
        this.pgl7 = pgl7;
    }

    public RichPanelGroupLayout getPgl7() {
        return pgl7;
    }


    public void setPgl8(RichPanelGroupLayout pgl8) {
        this.pgl8 = pgl8;
    }

    public RichPanelGroupLayout getPgl8() {
        return pgl8;
    }

    public void setCb_jur_aceptar(RichCommandButton cb5) {
        this.cb_jur_aceptar = cb5;
    }

    public RichCommandButton getCb_jur_aceptar() {
        return cb_jur_aceptar;
    }

    public void setS9(RichSpacer s9) {
        this.s9 = s9;
    }

    public RichSpacer getS9() {
        return s9;
    }

    public void setCb_jur_borrar(RichCommandButton cb6) {
        this.cb_jur_borrar = cb6;
    }

    public RichCommandButton getCb_jur_borrar() {
        return cb_jur_borrar;
    }

    public void setSoc_rep_tipo_id(RichSelectOneChoice soc10) {
        this.soc_rep_tipo_id = soc10;
    }

    public RichSelectOneChoice getSoc_rep_tipo_id() {
        return soc_rep_tipo_id;
    }

    public void setSi7(UISelectItems si7) {
        this.si7 = si7;
    }

    public UISelectItems getSi7() {
        return si7;
    }

    public void setIt_rep_id(RichInputText it21) {
        this.it_rep_id = it21;
    }

    public RichInputText getIt_rep_id() {
        return it_rep_id;
    }

    public void setIt_rep_nombre(RichInputText it22) {
        this.it_rep_nombre = it22;
    }

    public RichInputText getIt_rep_nombre() {
        return it_rep_nombre;
    }

    public void setIt_rep_apellidos(RichInputText it23) {
        this.it_rep_apellidos = it23;
    }

    public RichInputText getIt_rep_apellidos() {
        return it_rep_apellidos;
    }

    public void setIt_rep_direccion(RichInputText it24) {
        this.it_rep_direccion = it24;
    }

    public RichInputText getIt_rep_direccion() {
        return it_rep_direccion;
    }

    public void setSoc_rep_depto(RichSelectOneChoice soc11) {
        this.soc_rep_depto = soc11;
    }

    public RichSelectOneChoice getSoc_rep_depto() {
        return soc_rep_depto;
    }

    public void setSi8(UISelectItems si8) {
        this.si8 = si8;
    }

    public UISelectItems getSi8() {
        return si8;
    }

    public void setSoc_rep_mun(RichSelectOneChoice soc12) {
        this.soc_rep_mun = soc12;
    }

    public RichSelectOneChoice getSoc_rep_mun() {
        return soc_rep_mun;
    }

    public void setSi9(UISelectItems si9) {
        this.si9 = si9;
    }

    public UISelectItems getSi9() {
        return si9;
    }

    public void setIt_rep_telefono(RichInputText it25) {
        this.it_rep_telefono = it25;
    }

    public RichInputText getIt_rep_telefono() {
        return it_rep_telefono;
    }

    public void setIt_rep_email(RichInputText it26) {
        this.it_rep_email = it26;
    }

    public RichInputText getIt_rep_email() {
        return it_rep_email;
    }

    public void setOt4(RichOutputText ot4) {
        this.ot4 = ot4;
    }

    public RichOutputText getOt4() {
        return ot4;
    }

    public String getCodigoCiiu() {
        return codigoCiiu;
    }

    public void setCodigoCiiu(String codigoCiiu) {
        this.codigoCiiu = codigoCiiu;
    }

    public String getDescripcionCiiu() {
        return descripcionCiiu;
    }

    public void setDescripcionCiiu(String descripcionCiiu) {
        this.descripcionCiiu = descripcionCiiu;
    }

    public void setPgl9(RichPanelGroupLayout pgl9) {
        this.pgl9 = pgl9;
    }

    public RichPanelGroupLayout getPgl9() {
        return pgl9;
    }


    public void setPgl10(RichPanelGroupLayout pgl10) {
        this.pgl10 = pgl10;
    }

    public RichPanelGroupLayout getPgl10() {
        return pgl10;
    }

    public void setS11(RichSpacer s11) {
        this.s11 = s11;
    }

    public RichSpacer getS11() {
        return s11;
    }


    public void setS12(RichSpacer s12) {
        this.s12 = s12;
    }

    public RichSpacer getS12() {
        return s12;
    }

    public void setPgl11(RichPanelGroupLayout pgl11) {
        this.pgl11 = pgl11;
    }

    public RichPanelGroupLayout getPgl11() {
        return pgl11;
    }


    public ActividadCIIU getActividadCiiu() {
        return actividadCiiu;
    }

    public void setActividadCiiu(ActividadCIIU actividadCiiu) {
        this.actividadCiiu = actividadCiiu;
    }

    public void cb_jur_aceptar_actionListener(ActionEvent actionEvent) {
        boolean continuar = true;
        if (soc_jur_tipo_id.getValue() == null) {
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                        soc_jur_tipo_id);
            continuar = false;
        }
        if (it1_jur_id.getValue() == null ||
            it1_jur_id.getValue().toString().length() == 0) {
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                        it1_jur_id);
            continuar = false;
        }
        if (it1_jur_nombre.getValue() == null ||
            it1_jur_nombre.getValue().toString().length() == 0) {
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                        it1_jur_nombre);
            continuar = false;
        }
        if (it_jur_direccion.getValue() == null ||
            it_jur_direccion.getValue().toString().length() == 0) {
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                        it_jur_direccion);
            continuar = false;
        }
        if (soc_jur_depto.getValue() == null) {
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                        soc_jur_depto);
            continuar = false;
        }
        if (soc_jur_mun.getValue() == null) {
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                        soc_jur_mun);
            continuar = false;
        }
        /*
        if(it_jur_telefono.getValue()==null ||
           it_jur_telefono.getValue().toString().length()==0){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,it_jur_telefono);
            continuar = false;
        } */
        if (it_jur_email.getValue() == null ||
            it_jur_email.getValue().toString().length() == 0) {
            //showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,it_jur_email);
            //continuar = false;
        } else {
            EmailValidator val = new EmailValidator();
            val.validate(FacesContext.getCurrentInstance(), it_jur_email,
                         it_jur_email.getValue());
            it_jur_email.setValid(val.isValid());
            if (!val.isValid()) {
                continuar = false;
            }
        }
        if (this.actividadCiiu == null) {
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                        it_jur_ciiu);
            continuar = false;
        }
        // Validar el representante legal
        if (soc_rep_tipo_id.getValue() == null) {
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                        soc_rep_tipo_id);
            continuar = false;
        }

        if (it_rep_id.getValue() == null ||
            it_rep_id.getValue().toString().length() == 0) {
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR, it_rep_id);
            continuar = false;
        }
        if (it_rep_nombre.getValue() == null ||
            it_rep_nombre.getValue().toString().length() == 0) {
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                        it_rep_nombre);
            continuar = false;
        } else {
            NombreValidator val = new NombreValidator();
            val.validate(FacesContext.getCurrentInstance(), this.it_rep_nombre,
                         this.it_rep_nombre.getValue());
            this.it_rep_nombre.setValid(val.isValid());
            if (!val.isValid()) {
                continuar = false;
            }
        }
        if (it_rep_apellidos.getValue() == null ||
            it_rep_apellidos.getValue().toString().length() == 0) {
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                        it_rep_apellidos);
            continuar = false;
        } else {
            NombreValidator val = new NombreValidator();
            val.validate(FacesContext.getCurrentInstance(),
                         this.it_rep_apellidos,
                         this.it_rep_apellidos.getValue());
            this.it_rep_apellidos.setValid(val.isValid());
            if (!val.isValid()) {
                continuar = false;
            }
        }
        if (it_rep_direccion.getValue() == null ||
            it_rep_direccion.getValue().toString().length() == 0) {
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                        it_rep_direccion);
            continuar = false;
        }
        if (soc_rep_depto.getValue() == null) {
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                        soc_rep_depto);
            continuar = false;
        }
        if (soc_rep_mun.getValue() == null) {
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                        soc_rep_mun);
            continuar = false;
        }
        /*
        if(it_rep_telefono.getValue()==null ||
           it_rep_telefono.getValue().toString().length()==0){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,it_rep_telefono);
            continuar = false;
        } */
        if (it_rep_email.getValue() == null ||
            it_rep_email.getValue().toString().length() == 0) {
            //showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,it_rep_email);
            //continuar = false;
        } else {
            EmailValidator val = new EmailValidator();
            val.validate(FacesContext.getCurrentInstance(), it_rep_email,
                         it_rep_email.getValue());
            it_rep_email.setValid(val.isValid());
            if (!val.isValid()) {
                continuar = false;
            }
        }
        if (continuar) {
            this.usuarioAgua.setDepartamento(this.deptoJuridicaSeleccionado);
            this.usuarioAgua.setMunicipio(this.municipioJuridicaSeleccionado);

            this.usuarioAgua.setCodigoDepartamento(this.deptoJuridicaSeleccionado.getId());
            this.usuarioAgua.setCodigoMunicipio(this.municipioJuridicaSeleccionado.getId());

            this.usuarioAgua.getRepresentanteLegal().setDepartamento(deptoRespresentnate);
            this.usuarioAgua.getRepresentanteLegal().setMunicipio(municipioRepresentante);

            this.usuarioAgua.getRepresentanteLegal().setCodigoDepartamento(deptoRespresentnate.getId());
            this.usuarioAgua.getRepresentanteLegal().setCodigoMunicipio(municipioRepresentante.getId());
            guardar();
        }
    }

    public void setM2(RichMessage m2) {
        this.m2 = m2;
    }

    public RichMessage getM2() {
        return m2;
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

    public void setPsl4(RichPanelStretchLayout psl4) {
        this.psl4 = psl4;
    }

    public RichPanelStretchLayout getPsl4() {
        return psl4;
    }

    public void setPfl4(RichPanelFormLayout pfl4) {
        this.pfl4 = pfl4;
    }

    public RichPanelFormLayout getPfl4() {
        return pfl4;
    }

    public void setM3(RichMessage m3) {
        this.m3 = m3;
    }

    public RichMessage getM3() {
        return m3;
    }

    public void setOl_mensaje(RichOutputLabel ol1) {
        this.ol_mensaje = ol1;
    }

    public RichOutputLabel getOl_mensaje() {
        return ol_mensaje;
    }

    public void setOl_invisible(RichOutputLabel ol2) {
        this.ol_invisible = ol2;
    }

    public RichOutputLabel getOl_invisible() {
        return ol_invisible;
    }

    public void setS4(UINamingContainer s4) {
        this.s4 = s4;
    }

    public UINamingContainer getS4() {
        return s4;
    }

    public void cb_jur_buscar_ciiu_actionListener(ActionEvent actionEvent) {
        Object obj = FacesUtils.getManagedBeanValue("buscarCiiu");
        if (obj != null) {
            ((BuscarCiiuBean)obj).inicialize();
        }
        AdfFacesContext.getCurrentInstance().addPartialTarget(p_ciiu);
        showPopup(p_ciiu, true);
    }

    public void d_ciiu_dialogListener(DialogEvent dialogEvent) {
        DialogEvent.Outcome oc = dialogEvent.getOutcome();
        if (oc == DialogEvent.Outcome.yes) {
            Object obj = FacesUtils.getManagedBeanValue("buscarCiiu");
            BuscarCiiuBean ciiuBean = (BuscarCiiuBean)obj;
            try {
                ciiuBean.buscar();

                showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                            ol_invisible);
            } catch (IdeamException e) {
                showMessage(e.getMessage(), FacesMessage.SEVERITY_ERROR,
                            ol_mensaje);
                //showMessage(e);
            }
        }
        if (oc == DialogEvent.Outcome.no) {
            Object obj = FacesUtils.getManagedBeanValue("buscarCiiu");
            BuscarCiiuBean ciiuBean = (BuscarCiiuBean)obj;
            try {
                ActividadCIIU ciiuSelecciona = ciiuBean.getData();
                this.actividadCiiu = ciiuSelecciona;
                this.usuarioAgua.setActividadCiiu(ciiuSelecciona);
                this.usuarioAgua.setCodigoActividadCiiu(ciiuSelecciona.getId());
                this.codigoCiiu = ciiuSelecciona.getCodigo();
                this.descripcionCiiu = ciiuSelecciona.getDescripcion();
                AdfFacesContext.getCurrentInstance().addPartialTarget(it_jur_ciiu);
                AdfFacesContext.getCurrentInstance().addPartialTarget(it_jur_ciiu_desc);

                FacesUtils.removeManagedBeanFromSession("buscarCiiu");
                showPopup(p_ciiu, false);
            } catch (IdeamException e) {
                showMessage(e.getMessage(), FacesMessage.SEVERITY_ERROR,
                            ol_mensaje);
            }

        }
    }

    public void setPb_municipio(RichPanelBox pb1) {
        this.pb_municipio = pb1;
    }

    public RichPanelBox getPb_municipio() {
        return pb_municipio;
    }

    public void setPgl12(RichPanelGroupLayout pgl12) {
        this.pgl12 = pgl12;
    }

    public RichPanelGroupLayout getPgl12() {
        return pgl12;
    }

    public void setPgl13(RichPanelGroupLayout pgl13) {
        this.pgl13 = pgl13;
    }

    public RichPanelGroupLayout getPgl13() {
        return pgl13;
    }

    public void setDb4(RichDecorativeBox db4) {
        this.db4 = db4;
    }

    public RichDecorativeBox getDb4() {
        return db4;
    }

    public void setPgl14(RichPanelGroupLayout pgl14) {
        this.pgl14 = pgl14;
    }

    public RichPanelGroupLayout getPgl14() {
        return pgl14;
    }

    public void setS6(RichSpacer s6) {
        this.s6 = s6;
    }

    public RichSpacer getS6() {
        return s6;
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

    public void setSoc_mun_tipo_id(RichSelectOneChoice soc1) {
        this.soc_mun_tipo_id = soc1;
    }

    public RichSelectOneChoice getSoc_mun_tipo_id() {
        return soc_mun_tipo_id;
    }

    public void setSi10(UISelectItems si10) {
        this.si10 = si10;
    }

    public UISelectItems getSi10() {
        return si10;
    }

    public void setIt_mun_id(RichInputText it1) {
        this.it_mun_id = it1;
    }

    public RichInputText getIt_mun_id() {
        return it_mun_id;
    }

    public void setIt_mun_nombre(RichInputText it2) {
        this.it_mun_nombre = it2;
    }

    public RichInputText getIt_mun_nombre() {
        return it_mun_nombre;
    }

    public void setIt_mun_direccion(RichInputText it3) {
        this.it_mun_direccion = it3;
    }

    public RichInputText getIt_mun_direccion() {
        return it_mun_direccion;
    }

    public void setSoc_mun_depto(RichSelectOneChoice soc2) {
        this.soc_mun_depto = soc2;
    }

    public RichSelectOneChoice getSoc_mun_depto() {
        return soc_mun_depto;
    }

    public void setSi11(UISelectItems si11) {
        this.si11 = si11;
    }

    public UISelectItems getSi11() {
        return si11;
    }

    public void setSoc_mun_municipio(RichSelectOneChoice soc3) {
        this.soc_mun_municipio = soc3;
    }

    public RichSelectOneChoice getSoc_mun_municipio() {
        return soc_mun_municipio;
    }

    public void setSi12(UISelectItems si12) {
        this.si12 = si12;
    }

    public UISelectItems getSi12() {
        return si12;
    }

    public void setIt_mun_telefono(RichInputText it4) {
        this.it_mun_telefono = it4;
    }

    public RichInputText getIt_mun_telefono() {
        return it_mun_telefono;
    }


    public void setIt_mun_email(RichInputText it6) {
        this.it_mun_email = it6;
    }

    public RichInputText getIt_mun_email() {
        return it_mun_email;
    }

    public void setDb5(RichDecorativeBox db5) {
        this.db5 = db5;
    }

    public RichDecorativeBox getDb5() {
        return db5;
    }

    public void setS8(RichSpacer s8) {
        this.s8 = s8;
    }

    public RichSpacer getS8() {
        return s8;
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

    public void setCb_mun_guardar(RichCommandButton cb1) {
        this.cb_mun_guardar = cb1;
    }

    public RichCommandButton getCb_mun_guardar() {
        return cb_mun_guardar;
    }

    public void setS13(RichSpacer s13) {
        this.s13 = s13;
    }

    public RichSpacer getS13() {
        return s13;
    }

    public void setCb_mun_borrar(RichCommandButton cb2) {
        this.cb_mun_borrar = cb2;
    }

    public RichCommandButton getCb_mun_borrar() {
        return cb_mun_borrar;
    }

    public void setPgl17(RichPanelGroupLayout pgl17) {
        this.pgl17 = pgl17;
    }

    public RichPanelGroupLayout getPgl17() {
        return pgl17;
    }

    public void setS14(RichSpacer s14) {
        this.s14 = s14;
    }

    public RichSpacer getS14() {
        return s14;
    }

    public void setOt6(RichOutputText ot6) {
        this.ot6 = ot6;
    }

    public RichOutputText getOt6() {
        return ot6;
    }

    public void setPfl6(RichPanelFormLayout pfl6) {
        this.pfl6 = pfl6;
    }

    public RichPanelFormLayout getPfl6() {
        return pfl6;
    }

    public void setSoc_mun_rep_tipo_id(RichSelectOneChoice soc4) {
        this.soc_mun_rep_tipo_id = soc4;
    }

    public RichSelectOneChoice getSoc_mun_rep_tipo_id() {
        return soc_mun_rep_tipo_id;
    }

    public void setSi13(UISelectItems si13) {
        this.si13 = si13;
    }

    public UISelectItems getSi13() {
        return si13;
    }

    public void setIt_mun_rep_id(RichInputText it7) {
        this.it_mun_rep_id = it7;
    }

    public RichInputText getIt_mun_rep_id() {
        return it_mun_rep_id;
    }

    public void setIt_mun_rep_nombre(RichInputText it8) {
        this.it_mun_rep_nombre = it8;
    }

    public RichInputText getIt_mun_rep_nombre() {
        return it_mun_rep_nombre;
    }

    public void setIt_mun_rep_apellido(RichInputText it9) {
        this.it_mun_rep_apellido = it9;
    }

    public RichInputText getIt_mun_rep_apellido() {
        return it_mun_rep_apellido;
    }

    public void setIt_mun_rep_direccion(RichInputText it10) {
        this.it_mun_rep_direccion = it10;
    }

    public RichInputText getIt_mun_rep_direccion() {
        return it_mun_rep_direccion;
    }

    public void setSoc_mun_rep_depto(RichSelectOneChoice soc5) {
        this.soc_mun_rep_depto = soc5;
    }

    public RichSelectOneChoice getSoc_mun_rep_depto() {
        return soc_mun_rep_depto;
    }

    public void setSi14(UISelectItems si14) {
        this.si14 = si14;
    }

    public UISelectItems getSi14() {
        return si14;
    }

    public void setSoc_mun_rep_municipio(RichSelectOneChoice soc6) {
        this.soc_mun_rep_municipio = soc6;
    }

    public RichSelectOneChoice getSoc_mun_rep_municipio() {
        return soc_mun_rep_municipio;
    }

    public void setSi15(UISelectItems si15) {
        this.si15 = si15;
    }

    public UISelectItems getSi15() {
        return si15;
    }

    public void setIt_mun_rep_telefono(RichInputText it11) {
        this.it_mun_rep_telefono = it11;
    }

    public RichInputText getIt_mun_rep_telefono() {
        return it_mun_rep_telefono;
    }

    public void setIt_mun_rep_email(RichInputText it12) {
        this.it_mun_rep_email = it12;
    }

    public RichInputText getIt_mun_rep_email() {
        return it_mun_rep_email;
    }

    public void cb_mun_guardar_actionListener(ActionEvent actionEvent) {
        boolean continuar = true;
        if (soc_mun_tipo_id.getValue() == null) {
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                        soc_mun_tipo_id);
            continuar = false;
        }
        if (it_mun_id.getValue() == null ||
            it_mun_id.getValue().toString().length() == 0) {
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR, it_mun_id);
            continuar = false;
        }
        if (it_mun_nombre.getValue() == null ||
            it_mun_nombre.getValue().toString().length() == 0) {
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                        it_mun_nombre);
            continuar = false;
        }
        if (it_mun_direccion.getValue() == null ||
            it_mun_direccion.getValue().toString().length() == 0) {
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                        it_mun_direccion);
            continuar = false;
        }
        if (soc_mun_depto.getValue() == null) {
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                        soc_mun_depto);
            continuar = false;
        }
        if (soc_mun_municipio.getValue() == null) {
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                        soc_mun_municipio);
            continuar = false;
        }
        /*
        if(it_mun_telefono.getValue()==null ||
           it_mun_telefono.getValue().toString().length()==0){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,it_mun_telefono);
            continuar = false;
        }          */
        if (it_mun_email.getValue() == null ||
            it_mun_email.getValue().toString().length() == 0) {
            //showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,it_mun_email);
            //continuar = false;
        } else {
            EmailValidator val = new EmailValidator();
            val.validate(FacesContext.getCurrentInstance(), it_mun_email,
                         it_mun_email.getValue());
            it_mun_email.setValid(val.isValid());
            if (!val.isValid()) {
                continuar = false;
            }
        }
        // Validar el representante legal
        if (soc_mun_rep_tipo_id.getValue() == null) {
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                        soc_mun_rep_tipo_id);
            continuar = false;
        }

        if (it_mun_rep_id.getValue() == null ||
            it_mun_rep_id.getValue().toString().length() == 0) {
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                        it_mun_rep_id);
            continuar = false;
        }
        if (it_mun_rep_nombre.getValue() == null ||
            it_mun_rep_nombre.getValue().toString().length() == 0) {
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                        it_mun_rep_nombre);
            continuar = false;
        } else {
            NombreValidator val = new NombreValidator();
            val.validate(FacesContext.getCurrentInstance(),
                         this.it_mun_rep_nombre,
                         this.it_mun_rep_nombre.getValue());
            this.it_mun_rep_nombre.setValid(val.isValid());
            if (!val.isValid()) {
                continuar = false;
            }
        }
        if (it_mun_rep_apellido.getValue() == null ||
            it_mun_rep_apellido.getValue().toString().length() == 0) {
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                        it_mun_rep_apellido);
            continuar = false;
        } else {
            NombreValidator val = new NombreValidator();
            val.validate(FacesContext.getCurrentInstance(),
                         this.it_mun_rep_apellido,
                         this.it_mun_rep_apellido.getValue());
            this.it_mun_rep_apellido.setValid(val.isValid());
            if (!val.isValid()) {
                continuar = false;
            }
        }
        if (it_mun_rep_direccion.getValue() == null ||
            it_mun_rep_direccion.getValue().toString().length() == 0) {
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                        it_mun_rep_direccion);
            continuar = false;
        }
        if (soc_mun_rep_depto.getValue() == null) {
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                        soc_mun_rep_depto);
            continuar = false;
        }
        if (soc_mun_rep_municipio.getValue() == null) {
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                        soc_mun_rep_municipio);
            continuar = false;
        }
        /*
        if(it_mun_rep_telefono.getValue()==null ||
           it_mun_rep_telefono.getValue().toString().length()==0){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,it_mun_rep_telefono);
            continuar = false;
        }   */
        if (it_mun_rep_email.getValue() == null ||
            it_mun_rep_email.getValue().toString().length() == 0) {
            //showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,it_mun_rep_email);
            //continuar = false;
        } else {
            EmailValidator val = new EmailValidator();
            val.validate(FacesContext.getCurrentInstance(), it_mun_rep_email,
                         it_mun_rep_email.getValue());
            it_mun_rep_email.setValid(val.isValid());
            if (!val.isValid()) {
                continuar = false;
            }
        }
        if (continuar) {
            this.usuarioAgua.setDepartamento(this.deptoMunicipioSeleccionado);
            this.usuarioAgua.setMunicipio(this.municipioMunicipioSeleccionado);

            this.usuarioAgua.setCodigoDepartamento(this.deptoMunicipioSeleccionado.getId());
            this.usuarioAgua.setCodigoMunicipio(this.municipioMunicipioSeleccionado.getId());

            this.usuarioAgua.getRepresentanteLegal().setDepartamento(deptoRespresentnate);
            this.usuarioAgua.getRepresentanteLegal().setMunicipio(municipioRepresentante);

            this.usuarioAgua.getRepresentanteLegal().setCodigoDepartamento(deptoRespresentnate.getId());
            this.usuarioAgua.getRepresentanteLegal().setCodigoMunicipio(municipioRepresentante.getId());
            guardar();
        }
    }

    public void setP_borrar(RichPopup p1) {
        this.p_borrar = p1;
    }

    public RichPopup getP_borrar() {
        return p_borrar;
    }

    public void setD_borrar(RichDialog d1) {
        this.d_borrar = d1;
    }

    public RichDialog getD_borrar() {
        return d_borrar;
    }

    public void setPgl18(RichPanelGroupLayout pgl18) {
        this.pgl18 = pgl18;
    }

    public RichPanelGroupLayout getPgl18() {
        return pgl18;
    }

    public void setOt_borrar_1(RichOutputText ot7) {
        this.ot_borrar_1 = ot7;
    }

    public RichOutputText getOt_borrar_1() {
        return ot_borrar_1;
    }

    public void setS15(RichSpacer s15) {
        this.s15 = s15;
    }

    public RichSpacer getS15() {
        return s15;
    }

    public void setOt_borrar_2(RichOutputText ot8) {
        this.ot_borrar_2 = ot8;
    }

    public RichOutputText getOt_borrar_2() {
        return ot_borrar_2;
    }

    public void cb_nat_borrar_actionListener(ActionEvent actionEvent) {
        String nombreUsuario = this.usuarioAgua.getNombre();
        nombreUsuario +=
                this.usuarioAgua.getApellido() != null ? " " + this.usuarioAgua.getApellido() :
                "";

        String params[] = { nombreUsuario };
        String texto = getText("MENSAJE_BORRAR_USUARIO", params);
        ot_borrar_1.setValue(texto);
        ot_borrar_2.setValue("");

        if (listaPredios != null && listaPredios.size() > 0) {
            String params2[] = { "" + listaPredios.size() };
            String texto2 =
                getText("MENSAJE_BORRAR_USUARIO_DETALLE_PREDIO", params2);
            ot_borrar_2.setValue(texto2);
        }
        AdfFacesContext.getCurrentInstance().addPartialTarget(p_borrar);


        showPopup(p_borrar, true);
    }


    public void setPgl19(RichPanelGroupLayout pgl19) {
        this.pgl19 = pgl19;
    }

    public RichPanelGroupLayout getPgl19() {
        return pgl19;
    }

    public void setCb_si_borrar(RichCommandButton cb1) {
        this.cb_si_borrar = cb1;
    }

    public RichCommandButton getCb_si_borrar() {
        return cb_si_borrar;
    }

    public void setS16(RichSpacer s16) {
        this.s16 = s16;
    }

    public RichSpacer getS16() {
        return s16;
    }

    public void setCb_no_borrar(RichCommandButton cb2) {
        this.cb_no_borrar = cb2;
    }

    public RichCommandButton getCb_no_borrar() {
        return cb_no_borrar;
    }

    public void cb_si_borrar_actionListener(ActionEvent actionEvent) {
        setAccionBorrar("");
        try {
            UsuariosAguaDelegate uad = UsuariosAguaDelegate.getInstance();
            uad.deleteUsuario(this.usuarioAgua);

            try {

                /**
                 * Estas variables son necesarias para llenar los datos intrinsecos de la auditoria.
                 */
                UsuarioConectadoTO usuarioConectado =
                    (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");

                Auditoria auditoria = new Auditoria();
                auditoria.setIdUsuario(usuarioConectado.getUsuario().getCodigo());
                auditoria.setOperacion("BORRAR");
                auditoria.setObjeto("USUARIOS");
                auditoria.setMetodo(Thread.currentThread().getStackTrace()[1].getMethodName());
                auditoria.setClase(this.getClass().getName());
                //Objeto Afectado (usuario modificado, eliminado o agregado)
                auditoria.setIdObjeto(this.usuarioAgua.getCodigo());

                AuditoriasDelegate audDelegate =
                    AuditoriasDelegate.getInstance();
                audDelegate.setAuditoria(auditoria);

            } catch (Exception e) {
                System.out.println("No se pudo Almacenar la auditoria: ---------------------------------- ");
                System.out.println(e.getMessage() +
                                   "------------------------------------------------------ ");
            }

            showMessage(getText("MENSAJE_BORRAR_USUARIO_EXITOSO"));
            setAccionBorrar("usuariosAgua");
        } catch (IdeamException e) {
            showMessage(e);
        }
    }

    public String getAccionBorrar() {
        return accionBorrar;
    }

    public void setAccionBorrar(String accionBorrar) {
        this.accionBorrar = accionBorrar;
    }

    public void cb_no_borrar_actionListener(ActionEvent actionEvent) {
        showPopup(p_borrar, false);
    }

    public void soc_rep_depto_valueChangeListener(ValueChangeEvent valueChangeEvent) {
        Object obj = valueChangeEvent.getNewValue();
        try {
            if (obj != null) {
                this.listaMunicipiosRespresentante =
                        this.cargarDivipolaSinRestriccion(((Divipola)obj).getId());
            } else {
                this.listaMunicipiosRespresentante = new ArrayList();
            }
            AdfFacesContext.getCurrentInstance().addPartialTarget(soc_rep_mun);
            AdfFacesContext.getCurrentInstance().addPartialTarget(soc_mun_rep_municipio);
        } catch (IdeamException e) {
            showMessage(e);
        }
    }

    public Divipola getDeptoJuridicaSeleccionado() {
        return deptoJuridicaSeleccionado;
    }

    public void setDeptoJuridicaSeleccionado(Divipola deptoJuridicaSeleccionado) {
        this.deptoJuridicaSeleccionado = deptoJuridicaSeleccionado;
    }

    public Divipola getMunicipioJuridicaSeleccionado() {
        return municipioJuridicaSeleccionado;
    }

    public void setMunicipioJuridicaSeleccionado(Divipola municipioJuridicaSeleccionado) {
        this.municipioJuridicaSeleccionado = municipioJuridicaSeleccionado;
    }

    public Divipola getDeptoMunicipioSeleccionado() {
        return deptoMunicipioSeleccionado;
    }

    public void setDeptoMunicipioSeleccionado(Divipola deptoMunicipioSeleccionado) {
        this.deptoMunicipioSeleccionado = deptoMunicipioSeleccionado;
    }

    public Divipola getMunicipioMunicipioSeleccionado() {
        return municipioMunicipioSeleccionado;
    }

    public void setMunicipioMunicipioSeleccionado(Divipola municipioMunicipioSeleccionado) {
        this.municipioMunicipioSeleccionado = municipioMunicipioSeleccionado;
    }

    public void cl1_actionListener2(ActionEvent actionEvent) {
        // Add event code here...
    }

    public void setOtapellido(RichOutputText otapellido) {
        this.otapellido = otapellido;
    }

    public RichOutputText getOtapellido() {
        return otapellido;
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


    public void setS20(RichSpacer s20) {
        this.s20 = s20;
    }

    public RichSpacer getS20() {
        return s20;
    }


    public void cmi_adicionar_predio_actionListener(ActionEvent actionEvent) {
        FacesUtils.setInSession("usuarioSeleccionado", this.usuarioAgua);
        accionAdicionar = "adicionarPredio";
    }
    //adicionar pueaa

    public void cmi_adicionar_pueaa_actionListener(ActionEvent actionEvent) {
        FacesUtils.setInSession("usuarioSeleccionado", this.usuarioAgua);
        FacesUtils.removeFromSession("pueaaSeleccionado");
        FacesUtils.removeManagedBeanFromSession("adicionarPueaa");

        accionAdicionar = "adicionarPueaa";
    }
    //adicionar Proyecto

    public void cmi_adicionar_proyecto_actionListener(ActionEvent actionEvent) {
        FacesUtils.setInSession("usuarioSeleccionado", this.usuarioAgua);
        accionAdicionar = "adicionarProyectoPueaa";
    }


    public void setAccionAdicionar(String accionAdicionar) {
        this.accionAdicionar = accionAdicionar;
    }

    public String getAccionAdicionar() {
        return accionAdicionar;
    }

    public void setCmi_adicionar_proyecto_pueaa(RichCommandMenuItem cmi_adicionar_proyecto_pueaa) {
        this.cmi_adicionar_proyecto_pueaa = cmi_adicionar_proyecto_pueaa;
    }

    public RichCommandMenuItem getCmi_adicionar_proyecto_pueaa() {
        return cmi_adicionar_proyecto_pueaa;
    }

    public void setPueaaTreeModel(TreeModel pueaaTreeModel) {
        this.pueaaTreeModel = pueaaTreeModel;
    }

    public TreeModel getPueaaTreeModel() {
        return pueaaTreeModel;
    }

    public void setT2(RichTree t2) {
        this.t2 = t2;
    }

    public RichTree getT2() {
        return t2;
    }

    public void setClPueaa(RichCommandLink clPueaa) {
        this.clPueaa = clPueaa;
    }

    public RichCommandLink getClPueaa() {
        return clPueaa;
    }

    public void setPc2p(RichPanelCollection pc2p) {
        this.pc2p = pc2p;
    }

    public RichPanelCollection getPc2p() {
        return pc2p;
    }
}
