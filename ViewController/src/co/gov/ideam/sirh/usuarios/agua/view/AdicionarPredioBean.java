package co.gov.ideam.sirh.usuarios.agua.view;


import co.gov.ideam.sirh.parametros.model.Categoria;
import co.gov.ideam.sirh.parametros.model.Divipola;

import co.gov.ideam.sirh.seguridad.model.UsuarioConectadoTO;
import co.gov.ideam.sirh.usuarios.agua.model.Predio;

import co.gov.ideam.sirh.usuarios.agua.model.UsuarioAgua;
import co.gov.ideam.sirh.usuarios.agua.model.UsuarioAguaTO;

import co.gov.ideam.sirh.util.IdeamException;

import co.gov.ideam.sirh.view.FacesUtils;
import co.gov.ideam.sirh.view.StandarBean;



import java.util.ArrayList;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UINamingContainer;
import javax.faces.component.UISelectItems;

import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import oracle.adf.view.rich.component.rich.RichDialog;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.layout.RichPanelBox;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.component.rich.output.RichImage;
import oracle.adf.view.rich.component.rich.output.RichMessage;
import oracle.adf.view.rich.component.rich.output.RichOutputLabel;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.component.rich.output.RichSpacer;
import oracle.adf.view.rich.context.AdfFacesContext;

public class AdicionarPredioBean extends StandarBean{
    private RichForm f2;
    private RichDocument d2;
    private RichPanelStretchLayout psl12;
    private RichPanelGroupLayout pgl115;
    private List listaDepartamentos;
    private List listaMunicipios;
    private List listaClasificacionSuelo;
    private List listaSistemasReferencia;    
    private UsuarioAgua usuarioAgua;
    private Predio predio;
    private Divipola departamentoSeleccionado;
    private Divipola municipioSeleccionado;
    private List listaDeptosPredio;
    private List listaMunPredio;    
    private List listaTipoCentroPoblado;

 

   
    private RichPanelGroupLayout pgl6;
    private RichPanelGroupLayout pgl7;

    private  RichPanelGroupLayout pgl11;
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
    private RichInputText it_nombre_predio;
    private RichInputText it_observaciones;
    private RichInputText it_direccion_predio;
    private RichInputText it_cedula_predio;
    private RichSelectOneChoice soc_depto_predio;
    private UISelectItems si9;
    private RichSelectOneChoice soc_municipio_predio;
    private UISelectItems si10;
    private RichSelectOneChoice soc_clasifica_suelo;
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
   private RichInputText  it_nombre_centro_poblado;
    private RichInputText it_area;

    public AdicionarPredioBean(){
        FacesUtils.setActiveBean("adicionarPredio",
                                 "Adicionar Predio",
                                 UsuariosAguaDelegate.class);
        this.load();
    }
    
    public void load(){
        
     it_altitud             =  new RichInputText() ;
     it_matricula           =  new RichInputText() ;
     it_observaciones       =  new RichInputText() ;
     pgl13                   =  new RichPanelGroupLayout();
     it_cedula_predio       =  new RichInputText() ;
     soc_sistema_referencia =  new RichSelectOneChoice() ;
        
        
        
        try{
            UsuarioConectadoTO usuarioConectado = 
                (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
            
            usuarioAgua = new UsuarioAgua();
            predio = new Predio();
            UsuariosAguaDelegate uad = UsuariosAguaDelegate.getInstance();
            Object obj = FacesUtils.getFromSession("usuarioSeleccionado");
            if(obj instanceof UsuarioAgua){
                 this.usuarioAgua = (UsuarioAgua)obj;
                 Long codigoUsuario=  this.usuarioAgua.getCodigo();
                 System.out.println("usuarioAgua----------------"+codigoUsuario);
                 
                
            }  else if(obj instanceof UsuarioAguaTO){
                 this.usuarioAguaTo = (UsuarioAguaTO)obj;
                 Long codigoUsuario=  this.usuarioAguaTo.getCodigo();
                 this.usuarioAgua = uad.getUsuarioAgua(codigoUsuario); 
                 System.out.println("usuarioAguaTo-----ad pre-----------"+codigoUsuario);
                
            }else{                
                Long codigoUsuario = (Long)FacesUtils.getFromSession("usuarioSeleccionado"); 
                System.out.println("usuarioSeleccionado es un ID-------ad pre---------"+codigoUsuario);
                this.usuarioAgua = uad.getUsuarioAgua(codigoUsuario);                
            }
                                    
           
           
            predio.setAutoridadAmbiental(usuarioConectado.getUsuario().
                                         getAutoridadAmbiental());
          
            this.listaClasificacionSuelo = this.cargarParametro(Categoria.CLASIFICACION_SUELO);
            this.listaSistemasReferencia = this.cargarParametro(Categoria.SISTEMAS_REFERENCIA_GEOGRAFICO);
            this.listaTipoCentroPoblado  = this.cargarParametro(Categoria.TIPO_CENTRO_POBLADO);
          
            this.listaDeptosPredio = this.cargarDivipola(null);
            
            
            if(this.usuarioAgua.getTipoUsuario().getCodigo()==2 || this.usuarioAgua.getTipoUsuario().getCodigo() == 136 || this.usuarioAgua.getTipoUsuario().getCodigo()== 956)
            {                
                  
                  this.it_cedula_predio.setVisible(false);
                 AdfFacesContext.getCurrentInstance().addPartialTarget(it_cedula_predio);
                 this.it_observaciones.setVisible(false);
                 AdfFacesContext.getCurrentInstance().addPartialTarget(it_observaciones);    
                 this.soc_sistema_referencia.setVisible(false);
                 AdfFacesContext.getCurrentInstance().addPartialTarget(soc_sistema_referencia);
                 this.it_matricula.setVisible(false);
                 AdfFacesContext.getCurrentInstance().addPartialTarget(it_matricula);    
                 this.pgl13.setVisible(false);
                 AdfFacesContext.getCurrentInstance().addPartialTarget(pgl13);
                 this.it_altitud.setVisible(false);
                 AdfFacesContext.getCurrentInstance().addPartialTarget(it_altitud);
                 
                 
                
                                  
             }
            
            
            
            
            
            
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

    public void setIt_nombre_predio(RichInputText it1) {
        this.it_nombre_predio = it1;
    }

    public RichInputText getIt_nombre_predio() {
        return it_nombre_predio;
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

    public void setSoc_clasifica_suelo(RichSelectOneChoice soc1) {
        this.soc_clasifica_suelo = soc1;
    }

    public RichSelectOneChoice getSoc_clasifica_suelo() {
        return soc_clasifica_suelo;
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

    public Predio getPredio() {
        return predio;
    }

    public void setPredio(Predio predio) {
        this.predio = predio;
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

    public void cb_terminar_actionListener(ActionEvent actionEvent) {
           
        boolean continuar = validarPredio();
        if(continuar){
            guardar();
        }
    }
    public boolean validarPredio(){
        boolean continuar = true;
        // validar datos obligatorios
        if(this.it_nombre_predio.getValue()==null ||
           this.it_nombre_predio.getValue().toString().length()==0){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,it_nombre_predio);
            continuar = false;
        }
        if(this.it_direccion_predio.getValue()==null ||
           this.it_direccion_predio.getValue().toString().length()==0){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,it_direccion_predio);
            continuar = false;
        }
        if(this.soc_depto_predio.getValue()==null){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,soc_depto_predio);
            continuar = false;
        }
        if(this.soc_municipio_predio.getValue()==null){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,soc_municipio_predio);
            continuar = false;
        }        
        
   /*     if(this.it_cedula_predio.getValue()==null ||
           this.it_cedula_predio.getValue().toString().length()==0){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,it_cedula_predio);
            continuar = false;
        }
    try{
            UsuariosAguaDelegate uad = UsuariosAguaDelegate.getInstance();
            UsuarioConectadoTO usuarioConectado = 
                (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
            PrediosTO existe = uad.getPredioTO(
                                this.it_cedula_predio.getValue().toString(),this.it_numero_id.getValue().toString(), usuarioConectado.getUsuario().getAutoridadAmbiental().getId().longValue() );
            if(existe!=null){
                showMessage("CEDULA_EXISTE",FacesMessage.SEVERITY_ERROR,it_cedula_predio);
                continuar = false;
            }
        }catch(IdeamException e){
            showMessage(e);
        }*/
        
        // Se elimina validacion de obligatoriedad por solicitud del usuario
        // funcional con email del 12/12/2013        
    //    if(this.it_matricula.getValue()==null ||
   //        this.it_matricula.getValue().toString().length()==0){
            //showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,it_matricula);
            //continuar = false;
      //  }        
        if(this.soc_clasifica_suelo.getValue()==null){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,soc_clasifica_suelo);
            continuar = false;
        }        
      /*  if(this.soc_sistema_referencia.getValue()==null){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,soc_sistema_referencia);
            continuar = false;
        }        
        if(this.it_altitud.getValue()==null ||
           this.it_altitud.getValue().toString().length()==0){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,it_altitud);
            continuar = false;
        }
        if(this.it_grados.getValue()==null ||
           this.it_grados.getValue().toString().length()==0){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,it_grados);
            continuar = false;
        }        
        if(this.it_minutos.getValue()==null ||
           this.it_minutos.getValue().toString().length()==0){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,it_minutos);
            continuar = false;
        }         
        if(this.it_segundos.getValue()==null ||
           this.it_segundos.getValue().toString().length()==0){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,it_segundos);
            continuar = false;
        }*/               
        return continuar;
    }
    public void guardar(){
        if(this.predio.getNombre()==null ||
           this.predio.getNombre().toString().length() ==0){
            this.predio.setNombre(
                    this.usuarioAgua.getNombre());       
        }
        try{
            UsuariosAguaDelegate usd = UsuariosAguaDelegate.getInstance();
            UsuarioConectadoTO usuarioConectado = 
                (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
            
                System.out.println("puntos---a guardarr----dpto------------->");
                
                    predio.setCodigoUsuario(this.usuarioAgua.getCodigo());
                    predio.setNombre( this.predio.getNombre().toUpperCase());
                  
                    predio.setCodigoDepartamento(new Long (this.predio.getDepartamento().getId()));
                    predio.setCodigoMunicipio(new Long (this.predio.getMunicipio().getId()));
             
                    predio.setCodigoTipoSuelo(this.predio.getCodigoTipoSuelo());
                    predio.setCodigoTipoCentroPoblado(this.predio.getCodigoTipoCentroPoblado());
              
             
               
                System.out.println("puntos-------dpto------------->"+ this.predio.getCodigoDepartamento());
                System.out.println("puntos-------mun------------->"+ this.predio.getCodigoMunicipio());
            Predio pp=  usd.registrarPredio(this.usuarioAgua,this.predio);
              
            pp= usd.getPredio(pp.getCodigo());
            System.out.println("puntos-------------------->"+pp.getNombre());
            FacesUtils.setInSession("predioSeleccionado",pp);
            FacesUtils.setInSession("UsuarioSeleccionado",this.usuarioAgua);
                
        
                showPopup(p_registro_exitoso, true);
            }catch(IdeamException e){
             showMessage(e);
            }   
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
        
        
       this.setAceptarAction("detalleUsuario");
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

    public void cb_mostrar_ciiu_actionListener(ActionEvent actionEvent) {        
        Object obj = FacesUtils.getManagedBeanValue("buscarCiiu");
        if(obj!=null){
            ((BuscarCiiuBean)obj).inicialize();
        }
        AdfFacesContext.getCurrentInstance().addPartialTarget(p_ciiu);
        showPopup(p_ciiu, true);
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


 


    public void soc_depto_predio_valueChangeListener(ValueChangeEvent valueChangeEvent) {
        Divipola depto = (Divipola)valueChangeEvent.getNewValue();
        try{
            if(depto!=null){
                this.listaMunPredio = this.cargarDivipola(depto.getId());                
            }else{
                this.listaMunPredio = new ArrayList();    
            }
        }catch(IdeamException e){
            showMessage(e);
        }
        AdfFacesContext.getCurrentInstance().addPartialTarget(soc_municipio_predio);
    }

    

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

    public void setIt_nombre_centro_poblado(RichInputText it_nombre_centro_poblado) {
        this.it_nombre_centro_poblado = it_nombre_centro_poblado;
    }

    public RichInputText getIt_nombre_centro_poblado() {
        return it_nombre_centro_poblado;
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
}
