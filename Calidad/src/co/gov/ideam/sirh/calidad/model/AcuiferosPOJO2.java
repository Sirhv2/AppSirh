package co.gov.ideam.sirh.calidad.model;

import java.io.Serializable;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

public class AcuiferosPOJO2 implements Serializable { 


  @Column(name = "plantillaID")
  private Integer plantillaID;  
  @Column(name = "area_hidrografica")
  private String area_hidrografica;  
  @Column(name = "zona_hidrografica")
  private String zona_hidrografica;
  @Column(name = "provincia_hidroge")
  private String provinciaHidroge;  
  @Column(name = "nombre_plantilla")
    private String nombrePlantilla;  
    @Column(name = "car_gestiona")
    private String carGestiona;  
    @Column(name = "recarga_estimada")
    private String recargaEstimada;  
    @Column(name = "num_pozo_inventariados")
    private String numPozoInventariados;  
    @Column(name = "num_alijibes_inventariados")
    private String numAlijibesInventariados;  
    @Column(name = "num_manantiales_inventariados")
    private String numManantialesInventariados;  
    @Column(name = "demanda_calculada")
    private String demandaCalculada;    
  
  
  

  public AcuiferosPOJO2() {
  }


  public void setPlantillaID(Integer plantillaID) {
    this.plantillaID = plantillaID;
  }

  public Integer getPlantillaID() {
    return plantillaID;
  }

  public void setArea_hidrografica(String area_hidrografica) {
    this.area_hidrografica = area_hidrografica;
  }

  public String getArea_hidrografica() {
    return area_hidrografica;
  }

  public void setZona_hidrografica(String zona_hidrografica) {
    this.zona_hidrografica = zona_hidrografica;
  }

  public String getZona_hidrografica() {
    return zona_hidrografica;
  }

  public void setProvinciaHidroge(String provinciaHidroge) {
    this.provinciaHidroge = provinciaHidroge;
  }

  public String getProvinciaHidroge() {
    return provinciaHidroge;
  }

  public void setNombrePlantilla(String nombrePlantilla) {
    this.nombrePlantilla = nombrePlantilla;
  }

  public String getNombrePlantilla() {
    return nombrePlantilla;
  }

  public void setCarGestiona(String carGestiona) {
    this.carGestiona = carGestiona;
  }

  public String getCarGestiona() {
    return carGestiona;
  }

  public void setRecargaEstimada(String recargaEstimada) {
    this.recargaEstimada = recargaEstimada;
  }

  public String getRecargaEstimada() {
    return recargaEstimada;
  }

  public void setNumPozoInventariados(String numPozoInventariados) {
    this.numPozoInventariados = numPozoInventariados;
  }

  public String getNumPozoInventariados() {
    return numPozoInventariados;
  }

  public void setNumAlijibesInventariados(String numAlijibesInventariados) {
    this.numAlijibesInventariados = numAlijibesInventariados;
  }

  public String getNumAlijibesInventariados() {
    return numAlijibesInventariados;
  }

  public void setNumManantialesInventariados(String numManantialesInventariados) {
    this.numManantialesInventariados = numManantialesInventariados;
  }

  public String getNumManantialesInventariados() {
    return numManantialesInventariados;
  }

  public void setDemandaCalculada(String demandaCalculada) {
    this.demandaCalculada = demandaCalculada;
  }

  public String getDemandaCalculada() {
    return demandaCalculada;
  }
}
