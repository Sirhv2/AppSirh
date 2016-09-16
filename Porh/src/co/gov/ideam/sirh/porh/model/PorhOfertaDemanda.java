package co.gov.ideam.sirh.porh.model;

import co.gov.ideam.sirh.parametros.model.Lista;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.id.SequenceGenerator;

@Entity
@NamedQueries({
  @NamedQuery(name = "PorhOfertaDemanda.findAll", query = "select o from PorhOfertaDemanda o"),
  @NamedQuery(name = "PorhOfertaDemanda.find", query = "select o from PorhOfertaDemanda o where o.id_fuente =:idFuente and o.id_tramo = :idTramo"),
  @NamedQuery(name = "PorhOfertaDemanda.findByPuntoMonitoreo", query = "select o from PorhOfertaDemanda o where o.id_fuente =:idFuente and o.id_tramo = :idTramo and o.id_punto_monitoreo = :idPuntoMonitoreo"),
  @NamedQuery(name = "PorhOfertaDemanda.deleteByPlan", query = "delete from PorhOfertaDemanda o where o.porhId =:codigoPlan")
})
@Table(name = "porh_oferta_demanda")
public class PorhOfertaDemanda implements Serializable {
    @GenericGenerator(name = "porh_generator", 
                      strategy = "co.gov.ideam.sirh.usuarios.agua.model.IdeamSirhGenerator",
                      parameters = {@Parameter(name = "AutoridadAmbientalId", value = "codigoAutoridad"),
                                    @Parameter(name = "Codigo", value = "id"),
                                    @Parameter(name = SequenceGenerator.SEQUENCE,  value= "seq_porh")})
    @Id
   // @GeneratedValue(generator = "porh_generator")        
    @Column(name="id", nullable = false)
    private Long id;
    @Column(name="cauda_mamm_mayo")
    private Double caudal_mamm_mayo;
    @Column(name="cauda_mmm_mayo")
    private Double caudal_mmm_mayo;
    @Column(name="caudal_mamm_abril")
    private Double caudal_mamm_abril;
    @Column(name="caudal_mamm_agosto")
    private Double caudal_mamm_agosto;
    @Column(name="caudal_mamm_dic")
    private Double caudal_mamm_dic;
    @Column(name="caudal_mamm_enero")
    private Double caudal_mamm_enero;
    @Column(name="caudal_mamm_febrero")
    private Double caudal_mamm_febrero;
    @Column(name="caudal_mamm_julio")
    private Double caudal_mamm_julio;
    @Column(name="caudal_mamm_junio")
    private Double caudal_mamm_junio;
    @Column(name="caudal_mamm_marzo")
    private Double caudal_mamm_marzo;
    @Column(name="caudal_mamm_nov")
    private Double caudal_mamm_nov;
    @Column(name="caudal_mamm_oct")
    private Double caudal_mamm_oct;
    @Column(name="caudal_mamm_sep")
    private Double caudal_mamm_sep;
    @Column(name="caudal_mmm_abril")
    private Double caudal_mmm_abril;
    @Column(name="caudal_mmm_agosto")
    private Double caudal_mmm_agosto;
    @Column(name="caudal_mmm_dic")
    private Double caudal_mmm_dic;
    @Column(name="caudal_mmm_enero")
    private Double caudal_mmm_enero;
    @Column(name="caudal_mmm_febrero")
    private Double caudal_mmm_febrero;
    @Column(name="caudal_mmm_julio")
    private Double caudal_mmm_julio;
    @Column(name="caudal_mmm_junio")
    private Double caudal_mmm_junio;
    @Column(name="caudal_mmm_marzo")
    private Double caudal_mmm_marzo;
    @Column(name="caudal_mmm_nov")
    private Double caudal_mmm_nov;
    @Column(name="caudal_mmm_oct")
    private Double caudal_mmm_oct;
    @Column(name="caudal_mmm_sep")
    private Double caudal_mmm_sep;
    @Column(name="dem_estim_usu_ilegales")
    private Double dem_estim_usu_ilegales;
    @Column(name="demanda_concesionada")
    private Double demanda_concesionada;
    @Column(name="demanda_potencial")
    private Double demanda_potencial;
    @Column(name="desc_conflictos")
    private String desc_conflictos;
    @Column(name="desc_riesgos")
    private String desc_riesgos;
    @Column(name="id_fuente")
    private Long id_fuente;
    @Column(name="id_tramo")
    private Long id_tramo;
    @Column(name="id_punto_monitoreo")
    private Long id_punto_monitoreo;
    @Column(name="indice_de_uso")
    private Double indice_de_uso;
    @Column(name="indice_escases")
    private Double indice_escases;
     
    @Column(name = "indice_vulnerabilidad_hidrica")
    private Double indice_vulnerabilidad_hidrica;
    @Column(name = "indice_de_calidad_del_agua")
    private Double indice_de_calidad_del_agua;
    @Column(name = "monitoreo_biologico_grupo_trab")
    private Double monitoreo_biologico_grupo_trab;
    @Column(name = "indice_de_uso_del_agua")
    private Double indice_de_uso_del_agua;
    @Column(name = "indice_val_imp_eco_especie")
    private Double indice_val_imp_eco_especie;
    @Column(name = "indice_de_calidad_ecologica")
    private Double indice_de_calidad_ecologica;
    @Column(name = "indice_regulacion_hidrica")
    private Double indice_regulacion_hidrica;
    
    @Column(name = "proy_demanda_corto")
    private Double proy_demanda_corto;
    @Column(name = "proy_demanda_mediano")
    private Double proy_demanda_mediano;
    @Column(name = "proy_demanda_largo")
    private Double proy_demanda_largo;
    @Column(name = "metodo_empleado")
    private String metodo_empleado;
      
    @Column(name="num_captacion_ileg")
    private Long num_captacion_ileg;
    @Column(name="num_captacion_leg")
    private Long num_captacion_leg;
    @Column(name="demanda_estimada_capt_ile")
    private Long demanda_estimada_capt_ile;
    @Column(name="demanda_estimada_capt_leg")
    private Long demanda_estimada_capt_leg;
    @Column(name="demanda_estimada_vert_ile")
    private Long demanda_estimada_vert_ile;
    @Column(name="demanda_estimada_vert_leg")
    private Long demanda_estimada_vert_leg;


    @JoinColumn(name = "cau_amb_met", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Lista cau_amb_met; 
    @Column(name="cau_amb_met1_val")
    private Double cau_amb_met1_val ;
    @Column(name="cau_amb_met2_ano_seco")
    private Double cau_amb_met2_ano_seco ;
    @Column(name="cau_amb_met2_ano_hum")
    private Double cau_amb_met2_ano_hum ;
    @Column(name="cau_amb_met2_ano_nor")
    private Double cau_amb_met2_ano_nor ;
    @Column(name="cau_amb_met3_as_ene")
    private Double cau_amb_met3_as_ene ;
    @Column(name="cau_amb_met3_as_feb")
    private Double cau_amb_met3_as_feb ;
    @Column(name="cau_amb_met3_as_mar")
    private Double cau_amb_met3_as_mar ;
    @Column(name="cau_amb_met3_as_abr")
    private Double cau_amb_met3_as_abr ;
    @Column(name="cau_amb_met3_as_may")
    private Double cau_amb_met3_as_may ;
    @Column(name="cau_amb_met3_as_jun")
    private Double cau_amb_met3_as_jun ;
    @Column(name="cau_amb_met3_as_jul")
    private Double cau_amb_met3_as_jul ;
    @Column(name="cau_amb_met3_as_ago")
    private Double cau_amb_met3_as_ago ;
    @Column(name="cau_amb_met3_as_sep")
    private Double cau_amb_met3_as_sep ;
    @Column(name="cau_amb_met3_as_oct")
    private Double cau_amb_met3_as_oct ;
    @Column(name="cau_amb_met3_as_nov")
    private Double cau_amb_met3_as_nov ;
    @Column(name="cau_amb_met3_as_dic")
    private Double cau_amb_met3_as_dic ;
    @Column(name="cau_amb_met3_am_ene")
    private Double cau_amb_met3_am_ene ;
    @Column(name="cau_amb_met3_am_feb")
    private Double cau_amb_met3_am_feb ;
    @Column(name="cau_amb_met3_am_mar")
    private Double cau_amb_met3_am_mar ;
    @Column(name="cau_amb_met3_am_abr")
    private Double cau_amb_met3_am_abr ;
    @Column(name="cau_amb_met3_am_may")
    private Double cau_amb_met3_am_may ;
    @Column(name="cau_amb_met3_am_jun")
    private Double cau_amb_met3_am_jun ;
    @Column(name="cau_amb_met3_am_jul")
    private Double cau_amb_met3_am_jul ;
    @Column(name="cau_amb_met3_am_ago")
    private Double cau_amb_met3_am_ago ;
    @Column(name="cau_amb_met3_am_sep")
    private Double cau_amb_met3_am_sep ;
    @Column(name="cau_amb_met3_am_oct")
    private Double cau_amb_met3_am_oct ;
    @Column(name="cau_amb_met3_am_nov")
    private Double cau_amb_met3_am_nov ;
    @Column(name="cau_amb_met3_am_dic")
    private Double cau_amb_met3_am_dic ;
    @Column(name="cau_amb_met3_ah_ene")
    private Double cau_amb_met3_ah_ene ;
    @Column(name="cau_amb_met3_ah_feb")
    private Double cau_amb_met3_ah_feb ;
    @Column(name="cau_amb_met3_ah_mar")
    private Double cau_amb_met3_ah_mar ;
    @Column(name="cau_amb_met3_ah_abr")
    private Double cau_amb_met3_ah_abr ;
    @Column(name="cau_amb_met3_ah_may")
    private Double cau_amb_met3_ah_may ;
    @Column(name="cau_amb_met3_ah_jun")
    private Double cau_amb_met3_ah_jun ;
    @Column(name="cau_amb_met3_ah_jul")
    private Double cau_amb_met3_ah_jul ;
    @Column(name="cau_amb_met3_ah_ago")
    private Double cau_amb_met3_ah_ago ;
    @Column(name="cau_amb_met3_ah_sep")
    private Double cau_amb_met3_ah_sep ;
    @Column(name="cau_amb_met3_ah_oct")
    private Double cau_amb_met3_ah_oct ;
    @Column(name="cau_amb_met3_ah_nov")
    private Double cau_amb_met3_ah_nov ;
    @Column(name="cau_amb_met3_ah_dic")
    private Double cau_amb_met3_ah_dic ;
        
    @Column(name="num_est_usu_ilegales")
    private Long num_est_usu_ilegales;
    @Column(name="oferta_disponible")
    private Double oferta_disponible;
    @Column(name="oferta_neta")
    private Double oferta_neta;
    @Column(name="oferta_tot_anno_hum")
    private Double oferta_tot_anno_hum;
    @Column(name="oferta_tot_anno_medio")
    private Double oferta_tot_anno_medio;
    @Column(name="oferta_tot_anno_seco")
    private Double oferta_tot_anno_seco;
    @Column(name="num_vertimientos_leg")
    private Long num_vertimientos_leg;
    @Column(name="num_vertimientos_ileg")
    private Long num_vertimientos_ileg;
    @Column(name="porh_id", nullable = false)
    private Long porhId;    
    @Transient
    private Long codigoAutoridad;

    public PorhOfertaDemanda() {
    }

    public PorhOfertaDemanda(Double caudal_mamm_mayo, Double caudal_mmm_mayo,
                             Double caudal_mamm_abril, Double caudal_mamm_agosto,
                             Double caudal_mamm_dic, Double caudal_mamm_enero,
                             Double caudal_mamm_febrero,
                             Double caudal_mamm_julio, Double caudal_mamm_junio,
                             Double caudal_mamm_marzo, Double caudal_mamm_nov,
                             Double caudal_mamm_oct, Double caudal_mamm_sep,
                             Double caudal_mmm_abril, Double caudal_mmm_agosto,
                             Double caudal_mmm_dic, Double caudal_mmm_enero,
                             Double caudal_mmm_febrero, Double caudal_mmm_julio,
                             Double caudal_mmm_junio, Double caudal_mmm_marzo,
                             Double caudal_mmm_nov, Double caudal_mmm_oct,
                             Double caudal_mmm_sep,
                             Double dem_estim_usu_ilegales,
                             Double demanda_concesionada,
                             Double demanda_potencial, String desc_conflictos,
                             String desc_riesgos, Long id_fuente,
                             Long id_tramo, Double indice_de_uso,
                             Double indice_escases, Long num_captacion_ileg,
                             Long num_captacion_leg,
                             Long num_est_usu_ilegales,
                             Double oferta_disponible, Double oferta_neta,
                             Double oferta_tot_anno_hum,
                             Double oferta_tot_anno_medio,
                             Double oferta_tot_anno_seco,Long id_punto_monitoreo, Double indice_vulnerabilidad_hidrica,
                             Double indice_de_calidad_del_agua,Double monitoreo_biologico_grupo_trab,Double indice_de_uso_del_agua,
                             Double indice_val_imp_eco_especie,Double indice_de_calidad_ecologica,Double indice_regulacion_hidrica) {
        this.caudal_mamm_mayo = caudal_mamm_mayo;
        this.caudal_mmm_mayo = caudal_mmm_mayo;
        this.caudal_mamm_abril = caudal_mamm_abril;
        this.caudal_mamm_agosto = caudal_mamm_agosto;
        this.caudal_mamm_dic = caudal_mamm_dic;
        this.caudal_mamm_enero = caudal_mamm_enero;
        this.caudal_mamm_febrero = caudal_mamm_febrero;
        this.caudal_mamm_julio = caudal_mamm_julio;
        this.caudal_mamm_junio = caudal_mamm_junio;
        this.caudal_mamm_marzo = caudal_mamm_marzo;
        this.caudal_mamm_nov = caudal_mamm_nov;
        this.caudal_mamm_oct = caudal_mamm_oct;
        this.caudal_mamm_sep = caudal_mamm_sep;
        this.caudal_mmm_abril = caudal_mmm_abril;
        this.caudal_mmm_agosto = caudal_mmm_agosto;
        this.caudal_mmm_dic = caudal_mmm_dic;
        this.caudal_mmm_enero = caudal_mmm_enero;
        this.caudal_mmm_febrero = caudal_mmm_febrero;
        this.caudal_mmm_julio = caudal_mmm_julio;
        this.caudal_mmm_junio = caudal_mmm_junio;
        this.caudal_mmm_marzo = caudal_mmm_marzo;
        this.caudal_mmm_nov = caudal_mmm_nov;
        this.caudal_mmm_oct = caudal_mmm_oct;
        this.caudal_mmm_sep = caudal_mmm_sep;
        this.dem_estim_usu_ilegales = dem_estim_usu_ilegales;
        this.demanda_concesionada = demanda_concesionada;
        this.demanda_potencial = demanda_potencial;
        this.desc_conflictos = desc_conflictos;
        this.desc_riesgos = desc_riesgos;
        this.id_fuente = id_fuente;
        this.id_tramo = id_tramo;
        this.indice_de_uso = indice_de_uso;
        this.indice_escases = indice_escases;
        this.num_captacion_ileg = num_captacion_ileg;
        this.num_captacion_leg = num_captacion_leg;
        this.num_est_usu_ilegales = num_est_usu_ilegales;
        this.oferta_disponible = oferta_disponible;
        this.oferta_neta = oferta_neta;
        this.oferta_tot_anno_hum = oferta_tot_anno_hum;
        this.oferta_tot_anno_medio = oferta_tot_anno_medio;
        this.oferta_tot_anno_seco = oferta_tot_anno_seco;
        this.id_punto_monitoreo = id_punto_monitoreo;
        this.indice_vulnerabilidad_hidrica = indice_vulnerabilidad_hidrica;
        this.indice_de_calidad_del_agua = indice_de_calidad_del_agua;
        this.monitoreo_biologico_grupo_trab = monitoreo_biologico_grupo_trab;
        this.indice_de_uso_del_agua = indice_de_uso_del_agua;
        this.indice_val_imp_eco_especie = indice_val_imp_eco_especie;
        this.indice_de_calidad_ecologica = indice_de_calidad_ecologica;
        this.indice_regulacion_hidrica=indice_regulacion_hidrica;
        
    }

    public Double getCaudal_mamm_mayo() {
        return caudal_mamm_mayo;
    }

    public void setCaudal_mamm_mayo(Double caudal_mamm_mayo) {
        this.caudal_mamm_mayo = caudal_mamm_mayo;
    }

    public Double getCaudal_mmm_mayo() {
        return caudal_mmm_mayo;
    }

    public void setCaudal_mmm_mayo(Double caudal_mmm_mayo) {
        this.caudal_mmm_mayo = caudal_mmm_mayo;
    }

    public Double getCaudal_mamm_abril() {
        return caudal_mamm_abril;
    }

    public void setCaudal_mamm_abril(Double caudal_mamm_abril) {
        this.caudal_mamm_abril = caudal_mamm_abril;
    }

    public Double getCaudal_mamm_agosto() {
        return caudal_mamm_agosto;
    }

    public void setCaudal_mamm_agosto(Double caudal_mamm_agosto) {
        this.caudal_mamm_agosto = caudal_mamm_agosto;
    }

    public Double getCaudal_mamm_dic() {
        return caudal_mamm_dic;
    }

    public void setCaudal_mamm_dic(Double caudal_mamm_dic) {
        this.caudal_mamm_dic = caudal_mamm_dic;
    }

    public Double getCaudal_mamm_enero() {
        return caudal_mamm_enero;
    }

    public void setCaudal_mamm_enero(Double caudal_mamm_enero) {
        this.caudal_mamm_enero = caudal_mamm_enero;
    }

    public Double getCaudal_mamm_febrero() {
        return caudal_mamm_febrero;
    }

    public void setCaudal_mamm_febrero(Double caudal_mamm_febrero) {
        this.caudal_mamm_febrero = caudal_mamm_febrero;
    }

    public Double getCaudal_mamm_julio() {
        return caudal_mamm_julio;
    }

    public void setCaudal_mamm_julio(Double caudal_mamm_julio) {
        this.caudal_mamm_julio = caudal_mamm_julio;
    }

    public Double getCaudal_mamm_junio() {
        return caudal_mamm_junio;
    }

    public void setCaudal_mamm_junio(Double caudal_mamm_junio) {
        this.caudal_mamm_junio = caudal_mamm_junio;
    }

    public Double getCaudal_mamm_marzo() {
        return caudal_mamm_marzo;
    }

    public void setCaudal_mamm_marzo(Double caudal_mamm_marzo) {
        this.caudal_mamm_marzo = caudal_mamm_marzo;
    }

    public Double getCaudal_mamm_nov() {
        return caudal_mamm_nov;
    }

    public void setCaudal_mamm_nov(Double caudal_mamm_nov) {
        this.caudal_mamm_nov = caudal_mamm_nov;
    }

    public Double getCaudal_mamm_oct() {
        return caudal_mamm_oct;
    }

    public void setCaudal_mamm_oct(Double caudal_mamm_oct) {
        this.caudal_mamm_oct = caudal_mamm_oct;
    }

    public Double getCaudal_mamm_sep() {
        return caudal_mamm_sep;
    }

    public void setCaudal_mamm_sep(Double caudal_mamm_sep) {
        this.caudal_mamm_sep = caudal_mamm_sep;
    }

    public Double getCaudal_mmm_abril() {
        return caudal_mmm_abril;
    }

    public void setCaudal_mmm_abril(Double caudal_mmm_abril) {
        this.caudal_mmm_abril = caudal_mmm_abril;
    }

    public Double getCaudal_mmm_agosto() {
        return caudal_mmm_agosto;
    }

    public void setCaudal_mmm_agosto(Double caudal_mmm_agosto) {
        this.caudal_mmm_agosto = caudal_mmm_agosto;
    }

    public Double getCaudal_mmm_dic() {
        return caudal_mmm_dic;
    }

    public void setCaudal_mmm_dic(Double caudal_mmm_dic) {
        this.caudal_mmm_dic = caudal_mmm_dic;
    }

    public Double getCaudal_mmm_enero() {
        return caudal_mmm_enero;
    }

    public void setCaudal_mmm_enero(Double caudal_mmm_enero) {
        this.caudal_mmm_enero = caudal_mmm_enero;
    }

    public Double getCaudal_mmm_febrero() {
        return caudal_mmm_febrero;
    }

    public void setCaudal_mmm_febrero(Double caudal_mmm_febrero) {
        this.caudal_mmm_febrero = caudal_mmm_febrero;
    }

    public Double getCaudal_mmm_julio() {
        return caudal_mmm_julio;
    }

    public void setCaudal_mmm_julio(Double caudal_mmm_julio) {
        this.caudal_mmm_julio = caudal_mmm_julio;
    }

    public Double getCaudal_mmm_junio() {
        return caudal_mmm_junio;
    }

    public void setCaudal_mmm_junio(Double caudal_mmm_junio) {
        this.caudal_mmm_junio = caudal_mmm_junio;
    }

    public Double getCaudal_mmm_marzo() {
        return caudal_mmm_marzo;
    }

    public void setCaudal_mmm_marzo(Double caudal_mmm_marzo) {
        this.caudal_mmm_marzo = caudal_mmm_marzo;
    }

    public Double getCaudal_mmm_nov() {
        return caudal_mmm_nov;
    }

    public void setCaudal_mmm_nov(Double caudal_mmm_nov) {
        this.caudal_mmm_nov = caudal_mmm_nov;
    }

    public Double getCaudal_mmm_oct() {
        return caudal_mmm_oct;
    }

    public void setCaudal_mmm_oct(Double caudal_mmm_oct) {
        this.caudal_mmm_oct = caudal_mmm_oct;
    }

    public Double getCaudal_mmm_sep() {
        return caudal_mmm_sep;
    }

    public void setCaudal_mmm_sep(Double caudal_mmm_sep) {
        this.caudal_mmm_sep = caudal_mmm_sep;
    }

    public Double getDem_estim_usu_ilegales() {
        return dem_estim_usu_ilegales;
    }

    public void setDem_estim_usu_ilegales(Double dem_estim_usu_ilegales) {
        this.dem_estim_usu_ilegales = dem_estim_usu_ilegales;
    }

    public Double getDemanda_concesionada() {
        return demanda_concesionada;
    }

    public void setDemanda_concesionada(Double demanda_concesionada) {
        this.demanda_concesionada = demanda_concesionada;
    }

    public Double getDemanda_potencial() {
        return demanda_potencial;
    }

    public void setDemanda_potencial(Double demanda_potencial) {
        this.demanda_potencial = demanda_potencial;
    }

    public String getDesc_conflictos() {
        return desc_conflictos;
    }

    public void setDesc_conflictos(String desc_conflictos) {
        this.desc_conflictos = desc_conflictos;
    }

    public String getDesc_riesgos() {
        return desc_riesgos;
    }

    public void setDesc_riesgos(String desc_riesgos) {
        this.desc_riesgos = desc_riesgos;
    }

    public Long getId_fuente() {
        return id_fuente;
    }

    public void setId_fuente(Long id_fuente) {
        this.id_fuente = id_fuente;
    }

    public Long getId_tramo() {
        return id_tramo;
    }

    public void setId_tramo(Long id_tramo) {
        this.id_tramo = id_tramo;
    }

    public Double getIndice_de_uso() {
        return indice_de_uso;
    }

    public void setIndice_de_uso(Double indice_de_uso) {
        this.indice_de_uso = indice_de_uso;
    }

    public Double getIndice_escases() {
        return indice_escases;
    }

    public void setIndice_escases(Double indice_escases) {
        this.indice_escases = indice_escases;
    }

    public Long getNum_captacion_ileg() {
        return num_captacion_ileg;
    }

    public void setNum_captacion_ileg(Long num_captacion_ileg) {
        this.num_captacion_ileg = num_captacion_ileg;
    }

    public Long getNum_captacion_leg() {
        return num_captacion_leg;
    }

    public void setNum_captacion_leg(Long num_captacion_leg) {
        this.num_captacion_leg = num_captacion_leg;
    }

    public Long getNum_est_usu_ilegales() {
        return num_est_usu_ilegales;
    }

    public void setNum_est_usu_ilegales(Long num_est_usu_ilegales) {
        this.num_est_usu_ilegales = num_est_usu_ilegales;
    }

    public Double getOferta_disponible() {
        return oferta_disponible;
    }

    public void setOferta_disponible(Double oferta_disponible) {
        this.oferta_disponible = oferta_disponible;
    }

    public Double getOferta_neta() {
        return oferta_neta;
    }

    public void setOferta_neta(Double oferta_neta) {
        this.oferta_neta = oferta_neta;
    }

    public Double getOferta_tot_anno_hum() {
        return oferta_tot_anno_hum;
    }

    public void setOferta_tot_anno_hum(Double oferta_tot_anno_hum) {
        this.oferta_tot_anno_hum = oferta_tot_anno_hum;
    }

    public Double getOferta_tot_anno_medio() {
        return oferta_tot_anno_medio;
    }

    public void setOferta_tot_anno_medio(Double oferta_tot_anno_medio) {
        this.oferta_tot_anno_medio = oferta_tot_anno_medio;
    }

    public Double getOferta_tot_anno_seco() {
        return oferta_tot_anno_seco;
    }

    public void setOferta_tot_anno_seco(Double oferta_tot_anno_seco) {
        this.oferta_tot_anno_seco = oferta_tot_anno_seco;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCodigoAutoridad() {
        return codigoAutoridad;
    }

    public void setCodigoAutoridad(Long codigoAutoridad) {
        this.codigoAutoridad = codigoAutoridad;
    }

    public Long getNum_vertimientos_leg() {
        return num_vertimientos_leg;
    }

    public void setNum_vertimientos_leg(Long num_vertimientos_leg) {
        this.num_vertimientos_leg = num_vertimientos_leg;
    }

    public Long getNum_vertimientos_ileg() {
        return num_vertimientos_ileg;
    }

    public void setNum_vertimientos_ileg(Long num_vertimientos_ileg) {
        this.num_vertimientos_ileg = num_vertimientos_ileg;
    }

    public Long getPorhId() {
        return porhId;
    }

    public void setPorhId(Long porhId) {
        this.porhId = porhId;
    }

  public void setId_punto_monitoreo(Long id_punto_monitoreo) {
    this.id_punto_monitoreo = id_punto_monitoreo;
  }

  public Long getId_punto_monitoreo() {
    return id_punto_monitoreo;
  }

  public void setIndice_vulnerabilidad_hidrica(Double indice_vulnerabilidad_hidrica) {
    this.indice_vulnerabilidad_hidrica = indice_vulnerabilidad_hidrica;
  }

  public Double getIndice_vulnerabilidad_hidrica() {
    return indice_vulnerabilidad_hidrica;
  }

  public void setIndice_de_calidad_del_agua(Double indice_de_calidad_del_agua) {
    this.indice_de_calidad_del_agua = indice_de_calidad_del_agua;
  }

  public Double getIndice_de_calidad_del_agua() {
    return indice_de_calidad_del_agua;
  }

  public void setMonitoreo_biologico_grupo_trab(Double monitoreo_biologico_grupo_trab) {
    this.monitoreo_biologico_grupo_trab = monitoreo_biologico_grupo_trab;
  }

  public Double getMonitoreo_biologico_grupo_trab() {
    return monitoreo_biologico_grupo_trab;
  }

  public void setIndice_de_uso_del_agua(Double indice_de_uso_del_agua) {
    this.indice_de_uso_del_agua = indice_de_uso_del_agua;
  }

  public Double getIndice_de_uso_del_agua() {
    return indice_de_uso_del_agua;
  }

  public void setIndice_val_imp_eco_especie(Double indice_val_imp_eco_especie) {
    this.indice_val_imp_eco_especie = indice_val_imp_eco_especie;
  }

  public Double getIndice_val_imp_eco_especie() {
    return indice_val_imp_eco_especie;
  }

  public void setIndice_de_calidad_ecologica(Double indice_de_calidad_ecologica) {
    this.indice_de_calidad_ecologica = indice_de_calidad_ecologica;
  }

  public Double getIndice_de_calidad_ecologica() {
    return indice_de_calidad_ecologica;
  }

  public void setIndice_regulacion_hidrica(Double indice_regulacion_hidrica) {
    this.indice_regulacion_hidrica = indice_regulacion_hidrica;
  }

  public Double getIndice_regulacion_hidrica() {
    return indice_regulacion_hidrica;
  }


  public void setMetodo_empleado(String metodo_empleado) {
    this.metodo_empleado = metodo_empleado;
  }

  public String getMetodo_empleado() {
    return metodo_empleado;
  }

  public void setDemanda_estimada_capt_ile(Long demanda_estimada_capt_ile) {
    this.demanda_estimada_capt_ile = demanda_estimada_capt_ile;
  }

  public Long getDemanda_estimada_capt_ile() {
    return demanda_estimada_capt_ile;
  }

  public void setDemanda_estimada_capt_leg(Long demanda_estimada_capt_leg) {
    this.demanda_estimada_capt_leg = demanda_estimada_capt_leg;
  }

  public Long getDemanda_estimada_capt_leg() {
    return demanda_estimada_capt_leg;
  }

  public void setDemanda_estimada_vert_ile(Long demanda_estimada_vert_ile) {
    this.demanda_estimada_vert_ile = demanda_estimada_vert_ile;
  }

  public Long getDemanda_estimada_vert_ile() {
    return demanda_estimada_vert_ile;
  }

  public void setDemanda_estimada_vert_leg(Long demanda_estimada_vert_leg) {
    this.demanda_estimada_vert_leg = demanda_estimada_vert_leg;
  }

  public Long getDemanda_estimada_vert_leg() {
    return demanda_estimada_vert_leg;
  }

  public void setProy_demanda_corto(Double proy_demanda_corto) {
    this.proy_demanda_corto = proy_demanda_corto;
  }

  public Double getProy_demanda_corto() {
    return proy_demanda_corto;
  }

  public void setProy_demanda_mediano(Double proy_demanda_mediano) {
    this.proy_demanda_mediano = proy_demanda_mediano;
  }

  public Double getProy_demanda_mediano() {
    return proy_demanda_mediano;
  }

  public void setProy_demanda_largo(Double proy_demanda_largo) {
    this.proy_demanda_largo = proy_demanda_largo;
  }

  public Double getProy_demanda_largo() {
    return proy_demanda_largo;
  }

  public void setCau_amb_met1_val(Double cau_amb_met1_val) {
    this.cau_amb_met1_val = cau_amb_met1_val;
  }

  public Double getCau_amb_met1_val() {
    return cau_amb_met1_val;
  }

  public void setCau_amb_met2_ano_seco(Double cau_amb_met2_ano_seco) {
    this.cau_amb_met2_ano_seco = cau_amb_met2_ano_seco;
  }

  public Double getCau_amb_met2_ano_seco() {
    return cau_amb_met2_ano_seco;
  }

  public void setCau_amb_met2_ano_hum(Double cau_amb_met2_ano_hum) {
    this.cau_amb_met2_ano_hum = cau_amb_met2_ano_hum;
  }

  public Double getCau_amb_met2_ano_hum() {
    return cau_amb_met2_ano_hum;
  }

  public void setCau_amb_met2_ano_nor(Double cau_amb_met2_ano_nor) {
    this.cau_amb_met2_ano_nor = cau_amb_met2_ano_nor;
  }

  public Double getCau_amb_met2_ano_nor() {
    return cau_amb_met2_ano_nor;
  }

  public void setCau_amb_met3_as_ene(Double cau_amb_met3_as_ene) {
    this.cau_amb_met3_as_ene = cau_amb_met3_as_ene;
  }

  public Double getCau_amb_met3_as_ene() {
    return cau_amb_met3_as_ene;
  }

  public void setCau_amb_met3_as_feb(Double cau_amb_met3_as_feb) {
    this.cau_amb_met3_as_feb = cau_amb_met3_as_feb;
  }

  public Double getCau_amb_met3_as_feb() {
    return cau_amb_met3_as_feb;
  }

  public void setCau_amb_met3_as_mar(Double cau_amb_met3_as_mar) {
    this.cau_amb_met3_as_mar = cau_amb_met3_as_mar;
  }

  public Double getCau_amb_met3_as_mar() {
    return cau_amb_met3_as_mar;
  }

  public void setCau_amb_met3_as_abr(Double cau_amb_met3_as_abr) {
    this.cau_amb_met3_as_abr = cau_amb_met3_as_abr;
  }

  public Double getCau_amb_met3_as_abr() {
    return cau_amb_met3_as_abr;
  }

  public void setCau_amb_met3_as_may(Double cau_amb_met3_as_may) {
    this.cau_amb_met3_as_may = cau_amb_met3_as_may;
  }

  public Double getCau_amb_met3_as_may() {
    return cau_amb_met3_as_may;
  }

  public void setCau_amb_met3_as_jun(Double cau_amb_met3_as_jun) {
    this.cau_amb_met3_as_jun = cau_amb_met3_as_jun;
  }

  public Double getCau_amb_met3_as_jun() {
    return cau_amb_met3_as_jun;
  }

  public void setCau_amb_met3_as_jul(Double cau_amb_met3_as_jul) {
    this.cau_amb_met3_as_jul = cau_amb_met3_as_jul;
  }

  public Double getCau_amb_met3_as_jul() {
    return cau_amb_met3_as_jul;
  }

  public void setCau_amb_met3_as_ago(Double cau_amb_met3_as_ago) {
    this.cau_amb_met3_as_ago = cau_amb_met3_as_ago;
  }

  public Double getCau_amb_met3_as_ago() {
    return cau_amb_met3_as_ago;
  }

  public void setCau_amb_met3_as_sep(Double cau_amb_met3_as_sep) {
    this.cau_amb_met3_as_sep = cau_amb_met3_as_sep;
  }

  public Double getCau_amb_met3_as_sep() {
    return cau_amb_met3_as_sep;
  }

  public void setCau_amb_met3_as_oct(Double cau_amb_met3_as_oct) {
    this.cau_amb_met3_as_oct = cau_amb_met3_as_oct;
  }

  public Double getCau_amb_met3_as_oct() {
    return cau_amb_met3_as_oct;
  }

  public void setCau_amb_met3_as_nov(Double cau_amb_met3_as_nov) {
    this.cau_amb_met3_as_nov = cau_amb_met3_as_nov;
  }

  public Double getCau_amb_met3_as_nov() {
    return cau_amb_met3_as_nov;
  }

  public void setCau_amb_met3_as_dic(Double cau_amb_met3_as_dic) {
    this.cau_amb_met3_as_dic = cau_amb_met3_as_dic;
  }

  public Double getCau_amb_met3_as_dic() {
    return cau_amb_met3_as_dic;
  }

  public void setCau_amb_met3_am_ene(Double cau_amb_met3_am_ene) {
    this.cau_amb_met3_am_ene = cau_amb_met3_am_ene;
  }

  public Double getCau_amb_met3_am_ene() {
    return cau_amb_met3_am_ene;
  }

  public void setCau_amb_met3_am_feb(Double cau_amb_met3_am_feb) {
    this.cau_amb_met3_am_feb = cau_amb_met3_am_feb;
  }

  public Double getCau_amb_met3_am_feb() {
    return cau_amb_met3_am_feb;
  }

  public void setCau_amb_met3_am_mar(Double cau_amb_met3_am_mar) {
    this.cau_amb_met3_am_mar = cau_amb_met3_am_mar;
  }

  public Double getCau_amb_met3_am_mar() {
    return cau_amb_met3_am_mar;
  }

  public void setCau_amb_met3_am_abr(Double cau_amb_met3_am_abr) {
    this.cau_amb_met3_am_abr = cau_amb_met3_am_abr;
  }

  public Double getCau_amb_met3_am_abr() {
    return cau_amb_met3_am_abr;
  }

  public void setCau_amb_met3_am_may(Double cau_amb_met3_am_may) {
    this.cau_amb_met3_am_may = cau_amb_met3_am_may;
  }

  public Double getCau_amb_met3_am_may() {
    return cau_amb_met3_am_may;
  }

  public void setCau_amb_met3_am_jun(Double cau_amb_met3_am_jun) {
    this.cau_amb_met3_am_jun = cau_amb_met3_am_jun;
  }

  public Double getCau_amb_met3_am_jun() {
    return cau_amb_met3_am_jun;
  }

  public void setCau_amb_met3_am_jul(Double cau_amb_met3_am_jul) {
    this.cau_amb_met3_am_jul = cau_amb_met3_am_jul;
  }

  public Double getCau_amb_met3_am_jul() {
    return cau_amb_met3_am_jul;
  }

  public void setCau_amb_met3_am_ago(Double cau_amb_met3_am_ago) {
    this.cau_amb_met3_am_ago = cau_amb_met3_am_ago;
  }

  public Double getCau_amb_met3_am_ago() {
    return cau_amb_met3_am_ago;
  }

  public void setCau_amb_met3_am_sep(Double cau_amb_met3_am_sep) {
    this.cau_amb_met3_am_sep = cau_amb_met3_am_sep;
  }

  public Double getCau_amb_met3_am_sep() {
    return cau_amb_met3_am_sep;
  }

  public void setCau_amb_met3_am_oct(Double cau_amb_met3_am_oct) {
    this.cau_amb_met3_am_oct = cau_amb_met3_am_oct;
  }

  public Double getCau_amb_met3_am_oct() {
    return cau_amb_met3_am_oct;
  }

  public void setCau_amb_met3_am_nov(Double cau_amb_met3_am_nov) {
    this.cau_amb_met3_am_nov = cau_amb_met3_am_nov;
  }

  public Double getCau_amb_met3_am_nov() {
    return cau_amb_met3_am_nov;
  }

  public void setCau_amb_met3_am_dic(Double cau_amb_met3_am_dic) {
    this.cau_amb_met3_am_dic = cau_amb_met3_am_dic;
  }

  public Double getCau_amb_met3_am_dic() {
    return cau_amb_met3_am_dic;
  }

  public void setCau_amb_met3_ah_ene(Double cau_amb_met3_ah_ene) {
    this.cau_amb_met3_ah_ene = cau_amb_met3_ah_ene;
  }

  public Double getCau_amb_met3_ah_ene() {
    return cau_amb_met3_ah_ene;
  }

  public void setCau_amb_met3_ah_feb(Double cau_amb_met3_ah_feb) {
    this.cau_amb_met3_ah_feb = cau_amb_met3_ah_feb;
  }

  public Double getCau_amb_met3_ah_feb() {
    return cau_amb_met3_ah_feb;
  }

  public void setCau_amb_met3_ah_mar(Double cau_amb_met3_ah_mar) {
    this.cau_amb_met3_ah_mar = cau_amb_met3_ah_mar;
  }

  public Double getCau_amb_met3_ah_mar() {
    return cau_amb_met3_ah_mar;
  }

  public void setCau_amb_met3_ah_abr(Double cau_amb_met3_ah_abr) {
    this.cau_amb_met3_ah_abr = cau_amb_met3_ah_abr;
  }

  public Double getCau_amb_met3_ah_abr() {
    return cau_amb_met3_ah_abr;
  }

  public void setCau_amb_met3_ah_may(Double cau_amb_met3_ah_may) {
    this.cau_amb_met3_ah_may = cau_amb_met3_ah_may;
  }

  public Double getCau_amb_met3_ah_may() {
    return cau_amb_met3_ah_may;
  }

  public void setCau_amb_met3_ah_jun(Double cau_amb_met3_ah_jun) {
    this.cau_amb_met3_ah_jun = cau_amb_met3_ah_jun;
  }

  public Double getCau_amb_met3_ah_jun() {
    return cau_amb_met3_ah_jun;
  }

  public void setCau_amb_met3_ah_jul(Double cau_amb_met3_ah_jul) {
    this.cau_amb_met3_ah_jul = cau_amb_met3_ah_jul;
  }

  public Double getCau_amb_met3_ah_jul() {
    return cau_amb_met3_ah_jul;
  }

  public void setCau_amb_met3_ah_ago(Double cau_amb_met3_ah_ago) {
    this.cau_amb_met3_ah_ago = cau_amb_met3_ah_ago;
  }

  public Double getCau_amb_met3_ah_ago() {
    return cau_amb_met3_ah_ago;
  }

  public void setCau_amb_met3_ah_sep(Double cau_amb_met3_ah_sep) {
    this.cau_amb_met3_ah_sep = cau_amb_met3_ah_sep;
  }

  public Double getCau_amb_met3_ah_sep() {
    return cau_amb_met3_ah_sep;
  }

  public void setCau_amb_met3_ah_oct(Double cau_amb_met3_ah_oct) {
    this.cau_amb_met3_ah_oct = cau_amb_met3_ah_oct;
  }

  public Double getCau_amb_met3_ah_oct() {
    return cau_amb_met3_ah_oct;
  }

  public void setCau_amb_met3_ah_nov(Double cau_amb_met3_ah_nov) {
    this.cau_amb_met3_ah_nov = cau_amb_met3_ah_nov;
  }

  public Double getCau_amb_met3_ah_nov() {
    return cau_amb_met3_ah_nov;
  }

  public void setCau_amb_met3_ah_dic(Double cau_amb_met3_ah_dic) {
    this.cau_amb_met3_ah_dic = cau_amb_met3_ah_dic;
  }

  public Double getCau_amb_met3_ah_dic() {
    return cau_amb_met3_ah_dic;
  }

  public void setCau_amb_met(Lista cau_amb_met) {
    this.cau_amb_met = cau_amb_met;
  }

  public Lista getCau_amb_met() {
    return cau_amb_met;
  }
}
