package co.gov.ideam.sirh.dashboard.view;


import java.util.ArrayList;
import java.util.List;

/**
 * Lista de Porlets que se deben cargar al DashBoard
 */
public class PorletList {
    
    private static List<PorletDashBoard> listaPorletsTotal = 
        new ArrayList<PorletDashBoard>();
    
    private static List<Category> listaCategorias =
        new ArrayList<Category>();
    
    //eduin ortiz.
    private static List<Category> listaCategoriasDemanda =
        new ArrayList<Category>();
    
    private static List<PorletDashBoard> listaPorletsDemanda = 
        new ArrayList<PorletDashBoard>();
    
    private static List<Category> listaCategoriasOferta =
        new ArrayList<Category>();
    
    private static List<PorletDashBoard> listaPorletsOferta = 
        new ArrayList<PorletDashBoard>();
    
    private static List<Category> listaCategoriasCalidad =
        new ArrayList<Category>();
    
    private static List<PorletDashBoard> listaPorletsCalidad = 
        new ArrayList<PorletDashBoard>();
    
    private static List<Category> listaCategoriasGestion =
        new ArrayList<Category>();
    
    private static List<PorletDashBoard> listaPorletsGestion = 
        new ArrayList<PorletDashBoard>();
    
    private static List<Category> listaCategoriasRiesgo =
        new ArrayList<Category>();
    
    private static List<PorletDashBoard> listaPorletsRiesgo = 
        new ArrayList<PorletDashBoard>();
    //termina eduin
   
    private static List<PorletDashBoard> listaPorletsAlerta = 
        new ArrayList<PorletDashBoard>();
    
    static{
        PorletDashBoard porlet1 = 
            new PorletDashBoard("Naturaleza de los Usuarios","estadisticas/naturalezaUsuarios.jsff", "Cantidad de Usuarios según su naturaleza", true);    
      //  PorletDashBoard porlet2 = 
    //        new PorletDashBoard("Uso de Agua por Actividades","estadisticas/usoAguaXActividad.jsff", "Uso de Agua por Actividades", true);    
        PorletDashBoard porlet3 = 
            new PorletDashBoard("Ubicación de Usuarios","estadisticas/ubicacionUsuarios.jsff", "Ubicación de Usuarios", true);    
        PorletDashBoard porlet4 = 
            new PorletDashBoard("Estado de Vigencia de Concesiones","estadisticas/vigenciaConcesiones.jsff", "Cantidad de Concesiones vigentes o vencidas", false);    
        PorletDashBoard porlet5 = 
            new PorletDashBoard("Estado de Vigencia Permisos de Vertimiento","estadisticas/vigenciaPermisosVertimiento.jsff", "Cantidad de Permisos de Vertimiento vigentes o vencidos", false);    
        PorletDashBoard porlet6 = 
            new PorletDashBoard("Captaciones por fuente","estadisticas/captacionesfuente.jsff", "Cantidad captaciones por fuente hidrica", false);     
        PorletDashBoard porlet7 = 
            new PorletDashBoard("Usos del agua","estadisticas/usosAgua.jsff", "Usos del agua", false);    
        PorletDashBoard porlet11= 
            new PorletDashBoard("Caudales Concesionados","estadisticas/caudalesConcesionados.jsff", "Caudales Concesionados", false);    
        PorletDashBoard porlet8 = 
            new PorletDashBoard("Número de Muestras de Calidad ","estadisticas/muestrasPorTipo.jsff", "Número de Muestras de Calidad", false);    
        PorletDashBoard porlet9 = 
            new PorletDashBoard("Muestras de Calidad Por Año","estadisticas/muestrasPorAnio.jsff", "Muestras de Calidad Por Año", false);    
       
        PorletDashBoard porlet10 = 
            new PorletDashBoard("Estadisticas Por Parametros de Calidad","estadisticas/enlacesParametros.jsff", "Estadisticas Por Parametros de Calidad", false);    

        PorletDashBoard porlet12 = 
            new PorletDashBoard("Predios por Departamento","estadisticas/prediosPorDepartamento.jsff", "Predios por departamento", false);    
   
        PorletDashBoard porlet13 = 
            new PorletDashBoard("Captaciones por tipo de fuente","estadisticas/CaptacionTipoFuente.jsff", "Predios por departamento", false);    

        /* HUGO C*/
         PorletDashBoard porlet14 = 
             new PorletDashBoard("Cumplimiento por Programa","estadisticas/cumplimientoPorPrograma.jsff", "Cumplimiento por Programa", false);    

        PorletDashBoard porlet15 = 
             new PorletDashBoard("Ejecucion por Programa","estadisticas/ejecucionPorPrograma.jsff", "Ejecucion por Programa", false);    
        /* HUGO C*/

        PorletDashBoard porlet16 = 
            new PorletDashBoard("Formacion de Especilistas","estadisticas/formacionEspecialistas.jsff", "Formacion de Especialistas en Estudios del Agua", false);    
        PorletDashBoard porlet17 = 
            new PorletDashBoard("Trabajo de Investigacion ","estadisticas/investigacionGestion.jsff", "Investigacion de Especialistas en Estudios del Agua", false);    
        PorletDashBoard porlet18 = 
            new PorletDashBoard("Gestion de Especialistas","estadisticas/gestionEspecialistas.jsff", "Gestion de Especialistas en Estudios del Agua", false);    

        /* ENERO 7 */
          PorletDashBoard porlet19 = 
             new PorletDashBoard("Estadisticas POMCA","pomca/enlacesParametros.jsff", "Estadisticas POMCA", false);    
        PorletDashBoard porlet20 = 
                   new PorletDashBoard("Busquedas","estadisticas/enlacesBusquedas.jsff", "Búsquedas", false);    
        
        PorletDashBoard porlet21 = 
            new PorletDashBoard("Vertimientos por fuente","estadisticas/vertimientosfuente.jsff", "Cantidad vertimientos por fuente hidrica ", false);    
        PorletDashBoard porlet22 = 
            new PorletDashBoard("Tipo de Vertimientos ","estadisticas/vertimientostipo.jsff", "Cantidad vertimientos por tipo", false);    
        
        PorletDashBoard porlet23= 
            new PorletDashBoard("Caudales Vertidos","estadisticas/caudalesVertidos.jsff", "Caudales Vertidos", false);    
        
        PorletDashBoard porlet24= 
            new PorletDashBoard("Estadisticas por Tramites","estadisticas/enlacesTramites.jsff", "Estadisticas por Tramites", false);    
        
        PorletDashBoard porlet25 = 
            new PorletDashBoard("Analisis de la oferta","", "Análisis de la oferta", false);    
        PorletDashBoard porlet26 =
            new PorletDashBoard("Alertas", "dashBoard/enlacesAlertas.jsff",
                                      "Alertas", false);
        
        
        
        /* Se publicará en el 2015
         * PorletDashBoard porlet27 = 
            new PorletDashBoard("Datos referencia demanda","demanda/enlacesDemanda.jsff", "Datos referencia demanda", false);    
         */
        
        /* lis */
        
        listaPorletsTotal.add(porlet1);
        //listaPorletsTotal.add(porlet2);
        listaPorletsTotal.add(porlet3);
        listaPorletsTotal.add(porlet4);
        listaPorletsTotal.add(porlet5);
        listaPorletsTotal.add(porlet6);
        listaPorletsTotal.add(porlet7);
        
        listaPorletsTotal.add(porlet8);
        listaPorletsTotal.add(porlet9);
        listaPorletsTotal.add(porlet10);
        listaPorletsTotal.add(porlet11);
        listaPorletsTotal.add(porlet12);
        listaPorletsTotal.add(porlet13);

        /* HUGO C*/
        listaPorletsTotal.add(porlet14);
        listaPorletsTotal.add(porlet15);
        /* HUGO C*/

        listaPorletsTotal.add(porlet16);
        listaPorletsTotal.add(porlet17);
        listaPorletsTotal.add(porlet18);
        
        listaPorletsTotal.add(porlet19);
        
        
        /*Lis*/
        listaPorletsTotal.add(porlet20);
        listaPorletsTotal.add(porlet21);
        listaPorletsTotal.add(porlet22);
        listaPorletsTotal.add(porlet23);
        listaPorletsTotal.add(porlet24);
        listaPorletsTotal.add(porlet26);
        
        listaPorletsRiesgo.add(porlet26);

        listaPorletsTotal.add(porlet25);
        
 
        //listaPorletsTotal.add(porlet27);
        
        
        
        PorletDashBoard porlet28 = 
            new PorletDashBoard("Red Monitoreo Ideam","calidadIdeam/enlacesParametros.jsff", "Red Monitoreo Ideam", false);    

        listaPorletsTotal.add(porlet28);
        //TERMINA EDUIN ORTIZ
        
        // Crear las categorias
        Category categoria1 = new Category(" Fuentes y usuarios");
        categoria1.addPorlet(porlet1);
       // categoria1.addPorlet(porlet2);
        categoria1.addPorlet(porlet3);
        categoria1.addPorlet(porlet12);
        categoria1.addPorlet(porlet13);
        
        //OCGA
        PorletDashBoard porletOCGA1 = 
            new PorletDashBoard("Buenas Practicas por Departamento", null, null, "buenasPracticasPorDepartamento", true);
        listaPorletsTotal.add(porletOCGA1);
        categoria1.addPorlet(porletOCGA1);
        
        PorletDashBoard porletOCGA2 = 
            new PorletDashBoard("Buenas Practicas por promotores", null, null, "buenasPracticasPorPromotores", true);
        listaPorletsTotal.add(porletOCGA2);
        categoria1.addPorlet(porletOCGA2);
        
        PorletDashBoard porletOCGA3 = 
        new PorletDashBoard("Frecuencia de Principios en Buenas Practicas", null, null, "frecuenciaPrincipios", true);
        listaPorletsTotal.add(porletOCGA3);
        categoria1.addPorlet(porletOCGA3);
        
        PorletDashBoard porletOCGA4 = 
        new PorletDashBoard("Inversión de promotores para desarrollo de buenas prácticas", null, null, "buenasPracticasPorInversion", true);
        listaPorletsTotal.add(porletOCGA4);
        categoria1.addPorlet(porletOCGA4);
        
        PorletDashBoard porletOCGA5 = 
        new PorletDashBoard("Principales logros de las buenas prácticas", null, null, "frecuenciaLogros", true);
        listaPorletsTotal.add(porletOCGA5);
        categoria1.addPorlet(porletOCGA5);
        
        PorletDashBoard porletOCGA6 = 
        new PorletDashBoard("Tipo de proyectos educativos desarrollados por la ejecución de la buena práctica", null, null, "buenasPracticasPorTipoProyecto", true);
        listaPorletsTotal.add(porletOCGA6);
        categoria1.addPorlet(porletOCGA6);
        
        PorletDashBoard porletOCGA7 = 
        new PorletDashBoard("Buenas Practicas por Categoria", null, null, "buenasPracticasPorCategoria", true);
        listaPorletsTotal.add(porletOCGA7);
        categoria1.addPorlet(porletOCGA7);
        
        PorletDashBoard porletOCGA8 = 
        new PorletDashBoard("Motivaciones", null, null, "frecuenciaMotivaciones", true);
        listaPorletsTotal.add(porletOCGA8);
        categoria1.addPorlet(porletOCGA8);
        
        PorletDashBoard porletOCGA9 = 
        new PorletDashBoard("Conflictos Por Departamento", null, null, "conflictosPorDepartamento", true);
        listaPorletsTotal.add(porletOCGA9);
        categoria1.addPorlet(porletOCGA9);
        
        PorletDashBoard porletOCGA10 = 
        new PorletDashBoard("Tipo Conflicto", null, null, "frecuenciaCFTipoConflicto", true);
        listaPorletsTotal.add(porletOCGA10);
        categoria1.addPorlet(porletOCGA10);
        
        PorletDashBoard porletOCGA11 = 
        new PorletDashBoard("Poblacion Afectada", null, null, "frecuenciaCFPoblacionAfectada", true);
        listaPorletsTotal.add(porletOCGA11);
        categoria1.addPorlet(porletOCGA11);
        
        PorletDashBoard porletOCGA12 = 
        new PorletDashBoard("Actores Conflicto", null, null, "frecuenciaCFActores", true);
        listaPorletsTotal.add(porletOCGA12);
        categoria1.addPorlet(porletOCGA12);
        
        PorletDashBoard porletOCGA13 = 
        new PorletDashBoard("Frecuencia Gestion de Conflicto", null, null, "frecuenciaCFGestion", true);
        listaPorletsTotal.add(porletOCGA13);
        categoria1.addPorlet(porletOCGA13);
        
        PorletDashBoard porletOCGA14 = 
        new PorletDashBoard("Frecuencia Subgestion conflicto", null, null, "frecuenciaCFSubgestion", true);
        listaPorletsTotal.add(porletOCGA14);
        categoria1.addPorlet(porletOCGA14);
        
        PorletDashBoard porletOCGA15 = 
        new PorletDashBoard("Conflictos Por Cuenca", null, null, "conflictosPorCuenca", true);
        listaPorletsTotal.add(porletOCGA15);
        categoria1.addPorlet(porletOCGA15);
        
        PorletDashBoard porletOCGA16 = 
        new PorletDashBoard("Conflictos Por Subzona", null, null, "conflictosPorSubzona", true);
        listaPorletsTotal.add(porletOCGA16);
        categoria1.addPorlet(porletOCGA16);
        //OCGA
        
        getListaCategorias().add(categoria1);
        
        Category categoria2 = new Category("Actos Administrativos");
        categoria2.addPorlet(porlet4);
        categoria2.addPorlet(porlet5);
        categoria2.addPorlet(porlet24);
        getListaCategorias().add(categoria2);
        
        Category categoria3 = new Category("Captaciones");
        categoria3.addPorlet(porlet6);
        categoria3.addPorlet(porlet7);
        categoria3.addPorlet(porlet11);
        getListaCategorias().add(categoria3);
        
        Category categoria9 = new Category("Vertimientos");
        categoria9.addPorlet(porlet21);
        categoria9.addPorlet(porlet22);
        categoria9.addPorlet(porlet23);
        getListaCategorias().add(categoria9);
        
        Category categoria5 = new Category("Calidad");
        
        categoria5.addPorlet(porlet8);
        categoria5.addPorlet(porlet9);
        categoria5.addPorlet(porlet10); 
        categoria5.addPorlet(porlet28); 
        getListaCategorias().add(categoria5);
		
		Category categoria8 = new Category("Búsquedas");
        categoria8.addPorlet(porlet20);
        getListaCategorias().add(categoria8);
        
        /* ENERO 7  comentado por lisbeth
       
       
       Category categoria6 = new Category("Pomca");
        
       categoria6.addPorlet(porlet19);
       getListaCategorias().add(categoria6);
        
        
         Category categoria7 = new Category("Actores");
         categoria7.addPorlet(porlet16);
        categoria7.addPorlet(porlet17);
        categoria7.addPorlet(porlet18);
        getListaCategorias().add(categoria7);
        
        
        
        Category categoriaOferta = new Category("Oferta");
        categoriaOferta.addPorlet(porlet25);
        getListaCategorias().add(categoriaOferta);
        
        
        Category categoriaDemanda = new Category("Datos Referencia");
        categoriaDemanda.addPorlet(porlet27);
        
          getListaCategorias().add(categoriaDemanda);
          getListaCategoriasDemanda().add(categoria8);
          getListaCategoriasGestion().add(categoria6);
          getListaCategoriasGestion().add(categoria7);
       
       
       
          Category categoriaAlertas = new Category("Alertas");
          categoriaAlertas.addPorlet(porlet26);
          getListaCategorias().add(categoriaAlertas);
          getListaCategoriasRiesgo().add(categoriaAlertas);
        
        ENERO 7 --- comentado por lisbeth */
     
        getListaCategoriasDemanda().add(categoria8);
        getListaCategoriasDemanda().add(categoria1);
        getListaCategoriasDemanda().add(categoria2);
        getListaCategoriasDemanda().add(categoria3);
        getListaCategoriasDemanda().add(categoria9);
        //getListaCategoriasDemanda().add(categoriaDemanda);
        
        getListaCategoriasCalidad().add(categoria5);
        
    
        //FIN EDUIN ORTIZ

    
    }
    
    public PorletList() {
        super();
    }

    public void addPorlet(PorletDashBoard porlet){
        if(listaPorletsTotal!=null){
            listaPorletsTotal.add(porlet);
        }
    }
    public static List getListaPorlets() {
        //System.out.println("PORLETS en PorletList: "+listaPorletsTotal);
        return listaPorletsTotal;
    }

    public static List<Category> getListaCategorias() {
        return listaCategorias;
    }


    public static void setListaCategoriasDemanda(List<Category> listaCategoriasDemanda) {
        PorletList.listaCategoriasDemanda = listaCategoriasDemanda;
    }

    public static List<Category> getListaCategoriasDemanda() {
        return listaCategoriasDemanda;
    }

    public static void setListaPorletsDemanda(List<PorletDashBoard> listaPorletsDemanda) {
        PorletList.listaPorletsDemanda = listaPorletsDemanda;
    }

    public static List<PorletDashBoard> getListaPorletsDemanda() {
        return listaPorletsDemanda;
    }

    public static void setListaCategoriasOferta(List<Category> listaCategoriasOferta) {
        PorletList.listaCategoriasOferta = listaCategoriasOferta;
    }

    public static List<Category> getListaCategoriasOferta() {
        return listaCategoriasOferta;
    }

    public static void setListaPorletsOferta(List<PorletDashBoard> listaPorletsOferta) {
        PorletList.listaPorletsOferta = listaPorletsOferta;
    }

    public static List<PorletDashBoard> getListaPorletsOferta() {
        return listaPorletsOferta;
    }

    public static void setListaCategoriasCalidad(List<Category> listaCategoriasCalidad) {
        PorletList.listaCategoriasCalidad = listaCategoriasCalidad;
    }

    public static List<Category> getListaCategoriasCalidad() {
        return listaCategoriasCalidad;
    }

    public static void setListaPorletsCalidad(List<PorletDashBoard> listaPorletsCalidad) {
        PorletList.listaPorletsCalidad = listaPorletsCalidad;
    }

    public static List<PorletDashBoard> getListaPorletsCalidad() {
        return listaPorletsCalidad;
    }

    public static void setListaCategoriasGestion(List<Category> listaCategoriasGestion) {
        PorletList.listaCategoriasGestion = listaCategoriasGestion;
    }

    public static List<Category> getListaCategoriasGestion() {
        return listaCategoriasGestion;
    }

    public static void setListaPorletsGestion(List<PorletDashBoard> listaPorletsGestion) {
        PorletList.listaPorletsGestion = listaPorletsGestion;
    }

    public static List<PorletDashBoard> getListaPorletsGestion() {
        return listaPorletsGestion;
    }

    public static void setListaCategoriasRiesgo(List<Category> listaCategoriasRiesgo) {
        PorletList.listaCategoriasRiesgo = listaCategoriasRiesgo;
    }

    public static List<Category> getListaCategoriasRiesgo() {
        return listaCategoriasRiesgo;
    }

    public static void setListaPorletsRiesgo(List<PorletDashBoard> listaPorletsRiesgo) {
        PorletList.listaPorletsRiesgo = listaPorletsRiesgo;
    }

    public static List<PorletDashBoard> getListaPorletsRiesgo() {
        return listaPorletsRiesgo;
    }
}
