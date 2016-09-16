package co.gov.ideam.sirh.view.util;


import co.gov.ideam.sirh.calidad.model.Muestra;
import co.gov.ideam.sirh.calidad.model.PuntoMonitoreo;
import co.gov.ideam.sirh.fuentes.model.FnttTramo;
import co.gov.ideam.sirh.pomca.model.Indicador;
import co.gov.ideam.sirh.pomca.model.Proyecto;
import co.gov.ideam.sirh.pueaa.model.PueatProyectos;
import co.gov.ideam.sirh.pueaa.model.PueatPuea;
import co.gov.ideam.sirh.red.ideam.model.FqMuestras;
import co.gov.ideam.sirh.seguridad.model.OpcionVO;
import co.gov.ideam.sirh.usuarios.agua.model.Captacion;
import co.gov.ideam.sirh.usuarios.agua.model.Concesion;
import co.gov.ideam.sirh.usuarios.agua.model.PermisoVertimiento;
import co.gov.ideam.sirh.usuarios.agua.model.Predio;
import co.gov.ideam.sirh.usuarios.agua.model.PuntoVertimiento;
import co.gov.ideam.sirh.usuarios.agua.model.RurtCaptacionUso;
import co.gov.ideam.sirh.usuarios.agua.model.SubttFunias;
import co.gov.ideam.sirh.view.FacesUtils;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Utilizado para generar los arboles de opciones que se muestran en el Bean
 * de perfiles
 */
public class TreeNode {

    private String description ;
    private String longDescription ;
    private Collection children;
    private String nodeType;
    private boolean nodeSelected;
    private Object data;
    private Object extraData;
    private Object extraData2;
    private Object extraData3;
    private static final String bold = "font-weight:bold;";
    private static final String italic = "font-style:italic;";
    private static final String detallePredioAction = "detallePredio";
    private static final String listaConcesiones = "listarConcesiones";
    private static final String listaCaptaciones = "listarCaptaciones";
    private static final String listaPermisos = "listarPermisos";
    private static final String detallePermiso = "detallePermiso";
    private static final String detalleConcesion = "detalleConcesion";
    private static final String detalleTramo = "detalleTramo";
    private static final String listaTramos = "listarTramos";
    private static final String detalleCaptacion = "detalleCaptacion";
    private static final String listaMuestrasPunto = "listaMuestrasPunto";
    private static final String detalleMuestra = "detalleMuestra";
    private static final String detalleVertimiento = "detalleVertimiento";
    private static final String listaVertimientos = "listarVertimientos";
    private static final String listaPuntosTramo = "listaPuntosTramo";
    private static final String detallePuntosMonitoreo = "detallepunto";
    private static final String listaPuntosVert = "listaPuntosVert";
    private static final String detalleUso = "detalleUso";
    private static final String listaUsos = "listarUsos";
    private static final String detalleFunias = "detalleFunias";
    private static final String listaFunias = "listarFunias";
    /* Inicia Hugo Cendales */
        private static final String detalleProyecto = "detalleProyecto";
        private static final String listaProyectos = "listarProyectos";
        private static final String detalleIndicador = "detalleIndicador";
        private static final String listaIndicadores = "listarIndicadores";
    /* Termian Hugo Cendales */
    /* Inicia Pilar */
        private static final String detalleCaptacionSinConc = "detalleCaptacionSinConc";
        private static final String listaCaptacionesSinConc= "listarCaptacionesSinConc";
       
    /* Termian Pilar */
        
    private static final String detalleMuestraIdeam = "detalleMuestraIdeam";
    private static final String listaMuestrasIdeam = "listaMuestrasIdeam";

    
    private boolean isBold = false;
    private boolean isItalic = false;
    private String action;
    private String nombreParametro;
    private boolean detalleNodo;

    public TreeNode() {
    }

    public TreeNode(String description, String nodeType, boolean boldFont, boolean italicFont) {
        this(description,nodeType);
        this.isBold = boldFont;
        this.isItalic = italicFont;
    }
    public TreeNode(String description, String nodeType) {
      this.description = description;
      this.nodeType = nodeType;
      this.children = new ArrayList();
    }
    public TreeNode(String description, String longDescription, Collection children) {
      this.description = description;
      this.longDescription = longDescription;
      this.children = children;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setLongDescription(String longDescription) {
        this.longDescription = longDescription;
    }

    public String getLongDescription() {
        return longDescription;
    }

    public void setChildren(Collection children) {
        this.children = children;
    }

    public Collection getChildren() {
        return children;
    }

    public int getChildCount() {
      if (children == null)
        return 0;
      else
        return children.size();
    }

    public void setNodeType(String nodeType) {
        this.nodeType = nodeType;
    }

    public String getNodeType() {
        return nodeType;
    }

    public void setNodeSelected(boolean nodeSelected) {
        this.nodeSelected = nodeSelected;
    }

    public boolean isNodeSelected() {
        return nodeSelected;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getInternalName(){
        if (this.data!=null && this.data instanceof OpcionVO){
            return ((OpcionVO)data).getOpcIdJsf();
        }else if(this.data != null && this.data instanceof Predio){
            return ((Predio)data).getNombre();
        }
        return "";
    }

    public String getInlineStyle(){
        String estilo = "";
        if(isBold){
            estilo += bold;
        }
        if(isItalic){
            estilo += italic;
        }
        return estilo;
    }
    /** Define la relga de navegacion que debe ejecutar el nodo de un arbol. Si recibe un objeto debe ir
     * al detalle del mismo. Si recibe un String significa que el nodo es un enlace
     *
     * @return
     */
    public String getAction() {
        System.out.println("Tipo de nodo:"+this.data.toString());
       
        /*if (this.data!=null && this.data instanceof PueaaDelegate){
            return detallePredioAction;
        }*/
        if (this.data!=null && this.data instanceof Predio){
            return detallePredioAction;
        }
        if (this.data!=null && this.data instanceof PueatPuea){
            return "adicionarPueaa";
        }   
        if (this.data!=null && this.data instanceof PueatProyectos){
            return "adicionarProyectoPueaa";
        }  
        if (this.data!=null && this.data instanceof String){
           
            if(this.data.toString().equalsIgnoreCase("Concesiones")){
                detalleNodo=false;
                return listaConcesiones;
            }
            if(this.data.toString().equalsIgnoreCase("Permisos")){
                detalleNodo=false;
                return listaPermisos;
            }
            if(this.data.toString().equalsIgnoreCase("Captaciones")){
                detalleNodo=false;
                return listaCaptaciones;
            }

            //eduin ortiz
            if(this.data.toString().equalsIgnoreCase("Tramos")){
                detalleNodo=false;
                return listaTramos;
            }
            //Lis
            if(this.data.toString().equalsIgnoreCase("Muestras")){
                detalleNodo=false;
                return listaMuestrasPunto;
            }
            
            //eduin ortiz
            if(this.data.toString().equalsIgnoreCase("Vertimientos")){
                detalleNodo=false;
                return listaVertimientos;
            }
           
            //Lis
            if(this.data.toString().equalsIgnoreCase("PuntosMonitoreo")){
                detalleNodo=false;
                return listaPuntosTramo;
            }
            //Lis
            if(this.data.toString().equalsIgnoreCase("PuntosMonitoreoVert")){
                detalleNodo=false;
                return listaPuntosVert;
            }
            
            //eduin ortiz
            if(this.data.toString().equalsIgnoreCase("Usos")){
                detalleNodo=false;
                return listaUsos;
            }
            
            //eduin ortiz
            if(this.data.toString().equalsIgnoreCase("Funias")){
                detalleNodo=false;
                return listaFunias;
            }
            // Incia Hugo Cendales 
            if(this.data.toString().equalsIgnoreCase("Proyectos")){
                detalleNodo=false;
                return listaProyectos;
            }
            
            if(this.data.toString().equalsIgnoreCase("Indicadores")){
                detalleNodo=false;
                return listaIndicadores;
            }
                                    
            // Fin Hugo Cendales
            // Incia Pilar
            if(this.data.toString().equalsIgnoreCase("CaptacionesSinConc")){
                detalleNodo=true;
                return listaCaptacionesSinConc;
            }

                                    
            // Fin Pilar
            
            if(this.data.toString().equalsIgnoreCase("MuestrasIdeam")){
                detalleNodo=false;
                return listaMuestrasIdeam;
            }
            if(this.data.toString().equalsIgnoreCase("Proyecto")){
                detalleNodo=false;
                return "adicionarProyectoPueaa";
            }
        }
        
        if(this.data!=null && this.data instanceof Concesion){
            return detalleConcesion;
        }
        
        if(this.data!=null && this.data instanceof PermisoVertimiento){
          System.out.println("-------------V E R T I M I E N T O ?????? - " + this.nodeType);
          if (this.nodeType.equals("Permiso"))
            return detallePermiso;
          else {//VERTIMIENTO SIN RESOLUCION
            System.out.println("-------------V E R T I M I E N T O SIN ------ " );
            return detalleVertimiento; 
          }
        }

        if (this.data!=null && this.data instanceof FnttTramo){
            return detalleTramo;
        }

            if(this.detalleNodo==false){
            if (this.data!=null && this.data instanceof Captacion)//Pilar
              return detalleCaptacion;
        }
        
        if (this.data!=null && this.data instanceof Muestra){
            return detalleMuestra;
        }
        
        if (this.data!=null && this.data instanceof PuntoVertimiento){
            return detalleVertimiento;
        }
        
        if (this.data!=null && this.data instanceof PuntoMonitoreo){
            return detallePuntosMonitoreo;
        }
        
        if (this.data!=null && this.data instanceof RurtCaptacionUso){
            return detalleUso;
        }
        
        if (this.data!=null && this.data instanceof SubttFunias){
            return detalleFunias;
        }
        /* Inciar Hugo Cendales */
        if (this.data!=null && this.data instanceof Proyecto){
            return detalleProyecto;
        }
        
        if (this.data!=null && this.data instanceof Indicador){
            return detalleIndicador;
        }
        /* Fin Hugo Cendales */
        
        if (this.data!=null && this.data instanceof FqMuestras){
            return detalleMuestraIdeam;
        }
        
        //Pilar
        if(this.detalleNodo==true){
            if (this.data!=null && this.data instanceof Captacion)
                return detalleCaptacionSinConc;
        }
        System.out.println("detalle Nodo:"+this.detalleNodo);
        return "--";
    }

    public void setAction(String action) {
        this.action = action;
    }


    public String getNombreParametro() {
        System.out.println("　　　　　　　　　　　　getNombreParametro detalle Nodo:"+this.detalleNodo);
        if (this.data!=null && this.data instanceof Predio){
            return "predioSeleccionado";
        }
        if (this.data!=null && this.data instanceof Concesion){
            FacesUtils.setInSession("paginaOrigen","listaPredios");
            return "concesionSeleccionada";
        }
        if (this.data!=null && this.data instanceof PermisoVertimiento){
            FacesUtils.setInSession("paginaOrigen","listaPredios");
            return "permisoSeleccionado";
        }
        //Pueaa
        if (this.data!=null && this.data instanceof PueatPuea){
            return "pueaaSeleccionado";
        }
        if (this.data!=null && this.data instanceof PueatProyectos){
            FacesUtils.setInSession("paginaOrigen","detalleUsuario");
            return "proyectoSeleccionada";
        }

        if(this.detalleNodo==false){
           if (this.data!=null && this.data instanceof Captacion)//Pilar
             return "captacionSeleccionada";
        }

        if (this.data!=null && this.data instanceof FnttTramo){
            return "tramoSeleccionado";
        }
        
        if (this.data!=null && this.data instanceof Muestra){
            return "muestraSeleccionada";
        }
        
        if (this.data!=null && this.data instanceof PuntoVertimiento){
            return "vertimientoSeleccionado";
        }
        
        if (this.data!=null && this.data instanceof PuntoMonitoreo){
            return  "puntoSeleccionado";
        }
        
        if (this.data!=null && this.data instanceof RurtCaptacionUso){
            return "usoSeleccionado";
        }
        
        if (this.data!=null && this.data instanceof SubttFunias){
            return "funiasSeleccionado";
        }
        /* Iniciar Hugo Cendales */
       if (this.data!=null && this.data instanceof Proyecto){
           return "proyectoSeleccionado";
       }
       
       if (this.data!=null && this.data instanceof Indicador){
           return "indicadorSeleccionado";
       }
       /* Fin Hugo Cendales */
       
       if (this.data!=null && this.data instanceof FqMuestras){
           return "muestraIdeamSeleccionada";
       }
       
       //Pilar
       if(this.detalleNodo==true){
          if (this.data!=null && this.data instanceof Captacion)
               return "captacionSinConcSeleccionado";
       }
        return "";
    }

    public void setNombreParametro(String nombreParametro) {
        this.nombreParametro = nombreParametro;
    }

    public Object getExtraData() {
        return extraData;
    }

    public void setExtraData(Object extraData) {
        this.extraData = extraData;
    }

    public Object getExtraData2() {
        return extraData2;
    }

    public void setExtraData2(Object extraData2) {
        this.extraData2 = extraData2;
    }

    public Object getExtraData3() {
        return extraData3;
    }

    public void setExtraData3(Object extraData3) {
        this.extraData3 = extraData3;
    }

    public void setDetalleNodo(boolean detalleNodo) {
        this.detalleNodo = detalleNodo;
    }

    public boolean isDetalleNodo() {
        return detalleNodo;
    }
}
