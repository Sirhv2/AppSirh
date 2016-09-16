package co.gov.ideam.sirh.dashboard.view;

import co.gov.ideam.sirh.calidad.model.CriteriosBusquedaMuestrasTO;
import co.gov.ideam.sirh.calidad.model.DatosGrafico;
import co.gov.ideam.sirh.calidad.model.DatosGraficoCalidad;
import co.gov.ideam.sirh.calidad.model.DatosReporteCalidadPOJO;
import co.gov.ideam.sirh.calidad.model.DatosReporteCalidadParametrosPuntoPOJO;
import co.gov.ideam.sirh.calidad.model.PuntoMonitoreo;
import co.gov.ideam.sirh.calidad.view.CalidadDelegate;

import co.gov.ideam.sirh.calidad.view.FiltrosCalidad;
import co.gov.ideam.sirh.parametros.model.Lista;
import co.gov.ideam.sirh.reportes.view.ReporteDelegate;
import co.gov.ideam.sirh.seguridad.model.PerfilVO;
import co.gov.ideam.sirh.seguridad.model.UsuarioConectadoTO;
import co.gov.ideam.sirh.seguridad.view.SeguridadDelegate;
import co.gov.ideam.sirh.util.ConstantesCalidad;
import co.gov.ideam.sirh.util.IdeamException;

import co.gov.ideam.sirh.view.FacesUtils;
import co.gov.ideam.sirh.view.StandarBean;

import co.gov.ideam.sirh.view.StandarDashBoard;

import java.sql.Date;

import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;

import oracle.adf.view.faces.bi.component.graph.UIGraph;

import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.RichMenu;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.layout.RichPanelBox;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelSplitter;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.component.rich.nav.RichCommandLink;
import oracle.adf.view.rich.component.rich.nav.RichCommandMenuItem;
import oracle.adf.view.rich.component.rich.output.RichActiveOutputText;
import oracle.adf.view.rich.component.rich.output.RichOutputLabel;
import oracle.adf.view.rich.component.rich.output.RichPanelCollection;
import oracle.adf.view.rich.component.rich.output.RichSpacer;
import oracle.adf.view.rich.context.AdfFacesContext;

public class DashBoardCalidadGrafico2Bean  extends StandarDashBoard{
    private RichForm form1;
    private RichDocument document1;
    private RichPanelStretchLayout panelStretchLayout1;
    private RichPanelSplitter panelSplitter1;
    private RichPanelGroupLayout panelGroupLayout2;
    private RichPanelFormLayout panelFormLayout2;
    private RichCommandLink clink_inicio;
    private RichPanelGroupLayout panelGroupLayout3;
    private RichCommandLink clink_grafico1;
    private RichCommandLink clink_grafico2;
    private RichPanelBox panelBoxCalidad2;
    private RichPanelSplitter panelSplitter4;

    private List<Object[]> listObjectGrafico;

    
    private List listaPuntos;
    private List listaParametros;
    private PuntoMonitoreo pm;

    private List listaDatos;
    private String parametroNombre;   

    private UIGraph lineGraph1;
    private Long idAutoridadPunto;
    
    private RichPanelGroupLayout panelGroupLayout1;
    private RichPanelFormLayout panelFormGrafico;


    private RichOutputLabel otNota;
    private RichSpacer spacer4;
    private RichSpacer spacer5;
    private RichSpacer spacer6;
    private RichSpacer spacer7;
    private RichCommandLink clink3;
    private RichActiveOutputText datos;
    private RichActiveOutputText aot_pto;
    private RichActiveOutputText aot_parametro;
    private RichTable tableDatosGrafico2;
    UsuarioConectadoTO usuarioConectado;
    private String redireccionar;
    private RichPanelGroupLayout panelGroupLayout5;
    private RichSpacer spacer8;
    private RichCommandButton cb_buscar;
    private RichSpacer spacer9;
    private RichSpacer spacer10;
    private RichPanelCollection panelCollection1;
    private RichMenu menu1;
    private RichCommandMenuItem salidaExcel;
    private RichSpacer spacer1;
    private RichPanelGroupLayout panelGroupLayout4;
    private Integer numOrigen;

    public DashBoardCalidadGrafico2Bean(){
    
    
        FacesUtils.setActiveBean("dashBoardCalidadGrafico2","DashBoardCalidadGrafico2Bean", DashBoardCalidadGrafico2Bean.class);
        FacesUtils.removeManagedBeanFromSession("dashBoardCalidad");
        FacesUtils.removeManagedBeanFromSession("dashBoardCalidadGrafico3");
        numOrigen=new Integer("2");
        FacesUtils.removeManagedBeanFromSession("numOrigen");
        FacesUtils.setInSession("numOrigen",numOrigen);
       this.load();        
    }
    

    
    
    public void load(){
        System.out.println("load de DBCG2 ");    
        usuarioConectado = new UsuarioConectadoTO();
        try{
              usuarioConectado = 
                    (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
        }catch(Exception e){
            System.out.println("++++++++++++++++e"+e);
        }
        try{
        
            if(usuarioConectado==null){
               
                this.listaPuntos = this.getListaPuntosMediciones(null);
                
            } else {   
                
                    SeguridadDelegate seg = SeguridadDelegate.getInstance(); 
                    PerfilVO pp= new PerfilVO();
                    pp=   seg.getPerfilUsuario(usuarioConectado.getUsuario().getCodigo());
                        
                    if (pp.getCodigo()==ConstantesCalidad.PERFIL_CONSULTA){
                        this.listaPuntos = this.getListaPuntosMediciones(null);
                        
                    }else{  
                 
                     this.listaPuntos = this.getListaPuntosMediciones(usuarioConectado.getUsuario().
                                                           getAutoridadAmbiental().
                                                           getId().
                                                           longValue());
                    }               
                }
        }catch(Exception e){
            System.out.println( "error cargando puntos de monitoreo");
        }
        System.out.println("DBCG2 carga lista de puntos de monitore---"); 
        definirFiltros();
        
    }
    public void definirFiltros(){
        try{
            System.out.println("----------2-----DEFINICION INICIAL DE FILTROS DashBoardCalidadGrafico2Bean-----------");
            Object objFiltros = FacesUtils.getManagedBeanValue("filtrosCalidad");
                
            ((FiltrosCalidad) objFiltros).setObligatorioFuente(false);
            System.out.println("1.DBCG2 solicita habilitar la lista de parametros---"); 
            ((FiltrosCalidad) objFiltros).setVerListaParametros(true);  
            System.out.println("2.DBCG2 solicita habilitar la lista de parametros---");    
            ((FiltrosCalidad) objFiltros).setObligatorioParametro(true);           
            ((FiltrosCalidad) objFiltros).setDisabledListaPuntosMonitoreo(false);          
            ((FiltrosCalidad) objFiltros).setObligatorioPuntoMonitoreo(true);
            ((FiltrosCalidad) objFiltros).setVerFechaInicial(true);                    
            ((FiltrosCalidad) objFiltros).setVerFechaFinal(true);                  
            ((FiltrosCalidad) objFiltros).setObligatorioAnio(false);
            ((FiltrosCalidad) objFiltros).setVerListaAnios(false);     
            ((FiltrosCalidad) objFiltros).setNullListaAnios();
            AdfFacesContext.getCurrentInstance().addPartialTarget( ((FiltrosCalidad) objFiltros).getFc_panelGroupLayout1() );
            this.listaParametros= new ArrayList();
        }catch(Exception e){
            System.out.println( "error refrescando filtrosCalidad.jsff:"+e);
        }
    }
 
    public void cb_buscar_actionListener(ActionEvent actionEvent) {
        System.out.println("Creando Graficos");
        List<Object[]> listaObjGrafica = null;
        listaObjGrafica = getListObjectGrafico();
        if(listaObjGrafica.size()>=1){  
            this.panelFormGrafico.setVisible(true);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.panelFormGrafico);  
            this.tableDatosGrafico2.setVisible(true);
            AdfFacesContext.getCurrentInstance().addPartialTarget(tableDatosGrafico2);
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.lineGraph1);   
        }else{
             this.panelFormGrafico.setVisible(false);
             AdfFacesContext.getCurrentInstance().addPartialTarget(this.panelFormGrafico); 
             this.tableDatosGrafico2.setVisible(false);
             AdfFacesContext.getCurrentInstance().addPartialTarget(tableDatosGrafico2); 
         }   
    }
    
    
    public List<Object[]> getListObjectGrafico() {
        listObjectGrafico = new ArrayList<Object[]>();            
        this.listaDatos = new ArrayList();
        
        try{          
        Object obj = FacesUtils.getManagedBeanValue("filtrosCalidad");
        CriteriosBusquedaMuestrasTO criterios = null;
        
        if(obj!=null){
            criterios = ((FiltrosCalidad) obj).getCriterios(); 
            if (criterios==null){
                showMessage("Debe seleccionar un Punto de Monitoreo y un parámetro", FacesMessage.SEVERITY_ERROR);                
                return listObjectGrafico;
            }else{
                    // validar que se haya seleccionado un Punto de Monitoreo
                    if(criterios.getPtoMonitoreo()==null){
                        showMessage("Debe seleccionar un Punto de Monitoreo", FacesMessage.SEVERITY_ERROR);
                        return listObjectGrafico;
                    }
                    // Validar Parametro
                    if(criterios.getParametro()==null){
                        showMessage("Debe seleccionar un Parámetro", FacesMessage.SEVERITY_ERROR);
                        return listObjectGrafico;
                    }                                         
                }            
        } 
                    
            CalidadDelegate cd = CalidadDelegate.getInstance();
           
            this.listaDatos = cd.getParametroEnPuntoMonitoreoDatos(criterios,((FiltrosCalidad) obj).getIdAutoridadFuentePunto()); 
            System.out.println("this.listaDatos:"+this.listaDatos.size());
            if (listaDatos != null) {                             
                  Integer i=1;                  
                  Iterator<DatosReporteCalidadParametrosPuntoPOJO> it = listaDatos.iterator();
                  while (it.hasNext()) {
                      DatosReporteCalidadParametrosPuntoPOJO reg = it.next();
                      reg.setNum(i);
                      i++;
                  } 
              } 
            DatosReporteCalidadParametrosPuntoPOJO reg1 = (DatosReporteCalidadParametrosPuntoPOJO)this.listaDatos.get(0);
            
            this.aot_pto.setValue(reg1.getPuntoMonitoreo().toString());
            this.aot_parametro.setValue("Parámetro: "+  reg1.getParametro() + " Unidad: "+ reg1.getUnidad().toString());  
            
            this.parametroNombre = reg1.getParametro();
           
            List<Object[]> lDatosGrafico = cd.getPararametroEnPuntoMonitoreo(criterios, ((FiltrosCalidad) obj).getIdAutoridadFuentePunto());      
            
            if(lDatosGrafico.size()>=1){
               for (int j = 0; j < lDatosGrafico.size(); j++) {
                 Object[] dat = lDatosGrafico.get(j);
                  DatosGrafico d = new DatosGrafico();
                  d.setFecha((String) dat[0]);
                  d.setCantidad(new Double(dat[1].toString()));
                                            
                  Object[] obj1 = { d.getFecha(),  this.parametroNombre +" ("+ reg1.getUnidad()+")"  , d.getCantidad() };

                  listObjectGrafico.add(obj1);                     
                }
              }                         
        }catch(IdeamException e){
            showMessage(e);
         } 
        System.out.println("Datos graficoXX = " + listObjectGrafico.size());                         
        return listObjectGrafico;
    }
    
    public void salidaExcel_actionListener(ActionEvent actionEvent) {
    
        try {
            HashMap parametros = new HashMap();
            parametros.put("p_modulo", "Observatorio del Agua - Componente Calidad");
            parametros.put("p_titulo", "Comportamiento cronológico de un parámetro en un Punto de Monitoreo");
            this.generateReport("ListadoCalidad_2.jasper", this.listaDatos,
                                parametros, ReporteDelegate.EXCEL);
        } catch (IdeamException e) {
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

    public void setPanelGroupLayout2(RichPanelGroupLayout panelGroupLayout2) {
        this.panelGroupLayout2 = panelGroupLayout2;
    }

    public RichPanelGroupLayout getPanelGroupLayout2() {
        return panelGroupLayout2;
    }

    public void setPanelFormLayout2(RichPanelFormLayout panelFormLayout2) {
        this.panelFormLayout2 = panelFormLayout2;
    }

    public RichPanelFormLayout getPanelFormLayout2() {
        return panelFormLayout2;
    }

    public void setClink_grafico1(RichCommandLink commandLink2) {
        this.clink_grafico1 = commandLink2;
    }

    public RichCommandLink getClink_grafico1() {
        return clink_grafico1;
    }

    public void setClink_grafico2(RichCommandLink commandLink4) {
        this.clink_grafico2 = commandLink4;
    }

    public RichCommandLink getClink_grafico2() {
        return clink_grafico2;
    }


    public void setPanelBoxCalidad2(RichPanelBox panelBox1) {
        this.panelBoxCalidad2 = panelBox1;
    }

    public RichPanelBox getPanelBoxCalidad2() {
        return panelBoxCalidad2;
    }
 
    public void setListaPuntos(List listaPuntos) {
        this.listaPuntos = listaPuntos;
    }

    public List getListaPuntos() {
        return listaPuntos;
    }

    public void setListaParametros(List listaParametros) {
        this.listaParametros = listaParametros;
    }

    public List getListaParametros() {
        return listaParametros;
    }

    public void setPm(PuntoMonitoreo pm) {
        this.pm = pm;
    }

    public PuntoMonitoreo getPm() {
        return pm;
    }

    public void setPanelGroupLayout3(RichPanelGroupLayout panelGroupLayout3) {
        this.panelGroupLayout3 = panelGroupLayout3;
    }

    public RichPanelGroupLayout getPanelGroupLayout3() {
        return panelGroupLayout3;
    }


    public void setPanelSplitter4(RichPanelSplitter panelSplitter4) {
        this.panelSplitter4 = panelSplitter4;
    }

    public RichPanelSplitter getPanelSplitter4() {
        return panelSplitter4;
    }


    public void setClink_inicio(RichCommandLink clink_inicio) {
        this.clink_inicio = clink_inicio;
    }

    public RichCommandLink getClink_inicio() {
        return clink_inicio;
    }


    public void setLineGraph1(UIGraph lineGraph1) {
        this.lineGraph1 = lineGraph1;
    }

    public UIGraph getLineGraph1() {
        return lineGraph1;
    }

    public void setPanelGroupLayout1(RichPanelGroupLayout panelGroupLayout1) {
        this.panelGroupLayout1 = panelGroupLayout1;
    }

    public RichPanelGroupLayout getPanelGroupLayout1() {
        return panelGroupLayout1;
    }

    public void setPanelFormGrafico(RichPanelFormLayout panelFormLayout1) {
        this.panelFormGrafico = panelFormLayout1;
    }

    public RichPanelFormLayout getPanelFormGrafico() {
        return panelFormGrafico;
    }

    public void setParametroNombre(String parametroNombre) {
        this.parametroNombre = parametroNombre;
    }

    public String getParametroNombre() {
        return parametroNombre;
    }


    public void setOtNota(RichOutputLabel otNota) {
        this.otNota = otNota;
    }

    public RichOutputLabel getOtNota() {
        return otNota;
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

    public void setSpacer6(RichSpacer spacer6) {
        this.spacer6 = spacer6;
    }

    public RichSpacer getSpacer6() {
        return spacer6;
    }

    public void setSpacer7(RichSpacer spacer7) {
        this.spacer7 = spacer7;
    }

    public RichSpacer getSpacer7() {
        return spacer7;
    }

    public void setClink3(RichCommandLink commandLink1) {
        this.clink3 = commandLink1;
    }

    public RichCommandLink getClink3() {
        return clink3;
    }

    public void setDatos(RichActiveOutputText activeOutputText1) {
        this.datos = activeOutputText1;
    }

    public RichActiveOutputText getDatos() {
        return datos;
    }

    public void setAot_pto(RichActiveOutputText activeOutputText2) {
        this.aot_pto = activeOutputText2;
    }

    public RichActiveOutputText getAot_pto() {
        return aot_pto;
    }

    public void setAot_parametro(RichActiveOutputText activeOutputText3) {
        this.aot_parametro = activeOutputText3;
    }

    public RichActiveOutputText getAot_parametro() {
        return aot_parametro;
    }

    public void setTableDatosGrafico2(RichTable table1) {
        this.tableDatosGrafico2 = table1;
    }

    public RichTable getTableDatosGrafico2() {
        return tableDatosGrafico2;
    }

    public void setListObjectGrafico(List<Object[]> listObjectGrafico) {
        this.listObjectGrafico = listObjectGrafico;
    }

    public void setListaDatos(List listaDatos) {
        this.listaDatos = listaDatos;
    }

    public List getListaDatos() {
        return listaDatos;
    }

    public void setIdAutoridadPunto(Long idAutoridadPunto) {
        this.idAutoridadPunto = idAutoridadPunto;
    }
    
    public void setRedireccionar(String redireccionar) {
        this.redireccionar = redireccionar;
    }

    public String getRedireccionar() {
        return redireccionar;
    }
    public void redireccionarAOrigen(ActionEvent actionEvent) {
        
        String regla = (String)FacesUtils.getFromSession("OrigenNavegacionUsuario");
        System.out.println("regla de navegación:"+regla);
        if( regla.equals("observatorio"))
             redireccionar= "observatorio";
        else
           redireccionar= "dashBoard";
    }

    public Long getIdAutoridadPunto() {
        return idAutoridadPunto;
    }

    public void setPanelGroupLayout5(RichPanelGroupLayout panelGroupLayout5) {
        this.panelGroupLayout5 = panelGroupLayout5;
    }

    public RichPanelGroupLayout getPanelGroupLayout5() {
        return panelGroupLayout5;
    }

    public void setSpacer8(RichSpacer spacer8) {
        this.spacer8 = spacer8;
    }

    public RichSpacer getSpacer8() {
        return spacer8;
    }

    public void setCb_buscar(RichCommandButton cb_buscar) {
        this.cb_buscar = cb_buscar;
    }

    public RichCommandButton getCb_buscar() {
        return cb_buscar;
    }

    public void setSpacer9(RichSpacer spacer9) {
        this.spacer9 = spacer9;
    }

    public RichSpacer getSpacer9() {
        return spacer9;
    }

    public void setSpacer10(RichSpacer spacer10) {
        this.spacer10 = spacer10;
    }

    public RichSpacer getSpacer10() {
        return spacer10;
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

    public void setSalidaExcel(RichCommandMenuItem commandMenuItem1) {
        this.salidaExcel = commandMenuItem1;
    }

    public RichCommandMenuItem getSalidaExcel() {
        return salidaExcel;
    }

    public void setSpacer1(RichSpacer spacer1) {
        this.spacer1 = spacer1;
    }

    public RichSpacer getSpacer1() {
        return spacer1;
    }

    public void setPanelGroupLayout4(RichPanelGroupLayout panelGroupLayout4) {
        this.panelGroupLayout4 = panelGroupLayout4;
    }

    public RichPanelGroupLayout getPanelGroupLayout4() {
        return panelGroupLayout4;
    }


    public void setNumOrigen(Integer numOrigen) {
        this.numOrigen = numOrigen;
    }

    public Integer getNumOrigen() {
        return numOrigen;
    }
}
