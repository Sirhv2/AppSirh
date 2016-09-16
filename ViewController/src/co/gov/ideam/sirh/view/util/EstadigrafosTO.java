package co.gov.ideam.sirh.view.util;

import java.text.DecimalFormat;

import java.util.Calendar;

public class EstadigrafosTO {
    
    //conjunto total de datos en la serie.
    private Long serie;
    //cantidad de datos nulos en la serie.
    private Long nulos;
    //conjunto de datos con los cuales se realiza el analisis.
    private Long poblacion;
    //suma total de los datos con los cuales se realiza el análisis.
    private Double sumaDatos;
    //media aritmetica del conjunto de datos.
    private Double media;
    private Double moda;
    private Double varianza;
    private Double desviacionEstandar;
    //valor maximo en el conjunto de datos
    private Double maximo;
    //valor minimo en el conjunto de datos
    private Double minimo;
    //fecha minima del rango de datos
    private String fechaMinima;
    //fecha maxima del rango de datos
    private String fechaMaxima;
    //75 por ciento de los datos
    private double q75;
    //25 por ciento de los datos
    private double q25;
    //diferencia entre q75 y q25
    private double iqr;
    
    // datos calculo irh
    private double areaTotal = 0;
    private double area50 = 0;
    private double areaPunto50 = 0;
    private double areaTotal50 = 0;
    private double irh = 0;
    private String categoriaIrh = ""; 
    
    
    
    public EstadigrafosTO() {
    }


    public void setPoblacion(Long poblacion) {
        this.poblacion = poblacion;
    }

    public Long getPoblacion() {
        return poblacion;
    }

    public void setMedia(Double media) {
        this.media = media;
    }

    public Double getMedia() {
        return media;
    }

    public void setModa(Double moda) {
        this.moda = moda;
    }

    public Double getModa() {
        return moda;
    }

    public void setVarianza(Double varianza) {
        this.varianza = varianza;
    }

    public Double getVarianza() {
        return varianza;
    }

    public void setDesviacionEstandar(Double desviacionEstandar) {
        this.desviacionEstandar = desviacionEstandar;
    }

    public Double getDesviacionEstandar() {
        return desviacionEstandar;
    }

    public void setSumaDatos(Double sumaDatos) {
        this.sumaDatos = sumaDatos;
    }

    public Double getSumaDatos() {
        return sumaDatos;
    }

    public void setSerie(Long serie) {
        this.serie = serie;
    }

    public Long getSerie() {
        return serie;
    }

    public void setNulos(Long nulos) {
        this.nulos = nulos;
    }

    public Long getNulos() {
        return nulos;
    }

    public void setMaximo(Double maximo) {
        this.maximo = maximo;
    }

    public Double getMaximo() {
        return maximo;
    }

    public void setMinimo(Double minimo) {
        this.minimo = minimo;
    }

    public Double getMinimo() {
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

    public void setIqr(double iqr) {
        this.iqr = iqr;
    }

    public double getIqr() {
        return iqr;
    }

    public void setAreaTotal(double areaTotal) {
        this.areaTotal = areaTotal;
    }

    public String getAreaTotal() {
        DecimalFormat myFormatter = new DecimalFormat("###,###,###.##");
        return myFormatter.format(areaTotal);
    }

    public void setArea50(double area50) {
        this.area50 = area50;
    }

    public String getArea50() {
        DecimalFormat myFormatter = new DecimalFormat("###,###,###.##");
        return myFormatter.format(area50);
    }

    public void setAreaPunto50(double areaPunto50) {
        this.areaPunto50 = areaPunto50;
    }

    public String getAreaPunto50() {
        DecimalFormat myFormatter = new DecimalFormat("###,###,###.##");
        return myFormatter.format(areaPunto50);
    }

    public void setAreaTotal50(double areaTotal50) {
        this.areaTotal50 = areaTotal50;
    }

    public String getAreaTotal50() {
        DecimalFormat myFormatter = new DecimalFormat("###,###,###.##");
        return myFormatter.format(areaTotal50);
   }

    public void setIrh(double irh) {
        this.irh = irh;
    }

    public String getIrh() {
        DecimalFormat myFormatter = new DecimalFormat("#.##");
        return myFormatter.format(irh);
    }

    public void setCategoriaIrh(String categoriaIrh) {
        this.categoriaIrh = categoriaIrh;
    }

    public String getCategoriaIrh() {
        return categoriaIrh;
    }
}
