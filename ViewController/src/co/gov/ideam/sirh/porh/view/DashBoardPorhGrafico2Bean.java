package co.gov.ideam.sirh.dashboard.view;

import co.gov.ideam.sirh.calidad.view.CalidadDelegate;
import co.gov.ideam.sirh.fuentes.model.CriteriosBusquedaTO;
import co.gov.ideam.sirh.fuentes.model.FnttFuente;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import java.util.Random;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UISelectItems;
import javax.faces.event.ActionEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

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

public class DashBoardPorhGrafico2Bean extends StandarDashBoard {

  static Logger logger = Logger.getLogger(DashBoardPorhGrafico1Bean.class);

  private GraphDataModel graphData;
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
  private UIGraph barGraphEjecucionCuencas;
  private String redireccionar;

  /*Grafico 1 fuentes*/
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

  public DashBoardPorhGrafico2Bean() {
    FacesUtils.setActiveBean("DashBoardPorhGrafico2Bean",
                             "DashBoardPorhGrafico2Bean",
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

  protected List getListaAllFuentes() throws IdeamException {
    FuenteDelegate fd = FuenteDelegate.getInstance();
    List lista = new ArrayList(); //carga el selectItem.
    List result = null;
    logger.info("ZZZZZ1");
    result = fd.getAllFuentes();
    logger.info("ZZZZZ2");

    if (result != null) {
      Iterator it = result.iterator();
      Integer i = 0;
      while (it.hasNext()) {
        FnttFuente result1 = (FnttFuente)it.next();
        SelectItem si = new SelectItem(result1, result1.getNombre());
        lista.add(si);
        i = i + 1;
        if (i > 10) {
          break;
        }
      }
    }
    return lista;
  }

  public void cb_aceptar_actionListener(ActionEvent actionEvent) {
    try {
      boolean listo = false;

      if (rsoc_fuentes.getValue() != null) {
        listo = true;
      }
      if (rsoc_usos.getValue() != null) {
        listo = true;
      }
      if (rsoc_parametros.getValue() != null) {
        listo = true;
      }

      if (listo) {
        FnttFuente fuenteSeleccionada = (FnttFuente)rsoc_fuentes.getValue();
        Lista iduso = (Lista)rsoc_usos.getValue();
        Lista idparametro = (Lista)rsoc_parametros.getValue();

        logger.info("XXX" + fuenteSeleccionada.getId() + "/" +
                    iduso.getCodigo().longValue() + "/" +
                    idparametro.getCodigo().longValue());
        graficar(fuenteSeleccionada.getId(), iduso.getCodigo().longValue(),
                 idparametro.getCodigo().longValue());
        initGraphDataModel();
        panelFormLayout1.setVisible(true);
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.panelFormLayout1);
        AdfFacesContext.getCurrentInstance().addPartialTarget(this.barGraphEjecucionCuencas);
      }

    } catch (Exception e) {
      logger.info("Error " + e.getMessage());
    }
  }

  public void graficar(Long idFuente, Long idUso, Long idParametro) {

    logger.info("entro a graficar");
    try {

      List listaProgramas = new ArrayList();
      listaProgramas.add("alo1");
      listaProgramas.add("alo2");
      listaProgramas.add("alo3");

      seriesLabels = new String[listaProgramas.size()];
      data2 = new Object[1][listaProgramas.size()];

      if (listaProgramas != null) {
        int i = 0;
        logger.info("XXX Lista");
        Iterator it = listaProgramas.iterator();
        while (it.hasNext()) {
          String valor = (String)it.next();
          logger.info("XXX valor" + valor);
          seriesLabels[i] = valor;
          data2[0][i] = new Double(2);

          i++;
        }
      }

      logger.info("XXX panel");
      this.panelFormLayout1.setVisible(true);

      AdfFacesContext.getCurrentInstance().addPartialTarget(this.panelFormLayout1);
      logger.info("XXX initGraphDataModel");
      initGraphDataModel();
      AdfFacesContext.getCurrentInstance().addPartialTarget(this.barGraphEjecucionCuencas);
    } catch (Exception e) {
      logger.info("Error en graficar" + e.getMessage());
    }


  }

  public void initGraphDataModel() {
    try {
      LocalXMLDataSource ds =
        new LocalXMLDataSource(columnLabels, seriesLabels, data2);

      setGraphData(new GraphDataModel());
      getGraphData().setDataSource(ds);
    } catch (Exception e) {
      logger.info("Error en initGraphDataModel" + e.getMessage());
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

  public void setBarGraphEjecucionCuencas(UIGraph barGraphMuestrasFuente) {
    this.barGraphEjecucionCuencas = barGraphMuestrasFuente;
  }

  public UIGraph getBarGraphEjecucionCuencas() {
    return barGraphEjecucionCuencas;
  }

  public void setGraphData(GraphDataModel graphData) {
    this.graphData = graphData;
  }

  public GraphDataModel getGraphData() {
    return graphData;
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
}
