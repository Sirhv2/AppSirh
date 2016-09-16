package co.gov.ideam.sirh.pueaa.model;

import java.io.Serializable;

import java.sql.Timestamp;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@NamedQueries({
  @NamedQuery(name = "PueatSeguimiento.findAll", query = "select o from PueatSeguimiento o"),
  @NamedQuery(name = "PueatSeguimiento.findByProject", query = "select o from PueatSeguimiento o where o.pueatProyectos.id = :idProyecto order by o.fechaVisita,o.consumo")
})
@Table(name = "PUEAT_SEGUIMIENTO")
public class PueatSeguimiento implements Serializable {
    private byte[] certificado;
    private Float consumo;
    @Column(name="FECHA_VISITA")
    private Date fechaVisita;
    @Id
    @SequenceGenerator(name = "PueatSeguimientoSeq",
                       sequenceName = "SEQ_PUEAT_SEGUIMIENTO", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
                    generator = "PueatSeguimientoSeq")
    @Column(nullable = false)
    private Long id;
    @Column(name="NOMBRE_VISITA", nullable = false, length = 200)
    private String nombreVisita;
    @Column(length = 300)
    private String observaciones;
    @ManyToOne
    @JoinColumn(name = "PUEAT_PROYECTOS_ID")
    private PueatProyectos pueatProyectos;

    public PueatSeguimiento() {
    }

    public PueatSeguimiento(Float consumo, Timestamp fechaVisita, Long id,
                            String nombreVisita, String observaciones,
                            PueatProyectos pueatProyectos) {
        this.consumo = consumo;
        this.fechaVisita = fechaVisita;
        this.id = id;
        this.nombreVisita = nombreVisita;
        this.observaciones = observaciones;
        this.pueatProyectos = pueatProyectos;
    }

    public byte[] getCertificado() {
        return certificado;
    }

    public void setCertificado(byte[] certificado) {
        this.certificado = certificado;
    }

    public Float getConsumo() {
        return consumo;
    }

    public void setConsumo(Float consumo) {
        this.consumo = consumo;
    }

   public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreVisita() {
        return nombreVisita;
    }

    public void setNombreVisita(String nombreVisita) {
        this.nombreVisita = nombreVisita;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }


    public PueatProyectos getPueatProyectos() {
        return pueatProyectos;
    }

    public void setPueatProyectos(PueatProyectos pueatProyectos) {
        this.pueatProyectos = pueatProyectos;
    }

  public void setFechaVisita(Date fechaVisita) {
    this.fechaVisita = fechaVisita;
  }

  public Date getFechaVisita() {
    return fechaVisita;
  }
}
