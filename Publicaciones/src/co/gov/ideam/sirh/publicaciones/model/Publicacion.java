package co.gov.ideam.sirh.publicaciones.model;

import co.gov.ideam.sirh.parametros.model.Lista;

import java.io.Serializable;

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

@Entity
@Table(name = "pomt_publicaciones")
@NamedQueries( { @NamedQuery(name = "Publicacion.findAll", query = "SELECT p FROM Publicacion p")
             ,   @NamedQuery(name = "Publicacion.findById", query = "SELECT p FROM Publicacion p WHERE p.id = :id")
             ,   @NamedQuery(name = "Publicacion.findByNombre", query = "SELECT p FROM Publicacion p WHERE upper(p.nombrePublicacion) =:nombrePublicacion ")
             ,   @NamedQuery(name = "Publicacion.findByTipoRecurso", query = "SELECT p FROM Publicacion p WHERE upper(p.tipoRecurso) =:tipoRecurso ")
             ,   @NamedQuery(name = "Publicacion.findByAH", query = "SELECT p FROM Publicacion p WHERE upper(p.ah) LIKE :ah ")             
              ,   @NamedQuery(name = "Publicacion.findBySistemaAcuifero", query = "SELECT p FROM Publicacion p WHERE p.sistemaAcuifero LIKE :sistemaAcuifero " )
             
             
             } )

@SequenceGenerator(name = "project_id_seq_pomt_publicaciones", sequenceName = "seq_pomt_publicaciones")
public class Publicacion implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO, generator = "project_id_seq_pomt_publicaciones")
    @Column(name = "id")
    private Long id;

    @Column(name = "nombre_publicacion")
    private String nombrePublicacion;
    @Column(name = "fase_pomca")
    private String fasePomca;
    @Column(name = "autor")
    private String autor;    
    @Column(name="tipo_recurso")
    private Integer tipoRecurso;
    @Column(name = "editor")
    private String editor;
    @Column(name = "paginas")
    private Integer paginas;
    @Column(name = "lugar_publicacion")
    private String lugarPublicacion;
    @Column(name = "serie")
    private String serie;
    @Column(name = "volumen")
    private String volumen;
    @Column(name = "hipervinculo")
    private String hipervinculo;
    @Column(name = "anio")
    private Integer anio;
    @Column(name = "id_archivo")
    private Long idArchivo;
  @Column(name = "AH")
  private String ah;
  @Column(name = " PH")
  private String ph;
  @Column(name = "SAC")
  private String sac;
  @Column(name = "SISTEMA_ACUIFERO")
  private String sistemaAcuifero;
  
  @Column(name = "BIBLIOGRAFIA")
  private String bibliografia;
  @Column(name = "ANEXOS")
  private String anexos;
  @Column(name = "VERSION")
  private String version;
 
  
  

 

    
    
    @Transient
    private Lista lTipoRecurso;

    public Publicacion() {
    }

    public Publicacion(Long id) {
        this.setId(id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombrePublicacion() {
        return nombrePublicacion;
    }

    public void setNombrePublicacion(String nombrePublicacion) {
        this.nombrePublicacion = nombrePublicacion;
    }

    public String getFasePomca() {
        return fasePomca;
    }

    public void setFasePomca(String fasePomca) {
        this.fasePomca = fasePomca;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }


    public String getEditor() {
        return editor;
    }

    public void setEditor(String editor) {
        this.editor = editor;
    }

    public Integer getPaginas() {
        return paginas;
    }

    public void setPaginas(Integer paginas) {
        this.paginas = paginas;
    }

    public String getLugarPublicacion() {
        return lugarPublicacion;
    }

    public void setLugarPublicacion(String lugarPublicacion) {
        this.lugarPublicacion = lugarPublicacion;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getVolumen() {
        return volumen;
    }

    public void setVolumen(String volumen) {
        this.volumen = volumen;
    }

    public String getHipervinculo() {
        return hipervinculo;
    }

    public void setHipervinculo(String hipervinculo) {
        this.hipervinculo = hipervinculo;
    }

    public Integer getAnio() {
        return anio;
    }

    public void setAnio(Integer anio) {
        this.anio = anio;
    }

    public Long getIdArchivo() {
        return idArchivo;
    }

    public void setIdArchivo(Long idArchivo) {
        this.idArchivo = idArchivo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Publicacion)) {
            return false;
        }
        Publicacion other = (Publicacion)object;
        if ((this.getId() == null && other.getId() != null) ||
            (this.getId() != null && !this.getId().equals(other.getId()))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.gov.ideam.sirh.pomca.model.PomtPublicacion[ id=" + getId() +
            " ]";
    }

    public void setTipoRecurso(Integer tipoRecurso) {
        this.tipoRecurso = tipoRecurso;
    }

    public Integer getTipoRecurso() {
        return tipoRecurso;
    }

    public void setLTipoRecurso(Lista lTipoRecurso) {
        this.lTipoRecurso = lTipoRecurso;
    }

    public Lista getLTipoRecurso() {
        return lTipoRecurso;
    }


  public static long getSerialVersionUID() {
    return serialVersionUID;
  }

  public void setAh(String ah) {
    this.ah = ah;
  }

  public String getAh() {
    return ah;
  }

  public void setPh(String ph) {
    this.ph = ph;
  }

  public String getPh() {
    return ph;
  }

  public void setSac(String sac) {
    this.sac = sac;
  }

  public String getSac() {
    return sac;
  }

  public void setSistemaAcuifero(String sistemaAcuifero) {
    this.sistemaAcuifero = sistemaAcuifero;
  }

  public String getSistemaAcuifero() {
    return sistemaAcuifero;
  }

  public void setBibliografia(String bibliografia) {
    this.bibliografia = bibliografia;
  }

  public String getBibliografia() {
    return bibliografia;
  }

  public void setAnexos(String anexos) {
    this.anexos = anexos;
  }

  public String getAnexos() {
    return anexos;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public String getVersion() {
    return version;
  }
  
  
}
