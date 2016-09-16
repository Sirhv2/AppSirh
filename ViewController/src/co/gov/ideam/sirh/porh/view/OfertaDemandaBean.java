package co.gov.ideam.sirh.porh.view;

import co.gov.ideam.sirh.calidad.model.PuntoMonitoreo;
import co.gov.ideam.sirh.fuentes.model.FnttFuente;
import co.gov.ideam.sirh.fuentes.model.FnttTramo;
import co.gov.ideam.sirh.parametros.model.Autoridades;
import co.gov.ideam.sirh.parametros.model.Categoria;
import co.gov.ideam.sirh.parametros.model.Lista;
import co.gov.ideam.sirh.parametros.model.PartZonificListas;
import co.gov.ideam.sirh.porh.model.CaudalAmbientalDTO;
import co.gov.ideam.sirh.porh.model.CaudalMedioTO;

import co.gov.ideam.sirh.porh.model.PorhOfertaDemanda;
import co.gov.ideam.sirh.porh.model.PorhPlanes;
import co.gov.ideam.sirh.porh.model.TotalCapVertTO;

import co.gov.ideam.sirh.seguridad.model.UsuarioConectadoTO;
import co.gov.ideam.sirh.usuarios.agua.view.UsuariosAguaDelegate;
import co.gov.ideam.sirh.util.Constantes;
import co.gov.ideam.sirh.util.ConstantesParametros;
import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.view.FacesUtils;

import co.gov.ideam.sirh.view.StandarBean;

import java.text.DecimalFormat;

import java.text.NumberFormat;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import javax.faces.event.ValueChangeEvent;

import javax.faces.model.SelectItem;

import oracle.adf.view.rich.component.rich.RichDocument;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.data.RichColumn;
import oracle.adf.view.rich.component.rich.data.RichTable;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.input.RichSelectOneChoice;
import oracle.adf.view.rich.component.rich.layout.RichPanelBox;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelStretchLayout;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.component.rich.nav.RichCommandLink;
import oracle.adf.view.rich.component.rich.output.RichOutputText;
import oracle.adf.view.rich.component.rich.output.RichPanelCollection;
import oracle.adf.view.rich.component.rich.output.RichSpacer;
import oracle.adf.view.rich.context.AdfFacesContext;

public class OfertaDemandaBean extends StandarBean{
    private RichForm f2;
    private RichDocument d2;
    private RichPanelStretchLayout psl_total;
    private RichPanelFormLayout pfl1;
    private RichInputText it1;
    private RichInputText it2;
    private RichInputText it_oferta_disponible;
    private RichInputText it4;
    private RichInputText it5;
    private RichInputText it6;
    private RichInputText it7;
    private RichInputText it8;
    private RichOutputText outputText_caudal_ambiental;
    private RichPanelGroupLayout pgl2;
    
    private List listaCaudal;
    private List listaCaptacionesVertimientos;
    private List listaCaudalAmbientalMet3;
    private PorhOfertaDemanda ofertaDemanda;
    private String nombreFuente;
    private String nombreTramo;
    private Double demandaCalculada;
    private PorhPlanes planOrdenamiento;
    private String valorOfertaDisponibleCalculada = "Indeterminada";
    
    private RichTable t_caudal;
    private RichInputText it9;
    private RichInputText it10;
    private RichTable t_capvert;
    private RichTable richTableCaudalAmbiental;
    private RichInputText it11;
    private RichInputText it12;
    private RichPanelBox pb1;
    private RichSpacer s2;
    private RichOutputText ot1;
    private RichOutputText ot4;
    private RichSpacer s3;
    private RichOutputText ot5;
    private RichSpacer s4;
    private RichOutputText ot6;
    private RichSpacer s5;
    private RichOutputText ot7;
    private RichSpacer s6;
    private RichOutputText ot8;
    private RichInputText it_oferta_anio_medio;
    private RichInputText it15;
    private RichPanelCollection pc1;
    private RichPanelGroupLayout pgl1;
    private RichCommandButton cb_aceptar;
    private RichSpacer s1;
    private RichCommandButton cb_regresar;
    private RichPanelGroupLayout pgl3;
    private RichSpacer s7;
    private RichCommandLink cl1;
    private RichSpacer s8;
    private RichCommandLink cl2;
    private RichSpacer s9;
    private RichSpacer s10;
    private RichInputText caudalTotal;
    private RichInputText caudalAmbientalTotal;
    private RichSelectOneChoice selectOneChoiceMetodologia;
    private UISelectItems selectItemsMetodologia;
    private List metodologiasCaudalAmbiental;
    
    private static final String caudalMedioMensual = "Caudal medio mensual multianual";
    private static final String caudalAmbienalMedio = "Caudal ambiental medio mensual multianual";
    private static final String numeroCaptaciones = "Captaciones";
    private static final String numeroVertimientos = "Vertimeintos";
    private static final String caudalAmbientalSeco = "Año seco";
    private static final String caudalAmbientalMedio = "Año medio";
    private static final String caudalAmbientalHumedo = "Año humedo";
    private static final Integer metodologia1Id = 1058;
    private static final Integer metodologia2Id = 1059;
    private static final Integer metodologia3Id = 1060;
    
    private RichPanelFormLayout panelFormLayoutOfertaDemanda;
    private RichPanelFormLayout panelFormLayoutMetodologia1;
    private RichPanelFormLayout panelFormLayoutMetodologia2;
    private RichPanelFormLayout panelFormLayoutMetodologia3;
    private RichInputText indiceVulnerabilidadHidrica;
    private RichInputText indiceCalidadAgua;
    private RichInputText monitoreoBiologicoGrupoTrabajo;
    private RichInputText indiceUsoAgua;
    private RichInputText indiceValorImportacionEcologicaEspecie;
    private RichInputText indiceCalidadEcologica;
    private RichInputText indiceRegulacionHidrica;
    private RichInputText inputTextproyecciones_demanda_corto;
    private RichInputText inputTextproyecciones_demanda_mediano;
    private RichInputText inputTextproyecciones_demanda_largo;
    private RichInputText inputTextmetodo_empleado;
    private Boolean renderOferta;
    private Boolean renderDemanda;
    private Boolean renderIndices;
    private Boolean renderMetodologia1;
    private Boolean renderMetodologia2;
    private Boolean renderMetodologia3;
    
    
    public OfertaDemandaBean(){
        FacesUtils.setActiveBean("ofertaDemanda",
                                 "ofertaDemanda",
                                 UsuariosAguaDelegate.class);
        this.load();
    }
    
    public void load(){
    try {
      renderOferta = false;
      renderDemanda = false;
      renderIndices = false;
      renderMetodologia1 = false;
      renderMetodologia2 = false;
      renderMetodologia3 = false;

      this.metodologiasCaudalAmbiental =
          this.cargarParametro(Categoria.METODOLOGIA_CAUDAL_AMBIENTAL);

      UsuarioConectadoTO usuarioConectado =
        (UsuarioConectadoTO)FacesUtils.getFromSession("usuarioConectado");

      Autoridades autoridadAmbiental =
        usuarioConectado.getUsuario().getAutoridadAmbiental();

      if (autoridadAmbiental == null ||
          autoridadAmbiental.getId().intValue() == Constantes.IDEAM) {
        showMessage(getText("NO_EXISTE_AUTORIDAD_AMBIENTAL"),
                    FacesMessage.SEVERITY_ERROR);
        return;
      }

      FnttTramo tramo =
        (FnttTramo)FacesUtils.getFromSession("tramoSeleccionado");
      FnttFuente fuente =
        (FnttFuente)FacesUtils.getFromSession("fuenteSeleccionada");
      PuntoMonitoreo PuntoMonitoreo =
        (PuntoMonitoreo)FacesUtils.getFromSession("puntoMonitoreoSeleccionado");

      String renderCase =
        (String)FacesUtils.getFromSession("renderCaseOfertaDemandaIndices");


      if (renderCase.equals("Oferta")) {
        renderOferta = true;
      } else if (renderCase.equals("Demanda")) {
        renderDemanda = true;
      } else if (renderCase.equals("Indices")) {
        renderIndices = true;
      }
      
      nombreFuente = fuente.getNombre();
      nombreTramo = tramo.getNombre();
      try {
        PorhDelegate pd = PorhDelegate.getInstance();
        ofertaDemanda =
            pd.getOfertaDemanda(fuente.getId(), tramo.getId(), PuntoMonitoreo.getId());
        if (ofertaDemanda == null) {
          ofertaDemanda = new PorhOfertaDemanda();
          ofertaDemanda.setId_fuente(fuente.getId());
          ofertaDemanda.setId_tramo(tramo.getId());
          ofertaDemanda.setId_punto_monitoreo(PuntoMonitoreo.getId());
          ofertaDemanda.setCodigoAutoridad(autoridadAmbiental.getId().longValue());
        }

        UsuariosAguaDelegate uad = UsuariosAguaDelegate.getInstance();
        this.demandaCalculada = uad.getDemandaTramo(tramo);
      } catch (IdeamException e) {
        showMessage(e);
      }

      if (ofertaDemanda.getCau_amb_met() != null && ofertaDemanda.getCau_amb_met().getCodigo().toString().equals(metodologia1Id.toString())) {
        renderMetodologia1 = true;
      } 
      if (ofertaDemanda.getCau_amb_met() != null && ofertaDemanda.getCau_amb_met().getCodigo().toString().equals(metodologia2Id.toString())) {
        renderMetodologia2 = true;
      } 
      if (ofertaDemanda.getCau_amb_met() != null && ofertaDemanda.getCau_amb_met().getCodigo().toString().equals(metodologia3Id.toString())) {
        renderMetodologia3 = true;
      }

      setPlanOrdenamiento((PorhPlanes)FacesUtils.getFromSession("planSeleccionado"));

      CaudalMedioTO mensual = new CaudalMedioTO();
      mensual.setNombre(caudalMedioMensual);
      mensual.setEnero(ofertaDemanda.getCaudal_mmm_enero());
      mensual.setFebrero(ofertaDemanda.getCaudal_mmm_febrero());
      mensual.setMarzo(ofertaDemanda.getCaudal_mmm_marzo());
      mensual.setAbril(ofertaDemanda.getCaudal_mmm_abril());
      mensual.setMayo(ofertaDemanda.getCaudal_mmm_mayo());
      mensual.setJunio(ofertaDemanda.getCaudal_mmm_junio());
      mensual.setJulio(ofertaDemanda.getCaudal_mmm_julio());
      mensual.setAgosto(ofertaDemanda.getCaudal_mmm_agosto());
      mensual.setSeptiembre(ofertaDemanda.getCaudal_mmm_sep());
      mensual.setOctubre(ofertaDemanda.getCaudal_mmm_oct());
      mensual.setNoviembre(ofertaDemanda.getCaudal_mmm_nov());
      mensual.setDiciembre(ofertaDemanda.getCaudal_mmm_dic());


      CaudalMedioTO ambiental = new CaudalMedioTO();
      ambiental.setNombre(caudalAmbienalMedio);
      ambiental.setEnero(ofertaDemanda.getCaudal_mamm_enero());
      ambiental.setFebrero(ofertaDemanda.getCaudal_mamm_febrero());
      ambiental.setMarzo(ofertaDemanda.getCaudal_mamm_marzo());
      ambiental.setAbril(ofertaDemanda.getCaudal_mamm_abril());
      ambiental.setMayo(ofertaDemanda.getCaudal_mamm_mayo());
      ambiental.setJunio(ofertaDemanda.getCaudal_mamm_junio());
      ambiental.setJulio(ofertaDemanda.getCaudal_mamm_julio());
      ambiental.setAgosto(ofertaDemanda.getCaudal_mamm_agosto());
      ambiental.setSeptiembre(ofertaDemanda.getCaudal_mamm_sep());
      ambiental.setOctubre(ofertaDemanda.getCaudal_mamm_oct());
      ambiental.setNoviembre(ofertaDemanda.getCaudal_mamm_nov());
      ambiental.setDiciembre(ofertaDemanda.getCaudal_mamm_dic());

      this.listaCaudal = new ArrayList();
      this.listaCaudal.add(mensual);
      //this.listaCaudal.add(ambiental);

      TotalCapVertTO captaciones = new TotalCapVertTO();
      captaciones.setNombre(numeroCaptaciones);
      captaciones.setLegales(ofertaDemanda.getNum_captacion_leg());
      captaciones.setDemandaEstimadaLegales(ofertaDemanda.getDemanda_estimada_capt_leg());
      captaciones.setIlegales(ofertaDemanda.getNum_captacion_ileg());
      captaciones.setDemandaEstimadaIlegales(ofertaDemanda.getDemanda_estimada_capt_ile());

      TotalCapVertTO vertimientos = new TotalCapVertTO();
      vertimientos.setNombre(numeroVertimientos);
      vertimientos.setLegales(ofertaDemanda.getNum_vertimientos_leg());
      vertimientos.setDemandaEstimadaLegales(ofertaDemanda.getDemanda_estimada_vert_leg());
      vertimientos.setIlegales(ofertaDemanda.getNum_vertimientos_ileg());
      vertimientos.setDemandaEstimadaIlegales(ofertaDemanda.getDemanda_estimada_vert_ile());

      this.listaCaptacionesVertimientos = new ArrayList();
      this.listaCaptacionesVertimientos.add(captaciones);
      this.listaCaptacionesVertimientos.add(vertimientos);

      CaudalAmbientalDTO caudalAnoSeco = new CaudalAmbientalDTO();
      CaudalAmbientalDTO caudalAnoMedio = new CaudalAmbientalDTO();
      CaudalAmbientalDTO caudalAnoHumeno = new CaudalAmbientalDTO();


      caudalAnoSeco.setNombre(caudalAmbientalSeco);
      caudalAnoSeco.setCaudalAmbientalEnero(ofertaDemanda.getCau_amb_met3_am_ene());
      caudalAnoSeco.setCaudalAmbientalFebrero(ofertaDemanda.getCau_amb_met3_as_feb());
      caudalAnoSeco.setCaudalAmbientalMarzo(ofertaDemanda.getCau_amb_met3_as_mar());
      caudalAnoSeco.setCaudalAmbientalAbril(ofertaDemanda.getCau_amb_met3_as_abr());
      caudalAnoSeco.setCaudalAmbientalMayo(ofertaDemanda.getCau_amb_met3_as_may());
      caudalAnoSeco.setCaudalAmbientalJunio(ofertaDemanda.getCau_amb_met3_as_jun());
      caudalAnoSeco.setCaudalAmbientalJulio(ofertaDemanda.getCau_amb_met3_as_jul());
      caudalAnoSeco.setCaudalAmbientalAgosto(ofertaDemanda.getCau_amb_met3_as_ago());
      caudalAnoSeco.setCaudalAmbientalSeptiembre(ofertaDemanda.getCau_amb_met3_as_sep());
      caudalAnoSeco.setCaudalAmbientalOctubre(ofertaDemanda.getCau_amb_met3_as_oct());
      caudalAnoSeco.setCaudalAmbientalNoviembre(ofertaDemanda.getCau_amb_met3_as_nov());
      caudalAnoSeco.setCaudalAmbientalDiciembre(ofertaDemanda.getCau_amb_met3_as_dic());

      caudalAnoMedio.setNombre(caudalAmbientalMedio);
      caudalAnoMedio.setCaudalAmbientalEnero(ofertaDemanda.getCau_amb_met3_am_ene());
      caudalAnoMedio.setCaudalAmbientalFebrero(ofertaDemanda.getCau_amb_met3_am_feb());
      caudalAnoMedio.setCaudalAmbientalMarzo(ofertaDemanda.getCau_amb_met3_am_mar());
      caudalAnoMedio.setCaudalAmbientalAbril(ofertaDemanda.getCau_amb_met3_am_abr());
      caudalAnoMedio.setCaudalAmbientalMayo(ofertaDemanda.getCau_amb_met3_am_may());
      caudalAnoMedio.setCaudalAmbientalJunio(ofertaDemanda.getCau_amb_met3_am_jun());
      caudalAnoMedio.setCaudalAmbientalJulio(ofertaDemanda.getCau_amb_met3_am_jul());
      caudalAnoMedio.setCaudalAmbientalAgosto(ofertaDemanda.getCau_amb_met3_am_ago());
      caudalAnoMedio.setCaudalAmbientalSeptiembre(ofertaDemanda.getCau_amb_met3_am_sep());
      caudalAnoMedio.setCaudalAmbientalOctubre(ofertaDemanda.getCau_amb_met3_am_oct());
      caudalAnoMedio.setCaudalAmbientalNoviembre(ofertaDemanda.getCau_amb_met3_am_nov());
      caudalAnoMedio.setCaudalAmbientalDiciembre(ofertaDemanda.getCau_amb_met3_am_dic());

      caudalAnoHumeno.setNombre(caudalAmbientalHumedo);
      caudalAnoHumeno.setCaudalAmbientalEnero(ofertaDemanda.getCau_amb_met3_ah_ene());
      caudalAnoHumeno.setCaudalAmbientalFebrero(ofertaDemanda.getCau_amb_met3_ah_feb());
      caudalAnoHumeno.setCaudalAmbientalMarzo(ofertaDemanda.getCau_amb_met3_ah_mar());
      caudalAnoHumeno.setCaudalAmbientalAbril(ofertaDemanda.getCau_amb_met3_ah_abr());
      caudalAnoHumeno.setCaudalAmbientalMayo(ofertaDemanda.getCau_amb_met3_ah_may());
      caudalAnoHumeno.setCaudalAmbientalJunio(ofertaDemanda.getCau_amb_met3_ah_jun());
      caudalAnoHumeno.setCaudalAmbientalJulio(ofertaDemanda.getCau_amb_met3_ah_jul());
      caudalAnoHumeno.setCaudalAmbientalAgosto(ofertaDemanda.getCau_amb_met3_ah_ago());
      caudalAnoHumeno.setCaudalAmbientalSeptiembre(ofertaDemanda.getCau_amb_met3_ah_sep());
      caudalAnoHumeno.setCaudalAmbientalOctubre(ofertaDemanda.getCau_amb_met3_ah_oct());
      caudalAnoHumeno.setCaudalAmbientalNoviembre(ofertaDemanda.getCau_amb_met3_ah_nov());
      caudalAnoHumeno.setCaudalAmbientalDiciembre(ofertaDemanda.getCau_amb_met3_ah_dic());
      //carga caudal ambiental
      this.listaCaudalAmbientalMet3 = new ArrayList();
      this.listaCaudalAmbientalMet3.add(caudalAnoSeco);
      this.listaCaudalAmbientalMet3.add(caudalAnoMedio);
      this.listaCaudalAmbientalMet3.add(caudalAnoHumeno);

    } catch (IdeamException e) {
      showMessage(e);
    }
 
    }
    
  public void valueChangeListenerMetodologia(ValueChangeEvent event)
          throws IdeamException{
    event.getComponent().processUpdates(FacesContext.getCurrentInstance());
    if (ofertaDemanda.getCau_amb_met().getCodigo().toString().equals(metodologia1Id.toString())) {
      renderMetodologia1 = true;
      renderMetodologia2 = false;
      renderMetodologia3 = false;
      panelFormLayoutMetodologia1.setRendered(renderMetodologia1);
    } else if (ofertaDemanda.getCau_amb_met().getCodigo().toString().equals(metodologia2Id.toString())) {
      renderMetodologia1 = false;
      renderMetodologia2 = true;
      renderMetodologia3 = false;
      panelFormLayoutMetodologia2.setRendered(renderMetodologia2);
    } else if (ofertaDemanda.getCau_amb_met().getCodigo().toString().equals(metodologia3Id.toString())) {
      renderMetodologia1 = false;
      renderMetodologia2 = false;
      renderMetodologia3 = true;
      panelFormLayoutMetodologia3.setRendered(renderMetodologia3);
    }
    AdfFacesContext.getCurrentInstance().addPartialTarget(panelFormLayoutMetodologia1);
    AdfFacesContext.getCurrentInstance().addPartialTarget(panelFormLayoutMetodologia2);
    AdfFacesContext.getCurrentInstance().addPartialTarget(panelFormLayoutMetodologia3);
        
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

    public void setPsl_total(RichPanelStretchLayout psl1) {
        this.psl_total = psl1;
    }

    public RichPanelStretchLayout getPsl_total() {
        return psl_total;
    }

    public void setPfl1(RichPanelFormLayout pfl1) {
        this.pfl1 = pfl1;
    }

    public RichPanelFormLayout getPfl1() {
        return pfl1;
    }

    public void setIt1(RichInputText it1) {
        this.it1 = it1;
    }

    public RichInputText getIt1() {
        return it1;
    }

    public void setIt2(RichInputText it2) {
        this.it2 = it2;
    }

    public RichInputText getIt2() {
        return it2;
    }

    public void setIt_oferta_disponible(RichInputText it3) {
        this.it_oferta_disponible = it3;
    }

    public RichInputText getIt_oferta_disponible() {
        return it_oferta_disponible;
    }

    public void setIt4(RichInputText it4) {
        this.it4 = it4;
    }

    public RichInputText getIt4() {
        return it4;
    }

    public void setIt5(RichInputText it5) {
        this.it5 = it5;
    }

    public RichInputText getIt5() {
        return it5;
    }

    public void setIt6(RichInputText it6) {
        this.it6 = it6;
    }

    public RichInputText getIt6() {
        return it6;
    }

    public void setIt7(RichInputText it7) {
        this.it7 = it7;
    }

    public RichInputText getIt7() {
        return it7;
    }

    public void setIt8(RichInputText it8) {
        this.it8 = it8;
    }

    public RichInputText getIt8() {
        return it8;
    }


    public void setPgl2(RichPanelGroupLayout pgl2) {
        this.pgl2 = pgl2;
    }

    public RichPanelGroupLayout getPgl2() {
        return pgl2;
    }


    public List getListaCaudal() {
        return listaCaudal;
    }

    public void setListaCaudal(List listaCaudal) {
        this.listaCaudal = listaCaudal;
    }

    public void setT_caudal(RichTable t1) {
        this.t_caudal = t1;
    }

    public RichTable getT_caudal() {
        return t_caudal;
    }


    public void setIt9(RichInputText it9) {
        this.it9 = it9;
    }

    public RichInputText getIt9() {
        return it9;
    }

    public void setIt10(RichInputText it10) {
        this.it10 = it10;
    }

    public RichInputText getIt10() {
        return it10;
    }


    public List getListaCaptacionesVertimientos() {
        return listaCaptacionesVertimientos;
    }

    public void setListaCaptacionesVertimientos(List listaCaptacionesVertimientos) {
        this.listaCaptacionesVertimientos = listaCaptacionesVertimientos;
    }

    public void setT_capvert(RichTable t2) {
        this.t_capvert = t2;
    }

    public RichTable getT_capvert() {
        return t_capvert;
    }


    public void setIt11(RichInputText it11) {
        this.it11 = it11;
    }

    public RichInputText getIt11() {
        return it11;
    }

    public void setIt12(RichInputText it12) {
        this.it12 = it12;
    }

    public RichInputText getIt12() {
        return it12;
    }


    public void setPb1(RichPanelBox pb1) {
        this.pb1 = pb1;
    }

    public RichPanelBox getPb1() {
        return pb1;
    }

    public void setS2(RichSpacer s2) {
        this.s2 = s2;
    }

    public RichSpacer getS2() {
        return s2;
    }

    public void setOt1(RichOutputText ot1) {
        this.ot1 = ot1;
    }

    public RichOutputText getOt1() {
        return ot1;
    }

    public void setOt4(RichOutputText ot4) {
        this.ot4 = ot4;
    }

    public RichOutputText getOt4() {
        return ot4;
    }

    public void setS3(RichSpacer s3) {
        this.s3 = s3;
    }

    public RichSpacer getS3() {
        return s3;
    }

    public void setOt5(RichOutputText ot5) {
        this.ot5 = ot5;
    }

    public RichOutputText getOt5() {
        return ot5;
    }

    public void setS4(RichSpacer s4) {
        this.s4 = s4;
    }

    public RichSpacer getS4() {
        return s4;
    }

    public void setOt6(RichOutputText ot6) {
        this.ot6 = ot6;
    }

    public RichOutputText getOt6() {
        return ot6;
    }

    public void setS5(RichSpacer s5) {
        this.s5 = s5;
    }

    public RichSpacer getS5() {
        return s5;
    }

    public void setOt7(RichOutputText ot7) {
        this.ot7 = ot7;
    }

    public RichOutputText getOt7() {
        return ot7;
    }

    public void setS6(RichSpacer s6) {
        this.s6 = s6;
    }

    public RichSpacer getS6() {
        return s6;
    }

    public void setOt8(RichOutputText ot8) {
        this.ot8 = ot8;
    }

    public RichOutputText getOt8() {
        return ot8;
    }

    public void setIt_oferta_anio_medio(RichInputText it14) {
        this.it_oferta_anio_medio = it14;
    }

    public RichInputText getIt_oferta_anio_medio() {
        return it_oferta_anio_medio;
    }

    public void setIt15(RichInputText it15) {
        this.it15 = it15;
    }

    public RichInputText getIt15() {
        return it15;
    }

    public void setPc1(RichPanelCollection pc1) {
        this.pc1 = pc1;
    }

    public RichPanelCollection getPc1() {
        return pc1;
    }

    public void setPgl1(RichPanelGroupLayout pgl1) {
        this.pgl1 = pgl1;
    }

    public RichPanelGroupLayout getPgl1() {
        return pgl1;
    }

    public void setCb_aceptar(RichCommandButton cb1) {
        this.cb_aceptar = cb1;
    }

    public RichCommandButton getCb_aceptar() {
        return cb_aceptar;
    }

    public void setS1(RichSpacer s1) {
        this.s1 = s1;
    }

    public RichSpacer getS1() {
        return s1;
    }

    public void setCb_regresar(RichCommandButton cb2) {
        this.cb_regresar = cb2;
    }

    public RichCommandButton getCb_regresar() {
        return cb_regresar;
    }

    public void setPgl3(RichPanelGroupLayout pgl3) {
        this.pgl3 = pgl3;
    }

    public RichPanelGroupLayout getPgl3() {
        return pgl3;
    }

    public void setS7(RichSpacer s7) {
        this.s7 = s7;
    }

    public RichSpacer getS7() {
        return s7;
    }

    public void setCl1(RichCommandLink cl1) {
        this.cl1 = cl1;
    }

    public RichCommandLink getCl1() {
        return cl1;
    }

    public void setS8(RichSpacer s8) {
        this.s8 = s8;
    }

    public RichSpacer getS8() {
        return s8;
    }

    public void setCl2(RichCommandLink cl2) {
        this.cl2 = cl2;
    }

    public RichCommandLink getCl2() {
        return cl2;
    }

    public void setS9(RichSpacer s9) {
        this.s9 = s9;
    }

    public RichSpacer getS9() {
        return s9;
    }


    public void setS10(RichSpacer s10) {
        this.s10 = s10;
    }

    public RichSpacer getS10() {
        return s10;
    }

    public PorhOfertaDemanda getOfertaDemanda() {
        return ofertaDemanda;
    }

    public void setOfertaDemanda(PorhOfertaDemanda ofertaDemanda) {
        this.ofertaDemanda = ofertaDemanda;
    }

    public void cb_aceptar_actionListener(ActionEvent actionEvent) {
      Boolean errores = false;
        try{
          
          if ((this.it4.getValue() == null ||
              this.it4.getValue().toString().length() == 0) && renderDemanda) {
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                        this.it4);
            errores = true;
          }
          if ((this.it5.getValue() == null ||
              this.it5.getValue().toString().length() == 0) && renderDemanda) {
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                        this.it5);
            errores = true;
          }
          if ((this.indiceCalidadAgua.getValue() == null ||
              this.indiceCalidadAgua.getValue().toString().length() == 0) && renderIndices) {
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                        this.indiceCalidadAgua);
            errores = true;
          }
          if ((this.indiceRegulacionHidrica.getValue() == null ||
              this.indiceRegulacionHidrica.getValue().toString().length() == 0) && renderIndices) {
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                        this.indiceRegulacionHidrica);
            errores = true;
          }
          if ((this.indiceValorImportacionEcologicaEspecie.getValue() == null ||
              this.indiceValorImportacionEcologicaEspecie.getValue().toString().length() == 0) && renderIndices) {
            showMessage("obligatorio", FacesMessage.SEVERITY_ERROR,
                        this.indiceValorImportacionEcologicaEspecie);
            errores = true;
          }
          
          if (ofertaDemanda.getCau_amb_met2_ano_seco() == null && renderMetodologia2 && renderOferta) {
            showMessage("El campo Caudal ambiental (m3/s) año seco es obligatorio", FacesMessage.SEVERITY_ERROR);
            errores = true;
          }
          if (ofertaDemanda.getCau_amb_met2_ano_hum() == null && renderMetodologia2 && renderOferta) {
            showMessage("El campo Caudal ambiental (m3/s) año humedo es obligatorio", FacesMessage.SEVERITY_ERROR);
            errores = true;
          }
          if (ofertaDemanda.getCau_amb_met2_ano_nor() == null && renderMetodologia2 && renderOferta) {
            showMessage("El campo Caudal ambiental (m3/s) año normal es obligatorio", FacesMessage.SEVERITY_ERROR);
            errores = true;
          }
          

          if(!errores){
            CaudalMedioTO caudalMedio = (CaudalMedioTO)this.listaCaudal.get(0);
            this.ofertaDemanda.setCaudal_mmm_enero( caudalMedio.getEnero() );
            this.ofertaDemanda.setCaudal_mmm_febrero( caudalMedio.getFebrero() );
            this.ofertaDemanda.setCaudal_mmm_marzo( caudalMedio.getMarzo() );
            this.ofertaDemanda.setCaudal_mmm_abril( caudalMedio.getAbril() );
            this.ofertaDemanda.setCaudal_mmm_mayo( caudalMedio.getMayo() );
            this.ofertaDemanda.setCaudal_mmm_junio( caudalMedio.getJunio() );
            this.ofertaDemanda.setCaudal_mmm_julio( caudalMedio.getJulio() );
            this.ofertaDemanda.setCaudal_mmm_agosto( caudalMedio.getAgosto() );
            this.ofertaDemanda.setCaudal_mmm_sep( caudalMedio.getSeptiembre() );
            this.ofertaDemanda.setCaudal_mmm_oct( caudalMedio.getOctubre() );
            this.ofertaDemanda.setCaudal_mmm_nov( caudalMedio.getNoviembre() );
            this.ofertaDemanda.setCaudal_mmm_dic( caudalMedio.getDiciembre() );
            
            /*CaudalMedioTO caudalAmbiental = (CaudalMedioTO)this.listaCaudal.get(1);
            this.ofertaDemanda.setCaudal_mamm_enero( caudalAmbiental.getEnero() );
            this.ofertaDemanda.setCaudal_mamm_febrero( caudalAmbiental.getFebrero() );
            this.ofertaDemanda.setCaudal_mamm_marzo( caudalAmbiental.getMarzo() );
            this.ofertaDemanda.setCaudal_mamm_abril( caudalAmbiental.getAbril() );
            this.ofertaDemanda.setCaudal_mamm_mayo( caudalAmbiental.getMayo() );
            this.ofertaDemanda.setCaudal_mamm_junio( caudalAmbiental.getJunio() );
            this.ofertaDemanda.setCaudal_mamm_julio( caudalAmbiental.getJulio() );
            this.ofertaDemanda.setCaudal_mamm_agosto( caudalAmbiental.getAgosto() );
            this.ofertaDemanda.setCaudal_mamm_sep( caudalAmbiental.getSeptiembre() );
            this.ofertaDemanda.setCaudal_mamm_oct( caudalAmbiental.getOctubre() );
            this.ofertaDemanda.setCaudal_mamm_nov( caudalAmbiental.getNoviembre() );
            this.ofertaDemanda.setCaudal_mamm_dic( caudalAmbiental.getDiciembre() );*/
          
          
            CaudalAmbientalDTO caudalAnoSeco = (CaudalAmbientalDTO)this.listaCaudalAmbientalMet3.get(0);
            this.ofertaDemanda.setCau_amb_met3_as_ene(caudalAnoSeco.getCaudalAmbientalEnero());
            this.ofertaDemanda.setCau_amb_met3_as_feb(caudalAnoSeco.getCaudalAmbientalFebrero());
            this.ofertaDemanda.setCau_amb_met3_as_mar(caudalAnoSeco.getCaudalAmbientalMarzo());
            this.ofertaDemanda.setCau_amb_met3_as_abr(caudalAnoSeco.getCaudalAmbientalAbril());
            this.ofertaDemanda.setCau_amb_met3_as_may(caudalAnoSeco.getCaudalAmbientalMayo());
            this.ofertaDemanda.setCau_amb_met3_as_jun(caudalAnoSeco.getCaudalAmbientalJunio());
            this.ofertaDemanda.setCau_amb_met3_as_jul(caudalAnoSeco.getCaudalAmbientalJulio());
            this.ofertaDemanda.setCau_amb_met3_as_ago(caudalAnoSeco.getCaudalAmbientalAgosto());
            this.ofertaDemanda.setCau_amb_met3_as_sep(caudalAnoSeco.getCaudalAmbientalSeptiembre());
            this.ofertaDemanda.setCau_amb_met3_as_oct(caudalAnoSeco.getCaudalAmbientalOctubre());
            this.ofertaDemanda.setCau_amb_met3_as_nov(caudalAnoSeco.getCaudalAmbientalNoviembre());
            this.ofertaDemanda.setCau_amb_met3_as_dic(caudalAnoSeco.getCaudalAmbientalDiciembre());
            
            CaudalAmbientalDTO caudalAnoMedio = (CaudalAmbientalDTO)this.listaCaudalAmbientalMet3.get(1);
            this.ofertaDemanda.setCau_amb_met3_am_ene(caudalAnoMedio.getCaudalAmbientalEnero());
            this.ofertaDemanda.setCau_amb_met3_am_feb(caudalAnoMedio.getCaudalAmbientalFebrero());
            this.ofertaDemanda.setCau_amb_met3_am_mar(caudalAnoMedio.getCaudalAmbientalMarzo());
            this.ofertaDemanda.setCau_amb_met3_am_abr(caudalAnoMedio.getCaudalAmbientalAbril());
            this.ofertaDemanda.setCau_amb_met3_am_may(caudalAnoMedio.getCaudalAmbientalMayo());
            this.ofertaDemanda.setCau_amb_met3_am_jun(caudalAnoMedio.getCaudalAmbientalJunio());
            this.ofertaDemanda.setCau_amb_met3_am_jul(caudalAnoMedio.getCaudalAmbientalJulio());
            this.ofertaDemanda.setCau_amb_met3_am_ago(caudalAnoMedio.getCaudalAmbientalAgosto());
            this.ofertaDemanda.setCau_amb_met3_am_sep(caudalAnoMedio.getCaudalAmbientalSeptiembre());
            this.ofertaDemanda.setCau_amb_met3_am_oct(caudalAnoMedio.getCaudalAmbientalOctubre());
            this.ofertaDemanda.setCau_amb_met3_am_nov(caudalAnoMedio.getCaudalAmbientalNoviembre());
            this.ofertaDemanda.setCau_amb_met3_am_dic( caudalAnoMedio.getCaudalAmbientalDiciembre());
            
            
            CaudalAmbientalDTO caudalAnoHumeno = (CaudalAmbientalDTO)this.listaCaudalAmbientalMet3.get(2);
            this.ofertaDemanda.setCau_amb_met3_ah_ene(caudalAnoHumeno.getCaudalAmbientalEnero());
            this.ofertaDemanda.setCau_amb_met3_ah_feb(caudalAnoHumeno.getCaudalAmbientalFebrero());
            this.ofertaDemanda.setCau_amb_met3_ah_mar(caudalAnoHumeno.getCaudalAmbientalMarzo());
            this.ofertaDemanda.setCau_amb_met3_ah_abr(caudalAnoHumeno.getCaudalAmbientalAbril());
            this.ofertaDemanda.setCau_amb_met3_ah_may(caudalAnoHumeno.getCaudalAmbientalMayo());
            this.ofertaDemanda.setCau_amb_met3_ah_jun(caudalAnoHumeno.getCaudalAmbientalJunio());
            this.ofertaDemanda.setCau_amb_met3_ah_jul(caudalAnoHumeno.getCaudalAmbientalJulio());
            this.ofertaDemanda.setCau_amb_met3_ah_ago(caudalAnoHumeno.getCaudalAmbientalAgosto());
            this.ofertaDemanda.setCau_amb_met3_ah_sep(caudalAnoHumeno.getCaudalAmbientalSeptiembre());
            this.ofertaDemanda.setCau_amb_met3_ah_oct(caudalAnoHumeno.getCaudalAmbientalOctubre());
            this.ofertaDemanda.setCau_amb_met3_ah_nov(caudalAnoHumeno.getCaudalAmbientalNoviembre());
            this.ofertaDemanda.setCau_amb_met3_ah_dic( caudalAnoHumeno.getCaudalAmbientalDiciembre());
          
            TotalCapVertTO captaciones = 
                (TotalCapVertTO)this.listaCaptacionesVertimientos.get(0);
            this.ofertaDemanda.setNum_captacion_leg( captaciones.getLegales() );
            this.ofertaDemanda.setNum_captacion_ileg( captaciones.getIlegales() );
            this.ofertaDemanda.setDemanda_estimada_capt_ile(captaciones.getDemandaEstimadaIlegales());
            this.ofertaDemanda.setDemanda_estimada_capt_leg(captaciones.getDemandaEstimadaLegales());
            
            TotalCapVertTO vertimiento = 
                (TotalCapVertTO)this.listaCaptacionesVertimientos.get(1);
            this.ofertaDemanda.setNum_vertimientos_leg( vertimiento.getLegales() );
            this.ofertaDemanda.setNum_vertimientos_ileg( vertimiento.getIlegales() );
            this.ofertaDemanda.setDemanda_estimada_vert_ile(vertimiento.getDemandaEstimadaIlegales());
            this.ofertaDemanda.setDemanda_estimada_vert_leg(vertimiento.getDemandaEstimadaLegales());
            
            if(this.ofertaDemanda.getPorhId()==null ||
                this.ofertaDemanda.getPorhId().longValue()==0){
                this.ofertaDemanda.setPorhId( this.planOrdenamiento.getCodigo() );
            }
            
            PorhDelegate pd = PorhDelegate.getInstance();                        
            pd.updateOfertaDemanda(this.ofertaDemanda);
            AdfFacesContext.getCurrentInstance().addPartialTarget( psl_total );
            String params[] = {"del Plan de Ordenamiento"};
            showMessage(getText("mensaje_registro_exitoso",
                                params), FacesMessage.SEVERITY_INFO);
          }
        }catch(IdeamException e){
            showMessage(e);
        }
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
    
    private Double getOfertaDisponibleCalculada(){
        CaudalMedioTO caudalAmbiental = (CaudalMedioTO)this.listaCaudal.get(1);
        if(this.ofertaDemanda.getOferta_tot_anno_medio()!= null &&
           caudalAmbiental != null &&
           caudalAmbiental.getPromedio() !=null){
           return this.ofertaDemanda.getOferta_tot_anno_medio().doubleValue() -
               caudalAmbiental.getPromedio().doubleValue();
        }else{
            return null;
        }
    }

    public Double getDemandaCalculada() {
        return demandaCalculada;
    }

    public void setDemandaCalculada(Double demandaCalculada) {
        this.demandaCalculada = demandaCalculada;
    }

    public void it_oferta_anio_medio_valueChangeListener(ValueChangeEvent valueChangeEvent) {
        /*valueChangeEvent.getComponent().processUpdates(FacesContext.getCurrentInstance());
        valorOfertaDisponibleCalculada = "Indeterminada";
        
        CaudalMedioTO caudalAmbiental = (CaudalMedioTO)this.listaCaudal.get(1);
        
        if(caudalAmbiental != null &&
           caudalAmbiental.getPromedio() !=null &&
           caudalAmbiental.getPromedio() >0 ){
        
            NumberFormat formatter = new DecimalFormat("#.###");
            valorOfertaDisponibleCalculada = formatter.format(
                this.ofertaDemanda.getOferta_tot_anno_medio().doubleValue() -
                caudalAmbiental.getPromedio().doubleValue());
          caudalAmbiental.setPromedio(Double.valueOf(valorOfertaDisponibleCalculada));
        }
        
        AdfFacesContext.getCurrentInstance().addPartialTarget(it_oferta_disponible);
*/
        //AdfFacesContext.getCurrentInstance().addPartialTarget(getCaudalTotal());
    }
    
  public void valueChangeCaudalAmbiental(ValueChangeEvent valueChangeEvent) {
     
  }

    public RichInputText getCaudalTotal() {
        return caudalTotal;
    }

    public void setCaudalTotal(RichInputText caudalTotal) {
        this.caudalTotal = caudalTotal;
    }

    public PorhPlanes getPlanOrdenamiento() {
        return planOrdenamiento;
    }

    public void setPlanOrdenamiento(PorhPlanes planOrdenamiento) {
        this.planOrdenamiento = planOrdenamiento;
    }

    public String getValorOfertaDisponibleCalculada() {
        return valorOfertaDisponibleCalculada;
    }

    public void setValorOfertaDisponibleCalculada(String valorOfertaDisponibleCalculada) {
        this.valorOfertaDisponibleCalculada = valorOfertaDisponibleCalculada;
    }

  public static String getCaudalMedioMensual() {
    return caudalMedioMensual;
  }

  public static String getCaudalAmbienalMedio() {
    return caudalAmbienalMedio;
  }

  public static String getNumeroCaptaciones() {
    return numeroCaptaciones;
  }

  public static String getNumeroVertimientos() {
    return numeroVertimientos;
  }

  public void setIndiceVulnerabilidadHidrica(RichInputText indiceVulnerabilidadHidrica) {
    this.indiceVulnerabilidadHidrica = indiceVulnerabilidadHidrica;
  }

  public RichInputText getIndiceVulnerabilidadHidrica() {
    return indiceVulnerabilidadHidrica;
  }

  public void setIndiceCalidadAgua(RichInputText indiceCalidadAgua) {
    this.indiceCalidadAgua = indiceCalidadAgua;
  }

  public RichInputText getIndiceCalidadAgua() {
    return indiceCalidadAgua;
  }

  public void setMonitoreoBiologicoGrupoTrabajo(RichInputText monitoreoBiologicoGrupoTrabajo) {
    this.monitoreoBiologicoGrupoTrabajo = monitoreoBiologicoGrupoTrabajo;
  }

  public RichInputText getMonitoreoBiologicoGrupoTrabajo() {
    return monitoreoBiologicoGrupoTrabajo;
  }

  public void setIndiceUsoAgua(RichInputText indiceUsoAgua) {
    this.indiceUsoAgua = indiceUsoAgua;
  }

  public RichInputText getIndiceUsoAgua() {
    return indiceUsoAgua;
  }

  public void setIndiceValorImportacionEcologicaEspecie(RichInputText indiceValorImportacionEcologicaEspecie) {
    this.indiceValorImportacionEcologicaEspecie = indiceValorImportacionEcologicaEspecie;
  }

  public RichInputText getIndiceValorImportacionEcologicaEspecie() {
    return indiceValorImportacionEcologicaEspecie;
  }

  public void setIndiceCalidadEcologica(RichInputText indiceCalidadEcologica) {
    this.indiceCalidadEcologica = indiceCalidadEcologica;
  }

  public RichInputText getIndiceCalidadEcologica() {
    return indiceCalidadEcologica;
  }

  public void setIndiceRegulacionHidrica(RichInputText indiceRegulacionHidrica) {
    this.indiceRegulacionHidrica = indiceRegulacionHidrica;
  }

  public RichInputText getIndiceRegulacionHidrica() {
    return indiceRegulacionHidrica;
  }

  public void setPanelFormLayoutOfertaDemanda(RichPanelFormLayout panelFormLayoutOfertaDemanda) {
    this.panelFormLayoutOfertaDemanda = panelFormLayoutOfertaDemanda;
  }

  public RichPanelFormLayout getPanelFormLayoutOfertaDemanda() {
    return panelFormLayoutOfertaDemanda;
  }

  public void setInputTextmetodo_empleado(RichInputText inputTextmetodo_empleado) {
    this.inputTextmetodo_empleado = inputTextmetodo_empleado;
  }

  public RichInputText getInputTextmetodo_empleado() {
    return inputTextmetodo_empleado;
  }

  public void setRenderOferta(Boolean renderOferta) {
    this.renderOferta = renderOferta;
  }

  public Boolean getRenderOferta() {
    return renderOferta;
  }

  public void setRenderDemanda(Boolean renderDemanda) {
    this.renderDemanda = renderDemanda;
  }

  public Boolean getRenderDemanda() {
    return renderDemanda;
  }

  public void setRenderIndices(Boolean renderIndices) {
    this.renderIndices = renderIndices;
  }

  public Boolean getRenderIndices() {
    return renderIndices;
  }

  public void setInputTextproyecciones_demanda_corto(RichInputText inputTextproyecciones_demanda_corto) {
    this.inputTextproyecciones_demanda_corto = inputTextproyecciones_demanda_corto;
  }

  public RichInputText getInputTextproyecciones_demanda_corto() {
    return inputTextproyecciones_demanda_corto;
  }

  public void setInputTextproyecciones_demanda_mediano(RichInputText inputTextproyecciones_demanda_mediano) {
    this.inputTextproyecciones_demanda_mediano = inputTextproyecciones_demanda_mediano;
  }

  public RichInputText getInputTextproyecciones_demanda_mediano() {
    return inputTextproyecciones_demanda_mediano;
  }

  public void setInputTextproyecciones_demanda_largo(RichInputText inputTextproyecciones_demanda_largo) {
    this.inputTextproyecciones_demanda_largo = inputTextproyecciones_demanda_largo;
  }

  public RichInputText getInputTextproyecciones_demanda_largo() {
    return inputTextproyecciones_demanda_largo;
  }

  public void setListaCaudalAmbientalMet3(List listaCaudalAmbientalMet3) {
    this.listaCaudalAmbientalMet3 = listaCaudalAmbientalMet3;
  }

  public List getListaCaudalAmbientalMet3() {
    return listaCaudalAmbientalMet3;
  }

  public void setCaudalAmbientalTotal(RichInputText caudalAmbientalTotal) {
    this.caudalAmbientalTotal = caudalAmbientalTotal;
  }

  public RichInputText getCaudalAmbientalTotal() {
    return caudalAmbientalTotal;
  }

  public static String getCaudalAmbientalSeco() {
    return caudalAmbientalSeco;
  }

  public static String getCaudalAmbientalMedio() {
    return caudalAmbientalMedio;
  }

  public static String getCaudalAmbientalHumedo() {
    return caudalAmbientalHumedo;
  }

  public void setOutputText_caudal_ambiental(RichOutputText outputText_caudal_ambiental) {
    this.outputText_caudal_ambiental = outputText_caudal_ambiental;
  }

  public RichOutputText getOutputText_caudal_ambiental() {
    return outputText_caudal_ambiental;
  }

  public void setRichTableCaudalAmbiental(RichTable richTableCaudalAmbiental) {
    this.richTableCaudalAmbiental = richTableCaudalAmbiental;
  }

  public RichTable getRichTableCaudalAmbiental() {
    return richTableCaudalAmbiental;
  }

  public void setSelectOneChoiceMetodologia(RichSelectOneChoice selectOneChoiceMetodologia) {
    this.selectOneChoiceMetodologia = selectOneChoiceMetodologia;
  }

  public RichSelectOneChoice getSelectOneChoiceMetodologia() {
    return selectOneChoiceMetodologia;
  }

  public void setSelectItemsMetodologia(UISelectItems selectItemsMetodologia) {
    this.selectItemsMetodologia = selectItemsMetodologia;
  }

  public UISelectItems getSelectItemsMetodologia() {
    return selectItemsMetodologia;
  }

  public void setMetodologiasCaudalAmbiental(List metodologiasCaudalAmbiental) {
    this.metodologiasCaudalAmbiental = metodologiasCaudalAmbiental;
  }

  public List getMetodologiasCaudalAmbiental() {
    return metodologiasCaudalAmbiental;
  }

  public static Integer getMetodologia1Id() {
    return metodologia1Id;
  }

  public static Integer getMetodologia2Id() {
    return metodologia2Id;
  }

  public static Integer getMetodologia3Id() {
    return metodologia3Id;
  }

  public void setRenderMetodologia1(Boolean renderMetodologia1) {
    this.renderMetodologia1 = renderMetodologia1;
  }

  public Boolean getRenderMetodologia1() {
    return renderMetodologia1;
  }

  public void setRenderMetodologia2(Boolean renderMetodologia2) {
    this.renderMetodologia2 = renderMetodologia2;
  }

  public Boolean getRenderMetodologia2() {
    return renderMetodologia2;
  }

  public void setRenderMetodologia3(Boolean renderMetodologia3) {
    this.renderMetodologia3 = renderMetodologia3;
  }

  public Boolean getRenderMetodologia3() {
    return renderMetodologia3;
  }

  public void setPanelFormLayoutMetodologia1(RichPanelFormLayout panelFormLayoutMetodologia1) {
    this.panelFormLayoutMetodologia1 = panelFormLayoutMetodologia1;
  }

  public RichPanelFormLayout getPanelFormLayoutMetodologia1() {
    return panelFormLayoutMetodologia1;
  }

  public void setPanelFormLayoutMetodologia2(RichPanelFormLayout panelFormLayoutMetodologia2) {
    this.panelFormLayoutMetodologia2 = panelFormLayoutMetodologia2;
  }

  public RichPanelFormLayout getPanelFormLayoutMetodologia2() {
    return panelFormLayoutMetodologia2;
  }

  public void setPanelFormLayoutMetodologia3(RichPanelFormLayout panelFormLayoutMetodologia3) {
    this.panelFormLayoutMetodologia3 = panelFormLayoutMetodologia3;
  }

  public RichPanelFormLayout getPanelFormLayoutMetodologia3() {
    return panelFormLayoutMetodologia3;
  }

}
