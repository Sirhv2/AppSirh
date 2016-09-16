package co.gov.ideam.sirh.seguridad.model;


import java.io.Serializable;

import java.util.Iterator;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

@Entity(name = "SIRH_GRUPOS")
@NamedNativeQueries({
    @NamedNativeQuery(name = "perfilesxusuario", 
                      query = "select p.* " + 
                      "from   SIRH_GRUPOS p " + 
                      "where  p.grps_codigo in (select usgr_gprs_codigo " + 
                      "                     from  SIRH_USUARIOS_GRUPOS" + 
                      "                    where  usgr_usu_codigo = :codigoUsuario)", 
                      resultClass = PerfilVO.class),
    
    @NamedNativeQuery(name = "deleteperfilesxusuario", 
                      query = "delete from SIRH_USUARIOS_GRUPOS" +
                      "         where  usgr_usu_codigo = :codigoUsuario", 
                      resultClass = PerfilVO.class),
    
    @NamedNativeQuery(name = "insertperfilesxusuario", 
                      query = "insert into SIRH_USUARIOS_GRUPOS " +
                      " (usgr_gprs_codigo, usgr_usu_codigo) " +
                      " values (:codigoPerfil, :codigoUsuario)", 
                      resultClass = PerfilVO.class),
    
    @NamedNativeQuery(name = "deleteperfilfromusuarios", 
                      query = "delete from SIRH_USUARIOS_GRUPOS" +
                      "         where  usgr_gprs_codigo = :codigoPerfil", 
                      resultClass = PerfilVO.class)
})
/**
 * Representa un perfil que agrupa menu y opciones que dan permisos a los 
 * usuarios paa hacer uso de los diferentes recursos del sistema
 */
public class PerfilVO implements Serializable {
    @TableGenerator(
            name="GENERAL_TABLE_GENERATOR", 
            table = "GENERATOR_TABLE", 
            pkColumnName = "SEQ_NAME",
            valueColumnName="SEQ_COUNT",
            pkColumnValue = "SEQ_GRUPO", 
            allocationSize = 1            
        )
    
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,generator = "GENERAL_TABLE_GENERATOR")
    @Column(name="grps_codigo")
    private long codigo;
    @Column(name="grps_nombre")
    private String nombre;
    @Column(name="grps_estado")
    private boolean activo;
    @Column(name="GRPS_IDEAM", nullable = false)
    private boolean ideam;
    
    @Transient
    private List menuHabilitado;
    @Transient
    private boolean selected;
    
    public PerfilVO() {
        super();
    }

    public long getCodigo() {
        return codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public List getMenuHabilitado() {
        return menuHabilitado;
    }

    public void setMenuHabilitado(List menuHabilitado) {
        this.menuHabilitado = menuHabilitado;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    /**
     * Compara dos instancias del objeto por el codigo del mismo
     * @param obj
     * @return
     */
    public boolean equals(Object obj){
        if (obj instanceof PerfilVO){
            return ((PerfilVO)obj).getCodigo() == this.getCodigo();
        }
        return false;
    }

    public boolean isIdeam() {
        return ideam;
    }

    public void setIdeam(boolean ideam) {
        this.ideam = ideam;
    }
}
