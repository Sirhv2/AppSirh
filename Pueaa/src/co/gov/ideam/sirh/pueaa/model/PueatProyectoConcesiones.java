package co.gov.ideam.sirh.pueaa.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
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
  @NamedQuery(name = "PueatProyectoConcesiones.findAll", query = "select o from PueatProyectoConcesiones o where o.pueatProyectos.id =:pueaaPryId")
})
@Table(name = "PUEAT_PROYECTO_CONCESIONES")
public class PueatProyectoConcesiones implements Serializable {
    @GenericGenerator(name = "pueaa_generator", 
                     strategy = "co.gov.ideam.sirh.usuarios.agua.model.IdeamSirhGenerator",
                     parameters = {@Parameter(name = "AutoridadAmbientalId", value = "codigoAutoridad"),
                                   @Parameter(name = "Codigo", value = "id"),
                                   @Parameter(name = SequenceGenerator.SEQUENCE,  value= "SEQ_PRY_CONCESION")})
    @Id
    @Column(name = "id",nullable = false)
    private Long id;
    @Transient
    private Long codigoAutoridad; 
    @Column(name="RURT_CONCESIONES_ID", nullable = false)
    private Long rurtConcesionesId;
    @Column(name="RURT_PREDIOS_ID", nullable = false)
    private Long rurtPrediosId;
    @ManyToOne
    @JoinColumn(name = "PUEAT_PROYECTOS_ID")
    private PueatProyectos pueatProyectos;

    public PueatProyectoConcesiones() {
    }

    public PueatProyectoConcesiones(Long id, PueatProyectos pueatProyectos,
                                    Long rurtConcesionesId,
                                    Long rurtPrediosId) {
        this.id = id;
        this.pueatProyectos = pueatProyectos;
        this.rurtConcesionesId = rurtConcesionesId;
        this.rurtPrediosId = rurtPrediosId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Long getRurtConcesionesId() {
        return rurtConcesionesId;
    }

    public void setRurtConcesionesId(Long rurtConcesionesId) {
        this.rurtConcesionesId = rurtConcesionesId;
    }

    public Long getRurtPrediosId() {
        return rurtPrediosId;
    }

    public void setRurtPrediosId(Long rurtPrediosId) {
        this.rurtPrediosId = rurtPrediosId;
    }

    public PueatProyectos getPueatProyectos() {
        return pueatProyectos;
    }

    public void setPueatProyectos(PueatProyectos pueatProyectos) {
        this.pueatProyectos = pueatProyectos;
    }

    public void setCodigoAutoridad(Long codigoAutoridad) {
        this.codigoAutoridad = codigoAutoridad;
    }

    public Long getCodigoAutoridad() {
        return codigoAutoridad;
    }
}
