package co.gov.ideam.sirh.oferta.view.grafica;

import co.gov.ideam.sirh.oferta.model.ShmvDiariosHid;
import co.gov.ideam.sirh.oferta.model.ShmvMensualesHid;
import co.gov.ideam.sirh.util.IdeamException;

import java.util.List;

public class TendenciaCentral extends Grafica{
    
    private long cantidadTotalSerie;
    private long cantidadTotalValid;
    private long cantidadNulos;
    private double sumaTotalValid;
    private double maximo;
    private double minimo;
    private String fechaMinima;
    private String fechaMaxima;
    private double q75;
    private double q25;
    private int datosIniciales;// Calcula los valores nulos a partir del inicio de los datos.
    private int datosFinales;// Calcula los valores nulos al final de los datos.
    
    
    private Double media;
    
    public void calcular(List datos) throws IdeamException {
        this.sumaTotalValid = 0;
        this.cantidadTotalValid = 0;
        this.cantidadTotalSerie = 0;
        this.cantidadNulos = 0;
        this.media = null;
        
        if(datos!=null && !datos.isEmpty()){
            this.fechaMinima = this.fechaMinima(datos);
            this.fechaMaxima = this.fechaMaxima(datos);
            
            this.cantidadTotalSerie = this.serie(datos) - 
                                 (this.datosIniciales + this.datosFinales);;
            this.cantidadTotalValid = this.poblacionValid(datos);
            this.sumaTotalValid = this.sumaValid(datos);
            this.cantidadNulos = this.nulos(datos) - 
                                 (this.datosIniciales + this.datosFinales);
            
            this.media = this.calcularMedia(datos);
            
            
            List serieValida = Util.extractValidData(datos);
            this.q25 = this.cuartil(serieValida, 25);
            this.q75 = this.cuartil(serieValida, 75);
            
            
        }
    }
    
    
    private Double sumaValid(List datos) throws IdeamException{
        Double sumaTotal = new Double(0);
        if(datos!=null && !datos.isEmpty()){
            if(datos.get(0) instanceof ShmvDiariosHid){
                for(ShmvDiariosHid diario : (List<ShmvDiariosHid>)datos ){
                    sumaTotal += diario.getSumaTotalValid();
                }
            }else if(datos.get(0) instanceof ShmvMensualesHid){
                for(ShmvMensualesHid mes : (List<ShmvMensualesHid>)datos ){
                    sumaTotal += mes.getSumaTotalValid();
                }
            }
        }
        return sumaTotal;
    }
    
    public Long poblacionValid(List datos) throws IdeamException{
        Long cantidadTotal = new Long(0);
        if(datos!=null && !datos.isEmpty()){
            if(datos.get(0) instanceof ShmvDiariosHid){
                for(ShmvDiariosHid diario : (List<ShmvDiariosHid>)datos ){
                    cantidadTotal += diario.getCantidadDatosValid();
                }
            }else if(datos.get(0) instanceof ShmvMensualesHid){
                for(ShmvMensualesHid mes : (List<ShmvMensualesHid>)datos ){
                    cantidadTotal += mes.getCantidadDatosValid();
                }
            }
        }
        return cantidadTotal;
    }
    
    public Long serie(List datos) throws IdeamException{
        Long cantidadTotal = new Long(0);
        if(datos!=null && !datos.isEmpty()){
            if(datos.get(0) instanceof ShmvDiariosHid){
                for(ShmvDiariosHid diario : (List<ShmvDiariosHid>)datos ){
                    cantidadTotal += diario.getCantidadDatosValid();
                    cantidadTotal += diario.getCantidadDatosNull();
                }
            }else if(datos.get(0) instanceof ShmvMensualesHid){
                for(ShmvMensualesHid mes : (List<ShmvMensualesHid>)datos ){
                    cantidadTotal += mes.getCantidadDatosValid();
                    cantidadTotal += mes.getCantidadDatosNull();
                }
            }
        }
        return cantidadTotal;
    }
    
    public Long nulos(List datos) throws IdeamException{
        Long cantidadTotal = new Long(0);
        if(datos!=null && !datos.isEmpty()){
            if(datos.get(0) instanceof ShmvDiariosHid){
                for(ShmvDiariosHid diario : (List<ShmvDiariosHid>)datos ){
                    cantidadTotal += diario.getCantidadDatosNull();
                }
            }else if(datos.get(0) instanceof ShmvMensualesHid){
                for(ShmvMensualesHid mes : (List<ShmvMensualesHid>)datos ){
                    cantidadTotal += mes.getCantidadDatosNull();
                }
            }
        }
        return cantidadTotal;
    }
    
    private Double calcularMedia(List datos) throws IdeamException{
        long cantidadDatos = 0;
        double sumaDatos = 0;
        Double media = null;
        
        if(datos!=null && !datos.isEmpty()){
            if(datos.get(0) instanceof ShmvDiariosHid){
                for(ShmvDiariosHid diario : (List<ShmvDiariosHid>)datos ){
                    cantidadDatos += diario.getCantidadDatosValid();
                    sumaDatos += diario.getSumaTotalValid();
                }
            }else if(datos.get(0) instanceof ShmvMensualesHid){
                for(ShmvMensualesHid mes : (List<ShmvMensualesHid>)datos ){
                    cantidadDatos += mes.getCantidadDatosValid();
                    sumaDatos += mes.getSumaTotalValid();
                }
            }
            media = (double)(sumaDatos / (double)((cantidadDatos!=0) ? cantidadDatos : 1));
        }
        return media;
    }
    
    private String fechaMinima(List datos) throws IdeamException{
        String fecha = "";
        if(datos!=null && !datos.isEmpty()){
            this.datosIniciales = 0;
            if(datos.get(0) instanceof ShmvDiariosHid){
                ShmvDiariosHid diario = (ShmvDiariosHid) datos.get(0);
                if(diario.getDrsValor1()!=null){
                    fecha = diario.getDrsAno()+"/"+diario.getDrsMes()+"/01";
                }else if(diario.getDrsValor2()!=null){
                    fecha = diario.getDrsAno()+"/"+diario.getDrsMes()+"/02";
                    this.datosIniciales = 1;
                }else if(diario.getDrsValor3()!=null){
                    fecha = diario.getDrsAno()+"/"+diario.getDrsMes()+"/03";
                    this.datosIniciales = 2;
                }else if(diario.getDrsValor4()!=null){
                    fecha = diario.getDrsAno()+"/"+diario.getDrsMes()+"/04";
                    this.datosIniciales = 3;
                }else if(diario.getDrsValor5()!=null){
                    fecha = diario.getDrsAno()+"/"+diario.getDrsMes()+"/05";
                    this.datosIniciales = 4;
                }else if(diario.getDrsValor6()!=null){
                    fecha = diario.getDrsAno()+"/"+diario.getDrsMes()+"/06";
                    this.datosIniciales = 5;
                }else if(diario.getDrsValor7()!=null){
                    fecha = diario.getDrsAno()+"/"+diario.getDrsMes()+"/07";
                    this.datosIniciales = 6;
                }else if(diario.getDrsValor8()!=null){
                    fecha = diario.getDrsAno()+"/"+diario.getDrsMes()+"/08";
                    this.datosIniciales = 7;
                }else if(diario.getDrsValor9()!=null){
                    fecha = diario.getDrsAno()+"/"+diario.getDrsMes()+"/09";
                    this.datosIniciales = 8;
                }else if(diario.getDrsValor10()!=null){
                    fecha = diario.getDrsAno()+"/"+diario.getDrsMes()+"/10";
                    this.datosIniciales = 9;
                }else if(diario.getDrsValor11()!=null){
                    fecha = diario.getDrsAno()+"/"+diario.getDrsMes()+"/11";
                    this.datosIniciales = 10;
                }else if(diario.getDrsValor12()!=null){
                    fecha = diario.getDrsAno()+"/"+diario.getDrsMes()+"/12";
                    this.datosIniciales = 11;
                }else if(diario.getDrsValor13()!=null){
                    fecha = diario.getDrsAno()+"/"+diario.getDrsMes()+"/13";
                    this.datosIniciales = 12;
                }else if(diario.getDrsValor14()!=null){
                    fecha = diario.getDrsAno()+"/"+diario.getDrsMes()+"/14";
                    this.datosIniciales = 13;
                }else if(diario.getDrsValor15()!=null){
                    fecha = diario.getDrsAno()+"/"+diario.getDrsMes()+"/15";
                    this.datosIniciales = 14;
                }else if(diario.getDrsValor16()!=null){
                    fecha = diario.getDrsAno()+"/"+diario.getDrsMes()+"/16";
                    this.datosIniciales = 15;
                }else if(diario.getDrsValor17()!=null){
                    fecha = diario.getDrsAno()+"/"+diario.getDrsMes()+"/17";
                    this.datosIniciales = 16;
                }else if(diario.getDrsValor18()!=null){
                    fecha = diario.getDrsAno()+"/"+diario.getDrsMes()+"/18";
                    this.datosIniciales = 17;
                }else if(diario.getDrsValor19()!=null){
                    fecha = diario.getDrsAno()+"/"+diario.getDrsMes()+"/19";
                    this.datosIniciales = 18;
                }else if(diario.getDrsValor20()!=null){
                    fecha = diario.getDrsAno()+"/"+diario.getDrsMes()+"/20";
                    this.datosIniciales = 19;
                }else if(diario.getDrsValor21()!=null){
                    fecha = diario.getDrsAno()+"/"+diario.getDrsMes()+"/21";
                    this.datosIniciales = 20;
                }else if(diario.getDrsValor22()!=null){
                    fecha = diario.getDrsAno()+"/"+diario.getDrsMes()+"/22";
                    this.datosIniciales = 21;
                }else if(diario.getDrsValor23()!=null){
                    fecha = diario.getDrsAno()+"/"+diario.getDrsMes()+"/23";
                    this.datosIniciales = 22;
                }else if(diario.getDrsValor24()!=null){
                    fecha = diario.getDrsAno()+"/"+diario.getDrsMes()+"/24";
                    this.datosIniciales = 23;
                }else if(diario.getDrsValor25()!=null){
                    fecha = diario.getDrsAno()+"/"+diario.getDrsMes()+"/25";
                    this.datosIniciales = 24;
                }else if(diario.getDrsValor26()!=null){
                    fecha = diario.getDrsAno()+"/"+diario.getDrsMes()+"/26";
                    this.datosIniciales = 25;
                }else if(diario.getDrsValor27()!=null){
                    fecha = diario.getDrsAno()+"/"+diario.getDrsMes()+"/27";
                    this.datosIniciales = 26;
                }else if(diario.getDrsValor28()!=null){
                    fecha = diario.getDrsAno()+"/"+diario.getDrsMes()+"/28";
                    this.datosIniciales = 27;
                }else if(diario.getDrsValor29()!=null){
                    fecha = diario.getDrsAno()+"/"+diario.getDrsMes()+"/29";
                    this.datosIniciales = 28;
                }else if(diario.getDrsValor30()!=null){
                    fecha = diario.getDrsAno()+"/"+diario.getDrsMes()+"/30";
                    this.datosIniciales = 29;
                }else if(diario.getDrsValor31()!=null){
                    fecha = diario.getDrsAno()+"/"+diario.getDrsMes()+"/31";
                    this.datosIniciales = 30;
                }
            }else if(datos.get(0) instanceof ShmvMensualesHid){
                ShmvMensualesHid mes = (ShmvMensualesHid) datos.get(0);
                if(mes.getMnsValor1()!=null){
                    fecha = mes.getMnsAno()+"/01";
                }else if(mes.getMnsValor2()!=null){
                    fecha = mes.getMnsAno()+"/02";
                    this.datosIniciales = 1;
                }else if(mes.getMnsValor3()!=null){
                    fecha = mes.getMnsAno()+"/03";
                    this.datosIniciales = 2;
                }else if(mes.getMnsValor4()!=null){
                    fecha = mes.getMnsAno()+"/04";
                    this.datosIniciales = 3;
                }else if(mes.getMnsValor5()!=null){
                    fecha = mes.getMnsAno()+"/05";
                    this.datosIniciales = 4;
                }else if(mes.getMnsValor6()!=null){
                    fecha = mes.getMnsAno()+"/06";
                    this.datosIniciales = 5;
                }else if(mes.getMnsValor7()!=null){
                    fecha = mes.getMnsAno()+"/07";
                    this.datosIniciales = 6;
                }else if(mes.getMnsValor8()!=null){
                    fecha = mes.getMnsAno()+"/08";
                    this.datosIniciales = 7;
                }else if(mes.getMnsValor9()!=null){
                    fecha = mes.getMnsAno()+"/09";
                    this.datosIniciales = 8;
                }else if(mes.getMnsValor10()!=null){
                    fecha = mes.getMnsAno()+"/10";
                    this.datosIniciales = 9;
                }else if(mes.getMnsValor11()!=null){
                    fecha = mes.getMnsAno()+"/11";
                    this.datosIniciales = 10;
                }else if(mes.getMnsValor12()!=null){
                    fecha = mes.getMnsAno()+"/12";
                    this.datosIniciales = 11;
                }
            }
        }
        //llamado recursivo.
        if(fecha.equals("")){
            List aux = datos;
            aux.remove(0);
            fecha = fechaMinima(aux);
        
        }
        return fecha;
    }
    
    private String fechaMaxima(List datos) throws IdeamException{
        String fecha = "";
        if(datos!=null && !datos.isEmpty()){
            this.datosFinales = 0;
            if(datos.get(datos.size()-1) instanceof ShmvDiariosHid){
                ShmvDiariosHid diario = (ShmvDiariosHid) datos.get(datos.size()-1);
                if(diario.getDrsValor31()!=null){
                    fecha = diario.getDrsAno()+"/"+diario.getDrsMes()+"/31";
                }else if(diario.getDrsValor30()!=null){
                    fecha = diario.getDrsAno()+"/"+diario.getDrsMes()+"/30";
                    if(diario.getDrsMes()==1 || diario.getDrsMes()==3 || 
                       diario.getDrsMes()==5 || diario.getDrsMes()==8 || 
                       diario.getDrsMes()==10 || diario.getDrsMes()==12){//meses de 31 dias
                            this.datosFinales = 1;    
                    }
                }else if(diario.getDrsValor29()!=null){
                    fecha = diario.getDrsAno()+"/"+diario.getDrsMes()+"/29";
                    if(diario.getDrsMes()==1 || diario.getDrsMes()==3 || 
                       diario.getDrsMes()==5 || diario.getDrsMes()==8 || 
                       diario.getDrsMes()==10 || diario.getDrsMes()==12){//meses de 31 dias
                            this.datosFinales = 2;    
                    }else if (diario.getDrsMes()!=2){//no es febrero.
                        this.datosFinales = 1; 
                    }
                }else if(diario.getDrsValor28()!=null){
                    fecha = diario.getDrsAno()+"/"+diario.getDrsMes()+"/28";
                    if(diario.getDrsMes()==1 || diario.getDrsMes()==3 || 
                       diario.getDrsMes()==5 || diario.getDrsMes()==8 || 
                       diario.getDrsMes()==10 || diario.getDrsMes()==12){//meses de 31 dias
                            this.datosFinales = 3;    
                    }else if (diario.getDrsMes()!=2){//no es febrero.
                        this.datosFinales = 2; 
                    }
                }else if(diario.getDrsValor27()!=null){
                    fecha = diario.getDrsAno()+"/"+diario.getDrsMes()+"/27";
                    if(diario.getDrsMes()==1 || diario.getDrsMes()==3 || 
                       diario.getDrsMes()==5 || diario.getDrsMes()==8 || 
                       diario.getDrsMes()==10 || diario.getDrsMes()==12){//meses de 31 dias
                            this.datosFinales = 4;    
                    }else if (diario.getDrsMes()!=2){//no es febrero.
                        this.datosFinales = 3; 
                    }else{// si es febrero
                        this.datosFinales = 1; 
                    }
                }else if(diario.getDrsValor26()!=null){
                    fecha = diario.getDrsAno()+"/"+diario.getDrsMes()+"/26";
                    if(diario.getDrsMes()==1 || diario.getDrsMes()==3 || 
                       diario.getDrsMes()==5 || diario.getDrsMes()==8 || 
                       diario.getDrsMes()==10 || diario.getDrsMes()==12){//meses de 31 dias
                            this.datosFinales = 5;    
                    }else if (diario.getDrsMes()!=2){//no es febrero.
                        this.datosFinales = 4; 
                    }else{// si es febrero
                        this.datosFinales = 2; 
                    }
                }else if(diario.getDrsValor25()!=null){
                    fecha = diario.getDrsAno()+"/"+diario.getDrsMes()+"/25";
                    if(diario.getDrsMes()==1 || diario.getDrsMes()==3 || 
                       diario.getDrsMes()==5 || diario.getDrsMes()==8 || 
                       diario.getDrsMes()==10 || diario.getDrsMes()==12){//meses de 31 dias
                            this.datosFinales = 6;    
                    }else if (diario.getDrsMes()!=2){//no es febrero.
                        this.datosFinales = 5; 
                    }else{// si es febrero
                        this.datosFinales = 3; 
                    }
                }else if(diario.getDrsValor24()!=null){
                    fecha = diario.getDrsAno()+"/"+diario.getDrsMes()+"/24";
                    if(diario.getDrsMes()==1 || diario.getDrsMes()==3 || 
                       diario.getDrsMes()==5 || diario.getDrsMes()==8 || 
                       diario.getDrsMes()==10 || diario.getDrsMes()==12){//meses de 31 dias
                            this.datosFinales = 7;    
                    }else if (diario.getDrsMes()!=2){//no es febrero.
                        this.datosFinales = 6; 
                    }else{// si es febrero
                        this.datosFinales = 4; 
                    }
                }else if(diario.getDrsValor23()!=null){
                    fecha = diario.getDrsAno()+"/"+diario.getDrsMes()+"/23";
                    if(diario.getDrsMes()==1 || diario.getDrsMes()==3 || 
                       diario.getDrsMes()==5 || diario.getDrsMes()==8 || 
                       diario.getDrsMes()==10 || diario.getDrsMes()==12){//meses de 31 dias
                            this.datosFinales = 8;    
                    }else if (diario.getDrsMes()!=2){//no es febrero.
                        this.datosFinales = 7; 
                    }else{// si es febrero
                        this.datosFinales = 5; 
                    }
                }else if(diario.getDrsValor22()!=null){
                    fecha = diario.getDrsAno()+"/"+diario.getDrsMes()+"/22";
                    if(diario.getDrsMes()==1 || diario.getDrsMes()==3 || 
                       diario.getDrsMes()==5 || diario.getDrsMes()==8 || 
                       diario.getDrsMes()==10 || diario.getDrsMes()==12){//meses de 31 dias
                            this.datosFinales = 9;    
                    }else if (diario.getDrsMes()!=2){//no es febrero.
                        this.datosFinales = 8; 
                    }else{// si es febrero
                        this.datosFinales = 6; 
                    }
                }else if(diario.getDrsValor21()!=null){
                    fecha = diario.getDrsAno()+"/"+diario.getDrsMes()+"/21";
                    if(diario.getDrsMes()==1 || diario.getDrsMes()==3 || 
                       diario.getDrsMes()==5 || diario.getDrsMes()==8 || 
                       diario.getDrsMes()==10 || diario.getDrsMes()==12){//meses de 31 dias
                            this.datosFinales = 10;    
                    }else if (diario.getDrsMes()!=2){//no es febrero.
                        this.datosFinales = 9; 
                    }else{// si es febrero
                        this.datosFinales = 7; 
                    }
                }else if(diario.getDrsValor20()!=null){
                    fecha = diario.getDrsAno()+"/"+diario.getDrsMes()+"/20";
                    if(diario.getDrsMes()==1 || diario.getDrsMes()==3 || 
                       diario.getDrsMes()==5 || diario.getDrsMes()==8 || 
                       diario.getDrsMes()==10 || diario.getDrsMes()==12){//meses de 31 dias
                            this.datosFinales = 11;    
                    }else if (diario.getDrsMes()!=2){//no es febrero.
                        this.datosFinales = 10; 
                    }else{// si es febrero
                        this.datosFinales = 8; 
                    }
                }else if(diario.getDrsValor19()!=null){
                    fecha = diario.getDrsAno()+"/"+diario.getDrsMes()+"/19";
                    if(diario.getDrsMes()==1 || diario.getDrsMes()==3 || 
                       diario.getDrsMes()==5 || diario.getDrsMes()==8 || 
                       diario.getDrsMes()==10 || diario.getDrsMes()==12){//meses de 31 dias
                            this.datosFinales = 12;    
                    }else if (diario.getDrsMes()!=2){//no es febrero.
                        this.datosFinales = 11; 
                    }else{// si es febrero
                        this.datosFinales = 9; 
                    }
                }else if(diario.getDrsValor18()!=null){
                    fecha = diario.getDrsAno()+"/"+diario.getDrsMes()+"/18";
                    if(diario.getDrsMes()==1 || diario.getDrsMes()==3 || 
                       diario.getDrsMes()==5 || diario.getDrsMes()==8 || 
                       diario.getDrsMes()==10 || diario.getDrsMes()==12){//meses de 31 dias
                            this.datosFinales = 13;    
                    }else if (diario.getDrsMes()!=2){//no es febrero.
                        this.datosFinales = 12; 
                    }else{// si es febrero
                        this.datosFinales = 10; 
                    }
                }else if(diario.getDrsValor17()!=null){
                    fecha = diario.getDrsAno()+"/"+diario.getDrsMes()+"/17";
                    if(diario.getDrsMes()==1 || diario.getDrsMes()==3 || 
                       diario.getDrsMes()==5 || diario.getDrsMes()==8 || 
                       diario.getDrsMes()==10 || diario.getDrsMes()==12){//meses de 31 dias
                            this.datosFinales = 14;    
                    }else if (diario.getDrsMes()!=2){//no es febrero.
                        this.datosFinales = 13; 
                    }else{// si es febrero
                        this.datosFinales = 11; 
                    }
                }else if(diario.getDrsValor16()!=null){
                    fecha = diario.getDrsAno()+"/"+diario.getDrsMes()+"/16";
                    if(diario.getDrsMes()==1 || diario.getDrsMes()==3 || 
                       diario.getDrsMes()==5 || diario.getDrsMes()==8 || 
                       diario.getDrsMes()==10 || diario.getDrsMes()==12){//meses de 31 dias
                            this.datosFinales = 15;    
                    }else if (diario.getDrsMes()!=2){//no es febrero.
                        this.datosFinales = 14; 
                    }else{// si es febrero
                        this.datosFinales = 12; 
                    }
                }else if(diario.getDrsValor15()!=null){
                    fecha = diario.getDrsAno()+"/"+diario.getDrsMes()+"/15";
                    if(diario.getDrsMes()==1 || diario.getDrsMes()==3 || 
                       diario.getDrsMes()==5 || diario.getDrsMes()==8 || 
                       diario.getDrsMes()==10 || diario.getDrsMes()==12){//meses de 31 dias
                            this.datosFinales = 16;    
                    }else if (diario.getDrsMes()!=2){//no es febrero.
                        this.datosFinales = 15; 
                    }else{// si es febrero
                        this.datosFinales = 13; 
                    }
                }else if(diario.getDrsValor14()!=null){
                    fecha = diario.getDrsAno()+"/"+diario.getDrsMes()+"/14";
                    if(diario.getDrsMes()==1 || diario.getDrsMes()==3 || 
                       diario.getDrsMes()==5 || diario.getDrsMes()==8 || 
                       diario.getDrsMes()==10 || diario.getDrsMes()==12){//meses de 31 dias
                            this.datosFinales = 17;    
                    }else if (diario.getDrsMes()!=2){//no es febrero.
                        this.datosFinales = 16; 
                    }else{// si es febrero
                        this.datosFinales = 14; 
                    }
                }else if(diario.getDrsValor13()!=null){
                    fecha = diario.getDrsAno()+"/"+diario.getDrsMes()+"/13";
                    if(diario.getDrsMes()==1 || diario.getDrsMes()==3 || 
                       diario.getDrsMes()==5 || diario.getDrsMes()==8 || 
                       diario.getDrsMes()==10 || diario.getDrsMes()==12){//meses de 31 dias
                            this.datosFinales = 18;    
                    }else if (diario.getDrsMes()!=2){//no es febrero.
                        this.datosFinales = 17; 
                    }else{// si es febrero
                        this.datosFinales = 15; 
                    }
                }else if(diario.getDrsValor12()!=null){
                    fecha = diario.getDrsAno()+"/"+diario.getDrsMes()+"/12";
                    if(diario.getDrsMes()==1 || diario.getDrsMes()==3 || 
                       diario.getDrsMes()==5 || diario.getDrsMes()==8 || 
                       diario.getDrsMes()==10 || diario.getDrsMes()==12){//meses de 31 dias
                            this.datosFinales = 19;    
                    }else if (diario.getDrsMes()!=2){//no es febrero.
                        this.datosFinales = 18; 
                    }else{// si es febrero
                        this.datosFinales = 16; 
                    }
                }else if(diario.getDrsValor11()!=null){
                    fecha = diario.getDrsAno()+"/"+diario.getDrsMes()+"/11";
                    if(diario.getDrsMes()==1 || diario.getDrsMes()==3 || 
                       diario.getDrsMes()==5 || diario.getDrsMes()==8 || 
                       diario.getDrsMes()==10 || diario.getDrsMes()==12){//meses de 31 dias
                            this.datosFinales = 20;    
                    }else if (diario.getDrsMes()!=2){//no es febrero.
                        this.datosFinales = 19; 
                    }else{// si es febrero
                        this.datosFinales = 17; 
                    }
                }else if(diario.getDrsValor10()!=null){
                    fecha = diario.getDrsAno()+"/"+diario.getDrsMes()+"/10";
                    if(diario.getDrsMes()==1 || diario.getDrsMes()==3 || 
                       diario.getDrsMes()==5 || diario.getDrsMes()==8 || 
                       diario.getDrsMes()==10 || diario.getDrsMes()==12){//meses de 31 dias
                            this.datosFinales = 21;    
                    }else if (diario.getDrsMes()!=2){//no es febrero.
                        this.datosFinales = 20; 
                    }else{// si es febrero
                        this.datosFinales = 18; 
                    }
                }else if(diario.getDrsValor9()!=null){
                    fecha = diario.getDrsAno()+"/"+diario.getDrsMes()+"/09";
                    if(diario.getDrsMes()==1 || diario.getDrsMes()==3 || 
                       diario.getDrsMes()==5 || diario.getDrsMes()==8 || 
                       diario.getDrsMes()==10 || diario.getDrsMes()==12){//meses de 31 dias
                            this.datosFinales = 22;    
                    }else if (diario.getDrsMes()!=2){//no es febrero.
                        this.datosFinales = 21; 
                    }else{// si es febrero
                        this.datosFinales = 19; 
                    }
                }else if(diario.getDrsValor8()!=null){
                    fecha = diario.getDrsAno()+"/"+diario.getDrsMes()+"/08";
                    if(diario.getDrsMes()==1 || diario.getDrsMes()==3 || 
                       diario.getDrsMes()==5 || diario.getDrsMes()==8 || 
                       diario.getDrsMes()==10 || diario.getDrsMes()==12){//meses de 31 dias
                            this.datosFinales = 23;    
                    }else if (diario.getDrsMes()!=2){//no es febrero.
                        this.datosFinales = 22; 
                    }else{// si es febrero
                        this.datosFinales = 20; 
                    }
                }else if(diario.getDrsValor7()!=null){
                    fecha = diario.getDrsAno()+"/"+diario.getDrsMes()+"/07";
                    if(diario.getDrsMes()==1 || diario.getDrsMes()==3 || 
                       diario.getDrsMes()==5 || diario.getDrsMes()==8 || 
                       diario.getDrsMes()==10 || diario.getDrsMes()==12){//meses de 31 dias
                            this.datosFinales = 24;    
                    }else if (diario.getDrsMes()!=2){//no es febrero.
                        this.datosFinales = 23; 
                    }else{// si es febrero
                        this.datosFinales = 21; 
                    }
                }else if(diario.getDrsValor6()!=null){
                    fecha = diario.getDrsAno()+"/"+diario.getDrsMes()+"/06";
                    if(diario.getDrsMes()==1 || diario.getDrsMes()==3 || 
                       diario.getDrsMes()==5 || diario.getDrsMes()==8 || 
                       diario.getDrsMes()==10 || diario.getDrsMes()==12){//meses de 31 dias
                            this.datosFinales = 25;    
                    }else if (diario.getDrsMes()!=2){//no es febrero.
                        this.datosFinales = 24; 
                    }else{// si es febrero
                        this.datosFinales = 22; 
                    }
                }else if(diario.getDrsValor5()!=null){
                    fecha = diario.getDrsAno()+"/"+diario.getDrsMes()+"/05";
                    if(diario.getDrsMes()==1 || diario.getDrsMes()==3 || 
                       diario.getDrsMes()==5 || diario.getDrsMes()==8 || 
                       diario.getDrsMes()==10 || diario.getDrsMes()==12){//meses de 31 dias
                            this.datosFinales = 26;    
                    }else if (diario.getDrsMes()!=2){//no es febrero.
                        this.datosFinales = 25; 
                    }else{// si es febrero
                        this.datosFinales = 23; 
                    }
                }else if(diario.getDrsValor4()!=null){
                    fecha = diario.getDrsAno()+"/"+diario.getDrsMes()+"/04";
                    if(diario.getDrsMes()==1 || diario.getDrsMes()==3 || 
                       diario.getDrsMes()==5 || diario.getDrsMes()==8 || 
                       diario.getDrsMes()==10 || diario.getDrsMes()==12){//meses de 31 dias
                            this.datosFinales = 27;    
                    }else if (diario.getDrsMes()!=2){//no es febrero.
                        this.datosFinales = 26; 
                    }else{// si es febrero
                        this.datosFinales = 24; 
                    }
                }else if(diario.getDrsValor3()!=null){
                    fecha = diario.getDrsAno()+"/"+diario.getDrsMes()+"/03";
                    if(diario.getDrsMes()==1 || diario.getDrsMes()==3 || 
                       diario.getDrsMes()==5 || diario.getDrsMes()==8 || 
                       diario.getDrsMes()==10 || diario.getDrsMes()==12){//meses de 31 dias
                            this.datosFinales = 28;    
                    }else if (diario.getDrsMes()!=2){//no es febrero.
                        this.datosFinales = 27; 
                    }else{// si es febrero
                        this.datosFinales = 25; 
                    }
                }else if(diario.getDrsValor2()!=null){
                    fecha = diario.getDrsAno()+"/"+diario.getDrsMes()+"/02";
                    if(diario.getDrsMes()==1 || diario.getDrsMes()==3 || 
                       diario.getDrsMes()==5 || diario.getDrsMes()==8 || 
                       diario.getDrsMes()==10 || diario.getDrsMes()==12){//meses de 31 dias
                            this.datosFinales = 29;    
                    }else if (diario.getDrsMes()!=2){//no es febrero.
                        this.datosFinales = 28; 
                    }else{// si es febrero
                        this.datosFinales = 26; 
                    }
                }else if(diario.getDrsValor1()!=null){
                    fecha = diario.getDrsAno()+"/"+diario.getDrsMes()+"/01";
                    if(diario.getDrsMes()==1 || diario.getDrsMes()==3 || 
                       diario.getDrsMes()==5 || diario.getDrsMes()==8 || 
                       diario.getDrsMes()==10 || diario.getDrsMes()==12){//meses de 31 dias
                            this.datosFinales = 30;    
                    }else if (diario.getDrsMes()!=2){//no es febrero.
                        this.datosFinales = 29; 
                    }else{// si es febrero
                        this.datosFinales = 27; 
                    }
                }
            }else if(datos.get(datos.size()-1) instanceof ShmvMensualesHid){
                ShmvMensualesHid mes = (ShmvMensualesHid) datos.get(datos.size()-1);
                if(mes.getMnsValor12()!=null){
                    fecha = mes.getMnsAno()+"/12";
                }else if(mes.getMnsValor11()!=null){
                    fecha = mes.getMnsAno()+"/11";
                    this.datosFinales = 1;
                }else if(mes.getMnsValor10()!=null){
                    fecha = mes.getMnsAno()+"/10";
                    this.datosFinales = 2;
                }else if(mes.getMnsValor9()!=null){
                    fecha = mes.getMnsAno()+"/09";
                    this.datosFinales = 3;
                }else if(mes.getMnsValor8()!=null){
                    fecha = mes.getMnsAno()+"/08";
                    this.datosFinales = 4;
                }else if(mes.getMnsValor7()!=null){
                    fecha = mes.getMnsAno()+"/07";
                    this.datosFinales = 5;
                }else if(mes.getMnsValor6()!=null){
                    fecha = mes.getMnsAno()+"/06";
                    this.datosFinales = 6;
                }else if(mes.getMnsValor5()!=null){
                    fecha = mes.getMnsAno()+"/05";
                    this.datosFinales = 7;
                }else if(mes.getMnsValor4()!=null){
                    fecha = mes.getMnsAno()+"/04";
                    this.datosFinales = 8;
                }else if(mes.getMnsValor3()!=null){
                    fecha = mes.getMnsAno()+"/03";
                    this.datosFinales = 9;
                }else if(mes.getMnsValor2()!=null){
                    fecha = mes.getMnsAno()+"/02";
                    this.datosFinales = 10;
                }else if(mes.getMnsValor1()!=null){
                    fecha = mes.getMnsAno()+"/01";
                    this.datosFinales = 11;
                }
            }
        }
        
        //llamado recursivo.
        if(fecha.equals("")){
            List aux = datos;
            aux.remove(datos.size()-1);
            fecha = fechaMaxima(aux);
        }
        
        return fecha;
    }
    
    private Double cuartil(List datos, int q) throws IdeamException{
        Double qCalcular = null;
        int cant = 0;
        if(datos != null && !datos.isEmpty()){
            List<Double> dataSort = Util.sortData((List<Double>)datos, Util.ASCENDENTE);
            cant = dataSort.size();
            int residuo = ((cant * q) % 100);
            Double division = (double)((double)((double)cant * (double)q) / (double)100);
            
            if (residuo > 0){
                Double index1 = (Math.floor(division));
                Double index2 = (Math.ceil(division));
                
                double valor1 = (Double)dataSort.get(index1.intValue());
                double valor2 = (Double)dataSort.get(index2.intValue());
                qCalcular = (double)((double)(valor2 + valor1)/(double)2);
            }else if (residuo == 0){
                qCalcular = (Double)dataSort.get(division.intValue());
            }
        }
        return qCalcular;
    }
    

    public void setMedia(Double media) {
        this.media = media;
    }

    public Double getMedia() {
        return media;
    }

    public void setSumaTotalValid(double sumaTotalValid) {
        this.sumaTotalValid = sumaTotalValid;
    }

    public double getSumaTotalValid() {
        return sumaTotalValid;
    }

    public void setCantidadTotalValid(long cantidadTotalValid) {
        this.cantidadTotalValid = cantidadTotalValid;
    }

    public long getCantidadTotalValid() {
        return cantidadTotalValid;
    }

    public void setCantidadNulos(long cantidadNulos) {
        this.cantidadNulos = cantidadNulos;
    }

    public long getCantidadNulos() {
        return cantidadNulos;
    }

    public void setCantidadTotalSerie(long cantidadTotalSerie) {
        this.cantidadTotalSerie = cantidadTotalSerie;
    }

    public long getCantidadTotalSerie() {
        return cantidadTotalSerie;
    }

    public void setMaximo(double maximo) {
        this.maximo = maximo;
    }

    public double getMaximo() {
        return maximo;
    }

    public void setMinimo(double minimo) {
        this.minimo = minimo;
    }

    public double getMinimo() {
        return minimo;
    }

    public void setFechaMinima(String fechaMinima) {
        this.fechaMinima = fechaMinima;
    }

    public String getFechaMinima() {
        return fechaMinima;
    }

    public void setFechaMaxima(String fechaMaxima) {
        this.fechaMaxima = fechaMaxima;
    }

    public String getFechaMaxima() {
        return fechaMaxima;
    }

    public void setQ75(double q75) {
        this.q75 = q75;
    }

    public double getQ75() {
        return q75;
    }

    public void setQ25(double q25) {
        this.q25 = q25;
    }

    public double getQ25() {
        return q25;
    }
}
