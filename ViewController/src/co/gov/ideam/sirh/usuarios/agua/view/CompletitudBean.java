package co.gov.ideam.sirh.usuarios.agua.view;

import co.gov.ideam.sirh.archivos.model.CellTO;
import co.gov.ideam.sirh.archivos.model.ColumnTO;
import co.gov.ideam.sirh.archivos.model.RowTO;
import co.gov.ideam.sirh.parametros.model.Autoridades;
import co.gov.ideam.sirh.parametros.view.ParametrosDelegate;
import co.gov.ideam.sirh.seguridad.model.PerfilVO;
import co.gov.ideam.sirh.seguridad.model.UsuarioConectadoTO;
import co.gov.ideam.sirh.seguridad.view.SeguridadDelegate;
import co.gov.ideam.sirh.usuarios.agua.model.CondicionErrorTO;
import co.gov.ideam.sirh.util.Constantes;
import co.gov.ideam.sirh.util.ConstantesCalidad;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.view.FacesUtils;
import co.gov.ideam.sirh.view.StandarBean;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;

import javax.faces.application.Application;
import javax.faces.component.UISelectItems;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.context.FacesContext;

import javax.faces.event.ValueChangeEvent;

import javax.naming.NamingException;

import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.data.RichColumn;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.layout.RichPanelBox;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelSplitter;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.output.RichActiveOutputText;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.component.rich.output.RichPanelCollection;

import oracle.adf.view.rich.context.AdfFacesContext;

import org.apache.myfaces.trinidad.event.SelectionEvent;

public class CompletitudBean extends StandarBean{
    private RichForm f2;
    private RichDocument d2;
    private RichPanelStretchLayout psl1;
    private RichPanelSplitter ps1;
    private RichPanelBox pb_totales;

    private List<CondicionErrorTO> listaCondiciones;
    private List listaDetalle;
    
    private RichTable t_totales;
    private RichPanelBox pb_detalle;
    private RichPanelCollection pc2;
    private RichTable t_detalle;
    
    private RichSelectOneChoice soc_autoridad_id;
    private String visiblePerfil="true";
    private UISelectItems si1Autoridad;
    private List listaAutoridades;
    private HtmlOutputText outputText1;
    private RichActiveOutputText activeOutputText1;
    private RichPanelGroupLayout panelGroupLayout1;
    private RichPanelBox pb_totales1;
    private RichPanelCollection pc11;
    private RichTable t_totales1;
    private HtmlOutputText outputText11;
    private RichPanelBox pb_detalle1;
    private RichActiveOutputText activeOutputText11;
    private RichPanelCollection pc21;
    private RichTable t_detalle1;
    private RichPanelSplitter panelSplitter1;
    private RichSelectOneChoice selectOneChoice1;
    private UISelectItems selectItems1;
    Autoridades aut=null;

    public CompletitudBean(){
        FacesUtils.setActiveBean("completitud",
                                 "completitud",
                                 UsuariosAguaDelegate.class);
        this.load();        
    }
    public void load(){
        try{
            UsuarioConectadoTO usuarioConectado = 
                (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
            
            construirLista();
            SeguridadDelegate seg = SeguridadDelegate.getInstance(); 
            PerfilVO pp= new PerfilVO();
            pp=   seg.getPerfilUsuario(usuarioConectado.getUsuario().getCodigo());
            if ( pp.getCodigo()==ConstantesCalidad.PERFIL_CONSULTA){
                this.visiblePerfil="false";
                this.listaAutoridades= this.cargarAutoridades();
                
            }
        }catch(IdeamException e){
            showMessage(e);
        }
    }
    public void soc_filtro_porAtoridad(ValueChangeEvent valueChangeEvent) throws IdeamException {
        Object autoridadSeleccionada = valueChangeEvent.getNewValue();
        construirLista();
    }
    public void construirLista() throws IdeamException {
        UsuarioConectadoTO usuarioConectado = 
            (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
        UsuariosAguaDelegate uad = UsuariosAguaDelegate.getInstance();
        
        SeguridadDelegate seg = SeguridadDelegate.getInstance(); 
        PerfilVO pp= new PerfilVO();
        pp=   seg.getPerfilUsuario(usuarioConectado.getUsuario().getCodigo());
        System.out.println("rol MADS:"+pp.getCodigo());
        if ( pp.getCodigo()==ConstantesCalidad.PERFIL_CONSULTA&&this.soc_autoridad_id!=null){
                ParametrosDelegate pd = ParametrosDelegate.getInstance();
                this.aut=(Autoridades)this.soc_autoridad_id.getValue();
                System.out.println("Autoridad seleccioanda por rol MADS:"+ this.aut.getId());
                listaCondiciones = uad.getTotalesCondicionesError(aut);
                AdfFacesContext.getCurrentInstance().addPartialTarget(t_totales);
        }else{
            listaCondiciones = uad.getTotalesCondicionesError(usuarioConectado.getUsuario().getAutoridadAmbiental());
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

    public void setPs1(RichPanelSplitter ps1) {
        this.ps1 = ps1;
    }

    public RichPanelSplitter getPs1() {
        return ps1;
    }

    public void setPb_totales(RichPanelBox pb1) {
        this.pb_totales = pb1;
    }

    public RichPanelBox getPb_totales() {
        return pb_totales;
    }


    public List<CondicionErrorTO> getListaCondiciones() {
        return listaCondiciones;
    }

    public void setListaCondiciones(List<CondicionErrorTO> listaCondiciones) {
        this.listaCondiciones = listaCondiciones;
    }

    public void setT_totales(RichTable t1) {
        this.t_totales = t1;
    }

    public RichTable getT_totales() {
        return t_totales;
    }

    public void setPb_detalle(RichPanelBox pb1) {
        this.pb_detalle = pb1;
    }

    public RichPanelBox getPb_detalle() {
        return pb_detalle;
    }

    public void setPc2(RichPanelCollection pc2) {
        this.pc2 = pc2;
    }

    public RichPanelCollection getPc2() {
        return pc2;
    }

    public List getListaDetalle() {
        return listaDetalle;
    }

    public void setListaDetalle(List listaDetalle) {
        this.listaDetalle = listaDetalle;
    }

    public void setT_detalle(RichTable t1) {
        this.t_detalle = t1;
    }

    public RichTable getT_detalle() {
        return t_detalle;
    }

    public void t_totales_selectionListener(SelectionEvent selectionEvent) {
        this.listaDetalle = new ArrayList();
        
        if(t_detalle != null){                                    
            for(int i=0; i<t_detalle.getChildCount(); i++){                
                t_detalle.getChildren().remove(i);                
            }
        }
        
        CondicionErrorTO condicion = (CondicionErrorTO)t_totales.getSelectedRowData();
        if(condicion!=null){
            t_detalle.getChildren().clear();
            if (condicion.getSqlDetalle()!=null){
                try{
                    UsuarioConectadoTO usuarioConectado = 
                        (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
                    List<RowTO> lista =new ArrayList();
                    UsuariosAguaDelegate uad = UsuariosAguaDelegate.getInstance();
                    SeguridadDelegate seg = SeguridadDelegate.getInstance(); 
                    PerfilVO pp= new PerfilVO();
                    pp=   seg.getPerfilUsuario(usuarioConectado.getUsuario().getCodigo());
                    System.out.println("rol MADS:"+pp.getCodigo());
                    if ( pp.getCodigo()==ConstantesCalidad.PERFIL_CONSULTA&&this.soc_autoridad_id!=null){
                        lista = uad.getDetalleCompletitud(condicion, this.aut);
                    }else{
                        lista = uad.getDetalleCompletitud(condicion, usuarioConectado.getUsuario().getAutoridadAmbiental());
                    }
                    if (lista!=null && lista.size()>0){
                        //////////////////////////////////              
                        
                        RichOutputText textoIndice = new RichOutputText();
                        textoIndice.setId("text_indice" );                        
                        textoIndice.setValueExpression("value", resolveExpression("#{row.indice}"));
                        RichColumn dataColIndice = new RichColumn();
                        dataColIndice.setId("col_indice");
                        dataColIndice.setSortable(true);
                        dataColIndice.setHeaderText("Fila");
                        dataColIndice.setWidth("50");                        
                        dataColIndice.getChildren().add(textoIndice);
                        t_detalle.getChildren().add(dataColIndice);

                        
                        // Crear las columnas de la tabla
                        RowTO row = lista.get(0);
                        Iterator<CellTO> itCols = row.getCeldas().iterator();
                        int contadorColumnas = 0;
                        while(itCols.hasNext()){
                            ColumnTO col = itCols.next();

                            RichOutputText texto = new RichOutputText();
                            texto.setId("text_" + col.getIndice());                            
                            texto.setValueExpression("value",
                                                     resolveExpression("#{row.celdas[" + contadorColumnas + "].value}"));
                            
                            RichColumn dataCol = new RichColumn();
                            dataCol.setId("col_" + col.getIndice());
                            dataCol.setSortable(true);
                            dataCol.setHeaderText(col.getTitulo());                            
                            dataCol.getChildren().add(texto);
                            t_detalle.getChildren().add(dataCol);
                            contadorColumnas++;
                        }
                        
                        this.listaDetalle = new ArrayList();

                        Iterator<RowTO> it = lista.iterator();
                        while(it.hasNext()){
                            RowTO rowData = it.next();                            
                            this.listaDetalle.add(rowData);
                        }                        
                    }
                    t_detalle.setRendered(true);
                    AdfFacesContext.getCurrentInstance().addPartialTarget(t_detalle);
                }catch(IdeamException e){
                    showMessage(e);
                }catch(NamingException e){
                    //showMessage(e);
                }catch(SQLException e){
                    //showMessage(e);
                }
                
            }                      
        }        
        AdfFacesContext.getCurrentInstance().addPartialTarget(pb_detalle);
    }
    
    public static ValueExpression resolveExpression(String attributeName) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        Application app = facesContext.getApplication();
        ExpressionFactory elFactory = app.getExpressionFactory();
        ELContext elContext = facesContext.getELContext();
        ValueExpression valueExp =
        elFactory.createValueExpression(elContext, attributeName, Object.class);
        return valueExp;
    }

    public void setSoc_autoridad_id(RichSelectOneChoice soc_autoridad_id) {
        this.soc_autoridad_id = soc_autoridad_id;
    }

    public RichSelectOneChoice getSoc_autoridad_id() {
        return soc_autoridad_id;
    }

    public void setVisiblePerfil(String visiblePerfil) {
        this.visiblePerfil = visiblePerfil;
    }

    public String getVisiblePerfil() {
        return visiblePerfil;
    }

    public void setSi1Autoridad(UISelectItems si1Autoridad) {
        this.si1Autoridad = si1Autoridad;
    }

    public UISelectItems getSi1Autoridad() {
        return si1Autoridad;
    }

    public void setListaAutoridades(List listaAutoridades) {
        this.listaAutoridades = listaAutoridades;
    }

    public List getListaAutoridades() {
        return listaAutoridades;
    }

    public void setOutputText1(HtmlOutputText outputText1) {
        this.outputText1 = outputText1;
    }

    public HtmlOutputText getOutputText1() {
        return outputText1;
    }

    public void setActiveOutputText1(RichActiveOutputText activeOutputText1) {
        this.activeOutputText1 = activeOutputText1;
    }

    public RichActiveOutputText getActiveOutputText1() {
        return activeOutputText1;
    }

    public void setPanelGroupLayout1(RichPanelGroupLayout panelGroupLayout1) {
        this.panelGroupLayout1 = panelGroupLayout1;
    }

    public RichPanelGroupLayout getPanelGroupLayout1() {
        return panelGroupLayout1;
    }

    public void setPb_totales1(RichPanelBox pb_totales1) {
        this.pb_totales1 = pb_totales1;
    }

    public RichPanelBox getPb_totales1() {
        return pb_totales1;
    }


    public void setPc11(RichPanelCollection pc11) {
        this.pc11 = pc11;
    }

    public RichPanelCollection getPc11() {
        return pc11;
    }

    public void setT_totales1(RichTable t_totales1) {
        this.t_totales1 = t_totales1;
    }

    public RichTable getT_totales1() {
        return t_totales1;
    }

    public void setOutputText11(HtmlOutputText outputText11) {
        this.outputText11 = outputText11;
    }

    public HtmlOutputText getOutputText11() {
        return outputText11;
    }

    public void setPb_detalle1(RichPanelBox pb_detalle1) {
        this.pb_detalle1 = pb_detalle1;
    }

    public RichPanelBox getPb_detalle1() {
        return pb_detalle1;
    }

    public void setActiveOutputText11(RichActiveOutputText activeOutputText11) {
        this.activeOutputText11 = activeOutputText11;
    }

    public RichActiveOutputText getActiveOutputText11() {
        return activeOutputText11;
    }

    public void setPc21(RichPanelCollection pc21) {
        this.pc21 = pc21;
    }

    public RichPanelCollection getPc21() {
        return pc21;
    }

    public void setT_detalle1(RichTable t_detalle1) {
        this.t_detalle1 = t_detalle1;
    }

    public RichTable getT_detalle1() {
        return t_detalle1;
    }

    public void setPanelSplitter1(RichPanelSplitter panelSplitter1) {
        this.panelSplitter1 = panelSplitter1;
    }

    public RichPanelSplitter getPanelSplitter1() {
        return panelSplitter1;
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

    public void setAut(Autoridades aut) {
        this.aut = aut;
    }

    public Autoridades getAut() {
        return aut;
    }
}
