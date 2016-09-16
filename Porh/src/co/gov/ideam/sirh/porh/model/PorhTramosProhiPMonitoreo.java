package co.gov.ideam.sirh.porh.model;


import co.gov.ideam.sirh.calidad.model.PuntoMonitoreo;

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
@Table(name = "PORH_TRAMOS_PROHI_P_MONITOREO")
@XmlRootElement
@NamedQueries( { @NamedQuery(name = "PorhTramosProhiPMonitoreo.findAll",
                             query = "SELECT ptppm FROM PorhTramosProhiPMonitoreo ptppm")
    ,
    @NamedQuery(name = "PorhTramosProhiPMonitoreo.findById", query = "SELECT ptppm FROM PorhTramosProhiPMonitoreo ptppm WHERE ptppm.id = :id")
    ,
    @NamedQuery(name = "PorhTramosProhiPMonitoreo.findAllByTramoProhibiciones", query = "SELECT ptppm FROM PorhTramosProhiPMonitoreo ptppm WHERE ptppm.idTramosProhibiciones = :idTramosProhibiciones")
    } )
public class PorhTramosProhiPMonitoreo implements Serializable {
  private static final long serialVersionUID = 1L;
  @Id
  @SequenceGenerator(name = "TramoProhibicionPMon",
                     sequenceName = "SEQ_PORH_TRAMOS_PROHI_P_MON", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE,
                  generator = "TramoProhibicionPMon")
  @Column(name = "ID", nullable = false)
  private Long id;
  @Column(name = "ID_PORH_TRAMOS_PROHIBICIONES", nullable = false)
  private Long idTramosProhibiciones;
  @Column(name = "ID_CALT_PUNTO_MONITOREO", nullable = false)
  private Long idPuntosMonitoreo;
  @Transient
  private PorhTramosProhibiciones tramosProhibiciones;
  @Transient
  private PuntoMonitoreo puntosMonitoreo;

  public PorhTramosProhiPMonitoreo() {
  }

  public PorhTramosProhiPMonitoreo(Long id) {
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
    if (!(object instanceof PorhTramosProhiPMonitoreo)) {
      return false;
    }
    PorhTramosProhiPMonitoreo other = (PorhTramosProhiPMonitoreo)object;
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


  public void setIdTramosProhibiciones(Long idTramosProhibiciones) {
    this.idTramosProhibiciones = idTramosProhibiciones;
  }

  public Long getIdTramosProhibiciones() {
    return idTramosProhibiciones;
  }

  public void setIdPuntosMonitoreo(Long idPuntosMonitoreo) {
    this.idPuntosMonitoreo = idPuntosMonitoreo;
  }

  public Long getIdPuntosMonitoreo() {
    return idPuntosMonitoreo;
  }

  public void setTramosProhibiciones(PorhTramosProhibiciones tramosProhibiciones) {
    this.tramosProhibiciones = tramosProhibiciones;
  }

  public PorhTramosProhibiciones getTramosProhibiciones() {
    return tramosProhibiciones;
  }

  public void setPuntosMonitoreo(PuntoMonitoreo puntosMonitoreo) {
    this.puntosMonitoreo = puntosMonitoreo;
  }

  public PuntoMonitoreo getPuntosMonitoreo() {
    return puntosMonitoreo;
  }
}
