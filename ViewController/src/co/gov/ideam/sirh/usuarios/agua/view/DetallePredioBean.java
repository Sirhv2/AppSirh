package co.gov.ideam.sirh.usuarios.agua.view;

import co.gov.ideam.sirh.parametros.model.Categoria;
import co.gov.ideam.sirh.parametros.model.Divipola;
import co.gov.ideam.sirh.parametros.model.Lista;
import co.gov.ideam.sirh.parametros.view.ParametrosDelegate;
import co.gov.ideam.sirh.usuarios.agua.model.Captacion;
import co.gov.ideam.sirh.usuarios.agua.model.Concesion;
import co.gov.ideam.sirh.usuarios.agua.model.PermisoVertimiento;
import co.gov.ideam.sirh.usuarios.agua.model.Predio;
import co.gov.ideam.sirh.usuarios.agua.model.PrediosTO;
import co.gov.ideam.sirh.usuarios.agua.model.PuntoVertimiento;
import co.gov.ideam.sirh.usuarios.agua.model.UsuarioAgua;
import co.gov.ideam.sirh.usuarios.agua.model.UsuarioAguaTO;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.view.FacesUtils;
import co.gov.ideam.sirh.view.StandarBean;

import co.gov.ideam.sirh.view.util.SpecialTreeModel;
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
import oracle.adf.view.rich.component.rich.RichMenu;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.data.RichTree;
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
import oracle.adf.view.rich.component.rich.output.RichOutputLabel;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.component.rich.output.RichPanelCollection;
import oracle.adf.view.rich.component.rich.output.RichSpacer;

import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.adf.view.rich.event.DialogEvent;

import org.apache.myfaces.trinidad.model.TreeModel;

public class DetallePredioBean extends StandarBean{
    private RichForm f2;
    private RichDocument d2;
    private RichPanelStretchLayout psl1;
    private RichPanelGroupLayout pgl2;
    private RichSpacer s3;
    private RichSpacer s4;
    private RichCommandLink cl3;
    
    private UsuarioAgua usuario;
    private Predio predio;
    private List listaDepartamentos;
    private List listaMunicipios;
    private List listaClasificacionSuelo;
    private List listaSistemasReferencia;
    private List listaTipoCentroPoblado;
    private TreeModel concesionesTreeModel;
    private String accionBorrar;
    private Boolean esMunicipio = false;
    private String titulo;
    
    private RichSpacer s1;
    private RichOutputText ot1;
    private RichPanelSplitter ps1;
    private RichPanelStretchLayout psl2;
    private RichPanelGroupLayout pgl1;
    private RichPanelBox pb1;
    private RichPanelGroupLayout pgl3;
    private RichPanelFormLayout pfl1;
    private RichPanelGroupLayout pgl4;
    private RichPanelFormLayout pfl2;
    private RichInputText it_nombre;
    private RichInputText it_direccion;
    private RichSelectOneChoice soc_depto;
    private UISelectItems si1;
    private RichSelectOneChoice soc_municipio;
    private UISelectItems si2;
    private RichInputText it_ced_catastral;
    private RichInputText it_mat_catastral;
    private RichSelectOneChoice soc_tipo_suelo;
    private UISelectItems si3;
    private RichSelectOneChoice soc_sistema_referencia;
    private UISelectItems si4;
    private RichInputText it_altitud;
    private RichInputText it_grados_lat;
    private RichInputText it_minutos_lat;
    private RichInputText it_segundos_lat;
    private RichPanelStretchLayout psl3;
    private RichPanelBox pb2;
    private RichPanelCollection pc1;
    private RichTree t1;
    private RichCommandLink cl1;
    private RichSpacer s5;
    private RichPanelGroupLayout pgl5;
    private RichSpacer s6;
    private RichPanelGroupLayout pgl6;
    private RichCommandButton cb_aceptar;
    private RichCommandLink cl4;
    private RichSpacer s7;
    private RichCommandButton cb_borrar;
    private RichMenu m3;
    private RichCommandMenuItem cmi_adicionar_concesion;
    private RichCommandMenuItem cmi_adicionar_permiso;
    private RichPopup p_borrar;
    private RichDialog d_borrar;
    private RichPanelGroupLayout pgl7;
    private RichOutputText ot_borrar_1;
    private RichOutputText ot_borrar_2;
    private RichPanelGroupLayout pgl8;
    private RichCommandButton cb_borrar_si;
    private RichSpacer s8;
    private RichCommandButton cb_borrar_no;
    private RichInputText it1;
    private RichInputText it_nombre_centro_poblado;
    private RichSelectOneChoice soc_tipo_centro;
    private UISelectItems si5;
    private RichSpacer s9;
    private RichPanelFormLayout pfl3;
    private RichOutputLabel ol1;
    private RichOutputLabel ol2;
    private RichInputText it_grados_lon;
    private RichInputText it_minutos_lon;
    private RichInputText it_segundos_lon;
    private RichSpacer s2;
    private RichCommandMenuItem cmi_adicionar_captacion_sinConc;
    private RichInputText it_area;
  
    //CDONCEL
    private RichCommandMenuItem cmi_adicionar_vert_sinConc;
  

    public DetallePredioBean(){
        FacesUtils.setActiveBean("detallePredio",
                                 "Detalle Predio",
                                 UsuariosAguaDelegate.class);        
        FacesUtils.removeManagedBeanFromSession("UsuariosTreeHandler");
        this.load();
    }
    
    private void load(){
        try{            
            UsuariosAguaDelegate uad = UsuariosAguaDelegate.getInstance();            
            ParametrosDelegate pd = ParametrosDelegate.getInstance();//Pilar
            Object obj = FacesUtils.getFromSession("usuarioSeleccionado");
            if(obj instanceof UsuarioAguaTO){//Pilar Julio 2014
                UsuarioAguaTO u = (UsuarioAguaTO)obj;
                this.usuario = uad.getUsuarioAgua(u.getCodigo());     
            }else if(obj instanceof UsuarioAgua){
                this.usuario = (UsuarioAgua)obj;
            }else{                
                Long codigoUsuario = (Long)FacesUtils.getFromSession("usuarioSeleccionado");                                            
                this.usuario = uad.getUsuarioAgua(codigoUsuario);                
            }            
            FacesUtils.setInSession("usuarioSeleccionado", usuario);           
            obj = FacesUtils.getFromSession("predioSeleccionado");
            if(obj instanceof Predio){
                this.predio = (Predio)obj;
            }else{
                Long codigoPredio = (Long)FacesUtils.getFromSession("predioSeleccionado");                                            
                this.predio = uad.getPredio(codigoPredio);
            }
                        
            this.listaClasificacionSuelo = this.cargarParametro(Categoria.CLASIFICACION_SUELO);
            this.listaSistemasReferencia = this.cargarParametro(Categoria.SISTEMAS_REFERENCIA_GEOGRAFICO);            
            this.listaTipoCentroPoblado  = this.cargarParametro(Categoria.TIPO_CENTRO_POBLADO);
            this.listaDepartamentos = this.cargarDivipola(null);            
            
                        
            if(this.usuario.getTipoUsuario().getCodigo().intValue() != UsuarioAgua.MUNICIPIO &&
               this.usuario.getTipoUsuario().getCodigo().intValue() != UsuarioAgua.ACUEDUCTO &&
               this.usuario.getTipoUsuario().getCodigo().intValue() != UsuarioAgua.MEGAPROYECTO){
                esMunicipio = false;       
                titulo = getText("DETALLE_PREDIO");
            }else{
                esMunicipio = true;
                titulo = getText("DETALLE_MUNICIPIO");
            }
            
            if(this.predio.getDepartamento()!=null){
                this.listaMunicipios = this.cargarDivipola(this.predio.getDepartamento().getId());
            }else{
                this.listaMunicipios = new ArrayList();    
            }
                                    
            List listaC = uad.getConcesiones(this.predio);
            this.predio.setListaConcesiones(listaC);
            
            List listaP = uad.getPermisosVertimiento(this.predio);
            this.predio.setListaPermisos(listaP);
            
            List listaCapSinConc= uad.getCaptacionesSinConcesion(this.predio);
            this.predio.setListaCaptacionesSinConc(listaCapSinConc);
            System.out.println("+++++++++++++++++++++++++listaCapSinConc:"+listaCapSinConc.size());
            List listaNodos = new ArrayList();
            
            TreeNode nodoConcesiones = new TreeNode(getText("CONCESIONES"), 
                                               "Concesiones");                   
            nodoConcesiones.setData("Concesiones");
            listaNodos.add(nodoConcesiones);
            if (predio.getListaConcesiones()!=null &&
                predio.getListaConcesiones().size() > 0){
                    Iterator it = this.predio.getListaConcesiones().iterator();
                    while(it.hasNext()){
                        Concesion con = (Concesion)it.next();
                        TreeNode nodoConsesion = new TreeNode(getText("NUMERO_DE_EXPEDIENTE") + "  " +
                                                              con.getNumeroExpediente(), 
                                                              "Concesion",
                                                              false,
                                                              false);      
                        nodoConsesion.setData(con);
                        nodoConcesiones.getChildren().add(nodoConsesion);                        
                    }                        
            }else{
                TreeNode nodoConsesion = new TreeNode(getText("NO_HAY_REGISTROS"), 
                                                      "Concesion",
                                                      false,
                                                      true);                                           
                nodoConcesiones.getChildren().add(nodoConsesion);
            }
            
            List vertSinRes = new ArrayList();
            TreeNode nodoPermisos = new TreeNode(getText("PERMISOS_VERTIMIENTO"), 
                                               "Permisos");  
            nodoPermisos.setData("Permisos");
            listaNodos.add(nodoPermisos);                    
            if (predio.getListaPermisos()!=null &&
                predio.getListaPermisos().size() > 0){
                Iterator it = this.predio.getListaPermisos().iterator();
                while(it.hasNext()){
                    PermisoVertimiento per = (PermisoVertimiento)it.next();
                  //CDONCEL
                  if (per.getNo_valida() == null){
                    TreeNode nodoPermiso = new TreeNode(getText("NUMERO_DE_EXPEDIENTE") + "  " +
                                                          per.getNumeroExpediente(), 
                                                          "Permiso",
                                                          false,
                                                          false);      
                    nodoPermiso.setData(per);
                    nodoPermisos.getChildren().add(nodoPermiso);                        
                } else 
                    vertSinRes.add(per);
                }                       
            }else{
                TreeNode nodoPermiso = new TreeNode(getText("NO_HAY_REGISTROS"), 
                                                    "Permisos",
                                                    false,
                                                    true);                   
                nodoPermisos.getChildren().add(nodoPermiso);
            }
            //Pilar
            
            TreeNode nodoCaptacinesSinConc = new TreeNode(getText("CAPTACIONESSINCONC"), 
                                               "CaptacionesSinConc");  
            nodoCaptacinesSinConc.setData("CaptacionesSinConc");
            listaNodos.add(nodoCaptacinesSinConc);                    
            if (predio.getListaCaptacionesSinConc()!=null &&
                predio.getListaCaptacionesSinConc().size() > 0){
                Iterator it = this.predio.getListaCaptacionesSinConc().iterator();
                while(it.hasNext()){
                    Captacion capSinConc = (Captacion)it.next();
                    Lista lista = pd.getLista(capSinConc.getTipoFuenteCaptacion());
                    TreeNode nodoCaptacion = new TreeNode(getText("CAPTACIONSINCONC")
                                                        +" "+ lista.getValor(),
                                                       "", false, false);     
                    nodoCaptacion.setData(capSinConc);
                    nodoCaptacinesSinConc.getChildren().add(nodoCaptacion);                        
                }                        
            }else{
                TreeNode nodoCaptacion = new TreeNode(getText("NO_HAY_REGISTROS"), 
                                                    "Captaciones sin resolución",
                                                    false,
                                                    true);                   
                nodoCaptacinesSinConc.getChildren().add(nodoCaptacion);
            }
            //--fin Pilar
          
          //CDONCEL
          TreeNode nodoVertSp = new TreeNode(getText("VERTIMIENTOSINCONC"), "vertSp");  
          nodoVertSp.setData("vertSp");
          listaNodos.add(nodoVertSp);                    
          if (vertSinRes.size() > 0){
            int x = 1;
            Iterator it = vertSinRes.iterator();
            while(it.hasNext()){
                PermisoVertimiento per = (PermisoVertimiento)it.next();
                
              //CDONCEL
              if (per.getNo_valida() != null){
                 PuntoVertimiento pv = new PuntoVertimiento();
                 pv = uad.getVertimientobyPerm(per);
                 String tipo = "";
                 int tv = pv.getTipoVertimiento();
                 if (tv == 594)
                   tipo = "Municipal";
                 else if (tv == 595)
                   tipo = "Residual domestico";
                 else if (tv == 596)
                   tipo = "Residual industrial";
                 else if (tv == 663)
                   tipo = "Otro";
                  //TreeNode nodoVert = new TreeNode(getText("VERTIMIENTOSINCONC") + "  " + tipo + " "+
                  TreeNode nodoVert = new TreeNode("Vertimiento sin resolución " + tipo + " "+
                                                        per.getNumeroExpediente() +"-" +x, 
                                                         "",//"Vertimiento sin resolución",
                                                        false,
                                                        false);      
                  nodoVert.setData(per);
                  nodoVertSp.getChildren().add(nodoVert);     
                  x++;
               } }                   
          }else{
              TreeNode nodoVert = new TreeNode(getText("NO_HAY_REGISTROS"), 
                                                  "Permisos",
                                                  false,
                                                  true);                   
              nodoVertSp.getChildren().add(nodoVert);
          }
          //FIN CDONCEL
            concesionesTreeModel = new SpecialTreeModel(listaNodos, "children");            
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

    public void setPsl1(RichPanelStretchLayout psl1) {
        this.psl1 = psl1;
    }

    public RichPanelStretchLayout getPsl1() {
        return psl1;
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


    public void setS4(RichSpacer s4) {
        this.s4 = s4;
    }

    public RichSpacer getS4() {
        return s4;
    }

    public void setCl3(RichCommandLink cl3) {
        this.cl3 = cl3;
    }

    public RichCommandLink getCl3() {
        return cl3;
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

    public void setS1(RichSpacer s1) {
        this.s1 = s1;
    }

    public RichSpacer getS1() {
        return s1;
    }

    public void setOt1(RichOutputText ot1) {
        this.ot1 = ot1;
    }

    public RichOutputText getOt1() {
        return ot1;
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

    public void setPgl3(RichPanelGroupLayout pgl3) {
        this.pgl3 = pgl3;
    }

    public RichPanelGroupLayout getPgl3() {
        return pgl3;
    }

    public void setPfl1(RichPanelFormLayout pfl1) {
        this.pfl1 = pfl1;
    }

    public RichPanelFormLayout getPfl1() {
        return pfl1;
    }

    public void setPgl4(RichPanelGroupLayout pgl4) {
        this.pgl4 = pgl4;
    }

    public RichPanelGroupLayout getPgl4() {
        return pgl4;
    }


    public void setPfl2(RichPanelFormLayout pfl2) {
        this.pfl2 = pfl2;
    }

    public RichPanelFormLayout getPfl2() {
        return pfl2;
    }

    public void setIt_nombre(RichInputText it1) {
        this.it_nombre = it1;
    }

    public RichInputText getIt_nombre() {
        return it_nombre;
    }

    public void setIt_direccion(RichInputText it2) {
        this.it_direccion = it2;
    }

    public RichInputText getIt_direccion() {
        return it_direccion;
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

    public void setSoc_depto(RichSelectOneChoice soc1) {
        this.soc_depto = soc1;
    }

    public RichSelectOneChoice getSoc_depto() {
        return soc_depto;
    }

    public void setSi1(UISelectItems si1) {
        this.si1 = si1;
    }

    public UISelectItems getSi1() {
        return si1;
    }

    public void setSoc_municipio(RichSelectOneChoice soc2) {
        this.soc_municipio = soc2;
    }

    public RichSelectOneChoice getSoc_municipio() {
        return soc_municipio;
    }

    public void setSi2(UISelectItems si2) {
        this.si2 = si2;
    }

    public UISelectItems getSi2() {
        return si2;
    }

    public void setIt_ced_catastral(RichInputText it3) {
        this.it_ced_catastral = it3;
    }

    public RichInputText getIt_ced_catastral() {
        return it_ced_catastral;
    }

    public void setIt_mat_catastral(RichInputText it4) {
        this.it_mat_catastral = it4;
    }

    public RichInputText getIt_mat_catastral() {
        return it_mat_catastral;
    }

    public void setSoc_tipo_suelo(RichSelectOneChoice soc3) {
        this.soc_tipo_suelo = soc3;
    }

    public RichSelectOneChoice getSoc_tipo_suelo() {
        return soc_tipo_suelo;
    }

    public void setSi3(UISelectItems si3) {
        this.si3 = si3;
    }

    public UISelectItems getSi3() {
        return si3;
    }

    public void setSoc_sistema_referencia(RichSelectOneChoice soc4) {
        this.soc_sistema_referencia = soc4;
    }

    public RichSelectOneChoice getSoc_sistema_referencia() {
        return soc_sistema_referencia;
    }

    public void setSi4(UISelectItems si4) {
        this.si4 = si4;
    }

    public UISelectItems getSi4() {
        return si4;
    }

    public void setIt_altitud(RichInputText it5) {
        this.it_altitud = it5;
    }

    public RichInputText getIt_altitud() {
        return it_altitud;
    }

    public void setIt_grados_lat(RichInputText it6) {
        this.it_grados_lat = it6;
    }

    public RichInputText getIt_grados_lat() {
        return it_grados_lat;
    }

    public void setIt_minutos_lat(RichInputText it7) {
        this.it_minutos_lat = it7;
    }

    public RichInputText getIt_minutos_lat() {
        return it_minutos_lat;
    }

    public void setIt_segundos_lat(RichInputText it8) {
        this.it_segundos_lat = it8;
    }

    public RichInputText getIt_segundos_lat() {
        return it_segundos_lat;
    }

    public void setPsl3(RichPanelStretchLayout psl3) {
        this.psl3 = psl3;
    }

    public RichPanelStretchLayout getPsl3() {
        return psl3;
    }

    public void setPb2(RichPanelBox pb2) {
        this.pb2 = pb2;
    }

    public RichPanelBox getPb2() {
        return pb2;
    }

    public void setPc1(RichPanelCollection pc1) {
        this.pc1 = pc1;
    }

    public RichPanelCollection getPc1() {
        return pc1;
    }

    public TreeModel getConcesionesTreeModel() {
        return concesionesTreeModel;
    }

    public void setConcesionesTreeModel(TreeModel concesionesTreeModel) {
        this.concesionesTreeModel = concesionesTreeModel;
    }

    public void setT1(RichTree t1) {
        this.t1 = t1;
    }

    public RichTree getT1() {
        return t1;
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

    public void setPgl5(RichPanelGroupLayout pgl5) {
        this.pgl5 = pgl5;
    }

    public RichPanelGroupLayout getPgl5() {
        return pgl5;
    }

    public void setS6(RichSpacer s6) {
        this.s6 = s6;
    }

    public RichSpacer getS6() {
        return s6;
    }

    public void setPgl6(RichPanelGroupLayout pgl6) {
        this.pgl6 = pgl6;
    }

    public RichPanelGroupLayout getPgl6() {
        return pgl6;
    }

    public void setCb_aceptar(RichCommandButton cb2) {
        this.cb_aceptar = cb2;
    }

    public RichCommandButton getCb_aceptar() {
        return cb_aceptar;
    }

    public void setCl4(RichCommandLink cl4) {
        this.cl4 = cl4;
    }

    public RichCommandLink getCl4() {
        return cl4;
    }

    public void setS7(RichSpacer s7) {
        this.s7 = s7;
    }

    public RichSpacer getS7() {
        return s7;
    }

    public void setCb_borrar(RichCommandButton cb1) {
        this.cb_borrar = cb1;
    }

    public RichCommandButton getCb_borrar() {
        return cb_borrar;
    }
    
    public void enlaces_actionListener(ActionEvent actionEvent) {
        String nombreParametro = 
            (String)actionEvent.getComponent().getAttributes().get("nombreParametro"); 
        System.out.println("N O M B R E - P A R A M: " + nombreParametro);
        Object data = 
            actionEvent.getComponent().getAttributes().get("valorParametro");        
        if(nombreParametro!=null &&
           data != null){
            FacesUtils.setInSession(nombreParametro,
                                    data);
        }
    }

    public void setM3(RichMenu m3) {
        this.m3 = m3;
    }

    public RichMenu getM3() {
        return m3;
    }

    public void setCmi_adicionar_concesion(RichCommandMenuItem cmi4) {
        this.cmi_adicionar_concesion = cmi4;
    }

    public RichCommandMenuItem getCmi_adicionar_concesion() {
        return cmi_adicionar_concesion;
    }

    public void setCmi_adicionar_permiso(RichCommandMenuItem cmi5) {
        this.cmi_adicionar_permiso = cmi5;
    }

    public RichCommandMenuItem getCmi_adicionar_permiso() {
        return cmi_adicionar_permiso;
    }

    public void cmi_adicionar_concesion_actionListener(ActionEvent actionEvent) {
        Concesion concesion = new Concesion();
        concesion.setCodigoAutoridadAmbiental(this.usuario.getAutoridadAmbiental().getId().longValue());
        concesion.setCodigoPredio(this.predio.getCodigo());
        FacesUtils.setInSession("concesionSeleccionada", concesion);
        FacesUtils.setInSession("paginaOrigen","listaPredios");
    }

    public void soc_depto_valueChangeListener(ValueChangeEvent valueChangeEvent) {
        Divipola depto = (Divipola)valueChangeEvent.getNewValue();
        try{
            if(depto!=null){
                this.listaMunicipios = this.cargarDivipola(depto.getId());                
            }else{
                this.listaMunicipios = new ArrayList();    
            }
        }catch(IdeamException e){
            showMessage(e);
        }
        AdfFacesContext.getCurrentInstance().addPartialTarget(soc_municipio);
    }

    public void cb_aceptar_actionListener(ActionEvent actionEvent) {
        // Valicar campos obligatorios
        boolean continuar = true;
        if(this.usuario.getTipoUsuario().getCodigo().intValue() != UsuarioAgua.MUNICIPIO &&
           this.usuario.getTipoUsuario().getCodigo().intValue() != UsuarioAgua.ACUEDUCTO &&
               this.usuario.getTipoUsuario().getCodigo().intValue() != UsuarioAgua.MEGAPROYECTO){
            if(this.it_nombre.getValue()==null ||
                this.it_nombre.getValue().toString().length()==0){
                showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,it_nombre);
                continuar = false;
            }
            if(this.it_direccion.getValue()==null ||
                this.it_direccion.getValue().toString().length()==0){
                showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,it_direccion);
                continuar = false;
            }
            if(this.soc_depto.getValue()==null) {
                showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,soc_depto);
                continuar = false;
            }
            if(this.soc_municipio.getValue()==null) {
                showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,soc_municipio);
                continuar = false;
            }
            if(this.it_ced_catastral.getValue()==null ||
                this.it_ced_catastral.getValue().toString().length()==0){
                showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,it_ced_catastral);
                continuar = false;
            }        
            /* Se elimina validacion segun Solicitud de Pilar Galindo
            if(this.it_mat_catastral.getValue()==null ||
                this.it_mat_catastral.getValue().toString().length()==0){
                showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,it_mat_catastral);
                continuar = false;
            } */
            
            if(this.soc_tipo_suelo.getValue()==null) {
                showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,soc_tipo_suelo);
                continuar = false;
            } 
            /*
            if(this.soc_sistema_referencia.getValue()==null) {
                showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,soc_sistema_referencia);
                continuar = false;
            }        
            if(this.it_altitud.getValue()==null){
                showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,it_altitud);
                continuar = false;
            }
            if(this.it_grados_lat.getValue()==null){
                showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,it_grados_lat);
                continuar = false;
            }
            if(this.it_minutos_lat.getValue()==null){
                showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,it_minutos_lat);
                continuar = false;
            }
            if(this.it_segundos_lat.getValue()==null){
                showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,it_segundos_lat);
                continuar = false;
            }
            
            if(this.it_grados_lon.getValue()==null){
                showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,it_grados_lon);
                continuar = false;
            }
            if(this.it_minutos_lon.getValue()==null){
                showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,it_minutos_lon);
                continuar = false;
            }
            if(this.it_segundos_lon.getValue()==null){
                showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,it_segundos_lon);
                continuar = false;
            }*/
            
        }else{
            if(this.it_nombre.getValue()==null ||
                this.it_nombre.getValue().toString().length()==0){
                showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,it_nombre);
                continuar = false;
            }
            if(this.soc_depto.getValue()==null) {
                showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,soc_depto);
                continuar = false;
            }
            if(this.soc_municipio.getValue()==null) {
                showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,soc_municipio);
                continuar = false;
            }
            if(this.soc_tipo_suelo.getValue()==null) {
                showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,soc_tipo_suelo);
                continuar = false;
            }        
            if(this.soc_tipo_centro.getValue()==null) {
                showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,soc_tipo_centro);
                continuar = false;
            }        
            if(this.it_nombre_centro_poblado.getValue()==null ||
                this.it_nombre_centro_poblado.getValue().toString().length()==0){
                showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,it_nombre_centro_poblado);
                continuar = false;
            }

        }
        if(continuar){
            try{
                UsuariosAguaDelegate uad = UsuariosAguaDelegate.getInstance();
                
                if(this.usuario.getTipoUsuario().getCodigo().intValue() != UsuarioAgua.MUNICIPIO &&
                   this.usuario.getTipoUsuario().getCodigo().intValue() != UsuarioAgua.ACUEDUCTO &&
               this.usuario.getTipoUsuario().getCodigo().intValue() != UsuarioAgua.MEGAPROYECTO){
                
                    PrediosTO existe = uad.getPredioTO(this.it_ced_catastral.getValue().toString(), this.usuario.getNumeroDocumento(), this.usuario.getAutoridadAmbiental().getId().longValue() );
                    if(existe!=null && 
                        existe.getCodigo().longValue() != this.predio.getCodigo().longValue()){
                        showMessage("CEDULA_EXISTE",FacesMessage.SEVERITY_ERROR,it_ced_catastral);
                        return;
                    }                
                }                
                uad.updatePredio(this.usuario, this.predio);
                String params[] = {"del predio"};
                showMessage(getText("mensaje_registro_exitoso",
                                    params), FacesMessage.SEVERITY_INFO);
            }catch(IdeamException e){
                showMessage(e);
            }
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


    public void setPgl7(RichPanelGroupLayout pgl7) {
        this.pgl7 = pgl7;
    }

    public RichPanelGroupLayout getPgl7() {
        return pgl7;
    }

    public void setOt_borrar_1(RichOutputText ot3) {
        this.ot_borrar_1 = ot3;
    }

    public RichOutputText getOt_borrar_1() {
        return ot_borrar_1;
    }

    public void setOt_borrar_2(RichOutputText ot4) {
        this.ot_borrar_2 = ot4;
    }

    public RichOutputText getOt_borrar_2() {
        return ot_borrar_2;
    }

    public void cb_borrar_actionListener(ActionEvent actionEvent) {
        try{
            UsuariosAguaDelegate uad = UsuariosAguaDelegate.getInstance();
            List listaConcesiones = uad.getConcesiones(this.predio);
            List listaPermisos = uad.getPermisosVertimiento(this.predio);

            String nombrePredio = this.predio.getNombre() != null &&
                                  this.predio.getNombre().length() > 0 ?
                this.predio.getNombre() : "-Sin Nombre-";
            
            String params1[] = {nombrePredio};
            this.ot_borrar_1.setValue( getText("MENSAJE_BORRAR_PREDIO", params1) );
            
            this.ot_borrar_2.setValue( "" );
            if(listaConcesiones!=null && listaPermisos!=null){
                if(listaConcesiones.size()>0 && listaPermisos.size()>0){
                    String params2[] = {"" + listaConcesiones.size(),
                                        "" + listaPermisos.size()};
                    this.ot_borrar_2.setValue( getText("MENSAJE_BORRAR_PREDIO_DETALLE_TOTAL",
                                                       params2) );
                }else{
                    if(listaConcesiones.size()>0){
                        String params2[] = {"" + listaConcesiones.size()};
                        this.ot_borrar_2.setValue( getText("MENSAJE_BORRAR_PREDIO_DETALLE_CONCESIONES",
                                                           params2) );
                    }else if(listaPermisos.size()>0){
                        String params2[] = {"" + listaPermisos.size()};
                        this.ot_borrar_2.setValue( getText("MENSAJE_BORRAR_PREDIO_DETALLE_PERMISOS",
                                                           params2) );
                    }
                }
            }else{
                this.ot_borrar_2.setValue( "" );
            }
            
            AdfFacesContext.getCurrentInstance().addPartialTarget(ot_borrar_1);
            AdfFacesContext.getCurrentInstance().addPartialTarget(ot_borrar_2);
            showPopup(p_borrar, true);
        }catch(IdeamException e){
            showMessage(e);
        }
    }

    public void setPgl8(RichPanelGroupLayout pgl8) {
        this.pgl8 = pgl8;
    }

    public RichPanelGroupLayout getPgl8() {
        return pgl8;
    }

    public void setCb_borrar_si(RichCommandButton cb1) {
        this.cb_borrar_si = cb1;
    }

    public RichCommandButton getCb_borrar_si() {
        return cb_borrar_si;
    }

    public void setS8(RichSpacer s8) {
        this.s8 = s8;
    }

    public RichSpacer getS8() {
        return s8;
    }

    public void setCb_borrar_no(RichCommandButton cb2) {
        this.cb_borrar_no = cb2;
    }

    public RichCommandButton getCb_borrar_no() {
        return cb_borrar_no;
    }

    public void cb_borrar_si_actionListener(ActionEvent actionEvent) {
        accionBorrar = "";
        try{
            UsuariosAguaDelegate uad = UsuariosAguaDelegate.getInstance();
            uad.borrarPredio(this.predio);
            showMessage(getText("MENSAJE_BORRAR_PREDIO_EXITOSO"));
            accionBorrar = "detalleUsuario";
        }catch(IdeamException e){
            showMessage(e);
        }
    }

    public String getAccionBorrar() {
        return accionBorrar;
    }

    public void setAccionBorrar(String accionBorrar) {
        this.accionBorrar = accionBorrar;
    }

    public void cb_borrar_no_actionListener(ActionEvent actionEvent) {
        showPopup(p_borrar, false);
    }

    public void cmi_adicionar_permiso_actionListener(ActionEvent actionEvent) {
        PermisoVertimiento permiso = new PermisoVertimiento();
        permiso.setCodigoAutoridadAmbiental(this.usuario.getAutoridadAmbiental().getId().longValue());
        permiso.setCodigoPredio(this.predio.getCodigo());
        FacesUtils.setInSession("permisoSeleccionado", permiso);
        FacesUtils.setInSession("paginaOrigen","listaPredios");
    }

    public void setIt1(RichInputText it1) {
        this.it1 = it1;
    }

    public RichInputText getIt1() {
        return it1;
    }

    public Boolean getEsMunicipio() {
        return esMunicipio;
    }

    public void setEsMunicipio(Boolean esMunicipio) {
        this.esMunicipio = esMunicipio;
    }

    public void setIt_nombre_centro_poblado(RichInputText it2) {
        this.it_nombre_centro_poblado = it2;
    }

    public RichInputText getIt_nombre_centro_poblado() {
        return it_nombre_centro_poblado;
    }

    public List getListaTipoCentroPoblado() {
        return listaTipoCentroPoblado;
    }

    public void setListaTipoCentroPoblado(List listaTipoCentroPoblado) {
        this.listaTipoCentroPoblado = listaTipoCentroPoblado;
    }

    public void setSoc_tipo_centro(RichSelectOneChoice soc1) {
        this.soc_tipo_centro = soc1;
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

    public void setS9(RichSpacer s9) {
        this.s9 = s9;
    }

    public RichSpacer getS9() {
        return s9;
    }

    public void setPfl3(RichPanelFormLayout pfl3) {
        this.pfl3 = pfl3;
    }

    public RichPanelFormLayout getPfl3() {
        return pfl3;
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

    public void setIt_grados_lon(RichInputText it2) {
        this.it_grados_lon = it2;
    }

    public RichInputText getIt_grados_lon() {
        return it_grados_lon;
    }

    public void setIt_minutos_lon(RichInputText it3) {
        this.it_minutos_lon = it3;
    }

    public RichInputText getIt_minutos_lon() {
        return it_minutos_lon;
    }

    public void setIt_segundos_lon(RichInputText it4) {
        this.it_segundos_lon = it4;
    }

    public RichInputText getIt_segundos_lon() {
        return it_segundos_lon;
    }

    public void setS2(RichSpacer s2) {
        this.s2 = s2;
    }

    public RichSpacer getS2() {
        return s2;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public void setCmi_adicionar_captacion_sinConc(RichCommandMenuItem commandMenuItem1) {
        this.cmi_adicionar_captacion_sinConc = commandMenuItem1;
    }

    public RichCommandMenuItem getCmi_adicionar_captacion_sinConc() {
        return cmi_adicionar_captacion_sinConc;
    }

    public void cmi_adicionar_captacion_sinConc_actionListener(ActionEvent actionEvent) {//Pilar
        // Add event code here...
        
        FacesUtils.setInSession("paginaOrigen","detallePredio");
        FacesUtils.setInSession("predioConCaptacionesSinConc",this.predio);
    }
    
    //CDONCEL
    public void cmi_adicionar_vertimiento_sinConc_actionListener(ActionEvent actionEvent) {
        System.out.println("++++++++++++ H O L A +++++++++++++++++");
        FacesUtils.setInSession("paginaOrigen","detallePredio");
        FacesUtils.setInSession("predioConVertimientoSinConc",this.predio);
    }
    //FIN CDONCEL

    public void setIt_area(RichInputText inputText1) {
        this.it_area = inputText1;
    }

    public RichInputText getIt_area() {
        return it_area;
    }

  public RichCommandMenuItem getCmi_adicionar_vert_sinConc() {
    return cmi_adicionar_vert_sinConc;
  }

  public void setCmi_adicionar_vert_sinConc(RichCommandMenuItem cmi_adicionar_vert_sinConc) {
    this.cmi_adicionar_vert_sinConc = cmi_adicionar_vert_sinConc;
  }
}
