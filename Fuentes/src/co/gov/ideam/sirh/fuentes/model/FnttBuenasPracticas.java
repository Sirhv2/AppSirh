package co.gov.ideam.sirh.fuentes.model;

import java.io.Serializable;

import java.sql.Timestamp;

import java.util.LinkedHashSet;
import java.util.List;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@NamedQueries({
  @NamedQuery(name = "FnttBuenasPracticas.findAll", query = "select o from FnttBuenasPracticas o"),
  @NamedQuery(name = "FnttBuenasPracticas.findByFuente", query = "SELECT b FROM FnttBuenasPracticas b WHERE b.fnttFuente.id = :idFuente")
})
@Table(name = "FNTT_BUENAS_PRACTICAS")
@SequenceGenerator(name = "SEQ_BUENAS_PRACTICAS", sequenceName = "SEQ_BUENAS_PRACTICAS", allocationSize = 1)
public class FnttBuenasPracticas implements Serializable {
    @Column(nullable = false)
    private String descripcion;
    @Column(nullable = false)
    private String dificultad;
    @Column(nullable = false)
    private String educativo;
    @Column(nullable = false)
    private Timestamp fechadiligenciamiento;
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_BUENAS_PRACTICAS")
    @Column(nullable = false)        
    private Long idbuenapraactica;
    private String otrosactor;
    private String otroseducativo;
    private String otrosestrategia;
    private String otrosexpresiones;
    private String otroslogros;
    private String otrosmotivacion;
    @Column(nullable = false)
    private String recomendacion;
    @ManyToOne(cascade=CascadeType.REFRESH)
    @JoinColumn(name = "IDEXPRESION")
    private FnttExpresionesCulturales fnttExpresionesCulturales;
    @ManyToOne(cascade=CascadeType.REFRESH)
    @JoinColumn(name = "IDESTRATEGIA")
    private FnttEstrategiasComunicacion fnttEstrategiasComunicacion;
    @ManyToOne(cascade=CascadeType.REFRESH)
    @JoinColumn(name = "IDFUENTE")
    private FnttFuente fnttFuente;
    
    @ManyToOne(cascade=CascadeType.REFRESH)
    @JoinColumn(name = "IDPROYECTO")
    private FnttProyectosEducativos fnttProyectosEducativos; 
    
    @ManyToMany(cascade=CascadeType.REFRESH)
      @JoinTable(
          name="FNTT_MOTIVACIONES_PRACTICAS",
          joinColumns={@JoinColumn(name="IDBUENAPRAACTICA", referencedColumnName="IDBUENAPRAACTICA")},
          inverseJoinColumns={@JoinColumn(name="IDMOTIVACION", referencedColumnName="IDMOTIVACION")})        
    private Set<FnttMotivaciones> fnttMotivacionesList;
    
    @ManyToMany
      @JoinTable(
          name="FNTT_PRINCIPIOS_PRACTICAS",
          joinColumns={@JoinColumn(name="IDBUENAPRAACTICA", referencedColumnName="IDBUENAPRAACTICA")},
          inverseJoinColumns={@JoinColumn(name="IDPRINCIPIO", referencedColumnName="IDPRINCIPIO")})        
    private Set<FnttPrincipios> fnttPrincipiosList;
    
    @ManyToMany(cascade=CascadeType.REFRESH)
      @JoinTable(
          name="FNTT_LOGROS_PRACTICAS",
          joinColumns={@JoinColumn(name="IDBUENAPRAACTICA", referencedColumnName="IDBUENAPRAACTICA")},
          inverseJoinColumns={@JoinColumn(name="IDLOGRO", referencedColumnName="IDLOGRO")})        
    private Set<FnttLogros> fnttLogrosList;
    
    @ManyToOne(cascade=CascadeType.REFRESH)
    @JoinColumn(name = "IDCOSTO")
    private FnttCostos fnttCostos;
    @ManyToOne(cascade=CascadeType.REFRESH)
    @JoinColumn(name = "IDACTOR")
    private FnttActores fnttActores;
    @ManyToOne(cascade=CascadeType.REFRESH)
    @JoinColumn(name = "IDCATEGORIA")
    private FnttCategorias fnttCategorias;
    
	//---
    private Double altitud;
    @Column(name="ID_DEPARTAMENTO")
    private Long idDepartamento;
    @Column(name="ID_MUNICIPIO")
    private Long idMunicipio;
    @Column(name="LATITUD_GRADOS")
    private Long latitudGrados;
    @Column(name="LATITUD_MINUTOS")
    private Long latitudMinutos;
    @Column(name="LATITUD_SEGUNDOS")
    private Double latitudSegundos;
    @Column(name="LONGITUD_GRADOS")
    private Long longitudGrados;
    @Column(name="LONGITUD_MINUTOS")
    private Long longitudMinutos;
    @Column(name="LONGITUD_SEGUNDOS")
    private Double longitudSegundos;
    @Column(name="SISTEMA_REFERENCIA")
    private Long sistemaReferencia;

    public FnttBuenasPracticas() {
    }

    public FnttBuenasPracticas(String descripcion, String dificultad,
                               String educativo,
                               Timestamp fechadiligenciamiento,
                               FnttActores fnttActores,
                               Long idbuenapraactica,
                               FnttCategorias fnttCategorias,
                               FnttCostos fnttCostos,
                               FnttEstrategiasComunicacion fnttEstrategiasComunicacion,
                               FnttExpresionesCulturales fnttExpresionesCulturales,
                               FnttFuente fnttFuente,
                               FnttProyectosEducativos fnttProyectosEducativos, String otrosactor,
                               String otroseducativo, String otrosestrategia,
                               String otrosexpresiones, String otroslogros,
                               String otrosmotivacion, String recomendacion) {
        this.descripcion = descripcion;
        this.dificultad = dificultad;
        this.educativo = educativo;
        this.fechadiligenciamiento = fechadiligenciamiento;
        this.fnttActores = fnttActores;
        this.idbuenapraactica = idbuenapraactica;
        this.fnttCategorias = fnttCategorias;
        this.fnttCostos = fnttCostos;
        this.fnttEstrategiasComunicacion = fnttEstrategiasComunicacion;
        this.fnttExpresionesCulturales = fnttExpresionesCulturales;
        this.fnttFuente = fnttFuente;
        this.fnttProyectosEducativos = fnttProyectosEducativos;
        this.otrosactor = otrosactor;
        this.otroseducativo = otroseducativo;
        this.otrosestrategia = otrosestrategia;
        this.otrosexpresiones = otrosexpresiones;
        this.otroslogros = otroslogros;
        this.otrosmotivacion = otrosmotivacion;
        this.recomendacion = recomendacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDificultad() {
        return dificultad;
    }

    public void setDificultad(String dificultad) {
        this.dificultad = dificultad;
    }

    public String getEducativo() {
        return educativo;
    }

    public void setEducativo(String educativo) {
        this.educativo = educativo;
    }

    public Timestamp getFechadiligenciamiento() {
        return fechadiligenciamiento;
    }

    public void setFechadiligenciamiento(Timestamp fechadiligenciamiento) {
        this.fechadiligenciamiento = fechadiligenciamiento;
    }


    public Long getIdbuenapraactica() {
        return idbuenapraactica;
    }

    public void setIdbuenapraactica(Long idbuenapraactica) {
        this.idbuenapraactica = idbuenapraactica;
    }


    public String getOtrosactor() {
        return otrosactor;
    }

    public void setOtrosactor(String otrosactor) {
        this.otrosactor = otrosactor;
    }

    public String getOtroseducativo() {
        return otroseducativo;
    }

    public void setOtroseducativo(String otroseducativo) {
        this.otroseducativo = otroseducativo;
    }

    public String getOtrosestrategia() {
        return otrosestrategia;
    }

    public void setOtrosestrategia(String otrosestrategia) {
        this.otrosestrategia = otrosestrategia;
    }

    public String getOtrosexpresiones() {
        return otrosexpresiones;
    }

    public void setOtrosexpresiones(String otrosexpresiones) {
        this.otrosexpresiones = otrosexpresiones;
    }

    public String getOtroslogros() {
        return otroslogros;
    }

    public void setOtroslogros(String otroslogros) {
        this.otroslogros = otroslogros;
    }

    public String getOtrosmotivacion() {
        return otrosmotivacion;
    }

    public void setOtrosmotivacion(String otrosmotivacion) {
        this.otrosmotivacion = otrosmotivacion;
    }

    public String getRecomendacion() {
        return recomendacion;
    }

    public void setRecomendacion(String recomendacion) {
        this.recomendacion = recomendacion;
    }

    public FnttExpresionesCulturales getFnttExpresionesCulturales() {
        return fnttExpresionesCulturales;
    }

    public void setFnttExpresionesCulturales(FnttExpresionesCulturales fnttExpresionesCulturales) {
        this.fnttExpresionesCulturales = fnttExpresionesCulturales;
    }

    public FnttEstrategiasComunicacion getFnttEstrategiasComunicacion() {
        return fnttEstrategiasComunicacion;
    }

    public void setFnttEstrategiasComunicacion(FnttEstrategiasComunicacion fnttEstrategiasComunicacion) {
        this.fnttEstrategiasComunicacion = fnttEstrategiasComunicacion;
    }

    public FnttFuente getFnttFuente() {
        return fnttFuente;
    }

    public void setFnttFuente(FnttFuente fnttFuente) {
        this.fnttFuente = fnttFuente;
    }

    public FnttProyectosEducativos getFnttProyectosEducativos() {
        return fnttProyectosEducativos;
    }

    public void setFnttProyectosEducativos(FnttProyectosEducativos fnttProyectosEducativos) {
        this.fnttProyectosEducativos = fnttProyectosEducativos;
    }

    public FnttCostos getFnttCostos() {
        return fnttCostos;
    }

    public void setFnttCostos(FnttCostos fnttCostos) {
        this.fnttCostos = fnttCostos;
    }

    public FnttActores getFnttActores() {
        return fnttActores;
    }

    public void setFnttActores(FnttActores fnttActores) {
        this.fnttActores = fnttActores;
    }

    public FnttCategorias getFnttCategorias() {
        return fnttCategorias;
    }

    public void setFnttCategorias(FnttCategorias fnttCategorias) {
        this.fnttCategorias = fnttCategorias;
    }

    public void setFnttLogrosList(Set<FnttLogros> fnttLogrosList) {
        this.fnttLogrosList = fnttLogrosList;
    }

    public Set<FnttLogros> getFnttLogrosList() {
        return fnttLogrosList;
    }

    public void setFnttPrincipiosList(Set<FnttPrincipios> fnttPrincipiosList) {
        this.fnttPrincipiosList = fnttPrincipiosList;
    }

    public Set<FnttPrincipios> getFnttPrincipiosList() {
        return fnttPrincipiosList;
    }

    public void setFnttMotivacionesList(Set<FnttMotivaciones> fnttMotivacionesList) {
        this.fnttMotivacionesList = fnttMotivacionesList;
    }

    public Set<FnttMotivaciones> getFnttMotivacionesList() {
        return fnttMotivacionesList;
    }

    public void setAltitud(Double altitud) {
        this.altitud = altitud;
    }

    public Double getAltitud() {
        return altitud;
    }

    public void setIdDepartamento(Long idDepartamento) {
        this.idDepartamento = idDepartamento;
    }

    public Long getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdMunicipio(Long idMunicipio) {
        this.idMunicipio = idMunicipio;
    }

    public Long getIdMunicipio() {
        return idMunicipio;
    }

    public void setLatitudGrados(Long latitudGrados) {
        this.latitudGrados = latitudGrados;
    }

    public Long getLatitudGrados() {
        return latitudGrados;
    }

    public void setLatitudMinutos(Long latitudMinutos) {
        this.latitudMinutos = latitudMinutos;
    }

    public Long getLatitudMinutos() {
        return latitudMinutos;
    }

    public void setLatitudSegundos(Double latitudSegundos) {
        this.latitudSegundos = latitudSegundos;
    }

    public Double getLatitudSegundos() {
        return latitudSegundos;
    }

    public void setLongitudGrados(Long longitudGrados) {
        this.longitudGrados = longitudGrados;
    }

    public Long getLongitudGrados() {
        return longitudGrados;
    }

    public void setLongitudMinutos(Long longitudMinutos) {
        this.longitudMinutos = longitudMinutos;
    }

    public Long getLongitudMinutos() {
        return longitudMinutos;
    }

    public void setLongitudSegundos(Double longitudSegundos) {
        this.longitudSegundos = longitudSegundos;
    }

    public Double getLongitudSegundos() {
        return longitudSegundos;
    }

    public void setSistemaReferencia(Long sistemaReferencia) {
        this.sistemaReferencia = sistemaReferencia;
    }

    public Long getSistemaReferencia() {
        return sistemaReferencia;
    }
}