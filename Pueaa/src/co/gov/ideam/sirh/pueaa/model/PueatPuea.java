package co.gov.ideam.sirh.pueaa.model;

import java.io.Serializable;

import java.sql.Timestamp;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@NamedQueries({
  @NamedQuery(name = "PueatPuea.findAll", query = "select o from PueatPuea o where o.idUsuario =:usuarioId")
})
@Table(name = "PUEAT_PUEA")
public class PueatPuea implements Serializable {
    @GenericGenerator(name = "pueaa_generator", 
                      strategy = "co.gov.ideam.sirh.usuarios.agua.model.IdeamSirhGenerator",
                      parameters = {@Parameter(name = "AutoridadAmbientalId", value = "codigoAutoridad"),
                                    @Parameter(name = "Codigo", value = "id"),
                                    @Parameter(name = SequenceGenerator.SEQUENCE,  value= "SEQ_PUEAA")})
    @Id
    @Column(name = "id",nullable = false)
    private Long id;
    @Transient
    private Long codigoAutoridad;
    @Column(name="ID_LISTAS", nullable = false)
    private Long idListas;
    @Column(name="ID_USUARIO")
    private Long idUsuario;
    @Column(name="FECHA_APROBACION")
    private Timestamp fechaAprobacion;
    @Column(name="FECHA_EXPEDICION")
    private Timestamp fechaExpedicion;
    @Column(name="NUMERO_EXPEDIENTE")
    private String numeroExpediente;
    @Column(name="NUM_RESOLUCION", nullable = false)
    private String numResolucion;
    @Column(length = 200)
    private String observaciones;
    @Column(name="VIGENCIA_FINAL")
    private Timestamp vigenciaFinal;
    @Column(name="VIGENCIA_INICIAL")
    private Timestamp vigenciaInicial;
    @OneToMany(mappedBy = "pueatPuea")
    private List<PueatProyectos> pueatProyectosList;

    public PueatPuea() {
    }

    public PueatPuea(Long id, Long codigoAutoridad, Long idListas,
                     Long idUsuario, Timestamp fechaAprobacion,
                     Timestamp fechaExpedicion, String numeroExpediente,
                     String numResolucion, String observaciones,
                     Timestamp vigenciaFinal, Timestamp vigenciaInicial,
                     List<PueatProyectos> pueatProyectosList) {
        super();
        this.id = id;
        this.codigoAutoridad = codigoAutoridad;
        this.idListas = idListas;
        this.idUsuario = idUsuario;
        this.fechaAprobacion = fechaAprobacion;
        this.fechaExpedicion = fechaExpedicion;
        this.numeroExpediente = numeroExpediente;
        this.numResolucion = numResolucion;
        this.observaciones = observaciones;
        this.vigenciaFinal = vigenciaFinal;
        this.vigenciaInicial = vigenciaInicial;
        this.pueatProyectosList = pueatProyectosList;
    }


    public Timestamp getFechaAprobacion() {
        return fechaAprobacion;
    }

    public void setFechaAprobacion(Timestamp fechaAprobacion) {
        this.fechaAprobacion = fechaAprobacion;
    }

    public Timestamp getFechaExpedicion() {
        return fechaExpedicion;
    }

    public void setFechaExpedicion(Timestamp fechaExpedicion) {
        this.fechaExpedicion = fechaExpedicion;
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

    public Long getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Long idUsuario) {
        this.idUsuario = idUsuario;
    }

  

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Timestamp getVigenciaFinal() {
        return vigenciaFinal;
    }

    public void setVigenciaFinal(Timestamp vigenciaFinal) {
        this.vigenciaFinal = vigenciaFinal;
    }

    public Timestamp getVigenciaInicial() {
        return vigenciaInicial;
    }

    public void setVigenciaInicial(Timestamp vigenciaInicial) {
        this.vigenciaInicial = vigenciaInicial;
    }

    public List<PueatProyectos> getPueatProyectosList() {
        return pueatProyectosList;
    }

    public void setPueatProyectosList(List<PueatProyectos> pueatProyectosList) {
        this.pueatProyectosList = pueatProyectosList;
    }

    public PueatProyectos addPueatProyectos(PueatProyectos pueatProyectos) {
        getPueatProyectosList().add(pueatProyectos);
        pueatProyectos.setPueatPuea(this);
        return pueatProyectos;
    }

    public PueatProyectos removePueatProyectos(PueatProyectos pueatProyectos) {
        getPueatProyectosList().remove(pueatProyectos);
        pueatProyectos.setPueatPuea(null);
        return pueatProyectos;
    }

    public void setCodigoAutoridad(Long codigoAutoridad) {
        this.codigoAutoridad = codigoAutoridad;
    }

    public Long getCodigoAutoridad() {
        return codigoAutoridad;
    }

    public void setNumeroExpediente(String numeroExpediente) {
        this.numeroExpediente = numeroExpediente;
    }

    public String getNumeroExpediente() {
        return numeroExpediente;
    }

    public void setNumResolucion(String numResolucion) {
        this.numResolucion = numResolucion;
    }

    public String getNumResolucion() {
        return numResolucion;
    }
}
