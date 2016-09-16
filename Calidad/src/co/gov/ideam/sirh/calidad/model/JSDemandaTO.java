package co.gov.ideam.sirh.calidad.model;



import java.io.Serializable;

import utils.system;


public class JSDemandaTO implements Serializable{
   
  public static final String consultaComponenteDemandaXAcuifero =    " SELECT * FROM  (SELECT  COUNT(CP.ID) CAPTACIONES  , ft.nombre as acuifero , AU.SIGLA AS SIGLA " + 
  "                                                         FROM RURT_CAPTACION CP   " +                                                       
  "                                                        LEFT JOIN PART_LISTAS PL ON  CP.TIPO_FUENTE_CAPTACION = PL.ID AND PL.ID=29 " + 
  "                                                        LEFT JOIN fntt_fuente ft on ft.id= CP.ID_FUENTE " + 
  "                                                        INNER JOIN part_listas tf ON Tf.ID = FT.ID_TIPO_FUENTE  AND FT.ID_TIPO_FUENTE=59  " + 
  "                                                        INNER JOIN AUTORIDADES AU ON AU.ID= FT.COD_AUTORIDAD " +                                                          
  "                                                        GROUP BY ft.nombre, AU.SIGLA " + 
  "                                                          ORDER BY  CAPTACIONES desc) " + 
  "                                                          WHERE ROWNUM <= 20 " ;

 



  public static final String consultaMonitoreoPiezometrico =   " SELECT CP.ID AS IDCAPTACION, PL.VALOR AS TIPOCAPTACION, DEP.NOMBRE AS DEPARTAMENTO,MUN.NOMBRE AS MUNICIPIO,      REPLACE (CP.IDENTIFICADOR_PUNTO , '-', ' '), TPC.VALOR AS TIPOPUNTO , " +
                                                                "tf.valor as tipofuente, REPLACE (ft.nombre , '-',' ') as acuifero , TN.VALOR AS NIVEL, to_char (FN.FECHA_MEDICION, 'YYYY-MM-DD' ), FN.NIVEL_MEDIO, FN.NIVEL_PIEZOMETRICO "+
                                                                "FROM RURT_CAPTACION CP  " +
                                                                "INNER JOIN SUBTT_FUNIAS FF ON FF.ID_CAPTACION= CP.ID  " +                                                                
                                                               "INNER JOIN SUBTT_FUNIAS_NIVEL FN ON FN.ID_FUNIAS = FF.ID " +
                                                                "LEFT JOIN PART_LISTAS PL ON  CP.TIPO_FUENTE_CAPTACION = PL.ID AND PL.ID=29 " +
                                                               " LEFT JOIN fntt_fuente ft on ft.id= CP.ID_FUENTE    " + 
                                                               " INNER JOIN part_listas tf ON Tf.ID = FT.ID_TIPO_FUENTE    and FT.ID_TIPO_FUENTE=59 " +
                                                                "LEFT JOIN part_listas TPC ON TPC.ID = CP.TIPO_PUNTO " +
                                                                "LEFT JOIN PART_DIVIPOLA DEP ON DEP.DIVIPOLA_ID   = CP.DEPARTAMENTO " +
                                                                "LEFT JOIN PART_DIVIPOLA MUN ON MUN.DIVIPOLA_ID   = CP.MUNICIPIO " +
                                                                "LEFT JOIN part_listas TN ON TN.ID = FN.TIPO_NIVEL ";
 

  public static final String consultaComponenteDemanda3=   " SELECT  COUNT(CP.ID) CAPTACIONES  , TPC.VALOR as PUNTO   " + 
                                                  "FROM RURT_CAPTACION CP  " + 
                                                  "INNER JOIN fntt_fuente ft on ft.id= CP.ID_FUENTE AND FT.ID_TIPO_FUENTE=59 " + 
                                                  "INNER JOIN part_listas TPC ON TPC.ID = CP.TIPO_PUNTO ";
  

  public static final String consultaComponenteDemandaXProvivincia =   "SELECT  COUNT(CP.ID) CAPTACIONES  , SUBSTR(PV.VALOR,4,length(PV.VALOR)) as PROVINCIA  , AU.SIGLA AS SIGLA " + 
                                                                        "FROM RURT_CAPTACION CP " + 
                                                                        "INNER JOIN fntt_fuente ft on ft.id= CP.ID_FUENTE AND FT.ID_TIPO_FUENTE=59 " + 
                                                                        "INNER JOIN PART_LISTAS PV ON  FT.ID_PROVINCIAHIDRO = PV.ID " + 
                                                                        "INNER JOIN AUTORIDADES AU ON AU.ID= FT.COD_AUTORIDAD " + 
                                                                        "GROUP BY PV.VALOR, AU.SIGLA ORDER BY 1 DESC ";
    
  public static final String listaParametrosCalidadXTipoPunto  = " SELECT CP.ID AS IDCAPTACION, PL.VALOR AS TIPOCAPTACION, DEP.NOMBRE AS DEPARTAMENTO,MUN.NOMBRE AS MUNICIPIO,   CP.IDENTIFICADOR_PUNTO , NVL(TO_CHAR(TPC.VALOR ), 0) AS TIPOPUNTO ," + 
                                                                "  tf.valor as tipofuente, ft.nombre as acuifero ,   PM.NOMBRE AS PUNTOMONITOREO, MM.ID AS ID_MUESTRA,   TM.VALOR AS TIPOMUESTRA ," + 
                                                                "  MED.ID AS MEDICION ,PRM.VALOR AS PARAMETRO_CALIDAD, MED.VALOR , NVL(TO_CHAR(PHID.valor),0) as Prov_Hidro, case red_monitoreo when 1 then 'Si' when 0 then 'No' else ' ' end as red , case red_regional when 1 then 'Si' when 0 then 'No' else ' ' end as red2 , MM.fecha_muestreo, mm.id_autoridad " + 
                                                                "  FROM RURT_CAPTACION CP " + 
                                                                "  INNER JOIN CALT_PUNTO_MONITOREO PM ON PM.ID_CAPTACION= CP.ID " + 
                                                                "  LEFT JOIN PART_LISTAS PL ON  CP.TIPO_FUENTE_CAPTACION = PL.ID AND PL.ID=29 " + 
                                                                "  INNER JOIN CALT_MUESTRA MM ON MM.ID_PUNTO = PM.ID " + 
                                                                "  INNER JOIN CALT_MEDICION MED ON MED.ID_MUESTRA= MM.ID" + 
                                                                "  LEFT JOIN fntt_fuente ft on ft.id= CP.ID_FUENTE   " + 
                                                                "  INNER JOIN part_listas tf ON Tf.ID = FT.ID_TIPO_FUENTE    and FT.ID_TIPO_FUENTE=59" + 
                                                                "  LEFT JOIN part_listas TM ON TM.ID = MM.TIPO_MUESTRA" + 
                                                                "  LEFT JOIN part_listas TPC ON TPC.ID = CP.TIPO_PUNTO" + 
                                                                "  LEFT JOIN part_listas PRM ON PRM.ID = MED.ID_PARAMETRO" + 
                                                                "  LEFT JOIN PART_DIVIPOLA DEP ON DEP.DIVIPOLA_ID   = CP.DEPARTAMENTO" + 
                                                                "  LEFT JOIN PART_DIVIPOLA MUN ON MUN.DIVIPOLA_ID   = CP.MUNICIPIO " +
                                                                "  LEFT JOIN part_listas PHID ON cp.provincia_hidrogeologica = phid.id and phid.id_categoria = 21";
  
public static final String consultaAcuiferos = "SELECT  carac.plantilla_id AS NUMERO "+
                                              " , dat.area_hidrografica "+
                                               ", dat.zona_hidrografica "+
                                               ", dat.provincia_hidroge "+
                                               ", dat.nombre_plantilla AS SISTEMA_ACUIFERO "+
                                               ", dat.car_gestion "+
                                               ", carac.recarga_estimada "+
                                               ", dat.num_pozo_inventariados "+
                                               ", dat.num_alijibes_inventariados "+
                                               ", dat.num_manantiales_inventariados "+
                                               ", carac.demanda_calculada "+                                            
                                             "FROM CARACTERISTICAS CARAC "+
                                                 ",DATOS_BASICOS DAT  "+                                             
                                            "WHERE carac.plantilla_id   =  dat.plantilla_id"  ; 

  public static final String getconsultaPomca =
                                              "select " +
                                                  "pomc.id as pomca_id  " +
                                                  ",fuente.nombre  "+
                                                   ", autor.sigla as AUTORIDAD_NOMBRE  "+
                                                   ", pomc.nombre as POMCA  "+ 
                                                  ", pomc.id_autoridad as AUTORIDAD "+
                                                  ", prog.nombre as PROGRAMA "+
                                                  ", proy.nombre as PROYECTO "+
                                                  ", acti.nombre as ACTIVIDAD "+
                                                 ", acti.porc_cumplimiento as PORC_CUMPLI_ACTIVIDAD "+
                                                   ", acti.porc_ejecucion as PORC_EJECUCION "+
                                                   ", acti.presupuesto_asignado as PRESUP_ASIG "+
                                                   ", acti.presupuesto_ejecutado as PRESUP_EJECUTADO "+  
                                                "from pomt_pomca pomc "+
                                                   ", pomt_programa prog "+ 
                                                   ", pomt_proyecto proy "+
                                                   ", pomt_actividad acti "+
                                                   ", autoridades autor "+
                                                   ", fntt_fuente fuente  "+ 
                                               "where 1=1 "+
                                                 "and pomc.id_fuente = fuente.id "+
                                                 "and pomc.id_autoridad = autor.id  "+
                                                 "and pomc.id = prog.id_pomca "+
                                                 "and prog.id = proy.id_programa "+
                                                 "and acti.id_proyecto = proy.id ";
  
  public static final String getPueaEstados ="Select l.valor As Descripcion " + 
                                    "      , count(*) As cantidad " + 
                                    "      From " + 
                                    "      Pueat_Puea Pu  " + 
                                    "      left Outer Join Rurt_usuarios_agua uu   on uu.id= PU.ID_USUARIO " + 
                                    "      left Outer Join Part_Listas L          On L.Id = Pu.Id_Listas  and L.Id_Categoria = 95 " + 
                                    "      left Outer Join Autoridades Au         On au.id = uu.id_autoridad";
  
public static final String getPueaSeguimiento = "Select Au.SIGLA As Descripcion " + 
                                                "      , count(*) As cantidad " + 
                                                "      From Pueat_Puea Pu   " + 
                                                "      left Outer Join Rurt_usuarios_agua uu   on uu.id= PU.ID_USUARIO " + 
                                                "      left Outer Join Part_Listas L  On L.Id = Pu.Id_Listas  and L.Id_Categoria = 95 " + 
                                                "      left Outer Join Autoridades Au         On au.id = uu.id_autoridad " + 
                                                "      Where  L.ID In (1048, 1050, 1047) ";
}

          