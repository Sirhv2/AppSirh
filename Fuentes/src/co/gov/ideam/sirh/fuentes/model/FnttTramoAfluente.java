package co.gov.ideam.sirh.fuentes.model;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Carlos Ferro
 */
@Entity
@Table(name = "FNTT_TRAMO_AFLUENTES")
@XmlRootElement
@NamedQueries( { 
@NamedQuery(name = "FnttTramoAfluente.findAll", query = "SELECT fta FROM FnttTramoAfluente fta")
    ,
    @NamedQuery(name = "FnttTramoAfluente.findById", query = "SELECT fta FROM FnttTramoAfluente fta WHERE fta.id = :id")
    ,
    @NamedQuery(name = "FnttTramoAfluente.findAllByTramo", query = "SELECT fta FROM FnttTramoAfluente fta WHERE fta.idTramo = :idTramo")
    } )
public class FnttTramoAfluente implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @SequenceGenerator(name = "TramoAfluente",
                     sequenceName = "SEQ_TRAMO_AFLUENTE", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE,
                  generator = "TramoAfluente")
  @Column(name = "ID", nullable = false)
  private Long id;
  @Column(name = "ID_TRAMO", nullable = false)
  private Long idTramo;
  @Column(name = "ID_FUENTE", nullable = false)
  private Long idFuente;
  @Transient
  private FnttTramo tramo;
  @Transient
  private FnttFuente fuente;

  public FnttTramoAfluente() {
  }

  public FnttTramoAfluente(Long id) {
    this.id = id;
  }

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (id != null ? id.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof FnttTramoAfluente)) {
      return false;
    }
    FnttTramoAfluente other = (FnttTramoAfluente)object;
    if ((this.id == null && other.id != null) ||
        (this.id != null && !this.id.equals(other.id))) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "co.gov.ideam.sirh.fuentes.model.FnttTramoAfluente[ id=" + id +
      " ]";
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Long getId() {
    return id;
  }

  public void setIdTramo(Long idTramo) {
    this.idTramo = idTramo;
  }

  public Long getIdTramo() {
    return idTramo;
  }

  public void setIdFuente(Long idFuente) {
    this.idFuente = idFuente;
  }

  public Long getIdFuente() {
    return idFuente;
  }

  public void setTramo(FnttTramo tramo) {
    this.tramo = tramo;
  }

  public FnttTramo getTramo() {
    return tramo;
  }

  public void setFuente(FnttFuente fuente) {
    this.fuente = fuente;
  }

  public FnttFuente getFuente() {
    return fuente;
  }
}
