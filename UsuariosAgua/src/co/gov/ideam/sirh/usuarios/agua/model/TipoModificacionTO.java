package co.gov.ideam.sirh.usuarios.agua.model;

import java.io.Serializable;

/**
 * Tipos de modificacion para las concesiones y los permisos de vertimiento
 */
public class TipoModificacionTO implements Serializable{
    
    private String codigo;
    private String nombre;
    private String descripcion;
    
    public static final String MODIFICACION = "MOD";
    public static final String RENOVACION = "REN";
    public static final String REVOCACION = "REV";
    public static final String TRASPASO = "TRA";
    
    public TipoModificacionTO() {
        super();
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
    public boolean equals(Object obj){
        return obj!=null &&
            obj instanceof TipoModificacionTO &&
            ((TipoModificacionTO)obj).getCodigo()!=null &&
            this.getCodigo() != null &&
            ((TipoModificacionTO)obj).getCodigo().equalsIgnoreCase(this.getCodigo());
    }
}
