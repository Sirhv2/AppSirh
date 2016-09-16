package co.gov.ideam.sirh.oferta.view;

import co.gov.ideam.sirh.oferta.model.PartDatosIdf;
import co.gov.ideam.sirh.oferta.model.PartRefOfertaEstacSubzona;
import co.gov.ideam.sirh.oferta.model.SiovEstaciones;
import co.gov.ideam.sirh.parametros.model.OfertaEstacSubzonas;
import co.gov.ideam.sirh.parametros.model.OfertaSubzonas;
import co.gov.ideam.sirh.parametros.model.PartZonificListas;
import co.gov.ideam.sirh.parametros.view.ParametrosDelegate;
import co.gov.ideam.sirh.view.StandarBean;

import java.util.ArrayList;
import co.gov.ideam.sirh.util.IdeamException;

import co.gov.ideam.sirh.view.FacesUtils;

import java.util.List;

import javax.faces.model.SelectItem;

/**
 * Esta clase se crea para separar el manejo de los objetos de la vista ADF
 * de la lógica propia relacionada al negocio.
 *
 * Esta relacionada con la clase EstacionesBacking.
 * */
public class Estaciones extends StandarBean{
    //Lista de areas hidrográficas.
    private List listaArea;
    //Lista de zonas hidrográficas relacionadas a un area.
    private List listaZona;
    //Lista de subzonas hidrográficas relacionadas a una zona.
    private List listaSubzona;
    //Listado de estaciones
    private List<SiovEstaciones> lEstaciones;
    //listado de los datos de referencia ENA  por subzona.
    private List<OfertaSubzonas> listaEna;
    //Estacion seleccionada en la tabla.
    protected SiovEstaciones estacionSeleccionada;
    
    //CDONCEL
    //Lista de Departamentos.
    private List listaDep;
    private List listaMun;
    private static String [] arrMeteo = {"AM", "CO", "CP", "ME", "PG", "PM", "SP", "SS"};
    private List<SiovEstaciones> lsEstIDF;
    private List lsEstIDFAux;
    private List<PartDatosIdf> lsDatosIDF;
    
    //FIN_CDOCNEL

    private boolean visibleCol;
    
    public void loadArea()throws IdeamException{  
        this.listaArea = new ArrayList();
        this.listaArea = this.cargarZonificacion(null);
    }
    
    public void loadZona(PartZonificListas area)throws IdeamException{  
        this.listaZona = new ArrayList();
        this.listaZona = this.cargarZonificacion(area.getId());
    }
    
    public void loadSubzona(PartZonificListas zona)throws IdeamException{
        this.listaSubzona = new ArrayList();
        this.listaSubzona = this.cargarZonificacion(zona.getId());
    }
    
    public void buscarEstaciones(PartZonificListas subZona, Integer codigo)throws IdeamException{
        ParametrosDelegate pd = ParametrosDelegate.getInstance();
        OfertaDelegate od = OfertaDelegate.getInstance();
        this.lEstaciones = new ArrayList();
        List<OfertaEstacSubzonas> estacionAux = new ArrayList();
        if(codigo!=null)
            estacionAux = pd.getAllEstacionesBySubzona(null, codigo);
        else
            estacionAux = pd.getAllEstacionesBySubzona(subZona.getId().intValue(),null);
        if(estacionAux != null){
            for(OfertaEstacSubzonas est : estacionAux){
                SiovEstaciones estacion = od.getEstacion(""+est.getOfertaEstacSubzonasPK().getIdEstacion());
                if(estacion!=null){
                    String idEstacion = estacion.getCodCatalogoEs();
                    
                    PartRefOfertaEstacSubzona po = od.getPartRefOfertaEstacSubzona(idEstacion);
                    
                    estacion.setTieneFews(false);
                    estacion.setUrlFews("");
                    
                    if (po != null)
                        if (po.getUrlFews() != null)
                            if (po.getUrlFews().length() > 0) {
                                estacion.setTieneFews(true);
                                estacion.setUrlFews(po.getUrlFews());
                            }
                    
                    if (this.isVisibleCol() && estacion.getTieneFews())
                        this.lEstaciones.add(estacion);

                    if (!this.isVisibleCol())
                        this.lEstaciones.add(estacion);

                } 
            }
            
        }
        
        
    }
    
    public void buscarDatosReferencia(PartZonificListas subZona)throws IdeamException{
        ParametrosDelegate pd = ParametrosDelegate.getInstance();
        this.listaEna = new ArrayList();
        this.listaEna = pd.getAllOfertaEnaBySubzona(subZona.getId().intValue());
        
    }


    public void setListaArea(List listaArea) {
        this.listaArea = listaArea;
    }

    public List getListaArea() {
        return listaArea;
    }

    public void setListaZona(List listaZona) {
        this.listaZona = listaZona;
    }

    public List getListaZona() {
        return listaZona;
    }

    public void setListaSubzona(List listaSubzona) {
        this.listaSubzona = listaSubzona;
    }

    public List getListaSubzona() {
        return listaSubzona;
    }

    public void setLEstaciones(List<SiovEstaciones> lEstaciones) {
        this.lEstaciones = lEstaciones;
    }

    public List<SiovEstaciones> getLEstaciones() {
        return lEstaciones;
    }

    public void setListaEna(List<OfertaSubzonas> listaEna) {
        this.listaEna = listaEna;
    }

    public List<OfertaSubzonas> getListaEna() {
        return listaEna;
    }
    public void setVisibleCol(boolean visibleCol) {
        this.visibleCol = visibleCol;
    }

    public boolean isVisibleCol() {
        return visibleCol;
    }
    
    //CDONCEL
    public void loadDep()throws IdeamException{  
        this.listaDep = new ArrayList();
        this.listaDep = this.cargarDivipola(null);
        System.out.println("Departamentos: " + listaDep.size());
  }  
      public void loadMun(Long dept)throws IdeamException{  
          this.listaMun = new ArrayList();
          this.listaMun = this.cargarDivipola(dept);
    }
  public void buscarEstacionesByMun(String mun, int opt)throws IdeamException{
      OfertaDelegate od = OfertaDelegate.getInstance();
      this.lEstaciones =  new ArrayList<SiovEstaciones>();
      List<SiovEstaciones> lEstacionesAux = new ArrayList();
      lEstacionesAux.addAll(od.getEstacionesByMun(mun));
        for(SiovEstaciones estacion : lEstacionesAux){  
            if(estacion!=null){
                String idEstacion = estacion.getCodCatalogoEs();
                
                PartRefOfertaEstacSubzona po = od.getPartRefOfertaEstacSubzona(idEstacion);
                
                estacion.setTieneFews(false);
                estacion.setUrlFews("");
                
                if (po != null)
                    if (po.getUrlFews() != null)
                        if (po.getUrlFews().length() > 0) {
                            estacion.setTieneFews(true);
                            estacion.setUrlFews(po.getUrlFews());
                        }
                if(opt == 1){//Estaciones Hidrologicas
                  if (estacion.getClaseEs().equals("HID"))
                    this.lEstaciones.add(estacion);
                } else if (opt == 2){//Estaciones Meteorologicas
                  for (String a: arrMeteo){
                      if (estacion.getCategoriaEs().equals(a))
                        this.lEstaciones.add(estacion);
                  }
                    
               }
          }
        }
  }
  
  public void buscarEstacionesMeteo(int subzona)throws IdeamException{
      ParametrosDelegate pd = ParametrosDelegate.getInstance();
      OfertaDelegate od = OfertaDelegate.getInstance();
      List<SiovEstaciones> lEstacionesAux = new ArrayList<SiovEstaciones>();
      lEstacionesAux.addAll(od.findBySubzona(subzona));
      lEstaciones =  new ArrayList<SiovEstaciones>();
      System.out.println("++++++++++");
      System.out.println("++++++++++");
      System.out.println("++++++++++");
      System.out.println("++++++++++");
      System.out.println("ESTACIONES METEO   :   " +lEstacionesAux.size());
      for(SiovEstaciones estacion : lEstacionesAux){
          System.out.println("ESTACION   :   " + estacion.getCategoriaEs());  
        }
          for(SiovEstaciones estacion : lEstacionesAux){
                if(estacion!=null){
                  String idEstacion = estacion.getCodCatalogoEs();
                  
                  PartRefOfertaEstacSubzona po = od.getPartRefOfertaEstacSubzona(idEstacion);
                  
                  estacion.setTieneFews(false);
                  estacion.setUrlFews("");
                  if (po != null)
                      if (po.getUrlFews() != null)
                          if (po.getUrlFews().length() > 0) {
                              estacion.setTieneFews(true);
                              estacion.setUrlFews(po.getUrlFews());
                          }
                for (String a: arrMeteo){
                    if (estacion.getCategoriaEs().equals(a))
                      this.lEstaciones.add(estacion);
                }
                  /*if (this.isVisibleCol() && estacion.getTieneFews())
                      this.lEstaciones.add(estacion);

                  if (!this.isVisibleCol())
                      this.lEstaciones.add(estacion);
*/
              } 
          }
          
  
      
      
  }
  
  public void loadIDF() throws IdeamException {
    try{
      lsEstIDF = new ArrayList<SiovEstaciones>();
      OfertaDelegate od = OfertaDelegate.getInstance();
      lsEstIDF.addAll(od.findEstacionesIDF());
      SiovEstaciones s1 = lsEstIDF.get(0);
      String m1 = s1.getDescDivipola();
      this.listaMun = new ArrayList();
      //SelectItem si = new SelectItem(s1, s1.getNomArea() + " - " + s1.getDescDivipola());
      SelectItem si = new SelectItem(s1,  s1.getDescDivipola());
      listaMun.add(si);
     // listaMun.add(s1.getNomArea() + " - " + s1.getDescDivipola());
      for (SiovEstaciones s : lsEstIDF){
        String m2 = s.getDescDivipola();
        if (!m1.equals(m2)){
            //SelectItem si1 = new SelectItem(s, s.getNomArea() + " - " + s.getDescDivipola());
            SelectItem si1 = new SelectItem(s,  s.getDescDivipola());
            listaMun.add(si1);
          }
        m1 = m2;
        //s.getNomArea() + " - " + s.getDescDivipola();
        }
      System.out.println("++++++++++++++ MUNICIPIO IDF : " + listaMun.size());
    } catch (Exception e){
        System.err.println("ERROR  CARGANDO ESTACIONES IDF... " + e.getLocalizedMessage());
      }
    }
  
  public void loadEstIDF(String mun)throws IdeamException{ 
      this.lsEstIDFAux = new ArrayList();
      for (SiovEstaciones s : lsEstIDF){
        if (s.getDescDivipola().equals(mun)){
            SelectItem si1 = new SelectItem(s, s.getNombreEs());
            lsEstIDFAux.add(si1);
        }            
      }
    //System.out.println("HOLA 3 _ " + lsEstIDFAux.size());
      //this.lsEstIDFAux = this.cargarDivipola(dept);
  }
  
  public void cargarDatosIDF(int cod){
      
      OfertaDelegate od;
    try {
      od = OfertaDelegate.getInstance();
      lsDatosIDF = new ArrayList<PartDatosIdf>();
      lsDatosIDF.addAll(od.findDatosIDF(cod));
    } catch (IdeamException e) {
    }
  }
      
    //FIN_CDONCEL

  public List getListaDep() {
    return listaDep;
  }

  public void setListaDep(List listaDep) {
    this.listaDep = listaDep;
  }

  public List getListaMun() {
    return listaMun;
  }

  public void setListaMun(List listaMun) {
    this.listaMun = listaMun;
  }

  public static String[] getArrMeteo() {
    return arrMeteo;
  }

  public static void setArrMeteo(String[] arrMeteo) {
    Estaciones.arrMeteo = arrMeteo;
  }

  public List<SiovEstaciones> getLsEstIDF() {
    return lsEstIDF;
  }

  public void setLsEstIDF(List<SiovEstaciones> lsEstIDF) {
    this.lsEstIDF = lsEstIDF;
  }

  public List getLsEstIDFAux() {
    return lsEstIDFAux;
  }

  public void setLsEstIDFAux(List lsEstIDFAux) {
    this.lsEstIDFAux = lsEstIDFAux;
  }

  public List<PartDatosIdf> getLsDatosIDF() {
    return lsDatosIDF;
  }

  public void setLsDatosIDF(List<PartDatosIdf> lsDatosIDF) {
    this.lsDatosIDF = lsDatosIDF;
  }
}
