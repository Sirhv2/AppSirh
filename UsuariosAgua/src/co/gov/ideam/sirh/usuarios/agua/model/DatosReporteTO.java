package co.gov.ideam.sirh.usuarios.agua.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class DatosReporteTO implements Serializable{
   
   
   
   
    @Id
    @Column(name="descripcion")     
    private String descripcion;
    @Column(name="cantidad")    
    private Long cantidad;


    @Transient
    private Long idAutoridad;
    @Transient
    public  String naturalezaUsuarios;
    @Transient
    public  String estadosConcesiones ;
    @Transient
    public  String estadosVertimientos;
    @Transient
    public  String captacionesFuentes;
    @Transient
    public  String vertimientosFuentes;
    @Transient
    public  String puntosMonitoreoFuentes;
    @Transient
    public String usoAguaActividad;
    @Transient
    public String ubicacionUsuarios;
    @Transient
    public String usosCaptaciones ; 
    @Transient
    public  String prediosPorDepartamento ;
  
    @Transient
    public  String prediosPorMunicipio ;

    @Transient
    public  String captacionesPorTipoDeFuente ;
    @Transient
    public  String captacionesPorTipoDeFuenteDet ;
    
    @Transient
    public  String vertimientosPorTipo;
    
    @Transient
    public  String concesionesAnio;
    
    @Transient
    public  String permisosAnio;
    
    public static final String listaAnioConcesiones= " select distinct( EXTRACT(YEAR FROM FECHA_INICIO)) as annio "+
                                                     " from rurt_concesiones  where id_predio in "+
                                                     "   (select id from rurt_predios where  id_usuario in  "+
                                                     "     (select id from rurt_usuarios_agua ) "+
                                                     "    ) AND  FECHA_INICIO is not null order by annio asc ";
    
    public static final String listaAnioConcesionesAut= " select distinct( EXTRACT(YEAR FROM FECHA_INICIO)) as annio "+
                                                     " from rurt_concesiones  where id_predio in "+
                                                     "   (select id from rurt_predios where  id_usuario in  "+
                                                     "     (select id from rurt_usuarios_agua  "+
                                                     "        where id_autoridad= ?1 ) "+
                                                     "    ) AND  FECHA_INICIO is not null order by annio asc ";
    
    
    public static final String listaAnioPermisos= " select distinct( EXTRACT(YEAR FROM vigencias_desde)) as annio "+
                                                     " from rurt_permisos_vertimientos  where id_predio in "+
                                                     "   (select id from rurt_predios where  id_usuario in  "+
                                                     "     (select id from rurt_usuarios_agua ) "+
                                                     "    ) AND  vigencias_desde is not null order by annio asc ";
    
    public static final String listaAnioPermisosAut= " select distinct( EXTRACT(YEAR FROM vigencias_desde)) as annio "+
                                                     " from rurt_permisos_vertimientos  where id_predio in "+
                                                     "   (select id from rurt_predios where  id_usuario in  "+
                                                     "     (select id from rurt_usuarios_agua  "+
                                                     "        where id_autoridad= ?1 ) "+
                                                     "    ) AND  vigencias_desde is not null order by annio asc ";
    
    
    public static final String fechavigencia =" SELECT ('' || AGE(fecha_fin , fecha_inicio )) as  intt FROM rurt_concesiones where id= ?1";
        
        
    public static final String fechavigenciaPermiso ="select ('' || AGE(vigencia_hasta , vigencias_desde )) as intt FROM rurt_permisos_vertimientos WHERE id= ?1  ";
          
        
     
    
    public DatosReporteTO() {
        super();
    
    
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Long getCantidad() {
        return cantidad;
    }

    public void setCantidad(Long cantidad) {
        this.cantidad = cantidad;
    }

    public void setIdAutoridad(Long idAutoridad) {
        this.idAutoridad = idAutoridad;
    }

    public Long getIdAutoridad() {
        return idAutoridad;
    }

    public void setNaturalezaUsuarios(String naturalezaUsuarios) {
        this.naturalezaUsuarios = naturalezaUsuarios;
    }

    public String getNaturalezaUsuarios() {
      naturalezaUsuarios = "SELECT l.valor as descripcion, count(*) cantidad " + 
                                "FROM rurt_concesiones conc " + 
                                "   , rurt_captacion ca " + 
                                "   , rurt_predios p " + 
                                "   , rurt_usuarios_agua ua " + 
                                "   , part_listas l " + 
                                "WHERE ca.tipo_fuente_captacion <> 29 " + 
                                "AND l.id_categoria = 1 " + 
                                "AND ca.id_concesion = conc.id " + 
                                "AND p.id = conc.id_predio " + 
                                "AND l.id = ua.tipo_persona " + 
                                "AND ua.id = p.id_usuario "; 
        if(getIdAutoridad()!=null){
            naturalezaUsuarios=naturalezaUsuarios+ " and ua.id_autoridad="+getIdAutoridad()+" "; 
        }
            naturalezaUsuarios=naturalezaUsuarios+ "  group by l.valor  order by cantidad desc";
        
       return naturalezaUsuarios;
    }
    
  public String getNaturalezaUsuariosSubt() {
      naturalezaUsuarios = "SELECT l.valor as descripcion, count(*) cantidad " + 
                                "FROM rurt_concesiones conc " + 
                                "   , rurt_captacion ca " + 
                                "   , rurt_predios p " + 
                                "   , rurt_usuarios_agua ua " + 
                                "   , part_listas l " + 
                                "WHERE ca.tipo_fuente_captacion = 29 " + 
                                "AND l.id_categoria = 1 " + 
                                "AND ca.id_concesion = conc.id " + 
                                "AND p.id = conc.id_predio " + 
                                "AND l.id = ua.tipo_persona " + 
                                "AND ua.id = p.id_usuario "; 
      if(getIdAutoridad()!=null){
          naturalezaUsuarios=naturalezaUsuarios+ " and ua.id_autoridad="+getIdAutoridad()+" "; 
      }
          naturalezaUsuarios=naturalezaUsuarios+ "  group by l.valor  order by cantidad desc";
    
   
      
     return naturalezaUsuarios;
  }



    public void setEstadosConcesiones(String estadosConcesiones) {
        this.estadosConcesiones = estadosConcesiones;
    }

    public String getEstadosConcesiones() {
        estadosConcesiones = "select 'NO ESPECIFICADO' AS descripcion, count(distinct(rc.num_res_caudal)) as cantidad " + 
                                                        "from rurt_concesiones rc  , rurt_predios p , rurt_usuarios_agua u, rurt_captacion capt " + 
                                                        "where  rc.fecha_fin is null " + 
                                                        "and rc.tipo_novedad is null " + 
                                                        "and p.id=rc.id_predio " + 
                                                        "and u.id=p.id_usuario " +
                                                        "and capt.id_concesion = rc.id " + 
                                                        "and capt.tipo_fuente_captacion <> 29 "; 
        if(getIdAutoridad()!=null)
        estadosConcesiones=  estadosConcesiones+ "       and u.id_autoridad="+getIdAutoridad()+" ";
        estadosConcesiones=  estadosConcesiones+ "       group by rc.num_res_caudal ";
        estadosConcesiones=  estadosConcesiones+ " UNION " + 
                                                        "select 'VIGENTES' AS descripcion, count(distinct(rc.num_res_caudal)) as cantidad " + 
                                                        "from rurt_concesiones rc  , rurt_predios p , rurt_usuarios_agua u, rurt_captacion capt " + 
                                                        "where  rc.fecha_fin >= current_date " + 
                                                        "and rc.tipo_novedad is null " +
                                                        "and p.id=rc.id_predio " + 
                                                        "and u.id=p.id_usuario " +
                                                        "and capt.id_concesion = rc.id " + 
                                                        "and capt.tipo_fuente_captacion <> 29 ";
        
        if(getIdAutoridad()!=null)
        estadosConcesiones=  estadosConcesiones+ "       and u.id_autoridad="+getIdAutoridad()+" ";

        estadosConcesiones=  estadosConcesiones+ " UNION " + 
                                                        "select 'VENCIDAS' AS descripcion, count(distinct(rc.num_res_caudal)) as cantidad " + 
                                                        "from rurt_concesiones rc  , rurt_predios p , rurt_usuarios_agua u, rurt_captacion capt " + 
                                                        "where rc.fecha_fin < current_date " + 
                                                        "and rc.tipo_novedad is null " + 
                                                        "and p.id=rc.id_predio " + 
                                                        "and u.id=p.id_usuario " +
                                                        "and capt.id_concesion = rc.id " + 
                                                        "and capt.tipo_fuente_captacion <> 29 ";
        if(getIdAutoridad()!=null)
        estadosConcesiones=  estadosConcesiones+ "       and u.id_autoridad="+getIdAutoridad()+" ";
        
        estadosConcesiones=  estadosConcesiones+ "       ORDER BY cantidad DESC";
        return estadosConcesiones;
    }
    
  public String getEstadosConcesionesSubt() {
      estadosConcesiones = "select 'NO ESPECIFICADO' AS descripcion, count(distinct(rc.num_res_caudal)) as cantidad " + 
                                                      "from rurt_concesiones rc  , rurt_predios p , rurt_usuarios_agua u, rurt_captacion capt " + 
                                                      "where  rc.fecha_fin is null " + 
                                                      "and rc.tipo_novedad is null " + 
                                                      "and p.id=rc.id_predio " + 
                                                      "and u.id=p.id_usuario " +                                                        "and capt.id_concesion = rc.id " + 
                                                      "and capt.tipo_fuente_captacion = 29 "; 
      if(getIdAutoridad()!=null)
      estadosConcesiones=  estadosConcesiones+ "       and u.id_autoridad="+getIdAutoridad()+" ";
      estadosConcesiones=  estadosConcesiones+ "       group by rc.num_res_caudal ";
      estadosConcesiones=  estadosConcesiones+ " UNION " + 
                                                      "select 'VIGENTES' AS descripcion, count(distinct(rc.num_res_caudal)) as cantidad " + 
                                                      "from rurt_concesiones rc  , rurt_predios p , rurt_usuarios_agua u, rurt_captacion capt " + 
                                                      "where  rc.fecha_fin >= current_date " + 
                                                      "and rc.tipo_novedad is null " +
                                                      "and p.id=rc.id_predio " + 
                                                      "and u.id=p.id_usuario " +
                                                      "and capt.id_concesion = rc.id " + 
                                                      "and capt.tipo_fuente_captacion = 29 ";
      
      if(getIdAutoridad()!=null)
      estadosConcesiones=  estadosConcesiones+ "       and u.id_autoridad="+getIdAutoridad()+" ";

      estadosConcesiones=  estadosConcesiones+ " UNION " + 
                                                      "select 'VENCIDAS' AS descripcion, count(distinct(rc.num_res_caudal)) as cantidad " + 
                                                      "from rurt_concesiones rc  , rurt_predios p , rurt_usuarios_agua u, rurt_captacion capt " + 
                                                      "where rc.fecha_fin < current_date " + 
                                                      "and rc.tipo_novedad is null " + 
                                                      "and p.id=rc.id_predio " + 
                                                      "and u.id=p.id_usuario " +                                                        "and capt.id_concesion = rc.id " + 
                                                      "and capt.tipo_fuente_captacion = 29 ";
      if(getIdAutoridad()!=null)
      estadosConcesiones=  estadosConcesiones+ "       and u.id_autoridad="+getIdAutoridad()+" ";
      
      estadosConcesiones=  estadosConcesiones+ "       ORDER BY cantidad DESC";
      return estadosConcesiones;
  }

    public void setEstadosVertimientos(String estadosVertimientos) {
        this.estadosVertimientos = estadosVertimientos;
    }

    public String getEstadosVertimientos() {
        estadosVertimientos= "select 'NO ESPECIFICADO' AS descripcion, count(distinct(num_res_permiso_vert)) as cantidad " + 
                                                        "from rurt_permisos_vertimientos pv  , rurt_predios p , rurt_usuarios_agua u " + 
                                                        "where  pv.vigencia_hasta is null " + 
                                                        "and pv.tipo_novedad is null " + 
                                                        "and p.id=pv.id_predio " + 
                                                        "and u.id=p.id_usuario " ; 
        if(getIdAutoridad()!=null)  
        estadosVertimientos=  estadosVertimientos+ " and u.id_autoridad="+getIdAutoridad()+" ";                                                                            
                                                                                       
        estadosVertimientos=  estadosVertimientos+ "  UNION " + 
                                                        "select 'VIGENTES' AS descripcion, count(distinct(num_res_permiso_vert)) as cantidad " + 
                                                        "from rurt_permisos_vertimientos pv  , rurt_predios p , rurt_usuarios_agua u " +  
                                                        "where  pv.vigencia_hasta >= current_date " + 
                                                        "and pv.tipo_novedad is null " + 
                                                        "and p.id=pv.id_predio " + 
                                                        "and u.id=p.id_usuario " ; 
        if(getIdAutoridad()!=null)  
        estadosVertimientos=  estadosVertimientos+ " and u.id_autoridad="+getIdAutoridad()+" ";  
      
        estadosVertimientos=  estadosVertimientos+ "  UNION " +               
                                                        "select 'VENCIDOS' AS descripcion, count(distinct(num_res_permiso_vert)) as cantidad " + 
                                                        "from rurt_permisos_vertimientos pv  , rurt_predios p , rurt_usuarios_agua u " +  
                                                        "where  vigencia_hasta < current_date " + 
                                                        "and tipo_novedad is null " + 
                                                        "and p.id=pv.id_predio " + 
                                                        "and u.id=p.id_usuario " ; 
        if(getIdAutoridad()!=null)  
        estadosVertimientos=  estadosVertimientos+ " and u.id_autoridad="+getIdAutoridad()+" ";   
  
        estadosVertimientos=  estadosVertimientos+ "  ORDER BY cantidad DESC"; 
        return estadosVertimientos;
    }
 
    public void setCaptacionesFuentes(String captacionesFuentes) {
        this.captacionesFuentes = captacionesFuentes;
    }

    public String getCaptacionesFuentes() {
        captacionesFuentes = "select  (pl.valor || ' ') ||f.nombre as descripcion, count(*) as cantidad from rurt_captacion r, fntt_fuente f,  part_listas pl " + 
                            " where r.id_fuente=f.id and pl.ID = f.id_tipo_fuente and f.id_tipo_fuente <> 59" ;
                                if(getIdAutoridad()!=null)
                                    captacionesFuentes=captacionesFuentes+ " and f.cod_autoridad="+getIdAutoridad()+" ";
                               captacionesFuentes=captacionesFuentes+ " group by f.nombre,pl.valor  order by cantidad desc";
        
        
        return captacionesFuentes;
    }
    
  public String getCaptacionesFuentesSubt() {
      captacionesFuentes = "select  (pl.valor || ' ') ||f.nombre as descripcion, count(*) as cantidad from rurt_captacion r, fntt_fuente f,  part_listas pl " + 
                          " where r.id_fuente=f.id and pl.ID = f.id_tipo_fuente and f.id_tipo_fuente = 59" ;
                              if(getIdAutoridad()!=null)
                                  captacionesFuentes=captacionesFuentes+ " and f.cod_autoridad="+getIdAutoridad()+" ";
                             captacionesFuentes=captacionesFuentes+ " group by f.nombre,pl.valor  order by cantidad desc";
      
      
      return captacionesFuentes;
  }
    
    
    public String getVertimientosFuentes() {
        vertimientosFuentes = "select  (pl.valor || ' ') ||f.nombre as descripcion, count(*) as cantidad from rurt_punto_vertimiento r, fntt_fuente f,  part_listas pl " + 
                            " where r.id_fuente=f.id and pl.ID = f.id_tipo_fuente" ;
                                if(getIdAutoridad()!=null)
                                    vertimientosFuentes=vertimientosFuentes+ " and f.cod_autoridad="+getIdAutoridad()+" ";
                               vertimientosFuentes=vertimientosFuentes+ " group by f.nombre,pl.valor  order by cantidad desc";
        
        
        return vertimientosFuentes;
    }




    public void setConcesionesAnio(String concesionesAnio) {
        this.concesionesAnio = concesionesAnio;
    }

    public String getConcesionesAnio(Integer anio ) {
        
                               concesionesAnio = " select count(*) as cantidad, EXTRACT(MONTH FROM cc.FECHA_INICIO) as descripcion " + 
                                                 " from rurt_concesiones cc  where cc.id_predio in " + 
                                                 "           (select pp.id from rurt_predios pp where  pp.id_usuario in  " + 
                                                 "                (select uu.id from rurt_usuarios_agua uu " ;
        if(getIdAutoridad()!=null){
           concesionesAnio = concesionesAnio +   "                   where uu.id_autoridad= "+getIdAutoridad()+" "; 
           }
           concesionesAnio = concesionesAnio +   "                 ) " + 
                                                 "           ) and  EXTRACT(YEAR  from CC.FECHA_INICIO )  =" +anio + 
                                                 " group by  EXTRACT(MONTH FROM cc.FECHA_INICIO), EXTRACT(YEAR FROM cc.FECHA_INICIO) " +
                                                 " order by  descripcion asc    " ;
    
         
        return concesionesAnio;
    }
    
    
    
    public void setPermisosAnio(String permisosAnio) {
        this.permisosAnio = permisosAnio;
    }

    public String getPermisosAnio(Integer anio ) {
        
                               permisosAnio = " select count(*) as cantidad, EXTRACT(MONTH FROM cc.vigencias_desde) as descripcion " + 
                                                 " from rurt_permisos_vertimientos cc  where cc.id_predio in " + 
                                                 "           (select pp.id from rurt_predios pp where  pp.id_usuario in  " + 
                                                 "                (select uu.id from rurt_usuarios_agua uu " ;
        if(getIdAutoridad()!=null){
           permisosAnio = permisosAnio +   "                   where uu.id_autoridad= "+getIdAutoridad()+" "; 
           }
           permisosAnio = permisosAnio +   "                 ) " + 
                                                 "           ) and  EXTRACT(YEAR  from CC.vigencias_desde )  =" +anio + 
                                                 " group by  EXTRACT(MONTH FROM cc.vigencias_desde), EXTRACT(YEAR FROM cc.vigencias_desde) " +
                                                 " order by  descripcion asc    " ;
    
         
        return permisosAnio;
    }


    public void setUsoAguaActividad(String usoAguaActividad) {
        this.usoAguaActividad = usoAguaActividad;
    }

    public String getUsoAguaActividad() {
        usoAguaActividad = "select '[' || ciu_codigo || ']' || ciu_descripcion AS descripcion, count(*) as cantidad " +
                                                        " from rurt_usuarios_agua u, part_ciiu c " + 
                                                        " where u.actividad_ciiu = c.ciu_id " + 
                                                        " group by ciu_codigo, ciu_descripcion " + 
                                                        " ORDER BY descripcion ASC"; 
        return usoAguaActividad;
    }

    public void setUbicacionUsuarios(String ubicacionUsuarios) {
      
        this.ubicacionUsuarios = ubicacionUsuarios;
    }

    public String getUbicacionUsuarios() {
        ubicacionUsuarios = "select l.valor as descripcion, count(*) as cantidad\n" + 
        "                                                         from rurt_predios p\n" + 
        "                                                            , part_listas l \n" + 
        "                                                            , rurt_usuarios_agua u\n" + 
        "                                                            , rurt_concesiones conc\n" + 
        "                                                            , rurt_captacion ca\n" + 
        "                                                         where p.tipo_suelo = l.id \n" + 
        "                                                         and   l.id_categoria = 5 \n" + 
        "                                                         and   u.id=p.id_usuario\n" + 
        "                                                         and conc.id_predio = p.id\n" + 
        "                                                         and ca.id_concesion = conc.id\n" + 
        "                                                         and ca.tipo_fuente_captacion <> 29 " ;
        if(getIdAutoridad()!=null)
            ubicacionUsuarios=ubicacionUsuarios+ " and  u.id_autoridad ="+getIdAutoridad()+" ";
            ubicacionUsuarios=ubicacionUsuarios+ " group by l.valor order by cantidad desc";
        
        
        return ubicacionUsuarios;
    }
    
  public String getUbicacionUsuariosSubt() {
      ubicacionUsuarios = "select l.valor as descripcion, count(*) as cantidad\n" + 
      "                                                         from rurt_predios p\n" + 
      "                                                            , part_listas l \n" + 
      "                                                            , rurt_usuarios_agua u\n" + 
      "                                                            , rurt_concesiones conc\n" + 
      "                                                            , rurt_captacion ca\n" + 
      "                                                         where p.tipo_suelo = l.id \n" + 
      "                                                         and   l.id_categoria = 5 \n" + 
      "                                                         and   u.id=p.id_usuario\n" + 
      "                                                         and conc.id_predio = p.id\n" + 
      "                                                         and ca.id_concesion = conc.id\n" + 
      "                                                         and ca.tipo_fuente_captacion = 29 " ;
      if(getIdAutoridad()!=null)
          ubicacionUsuarios=ubicacionUsuarios+ " and  u.id_autoridad ="+getIdAutoridad()+" ";
          ubicacionUsuarios=ubicacionUsuarios+ " group by l.valor order by cantidad desc";
      
      
      return ubicacionUsuarios;
  }

    public void setUsosCaptaciones(String usosCaptaciones) {
        this.usosCaptaciones = usosCaptaciones;
    }

    public String getUsosCaptaciones() {
        usosCaptaciones = "    select pl.valor as descripcion, count(rcu.tipo_uso) as cantidad from rurt_captacion_uso rcu, part_listas pl, rurt_captacion rca, fntt_fuente f " + 
                                                    "    where rcu.tipo_uso=pl.id and (pl.id_categoria=45) and pl.id !=627 " + 
                                                    "    and rca.id=rcu.id_captacion and rca.id_fuente=f.id and rca.tipo_fuente_captacion <> 29 ";
                                                    if(getIdAutoridad()!=null)
                                                        usosCaptaciones=usosCaptaciones+ " and f.cod_autoridad="+getIdAutoridad()+" ";
                                                    usosCaptaciones=usosCaptaciones+ "    group by  pl.valor ";
                                                    usosCaptaciones=usosCaptaciones+ "    union        "+             
                                                    "    select pl.valor as descripcion, count(rcu.tipo_uso) as cantidad from rurt_captacion_uso rcu, part_listas pl, rurt_captacion rca, fntt_fuente f " + 
                                                    "    where rcu.tipo_uso=627 and rcu.tipo not like '%@%' " +
                                                    "   and cast(rcu.tipo as integer)=pl.id  " +
                                                    "    and rca.id=rcu.id_captacion and rca.id_fuente=f.id and rca.tipo_fuente_captacion <> 29 "    ;   
                                                    if(getIdAutoridad()!=null)
                                                        usosCaptaciones=usosCaptaciones+ " and f.cod_autoridad="+getIdAutoridad()+" ";
                                                    usosCaptaciones=usosCaptaciones+
                                                    "    group by  pl.valor order by cantidad desc ";
        return usosCaptaciones;
    }
    
  public String getUsosCaptacionesSubt() {
      usosCaptaciones = "    select pl.valor as descripcion, count(rcu.tipo_uso) as cantidad from rurt_captacion_uso rcu, part_listas pl, rurt_captacion rca, fntt_fuente f " + 
                                                  "    where rcu.tipo_uso=pl.id and (pl.id_categoria=45) and pl.id !=627 " + 
                                                  "    and rca.id=rcu.id_captacion and rca.id_fuente=f.id and rca.tipo_fuente_captacion = 29 ";
                                                  if(getIdAutoridad()!=null)
                                                      usosCaptaciones=usosCaptaciones+ " and f.cod_autoridad="+getIdAutoridad()+" ";
                                                  usosCaptaciones=usosCaptaciones+ "    group by  pl.valor ";
                                                  usosCaptaciones=usosCaptaciones+ "    union        "+             
                                                  "    select pl.valor as descripcion, count(rcu.tipo_uso) as cantidad from rurt_captacion_uso rcu, part_listas pl, rurt_captacion rca, fntt_fuente f " + 
                                                  "    where rcu.tipo_uso=627 and rcu.tipo not like '%@%' " +
                                                  "   and cast(rcu.tipo as integer)=pl.id  " +
                                                  "    and rca.id=rcu.id_captacion and rca.id_fuente=f.id and rca.tipo_fuente_captacion = 29 "    ;
                                                  if(getIdAutoridad()!=null)
                                                      usosCaptaciones=usosCaptaciones+ " and f.cod_autoridad="+getIdAutoridad()+" ";
                                                  usosCaptaciones=usosCaptaciones+
                                                  "    group by  pl.valor order by cantidad desc ";
      return usosCaptaciones;
  }


    public void setPrediosPorDepartamento(String prediosPorDepartamento) {
        this.prediosPorDepartamento = prediosPorDepartamento;
    }

  public String getPrediosPorDepartamento() {
          prediosPorDepartamento = " select count(rp.id_divipola_depto) cantidad ,d.nombre descripcion \n" + 
                                    "from rurt_predios rp \n" + 
                                    "   , PART_DIVIPOLA d \n" + 
                                    "   , RURT_USUARIOS_AGUA ua\n" + 
                                    "   , rurt_predios p\n" + 
                                    "   , rurt_concesiones conc\n" + 
                                    "   , rurt_captacion ca\n" + 
                                    "where ua.id=rp.id_usuario \n" + 
                                    "  and rp.ID_DIVIPOLA_DEPTO=d.DIVIPOLA_ID\n" + 
                                    "  and p.id_usuario = ua.id\n" + 
                                    "  and conc.id_predio = p.id\n" + 
                                    "  and ca.id_concesion = conc.id\n" + 
                                    "  and ca.tipo_fuente_captacion <> 29" ;
                                      if(getIdAutoridad()!=null)
                                          prediosPorDepartamento=prediosPorDepartamento+ " and ua.ID_AUTORIDAD="+getIdAutoridad()+" ";
                                      prediosPorDepartamento=prediosPorDepartamento+ "    group by rp.ID_DIVIPOLA_DEPTO, d.nombre order by count(rp.id_divipola_depto) desc ";
                                                     

          return prediosPorDepartamento;
      }
    
  
    public String getPrediosPorMunicipio() {
        prediosPorMunicipio = "  select count(rp.id_divipola_depto) cantidad ,d.nombre  ||', '|| mun.nombre as descripcion \n" + 
                                "from rurt_predios rp \n" + 
                                "  , PART_DIVIPOLA d \n" + 
                                "  , PART_DIVIPOLA mun \n" + 
                                "  , RURT_USUARIOS_AGUA ua\n" + 
                                "  , rurt_concesiones conc\n" + 
                                "  , rurt_captacion ca\n" + 
                                "where ua.id=rp.id_usuario \n" + 
                                "and rp.ID_DIVIPOLA_DEPTO=d.DIVIPOLA_ID\n" + 
                                "and rp.ID_DIVIPOLA_MUNICIPIO=mun.DIVIPOLA_ID\n" + 
                                "and conc.id_predio = rp.id\n" + 
                                "and ca.id_concesion = conc.id\n" + 
                                "and ca.tipo_fuente_captacion <> 29" ;
                                    if(getIdAutoridad()!=null)
                                        prediosPorMunicipio=prediosPorMunicipio+ " and ua.ID_AUTORIDAD="+getIdAutoridad()+" ";
                                    prediosPorMunicipio=prediosPorMunicipio+ "    group by rp.ID_DIVIPOLA_DEPTO, d.nombre, mun. nombre order by count(rp.id_divipola_depto) desc  ";
                                                   
      
        return prediosPorMunicipio;
    }
    
  public String getPrediosPorDepartamentoSubt() {
      prediosPorDepartamento = "  select count(rp.id_divipola_depto) cantidad ,d.nombre descripcion \n" + 
                              "from rurt_predios rp \n" + 
                              "   , PART_DIVIPOLA d \n" + 
                              "   , RURT_USUARIOS_AGUA ua\n" + 
                              "   , rurt_predios p\n" + 
                              "   , rurt_concesiones conc\n" + 
                              "   , rurt_captacion ca\n" + 
                              "where ua.id=rp.id_usuario \n" + 
                              "  and rp.ID_DIVIPOLA_DEPTO=d.DIVIPOLA_ID\n" + 
                              "  and p.id_usuario = ua.id\n" + 
                              "  and conc.id_predio = p.id\n" + 
                              "  and ca.id_concesion = conc.id\n" + 
                              "  and ca.tipo_fuente_captacion = 29" ;
            if(getIdAutoridad()!=null)
                prediosPorDepartamento=prediosPorDepartamento+ " and ID_AUTORIDAD="+getIdAutoridad()+" ";
            prediosPorDepartamento=prediosPorDepartamento+ "    group by rp.ID_DIVIPOLA_DEPTO, d.nombre order by count(rp.id_divipola_depto) desc  ";
                           
     
      return prediosPorDepartamento;
  }
  
  public String getPrediosPorMunicipioSubt() {
      prediosPorMunicipio = "  select count(rp.id_divipola_depto) cantidad ,d.nombre  ||', '|| mun.nombre as descripcion \n" + 
                              "from rurt_predios rp \n" + 
                              "  , PART_DIVIPOLA d \n" + 
                              "  , PART_DIVIPOLA mun \n" + 
                              "  , RURT_USUARIOS_AGUA ua\n" + 
                              "  , rurt_concesiones conc\n" + 
                              "  , rurt_captacion ca\n" + 
                              "where ua.id=rp.id_usuario \n" + 
                              "and rp.ID_DIVIPOLA_DEPTO=d.DIVIPOLA_ID\n" + 
                              "and rp.ID_DIVIPOLA_MUNICIPIO=mun.DIVIPOLA_ID\n" + 
                              "and conc.id_predio = rp.id\n" + 
                              "and ca.id_concesion = conc.id\n" + 
                              "and ca.tipo_fuente_captacion <> 29" ;
                                  if(getIdAutoridad()!=null)
                                      prediosPorMunicipio=prediosPorMunicipio+ " and ua.ID_AUTORIDAD="+getIdAutoridad()+" ";
                                  prediosPorMunicipio=prediosPorMunicipio+ "    group by rp.ID_DIVIPOLA_DEPTO, d.nombre, mun. nombre order by count(rp.id_divipola_depto) desc  ";
                                                 
    
      return prediosPorMunicipio;
  }

    public void setCaptacionesPorTipoDeFuente(String captacionesPorTipoDeFuente) {
        this.captacionesPorTipoDeFuente = captacionesPorTipoDeFuente;
    }

    public String getCaptacionesPorTipoDeFuente() {
        captacionesPorTipoDeFuente=" select count(*) as cantidad, 'Subterránea' as descripcion from FNTT_FUENTE f join RURT_CAPTACION rc  on rc.id_fuente=f.id"+
                                    " where f.ID_TIPO_FUENTE=59";
        if(getIdAutoridad()!=null)
            captacionesPorTipoDeFuente=captacionesPorTipoDeFuente+ " and  f.COD_AUTORIDAD="+getIdAutoridad()+"";
         captacionesPorTipoDeFuente=captacionesPorTipoDeFuente+ "  union";
         captacionesPorTipoDeFuente=captacionesPorTipoDeFuente+  " select count(*)  as cantidad , 'Superficial' as descripcion from FNTT_FUENTE f  join RURT_CAPTACION rc   on rc.id_fuente=f.id";
         captacionesPorTipoDeFuente=captacionesPorTipoDeFuente+ " and f.ID_TIPO_FUENTE!=59";
         if(getIdAutoridad()!=null)
            captacionesPorTipoDeFuente=captacionesPorTipoDeFuente+ " and  f.COD_AUTORIDAD="+getIdAutoridad()+"";
        return captacionesPorTipoDeFuente;
    }



    public String getVertimientosPorTipo() {
        vertimientosPorTipo=" select count(*) as cantidad,tp.valor as descripcion   " + 
        "                             from  rurt_punto_vertimiento v  , part_listas tp ,rurt_permisos_vertimientos cc " + 
        "                             where tp.id= v.tipo_vertimiento and   V.ID_PERMISO_VERTIMIENTO = CC.ID  " + 
        "                             and  cc.id_predio in " + 
        "                                                 (select pp.id from rurt_predios pp where  pp.id_usuario in  " + 
        "                                                    (select uu.id from rurt_usuarios_agua uu" ;
        if(getIdAutoridad()!=null)
        vertimientosPorTipo= vertimientosPorTipo+"             where uu.id_autoridad="+getIdAutoridad()+"";
        vertimientosPorTipo= vertimientosPorTipo+          " ) " + 
                                         "                ) " + 
                                         " group by tp.valor";
             
        return vertimientosPorTipo;
    }


    public void setCaptacionesPorTipoDeFuenteDet(String captacionesPorTipoDeFuenteDet) {
        this.captacionesPorTipoDeFuenteDet = captacionesPorTipoDeFuenteDet;
    }

    public String getCaptacionesPorTipoDeFuenteDet() {
        
        captacionesPorTipoDeFuenteDet="select count(*) as cantidad , pl.valor as descripcion ,( case when pl.id!=59 then 'Sup' when pl.id=59 then 'Sub' end) tipo "+
        "from FNTT_FUENTE f  "+
        "join PART_LISTAS pl on f.ID_TIPO_FUENTE=pl.id "+
        "join RURT_CAPTACION rc   on rc.id_fuente=f.id ";
        if(getIdAutoridad()!=null)
           captacionesPorTipoDeFuenteDet=captacionesPorTipoDeFuenteDet+ "  and  f.COD_AUTORIDAD="+getIdAutoridad()+"";
        captacionesPorTipoDeFuenteDet=captacionesPorTipoDeFuenteDet+"group by pl.valor, pl.id order by tipo, count(*)";
        return captacionesPorTipoDeFuenteDet;
    }
    /**
     * Retorna un SQL para generar la grafica de formacion de especialistas
     * @return
     */
    public String getFormacionEspecialistas() {
        String sql = "select l.valor as descripcion, count(distinct(f.id_especialista)) as cantidad " +
                                                        " from act_especialista_formacion f, part_listas l " + 
                                                        " where f.id_formacion = l.id " + 
                                                        " and   l.id_categoria = 62 " + 
                                                        " group by f.id_formacion,valor order by valor asc ";        
        
        return sql;
    }
    
    /**
     * Retorna un SQL para generar la grafica de investigacion de especialistas
     * @return
     */
    public String getInvestigacionEspecialistas() {
        String sql = "select l.valor as descripcion, count(*) as cantidad " +
                                                        " from act_especilistas_prop a, part_listas l " + 
                                                        " where  a.id_lista = l.id " + 
                                                        " and    a.id_categoria = 59 " + 
                                                        " group by valor order by valor asc ";        
        
        return sql;
    }
    /**
     * Retorna un SQL para generar la grafica de investigacion de especialistas
     * @return
     */
    public String getGestionEspecialistas() {
        String sql = "select l.valor as descripcion, count(*) as cantidad " +
                                                        " from act_especilistas_prop a, part_listas l " + 
                                                        " where  a.id_lista = l.id " + 
                                                        " and    a.id_categoria = 58 " + 
                                                        " group by valor order by valor asc ";        
        
        return sql;
    }

    public void setVertimientosFuentes(String vertimientosFuentes) {
        this.vertimientosFuentes = vertimientosFuentes;
    }

    public String getVertimientosFuentes1() {
        return vertimientosFuentes;
    }

    public void setVertimientosPorTipo(String vertimientosPorTipo) {
        this.vertimientosPorTipo = vertimientosPorTipo;
    }

    public void setPrediosPorMunicipio(String prediosPorMunicipio) {
        this.prediosPorMunicipio = prediosPorMunicipio;
    }
    
    
    public String getPuntosMonitoreoFuentes() {
        puntosMonitoreoFuentes = "select  (pl.valor || ' ') ||f.nombre as descripcion, count(*) as cantidad from calt_punto_monitoreo r, fntt_fuente f,  part_listas pl " + 
                            " where r.id_fuente=f.id and pl.ID = f.id_tipo_fuente" ;
                                if(getIdAutoridad()!=null)
                                    puntosMonitoreoFuentes=puntosMonitoreoFuentes+ " and r.id_autoridad="+getIdAutoridad()+" ";
                               puntosMonitoreoFuentes=puntosMonitoreoFuentes+ " group by f.nombre,pl.valor  order by cantidad desc";
        
        
        return puntosMonitoreoFuentes;
    }


    public void setPuntosMonitoreoFuentes(String puntosMonitoreoFuentes) {
        this.puntosMonitoreoFuentes = puntosMonitoreoFuentes;
    }
}
