package co.gov.ideam.sirh.calidad.model;


import co.gov.ideam.sirh.parametros.model.Lista;
import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.id.SequenceGenerator;

@Entity
@NamedQueries({
  @NamedQuery(name = "Medicion.findAllMedicion", query = "select o from Medicion o"),
  @NamedQuery(name = "Medicion.findMuestra", query = "select o from Medicion o where o.idMuestra.id = :idMuestra "),
  @NamedQuery(name = "Medicion.findId", query = "select o from Medicion o where o.id = :id ")

 
})
@Table(name = "calt_medicion")
public class Medicion implements Serializable {
    
    
    @GenericGenerator(name = "medicion_generator", 
                      strategy = "co.gov.ideam.sirh.usuarios.agua.model.IdeamSirhGeneratorCalidad",
                      parameters = {@Parameter(name = "AutoridadAmbientalId", value = "codigoAutoridad"),
                                    @Parameter(name = "Codigo", value = "id"),
                                    @Parameter(name = SequenceGenerator.SEQUENCE,  value= "seq_medicion")})
        
 
    
  @Id
  @Column(name = "id", nullable = false)
  //@GeneratedValue(generator = "medicion_generator")        
  private Long id;
    
  @JoinColumn(name = "id_muestra", referencedColumnName = "id")
  @javax.persistence.ManyToOne
  private Muestra idMuestra;
  
  
  
  @Column(name="id_parametro")
  private Integer idParametro;
    
  @Column(name="unidad")
  private Integer idUnidad;
  
  @Column(name="valor")   
  private Double valor;

  @Column(name="valor2")   
  private Double valor2;
    
  @Column(name="signo")
  private Integer idSigno;
   
  @Column(name = "es_acreditado")
  private Integer esAcreditado;
    
  @Column(name="limite_deteccion")   
  private Double limiteDeteccion;
  
  @Column(name = "metodo_determinante")
  private String metodoDeterminacion;


  @Column(name = "valor_char")
  private String valorchar;

  @Transient
  private Long codigoAutoridad;
  
  @Transient
  private Boolean acreditado;  
    
  @Transient
  private Lista parametro;
 
  @Transient
  private Lista signo;
  
  @Transient
  private Lista unidad;
  
  @Transient
  private String valor_parametro;
    
    public Medicion() {
        super();
    }

    public Medicion(Long id) {
        this.id = id;
        
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setIdMuestra(Muestra idMuestra) {
        this.idMuestra = idMuestra;
    }

    public Muestra getIdMuestra() {
        return idMuestra;
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

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public Double getValor() {
        return valor;
    }

    public void setIdSigno(Integer idSigno) {
        this.idSigno = idSigno;
    }

    public Integer getIdSigno() {
        return idSigno;
    }



    public void setLimiteDeteccion(Double limiteDeteccion) {
        this.limiteDeteccion = limiteDeteccion;
    }

    public Double getLimiteDeteccion() {
        return limiteDeteccion;
    }

    public void setMetodoDeterminacion(String metodoDeterminacion) {
        this.metodoDeterminacion = metodoDeterminacion;
    }

    public String getMetodoDeterminacion() {
        return metodoDeterminacion;
    }

    public void setParametro(Lista parametro) {
        this.parametro = parametro;
    }

    public Lista getParametro() {
        return parametro;
    }

    public void setSigno(Lista signo) {
        this.signo = signo;
    }

    public Lista getSigno() {
        return signo;
    }

    public void setUnidad(Lista unidad) {
        this.unidad = unidad;
    }

    public Lista getUnidad() {
        return unidad;
    }

    public void setCodigoAutoridad(Long codigoAutoridad) {
        this.codigoAutoridad = codigoAutoridad;
    }

    public Long getCodigoAutoridad() {
        return codigoAutoridad;
    }


    public void setValor2(Double valor2) {
        this.valor2 = valor2;
    }

    public Double getValor2() {
        return valor2;
    }

 

    public String getValor_parametro() {
        return valor_parametro;
    }


    public void setValor_parametro(String valor_parametro) {
        this.valor_parametro = valor_parametro;
    }


    public void setValorchar(String valorchar) {
        this.valorchar = valorchar;
    }

    public String getValorchar() {
        return valorchar;
    }

    public void setEsAcreditado(Integer esAcreditado) {
        this.esAcreditado = esAcreditado;
    }

    public Integer getEsAcreditado() {
        return esAcreditado;
    }

    public void setAcreditado(Boolean acreditado) {
        this.acreditado = acreditado;
    }

    public Boolean getAcreditado() {
        return acreditado;
    }
}//Fin clase
