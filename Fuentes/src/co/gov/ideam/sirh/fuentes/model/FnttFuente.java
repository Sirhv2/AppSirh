package co.gov.ideam.sirh.fuentes.model;

import co.gov.ideam.sirh.parametros.model.Autoridades;

import co.gov.ideam.sirh.parametros.model.Lista;
import co.gov.ideam.sirh.parametros.model.PartZonificListas;


import co.gov.ideam.sirh.porh.model.PorhPlanes;
import co.gov.ideam.sirh.util.Constantes;

import co.gov.ideam.sirh.util.ConstantesParametros;

import java.io.Serializable;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.id.SequenceGenerator;

@Entity
@NamedQueries({
    @NamedQuery(name = "FnttFuente.findAll", query = "SELECT f FROM FnttFuente f"),
    @NamedQuery(name = "FnttFuente.findByNombre", query = "SELECT f FROM FnttFuente f WHERE f.nombre = :nombre"),
    @NamedQuery(name = "FnttFuente.findByDescripcion", query = "SELECT f FROM FnttFuente f WHERE f.descripcion = :descripcion"),
    @NamedQuery(name = "FnttFuente.findByEsCompartida", query = "SELECT f FROM FnttFuente f WHERE f.esCompartida = :esCompartida"),
    @NamedQuery(name = "FnttFuente.findById", query = "SELECT f FROM FnttFuente f WHERE f.id = :id"),
    @NamedQuery(name = "FnttFuente.findByAutoridad", query = "SELECT f FROM FnttFuente f WHERE f.codAutoridad.id = :id"),
    @NamedQuery(name = "FnttFuente.findByNombreAndAutoridad", 
                query = "SELECT f FROM FnttFuente f WHERE  upper(f.nombre) = :nombre AND f.codAutoridad.id = :idAutoridad"),
    @NamedQuery(name = "FnttFuente.findByNombreTipoAutoridad", 
                query = "SELECT f FROM FnttFuente f WHERE  upper(f.nombre) = :nombre AND f.codAutoridad.id = :idAutoridad AND f.idTipoFuente.codigo = :idTipo" ),
    @NamedQuery(name = "FnttFuente.findByTipoAutoridad", 
                query = "SELECT f FROM FnttFuente f WHERE f.codAutoridad.id = :idAutoridad AND f.idTipoFuente.codigo = :idTipo" )
})

@Table(name = "fntt_fuente")
public class FnttFuente implements Serializable {    
    @Column(name = "nombre", nullable = false)
    private String nombre;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "es_compartida")
    private Integer esCompartida;
    @Column(name = "codigo_cuencaaa")
    private String codigoCuencaAA;
    
    @JoinColumn(name = "id_provinciahidro", referencedColumnName = "id")
    @ManyToOne(optional = true)
    private Lista id_provinciahidro;
   
    @Column(name = "unidadhidro")
    private String unidadhidro;
    
    @Column(name = "fuente_car")
    private String fuente_car;
    
    
    
    
    @Id
    @Column(name = "id", nullable = false)
   // @GeneratedValue(generator = "fuentes_generator")        
    private Long id;
    
    @OneToMany(fetch = FetchType.LAZY, 
               cascade = CascadeType.ALL, mappedBy = "fnttFuente")
    private List<FnttFuenteTramoMunicipio> fnttFuenteTramoMunicipioList;
    
    @JoinColumn(name = "id_tipo_fuente", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Lista idTipoFuente;
    
    @JoinColumn(name = "cod_autoridad", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Autoridades codAutoridad;
    
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "idFuente")
    private List<FnttTramo> fnttTramoList;
    
    //
    @OneToMany(mappedBy = "fnttFuente")
    private List<FnttBuenasPracticas> fnttBuenasPracticasList;
    @OneToMany(mappedBy = "fnttFuente")
    private List<FnttAutoridadFuente> fnttAutoridadFuenteList;
    
    @Transient
    private Long codigoAutoridad;
    @Transient
    private Boolean tramosRelacionados;
    @Transient
    private Boolean esCompartidaAux;
    
    @Transient
    private boolean esSubterranea;
    
    @Transient
    private Integer num;
    
    
    
    @Transient
    private String nombretipoFuente;
    @Transient
    private String provinciaH;
    @Transient
    private String siglaAutoridad;
    /*
    @OneToMany(mappedBy = "idFuente")
    private List<RurtCaptacion> rurtCaptacionList;
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "idFuente")
    private List<RurtPuntoVertimiento> rurtPuntoVertimientoList;
    */
    @Transient
    private PorhPlanes porh;

    public FnttFuente() {
    }

    public FnttFuente(Long id) {
        this.id = id;
    }

    public FnttFuente(Long id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
    
    public FnttFuente(String descripcion,
                      Integer es_compartida, Long id,
                      String nombre, String codigoCuencaAA) {
        this.descripcion = descripcion;
        this.esCompartida = es_compartida;
        this.id = id;
        this.nombre = nombre;
        this.codigoCuencaAA=codigoCuencaAA;
    }
    
    //atributo transient
    public Long getCodigoAutoridad() {
        if(codAutoridad!=null){
            codigoAutoridad = codAutoridad.getId().longValue();
        }
        return codigoAutoridad;
    }

    public void setCodigoAutoridad(Long codigoAutoridad) {
        this.codigoAutoridad = codigoAutoridad;
    }
    //


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

    public Integer getEsCompartida() {
        return esCompartida;
    }

    public void setEsCompartida(Integer esCompartida) {
        this.esCompartida = esCompartida;
    }

    public Long getId() {
        System.out.println("getId(Long id):"+id);
        
        return id;
    }

    public void setId(Long id) {
        
      
        this.id = id;
    }

    public void setEsCompartidaAux(Boolean esCompartidaAux) {
        this.esCompartidaAux = esCompartidaAux;
        if(esCompartidaAux==true){
            this.esCompartida = Constantes.TRUE_TO_INTEGER;
        }else{
            this.esCompartida = Constantes.FALSE_TO_INTEGER;
        }
    }

    public Boolean getEsCompartidaAux() {
        return ((this.esCompartida.intValue() == Constantes.TRUE_TO_INTEGER)?true : false);
    }
    
    public List<FnttFuenteTramoMunicipio> getFnttFuenteTramoMunicipioList() {
        return fnttFuenteTramoMunicipioList;
    }

    public void setFntFuenteTramoMunicipioList(List<FnttFuenteTramoMunicipio> fnttFuenteTramoMunicipioList) {
        this.fnttFuenteTramoMunicipioList = fnttFuenteTramoMunicipioList;
    }

    public Lista getIdTipoFuente() {
        return idTipoFuente;
    }

    public void setIdTipoFuente(Lista idTipoFuente) {
        this.idTipoFuente = idTipoFuente;
    }

    public Autoridades getCodAutoridad() {
        return codAutoridad;
    }

    public void setCodAutoridad(Autoridades codAutoridad) {
        this.codAutoridad = codAutoridad;
    }

    public List<FnttTramo> getFnttTramoList() {
        return fnttTramoList;
    }

    public void setFnttTramoList(List<FnttTramo> fnttTramoList) {
        this.fnttTramoList = fnttTramoList;
    }
    
    public void setTramosRelacionados(Boolean tramosRelacionados) {
        this.tramosRelacionados = tramosRelacionados;
    }

    public Boolean getTramosRelacionados() {
        
        if(this.fnttTramoList!=null && !this.fnttTramoList.isEmpty()){
            tramosRelacionados = true;
        }else{
            tramosRelacionados = false;
        }
            
        return tramosRelacionados;
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
        if (!(object instanceof FnttFuente)) {
            return false;
        }
        FnttFuente other = (FnttFuente) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "co.gov.ideam.sirh.fuentes.model.FnttFuente[ id=" + id + " ]";
    }

    
    public boolean getTienePorh(){
        return this.porh != null;
    }

    public PorhPlanes getPorh() {
        return porh;
    }

    public void setPorh(PorhPlanes porh) {
        this.porh = porh;
    }

    public void setCodigoCuencaAA(String codigoCuencaAA) {
        this.codigoCuencaAA = codigoCuencaAA;
    }

    public String getCodigoCuencaAA() {
        return codigoCuencaAA;
    }
    
    public void setEsSubterranea(boolean esSubterranea) {
        this.esSubterranea = esSubterranea;
    }

    public boolean isEsSubterranea() {
        if(getIdTipoFuente().getCodigo().longValue() == 
                ConstantesParametros.LISTA_FUENTE_SUBTERRANEA){
            esSubterranea = true;
        }else{
            esSubterranea = false;
        }
        return esSubterranea;
    }

    public Boolean getEsCompartidaAux1() {
        return esCompartidaAux;
    }


    public void setNum(Integer num) {
        this.num = num;
    }

    public Integer getNum() {
        return num;
    }

    public void setId_provinciahidro(Lista id_provinciahidro) {
        this.id_provinciahidro = id_provinciahidro;
    }

    public Lista getId_provinciahidro() {
        return id_provinciahidro;
    }

    public void setUnidadhidro(String unidadhidro) {
        this.unidadhidro = unidadhidro;
    }

    public String getUnidadhidro() {
        return unidadhidro;
    }


    public void setNombretipoFuente(String nombretipoFuente) {
        this.nombretipoFuente = nombretipoFuente;
    }

    public String getNombretipoFuente() {
        return nombretipoFuente;
    }

    public void setProvinciaH(String provinciaH) {
        this.provinciaH = provinciaH;
    }

    public String getProvinciaH() {
        return provinciaH;
    }

    public void setSiglaAutoridad(String siglaAutoridad) {
        this.siglaAutoridad = siglaAutoridad;
    }

    public String getSiglaAutoridad() {
        return siglaAutoridad;
    }
    
    //
    public List<FnttBuenasPracticas> getFnttBuenasPracticasList() {
        return fnttBuenasPracticasList;
    }

    public void setFnttBuenasPracticasList(List<FnttBuenasPracticas> fnttBuenasPracticasList) {
        this.fnttBuenasPracticasList = fnttBuenasPracticasList;
    }

    public FnttBuenasPracticas addFnttBuenasPracticas(FnttBuenasPracticas fnttBuenasPracticas) {
        getFnttBuenasPracticasList().add(fnttBuenasPracticas);
        fnttBuenasPracticas.setFnttFuente(this);
        return fnttBuenasPracticas;
    }

    public FnttBuenasPracticas removeFnttBuenasPracticas(FnttBuenasPracticas fnttBuenasPracticas) {
        getFnttBuenasPracticasList().remove(fnttBuenasPracticas);
        fnttBuenasPracticas.setFnttFuente(null);
        return fnttBuenasPracticas;
    }

    public void setFuente_car(String fuente_car) {
        this.fuente_car = fuente_car;
    }
	
    public List<FnttAutoridadFuente> getFnttAutoridadFuenteList() {
        return fnttAutoridadFuenteList;
    }

    public void setFnttAutoridadFuenteList(List<FnttAutoridadFuente> fnttAutoridadFuenteList) {
        this.fnttAutoridadFuenteList = fnttAutoridadFuenteList;
    }

    public FnttAutoridadFuente addFnttAutoridadFuente(FnttAutoridadFuente fnttAutoridadFuente) {
        getFnttAutoridadFuenteList().add(fnttAutoridadFuente);
        fnttAutoridadFuente.setFnttFuente(this);
        return fnttAutoridadFuente;
    }

    public FnttAutoridadFuente removeFnttAutoridadFuente(FnttAutoridadFuente fnttAutoridadFuente) {
        getFnttAutoridadFuenteList().remove(fnttAutoridadFuente);
        fnttAutoridadFuente.setFnttFuente(null);
        return fnttAutoridadFuente;
    }
    
    public FnttFuenteTramoMunicipio addFnttFuenteTramoMunicipio1(FnttFuenteTramoMunicipio fnttFuenteTramoMunicipio) {
        getFnttFuenteTramoMunicipioList().add(fnttFuenteTramoMunicipio);
        fnttFuenteTramoMunicipio.setFnttFuente(this);
        return fnttFuenteTramoMunicipio;
    }

    public FnttFuenteTramoMunicipio removeFnttFuenteTramoMunicipio1(FnttFuenteTramoMunicipio fnttFuenteTramoMunicipio) {
        getFnttFuenteTramoMunicipioList().remove(fnttFuenteTramoMunicipio);
        fnttFuenteTramoMunicipio.setFnttFuente(null);
        return fnttFuenteTramoMunicipio;
    }

    public FnttTramo addFnttTramo(FnttTramo fnttTramo) {
        getFnttTramoList().add(fnttTramo);
        fnttTramo.setIdFuente(this);
        return fnttTramo;
    }
	
	public String getFuente_car() {
        return fuente_car;
    }


    public FnttTramo removeFnttTramo(FnttTramo fnttTramo) {
        getFnttTramoList().remove(fnttTramo);
        fnttTramo.setIdFuente(null);
        return fnttTramo;
    }
}
