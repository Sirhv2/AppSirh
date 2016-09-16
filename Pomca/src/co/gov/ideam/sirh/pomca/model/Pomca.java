package co.gov.ideam.sirh.pomca.model;


import co.gov.ideam.sirh.fuentes.model.FnttFuente;

import java.io.Serializable;


import java.math.BigDecimal;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.id.SequenceGenerator;


@Entity
@NamedQueries( { @NamedQuery(name = "Pomca.findAll",
                             query = "select o from Pomca o")
        ,
        @NamedQuery(name = "Pomca.findByCuenca", query = "select o from Pomca o where o.idCuenca = :idCuenca")
        ,
        @NamedQuery(name = "Pomca.findById", query = "select o from Pomca o where o.id = :idPomca")
        } )
@Table(name = "pomt_planes")
public class Pomca implements Serializable {


    @GenericGenerator(name = "pomca_generator",
                      strategy = "co.gov.ideam.sirh.usuarios.agua.model.IdeamSirhGenerator",
                      parameters =
                      { @Parameter(name = "AutoridadAmbientalId", value =
                                   "codigoAutoridad")
                , @Parameter(name = "Codigo", value = "id")
                ,
                @Parameter(name = SequenceGenerator.SEQUENCE, value = "seq_pomca")
                } )


    @Id
    @Column(name = "id", nullable = false)
    //@GeneratedValue(generator = "pomca_generator")
    private Long id;

    @Column(name = "fecha_aprobacion")
    private Timestamp fecha_aprobacion;


    @Column(name = "num_res_aprobacion")
    private String num_res_aprobacion;

    @Column(name = "presupuesto_asignado")
    private BigDecimal presupuestoAsignado;

    @Column(name = "porc_cumplimiento")
    private Double porcCumplimiento;

    @Column(name = "porc_ejecucion")
    private Double porcEjecucion;

    @Column(name = "id_cuenca", nullable = false)
    private Long idCuenca;


    @Column(name = "codigo_autoridad", nullable = false)
    private Long codigoAutoridad;

    @Column(name = "presupuesto_ejecutado")
    private BigDecimal presupuestoEjecutado;


    public Pomca() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }


    public void setPresupuestoAsignado(BigDecimal presupuestoAsignado) {
        this.presupuestoAsignado = presupuestoAsignado;
    }

    public BigDecimal getPresupuestoAsignado() {
        return presupuestoAsignado;
    }

    public void setPorcCumplimiento(Double porcCumplimiento) {
        this.porcCumplimiento = porcCumplimiento;
    }

    public Double getPorcCumplimiento() {
        return porcCumplimiento;
    }

    public void setPorcEjecucion(Double porcEjecucion) {
        this.porcEjecucion = porcEjecucion;
    }

    public Double getPorcEjecucion() {
        return porcEjecucion;
    }

    public void setCodigoAutoridad(Long codigoAutoridad) {
        this.codigoAutoridad = codigoAutoridad;
    }

    public Long getCodigoAutoridad() {
        return codigoAutoridad;
    }

    public void setIdCuenca(Long idCuenca) {
        this.idCuenca = idCuenca;
    }

    public Long getIdCuenca() {
        return idCuenca;
    }

    public void setNum_res_aprobacion(String num_res_aprobacion) {
        this.num_res_aprobacion = num_res_aprobacion;
    }

    public String getNum_res_aprobacion() {
        return num_res_aprobacion;
    }

    public void setFecha_aprobacion(Timestamp fecha_aprobacion) {
        this.fecha_aprobacion = fecha_aprobacion;
    }

    public Timestamp getFecha_aprobacion() {
        return fecha_aprobacion;
    }

    public void setPresupuestoEjecutado(BigDecimal presupuestoEjecutado) {
        this.presupuestoEjecutado = presupuestoEjecutado;
    }

    public BigDecimal getPresupuestoEjecutado() {
        return presupuestoEjecutado;
    }
}
