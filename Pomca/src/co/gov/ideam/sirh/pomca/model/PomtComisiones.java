package co.gov.ideam.sirh.pomca.model;

import java.io.Serializable;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
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
@NamedQueries( { @NamedQuery(name = "PomtComisiones.findAll", query = "select o from PomtComisiones o")
               , @NamedQuery(name = "PomtComisiones.findByPomtPlanes", query = "SELECT o FROM PomtComisiones o WHERE o.pomtPlanes = :pomtPlanes")
        } )
@Table(name = "pomt_comisiones")
//@SequenceGenerator(name = "project_id_seq_pomt_comisiones",  sequenceName = "seq_pomt_comisiones")
public class PomtComisiones implements Serializable {
    @GenericGenerator(name = "pomca_generator",
                      strategy = "co.gov.ideam.sirh.usuarios.agua.model.IdeamSirhGenerator",
                      parameters =
                      { @Parameter(name = "AutoridadAmbientalId", value =
                                   "codigoAutoridad")
                , @Parameter(name = "Codigo", value = "id")
                ,
                @Parameter(name = SequenceGenerator.SEQUENCE, value = "seq_pomt_comisiones")
                } )
    //@GeneratedValue(generator = "pomca_generator")
    @Id
    // @GeneratedValue(strategy = GenerationType.AUTO,  generator = "project_id_seq_pomt_comisiones")
    @Column(name = "id")
    private Long id;


    @Column(name = "area")
    private Float area;
    @Column(name = "id_autoridad")
    private Integer id_autoridad;
    @Transient
    private Long codigoAutoridad;

    /**
     * De muchos a uno desde comisiones a pomt_plan
     * luego hace join con la "id_plan" que es mio hacia el "id" del pomt_planes (ambos campos de tabla
     * y la variable pomtPlanes debe llamarse igual en PomtPlanes
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_plan", referencedColumnName = "id", nullable = false)
    private PomtPlanes pomtPlanes;


    public PomtComisiones() {

    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Float getArea() {
        return area;
    }

    public void setArea(Float area) {
        this.area = area;
    }

    public Integer getId_autoridad() {
        return id_autoridad;
    }

    public void setId_autoridad(Integer id_autoridad) {
        this.id_autoridad = id_autoridad;
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
