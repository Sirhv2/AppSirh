package co.gov.ideam.sirh.calidad.model;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DatosReporteCroosTab  implements Serializable{
       
    @Column(name="nombre_punto")     
    private String nombrepunto;
    
    @Id  
    @Column(name="fecha")     
    private String fecha;
       
    @Column(name="acidez") 
    private Double acidez;  
    @Column(name="unidad2") 
    private String unidad2;
    
    @Column(name="acidez_intercambiable") 
    private Double acidez_intercambiable;   
    @Column(name="unidad3") 
    private String unidad3;
   
    @Column(name="alcalinidad") 
    private Double alcalinidad;
    @Column(name="unidad4") 
    private String unidad4;
    
    @Column(name="alcalinidad_total") 
    private Double alcalinidad_total;  
    @Column(name="unidad5") 
    private String unidad5;   
    
    @Column(name="aluminio") 
    private Double aluminio;    
    @Column(name="unidad6") 
    private String unidad6;
    
    @Column(name="arsenico") 
    private Double arsenico;
    @Column(name="unidad7") 
    private String unidad7;
    
    @Column(name="bromo") 
    private Double bromo;   
    @Column(name="unidad8") 
    private String unidad8;
    
    @Column(name="bromuro") 
    private Double bromuro;
    @Column(name="unidad9") 
    private String unidad9;    
    
    @Column(name="calcio") 
    private Double calcio;
    @Column(name="unidad10") 
    private String unidad10;  
    
    @Column(name="calcio_soluble") 
    private Double calcio_soluble;
    @Column(name="unidad11") 
    private String unidad11;
    
    @Column(name="caudal") 
    private Double caudal;
    @Column(name="unidad12") 
    private String unidad12;    
    
    @Column(name="clorato")   
    private Double clorato;
    @Column(name="unidad13") 
    private String unidad13;    
    
    @Column(name="clordano")     
    private Double clordano;
    @Column(name="unidad14") 
    private String unidad14;
    
    @Column(name="cloro") 
    private Double cloro;
    @Column(name="unidad15") 
    private String unidad15;

    @Column(name="cloruro") 
    private Double cloruro;
    @Column(name="unidad16") 
    private String unidad16;    

    @Column(name="coliformes_fecales") 
    private Double coliformes_fecales;
    @Column(name="unidad17") 
    private String unidad17;
    
    @Column(name="coliformes_totales") 
    private Double coliformes_totales;
    @Column(name="unidad18") 
    private String unidad18;
    
    @Column(name="color") 
    private Double color;
    @Column(name="unidad19") 
    private String unidad19;
    
    @Column(name="color_aparente") 
    private Double color_aparente;
    @Column(name="unidad20") 
    private String unidad20;
    
    @Column(name="color_real") 
    private Double color_real;
    @Column(name="unidad21") 
    private String unidad21;
    
    @Column(name="conductividad_electrica") 
    private Double conductividad_electrica;
    @Column(name="unidad22") 
    private String unidad22;
    
    @Column(name="demanda_bioquimica_de_oxigeno")
    private Double demanda_bioquimica_de_oxigeno;
    @Column(name="unidad23") 
    private String unidad23;
    
    @Column(name="demanda_quimica_de_oxigeno")
    private Double demanda_quimica_de_oxigeno;           
    @Column(name="unidad24") 
    private String unidad24;
        
    @Column(name="densidad") 
    private Double densidad;
    @Column(name="unidad25") 
    private String unidad25;   
    
    @Column(name="escherichia_coli") 
    private Double escherichia_coli;
    @Column(name="unidad26") 
    private String unidad26;   
    
    @Column(name="fosfato")    
    private Double fosfato;
    @Column(name="unidad27") 
    private String unidad27;   
    
    @Column(name="fosforo_disuelto_total") 
    private Double fosforo_disuelto_total;
    @Column(name="unidad28") 
    private String unidad28;   
    
    @Column(name="nitrato") 
    private Double nitrato;
    @Column(name="unidad29") 
    private String unidad29;   
    
    @Column(name="nitrito") 
    private Double nitrito;
    @Column(name="unidad30") 
    private String unidad30;   
    
    @Column(name="nitrogeno_amoniacal") 
    private Double nitrogeno_amoniacal;
    @Column(name="unidad31") 
    private String unidad31;   
        
    @Column(name="nitrogeno_Kjeldahl_Total")     
    private Double nitrogeno_Kjeldahl_Total;
    @Column(name="unidad32") 
    private String unidad32;   
    
    @Column(name="oxigeno_disuelto") 
    private Double oxigeno_disuelto;
    @Column(name="unidad33") 
    private String unidad33;   
    
    @Column(name="pH") 
    private Double pH;
    @Column(name="unidad34") 
    private String unidad34;   
    
    @Column(name="solidos_disueltos_totales") 
    private Double solidos_disueltos_totales;
    @Column(name="unidad35") 
    private String unidad35;       
    
    @Column(name="solidos_suspendidos_totales") 
    private Double solidos_suspendidos_totales;
    @Column(name="unidad36") 
    private String unidad36;   
    
    @Column(name="solidos_totales") 
    private Double solidos_totales;
    @Column(name="unidad37") 
    private String unidad37;   
    
    @Column(name="sulfatos") 
    private Double sulfatos;
    @Column(name="unidad38") 
    private String unidad38;       
    
    @Column(name="temperatura") 
    private Double temperatura;
    @Column(name="unidad39") 
    private String unidad39;   
    
    @Column(name="turbidez") 
    private Double turbidez;
    @Column(name="unidad40") 
    private String unidad40;


    public DatosReporteCroosTab() {
    }
      
      
    public void setNombrepunto(String nombrepunto) {
        this.nombrepunto = nombrepunto;
    }

    public String getNombrepunto() {
        return nombrepunto;
    }
    
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getFecha() {
        return fecha;
    }
    
    public void setAcidez(Double acidez) {
        this.acidez = acidez;
    }

    public Double getAcidez() {
        return acidez;
    }
    
    public String getUnidad2() {
        return unidad2;
    }

    public void setUnidad2(String unidad2) {
        this.unidad2 = unidad2;
    }
    
    public void setAcidez_intercambiable(Double acidez_intercambiable) {
        this.acidez_intercambiable = acidez_intercambiable;
    }

    public Double getAcidez_intercambiable() {
        return acidez_intercambiable;
    }

    public String getUnidad3() {
        return unidad3;
    }

    public void setUnidad3(String unidad3) {
        this.unidad3 = unidad3;
    }
   
    public void setAlcalinidad(Double alcalinidad) {
        this.alcalinidad = alcalinidad;
    }
    
    public Double getAlcalinidad() {
        return alcalinidad;
    } 
    
    public String getUnidad4() {
        return unidad4;
    }

    public void setUnidad4(String unidad4) {
        this.unidad4 = unidad4;
    }
 
    public void setAlcalinidad_total(Double alcalinidad_total) {
        this.alcalinidad_total = alcalinidad_total;
    }
    
    public Double getAlcalinidad_total() {
        return alcalinidad_total;
    }
    
    public String getUnidad5() {
        return unidad5;
    }

    public void setUnidad5(String unidad5) {
        this.unidad5 = unidad5;
    }

    public void setAluminio(Double aluminio) {
        this.aluminio = aluminio;
    }

    public Double getAluminio() {
        return aluminio;
    }

    public String getUnidad6() {
        return unidad6;
    }

    public void setUnidad6(String unidad6) {
        this.unidad6 = unidad6;
    }
    
    public void setArsenico(Double arsenico) {
        this.arsenico = arsenico;
    }

    public Double getArsenico() {
        return arsenico;
    }

    public String getUnidad7() {
        return unidad7;
    }

    public void setUnidad7(String unidad7) {
        this.unidad7 = unidad7;
    }

    public void setBromo(Double bromo) {
        this.bromo = bromo;
    }

    public Double getBromo() {
        return bromo;
    }
    
    public String getUnidad8() {
        return unidad8;
    }

    public void setUnidad8(String unidad8) {
        this.unidad8 = unidad8;
    }
    
    public void setBromuro(Double bromuro) {
        this.bromuro = bromuro;
    }

    public Double getBromuro() {
        return bromuro;
    }

    public String getUnidad9() {
        return unidad9;
    }

    public void setUnidad9(String unidad9) {
        this.unidad9 = unidad9;
    }   
    
    public void setCalcio(Double calcio) {
        this.calcio = calcio;
    }

    public Double getCalcio() {
        return calcio;
    }

    public String getUnidad10() {
        return unidad10;
    }

    public void setUnidad10(String unidad10) {
        this.unidad10 = unidad10;
    }
    
    public void setCalcio_soluble(Double calcio_soluble) {
        this.calcio_soluble = calcio_soluble;
    }

    public Double getCalcio_soluble() {
        return calcio_soluble;
    }
    
    public String getUnidad11() {
        return unidad11;
    }

    public void setUnidad11(String unidad11) {
        this.unidad11 = unidad11;
    }
    
    public void setCaudal(Double caudal) {
        this.caudal = caudal;
    }

    public Double getCaudal() {
        return caudal;
    }

    public String getUnidad12() {
        return unidad12;
    }

    public void setUnidad12(String unidad12) {
        this.unidad12 = unidad12;
    }
    
    public void setClorato(Double clorato) {
        this.clorato = clorato;
    }

    public Double getClorato() {
        return clorato;
    }

    public String getUnidad13() {
        return unidad13;
    }

    public void setUnidad13(String unidad13) {
        this.unidad13 = unidad13;
    }
    
    public void setClordano(Double clordano) {
        this.clordano = clordano;
    }

    public Double getClordano() {
        return clordano;
    }

    public String getUnidad14() {
        return unidad14;
    }

    public void setUnidad14(String unidad14) {
        this.unidad14 = unidad14;
    }
    public void setCloro(Double cloro) {
        this.cloro = cloro;
    }

    public Double getCloro() {
        return cloro;
    }

    public String getUnidad15() {
        return unidad15;
    }

    public void setUnidad15(String unidad15) {
        this.unidad15 = unidad15;
    }
    
    public void setCloruro(Double cloruro) {
        this.cloruro = cloruro;
    }

    public Double getCloruro() {
        return cloruro;
    }

    public String getUnidad16() {
        return unidad16;
    }

    public void setUnidad16(String unidad16) {
        this.unidad16 = unidad16;
    }
    
    public void setColiformes_fecales(Double coliformes_fecales) {
        this.coliformes_fecales = coliformes_fecales;
    }

    public Double getColiformes_fecales() {
        return coliformes_fecales;
    }
    public String getUnidad17() {
        return unidad17;
    }

    public void setUnidad17(String unidad17) {
        this.unidad17 = unidad17;
    }
    
    public void setColiformes_totales(Double coliformes_totales) {
        this.coliformes_totales = coliformes_totales;
    }

    public Double getColiformes_totales() {
        return coliformes_totales;
    }

    public String getUnidad18() {
        return unidad18;
    }

    public void setUnidad18(String unidad18) {
        this.unidad18 = unidad18;
    }
    
    public void setColor(Double color) {
        this.color = color;
    }

    public Double getColor() {
        return color;
    }

    public String getUnidad19() {
        return unidad19;
    }

    public void setUnidad19(String unidad19) {
        this.unidad19 = unidad19;
    }
    
    public void setColor_aparente(Double color_aparente) {
        this.color_aparente = color_aparente;
    }

    public Double getColor_aparente() {
        return color_aparente;
    }

    public String getUnidad20() {
        return unidad20;
    }

    public void setUnidad20(String unidad20) {
        this.unidad20 = unidad20;
    }
    
    public void setColor_real(Double color_real) {
        this.color_real = color_real;
    }

    public Double getColor_real() {
        return color_real;
    }

    public String getUnidad21() {
        return unidad21;
    }

    public void setUnidad21(String unidad21) {
        this.unidad21 = unidad21;
    }
    
    public void setConductividad_electrica(Double conductividad_electrica) {
        this.conductividad_electrica = conductividad_electrica;
    }

    public Double getConductividad_electrica() {
        return conductividad_electrica;
    }

    public String getUnidad22() {
        return unidad22;
    }

    public void setUnidad22(String unidad22) {
        this.unidad22 = unidad22;
    }
    
    public void setDemanda_bioquimica_de_oxigeno(Double demanda_bioquimica_de_oxigeno) {
        this.demanda_bioquimica_de_oxigeno = demanda_bioquimica_de_oxigeno;
    }

    public Double getDemanda_bioquimica_de_oxigeno() {
        return demanda_bioquimica_de_oxigeno;
    }

    public String getUnidad23() {
        return unidad23;
    }

    public void setUnidad23(String unidad23) {
        this.unidad23 = unidad23;
    }
    
    public void setDemanda_quimica_de_oxigeno(Double demanda_quimica_de_oxigeno) {
        this.demanda_quimica_de_oxigeno = demanda_quimica_de_oxigeno;
    }

    public Double getDemanda_quimica_de_oxigeno() {
        return demanda_quimica_de_oxigeno;
    }

    public String getUnidad24() {
        return unidad24;
    }

    public void setUnidad24(String unidad24) {
        this.unidad24 = unidad24;
    }
    
    public void setDensidad(Double densidad) {
        this.densidad = densidad;
    }

    public Double getDensidad() {
        return densidad;
    }

    public String getUnidad25() {
        return unidad25;
    }

    public void setUnidad25(String unidad25) {
        this.unidad25 = unidad25;
    }

    public void setEscherichia_coli(Double escherichia_coli) {
        this.escherichia_coli = escherichia_coli;
    }

    public Double getEscherichia_coli() {
        return escherichia_coli;
    }

    public String getUnidad26() {
        return unidad26;
    }

    public void setUnidad26(String unidad26) {
        this.unidad26 = unidad26;
    }

    public void setFosfato(Double fosfato) {
        this.fosfato = fosfato;
    }

    public Double getFosfato() {
        return fosfato;
    }

    public String getUnidad27() {
        return unidad27;
    }

    public void setUnidad27(String unidad27) {
        this.unidad27 = unidad27;
    }
    
    public void setFosforo_disuelto_total(Double fosforo_disuelto_total) {
        this.fosforo_disuelto_total = fosforo_disuelto_total;
    }

    public Double getFosforo_disuelto_total() {
        return fosforo_disuelto_total;
    }

    public String getUnidad28() {
        return unidad28;
    }

    public void setUnidad28(String unidad28) {
        this.unidad28 = unidad28;
    }
    
    public void setNitrato(Double nitrato) {
        this.nitrato = nitrato;
    }

    public Double getNitrato() {
        return nitrato;
    }

    public String getUnidad29() {
        return unidad29;
    }

    public void setUnidad29(String unidad29) {
        this.unidad29 = unidad29;
    }
    
    public void setNitrito(Double nitrito) {
        this.nitrito = nitrito;
    }

    public Double getNitrito() {
        return nitrito;
    }

    public String getUnidad30() {
        return unidad30;
    }

    public void setUnidad30(String unidad30) {
        this.unidad30 = unidad30;
    }
    
    public void setNitrogeno_amoniacal(Double nitrogeno_amoniacal) {
        this.nitrogeno_amoniacal = nitrogeno_amoniacal;
    }

    public Double getNitrogeno_amoniacal() {
        return nitrogeno_amoniacal;
    }

    public String getUnidad31() {
        return unidad31;
    }

    public void setUnidad31(String unidad31) {
        this.unidad31 = unidad31;
    }
    
    public void setNitrogeno_Kjeldahl_Total(Double nitrogeno_Kjeldahl_Total) {
        this.nitrogeno_Kjeldahl_Total = nitrogeno_Kjeldahl_Total;
    }

    public Double getNitrogeno_Kjeldahl_Total() {
        return nitrogeno_Kjeldahl_Total;
    }

    public String getUnidad32() {
        return unidad32;
    }

    public void setUnidad32(String unidad32) {
        this.unidad32 = unidad32;
    }
    
    public void setOxigeno_disuelto(Double oxigeno_disuelto) {
        this.oxigeno_disuelto = oxigeno_disuelto;
    }

    public Double getOxigeno_disuelto() {
        return oxigeno_disuelto;
    }

    public String getUnidad33() {
        return unidad33;
    }

    public void setUnidad33(String unidad33) {
        this.unidad33 = unidad33;
    }
    
    public void setPH(Double pH) {
        this.pH = pH;
    }

    public Double getPH() {
        return pH;
    }

    public String getUnidad34() {
        return unidad34;
    }

    public void setUnidad34(String unidad34) {
        this.unidad34 = unidad34;
    }
    
    public void setSolidos_disueltos_totales(Double solidos_disueltos_totales) {
        this.solidos_disueltos_totales = solidos_disueltos_totales;
    }

    public Double getSolidos_disueltos_totales() {
        return solidos_disueltos_totales;
    }

    public String getUnidad35() {
        return unidad35;
    }

    public void setUnidad35(String unidad35) {
        this.unidad35 = unidad35;
    }
    
    public void setSolidos_suspendidos_totales(Double solidos_suspendidos_totales) {
        this.solidos_suspendidos_totales = solidos_suspendidos_totales;
    }

    public Double getSolidos_suspendidos_totales() {
        return solidos_suspendidos_totales;
    }

    public String getUnidad36() {
        return unidad36;
    }

    public void setUnidad36(String unidad36) {
        this.unidad36 = unidad36;
    }
    
    public void setSolidos_totales(Double solidos_totales) {
        this.solidos_totales = solidos_totales;
    }

    public Double getSolidos_totales() {
        return solidos_totales;
    }

    public String getUnidad37() {
        return unidad37;
    }

    public void setUnidad37(String unidad37) {
        this.unidad37 = unidad37;
    }
    
    public void setSulfatos(Double sulfatos) {
        this.sulfatos = sulfatos;
    }

    public Double getSulfatos() {
        return sulfatos;
    }

    public String getUnidad38() {
        return unidad38;
    }

    public void setUnidad38(String unidad38) {
        this.unidad38 = unidad38;
    }
    
    public void setTemperatura(Double temperatura) {
        this.temperatura = temperatura;
    }

    public Double getTemperatura() {
        return temperatura;
    }

    public String getUnidad39() {
        return unidad39;
    }

    public void setUnidad39(String unidad39) {
        this.unidad39 = unidad39;
    }
    
    public void setTurbidez(Double turbidez) {
        this.turbidez = turbidez;
    }

    public Double getTurbidez() {
        return turbidez;
    }
   
    public String getUnidad40() {
        return unidad40;
    }

    public void setUnidad40(String unidad40) {
        this.unidad40 = unidad40;
    }
}
