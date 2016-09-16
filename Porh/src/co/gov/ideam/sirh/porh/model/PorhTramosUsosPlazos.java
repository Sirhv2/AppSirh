package co.gov.ideam.sirh.porh.model;

import java.io.Serializable;

import java.sql.Timestamp;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
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
  @NamedQuery(name = "PorhTramosUsosPlazos.findAllByUsoTramo", query = "select o from PorhTramosUsosPlazos o where o.porhTramosUsos.id = :codigoUsoTramo"), 
  @NamedQuery(name = "PorhTramosUsosPlazos.deleteByPlan", query = "delete from PorhTramosUsosPlazos o where o.porhTramosUsos.id in (select u.id from PorhTramosUsos u where u.porh_id= :codigoPlan)") ,
  @NamedQuery(name = "PorhTramosUsosPlazos.deleteByUso", query = "delete from PorhTramosUsosPlazos o where o.porhTramosUsos.id = :codigoUso") 
})
@Table(name = "porh_tramos_usos_plazos")
public class PorhTramosUsosPlazos implements Serializable {
    @Column(name="fecha", nullable = false)
    private Date fecha;
    
    @GenericGenerator(name = "porh_generator", 
                      strategy = "co.gov.ideam.sirh.usuarios.agua.model.IdeamSirhGenerator",
                      parameters = {@Parameter(name = "AutoridadAmbientalId", value = "codigoAutoridad"),
                                    @Parameter(name = "Codigo", value = "id"),
                                    @Parameter(name = SequenceGenerator.SEQUENCE,  value= "seq_porh")})    
    @Id
    //@GeneratedValue(generator = "porh_generator")        
    private Long id;
    @Column(name="objetivo", nullable = false)
    private String objetivo;
    @OneToMany(mappedBy = "porhTramosUsosPlazos")
    private List<PorhTramosUsosObjetivo> porhTramosUsosObjetivoList;
    @ManyToOne
    @JoinColumn(name = "uso_id")
    private PorhTramosUsos porhTramosUsos;
    @Transient
    private Long codigoAutoridad;
    public PorhTramosUsosPlazos() {
    }

    public PorhTramosUsosPlazos(Timestamp fecha, Long id, String objetivo,
                                PorhTramosUsos porhTramosUsos) {
        this.fecha = fecha;
        this.id = id;
        this.objetivo = objetivo;
        this.porhTramosUsos = porhTramosUsos;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getObjetivo() {
        return objetivo;
    }

    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }


    public List<PorhTramosUsosObjetivo> getPorhTramosUsosObjetivoList() {
        return porhTramosUsosObjetivoList;
    }

    public void setPorhTramosUsosObjetivoList(List<PorhTramosUsosObjetivo> porhTramosUsosObjetivoList) {
        this.porhTramosUsosObjetivoList = porhTramosUsosObjetivoList;
    }

    public PorhTramosUsosObjetivo addPorhTramosUsosObjetivo(PorhTramosUsosObjetivo porhTramosUsosObjetivo) {
        getPorhTramosUsosObjetivoList().add(porhTramosUsosObjetivo);
        porhTramosUsosObjetivo.setPorhTramosUsosPlazos(this);
        return porhTramosUsosObjetivo;
    }

    public PorhTramosUsosObjetivo removePorhTramosUsosObjetivo(PorhTramosUsosObjetivo porhTramosUsosObjetivo) {
        getPorhTramosUsosObjetivoList().remove(porhTramosUsosObjetivo);
        porhTramosUsosObjetivo.setPorhTramosUsosPlazos(null);
        return porhTramosUsosObjetivo;
    }

    public PorhTramosUsos getPorhTramosUsos() {
        return porhTramosUsos;
    }

    public void setPorhTramosUsos(PorhTramosUsos porhTramosUsos) {
        this.porhTramosUsos = porhTramosUsos;
    }

    public Long getCodigoAutoridad() {
        return codigoAutoridad;
    }

    public void setCodigoAutoridad(Long codigoAutoridad) {
        this.codigoAutoridad = codigoAutoridad;
    }
    
    public boolean equals(Object obj){
        return obj != null &&
            obj instanceof PorhTramosUsosPlazos &&
            ((PorhTramosUsosPlazos)obj).getId() != null &&
            this.getId() != null &&
            ((PorhTramosUsosPlazos)obj).getId().longValue() == 
            this.getId().longValue();
    }
}
