package co.gov.ideam.sirh.usuarios.agua.view;


import co.gov.ideam.sirh.auditoria.model.Auditoria;
import co.gov.ideam.sirh.auditoria.view.AuditoriasDelegate;
import co.gov.ideam.sirh.parametros.model.Categoria;
import co.gov.ideam.sirh.parametros.model.Lista;
import co.gov.ideam.sirh.parametros.view.ParametrosDelegate;
import co.gov.ideam.sirh.seguridad.model.UsuarioConectadoTO;
import co.gov.ideam.sirh.usuarios.agua.model.Captacion;
import co.gov.ideam.sirh.usuarios.agua.model.Concesion;
import co.gov.ideam.sirh.usuarios.agua.model.Predio;
import co.gov.ideam.sirh.usuarios.agua.model.TipoModificacionTO;
import co.gov.ideam.sirh.usuarios.agua.model.UsuarioAgua;
import co.gov.ideam.sirh.usuarios.agua.model.UsuarioAguaTO;
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
import oracle.adf.view.rich.component.rich.output.RichOutputLabel;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.component.rich.output.RichPanelCollection;
import oracle.adf.view.rich.component.rich.output.RichSpacer;
import oracle.adf.view.rich.context.AdfFacesContext;
import oracle.adf.view.rich.event.DialogEvent;

import org.apache.myfaces.trinidad.event.AttributeChangeEvent;
import org.apache.myfaces.trinidad.model.TreeModel;
import org.apache.myfaces.trinidad.model.UploadedFile;


public class DetalleConcesionBean extends StandarBean{
    private RichForm f2;
    private RichDocument d2;
    private RichPanelGroupLayout pgl2;
    private RichSpacer s3;
    private RichCommandLink cl4;
    private RichSpacer s4;
    private RichCommandLink cl5;
    private RichSpacer s7;
    private RichCommandLink cl6;
    private RichSpacer s8;
    private RichOutputText titulo;
    private RichPanelFormLayout pfl1;
    private RichPanelGroupLayout pgl1;
    private RichCommandButton cb_aceptar;
    private RichSpacer s1;
    private RichCommandButton cb_borrar;
    private RichInputText it_expediente;
    private RichInputText it_res_caudal;
    private RichInputDate id_not_caudal;
    private RichInputDate id_exp_caudal;
    private RichInputText it_res_planos;
    private RichInputDate id_not_planos;
    private RichInputDate id_exp_planos;
    private RichInputText it_res_obras;
    private RichInputDate id_not_obras;
    private RichInputDate id_exp_obras;
    private TreeModel captacionessTreeModel;

    private Concesion concesion;
    private String tituloConcesion;
    private Predio predio;
    private String paginaOrigen;
    private String retorno;
    private UploadedFile uploadedFile;
    private ArchivoCargadoTO archivoCargado;
    private UsuarioAgua usuario;
    private UsuarioAguaTO usuarioAguaTo;
    private List listaTiposModificacion;
    private List listaTiposIdentificacion;
    private List listaPrediosTraspaso;
    private Concesion concesionNovedad;
    private UsuarioAgua usuarioTraspaso;

    private RichCommandLink cl1;
    private RichSpacer s5;
    private RichSpacer s6;
    private RichPanelStretchLayout psl3;
    private RichPanelSplitter ps2;
    private RichPanelBox pb_detalle_concesion;
    private RichSpacer s9;
    private RichPopup p_borrar;
    private RichDialog d_borrar;
    private RichOutputText ot1;
    private RichPanelGroupLayout pgl3;
    private RichCommandButton cb_borrar_si;
    private RichSpacer s10;
    private RichCommandButton cb_borrar_no;
    private RichPanelBox panelBox1;
    private RichPanelCollection panelCollection1;
    private RichTree tree1;
    private RichMenu menu1;
    private RichCommandMenuItem commandMenuAddCaptacion;
    private RichCommandLink commandLink1;
    private RichCommandLink cml2;
    private RichInputFile if_archivo;
    private RichMessage m2;
    private RichPanelGroupLayout pgl_archivo;
    private RichCommandLink cl_documento;
    private RichSpacer s2;
    private RichCommandButton cb_borrar_docto;
    private RichPanelGroupLayout pgl5;
    private RichPopup p_borrar_docto;
    private RichDialog d_borrar_docto;
    private RichOutputText ot2;
    private RichInputDate id_inicio;
    private RichInputDate id_fin;
    private RichInputText it_caudal;
    private RichPanelGroupLayout pgl4;
    private RichInputText it1;
    private RichInputText it2;
    private RichInputText it3;
    private RichOutputLabel ol1;
    private RichCommandButton cb_novedad;
    private RichSpacer s11;
    private RichPopup p_novedad;
    private RichDialog d_novedad;
    private RichPanelFormLayout pfl2;
    private RichSelectOneChoice soc_tipo_novedad;
    private UISelectItems si1;
    private RichInputText it_desc_novedad;
    private RichInputText it_acto_adm;
    private RichInputDate id_fecha;
    private RichInputText it_observaciones;
    private RichInputText it_id_usuario_traspasa;
    private RichSelectOneChoice soc_tipo_id_traspaso;
    private UISelectItems si2;
    private RichSelectOneChoice soc_predio_traspaso;
    private UISelectItems si3;
    private RichPanelStretchLayout panelStretchLayout1;

    public DetalleConcesionBean() throws IdeamException {
        FacesUtils.setActiveBean("detalleConcesion",
                                 "Adicionar Del Agua",
                                 UsuariosAguaDelegate.class);
        FacesUtils.removeManagedBeanFromSession("PrediosTreeHandler");
        
        this.load();
    }

    public void load() throws IdeamException {
        try{
            UsuariosAguaDelegate uad = UsuariosAguaDelegate.getInstance();
            ParametrosDelegate pd = ParametrosDelegate.getInstance();
            Object obj = FacesUtils.getFromSession("usuarioSeleccionado");
           
            
            if(obj instanceof UsuarioAgua){
                 this.setUsuario( (UsuarioAgua)obj);
                 Long codigoUsuario=  this.usuario.getCodigo();
                 System.out.println("usuarioAgua----------------"+codigoUsuario);
                 
                
            }  else if(obj instanceof UsuarioAguaTO){
                 this.usuarioAguaTo = (UsuarioAguaTO)obj;
                 Long codigoUsuario=  this.usuarioAguaTo.getCodigo();
                 this.setUsuario( uad.getUsuarioAgua(codigoUsuario)); 
                 System.out.println("usuarioAguaTo----------------"+codigoUsuario);
                
            }else{                
                Long codigoUsuario = (Long)FacesUtils.getFromSession("usuarioSeleccionado"); 
                System.out.println("usuarioSeleccionado es un ID----------------"+codigoUsuario);
                this.setUsuario(uad.getUsuarioAgua(codigoUsuario));                
            }
            FacesUtils.setInSession("usuarioSeleccionado", usuario);
            

            Object objPredio = FacesUtils.getFromSession("predioSeleccionado");
            if(objPredio instanceof Predio){
                this.setPredio((Predio)FacesUtils.getFromSession("predioSeleccionado"));
            }else{
                Long codigoPredio = (Long)FacesUtils.getFromSession("predioSeleccionado");
                System.out.println("codigoPredio es un ID fffffffffff----------------"+codigoPredio);
                
                this.setPredio(uad.getPredio( codigoPredio ));
                FacesUtils.setInSession("predioSeleccionado", predio);
            }

            Object objConcesion = FacesUtils.getFromSession("concesionSeleccionada");
            if(objConcesion instanceof Concesion){
                concesion = (Concesion)FacesUtils.getFromSession("concesionSeleccionada");
            }else{
                Long codigoConcesion = (Long)FacesUtils.getFromSession("concesionSeleccionada");
                concesion = uad.getConcesion(codigoConcesion);
                FacesUtils.setInSession("concesionSeleccionada", concesion);
            }
            paginaOrigen = (String)FacesUtils.getFromSession("paginaOrigen");
            if(concesion==null ||
               concesion.getNumeroExpediente()==null ||
               concesion.getNumeroExpediente().toString().length() == 0){
               this.tituloConcesion = "Nueva";
            }else{
                this.tituloConcesion = concesion.getNumeroExpediente();
            }
            List listaNodos = new ArrayList();
            List listaC = uad.getCaptaciones(concesion);
            this.concesion.setListaCaptaciones(listaC);
            TreeNode nodoCaptaciones = new TreeNode(getText("CAPTACIONES"),
                                               "Captaciones");
            nodoCaptaciones.setData("Captaciones");
            listaNodos.add(nodoCaptaciones);
            if (listaC!=null) {

                Iterator it = listaC.iterator();
                while(it.hasNext()){
                    Captacion captacion = (Captacion)it.next();
                    Lista lista = pd.getLista(captacion.getTipoFuenteCaptacion());
                    TreeNode nodoCaptacion = new TreeNode(getText("CAPTACION")
                                                        +" "+ lista.getValor(),
                                                       "", true, false);
                    nodoCaptacion.setData(captacion);
                    nodoCaptaciones.getChildren().add(nodoCaptacion);
                }
            }  else{
                    TreeNode nodoCaptacion = new TreeNode(getText("NO_HAY_REGISTROS"),
                                                          "captacion",
                                                          false, true);
                    nodoCaptaciones.getChildren().add(nodoCaptacion);
                }
            captacionessTreeModel = new SpecialTreeModel(listaNodos, "children");
        }catch(IdeamException e){
            showMessage(e);
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


    public void setPgl2(RichPanelGroupLayout pgl2) {
        this.pgl2 = pgl2;
    }

    public RichPanelGroupLayout getPgl2() {
        return pgl2;
    }

    public void setS3(RichSpacer s3) {
        this.s3 = s3;
    }

    public RichSpacer getS3() {
        return s3;
    }

    public void setCl4(RichCommandLink cl4) {
        this.cl4 = cl4;
    }

    public RichCommandLink getCl4() {
        return cl4;
    }

    public void setS4(RichSpacer s4) {
        this.s4 = s4;
    }

    public RichSpacer getS4() {
        return s4;
    }

    public void setCl5(RichCommandLink cl5) {
        this.cl5 = cl5;
    }

    public RichCommandLink getCl5() {
        return cl5;
    }

    public void setS7(RichSpacer s7) {
        this.s7 = s7;
    }

    public RichSpacer getS7() {
        return s7;
    }

    public void setCl6(RichCommandLink cl6) {
        this.cl6 = cl6;
    }

    public RichCommandLink getCl6() {
        return cl6;
    }

    public void setS8(RichSpacer s8) {
        this.s8 = s8;
    }

    public RichSpacer getS8() {
        return s8;
    }

    public void setTitulo(RichOutputText ot2) {
        this.titulo = ot2;
    }

    public RichOutputText getTitulo() {
        return titulo;
    }

    public void setPfl1(RichPanelFormLayout pfl1) {
        this.pfl1 = pfl1;
    }

    public RichPanelFormLayout getPfl1() {
        return pfl1;
    }

    public void setPgl1(RichPanelGroupLayout pgl1) {
        this.pgl1 = pgl1;
    }

    public RichPanelGroupLayout getPgl1() {
        return pgl1;
    }

    public void setCb_aceptar(RichCommandButton cb1) {
        this.cb_aceptar = cb1;
    }

    public RichCommandButton getCb_aceptar() {
        return cb_aceptar;
    }

    public void setS1(RichSpacer s1) {
        this.s1 = s1;
    }

    public RichSpacer getS1() {
        return s1;
    }

    public void setCb_borrar(RichCommandButton cb2) {
        this.cb_borrar = cb2;
    }

    public RichCommandButton getCb_borrar() {
        return cb_borrar;
    }

    public void setIt_expediente(RichInputText it1) {
        this.it_expediente = it1;
    }

    public RichInputText getIt_expediente() {
        return it_expediente;
    }

    public void setIt_res_caudal(RichInputText it2) {
        this.it_res_caudal = it2;
    }

    public RichInputText getIt_res_caudal() {
        return it_res_caudal;
    }


    public void setId_not_caudal(RichInputDate id1) {
        this.id_not_caudal = id1;
    }

    public RichInputDate getId_not_caudal() {
        return id_not_caudal;
    }

    public void setId_exp_caudal(RichInputDate id2) {
        this.id_exp_caudal = id2;
    }

    public RichInputDate getId_exp_caudal() {
        return id_exp_caudal;
    }

    public void setIt_res_planos(RichInputText it3) {
        this.it_res_planos = it3;
    }

    public RichInputText getIt_res_planos() {
        return it_res_planos;
    }

    public void setId_not_planos(RichInputDate id3) {
        this.id_not_planos = id3;
    }

    public RichInputDate getId_not_planos() {
        return id_not_planos;
    }

    public void setId_exp_planos(RichInputDate id4) {
        this.id_exp_planos = id4;
    }

    public RichInputDate getId_exp_planos() {
        return id_exp_planos;
    }

    public void setIt_res_obras(RichInputText it4) {
        this.it_res_obras = it4;
    }

    public RichInputText getIt_res_obras() {
        return it_res_obras;
    }

    public void setId_not_obras(RichInputDate id5) {
        this.id_not_obras = id5;
    }

    public RichInputDate getId_not_obras() {
        return id_not_obras;
    }

    public void setId_exp_obras(RichInputDate id6) {
        this.id_exp_obras = id6;
    }

    public RichInputDate getId_exp_obras() {
        return id_exp_obras;
    }

    public Concesion getConcesion() {
        return concesion;
    }

    public void setConcesion(Concesion concesion) {
        this.concesion = concesion;
    }

    public void setCl1(RichCommandLink cl1) {
        this.cl1 = cl1;
    }

    public RichCommandLink getCl1() {
        return cl1;
    }

    public void setS5(RichSpacer s5) {
        this.s5 = s5;
    }

    public RichSpacer getS5() {
        return s5;
    }

    public String getTituloConcesion() {
        return tituloConcesion;
    }

    public void setTituloConcesion(String tituloConcesion) {
        this.tituloConcesion = tituloConcesion;
    }


    public void setS6(RichSpacer s6) {
        this.s6 = s6;
    }

    public RichSpacer getS6() {
        return s6;
    }


    public void setPsl3(RichPanelStretchLayout psl3) {
        this.psl3 = psl3;
    }

    public RichPanelStretchLayout getPsl3() {
        return psl3;
    }

    public void setPs2(RichPanelSplitter ps2) {
        this.ps2 = ps2;
    }

    public RichPanelSplitter getPs2() {
        return ps2;
    }

    public void setPb_detalle_concesion(RichPanelBox pb1) {
        this.pb_detalle_concesion = pb1;
    }

    public RichPanelBox getPb_detalle_concesion() {
        return pb_detalle_concesion;
    }


    public void setS9(RichSpacer s9) {
        this.s9 = s9;
    }

    public RichSpacer getS9() {
        return s9;
    }

    public void cb_aceptar_actionListener(ActionEvent actionEvent) {
        // No se permite modificar si la concesion esta revocada
        if (this.concesion.getTipoNovedad()!=null &&
            this.concesion.getTipoNovedad().equalsIgnoreCase("REV")){
            showMessage("No se permite modificar una concesión que se encuentra Revocada",FacesMessage.SEVERITY_ERROR);
            return;
        }
        // Validar campos obligatorios
        retorno = "";
        boolean  continuar = true;
        if(this.it_expediente.getValue()==null ||
           this.it_expediente.getValue().toString().length()==0){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,it_expediente);
            continuar = false;
        }
        if(this.it_res_caudal.getValue()==null ||
           this.it_res_caudal.getValue().toString().length()==0){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,it_res_caudal);
            continuar = false;
        }
        /*
        if(id_not_caudal.getValue()==null){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,id_not_caudal);
            continuar = false;
        }*/
        if(it_caudal.getValue()==null){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,it_caudal);
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
        if(id_inicio.getValue()!=null && id_fin.getValue()!=null){
            if(id_inicio.getValue() instanceof Date){
                Calendar fecha = GregorianCalendar.getInstance();
                fecha.setTime((Date)this.id_inicio.getValue());

                Calendar fecha2 = GregorianCalendar.getInstance();
                fecha2.setTime((Date)this.id_fin.getValue());

                if (fecha2.before(fecha)){
                    showMessage("error_rango_fechas", FacesMessage.SEVERITY_ERROR, id_fin);
                    return;
                }
            }else if (id_inicio.getValue() instanceof Calendar){
                Calendar fecha = (Calendar)id_inicio.getValue();
                Calendar fecha2 = (Calendar)id_fin.getValue();

                if (fecha2.before(fecha)){
                    showMessage("error_rango_fechas", FacesMessage.SEVERITY_ERROR, id_fin);
                    return;
                }
            }
        }
        if(archivoCargado != null && this.concesion.getArchivo()==null){
            try{
                this.concesion.setArchivo(archivoCargado.getContent());
                this.concesion.setTipoArchivo( archivoCargado.getType() );
            }catch(IdeamException e){
                showMessage(e);
                continuar = false;
            }
        }
        if(continuar){
            try{
                UsuariosAguaDelegate uad = UsuariosAguaDelegate.getInstance();
                Concesion concesionActualizada =
                    uad.registrarConcesion(this.concesion);
                this.concesion = concesionActualizada;
                
                
                try{
                    
                    /** 
                     * Estas variables son necesarias para llenar los datos intrinsecos de la auditoria.
                     */
                     UsuarioConectadoTO usuarioConectado = (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
                    
                    Auditoria auditoria = new Auditoria();
                    auditoria.setIdUsuario(usuarioConectado.getUsuario().getCodigo());
                    auditoria.setOperacion("MODIFICAR");
                    auditoria.setObjeto("CONCESION");
                    auditoria.setMetodo(Thread.currentThread().getStackTrace()[1].getMethodName());
                    auditoria.setClase(this.getClass().getName());
                    //Objeto Afectado (usuario modificado, eliminado o agregado) 
                    auditoria.setIdObjeto(this.concesion.getCodigo());
                   
                    AuditoriasDelegate audDelegate = AuditoriasDelegate.getInstance();
                    audDelegate.setAuditoria(auditoria);
                    
                }catch(Exception e){
                    System.out.println("No se pudo Almacenar la auditoria: ---------------------------------- ");
                    System.out.println(e.getMessage()+"------------------------------------------------------ ");
                }
                
                
                
                
                
                
                if (this.concesionNovedad!=null){
                    uad.registrarConcesion(concesionNovedad);
                }
                
                
                AdfFacesContext.getCurrentInstance().addPartialTarget( pb_detalle_concesion );
                String params[] = {"de la Concesion"};
                showMessage(getText("mensaje_registro_exitoso",
                                    params), FacesMessage.SEVERITY_INFO);

                //retorno = this.paginaOrigen;
            }catch(IdeamException e){
                showMessage(e);
            }
        }
    }

    public String getPaginaOrigen() {
        return paginaOrigen;
    }

    public void setPaginaOrigen(String paginaOrigen) {
        this.paginaOrigen = paginaOrigen;
    }

    public String getRetorno() {
        return retorno;
    }

    public void setRetorno(String retorno) {
        this.retorno = retorno;
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

    public void setOt1(RichOutputText ot1) {
        this.ot1 = ot1;
    }

    public RichOutputText getOt1() {
        return ot1;
    }

    public void cb_borrar_actionListener(ActionEvent actionEvent) {
        // No se permite modificar si la concesion esta revocada
        if (this.concesion.getTipoNovedad()!=null &&
            this.concesion.getTipoNovedad().equalsIgnoreCase("REV")){
            showMessage("No se permite modificar una concesión que se encuentra Revocada",FacesMessage.SEVERITY_ERROR);
            return;
        }        
        showPopup(p_borrar, true);
    }

    public void setPgl3(RichPanelGroupLayout pgl3) {
        this.pgl3 = pgl3;
    }

    public RichPanelGroupLayout getPgl3() {
        return pgl3;
    }

    public void setCb_borrar_si(RichCommandButton cb1) {
        this.cb_borrar_si = cb1;
    }

    public RichCommandButton getCb_borrar_si() {
        return cb_borrar_si;
    }

    public void setS10(RichSpacer s10) {
        this.s10 = s10;
    }

    public RichSpacer getS10() {
        return s10;
    }

    public void setCb_borrar_no(RichCommandButton cb2) {
        this.cb_borrar_no = cb2;
    }

    public RichCommandButton getCb_borrar_no() {
        return cb_borrar_no;
    }

    public void cb_borrar_si_actionListener(ActionEvent actionEvent) {
        try{
            UsuariosAguaDelegate uad = UsuariosAguaDelegate.getInstance();
            uad.borrarConcesion(this.concesion);
            try{
                
                /** 
                 * Estas variables son necesarias para llenar los datos intrinsecos de la auditoria.
                 */
                 UsuarioConectadoTO usuarioConectado = (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
                
                Auditoria auditoria = new Auditoria();
                auditoria.setIdUsuario(usuarioConectado.getUsuario().getCodigo());
                auditoria.setOperacion("BORRAR");
                auditoria.setObjeto("CONCESION");
                auditoria.setMetodo(Thread.currentThread().getStackTrace()[1].getMethodName());
                auditoria.setClase(this.getClass().getName());
                //Objeto Afectado (usuario modificado, eliminado o agregado) 
                auditoria.setIdObjeto(this.concesion.getCodigo());
               
                AuditoriasDelegate audDelegate = AuditoriasDelegate.getInstance();
                audDelegate.setAuditoria(auditoria);
                
            }catch(Exception e){
                System.out.println("No se pudo Almacenar la auditoria: ---------------------------------- ");
                System.out.println(e.getMessage()+"------------------------------------------------------ ");
            }
            
            retorno = this.paginaOrigen;
        }catch(IdeamException e){
            showMessage(e);
        }
    }


    public void cb_borrar_no_actionListener(ActionEvent actionEvent) {
        showPopup(p_borrar, false);
    }

    public void it_expediente_valueChangeListener(ValueChangeEvent valueChangeEvent) {
        // DEterminar si existe una concesion con el mismo numero de expediente
        //Pilar: y en la misma autoridad ambiental
        Object obj = valueChangeEvent.getNewValue();
        if(obj!=null){
            try{
                UsuariosAguaDelegate uad = UsuariosAguaDelegate.getInstance();
                UsuarioConectadoTO usuarioConectado = 
                    (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
                Concesion existe =
                    uad.getConcesionByAutoridadAmbiental(valueChangeEvent.getNewValue().toString(), usuarioConectado.getUsuario().getAutoridadAmbiental().getId().longValue());
                if (existe!=null){
                    this.concesion = existe;
                    this.concesion.setCodigo(null);
                    this.concesion.setCodigoPredio( this.predio.getCodigo() );
                   
                    
                    this.concesion.setCodigoAutoridadAmbiental(usuarioConectado.getUsuario().getAutoridadAmbiental().getId().longValue());
                    AdfFacesContext.getCurrentInstance().addPartialTarget(pb_detalle_concesion);
                }
            }catch(IdeamException e){
                showMessage(e);
            }
        }
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

    public void panelCollection1_attributeChangeListener(AttributeChangeEvent attributeChangeEvent) {
        // Add event code here...
    }

    public void setCaptacionessTreeModel(TreeModel captacionessTreeModel) {
        this.captacionessTreeModel = captacionessTreeModel;
    }

    public TreeModel getCaptacionessTreeModel() {
        return captacionessTreeModel;
    }

    public void setMenu1(RichMenu menu1) {
        this.menu1 = menu1;
    }

    public RichMenu getMenu1() {
        return menu1;
    }

    public void setCommandMenuAddCaptacion(RichCommandMenuItem commandMenuItem1) {
        this.commandMenuAddCaptacion = commandMenuItem1;
    }

    public RichCommandMenuItem getCommandMenuAddCaptacion() {
        return commandMenuAddCaptacion;
    }

    public void commandMenuAddCaptacion_actionListener(ActionEvent actionEvent) {
        FacesUtils.setInSession("concesionSeleccionada", concesion);
        FacesUtils.setInSession("paginaOrigen","detalleConcesion");
    }


    public void setCommandLink1(RichCommandLink commandLink1) {
        this.commandLink1 = commandLink1;
    }

    public RichCommandLink getCommandLink1() {
        return commandLink1;
    }

    public void setCml2(RichCommandLink commandLink2) {
        this.cml2 = commandLink2;
    }

    public RichCommandLink getCml2() {
        return cml2;
    }

    public void cml2_actionListener(ActionEvent actionEvent) {
        String nombreParametro = (String)actionEvent.
                                            getComponent().getAttributes().
                                                        get("nombreParametro");
        Object data = actionEvent.getComponent().
                                    getAttributes().get("valorParametro");
        if(nombreParametro!=null && data != null){
            FacesUtils.setInSession(nombreParametro,  data);
            if(data instanceof String){
                if(data.toString().equals("Captaciones")){
                    if(concesion!=null){
                        FacesUtils.setInSession("concesionSeleccionada", concesion);
                    }
                }
            }
        }
    }

    public void setIf_archivo(RichInputFile if1) {
        this.if_archivo = if1;
    }

    public RichInputFile getIf_archivo() {
        return if_archivo;
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

    public void setM2(RichMessage m2) {
        this.m2 = m2;
    }

    public RichMessage getM2() {
        return m2;
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

    public void setPgl_archivo(RichPanelGroupLayout pgl4) {
        this.pgl_archivo = pgl4;
    }

    public RichPanelGroupLayout getPgl_archivo() {
        return pgl_archivo;
    }

    public void setCl_documento(RichCommandLink cl2) {
        this.cl_documento = cl2;
    }

    public RichCommandLink getCl_documento() {
        return cl_documento;
    }

    public void setS2(RichSpacer s2) {
        this.s2 = s2;
    }

    public RichSpacer getS2() {
        return s2;
    }

    public void setCb_borrar_docto(RichCommandButton cb1) {
        this.cb_borrar_docto = cb1;
    }

    public RichCommandButton getCb_borrar_docto() {
        return cb_borrar_docto;
    }

    public void cl_documento_actionListener(ActionEvent actionEvent) {
        showReport(this.concesion.getArchivo());
    }

    public void cb_borrar_docto_actionListener(ActionEvent actionEvent) {
        showPopup(p_borrar_docto, true);
    }

    public void setPgl5(RichPanelGroupLayout pgl5) {
        this.pgl5 = pgl5;
    }

    public RichPanelGroupLayout getPgl5() {
        return pgl5;
    }

    public void setP_borrar_docto(RichPopup p1) {
        this.p_borrar_docto = p1;
    }

    public RichPopup getP_borrar_docto() {
        return p_borrar_docto;
    }

    public void setD_borrar_docto(RichDialog d1) {
        this.d_borrar_docto = d1;
    }

    public RichDialog getD_borrar_docto() {
        return d_borrar_docto;
    }

    public void setOt2(RichOutputText ot2) {
        this.ot2 = ot2;
    }

    public RichOutputText getOt2() {
        return ot2;
    }

    public void d_borrar_docto_dialogListener(DialogEvent dialogEvent) {
        this.concesion.setArchivo(null);
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.if_archivo);
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.pgl_archivo);
    }

    public UsuarioAgua getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioAgua usuario) {
        this.usuario = usuario;
    }

    public Predio getPredio() {
        return predio;
    }

    public void setPredio(Predio predio) {
        this.predio = predio;
    }

    public void setId_inicio(RichInputDate id1) {
        this.id_inicio = id1;
    }

    public RichInputDate getId_inicio() {
        return id_inicio;
    }

    public void setId_fin(RichInputDate id2) {
        this.id_fin = id2;
    }

    public RichInputDate getId_fin() {
        return id_fin;
    }

    public void setIt_caudal(RichInputText it1) {
        this.it_caudal = it1;
    }

    public RichInputText getIt_caudal() {
        return it_caudal;
    }

    public void setPgl4(RichPanelGroupLayout pgl4) {
        this.pgl4 = pgl4;
    }

    public RichPanelGroupLayout getPgl4() {
        return pgl4;
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

    public void setOl1(RichOutputLabel ol1) {
        this.ol1 = ol1;
    }

    public RichOutputLabel getOl1() {
        return ol1;
    }

    public void setCb_novedad(RichCommandButton cb1) {
        this.cb_novedad = cb1;
    }

    public RichCommandButton getCb_novedad() {
        return cb_novedad;
    }

    public void setS11(RichSpacer s11) {
        this.s11 = s11;
    }

    public RichSpacer getS11() {
        return s11;
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

    public void setIt_desc_novedad(RichInputText it4) {
        this.it_desc_novedad = it4;
    }

    public RichInputText getIt_desc_novedad() {
        return it_desc_novedad;
    }

    public void setIt_acto_adm(RichInputText it4) {
        this.it_acto_adm = it4;
    }

    public RichInputText getIt_acto_adm() {
        return it_acto_adm;
    }

    public void setId_fecha(RichInputDate id1) {
        this.id_fecha = id1;
    }

    public RichInputDate getId_fecha() {
        return id_fecha;
    }

    public void setIt_observaciones(RichInputText it4) {
        this.it_observaciones = it4;
    }

    public RichInputText getIt_observaciones() {
        return it_observaciones;
    }

    public void setIt_id_usuario_traspasa(RichInputText it4) {
        this.it_id_usuario_traspasa = it4;
    }

    public RichInputText getIt_id_usuario_traspasa() {
        return it_id_usuario_traspasa;
    }


    public void cb_novedad_actionListener(ActionEvent actionEvent) {      
        // No se permite modificar si la concesion esta revocada
        if (this.concesion.getTipoNovedad()!=null &&
            this.concesion.getTipoNovedad().equalsIgnoreCase("REV")){
            showMessage("No se permite modificar una concesión que se encuentra Revocada",FacesMessage.SEVERITY_ERROR);
            return;
        }        
        if(this.concesionNovedad!=null){
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
    
        this.concesionNovedad = this.concesion;
        this.concesionNovedad.setTipoNovedad( 
            ((TipoModificacionTO)soc_tipo_novedad.getValue()).getCodigo());
        this.concesionNovedad.setNumeroExpedienteNovedad(
            it_acto_adm.getValue().toString());
        
        Calendar fecha = GregorianCalendar.getInstance();
        fecha.setTime((Date)id_fecha.getValue());
        
        this.concesionNovedad.setFechaExpedicionNovedad(fecha);
        this.concesionNovedad.setObservacionesNovedad(
            it_observaciones.getValue().toString());
        
        this.concesion = new Concesion();
        this.concesion.setCodigoAutoridadAmbiental(
            usuarioConectado.getUsuario().getAutoridadAmbiental().getId().longValue());
        this.concesion.setCodigoPredio( 
            this.concesionNovedad.getCodigoPredio());
        this.concesion.setResolucionCaudal( 
            it_acto_adm.getValue().toString());
        this.concesion.setFechaExpedicionCaudal(fecha);
        this.concesion.setNumeroExpediente( 
            this.concesionNovedad.getNumeroExpediente() );
        
        this.it_expediente.setReadOnly(true);
        this.it_res_caudal.setReadOnly(true);
        this.id_exp_caudal.setReadOnly(true);
        
        if(novedad.getCodigo().equalsIgnoreCase(TipoModificacionTO.REVOCACION)){
            this.concesion.setFechaInicio(fecha);
            this.concesion.setFechaFin((fecha));
        }                                
        
        if (novedad.getCodigo().equalsIgnoreCase(TipoModificacionTO.TRASPASO)){
            this.usuario = usuarioTraspaso;            
                        
            Predio p = (Predio)soc_predio_traspaso.getValue();
            this.predio = p;
            
            this.concesion.setCodigoPredio( 
                this.predio.getCodigo());
            AdfFacesContext.getCurrentInstance().addPartialTarget(psl3);                
            
            this.captacionessTreeModel = null;
            FacesUtils.setInSession("usuarioSeleccionado", this.usuario);
            FacesUtils.setInSession("predioSeleccionado", this.predio);
            FacesUtils.setInSession("concesionSeleccionada", this.concesion);                        
        }
        
        //Inicio Pilar
        List listaNodosLimpio = new ArrayList();
        this.concesion.setListaCaptaciones(null);
        TreeNode nodoCaptacionesLimpio = new TreeNode(getText("CAPTACIONES"),
                                           "Captaciones");
        nodoCaptacionesLimpio.setData("Captaciones");
        listaNodosLimpio.add(nodoCaptacionesLimpio);
        captacionessTreeModel = new SpecialTreeModel(listaNodosLimpio, "children");
        
        FacesUtils.removeManagedBeanFromSession("PrediosTreeHandler");//Pilar
        AdfFacesContext.getCurrentInstance().addPartialTarget(panelBox1);
        //Fin Pilar
        AdfFacesContext.getCurrentInstance().addPartialTarget(pb_detalle_concesion);                
        
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

    public void setPanelStretchLayout1(RichPanelStretchLayout panelStretchLayout1) {
        this.panelStretchLayout1 = panelStretchLayout1;
    }

    public RichPanelStretchLayout getPanelStretchLayout1() {
        return panelStretchLayout1;
    }


    public void setUsuarioAguaTo(UsuarioAguaTO usuarioAguaTo) {
        this.usuarioAguaTo = usuarioAguaTo;
    }

    public UsuarioAguaTO getUsuarioAguaTo() {
        return usuarioAguaTo;
    }
}
