package co.gov.ideam.sirh.fuentes.model;

import co.gov.ideam.sirh.usuarios.agua.model.PuntoVertimientoTratamiento;

import java.io.Serializable;

import java.math.BigDecimal;

import java.sql.Timestamp;

import java.util.Calendar;
import java.util.List;

import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.id.SequenceGenerator;

@Entity
@Table(name = "fntt_conflicto")
@NamedQueries({
    @NamedQuery(name = "FnttConflicto.findAll", query = "SELECT c FROM FnttConflicto c"),
    @NamedQuery(name = "FnttConflicto.findById", query = "SELECT c FROM FnttConflicto c WHERE c.id = :id"),
    @NamedQuery(name = "FnttConflicto.findByFuente", query = "SELECT c FROM FnttConflicto c WHERE c.idFuente = :idFuente")
})

public class FnttConflicto implements Serializable {    
    
    @Id
    @Column(name = "ID", nullable = false)
    private Long id;
    
    @Column(name = "ID_FUENTE", nullable = false)
    private Long idFuente;
    
    @Column(name = "ID_TRAMO")
    private Long idTramo;
    
    @Column(name = "FECHA_REGISTRO")
    private Calendar fechaRegistro;

    @Column(name = "NOMBRE")
    private String nombre;
    
    @Column(name = "NUMERO_RADICADO")
    private String numeroRadicado;

    @Column(name = "DESCRIPCION")
    private String descripcion;

    @Column(name = "SISTEMA_REFERENCIA")
    private Long sistemaReferencia;

    @Column(name = "LONGITUD_GRADOS")
    private int longitudGrados;

    @Column(name = "LONGITUD_MINUTOS")
    private int longitudMinutos;

    @Column(name = "LONGITUD_SEGUNDOS")
    private BigDecimal longitudSegundos;

    @Column(name = "LATITUD_GRADOS")
    private int latitudGrados;

    @Column(name = "LATITUD_MINUTOS")
    private int latitudMinutos;

    @Column(name = "LATITUD_SEGUNDOS")
    private BigDecimal latitudSegundos;

    @Column(name = "ALTITUD")
    private BigDecimal altitud;

    @Column(name = "ID_DEPARTAMENTO")
    private Integer idDepartamento;

    @Column(name = "ID_MUNICIPIO")
    private Integer idMunicipio;

    @Column(name = "ESTADO_CONFLICTO")
    private Integer estadoConflicto;
    
    @Column(name = "COD_AUTORIDAD")
    private Integer codAutoridad;

    @Column(name = "FECHA_CIERRE")
    private Calendar fechaCierre;

    @Column(name = "TIPO_OTRO")
    private String tipoOtro;

    @Column(name = "ORIGEN_OTRO")
    private String origenOtro;

    @Column(name = "POBLACION_OTRO")
    private String poblacionOtro;

    @Column(name = "DERECHO_OTRO")
    private String derechoOtro;
    
    @Column(name = "LOGRO_OTRO")
    private String logroOtro;
    
    @Column(name = "ACT_ECONOMICA_OTRO")
    private String actividadEconomicaOtro;
        
    @ManyToMany(cascade=CascadeType.REFRESH)
      @JoinTable(
          name="FNTT_LOGROS_CONFLICTO",
          joinColumns={@JoinColumn(name="IDCONFLICTO", referencedColumnName="ID")},
          inverseJoinColumns={@JoinColumn(name="IDLOGRO", referencedColumnName="IDLOGRO")})           
    private Set<FnttLogros> fnttLogrosList;
    
    @ManyToMany
      @JoinTable(
          name="FNTT_ACTIVIDADES_CONFLICTO",
          joinColumns={@JoinColumn(name="IDCONFLICTO", referencedColumnName="ID")},
          inverseJoinColumns={@JoinColumn(name="IDACTIVIDAD", referencedColumnName="IDACTIVIDAD")})
    private Set<FnttActividadesEconomicas> fnttActividadesEconomicasList;

    @Transient
    private String nomEstado;

    @Transient
    private List<TipoConflicto> listaTipoConflicto;

    @Transient
    private List<OrigenConflicto> listaOrigenConflicto;

    @Transient
    private List<PoblacionConflicto> listaPoblacionConflicto;

    @Transient
    private List<ActorConflicto> listaActorConflicto;

    @Transient
    private List<DerechoConflicto> listaDerechoConflicto;

    @Transient
    private List<GestionConflicto> listaGestionConflicto;

    public FnttConflicto() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setIdFuente(Long idFuente) {
        this.idFuente = idFuente;
    }

    public Long getIdFuente() {
        return idFuente;
    }

    public void setIdTramo(Long idTramo) {
        this.idTramo = idTramo;
    }

    public Long getIdTramo() {
        return idTramo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setEstadoConflicto(Integer estadoConflicto) {
        this.estadoConflicto = estadoConflicto;
    }

    public Integer getEstadoConflicto() {
        return estadoConflicto;
    }

    public String getNomEstado() {
        
        String nombre = "Abierto";

        if (this.estadoConflicto == 1)
            nombre = "Cerrado";
        
        return nombre;
    }


    public void setFechaRegistro(Calendar fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Calendar getFechaRegistro() {
        return fechaRegistro;
    }

    public void setCodAutoridad(Integer codAutoridad) {
        this.codAutoridad = codAutoridad;
    }

    public Integer getCodAutoridad() {
        return codAutoridad;
    }

    public void setNumeroRadicado(String numeroRadicado) {
        this.numeroRadicado = numeroRadicado;
    }

    public String getNumeroRadicado() {
        return numeroRadicado;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setSistemaReferencia(Long sistemaReferencia) {
        this.sistemaReferencia = sistemaReferencia;
    }

    public Long getSistemaReferencia() {
        return sistemaReferencia;
    }

    public void setLongitudGrados(int longitudGrados) {
        this.longitudGrados = longitudGrados;
    }

    public int getLongitudGrados() {
        return longitudGrados;
    }

    public void setLongitudMinutos(int longitudMinutos) {
        this.longitudMinutos = longitudMinutos;
    }

    public int getLongitudMinutos() {
        return longitudMinutos;
    }

    public void setLongitudSegundos(BigDecimal longitudSegundos) {
        this.longitudSegundos = longitudSegundos;
    }

    public BigDecimal getLongitudSegundos() {
        return longitudSegundos;
    }

    public void setLatitudGrados(int latitudGrados) {
        this.latitudGrados = latitudGrados;
    }

    public int getLatitudGrados() {
        return latitudGrados;
    }

    public void setLatitudMinutos(int latitudMinutos) {
        this.latitudMinutos = latitudMinutos;
    }

    public int getLatitudMinutos() {
        return latitudMinutos;
    }

    public void setLatitudSegundos(BigDecimal latitudSegundos) {
        this.latitudSegundos = latitudSegundos;
    }

    public BigDecimal getLatitudSegundos() {
        return latitudSegundos;
    }

    public void setAltitud(BigDecimal altitud) {
        this.altitud = altitud;
    }

    public BigDecimal getAltitud() {
        return altitud;
    }

    public void setIdDepartamento(Integer idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public Integer getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdMunicipio(Integer idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public Integer getIdMunicipio() {
        return idMunicipio;
    }

    public void setFechaCierre(Calendar fechaCierre) {
        this.fechaCierre = fechaCierre;
    }

    public Calendar getFechaCierre() {
        return fechaCierre;
    }

    public void setNomEstado(String nomEstado) {
        this.nomEstado = nomEstado;
    }

    public String getNomEstado1() {
        return nomEstado;
    }

    public List<TipoConflicto> getListaTipoConflicto() {
        return listaTipoConflicto;
    }

    public void setListaTipoConflicto(List<TipoConflicto> listaTipoConflicto) {
        this.listaTipoConflicto = listaTipoConflicto;
    }

    public void setTipoOtro(String tipoOtro) {
        this.tipoOtro = tipoOtro;
    }

    public String getTipoOtro() {
        return tipoOtro;
    }

    public void setOrigenOtro(String origenOtro) {
        this.origenOtro = origenOtro;
    }

    public String getOrigenOtro() {
        return origenOtro;
    }

    public void setListaOrigenConflicto(List<OrigenConflicto> listaOrigenConflicto) {
        this.listaOrigenConflicto = listaOrigenConflicto;
    }

    public List<OrigenConflicto> getListaOrigenConflicto() {
        return listaOrigenConflicto;
    }

    public void setPoblacionOtro(String poblacionOtro) {
        this.poblacionOtro = poblacionOtro;
    }

    public String getPoblacionOtro() {
        return poblacionOtro;
    }

    public void setListaPoblacionConflicto(List<PoblacionConflicto> listaPoblacionConflicto) {
        this.listaPoblacionConflicto = listaPoblacionConflicto;
    }

    public List<PoblacionConflicto> getListaPoblacionConflicto() {
        return listaPoblacionConflicto;
    }

    public void setListaActorConflicto(List<ActorConflicto> listaActorConflicto) {
        this.listaActorConflicto = listaActorConflicto;
    }

    public List<ActorConflicto> getListaActorConflicto() {
        return listaActorConflicto;
    }

    public void setDerechoOtro(String derechoOtro) {
        this.derechoOtro = derechoOtro;
    }

    public String getDerechoOtro() {
        return derechoOtro;
    }

    public void setListaDerechoConflicto(List<DerechoConflicto> listaDerechoConflicto) {
        this.listaDerechoConflicto = listaDerechoConflicto;
    }

    public List<DerechoConflicto> getListaDerechoConflicto() {
        return listaDerechoConflicto;
    }

    public void setListaGestionConflicto(List<GestionConflicto> listaGestionConflicto) {
        this.listaGestionConflicto = listaGestionConflicto;
    }

    public List<GestionConflicto> getListaGestionConflicto() {
        return listaGestionConflicto;
    }

    public void setFnttLogrosList(Set<FnttLogros> fnttLogrosList) {
        this.fnttLogrosList = fnttLogrosList;
    }

    public Set<FnttLogros> getFnttLogrosList() {
        return fnttLogrosList;
    }

    public void setFnttActividadesEconomicasList(Set<FnttActividadesEconomicas> fnttActividadesEconomicasList) {
        this.fnttActividadesEconomicasList = fnttActividadesEconomicasList;
    }

    public Set<FnttActividadesEconomicas> getFnttActividadesEconomicasList() {
        return fnttActividadesEconomicasList;
    }

    public void setLogroOtro(String logroOtro) {
        this.logroOtro = logroOtro;
    }

    public String getLogroOtro() {
        return logroOtro;
    }

    public void setActividadEconomicaOtro(String actividadEconomicaOtro) {
        this.actividadEconomicaOtro = actividadEconomicaOtro;
    }

    public String getActividadEconomicaOtro() {
        return actividadEconomicaOtro;
    }
}
