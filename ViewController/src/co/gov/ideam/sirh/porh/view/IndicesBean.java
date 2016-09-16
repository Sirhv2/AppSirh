package co.gov.ideam.sirh.porh.view;

import co.gov.ideam.sirh.fuentes.model.FnttFuente;
import co.gov.ideam.sirh.fuentes.model.FnttTramo;
import co.gov.ideam.sirh.parametros.model.Autoridades;
import co.gov.ideam.sirh.parametros.model.Categoria;
import co.gov.ideam.sirh.porh.model.PorhIndices;
import co.gov.ideam.sirh.porh.model.PorhPlanes;
import co.gov.ideam.sirh.porh.model.PorhSeguimientoIndices;
import co.gov.ideam.sirh.seguridad.model.UsuarioConectadoTO;
import co.gov.ideam.sirh.util.Constantes;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.view.FacesUtils;
import co.gov.ideam.sirh.view.StandarBean;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;

import javax.faces.event.ActionEvent;

import oracle.adf.view.rich.component.rich.RichDialog;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.RichPopup;
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

import oracle.adf.view.rich.event.DialogEvent;

import org.apache.myfaces.trinidad.event.SelectionEvent;

public class IndicesBean extends StandarBean{
    private RichForm f2;
    private RichDocument d2;
    private RichPanelStretchLayout psl1;
    private RichPanelGroupLayout pgl1;
    private RichSpacer s4;
    private RichCommandLink cl1;
    private RichSpacer s5;
    private RichCommandLink cl2;

    private FnttTramo tramo;
    private String nombreFuente;
    private String nombreTramo;
    private PorhPlanes planOrdenamiento;
    private List<PorhIndices> listaIndicadores;
    private PorhIndices indicadorSeleccionado;
    private List<PorhSeguimientoIndices> listaSeguimiento;
    private PorhSeguimientoIndices seguimientoSeleccionado;
    
    private RichPanelSplitter ps1;
    private RichPanelBox pb_indicadores;
    private RichPanelStretchLayout psl2;
    private RichPanelFormLayout pfl1;
    private RichInputText it_nombre;
    private RichPanelFormLayout pfl2;
    private RichOutputText ot1;
    private RichInputText it_meta_cp;
    private RichPanelGroupLayout pgl3;
    private RichPanelGroupLayout pgl2;
    private RichInputDate id_fecha_cp;
    private RichPanelGroupLayout pgl4;
    private RichPanelGroupLayout pgl5;
    private RichSpacer s1;
    private RichPanelGroupLayout pgl6;
    private RichPanelGroupLayout pgl7;
    private RichOutputText ot2;
    private RichPanelFormLayout pfl3;
    private RichInputText it_meta_mp;
    private RichInputDate id_fecha_mp;
    private RichSpacer s2;
    private RichPanelGroupLayout pgl8;
    private RichPanelGroupLayout pgl9;
    private RichOutputText ot3;
    private RichPanelFormLayout pfl4;
    private RichInputText it_meta_lp;
    private RichInputDate id_fecha_lp;
    private RichSpacer s3;
    private RichSpacer s7;
    private RichPanelGroupLayout pgl10;
    private RichCommandButton cb_adicionar_indicador;
    private RichSpacer s8;
    private RichSpacer s9;
    private RichPanelCollection pc1;
    private RichTable t_indicadores;
    private RichPanelBox pb_avance;
    private RichPanelStretchLayout psl3;
    private RichPanelGroupLayout pgl11;
    private RichPanelFormLayout pfl5;
    private RichInputText it_nombre_seg;
    private RichSpacer s10;
    private RichPanelGroupLayout pgl12;
    private RichSpacer s11;
    private RichPanelGroupLayout pgl13;
    private RichSpacer s12;
    private RichCommandButton cb_adicionar_seguimiento;
    private RichSpacer s13;
    private RichPanelGroupLayout pgl14;
    private RichPanelGroupLayout pgl15;
    private RichOutputText ot11;
    private RichPanelFormLayout pfl6;
    private RichInputText it_seg_meta_cp;
    private RichInputDate id_seg_fecha_cp;
    private RichPanelCollection pc2;
    private RichTable t_seguimiento;
    private RichSpacer spacer1;
    private RichCommandButton cb_borrar_indicador;
    private RichPopup p_borrar_indicador;
    private RichPanelGroupLayout pg46;
    private RichDialog d_borrar_indicador;
    private RichOutputText ot40;
    private boolean mostrarBotonBorrar=false;

    public IndicesBean(){
        FacesUtils.setActiveBean("indices",
                                 "indices",
                                 PorhDelegate.class);        
        this.load();
    }
    
    public void load(){
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
            listaIndicadores = pd.getIndicadores(this.tramo,
                                                 planOrdenamiento,
                                                 autoridadAmbiental.getId().longValue());
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

    public PorhPlanes getPlanOrdenamiento() {
        return planOrdenamiento;
    }

    public void setPlanOrdenamiento(PorhPlanes planOrdenamiento) {
        this.planOrdenamiento = planOrdenamiento;
    }

    public void setPs1(RichPanelSplitter ps1) {
        this.ps1 = ps1;
    }

    public RichPanelSplitter getPs1() {
        return ps1;
    }

    public void setPb_indicadores(RichPanelBox pb1) {
        this.pb_indicadores = pb1;
    }

    public RichPanelBox getPb_indicadores() {
        return pb_indicadores;
    }

    public void setPsl2(RichPanelStretchLayout psl2) {
        this.psl2 = psl2;
    }

    public RichPanelStretchLayout getPsl2() {
        return psl2;
    }

    public void setPfl1(RichPanelFormLayout pfl1) {
        this.pfl1 = pfl1;
    }

    public RichPanelFormLayout getPfl1() {
        return pfl1;
    }

    public void setIt_nombre(RichInputText it1) {
        this.it_nombre = it1;
    }

    public RichInputText getIt_nombre() {
        return it_nombre;
    }


    public void setPfl2(RichPanelFormLayout pfl2) {
        this.pfl2 = pfl2;
    }

    public RichPanelFormLayout getPfl2() {
        return pfl2;
    }


    public void setOt1(RichOutputText ot1) {
        this.ot1 = ot1;
    }

    public RichOutputText getOt1() {
        return ot1;
    }

    public void setIt_meta_cp(RichInputText it2) {
        this.it_meta_cp = it2;
    }

    public RichInputText getIt_meta_cp() {
        return it_meta_cp;
    }

    public void setPgl3(RichPanelGroupLayout pgl3) {
        this.pgl3 = pgl3;
    }

    public RichPanelGroupLayout getPgl3() {
        return pgl3;
    }

    public void setPgl2(RichPanelGroupLayout pgl2) {
        this.pgl2 = pgl2;
    }

    public RichPanelGroupLayout getPgl2() {
        return pgl2;
    }

    public void setId_fecha_cp(RichInputDate id1) {
        this.id_fecha_cp = id1;
    }

    public RichInputDate getId_fecha_cp() {
        return id_fecha_cp;
    }

    public void setPgl4(RichPanelGroupLayout pgl4) {
        this.pgl4 = pgl4;
    }

    public RichPanelGroupLayout getPgl4() {
        return pgl4;
    }

    public void setPgl5(RichPanelGroupLayout pgl5) {
        this.pgl5 = pgl5;
    }

    public RichPanelGroupLayout getPgl5() {
        return pgl5;
    }

    public void setS1(RichSpacer s1) {
        this.s1 = s1;
    }

    public RichSpacer getS1() {
        return s1;
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

    public void setOt2(RichOutputText ot2) {
        this.ot2 = ot2;
    }

    public RichOutputText getOt2() {
        return ot2;
    }

    public void setPfl3(RichPanelFormLayout pfl3) {
        this.pfl3 = pfl3;
    }

    public RichPanelFormLayout getPfl3() {
        return pfl3;
    }

    public void setIt_meta_mp(RichInputText it3) {
        this.it_meta_mp = it3;
    }

    public RichInputText getIt_meta_mp() {
        return it_meta_mp;
    }

    public void setId_fecha_mp(RichInputDate id2) {
        this.id_fecha_mp = id2;
    }

    public RichInputDate getId_fecha_mp() {
        return id_fecha_mp;
    }

    public void setS2(RichSpacer s2) {
        this.s2 = s2;
    }

    public RichSpacer getS2() {
        return s2;
    }

    public void setPgl8(RichPanelGroupLayout pgl8) {
        this.pgl8 = pgl8;
    }

    public RichPanelGroupLayout getPgl8() {
        return pgl8;
    }

    public void setPgl9(RichPanelGroupLayout pgl9) {
        this.pgl9 = pgl9;
    }

    public RichPanelGroupLayout getPgl9() {
        return pgl9;
    }

    public void setOt3(RichOutputText ot3) {
        this.ot3 = ot3;
    }

    public RichOutputText getOt3() {
        return ot3;
    }

    public void setPfl4(RichPanelFormLayout pfl4) {
        this.pfl4 = pfl4;
    }

    public RichPanelFormLayout getPfl4() {
        return pfl4;
    }

    public void setIt_meta_lp(RichInputText it4) {
        this.it_meta_lp = it4;
    }

    public RichInputText getIt_meta_lp() {
        return it_meta_lp;
    }

    public void setId_fecha_lp(RichInputDate id3) {
        this.id_fecha_lp = id3;
    }

    public RichInputDate getId_fecha_lp() {
        return id_fecha_lp;
    }

    public void setS3(RichSpacer s3) {
        this.s3 = s3;
    }

    public RichSpacer getS3() {
        return s3;
    }

    public void setS7(RichSpacer s7) {
        this.s7 = s7;
    }

    public RichSpacer getS7() {
        return s7;
    }

    public void setPgl10(RichPanelGroupLayout pgl10) {
        this.pgl10 = pgl10;
    }

    public RichPanelGroupLayout getPgl10() {
        return pgl10;
    }

    public void setCb_adicionar_indicador(RichCommandButton cb1) {
        this.cb_adicionar_indicador = cb1;
    }

    public RichCommandButton getCb_adicionar_indicador() {
        return cb_adicionar_indicador;
    }

    public void setS8(RichSpacer s8) {
        this.s8 = s8;
    }

    public RichSpacer getS8() {
        return s8;
    }

    public void setS9(RichSpacer s9) {
        this.s9 = s9;
    }

    public RichSpacer getS9() {
        return s9;
    }

    public void setPc1(RichPanelCollection pc1) {
        this.pc1 = pc1;
    }

    public RichPanelCollection getPc1() {
        return pc1;
    }

    public List<PorhIndices> getListaIndicadores() {
        return listaIndicadores;
    }

    public void setListaIndicadores(List<PorhIndices> listaIndicadores) {
        this.listaIndicadores = listaIndicadores;
    }

    public void setT_indicadores(RichTable t1) {
        this.t_indicadores = t1;
    }

    public RichTable getT_indicadores() {
        return t_indicadores;
    }

    public void cb_adicionar_indicador_actionListener(ActionEvent actionEvent) {
        boolean errores = false;
        if(it_nombre.getValue()==null || it_nombre.getValue().toString().length()==0){
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR, it_nombre);
            errores = true;
        }
        if(it_meta_cp.getValue()==null || it_meta_cp.getValue().toString().length()==0){
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR, it_meta_cp);
            errores = true;
        }
        if(id_fecha_cp.getValue()==null){
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR, id_fecha_cp);
            errores = true;
        }

        if(it_meta_mp.getValue()==null || it_meta_mp.getValue().toString().length()==0){
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR, it_meta_mp);
            errores = true;
        }
        if(id_fecha_mp.getValue()==null){
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR, id_fecha_mp);
            errores = true;
        }

        if(it_meta_lp.getValue()==null || it_meta_lp.getValue().toString().length()==0){
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR, it_meta_cp);
            errores = true;
        }
        if(id_fecha_lp.getValue()==null){
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR, id_fecha_lp);
            errores = true;
        }
        if(errores){
            return;
        }
        try{
            UsuarioConectadoTO usuarioConectado = 
                (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");

            Autoridades autoridadAmbiental = usuarioConectado.getUsuario().getAutoridadAmbiental();
            
            PorhIndices indice = null;
            
            if(getIndicadorSeleccionado() != null){
                indice = getIndicadorSeleccionado();
            }else{
                indice = new PorhIndices();
                indice.setIdPlan( this.planOrdenamiento.getCodigo() );
                indice.setIdTramo( this.tramo.getId() );                
                indice.setCodigoAutoridad( autoridadAmbiental.getId().longValue() );                
            }
            
            indice.setNombre( it_nombre.getValue().toString() );
            indice.setMetaCp( ((BigDecimal)it_meta_cp.getValue()).doubleValue() );
            indice.setFechaCp((Date)id_fecha_cp.getValue());
            indice.setMetaMp( ((BigDecimal)it_meta_mp.getValue()).doubleValue() );
            indice.setFechaMp((Date)id_fecha_mp.getValue());
            indice.setMetaLp( ((BigDecimal)it_meta_lp.getValue()).doubleValue() );
            indice.setFechaLp((Date)id_fecha_lp.getValue());
            
            PorhDelegate pd = PorhDelegate.getInstance();
            pd.updateIndicador( indice );
            
            String params[] = {"del Indicador"};
            showMessage(getText("mensaje_registro_exitoso",
                                params), FacesMessage.SEVERITY_INFO);
            
            listaIndicadores = pd.getIndicadores(this.tramo,
                                                 planOrdenamiento,
                                                 autoridadAmbiental.getId().longValue());
            it_nombre.setValue(null);
            it_nombre.setReadOnly(false);       
            it_meta_cp.setValue(null);
            id_fecha_cp.setValue(null);
            it_meta_mp.setValue(null);
            id_fecha_mp.setValue(null);
            it_meta_lp.setValue(null);
            id_fecha_lp.setValue(null);
            this.setIndicadorSeleccionado(null);
            cb_adicionar_indicador.setText("Adicionar");            
            it_seg_meta_cp.setValue( null );
            id_seg_fecha_cp.setValue( null );            
            
            AdfFacesContext.getCurrentInstance().addPartialTarget(it_seg_meta_cp);
            
            
            AdfFacesContext.getCurrentInstance().addPartialTarget(id_seg_fecha_cp);
            
            
            
            
            AdfFacesContext.getCurrentInstance().addPartialTarget(pb_indicadores);
        }catch(IdeamException e){
            showMessage(e);
        }
    }

    public void t_indicadores_selectionListener(SelectionEvent selectionEvent) {
        setIndicadorSeleccionado((PorhIndices)t_indicadores.getSelectedRowData());
        this.listaSeguimiento = new ArrayList();
        if(getIndicadorSeleccionado() != null){
            it_nombre.setValue(getIndicadorSeleccionado().getNombre() );
            
            if(getIndicadorSeleccionado().getIdIndiceBasico()!=null &&
                getIndicadorSeleccionado().getIdIndiceBasico().longValue()>0){
                it_nombre.setReadOnly(true);       
            }else{
                it_nombre.setReadOnly(false);       
            } 
            System.out.println("Corto plazon:"+getIndicadorSeleccionado().getMetaCp());
            it_meta_cp.setValue(getIndicadorSeleccionado().getMetaCp() );
            id_fecha_cp.setValue(getIndicadorSeleccionado().getFechaCp() );
            System.out.println("Mediano plazon:"+getIndicadorSeleccionado().getMetaMp());
            it_meta_mp.setValue(getIndicadorSeleccionado().getMetaMp() );
            id_fecha_mp.setValue(getIndicadorSeleccionado().getFechaMp() );
            System.out.println("Largo  plazon:"+getIndicadorSeleccionado().getMetaLp());
            it_meta_lp.setValue(getIndicadorSeleccionado().getMetaLp() );
            id_fecha_lp.setValue(getIndicadorSeleccionado().getFechaLp() );
            cb_adicionar_indicador.setText("Actualizar");
            it_nombre_seg.setValue( indicadorSeleccionado.getNombre() );
            
            if (indicadorSeleccionado.getPermitirSeguimiento()){
                this.listaSeguimiento = new ArrayList();
                
                PorhSeguimientoIndices corto = new PorhSeguimientoIndices();
                corto.setFecha( indicadorSeleccionado.getFechaCp() );
                corto.setValor( indicadorSeleccionado.getMetaLogradaCp() );

                PorhSeguimientoIndices medio = new PorhSeguimientoIndices();
                medio.setFecha( indicadorSeleccionado.getFechaMp() );
                medio.setValor( indicadorSeleccionado.getMetaLogradaMp() );

                PorhSeguimientoIndices largo = new PorhSeguimientoIndices();
                largo.setFecha( indicadorSeleccionado.getFechaLp() );
                largo.setValor( indicadorSeleccionado.getMetaLogradaLp() );
                
                this.listaSeguimiento.add(corto);
                this.listaSeguimiento.add(medio);
                this.listaSeguimiento.add(largo);
                
                it_seg_meta_cp.setDisabled(true);
                id_seg_fecha_cp.setDisabled(true);
                //it_seg_meta_cp.setValue( indicadorSeleccionado.getMetaLogradaCp() );
                //id_seg_fecha_cp.setValue( indicadorSeleccionado.getFechaLp() );
                AdfFacesContext.getCurrentInstance().addPartialTarget(it_seg_meta_cp);
                AdfFacesContext.getCurrentInstance().addPartialTarget(id_seg_fecha_cp);
                mostrarBotonBorrar=false;
            }else{
                try{
                    PorhDelegate pd = PorhDelegate.getInstance();
                    this.listaSeguimiento = 
                        pd.getAllSeguimiento(getIndicadorSeleccionado());                    
                }catch(IdeamException e){
                    showMessage(e);
                }                
                it_seg_meta_cp.setDisabled(false);
                id_seg_fecha_cp.setDisabled(false);
                
                it_seg_meta_cp.setValue( null );                
                id_seg_fecha_cp.setValue( null );
                AdfFacesContext.getCurrentInstance().addPartialTarget(it_seg_meta_cp);
                AdfFacesContext.getCurrentInstance().addPartialTarget(id_seg_fecha_cp); 
                mostrarBotonBorrar=true;
            }
        }else{
            it_nombre.setValue(null);
            it_nombre.setReadOnly(false);       
            it_meta_cp.setValue(null);
            id_fecha_cp.setValue(null);
            it_meta_mp.setValue(null);
            id_fecha_mp.setValue(null);
            it_meta_lp.setValue(null);
            id_fecha_lp.setValue(null);
            this.setIndicadorSeleccionado(null);
            it_nombre_seg.setValue(null);
            cb_adicionar_indicador.setText("Adicionar");
        }
        AdfFacesContext.getCurrentInstance().addPartialTarget(pgl3);
        AdfFacesContext.getCurrentInstance().addPartialTarget(cb_adicionar_seguimiento);
        AdfFacesContext.getCurrentInstance().addPartialTarget(it_nombre_seg);
        AdfFacesContext.getCurrentInstance().addPartialTarget(t_seguimiento);
    }

    public void setPb_avance(RichPanelBox pb1) {
        this.pb_avance = pb1;
    }

    public RichPanelBox getPb_avance() {
        return pb_avance;
    }

    public void setPsl3(RichPanelStretchLayout psl3) {
        this.psl3 = psl3;
    }

    public RichPanelStretchLayout getPsl3() {
        return psl3;
    }

    public void setPgl11(RichPanelGroupLayout pgl11) {
        this.pgl11 = pgl11;
    }

    public RichPanelGroupLayout getPgl11() {
        return pgl11;
    }

    public void setPfl5(RichPanelFormLayout pfl5) {
        this.pfl5 = pfl5;
    }

    public RichPanelFormLayout getPfl5() {
        return pfl5;
    }

    public void setIt_nombre_seg(RichInputText it1) {
        this.it_nombre_seg = it1;
    }

    public RichInputText getIt_nombre_seg() {
        return it_nombre_seg;
    }

    public void setS10(RichSpacer s10) {
        this.s10 = s10;
    }

    public RichSpacer getS10() {
        return s10;
    }

    public void setPgl12(RichPanelGroupLayout pgl12) {
        this.pgl12 = pgl12;
    }

    public RichPanelGroupLayout getPgl12() {
        return pgl12;
    }

    public void setS11(RichSpacer s11) {
        this.s11 = s11;
    }

    public RichSpacer getS11() {
        return s11;
    }

    public void setPgl13(RichPanelGroupLayout pgl13) {
        this.pgl13 = pgl13;
    }

    public RichPanelGroupLayout getPgl13() {
        return pgl13;
    }

    public void setS12(RichSpacer s12) {
        this.s12 = s12;
    }

    public RichSpacer getS12() {
        return s12;
    }

    public void setCb_adicionar_seguimiento(RichCommandButton cb1) {
        this.cb_adicionar_seguimiento = cb1;
    }

    public RichCommandButton getCb_adicionar_seguimiento() {
        return cb_adicionar_seguimiento;
    }

    public void setS13(RichSpacer s13) {
        this.s13 = s13;
    }

    public RichSpacer getS13() {
        return s13;
    }

    public void setPgl14(RichPanelGroupLayout pgl14) {
        this.pgl14 = pgl14;
    }

    public RichPanelGroupLayout getPgl14() {
        return pgl14;
    }

    public void setPgl15(RichPanelGroupLayout pgl15) {
        this.pgl15 = pgl15;
    }

    public RichPanelGroupLayout getPgl15() {
        return pgl15;
    }

    public void setOt11(RichOutputText ot11) {
        this.ot11 = ot11;
    }

    public RichOutputText getOt11() {
        return ot11;
    }

    public void setPfl6(RichPanelFormLayout pfl6) {
        this.pfl6 = pfl6;
    }

    public RichPanelFormLayout getPfl6() {
        return pfl6;
    }

    public void setIt_seg_meta_cp(RichInputText it2) {
        this.it_seg_meta_cp = it2;
    }

    public RichInputText getIt_seg_meta_cp() {
        return it_seg_meta_cp;
    }

    public void setId_seg_fecha_cp(RichInputDate id1) {
        this.id_seg_fecha_cp = id1;
    }

    public RichInputDate getId_seg_fecha_cp() {
        return id_seg_fecha_cp;
    }


    public List<PorhSeguimientoIndices> getListaSeguimiento() {
        return listaSeguimiento;
    }

    public void setListaSeguimiento(List<PorhSeguimientoIndices> listaSeguimiento) {
        this.listaSeguimiento = listaSeguimiento;
    }

    public void setPc2(RichPanelCollection pc2) {
        this.pc2 = pc2;
    }

    public RichPanelCollection getPc2() {
        return pc2;
    }

    public void setT_seguimiento(RichTable t1) {
        this.t_seguimiento = t1;
    }

    public RichTable getT_seguimiento() {
        return t_seguimiento;
    }

    public PorhIndices getIndicadorSeleccionado() {
        return indicadorSeleccionado;
    }

    public void setIndicadorSeleccionado(PorhIndices indicadorSeleccionado) {
        this.indicadorSeleccionado = indicadorSeleccionado;
    }

    public void cb_adicionar_seguimiento_actionListener(ActionEvent actionEvent) {
        boolean errores = false;
        if(it_seg_meta_cp.getValue()==null || it_seg_meta_cp.getValue().toString().length()==0){
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR, it_seg_meta_cp);
            errores = true;
        }
        if(id_seg_fecha_cp.getValue()==null){
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR, id_seg_fecha_cp);
            errores = true;
        }

        if(errores){
            return;
        }
        try{
            UsuarioConectadoTO usuarioConectado = 
                (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");

            Autoridades autoridadAmbiental = usuarioConectado.getUsuario().getAutoridadAmbiental();
            
            PorhDelegate pd = PorhDelegate.getInstance();
            PorhSeguimientoIndices seguimiento = null;
            
            if(seguimientoSeleccionado!=null){
                seguimiento = seguimientoSeleccionado;
            }else{
                seguimiento = new PorhSeguimientoIndices();
                seguimiento.setIdIndice( this.indicadorSeleccionado.getId() ); 
                seguimiento.setCodigoAutoridad( autoridadAmbiental.getId().longValue() );
            }
            
            seguimiento.setValor( ((BigDecimal)it_seg_meta_cp.getValue()).doubleValue() );
            seguimiento.setFecha((Date)id_seg_fecha_cp.getValue());
            
            pd.updateSeguimientoIndice(seguimiento);
            
            String params[] = {"del Seguimiento"};
            showMessage(getText("mensaje_registro_exitoso",
                                params), FacesMessage.SEVERITY_INFO);

            this.listaSeguimiento = pd.getAllSeguimiento(indicadorSeleccionado);
            it_nombre_seg.setValue( null );
            it_seg_meta_cp.setValue( null );
            id_seg_fecha_cp.setValue( null );
            cb_adicionar_seguimiento.setText("Adicionar");
            this.seguimientoSeleccionado = null;
            AdfFacesContext.getCurrentInstance().addPartialTarget(pb_avance);
        }catch(IdeamException e){
            showMessage(e);
        }
    }

    public void t_seguimiento_selectionListener(SelectionEvent selectionEvent) {
        seguimientoSeleccionado = 
            (PorhSeguimientoIndices)t_seguimiento.getSelectedRowData();
        
        if(seguimientoSeleccionado!=null){
            it_nombre_seg.setValue( this.indicadorSeleccionado.getNombre() );
            it_seg_meta_cp.setValue( seguimientoSeleccionado.getValor() );
            id_seg_fecha_cp.setValue( seguimientoSeleccionado.getFecha() );
            cb_adicionar_seguimiento.setText("Actualizar");
        }else{
            it_nombre_seg.setValue( null );
            it_seg_meta_cp.setValue( null );
            id_seg_fecha_cp.setValue( null );
            cb_adicionar_seguimiento.setText("Adicionar");
        }
        AdfFacesContext.getCurrentInstance().addPartialTarget(pgl11);
    }

    public void setSpacer1(RichSpacer spacer1) {
        this.spacer1 = spacer1;
    }

    public RichSpacer getSpacer1() {
        return spacer1;
    }

    public void setCb_borrar_indicador(RichCommandButton commandButton1) {
        this.cb_borrar_indicador = commandButton1;
    }

    public RichCommandButton getCb_borrar_indicador() {
        return cb_borrar_indicador;
    }

    public void cb_borrar_indicador_actionListener(ActionEvent actionEvent) {
        // Add event code here...
        showPopup(p_borrar_indicador, true);
    }

    public void setP_borrar_indicador(RichPopup p_borrar_indicador) {
        this.p_borrar_indicador = p_borrar_indicador;
    }

    public RichPopup getP_borrar_indicador() {
        return p_borrar_indicador;
    }

    public void setPg46(RichPanelGroupLayout pg46) {
        this.pg46 = pg46;
    }

    public RichPanelGroupLayout getPg46() {
        return pg46;
    }

    public void setD_borrar_indicador(RichDialog d_borrar_indicador) {
        this.d_borrar_indicador = d_borrar_indicador;
    }

    public RichDialog getD_borrar_indicador() {
        return d_borrar_indicador;
    }
    public void d_borrar_indicador_dialogListener(DialogEvent dialogEvent) {
        UsuarioConectadoTO usuarioConectado = 
            (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");

        Autoridades autoridadAmbiental = usuarioConectado.getUsuario().getAutoridadAmbiental();
        try{
            PorhDelegate pd = PorhDelegate.getInstance();
            pd.deleteIndicador(this.indicadorSeleccionado);

            String params[] = {"del Indicador"};
            showMessage(getText("mensaje_registro_exitoso",
                                params), FacesMessage.SEVERITY_INFO);

            listaIndicadores = pd.getIndicadores(this.tramo, this.planOrdenamiento, autoridadAmbiental.getId().longValue());
            this.listaSeguimiento = pd.getAllSeguimiento( this.indicadorSeleccionado );
            
            it_seg_meta_cp.setValue(null);
            id_seg_fecha_cp.setValue(null);
            it_nombre.setValue(null);
            it_meta_cp.setValue(null);
            id_fecha_cp.setValue(null);
            it_meta_mp.setValue(null);
            id_fecha_mp.setValue(null);
            it_meta_lp.setValue(null);
            id_fecha_lp.setValue(null);
            //soc_parametro.setDisabled(false);            
            //cb_adicionar.setText("Adicionar");
            t_indicadores.setSelectedRowKeys(null);
            this.indicadorSeleccionado = null;
            AdfFacesContext.getCurrentInstance().addPartialTarget(pb_indicadores);  
            AdfFacesContext.getCurrentInstance().addPartialTarget(psl3);
        }catch(IdeamException e){
            showMessage(e);
        }
    }

    public void setOt40(RichOutputText ot40) {
        this.ot40 = ot40;
    }

    public RichOutputText getOt40() {
        return ot40;
    }

    public void setMostrarBotonBorrar(boolean mostrarBotonBorrar) {
        this.mostrarBotonBorrar = mostrarBotonBorrar;
    }

    public boolean isMostrarBotonBorrar() {
        return mostrarBotonBorrar;
    }
}
