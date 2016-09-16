package co.gov.ideam.sirh.dashboard.view;

import co.gov.ideam.sirh.calidad.model.DatosGrafico;
import co.gov.ideam.sirh.calidad.view.CalidadDelegate;
import co.gov.ideam.sirh.calidad.view.FiltrosCalidad;
import co.gov.ideam.sirh.fuentes.model.CriteriosBusquedaTO;
import co.gov.ideam.sirh.fuentes.model.FnttFuente;
import co.gov.ideam.sirh.fuentes.model.FnttTramo;
import co.gov.ideam.sirh.fuentes.view.FuenteDelegate;
import co.gov.ideam.sirh.parametros.model.Autoridades;
import co.gov.ideam.sirh.parametros.model.Categoria;
import co.gov.ideam.sirh.parametros.model.Lista;
import co.gov.ideam.sirh.parametros.view.ParametrosDelegate;
import co.gov.ideam.sirh.pomca.model.Cuenca;
import co.gov.ideam.sirh.pomca.model.Pomca;
import co.gov.ideam.sirh.pomca.model.Programa;
import co.gov.ideam.sirh.pomca.view.AdicionarPomcaBean;
import co.gov.ideam.sirh.pomca.view.PomcaDelegate;
import co.gov.ideam.sirh.porh.model.PorhPlanes;
import co.gov.ideam.sirh.porh.model.PorhPriorizacion;
import co.gov.ideam.sirh.porh.view.PorhDelegate;
import co.gov.ideam.sirh.porh.view.UsosComparator;
import co.gov.ideam.sirh.seguridad.model.PerfilVO;
import co.gov.ideam.sirh.seguridad.model.UsuarioConectadoTO;
import co.gov.ideam.sirh.seguridad.view.SeguridadDelegate;
import co.gov.ideam.sirh.util.ConstantesCalidad;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.view.FacesUtils;
import co.gov.ideam.sirh.view.StandarBean;
import co.gov.ideam.sirh.view.StandarDashBoard;

import java.math.BigDecimal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import java.util.Random;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UISelectItems;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import oracle.adf.view.faces.bi.component.graph.UIGraph;
import oracle.adf.view.faces.bi.model.GraphDataModel;
import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.layout.RichPanelBox;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelSplitter;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandLink;
import oracle.adf.view.rich.component.rich.output.RichSpacer;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.dss.dataView.LocalXMLDataSource;

import org.apache.log4j.Logger;

public class DashBoardPorhGrafico1Bean extends StandarDashBoard {

  static Logger logger = Logger.getLogger(DashBoardPorhGrafico1Bean.class);

  
  private String[] columnLabels = { "Columnas" };
  private String[] seriesLabels;
  private Object[][] data2;


  private RichForm form1;
  private RichDocument document1;
  private RichPanelStretchLayout panelStretchLayout1;
  private RichPanelSplitter panelSplitter1;
  private RichPanelGroupLayout panelGroupLayout2;
  private RichPanelFormLayout panelFormLayout2;
  private RichSpacer spacer4;
  private RichCommandLink clink_inicio;
  private RichSpacer spacer5;
  private RichSpacer spacer6;
  private RichCommandLink clink_grafico1;
  private RichCommandLink clink_grafico2;
  private RichCommandLink clink_grafico3;
  private RichCommandLink clink_grafico4;
  private RichPanelGroupLayout panelGroupLayout3;
  private RichPanelBox panelBoxCalidadGraf1;
  private RichPanelSplitter panelSplitter2;
  private RichPanelGroupLayout panelGroupLayout4;
  private RichPanelFormLayout panelFormLayout12;
  private RichSpacer spacer1;

  private RichPanelGroupLayout panelGroupLayout1;
  private RichPanelFormLayout panelFormLayout1;
  private String redireccionar;

  /*Grafico 1 fuentes*/
  private UIGraph lineGraph1;
  private List<Object[]> listObjectGrafico;
  private CriteriosBusquedaTO criteriosFuentesPuntos =
    new CriteriosBusquedaTO();
  
  private RichSelectOneChoice rsoc_autoridades;
  private UISelectItems selectItemsListaAutoridades;
  private List listaAutoridades;
    
  private RichSelectOneChoice rsoc_fuentes;
  private UISelectItems selectItemsListaFuentes;
  private List listaFuentes;
  
  private RichSelectOneChoice rsoc_tramos;
  private UISelectItems selectItemsListaTramos;
  private List listaTramos;

  private RichSelectOneChoice rsoc_usos;
  private UISelectItems selectItemsListaUsos;
  private List listaUsos;

  private RichSelectOneChoice rsoc_parametros;
  private UISelectItems selectItemsParametros;
  private List listaParametros;
  
  private RichSelectOneChoice rsoc_annos;
  private UISelectItems selectItemsannos;
  private List listaannos;

  public DashBoardPorhGrafico1Bean() {
    FacesUtils.setActiveBean("DashBoardPorhGrafico1Bean",
                             "DashBoardPorhGrafico1Bean",
                             DashBoardPorhGrafico1Bean.class);

    this.load();
  }

  public void load() {
    UsuarioConectadoTO usuarioConectado = new UsuarioConectadoTO();
    try {
      usuarioConectado =
          (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");

    } catch (Exception e) {
      usuarioConectado = null;

    }
    try {
      this.listaAutoridades = this.cargarListaAutoridades(); 
      
      ParametrosDelegate pard = ParametrosDelegate.getInstance();
      criteriosFuentesPuntos = new CriteriosBusquedaTO();

      listaUsos = new ArrayList();

      Categoria categoria = pard.getCategoria(Categoria.USOS_AGUA);
      if (categoria != null && categoria.getLista() != null) {
        List listaCategoria = categoria.getLista();
        Iterator it = listaCategoria.iterator();
        while (it.hasNext()) {
          Lista l = (Lista)it.next();
          if (!l.getValor().equalsIgnoreCase("Otro")) {
            SelectItem item = new SelectItem(l, l.getValor());
            listaUsos.add(item);
          }
        }
      }

      categoria = pard.getCategoria(Categoria.OTROS_USOS);
      if (categoria != null && categoria.getLista() != null) {
        List listaCategoria = categoria.getLista();
        Iterator it = listaCategoria.iterator();
        while (it.hasNext()) {
          Lista l = (Lista)it.next();
          SelectItem item = new SelectItem(l, l.getValor());
          listaUsos.add(item);
        }
      }

      Collections.sort(listaUsos, new UsosComparator());

      listaParametros = this.cargarParametro(Categoria.PARAMETROS);

    } catch (IdeamException e) {
      showMessage(e);
    }

  }

  public void cb_aceptar_actionListener(ActionEvent actionEvent) {
    try {
      boolean listo = true;
      listObjectGrafico = new ArrayList<Object[]>(); 
      logger.info("XXX1");
      if (rsoc_fuentes.getValue() == null) {
        showMessage("Debe seleccionar una Fuente", FacesMessage.SEVERITY_ERROR);
        listo = false;
      }
      if (rsoc_annos.getValue() == null) {
        showMessage("Debe seleccionar un año", FacesMessage.SEVERITY_ERROR);
        listo = false;
      }
      if (rsoc_parametros.getValue() == null) {
        showMessage("Debe seleccionar un parametro", FacesMessage.SEVERITY_ERROR);
        listo = false;
      }


      if (listo) {
        logger.info("XXX2");
        FnttFuente fuenteSeleccionada = (FnttFuente)rsoc_fuentes.getValue();
        logger.info("XXX3");
        Lista idparametro = (Lista)rsoc_parametros.getValue();
        logger.info("XXX4");
        BigDecimal annoSeleccionado = (BigDecimal)rsoc_annos.getValue();
        logger.info("XXX5");
        CalidadDelegate cd = CalidadDelegate.getInstance(); 
        
        logger.info("XXX" + fuenteSeleccionada.getId().longValue()+"--"+idparametro.getCodigo().intValue()+"--"+2009 );
        /*
         * Se crea el query aqui por que este query no es reutilizable, solo sirve para el proposito de este reporte
         * 
         */
        String sql = " SELECT C.ID, C.ID_MUESTRA, C.ID_PARAMETRO, C.UNIDAD, C.VALOR, C.METODO_DETERMINANTE, C.LIMITE_DETECCION, C.SIGNO, C.VALOR2, C.VALOR_CHAR, C.ES_ACREDITADO ,CP.NOMBRE " + 
        " FROM SIRHV2.CALT_MEDICION C,SIRHV2.CALT_MUESTRA CM,SIRHV2.CALT_PUNTO_MONITOREO CP " + 
        " Where C.ID_PARAMETRO = " + idparametro.getCodigo() +
        " AND C.ID_MUESTRA = CM.ID " + 
        " AND EXTRACT(YEAR from CM.FECHA_MUESTREO) = " + annoSeleccionado.intValue() +
        " AND CM.ID_PUNTO = CP.ID  " + 
        " AND CP.ID_FUENTE = " + fuenteSeleccionada.getId() ;

        List<Object[]> lDatosGrafico1 = cd.ejecutaQueryNativo(sql);
        sql = " SELECT C.ID, C.ID_MUESTRA, C.ID_PARAMETRO, C.UNIDAD, C.VALOR, C.METODO_DETERMINANTE, C.LIMITE_DETECCION, C.SIGNO, C.VALOR2, C.VALOR_CHAR, C.ES_ACREDITADO ,CP.NOMBRE " + 
                " FROM SIRHV2.CALT_MEDICION C,SIRHV2.CALT_MUESTRA CM,SIRHV2.CALT_PUNTO_MONITOREO CP " + 
                " Where C.ID_PARAMETRO = " + idparametro.getCodigo() +
                " AND C.ID_MUESTRA = CM.ID " + 
                " AND EXTRACT(YEAR from CM.FECHA_MUESTREO) = " + (annoSeleccionado.intValue()-1) +
                " AND CM.ID_PUNTO = CP.ID  " + 
                " AND CP.ID_FUENTE = " + fuenteSeleccionada.getId() ;
        List<Object[]> lDatosGrafico2 = cd.ejecutaQueryNativo(sql);
        sql = " SELECT C.ID, C.ID_MUESTRA, C.ID_PARAMETRO, C.UNIDAD, C.VALOR, C.METODO_DETERMINANTE, C.LIMITE_DETECCION, C.SIGNO, C.VALOR2, C.VALOR_CHAR, C.ES_ACREDITADO ,CP.NOMBRE " + 
                " FROM SIRHV2.CALT_MEDICION C,SIRHV2.CALT_MUESTRA CM,SIRHV2.CALT_PUNTO_MONITOREO CP " + 
                " Where C.ID_PARAMETRO = " + idparametro.getCodigo() +
                " AND C.ID_MUESTRA = CM.ID " + 
                " AND EXTRACT(YEAR from CM.FECHA_MUESTREO) = " + (annoSeleccionado.intValue()-2) +
                " AND CM.ID_PUNTO = CP.ID  " + 
                " AND CP.ID_FUENTE = " + fuenteSeleccionada.getId() ;
        List<Object[]> lDatosGrafico3 = cd.ejecutaQueryNativo(sql);
        
        logger.info("lDatosGrafico1 " + lDatosGrafico1.size());
        logger.info("lDatosGrafico2 " + lDatosGrafico2.size());
        logger.info("lDatosGrafico3 " + lDatosGrafico3.size());
        
        for (int j = 0; j < lDatosGrafico1.size(); j++) {
          Object[] dat = lDatosGrafico1.get(j);
         /* logger.info("YYY" );
          BigDecimal parametro = (BigDecimal)dat[2];
          logger.info("parametro"+parametro );
          logger.info("dat[4]"+dat[4] );
          */
          Object[] obj1 = { dat[11], annoSeleccionado.intValue(), dat[4] };                
          listObjectGrafico.add(obj1);
        }
        for (int j = 0; j < lDatosGrafico2.size(); j++) {
          Object[] dat = lDatosGrafico2.get(j);

          Object[] obj1 = { dat[11], (annoSeleccionado.intValue()-1), dat[4] };                
          listObjectGrafico.add(obj1);
        }
        logger.info("YYY" );
        for (int j = 0; j < lDatosGrafico3.size(); j++) {
          Object[] dat = lDatosGrafico3.get(j);

          Object[] obj1 = { dat[11], (annoSeleccionado.intValue()-2), dat[4] };                
          listObjectGrafico.add(obj1);
        }

        logger.info("ZZZ" +listObjectGrafico );
        logger.info("ZZZ" +listObjectGrafico.toString() );
        panelFormLayout1.setVisible(true);
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.panelFormLayout1);
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.lineGraph1);
      }

    } catch (Exception e) {
      logger.info("Error " + e.getMessage());
    }
  }

  public void redireccionarAOrigen(ActionEvent actionEvent) {

    String regla =
      (String)FacesUtils.getFromSession("OrigenNavegacionUsuario");
    System.out.println("regla de navegación:" + regla);
    if (regla.equals("observatorio"))
      redireccionar = "observatorio";
    else
      redireccionar = "dashBoard";
  }
  
  public void rsoc_autoridades_valueChangeListener(ValueChangeEvent valueChangeEvent) throws IdeamException {
      Object autoridades = valueChangeEvent.getNewValue();
      try {
          if (autoridades != null) {      
              criteriosFuentesPuntos = new CriteriosBusquedaTO();
              criteriosFuentesPuntos.setAutoridad((Autoridades)this.rsoc_autoridades.getValue());       
              this.listaFuentes = this.getListaFuentesCriterios(criteriosFuentesPuntos); 
              AdfFacesContext.getCurrentInstance().addPartialTarget(rsoc_fuentes);
          }
      } catch (IdeamException e) {
          showMessage(e);
      }
  }
  
  public void rsoc_fuentes_valueChangeListener(ValueChangeEvent valueChangeEvent) throws IdeamException {
      Object fuente = valueChangeEvent.getNewValue();
      try {
          if (fuente != null) { 
            FnttFuente fnttFuente = (FnttFuente) fuente;
            this.listaTramos = this.getListaTramos(fnttFuente.getId().intValue());
            AdfFacesContext.getCurrentInstance().addPartialTarget(rsoc_tramos);
          }
      } catch (IdeamException e) {
          showMessage(e);
      }
  }
  
    public void rsoc_parametros_valueChangeListener(ValueChangeEvent valueChangeEvent) throws IdeamException {
        logger.info("XXX1" );
        Object parametro = valueChangeEvent.getNewValue();
        FnttFuente fuenteSeleccionada = (FnttFuente)rsoc_fuentes.getValue();
        logger.info("XXX2" );
        try {
            if (parametro != null && fuenteSeleccionada !=null) { 
              Lista idparametro = (Lista)parametro;
              
              /*
               * Se crea el query aqui por que este query no es reutilizable, solo sirve para el proposito de este reporte
               * 
               */
              String sql = " SELECT DISTINCT (EXTRACT(YEAR from CM.FECHA_MUESTREO))  " + 
              " FROM SIRHV2.CALT_MEDICION C,SIRHV2.CALT_MUESTRA CM,SIRHV2.CALT_PUNTO_MONITOREO CP " + 
              " Where C.ID_PARAMETRO = " + idparametro.getCodigo() +
              " AND C.ID_MUESTRA = CM.ID " + 
              " AND CM.ID_PUNTO = CP.ID  " + 
              " AND CP.ID_FUENTE = " + fuenteSeleccionada.getId() ;
              
              CalidadDelegate cd = CalidadDelegate.getInstance(); 
              List<Object[]> listaAnnosQuery = cd.ejecutaQueryNativo(sql);
              
              logger.info("yyy" + listaAnnosQuery);
              Iterator it = listaAnnosQuery.iterator();
              listaannos = new ArrayList();
              while (it.hasNext()) {
                BigDecimal anno = (BigDecimal)it.next();
                logger.info("yyy" + anno);
                  SelectItem item = new SelectItem(anno, anno.toString());
                  this.listaannos.add(item);
              }
              
              AdfFacesContext.getCurrentInstance().addPartialTarget(rsoc_annos);
            }
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

  public void setSpacer4(RichSpacer spacer4) {
    this.spacer4 = spacer4;
  }

  public RichSpacer getSpacer4() {
    return spacer4;
  }

  public void setClink_inicio(RichCommandLink clink_inicio) {
    this.clink_inicio = clink_inicio;
  }

  public RichCommandLink getClink_inicio() {
    return clink_inicio;
  }

  public void setSpacer5(RichSpacer spacer5) {
    this.spacer5 = spacer5;
  }

  public RichSpacer getSpacer5() {
    return spacer5;
  }

  public void setClink_grafico1(RichCommandLink clink_grafico1) {
    this.clink_grafico1 = clink_grafico1;
  }

  public RichCommandLink getClink_grafico1() {
    return clink_grafico1;
  }

  public void setSpacer6(RichSpacer spacer6) {
    this.spacer6 = spacer6;
  }

  public RichSpacer getSpacer6() {
    return spacer6;
  }

  public void setClink_grafico2(RichCommandLink clink_grafico2) {
    this.clink_grafico2 = clink_grafico2;
  }

  public RichCommandLink getClink_grafico2() {
    return clink_grafico2;
  }

  public void setRedireccionar(String redireccionar) {
    this.redireccionar = redireccionar;
  }

  public String getRedireccionar() {
    return redireccionar;
  }

  public void setPanelGroupLayout3(RichPanelGroupLayout panelGroupLayout3) {
    this.panelGroupLayout3 = panelGroupLayout3;
  }

  public RichPanelGroupLayout getPanelGroupLayout3() {
    return panelGroupLayout3;
  }

  public void setPanelBoxCalidadGraf1(RichPanelBox panelBoxCalidadGraf1) {
    this.panelBoxCalidadGraf1 = panelBoxCalidadGraf1;
  }

  public RichPanelBox getPanelBoxCalidadGraf1() {
    return panelBoxCalidadGraf1;
  }

  public void setPanelSplitter2(RichPanelSplitter panelSplitter2) {
    this.panelSplitter2 = panelSplitter2;
  }

  public RichPanelSplitter getPanelSplitter2() {
    return panelSplitter2;
  }

  public void setPanelGroupLayout4(RichPanelGroupLayout panelGroupLayout4) {
    this.panelGroupLayout4 = panelGroupLayout4;
  }

  public RichPanelGroupLayout getPanelGroupLayout4() {
    return panelGroupLayout4;
  }

  public void setPanelFormLayout12(RichPanelFormLayout panelFormLayout12) {
    this.panelFormLayout12 = panelFormLayout12;
  }

  public RichPanelFormLayout getPanelFormLayout12() {
    return panelFormLayout12;
  }

  public void setSpacer1(RichSpacer spacer1) {
    this.spacer1 = spacer1;
  }

  public RichSpacer getSpacer1() {
    return spacer1;
  }

  public void setPanelGroupLayout1(RichPanelGroupLayout panelGroupLayout1) {
    this.panelGroupLayout1 = panelGroupLayout1;
  }

  public RichPanelGroupLayout getPanelGroupLayout1() {
    return panelGroupLayout1;
  }

  public void setPanelFormLayout1(RichPanelFormLayout panelFormLayout1) {
    this.panelFormLayout1 = panelFormLayout1;
  }

  public RichPanelFormLayout getPanelFormLayout1() {
    return panelFormLayout1;
  }

  public void setColumnLabels(String[] columnLabels) {
    this.columnLabels = columnLabels;
  }

  public String[] getColumnLabels() {
    return columnLabels;
  }

  public void setSeriesLabels(String[] seriesLabels) {
    this.seriesLabels = seriesLabels;
  }

  public String[] getSeriesLabels() {
    return seriesLabels;
  }

  public void setData2(Object[][] data2) {
    this.data2 = data2;
  }

  public Object[][] getData2() {
    return data2;
  }

  public static void setLogger(Logger logger) {
    DashBoardPorhGrafico1Bean.logger = logger;
  }

  public static Logger getLogger() {
    return logger;
  }

  public void setCriteriosFuentesPuntos(CriteriosBusquedaTO criteriosFuentesPuntos) {
    this.criteriosFuentesPuntos = criteriosFuentesPuntos;
  }

  public CriteriosBusquedaTO getCriteriosFuentesPuntos() {
    return criteriosFuentesPuntos;
  }

  public void setRsoc_fuentes(RichSelectOneChoice rsoc_fuentes) {
    this.rsoc_fuentes = rsoc_fuentes;
  }

  public RichSelectOneChoice getRsoc_fuentes() {
    return rsoc_fuentes;
  }

  public void setSelectItemsListaFuentes(UISelectItems selectItemsListaFuentes) {
    this.selectItemsListaFuentes = selectItemsListaFuentes;
  }

  public UISelectItems getSelectItemsListaFuentes() {
    return selectItemsListaFuentes;
  }

  public void setListaFuentes(List listaFuentes) {
    this.listaFuentes = listaFuentes;
  }

  public List getListaFuentes() {
    return listaFuentes;
  }

  public void setRsoc_usos(RichSelectOneChoice rsoc_usos) {
    this.rsoc_usos = rsoc_usos;
  }

  public RichSelectOneChoice getRsoc_usos() {
    return rsoc_usos;
  }

  public void setSelectItemsListaUsos(UISelectItems selectItemsListaUsos) {
    this.selectItemsListaUsos = selectItemsListaUsos;
  }

  public UISelectItems getSelectItemsListaUsos() {
    return selectItemsListaUsos;
  }

  public void setListaUsos(List listaUsos) {
    this.listaUsos = listaUsos;
  }

  public List getListaUsos() {
    return listaUsos;
  }

  public void setRsoc_parametros(RichSelectOneChoice rsoc_parametros) {
    this.rsoc_parametros = rsoc_parametros;
  }

  public RichSelectOneChoice getRsoc_parametros() {
    return rsoc_parametros;
  }

  public void setSelectItemsParametros(UISelectItems selectItemsParametros) {
    this.selectItemsParametros = selectItemsParametros;
  }

  public UISelectItems getSelectItemsParametros() {
    return selectItemsParametros;
  }

  public void setListaParametros(List listaParametros) {
    this.listaParametros = listaParametros;
  }

  public List getListaParametros() {
    return listaParametros;
  }

  public void setClink_grafico3(RichCommandLink clink_grafico3) {
    this.clink_grafico3 = clink_grafico3;
  }

  public RichCommandLink getClink_grafico3() {
    return clink_grafico3;
  }

  public void setClink_grafico4(RichCommandLink clink_grafico4) {
    this.clink_grafico4 = clink_grafico4;
  }

  public RichCommandLink getClink_grafico4() {
    return clink_grafico4;
  }

  public void setRsoc_autoridades(RichSelectOneChoice rsoc_autoridades) {
    this.rsoc_autoridades = rsoc_autoridades;
  }

  public RichSelectOneChoice getRsoc_autoridades() {
    return rsoc_autoridades;
  }

  public void setSelectItemsListaAutoridades(UISelectItems selectItemsListaAutoridades) {
    this.selectItemsListaAutoridades = selectItemsListaAutoridades;
  }

  public UISelectItems getSelectItemsListaAutoridades() {
    return selectItemsListaAutoridades;
  }

  public void setListaAutoridades(List listaAutoridades) {
    this.listaAutoridades = listaAutoridades;
  }

  public List getListaAutoridades() {
    return listaAutoridades;
  }

  public void setRsoc_tramos(RichSelectOneChoice rsoc_tramos) {
    this.rsoc_tramos = rsoc_tramos;
  }

  public RichSelectOneChoice getRsoc_tramos() {
    return rsoc_tramos;
  }

  public void setSelectItemsListaTramos(UISelectItems selectItemsListaTramos) {
    this.selectItemsListaTramos = selectItemsListaTramos;
  }

  public UISelectItems getSelectItemsListaTramos() {
    return selectItemsListaTramos;
  }

  public void setListaTramos(List listaTramos) {
    this.listaTramos = listaTramos;
  }

  public List getListaTramos() {
    return listaTramos;
  }

  public void setLineGraph1(UIGraph lineGraph1) {
    this.lineGraph1 = lineGraph1;
  }

  public UIGraph getLineGraph1() {
    return lineGraph1;
  }

  public void setListObjectGrafico(List<Object[]> listObjectGrafico) {
    this.listObjectGrafico = listObjectGrafico;
  }

  public List<Object[]> getListObjectGrafico() {
    return listObjectGrafico;
  }

  public void setRsoc_annos(RichSelectOneChoice rsoc_annos) {
    this.rsoc_annos = rsoc_annos;
  }

  public RichSelectOneChoice getRsoc_annos() {
    return rsoc_annos;
  }

  public void setSelectItemsannos(UISelectItems selectItemsannos) {
    this.selectItemsannos = selectItemsannos;
  }

  public UISelectItems getSelectItemsannos() {
    return selectItemsannos;
  }

  public void setListaannos(List listaannos) {
    this.listaannos = listaannos;
  }

  public List getListaannos() {
    return listaannos;
  }
}
