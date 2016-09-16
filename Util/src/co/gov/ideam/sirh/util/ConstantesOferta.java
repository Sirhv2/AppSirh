package co.gov.ideam.sirh.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ConstantesOferta {
     
    
    public static final String NVL_MDO_DRO_NVQ = "NVL_MDO_DRO_NVQ";//Nivel Medio Diario en metamodelo
    
    public static final String NVL_MDO_MNS_NVQ = "NVL_MDO_MNS_NVQ";//Nivel Medio Mensual en metamodelo
    
    public static final String NVL_MNM_ABS_MNS_NVQ = "NVL_MNM_ABS_MNS_NVQ";//Nivel Minimo Absoluto Mensual en metamodelo
    public static final String NVL_MNM_MDO_MNS_NVQ = "NVL_MNM_MDO_MNS_NVQ";//Nivel Minimo Medio Mensual en metamodelo
    public static final String NVL_MXM_ABS_MNS_NVQ = "NVL_MXM_ABS_MNS_NVQ";//Nivel Maximo Absoluto Mensual en metamodelo
    public static final String NVL_MXM_MDO_MNS_NVQ = "NVL_MXM_MDO_MNS_NVQ";//Nivel Maximo Medio Mensual en metamodelo
    
    
    public static final String CDL_MDO_DRO_NVQ = "CDL_MDO_DRO_NVQ";//Caudal Medio Diario en metamodelo
    public static final String CDL_MNM_DRO_NVQ = "CDL_MNM_DRO_NVQ";//Caudal Minimo Diario en metamodelo
    public static final String CDL_MXM_DRO_NVQ = "CDL_MXM_DRO_NVQ";//Caudal Maximo Diario en metamodelo
    
    public static final String CDL_MDO_MNS_NVQ = "CDL_MDO_MNS_NVQ";//Caudales Medios Mensuales
    public static final String CDL_MXM_MDO_MNS_NVQ = "CDL_MXM_MDO_MNS_NVQ";//Caudales Máximos Mensuales
    public static final String CDL_MNM_MDO_MNS_NVQ = "CDL_MNM_MDO_MNS_NVQ";//Caudales Mínimos Mensuales  
    
    public static final String SDM_CNT_MDO_DRO_SDM = "SDM_CNT_MDO_DRO_SDM";//Sedimentos Concentracion Media Diario en metamodelo
    public static final String SDM_CNT_MDO_SPF_DRO = "SDM_CNT_MDO_SPF_DRO";//Sedimentos Concentracion Media Superficial Diario en metamodelo
    public static final String SDM_TRN_MXMD_MNS_SDM = "SDM_TRN_MXMD_MNS_SDM";//Sedimentos Transporte Maximo Diario en metamodelo
    public static final String SDM_TRN_TTL_DRO_SDM = "SDM_TRN_TTL_DRO_SDM";//Sedimentos transporte Total Diario en metamodelo
    public static final String SDM_TRN_MXM_TTL_MNS = "SDM_TRN_MXM_TTL_MNS";//Transporte Máximos Mensuales 
    
    public static final String SDM_CNT_MDO_MNS_SDM = "SDM_CNT_MDO_MNS_SDM";//Sedimentos Concentracion Media Mensual en metamodelo
    public static final String SDM_CNT_MNM_MNS_SDM = "SDM_CNT_MNM_MNS_SDM";//Sedimentos Concentracion Minima Mensual en metamodelo
    public static final String SDM_CNT_MXM_MNS_SDM = "SDM_CNT_MXM_MNS_SDM";//Sedimentos Concentracion Maxima Mensual en metamodelo
    public static final String SDM_TRN_MDO_MNS_SDM = "SDM_TRN_MDO_MNS_SDM";//Sedimentos transporte Medio Mensual en metamodelo
    public static final String SDM_TRN_TTL_MNS_SDM = "SDM_TRN_TTL_MNS_SDM";//Sedimentos Transporte Total Mensual en metamodelo
    
    public static final Integer CAN_DATOS_MIN_HISTOGRAMA = 25;//valor minimo de cantidad de datos que debe tener la serie.
    public static final Integer CAN_DATOS_MIN_SERIE = 1;//valor minimo de cantidad de datos que debe tener la serie.
    public static final Integer PORCENTAJE_DATOS_MIN = 70;//valor minimo de porcentaje de datos que debe tener la serie.
    
    public static final String BOX_PLOT_MEDIOS = "1-MEDIOS";
    public static final String BOX_PLOT_MAXIMOS = "2-MAXIMO";
    public static final String BOX_PLOT_MINIMOS = "3-MINIMO";
    
    public static final String PREFIJO_MES = "MNS";
    public static final String PREFIJO_DIA = "DRO";
    public static final String PREFIJO_NIVEL = "NVL";
    public static final String PREFIJO_CAUDAL = "CDL";
    public static final String PREFIJO_SEDIMENTO = "SDM";
    public static final String PREFIJO_CONCENTRACION = "CNT";
    public static final String PREFIJO_TRANSPORTE = "TRN";
    
    //CDONCEL VARIABLES DE DATOS METEOROLOGICOS
    public static final String PCP_TTL_DRO_MET2 = "PCP_TTL_DRO_MET2"; //Precipitación Transporte Diario
    public static final String PCP_TTL_MNS_MET2 = "PCP_TTL_MNS_MET2"; //Precipitación Transporte Mensual
    public static final String EVP_TTL_DRO_MET2 = "EVP_TTL_DRO_MET2"; //Evaporación Transporte Diario
    public static final String EVP_TTL_MNS_MET2 = "EVP_TTL_MNS_MET2"; //Evaporación Transporte Mensual
   //FIN CDONCEL 
    
    public static final HashMap<String, String> variablesSerie = new HashMap<String, String>();
    static {
        variablesSerie.put(NVL_MDO_DRO_NVQ, "Nivel Medio Diario");
        variablesSerie.put(NVL_MDO_MNS_NVQ, "Nivel Medio Mensual");
        variablesSerie.put(NVL_MNM_ABS_MNS_NVQ, "Nivel Minimo Absoluto Mensual");
        variablesSerie.put(NVL_MNM_MDO_MNS_NVQ, "Nivel Minimo Medio Mensual");
        variablesSerie.put(NVL_MXM_ABS_MNS_NVQ, "Nivel Maximo Absoluto Mensual");
        variablesSerie.put(NVL_MXM_MDO_MNS_NVQ, "Nivel Maximo Medio Mensual");
        variablesSerie.put(CDL_MDO_DRO_NVQ, "Caudal Medio Diario");
        variablesSerie.put(CDL_MNM_DRO_NVQ, "Caudal Minimo Diario");
        variablesSerie.put(CDL_MXM_DRO_NVQ, "Caudal Maximo Diario");
        variablesSerie.put(CDL_MDO_MNS_NVQ, "Caudales Medios Mensuales");
        variablesSerie.put(CDL_MXM_MDO_MNS_NVQ, "Caudales Máximos Mensuales");
        variablesSerie.put(CDL_MNM_MDO_MNS_NVQ, "Caudales Mínimos Mensuales");  
        variablesSerie.put(SDM_CNT_MDO_DRO_SDM, "Sedimentos Concentracion Media Diario");
        variablesSerie.put(SDM_CNT_MDO_SPF_DRO, "Sedimentos Concentracion Media Superficial Diario");
        variablesSerie.put(SDM_TRN_MXMD_MNS_SDM, "Sedimentos Transporte Maximo Diario");
        variablesSerie.put(SDM_TRN_TTL_DRO_SDM, "Sedimentos transporte Total Diario");
        variablesSerie.put(SDM_TRN_MXM_TTL_MNS, "Transporte Máximos Mensuales"); 
        variablesSerie.put(SDM_CNT_MDO_MNS_SDM, "Sedimentos Concentracion Media Mensual");
        variablesSerie.put(SDM_CNT_MNM_MNS_SDM, "Sedimentos Concentracion Minima Mensual");
        variablesSerie.put(SDM_CNT_MXM_MNS_SDM, "Sedimentos Concentracion Maxima Mensual");
        variablesSerie.put(SDM_TRN_MDO_MNS_SDM, "Sedimentos transporte Medio Mensual");
        variablesSerie.put(SDM_TRN_TTL_MNS_SDM, "Sedimentos Transporte Total Mensual");
      //CDONCEL       
        variablesSerie.put(PCP_TTL_DRO_MET2, "Precipitación Transporte Diario");
        variablesSerie.put(PCP_TTL_MNS_MET2, "Precipitación Transporte Mensual");
        variablesSerie.put(EVP_TTL_DRO_MET2, "Evaporación Transporte Diario");
        variablesSerie.put(EVP_TTL_MNS_MET2, "Evaporación Transporte Mensual");
      //FIN CDONCEL
    } 
    
    public static final HashMap<String, String> variablesSerieAnual = new HashMap<String, String>();
    static {
        variablesSerieAnual.put(NVL_MDO_DRO_NVQ, "Nivel Medio");
        variablesSerieAnual.put(NVL_MDO_MNS_NVQ, "Nivel Medio");
        variablesSerieAnual.put(NVL_MNM_ABS_MNS_NVQ, "Nivel Minimo Absoluto");
        variablesSerieAnual.put(NVL_MNM_MDO_MNS_NVQ, "Nivel Minimo Medio");
        variablesSerieAnual.put(NVL_MXM_ABS_MNS_NVQ, "Nivel Maximo Absoluto");
        variablesSerieAnual.put(NVL_MXM_MDO_MNS_NVQ, "Nivel Maximo Medio");
        variablesSerieAnual.put(CDL_MDO_DRO_NVQ, "Caudal Medio");
        variablesSerieAnual.put(CDL_MNM_DRO_NVQ, "Caudal Minimo");
        variablesSerieAnual.put(CDL_MXM_DRO_NVQ, "Caudal Maximo");
        variablesSerieAnual.put(CDL_MDO_MNS_NVQ, "Caudales Medios");
        variablesSerieAnual.put(CDL_MXM_MDO_MNS_NVQ, "Caudales Máximos");
        variablesSerieAnual.put(CDL_MNM_MDO_MNS_NVQ, "Caudales Mínimos");  
        variablesSerieAnual.put(SDM_CNT_MDO_DRO_SDM, "Sedimentos Concentracion Media");
        variablesSerieAnual.put(SDM_CNT_MDO_SPF_DRO, "Sedimentos Concentracion Media Superficial");
        variablesSerieAnual.put(SDM_TRN_MXMD_MNS_SDM, "Sedimentos Transporte Maximo");
        variablesSerieAnual.put(SDM_TRN_TTL_DRO_SDM, "Sedimentos transporte Total");
        variablesSerieAnual.put(SDM_TRN_MXM_TTL_MNS, "Transporte Máximos"); 
        variablesSerieAnual.put(SDM_CNT_MDO_MNS_SDM, "Sedimentos Concentracion Media");
        variablesSerieAnual.put(SDM_CNT_MNM_MNS_SDM, "Sedimentos Concentracion Minima");
        variablesSerieAnual.put(SDM_CNT_MXM_MNS_SDM, "Sedimentos Concentracion Maxima");
        variablesSerieAnual.put(SDM_TRN_MDO_MNS_SDM, "Sedimentos transporte Medio");
        variablesSerieAnual.put(SDM_TRN_TTL_MNS_SDM, "Sedimentos Transporte Total");
      //CDONCEL
        variablesSerieAnual.put(PCP_TTL_MNS_MET2, "Precipitación Transporte Total");
        variablesSerieAnual.put(EVP_TTL_MNS_MET2, "Evaporación Transporte Total");
      //FIN CDONCEL
    } 
    
    public static final List<String> variablesSerieOrdenada = new ArrayList<String>();
    static {
        variablesSerieOrdenada.add(NVL_MDO_DRO_NVQ);//Niveles Medios Diarios       
        variablesSerieOrdenada.add(CDL_MDO_DRO_NVQ);//Caudales Medios Diarios
        variablesSerieOrdenada.add(CDL_MNM_DRO_NVQ);//Caudal Minimo Diario
        variablesSerieOrdenada.add(CDL_MXM_DRO_NVQ);//Caudal Maximo Diario
        variablesSerieOrdenada.add(SDM_CNT_MDO_DRO_SDM);//Sedimentos Concentración Medios Diarios
        variablesSerieOrdenada.add(SDM_CNT_MDO_SPF_DRO);//Sedimentos Concentracion Media Superficial Diario
        variablesSerieOrdenada.add(SDM_TRN_TTL_DRO_SDM);//Sedimentos transporte Total Diario
        variablesSerieOrdenada.add(NVL_MDO_MNS_NVQ);//Niveles Medios Mensuales
        variablesSerieOrdenada.add(NVL_MNM_ABS_MNS_NVQ);//Nivel Minimo Absoluto Mensual
        variablesSerieOrdenada.add(NVL_MNM_MDO_MNS_NVQ);//Nivel Minimo Medio Mensual
        variablesSerieOrdenada.add(NVL_MXM_ABS_MNS_NVQ);//Nivel Maximo Absoluto Mensual
        variablesSerieOrdenada.add(NVL_MXM_MDO_MNS_NVQ);//Nivel Maximo Medio Mensual
        variablesSerieOrdenada.add(CDL_MDO_MNS_NVQ);//Caudales Medios Mensuales
        variablesSerieOrdenada.add(CDL_MXM_MDO_MNS_NVQ);//Caudal Máximo Mensual
        variablesSerieOrdenada.add(CDL_MNM_MDO_MNS_NVQ); //Caudal Minimo Mensual
        variablesSerieOrdenada.add(SDM_CNT_MDO_MNS_SDM);//Sedimentos Concentración Medios Mensuales
        variablesSerieOrdenada.add(SDM_TRN_MXMD_MNS_SDM);//Sedimentos Transporte Maximo Mensual
        variablesSerieOrdenada.add(SDM_TRN_MXM_TTL_MNS);//Sedimentos Transporte Máximos Mensuales
        variablesSerieOrdenada.add(SDM_CNT_MNM_MNS_SDM);//Sedimentos Concentracion Minima Mensual
        variablesSerieOrdenada.add(SDM_CNT_MXM_MNS_SDM);//Sedimentos Concentracion Maxima Mensual
        variablesSerieOrdenada.add(SDM_TRN_MDO_MNS_SDM);//Sedimentos Transporte Medio Mensual
        variablesSerieOrdenada.add(SDM_TRN_TTL_MNS_SDM);//Sedimentos Transporte Total Mensual
 //CDONCEL       
        variablesSerieOrdenada.add(PCP_TTL_DRO_MET2); //Precipitación Transporte Diario
        variablesSerieOrdenada.add(PCP_TTL_MNS_MET2); //Precipitación Transporte Mensual
        variablesSerieOrdenada.add(EVP_TTL_DRO_MET2); //Evaporación Transporte Diario
        variablesSerieOrdenada.add(EVP_TTL_MNS_MET2); //Evaporación Transporte Mensual
//FIN CDONCEL        
    }
}
