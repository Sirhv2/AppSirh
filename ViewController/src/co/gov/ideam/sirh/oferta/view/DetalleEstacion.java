package co.gov.ideam.sirh.oferta.view;

import co.gov.ideam.sirh.oferta.model.PartRefOfertaEstacSubzona;
import co.gov.ideam.sirh.oferta.model.ShmvDiariosHid;
import co.gov.ideam.sirh.oferta.model.ShmvMensualesHid;
import co.gov.ideam.sirh.oferta.model.SiovEstaciones;
import co.gov.ideam.sirh.oferta.view.grafica.Anual;
import co.gov.ideam.sirh.oferta.view.grafica.CurvaDuracion;
import co.gov.ideam.sirh.oferta.view.grafica.FrecuenciaDatos;
import co.gov.ideam.sirh.oferta.view.grafica.Grafica;
import co.gov.ideam.sirh.oferta.view.grafica.Histograma;
import co.gov.ideam.sirh.oferta.view.grafica.Historico;
import co.gov.ideam.sirh.oferta.view.grafica.Multianual;
import co.gov.ideam.sirh.oferta.view.grafica.TendenciaCentral;
import co.gov.ideam.sirh.oferta.view.grafica.Util;
import co.gov.ideam.sirh.oferta.view.grafica.Validacion;
import co.gov.ideam.sirh.util.ConstantesOferta;

import co.gov.ideam.sirh.util.IdeamException;
import co.gov.ideam.sirh.view.FacesUtils;
import co.gov.ideam.sirh.view.StandarBean;

import co.gov.ideam.sirh.view.util.EstadigrafosTO;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.faces.application.FacesMessage;

/**
 * Esta clase se crea para separar el manejo de los objetos de la vista ADF
 * de la lógica propia relacionada al negocio.
 *
 * Esta relacionada con la clase DetalleEstacionBacking.
 * */
public class DetalleEstacion extends StandarBean {
    //Estacion seleccionada para detallar
    private SiovEstaciones estacionSeleccionada;

    //Datos adicionales de la estacion
    private PartRefOfertaEstacSubzona datosAdicionalesEstacion;

    //listado de resultados.
    private List datos;
    //Listado de valores validos en la serie.
    private List validData;
    //Listado de valores NO validos en la serie.
    private List nullData;
    //listado de resultados historicos por estacion.
    private List datosHistoricos;
    //listado de resultados multianuales por estacion.
    private List datosMultianuales;
    //listado de resumen de los multianuales por estacion.
    private List datosMultianualesResumen;
    //Resultados para las graficas de serie historica.
    private List tabularDataHistorico;
    //Resultados para las graficas de curva de duracion.
    private List tabularDataDuracion;
    //Resultados para las graficas de frecuencia datos.
    private List tabularDataFrecuencia;
    //Resultados para las graficas de histograma.
    private List tabularDataHistograma;
    //Resultados para las graficas de box plot.
    private List tabularDataBoxPlot;
    //Resultados para las graficas de multianuales.
    private List tabularDataMultianual;

    //listado de meses del agno.
    private List listaMes;
    //listado de variables con series disponible.
    private List listaVariables;
    //valor del agno seleccionado.
    private String agnoSeleccionado;
    //Objeto para almacenar las medidas de tendencia Central.
    private EstadigrafosTO estadigrafos;

    //bandera para mostrar la tabla de datos diaria.
    private boolean diaria;
    //bandera para mostrar la tabla de datos mensuales.
    private boolean mensual;
    //bandera para mostrar grafica histograma.
    private boolean showHistograma;
    //bandera para mostrar grafica curva de duración.
    private boolean showCurva;
    //bandera para mostrar grafica de promedio datos por año.
    private boolean showPromedio;
    //bandera para mostrar grafica box plot.
    private boolean showAnual;
    //bandera para mostrar la serie historica.
    private boolean showHistorico;
    //bandera para mostrar la grafica multianual.
    private boolean showMultianual;

    private String unidadMedida;
    private String unidadMedidaAnual;

    //CDONCEL
    private Boolean flagHidro = true;

    private Boolean flagMorfo = true;

    public static final String[] varMeteo =
    { "EVP_TTL_MNS_MET2", "EVP_TTL_DRO_MET2", "PCP_TTL_MNS_MET2",
      "PCP_TTL_DRO_MET2" };
    private String unidad;
    //listado de resultados aux.
    private List datosAux;
    private List variablesAux;
    private Boolean flagCompara = false;

    public void getEstacion() throws IdeamException {
        this.estacionSeleccionada = null;
        OfertaDelegate od = OfertaDelegate.getInstance();
        Object obj = FacesUtils.getFromSession("estacion");

        if (obj instanceof Integer) {
            Integer est = (Integer)obj;
            if (est != null) {
                this.estacionSeleccionada =
                        od.getEstacion("" + est.longValue());
                this.datosAdicionalesEstacion =
                        od.getPartRefOfertaEstacSubzona(estacionSeleccionada.getCodInternoEs());

            }
        } else if (obj instanceof Long) {
            Long est = (Long)obj;
            if (est != null) {
                this.estacionSeleccionada = od.getEstacion("" + est);
                this.datosAdicionalesEstacion =
                        od.getPartRefOfertaEstacSubzona(estacionSeleccionada.getCodInternoEs());
            }
        } else if (obj instanceof String) {
            String est = (String)obj;
            if (est != null) {
                this.estacionSeleccionada = od.getEstacion(est);
                this.datosAdicionalesEstacion =
                        od.getPartRefOfertaEstacSubzona(estacionSeleccionada.getCodInternoEs());


            }
        } else if (obj instanceof SiovEstaciones) {
            this.estacionSeleccionada = (SiovEstaciones)obj;
            this.datosAdicionalesEstacion =
                    od.getPartRefOfertaEstacSubzona(estacionSeleccionada.getCodInternoEs());

        }

        System.out.println("HCP-----------------  " + estacionSeleccionada.getCodInternoEs()); 
        if (datosAdicionalesEstacion != null && datosAdicionalesEstacion.getAreaCuenca() != null){
            System.out.println("TIENE DATOS MORFOMETRICOS");
            this.flagMorfo = true;
        }
           
        else{
            System.out.println("NO TIENE DATOS MORFOMETRICOS");
            this.flagMorfo = false;
        }
           
    }

    public boolean getMostrarMorfometrico() {
        if (datosAdicionalesEstacion.getAreaCuenca() != null) {
            System.out.println("TIENE DATOS MORFOMETRICOS");
            return true;
        } else {
            System.out.println("NO TIENE DATOS MORFOMETRICOS");
            return false;
        }
    }

    public void cargarParametros() throws IdeamException {
        this.listaMes = this.cargarMes();
        // this.listaVariables = this.cargarVariableOferta();
    }

    public void cargarVariables() throws IdeamException {
        setFlagHidro(false);
        OfertaDelegate od = OfertaDelegate.getInstance();
        this.listaVariables = new ArrayList();
        variablesAux = new ArrayList();
        variablesAux = od.getVariables(this.estacionSeleccionada.getIdEs());
        if (variablesAux != null && variablesAux.size() > 0) {
            List variablesAux2 = new ArrayList<String>();
            for (Object s : variablesAux) {
                String q = (String)s;
                for (String a : varMeteo) {
                    if (q.equals(a))
                        variablesAux2.add(a);
                }
            }
            if (this.estacionSeleccionada.getClaseEs().equals("MET")) {
                variablesAux = variablesAux2;
                // System.out.println("***************ESTACION METEOROLOGICA");
            } else
                variablesAux.removeAll(variablesAux2);
            this.listaVariables = this.cargarVariableOferta(variablesAux);
            if (this.estacionSeleccionada.getClaseEs().equals("HID")) {
                setFlagHidro(true);
                //  System.out.println("***************ESTACION HIDROLOGICA");
                setUnidad("Nivel medio mensual(centimetros)");
            } else
                setUnidad("Nivel medio mensual(milimetros)");
        }
    }

    public void cargarDatos(String variable, Integer agno,
                            Integer mes) throws IdeamException {
        flagCompara = false;
        this.unidadMedida = "";
        this.unidadMedidaAnual = "";
        this.unidadMedida = ConstantesOferta.variablesSerie.get(variable);
        this.unidadMedidaAnual =
                ConstantesOferta.variablesSerieAnual.get(variable);

        if (variable.contains(ConstantesOferta.PREFIJO_NIVEL)) {
            this.unidadMedida += " (cm)";
            this.unidadMedidaAnual += " Anual (cm)";
        } else if (variable.contains(ConstantesOferta.PREFIJO_CAUDAL)) {
            this.unidadMedida += " (m^3/s)";
            this.unidadMedidaAnual += " Anual (m^3/s)";
        } else if (variable.contains(ConstantesOferta.PREFIJO_SEDIMENTO)) {
            if (variable.contains(ConstantesOferta.PREFIJO_CONCENTRACION)) {
                this.unidadMedida += " (kg/m^3)";
                this.unidadMedidaAnual += " Anual (kg/m^3)";
            } else if (variable.contains(ConstantesOferta.PREFIJO_TRANSPORTE)) {
                this.unidadMedida += " (kTon/Dia)";
                this.unidadMedidaAnual += " Anual (kTon/Dia)";
            }
        }

        System.out.println("----------------cargarDatos 1");
        boolean continuar = true;
        this.datos = new ArrayList();
        this.datosHistoricos = new ArrayList();

        //System.out.println("----------------cargarDatos 2");
        if (continuar) {
            //System.out.println("----------------cargarDatos 3");

            OfertaDelegate od = OfertaDelegate.getInstance();

            //System.out.println("id estacion: "+this.estacionSeleccionada.getIdEs());
            //System.out.println("variable: "+variable);
            //System.out.println("año: "+agno);
            //System.out.println("mes: "+mes);

            if (variable.contains(ConstantesOferta.PREFIJO_MES)) { //serie de datos mensuales.
                if (agno > 0) {
                    this.datos =
                            od.getMensuales(this.estacionSeleccionada.getIdEs(),
                                            variable, agno);
                } else {
                    this.datos =
                            od.getMensuales(this.estacionSeleccionada.getIdEs(),
                                            variable);

                    //CDONCEL
                    if (variable.equals("EVP_TTL_MNS_MET2")) { //Estructura para calcular grafica
                        //de comparacion EVP vs PREC
                        datosAux = new ArrayList();
                        for (Object o : variablesAux) {
                            String q = (String)o;
                            if (q.equals("PCP_TTL_MNS_MET2")) {
                                datosAux =
                                        od.getMensuales(this.estacionSeleccionada.getIdEs(),
                                                        "PCP_TTL_MNS_MET2");
                            }
                        }
                        //System.out.println(" ++++++++ T A M A Ñ O - D A T O S : --- " + datos.size());
                        //System.out.println(" ++++++++ T A M A Ñ O - D A T O S - A U X : --- " + datosAux.size());
                        if (datos.size() > 1)
                            if (datosAux.size() > 1)
                                setFlagCompara(true);
                    }
                    //FIN CDONCEL
                }

                this.setDiaria(false);
                this.setMensual(true);

            } else if (variable.contains(ConstantesOferta.PREFIJO_DIA)) { //serie de datos diarios.
                if (mes > 0 && agno > 0) {
                    this.datos =
                            od.getDiarios(this.estacionSeleccionada.getIdEs(),
                                          variable, agno, mes);
                } else if (mes == 0 && agno > 0) {
                    this.datos =
                            od.getDiarios(this.estacionSeleccionada.getIdEs(),
                                          variable, agno);
                } else {
                    this.datos =
                            od.getDiarios(this.estacionSeleccionada.getIdEs(),
                                          variable);
                }

                this.setDiaria(true);
                this.setMensual(false);
            }

            this.datosHistoricos.addAll((Collection)this.datos);
        }
    }

    public void cargarDatosMultianuales(String variable,
                                        Integer agno) throws IdeamException {

        System.out.println("----------------cargarDatos Multianuales 1");
        boolean continuar = true;
        this.datosMultianuales = new ArrayList();

        //System.out.println("----------------cargarDatos Multianuales 2");
        if (continuar) {
            //System.out.println("----------------cargarDatos Multianuales 3");

            OfertaDelegate od = OfertaDelegate.getInstance();

            //System.out.println("id estacion: "+this.estacionSeleccionada.getIdEs());
            //System.out.println("variable: "+variable);
            //System.out.println("año: "+agno);
            if (variable.contains(ConstantesOferta.PREFIJO_MES)) { //serie de datos mensuales.
                //System.out.println("----------------cargarDatos Multianuales MES");
                this.datosMultianuales =
                        od.getMultianualesDiarios(this.estacionSeleccionada.getIdEs(),
                                                  variable, agno,
                                                  ConstantesOferta.PREFIJO_MES);
            } else if (variable.contains(ConstantesOferta.PREFIJO_DIA)) { //serie de datos diarios.
                //System.out.println("----------------cargarDatos Multianuales DIA");
                this.datosMultianuales =
                        od.getMultianualesDiarios(this.estacionSeleccionada.getIdEs(),
                                                  variable, agno,
                                                  ConstantesOferta.PREFIJO_DIA);
            }

        }
    }

    public void cargarDatosMultianualesResumen(String variable) throws IdeamException {

        System.out.println("----------------cargarDatos Multianuales Resumen 1");
        boolean continuar = true;
        this.datosMultianualesResumen = new ArrayList();

        //System.out.println("----------------cargarDatos Multianuales Resumen 2");
        if (continuar) {
            //System.out.println("----------------cargarDatos Multianuales Resumen 3");

            OfertaDelegate od = OfertaDelegate.getInstance();

            //System.out.println("id estacion: "+this.estacionSeleccionada.getIdEs());
            //System.out.println("variable: "+variable);
            if (variable.contains(ConstantesOferta.PREFIJO_MES)) { //serie de datos mensuales.
                this.datosMultianualesResumen =
                        od.getMultianualesResumen(this.estacionSeleccionada.getIdEs(),
                                                  variable, 0,
                                                  ConstantesOferta.PREFIJO_MES); // no es necesario el año.
            } else if (variable.contains(ConstantesOferta.PREFIJO_DIA)) { //serie de datos diarios.
                this.datosMultianualesResumen =
                        od.getMultianualesResumen(this.estacionSeleccionada.getIdEs(),
                                                  variable, 0,
                                                  ConstantesOferta.PREFIJO_DIA); // no es necesario el año.
            }

        }
    }

    public boolean validarCantidadDatos(Integer cantidad) throws IdeamException {
        if (Validacion.cantidadDatos(this.datos) < cantidad) {
            //this.datosHistoricos = null;
            //this.datos = null;
            return false;
        }
        return true;
    }

    public void limpiarDatos() throws IdeamException {
        this.validData = Util.extractValidData(this.datos);
        this.nullData = Util.extractNullData(this.datos);
    }

    public void tendenciaCentral() throws IdeamException {
        System.out.println("=============================================================ESTADIGRAFOS");
        this.estadigrafos = new EstadigrafosTO();
        TendenciaCentral grafica = new TendenciaCentral();

        grafica.calcular(this.datos); //calcula la media
        this.estadigrafos.setMedia(grafica.getMedia());
        this.estadigrafos.setSerie(grafica.getCantidadTotalSerie());
        this.estadigrafos.setNulos(grafica.getCantidadNulos());
        this.estadigrafos.setPoblacion(grafica.getCantidadTotalValid());
        this.estadigrafos.setSumaDatos(grafica.getSumaTotalValid());
        this.estadigrafos.setFechaMinima(grafica.getFechaMinima());
        this.estadigrafos.setFechaMaxima(grafica.getFechaMaxima());
        this.estadigrafos.setQ25(grafica.getQ25());
        this.estadigrafos.setQ75(grafica.getQ75());
        this.estadigrafos.setIqr(grafica.getQ75() - grafica.getQ25());

        if (this.validData != null && !this.validData.isEmpty()) {
            List data = Util.sortData(this.validData, Util.DESCENDENTE);

            this.estadigrafos.setMaximo((Double)data.get(0));
            this.estadigrafos.setMinimo((Double)data.get((data.size() - 1)));
        }
    }

    public void frecuenciaDatos() throws IdeamException {
        this.tabularDataFrecuencia = new ArrayList();
        if (this.datos != null && !this.datos.isEmpty()) {
            System.out.println("=============================================================FRECUENCIA DATOS");
            FrecuenciaDatos grafica = new FrecuenciaDatos();
            grafica.calcular(this.datos);
            this.tabularDataFrecuencia = grafica.tabular();
        }
    }

    public void curvaDuracion() throws IdeamException {
        this.tabularDataDuracion = new ArrayList();
        if (this.validData != null && !this.validData.isEmpty()) {
            System.out.println("=============================================================CURVA DURACION");
            CurvaDuracion grafica = new CurvaDuracion();
            grafica.calcular(this.validData);
            this.tabularDataDuracion = grafica.tabular();

            // obtener datos del calculo del IRH
            this.estadigrafos.setAreaTotal(grafica.getAreaTotal());
            this.estadigrafos.setArea50(grafica.getArea50());
            this.estadigrafos.setAreaPunto50(grafica.getAreaPunto50());
            this.estadigrafos.setAreaTotal50(grafica.getAreaTotal50());
            this.estadigrafos.setIrh(grafica.getIrh());
            this.estadigrafos.setCategoriaIrh(grafica.getCategoriaIrh());


        }
    }

    public void anual() throws IdeamException {
        this.tabularDataBoxPlot = new ArrayList();
        if (this.datosMultianuales != null &&
            !this.datosMultianuales.isEmpty()) {
            System.out.println("=============================================================PROMEDIOS ANUALES");
            Anual grafica = new Anual();
            grafica.calcular(this.datosMultianuales);
            this.tabularDataBoxPlot = grafica.tabular();
        }
    }

    public void multianual(Integer agno) throws IdeamException {
        this.tabularDataMultianual = new ArrayList();
        if (this.datosMultianualesResumen != null &&
            !this.datosMultianualesResumen.isEmpty()) {
            System.out.println("=============================================================MULTIANUALES");
            Multianual grafica = new Multianual();
            if (agno > 0) {
                grafica.calcular(this.datosMultianuales,
                                 this.datosMultianualesResumen);
            } else {
                grafica.calcular(null, this.datosMultianualesResumen);
            }
            this.tabularDataMultianual = grafica.tabular();
        }
    }

    public void histograma() throws IdeamException {
        this.tabularDataHistograma = new ArrayList();
        if (this.validData != null && !this.validData.isEmpty()) {
            System.out.println("=============================================================HISTOGRAMA");
            Histograma grafica = new Histograma();
            grafica.calcular(this.validData);
            this.tabularDataHistograma = grafica.tabular();
        }
    }

    public void serieHistorica() throws IdeamException {
        this.tabularDataHistorico = new ArrayList();
        if (this.datos != null && !this.datos.isEmpty()) {
            System.out.println("=============================================================HISTORICA");
            Historico grafica = new Historico();
            grafica.calcular(this.datos);
            this.tabularDataHistorico = grafica.tabular();
        }
    }

    public void setDatos(List datos) {
        this.datos = datos;
    }

    public List getDatos() {
        return datos;
    }

    public void setListaMes(List listaMes) {
        this.listaMes = listaMes;
    }

    public List getListaMes() {
        return listaMes;
    }

    public void setTabularDataHistorico(List tabularDataHistorico) {
        this.tabularDataHistorico = tabularDataHistorico;
    }

    public List getTabularDataHistorico() {
        return tabularDataHistorico;
    }

    public void setEstacionSeleccionada(SiovEstaciones estacionSeleccionada) {
        this.estacionSeleccionada = estacionSeleccionada;
    }

    public SiovEstaciones getEstacionSeleccionada() {
        return estacionSeleccionada;
    }

    public void setEstadigrafos(EstadigrafosTO estadigrafos) {
        this.estadigrafos = estadigrafos;
    }

    public EstadigrafosTO getEstadigrafos() {
        return estadigrafos;
    }

    public void setTabularDataDuracion(List tabularDataDuracion) {
        this.tabularDataDuracion = tabularDataDuracion;
    }

    public List getTabularDataDuracion() {
        return tabularDataDuracion;
    }

    public void setTabularDataFrecuencia(List tabularDataFrecuencia) {
        this.tabularDataFrecuencia = tabularDataFrecuencia;
    }

    public List getTabularDataFrecuencia() {
        return tabularDataFrecuencia;
    }


    public void setTabularDataHistograma(List tabularDataHistograma) {
        this.tabularDataHistograma = tabularDataHistograma;
    }

    public List getTabularDataHistograma() {
        return tabularDataHistograma;
    }

    public void setListaVariables(List listaVariables) {
        this.listaVariables = listaVariables;
    }

    public List getListaVariables() {
        return listaVariables;
    }

    public void setDiaria(boolean diaria) {
        this.diaria = diaria;
    }

    public boolean isDiaria() {
        return diaria;
    }

    public void setMensual(boolean mensual) {
        this.mensual = mensual;
    }

    public boolean isMensual() {
        return mensual;
    }

    public void setShowHistograma(boolean showHistograma) {
        this.showHistograma = showHistograma;
    }

    public boolean isShowHistograma() {
        return showHistograma;
    }

    public void setShowCurva(boolean showCurva) {
        this.showCurva = showCurva;
    }

    public boolean isShowCurva() {
        return showCurva;
    }

    public void setShowPromedio(boolean showPromedio) {
        this.showPromedio = showPromedio;
    }

    public boolean isShowPromedio() {
        return showPromedio;
    }

    public void setShowAnual(boolean showAnual) {
        this.showAnual = showAnual;
    }

    public boolean isShowAnual() {
        return showAnual;
    }

    public void setShowHistorico(boolean showHistorico) {
        this.showHistorico = showHistorico;
    }

    public boolean isShowHistorico() {
        return showHistorico;
    }

    public void setDatosMultianuales(List datosMultianuales) {
        this.datosMultianuales = datosMultianuales;
    }

    public List getDatosMultianuales() {
        return datosMultianuales;
    }

    public void setTabularDataBoxPlot(List tabularDataBoxPlot) {
        this.tabularDataBoxPlot = tabularDataBoxPlot;
    }

    public List getTabularDataBoxPlot() {
        return tabularDataBoxPlot;
    }

    public void setDatosMultianualesResumen(List datosMultianualesResumen) {
        this.datosMultianualesResumen = datosMultianualesResumen;
    }

    public List getDatosMultianualesResumen() {
        return datosMultianualesResumen;
    }


    public void setTabularDataMultianual(List tabularDataMultianual) {
        this.tabularDataMultianual = tabularDataMultianual;
    }

    public List getTabularDataMultianual() {
        return tabularDataMultianual;
    }

    public void setShowMultianual(boolean showMultianual) {
        this.showMultianual = showMultianual;
    }

    public boolean isShowMultianual() {
        return showMultianual;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setAgnoSeleccionado(String agnoSeleccionado) {
        this.agnoSeleccionado = agnoSeleccionado;
    }

    public String getAgnoSeleccionado() {
        return agnoSeleccionado;
    }

    public void setUnidadMedidaAnual(String unidadMedidaAnual) {
        this.unidadMedidaAnual = unidadMedidaAnual;
    }

    public String getUnidadMedidaAnual() {
        return unidadMedidaAnual;
    }

    public void setDatosAdicionalesEstacion(PartRefOfertaEstacSubzona datosAdicionalesEstacion) {
        this.datosAdicionalesEstacion = datosAdicionalesEstacion;
    }

    public PartRefOfertaEstacSubzona getDatosAdicionalesEstacion() {
        return datosAdicionalesEstacion;
    }

    public Boolean getFlagHidro() {
        return flagHidro;
    }

    public void setFlagHidro(Boolean flagHidro) {
        this.flagHidro = flagHidro;
    }

    public String getUnidad() {
        return unidad;
    }

    public void setUnidad(String unidad) {
        this.unidad = unidad;
    }

    //CDONCEL

    public List<Object[]> getTabularData() {
        List<Object[]> tabularData = new ArrayList<Object[]>();
        if (flagCompara) {
            for (Object o : datos) {
                ShmvMensualesHid s1 = (ShmvMensualesHid)o;
                tabularData.add(new Object[] { s1.getMnsAno(), "Evaporación",
                                               s1.getPromedio() });
            }
            for (Object o : datosAux) {
                ShmvMensualesHid s1 = (ShmvMensualesHid)o;
                long x =
                    (s1.getMnsValor1() == null ? 1 : new Long(0)) + (s1.getMnsValor2() ==
                                                                     null ? 1 :
                                                                     new Long(0)) +
                    (s1.getMnsValor3() == null ? 1 : new Long(0)) +
                    (s1.getMnsValor4() == null ? 1 : new Long(0)) +
                    (s1.getMnsValor5() == null ? 1 : new Long(0)) +
                    (s1.getMnsValor6() == null ? 1 : new Long(0)) +
                    (s1.getMnsValor7() == null ? 1 : new Long(0)) +
                    (s1.getMnsValor8() == null ? 1 : new Long(0)) +
                    (s1.getMnsValor9() == null ? 1 : new Long(0)) +
                    (s1.getMnsValor10() == null ? 1 : new Long(0)) +
                    (s1.getMnsValor11() == null ? 1 : new Long(0)) +
                    (s1.getMnsValor12() == null ? 1 : new Long(0));
                Double suma =
                    (s1.getMnsValor1() != null ? s1.getMnsValor1() : 0) +
                    (s1.getMnsValor2() != null ? s1.getMnsValor2() : 0) +
                    (s1.getMnsValor3() != null ? s1.getMnsValor3() : 0) +
                    (s1.getMnsValor4() != null ? s1.getMnsValor4() : 0) +
                    (s1.getMnsValor5() != null ? s1.getMnsValor5() : 0) +
                    (s1.getMnsValor6() != null ? s1.getMnsValor6() : 0) +
                    (s1.getMnsValor7() != null ? s1.getMnsValor7() : 0) +
                    (s1.getMnsValor8() != null ? s1.getMnsValor8() : 0) +
                    (s1.getMnsValor9() != null ? s1.getMnsValor9() : 0) +
                    (s1.getMnsValor10() != null ? s1.getMnsValor10() : 0) +
                    (s1.getMnsValor11() != null ? s1.getMnsValor11() : 0) +
                    (s1.getMnsValor12() != null ? s1.getMnsValor12() : 0);
                Double prom = suma / (12 - x);
                tabularData.add(new Object[] { s1.getMnsAno(), "Precipitación",
                                               prom });
            }
        }
        return tabularData;
    }
    //FIN CDONCEL

    public List getDatosAux() {
        return datosAux;
    }

    public void setDatosAux(List datosAux) {
        this.datosAux = datosAux;
    }

    public List getVariablesAux() {
        return variablesAux;
    }

    public void setVariablesAux(List variablesAux) {
        this.variablesAux = variablesAux;
    }

    public Boolean getFlagCompara() {
        return flagCompara;
    }

    public void setFlagCompara(Boolean flagCompara) {
        this.flagCompara = flagCompara;
    }

    public void setFlagMorfo(Boolean flagMorfo) {
        this.flagMorfo = flagMorfo;
    }

    public Boolean getFlagMorfo() {
        return flagMorfo;
    }
}
