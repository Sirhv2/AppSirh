package co.gov.ideam.sirh.pomca.model;

import co.gov.ideam.sirh.parametros.model.Lista;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@NamedQueries({
              @NamedQuery(name = "DeterminanteAmbiental.findAll", query = "select o from DeterminanteAmbiental o")
      ,       @NamedQuery(name = "DeterminanteAmbiental.findByPomtPlanes", query = "SELECT o FROM DeterminanteAmbiental o WHERE o.pomtPlanes = :pomtPlanes")
              
})
@Table(name = "POMT_DETERMINANTE_AMBIENTAL")
public class DeterminanteAmbiental implements Serializable {
  @GenericGenerator(name = "pomca_generator",
                    strategy = "co.gov.ideam.sirh.usuarios.agua.model.IdeamSirhGenerator",
                    parameters =  { @Parameter(name = "AutoridadAmbientalId", value =  "codigoAutoridad")
                                  , @Parameter(name = "Codigo", value = "id")
                                  , @Parameter(name = SequenceGenerator.SEQUENCE, value = "detamb_seq")
                                  } )
    
  @Id
  @Column(name="ID", nullable= false)
  private Long id;
  @Column(name="NOMBRE")
  private String nombre;
  @Column(name="INDICADORDA_ID")
  private Long indicadordaId;  
  @Column(name="COMPONENTE_AFECTADO_ID")
  private Long componenteAfectadoId;  
  @Column(name="RECURSO_AFECTADO_ID")
  private Long recursoAfectadoId; 
  @Column(name="DESCRIPCION")
  private String descripcion;  
  @Column(name="ARCHIVO")
  private Long archivo;  
  @Transient
  private Long codigoAutoridad;  
  @ManyToOne(optional = false)
  @JoinColumn(name = "id_plan", referencedColumnName = "id", nullable = false)
  private PomtPlanes pomtPlanes;
  
  @Transient
  private Lista lComponentes;
  @Transient
  private Lista lRecursos;

  
  public DeterminanteAmbiental() {
  }

  public DeterminanteAmbiental(String nombre, String descripcion) {
    this.nombre = nombre;
    this.descripcion = descripcion;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }
  
  public String getDescripcion() {
    return descripcion;
  }

  public void setDescripcion(String descripcion) {
    this.descripcion = descripcion;
  }
  
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
  
  public Long getCodigoAutoridad() {
    return codigoAutoridad;
  }

  public void setCodigoAutoridad(Long codigoAutoridad) {
    this.codigoAutoridad= codigoAutoridad;
  }
  public PomtPlanes getPomtPlanes() {
      return pomtPlanes;
  }

  public void setPomtPlanes(PomtPlanes pomtPlanes) {
      this.pomtPlanes = pomtPlanes;
  }

  public void setIndicadordaId(Long indicadordaId) {
    this.indicadordaId = indicadordaId;
  }

  public Long getIndicadordaId() {
    return indicadordaId;
  }

  public void setComponenteAfectadoId(Long componenteAfectadoId) {
    this.componenteAfectadoId = componenteAfectadoId;
  }

  public Long getComponenteAfectadoId() {
    return componenteAfectadoId;
  }

  public void setRecursoAfectadoId(Long recursoAfectadoId) {
    this.recursoAfectadoId = recursoAfectadoId;
  }

  public Long getRecursoAfectadoId() {
    return recursoAfectadoId;
  }

  public void setArchivo(Long archivo) {
    this.archivo = archivo;
  }

  public Long getArchivo() {
    return archivo;
  }

//ZSDG
  public void setLComponentes(Lista lComponentes) {
    this.componenteAfectadoId = Long.valueOf(lComponentes.getCodigo());
    this.lComponentes = lComponentes;
  }

  public Lista getLComponentes() {
    return lComponentes;
  }
  
  public void setLRecursos(Lista lRecursos) {
    this.recursoAfectadoId = Long.valueOf(lRecursos.getCodigo());
    this.lRecursos = lRecursos;
  }

  public Lista getLRecursos() {
    return lRecursos;
  }
  
  //ZSDG
}
