package co.gov.ideam.sirh.parametros.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
@Entity
@NamedQueries({
  @NamedQuery(name = "Parametro.findAll", query = "select o from Parametro o"),
  @NamedQuery(name = "Parametro.findById", query = "select o from Parametro o where o.codigo = :codigo "),
  @NamedQuery(name = "Parametro.findByName", query = "select o from Parametro o where upper(o.nombre) = :nombre")
})
@Table(name = "part_parametros")

public class Parametro  implements Serializable{
    @Id
    @Column(name="par_codigo", nullable = false)        
    private Long codigo;
    @Column(name="par_nombre", nullable = false)        
    private String nombre;
    @Column(name="par_valor", nullable = true)        
    private String valor;
    @Column(name="par_valor_adicional", nullable = true)        
    private String valorAdicional;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "par_codigo_tipo", nullable = false)
    private TipoParametro tipoParametro;
    
    
    public static final Long CONEXION_IDEAM_URL = 5L;
    public static final Long CONEXION_IDEAM_USUARIO = 6L;
    public static final Long CONEXION_IDEAM_CLAVE = 7L;

    public static final Long PATH_ARCHIVO_IMPORTAR = 9L;

    public static final Long CONEXION_LOGO = 8L;

    public Parametro() {
        super();
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getValorAdicional() {
        return valorAdicional;
    }

    public void setValorAdicional(String valorAdicional) {
        this.valorAdicional = valorAdicional;
    }

    public TipoParametro getTipoParametro() {
        return tipoParametro;
    }

    public void setTipoParametro(TipoParametro tipoParametro) {
        this.tipoParametro = tipoParametro;
    }
}
