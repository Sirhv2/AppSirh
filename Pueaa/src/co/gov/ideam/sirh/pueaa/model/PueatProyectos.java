package co.gov.ideam.sirh.pueaa.model;

import java.io.Serializable;

import java.sql.Timestamp;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import javax.persistence.Transient;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.id.SequenceGenerator;

@Entity
@NamedQueries({
  @NamedQuery(name = "PueatProyectos.findAll", query = "select o from PueatProyectos o"),
   @NamedQuery(name = "PueatProyectos.findByPueaa", query = "select o from PueatProyectos o where o.pueatPuea.id =:pueaaId")
})
@Table(name = "PUEAT_PROYECTOS")
public class PueatProyectos implements Serializable {
     @GenericGenerator(name = "pueaa_generator", 
                      strategy = "co.gov.ideam.sirh.usuarios.agua.model.IdeamSirhGenerator",
                      parameters = {@Parameter(name = "AutoridadAmbientalId", value = "codigoAutoridad"),
                                    @Parameter(name = "Codigo", value = "id"),
                                    @Parameter(name = SequenceGenerator.SEQUENCE,  value= "SEQ_PROYECTO")})
    @Id
    @Column(nullable = false)
    private Long id;
    @Transient
    private Long codigoAutoridad; 
    @Column(length = 200)
    private String descripcion;
    @Column(name="FECHA_FIN")
    private Timestamp fechaFin;
    @Column(name="FECHA_INICIO")
    private Timestamp fechaInicio;
    @Column(name="ID_LISTAS")
    private Long idListas;
    @Column(name="LINEA_BASE")
    private Long lineaBase;
    @Column(name="META_CONSUMO_ANO_1")
    private Double metaConsumoAno1;
    @Column(name="META_CONSUMO_ANO_2")
    private Double metaConsumoAno2;
    @Column(name="META_CONSUMO_ANO_3")
    private Double metaConsumoAno3;
    @Column(name="META_CONSUMO_ANO_4")
    private Double metaConsumoAno4;
    @Column(name="META_CONSUMO_ANO_5")
    private Double metaConsumoAno5;
    @Column(name="META_FECHA_ANO_1")
    private Timestamp metaFechaAno1;
    @Column(name="META_FECHA_ANO_2")
    private Timestamp metaFechaAno2;
    @Column(name="META_FECHA_ANO_3")
    private Timestamp metaFechaAno3;
    @Column(name="META_FECHA_ANO_4")
    private Timestamp metaFechaAno4;
    @Column(name="META_FECHA_ANO_5")
    private Timestamp metaFechaAno5;
    //Seguimiento x cambio de usuario
    @Column(name="META_SEGUIMIENTO_ANO_1")
    private Double metaSeguimientoAno1;
    @Column(name="META_SEGUIMIENTO_ANO_2")
    private Double metaSeguimientoAno2;
    @Column(name="META_SEGUIMIENTO_ANO_3")
    private Double metaSeguimientoAno3;
    @Column(name="META_SEGUIMIENTO_ANO_4")
    private Double metaSeguimientoAno4;
    @Column(name="META_SEGUIMIENTO_ANO_5")
    private Double metaSeguimientoAno5;
    @Column(name="META_SEGUIMIENTO_FECHA_1")
    private Timestamp metaSeguimientoFechaAno1;
    @Column(name="META_SEGUIMIENTO_FECHA_2")
    private Timestamp metaSeguimientoFechaAno2;
    @Column(name="META_SEGUIMIENTO_FECHA_3")
    private Timestamp metaSeguimientoFechaAno3;
    @Column(name="META_SEGUIMIENTO_FECHA_4")
    private Timestamp metaSeguimientoFechaAno4;
    @Column(name="META_SEGUIMIENTO_FECHA_5")
    private Timestamp metaSeguimientoFechaAno5;
    @Column(name="NOMBRE_PROYECTO", length = 200)
    private String nombreProyecto;
    @Column(length = 3000)
    private String observaciones;
    @Column(name="INDICADOR")
    private String indicador;
    @Column(name="PRESUPUESTO")
    private Long presupuesto;    
    @OneToMany(mappedBy = "pueatProyectos")
    private List<PueatSeguimiento> pueatSeguimientoList;
    @ManyToOne
    @JoinColumn(name = "PUEAT_PUEA_ID")
    private PueatPuea pueatPuea;
    @OneToMany(mappedBy = "pueatProyectos")
    private List<PueatProyectoConcesiones> pueatProyectoConcesionesList;

    public PueatProyectos() {
    }

    public PueatProyectos(Long id, Long codigoAutoridad, String descripcion,
                          Timestamp fechaFin, Timestamp fechaInicio,
                          Long idListas, Long lineaBase, Double metaConsumoAno1,
                          Double metaConsumoAno2, Double metaConsumoAno3,
                          Double metaConsumoAno4, Double metaConsumoAno5,
                          Timestamp metaFechaAno1, Timestamp metaFechaAno2,
                          Timestamp metaFechaAno3, Timestamp metaFechaAno4,
                          Timestamp metaFechaAno5, Double metaSeguimientoAno1,
                          Double metaSeguimientoAno2, Double metaSeguimientoAno3,
                          Double metaSeguimientoAno4, Double metaSeguimientoAno5,
                          Timestamp metaSeguimientoFechaAno1,
                          Timestamp metaSeguimientoFechaAno2,
                          Timestamp metaSeguimientoFechaAno3,
                          Timestamp metaSeguimientoFechaAno4,
                          Timestamp metaSeguimientoFechaAno5,
                          String nombreProyecto, String observaciones,
                          String indicador, Long presupuesto,
                          List<PueatSeguimiento> pueatSeguimientoList,
                          PueatPuea pueatPuea,
                          List<PueatProyectoConcesiones> pueatProyectoConcesionesList) {
        super();
        this.id = id;
        this.codigoAutoridad = codigoAutoridad;
        this.descripcion = descripcion;
        this.fechaFin = fechaFin;
        this.fechaInicio = fechaInicio;
        this.idListas = idListas;
        this.lineaBase = lineaBase;
        this.metaConsumoAno1 = metaConsumoAno1;
        this.metaConsumoAno2 = metaConsumoAno2;
        this.metaConsumoAno3 = metaConsumoAno3;
        this.metaConsumoAno4 = metaConsumoAno4;
        this.metaConsumoAno5 = metaConsumoAno5;
        this.metaFechaAno1 = metaFechaAno1;
        this.metaFechaAno2 = metaFechaAno2;
        this.metaFechaAno3 = metaFechaAno3;
        this.metaFechaAno4 = metaFechaAno4;
        this.metaFechaAno5 = metaFechaAno5;
        this.metaSeguimientoAno1 = metaSeguimientoAno1;
        this.metaSeguimientoAno2 = metaSeguimientoAno2;
        this.metaSeguimientoAno3 = metaSeguimientoAno3;
        this.metaSeguimientoAno4 = metaSeguimientoAno4;
        this.metaSeguimientoAno5 = metaSeguimientoAno5;
        this.metaSeguimientoFechaAno1 = metaSeguimientoFechaAno1;
        this.metaSeguimientoFechaAno2 = metaSeguimientoFechaAno2;
        this.metaSeguimientoFechaAno3 = metaSeguimientoFechaAno3;
        this.metaSeguimientoFechaAno4 = metaSeguimientoFechaAno4;
        this.metaSeguimientoFechaAno5 = metaSeguimientoFechaAno5;
        this.nombreProyecto = nombreProyecto;
        this.observaciones = observaciones;
        this.indicador = indicador;
        this.presupuesto = presupuesto;
        this.pueatSeguimientoList = pueatSeguimientoList;
        this.pueatPuea = pueatPuea;
        this.pueatProyectoConcesionesList = pueatProyectoConcesionesList;
    }


    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Timestamp getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Timestamp fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Timestamp getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Timestamp fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdListas() {
        return idListas;
    }

    public void setIdListas(Long idListas) {
        this.idListas = idListas;
    }

    public Long getLineaBase() {
        return lineaBase;
    }

    public void setLineaBase(Long lineaBase) {
        this.lineaBase = lineaBase;
    }


    public Timestamp getMetaFechaAno1() {
        return metaFechaAno1;
    }

    public void setMetaFechaAno1(Timestamp metaFechaAno1) {
        this.metaFechaAno1 = metaFechaAno1;
    }

    public Timestamp getMetaFechaAno2() {
        return metaFechaAno2;
    }

    public void setMetaFechaAno2(Timestamp metaFechaAno2) {
        this.metaFechaAno2 = metaFechaAno2;
    }

    public Timestamp getMetaFechaAno3() {
        return metaFechaAno3;
    }

    public void setMetaFechaAno3(Timestamp metaFechaAno3) {
        this.metaFechaAno3 = metaFechaAno3;
    }

    public Timestamp getMetaFechaAno4() {
        return metaFechaAno4;
    }

    public void setMetaFechaAno4(Timestamp metaFechaAno4) {
        this.metaFechaAno4 = metaFechaAno4;
    }

    public Timestamp getMetaFechaAno5() {
        return metaFechaAno5;
    }

    public void setMetaFechaAno5(Timestamp metaFechaAno5) {
        this.metaFechaAno5 = metaFechaAno5;
    }

    public String getNombreProyecto() {
        return nombreProyecto;
    }

    public void setNombreProyecto(String nombreProyecto) {
        this.nombreProyecto = nombreProyecto;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    
    


    public List<PueatSeguimiento> getPueatSeguimientoList() {
        return pueatSeguimientoList;
    }

    public void setPueatSeguimientoList(List<PueatSeguimiento> pueatSeguimientoList) {
        this.pueatSeguimientoList = pueatSeguimientoList;
    }

    public PueatSeguimiento addPueatSeguimiento(PueatSeguimiento pueatSeguimiento) {
        getPueatSeguimientoList().add(pueatSeguimiento);
        pueatSeguimiento.setPueatProyectos(this);
        return pueatSeguimiento;
    }

    public PueatSeguimiento removePueatSeguimiento(PueatSeguimiento pueatSeguimiento) {
        getPueatSeguimientoList().remove(pueatSeguimiento);
        pueatSeguimiento.setPueatProyectos(null);
        return pueatSeguimiento;
    }

    public PueatPuea getPueatPuea() {
        return pueatPuea;
    }

    public void setPueatPuea(PueatPuea pueatPuea) {
        this.pueatPuea = pueatPuea;
    }

    public List<PueatProyectoConcesiones> getPueatProyectoConcesionesList() {
        return pueatProyectoConcesionesList;
    }

    public void setPueatProyectoConcesionesList(List<PueatProyectoConcesiones> pueatProyectoConcesionesList) {
        this.pueatProyectoConcesionesList = pueatProyectoConcesionesList;
    }

    public PueatProyectoConcesiones addPueatProyectoConcesiones(PueatProyectoConcesiones pueatProyectoConcesiones) {
        getPueatProyectoConcesionesList().add(pueatProyectoConcesiones);
        pueatProyectoConcesiones.setPueatProyectos(this);
        return pueatProyectoConcesiones;
    }

    public PueatProyectoConcesiones removePueatProyectoConcesiones(PueatProyectoConcesiones pueatProyectoConcesiones) {
        getPueatProyectoConcesionesList().remove(pueatProyectoConcesiones);
        pueatProyectoConcesiones.setPueatProyectos(null);
        return pueatProyectoConcesiones;
    }

    public void setIndicador(String indicador) {
        this.indicador = indicador;
    }

    public String getIndicador() {
        return indicador;
    }


    public void setCodigoAutoridad(Long codigoAutoridad) {
        this.codigoAutoridad = codigoAutoridad;
    }

    public Long getCodigoAutoridad() {
        return codigoAutoridad;
    }


    public void setMetaSeguimientoFechaAno1(Timestamp metaSeguimientoFechaAno1) {
        this.metaSeguimientoFechaAno1 = metaSeguimientoFechaAno1;
    }

    public Timestamp getMetaSeguimientoFechaAno1() {
        return metaSeguimientoFechaAno1;
    }

    public void setMetaSeguimientoFechaAno2(Timestamp metaSeguimientoFechaAno2) {
        this.metaSeguimientoFechaAno2 = metaSeguimientoFechaAno2;
    }

    public Timestamp getMetaSeguimientoFechaAno2() {
        return metaSeguimientoFechaAno2;
    }

    public void setMetaSeguimientoFechaAno3(Timestamp metaSeguimientoFechaAno3) {
        this.metaSeguimientoFechaAno3 = metaSeguimientoFechaAno3;
    }

    public Timestamp getMetaSeguimientoFechaAno3() {
        return metaSeguimientoFechaAno3;
    }

    public void setMetaSeguimientoFechaAno4(Timestamp metaSeguimientoFechaAno4) {
        this.metaSeguimientoFechaAno4 = metaSeguimientoFechaAno4;
    }

    public Timestamp getMetaSeguimientoFechaAno4() {
        return metaSeguimientoFechaAno4;
    }

    public void setMetaSeguimientoFechaAno5(Timestamp metaSeguimientoFechaAno5) {
        this.metaSeguimientoFechaAno5 = metaSeguimientoFechaAno5;
    }

    public Timestamp getMetaSeguimientoFechaAno5() {
        return metaSeguimientoFechaAno5;
    }

    public void setMetaConsumoAno1(Double metaConsumoAno1) {
        this.metaConsumoAno1 = metaConsumoAno1;
    }

    public Double getMetaConsumoAno1() {
        return metaConsumoAno1;
    }

    public void setMetaConsumoAno2(Double metaConsumoAno2) {
        this.metaConsumoAno2 = metaConsumoAno2;
    }

    public Double getMetaConsumoAno2() {
        return metaConsumoAno2;
    }

    public void setMetaConsumoAno3(Double metaConsumoAno3) {
        this.metaConsumoAno3 = metaConsumoAno3;
    }

    public Double getMetaConsumoAno3() {
        return metaConsumoAno3;
    }

    public void setMetaConsumoAno4(Double metaConsumoAno4) {
        this.metaConsumoAno4 = metaConsumoAno4;
    }

    public Double getMetaConsumoAno4() {
        return metaConsumoAno4;
    }

    public void setMetaConsumoAno5(Double metaConsumoAno5) {
        this.metaConsumoAno5 = metaConsumoAno5;
    }

    public Double getMetaConsumoAno5() {
        return metaConsumoAno5;
    }

    public void setMetaSeguimientoAno1(Double metaSeguimientoAno1) {
        this.metaSeguimientoAno1 = metaSeguimientoAno1;
    }

    public Double getMetaSeguimientoAno1() {
        return metaSeguimientoAno1;
    }

    public void setMetaSeguimientoAno2(Double metaSeguimientoAno2) {
        this.metaSeguimientoAno2 = metaSeguimientoAno2;
    }

    public Double getMetaSeguimientoAno2() {
        return metaSeguimientoAno2;
    }

    public void setMetaSeguimientoAno3(Double metaSeguimientoAno3) {
        this.metaSeguimientoAno3 = metaSeguimientoAno3;
    }

    public Double getMetaSeguimientoAno3() {
        return metaSeguimientoAno3;
    }

    public void setMetaSeguimientoAno4(Double metaSeguimientoAno4) {
        this.metaSeguimientoAno4 = metaSeguimientoAno4;
    }

    public Double getMetaSeguimientoAno4() {
        return metaSeguimientoAno4;
    }

    public void setMetaSeguimientoAno5(Double metaSeguimientoAno5) {
        this.metaSeguimientoAno5 = metaSeguimientoAno5;
    }

    public Double getMetaSeguimientoAno5() {
        return metaSeguimientoAno5;
    }

    public void setPresupuesto(Long presupuesto) {
        this.presupuesto = presupuesto;
    }

    public Long getPresupuesto() {
        return presupuesto;
    }
}
