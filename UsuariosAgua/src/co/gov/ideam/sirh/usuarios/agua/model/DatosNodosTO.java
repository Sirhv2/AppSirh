package co.gov.ideam.sirh.usuarios.agua.model;


import co.gov.ideam.sirh.parametros.model.Autoridades;

import java.io.Serializable;


import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;

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
  @NamedQuery(name = "DatosNodosTO.findAll", query = "select o from DatosNodosTO o "),
  @NamedQuery(name = "DatosNodosTO.findAUTORIDAD", query = "select o from DatosNodosTO o where o.codigoAutoridad = :codigoAutoridad")
  
})
@Table(name = "sirh_datos_nodos")
public class DatosNodosTO implements Serializable{
    @GenericGenerator(name = "nodos_generator", 
                      strategy = "co.gov.ideam.sirh.usuarios.agua.model.IdeamSirhGenerator",
                      parameters = {@Parameter(name = "AutoridadAmbientalId", value = "codigoAutoridadAmbiental"),
                                    @Parameter(name = "Codigo", value = "codigo"),
                                    @Parameter(name = SequenceGenerator.SEQUENCE,  value= "seq_usuario_agua")})

    @Id
    //@GeneratedValue(generator = "predios_generator")        
    @Column(name="id", nullable = false)    
    private Long codigo;
    @Column(name="modulo", nullable = true)    
    private String modulo;
    @Column(name="cantidad_registros", nullable = true)    
    private Integer cantidadRegistros;
          
    @Column(name="id_autoridad", nullable = false)    
    private Long codigoAutoridad;
    @Column(name="autoridad", nullable = true)    
    private String autoridad;
    @Column(name="cant_registro_ideam", nullable = true)    
    private Integer cantidadRegistrosIDEAM;
    @Column(name="fecha_registro", nullable = true)    
    private Calendar fechaRegistro;
    
    @Transient
    private Integer diferencia;
   
       public DatosNodosTO() {
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

    public void setDiferencia(Integer diferencia) {
        this.diferencia = diferencia;
    }

    public Integer getDiferencia() {
        
        Integer dif = cantidadRegistros-cantidadRegistrosIDEAM;
        return dif;
    }
}
