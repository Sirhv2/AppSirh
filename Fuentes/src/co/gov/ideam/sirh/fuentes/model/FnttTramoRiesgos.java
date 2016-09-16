package co.gov.ideam.sirh.fuentes.model;


import co.gov.ideam.sirh.parametros.model.Lista;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "FNTT_TRAMO_RIESGOS")
@XmlRootElement
@NamedQueries( { 
@NamedQuery(name = "FnttTramoRiesgos.findAll", query = "SELECT ftr FROM FnttTramoRiesgos ftr")
    ,
    @NamedQuery(name = "FnttTramoRiesgos.findById", query = "SELECT ftr FROM FnttTramoRiesgos ftr WHERE ftr.id = :id")
    ,
    @NamedQuery(name = "FnttTramoRiesgos.findAllByTramo", query = "SELECT ftr FROM FnttTramoRiesgos ftr WHERE ftr.idTramo = :idTramo")
    } )
public class FnttTramoRiesgos implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @SequenceGenerator(name = "TramoRiesgos",
                     sequenceName = "SEQ_TRAMO_RIESGOS", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE,
                  generator = "TramoRiesgos")
    
   
  @Column(name = "ID", nullable = false)
  private Long id;
  @Column(name = "ID_TRAMO", nullable = false)
  private Long idTramo;
  @Column(name = "ID_FUENTE", nullable = false)
  private Long idFuente;
  @Column(name = "R_ASOC_REDUCCION_OFERTA", nullable = false)
  private Long rAsocReduccionOferta;
  @Column(name = "R_ASOC_DISPONIBILIDAD", nullable = false)
  private Long rAsocDisponibilidad;


  @Transient
  private FnttTramo tramo;
  @Transient
  private FnttFuente fuente;
  @Transient
  private Lista listarAsocReduccionOferta;
  @Transient
  private Lista listarAsocDisponibilidad;

  @Override
  public int hashCode() {
    int hash = 0;
    hash += (id != null ? id.hashCode() : 0);
    return hash;
  }

  @Override
  public boolean equals(Object object) {
    // TODO: Warning - this method won't work in the case the id fields are not set
    if (!(object instanceof FnttTramoRiesgos)) {
      return false;
    }
    FnttTramoRiesgos other = (FnttTramoRiesgos)object;
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

  public static long getSerialVersionUID() {
    return serialVersionUID;
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

  public void setRAsocReduccionOferta(Long rAsocReduccionOferta) {
    this.rAsocReduccionOferta = rAsocReduccionOferta;
  }

  public Long getRAsocReduccionOferta() {
    return rAsocReduccionOferta;
  }

  public void setRAsocDisponibilidad(Long rAsocDisponibilidad) {
    this.rAsocDisponibilidad = rAsocDisponibilidad;
  }

  public Long getRAsocDisponibilidad() {
    return rAsocDisponibilidad;
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

  public void setListarAsocReduccionOferta(Lista listarAsocReduccionOferta) {
    this.listarAsocReduccionOferta = listarAsocReduccionOferta;
  }

  public Lista getListarAsocReduccionOferta() {
    return listarAsocReduccionOferta;
  }

  public void setListarAsocDisponibilidad(Lista listarAsocDisponibilidad) {
    this.listarAsocDisponibilidad = listarAsocDisponibilidad;
  }

  public Lista getListarAsocDisponibilidad() {
    return listarAsocDisponibilidad;
  }
}
