package co.gov.ideam.sirh.calidad.model;

import java.io.Serializable;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

public class JSPomcaPOJO implements Serializable {
   
  
  
  @Column(name = "pomca_id")
  private Integer pomca_id;
  @Column(name = "nombre")
  private String nombre;
  @Column(name = "autor")
  private String autor;
  @Column(name = "nombrePomca")
  private String nombrePomca;
  @Column(name = "id_autoridad")
    private Integer id_autoridad;
    @Column(name = "programa")
    private String programa;
    @Column(name = "proyecto")
    private String proyecto;
    @Column(name = "actividad")
    private String actividad;
  @Column(name = "porcCumplimiento")
    private Double porcCumplimiento;
    @Column(name = "porcEjecucion")
    private Double porcEjecucion;
    @Column(name = "presupuesto_asignado")
    private Double presupuestoAsignado;
    @Column(name = "presupuestoEjecutado")
    private Double presupuestoeEjecutado;

   
  public JSPomcaPOJO() {
  }


  public void setAutor(String autor) {
    this.autor = autor;
  }

  public String getAutor() {
    return autor;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getNombre() {
    return nombre;
  }


  

  public void setPomca_id(Integer pomca_id) {
    this.pomca_id = pomca_id;
  }

  public Integer getPomca_id() {
    return pomca_id;
  }

  public void setNombrePomca(String nombrePomca) {
    this.nombrePomca = nombrePomca;
  }

  public String getNombrePomca() {
    return nombrePomca;
  }


  public void setId_autoridad(Integer id_autoridad) {
    this.id_autoridad = id_autoridad;
  }

  public Integer getId_autoridad() {
    return id_autoridad;
  }

  public void setPrograma(String programa) {
    this.programa = programa;
  }

  public String getPrograma() {
    return programa;
  }

  public void setProyecto(String proyecto) {
    this.proyecto = proyecto;
  }

  public String getProyecto() {
    return proyecto;
  }

  public void setActividad(String actividad) {
    this.actividad = actividad;
  }

  public String getActividad() {
    return actividad;
  }

  public void setPorcCumplimiento(Double porcCumplimiento) {
    this.porcCumplimiento = porcCumplimiento;
  }

  public Double getPorcCumplimiento() {
    return porcCumplimiento;
  }

  public void setPorcEjecucion(Double porcEjecucion) {
    this.porcEjecucion = porcEjecucion;
  }

  public Double getPorcEjecucion() {
    return porcEjecucion;
  }

  public void setPresupuestoAsignado(Double presupuestoAsignado) {
    this.presupuestoAsignado = presupuestoAsignado;
  }

  public Double getPresupuestoAsignado() {
    return presupuestoAsignado;
  }

  public void setPresupuestoeEjecutado(Double presupuestoeEjecutado) {
    this.presupuestoeEjecutado = presupuestoeEjecutado;
  }

  public Double getPresupuestoeEjecutado() {
    return presupuestoeEjecutado;
  }
}
