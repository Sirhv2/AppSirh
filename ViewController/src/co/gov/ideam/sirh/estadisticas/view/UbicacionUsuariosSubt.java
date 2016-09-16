package co.gov.ideam.sirh.estadisticas.view;

import co.gov.ideam.sirh.seguridad.model.PerfilVO;
import co.gov.ideam.sirh.seguridad.model.UsuarioConectadoTO;
import co.gov.ideam.sirh.seguridad.view.SeguridadDelegate;
import co.gov.ideam.sirh.usuarios.agua.view.UsuariosAguaDelegate;
import co.gov.ideam.sirh.util.ConstantesCalidad;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.view.FacesUtils;
import co.gov.ideam.sirh.view.StandarBean;

import javax.faces.event.ActionEvent;

import oracle.adf.view.faces.bi.component.graph.UIGraph;
import oracle.adf.view.faces.bi.event.ClickEvent;
import oracle.adf.view.faces.bi.model.GraphDataModel;
import oracle.adf.view.rich.component.rich.RichDialog;
import oracle.adf.view.rich.component.rich.RichPopup;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.component.rich.output.RichSpacer;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.dss.dataView.Attributes;
import oracle.dss.dataView.ComponentHandle;
import oracle.dss.dataView.DataComponentHandle;
import oracle.dss.dataView.LocalXMLDataSource;

public class UbicacionUsuariosSubt extends StandarBean {
  private RichPanelStretchLayout psl1;

  private GraphDataModel graphData;
  private String ubicacionSeleccionada;
  private String action;

  private String[] columnLabels = { "Ubicacion de Usuarios" };
  private String[] seriesLabels;
  private Object[][] data2;
  private UIGraph pg_ubicacion;
  private RichPanelGroupLayout pgl1;
  private RichPopup p_detalle;
  private RichDialog d_detalle;
  private RichOutputText ot_detalle;
  private RichPanelGroupLayout pgl2;
  private RichCommandButton cb_si;
  private RichSpacer s1;
  private RichCommandButton cb_no;

  public UbicacionUsuariosSubt() {
    String datos[][] = null;
    UsuarioConectadoTO usuarioConectado = new UsuarioConectadoTO();
    try {
      usuarioConectado =
          (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");

    } catch (Exception e) {
      System.out.println(e);
    }
    try {

      UsuariosAguaDelegate uad = UsuariosAguaDelegate.getInstance();
      if (usuarioConectado == null) {
        datos = uad.getUbicacionUsuariosSubt(null);
      } else {
        SeguridadDelegate seg = SeguridadDelegate.getInstance();
        PerfilVO pp = new PerfilVO();
        pp = seg.getPerfilUsuario(usuarioConectado.getUsuario().getCodigo());

        if (pp.getCodigo() == ConstantesCalidad.PERFIL_CONSULTA) {
          datos = uad.getUbicacionUsuariosSubt(null);

        } else {

          datos =
              uad.getUbicacionUsuariosSubt(usuarioConectado.getUsuario().getAutoridadAmbiental().getId().longValue());
        }
      }
      seriesLabels = new String[datos[0].length];
      data2 = new Object[1][datos[0].length];
      for (int i = 0; i < datos[0].length; i++) {
        seriesLabels[i] = datos[0][i];
        data2[0][i] = new Integer(datos[1][i]);
      }
      initGraphDataModel();
    } catch (IdeamException e) {
      showMessage(e);
    }
  }

  public void initGraphDataModel() {
    LocalXMLDataSource ds =
      new LocalXMLDataSource(columnLabels, seriesLabels, data2);

    setGraphData(new GraphDataModel());
    getGraphData().setDataSource(ds);
  }

  public void setPsl1(RichPanelStretchLayout psl1) {
    this.psl1 = psl1;
  }

  public RichPanelStretchLayout getPsl1() {
    return psl1;
  }

  public GraphDataModel getGraphData() {
    return graphData;
  }

  public void setGraphData(GraphDataModel graphData) {
    this.graphData = graphData;
  }

  public void setPg_ubicacion(UIGraph pg1) {
    this.pg_ubicacion = pg1;
  }

  public UIGraph getPg_ubicacion() {
    return pg_ubicacion;
  }

  public void pg_ubicacion_clickListener(ClickEvent clickEvent) {
    ComponentHandle handle = clickEvent.getComponentHandle();
    ubicacionSeleccionada = "";
    setAction("");
    if (handle instanceof DataComponentHandle) {
      DataComponentHandle dhandle = (DataComponentHandle)handle;
      //System.out.println("Value: " + dhandle.getValue(DataComponentHandle.UNFORMATTED_VALUE));

      Attributes[] seriesInfo = dhandle.getSeriesAttributes();
      if (seriesInfo != null) {
        for (Attributes attrs : seriesInfo) {
          ubicacionSeleccionada =
              attrs.getValue(Attributes.ID_VALUE).toString();
        }
      }
      String params[] = { "los predios", ubicacionSeleccionada };
      String texto = getText("DESEA_VER_DETALLE", params);
      this.ot_detalle.setValue(texto);
      AdfFacesContext.getCurrentInstance().addPartialTarget(p_detalle);
      showPopup(p_detalle, true);
    }
  }

  public void setPgl1(RichPanelGroupLayout pgl1) {
    this.pgl1 = pgl1;
  }

  public RichPanelGroupLayout getPgl1() {
    return pgl1;
  }

  public void setP_detalle(RichPopup p1) {
    this.p_detalle = p1;
  }

  public RichPopup getP_detalle() {
    return p_detalle;
  }

  public void setD_detalle(RichDialog d1) {
    this.d_detalle = d1;
  }

  public RichDialog getD_detalle() {
    return d_detalle;
  }


  public void setOt_detalle(RichOutputText ot1) {
    this.ot_detalle = ot1;
  }

  public RichOutputText getOt_detalle() {
    return ot_detalle;
  }

  public void setPgl2(RichPanelGroupLayout pgl2) {
    this.pgl2 = pgl2;
  }

  public RichPanelGroupLayout getPgl2() {
    return pgl2;
  }

  public void setCb_si(RichCommandButton cb1) {
    this.cb_si = cb1;
  }

  public RichCommandButton getCb_si() {
    return cb_si;
  }

  public void setS1(RichSpacer s1) {
    this.s1 = s1;
  }

  public RichSpacer getS1() {
    return s1;
  }

  public void setCb_no(RichCommandButton cb2) {
    this.cb_no = cb2;
  }

  public RichCommandButton getCb_no() {
    return cb_no;
  }

  public String getAction() {
    return action;
  }

  public void setAction(String action) {
    this.action = action;
  }

  public void cb_si_actionListener(ActionEvent actionEvent) {
    FacesUtils.setInSession("FiltroUsuariosDashboard", ubicacionSeleccionada);
    action = "predios";
  }

  public void cb_no_actionListener(ActionEvent actionEvent) {
    showPopup(p_detalle, false);
  }
}
