package co.gov.ideam.sirh;


import java.util.Calendar;


public class DatosNodos {
   
    private Long codigo;
    private String modulo;
    private Integer cantidadRegistros;
    private Long codigoAutoridad;
    private String autoridad;
    private Integer cantidadRegistrosIDEAM;
    private Calendar fechaRegistro;
       
       public DatosNodos() {
           super();
       }


    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setModulo(String modulo) {
        this.modulo = modulo;
    }

    public String getModulo() {
        return modulo;
    }

    public void setCantidadRegistros(Integer cantidadRegistros) {
        this.cantidadRegistros = cantidadRegistros;
    }

    public Integer getCantidadRegistros() {
        return cantidadRegistros;
    }

    public void setCodigoAutoridad(Long codigoAutoridad) {
        this.codigoAutoridad = codigoAutoridad;
    }

    public Long getCodigoAutoridad() {
        return codigoAutoridad;
    }

    public void setAutoridad(String autoridad) {
        this.autoridad = autoridad;
    }

    public String getAutoridad() {
        return autoridad;
    }

    public void setCantidadRegistrosIDEAM(Integer cantidadRegistrosIDEAM) {
        this.cantidadRegistrosIDEAM = cantidadRegistrosIDEAM;
    }

    public Integer getCantidadRegistrosIDEAM() {
        return cantidadRegistrosIDEAM;
    }

    public void setFechaRegistro(Calendar fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public Calendar getFechaRegistro() {
        
        return fechaRegistro;
    }

   
}
