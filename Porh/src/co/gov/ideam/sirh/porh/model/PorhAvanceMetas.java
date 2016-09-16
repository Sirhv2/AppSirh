package co.gov.ideam.sirh.porh.model;

import java.io.Serializable;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import javax.persistence.Transient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.id.SequenceGenerator;

@Entity
@NamedQueries({
  @NamedQuery(name = "PorhAvanceMetas.findAllByMeta", query = "select o from PorhAvanceMetas o where o.idMeta = :codigoMeta order by o.fecha_muestra ASC"),
  @NamedQuery(name = "PorhAvanceMetas.deleteByPlan", query = "delete from PorhAvanceMetas o where o.idMeta in (select m.id from PorhMetas m where m.idPlan = :codigoPlan)"),
  @NamedQuery(name = "PorhAvanceMetas.find", query = "select o from PorhAvanceMetas o where o.id = :codigo"),
  @NamedQuery(name = "PorhAvanceMetas.deleteByMeta", query = "delete from PorhAvanceMetas o where o.idMeta = :codigoMeta")
})
@Table(name = "porh_avance_metas")
public class PorhAvanceMetas implements Serializable {
    
    @GenericGenerator(name = "porh_generator", 
                      strategy = "co.gov.ideam.sirh.usuarios.agua.model.IdeamSirhGenerator",
                      parameters = {@Parameter(name = "AutoridadAmbientalId", value = "codigoAutoridad"),
                                    @Parameter(name = "Codigo", value = "id"),
                                    @Parameter(name = SequenceGenerator.SEQUENCE,  value= "seq_porh")})
    @Id
   // @GeneratedValue(generator = "porh_generator")            
    @Column(name="id", nullable = false)
    private Long id;        
    @Column(name="fecha_muestra", nullable = false)
    private Date fecha_muestra;
    @Column(name="fecha_registro", nullable = false)
    private Date fecha_registro;
    @Column(name="id_meta")
    private Long idMeta;
    @Column(name="valor", nullable = false)
    private Double valor;

    @Transient
    private Long codigoAutoridad;

    public PorhAvanceMetas() {
    }

    public PorhAvanceMetas(Date fecha_muestra, Date fecha_registro,
                           Long id, Long idMeta,
                           Double valor) {
        this.fecha_muestra = fecha_muestra;
        this.fecha_registro = fecha_registro;
        this.id = id;
        this.setIdMeta(idMeta);
        this.valor = valor;
    }

    public Date getFecha_muestra() {
        return fecha_muestra;
    }

    public void setFecha_muestra(Date fecha_muestra) {
        this.fecha_muestra = fecha_muestra;
    }

    public Date getFecha_registro() {
        return fecha_registro;
    }

    public void setFecha_registro(Date fecha_registro) {
        this.fecha_registro = fecha_registro;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Long getCodigoAutoridad() {
        return codigoAutoridad;
    }

    public void setCodigoAutoridad(Long codigoAutoridad) {
        this.codigoAutoridad = codigoAutoridad;
    }

    public Long getIdMeta() {
        return idMeta;
    }

    public void setIdMeta(Long idMeta) {
        this.idMeta = idMeta;
    }
}
