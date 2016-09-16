package co.gov.ideam.sirh.oferta.view.grafica;

import co.gov.ideam.sirh.oferta.model.ShmvDiariosHid;
import co.gov.ideam.sirh.oferta.model.ShmvMensualesHid;
import co.gov.ideam.sirh.util.IdeamException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class Util {
    public static final String ASCENDENTE = "ASC";
    public static final String DESCENDENTE = "DESC";
    
    public static List extractValidData(List datos) throws IdeamException {
        List<Double> data = null;

        if (datos != null && !datos.isEmpty()) {
            data = new ArrayList();
            if (datos.get(0) instanceof ShmvDiariosHid) {
                for (ShmvDiariosHid diario : (List<ShmvDiariosHid>)datos) {
                    if (diario.getDrsValor1() != null) {
                        data.add(diario.getDrsValor1());
                    }
                    if (diario.getDrsValor2() != null) {
                        data.add(diario.getDrsValor2());
                    }
                    if (diario.getDrsValor3() != null) {
                        data.add(diario.getDrsValor3());
                    }
                    if (diario.getDrsValor4() != null) {
                        data.add(diario.getDrsValor4());
                    }
                    if (diario.getDrsValor5() != null) {
                        data.add(diario.getDrsValor5());
                    }
                    if (diario.getDrsValor6() != null) {
                        data.add(diario.getDrsValor6());
                    }
                    if (diario.getDrsValor7() != null) {
                        data.add(diario.getDrsValor7());
                    }
                    if (diario.getDrsValor8() != null) {
                        data.add(diario.getDrsValor8());
                    }
                    if (diario.getDrsValor9() != null) {
                        data.add(diario.getDrsValor9());
                    }
                    if (diario.getDrsValor10() != null) {
                        data.add(diario.getDrsValor10());
                    }
                    if (diario.getDrsValor11() != null) {
                        data.add(diario.getDrsValor11());
                    }
                    if (diario.getDrsValor12() != null) {
                        data.add(diario.getDrsValor12());
                    }
                    if (diario.getDrsValor13() != null) {
                        data.add(diario.getDrsValor13());
                    }
                    if (diario.getDrsValor14() != null) {
                        data.add(diario.getDrsValor14());
                    }
                    if (diario.getDrsValor15() != null) {
                        data.add(diario.getDrsValor15());
                    }
                    if (diario.getDrsValor16() != null) {
                        data.add(diario.getDrsValor16());
                    }
                    if (diario.getDrsValor17() != null) {
                        data.add(diario.getDrsValor17());
                    }
                    if (diario.getDrsValor18() != null) {
                        data.add(diario.getDrsValor18());
                    }
                    if (diario.getDrsValor19() != null) {
                        data.add(diario.getDrsValor19());
                    }
                    if (diario.getDrsValor20() != null) {
                        data.add(diario.getDrsValor20());
                    }
                    if (diario.getDrsValor21() != null) {
                        data.add(diario.getDrsValor21());
                    }
                    if (diario.getDrsValor22() != null) {
                        data.add(diario.getDrsValor22());
                    }
                    if (diario.getDrsValor23() != null) {
                        data.add(diario.getDrsValor23());
                    }
                    if (diario.getDrsValor24() != null) {
                        data.add(diario.getDrsValor24());
                    }
                    if (diario.getDrsValor25() != null) {
                        data.add(diario.getDrsValor25());
                    }
                    if (diario.getDrsValor26() != null) {
                        data.add(diario.getDrsValor26());
                    }
                    if (diario.getDrsValor27() != null) {
                        data.add(diario.getDrsValor27());
                    }
                    if (diario.getDrsValor28() != null) {
                        data.add(diario.getDrsValor28());
                    }
                    if (diario.getDrsValor29() != null) {
                        data.add(diario.getDrsValor29());
                    }
                    if (diario.getDrsValor30() != null) {
                        data.add(diario.getDrsValor30());
                    }
                    if (diario.getDrsValor31() != null) {
                        data.add(diario.getDrsValor31());
                    }
                }
            } else if (datos.get(0) instanceof ShmvMensualesHid) {
                for (ShmvMensualesHid mes : (List<ShmvMensualesHid>)datos) {
                    if (mes.getMnsValor1() != null) {
                        data.add(mes.getMnsValor1());
                    }
                    if (mes.getMnsValor2() != null) {
                        data.add(mes.getMnsValor2());
                    }
                    if (mes.getMnsValor3() != null) {
                        data.add(mes.getMnsValor3());
                    }
                    if (mes.getMnsValor4() != null) {
                        data.add(mes.getMnsValor4());
                    }
                    if (mes.getMnsValor5() != null) {
                        data.add(mes.getMnsValor5());
                    }
                    if (mes.getMnsValor6() != null) {
                        data.add(mes.getMnsValor6());
                    }
                    if (mes.getMnsValor7() != null) {
                        data.add(mes.getMnsValor7());
                    }
                    if (mes.getMnsValor8() != null) {
                        data.add(mes.getMnsValor8());
                    }
                    if (mes.getMnsValor9() != null) {
                        data.add(mes.getMnsValor9());
                    }
                    if (mes.getMnsValor10() != null) {
                        data.add(mes.getMnsValor10());
                    }
                    if (mes.getMnsValor11() != null) {
                        data.add(mes.getMnsValor11());
                    }
                    if (mes.getMnsValor12() != null) {
                        data.add(mes.getMnsValor12());
                    }
                }
            }
        }
        return data;
    }
    
    public static List extractNullData(List datos) throws IdeamException {
        List<Double> data = null;

        if (datos != null && !datos.isEmpty()) {
            data = new ArrayList();
            if (datos.get(0) instanceof ShmvDiariosHid) {
                for (ShmvDiariosHid diario : (List<ShmvDiariosHid>)datos) {
                    if (diario.getDrsValor1() == null) {
                        data.add(diario.getDrsValor1());
                    }
                    if (diario.getDrsValor2() == null) {
                        data.add(diario.getDrsValor2());
                    }
                    if (diario.getDrsValor3() == null) {
                        data.add(diario.getDrsValor3());
                    }
                    if (diario.getDrsValor4() == null) {
                        data.add(diario.getDrsValor4());
                    }
                    if (diario.getDrsValor5() == null) {
                        data.add(diario.getDrsValor5());
                    }
                    if (diario.getDrsValor6() == null) {
                        data.add(diario.getDrsValor6());
                    }
                    if (diario.getDrsValor7() == null) {
                        data.add(diario.getDrsValor7());
                    }
                    if (diario.getDrsValor8() == null) {
                        data.add(diario.getDrsValor8());
                    }
                    if (diario.getDrsValor9() == null) {
                        data.add(diario.getDrsValor9());
                    }
                    if (diario.getDrsValor10() == null) {
                        data.add(diario.getDrsValor10());
                    }
                    if (diario.getDrsValor11() == null) {
                        data.add(diario.getDrsValor11());
                    }
                    if (diario.getDrsValor12() == null) {
                        data.add(diario.getDrsValor12());
                    }
                    if (diario.getDrsValor13() == null) {
                        data.add(diario.getDrsValor13());
                    }
                    if (diario.getDrsValor14() == null) {
                        data.add(diario.getDrsValor14());
                    }
                    if (diario.getDrsValor15() == null) {
                        data.add(diario.getDrsValor15());
                    }
                    if (diario.getDrsValor16() == null) {
                        data.add(diario.getDrsValor16());
                    }
                    if (diario.getDrsValor17() == null) {
                        data.add(diario.getDrsValor17());
                    }
                    if (diario.getDrsValor18() == null) {
                        data.add(diario.getDrsValor18());
                    }
                    if (diario.getDrsValor19() == null) {
                        data.add(diario.getDrsValor19());
                    }
                    if (diario.getDrsValor20() == null) {
                        data.add(diario.getDrsValor20());
                    }
                    if (diario.getDrsValor21() == null) {
                        data.add(diario.getDrsValor21());
                    }
                    if (diario.getDrsValor22() == null) {
                        data.add(diario.getDrsValor22());
                    }
                    if (diario.getDrsValor23() == null) {
                        data.add(diario.getDrsValor23());
                    }
                    if (diario.getDrsValor24() == null) {
                        data.add(diario.getDrsValor24());
                    }
                    if (diario.getDrsValor25() == null) {
                        data.add(diario.getDrsValor25());
                    }
                    if (diario.getDrsValor26() == null) {
                        data.add(diario.getDrsValor26());
                    }
                    if (diario.getDrsValor27() == null) {
                        data.add(diario.getDrsValor27());
                    }
                    if (diario.getDrsValor28() == null) {
                        data.add(diario.getDrsValor28());
                    }
                    if (diario.getDrsValor29() == null) {
                        data.add(diario.getDrsValor29());
                    }
                    if (diario.getDrsValor30() == null) {
                        data.add(diario.getDrsValor30());
                    }
                    if (diario.getDrsValor31() == null) {
                        data.add(diario.getDrsValor31());
                    }
                }
            } else if (datos.get(0) instanceof ShmvMensualesHid) {
                for (ShmvMensualesHid mes : (List<ShmvMensualesHid>)datos) {
                    if (mes.getMnsValor1() == null) {
                        data.add(mes.getMnsValor1());
                    }
                    if (mes.getMnsValor2() == null) {
                        data.add(mes.getMnsValor2());
                    }
                    if (mes.getMnsValor3() == null) {
                        data.add(mes.getMnsValor3());
                    }
                    if (mes.getMnsValor4() == null) {
                        data.add(mes.getMnsValor4());
                    }
                    if (mes.getMnsValor5() == null) {
                        data.add(mes.getMnsValor5());
                    }
                    if (mes.getMnsValor6() == null) {
                        data.add(mes.getMnsValor6());
                    }
                    if (mes.getMnsValor7() == null) {
                        data.add(mes.getMnsValor7());
                    }
                    if (mes.getMnsValor8() == null) {
                        data.add(mes.getMnsValor8());
                    }
                    if (mes.getMnsValor9() == null) {
                        data.add(mes.getMnsValor9());
                    }
                    if (mes.getMnsValor10() == null) {
                        data.add(mes.getMnsValor10());
                    }
                    if (mes.getMnsValor11() == null) {
                        data.add(mes.getMnsValor11());
                    }
                    if (mes.getMnsValor12() == null) {
                        data.add(mes.getMnsValor12());
                    }
                }
            }
        }
        return data;
    }
    
    public static List<Double> sortData(List<Double> datos, String orden) throws IdeamException {
        List<Double> resultado = new ArrayList();
        
        if(datos!=null && !datos.isEmpty()){
            resultado = datos;
            
            if(orden.equalsIgnoreCase(DESCENDENTE)){
                Comparator<Double> comparador = Collections.reverseOrder();
                Collections.sort(resultado, comparador);
            }else if(orden.equalsIgnoreCase(ASCENDENTE)){
                Collections.sort(resultado);
            }
            
        }
        
        return resultado;
    }
    
    @Deprecated
    public static List<Double> sortData1(List<Double> datos) throws IdeamException {
        List<Double> datosAux = new ArrayList();
        List<Double> resultado = new ArrayList();
        if(datos!=null && !datos.isEmpty()){
            datosAux = datos;
            int indice = 0;
            for(Double dato : datos){
                if(esMayor(datosAux, dato)){
                    resultado.add(dato);
                    break;
                }
                indice ++;
            }
            datosAux.remove(indice);
            resultado.addAll(sortData1(datosAux));//llamado recursivo a esta misma función.
        }
        
        return resultado;
    }
    
    @Deprecated
    private static boolean esMayor(List<Double> datosAux, Double valor) throws IdeamException {
        boolean esMayor = true;
        for(Double dato : datosAux){
            if(dato > valor){//si en la lista hay una dato mayor.
                esMayor = false;//no es el mayor.
                break;//rompe la comparación.
            }
        }
        return esMayor;
    }
  
}
