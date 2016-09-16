package co.gov.ideam.sirh.parametros.model;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@NamedQueries({
  @NamedQuery(name = "TipoParametro.findAll", query = "select o from TipoParametro o order by o.nombre ASC"),
  @NamedQuery(name = "TipoParametro.find", query = "select o from TipoParametro o where o.codigo = :codigo")
})

@Table(name = "part_tipos_parametro")
public class TipoParametro  implements Serializable{
    /**
     * Constantes con los codigos de los tipos de parametros
     */
    public static final Long SERVIDOR_CORREO = 1L;
    
    public static final Long CONEXION_NODO_IDEAM = 2L;
    
    public static final Long CLIENTE_WS_IMPORTAR = 3L;
    
    @Id
    @Column(name="tpar_codigo", nullable = false)    
    private Long codigo;
    @Column(name="tpar_nomnbre", nullable = false)    
    private String nombre;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "tipoParametro")        
    private List<Parametro> parametros = new ArrayList<Parametro>();
    
    public TipoParametro() {
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

    public List<Parametro> getParametros() {
        return parametros;
    }

    public void setParametros(List<Parametro> parametros) {
        this.parametros = parametros;
    }
    
    public Parametro getParametro(Long codigo){
        if(this.parametros!=null){
            Iterator it = this.parametros.iterator();
            while(it.hasNext()){
                Parametro pa = (Parametro)it.next();
                if(pa.getCodigo().longValue()==codigo.longValue()){
                    System.out.println("pa.getCodigo().longValue():"+pa.getCodigo().longValue());
                    return pa;
                }
            }
        }
        return null;
    }
    
    public Parametro getParametro(String nombre){        
        if(this.parametros!=null){            
            Iterator it = this.parametros.iterator();            
            while(it.hasNext()){                
                Parametro pa = (Parametro)it.next();                                
                if(pa.getNombre().equals(nombre)){
                    return pa;
                }
            }
        }
        return null;
    }
    
}
