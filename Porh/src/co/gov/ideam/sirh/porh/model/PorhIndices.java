package co.gov.ideam.sirh.porh.model;

import java.io.Serializable;


import java.util.Date;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
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
  @NamedQuery(name = "PorhIndices.findAllByTramo", query = "select o from PorhIndices o where o.idTramo = :codigoTramo"),
   @NamedQuery(name = "PorhIndices.find", query = "select o from PorhIndices o where o.id = :codigo"),
  @NamedQuery(name = "PorhIndices.deleteByPlan", query = "delete from PorhIndices o where o.idPlan = :codigoPlan")
})
@Table(name = "PORH_INDICES")
public class PorhIndices implements Serializable {
    @GenericGenerator(name = "porh_generator", 
                      strategy = "co.gov.ideam.sirh.usuarios.agua.model.IdeamSirhGenerator",
                      parameters = {@Parameter(name = "AutoridadAmbientalId", value = "codigoAutoridad"),
                                    @Parameter(name = "Codigo", value = "id"),
                                    @Parameter(name = SequenceGenerator.SEQUENCE,  value= "seq_porh")})
    @Id
    //@GeneratedValue(generator = "porh_generator")            
    @Column(nullable = false)
    private Long id;    
    @Column(name="FECHA_CP", nullable = true)
    private Date fechaCp;
    @Column(name="FECHA_LP", nullable = true)
    private Date fechaLp;
    @Column(name="FECHA_MP", nullable = true)
    private Date fechaMp;
    @Column(name="ID_INDICE_BASICO")
    private Long idIndiceBasico;
    @Column(name="ID_PLAN", nullable = false)
    private Long idPlan;
    @Column(name="ID_TRAMO", nullable = false)
    private Long idTramo;
    @Column(name="META_CP", nullable = true)
    private Double metaCp;
    @Column(name="META_LP", nullable = true)
    private Double metaLp;
    @Column(name="META_MP", nullable = true)
    private Double metaMp;
    @Column(nullable = false)
    private String nombre;

    @Transient
    private Long codigoAutoridad;
    @Transient
    private Boolean permitirSeguimiento;
    @Transient
    private Double metaLogradaCp;
    @Transient
    private Double metaLogradaMp;
    @Transient
    private Double metaLogradaLp;
    public PorhIndices() {
    }

    public PorhIndices(Date fechaCp, Date fechaLp, Date fechaMp,
                       Long id, Long idIndiceBasico, Long idPlan, Long idTramo,
                       Double metaCp, Double metaLp, Double metaMp,
                       String nombre) {
        this.fechaCp = fechaCp;
        this.fechaLp = fechaLp;
        this.fechaMp = fechaMp;
        this.id = id;
        this.idIndiceBasico = idIndiceBasico;
        this.idPlan = idPlan;
        this.idTramo = idTramo;
        this.metaCp = metaCp;
        this.metaLp = metaLp;
        this.metaMp = metaMp;
        this.nombre = nombre;
    }

    public Date getFechaCp() {
        return fechaCp;
    }

    public void setFechaCp(Date fechaCp) {
        this.fechaCp = fechaCp;
    }

    public Date getFechaLp() {
        return fechaLp;
    }

    public void setFechaLp(Date fechaLp) {
        this.fechaLp = fechaLp;
    }

    public Date getFechaMp() {
        return fechaMp;
    }

    public void setFechaMp(Date fechaMp) {
        this.fechaMp = fechaMp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdIndiceBasico() {
        return idIndiceBasico;
    }

    public void setIdIndiceBasico(Long idIndiceBasico) {
        this.idIndiceBasico = idIndiceBasico;
    }

    public Long getIdPlan() {
        return idPlan;
    }

    public void setIdPlan(Long idPlan) {
        this.idPlan = idPlan;
    }

    public Long getIdTramo() {
        return idTramo;
    }

    public void setIdTramo(Long idTramo) {
        this.idTramo = idTramo;
    }

    public Double getMetaCp() {
        return metaCp;
    }

    public void setMetaCp(Double metaCp) {
        this.metaCp = metaCp;
    }

    public Double getMetaLp() {
        return metaLp;
    }

    public void setMetaLp(Double metaLp) {
        this.metaLp = metaLp;
    }

    public Double getMetaMp() {
        return metaMp;
    }

    public void setMetaMp(Double metaMp) {
        this.metaMp = metaMp;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getCodigoAutoridad() {
        return codigoAutoridad;
    }

    public void setCodigoAutoridad(Long codigoAutoridad) {
        this.codigoAutoridad = codigoAutoridad;
    }
    /**
     * Permite establecer cuales son os indicadores a los cuales se permite asociar 
     * valores manuales de seguimiento.
     * El seguimiento es calculado para los indices que se copian de los 
     * indices basicos con codigo menor o igual a 4
     * @return
     */
    public Boolean getPermitirSeguimiento() {
        if(this.idIndiceBasico!=null &&
           this.idIndiceBasico.longValue()>0 &&
           this.idIndiceBasico.longValue() <= 4 ){
               return true;
        }
        return false;
    }
    /**
     * Retorna un arreglo con dos elementos correspondientes a la consulta sql
     * y a la condicion de fecha requeridos para calcular el seguimiento
     * del indicador correspondiente
     * Para los indicadores codigo 3 y 4 retorna un arreglo con tres posiciones
     * porque usa condiciones compuestas
     * @return
     */
    public String[] getSqlSeguimiento(){
        if (this.idIndiceBasico!=null && this.idIndiceBasico.longValue()>0 &&
            this.idIndiceBasico.longValue()<=4){
            
            String datos[] = new String[2];
            
            if(this.idIndiceBasico.longValue()==1){
                datos[0] = "select count(*) " + 
                "from   rurt_concesiones co " + 
                "where  id in (select id_concesion from rurt_captacion where id_fuente = :codigoFuente and id_tramo = :codigoTramo) ";
                datos[1] = "and    fecha_inicio ";
            }
            if(this.idIndiceBasico.longValue()==2){
                datos[0] = "select count(*) " + 
                "from   rurt_permisos_vertimientos " + 
                "where  id in (select id_permiso_vertimiento from rurt_punto_vertimiento where id_fuente = :codigoFuente and id_tramo = :codigoTramo) ";
                datos[1] = "and    vigencias_desde ";
            }
            if(this.idIndiceBasico.longValue()==3){
                datos = new String[3];
                
                datos[0] = "select count(*) " + 
                "from   rurt_permisos_vertimientos " + 
                "where  id in (select id_permiso_vertimiento from rurt_punto_vertimiento where id_fuente = :codigoFuente and id_tramo = :codigoTramo) ";
                datos[1] = " and    vigencias_desde ";
                datos[2] = " and    fecha_exp_plan_cumpl ";                
            }
            if(this.idIndiceBasico.longValue()==4){
                datos = new String[3];
                
                datos[0] = "select count(*) " + 
                "from   rurt_permisos_vertimientos " + 
                "where  id in (select id_permiso_vertimiento from rurt_punto_vertimiento where id_fuente = :codigoFuente and id_tramo = :codigoTramo) ";
                datos[1] = " and    vigencias_desde ";
                datos[2] = " and    fecha_resolucion_psmv ";                
            }
            return datos;
        }
        return null;
    }

    public Double getMetaLogradaCp() {
        return metaLogradaCp;
    }

    public void setMetaLogradaCp(Double metaLogradaCp) {
        this.metaLogradaCp = metaLogradaCp;
    }

    public Double getMetaLogradaMp() {
        return metaLogradaMp;
    }

    public void setMetaLogradaMp(Double metaLogradaMp) {
        this.metaLogradaMp = metaLogradaMp;
    }

    public Double getMetaLogradaLp() {
        return metaLogradaLp;
    }

    public void setMetaLogradaLp(Double metaLogradaLp) {
        this.metaLogradaLp = metaLogradaLp;
    }
    
    public String getSqlSeguimiento(boolean useFechaFinal){
        if(this.idIndiceBasico!=null &&
            this.idIndiceBasico.longValue()>0 &&
            this.idIndiceBasico.longValue()<=4){
            
            String sqls[] = this.getSqlSeguimiento();
            
            if(this.idIndiceBasico.longValue()<=2){                
                if(!useFechaFinal){
                    return sqls[0] + sqls[1] + " <= :fechaInicial ";                    
                }else{
                    String cadena = sqls[0] + sqls[1] + " >= :fechaInicial ";
                    cadena += sqls[1] + " <= :fechaFinal";
                    return cadena;
                }
            }else{
                if(!useFechaFinal){
                    return sqls[0] + sqls[1] + " <= :fechaInicial1" + sqls[2] + " <= :fechaInicial2";
                }else{
                    String cadena =sqls[0] + sqls[1] + " <= :fechaInicial1" + sqls[2] + " <= :fechaInicial2";
                    cadena += sqls[1] + " <= :fechaFinal1" + sqls[2] + " <= :fechaFinal2";
                    return cadena;
                }
            }
        }
        return null;
    }
}
