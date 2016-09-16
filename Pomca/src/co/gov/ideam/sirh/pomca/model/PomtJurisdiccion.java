package co.gov.ideam.sirh.pomca.model;

import co.gov.ideam.sirh.fuentes.model.FnttFuente;

import java.io.Serializable;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@NamedQueries( { @NamedQuery(name = "PomtJurisdiccion.findAll",
                             query = "select o from PomtJurisdiccion o")
        ,
        @NamedQuery(name = "PomtJurisdiccion.findByPomtPlanes", query = "SELECT o FROM PomtJurisdiccion o WHERE o.pomtPlanes = :pomtPlanes")
        } )
@Table(name = "pomt_jurisdiccion")
//@javax.persistence.SequenceGenerator(name = "project_id_seq_pomt_jurisdiccion",  sequenceName = "seq_pomt_jurisdiccion")
public class PomtJurisdiccion implements Serializable {
    @GenericGenerator(name = "pomca_generator",
                      strategy = "co.gov.ideam.sirh.usuarios.agua.model.IdeamSirhGenerator",
                      parameters =
                      { @Parameter(name = "AutoridadAmbientalId", value =
                                   "codigoAutoridad")
                , @Parameter(name = "Codigo", value = "id")
                ,
                @Parameter(name = SequenceGenerator.SEQUENCE, value = "seq_pomt_jurisdiccion")
                } )
    //@GeneratedValue(generator = "pomca_generator")
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO, generator = "project_id_seq_pomt_jurisdiccion")
    @Column(name = "id")
    private Long id;


    @Column(name = "id_departamento")
    private Long id_departamento;
    @Column(name = "id_municipio")
    private Long id_municipio;
    @Transient
    private Long codigoAutoridad;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_pomca", referencedColumnName = "id",
                nullable = false)
    private PomtPlanes pomtPlanes;

    public PomtJurisdiccion() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public PomtJurisdiccion(Long id_departamento, Long id_municipio,
                            PomtPlanes pomtPlanes) {
        this.id_departamento = id_departamento;
        this.id_municipio = id_municipio;
        this.pomtPlanes = pomtPlanes;
    }


    public Long getId_departamento() {
        return id_departamento;
    }

    public void setId_departamento(Long id_departamento) {
        this.id_departamento = id_departamento;
    }

    public Long getId_municipio() {
        return id_municipio;
    }

    public void setId_municipio(Long id_municipio) {
        this.id_municipio = id_municipio;
    }


    public PomtPlanes getPomtPlanes() {
        return pomtPlanes;
    }

    public void setPomtPlanes(PomtPlanes pomtPlanes) {
        this.pomtPlanes = pomtPlanes;
    }

    public Long getCodigoAutoridad() {
        //return codigoAutoridad;
        return this.getPomtPlanes().getCodigoAutoridad();
    }

    public void setCodigoAutoridad(Long codigoAutoridad) {
        this.codigoAutoridad = codigoAutoridad;
    }
}
