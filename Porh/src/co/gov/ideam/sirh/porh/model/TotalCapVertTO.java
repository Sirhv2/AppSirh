package co.gov.ideam.sirh.porh.model;

import java.io.Serializable;

public class TotalCapVertTO implements Serializable{
    private String nombre;
    private Long legales;
    private Long ilegales;
    private Long demandaEstimadaLegales;
    private Long demandaEstimadaIlegales;
        
    public TotalCapVertTO() {
        super();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getLegales() {
        return legales;
    }

    public void setLegales(Long legales) {
        this.legales = legales;
    }

    public Long getIlegales() {
        return ilegales;
    }

    public void setIlegales(Long ilegales) {
        this.ilegales = ilegales;
    }

  public void setDemandaEstimadaLegales(Long demandaEstimadaLegales) {
    this.demandaEstimadaLegales = demandaEstimadaLegales;
  }

  public Long getDemandaEstimadaLegales() {
    return demandaEstimadaLegales;
  }

  public void setDemandaEstimadaIlegales(Long demandaEstimadaIlegales) {
    this.demandaEstimadaIlegales = demandaEstimadaIlegales;
  }

  public Long getDemandaEstimadaIlegales() {
    return demandaEstimadaIlegales;
  }
}
