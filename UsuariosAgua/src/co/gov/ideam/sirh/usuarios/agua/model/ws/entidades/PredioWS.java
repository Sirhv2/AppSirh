package co.gov.ideam.sirh.usuarios.agua.model.ws.entidades;

import java.io.Serializable;

public class PredioWS implements Serializable {

  
    private String nombrePredio;
    private String matriculaInmobiliaria;
    private String cedulaCatastral;
    private Integer idMunicipioPredio;
    private Integer idDeptoPredio;
    private String direccionPredio;

    public PredioWS(){
          
        
        }

    public void setNombrePredio(String nombrePredio) {
        this.nombrePredio = nombrePredio;
    }

    public String getNombrePredio() {
        return nombrePredio;
    }

    public void setMatriculaInmobiliaria(String matriculaInmobiliaria) {
        this.matriculaInmobiliaria = matriculaInmobiliaria;
    }

    public String getMatriculaInmobiliaria() {
        return matriculaInmobiliaria;
    }

    public void setCedulaCatastral(String cedulaCatastral) {
        this.cedulaCatastral = cedulaCatastral;
    }

    public String getCedulaCatastral() {
        return cedulaCatastral;
    }

    public void setIdMunicipioPredio(Integer idMunicipioPredio) {
        this.idMunicipioPredio = idMunicipioPredio;
    }

    public Integer getIdMunicipioPredio() {
        return idMunicipioPredio;
    }

    public void setIdDeptoPredio(Integer idDeptoPredio) {
        this.idDeptoPredio = idDeptoPredio;
    }

    public Integer getIdDeptoPredio() {
        return idDeptoPredio;
    }

    public void setDireccionPredio(String direccionPredio) {
        this.direccionPredio = direccionPredio;
    }

    public String getDireccionPredio() {
        return direccionPredio;
    }
}
