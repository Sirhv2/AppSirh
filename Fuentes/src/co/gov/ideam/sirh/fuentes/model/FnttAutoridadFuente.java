package co.gov.ideam.sirh.fuentes.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries({
  @NamedQuery(name = "FnttAutoridadFuente.findAll", query = "select o from FnttAutoridadFuente o")
})
@Table(name = "fntt_autoridad_fuente")
@IdClass(FnttAutoridadFuentePK.class)
public class FnttAutoridadFuente implements Serializable {
    
    @Id
    @Column(name="id_autoridad", nullable = false)
    private Integer id_autoridad;
    
    @Id
    @Column(name="id_fuente", nullable = false)
    private Integer id_fuente;
    
    @ManyToOne
    @JoinColumn(name = "ID_FUENTE")
    private FnttFuente fnttFuente;

    public FnttAutoridadFuente() {
    }

    public FnttAutoridadFuente(Integer id_autoridad, Integer id_fuente) {
        this.id_autoridad = id_autoridad;
        this.id_fuente = id_fuente;
    }

    public Integer getId_autoridad() {
        return id_autoridad;
    }

    public void setId_autoridad(Integer id_autoridad) {
        this.id_autoridad = id_autoridad;
    }

    public Integer getId_fuente() {
        return id_fuente;
    }

    public void setId_fuente(Integer id_fuente) {
        this.id_fuente = id_fuente;
    }
    
    public FnttFuente getFnttFuente() {
        return fnttFuente;
    }

    public void setFnttFuente(FnttFuente fnttFuente) {
        this.fnttFuente = fnttFuente;
        if (fnttFuente != null) {
            this.id_fuente = fnttFuente.getId().intValue();
        }
    }
}
