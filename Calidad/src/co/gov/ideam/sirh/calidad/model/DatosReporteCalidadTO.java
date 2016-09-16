package co.gov.ideam.sirh.calidad.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class DatosReporteCalidadTO implements Serializable{
    
    public static final String listaAutoridadesConMuestras  = "Select Distinct  aut.id, aut.sigla, aut.nombre, aut.endpointnovedades, aut.fechaultimasincronizacion, aut.numnovedades " + 
                                                               "From calt_muestra mm " + 
                                                               "     left join autoridades aut on  mm.id_autoridad = aut.id " + 
                                                               "     left join calt_punto_monitoreo pm on pm.id = mm.id_punto " + 
                                                               "     left join fntt_fuente ft on ft.id = pm.id_fuente " + 
                                                               "Union All " + 
                                                               "Select aut.id, aut.sigla, aut.nombre, aut.endpointnovedades, aut.fechaultimasincronizacion, aut.numnovedades " + 
                                                               "From  autoridades aut " + 
                                                               "Where aut.id = 1 " + 
                                                               "Order by sigla Asc ";
    
    public static final String listaFuentesMuestrasAutoridades  =  " Select * From (select f.id , aa.sigla || ' - ' || pl.valor || ' ' || f.nombre as nombre  , " + 
                                                            "     f.id_tipo_fuente, f.descripcion, f.cod_autoridad , f.es_compartida , f.codigo_cuencaaa, f.id_provinciahidro, f.unidadhidro " + 
                                                            " from fntt_fuente f , autoridades aa, part_listas pl  " + 
                                                            " where f.id in ( select id_fuente  " + 
                                                            "                from calt_punto_monitoreo pm , calt_muestra mm , calt_medicion med  " + 
                                                            "                where pm.id=mm.id_punto and mm.id=med.id_muestra " + 
                                                            "                     and pl.id= f.id_tipo_fuente ) " + 
                                                            "     and  f.cod_autoridad = aa.id ) " ;
                                                                                                                        
   public static final String listaFuentesMuestrasIdeam  =  "  Select * From (select  f.id ,aa.sigla || ' - ' || pl.valor || ' ' || f.nombre as nombre , f.id_tipo_fuente, " + 
                                                            "         f.descripcion ,aa.id as cod_autoridad , f.es_compartida , f.codigo_cuencaaa, f.id_provinciahidro, f.unidadhidro " + 
                                                            " from fntt_fuente f, part_listas pl,  autoridades aa   " + 
                                                            " where pl.id= f.id_tipo_fuente    " + 
                                                            "       and f.id in  (select id_fuente from sirhv_part_puntos_muestras_fq pmm, sirhv_punto_monitoreo_fq pm " + 
                                                            "                            where pm.id = pmm.id_punto" +
                                                                                        " and id_fuente is not null group  by id_fuente) )" ;                                                          
    
    public static final String listaPuntosMonitoreoMuestrasAutoridades  =  " Select * " + 
                                                                " From (Select pm.id , aa.sigla || ' - ' || pl.valor || ' ' || f.nombre ||' - ' || pm.nombre as nombre , " + 
                                                                "      pm.tipo_punto, pm.ubicacion, pm.id_fuente, pm.id_tramo, pm.latitud_grados,   " + 
                                                                "      pm.latitud_minutos, pm.latitud_segundos, pm.longitud_grados, pm.longitud_minutos,   " + 
                                                                "      pm.longitud_segundos, pm.altitud, pm.descripcion_acceso, pm.id_municipio,   " + 
                                                                "      pm.id_subzona, pm.id_autoridad, pm.sist_ref, pm.id_departamento, pm.id_area,   " + 
                                                                "      pm.id_zona, pm.id_vertimiento, pm.id_captacion " + 
                                                                " from calt_punto_monitoreo pm, fntt_fuente f , autoridades aa , part_listas pl   " + 
                                                                " where pm.id in( Select distinct(id_punto) From calt_muestra  " + 
                                                                "                where id in( select distinct(id_muestra) from calt_medicion  ) )  " + 
                                                                "    and  f.cod_autoridad = aa.id and  pm.id_autoridad= aa.id " + 
                                                                "    and  pm.id_fuente = f.id and f.id_tipo_fuente = pl.id )" ;
                                                                
    public static final String listaPuntosMonitoreoMuestrasIdeam  =  " Select * " +
                                                                    " From (Select pm.id , aa.sigla || ' - ' || pl.valor || ' ' || f.nombre ||' - ' || pm.punto as nombre , " + 
                                                                    "      pm.tipo_punto, pm.ubicacion, pm.id_fuente, pm.id_tramo, pm.gradoslat as latitud_grados,  " + 
                                                                    "      pm.minutoslat as latitud_minutos, pm.segundoslat as latitud_segundos, pm.gradoslong as longitud_grados, pm.minutoslong as longitud_minutos, " + 
                                                                    "      pm.segundoslong as longitud_segundos, pm.altitud, pm.descripcionacceso as descripcion_acceso, pm.id_municipio,   " + 
                                                                    "      pm.id_subzona, pm.id_autoridad, pm.sistema_referencia as sist_ref, pm.id_departamento, pm.id_area,   " + 
                                                                    "      pm.id_zona, pm.id_vertimiento, pm.id_captacion " + 
                                                                    " From sirhv_punto_monitoreo_fq pm, part_ref_pto_mon_subzonas prpms, fntt_fuente f , autoridades aa , part_listas pl " + 
                                                                    " Where  pm.id = prpms.id_punto_ideam and pm.id_fuente = prpms.id_fuente " + 
                                                                    "       and prpms.id_fuente = f.id and  pm.id_autoridad= aa.id and f.id_tipo_fuente = pl.id )"; 
                                                                    
    
     public static final String listaParametrosFuenteIdeam =  " select distinct supacodigoparametro as id, parametro as valor, null id_categoria " + 
                                                             " from sirhv_muestras_fq  " + 
                                                             " where  estacionid in (select id  " + 
                                                             "                        from  sirhv_punto_monitoreo_fq  " + 
                                                             "                        where id_fuente = ?1 ) " + 
                                                             " order by parametro ";
     
    public static final String listaParametrosPuntosIdeam =  " select distinct supacodigoparametro as id, parametro as valor, null id_categoria " + 
                                                             " from sirhv_muestras_fq  " + 
                                                             " where  estacionid = ?1 " + 
                                                             " order by parametro";
            
    public static final String nroMuestrasPorTipoIdeam =  " select plist.valor as descripcion, count(*) as cantidad " + 
                                                          " from  sirhv_muestras_fq mm , part_listas plist, " + 
                                                          "       sirhv_punto_monitoreo_fq pmfq " + 
                                                          " where plist.id = id_tipomuestrasirh " + 
                                                          "       and id_tipomuestrasirh != 943 and mm.estacionid = pmfq.id  " + 
                                                          " group by plist.valor order by cantidad desc " ;
    
    public static final String nroMuestrasPorAnioIdeam = " select EXTRACT(YEAR  from  mm.fechamuestreo) as descripcion, count(*) as cantidad " + 
                                                         " from  sirhv_muestras_fq mm , part_listas plist, " + 
                                                         "        sirhv_punto_monitoreo_fq pmfq   " + 
                                                         " where plist.id = id_tipomuestrasirh " + 
                                                         "      and id_tipomuestrasirh != 943  and mm.estacionid = pmfq.id " + 
                                                         " group by EXTRACT(YEAR  from  mm.fechamuestreo)  order by cantidad desc " ;
    
    public static final String nroMuestrasPorTipoCriterios =    " select descripcion, sum(cantidad) as cantidad  " + 
                                                                " from (select  plist.valor as descripcion, count(*) as cantidad,                   " + 
                                                                "         mm.id_autoridad, pm.id_area, pm.id_zona, pm.id_subzona,     " + 
                                                                "        pm.id_departamento, pm.id_municipio, pm.id_fuente, mm.id_punto,      " + 
                                                                "        mm.fecha_muestreo as fecha_ini, mm.fecha_muestreo as fecha_fin                     " + 
                                                                "      from  calt_muestra mm , part_listas plist, calt_punto_monitoreo pm   " + 
                                                                "      where plist.id = mm.tipo_muestra and mm.id_punto = pm.id   " + 
                                                                "      group by  plist.valor, mm.id_autoridad, pm.id_area, pm.id_zona, pm.id_subzona,      " + 
                                                                "          pm.id_departamento, pm.id_municipio, pm.id_fuente, mm.id_punto,   " + 
                                                                "          mm.fecha_muestreo, mm.fecha_muestreo ) " ;
    
    public static final String nroMuestrasPorAnoCriterios =     " select descripcion, sum(cantidad) as cantidad   " + 
                                                                " from (select EXTRACT(YEAR  from  mm.fecha_muestreo) as descripcion, count(*) as cantidad,  " + 
                                                                "         mm.id_autoridad, pm.id_area, pm.id_zona, pm.id_subzona,     " + 
                                                                "         pm.id_departamento, pm.id_municipio, pm.id_fuente, mm.id_punto,   " + 
                                                                "         mm.fecha_muestreo as fecha_ini, mm.fecha_muestreo as fecha_fin                  " + 
                                                                "       from  calt_muestra mm , part_listas plist, calt_punto_monitoreo pm  " + 
                                                                "       where plist.id = mm.tipo_muestra and mm.id_punto = pm.id  " + 
                                                                "       group by  plist.valor, mm.id_autoridad, pm.id_area, pm.id_zona, pm.id_subzona,     " + 
                                                                "         pm.id_departamento, pm.id_municipio, pm.id_fuente, mm.id_punto,  " + 
                                                                "         mm.fecha_muestreo, mm.fecha_muestreo ) " ;   
      
    public static final String nroMuestrasPorTipoCriteriosIdeam =   " select descripcion, count(*) as cantidad  " + 
                                                                    " from (select distinct plist.valor as descripcion, " + 
                                                                    "          pmfq.id_autoridad, pmfq.id_area, pmfq.id_zona, pmfq.id_subzona,      " + 
                                                                    "          pmfq.id_departamento, pmfq.id_municipio, pmfq.id_fuente,mm.estacionid as id_punto,    " + 
                                                                    "          to_date(to_char (mm.fechamuestreo,'DD/MM/YYYY'),'DD/MM/YYYY') as fecha_ini,    " + 
                                                                    "          to_date(to_char (mm.fechamuestreo,'DD/MM/YYYY'),'DD/MM/YYYY') as fecha_fin                   " + 
                                                                    "      from  sirhv_muestras_fq mm , part_listas plist, sirhv_punto_monitoreo_fq pmfq    " + 
                                                                    "      where plist.id = id_tipomuestrasirh and id_tipomuestrasirh != 943 and mm.estacionid = pmfq.id             " + 
                                                                    "      group by id_tipomuestrasirh, plist.valor,  pmfq.id_autoridad, pmfq.id_area, pmfq.id_zona,    " + 
                                                                    "                pmfq.id_subzona, pmfq.id_departamento, pmfq.id_municipio, pmfq.id_fuente,     " + 
                                                                    "                mm.estacionid, mm.fechamuestreo, mm.fechamuestreo  ) " ;
      
    public static final String nroMuestrasPorAnoCriteriosIdeam  =   "  select descripcion, count(*) as cantidad   " + 
                                                                    " from (select Distinct EXTRACT(YEAR  from  mm.fechamuestreo) as descripcion, count(*) as cantidad,  " + 
                                                                    "          pmfq.id_autoridad, pmfq.id_area, pmfq.id_zona, pmfq.id_subzona,     " + 
                                                                    "          pmfq.id_departamento, pmfq.id_municipio, pmfq.id_fuente,mm.estacionid as id_punto,   " + 
                                                                    "          to_date(to_char (mm.fechamuestreo,'DD/MM/YYYY'),'DD/MM/YYYY') as fecha_ini,   " + 
                                                                    "          to_date(to_char (mm.fechamuestreo,'DD/MM/YYYY'),'DD/MM/YYYY') as fecha_fin                  " + 
                                                                    "        from  sirhv_muestras_fq mm , part_listas plist, sirhv_punto_monitoreo_fq pmfq   " + 
                                                                    "        where plist.id = id_tipomuestrasirh and id_tipomuestrasirh != 943  and mm.estacionid = pmfq.id           " + 
                                                                    "        group by mm.id_tipomuestrasirh, plist.valor,  pmfq.id_autoridad, pmfq.id_area, pmfq.id_zona,   " + 
                                                                    "                pmfq.id_subzona, pmfq.id_departamento, pmfq.id_municipio, pmfq.id_fuente,    " + 
                                                                    "                mm.estacionid, mm.fechamuestreo, mm.fechamuestreo ) "  ;
    
     public static final String nroMuestrasPorAnioFuente =    " select descripcion, count(*) as cantidad " +   
                                                              " from (select pm.nombre as descripcion, mm.id_autoridad, pm.id_area, pm.id_zona, pm.id_subzona, " +   
                                                                      "     pm.id_departamento, pm.id_municipio, pm.id_fuente, mm.id_punto, " +   
                                                                      "     mm.fecha_muestreo as fecha_ini, mm.fecha_muestreo as fecha_fin " + 
                                                                      " from calt_punto_monitoreo pm , calt_muestra mm  " + 
                                                                      " where pm.id=mm.id_punto) " ;  
     
    public static final String nroMuestrasPorAnioFuenteDatos =  " select id, fuente, puntomonitoreo, area, zona, subzona, departamento, municipio, latitud, longitud," +
                                                                "  altitud, nromuestra, fecha_ini as fechamuestreo " +
                                                                " from( Select rownum as id, pm.id_fuente, (aut.sigla || ' - ' || UPPER (ft.nombre)) AS fuente,pm.id as id_punto,  " +
                                                                "         UPPER (pm.nombre) AS puntomonitoreo,  pm.id_autoridad, pm.id_area, z_area.valor AS area,  " +
                                                                "         pm.id_zona, z_zona.valor AS zona, pm.id_subzona, z_subzona.valor AS subzona,  " +
                                                                "           pm.id_departamento, dep.nombre AS departamento,  pm.id_municipio, mun.nombre AS municipio,  " +
                                                                "           (((((pm.latitud_grados || 'º ') || pm.latitud_minutos) || ''' ')  " +
                                                                "                   || ROUND (pm.latitud_segundos, 2)) || '''') || ''''AS latitud,  " +                                    
                                                                "              (((((pm.longitud_grados || 'º ') || pm.longitud_minutos) || ''' ')  " + 
                                                                "                   || ROUND (pm.longitud_segundos, 2)) || '''') || '''' AS longitud,  " +
                                                                "           pm.altitud, mm.nro_muestra as nromuestra, mm.fecha_muestreo as fecha_ini, mm.fecha_muestreo as fecha_fin  " +            
                                                                "         from calt_punto_monitoreo pm   " +
                                                                "         left join calt_muestra mm on mm.id_punto= pm.id   " +
                                                                "         left join  autoridades aut on aut.id = pm.id_autoridad  " +         
                                                                "         left join part_zonific_listas z_area on pm.id_area = z_area.id  " +
                                                                "         left join part_zonific_listas z_zona on pm.id_zona = z_zona.id  " +
                                                                "         left join part_zonific_listas z_subzona on pm.id_subzona = z_subzona.id  " +
                                                                "         left join part_divipola dep on pm.id_departamento = dep.divipola_id " +                   
                                                                "         left join part_divipola mun on pm.id_municipio = mun.divipola_id  " +
                                                                "         left join fntt_fuente ft on ft.id = pm.id_fuente ) " ;
   
    public static final String nroMuestrasPorAnioFuenteIdeam =  "select descripcion, count(*) as cantidad     " + 
                                                                " from (select pm.punto as descripcion, pm.id_autoridad, pm.id_area, pm.id_zona, pm.id_subzona,     " + 
                                                                "           pm.id_departamento, pm.id_municipio, pm.id_fuente, pmm.id_punto,     " + 
                                                                "           pmm.fecha_muestreo as fecha_ini, pmm.fecha_muestreo as fecha_fin   " + 
                                                                "       from sirhv_punto_monitoreo_fq pm, sirhv_part_puntos_muestras_fq pmm    " + 
                                                                "       where pm.id = pmm.id_punto) ";        
    
    public static final String nroMuestrasPorAnioFuenteIdeamDatos =  " select id, fuente, puntomonitoreo, area, zona, subzona, departamento, municipio, latitud, longitud, " + 
                                                                    "      altitud, nromuestra, fecha_ini as fechamuestreo " + 
                                                                    " from(Select rownum as id, pm.id_fuente, pm.id as id_punto, (aut.sigla || ' - ' || UPPER (ft.nombre)) AS fuente, " + 
                                                                    "        UPPER (pm.punto) AS puntomonitoreo,  pm.id_autoridad, pm.id_area,     " + 
                                                                    "        z_area.valor AS area, pm.id_zona, z_zona.valor AS zona, pm.id_subzona, z_subzona.valor AS subzona,  " + 
                                                                    "        pm.id_departamento, dep.nombre AS departamento,  pm.id_municipio,mun.nombre AS municipio, " + 
                                                                    "        pm.latitud, pm.longitud, pm.altitud, pmm.codigo_muestra as nromuestra, " + 
                                                                    "        pmm.fecha_muestreo as fecha_ini, pmm.fecha_muestreo as fecha_fin " + 
                                                                    "     from sirhv_punto_monitoreo_fq pm " + 
                                                                    "     left join sirhv_part_puntos_muestras_fq  pmm on pmm.id_punto = pm.id " + 
                                                                    "     left join part_ref_pto_mon_subzonas prpms on prpms.id_punto_ideam = pm.id " + 
                                                                    "     left join fntt_fuente ft on  ft.id = prpms.id_fuente and ft.id = pm.id_fuente " + 
                                                                    "     left join autoridades aut on aut.id = pm.id_autoridad " + 
                                                                    "     left join part_listas pl on pl.id = ft.id_tipo_fuente " + 
                                                                    "     left join part_zonific_listas z_area on pm.id_area = z_area.id " + 
                                                                    "     left join part_zonific_listas z_zona on pm.id_zona = z_zona.id " + 
                                                                    "     left join part_zonific_listas z_subzona on pm.id_subzona = z_subzona.id " + 
                                                                    "     left join part_divipola dep on pm.id_departamento = dep.divipola_id  " + 
                                                                    "     left join part_divipola mun on pm.id_municipio = mun.divipola_id " + 
                                                                    "     where pm.id_fuente is not NULL  ) ";
    
    public static final String parametroEnPuntoMonitoreo = " select to_char(fecha_ini, 'dd/mm/yyyy') as fecha, cantidad " + 
                                                           " from(select m.id_muestra, " + 
                                                            "          (case m.signo  when 582 then m.valor2 " + 
                                                            "            else m.valor " + 
                                                            "            end)  cantidad,  " + 
                                                            "            mm.id_autoridad, pm.id_area, pm.id_zona, pm.id_subzona, " + 
                                                            "        pm.id_departamento, pm.id_municipio, pm.id_fuente, mm.id_punto,  m.id_parametro, " + 
                                                            "            mm.fecha_muestreo as fecha_ini, mm.fecha_muestreo as fecha_fin " + 
                                                            "    from calt_medicion m , calt_muestra mm, calt_punto_monitoreo pm    " + 
                                                            "    where m.id_muestra=mm.id and m.signo!=583 and pm.id=mm.id_punto " +
                                                            "           and pm.id=mm.id_punto)" ;
    
    
    public static final String parametroEnPuntoMonitoreoDatos = " select id, valor, fecha,  parametro, ld, cantidad, unidad, fuente,puntomonitoreo,area, zona, subzona, " + 
                                                                "        departamento, municipio,latitud, longitud, altitud, nromuestra " + 
                                                                " from(select  row_number() over (order by (mm.fecha_muestreo)) as id ,  to_char(mm.fecha_muestreo,'YYYY') valor, " + 
                                                                "       to_char(mm.fecha_muestreo , 'dd/mm/yyyy') || '  ' ||  mm.hora_muestreo || ':' ||  mm.min_muestreo ||   mm.horario as fecha,  " + 
                                                                "        m.id_parametro, pl.valor as parametro ,   m.id_muestra, (case sig.id when 616 then '' else sig.valor end) as ld, " + 
                                                                "       (case  m.signo    when 582 then m.valor2   " + 
                                                                "            else m.valor    " + 
                                                                "        end ) cantidad ,  und.valor as unidad, pm.id_fuente, (aut.sigla || ' - ' || UPPER (ft.nombre)) AS fuente,pm.id as id_punto,  " + 
                                                                "     UPPER (pm.nombre) AS puntomonitoreo,  pm.id_autoridad, pm.id_area, z_area.valor AS area,   " + 
                                                                "     pm.id_zona, z_zona.valor AS zona, pm.id_subzona, z_subzona.valor AS subzona,   " + 
                                                                "       pm.id_departamento, dep.nombre AS departamento,  pm.id_municipio, mun.nombre AS municipio, " + 
                                                                "       (((((pm.latitud_grados || 'º ') || pm.latitud_minutos) || ''' ')   " + 
                                                                "               || ROUND (pm.latitud_segundos, 2)) || '''') || ''''AS latitud, " + 
                                                                "          (((((pm.longitud_grados || 'º ') || pm.longitud_minutos) || ''' ')    " + 
                                                                "               || ROUND (pm.longitud_segundos, 2)) || '''') || '''' AS longitud,   " + 
                                                                "       pm.altitud, mm.nro_muestra as nromuestra,  " + 
                                                                "            mm.fecha_muestreo as fecha_ini, mm.fecha_muestreo as fecha_fin  " + 
                                                                "        from calt_medicion m " + 
                                                                "     left join calt_muestra mm   on  mm.id = m.id_muestra" + 
                                                                "     left join part_listas pl on pl.id = m.id_parametro" + 
                                                                "     left join part_listas und on und.id = m.unidad" + 
                                                                "     left join part_listas sig on sig.id = m.signo "+  
                                                                "     left join calt_punto_monitoreo pm on pm.id = mm.id_punto   " + 
                                                                "     left join autoridades aut on aut.id = pm.id_autoridad  " + 
                                                                "     left join part_zonific_listas z_area on pm.id_area = z_area.id   " + 
                                                                "     left join part_zonific_listas z_zona on pm.id_zona = z_zona.id   " + 
                                                                "     left join part_zonific_listas z_subzona on pm.id_subzona = z_subzona.id   " + 
                                                                "     left join part_divipola dep on pm.id_departamento = dep.divipola_id  " + 
                                                                "     left join part_divipola mun on pm.id_municipio = mun.divipola_id   " + 
                                                                "     left join fntt_fuente ft on ft.id = pm.id_fuente     " + 
                                                                "        where  m.signo!=583 and id_muestra in ( select id " + 
                                                                "                                                from calt_muestra  " + 
                                                                "                                                where id_punto = ?1) and m.id_parametro = ?2)" ; 
    
    public static final String parametroEnPuntoMonitoreoIdeam = " select to_char(fecha_ini, 'dd/mm/yyyy') as fecha, cantidad" + 
                                                                " from( select mm.codigomuestra, mm.valor as  cantidad,  pm.id_autoridad, " + 
                                                                "            pm.id_area, pm.id_zona, pm.id_subzona, pm.id_departamento,     " + 
                                                                "            pm.id_municipio, pm.id_fuente, mm.estacionid as id_punto, mm.supacodigoparametro as id_parametro, " + 
                                                                "            mm.fechamuestreo as fecha_ini, mm.fechamuestreo as fecha_fin        " + 
                                                                "       from  sirhv_muestras_fq mm, sirhv_punto_monitoreo_fq pm    " + 
                                                                "       where  pm.id = mm.estacionid)" ; 
    
    public static final String parametroEnPuntoMonitoreoIdeamDatos = "select id, valor, fecha,  parametro, ld, cantidad, unidad, fuente,puntomonitoreo,area, zona, subzona, departamento, municipio, " + 
                                                                    "        latitud, longitud, altitud, nromuestra" + 
                                                                    " from(select  row_number() over (order by (mm.fechamuestreo)) as id ,  to_char(mm.fechamuestreo,'YYYY') valor, " + 
                                                                    "       to_char(mm.fechamuestreo , 'dd/mm/yyyy') fecha,   " + 
                                                                    "        mm.supacodigoparametro as id_parametro, mm.parametro , mm.codigomuestra as id_muestra, mm.dlimite as ld, " + 
                                                                    "        mm.valor as cantidad, mm.unidad, pm.id_fuente, (aut.sigla || ' - ' || UPPER (ft.nombre)) AS fuente,pm.id as id_punto,   " + 
                                                                    "     UPPER (pm.punto) AS puntomonitoreo,  pm.id_autoridad, pm.id_area, z_area.valor AS area,   " + 
                                                                    "     pm.id_zona, z_zona.valor AS zona, pm.id_subzona, z_subzona.valor AS subzona,   " + 
                                                                    "       pm.id_departamento, dep.nombre AS departamento,  pm.id_municipio, mun.nombre AS municipio,   " + 
                                                                    "       pm.latitud, pm.longitud,pm.altitud, null as nromuestra,  " + 
                                                                    "       mm.fechamuestreo as fecha_ini, mm.fechamuestreo as fecha_fin               " + 
                                                                    "        from sirhv_punto_monitoreo_fq pm " + 
                                                                    "     left join sirhv_muestras_fq mm   on  mm.estacionid = pm.id" + 
                                                                    "     left join autoridades aut on aut.id = pm.id_autoridad            " + 
                                                                    "     left join part_zonific_listas z_area on pm.id_area = z_area.id   " + 
                                                                    "     left join part_zonific_listas z_zona on pm.id_zona = z_zona.id   " + 
                                                                    "     left join part_zonific_listas z_subzona on pm.id_subzona = z_subzona.id   " + 
                                                                    "     left join part_divipola dep on pm.id_departamento = dep.divipola_id                     " + 
                                                                    "     left join part_divipola mun on pm.id_municipio = mun.divipola_id   " + 
                                                                    "     left join fntt_fuente ft on ft.id = pm.id_fuente     " + 
                                                                    "     where mm.estacionid = ?1 and mm.supacodigoparametro = ?2 )";

    public static final String listaParametroPuntoFuente =  " select nombre as descripcion  ,avg(cantidad) as cantidad " + 
                                                            " from(select mm.fecha_muestreo  fecha, mm.id as muestra , pm.nombre as nombre, " + 
                                                            "       ( case  m.signo    when 582  " + 
                                                            "               then m.valor2 " + 
                                                            "               else m.valor " + 
                                                            "        end ) cantidad , mm.id_autoridad, pm.id_area, pm.id_zona, pm.id_subzona, " + 
                                                            "        pm.id_departamento, pm.id_municipio, pm.id_fuente, mm.id_punto,  m.id_parametro, " + 
                                                            "        mm.fecha_muestreo as fecha_ini, mm.fecha_muestreo as fecha_fin " + 
                                                            "     from calt_medicion m , calt_muestra mm , part_listas pp ,calt_punto_monitoreo  pm " + 
                                                            "     where m.id_muestra=mm.id and mm.id_punto = pm.id and mm.tipo_muestra= pp.id  " + 
                                                            "            and m.signo!=583 )" ; 
    
    public static final String listaParametroPuntoFuenteDatos = " SELECT id, valor, fecha,  puntomonitoreo, ld, cantidad, unidad, fuente,parametro,area, zona, subzona, departamento, municipio, " + 
                                                                "        latitud, longitud, altitud, nromuestra " + 
                                                                " FROM ( SELECT ROW_NUMBER () OVER (ORDER BY mm.fecha_muestreo) AS id, TO_CHAR(mm.fecha_muestreo,'YYYY') valor,   " + 
                                                                "             TO_CHAR(mm.fecha_muestreo , 'dd/MM/YYYY') || ' ' || MM.HORA_MUESTREO || ':' || MM.MIN_MUESTREO  || ' ' || MM.HORARIO as  fecha, " + 
                                                                "           m.id_muestra, (case sig.id when 616 then '' else sig.valor end) as ld, "+
                                                                "              ( case  m.signo when 582  " + 
                                                                "                then m.valor2      " + 
                                                                "                else m.valor " + 
                                                                "              end ) cantidad , pm.id as id_punto, UPPER (pm.nombre) AS puntomonitoreo,  " + 
                                                                "              pm.id_fuente, (aut.sigla || ' - ' || UPPER (ft.nombre)) AS fuente, m.id_muestra, m.id_parametro,  " + 
                                                                "              pl.valor as parametro,  und.valor as unidad , pm.id_autoridad, pm.id_area,  z_area.valor AS area, " + 
                                                                "              pm.id_zona, z_zona.valor AS zona, pm.id_subzona, z_subzona.valor AS subzona,   " + 
                                                                "              pm.id_departamento, dep.nombre AS departamento,  pm.id_municipio, mun.nombre AS municipio, " + 
                                                                "              (((((pm.latitud_grados || 'º ') || pm.latitud_minutos) || ''' ')   " + 
                                                                "                     || ROUND (pm.latitud_segundos, 2)) || '''') || ''''AS latitud,  " + 
                                                                "              (((((pm.longitud_grados || 'º ') || pm.longitud_minutos) || ''' ')    " + 
                                                                "                     || ROUND (pm.longitud_segundos, 2)) || '''') || '''' AS longitud,  " + 
                                                                "            pm.altitud, mm.nro_muestra as nromuestra, " + 
                                                                "              mm.fecha_muestreo as fecha_ini, mm.fecha_muestreo as fecha_fin  " + 
                                                                "       FROM  calt_medicion m  " + 
                                                                "             left join calt_muestra mm   on  mm.id = m.id_muestra " + 
                                                                "             left join part_listas pl on  pl.id = m.id_parametro  " + 
                                                                "             left join part_listas und on und.id = m.unidad " + 
                                                                "             left join part_listas sig on sig.id = m.signo "+                                                                  
                                                                "             left join calt_punto_monitoreo pm on pm.id = mm.id_punto " + 
                                                                "             left join autoridades aut on aut.id = pm.id_autoridad  " + 
                                                                "             left join part_zonific_listas z_area on pm.id_area = z_area.id   " + 
                                                                "             left join part_zonific_listas z_zona on pm.id_zona = z_zona.id   " + 
                                                                "             left join part_zonific_listas z_subzona on pm.id_subzona = z_subzona.id   " + 
                                                                "             left join part_divipola dep on pm.id_departamento = dep.divipola_id " + 
                                                                "             left join part_divipola mun on pm.id_municipio = mun.divipola_id   " + 
                                                                "             left join fntt_fuente ft on ft.id = pm.id_fuente " + 
                                                                "       WHERE  m.signo!=583 )" ;
    
    public static final String listaParametroPuntoFuenteIdeam = "select nombre as descripcion  ,avg(cantidad) as cantidad " + 
                                                                " from(select pmm.fecha_muestreo  fecha, pmm.codigo_muestra as muestra , pm.punto as nombre, " + 
                                                                "        mm.valor as cantidad , pm.id_autoridad, pm.id_area, pm.id_zona, pm.id_subzona,   " + 
                                                                "        pm.id_departamento, pm.id_municipio, pm.id_fuente, pmm.id_punto, mm.supacodigoparametro as id_parametro, " + 
                                                                "        pmm.fecha_muestreo as fecha_ini, pmm.fecha_muestreo as fecha_fin  " + 
                                                                "     from sirhv_part_puntos_muestras_fq pmm , sirhv_muestras_fq mm, sirhv_punto_monitoreo_fq  pm  " + 
                                                                "     where pmm.codigo_muestra = mm.codigomuestra and pmm.id_punto = pm.id ) " ;
    
    public static final String listaParametroPuntoFuenteIdeamDatos = " SELECT id, valor, fecha,  puntomonitoreo, ld,cantidad, unidad, fuente,parametro,area, zona, subzona, departamento, municipio, " + 
                                                                    "        latitud, longitud, altitud, nromuestra  " + 
                                                                    " FROM ( SELECT ROW_NUMBER () OVER (ORDER BY pmm.fecha_muestreo) AS id, TO_CHAR(pmm.fecha_muestreo,'YYYY') valor,  " + 
                                                                    "             TO_CHAR(pmm.fecha_muestreo , 'dd/MM/YYYY') as  fecha, mm.valor as cantidad , pm.id as id_punto, UPPER (pm.punto) AS puntomonitoreo, " + 
                                                                    "              pm.id_fuente, (aut.sigla || ' - ' || UPPER (ft.nombre)) AS fuente, pmm.codigo_muestra as id_muestra, " + 
                                                                    "              mm.supacodigoparametro as id_parametro,  mm.codigomuestra as id_muestra, mm.dlimite as ld, mm.parametro, mm.unidad , pm.id_autoridad, " + 
                                                                    "              pm.id_area,  z_area.valor AS area,pm.id_zona, z_zona.valor AS zona, pm.id_subzona, z_subzona.valor AS subzona,  " + 
                                                                    "              pm.id_departamento, dep.nombre AS departamento,  pm.id_municipio, mun.nombre AS municipio, " + 
                                                                    "              pm.latitud, pm.longitud, pm.altitud, null as nromuestra, " + 
                                                                    "              pmm.fecha_muestreo as fecha_ini, pmm.fecha_muestreo as fecha_fin   " + 
                                                                    "       FROM  sirhv_punto_monitoreo_fq pm " + 
                                                                    "             left join sirhv_part_puntos_muestras_fq  pmm on pmm.id_punto = pm.id " + 
                                                                    "             left join sirhv_muestras_fq mm  on   mm.codigomuestra = pmm.codigo_muestra  " + 
                                                                    "             left join part_zonific_listas z_area on pm.id_area = z_area.id  " + 
                                                                    "             left join part_zonific_listas z_zona on pm.id_zona = z_zona.id  " + 
                                                                    "             left join part_zonific_listas z_subzona on pm.id_subzona = z_subzona.id  " + 
                                                                    "             left join part_divipola dep on pm.id_departamento = dep.divipola_id  " + 
                                                                    "             left join part_divipola mun on pm.id_municipio = mun.divipola_id " + 
                                                                    "             left join autoridades aut on aut.id = pm.id_autoridad " + 
                                                                    "             left join fntt_fuente ft on ft.id = pm.id_fuente) ";   
    
    public static final String listaPuntosFuenteCriterios =  " Select pm.id , pl.valor || ' ' || f.nombre ||' - ' || pm.nombre as nombre ," +
                                                             " pm.tipo_punto, pm.ubicacion, pm.id_fuente, pm.id_tramo, pm.latitud_grados, " +
                                                             " pm.latitud_minutos, pm.latitud_segundos, pm.longitud_grados, pm.longitud_minutos, " +
                                                             " pm.longitud_segundos, pm.altitud, pm.descripcion_acceso, pm.id_municipio, " +
                                                             " pm.id_subzona, pm.id_autoridad, pm.sist_ref, pm.id_departamento, pm.id_area, " +
                                                             " pm.id_zona, pm.id_vertimiento, pm.id_captacion" +
                                                             " from calt_punto_monitoreo pm, fntt_fuente f , autoridades aa , part_listas pl  " +
                                                             " where pm.id in( Select distinct(id_punto) From calt_muestra " +
                                                             " where id in( select distinct(id_muestra) from calt_medicion  ) )  "+
                                                             " and  f.cod_autoridad = aa.id "+
                                                             " and  pm.id_autoridad= aa.id "+
                                                             " and  pm.id_fuente = f.id "+
                                                             " and  F.ID_TIPO_FUENTE=PL.ID ";
    
    public static final String listaParametrosPuntos=    " select  * " +
                                                         "  from part_listas " +
                                                         "  where id in ( select id_parametro " +
                                                         "                from calt_medicion " +
                                                         "                where id_muestra in  " +
                                                         "                                   ( select id  " +
                                                         "                                     from calt_muestra " +
                                                         "                                     where id_punto = ?1 ) " +
                                                         "                                         and signo!=583  ) ORDER BY valor ";

    
    
    public static final String listaParametrosFuente=    " select  * " +
                                                         "  from part_listas " +
                                                         "  where id in ( select id_parametro " +
                                                         "                from calt_medicion " +
                                                         "                where id_muestra in  " +
                                                         "                                   ( select id  " + 
                                                         "                                     from calt_muestra  " + 
                                                         "                                     where id_punto in (select id  from  " + 
                                                         "                                                        calt_punto_monitoreo " +
                                                         "                                                        where id_fuente =?1 )) " + 
                                                         "                                       and signo!=583  ) ORDER BY valor ";

        
    public static final String parametroPuntoMonitoreo = " select  TO_CHAR(mm.fecha_muestreo , 'dd/MM/YYYY')  fecha, " +
                                                         "          case  m.signo    when 582 then m.valor2 " + 
                                                         "          else m.valor  " + 
                                                         "	    end  cantidad     " + 
                                                         "  from calt_medicion m , calt_muestra mm " + 
                                                         "  where m.id_muestra in " +
                                                                                " ( select id " +
                                                                                 " from calt_muestra " +
                                                                                 " where id_punto = ?1 )" + 
                                                         " and m.id_parametro = ?2 " +
                                                         " and m.id_muestra=mm.id " +
                                                         " and m.signo!=583  order by mm.fecha_muestreo";
    


    public static final String listaparametroPuntoMonitoreo = " select  ROW_NUMBER () OVER (ORDER BY (mm.fecha_muestreo)) as num ,  TO_CHAR(mm.fecha_muestreo,'YYYY') valor, " +
                                                         "          TO_CHAR(mm.fecha_muestreo , 'dd/MM/YYYY') || '  ' ||  MM.HORA_MUESTREO || ':' ||  MM.MIN_MUESTREO ||   MM.HORARIO   as   fecha, " + 
                                                         "          PL.VALOR as descripcion ,  " + 
                                                         "          case  m.signo    when 582 then m.valor2 " + 
                                                         "          else m.valor  " + 
                                                         "          end  cantidad ,  und.valor as unidad     " + 
                                                         "  from calt_medicion m , calt_muestra mm , part_listas pl  , part_listas und     " + 
                                                         "  where m.id_muestra in " +
                                                                                " ( select id " +
                                                                                 " from calt_muestra " +
                                                                                 " where id_punto = ?1 )" + 
                                                         " and m.id_parametro = ?2 " +
                                                         " and m.id_muestra=mm.id " +
                                                         " and m.id_parametro= pl.id " + 
                                                         " and m.UNIDAD= und.id" +
                                                         " and m.signo!=583  order by mm.fecha_muestreo";

    public static final String unidadParametro = " select * from part_listas  where id in " + 
                                                "( select unidad  from calt_medicion me, calt_muestra mu, CALT_PUNTO_MONITOREO pu  " + 
                                                "where me.id_parametro= ?1 and ME.ID_MUESTRA=MU.ID and MU.ID_PUNTO=PU.ID and pu.ID_AUTORIDAD= ?2) ORDER BY valor ";
                                                
  
    public static final String listaAnioMuestrasORA= " select distinct (EXTRACT(YEAR  from  m.fecha_muestreo) ) as valor " +
                                                  " from calt_muestra m , calt_punto_monitoreo pm " +
                                                  " where pm.id=m.id_punto and pm.id_fuente= ?1 " +
                                                  " order by EXTRACT(YEAR  from  m.fecha_muestreo) asc ";
    
 
    public static final String listaAnioParametro= " select distinct (EXTRACT(YEAR  from m.fecha_muestreo)) as valor " +
                                                  "  from calt_muestra m , calt_medicion med " +
                                                  "  where med.id_muestra=m.id and med.id_parametro= ?1  "+
                                                  "  order by EXTRACT(YEAR  from m.fecha_muestreo) ";
                                                 
    
   
    public static final String listaAnioParametroFuente= " select distinct (EXTRACT(YEAR  from m.fecha_muestreo)) as valor " +
                                                  "  from calt_muestra m , calt_medicion med , calt_punto_monitoreo pm  " +
                                                  "  where med.id_muestra=m.id and med.id_parametro= ?1 " +
                                                  "  and m.id_punto=pm.id " +
                                                  "  and pm.id_fuente= ?2 " +
                                                  "  order by EXTRACT(YEAR  from m.fecha_muestreo) desc";
    
    public static final String listaAnioFuente= " select distinct (EXTRACT(YEAR  from m.fecha_muestreo)) as valor " +
                                                  "  from calt_muestra m , calt_medicion med , calt_punto_monitoreo pm  " +
                                                  "  where med.id_muestra=m.id " +
                                                  "  and m.id_punto=pm.id " +
                                                  "  and pm.id_fuente= ?1 " +
                                                  "  order by EXTRACT(YEAR  from m.fecha_muestreo) desc";
    
    public static final String listaAnioParametroFuenteIdeam = "select distinct (EXTRACT(YEAR  from mm.fechamuestreo)) as valor " + 
                                                            "from sirhv_muestras_fq mm, sirhv_punto_monitoreo_fq  pm " + 
                                                            "where mm.estacionid = pm.id and mm.supacodigoparametro = ?1 " + 
                                                            "and pm.id_fuente= ?2 " + 
                                                            "order by EXTRACT(YEAR  from mm.fechamuestreo) desc" ;
    public static final String listaAnioFuenteIdeam = "select distinct (EXTRACT(YEAR  from mm.fechamuestreo)) as valor " + 
                                                            "from sirhv_muestras_fq mm, sirhv_punto_monitoreo_fq  pm " + 
                                                            "where mm.estacionid = pm.id " + 
                                                            "and pm.id_fuente= ?1 " + 
                                                            "order by EXTRACT(YEAR  from mm.fechamuestreo) desc" ;

    public static final String parametrosPromedioAnio= " select x.nombre as descripcion  ,avg(cantidad) as cantidad from  " + 
                                                       " (select   mm.fecha_muestreo  fecha, mm.id as muestra , punto.nombre as nombre, " + 
                                                       " case  m.signo    when 582 " +
                                                       " then m.valor2  " + 
                                                       " else m.valor              " + 
                                                       " end  cantidad                " + 
                                                       " from calt_medicion m , calt_muestra mm , part_listas pp ,calt_punto_monitoreo  punto      " + 
                                                       " where m.id_muestra in " + 
                                                       " ( select id from calt_muestra  " +
                                                       " where id_punto in ( " + 
                                                                      "     Select id from calt_punto_monitoreo " + 
                                                                      "    where id_fuente = ?1 " + 
                                                                      "                             )  )  " + 
                                                        "   and m.id_parametro = ?2 " + 
                                                        "   and m.id_muestra=mm.id " +
                                                        "   and mm.id_punto = punto.id   " + 
                                                        "   and EXTRACT(YEAR  from  mm.fecha_muestreo)= ?3 " + 
                                                        "   and mm.tipo_muestra= pp.id" + 
                                                        "   and m.signo!=583  order by mm.fecha_muestreo) x group by x.nombre ";
    
    public static final String listaparametrosPromedioAnio= "  SELECT  ROW_NUMBER () OVER (ORDER BY vdatos.fecha) AS num, vdatos.valor, " + 
                                                            "          vdatos.fecha , vdatos.descripcion, vdatos.cantidad, vdatos.unidad " + 
                                                            "    FROM ( SELECT datos.valor , datos.fecha , datos.descripcion, datos.cantidad, datos.unidad " + 
                                                            "           FROM ( SELECT  x.valor, x.fecha  as fecha,x.nombre as descripcion  ," + 
                                                            "                          x.cantidad as cantidad , x.unidad as unidad  " + 
                                                            "                   FROM ( SELECT  TO_CHAR(mm.fecha_muestreo,'YYYY') valor, " + 
                                                            "                                  TO_CHAR(mm.fecha_muestreo , 'dd/MM/YYYY') || ' ' || MM.HORA_MUESTREO || ':' || MM.MIN_MUESTREO  || ' ' || MM.HORARIO as  fecha  , " + 
                                                            "                                  mm.id  as muestra , punto.nombre as nombre,   " + 
                                                            "                                  case  m.signo when 582  " + 
                                                            "                                  then m.valor2    " + 
                                                            "                                  else m.valor               " + 
                                                            "                                  end  cantidad , und.valor as unidad                 " + 
                                                            "                           FROM    calt_medicion m , calt_muestra mm , part_listas pp ," + 
                                                            "                                   calt_punto_monitoreo  punto   , part_listas und     " + 
                                                            "                           WHERE   m.id_muestra in ( SELECT id  " + 
                                                            "                                                     FROM calt_muestra    " + 
                                                            "                                                     WHERE id_punto in (  SELECT id  " + 
                                                            "                                                                          FROM calt_punto_monitoreo    " + 
                                                            "                                                                          WHERE id_fuente  = ?1 " + 
                                                            "                                                                       ) " + 
                                                            "                                                  )     " + 
                                                            "                           AND m.id_parametro  = ?2    " + 
                                                            "                           AND m.id_muestra=mm.id    " + 
                                                            "                           AND m.unidad=und.id  " + 
                                                            "                           AND mm.id_punto = punto.id       " + 
                                                            "                           AND EXTRACT(YEAR  from  mm.fecha_muestreo) = ?3      " + 
                                                            "                           AND mm.tipo_muestra= pp.id    " + 
                                                            "                           AND m.signo!=583 ) x  " + 
                                                            "                           GROUP BY x.nombre, x.unidad , x.fecha, x.cantidad, x.valor " + 
                                                            "           UNION " + 
                                                            "                   SELECT  x.valor, x.fecha  as fecha,x.nombre as descripcion  , " + 
                                                            "                           x.cantidad as cantidad , x.unidad as unidad   " + 
                                                            "                   FROM ( SELECT  TO_CHAR(mm.fecha_muestreo,'YYYY') valor,  " + 
                                                            "                                  TO_CHAR(mm.fecha_muestreo , 'dd/MM/YYYY') || ' ' || MM.HORA_MUESTREO || ':' || MM.MIN_MUESTREO  || ' ' || MM.HORARIO as  fecha  , " + 
                                                            "                                  mm.id  as muestra , punto.nombre as nombre,    " + 
                                                            "                                  case  m.signo when 582   " + 
                                                            "                                  then m.valor2     " + 
                                                            "                                  else m.valor                 " + 
                                                            "                                  end  cantidad , und.valor as unidad                  " + 
                                                            "                           FROM    calt_medicion m , calt_muestra mm , part_listas pp , " + 
                                                            "                                   calt_punto_monitoreo  punto   , part_listas und      " + 
                                                            "                           WHERE   m.id_muestra in ( SELECT id  " + 
                                                            "                                                     FROM calt_muestra    " + 
                                                            "                                                     WHERE id_punto in (  SELECT id  " + 
                                                            "                                                                          FROM calt_punto_monitoreo    " + 
                                                            "                                                                          WHERE id_fuente  = ?4   " + 
                                                            "                                                                       ) " + 
                                                            "                                                  )     " + 
                                                            "                           AND m.id_parametro  = ?5      " + 
                                                            "                           AND m.id_muestra=mm.id    " + 
                                                            "                           AND m.unidad=und.id  " + 
                                                            "                           AND mm.id_punto = punto.id       " + 
                                                            "                           AND EXTRACT(YEAR  from  mm.fecha_muestreo)  = ?6     " + 
                                                            "                           AND mm.tipo_muestra= pp.id    " + 
                                                            "                           AND m.signo!=583 ) x  " + 
                                                            "                           GROUP BY x.nombre, x.unidad , x.fecha, x.cantidad, x.valor                                        " + 
                                                            "            UNION " + 
                                                            "                   SELECT  x.valor, x.fecha  as fecha,x.nombre as descripcion  , " + 
                                                            "                           x.cantidad as cantidad , x.unidad as unidad   " + 
                                                            "                   FROM ( SELECT  TO_CHAR(mm.fecha_muestreo,'YYYY') valor,  " + 
                                                            "                                  TO_CHAR(mm.fecha_muestreo , 'dd/MM/YYYY') || ' ' || MM.HORA_MUESTREO || ':' || MM.MIN_MUESTREO  || ' ' || MM.HORARIO as  fecha  , " + 
                                                            "                                  mm.id  as muestra , punto.nombre as nombre,    " + 
                                                            "                                  case  m.signo when 582   " + 
                                                            "                                  then m.valor2     " + 
                                                            "                                  else m.valor                 " + 
                                                            "                                  end  cantidad , und.valor as unidad                  " + 
                                                            "                           FROM    calt_medicion m , calt_muestra mm , part_listas pp , " + 
                                                            "                                   calt_punto_monitoreo  punto   , part_listas und      " + 
                                                            "                           WHERE   m.id_muestra in ( SELECT id  " + 
                                                            "                                                     FROM calt_muestra    " + 
                                                            "                                                     WHERE id_punto in (  SELECT id  " + 
                                                            "                                                                          FROM calt_punto_monitoreo    " + 
                                                            "                                                                          WHERE id_fuente  = ?7   " + 
                                                            "                                                                       ) " + 
                                                            "                                                  )     " + 
                                                            "                           AND m.id_parametro  = ?8   " + 
                                                            "                           AND m.id_muestra=mm.id    " + 
                                                            "                           AND m.unidad=und.id  " + 
                                                            "                           AND mm.id_punto = punto.id       " + 
                                                            "                           AND EXTRACT(YEAR  from  mm.fecha_muestreo) = ?9     " + 
                                                            "                           AND mm.tipo_muestra= pp.id    " + 
                                                            "                           AND m.signo!=583 ) x  " + 
                                                            "                           GROUP BY x.nombre, x.unidad , x.fecha, x.cantidad, x.valor       " + 
                                                            "                )datos " + 
                                                            "             ORDER BY datos.fecha ) vdatos ";
                                                            
    
    
    public static final String listaMedicionParametro=    " select * from calt_medicion where id_muestra in " +
                                                          " (select id from calt_muestra where id_autoridad = ?2) " +
                                                          "  and id_parametro =?1    ";
    
    public static final String listaMedicionParametroIdeam =    " ";

        
   public static final String listaPuntosPorParametro=    " select * from calt_punto_monitoreo where id_autoridad= ?2" +
                                                          " and id in (select id_punto from calt_muestra    " +
                                                          " where id in (select id_muestra from calt_medicion " +
                                                          " where id_parametro = ?1) )";
    
    
    
    @Id
    @Column(name="descripcion")     
    private String descripcion;
    @Column(name="cantidad")    
    private Long cantidad;
    
    @Transient
    private Long idAutoridad;
    
    @Transient
    public  String nroMuestrasPorAnioORA;
    @Transient
    public  String muestrasFuentes;
    
    @Transient
    public  String nroMuestrasPorTipo;
    
    @Transient
    public  String listaFuentesMuestras; 
    
    @Transient
    public  String listaPuntosMedicion;
    
    @Transient
    private String listaFuentesTipo;
  
    
    public DatosReporteCalidadTO() {
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

    public void setNroMuestrasPorAnioORA(String nroMuestrasPorAnioORA) {
        this.nroMuestrasPorAnioORA = nroMuestrasPorAnioORA;
    }

    public String getNroMuestrasPorAnioORA() {
         nroMuestrasPorAnioORA = " select  EXTRACT(YEAR  from  mm.fecha_muestreo)  as descripcion, " +
                                                        " count(*) as cantidad  " +
                                                        " from  calt_muestra mm " ;
         if(getIdAutoridad()!=null){
                nroMuestrasPorAnioORA = nroMuestrasPorAnioORA +  " where mm.id_autoridad ="+getIdAutoridad()+" ";
            }
                                  
            nroMuestrasPorAnioORA = nroMuestrasPorAnioORA +  " group by  EXTRACT(YEAR  from  mm.fecha_muestreo)   order by cantidad desc" ;
                
        return nroMuestrasPorAnioORA;
    }

    public void setMuestrasFuentes(String muestrasFuentes) {
        this.muestrasFuentes = muestrasFuentes;
    }

    public String getMuestrasFuentes(Long idfuente, Integer anio ) {
        
       muestrasFuentes = " select  pm.nombre as descripcion, count(*) as cantidad    " +
                                                     " from calt_punto_monitoreo pm , calt_muestra mm " +
                                                     " where pm.id=mm.id_punto and pm.id_fuente="+idfuente +
                                                     " and  EXTRACT(YEAR  FROM  mm.fecha_muestreo)="+anio ;
     if(getIdAutoridad()!=null){
        muestrasFuentes = muestrasFuentes +  " and mm.id_autoridad ="+getIdAutoridad()+" ";
        }
        muestrasFuentes = muestrasFuentes +  "  group by pm.nombre  order by cantidad desc ";
        
        return muestrasFuentes;
    }
    

    public void setNroMuestrasPorTipo(String nroMuestrasPorTipo) {
        this.nroMuestrasPorTipo = nroMuestrasPorTipo;
    }

    public String getNroMuestrasPorTipo() {
      
     nroMuestrasPorTipo = " select  lista.valor as descripcion, " +
                                                            " count(*) as cantidad  " +
                                                            " from  calt_muestra mm , part_listas lista " +
                                                            " where lista.id=mm.tipo_muestra ";
                                                           
    if(getIdAutoridad()!=null){
     nroMuestrasPorTipo = nroMuestrasPorTipo +  " and  mm.id_autoridad ="+getIdAutoridad()+" ";
     }
     nroMuestrasPorTipo = nroMuestrasPorTipo +   " group by lista.valor  order by cantidad desc" ;
     
     return nroMuestrasPorTipo;
    }
   
    
  
  
    public void setListaFuentesMuestras(String listaFuentesMuestras) {
        this.listaFuentesMuestras = listaFuentesMuestras;
    }

    public String getListaFuentesMuestras() {
        listaFuentesMuestras= " select f.id ,aa.sigla || ' - ' || pl.valor || ' ' || f.nombre as nombre , f.id_tipo_fuente, f.descripcion , 147 as id_provinciahidro, '' as unidadhidro, " +
                                                            " f.cod_autoridad , f.es_compartida , f.codigo_cuencaaa , f.fuente_car from fntt_fuente f , autoridades aa, part_listas pl " +
                                                            " where f.id in ( select id_fuente " +
                                                            " from calt_punto_monitoreo pm , calt_muestra mm , calt_medicion med " +
                                                            " where pm.id=mm.id_punto and mm.id=med.id_muestra" +
                                                            " and pl.id= f.id_tipo_fuente )" +
                                                            " and  f.cod_autoridad = aa.id";
        if(getIdAutoridad()!=null){
                listaFuentesMuestras = listaFuentesMuestras +  " and  f.cod_autoridad ="+getIdAutoridad()+" ";
            }
                listaFuentesMuestras = listaFuentesMuestras +  "  order by f.cod_autoridad ";
       
                     
        return listaFuentesMuestras;
    }
    
    public void setListaFuentesTipo(String listaFuentesTipo) {
        this.listaFuentesTipo = listaFuentesTipo;
    }

    public String getListaFuentesTipo() {        
        listaFuentesTipo =   " select f.id , pl.valor || ' ' || f.nombre as nombre , f.id_tipo_fuente, f.descripcion ," +
                                                            " f.cod_autoridad , f.es_compartida , f.codigo_cuencaaa from fntt_fuente f , autoridades aa, part_listas pl " +
                                                            " where f.id in ( select id_fuente " +
                                                            " from calt_punto_monitoreo pm , calt_muestra mm , calt_medicion med " +
                                                            " where pm.id=mm.id_punto and mm.id=med.id_muestra" +
                                                            " and pl.id= f.id_tipo_fuente )" +
                                                            " and  f.cod_autoridad = aa.id";

        if(getIdAutoridad()!=null){
                listaFuentesTipo = listaFuentesTipo +  " and  f.cod_autoridad = "+getIdAutoridad()+" ";
        }
                                  
        listaFuentesTipo = listaFuentesTipo +  " order by f.cod_autoridad ";
        
        return listaFuentesTipo;
    }        

    
    public void setListaPuntosMedicion(String listaPuntosMedicion) {
        this.listaPuntosMedicion = listaPuntosMedicion;
    }

    public String getListaPuntosMedicion() {
        listaPuntosMedicion= " Select pm.id , aa.sigla || ' - ' || pl.valor || ' ' || f.nombre ||' - ' || pm.nombre as nombre ," +
                             " pm.tipo_punto, pm.ubicacion, pm.id_fuente, pm.id_tramo, pm.latitud_grados, " + 
                             " pm.latitud_minutos, pm.latitud_segundos, pm.longitud_grados, pm.longitud_minutos, " + 
                             " pm.longitud_segundos, pm.altitud, pm.descripcion_acceso, pm.id_municipio, " + 
                             " pm.id_subzona, pm.id_autoridad, pm.sist_ref, pm.id_departamento, pm.id_area, " + 
                             " pm.id_zona, pm.id_vertimiento, pm.id_captacion" +
                             " from calt_punto_monitoreo pm, fntt_fuente f , autoridades aa , part_listas pl  " +
                             " where pm.id in( Select distinct(id_punto) From calt_muestra " +
                             " where id in( select distinct(id_muestra) from calt_medicion  ) )  "+
                             " and  f.cod_autoridad = aa.id "+
                             " and  pm.id_autoridad= aa.id "+
                             " and  pm.id_fuente = f.id "+
                             " and  F.ID_TIPO_FUENTE=PL.ID";
        if(getIdAutoridad()!=null){
                listaPuntosMedicion = listaPuntosMedicion +  " and  pm.id_autoridad ="+getIdAutoridad()+" ";
            }
                                  
        listaPuntosMedicion = listaPuntosMedicion +  " order by pm.id_autoridad ";
        
        return listaPuntosMedicion;
    }
    

}
