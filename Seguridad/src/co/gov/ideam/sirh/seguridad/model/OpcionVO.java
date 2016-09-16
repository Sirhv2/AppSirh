package co.gov.ideam.sirh.seguridad.model;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

@Entity
@NamedNativeQueries({
    @NamedNativeQuery(name = "opcionxgrupo", 
                      query = "select o.* " + 
                      "from   SIRH_OPCIONES o " + 
                      "where  o.opc_codigo in (select meop_opc_codigo " + 
                      "                     from  SIRH_MENU_OPCIONES" + 
                      "                    where  meop_grps_codigo = :codigoPerfil)", 
                      resultClass = OpcionVO.class),

    @NamedNativeQuery(name = "deleteopcionxgrupo", 
                      query = "delete from SIRH_MENU_OPCIONES " +                   
                      "where  meop_grps_codigo = :codigoPerfil      ", 
                      resultClass = OpcionVO.class),

    @NamedNativeQuery(name = "insertopcionxgrupo", 
                      query = "insert into SIRH_MENU_OPCIONES " + 
                      "(meop_menu_codigo, meop_opc_codigo, meop_grps_codigo) values " +
                      "(:codigoMenu, :codigoOpcion, :codigoPerfil)", 
                      resultClass = MenuVO.class),
    
    @NamedNativeQuery(name = "opcionesAutorizadas", 
                      query = "select distinct o.* " + 
                      "from SIRH_MENU m,  SIRH_MENU_GRUPO mg, SIRH_USUARIOS_GRUPOS ug, SIRH_GRUPOS g, SIRH_USUARIOS u, SIRH_MENU_OPCIONES mo, SIRH_OPCIONES o " + 
                      "where m.menu_codigo = mg.megr_menu_codigo " + 
                      "and   ug.usgr_gprs_codigo = mg.megr_grps_codigo " + 
                      "and   g.grps_codigo = mg.megr_grps_codigo " + 
                      "and   g.grps_codigo = ug.usgr_gprs_codigo " + 
                      "and   ug.usgr_usu_codigo = u.usu_codigo " + 
                      "and   mo.meop_menu_codigo = mg.megr_menu_codigo " + 
                      "and   mo.meop_opc_codigo = o.opc_codigo " + 
                      "and   mo.meop_grps_codigo = mg.megr_grps_codigo " + 
                      "and   g.grps_estado = 1 " + 
                      "and   m.menu_estado = 1 " + 
                      "and   u.usu_estado = 1 " + 
                      "and   o.opc_estado = 1 " + 
                      "and   u.usu_codigo = :codigoUsuario " +
                      "and   m.menu_codigo = :codigoMenu",                               
                      resultClass = OpcionVO.class)
})
@Table(name = "SIRH_OPCIONES")
/**
 * Representa un boton dentro de una pagina del sistema
 */
public class OpcionVO implements Serializable {
    @TableGenerator(
            name="GENERAL_TABLE_GENERATOR", 
            table = "GENERATOR_TABLE", 
            pkColumnName = "SEQ_NAME",
            valueColumnName="SEQ_COUNT",
            pkColumnValue = "SEQ_OPCION", 
            allocationSize = 1            
        )
    
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,generator = "GENERAL_TABLE_GENERATOR")
    @Column(name="OPC_CODIGO", nullable = false, unique = true)
    private Long codigo;
    @Column(name="OPC_ESTADO", nullable = false)
    private boolean activo;
    @Column(name="OPC_NOMBRE", nullable = false)
    private String opcNombre;
    @Column(name="OPC_ORDEN")
    private Long opcOrden;
    @Column(name="OPC_JSF_ID", nullable = false, length = 200)
    private String opcIdJsf;
    @ManyToOne
    @JoinColumn(name = "OPC_CODIGOMENU")
    private MenuVO menuVO;
    /**
     * Utilizado para marcar los menues seleccionados en un perfil especifico
     */
    @Transient
    private boolean selected;

    public OpcionVO() {
    }

    public OpcionVO(Long opcCodigo, MenuVO menuVO, boolean opcEstado,
                    String opcNombre, Long opcOrden, String opcIdJsf) {
        this.codigo = opcCodigo;
        this.menuVO = menuVO;
        this.setActivo(opcEstado);
        this.opcNombre = opcNombre;
        this.opcOrden = opcOrden;
        this.setOpcIdJsf(opcIdJsf);
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long opcCodigo) {
        this.codigo = opcCodigo;
    }

    public String getOpcNombre() {
        return opcNombre;
    }

    public void setOpcNombre(String opcNombre) {
        this.opcNombre = opcNombre;
    }

    public Long getOpcOrden() {
        return opcOrden;
    }

    public void setOpcOrden(Long opcOrden) {
        this.opcOrden = opcOrden;
    }

    public MenuVO getMenuVO() {
        return menuVO;
    }

    public void setMenuVO(MenuVO menuVO) {
        this.menuVO = menuVO;
    }

    public String getOpcIdJsf() {
        return opcIdJsf;
    }

    public void setOpcIdJsf(String opcIdJsf) {
        this.opcIdJsf = opcIdJsf;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
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
        if (obj instanceof OpcionVO){
            return ((OpcionVO)obj).getCodigo().longValue() == 
                   this.getCodigo().longValue();
        }
        return false;
    }

}
