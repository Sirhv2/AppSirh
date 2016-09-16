package co.gov.ideam.sirh.calidad.model;


import co.gov.ideam.sirh.parametros.model.Lista;

import java.io.Serializable;

import java.util.Calendar;

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
    @NamedQuery(name = "NormaLimites.findAll", query = "select o from NormaLimites o"), 
    @NamedQuery(name = "NormaLimites.findId", query = "select o from NormaLimites  o where o.id = :id "),
    @NamedQuery(name = "NormaLimites.findNormaUso", query = "select o from NormaLimites  o where o.uso = :idUso and o.idNorma = :idNorma and o.riesgo = :idRiesgo ")
 
 
})
@Table(name = "part_norma_limites")
public class NormaLimites implements Serializable {
    
    public static final String listaLimitesParametro=    "select nl.id idNormaLimite, n.nombre nomNorma, lu.valor as nomUso, lr.valor as nomRiesgo, nl.id_parametro, lp.valor as nomParametro, nl.valor limitePar " + 
                "from PART_NORMA_LIMITES nl, part_norma n, part_listas lr, part_listas lu, part_listas lp " + 
                "where nl.id_norma = n.id " + 
                "and nl.id_riesgo = lr.id " + 
                "and nl.id_uso = lu.id " + 
                "and nl.id_parametro = lp.id " +
                "and nl.id_parametro = ?1 " +
                "and n.id_autoridad = ?2 ";
    
    @GenericGenerator(name = "normalimites_generator", 
                      strategy = "co.gov.ideam.sirh.usuarios.agua.model.IdeamSirhGenerator",
                      parameters = {@Parameter(name = "AutoridadAmbientalId", value = "codigoAutoridad"),
                                    @Parameter(name = "Codigo", value = "id"),
                                    @Parameter(name = SequenceGenerator.SEQUENCE,  value= "seq_norma_limites")})
        
 
    
  @Id
  @Column(name = "id", nullable = false)
  //@GeneratedValue(generator = "normalimites_generator")        
    private Long id;
    @Column(name="id_uso")
    private Integer uso;
    @Column(name="id_parametro")
    private Integer idParametro; 
    @Column(name="id_unidad")
    private Integer idUnidad;
    @Column(name="id_signo")
    private Integer idSigno;
    @Column(name="valor")
    private Double valor;
    @Column(name="valor2")
    private Double valor2;
    @Column(name="valor_char")
    private String valorChar;
    @Column(name="fecha_definicion")
    private Date fecha;
    @Column(name="id_norma")
    private Integer idNorma;
    @Column(name="id_riesgo")
    private Integer riesgo;

    @Transient
    private String valor_parametro;

    @Transient
    private Lista listaParametros;
    @Transient
    private Lista listaUnidad;
    @Transient
    private Lista listaSignos;
   
    @Transient
    private Long codigoAutoridad;
    
    

    public NormaLimites() {
        super();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setUso(Integer uso) {
        this.uso = uso;
    }

    public Integer getUso() {
        return uso;
    }

    public void setIdParametro(Integer idParametro) {
        this.idParametro = idParametro;
    }

    public Integer getIdParametro() {
        return idParametro;
    }

    public void setIdUnidad(Integer idUnidad) {
        this.idUnidad = idUnidad;
    }

    public Integer getIdUnidad() {
        return idUnidad;
    }

    public void setIdSigno(Integer idSigno) {
        this.idSigno = idSigno;
    }

    public Integer getIdSigno() {
        return idSigno;
    }


  
    public void setValor2(Double valor2) {
        this.valor2 = valor2;
    }

    public Double getValor2() {
        return valor2;
    }

    public void setValorChar(String valorChar) {
        this.valorChar = valorChar;
    }

    public String getValorChar() {
        return valorChar;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Double getValor() {
        return valor;
    }

    public void setListaParametros(Lista listaParametros) {
        this.listaParametros = listaParametros;
    }

    public Lista getListaParametros() {
        return listaParametros;
    }

    public void setListaUnidad(Lista listaUnidad) {
        this.listaUnidad = listaUnidad;
    }

    public Lista getListaUnidad() {
        return listaUnidad;
    }

    public void setListaSignos(Lista listaSignos) {
        this.listaSignos = listaSignos;
    }

    public Lista getListaSignos() {
        return listaSignos;
    }

    public void setCodigoAutoridad(Long codigoAutoridad) {
        this.codigoAutoridad = codigoAutoridad;
    }

    public Long getCodigoAutoridad() {
        return codigoAutoridad;
    }


    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setIdNorma(Integer idNorma) {
        this.idNorma = idNorma;
    }

    public Integer getIdNorma() {
        return idNorma;
    }

    public void setValor_parametro(String valor_parametro) {
        this.valor_parametro = valor_parametro;
    }

    public String getValor_parametro() {
        return valor_parametro;
    }

    public void setRiesgo(Integer riesgo) {
        this.riesgo = riesgo;
    }

    public Integer getRiesgo() {
        return riesgo;
    }
}
