package co.gov.ideam.sirh.usuarios.agua.view;

import co.gov.ideam.sirh.auditoria.model.Auditoria;
import co.gov.ideam.sirh.auditoria.view.AuditoriasDelegate;
import co.gov.ideam.sirh.parametros.model.Autoridades;
import co.gov.ideam.sirh.parametros.model.Categoria;
import co.gov.ideam.sirh.parametros.model.Lista;
import co.gov.ideam.sirh.parametros.view.ParametrosDelegate;
import co.gov.ideam.sirh.seguridad.model.UsuarioConectadoTO;
import co.gov.ideam.sirh.usuarios.agua.model.Captacion;
import co.gov.ideam.sirh.usuarios.agua.model.Concesion;
import co.gov.ideam.sirh.usuarios.agua.model.PermisoVertimiento;
import co.gov.ideam.sirh.usuarios.agua.model.Predio;
import co.gov.ideam.sirh.usuarios.agua.model.PuntoVertimiento;
import co.gov.ideam.sirh.usuarios.agua.model.TipoModificacionTO;
import co.gov.ideam.sirh.usuarios.agua.model.UsuarioAgua;
import co.gov.ideam.sirh.usuarios.agua.model.UsuarioAguaTO;
import co.gov.ideam.sirh.util.Constantes;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.view.FacesUtils;
import co.gov.ideam.sirh.view.StandarBean;

import co.gov.ideam.sirh.view.util.ArchivoCargadoTO;

import co.gov.ideam.sirh.view.util.SpecialTreeModel;
import co.gov.ideam.sirh.view.util.TreeNode;

import java.io.File;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UISelectItems;
import javax.faces.event.ActionEvent;

import javax.faces.event.ValueChangeEvent;

import javax.faces.model.SelectItem;

import oracle.adf.view.rich.component.rich.RichDialog;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.RichMenu;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTree;
import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputFile;
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
import oracle.adf.view.rich.component.rich.output.RichMessage;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.component.rich.output.RichPanelCollection;
import oracle.adf.view.rich.component.rich.output.RichSpacer;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.adf.view.rich.event.DialogEvent;

import org.apache.myfaces.trinidad.model.TreeModel;
import org.apache.myfaces.trinidad.model.UploadedFile;

public class DetallePermisoBean extends StandarBean{
    private RichForm f2;
    private RichDocument d2;
    private RichPanelStretchLayout psl1;
    private RichPanelGroupLayout pgl1;
    private RichSpacer s1;
    private RichCommandLink cl2;
    private RichSpacer s2;
    private RichCommandLink cl3;
    private RichSpacer s6;
    private RichCommandLink cl7;
    private RichSpacer s9;
    private RichCommandLink cl8;
    private RichSpacer s11;
    private RichOutputText ot2;
    private RichPopup p_borrar;
    private RichDialog d_borrar;
    private RichOutputText ot3;
    private RichPanelGroupLayout pgl4;
    private RichCommandButton cb_borrar_si;
    private RichSpacer s12;
    private RichCommandButton cb_borrar_no;
    private RichPanelSplitter ps1;
    private RichPanelBox pb_detalle;
    private RichPanelFormLayout pfl1;
    private RichSpacer s3;

    private String titulo;
    private PermisoVertimiento permiso;
    private Predio predio;
    private String paginaOrigen;
    private String retorno;
    private Boolean mostrarPlanCumplimiento;
    private UsuarioAgua usuarioAgua;
    private UploadedFile uploadedFile;
    private ArchivoCargadoTO archivoCargado;
    private List listaTiposModificacion;
    private List listaTiposIdentificacion;
    private List listaPrediosTraspaso;
    private PermisoVertimiento permisoNovedad;
    private UsuarioAgua usuarioTraspaso;
    private UsuarioAguaTO usuarioAguaTo;
    
    private TreeModel vertimientosTreeModel;
    
    private RichInputText it_res_ini_tramite;
    private RichInputText it_numero_expediente;
    private RichInputText it_caudal;
    private RichInputText it_evaluacion_ambiental;
    private RichInputText it_res_plan_cum;
    private RichInputDate id_exp_plan_cump;
    private RichInputText it_res_aprob_plan;
    private RichInputDate id_exp_plan;
    private RichInputDate id_ini_vig_plan;
    private RichInputDate id_fin_vig_plan;
    private RichInputText it_res_permiso;
    private RichInputDate id_exp_permiso;
    private RichInputDate id_inicio;
    private RichInputDate id_fin;
    private RichInputText it8;
    private RichInputDate id8;
    private RichInputText it9;
    private RichInputDate id9;
    private RichInputDate id_ini_tra;
    private RichPanelGroupLayout pgl2;
    private RichSpacer s4;
    private RichPanelGroupLayout pgl3;
    private RichCommandButton cb_aceptar;
    private RichSpacer s5;
    private RichCommandButton cb_borrar;
    private RichSpacer s7;
    private RichInputFile if_archivo;
    private RichMessage m2;
    private RichPanelGroupLayout pgl_archivo;
    private RichCommandLink cl_documento;
    private RichSpacer s8;
    private RichCommandButton cb_borrar_documento;
    private RichPanelGroupLayout pgl6;
    private RichPopup p_borrar_archivo;
    private RichDialog d_borrar_archivo;
    private RichOutputText ot_confirmacion;
    private RichOutputText ot1;
    private RichSpacer s10;
    private RichSpacer s13;
    private RichOutputText ot4;
    private RichSpacer s14;
    private RichOutputText ot5;
    private RichOutputText ot6;
    private RichInputText it1;
    private RichInputDate id1;
    private RichInputDate id2;
    private RichInputDate id3;
    private RichInputText it4;
    private RichSpacer s15;


    private RichPanelBox panelBox1;
    private RichPanelCollection panelCollection1;
    private RichTree tree1;
    private RichMenu menu1;
    private RichCommandMenuItem commandMenuAddVertimiento;
    private RichCommandLink cml2;    
    private RichCommandButton cb_novedad;
    private RichPopup p_novedad;
    private RichDialog d_novedad;
    private RichPanelFormLayout pfl2;
    private RichSelectOneChoice soc_tipo_novedad;
    private UISelectItems si1;
    private RichInputText it_desc_novedad;
    private RichInputText it_acto_adm;
    private RichInputDate id_fecha;
    private RichInputText it_observaciones;
    private RichSelectOneChoice soc_tipo_id_traspaso;
    private UISelectItems si2;
    private RichInputText it_id_usuario_traspasa;
    private RichSelectOneChoice soc_predio_traspaso;
    private UISelectItems si3;


    public DetallePermisoBean(){
        FacesUtils.setActiveBean("detallePermiso",
                                 "Usuarios Del Agua",
                                 UsuariosAguaDelegate.class);      
        FacesUtils.removeManagedBeanFromSession("PrediosTreeHandler");
        this.load();
    }
    
    public void load(){
        setMostrarPlanCumplimiento(true);
        
        try{
            UsuariosAguaDelegate uad = UsuariosAguaDelegate.getInstance();
            ParametrosDelegate pd = ParametrosDelegate.getInstance();  
            Object obj = FacesUtils.getFromSession("usuarioSeleccionado");
                
                if(obj instanceof UsuarioAgua){
                     this.setUsuarioAgua( (UsuarioAgua)obj);
                     Long codigoUsuario=  this.usuarioAgua.getCodigo();
                     System.out.println("usuarioAgua----------------"+codigoUsuario);
                     
                    
                }  else if(obj instanceof UsuarioAguaTO){
                     this.usuarioAguaTo = (UsuarioAguaTO)obj;
                     Long codigoUsuario=  this.usuarioAguaTo.getCodigo();
                     this.setUsuarioAgua( uad.getUsuarioAgua(codigoUsuario)); 
                     System.out.println("usuarioAguaTo----------------"+codigoUsuario);
                    
                }else{                
                    Long codigoUsuario = (Long)FacesUtils.getFromSession("usuarioSeleccionado"); 
                    System.out.println("usuarioSeleccionado es un ID----------------"+codigoUsuario);
                    this.setUsuarioAgua(uad.getUsuarioAgua(codigoUsuario));                
                }
                FacesUtils.setInSession("usuarioSeleccionado", usuarioAgua);
            if(usuarioAgua.getTipoUsuario().getCodigo().intValue()==UsuarioAgua.MUNICIPIO ||
                usuarioAgua.getTipoUsuario().getCodigo().intValue()==UsuarioAgua.ACUEDUCTO){
                setMostrarPlanCumplimiento(false);
            }else{
                setMostrarPlanCumplimiento(true);
            }
            
            Object objPredio = FacesUtils.getFromSession("predioSeleccionado");
            if(objPredio instanceof Predio){
                this.setPredio((Predio)FacesUtils.getFromSession("predioSeleccionado"));        
            }else{
                Long codigoPredio = (Long)FacesUtils.getFromSession("predioSeleccionado");
                this.setPredio( uad.getPredio( codigoPredio ) );
                FacesUtils.setInSession("predioSeleccionado", predio);
            }
            
            Object objPermiso = FacesUtils.getFromSession("permisoSeleccionado");
            if(objPermiso instanceof PermisoVertimiento){
                permiso = (PermisoVertimiento)FacesUtils.getFromSession("permisoSeleccionado");        
            }else{
                Long codigoPermiso = (Long)FacesUtils.getFromSession("permisoSeleccionado");
                permiso = uad.getPermisoVertimiento( codigoPermiso );
                FacesUtils.setInSession("permisoSeleccionado", permiso);
            }
            
            
            
            List listaNodos = new ArrayList();    
            List listaV = uad.getVertimientos(this.permiso);
            
            TreeNode nodoVertimientos = new TreeNode(getText("VERTIMIENTOS"), 
                                               "Vertimientos");
            nodoVertimientos.setData("Vertimientos");
            listaNodos.add(nodoVertimientos);
            if (listaV!=null) {
                Iterator it = listaV.iterator();
                while(it.hasNext()){
                    PuntoVertimiento vertimiento = (PuntoVertimiento)it.next();
                    Lista lista = pd.getLista(vertimiento.getTipoVertimiento());
                    TreeNode nodoVertimiento = new TreeNode(getText("VERTIMIENTO") 
                                                            + " " +
                                                            lista.getValor(),
                                                       "", true, false);                   
                    nodoVertimiento.setData(vertimiento);            
                    nodoVertimientos.getChildren().add(nodoVertimiento);   
                }
            }  else{
                    TreeNode nodoVertimiento = new TreeNode(getText("NO_HAY_REGISTROS"), 
                                                          "vertimiento", false, true);                                           
                    nodoVertimientos.getChildren().add(nodoVertimiento);
                } 
            vertimientosTreeModel = new SpecialTreeModel(listaNodos, "children"); 
            
            
            
            
        }catch(IdeamException e){
            showMessage(e);
        }
                
        setPaginaOrigen((String)FacesUtils.getFromSession("paginaOrigen"));        
        if(permiso==null ||
           permiso.getNumeroExpediente()==null ||
           permiso.getNumeroExpediente().toString().length() == 0){
           this.titulo = "  Nuevo";
        }else{
            this.titulo = permiso.getNumeroExpediente();
        }
    }
    
    
    
    public void cml2_actionListener(ActionEvent actionEvent) {
        String nombreParametro = 
            (String)actionEvent.getComponent().getAttributes().
                                                        get("nombreParametro");     
        //System.out.println("nombreParametro:"+nombreParametro);
        Object data = actionEvent.getComponent().getAttributes().
                                                        get("valorParametro");        
        if(nombreParametro!=null && data != null){
            FacesUtils.setInSession(nombreParametro, data);
            //System.out.println("DetalleConcesionBean -> se coloca objeto en seccion");
            if(data instanceof String){
                //System.out.println("data.toString():"+data.toString());
                if(data.toString().equals("Vertimientos")){          
                    if(this.permiso!=null){
                        FacesUtils.setInSession("permisoSeleccionado", this.permiso);
                    }
                }
            }
        }
    }
    
    
    
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

    public void setCl2(RichCommandLink cl2) {
        this.cl2 = cl2;
    }

    public RichCommandLink getCl2() {
        return cl2;
    }

    public void setS2(RichSpacer s2) {
        this.s2 = s2;
    }

    public RichSpacer getS2() {
        return s2;
    }

    public void setCl3(RichCommandLink cl3) {
        this.cl3 = cl3;
    }

    public RichCommandLink getCl3() {
        return cl3;
    }

    public void setS6(RichSpacer s6) {
        this.s6 = s6;
    }

    public RichSpacer getS6() {
        return s6;
    }

    public void setCl7(RichCommandLink cl7) {
        this.cl7 = cl7;
    }

    public RichCommandLink getCl7() {
        return cl7;
    }

    public void setS9(RichSpacer s9) {
        this.s9 = s9;
    }

    public RichSpacer getS9() {
        return s9;
    }

    public void setCl8(RichCommandLink cl8) {
        this.cl8 = cl8;
    }

    public RichCommandLink getCl8() {
        return cl8;
    }

    public void setS11(RichSpacer s11) {
        this.s11 = s11;
    }

    public RichSpacer getS11() {
        return s11;
    }

    public void setOt2(RichOutputText ot2) {
        this.ot2 = ot2;
    }

    public RichOutputText getOt2() {
        return ot2;
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

    public void setOt3(RichOutputText ot3) {
        this.ot3 = ot3;
    }

    public RichOutputText getOt3() {
        return ot3;
    }

    public void setPgl4(RichPanelGroupLayout pgl4) {
        this.pgl4 = pgl4;
    }

    public RichPanelGroupLayout getPgl4() {
        return pgl4;
    }

    public void setCb_borrar_si(RichCommandButton cb1) {
        this.cb_borrar_si = cb1;
    }

    public RichCommandButton getCb_borrar_si() {
        return cb_borrar_si;
    }

    public void setS12(RichSpacer s12) {
        this.s12 = s12;
    }

    public RichSpacer getS12() {
        return s12;
    }

    public void setCb_borrar_no(RichCommandButton cb2) {
        this.cb_borrar_no = cb2;
    }

    public RichCommandButton getCb_borrar_no() {
        return cb_borrar_no;
    }

    public void setPs1(RichPanelSplitter ps1) {
        this.ps1 = ps1;
    }

    public RichPanelSplitter getPs1() {
        return ps1;
    }

    public void setPb_detalle(RichPanelBox pb1) {
        this.pb_detalle = pb1;
    }

    public RichPanelBox getPb_detalle() {
        return pb_detalle;
    }

    public void setPfl1(RichPanelFormLayout pfl1) {
        this.pfl1 = pfl1;
    }

    public RichPanelFormLayout getPfl1() {
        return pfl1;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public PermisoVertimiento getPermiso() {
        return permiso;
    }

    public void setPermiso(PermisoVertimiento permiso) {
        this.permiso = permiso;
    }

    public Predio getPredio() {
        return predio;
    }

    public void setPredio(Predio predio) {
        this.predio = predio;
    }

    public String getPaginaOrigen() {
        return paginaOrigen;
    }

    public void setPaginaOrigen(String paginaOrigen) {
        this.paginaOrigen = paginaOrigen;
    }

    public void setIt_res_ini_tramite(RichInputText it1) {
        this.it_res_ini_tramite = it1;
    }

    public RichInputText getIt_res_ini_tramite() {
        return it_res_ini_tramite;
    }


    public void setIt_numero_expediente(RichInputText it2) {
        this.it_numero_expediente = it2;
    }

    public RichInputText getIt_numero_expediente() {
        return it_numero_expediente;
    }

    public void setIt_caudal(RichInputText it3) {
        this.it_caudal = it3;
    }

    public RichInputText getIt_caudal() {
        return it_caudal;
    }

    public void setIt_evaluacion_ambiental(RichInputText it4) {
        this.it_evaluacion_ambiental = it4;
    }

    public RichInputText getIt_evaluacion_ambiental() {
        return it_evaluacion_ambiental;
    }

    public void setIt_res_plan_cum(RichInputText it5) {
        this.it_res_plan_cum = it5;
    }

    public RichInputText getIt_res_plan_cum() {
        return it_res_plan_cum;
    }

    public void setId_exp_plan_cump(RichInputDate id1) {
        this.id_exp_plan_cump = id1;
    }

    public RichInputDate getId_exp_plan_cump() {
        return id_exp_plan_cump;
    }

    public void setIt_res_aprob_plan(RichInputText it6) {
        this.it_res_aprob_plan = it6;
    }

    public RichInputText getIt_res_aprob_plan() {
        return it_res_aprob_plan;
    }

    public void setId_exp_plan(RichInputDate id2) {
        this.id_exp_plan = id2;
    }

    public RichInputDate getId_exp_plan() {
        return id_exp_plan;
    }

    public void setId_ini_vig_plan(RichInputDate id3) {
        this.id_ini_vig_plan = id3;
    }

    public RichInputDate getId_ini_vig_plan() {
        return id_ini_vig_plan;
    }

    public void setId_fin_vig_plan(RichInputDate id4) {
        this.id_fin_vig_plan = id4;
    }

    public RichInputDate getId_fin_vig_plan() {
        return id_fin_vig_plan;
    }

    public void setIt_res_permiso(RichInputText it7) {
        this.it_res_permiso = it7;
    }

    public RichInputText getIt_res_permiso() {
        return it_res_permiso;
    }

    public void setId_exp_permiso(RichInputDate id5) {
        this.id_exp_permiso = id5;
    }

    public RichInputDate getId_exp_permiso() {
        return id_exp_permiso;
    }

    public void setId_inicio(RichInputDate id6) {
        this.id_inicio = id6;
    }

    public RichInputDate getId_inicio() {
        return id_inicio;
    }

    public void setId_fin(RichInputDate id7) {
        this.id_fin = id7;
    }

    public RichInputDate getId_fin() {
        return id_fin;
    }

    public void setIt8(RichInputText it8) {
        this.it8 = it8;
    }

    public RichInputText getIt8() {
        return it8;
    }

    public void setId8(RichInputDate id8) {
        this.id8 = id8;
    }

    public RichInputDate getId8() {
        return id8;
    }

    public void setIt9(RichInputText it9) {
        this.it9 = it9;
    }

    public RichInputText getIt9() {
        return it9;
    }

    public void setId9(RichInputDate id9) {
        this.id9 = id9;
    }

    public RichInputDate getId9() {
        return id9;
    }

    public void setId_ini_tra(RichInputDate id10) {
        this.id_ini_tra = id10;
    }

    public RichInputDate getId_ini_tra() {
        return id_ini_tra;
    }

    public void setPgl2(RichPanelGroupLayout pgl2) {
        this.pgl2 = pgl2;
    }

    public RichPanelGroupLayout getPgl2() {
        return pgl2;
    }

    public void setS4(RichSpacer s4) {
        this.s4 = s4;
    }

    public RichSpacer getS4() {
        return s4;
    }

    public void setPgl3(RichPanelGroupLayout pgl3) {
        this.pgl3 = pgl3;
    }

    public RichPanelGroupLayout getPgl3() {
        return pgl3;
    }

    public void setCb_aceptar(RichCommandButton cb3) {
        this.cb_aceptar = cb3;
    }

    public RichCommandButton getCb_aceptar() {
        return cb_aceptar;
    }

    public void setS5(RichSpacer s5) {
        this.s5 = s5;
    }

    public RichSpacer getS5() {
        return s5;
    }

    public void setCb_borrar(RichCommandButton cb4) {
        this.cb_borrar = cb4;
    }

    public RichCommandButton getCb_borrar() {
        return cb_borrar;
    }

    public void setS7(RichSpacer s7) {
        this.s7 = s7;
    }

    public RichSpacer getS7() {
        return s7;
    }

    public void cb_aceptar_actionListener(ActionEvent actionEvent) {
        retorno = "";
        boolean continuar = true;
        if(it_numero_expediente.getValue()==null ||
           it_numero_expediente.getValue().toString().length()==0){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,it_numero_expediente);       
            continuar = false;
        }
        if(it_caudal.getValue()==null){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,it_caudal);       
            continuar = false;
        }
        if(it_res_permiso.getValue()==null ||
           it_res_permiso.getValue().toString().length()==0){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,it_res_permiso);       
            continuar = false;
        }
        if(id_exp_permiso.getValue()==null){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,id_exp_permiso);       
            continuar = false;            
        }
        if(id_inicio.getValue()==null){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,id_inicio);       
            continuar = false;                        
        }
        if(id_fin.getValue()==null){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,id_fin);       
            continuar = false;            
        }
        if(archivoCargado != null && this.permiso.getArchivo()==null){
            try{
                this.permiso.setArchivo(archivoCargado.getContent());
                this.permiso.setTipoArchivo( archivoCargado.getType() );
            }catch(IdeamException e){
                showMessage(e);
                continuar = false;
            }
        }
        if(continuar){
            try{
                UsuariosAguaDelegate uad = UsuariosAguaDelegate.getInstance();
                this.permiso = uad.registrarPermiso(this.permiso);
                
                if(this.permisoNovedad!=null){
                    this.permisoNovedad = uad.registrarPermiso(this.permisoNovedad);
                }
                String params[] = {"del Permiso de Vertimiento"};
                
                
                try{
                    
                    /** 
                     * Estas variables son necesarias para llenar los datos intrinsecos de la auditoria.
                     */
                     UsuarioConectadoTO usuarioConectado = (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
                    
                    Auditoria auditoria = new Auditoria();
                    auditoria.setIdUsuario(usuarioConectado.getUsuario().getCodigo());
                    auditoria.setOperacion("MODIFICAR");
                    auditoria.setObjeto("PERMISO_VERTIMIENTO");
                    auditoria.setMetodo(Thread.currentThread().getStackTrace()[1].getMethodName());
                    auditoria.setClase(this.getClass().getName());
                    //Objeto Afectado (usuario modificado, eliminado o agregado) 
                    auditoria.setIdObjeto(this.permiso.getCodigo());
                   
                    AuditoriasDelegate audDelegate = AuditoriasDelegate.getInstance();
                    audDelegate.setAuditoria(auditoria);
                    
                }catch(Exception e){
                    System.out.println("No se pudo Almacenar la auditoria: ---------------------------------- ");
                    System.out.println(e.getMessage()+"------------------------------------------------------ ");
                }
                
                
                showMessage(getText("mensaje_registro_exitoso",
                                    params), FacesMessage.SEVERITY_INFO);
                
                setRetorno("");
            }catch(IdeamException e){
                showMessage(e);
            }
        }
    }

    public String getRetorno() {
        return retorno;
    }

    public void setRetorno(String retorno) {
        this.retorno = retorno;
    }

    public void cb_borrar_actionListener(ActionEvent actionEvent) {
        try{
            UsuariosAguaDelegate uad = UsuariosAguaDelegate.getInstance();
            List listaVertimientos = uad.getVertimientos(this.permiso);
            String nombrePermiso = 
                (this.permiso.getNumeroExpediente() != null && 
                this.permiso.getNumeroExpediente().length() > 0) 
                ? this.permiso.getNumeroExpediente() : "-Sin Nombre-";
            
            String params1[] = {nombrePermiso};
            this.ot1.setValue( getText("MENSAJE_BORRAR_PERMISO", params1) );
            
            this.ot2.setValue( "" );
            if(listaVertimientos!=null ){
                String params2[] = {"" + listaVertimientos.size()};
                this.ot2.setValue( getText("MENSAJE_BORRAR_PERMISO_DETALLE_VERTIMIENTOS",
                                                   params2) );
            }else{
                this.ot2.setValue( "" );
            }
            
            AdfFacesContext.getCurrentInstance().addPartialTarget(ot1);
            AdfFacesContext.getCurrentInstance().addPartialTarget(ot2);
            
            showPopup(p_borrar, true);
        }catch(IdeamException e){
            showMessage(e);
        }
    }

    public void cb_borrar_si_actionListener(ActionEvent actionEvent) {
        try{
            UsuariosAguaDelegate uad = UsuariosAguaDelegate.getInstance();
            uad.borrarPermiso(permiso);
            
            
            
            try{
                
                /** 
                 * Estas variables son necesarias para llenar los datos intrinsecos de la auditoria.
                 */
                 UsuarioConectadoTO usuarioConectado = (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
                
                Auditoria auditoria = new Auditoria();
                auditoria.setIdUsuario(usuarioConectado.getUsuario().getCodigo());
                auditoria.setOperacion("BORRAR");
                auditoria.setObjeto("PERMISO_VERTIMIENTO");
                auditoria.setMetodo(Thread.currentThread().getStackTrace()[1].getMethodName());
                auditoria.setClase(this.getClass().getName());
                //Objeto Afectado (usuario modificado, eliminado o agregado) 
                auditoria.setIdObjeto(this.permiso.getCodigo());
               
                AuditoriasDelegate audDelegate = AuditoriasDelegate.getInstance();
                audDelegate.setAuditoria(auditoria);
                
            }catch(Exception e){
                System.out.println("No se pudo Almacenar la auditoria: ---------------------------------- ");
                System.out.println(e.getMessage()+"------------------------------------------------------ ");
            }
            
            
            showMessage(getText("MENSAJE_BORRAR_PERMISO_EXITOSO"));
            retorno = this.paginaOrigen;
        }catch(IdeamException e){
            showMessage(e);
        }
    }

    public void cb_borrar_no_actionListener(ActionEvent actionEvent) {
        showPopup(p_borrar, false);
    }

    public void it_numero_expediente_valueChangeListener(ValueChangeEvent valueChangeEvent) {
        // DEterminar si existe un permiso con el mismo numero de expediente
        Object obj = valueChangeEvent.getNewValue();
        if(obj!=null){
            try{
                UsuarioConectadoTO usuarioConectado = 
                    (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");

                Autoridades autoridadAmbiental = usuarioConectado.getUsuario().getAutoridadAmbiental();
                
                if(autoridadAmbiental==null ||
                   autoridadAmbiental.getId().intValue() == Constantes.IDEAM){
                    showMessage(getText("NO_EXISTE_AUTORIDAD_AMBIENTAL"),
                                FacesMessage.SEVERITY_ERROR);       
                    return;
                }
                
                UsuariosAguaDelegate uad = UsuariosAguaDelegate.getInstance();
                PermisoVertimiento existe = 
                    uad.getPermisoVertimiento(valueChangeEvent.getNewValue().toString(),
                                              autoridadAmbiental.getId().longValue());
                
                PermisoVertimiento permisoActual = new PermisoVertimiento();
                permisoActual.setArchivo( this.permiso.getArchivo() );
                permisoActual.setCaudal( this.permiso.getCaudal() );
                permisoActual.setCodigo( this.permiso.getCodigo() );
                permisoActual.setCodigoAutoridadAmbiental( this.permiso.getCodigoAutoridadAmbiental() );
                permisoActual.setCodigoPredio( this.permiso.getCodigoPredio() );
                permisoActual.setEvaluacionAmbiental( this.permiso.getEvaluacionAmbiental() );
                permisoActual.setFechaExpedicionAprobacionPlan( this.permiso.getFechaExpedicionAprobacionPlan() );
                permisoActual.setFechaExpedicionInicioTramite( this.permiso.getFechaExpedicionInicioTramite() );
                permisoActual.setFechaExpedicionObras( this.permiso.getFechaExpedicionObras() );
                permisoActual.setFechaExpedicionPermiso( this.permiso.getFechaExpedicionPermiso() );
                permisoActual.setFechaExpedicionPlan( this.permiso.getFechaExpedicionPlan() );
                permisoActual.setFechaExpedicionPlanos( this.permiso.getFechaExpedicionPlanos() );
                permisoActual.setFechaFin( this.permiso.getFechaFin() );
                permisoActual.setFechaFinPlan( this.permiso.getFechaFinPlan() );
                permisoActual.setFechaFinPsmv( this.permiso.getFechaFinPsmv() );
                permisoActual.setFechaInicio(this.permiso.getFechaInicio() );
                permisoActual.setFechaInicioPsmv( this.permiso.getFechaInicioPsmv() );
                permisoActual.setFechaInicoPlan( this.permiso.getFechaInicoPlan() );
                permisoActual.setFechaNotificacionObrasPsmv(this.permiso.getFechaNotificacionObrasPsmv() );
                permisoActual.setFechaResolucionObrasPsmv( this.permiso.getFechaResolucionObrasPsmv() );
                permisoActual.setFechaResolucionPlanosPsmv( this.permiso.getFechaResolucionPlanosPsmv() );
                permisoActual.setFechaResolucionPsmv( this.permiso.getFechaResolucionPsmv() );
                permisoActual.setIdUsuarioModifica( this.permiso.getIdUsuarioModifica() );
                permisoActual.setNumeroExpediente( this.permiso.getNumeroExpediente() );
                permisoActual.setObervacionesPsmv( this.permiso.getObervacionesPsmv() );
                permisoActual.setResolucionAprobacionObras( this.permiso.getResolucionAprobacionObras() );
                permisoActual.setResolucionAprobacionPlan( this.permiso.getResolucionAprobacionPlan() );
                permisoActual.setResolucionAprobacionPlanos( this.permiso.getResolucionAprobacionPlanos() );
                permisoActual.setResolucionInicioTramite( this.permiso.getResolucionInicioTramite() );
                permisoActual.setResolucionObrasPsmv( this.permiso.getResolucionObrasPsmv() );
                permisoActual.setResolucionPermisoVertimiento( this.permiso.getResolucionPermisoVertimiento() );
                permisoActual.setResolucionPlanosPsmv( this.permiso.getResolucionPlanosPsmv() );
                permisoActual.setResolucionPsmv( this.permiso.getResolucionPsmv() );
                permisoActual.setResolucionSolicitudPlanCumplimiento( this.permiso.getResolucionSolicitudPlanCumplimiento() );
                permisoActual.setTipoArchivo( this.permiso.getTipoArchivo() );
                
                if (existe!=null){                    
                    this.permiso = existe;     
                    this.permiso.setCodigo(null);
                    this.permiso.setCodigoPredio( this.predio.getCodigo() );
                    this.permiso.setCodigoAutoridadAmbiental(usuarioConectado.getUsuario().getAutoridadAmbiental().getId().longValue());
                }else{
                    this.permiso = permisoActual;
                }
                AdfFacesContext.getCurrentInstance().addPartialTarget(pb_detalle);
            }catch(IdeamException e){
                showMessage(e);
            } 
        }
    }

    public Boolean getMostrarPlanCumplimiento() {
        return mostrarPlanCumplimiento;
    }

    public void setMostrarPlanCumplimiento(Boolean mostrarPlanCumplimiento) {
        this.mostrarPlanCumplimiento = mostrarPlanCumplimiento;
    }

    public UsuarioAgua getUsuarioAgua() {
        return usuarioAgua;
    }

    public void setUsuarioAgua(UsuarioAgua usuarioAgua) {
        this.usuarioAgua = usuarioAgua;
    }

    public void setIf_archivo(RichInputFile if1) {
        this.if_archivo = if1;
    }

    public RichInputFile getIf_archivo() {
        return if_archivo;
    }

    public void setM2(RichMessage m2) {
        this.m2 = m2;
    }

    public RichMessage getM2() {
        return m2;
    }

    public UploadedFile getUploadedFile() {
        return uploadedFile;
    }

    public void setUploadedFile(UploadedFile uploadedFile) {
        this.uploadedFile = uploadedFile;
        if (uploadedFile!=null){
            ArchivoCargadoTO ac = new ArchivoCargadoTO();
            ac.setFile(new File(uploadedFile.getFilename()));
            ac.setFileName(uploadedFile.getFilename());
            try {
                ac.setInputStream(uploadedFile.getInputStream());
            } catch (IOException e) {
                archivoCargado=null;
                showMessage(new IdeamException(e));            
            }
            ac.setSize(uploadedFile.getLength());
            ac.setType(uploadedFile.getContentType());
            archivoCargado = ac;
        }                       
    }

    public ArchivoCargadoTO getArchivoCargado() {
        return archivoCargado;
    }

    public void setArchivoCargado(ArchivoCargadoTO archivoCargado) {
        this.archivoCargado = archivoCargado;
    }

    public void if_archivo_valueChangeListener(ValueChangeEvent valueChangeEvent) {
        UploadedFile file = (UploadedFile)valueChangeEvent.getNewValue();
        if (file!=null){
            String tipoArchivo = file.getContentType();
            if (!tipoArchivo.endsWith("pdf")){                     
                String params[] = { file.getFilename(), "PDF" };            
                showMessage("tipo_archivo_incorrecto",
                            params,
                            FacesMessage.SEVERITY_ERROR,
                            if_archivo);
                this.if_archivo.resetValue();
                this.setUploadedFile(null);
                this.archivoCargado=null;
                return;
            }
            this.setUploadedFile(file);
        }
    }

    public void setPgl_archivo(RichPanelGroupLayout pgl5) {
        this.pgl_archivo = pgl5;
    }

    public RichPanelGroupLayout getPgl_archivo() {
        return pgl_archivo;
    }


    public void setCl_documento(RichCommandLink cl1) {
        this.cl_documento = cl1;
    }

    public RichCommandLink getCl_documento() {
        return cl_documento;
    }


    public void setS8(RichSpacer s8) {
        this.s8 = s8;
    }

    public RichSpacer getS8() {
        return s8;
    }

    public void setCb_borrar_documento(RichCommandButton cb1) {
        this.cb_borrar_documento = cb1;
    }

    public RichCommandButton getCb_borrar_documento() {
        return cb_borrar_documento;
    }

    public void cl_documento_actionListener(ActionEvent actionEvent) {                
        showReport(this.permiso.getArchivo());
    }


    public void setPgl6(RichPanelGroupLayout pgl6) {
        this.pgl6 = pgl6;
    }

    public RichPanelGroupLayout getPgl6() {
        return pgl6;
    }

    public void setP_borrar_archivo(RichPopup p1) {
        this.p_borrar_archivo = p1;
    }

    public RichPopup getP_borrar_archivo() {
        return p_borrar_archivo;
    }

    public void setD_borrar_archivo(RichDialog d1) {
        this.d_borrar_archivo = d1;
    }

    public RichDialog getD_borrar_archivo() {
        return d_borrar_archivo;
    }

    public void setOt_confirmacion(RichOutputText ot1) {
        this.ot_confirmacion = ot1;
    }

    public RichOutputText getOt_confirmacion() {
        return ot_confirmacion;
    }

    public void cb_borrar_documento_actionListener(ActionEvent actionEvent) {
        showPopup(p_borrar_archivo, true);
    }

    public void d_borrar_archivo_dialogListener(DialogEvent dialogEvent) {
        this.permiso.setArchivo(null);
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.if_archivo);
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.pgl_archivo);
    }

    public void setOt1(RichOutputText ot1) {
        this.ot1 = ot1;
    }

    public RichOutputText getOt1() {
        return ot1;
    }

    public void setS10(RichSpacer s10) {
        this.s10 = s10;
    }

    public RichSpacer getS10() {
        return s10;
    }

    public void setS13(RichSpacer s13) {
        this.s13 = s13;
    }

    public RichSpacer getS13() {
        return s13;
    }

    public void setOt4(RichOutputText ot4) {
        this.ot4 = ot4;
    }

    public RichOutputText getOt4() {
        return ot4;
    }

    public void setS14(RichSpacer s14) {
        this.s14 = s14;
    }

    public RichSpacer getS14() {
        return s14;
    }

    public void setOt5(RichOutputText ot5) {
        this.ot5 = ot5;
    }

    public RichOutputText getOt5() {
        return ot5;
    }

    public void setOt6(RichOutputText ot6) {
        this.ot6 = ot6;
    }

    public RichOutputText getOt6() {
        return ot6;
    }

    public void setIt1(RichInputText it1) {
        this.it1 = it1;
    }

    public RichInputText getIt1() {
        return it1;
    }

    public void setId1(RichInputDate id1) {
        this.id1 = id1;
    }

    public RichInputDate getId1() {
        return id1;
    }

    public void setId2(RichInputDate id2) {
        this.id2 = id2;
    }

    public RichInputDate getId2() {
        return id2;
    }

    public void setId3(RichInputDate id3) {
        this.id3 = id3;
    }

    public RichInputDate getId3() {
        return id3;
    }


    public void setIt4(RichInputText it4) {
        this.it4 = it4;
    }

    public RichInputText getIt4() {
        return it4;
    }

    public void setS15(RichSpacer s15) {
        this.s15 = s15;
    }

    public RichSpacer getS15() {
        return s15;
    }


    public void setVertimientosTreeModel(TreeModel vertimientosTreeModel) {
        this.vertimientosTreeModel = vertimientosTreeModel;
    }

    public TreeModel getVertimientosTreeModel() {
        return vertimientosTreeModel;
    }


    public void setPanelBox1(RichPanelBox panelBox1) {
        this.panelBox1 = panelBox1;
    }

    public RichPanelBox getPanelBox1() {
        return panelBox1;
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

    public void setMenu1(RichMenu menu1) {
        this.menu1 = menu1;
    }

    public RichMenu getMenu1() {
        return menu1;
    }

    public void setCommandMenuAddVertimiento(RichCommandMenuItem commandMenuAddVertimiento) {
        this.commandMenuAddVertimiento = commandMenuAddVertimiento;
    }

    public RichCommandMenuItem getCommandMenuAddVertimiento() {
        return commandMenuAddVertimiento;
    }

    public void setCml2(RichCommandLink cml2) {
        this.cml2 = cml2;
    }

    public RichCommandLink getCml2() {
        return cml2;
    }

    public void commandMenuAddVertimiento_actionListener(ActionEvent actionEvent) {
        FacesUtils.setInSession("permisoSeleccionado", this.permiso);
        FacesUtils.setInSession("paginaOrigen","detallePermiso");
        // Add event code here...
    }

    public void setS3(RichSpacer s3) {
        this.s3 = s3;
    }

    public RichSpacer getS3() {
        return s3;
    }

    public void setCb_novedad(RichCommandButton cb1) {
        this.cb_novedad = cb1;
    }

    public RichCommandButton getCb_novedad() {
        return cb_novedad;
    }

    public void setP_novedad(RichPopup p1) {
        this.p_novedad = p1;
    }

    public RichPopup getP_novedad() {
        return p_novedad;
    }

    public void setD_novedad(RichDialog d1) {
        this.d_novedad = d1;
    }

    public RichDialog getD_novedad() {
        return d_novedad;
    }

    public void setPfl2(RichPanelFormLayout pfl2) {
        this.pfl2 = pfl2;
    }

    public RichPanelFormLayout getPfl2() {
        return pfl2;
    }

    public List getListaTiposModificacion() {
        return listaTiposModificacion;
    }

    public void setListaTiposModificacion(List listaTiposModificacion) {
        this.listaTiposModificacion = listaTiposModificacion;
    }

    public List getListaTiposIdentificacion() {
        return listaTiposIdentificacion;
    }

    public void setListaTiposIdentificacion(List listaTiposIdentificacion) {
        this.listaTiposIdentificacion = listaTiposIdentificacion;
    }

    public List getListaPrediosTraspaso() {
        return listaPrediosTraspaso;
    }

    public void setListaPrediosTraspaso(List listaPrediosTraspaso) {
        this.listaPrediosTraspaso = listaPrediosTraspaso;
    }

    public PermisoVertimiento getPermisoNovedad() {
        return permisoNovedad;
    }

    public void setPermisoNovedad(PermisoVertimiento permisoNovedad) {
        this.permisoNovedad = permisoNovedad;
    }

    public UsuarioAgua getUsuarioTraspaso() {
        return usuarioTraspaso;
    }

    public void setUsuarioTraspaso(UsuarioAgua usuarioTraspaso) {
        this.usuarioTraspaso = usuarioTraspaso;
    }

    public void setSoc_tipo_novedad(RichSelectOneChoice soc1) {
        this.soc_tipo_novedad = soc1;
    }

    public RichSelectOneChoice getSoc_tipo_novedad() {
        return soc_tipo_novedad;
    }

    public void setSi1(UISelectItems si1) {
        this.si1 = si1;
    }

    public UISelectItems getSi1() {
        return si1;
    }

    public void setIt_desc_novedad(RichInputText it2) {
        this.it_desc_novedad = it2;
    }

    public RichInputText getIt_desc_novedad() {
        return it_desc_novedad;
    }

    public void setIt_acto_adm(RichInputText it2) {
        this.it_acto_adm = it2;
    }

    public RichInputText getIt_acto_adm() {
        return it_acto_adm;
    }

    public void setId_fecha(RichInputDate id4) {
        this.id_fecha = id4;
    }

    public RichInputDate getId_fecha() {
        return id_fecha;
    }

    public void setIt_observaciones(RichInputText it2) {
        this.it_observaciones = it2;
    }

    public RichInputText getIt_observaciones() {
        return it_observaciones;
    }

    public void setSoc_tipo_id_traspaso(RichSelectOneChoice soc1) {
        this.soc_tipo_id_traspaso = soc1;
    }

    public RichSelectOneChoice getSoc_tipo_id_traspaso() {
        return soc_tipo_id_traspaso;
    }

    public void setSi2(UISelectItems si2) {
        this.si2 = si2;
    }

    public UISelectItems getSi2() {
        return si2;
    }

    public void setIt_id_usuario_traspasa(RichInputText it2) {
        this.it_id_usuario_traspasa = it2;
    }

    public RichInputText getIt_id_usuario_traspasa() {
        return it_id_usuario_traspasa;
    }

    public void setSoc_predio_traspaso(RichSelectOneChoice soc1) {
        this.soc_predio_traspaso = soc1;
    }

    public RichSelectOneChoice getSoc_predio_traspaso() {
        return soc_predio_traspaso;
    }

    public void setSi3(UISelectItems si3) {
        this.si3 = si3;
    }

    public UISelectItems getSi3() {
        return si3;
    }

    public void cb_novedad_actionListener(ActionEvent actionEvent) {
        if(this.permisoNovedad!=null){
            showMessage("Esta pendiente por actualizar la información de una Novedad",
                        FacesMessage.SEVERITY_ERROR);
            return;
        }
        try{
            this.listaTiposModificacion = new ArrayList();                
            this.listaTiposIdentificacion = this.cargarParametro(Categoria.TIPO_DOCUMENTO);
            
            UsuariosAguaDelegate uad = UsuariosAguaDelegate.getInstance();
            List<TipoModificacionTO> lista = uad.getAllTiposModificacion();
            Iterator<TipoModificacionTO> it = lista.iterator();
            while(it.hasNext()){
                TipoModificacionTO t = it.next();
                SelectItem item = new SelectItem(t,t.getNombre());
                this.listaTiposModificacion.add(item);
            }
            showPopup(p_novedad, true);    
        }catch(IdeamException e){
            showMessage(e);
        }
    }
    private void mostrarPrediosUsuario(Lista tipoId, String numeroId){
        try{
            this.listaPrediosTraspaso = new ArrayList();
            AdfFacesContext.getCurrentInstance().addPartialTarget(soc_predio_traspaso);            
            
            UsuarioConectadoTO usuarioConectado = 
                (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
            
            UsuariosAguaDelegate uad = UsuariosAguaDelegate.getInstance();

            usuarioTraspaso = uad.getUsuario(tipoId.getCodigo(),
                                              numeroId,
                                              usuarioConectado.getUsuario().getCodigoAutoridadAmbiental().longValue());
            
            if(usuarioTraspaso==null){
                showMessage("NO_EXISTE_USUARIO",FacesMessage.SEVERITY_ERROR,soc_tipo_id_traspaso);
                return;
            }
            
            List<Predio> listaPredios = uad.getPredios(usuarioTraspaso);
            if(listaPredios==null || listaPredios.size()==0){
                showMessage("USUSARIO_SIN_PREDIOS",FacesMessage.SEVERITY_ERROR,soc_tipo_id_traspaso);
                return;
            }
            
            Iterator<Predio> it = listaPredios.iterator();
            while(it.hasNext()){
                Predio p = it.next();
                SelectItem item = null;
                if(usuarioTraspaso.getTipoUsuario().getCodigo().intValue() == UsuarioAgua.ACUEDUCTO ||
                   usuarioTraspaso.getTipoUsuario().getCodigo().intValue() == UsuarioAgua.MUNICIPIO){
                    item = new SelectItem(p, p.getNombre() + "/" + p.getNombreCentroPoblado());    
                }else{
                    item = new SelectItem(p, p.getCedulaCatastral());    
                }                
                listaPrediosTraspaso.add(item);
            }
            if(usuarioTraspaso.getTipoUsuario().getCodigo().intValue() == UsuarioAgua.ACUEDUCTO ||
               usuarioTraspaso.getTipoUsuario().getCodigo().intValue() == UsuarioAgua.MUNICIPIO){
                soc_predio_traspaso.setLabel("Nombre del Predio y/o Municipio");
            }else{
                soc_predio_traspaso.setLabel("Cédula Catastral al cual se traspasa");
            }
            
            AdfFacesContext.getCurrentInstance().addPartialTarget(soc_predio_traspaso);
        }catch(IdeamException e){
            showMessage(e);
        }
    }
    public void soc_tipo_novedad_valueChangeListener(ValueChangeEvent valueChangeEvent) {
        if (valueChangeEvent.getNewValue()==null){
            this.it_desc_novedad.setValue("");
        }else{
            TipoModificacionTO novedad = 
                (TipoModificacionTO)valueChangeEvent.getNewValue();
            this.it_desc_novedad.setValue(novedad.getDescripcion());                        
            this.soc_tipo_id_traspaso.setVisible(novedad.getCodigo().equalsIgnoreCase("TRA"));
            this.it_id_usuario_traspasa.setVisible(novedad.getCodigo().equalsIgnoreCase("TRA"));
            this.soc_predio_traspaso.setVisible(novedad.getCodigo().equalsIgnoreCase("TRA"));            
        }
        AdfFacesContext.getCurrentInstance().addPartialTarget(it_desc_novedad);
        AdfFacesContext.getCurrentInstance().addPartialTarget(soc_tipo_id_traspaso);
        AdfFacesContext.getCurrentInstance().addPartialTarget(it_id_usuario_traspasa);
        AdfFacesContext.getCurrentInstance().addPartialTarget(soc_predio_traspaso);
    }

    public void soc_tipo_id_traspaso_valueChangeListener(ValueChangeEvent valueChangeEvent) {
        Lista tipoId = (Lista)valueChangeEvent.getNewValue();
        Object numeroId = this.it_id_usuario_traspasa.getValue();
        
        if(tipoId != null && numeroId != null && numeroId.toString().length()>0){
            this.mostrarPrediosUsuario(tipoId, numeroId.toString());
        }
    }

    public void it_id_usuario_traspasa_valueChangeListener(ValueChangeEvent valueChangeEvent) {
        Lista tipoId = (Lista)soc_tipo_id_traspaso.getValue();
        Object numeroId = valueChangeEvent.getNewValue();        
        if(tipoId != null && numeroId != null && numeroId.toString().length()>0){
            this.mostrarPrediosUsuario(tipoId, numeroId.toString());
        }
    }

    public void d_novedad_dialogListener(DialogEvent dialogEvent) {
        boolean errores = false;
        if(soc_tipo_novedad.getValue()==null){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,soc_tipo_novedad);
            errores = true;
        }
        if(it_acto_adm.getValue()==null || it_acto_adm.getValue().toString().length()==0){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,it_acto_adm);
            errores = true;            
        }
        if(id_fecha.getValue()==null || id_fecha.getValue().toString().length()==0){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,id_fecha);
            errores = true;            
        }        
        if(it_observaciones.getValue()==null || it_observaciones.getValue().toString().length()==0){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,it_observaciones);
            errores = true;            
        }                
        if(soc_tipo_novedad.getValue()!=null){
            TipoModificacionTO novedad = (TipoModificacionTO)soc_tipo_novedad.getValue();
            if(novedad.getCodigo().equalsIgnoreCase(TipoModificacionTO.TRASPASO)){                
                if(soc_tipo_id_traspaso.getValue()==null){
                    showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,soc_tipo_id_traspaso);
                    errores = true;                                
                }                
                if(it_id_usuario_traspasa.getValue()==null || it_id_usuario_traspasa.getValue().toString().length()==0){
                    showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,it_id_usuario_traspasa);
                    errores = true;                                
                }
                if(soc_predio_traspaso.getValue()==null){
                    showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,soc_predio_traspaso);
                    errores = true;                                
                }                
            }
        }
        if(errores){
            return;
        }
        
        UsuarioConectadoTO usuarioConectado = 
            (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
        
        TipoModificacionTO novedad = (TipoModificacionTO)soc_tipo_novedad.getValue();
        
        this.permisoNovedad = this.permiso;
        this.permisoNovedad.setTipoNovedad( 
            ((TipoModificacionTO)soc_tipo_novedad.getValue()).getCodigo());
        this.permisoNovedad.setNumeroExpedienteNovedad(
            it_acto_adm.getValue().toString());
        
        Calendar fecha = GregorianCalendar.getInstance();
        fecha.setTime((Date)id_fecha.getValue());
        
        this.permisoNovedad.setFechaExpedicionNovedad(fecha);
        this.permisoNovedad.setObservacionesNovedad(
            it_observaciones.getValue().toString());
        
        this.permiso = new PermisoVertimiento();
        this.permiso.setCodigoAutoridadAmbiental(
            usuarioConectado.getUsuario().getAutoridadAmbiental().getId().longValue());
        this.permiso.setCodigoPredio( 
            this.permisoNovedad.getCodigoPredio());
        this.permiso.setResolucionPermisoVertimiento(
            it_acto_adm.getValue().toString());
        this.permiso.setFechaExpedicionPermiso(fecha);
        
        this.permiso.setNumeroExpediente(
            this.permisoNovedad.getNumeroExpediente());
        
        this.it_numero_expediente.setReadOnly(true);
        this.it_res_permiso.setReadOnly(true);
        this.id_exp_permiso.setReadOnly(true);
        
        if(novedad.getCodigo().equalsIgnoreCase(TipoModificacionTO.REVOCACION)){
            this.permiso.setFechaInicio(fecha);
            this.permiso.setFechaFin((fecha));
        }                                
        
        if (novedad.getCodigo().equalsIgnoreCase(TipoModificacionTO.TRASPASO)){
            this.usuarioAgua = usuarioTraspaso;
            
            Predio p = (Predio)soc_predio_traspaso.getValue();
            this.predio = p;
            
            this.permiso.setCodigoPredio( 
                this.predio.getCodigo());
            AdfFacesContext.getCurrentInstance().addPartialTarget(psl1);      
            
            this.vertimientosTreeModel = null;
            FacesUtils.setInSession("usuarioSeleccionado", this.usuarioAgua);
            FacesUtils.setInSession("predioSeleccionado", this.predio);
            FacesUtils.setInSession("permisoSeleccionado", this.permiso);                        
            
        }
        //Inicio Pilar
        List listaNodosLimpio = new ArrayList();

        TreeNode nodoVertimientosLimpio = new TreeNode(getText("VERTIMIENTOS"), 
                                           "Vertimientos");
        nodoVertimientosLimpio.setData("Vertimientos");
        listaNodosLimpio.add(nodoVertimientosLimpio);
        vertimientosTreeModel = new SpecialTreeModel(listaNodosLimpio, "children");
        
        FacesUtils.removeManagedBeanFromSession("PrediosTreeHandler");//Pilar
        AdfFacesContext.getCurrentInstance().addPartialTarget(panelBox1);
        //Fin Pilar
        
        AdfFacesContext.getCurrentInstance().addPartialTarget(pb_detalle);    
    }
}
