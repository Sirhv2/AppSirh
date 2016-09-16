package co.gov.ideam.sirh.calidad.model;


import java.io.Serializable;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;


@Entity
@Table(name = "calt_muestras_v")
@NamedQueries( { @NamedQuery(name = "MuestraTO.findAll",
                             query = "SELECT s FROM MuestraTO s"),
                 @NamedQuery(name = "MuestraTO.findAutoridadAll",
                             query = "SELECT s FROM MuestraTO s where s.autoridad = :codigoAutoridad ")

   } )
public class MuestraTO implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "nombre_punto")
    private String nombre_punto;
    @Column(name = "fuente")
    private String fuente;
    @Column(name = "tramo")
    private String tramo;
    @Column(name = "nro_muestra")
    private String nro_muestra;
    @Column(name = "tipo_muestra")
    private String tipo_muestra;
    @Column(name = "fecha_muestreo")
    private Calendar fecha_muestreo;
    @Column(name = "hora_muestra")
    private String hora_muestra;
    @Column(name = "caudal")
    private Double caudal;
    @Column(name = "autoridad")
    private Long autoridad;
    
    
    public MuestraTO() {
    }


    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setNombre_punto(String nombre_punto) {
        this.nombre_punto = nombre_punto;
    }

    public String getNombre_punto() {
        return nombre_punto;
    }

    public void setFuente(String fuente) {
        this.fuente = fuente;
    }

    public String getFuente() {
        return fuente;
    }

    public void setTramo(String tramo) {
        this.tramo = tramo;
    }

    public String getTramo() {
        return tramo;
    }

    public void setNro_muestra(String nro_muestra) {
        this.nro_muestra = nro_muestra;
    }

    public String getNro_muestra() {
        return nro_muestra;
    }

    public void setTipo_muestra(String tipo_muestra) {
        this.tipo_muestra = tipo_muestra;
    }

    public String getTipo_muestra() {
        return tipo_muestra;
    }

 


    public void setAutoridad(Long autoridad) {
        this.autoridad = autoridad;
    }

    public Long getAutoridad() {
        return autoridad;
    }


    public void setFecha_muestreo(Calendar fecha_muestreo) {
        this.fecha_muestreo = fecha_muestreo;
    }

    public Calendar getFecha_muestreo() {
        return fecha_muestreo;
    }

    public void setCaudal(Double caudal) {
        this.caudal = caudal;
    }

    public Double getCaudal() {
        return caudal;
    }

    public void setHora_muestra(String hora_muestra) {
        this.hora_muestra = hora_muestra;
    }

    public String getHora_muestra() {
        return hora_muestra;
    }
}

