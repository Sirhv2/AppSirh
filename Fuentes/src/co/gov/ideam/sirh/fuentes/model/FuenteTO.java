package co.gov.ideam.sirh.fuentes.model;

// HUGO 20141030
public class FuenteTO {
    public FuenteTO() {
    }
    
    public static final String qAlertasDisponibilidad="select max(t.id_area) as area, " + 
    "       max(t.id_zona) as zona, " + 
    "       max(t.id_subzona) as subzona, " + 
    "       max(t.id_fuente) as idFuente, " + 
    "       max(f.nombre) as nomFuente, " + 
    "       max(t.id) as idTramo, " + 
    "       max(t.nombre) as nomTramo, " + 
    "       max(t.oferta_hidrica_total) as oferta, " + 
    "       sum(nvl(c.caudal_disegno * 0.001, 0)) as demanda, " + 
    "       max('ALERTA A CALCULAR POR SISTEMA') as alerta " + 
    "  from FNTT_TRAMO t " + 
    "  left join rurt_captacion c " + 
    "    on t.id = c.id_tramo " +
    "  left join rurt_concesiones o " + 
    "    on c.id_concesion = o.id " +
    "  left join fntt_fuente f " + 
    "    on t.id_fuente = f.id " + 
    " where t.id_area = ?1 " + 
    "   and t.id_zona = ?2 " + 
    "   and t.id_subzona = ?3 " +
    "   and trunc(o.fecha_fin) > trunc(sysdate) " +                                                    
    " group by t.id " + 
    " order by max(f.nombre), max(t.nombre)";
        
        

    public static final String qAlertasDisponibilidadDet= "select c.id as idCaptacion, " + 
    "       t.id_fuente as idFuente, " + 
    "       f.nombre as nomFuente, " + 
    "       t.id as idTramo, " + 
    "       t.nombre as nomTramo, " + 
    "       t.oferta_hidrica_total as oferta, " + 
    "       nvl(c.caudal_disegno,0)  * 0.001 as demanda, " + 
    "       c.DESCRIPCION_ACCESO as nomCaptacion, " + 
    "       'ALERTA GENERADA POR EL SISTEMA' as alerta " + 
    "  from FNTT_TRAMO t, rurt_captacion c, fntt_fuente f, rurt_concesiones o " + 
    " where t.id = c.id_tramo " + 
    "   and t.id_fuente = f.id " + 
    "   and c.id_concesion = o.id "  +
    "   and trunc(o.fecha_fin) > trunc(sysdate) " +                                                           
    "   and t.id = ?1";
        
        
    
    public static final String qOfertaXFuenteTramo = "select max(t.nombre) as nombre, " + 
    "       max(t.oferta_hidrica_total) as oferta, " + 
    "       sum(nvl(c.caudal_disegno * 0.001, 0)) as demanda " + 
    "  from fntt_fuente f " + 
    "  left join FNTT_TRAMO t " + 
    "    on t.id_fuente = f.id " + 
    "  left join rurt_captacion c " + 
    "    on t.id = c.id_tramo " + 
    "  left join rurt_concesiones o " + 
    "    on c.id_concesion = o.id " +
    " where f.id = ?1 " + 
    "   and trunc(o.fecha_fin) > trunc(sysdate) " +                                                         
    " group by t.id ";

    public static final String qAlertasIca= 
    "select " +  
    "c.id as idMuestra, " +  
    "t.id_area as area, " +  
    "t.id_zona as zona, " +  
    "t.id_subzona as subzona, " +  
    "f.id as fuente, " +  
    "t.id as tramo, " +  
    "c.id_punto as punto, " +  
    "f.nombre as nomFuente, " +  
    "t.nombre as nomTramo, " +  
    "p.nombre as nomPunto, " +  
    "nvl(round(c.ica,4),-1) as ica, " +    
    "to_char( to_date(to_char(c.fecha_muestreo, 'yyyy-mm-dd') || ' ' || trim(to_char(c.hora_muestreo,'00')) || ':' || trim(to_char(c.min_muestreo,'00')) || ':00 ' || c.horario, 'yyyy-mm-dd HH:MI:SS PM'), 'yyyy-mm-dd HH:MI:SS PM' ) as fechaMuestra " +  
    "from calt_muestra c , calt_punto_monitoreo p, fntt_tramo t,fntt_fuente f, " +  
    " ( select  " +  
    "                  m.id_punto, " +  
    "                  max(id) as ultMuestra " +  
    "                  from calt_muestra m " +  
    "                  group by m.id_punto ) b " +  
    "where c.id_punto = b.id_punto " +  
    "and c.id = b.ultMuestra " +  
    "and c.hora_muestreo > 0 " +  
    "and p.id = c.id_punto " +  
    "and p.id_tramo = t.id " +  
    "and p.id_fuente = f.id " +  
    "and t.id_area = ?1 " +  
    "and t.id_zona = ?2 " +  
    "and t.id_subzona = ?3 " +  
    "and t.id_fuente = ?4 " +  
    "and round(c.ica,2) >= ?5 " +  
    "and round(c.ica,2) <= ?6 "; 

    public static final String qAlertasIcaXPunto= 
    "select " +  
    "c.id as idMuestra, " +  
    "t.id_area as area, " +  
    "t.id_zona as zona, " +  
    "t.id_subzona as subzona, " +  
    "f.id as fuente, " +  
    "t.id as tramo, " +  
    "c.id_punto as punto, " +  
    "f.nombre as nomFuente, " +  
    "t.nombre as nomTramo, " +  
    "p.nombre as nomPunto, " +  
    "nvl(round(c.ica,4),-1) as ica, " +    
    "to_char( to_date(to_char(c.fecha_muestreo, 'yyyy-mm-dd') || ' ' || trim(to_char(c.hora_muestreo,'00')) || ':' || trim(to_char(c.min_muestreo,'00')) || ':00 ' || c.horario, 'yyyy-mm-dd HH:MI:SS PM'), 'yyyy-mm-dd HH:MI:SS PM' ) as fechaMuestra " +  
    "from calt_muestra c , calt_punto_monitoreo p, fntt_tramo t,fntt_fuente f, " +  
    " ( select  " +  
    "                  m.id_punto, " +  
    "                  max(id) as ultMuestra " +  
    "                  from calt_muestra m " +  
    "                  group by m.id_punto ) b " +  
    "where c.id_punto = b.id_punto " +  
    "and c.id = b.ultMuestra " +  
    "and c.hora_muestreo > 0 " +  
    "and p.id = c.id_punto " +  
    "and p.id_tramo = t.id " +  
    "and p.id_fuente = f.id " +  
    "and c.id_punto = ?1 " ; 

    public static final String qAlertasIcaXFuente= 
    "select " +  
    "c.id as idMuestra, " +  
    "t.id_area as area, " +  
    "t.id_zona as zona, " +  
    "t.id_subzona as subzona, " +  
    "f.id as fuente, " +  
    "t.id as tramo, " +  
    "c.id_punto as punto, " +  
    "f.nombre as nomFuente, " +  
    "t.nombre as nomTramo, " +  
    "p.nombre as nomPunto, " +  
    "nvl(round(c.ica,4),-1) as ica, " +    
    "to_char( to_date(to_char(c.fecha_muestreo, 'yyyy-mm-dd') || ' ' || trim(to_char(c.hora_muestreo,'00')) || ':' || trim(to_char(c.min_muestreo,'00')) || ':00 ' || c.horario, 'yyyy-mm-dd HH:MI:SS PM'), 'yyyy-mm-dd HH:MI:SS PM' ) as fechaMuestra " +  
    "from calt_muestra c , calt_punto_monitoreo p, fntt_tramo t,fntt_fuente f, " +  
    " ( select  " +  
    "                  m.id_punto, " +  
    "                  max(id) as ultMuestra " +  
    "                  from calt_muestra m " +  
    "                  group by m.id_punto ) b " +  
    "where c.id_punto = b.id_punto " +  
    "and c.id = b.ultMuestra " +  
    "and c.hora_muestreo > 0 " +  
    "and p.id = c.id_punto " +  
    "and p.id_tramo = t.id " +  
    "and p.id_fuente = f.id " +  
    "and f.id = ?1 " +
    "order by t.nombre, p.nombre "; 

    public static final String qMuestrasIca= 
    "select * from (    " +
    "select m.id as idMuestra, to_char(m.fecha_muestreo, 'yyyy-mm-dd')  as fechaMuestra, " +
    "nvl(round(ica,4),0) as ica " +
    "from calt_muestra m, " +
    "( select  to_date(to_char(c.fecha_muestreo, 'yyyy-mm-dd') || ' ' || trim(to_char(c.hora_muestreo,'00')) || ':' || trim(to_char(c.min_muestreo,'00')) || ':00 ' || c.horario, 'yyyy-mm-dd HH:MI:SS PM') as fechaMuestra, " +
    "c.id_punto " +
    "from   calt_muestra c " +
    "where c.id = ?1 ) a " +
    "where to_date(to_char(m.fecha_muestreo, 'yyyy-mm-dd') || ' ' || trim(to_char(m.hora_muestreo,'00')) || ':' || trim(to_char(m.min_muestreo,'00')) || ':00 ' || m.horario, 'yyyy-mm-dd HH:MI:SS PM') <=  a.fechaMuestra " +
    "and m.hora_muestreo > 0 " +   
    "and m.id_punto = a.id_punto " +
    "and ica > 0 " +
    "order by m.fecha_muestreo desc ) " +
    "where rownum < 100 "+
    "order by 1 ";


}
