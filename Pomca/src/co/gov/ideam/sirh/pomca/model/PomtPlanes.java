package co.gov.ideam.sirh.pomca.model;


import java.io.Serializable;

import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.id.SequenceGenerator;


@Entity
@NamedQueries( { @NamedQuery(name = "PomtPlanes.findAll",
                             query = "select o from PomtPlanes o")
        ,
        @NamedQuery(name = "PomtPlanes.findByCuenca", query = "select o from PomtPlanes o where o.id_cuenca = :id_cuenca")
        } )
@Table(name = "pomt_planes")
//@SequenceGenerator(name = "project_id_seq_pomt_planes", sequenceName = "seq_pomt_planes")
public class PomtPlanes implements Serializable {

    @GenericGenerator(name = "pomca_generator",
                      strategy = "co.gov.ideam.sirh.usuarios.agua.model.IdeamSirhGenerator",
                      parameters =
                      { @Parameter(name = "AutoridadAmbientalId", value = "codigoAutoridad")
                      , @Parameter(name = "Codigo", value = "id")
                      , @Parameter(name = SequenceGenerator.SEQUENCE, value = "seq_pomt_planes")
                      } )
   // @GeneratedValue(generator = "pomca_generator")
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO, generator = "project_id_seq_pomt_planes")
    @Column(name = "id")
    private Long id;
    @Column(name = "id_archivo")
    private Long id_archivo;
    @Column(name = "id_cuenca")
    private Long id_cuenca;
    @Column(name = "id_subzona")
    private Integer id_subzona;
    @Column(name = "num_res_aprobacion")
    private String num_res_aprobacion;
    @Column(name = "num_res_declaratoria")
    private String num_res_declaratoria;
    @Column(name = "observaciones_consejo")
    private String observaciones_consejo;
    @Column(name = "acta_conf_consejo")
    private String acta_conf_consejo;
    @Column(name = "cod_cuenca")
    private String cod_cuenca;
    @Column(name = "fecha_acta_consejo")
    private Timestamp fecha_acta_consejo;
    @Column(name = "fecha_aprobacion")
    private Timestamp fecha_aprobacion;
    @Column(name = "fecha_declaratoria")
    private Timestamp fecha_declaratoria;

    @Column(name = "mapa_resultante_zonif")
    private Long mapa_resultante_zonif;
    @Column(name = "info_proceso_zonif")
    private Long info_proceso_zonif;
    @Column(name = "fecha_info_zonif")
    private Timestamp fecha_info_zonif;


    @Column(name = "comp_socio_economico")
    private String compSocioEconomico;
    @Column(name = "comp_economico")
    private String compEconomico;
    @Column(name = "comp_biofisico")
    private String compBiofisico;
    @Column(name = "comp_amenaza_riesgo")
    private String compAmenazaRiesgo;
    @Column(name = "comp_conflictos_soci_amb")
    private String compConflictosSociAmb;
    
    
    
    @Column(name = "com_hid_oferta_total")
    private Double comHidOfertaTotal;
    @Column(name = "com_hid_caud_amb_medio")
    private Double comHidCaudAmbMedio;
    @Column(name = "com_hid_oferta_disponible")
    private Double comHidOfertaDisponible;
    @Column(name = "com_hid_demanda_potencial")
    private Double comHidDemandaPotencial;
    @Column(name = "com_hid_oferta_neta")
    private Double comHidOfertaNeta;
    @Column(name = "com_hid_rendim_hidric")
    private Double comHidRendimHidric;
    @Column(name = "com_hid_aridez_indice")
    private Double comHidAridezIndice;
    @Column(name = "com_hid_aridez_observ")
    private String comHidAridezObserv;
    @Column(name = "com_hid_uso_agua_indice")
    private Double comHidUsoAguaIndice;
    @Column(name = "com_hid_uso_agua_obsv")
    private String comHidUsoAguaObsv;
   
    
    @Column(name = "com_hid_indice_ret_reg_hid")
    private Double comHidIndiceRetRegHid;
    @Column(name = "com_hid_indice_vulne_desab")
    private Double comHidIndiceVulneDesab;
    @Column(name = "com_hid_indice_calidad_agua")
    private Double comHidIndiceCalidadAgua;
    @Column(name = "com_hid_iacal")
    private Double comHidIacal;

    @Column(name = "fase_elab_aprestam")
    private String faseElabAprestam;
    @Column(name = "fase_elab_diagnost")
    private String faseElabDiagnost;
    @Column(name = "fase_elab_zonif")
    private String faseElabZonif;
    @Column(name = "fase_elab_formulac")
    private String faseElabFormulac;
    @Column(name = "fase_elab_ejecu")
    private String faseElabEjecu;
    @Column(name = "fase_elab_seg")
    private String faseElabSeg;

    @Column(name = "zn_area_prio_consrv_num")
    private Double znAreaPrioConsrvNum;
    @Column(name = "zn_area_prio_preser_num")
    private Double znAreaPrioPreserNum;
    @Column(name = "zn_area_proteg_num")
    private Double znAreaProtegNum;
    @Column(name = "zn_area_recup_num")
    private Double znAreaRecupNum;
    @Column(name = "zn_area_rehab_num")
    private Double znAreaRehabNum;
    @Column(name = "zn_area_restau_num")
    private Double znAreaRestauNum;
    @Column(name = "zn_area_manej_agri_num")
    private Double znAreaManejAgriNum;
    @Column(name = "zn_area_manej_fores_num")
    private Double znAreaManejForesNum;
    @Column(name = "zn_area_manej_pecu_num")
    private Double znAreaManejPecuNum;
    @Column(name = "zn_area_manej_miner_num")
    private Double znAreaManejMinerNum;
    @Column(name = "zn_estable_indus_num")
    private Double znEstableIndusNum;
    @Column(name = "zn_zonas_urbanas_num")
    private Double znZonasUrbanasNum;
    
    
    @Column(name = "zn_area_prio_consrv_prctj")
    private Double znAreaPrioConsrvPrctj;
    @Column(name = "zn_area_prio_preser_prctj")
    private Double znAreaPrioPreserPrctj;
    @Column(name = "zn_area_proteg_prctj")
    private Double znAreaProtegPrctj;
    @Column(name = "zn_area_recup_prctj")
    private Double znAreaRecupPrctj;
    @Column(name = "zn_area_rehab_prctj")
    private Double znAreaRehabPrctj;
    @Column(name = "zn_area_restau_prctj")
    private Double znAreaRestauPrctj;
    @Column(name = "zn_area_manej_agri_prctj")
    private Double znAreaManejAgriPrctj;
    @Column(name = "zn_area_manej_fores_prctj")
    private Double znAreaManejForesPrctj;
    @Column(name = "zn_area_manej_pecu_prctj")
    private Double znAreaManejPecuPrctj;
    @Column(name = "zn_area_manej_miner_prctj")
    private Double znAreaManejMinerPrctj;
    @Column(name = "zn_estable_indus_prctj")
    private Double znEstableIndusPrctj;
    @Column(name = "zn_zonas_urbanas_prctj")
    private Double znZonasUrbanasPrctj;
    
    
    @Column(name = "codigo_autoridad")
    private Long codigoAutoridad;

    @Column(name = "comi_acto_admin")
    private String comiActoAdmin;
    @Column(name = "comi_fecha_acto")
    private Timestamp comiFechaActo;


    //@OneToMany(mappedBy = "pomtPlanes")

    /**
     * De uno a muchos "un plan tiene varias comisiones" y se amarra con el "pomtPlanes" que es el nombre
     * de la variable del objeto  PomtComisiones que esta debajo del ManyToOne
     */
    @Transient
    private List<PomtComisiones> pomtComisionesList;

    //@OneToMany(mappedBy = "pomtPlanes")
    @Transient
    private List<PomtJurisdiccion> pomtJurisdiccionList;

    //@OneToMany(mappedBy = "pomtPlanes")
    @Transient
    private List<PomtInstrumentosPlanificacion> pomtInstrumentosPlanificacionList;

    //@OneToMany(mappedBy = "pomtPlanes")
    @Transient
    private List<PomtComunidadesEtnicas> pomtComunidadesEtnicasList;
 
   //ZSDG IN 
    @Transient
    private List<DeterminanteAmbiental> determinanteAmbientalList ;
      
  //ZSDG
    //@OneToMany(mappedBy = "pomtPlanes")
    //@Transient
    //private List<PomtArchivosXPlan> pomtArchivosXPlanList;

    public PomtPlanes() {
        this.setNum_res_declaratoria("");
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getActa_conf_consejo() {
        return acta_conf_consejo;
    }

    public void setActa_conf_consejo(String acta_conf_consejo) {
        this.acta_conf_consejo = acta_conf_consejo;
    }

    public String getCod_cuenca() {
        return cod_cuenca;
    }

    public void setCod_cuenca(String cod_cuenca) {
        this.cod_cuenca = cod_cuenca;
    }

    public Timestamp getFecha_acta_consejo() {
        return fecha_acta_consejo;
    }

    public void setFecha_acta_consejo(Timestamp fecha_acta_consejo) {
        this.fecha_acta_consejo = fecha_acta_consejo;
    }

    public Timestamp getFecha_aprobacion() {
        return fecha_aprobacion;
    }

    public void setFecha_aprobacion(Timestamp fecha_aprobacion) {
        this.fecha_aprobacion = fecha_aprobacion;
    }

    public Timestamp getFecha_declaratoria() {
        return fecha_declaratoria;
    }

    public void setFecha_declaratoria(Timestamp fecha_declaratoria) {
        this.fecha_declaratoria = fecha_declaratoria;
    }

    public Long getId_archivo() {
        return id_archivo;
    }

    public void setId_archivo(Long id_archivo) {
        this.id_archivo = id_archivo;
    }

    public Long getId_cuenca() {
        return id_cuenca;
    }

    public void setId_cuenca(Long id_cuenca) {
        this.id_cuenca = id_cuenca;
    }

    public Integer getId_subzona() {
        return id_subzona;
    }

    public void setId_subzona(Integer id_subzona) {
        this.id_subzona = id_subzona;
    }

    public String getNum_res_aprobacion() {
        return num_res_aprobacion;
    }

    public void setNum_res_aprobacion(String num_res_aprobacion) {
        this.num_res_aprobacion = num_res_aprobacion;
    }

    public String getNum_res_declaratoria() {
        return num_res_declaratoria;
    }

    public void setNum_res_declaratoria(String num_res_declaratoria) {
        this.num_res_declaratoria = num_res_declaratoria;
    }

    public String getObservaciones_consejo() {
        return observaciones_consejo;
    }

    public void setObservaciones_consejo(String observaciones_consejo) {
        this.observaciones_consejo = observaciones_consejo;
    }

    public List<PomtComisiones> getPomtComisionesList() {
        return pomtComisionesList;
    }

    public void setPomtComisionesList(List<PomtComisiones> pomtComisionesList) {
        this.pomtComisionesList = pomtComisionesList;
    }

    public PomtComisiones addPomtComisiones(PomtComisiones pomtComisiones) {
        getPomtComisionesList().add(pomtComisiones);
        pomtComisiones.setPomtPlanes(this);
        pomtComisiones.setCodigoAutoridad(this.getCodigoAutoridad());
        return pomtComisiones;
    }

    public PomtComisiones removePomtComisiones(PomtComisiones pomtComisiones) {
        getPomtComisionesList().remove(pomtComisiones);
        pomtComisiones.setPomtPlanes(null);
        return pomtComisiones;
    }

    public List<PomtJurisdiccion> getPomtJurisdiccionList() {
        return pomtJurisdiccionList;
    }

    public void setPomtJurisdiccionList(List<PomtJurisdiccion> pomtJurisdiccionList) {
        this.pomtJurisdiccionList = pomtJurisdiccionList;
    }

    public PomtJurisdiccion addPomtJurisdiccion(PomtJurisdiccion pomtJurisdiccion) {
        getPomtJurisdiccionList().add(pomtJurisdiccion);
        pomtJurisdiccion.setPomtPlanes(this);
        pomtJurisdiccion.setCodigoAutoridad(this.getCodigoAutoridad());
        return pomtJurisdiccion;
    }

    public PomtJurisdiccion removePomtJurisdiccion(PomtJurisdiccion pomtJurisdiccion) {
        getPomtJurisdiccionList().remove(pomtJurisdiccion);
        pomtJurisdiccion.setPomtPlanes(null);
        return pomtJurisdiccion;
    }

    public List<PomtInstrumentosPlanificacion> getPomtInstrumentosPlanificacionList() {
        return pomtInstrumentosPlanificacionList;
    }

    public void setPomtInstrumentosPlanificacionList(List<PomtInstrumentosPlanificacion> pomtInstrumentosPlanificacionList) {
        this.pomtInstrumentosPlanificacionList =
                pomtInstrumentosPlanificacionList;
    }

    public PomtInstrumentosPlanificacion addPomtInstrumentosPlanificacion(PomtInstrumentosPlanificacion pomtInstrumentosPlanificacion) {
        getPomtInstrumentosPlanificacionList().add(pomtInstrumentosPlanificacion);
        pomtInstrumentosPlanificacion.setPomtPlanes(this);
        pomtInstrumentosPlanificacion.setCodigoAutoridad(this.getCodigoAutoridad());
        return pomtInstrumentosPlanificacion;
    }

    public PomtInstrumentosPlanificacion removePomtInstrumentosPlanificacion(PomtInstrumentosPlanificacion pomtInstrumentosPlanificacion) {
        getPomtInstrumentosPlanificacionList().remove(pomtInstrumentosPlanificacion);
        pomtInstrumentosPlanificacion.setPomtPlanes(null);
        return pomtInstrumentosPlanificacion;
    }

    public List<PomtComunidadesEtnicas> getPomtComunidadesEtnicasList() {
        return pomtComunidadesEtnicasList;
    }

    public void setPomtComunidadesEtnicasList(List<PomtComunidadesEtnicas> pomtComunidadesEtnicasList) {
        this.pomtComunidadesEtnicasList = pomtComunidadesEtnicasList;
    }
    
  public PomtComunidadesEtnicas addPomtComunidadesEtnicas(PomtComunidadesEtnicas pomtComunidadesEtnicas) {
    System.out.println("PomtComunidadesEtnicasList " + pomtComunidadesEtnicasList);
      getPomtComunidadesEtnicasList().add(pomtComunidadesEtnicas);
      pomtComunidadesEtnicas.setPomtPlanes(this);
      pomtComunidadesEtnicas.setCodigoAutoridad(this.getCodigoAutoridad());
      return pomtComunidadesEtnicas;
  }

  public PomtComunidadesEtnicas removePomtComunidadesEtnicas(PomtComunidadesEtnicas pomtComunidadesEtnicas) {
      getPomtComunidadesEtnicasList().remove(pomtComunidadesEtnicas);
      pomtComunidadesEtnicas.setPomtPlanes(null);
      return pomtComunidadesEtnicas;
  }
// ZSDG IN
  public void setDeterminanteAmbientalList(List<DeterminanteAmbiental> determinanteAmbientalList) {
    this.determinanteAmbientalList = determinanteAmbientalList;
  }

  public List<DeterminanteAmbiental> getDeterminanteAmbientalList() {
    return determinanteAmbientalList;
  }
  
  public DeterminanteAmbiental addDeterminanteAmbiental(DeterminanteAmbiental determinanteAmbiental) {
     System.out.println("LLego al metodo addDeterminanteAmbiental" + determinanteAmbientalList);
    System.out.println("Pom planes " + this);
      getDeterminanteAmbientalList().add(determinanteAmbiental);
      
      determinanteAmbiental.setPomtPlanes(this);
      determinanteAmbiental.setCodigoAutoridad(this.getCodigoAutoridad());
      return determinanteAmbiental;
  }

  public DeterminanteAmbiental removeDeterminanteAmbiental(DeterminanteAmbiental determinanteAmbiental) {
      getDeterminanteAmbientalList().remove(determinanteAmbiental);
      determinanteAmbiental.setPomtPlanes(null);
      return determinanteAmbiental;
  }
// ZSDG
    

    /*public List<PomtArchivosXPlan> getPomtArchivosXPlanList() {
        return pomtArchivosXPlanList;
    }

    public void setPomtArchivosXPlanList(List<PomtArchivosXPlan> pomtArchivosXPlanList) {
        this.pomtArchivosXPlanList = pomtArchivosXPlanList;
    }

    public PomtArchivosXPlan addPomtArchivosXPlan(PomtArchivosXPlan pomtArchivosXPlan) {
        getPomtArchivosXPlanList().add(pomtArchivosXPlan);
        pomtArchivosXPlan.setPomtPlanes(this);
        return pomtArchivosXPlan;
    }

    public PomtArchivosXPlan removePomtArchivosXPlan(PomtArchivosXPlan pomtArchivosXPlan) {
        getPomtArchivosXPlanList().remove(pomtArchivosXPlan);
        pomtArchivosXPlan.setPomtPlanes(null);
        return pomtArchivosXPlan;
    }*/

    public Long getMapa_resultante_zonif() {
        return mapa_resultante_zonif;
    }

    public void setMapa_resultante_zonif(Long mapa_resultante_zonif) {
        this.mapa_resultante_zonif = mapa_resultante_zonif;
    }

    public Long getInfo_proceso_zonif() {
        return info_proceso_zonif;
    }

    public void setInfo_proceso_zonif(Long info_proceso_zonif) {
        this.info_proceso_zonif = info_proceso_zonif;
    }

    public Timestamp getFecha_info_zonif() {
        return fecha_info_zonif;
    }

    public void setFecha_info_zonif(Timestamp fecha_info_zonif) {
        this.fecha_info_zonif = fecha_info_zonif;
    }

    public String getCompSocioEconomico() {
        return compSocioEconomico;
    }

    public void setCompSocioEconomico(String compSocioEconomico) {
        this.compSocioEconomico = compSocioEconomico;
    }

    public String getCompEconomico() {
        return compEconomico;
    }

    public void setCompEconomico(String compEconomico) {
        this.compEconomico = compEconomico;
    }

    public String getCompBiofisico() {
        return compBiofisico;
    }

    public void setCompBiofisico(String compBiofisico) {
        this.compBiofisico = compBiofisico;
    }

    public String getCompAmenazaRiesgo() {
        return compAmenazaRiesgo;
    }

    public void setCompAmenazaRiesgo(String compAmenazaRiesgo) {
        this.compAmenazaRiesgo = compAmenazaRiesgo;
    }

    public String getCompConflictosSociAmb() {
        return compConflictosSociAmb;
    }

    public void setCompConflictosSociAmb(String compConflictosSociAmb) {
        this.compConflictosSociAmb = compConflictosSociAmb;
    }

    public Double getComHidOfertaTotal() {
        return comHidOfertaTotal;
    }

    public void setComHidOfertaTotal(Double comHidOfertaTotal) {
        this.comHidOfertaTotal = comHidOfertaTotal;
    }

    public Double getComHidCaudAmbMedio() {
        return comHidCaudAmbMedio;
    }

    public void setComHidCaudAmbMedio(Double comHidCaudAmbMedio) {
        this.comHidCaudAmbMedio = comHidCaudAmbMedio;
    }

    public Double getComHidOfertaDisponible() {
        return comHidOfertaDisponible;
    }

    public void setComHidOfertaDisponible(Double comHidOfertaDisponible) {
        this.comHidOfertaDisponible = comHidOfertaDisponible;
    }

    public Double getComHidDemandaPotencial() {
        return comHidDemandaPotencial;
    }

    public void setComHidDemandaPotencial(Double comHidDemandaPotencial) {
        this.comHidDemandaPotencial = comHidDemandaPotencial;
    }

    public Double getComHidOfertaNeta() {
        return comHidOfertaNeta;
    }

    public void setComHidOfertaNeta(Double comHidOfertaNeta) {
        this.comHidOfertaNeta = comHidOfertaNeta;
    }

    public Double getComHidRendimHidric() {
        return comHidRendimHidric;
    }

    public void setComHidRendimHidric(Double comHidRendimHidric) {
        this.comHidRendimHidric = comHidRendimHidric;
    }

    public Double getComHidAridezIndice() {
        return comHidAridezIndice;
    }

    public void setComHidAridezIndice(Double comHidAridezIndice) {
        this.comHidAridezIndice = comHidAridezIndice;
    }

    public String getComHidAridezObserv() {
        return comHidAridezObserv;
    }

    public void setComHidAridezObserv(String comHidAridezObserv) {
        this.comHidAridezObserv = comHidAridezObserv;
    }

    public Double getComHidUsoAguaIndice() {
        return comHidUsoAguaIndice;
    }

    public void setComHidUsoAguaIndice(Double comHidUsoAguaIndice) {
        this.comHidUsoAguaIndice = comHidUsoAguaIndice;
    }

    public String getComHidUsoAguaObsv() {
        return comHidUsoAguaObsv;
    }

    public void setComHidUsoAguaObsv(String comHidUsoAguaObsv) {
        this.comHidUsoAguaObsv = comHidUsoAguaObsv;
    }

   public String getFaseElabAprestam() {
        return faseElabAprestam;
    }

    public void setFaseElabAprestam(String faseElabAprestam) {
        this.faseElabAprestam = faseElabAprestam;
    }

    public String getFaseElabDiagnost() {
        return faseElabDiagnost;
    }

    public void setFaseElabDiagnost(String faseElabDiagnost) {
        this.faseElabDiagnost = faseElabDiagnost;
    }

    public String getFaseElabZonif() {
        return faseElabZonif;
    }

    public void setFaseElabZonif(String faseElabZonif) {
        this.faseElabZonif = faseElabZonif;
    }

    public String getFaseElabFormulac() {
        return faseElabFormulac;
    }

    public void setFaseElabFormulac(String faseElabFormulac) {
        this.faseElabFormulac = faseElabFormulac;
    }

    public String getFaseElabEjecu() {
        return faseElabEjecu;
    }

    public void setFaseElabEjecu(String faseElabEjecu) {
        this.faseElabEjecu = faseElabEjecu;
    }

    public String getFaseElabSeg() {
        return faseElabSeg;
    }

    public void setFaseElabSeg(String faseElabSeg) {
        this.faseElabSeg = faseElabSeg;
    }

    public Double getZnAreaPrioConsrvNum() {
        return znAreaPrioConsrvNum;
    }

    public void setZnAreaPrioConsrvNum(Double znAreaPrioConsrvNum) {
        this.znAreaPrioConsrvNum = znAreaPrioConsrvNum;
    }

    public Double getZnAreaPrioPreserNum() {
        return znAreaPrioPreserNum;
    }

    public void setZnAreaPrioPreserNum(Double znAreaPrioPreserNum) {
        this.znAreaPrioPreserNum = znAreaPrioPreserNum;
    }

    public Double getZnAreaProtegNum() {
        return znAreaProtegNum;
    }

    public void setZnAreaProtegNum(Double znAreaProtegNum) {
        this.znAreaProtegNum = znAreaProtegNum;
    }

    public Double getZnAreaRecupNum() {
        return znAreaRecupNum;
    }

    public void setZnAreaRecupNum(Double znAreaRecupNum) {
        this.znAreaRecupNum = znAreaRecupNum;
    }

    public Double getZnAreaRehabNum() {
        return znAreaRehabNum;
    }

    public void setZnAreaRehabNum(Double znAreaRehabNum) {
        this.znAreaRehabNum = znAreaRehabNum;
    }

    public Double getZnAreaRestauNum() {
        return znAreaRestauNum;
    }

    public void setZnAreaRestauNum(Double znAreaRestauNum) {
        this.znAreaRestauNum = znAreaRestauNum;
    }

    public Double getZnAreaManejAgriNum() {
        return znAreaManejAgriNum;
    }

    public void setZnAreaManejAgriNum(Double znAreaManejAgriNum) {
        this.znAreaManejAgriNum = znAreaManejAgriNum;
    }

    public Double getZnAreaManejForesNum() {
        return znAreaManejForesNum;
    }

    public void setZnAreaManejForesNum(Double znAreaManejForesNum) {
        this.znAreaManejForesNum = znAreaManejForesNum;
    }

    public Double getZnAreaManejPecuNum() {
        return znAreaManejPecuNum;
    }

    public void setZnAreaManejPecuNum(Double znAreaManejPecuNum) {
        this.znAreaManejPecuNum = znAreaManejPecuNum;
    }

    public Double getZnAreaManejMinerNum() {
        return znAreaManejMinerNum;
    }

    public void setZnAreaManejMinerNum(Double znAreaManejMinerNum) {
        this.znAreaManejMinerNum = znAreaManejMinerNum;
    }

    public Double getZn_estableIndusNum() {
        return znEstableIndusNum;
    }

    public void setZn_estableIndusNum(Double zn_estableIndusNum) {
        this.znEstableIndusNum = zn_estableIndusNum;
    }

    public Double getZn_zonasUrbanasNum() {
        return znZonasUrbanasNum;
    }

    public void setZn_zonasUrbanasNum(Double zn_zonasUrbanasNum) {
        this.znZonasUrbanasNum = zn_zonasUrbanasNum;
    }

    public Long getCodigoAutoridad() {
        return codigoAutoridad;
    }

    public void setCodigoAutoridad(Long codigoAutoridad) {
        this.codigoAutoridad = codigoAutoridad;
    }


    public String getComiActoAdmin() {
        return comiActoAdmin;
    }

    public void setComiActoAdmin(String comiActoAdmin) {
        this.comiActoAdmin = comiActoAdmin;
    }

    public Timestamp getComiFechaActo() {
        return comiFechaActo;
    }

    public void setComiFechaActo(Timestamp comiFechaActo) {
        this.comiFechaActo = comiFechaActo;
    }

    public Double getZnAreaPrioConsrvPrctj() {
        return znAreaPrioConsrvPrctj;
    }

    public void setZnAreaPrioConsrvPrctj(Double znAreaPrioConsrvPrctj) {
        this.znAreaPrioConsrvPrctj = znAreaPrioConsrvPrctj;
    }

    public Double getZnAreaPrioPreserPrctj() {
        return znAreaPrioPreserPrctj;
    }

    public void setZnAreaPrioPreserPrctj(Double znAreaPrioPreserPrctj) {
        this.znAreaPrioPreserPrctj = znAreaPrioPreserPrctj;
    }

    public Double getZnAreaProtegPrctj() {
        return znAreaProtegPrctj;
    }

    public void setZnAreaProtegPrctj(Double znAreaProtegPrctj) {
        this.znAreaProtegPrctj = znAreaProtegPrctj;
    }

    public Double getZnAreaRecupPrctj() {
        return znAreaRecupPrctj;
    }

    public void setZnAreaRecupPrctj(Double znAreaRecupPrctj) {
        this.znAreaRecupPrctj = znAreaRecupPrctj;
    }

    public Double getZnAreaRehabPrctj() {
        return znAreaRehabPrctj;
    }

    public void setZnAreaRehabPrctj(Double znAreaRehabPrctj) {
        this.znAreaRehabPrctj = znAreaRehabPrctj;
    }

    public Double getZnAreaRestauPrctj() {
        return znAreaRestauPrctj;
    }

    public void setZnAreaRestauPrctj(Double znAreaRestauPrctj) {
        this.znAreaRestauPrctj = znAreaRestauPrctj;
    }

    public Double getZnAreaManejAgriPrctj() {
        return znAreaManejAgriPrctj;
    }

    public void setZnAreaManejAgriPrctj(Double znAreaManejAgriPrctj) {
        this.znAreaManejAgriPrctj = znAreaManejAgriPrctj;
    }

    public Double getZnAreaManejForesPrctj() {
        return znAreaManejForesPrctj;
    }

    public void setZnAreaManejForesPrctj(Double znAreaManejForesPrctj) {
        this.znAreaManejForesPrctj = znAreaManejForesPrctj;
    }

    public Double getZnAreaManejPecuPrctj() {
        return znAreaManejPecuPrctj;
    }

    public void setZnAreaManejPecuPrctj(Double znAreaManejPecuPrctj) {
        this.znAreaManejPecuPrctj = znAreaManejPecuPrctj;
    }

    public Double getZnAreaManejMinerPrctj() {
        return znAreaManejMinerPrctj;
    }

    public void setZnAreaManejMinerPrctj(Double znAreaManejMinerPrctj) {
        this.znAreaManejMinerPrctj = znAreaManejMinerPrctj;
    }

    public Double getZnEstableIndusPrctj() {
        return znEstableIndusPrctj;
    }

    public void setZnEstableIndusPrctj(Double znEstableIndusPrctj) {
        this.znEstableIndusPrctj = znEstableIndusPrctj;
    }

    public Double getZnZonasUrbanasPrctj() {
        return znZonasUrbanasPrctj;
    }

    public void setZnZonasUrbanasPrctj(Double znZonasUrbanasPrctj) {
        this.znZonasUrbanasPrctj = znZonasUrbanasPrctj;
    }

    public Double getComHidIndiceRetRegHid() {
        return comHidIndiceRetRegHid;
    }

    public void setComHidIndiceRetRegHid(Double comHidIndiceRetRegHid) {
        this.comHidIndiceRetRegHid = comHidIndiceRetRegHid;
    }

    public Double getComHidIndiceVulneDesab() {
        return comHidIndiceVulneDesab;
    }

    public void setComHidIndiceVulneDesab(Double comHidIndiceVulneDesab) {
        this.comHidIndiceVulneDesab = comHidIndiceVulneDesab;
    }

    public Double getComHidIndiceCalidadAgua() {
        return comHidIndiceCalidadAgua;
    }

    public void setComHidIndiceCalidadAgua(Double comHidIndiceCalidadAgua) {
        this.comHidIndiceCalidadAgua = comHidIndiceCalidadAgua;
    }

    public Double getComHidIacal() {
        return comHidIacal;
    }

    public void setComHidIacal(Double comHidIacal) {
        this.comHidIacal = comHidIacal;
    }

 
}
