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
@NamedQueries( { @NamedQuery(name = "PomtInstrumentosPlanificacion.findAll",
                             query =
                             "select o from PomtInstrumentosPlanificacion o")
        ,
        @NamedQuery(name = "PomtInstrumentosPlanificacion.findByPomtPlanes", query =
                    "SELECT o FROM PomtInstrumentosPlanificacion o WHERE o.pomtPlanes = :pomtPlanes")
        } )
@Table(name = "pomt_instrumenos_planif")
//@javax.persistence.SequenceGenerator(name = "project_id_seq_pomt_instrumenos_planif", sequenceName = "seq_pomt_instrumenos_planif")
public class PomtInstrumentosPlanificacion implements Serializable {
    @GenericGenerator(name = "pomca_generator",
                      strategy = "co.gov.ideam.sirh.usuarios.agua.model.IdeamSirhGenerator",
                      parameters =
                      { @Parameter(name = "AutoridadAmbientalId", value =
                                   "codigoAutoridad")
                , @Parameter(name = "Codigo", value = "id")
                ,
                @Parameter(name = SequenceGenerator.SEQUENCE, value = "seq_pomt_instrumenos_planif")
                } )
    //@GeneratedValue(generator = "pomca_generator")
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO, generator = "project_id_seq_pomt_instrumenos_planif")
    @Column(name = "id")
    private Long id;

    @Column(name = "recurso_natural_ident")
    private String recursoNaturalIdent;
    @Column(name = "instrumento_planificacion")
    private String instrumentoPlanificacion;
    @Transient
    private Long codigoAutoridad;


    @ManyToOne(optional = false)
    @JoinColumn(name = "id_plan", referencedColumnName = "id",
                nullable = false)
    private PomtPlanes pomtPlanes;


    public PomtInstrumentosPlanificacion() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public PomtPlanes getPomtPlanes() {
        return pomtPlanes;
    }

    public void setPomtPlanes(PomtPlanes pomtPlanes) {
        this.pomtPlanes = pomtPlanes;
    }

    public String getRecursoNaturalIdent() {
        return recursoNaturalIdent;
    }

    public void setRecursoNaturalIdent(String recursoNaturalIdent) {
        this.recursoNaturalIdent = recursoNaturalIdent;
    }

    public String getInstrumentoPlanificacion() {
        return instrumentoPlanificacion;
    }

    public void setInstrumentoPlanificacion(String instrumentoPlanificacion) {
        this.instrumentoPlanificacion = instrumentoPlanificacion;
    }

    public Long getCodigoAutoridad() {
        //return codigoAutoridad;
        return this.getPomtPlanes().getCodigoAutoridad();
    }

    public void setCodigoAutoridad(Long codigoAutoridad) {
        this.codigoAutoridad = codigoAutoridad;
    }
}
