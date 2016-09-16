package co.gov.ideam.sirh.red.ideam.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DatosReporteIcaCroosTabIdeam implements Serializable{
    
        @Id  
        @Column(name="codigo_muestra")
         private Integer codigo_muestra;
        @Column(name="fecha")
         private String fecha;
        @Column(name="hora")
         private String hora;
        @Column(name="punto")
         private String punto;
        @Column(name="fuente")
         private String fuente;
        @Column(name="subcategoria")
         private String subcategoria;
        @Column(name="departamento")
         private String departamento;
        @Column(name="municipio")
         private String municipio;
        @Column(name="longitud")
         private String longitud;
        @Column(name="direccion_longitud")
         private String direccion_longitud;
        @Column(name="latitud")
         private String latitud;
        @Column(name="direccion_latitud")
         private String direccion_latitud;
        @Column(name="altitud")
         private Double altitud;
        @Column(name="temperatura_kelvin")
         private Double temperatura_kelvin;
        @Column(name="presion_atmosferica_mmhg")
         private Double presion_atmosferica_mmhg;
        @Column(name="presion_atmosferica")
         private Double presion_atmosferica;
        @Column(name="pw")
         private Double pw;
        @Column(name="teta")
         private Double teta;
        @Column(name="porcentaje_saturacion")
         private Double porcentaje_saturacion;
        @Column(name="ipsod")
         private Double ipsod;    
        @Column(name="isst")
         private Double isst;    
        @Column(name="idqo")
         private Double idqo;   
        @Column(name="icond")
         private Double icond;    
        @Column(name="iph")
         private Double iph;
        @Column(name="ica5")
         private Double ica5;
        @Column(name="descriptor5")
         private String descriptor5;   
        @Column(name="np")
         private Double np;
        @Column(name="inp")
         private String inp;
        @Column(name="ica6")
         private Double ica6;
        @Column(name="descriptor6")
         private String descriptor6;    
        @Column(name="icoliformes")
         private Double icoliformes;
        @Column(name="ica7")
         private Double ica7;
        @Column(name="descriptor7")
         private String descriptor7;
 //parametros   
        @Column(name="alcalinidad_total")
         private String alcalinidad_total;
        @Column(name="valor1")
         private Double valor1;
        @Column(name="aluminio_biodisponible")
         private String aluminio_biodisponible;
        @Column(name="valor2")
         private Double valor2;
        @Column(name="aluminio_total")
         private String aluminio_total;
        @Column(name="valor3")
         private Double valor3;
        @Column(name="cadmio_biodisponible")
         private String cadmio_biodisponible;
        @Column(name="valor4")
         private Double valor4;
        @Column(name="cadmio_total")
         private String cadmio_total;
        @Column(name="valor5")
         private Double valor5;
        @Column(name="calcio")
         private String calcio;
        @Column(name="valor6")
         private Double valor6;
        @Column(name="cianuro_total")
         private String cianuro_total;
        @Column(name="valor7")
         private Double valor7;
        @Column(name="cloruro")
         private String cloruro;
        @Column(name="valor8")
         private Double valor8;
        @Column(name="cobre_biodisponible")
         private String cobre_biodisponible;
        @Column(name="valor9")
         private Double valor9;
        @Column(name="cobre_total")
         private String cobre_total;
        @Column(name="valor10")
         private Double valor10;
        @Column(name="coliformes_totales")
         private String coliformes_totales;
        @Column(name="valor11")
         private Double valor11;
        @Column(name="conductividad_electrica")
         private String conductividad_electrica;
        @Column(name="valor12")
         private Double valor12;
        @Column(name="cromo_biodisponible")
         private String cromo_biodisponible;
        @Column(name="valor13")
         private Double valor13;
        @Column(name="cromo_total")
         private String cromo_total;
        @Column(name="valor14")
         private Double valor14;
        @Column(name="dbo5")
         private String dbo5;
        @Column(name="valor15")
         private Double valor15;
        @Column(name="demanda_quimica_oxigeno")
         private String demanda_quimica_oxigeno;
        @Column(name="valor16")
         private Double valor16;
        @Column(name="dureza_total")
         private String dureza_total;
        @Column(name="valor17")
         private Double valor17;
        @Column(name="escherichia_coli")
         private String escherichia_coli;
        @Column(name="valor18")
         private Double valor18;
        @Column(name="fenoles")
         private String fenoles;
        @Column(name="valor19")
         private Double valor19;
        @Column(name="fosforo_reactivo_disuelto")
         private String fosforo_reactivo_disuelto;
        @Column(name="valor20")
         private Double valor20;
        @Column(name="fosforo_total")
         private String fosforo_total;
        @Column(name="valor21")
         private Double valor21;
        @Column(name="glifosato")
         private String glifosato;
        @Column(name="valor22")
         private Double valor22;
        @Column(name="grasas_aceites")
         private String grasas_aceites;
        @Column(name="valor23")
         private Double valor23;
        @Column(name="hierro_biodisponible")
         private String hierro_biodisponible;
        @Column(name="valor24")
         private Double valor24;
        @Column(name="hierro_total")
         private String hierro_total;
        @Column(name="valor25")
         private Double valor25;
        @Column(name="magnesio_total")
         private String magnesio_total;
        @Column(name="valor26")
         private Double valor26;
        @Column(name="manganeso_biodisponible")
         private String manganeso_biodisponible;
        @Column(name="valor27")
         private Double valor27;
        @Column(name="manganeso_total")
         private String manganeso_total;
        @Column(name="valor28")
         private Double valor28;
        @Column(name="mercurio_biodisponible")
         private String mercurio_biodisponible;
        @Column(name="valor29")
         private Double valor29;
        @Column(name="mercurio_total")
         private String mercurio_total;
        @Column(name="valor30")
         private Double valor30;
        @Column(name="mercurio_total_sedimentos")
         private String mercurio_total_sedimentos;
        @Column(name="valor31")
         private Double valor31;
        @Column(name="niquel_biodisponible")
         private String niquel_biodisponible;
        @Column(name="valor32")
         private Double valor32;
        @Column(name="niquel_total")
         private String niquel_total;
        @Column(name="valor33")
         private Double valor33;
        @Column(name="nitrato")
         private String nitrato;
        @Column(name="valor34")
         private Double valor34;
        @Column(name="nitrito")
         private String nitrito;
        @Column(name="valor35")
         private Double valor35;
        @Column(name="nitrogeno_amoniacal")
         private String nitrogeno_amoniacal;
        @Column(name="valor36")
         private Double valor36;
        @Column(name="nitrogeno_kjeldahl_total")
         private String nitrogeno_kjeldahl_total;
        @Column(name="valor37")
         private Double valor37;
        @Column(name="oxigeno_disuelto")
         private String oxigeno_disuelto;
        @Column(name="valor38")
         private Double valor38;
        @Column(name="ph")
         private String ph;
        @Column(name="valor39")
         private Double valor39;
        @Column(name="plomo_biodisponible")
         private String plomo_biodisponible;
        @Column(name="valor40")
         private Double valor40;
        @Column(name="plomo_total")
         private String plomo_total;
        @Column(name="valor41")
         private Double valor41;
        @Column(name="solidos_disueltos_totales")
         private String solidos_disueltos_totales;
        @Column(name="valor42")
         private Double valor42;
        @Column(name="solidos_sedimentables")
         private String solidos_sedimentables;
        @Column(name="valor43")
         private Double valor43;
        @Column(name="solidos_suspendidos_totales")
         private String solidos_suspendidos_totales;
        @Column(name="valor44")
         private Double valor44;
        @Column(name="solidos_totales")
         private String solidos_totales;
        @Column(name="valor45")
         private Double valor45;
        @Column(name="sulfato")
         private String sulfato;
        @Column(name="valor46")
         private Double valor46;
        @Column(name="sulfuro")
         private String sulfuro;
        @Column(name="valor47")
         private Double valor47;
        @Column(name="temperatura")
         private String temperatura;
        @Column(name="valor48")
         private Double valor48;
        @Column(name="turbidez")
         private String turbidez;
        @Column(name="valor49")
         private Double valor49;
        @Column(name="zinc_biodisponible")
         private String zinc_biodisponible;
        @Column(name="valor50")
         private Double valor50;
        @Column(name="zinc_total")
         private String zinc_total;
        @Column(name="valor51")
         private Double valor51;

    public Integer getCodigo_muestra() {
        return codigo_muestra;
    }

    public void setCodigo_muestra(Integer codigo_muestra) {
        this.codigo_muestra = codigo_muestra;
    }

    public String getPunto() {
        return punto;
    }

    public void setPunto(String punto) {
        this.punto = punto;
    }

    public String getFuente() {
        return fuente;
    }

    public void setFuente(String fuente) {
        this.fuente = fuente;
    }

    public String getSubcategoria() {
        return subcategoria;
    }

    public void setSubcategoria(String subcategoria) {
        this.subcategoria = subcategoria;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public String getDireccion_longitud() {
        return direccion_longitud;
    }

    public void setDireccion_longitud(String direccion_longitud) {
        this.direccion_longitud = direccion_longitud;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getDireccion_latitud() {
        return direccion_latitud;
    }

    public void setDireccion_latitud(String direccion_latitud) {
        this.direccion_latitud = direccion_latitud;
    }

    public Double getAltitud() {
        return altitud;
    }

    public void setAltitud(Double altitud) {
        this.altitud = altitud;
    }

    public Double getTemperatura_kelvin() {
        return temperatura_kelvin;
    }

    public void setTemperatura_kelvin(Double temperatura_kelvin) {
        this.temperatura_kelvin = temperatura_kelvin;
    }

    public Double getPresion_atmosferica_mmhg() {
        return presion_atmosferica_mmhg;
    }

    public void setPresion_atmosferica_mmhg(Double presion_atmosferica_mmhg) {
        this.presion_atmosferica_mmhg = presion_atmosferica_mmhg;
    }

    public Double getPresion_atmosferica() {
        return presion_atmosferica;
    }

    public void setPresion_atmosferica(Double presion_atmosferica) {
        this.presion_atmosferica = presion_atmosferica;
    }

    public Double getPw() {
        return pw;
    }

    public void setPw(Double pw) {
        this.pw = pw;
    }

    public Double getTeta() {
        return teta;
    }

    public void setTeta(Double teta) {
        this.teta = teta;
    }

    public Double getPorcentaje_saturacion() {
        return porcentaje_saturacion;
    }

    public void setPorcentaje_saturacion(Double porcentaje_saturacion) {
        this.porcentaje_saturacion = porcentaje_saturacion;
    }

    public Double getIpsod() {
        return ipsod;
    }

    public void setIpsod(Double ipsod) {
        this.ipsod = ipsod;
    }

    public Double getIsst() {
        return isst;
    }

    public void setIsst(Double isst) {
        this.isst = isst;
    }

    public Double getIdqo() {
        return idqo;
    }

    public void setIdqo(Double idqo) {
        this.idqo = idqo;
    }

    public Double getIcond() {
        return icond;
    }

    public void setIcond(Double icond) {
        this.icond = icond;
    }

    public Double getIph() {
        return iph;
    }

    public void setIph(Double iph) {
        this.iph = iph;
    }

    public Double getIca5() {
        return ica5;
    }

    public void setIca5(Double ica5) {
        this.ica5 = ica5;
    }

    public String getDescriptor5() {
        return descriptor5;
    }

    public void setDescriptor5(String descriptor5) {
        this.descriptor5 = descriptor5;
    }

    public Double getNp() {
        return np;
    }

    public void setNp(Double np) {
        this.np = np;
    }

    public String getInp() {
        return inp;
    }

    public void setInp(String inp) {
        this.inp = inp;
    }

    public Double getIca6() {
        return ica6;
    }

    public void setIca6(Double ica6) {
        this.ica6 = ica6;
    }

    public String getDescriptor6() {
        return descriptor6;
    }

    public void setDescriptor6(String descriptor6) {
        this.descriptor6 = descriptor6;
    }

    public Double getIcoliformes() {
        return icoliformes;
    }

    public void setIcoliformes(Double icoliformes) {
        this.icoliformes = icoliformes;
    }

    public Double getIca7() {
        return ica7;
    }

    public void setIca7(Double ica7) {
        this.ica7 = ica7;
    }

    public String getDescriptor7() {
        return descriptor7;
    }

    public void setDescriptor7(String descriptor7) {
        this.descriptor7 = descriptor7;
    }

    public String getAlcalinidad_total() {
        return alcalinidad_total;
    }

    public void setAlcalinidad_total(String alcalinidad_total) {
        this.alcalinidad_total = alcalinidad_total;
    }

    public Double getValor1() {
        return valor1;
    }

    public void setValor1(Double valor1) {
        this.valor1 = valor1;
    }

    public String getAluminio_biodisponible() {
        return aluminio_biodisponible;
    }

    public void setAluminio_biodisponible(String aluminio_biodisponible) {
        this.aluminio_biodisponible = aluminio_biodisponible;
    }

    public Double getValor2() {
        return valor2;
    }

    public void setValor2(Double valor2) {
        this.valor2 = valor2;
    }

    public String getAluminio_total() {
        return aluminio_total;
    }

    public void setAluminio_total(String aluminio_total) {
        this.aluminio_total = aluminio_total;
    }

    public Double getValor3() {
        return valor3;
    }

    public void setValor3(Double valor3) {
        this.valor3 = valor3;
    }

    public String getCadmio_biodisponible() {
        return cadmio_biodisponible;
    }

    public void setCadmio_biodisponible(String cadmio_biodisponible) {
        this.cadmio_biodisponible = cadmio_biodisponible;
    }

    public Double getValor4() {
        return valor4;
    }

    public void setValor4(Double valor4) {
        this.valor4 = valor4;
    }

    public String getCadmio_total() {
        return cadmio_total;
    }

    public void setCadmio_total(String cadmio_total) {
        this.cadmio_total = cadmio_total;
    }

    public Double getValor5() {
        return valor5;
    }

    public void setValor5(Double valor5) {
        this.valor5 = valor5;
    }

    public String getCalcio() {
        return calcio;
    }

    public void setCalcio(String calcio) {
        this.calcio = calcio;
    }

    public Double getValor6() {
        return valor6;
    }

    public void setValor6(Double valor6) {
        this.valor6 = valor6;
    }

    public String getCianuro_total() {
        return cianuro_total;
    }

    public void setCianuro_total(String cianuro_total) {
        this.cianuro_total = cianuro_total;
    }

    public Double getValor7() {
        return valor7;
    }

    public void setValor7(Double valor7) {
        this.valor7 = valor7;
    }

    public String getCloruro() {
        return cloruro;
    }

    public void setCloruro(String cloruro) {
        this.cloruro = cloruro;
    }

    public Double getValor8() {
        return valor8;
    }

    public void setValor8(Double valor8) {
        this.valor8 = valor8;
    }

    public String getCobre_biodisponible() {
        return cobre_biodisponible;
    }

    public void setCobre_biodisponible(String cobre_biodisponible) {
        this.cobre_biodisponible = cobre_biodisponible;
    }

    public Double getValor9() {
        return valor9;
    }

    public void setValor9(Double valor9) {
        this.valor9 = valor9;
    }

    public String getCobre_total() {
        return cobre_total;
    }

    public void setCobre_total(String cobre_total) {
        this.cobre_total = cobre_total;
    }

    public Double getValor10() {
        return valor10;
    }

    public void setValor10(Double valor10) {
        this.valor10 = valor10;
    }

    public String getColiformes_totales() {
        return coliformes_totales;
    }

    public void setColiformes_totales(String coliformes_totales) {
        this.coliformes_totales = coliformes_totales;
    }

    public Double getValor11() {
        return valor11;
    }

    public void setValor11(Double valor11) {
        this.valor11 = valor11;
    }

    public String getConductividad_electrica() {
        return conductividad_electrica;
    }

    public void setConductividad_electrica(String conductividad_electrica) {
        this.conductividad_electrica = conductividad_electrica;
    }

    public Double getValor12() {
        return valor12;
    }

    public void setValor12(Double valor12) {
        this.valor12 = valor12;
    }

    public String getCromo_biodisponible() {
        return cromo_biodisponible;
    }

    public void setCromo_biodisponible(String cromo_biodisponible) {
        this.cromo_biodisponible = cromo_biodisponible;
    }

    public Double getValor13() {
        return valor13;
    }

    public void setValor13(Double valor13) {
        this.valor13 = valor13;
    }

    public String getCromo_total() {
        return cromo_total;
    }

    public void setCromo_total(String cromo_total) {
        this.cromo_total = cromo_total;
    }

    public Double getValor14() {
        return valor14;
    }

    public void setValor14(Double valor14) {
        this.valor14 = valor14;
    }

    public String getDbo5() {
        return dbo5;
    }

    public void setDbo5(String dbo5) {
        this.dbo5 = dbo5;
    }

    public Double getValor15() {
        return valor15;
    }

    public void setValor15(Double valor15) {
        this.valor15 = valor15;
    }

    public String getDemanda_quimica_oxigeno() {
        return demanda_quimica_oxigeno;
    }

    public void setDemanda_quimica_oxigeno(String demanda_quimica_oxigeno) {
        this.demanda_quimica_oxigeno = demanda_quimica_oxigeno;
    }

    public Double getValor16() {
        return valor16;
    }

    public void setValor16(Double valor16) {
        this.valor16 = valor16;
    }

    public String getDureza_total() {
        return dureza_total;
    }

    public void setDureza_total(String dureza_total) {
        this.dureza_total = dureza_total;
    }

    public Double getValor17() {
        return valor17;
    }

    public void setValor17(Double valor17) {
        this.valor17 = valor17;
    }

    public String getEscherichia_coli() {
        return escherichia_coli;
    }

    public void setEscherichia_coli(String escherichia_coli) {
        this.escherichia_coli = escherichia_coli;
    }

    public Double getValor18() {
        return valor18;
    }

    public void setValor18(Double valor18) {
        this.valor18 = valor18;
    }

    public String getFenoles() {
        return fenoles;
    }

    public void setFenoles(String fenoles) {
        this.fenoles = fenoles;
    }

    public Double getValor19() {
        return valor19;
    }

    public void setValor19(Double valor19) {
        this.valor19 = valor19;
    }

    public String getFosforo_reactivo_disuelto() {
        return fosforo_reactivo_disuelto;
    }

    public void setFosforo_reactivo_disuelto(String fosforo_reactivo_disuelto) {
        this.fosforo_reactivo_disuelto = fosforo_reactivo_disuelto;
    }

    public Double getValor20() {
        return valor20;
    }

    public void setValor20(Double valor20) {
        this.valor20 = valor20;
    }

    public String getFosforo_total() {
        return fosforo_total;
    }

    public void setFosforo_total(String fosforo_total) {
        this.fosforo_total = fosforo_total;
    }

    public Double getValor21() {
        return valor21;
    }

    public void setValor21(Double valor21) {
        this.valor21 = valor21;
    }

    public String getGlifosato() {
        return glifosato;
    }

    public void setGlifosato(String glifosato) {
        this.glifosato = glifosato;
    }

    public Double getValor22() {
        return valor22;
    }

    public void setValor22(Double valor22) {
        this.valor22 = valor22;
    }

    public String getGrasas_aceites() {
        return grasas_aceites;
    }

    public void setGrasas_aceites(String grasas_aceites) {
        this.grasas_aceites = grasas_aceites;
    }

    public Double getValor23() {
        return valor23;
    }

    public void setValor23(Double valor23) {
        this.valor23 = valor23;
    }

    public String getHierro_biodisponible() {
        return hierro_biodisponible;
    }

    public void setHierro_biodisponible(String hierro_biodisponible) {
        this.hierro_biodisponible = hierro_biodisponible;
    }

    public Double getValor24() {
        return valor24;
    }

    public void setValor24(Double valor24) {
        this.valor24 = valor24;
    }

    public String getHierro_total() {
        return hierro_total;
    }

    public void setHierro_total(String hierro_total) {
        this.hierro_total = hierro_total;
    }

    public Double getValor25() {
        return valor25;
    }

    public void setValor25(Double valor25) {
        this.valor25 = valor25;
    }

    public String getMagnesio_total() {
        return magnesio_total;
    }

    public void setMagnesio_total(String magnesio_total) {
        this.magnesio_total = magnesio_total;
    }

    public Double getValor26() {
        return valor26;
    }

    public void setValor26(Double valor26) {
        this.valor26 = valor26;
    }

    public String getManganeso_biodisponible() {
        return manganeso_biodisponible;
    }

    public void setManganeso_biodisponible(String manganeso_biodisponible) {
        this.manganeso_biodisponible = manganeso_biodisponible;
    }

    public Double getValor27() {
        return valor27;
    }

    public void setValor27(Double valor27) {
        this.valor27 = valor27;
    }

    public String getManganeso_total() {
        return manganeso_total;
    }

    public void setManganeso_total(String manganeso_total) {
        this.manganeso_total = manganeso_total;
    }

    public Double getValor28() {
        return valor28;
    }

    public void setValor28(Double valor28) {
        this.valor28 = valor28;
    }

    public String getMercurio_biodisponible() {
        return mercurio_biodisponible;
    }

    public void setMercurio_biodisponible(String mercurio_biodisponible) {
        this.mercurio_biodisponible = mercurio_biodisponible;
    }

    public Double getValor29() {
        return valor29;
    }

    public void setValor29(Double valor29) {
        this.valor29 = valor29;
    }

    public String getMercurio_total() {
        return mercurio_total;
    }

    public void setMercurio_total(String mercurio_total) {
        this.mercurio_total = mercurio_total;
    }

    public Double getValor30() {
        return valor30;
    }

    public void setValor30(Double valor30) {
        this.valor30 = valor30;
    }

    public String getMercurio_total_sedimentos() {
        return mercurio_total_sedimentos;
    }

    public void setMercurio_total_sedimentos(String mercurio_total_sedimentos) {
        this.mercurio_total_sedimentos = mercurio_total_sedimentos;
    }

    public Double getValor31() {
        return valor31;
    }

    public void setValor31(Double valor31) {
        this.valor31 = valor31;
    }

    public String getNiquel_biodisponible() {
        return niquel_biodisponible;
    }

    public void setNiquel_biodisponible(String niquel_biodisponible) {
        this.niquel_biodisponible = niquel_biodisponible;
    }

    public Double getValor32() {
        return valor32;
    }

    public void setValor32(Double valor32) {
        this.valor32 = valor32;
    }

    public String getNiquel_total() {
        return niquel_total;
    }

    public void setNiquel_total(String niquel_total) {
        this.niquel_total = niquel_total;
    }

    public Double getValor33() {
        return valor33;
    }

    public void setValor33(Double valor33) {
        this.valor33 = valor33;
    }

    public String getNitrato() {
        return nitrato;
    }

    public void setNitrato(String nitrato) {
        this.nitrato = nitrato;
    }

    public Double getValor34() {
        return valor34;
    }

    public void setValor34(Double valor34) {
        this.valor34 = valor34;
    }

    public String getNitrito() {
        return nitrito;
    }

    public void setNitrito(String nitrito) {
        this.nitrito = nitrito;
    }

    public Double getValor35() {
        return valor35;
    }

    public void setValor35(Double valor35) {
        this.valor35 = valor35;
    }

    public String getNitrogeno_amoniacal() {
        return nitrogeno_amoniacal;
    }

    public void setNitrogeno_amoniacal(String nitrogeno_amoniacal) {
        this.nitrogeno_amoniacal = nitrogeno_amoniacal;
    }

    public Double getValor36() {
        return valor36;
    }

    public void setValor36(Double valor36) {
        this.valor36 = valor36;
    }

    public String getNitrogeno_kjeldahl_total() {
        return nitrogeno_kjeldahl_total;
    }

    public void setNitrogeno_kjeldahl_total(String nitrogeno_kjeldahl_total) {
        this.nitrogeno_kjeldahl_total = nitrogeno_kjeldahl_total;
    }

    public Double getValor37() {
        return valor37;
    }

    public void setValor37(Double valor37) {
        this.valor37 = valor37;
    }

    public String getOxigeno_disuelto() {
        return oxigeno_disuelto;
    }

    public void setOxigeno_disuelto(String oxigeno_disuelto) {
        this.oxigeno_disuelto = oxigeno_disuelto;
    }

    public Double getValor38() {
        return valor38;
    }

    public void setValor38(Double valor38) {
        this.valor38 = valor38;
    }

    public String getPh() {
        return ph;
    }

    public void setPh(String ph) {
        this.ph = ph;
    }

    public Double getValor39() {
        return valor39;
    }

    public void setValor39(Double valor39) {
        this.valor39 = valor39;
    }

    public String getPlomo_biodisponible() {
        return plomo_biodisponible;
    }

    public void setPlomo_biodisponible(String plomo_biodisponible) {
        this.plomo_biodisponible = plomo_biodisponible;
    }

    public Double getValor40() {
        return valor40;
    }

    public void setValor40(Double valor40) {
        this.valor40 = valor40;
    }

    public String getPlomo_total() {
        return plomo_total;
    }

    public void setPlomo_total(String plomo_total) {
        this.plomo_total = plomo_total;
    }

    public Double getValor41() {
        return valor41;
    }

    public void setValor41(Double valor41) {
        this.valor41 = valor41;
    }

    public String getSolidos_disueltos_totales() {
        return solidos_disueltos_totales;
    }

    public void setSolidos_disueltos_totales(String solidos_disueltos_totales) {
        this.solidos_disueltos_totales = solidos_disueltos_totales;
    }

    public Double getValor42() {
        return valor42;
    }

    public void setValor42(Double valor42) {
        this.valor42 = valor42;
    }

    public String getSolidos_sedimentables() {
        return solidos_sedimentables;
    }

    public void setSolidos_sedimentables(String solidos_sedimentables) {
        this.solidos_sedimentables = solidos_sedimentables;
    }

    public Double getValor43() {
        return valor43;
    }

    public void setValor43(Double valor43) {
        this.valor43 = valor43;
    }

    public String getSolidos_suspendidos_totales() {
        return solidos_suspendidos_totales;
    }

    public void setSolidos_suspendidos_totales(String solidos_suspendidos_totales) {
        this.solidos_suspendidos_totales = solidos_suspendidos_totales;
    }

    public Double getValor44() {
        return valor44;
    }

    public void setValor44(Double valor44) {
        this.valor44 = valor44;
    }

    public String getSolidos_totales() {
        return solidos_totales;
    }

    public void setSolidos_totales(String solidos_totales) {
        this.solidos_totales = solidos_totales;
    }

    public Double getValor45() {
        return valor45;
    }

    public void setValor45(Double valor45) {
        this.valor45 = valor45;
    }

    public String getSulfato() {
        return sulfato;
    }

    public void setSulfato(String sulfato) {
        this.sulfato = sulfato;
    }

    public Double getValor46() {
        return valor46;
    }

    public void setValor46(Double valor46) {
        this.valor46 = valor46;
    }

    public String getSulfuro() {
        return sulfuro;
    }

    public void setSulfuro(String sulfuro) {
        this.sulfuro = sulfuro;
    }

    public Double getValor47() {
        return valor47;
    }

    public void setValor47(Double valor47) {
        this.valor47 = valor47;
    }

    public String getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(String temperatura) {
        this.temperatura = temperatura;
    }

    public Double getValor48() {
        return valor48;
    }

    public void setValor48(Double valor48) {
        this.valor48 = valor48;
    }

    public String getTurbidez() {
        return turbidez;
    }

    public void setTurbidez(String turbidez) {
        this.turbidez = turbidez;
    }

    public Double getValor49() {
        return valor49;
    }

    public void setValor49(Double valor49) {
        this.valor49 = valor49;
    }

    public String getZinc_biodisponible() {
        return zinc_biodisponible;
    }

    public void setZinc_biodisponible(String zinc_biodisponible) {
        this.zinc_biodisponible = zinc_biodisponible;
    }

    public Double getValor50() {
        return valor50;
    }

    public void setValor50(Double valor50) {
        this.valor50 = valor50;
    }

    public String getZinc_total() {
        return zinc_total;
    }

    public void setZinc_total(String zinc_total) {
        this.zinc_total = zinc_total;
    }

    public Double getValor51() {
        return valor51;
    }

    public void setValor51(Double valor51) {
        this.valor51 = valor51;
    }
    
}
