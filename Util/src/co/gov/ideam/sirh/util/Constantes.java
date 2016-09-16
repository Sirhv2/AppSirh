package co.gov.ideam.sirh.util;

public class Constantes {
    // Codigo de la autoridad Ideam
    public static final Long IDEAM = 1L;
    
    // Codigo de la fuente que se debe totalizar en el metodo getDemandaTramo
    public static final Integer TIPO_FUENTE_TOTALIZAR = 30;
    
    // Validaciones datos de entrada georeferenciacion
    public static final Long LATITUD_GRADOS_MIN = -4L;
    public static final Long LATITUD_GRADOS_MAX= 15L;
    public static final Long LONGITUD_GRADOS_MIN = -82L;
    public static final Long LONGITUD_GRADOS_MAX= -66L;
    public static final Long MINUTOS_MIN = 0L;
    public static final Long MINUTOS_MAX= 59L;
    public static final Double SEGUNDOS_MIN = 0D;
    public static final Double SEGUNDOS_MAX= 59.99999D;
    public static final Double ALTITUD_MIN = 0D;
    public static final Double ALTITUD_MAX = 5500D;
    
    public static final Integer TRUE_TO_INTEGER = 1;
    public static final Integer FALSE_TO_INTEGER = 0;
    
    // PARAMETROS ICA
    public static final Integer OXIGENO_DISULETO = 322;
    public static final Integer SOLIDOS_SUSPENDIDOS = 325;
    public static final Integer DEMANDA_OXIGENO = 323;
    public static final Integer NITROGENO_TOTAL = 435;
    public static final Integer CONDUCTIVIDAD_ELECTRICA = 312;
    public static final Integer FOSFORO_TOTAL = 321;
    public static final Integer COLIFORMES = 360;
    public static final Integer TEMPERATURA = 566;
    public static final Integer PH = 647;

    // PARAMETROS ICA IDEAM
    public static final Integer TEMPERATURA_IDEAM = 4;
    public static final Integer OXIGENO_DISULETO_IDEAM = 37;
    public static final Integer SOLIDOS_SUSPENDIDOS_IDEAM = 44;
    public static final Integer DEMANDA_OXIGENO_IDEAM = 39;
    public static final Integer CONDUCTIVIDAD_ELECTRICA_IDEAM = 20;
    public static final Integer PH_IDEAM = 5;    
    public static final Integer NITROGENO_TOTAL_IDEAM = 324014;
    public static final Integer FOSFORO_TOTAL_IDEAM = 33;
    public static final Integer COLIFORMES_IDEAM = 120012;    
    
}
    