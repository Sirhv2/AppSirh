package co.gov.ideam.sirh.calidad.model;


import java.io.Serializable;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import javax.persistence.Table;


import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.id.SequenceGenerator;



@Entity
@Table(name = "calt_muestra")
public class MuestrasPOJO implements Serializable {
    
    
    @GenericGenerator(name = "muestra_generator", 
                      strategy = "co.gov.ideam.sirh.usuarios.agua.model.IdeamSirhGeneratorCalidad",
                      parameters = {@Parameter(name = "AutoridadAmbientalId", value = "codigoAutoridad"),
                                    @Parameter(name = "Codigo", value = "id"),
                                    @Parameter(name = SequenceGenerator.SEQUENCE,  value= "seq_muestras")})
        
 
    
  @Id
  @Column(name = "id", nullable = false)
 // @GeneratedValue(generator = "muestra_generator")        
  private Long id;

    @Column(name="id_punto")
    private Long idPuntoMonitoreo;
    
    @Column(name="tipo_muestra")
    private Integer idTipoMuestra;
      
    @Column(name="id_laboratorio")
    private Integer idLaboratorio;
    
    @Column(name="fecha_muestreo", nullable = true)   
    private Calendar fechaMuestreo;
    
    @Column(name="id_autoridad")
    private Long codigoAutoridad;
      
    @Column(name="hora_muestreo")    
    private Integer horaMuestreo;

    @Column(name="min_muestreo")    
    private Integer minMuestreo;
    
    @Column(name="horario")
     private String horario;
    
    @Column(name="nro_muestra", nullable = true)
    private String nro_muestra;
      
      
    @Column(name="duracion")    
    private Double duracion;   
      
    @Column(name="intervalo_tiempo")    
    private Double intervaloTiempo;   
       
    @Column(name="caudal")    
    private Double caudal;  
    
    @Column(name="nro_verticales")
    private Integer nroVerticales;
        
    @Column(name="nro_submuestras")
    private Integer nroSubmuestras;
      
   
    @Column(name="otro_laboratorio")
    private String otroLaboratorio;
        
    @Column(name="ica")
    private Double ica;




    public MuestrasPOJO() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setIdPuntoMonitoreo(Long idPuntoMonitoreo) {
        this.idPuntoMonitoreo = idPuntoMonitoreo;
    }

    public Long getIdPuntoMonitoreo() {
        return idPuntoMonitoreo;
    }

    public void setIdTipoMuestra(Integer idTipoMuestra) {
        this.idTipoMuestra = idTipoMuestra;
    }

    public Integer getIdTipoMuestra() {
        return idTipoMuestra;
    }

    public void setIdLaboratorio(Integer idLaboratorio) {
        this.idLaboratorio = idLaboratorio;
    }

    public Integer getIdLaboratorio() {
        return idLaboratorio;
    }

    public void setFechaMuestreo(Calendar fechaMuestreo) {
        this.fechaMuestreo = fechaMuestreo;
    }

    public Calendar getFechaMuestreo() {
        return fechaMuestreo;
    }

    public void setCodigoAutoridad(Long codigoAutoridad) {
        this.codigoAutoridad = codigoAutoridad;
    }

    public Long getCodigoAutoridad() {
        return codigoAutoridad;
    }

    public void setHoraMuestreo(Integer horaMuestreo) {
        this.horaMuestreo = horaMuestreo;
    }

    public Integer getHoraMuestreo() {
        return horaMuestreo;
    }

    public void setMinMuestreo(Integer minMuestreo) {
        this.minMuestreo = minMuestreo;
    }

    public Integer getMinMuestreo() {
        return minMuestreo;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getHorario() {
        return horario;
    }

    public void setNro_muestra(String nro_muestra) {
        this.nro_muestra = nro_muestra;
    }

    public String getNro_muestra() {
        return nro_muestra;
    }

    public void setDuracion(Double duracion) {
        this.duracion = duracion;
    }

    public Double getDuracion() {
        return duracion;
    }

    public void setIntervaloTiempo(Double intervaloTiempo) {
        this.intervaloTiempo = intervaloTiempo;
    }

    public Double getIntervaloTiempo() {
        return intervaloTiempo;
    }

    public void setCaudal(Double caudal) {
        this.caudal = caudal;
    }

    public Double getCaudal() {
        return caudal;
    }

    public void setNroVerticales(Integer nroVerticales) {
        this.nroVerticales = nroVerticales;
    }

    public Integer getNroVerticales() {
        return nroVerticales;
    }

    public void setNroSubmuestras(Integer nroSubmuestras) {
        this.nroSubmuestras = nroSubmuestras;
    }

    public Integer getNroSubmuestras() {
        return nroSubmuestras;
    }

    public void setOtroLaboratorio(String otroLaboratorio) {
        this.otroLaboratorio = otroLaboratorio;
    }

    public String getOtroLaboratorio() {
        return otroLaboratorio;
    }

    public void setIca(Double ica) {
        this.ica = ica;
    }

    public Double getIca() {
        return ica;
    }
}
