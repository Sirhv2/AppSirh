package co.gov.ideam.sirh.seguridad.model;


import java.io.Serializable;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;

@Entity
@Table(name = "SIRH_MENU")
@NamedNativeQueries({
    @NamedNativeQuery(name = "menuxgrupo", 
                      query = "select m.* " + 
                      "from   SIRH_MENU m " + 
                      "where  m.menu_codigo in (select megr_menu_codigo " + 
                      "                     from  SIRH_MENU_GRUPO" + 
                      "                    where  megr_grps_codigo = :codigoPerfil)", 
                      resultClass = MenuVO.class),
    
    @NamedNativeQuery(name = "deletemenuxgrupo", 
                      query = "delete from SIRH_MENU_GRUPO " + 
                      "where  megr_grps_codigo = :codigoPerfil", 
                      resultClass = MenuVO.class),
    
    @NamedNativeQuery(name = "insertmenuxgrupo", 
                      query = "insert into SIRH_MENU_GRUPO " + 
                      "(megr_menu_codigo, megr_grps_codigo) values " +
                      "(:codigoMenu, :codigoPerfil)", 
                      resultClass = MenuVO.class),
    
    @NamedNativeQuery(name = "menuAutorizado", 
                      query = "    select distinct m.* " + 
                      "from SIRH_MENU m,  SIRH_MENU_GRUPO mg, SIRH_USUARIOS_GRUPOS ug, SIRH_GRUPOS g, SIRH_USUARIOS u " + 
                      "where m.menu_codigo = mg.megr_menu_codigo " + 
                      "and   ug.usgr_gprs_codigo = mg.megr_grps_codigo " + 
                      "and   g.grps_codigo = mg.megr_grps_codigo " + 
                      "and   g.grps_codigo = ug.usgr_gprs_codigo " + 
                      "and   ug.usgr_usu_codigo = u.usu_codigo " + 
                      "and   g.grps_estado = 1 " + 
                      "and   m.menu_estado = 1 " + 
                      "and   u.usu_estado = 1 " + 
                      "and   u.usu_codigo = :codigoUsuario " +
                      "order by m.menu_orden ASC", 
                      resultClass = MenuVO.class)
    })
/**
 * Representa un menu u opcion del sistema
 */
public class MenuVO implements Serializable {
    @TableGenerator(
            name="GENERAL_TABLE_GENERATOR", 
            table = "GENERATOR_TABLE", 
            pkColumnName = "SEQ_NAME",
            valueColumnName="SEQ_COUNT",
            pkColumnValue = "SEQ_MENU", 
            allocationSize = 1            
        )
    
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE,generator = "GENERAL_TABLE_GENERATOR")
    @Column(name="MENU_CODIGO", nullable = false)    
    private int codigo;    
    @Column(name="MENU_PAGINA", length = 100)
    private String pagina;    
    @Column(name="MENU_ACCION", length = 100)
    private String accion;
    
    
    @Transient
    private boolean activo; 
    
    @Column(name="MENU_ESTADO")
    private Integer activoAux;
    
    @Column(name="MENU_NOMBRE", nullable = false, length = 200)
    private String nombre;
    @Column(name="menu_class_name", nullable = true, length = 250)    
    private String nombreClase;
    @Column(name="MENU_ORDEN")
    private Long orden;
    @OneToMany(mappedBy = "menuVO", fetch = FetchType.EAGER, targetEntity = OpcionVO.class)
    private List<OpcionVO> opcionVOList;
    @ManyToOne
    @JoinColumn(name = "MENU_PADRE")
    private MenuVO menuVO;
    @OneToMany(mappedBy = "menuVO", fetch = FetchType.EAGER, targetEntity = MenuVO.class)
    private List<MenuVO> menuVOList;
    
    /**
     * Utilizado para marcar los menues seleccionados en un perfil especifico
     */
    @Transient
    private boolean selected;
    public MenuVO() {
    }

    public MenuVO(String menuAccion, int menuCodigo, boolean menuEstado,
                  String menuNombre, Long menuOrden,
                  MenuVO menuVO, String menuPagina, String nombreClase) {
        this.setPagina(menuPagina);
        this.setAccion(menuAccion);
        this.setCodigo(menuCodigo);
        this.setActivo(menuEstado);
        this.setNombre(menuNombre);
        this.setOrden(menuOrden);
        this.setNombreClase(nombreClase);
        this.menuVO = menuVO;        
    }

    public List<OpcionVO> getOpcionVOList() {
        return opcionVOList;
    }

    public void setOpcionVOList(List<OpcionVO> opcionVOList) {
        this.opcionVOList = opcionVOList;
    }

    public OpcionVO addOpcionVO(OpcionVO opcionVO) {
        if (this.getMenuVOList()==null){
            this.setMenuVOList(new ArrayList<MenuVO>());
        }
        getOpcionVOList().add(opcionVO);
        opcionVO.setMenuVO(this);
        return opcionVO;
    }

    public OpcionVO removeOpcionVO(OpcionVO opcionVO) {
        getOpcionVOList().remove(opcionVO);
        opcionVO.setMenuVO(null);
        return opcionVO;
    }

    public MenuVO getMenuVO() {
        return menuVO;
    }

    public void setMenuVO(MenuVO menuVO) {
        this.menuVO = menuVO;
    }

    public List<MenuVO> getMenuVOList() {
        return menuVOList;
    }

    public void setMenuVOList(List<MenuVO> menuVOList) {
        this.menuVOList = menuVOList;
    }

    public MenuVO addMenuVO(MenuVO menuVO) {
        if (this.getMenuVOList()==null){
            setMenuVOList(new ArrayList<MenuVO>());
        }
        getMenuVOList().add(menuVO);
        menuVO.setMenuVO(this);
        return menuVO;
    }

    public MenuVO removeMenuVO(MenuVO menuVO) {
        getMenuVOList().remove(menuVO);
        menuVO.setMenuVO(null);
        return menuVO;
    }

    public String getAccion() {
        return accion;
    }

    public void setAccion(String accion) {
        this.accion = accion;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

  

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getOrden() {
        return orden;
    }

    public void setOrden(Long orden) {
        this.orden = orden;
    }

    public String getPagina() {
        return pagina;
    }

    public void setPagina(String pagina) {
        this.pagina = pagina;
    }
    /**
     * Compara dos instancias del objeto por el codigo del mismo
     * @param obj
     * @return
     */
    public boolean equals(Object obj){
        if (obj instanceof MenuVO){
            return ((MenuVO)obj).getCodigo() == this.getCodigo();
        }
        return false;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public String getNombreClase() {
        return nombreClase;
    }

    public void setNombreClase(String nombreClase) {
        this.nombreClase = nombreClase;
    }

    public void setActivoAux(Integer activoAux) {
        this.activoAux = activoAux;
    }

    public Integer getActivoAux() {
        return activoAux;
    }

    public void setActivo(boolean activo) {

        this.activo = activo;
    }

    public boolean isActivo() {
      
        return activo;
    }
}
