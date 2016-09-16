package co.gov.ideam.sirh.dashboard.view;

import co.gov.ideam.sirh.calidad.model.CriteriosBusquedaMuestrasTO;
import co.gov.ideam.sirh.calidad.model.DatosGrafico;
import co.gov.ideam.sirh.calidad.model.DatosReporteCalidadParametrosPuntoPOJO;
import co.gov.ideam.sirh.calidad.model.PuntoMonitoreo;
import co.gov.ideam.sirh.calidad.view.CalidadDelegate;

import co.gov.ideam.sirh.calidad.view.FiltrosCalidad;
import co.gov.ideam.sirh.fuentes.model.FnttFuente;
import co.gov.ideam.sirh.parametros.model.Lista;
import co.gov.ideam.sirh.parametros.model.Parametro;
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

public class DashBoardCalidadGrafico3Bean  extends StandarDashBoard{
    private RichForm form1;
    private RichDocument document1;
    private RichPanelStretchLayout panelStretchLayout1;
    private RichPanelSplitter panelSplitter1;
    private RichPanelGroupLayout panelGroupLayout2;
    private RichPanelFormLayout panelFormLayout2;
    private RichPanelFormLayout panelFormPuntos;
    
    private RichCommandLink clink_inicio;
    private RichPanelGroupLayout panelGroupLayout3;
    private RichCommandLink clink_grafico1;
    private RichCommandLink clink_grafico2;
    private RichPanelBox panelBoxCalidad3;
    private RichPanelSplitter panelSplitter4;

    private List<Object[]> listObjectGrafico;
    private List listaFuentes;
    private List listaParametros;
    private PuntoMonitoreo pm;
    private Long idAutoridadPunto;


    private UIGraph lineGraph1;
    private Long fuenteId;
    private Long parametroId;
    private String parametroNombre;
    private String fuentepunto;
    private RichPanelGroupLayout panelGroupLayout1;
    private RichPanelFormLayout panelFormGrafico;


    private RichPanelFormLayout panelFormLayout1;
    private RichOutputLabel otNota;
    private RichSpacer spacer3;
    private RichCommandLink clink3;
    private RichSpacer spacer5;
    private RichSpacer spacer6;
    private RichSpacer spacer7;
    private RichSpacer spacer8;
    private String  anioString;
    private Long idFuente;
    private Long idParametro;
    private Integer anioInteger;
    
    private String textoNota;
    private RichActiveOutputText datos;
    private RichActiveOutputText ot_fuente;
    private RichActiveOutputText ot_parametro;
    private RichActiveOutputText nota;
    private RichActiveOutputText otNota2;
    
    private List listaDatos;
    private RichTable tableDatosGrafico3;

    private String redireccionar;
    private RichPanelGroupLayout panelGroupLayout5;
    private RichSpacer spacer9;
    private RichCommandButton cb_buscar;
    private RichSpacer spacer10;
    private RichSpacer spacer11;
    private RichPanelCollection panelCollection1;
    private RichMenu menu1;
    private RichCommandMenuItem salidaExcel;
    private RichSpacer spacer12;
    private RichSpacer spacer1;
    private RichPanelGroupLayout panelGroupLayout4;
    private Integer numOrigen;

   public DashBoardCalidadGrafico3Bean(){
        FacesUtils.setActiveBean("dashBoardCalidadGrafico3","DashBoardCalidadGrafico3Bean", DashBoardCalidadGrafico3Bean.class);
        FacesUtils.removeManagedBeanFromSession("dashBoardCalidad");
        FacesUtils.removeManagedBeanFromSession("dashBoardCalidadGrafico2");
        numOrigen=new Integer("3");
        FacesUtils.removeManagedBeanFromSession("numOrigen");
        FacesUtils.setInSession("numOrigen",numOrigen);
       this.load();        
    }
    
    public void load(){
    
    
          UsuarioConectadoTO usuarioConectado = new UsuarioConectadoTO();
          try{
                usuarioConectado = 
                      (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");
                  
          }catch(Exception e){
              System.out.println("++++++++++++++++e"+e);
          }
          try{
          
              if(usuarioConectado==null){
                  this.listaFuentes = this.getListaFuentesMuestras(null);
               
                  } else {   
                  
                  SeguridadDelegate seg = SeguridadDelegate.getInstance(); 
                  PerfilVO pp= new PerfilVO();
                  pp=   seg.getPerfilUsuario(usuarioConectado.getUsuario().getCodigo());
                      
                  if (pp.getCodigo()==ConstantesCalidad.PERFIL_CONSULTA){
                      this.listaFuentes = this.getListaFuentesMuestras(null);
                        
                  }else{  
                  
              this.listaFuentes = this.getListaFuentesMuestras(usuarioConectado.getUsuario().
                                                         getAutoridadAmbiental().
                                                         getId().
                                                         longValue());
              } 
                
          }
          definirFiltros();         
           
      }catch(IdeamException e){
          showMessage(e);
      }
    }   
    public void definirFiltros(){
        try{
            System.out.println("----------3-----DEFINICION INICIAL DE FILTROS DashBoardCalidadGrafico3Bean-----------");
            Object objFiltros = FacesUtils.getManagedBeanValue("filtrosCalidad");           
            ((FiltrosCalidad) objFiltros).setObligatorioFuente(true);
            ((FiltrosCalidad) objFiltros).setVerListaParametros(true);        
            ((FiltrosCalidad) objFiltros).setObligatorioParametro(true);   
            ((FiltrosCalidad) objFiltros).setDisabledListaPuntosMonitoreo(true);          
            ((FiltrosCalidad) objFiltros).setObligatorioPuntoMonitoreo(false);        
            ((FiltrosCalidad) objFiltros).setVerFechaInicial(false);                    
            ((FiltrosCalidad) objFiltros).setVerFechaFinal(false);      
            ((FiltrosCalidad) objFiltros).setObligatorioAnio(true);
            ((FiltrosCalidad) objFiltros).setVerListaAnios(true); 
            ((FiltrosCalidad) objFiltros).setNullFechaInicial();
            ((FiltrosCalidad) objFiltros).setNullFechaFinal(); 
            AdfFacesContext.getCurrentInstance().addPartialTarget( ((FiltrosCalidad) objFiltros).getFc_panelGroupLayout1() );
        }    catch(Exception e){
            System.out.println( "error refrescando filtrosCalidad.jsff:"+e);
        }
    }

    public void cb_buscar_actionListener(ActionEvent actionEvent) {  
        List<Object[]> listaObjGrafica = null;
        listaObjGrafica = getListObjectGrafico();
        if(listaObjGrafica.size()>=1){      
             this.panelFormGrafico.setVisible(true);
             AdfFacesContext.getCurrentInstance().addPartialTarget(this.panelFormGrafico); 
             this.tableDatosGrafico3.setVisible(true);
             AdfFacesContext.getCurrentInstance().addPartialTarget(tableDatosGrafico3);
             AdfFacesContext.getCurrentInstance().addPartialTarget(this.tableDatosGrafico3);   
             AdfFacesContext.getCurrentInstance().addPartialTarget(this.lineGraph1);                   
             }else{
                 this.panelFormGrafico.setVisible(false);
                 AdfFacesContext.getCurrentInstance().addPartialTarget(this.panelFormGrafico); 
                 this.tableDatosGrafico3.setVisible(false);
                 AdfFacesContext.getCurrentInstance().addPartialTarget(tableDatosGrafico3);
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
                // Si no existen criterios la grafica queda invisible
                if (criterios==null){
                    showMessage("Debe seleccionar una Fuente, un parámetro y el año", FacesMessage.SEVERITY_ERROR);                   
                    return listObjectGrafico;
                }else{
                    // valida que se haya seleccionado una fuente
                    if(criterios.getFuente()==null){
                        showMessage("Debe seleccionar una Fuente", FacesMessage.SEVERITY_ERROR);
                        return listObjectGrafico;
                    }
                    // Validar que se haya seleccionado un Parametro
                    if(criterios.getParametro()==null){
                        showMessage("Debe seleccionar un Parámetro", FacesMessage.SEVERITY_ERROR);
                        return listObjectGrafico;
                    } 
                    // Validar que se haya seleccionado un Año
                    if(criterios.getAnio()==null){
                        showMessage("Debe seleccionar el año", FacesMessage.SEVERITY_ERROR);
                        return listObjectGrafico;
                    }    
                }
                    
            }      
            
            
            CalidadDelegate cd = CalidadDelegate.getInstance();  
            this.listaDatos = cd.getParametroPuntoFuenteDatos(criterios,((FiltrosCalidad) obj).getIdAutoridadFuentePunto());   
            if (listaDatos != null) {                             
                  Integer i=1;                  
                  Iterator<DatosReporteCalidadParametrosPuntoPOJO> it = listaDatos.iterator();
                  while (it.hasNext()) {
                      DatosReporteCalidadParametrosPuntoPOJO reg = it.next();
                      reg.setNum(i);
                      i++;
                  } 
              } 
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.tableDatosGrafico3);      
            
            DatosReporteCalidadParametrosPuntoPOJO reg1 = (DatosReporteCalidadParametrosPuntoPOJO)this.listaDatos.get(0);
            
            this.anioString = criterios.getAnio().toString();            
             if(anioString.indexOf(".")!=-1){
                this.anioString = this.anioString.substring(0,this.anioString.indexOf("."));
             }
              this.anioInteger= Integer.parseInt(this.anioString);     
                       
            this.ot_fuente.setValue(reg1.getFuente().toString());
            this.ot_parametro.setValue("Parámetro: "+  reg1.getParametro() + " Unidad: "+ reg1.getUnidad().toString());  
            this.textoNota= " La información presentada calcula el promedio de las muestras y mediciones de "+
                             "calidad en cada punto de monitoreo de la Fuente Hidrica en el año "+  this.anioString +
                             " comparada con el comportamiento de los datos de dos años anteriores ";
               
            this.otNota2.setValue(this.textoNota.toString());
            AdfFacesContext.getCurrentInstance().addPartialTarget(this.otNota2);
            

          List<Object[]> lDatosGrafico1 = cd.getParametroPuntoFuente(criterios,((FiltrosCalidad) obj).getIdAutoridadFuentePunto(), this.anioInteger);
          List<Object[]> lDatosGrafico2 = cd.getParametroPuntoFuente(criterios,((FiltrosCalidad) obj).getIdAutoridadFuentePunto(), this.anioInteger-1);
          List<Object[]> lDatosGrafico3 = cd.getParametroPuntoFuente(criterios,((FiltrosCalidad) obj).getIdAutoridadFuentePunto(), this.anioInteger-2);
         
            for (int j = 0; j < lDatosGrafico1.size(); j++) {
              Object[] dat = lDatosGrafico1.get(j);
              DatosGrafico d = new DatosGrafico();
              d.setDescripcion((String)dat[0]);
              d.setCantidad(new Double(dat[1].toString()));

                Object[] obj1 = { d.getDescripcion(), this.anioInteger, d.getCantidad() };                
              listObjectGrafico.add(obj1);
            }
            
            for (int j = 0; j < lDatosGrafico2.size(); j++) {
              Object[] dat = lDatosGrafico2.get(j);
              DatosGrafico d = new DatosGrafico();
              d.setDescripcion((String)dat[0]);
              d.setCantidad(new Double(dat[1].toString()));
              Object[] obj1 = { d.getDescripcion(), this.anioInteger-1, d.getCantidad() };
              listObjectGrafico.add(obj1);
            }
            
            for (int j = 0; j < lDatosGrafico3.size(); j++) {
              Object[] dat = lDatosGrafico3.get(j);
              DatosGrafico d = new DatosGrafico();
              d.setDescripcion((String)dat[0]);
              d.setCantidad(new Double(dat[1].toString()));
              Object[] obj1 = { d.getDescripcion(), this.anioInteger-2, d.getCantidad() };
              listObjectGrafico.add(obj1);
            }
                                            
                                              
        }catch(IdeamException e){
            showMessage(e);
         } 
        return listObjectGrafico;
    }
    
    public void salidaExcel_actionListener(ActionEvent actionEvent) {
    
            try {
                HashMap parametros = new HashMap();
                parametros.put("p_modulo", "Observatorio del Agua - Componente Calidad");
                parametros.put("p_titulo", "Comportamiento cronológico de un parámetro en los Puntos de Monitoreo de una fuente hídrica");
                this.generateReport("ListadoCalidad_3.jasper", this.listaDatos,
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


    public void setPanelBoxCalidad3(RichPanelBox panelBox1) {
        this.panelBoxCalidad3 = panelBox1;
    }

    public RichPanelBox getPanelBoxCalidad3() {
        return panelBoxCalidad3;
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


    public void setLineGraph1(UIGraph lineGraph1) {
        this.lineGraph1 = lineGraph1;
    }

    public UIGraph getLineGraph1() {
        return lineGraph1;
    }



    public void setParametroId(Long parametroId) {
        this.parametroId = parametroId;
    }

    public Long getParametroId() {
        return parametroId;
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


    public void setPanelFormLayout1(RichPanelFormLayout panelFormLayout1) {
        this.panelFormLayout1 = panelFormLayout1;
    }

    public RichPanelFormLayout getPanelFormLayout1() {
        return panelFormLayout1;
    }


    public void setSpacer3(RichSpacer spacer3) {
        this.spacer3 = spacer3;
    }

    public RichSpacer getSpacer3() {
        return spacer3;
    }


    public void setOtNota(RichOutputLabel otNota) {
        this.otNota = otNota;
    }

    public RichOutputLabel getOtNota() {
        return otNota;
    }

    public void setListaFuentes(List listaFuentes) {
        this.listaFuentes = listaFuentes;
    }

    public List getListaFuentes() {
        return listaFuentes;
    }

    public void setFuenteId(Long fuenteId) {
        this.fuenteId = fuenteId;
    }

    public Long getFuenteId() {
        return fuenteId;
    }


    public void setClink3(RichCommandLink commandLink1) {
        this.clink3 = commandLink1;
    }

    public RichCommandLink getClink3() {
        return clink3;
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

    public void setSpacer8(RichSpacer spacer8) {
        this.spacer8 = spacer8;
    }

    public RichSpacer getSpacer8() {
        return spacer8;
    }


    public void setAnioString(String anioString) {
        this.anioString = anioString;
    }

    public String getAnioString() {
        return anioString;
    }

    public void setIdFuente(Long idFuente) {
        this.idFuente = idFuente;
    }

    public Long getIdFuente() {
        return idFuente;
    }

    public void setIdParametro(Long idParametro) {
        this.idParametro = idParametro;
    }

    public Long getIdParametro() {
        return idParametro;
    }

    public void setAnioInteger(Integer anioInteger) {
        this.anioInteger = anioInteger;
    }

    public Integer getAnioInteger() {
        return anioInteger;
    }

    public void setFuentepunto(String fuentepunto) {
        this.fuentepunto = fuentepunto;
    }

    public String getFuentepunto() {
        return fuentepunto;
    }

    public void setDatos(RichActiveOutputText activeOutputText1) {
        this.datos = activeOutputText1;
    }

    public RichActiveOutputText getDatos() {
        return datos;
    }

    public void setOt_fuente(RichActiveOutputText activeOutputText2) {
        this.ot_fuente = activeOutputText2;
    }

    public RichActiveOutputText getOt_fuente() {
        return ot_fuente;
    }

    public void setOt_parametro(RichActiveOutputText activeOutputText3) {
        this.ot_parametro = activeOutputText3;
    }

    public RichActiveOutputText getOt_parametro() {
        return ot_parametro;
    }

    public void setNota(RichActiveOutputText activeOutputText4) {
        this.nota = activeOutputText4;
    }

    public RichActiveOutputText getNota() {
        return nota;
    }

    public void setTextoNota(String textoNota) {
        this.textoNota = textoNota;
    }

    public String getTextoNota() {
        return textoNota;
    }


    public void setListObjectGrafico(List<Object[]> listObjectGrafico) {
        this.listObjectGrafico = listObjectGrafico;
    }

    public void setOtNota2(RichActiveOutputText otNota2) {
        this.otNota2 = otNota2;
    }

    public RichActiveOutputText getOtNota2() {
        return otNota2;
    }

    public void setListaDatos(List listaDatos) {
        this.listaDatos = listaDatos;
    }

    public List getListaDatos() {
        return listaDatos;
    }

    public void setTableDatosGrafico3(RichTable tableDatosGrafico3) {
        this.tableDatosGrafico3 = tableDatosGrafico3;
    }

    public RichTable getTableDatosGrafico3() {
        return tableDatosGrafico3;
    }

    public void setIdAutoridadPunto(Long idAutoridadPunto) {
        this.idAutoridadPunto = idAutoridadPunto;
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

    public void setSpacer9(RichSpacer spacer9) {
        this.spacer9 = spacer9;
    }

    public RichSpacer getSpacer9() {
        return spacer9;
    }

    public void setCb_buscar(RichCommandButton cb_buscar) {
        this.cb_buscar = cb_buscar;
    }

    public RichCommandButton getCb_buscar() {
        return cb_buscar;
    }

    public void setSpacer10(RichSpacer spacer10) {
        this.spacer10 = spacer10;
    }

    public RichSpacer getSpacer10() {
        return spacer10;
    }

    public void setSpacer11(RichSpacer spacer11) {
        this.spacer11 = spacer11;
    }

    public RichSpacer getSpacer11() {
        return spacer11;
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

    public void setSpacer12(RichSpacer spacer12) {
        this.spacer12 = spacer12;
    }

    public RichSpacer getSpacer12() {
        return spacer12;
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

    public void setPanelFormPuntos(RichPanelFormLayout panelFormPuntos) {
        this.panelFormPuntos = panelFormPuntos;
    }

    public RichPanelFormLayout getPanelFormPuntos() {
        return panelFormPuntos;
    }


    public void setNumOrigen(Integer numOrigen) {
        this.numOrigen = numOrigen;
    }

    public Integer getNumOrigen() {
        return numOrigen;
    }
}
