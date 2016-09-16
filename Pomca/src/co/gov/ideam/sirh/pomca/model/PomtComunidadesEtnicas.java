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
@NamedQueries( { @NamedQuery(name = "PomtComunidadesEtnicas.findAll", query = "select o from PomtComunidadesEtnicas o")
        ,        @NamedQuery(name = "PomtComunidadesEtnicas.findByPomtPlanes", query = "SELECT o FROM PomtComunidadesEtnicas o WHERE o.pomtPlanes = :pomtPlanes")
        } )
@Table(name = "pomt_comunidades_etnicas")
//@javax.persistence.SequenceGenerator(name = "project_id_seq_pomt_comunidades_etnicas", sequenceName = "seq_pomt_comunidades_etnicas")
public class PomtComunidadesEtnicas implements Serializable {
    @GenericGenerator(name = "pomca_generator",
                      strategy = "co.gov.ideam.sirh.usuarios.agua.model.IdeamSirhGenerator",
                      parameters =  { @Parameter(name = "AutoridadAmbientalId", value =  "codigoAutoridad")
                                    , @Parameter(name = "Codigo", value = "id")
                                    , @Parameter(name = SequenceGenerator.SEQUENCE, value = "seq_pomt_comunidades_etnicas")
                                    } )
    //@GeneratedValue(generator = "pomca_generator")
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO, generator = "project_id_seq_pomt_comunidades_etnicas")
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre_comunidad")
    private String nombreComunidad;
    @Column(name = "proc_pre_consulta")
    private String procPreConsulta;
    @Column(name = "proc_consulta")
    private String procConsulta;
    @Transient
    private Long codigoAutoridad;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_plan", referencedColumnName = "id", nullable = false)
    private PomtPlanes pomtPlanes;


    public PomtComunidadesEtnicas() {
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

    public String getNombreComunidad() {
        return nombreComunidad;
    }

    public void setNombreComunidad(String nombreComunidad) {
        this.nombreComunidad = nombreComunidad;
    }

    public String getProcPreConsulta() {
        return procPreConsulta;
    }

    public void setProcPreConsulta(String procPreConsulta) {
        this.procPreConsulta = procPreConsulta;
    }

    public String getProcConsulta() {
        return procConsulta;
    }

    public void setProcConsulta(String procConsulta) {
        this.procConsulta = procConsulta;
    }

    public Long getCodigoAutoridad() {
        //return codigoAutoridad;
        return this.getPomtPlanes().getCodigoAutoridad();
    }

    public void setCodigoAutoridad(Long codigoAutoridad) {
        this.codigoAutoridad = codigoAutoridad;
    }
}
