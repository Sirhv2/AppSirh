package co.gov.ideam.sirh.usuarios.agua.view;

import co.gov.ideam.sirh.parametros.model.Lista;
import co.gov.ideam.sirh.parametros.view.ParametrosDelegate;
import co.gov.ideam.sirh.seguridad.model.UsuarioConectadoTO;
import co.gov.ideam.sirh.usuarios.agua.model.Captacion;
import co.gov.ideam.sirh.usuarios.agua.model.Concesion;
import co.gov.ideam.sirh.usuarios.agua.model.Predio;
import co.gov.ideam.sirh.usuarios.agua.model.SubttFunias;
import co.gov.ideam.sirh.usuarios.agua.model.SubttFuniasNivel;
import co.gov.ideam.sirh.usuarios.agua.model.UsuarioAgua;
import co.gov.ideam.sirh.util.ConstantesParametros;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.view.FacesUtils;

import co.gov.ideam.sirh.view.StandarBean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UISelectItems;

import javax.faces.event.ActionEvent;

import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.RichMenu;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputDate;
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
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.component.rich.output.RichPanelCollection;
import oracle.adf.view.rich.component.rich.output.RichSpacer;

import oracle.adf.view.rich.context.AdfFacesContext;

import org.apache.myfaces.trinidad.event.AttributeChangeEvent;
import org.apache.myfaces.trinidad.event.SelectionEvent;

public class AdicionarFuniasNivelBean extends StandarBean{
    
    //usuario en session.
    private UsuarioAgua usuario;
    //predio de la concesion.
    private Predio predio;
    //concesion padre de la captacion.
    private Concesion concesion; 
    //objeto padre del funias.
    private Captacion captacion;
    //objeto padre de los niveles.
    private SubttFunias funias;
    //objeto actual seleccionado
    private SubttFuniasNivel nivelSeleccionado;
    
    //listado desde las categorias
    private List listaTipoNivel;
    //listado desde las categorias
    private List listaMetodoMedida;
    //listado desde las categorias
    private List listaInstrumentos;
    //listado de niveles relacionados.
    private List listaNiveles;
    //listado de horas del día.
    private List listaHora;
    //listado de minutos de una hora.
    private List listaMin;
    //listado de horarios tarde o manana.
    private List listaHorario;
    
    //variable auxiliar para recargar pagina
    private String action;
    
    private RichForm form1;
    private RichDocument document1;
    private RichPanelStretchLayout panelStretchLayout1;
    private RichPanelStretchLayout panelStretchLayout2;
    private RichPanelSplitter panelSplitter1;
    private RichPanelFormLayout panelFormLayout1;
    private RichPanelCollection panelCollection1;
    private RichMenu menu1;
    private RichCommandMenuItem commandMenuItem1;
    private RichCommandMenuItem commandMenuItem2;
    private RichTable tnivel;
    private RichPanelGroupLayout panelGroupLayout1;
    private RichPanelBox panelBox1;

    
    private RichCommandButton cmbAceptar;
    private RichPanelGroupLayout panelGroupLayout5;
    private RichInputDate idFechaMedicion;
    private RichSelectOneChoice socHoraMedicion;
    private UISelectItems siHora1Medicion;
    private RichSelectOneChoice socMinutosMedicion;
    private UISelectItems siMinutosMedicion;
    private RichSelectOneChoice socHorarioMedicion;
    private UISelectItems siHorarioMedicion;
    private RichInputText itProfundidadMedicion;
    private RichInputText itPiezometriaMedicion;
    private RichInputText itNivelMedioMedicion;
    private RichSelectOneChoice socTipoNivelMedicion;
    private UISelectItems siTipoNivelMedicion;
    private RichSelectOneChoice socMetodoMedicion;
    private UISelectItems siMetodoMedicion;
    private RichSelectOneChoice socInstrumentoMedicion;
    private UISelectItems siInstrumentosMedicion;
    private RichInputText itObservacionMedicion;
    private RichPanelGroupLayout panelGroupLayout6;
    private RichPanelGroupLayout panelGroupLayout2;
    private RichSpacer spacer1;
    private RichSpacer spacer2;
    private RichSpacer spacer3;
    
    
    
    //miga de pan
    private RichPanelGroupLayout panelGroupLayout3;
    private RichSpacer spacer4;
    private RichCommandLink commandLink1;
    private RichSpacer spacer5;
    private RichCommandLink commandLink2;
    private RichSpacer spacer6;
    private RichCommandLink commandLink3;
    private RichSpacer spacer7;
    private RichCommandLink commandLink4;
    private RichSpacer spacer8;
    private RichCommandLink commandLink5;
    private RichSpacer spacer9;
    private RichCommandLink commandLink6;
    private RichSpacer spacer10;
    private RichOutputText outputText26;
    private RichOutputText otapellido;
    private RichOutputText otnombre;
    //termina miga de pan

    public AdicionarFuniasNivelBean(){
        FacesUtils.setActiveBean("adicionarFuniasNivelBean",
                                 "Adicionar Nivel Funias", 
                                 UsuariosAguaDelegate.class);
        
        FacesUtils.removeManagedBeanFromSession("adicionarFuniasBean");
        this.load();
    }
    
    public void load(){
        try{

            UsuariosAguaDelegate uad = UsuariosAguaDelegate.getInstance();
            ParametrosDelegate pd = ParametrosDelegate.getInstance(); 
            Object objFunias = FacesUtils.getFromSession("funiasSeleccionado");
            
            if(objFunias instanceof SubttFunias){
                this.funias = (SubttFunias)objFunias;
            }else{                
                Long codigo = (Long)FacesUtils.getFromSession("funiasSeleccionado");                                            
                this.funias = uad.getFunias(codigo);   
            }
            
            this.nivelSeleccionado = new SubttFuniasNivel();
            if(FacesUtils.getFromSession("nivelSeleccionado")!=null){
                this.nivelSeleccionado = (SubttFuniasNivel)FacesUtils.getFromSession("nivelSeleccionado");
                System.out.println("nivel desde session LOAD: "+this.nivelSeleccionado);
            }
            
            //los siguientes son para la miga de pan
            Object objCaptacion = FacesUtils.getFromSession("captacionSeleccionada");
            if(objCaptacion instanceof Captacion){
                this.captacion = (Captacion)objCaptacion;
            }else{                
                Long codigo = (Long)FacesUtils.getFromSession("captacionSeleccionada");                                            
                this.captacion = uad.getCaptacion(codigo);     
                FacesUtils.setInSession("captacionSeleccionada", this.captacion);
            }
            
            Object objUsuario = FacesUtils.getFromSession("usuarioSeleccionado");
            if(objUsuario instanceof UsuarioAgua){            
                this.setUsuario((UsuarioAgua)objUsuario);
            }else{                
                Long codigoUsuario = (Long)FacesUtils.getFromSession("usuarioSeleccionado");
                this.setUsuario(uad.getUsuarioAgua(codigoUsuario));  
                FacesUtils.setInSession("usuarioSeleccionado", this.usuario);
            }
            
            Object objPredio = FacesUtils.getFromSession("predioSeleccionado");
            if(objPredio instanceof Predio){
                this.setPredio((Predio)FacesUtils.getFromSession("predioSeleccionado"));        
            }else{
                Long codigoPredio = (Long)FacesUtils.getFromSession("predioSeleccionado");
                this.setPredio(uad.getPredio( codigoPredio ));
                FacesUtils.setInSession("predioSeleccionado", this.predio);
            }                    
            
            Object objConcesion = FacesUtils.getFromSession("concesionSeleccionada");        
            if(objConcesion instanceof Concesion){
                this.concesion = (Concesion)FacesUtils.getFromSession("concesionSeleccionada");        
            }else{
                Long codigoConcesion = (Long)FacesUtils.getFromSession("concesionSeleccionada");        
                this.concesion = uad.getConcesion(codigoConcesion);
                FacesUtils.setInSession("concesionSeleccionada", this.concesion);
            }
            ///Termina los de la miga de pan
            
            this.listaTipoNivel = 
                this.cargarParametro(ConstantesParametros.
                                                   CATEGORIA_FUNIAS_TIPO_NIVEL);
            
            this.listaMetodoMedida = 
                this.cargarParametro(ConstantesParametros.
                                                   CATEGORIA_FUNIAS_METODO_MEDIDA);
            
            this.listaInstrumentos = 
                this.cargarParametro(ConstantesParametros.
                                                   CATEGORIA_FUNIAS_INSTRUMENTO_MEDICION);
            
            this.listaHora=this.cargarHoras();
            this.listaMin= this.cargarMin();
            this.listaHorario= this.cargarHorario();
            
            //listas seleccionadas en el registro
            if (this.nivelSeleccionado.getTipoNivel()!=null){
                this.nivelSeleccionado.setObjTipoNivel(pd.getLista(this.nivelSeleccionado.getTipoNivel()));
            }
            
            //listas seleccionadas en el registro
            if (this.nivelSeleccionado.getMetodoMedida()!=null){
                this.nivelSeleccionado.setObjMetodoMedida(pd.getLista(this.nivelSeleccionado.getMetodoMedida()));
            }
            
            if (this.nivelSeleccionado.getInstrumento()!=null){
                this.nivelSeleccionado.setObjInstrumentoMedida(pd.getLista(this.nivelSeleccionado.getInstrumento()));
            }
            
            this.listaNiveles = new ArrayList();
            this.listaNiveles = uad.getNivelByFunias(this.funias);
            
        }catch(IdeamException e){
            showMessage(e);
        }
    }
    
    //actualizar
    public void commandMenuItem1_actionListener(ActionEvent actionEvent) {
        this.action = "";
        try{
            if(FacesUtils.getFromSession("nivelSeleccionado")!=null){
                this.nivelSeleccionado = (SubttFuniasNivel)FacesUtils.getFromSession("nivelSeleccionado");
            }
            
            if(this.nivelSeleccionado!=null 
                    && this.nivelSeleccionado.getId()!=null 
                    && this.nivelSeleccionado.getId()>0){
            
                AdfFacesContext.getCurrentInstance().addPartialTarget(this.panelGroupLayout1);
                this.action="nivel";
            }else{
                showMessage(getText("seleccionar_registro"),
                            FacesMessage.SEVERITY_ERROR);
                return;
                    
            }
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
    
    //borrar
    public void commandMenuItem2_actionListener(ActionEvent actionEvent) {
        System.out.println("ENTRA actionListener CMI 2");
        this.action = "";
        try{
            if(FacesUtils.getFromSession("nivelSeleccionado")!=null){
                this.nivelSeleccionado = (SubttFuniasNivel)FacesUtils.getFromSession("nivelSeleccionado");
            }
            
            if(this.nivelSeleccionado!=null 
                    && this.nivelSeleccionado.getId()!=null 
                    && this.nivelSeleccionado.getId()>0){
            
            
                UsuariosAguaDelegate uad = UsuariosAguaDelegate.getInstance();
                uad.deleteNivel(this.nivelSeleccionado);
                this.nivelSeleccionado = new SubttFuniasNivel();
                FacesUtils.removeFromSession("nivelSeleccionado");
                
                this.listaNiveles = new ArrayList();
                this.listaNiveles = uad.getNivelByFunias(this.funias);
                AdfFacesContext.getCurrentInstance().addPartialTarget(this.panelBox1);
                
                this.action="nivel";
            }else{
                showMessage(getText("seleccionar_registro"),
                            FacesMessage.SEVERITY_ERROR);
                return;    
            }         
        }catch(IdeamException e){
            showMessage(e);
        }      
    }

    
    
    public void tnivel_selectionListener(SelectionEvent selectionEvent) {
        System.out.println("ENTRA selectionListener TABLE");
        RichTable tAux = (RichTable)selectionEvent.getSource();
        this.nivelSeleccionado = (SubttFuniasNivel)tAux.getSelectedRowData();
        FacesUtils.setInSession("nivelSeleccionado", this.nivelSeleccionado);
    }
    
    
    public String cmbAceptar_action() {
        System.out.println("ENTRA Action CommandButton");
        try{
            
            //falta hacer las validaciones
            boolean continuar = true;
            
            if(this.idFechaMedicion.getValue()==null){
                showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.idFechaMedicion);       
                continuar = false;
            }
            
            if(this.socHoraMedicion.getValue()==null){
                showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.socHoraMedicion);       
                continuar = false;
            }
            
            if(this.socMinutosMedicion.getValue()==null){
                showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.socMinutosMedicion);       
                continuar = false;
            }
                 
            if(this.socHorarioMedicion.getValue()==null){
                showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.socHorarioMedicion);       
                continuar = false;
            }
            
            if(this.itNivelMedioMedicion.getValue()==null){
                showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.itNivelMedioMedicion);       
                continuar = false;
            }
            
            if(this.socTipoNivelMedicion.getValue()==null){
                showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.socTipoNivelMedicion);       
                continuar = false;
            }
            
            if(this.socMetodoMedicion.getValue()==null){
                showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.socMetodoMedicion);       
                continuar = false;
            }
            
            if(this.socInstrumentoMedicion.getValue()==null){
                showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,this.socInstrumentoMedicion);       
                continuar = false;
            }
            
            if(continuar){
                UsuariosAguaDelegate uad = UsuariosAguaDelegate.getInstance();
                this.save();
                FacesUtils.removeFromSession("nivelSeleccionado");
                this.nivelSeleccionado = new SubttFuniasNivel();
                this.listaNiveles = new ArrayList();
                this.listaNiveles = uad.getNivelByFunias(this.funias);
            }
  
        }catch(IdeamException e){
            showMessage(e);
        }
        
        return "";
    }
    
    
    public void save(){
        System.out.println("ENTRA SAVE");
        try{
            UsuariosAguaDelegate uad = UsuariosAguaDelegate.getInstance();
            
            UsuarioConectadoTO usuarioConectado = 
                (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
            
            SubttFuniasNivel nivel = this.nivelSeleccionado;
            if(nivel==null){
                nivel = new SubttFuniasNivel();
            }
            
            nivel.setCodigoAutoridad(usuarioConectado.getUsuario().
                                   getAutoridadAmbiental().getId().longValue());
            
            nivel.setIdFunias(this.funias.getId());
            
            Calendar fecha = GregorianCalendar.getInstance();
            nivel.setFechaMedicion((this.idFechaMedicion.getValue()!=null)
                                        ?(Calendar)this.idFechaMedicion.getValue()
                                        :null); 
            
            nivel.setHoraMedicion((this.socHoraMedicion.getValue()!=null)
                                  ? new Integer(this.socHoraMedicion.getValue().toString())
                                  :null);
            
            nivel.setMinutoMedicion((this.socMinutosMedicion.getValue()!=null)
                                  ? new Integer(this.socMinutosMedicion.getValue().toString())
                                  :null);
            
            nivel.setPeriodoMedicion((this.socHorarioMedicion.getValue()!=null)
                                  ? this.socHorarioMedicion.getValue().toString()
                                  :null);
            
            nivel.setTipoNivel((this.socTipoNivelMedicion.getValue()!=null) 
                                              ? ((Lista)this.socTipoNivelMedicion.getValue()).getCodigo() 
                                              : null);
            
            nivel.setMetodoMedida((this.socMetodoMedicion.getValue()!=null) 
                                              ? ((Lista)this.socMetodoMedicion.getValue()).getCodigo() 
                                              : null);
            
            nivel.setInstrumento((this.socInstrumentoMedicion.getValue()!=null) 
                                              ? ((Lista)this.socInstrumentoMedicion.getValue()).getCodigo() 
                                              : null);
            
            nivel.setNivelMedio((this.itNivelMedioMedicion.getValue()!=null)
                                       ? Double.parseDouble(""+this.itNivelMedioMedicion.getValue())
                                       : null);
            
            nivel.setNivelPiezometrico((this.itPiezometriaMedicion.getValue()!=null)
                                       ? Double.parseDouble(""+this.itPiezometriaMedicion.getValue())
                                       : null);
            
            nivel.setProfundidad((this.itProfundidadMedicion.getValue()!=null)
                                       ? Double.parseDouble(""+this.itProfundidadMedicion.getValue())
                                       : null);
            
            nivel.setObservacion((this.itObservacionMedicion.getValue()!=null)
                                 ? this.itObservacionMedicion.getValue().toString() 
                                 : null);
            
            if(this.nivelSeleccionado!=null 
                    && this.nivelSeleccionado.getId()!=null 
                    && this.nivelSeleccionado.getId()>0){
                
                uad.updateNivel(nivel);
            }else{
                uad.createFuniasNivel(nivel);
            }
            
            //showPopup(p_registro_exitoso, true);
            
        }catch(IdeamException e){
            showMessage(e);
        }  
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

    public void setPanelSplitter1(RichPanelSplitter panelSplitter1) {
        this.panelSplitter1 = panelSplitter1;
    }

    public RichPanelSplitter getPanelSplitter1() {
        return panelSplitter1;
    }

    public void setPanelFormLayout1(RichPanelFormLayout panelFormLayout1) {
        this.panelFormLayout1 = panelFormLayout1;
    }

    public RichPanelFormLayout getPanelFormLayout1() {
        return panelFormLayout1;
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

    public void setCommandMenuItem2(RichCommandMenuItem commandMenuItem2) {
        this.commandMenuItem2 = commandMenuItem2;
    }

    public RichCommandMenuItem getCommandMenuItem2() {
        return commandMenuItem2;
    }

    public void setTnivel(RichTable table1) {
        this.tnivel = table1;
    }

    public RichTable getTnivel() {
        return tnivel;
    }

    public void setListaTipoNivel(List listaTipoNivel) {
        this.listaTipoNivel = listaTipoNivel;
    }

    public List getListaTipoNivel() {
        return listaTipoNivel;
    }

    public void setListaMetodoMedida(List listaMetodoMedida) {
        this.listaMetodoMedida = listaMetodoMedida;
    }

    public List getListaMetodoMedida() {
        return listaMetodoMedida;
    }

    public void setListaInstrumentos(List listaInstrumentos) {
        this.listaInstrumentos = listaInstrumentos;
    }

    public List getListaInstrumentos() {
        return listaInstrumentos;
    }


    public void setListaNiveles(List listaNiveles) {
        this.listaNiveles = listaNiveles;
    }

    public List getListaNiveles() {
        return listaNiveles;
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

    public void setPanelStretchLayout1(RichPanelStretchLayout panelStretchLayout1) {
        this.panelStretchLayout1 = panelStretchLayout1;
    }

    public RichPanelStretchLayout getPanelStretchLayout1() {
        return panelStretchLayout1;
    }

    public void setListaHora(List listaHora) {
        this.listaHora = listaHora;
    }

    public List getListaHora() {
        return listaHora;
    }

    public void setListaMin(List listaMin) {
        this.listaMin = listaMin;
    }

    public List getListaMin() {
        return listaMin;
    }

    public void setListaHorario(List listaHorario) {
        this.listaHorario = listaHorario;
    }

    public List getListaHorario() {
        return listaHorario;
    }

    public void setCmbAceptar(RichCommandButton commandButton1) {
        this.cmbAceptar = commandButton1;
    }

    public RichCommandButton getCmbAceptar() {
        return cmbAceptar;
    }

    public void setPanelStretchLayout2(RichPanelStretchLayout panelStretchLayout2) {
        this.panelStretchLayout2 = panelStretchLayout2;
    }

    public RichPanelStretchLayout getPanelStretchLayout2() {
        return panelStretchLayout2;
    }

    public void setPanelGroupLayout5(RichPanelGroupLayout panelGroupLayout5) {
        this.panelGroupLayout5 = panelGroupLayout5;
    }

    public RichPanelGroupLayout getPanelGroupLayout5() {
        return panelGroupLayout5;
    }

    public void setIdFechaMedicion(RichInputDate inputDate1111) {
        this.idFechaMedicion = inputDate1111;
    }

    public RichInputDate getIdFechaMedicion() {
        return idFechaMedicion;
    }

    public void setSocHoraMedicion(RichSelectOneChoice socHora11) {
        this.socHoraMedicion = socHora11;
    }

    public RichSelectOneChoice getSocHoraMedicion() {
        return socHoraMedicion;
    }

    public void setSiHora1Medicion(UISelectItems siHora11) {
        this.siHora1Medicion = siHora11;
    }

    public UISelectItems getSiHora1Medicion() {
        return siHora1Medicion;
    }

    public void setSocMinutosMedicion(RichSelectOneChoice socMinutos11) {
        this.socMinutosMedicion = socMinutos11;
    }

    public RichSelectOneChoice getSocMinutosMedicion() {
        return socMinutosMedicion;
    }

    public void setSiMinutosMedicion(UISelectItems siMinutos11) {
        this.siMinutosMedicion = siMinutos11;
    }

    public UISelectItems getSiMinutosMedicion() {
        return siMinutosMedicion;
    }

    public void setSocHorarioMedicion(RichSelectOneChoice socHorario11) {
        this.socHorarioMedicion = socHorario11;
    }

    public RichSelectOneChoice getSocHorarioMedicion() {
        return socHorarioMedicion;
    }

    public void setSiHorarioMedicion(UISelectItems siHorario11) {
        this.siHorarioMedicion = siHorario11;
    }

    public UISelectItems getSiHorarioMedicion() {
        return siHorarioMedicion;
    }

    public void setItProfundidadMedicion(RichInputText itProfundidad11) {
        this.itProfundidadMedicion = itProfundidad11;
    }

    public RichInputText getItProfundidadMedicion() {
        return itProfundidadMedicion;
    }

    public void setItPiezometriaMedicion(RichInputText itPiezometria11) {
        this.itPiezometriaMedicion = itPiezometria11;
    }

    public RichInputText getItPiezometriaMedicion() {
        return itPiezometriaMedicion;
    }

    public void setItNivelMedioMedicion(RichInputText itNivelMedio11) {
        this.itNivelMedioMedicion = itNivelMedio11;
    }

    public RichInputText getItNivelMedioMedicion() {
        return itNivelMedioMedicion;
    }

    public void setSocTipoNivelMedicion(RichSelectOneChoice socTipoNivel11) {
        this.socTipoNivelMedicion = socTipoNivel11;
    }

    public RichSelectOneChoice getSocTipoNivelMedicion() {
        return socTipoNivelMedicion;
    }

    public void setSiTipoNivelMedicion(UISelectItems siTipoNivel11) {
        this.siTipoNivelMedicion = siTipoNivel11;
    }

    public UISelectItems getSiTipoNivelMedicion() {
        return siTipoNivelMedicion;
    }

    public void setSocMetodoMedicion(RichSelectOneChoice socMetodo11) {
        this.socMetodoMedicion = socMetodo11;
    }

    public RichSelectOneChoice getSocMetodoMedicion() {
        return socMetodoMedicion;
    }

    public void setSiMetodoMedicion(UISelectItems siMetodo11) {
        this.siMetodoMedicion = siMetodo11;
    }

    public UISelectItems getSiMetodoMedicion() {
        return siMetodoMedicion;
    }

    public void setSocInstrumentoMedicion(RichSelectOneChoice socInstrumento11) {
        this.socInstrumentoMedicion = socInstrumento11;
    }

    public RichSelectOneChoice getSocInstrumentoMedicion() {
        return socInstrumentoMedicion;
    }

    public void setSiInstrumentosMedicion(UISelectItems siInstrumentos11) {
        this.siInstrumentosMedicion = siInstrumentos11;
    }

    public UISelectItems getSiInstrumentosMedicion() {
        return siInstrumentosMedicion;
    }

    public void setItObservacionMedicion(RichInputText itObservacion11) {
        this.itObservacionMedicion = itObservacion11;
    }

    public RichInputText getItObservacionMedicion() {
        return itObservacionMedicion;
    }

    public void setPanelGroupLayout6(RichPanelGroupLayout panelGroupLayout6) {
        this.panelGroupLayout6 = panelGroupLayout6;
    }

    public RichPanelGroupLayout getPanelGroupLayout6() {
        return panelGroupLayout6;
    }


    public void setPanelGroupLayout2(RichPanelGroupLayout panelGroupLayout2) {
        this.panelGroupLayout2 = panelGroupLayout2;
    }

    public RichPanelGroupLayout getPanelGroupLayout2() {
        return panelGroupLayout2;
    }

    public void setSpacer1(RichSpacer spacer1) {
        this.spacer1 = spacer1;
    }

    public RichSpacer getSpacer1() {
        return spacer1;
    }

    public void setSpacer2(RichSpacer spacer2) {
        this.spacer2 = spacer2;
    }

    public RichSpacer getSpacer2() {
        return spacer2;
    }

    public void setSpacer3(RichSpacer spacer3) {
        this.spacer3 = spacer3;
    }

    public RichSpacer getSpacer3() {
        return spacer3;
    }


    public void setNivelSeleccionado(SubttFuniasNivel nivelSeleccionado) {
        this.nivelSeleccionado = nivelSeleccionado;
    }

    public SubttFuniasNivel getNivelSeleccionado() {
        return nivelSeleccionado;
    }


    public void setAction(String action) {
        this.action = action;
    }

    public String getAction() {
        System.out.println("nivel desde Action: "+this.nivelSeleccionado);
        if(FacesUtils.getFromSession("nivelSeleccionado")!=null){
            System.out.println("nivel desde session Action: "+FacesUtils.getFromSession("nivelSeleccionado"));
        }
        return action;
    }

    public void setPanelGroupLayout3(RichPanelGroupLayout panelGroupLayout3) {
        this.panelGroupLayout3 = panelGroupLayout3;
    }

    public RichPanelGroupLayout getPanelGroupLayout3() {
        return panelGroupLayout3;
    }

    public void setSpacer4(RichSpacer spacer4) {
        this.spacer4 = spacer4;
    }

    public RichSpacer getSpacer4() {
        return spacer4;
    }


    public void setCommandLink1(RichCommandLink commandLink1) {
        this.commandLink1 = commandLink1;
    }

    public RichCommandLink getCommandLink1() {
        return commandLink1;
    }

    public void setSpacer5(RichSpacer spacer5) {
        this.spacer5 = spacer5;
    }

    public RichSpacer getSpacer5() {
        return spacer5;
    }

    public void setCommandLink2(RichCommandLink commandLink2) {
        this.commandLink2 = commandLink2;
    }

    public RichCommandLink getCommandLink2() {
        return commandLink2;
    }

    public void setSpacer6(RichSpacer spacer6) {
        this.spacer6 = spacer6;
    }

    public RichSpacer getSpacer6() {
        return spacer6;
    }

    public void setCommandLink3(RichCommandLink commandLink3) {
        this.commandLink3 = commandLink3;
    }

    public RichCommandLink getCommandLink3() {
        return commandLink3;
    }

    public void setSpacer7(RichSpacer spacer7) {
        this.spacer7 = spacer7;
    }

    public RichSpacer getSpacer7() {
        return spacer7;
    }

    public void setCommandLink4(RichCommandLink commandLink4) {
        this.commandLink4 = commandLink4;
    }

    public RichCommandLink getCommandLink4() {
        return commandLink4;
    }

    public void setSpacer8(RichSpacer spacer8) {
        this.spacer8 = spacer8;
    }

    public RichSpacer getSpacer8() {
        return spacer8;
    }

    public void setCommandLink5(RichCommandLink commandLink5) {
        this.commandLink5 = commandLink5;
    }

    public RichCommandLink getCommandLink5() {
        return commandLink5;
    }

    public void setSpacer9(RichSpacer spacer9) {
        this.spacer9 = spacer9;
    }

    public RichSpacer getSpacer9() {
        return spacer9;
    }

    public void setCommandLink6(RichCommandLink commandLink6) {
        this.commandLink6 = commandLink6;
    }

    public RichCommandLink getCommandLink6() {
        return commandLink6;
    }

    public void setSpacer10(RichSpacer spacer10) {
        this.spacer10 = spacer10;
    }

    public RichSpacer getSpacer10() {
        return spacer10;
    }

    public void setOutputText26(RichOutputText outputText26) {
        this.outputText26 = outputText26;
    }

    public RichOutputText getOutputText26() {
        return outputText26;
    }

    public void setUsuario(UsuarioAgua usuario) {
        this.usuario = usuario;
    }

    public UsuarioAgua getUsuario() {
        return usuario;
    }

    public void setPredio(Predio predio) {
        this.predio = predio;
    }

    public Predio getPredio() {
        return predio;
    }

    public void setConcesion(Concesion concesion) {
        this.concesion = concesion;
    }

    public Concesion getConcesion() {
        return concesion;
    }

    public void setCaptacion(Captacion captacion) {
        this.captacion = captacion;
    }

    public Captacion getCaptacion() {
        return captacion;
    }

    public void setFunias(SubttFunias funias) {
        this.funias = funias;
    }

    public SubttFunias getFunias() {
        return funias;
    }

    public void setOtapellido(RichOutputText otapellido) {
        this.otapellido = otapellido;
    }

    public RichOutputText getOtapellido() {
        return otapellido;
    }

    public void setOtnombre(RichOutputText otnombre) {
        this.otnombre = otnombre;
    }

    public RichOutputText getOtnombre() {
        return otnombre;
    }
}
