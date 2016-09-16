package co.gov.ideam.sirh.porh.view;

import co.gov.ideam.sirh.fuentes.model.FnttFuente;
import co.gov.ideam.sirh.fuentes.model.FnttTramo;
import co.gov.ideam.sirh.parametros.model.Autoridades;
import co.gov.ideam.sirh.porh.model.PorhAvanceMetas;
import co.gov.ideam.sirh.porh.model.PorhParametrosTO;
import co.gov.ideam.sirh.porh.model.PorhPlanes;
import co.gov.ideam.sirh.porh.model.PorhTramosUsos;
import co.gov.ideam.sirh.seguridad.model.UsuarioConectadoTO;
import co.gov.ideam.sirh.usuarios.agua.view.UsuariosAguaDelegate;
import co.gov.ideam.sirh.util.Constantes;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.view.FacesUtils;
import co.gov.ideam.sirh.view.StandarBean;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.faces.application.FacesMessage;

import javax.faces.event.ActionEvent;

import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputDate;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.layout.RichPanelBox;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelSplitter;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.component.rich.nav.RichCommandLink;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.component.rich.output.RichPanelCollection;
import oracle.adf.view.rich.component.rich.output.RichSpacer;

import oracle.adf.view.rich.context.AdfFacesContext;

import org.apache.myfaces.trinidad.event.SelectionEvent;

public class MetasDescontaminacionBean extends StandarBean{
    private RichForm f2;
    private RichDocument d2;
    
    private List<PorhAvanceMetas> listaMetas;
    private List<PorhParametrosTO> listaParametros;
    private PorhPlanes planOrdenamiento;
    private FnttTramo tramo;
    private String nombreFuente;
    private String nombreTramo;
    private PorhParametrosTO parametroSeleccionado;
    private PorhAvanceMetas metaSeleccionada;
    
    private RichPanelStretchLayout psl1;
    private RichPanelGroupLayout pgl1;
    private RichSpacer s4;
    private RichCommandLink cl1;
    private RichSpacer s5;
    private RichCommandLink cl2;
    private RichSpacer s6;
    private RichPanelSplitter ps1;
    private RichPanelBox pb1;
    private RichPanelBox pb_metas;
    private RichPanelStretchLayout psl2;
    private RichPanelCollection pc1;
    private RichTable t_parametros;
    private RichPanelStretchLayout psl3;
    private RichPanelFormLayout pfl1;
    private RichInputDate id_fecha_muestra;
    private RichInputText it_valor;
    private RichOutputText ot_unidad;
    private RichSpacer s1;
    private RichPanelGroupLayout pgl2;
    private RichPanelGroupLayout pgl3;
    private RichCommandButton cb_aceptar;
    private RichSpacer s2;
    private RichPanelCollection pc2;
    private RichTable t_metas;

    public MetasDescontaminacionBean(){
        FacesUtils.setActiveBean("metasDescontaminacion",
                                 "metasDescontaminacion",
                                 UsuariosAguaDelegate.class);        
        this.load();
    }    
    
    private void load(){
        UsuarioConectadoTO usuarioConectado = 
            (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");

        Autoridades autoridadAmbiental = usuarioConectado.getUsuario().getAutoridadAmbiental();
        
        if(autoridadAmbiental==null ||
           autoridadAmbiental.getId().intValue() == Constantes.IDEAM){
            showMessage(getText("NO_EXISTE_AUTORIDAD_AMBIENTAL"),
                        FacesMessage.SEVERITY_ERROR);       
            return;
        }

        setTramo((FnttTramo)FacesUtils.getFromSession("tramoSeleccionado"));        
        FnttFuente fuente = 
            (FnttFuente)FacesUtils.getFromSession("fuenteSeleccionada");

        setNombreFuente(fuente.getNombre());
        setNombreTramo(getTramo().getNombre());
        setPlanOrdenamiento((PorhPlanes)FacesUtils.getFromSession("planSeleccionado"));
        
        try{
            PorhDelegate pd = PorhDelegate.getInstance();
            listaParametros = pd.getParametros(this.getTramo());
        }catch(IdeamException e){
            showMessage(e);
        }
    }
    
    public void t_usos_selectionListener(SelectionEvent selectionEvent) {
        this.parametroSeleccionado = 
            (PorhParametrosTO)t_parametros.getSelectedRowData();        
        this.listaMetas = new ArrayList<PorhAvanceMetas>();
        this.ot_unidad.setValue( null );
        if(parametroSeleccionado!=null){
            try{
                PorhDelegate pd = PorhDelegate.getInstance();
                this.listaMetas = pd.getAvanceMetas( parametroSeleccionado.getObjetivoId() );
                this.ot_unidad.setValue( parametroSeleccionado.getUnidad() );
                
                String params[] = {parametroSeleccionado.getNombreUso(),
                                   parametroSeleccionado.getParametro()};
                String texto = getText("METAS_USO", params);
                
                this.pb_metas.setText( texto );
                this.cb_aceptar.setText( "Adicionar" );
                this.metaSeleccionada = null;
                this.id_fecha_muestra.setValue(null);
                this.it_valor.setValue(null);
                t_metas.setSelectedRowKeys(null);
            }catch(IdeamException e){
                showMessage(e);
            }
        }
        AdfFacesContext.getCurrentInstance().addPartialTarget(pb_metas);
    }    
    
    public void cb_adicionar_avance_actionListener(ActionEvent actionEvent){
        boolean errores = false;
        if(id_fecha_muestra.getValue()==null){
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,id_fecha_muestra);
            errores = true;
        }else{
            // Validar que la fecha sea menor a la fecha del sistema
            Calendar hoy = GregorianCalendar.getInstance();            
            Date fecha = (Date)id_fecha_muestra.getValue();
            if(fecha.getTime() > hoy.getTime().getTime()){
                showMessage("FECHA_MENOR_HOY",FacesMessage.SEVERITY_ERROR,id_fecha_muestra);
                errores = true;                
            }            
        }
        if(it_valor.getValue()==null || it_valor.getValue().toString().length()==0){            
            showMessage("obligatorio",FacesMessage.SEVERITY_ERROR,it_valor);
            errores = true;            
        }
        if(errores){
            return;
        }
        try{
            PorhDelegate pd = PorhDelegate.getInstance();
            
            UsuarioConectadoTO usuarioConectado = 
                (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");

            Autoridades autoridadAmbiental = usuarioConectado.getUsuario().getAutoridadAmbiental();
            
            if(autoridadAmbiental==null ||
               autoridadAmbiental.getId().intValue() == Constantes.IDEAM){
                showMessage(getText("NO_EXISTE_AUTORIDAD_AMBIENTAL"),
                            FacesMessage.SEVERITY_ERROR);       
                return;
            }
            
            PorhAvanceMetas avance = null;
            if(this.metaSeleccionada==null){
                avance = new PorhAvanceMetas();
                //avance.setTramo_uso_objetivo_id( parametroSeleccionado.getObjetivoId() );
                avance.setCodigoAutoridad( autoridadAmbiental.getId().longValue() );
            }else{
                avance = this.metaSeleccionada;
            }
            
            avance.setFecha_muestra((Date)id_fecha_muestra.getValue());
            //avance.setValor( it_valor.getValue().toString() );       
            pd.updateAvanceMeta( avance );                        
            this.listaMetas = pd.getAvanceMetas( parametroSeleccionado.getObjetivoId() );
            
            this.metaSeleccionada = null;
            id_fecha_muestra.setValue(null);
            it_valor.setValue(null);
            AdfFacesContext.getCurrentInstance().addPartialTarget(pb_metas);
        }catch(IdeamException e){
            showMessage(e);
        }
    }
    
    public void t_metas_selectionListener(SelectionEvent selectionEvent){
        this.metaSeleccionada = (PorhAvanceMetas)t_metas.getSelectedRowData();
        if(this.metaSeleccionada != null){           
            this.id_fecha_muestra.setValue( this.metaSeleccionada.getFecha_muestra() );
            this.it_valor.setValue( this.metaSeleccionada.getValor() );
            this.cb_aceptar.setText( "Actualizar" );
        }else{
            this.cb_aceptar.setText( "Adicionar" );
        }
        AdfFacesContext.getCurrentInstance().addPartialTarget(pb_metas);
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

    public List<PorhAvanceMetas> getListaMetas() {
        return listaMetas;
    }

    public void setListaMetas(List<PorhAvanceMetas> listaMetas) {
        this.listaMetas = listaMetas;
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

    public void setS4(RichSpacer s4) {
        this.s4 = s4;
    }

    public RichSpacer getS4() {
        return s4;
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

    public void setCl2(RichCommandLink cl2) {
        this.cl2 = cl2;
    }

    public RichCommandLink getCl2() {
        return cl2;
    }

    public void setS6(RichSpacer s6) {
        this.s6 = s6;
    }

    public RichSpacer getS6() {
        return s6;
    }


    public void setPs1(RichPanelSplitter ps1) {
        this.ps1 = ps1;
    }

    public RichPanelSplitter getPs1() {
        return ps1;
    }

    public void setPb1(RichPanelBox pb1) {
        this.pb1 = pb1;
    }

    public RichPanelBox getPb1() {
        return pb1;
    }

    public void setPb_metas(RichPanelBox pb2) {
        this.pb_metas = pb2;
    }

    public RichPanelBox getPb_metas() {
        return pb_metas;
    }

    public void setPsl2(RichPanelStretchLayout psl2) {
        this.psl2 = psl2;
    }

    public RichPanelStretchLayout getPsl2() {
        return psl2;
    }

    public void setPc1(RichPanelCollection pc1) {
        this.pc1 = pc1;
    }

    public RichPanelCollection getPc1() {
        return pc1;
    }

    public void setT_parametros(RichTable t1) {
        this.t_parametros = t1;
    }

    public RichTable getT_parametros() {
        return t_parametros;
    }

    public List<PorhParametrosTO> getListaParametros() {
        return listaParametros;
    }

    public void setListaParametros(List<PorhParametrosTO> listaParametros) {
        this.listaParametros = listaParametros;
    }

    public PorhPlanes getPlanOrdenamiento() {
        return planOrdenamiento;
    }

    public void setPlanOrdenamiento(PorhPlanes planOrdenamiento) {
        this.planOrdenamiento = planOrdenamiento;
    }

    public FnttTramo getTramo() {
        return tramo;
    }

    public void setTramo(FnttTramo tramo) {
        this.tramo = tramo;
    }

    public String getNombreFuente() {
        return nombreFuente;
    }

    public void setNombreFuente(String nombreFuente) {
        this.nombreFuente = nombreFuente;
    }

    public String getNombreTramo() {
        return nombreTramo;
    }

    public void setNombreTramo(String nombreTramo) {
        this.nombreTramo = nombreTramo;
    }

    public void setPsl3(RichPanelStretchLayout psl3) {
        this.psl3 = psl3;
    }

    public RichPanelStretchLayout getPsl3() {
        return psl3;
    }

    public void setPfl1(RichPanelFormLayout pfl1) {
        this.pfl1 = pfl1;
    }

    public RichPanelFormLayout getPfl1() {
        return pfl1;
    }

    public void setId_fecha_muestra(RichInputDate id1) {
        this.id_fecha_muestra = id1;
    }

    public RichInputDate getId_fecha_muestra() {
        return id_fecha_muestra;
    }

    public void setIt_valor(RichInputText it1) {
        this.it_valor = it1;
    }

    public RichInputText getIt_valor() {
        return it_valor;
    }

    public void setOt_unidad(RichOutputText ot6) {
        this.ot_unidad = ot6;
    }

    public RichOutputText getOt_unidad() {
        return ot_unidad;
    }

    public void setS1(RichSpacer s1) {
        this.s1 = s1;
    }

    public RichSpacer getS1() {
        return s1;
    }

    public void setPgl2(RichPanelGroupLayout pgl2) {
        this.pgl2 = pgl2;
    }

    public RichPanelGroupLayout getPgl2() {
        return pgl2;
    }


    public void setPgl3(RichPanelGroupLayout pgl3) {
        this.pgl3 = pgl3;
    }

    public RichPanelGroupLayout getPgl3() {
        return pgl3;
    }

    public void setCb_aceptar(RichCommandButton cb1) {
        this.cb_aceptar = cb1;
    }

    public RichCommandButton getCb_aceptar() {
        return cb_aceptar;
    }

    public void setS2(RichSpacer s2) {
        this.s2 = s2;
    }

    public RichSpacer getS2() {
        return s2;
    }

    public void setPc2(RichPanelCollection pc2) {
        this.pc2 = pc2;
    }

    public RichPanelCollection getPc2() {
        return pc2;
    }

    public void setT_metas(RichTable t1) {
        this.t_metas = t1;
    }

    public RichTable getT_metas() {
        return t_metas;
    }

    public PorhParametrosTO getParametroSeleccionado() {
        return parametroSeleccionado;
    }

    public void setParametroSeleccionado(PorhParametrosTO parametroSeleccionado) {
        this.parametroSeleccionado = parametroSeleccionado;
    }

    public PorhAvanceMetas getMetaSeleccionada() {
        return metaSeleccionada;
    }

    public void setMetaSeleccionada(PorhAvanceMetas metaSeleccionada) {
        this.metaSeleccionada = metaSeleccionada;
    }
}
