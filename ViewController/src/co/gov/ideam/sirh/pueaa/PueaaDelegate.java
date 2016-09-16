package co.gov.ideam.sirh.pueaa;


import co.gov.ideam.sirh.pueaa.business.PueaaEJB;
import co.gov.ideam.sirh.pueaa.model.PueatAdmon;
import co.gov.ideam.sirh.pueaa.model.PueatProyectoConcesiones;
import co.gov.ideam.sirh.pueaa.model.PueatProyectos;
import co.gov.ideam.sirh.pueaa.model.PueatPuea;
import co.gov.ideam.sirh.pueaa.model.PueatSeguimiento;
import co.gov.ideam.sirh.servlet.ServletLocator;
import co.gov.ideam.sirh.usuarios.agua.model.UsuarioAgua;
import co.gov.ideam.sirh.util.IdeamException;

import java.util.List;


/**
 * Class PueaaDelegate
 */
public class PueaaDelegate {

    /**
     * Referencia al EJB
     */
    private static PueaaEJB pueaaService = null;

    /**
     * Utilizada para implementar singleton
     */
    private static PueaaDelegate instance;

    /**
     * Retorna una instancia a la clase
     * @return
     */
    public static PueaaDelegate getInstance() throws IdeamException {
        if (instance == null) {
            instance = new PueaaDelegate();
        }
        return instance;
    }

    /**
     * Construcutor privado para implementar singleton
     */
    private PueaaDelegate() throws IdeamException {
        pueaaService = ServletLocator.getPueaaService();
        if (pueaaService == null)
            System.out.println("No existe PUEASERVICE ----");
    }

    /**
     * Metodo para registrar un programa de uso eficiente de ahorro de agua
     * @param pueaa
     * @return
     */
    public PueatPuea registrarPueaa(PueatPuea pueaa) throws IdeamException {
        return pueaaService.persistPueatPuea(pueaa);
    }

    /**
     * Metodo que actualiza el objeto pueaa en base de datos
     * @param pueatPuea
     * @return
     * @throws IdeamException
     */
    public PueatPuea mergePueatPuea(PueatPuea pueatPuea) throws IdeamException {
        return pueaaService.mergePueatPuea(pueatPuea);
    }


    /**
     * Metodo para registrar un proyecto de uso eficiente de ahorro de agua
     * @param pueaa
     * @return
     */
    public PueatProyectos registrarPueaaPry(PueatProyectos pueaaPry) throws IdeamException {
        return pueaaService.persistPueatProyectos(pueaaPry);
    }

    /**
     *  Registra las concesiones de los proyectos de pueaa x proyecto
     * @param
     * @return
     */
    public PueatProyectoConcesiones registrarPryConsecion(PueatProyectoConcesiones pryConcesion) throws IdeamException {
        return pueaaService.persistPueatProyectoConcesiones(pryConcesion);
    }

    /**
     * Metodo Consulta las concesiones de los proyectos de pueaa x proyecto
     * @return
     * @throws IdeamException
     */
    public List<PueatProyectoConcesiones> getPueatProyectoConcesionesFindAll(PueatProyectos pueaaPryCreated) throws IdeamException {
        return pueaaService.getPueatProyectoConcesionesFindAll(pueaaPryCreated);
    }

    /**
     * Metodo Borrar las concesiones de los proyectos de pueaa x proyecto
     * @return
     * @param pueatProyectoConcesiones
     * @throws IdeamException
     */
    public void removePueatProyectoConcesiones(PueatProyectoConcesiones pueatProyectoConcesiones) throws IdeamException {
         pueaaService.removePueatProyectoConcesiones(pueatProyectoConcesiones);
    }

    /**
     * Metodo que actualiza el objeto pueaa en base de datos
     * @param pueatPuea
     * @return
     * @throws IdeamException
     */
    public PueatProyectos mergePueaPry(PueatProyectos pueaaPry) throws IdeamException {
        return pueaaService.mergePueaPry(pueaaPry);
    }

    /**
     *Metodo que trae todos los pueaas filtrados por usuario del agua seleccionado
     * @param usuarioAgua
     * @return
     */
    public List<PueatPuea> getPueatPueaFindAll(UsuarioAgua usuarioAgua) {
        return pueaaService.getPueatPueaFindAll(usuarioAgua);

    }

    /**
     * Metodo que trae los proyectos de un pueaa seleccionado filtrado por usuario del agua
     * @param pueatPuea
     * @return
     */
    public List<PueatProyectos> getPueatProyectosFindByPueaa(PueatPuea pueatPuea) {
        return pueaaService.getPueatProyectosFindByPueaa(pueatPuea);
    }

    /**
     * Metodo para consultar los seguimientos de u proyecto
     * @param PueatProyectos
     * @return
     */
    public List<PueatSeguimiento> getSeguimientosProyecto(PueatProyectos proyecto) throws IdeamException {
        return pueaaService.getSeguimientosProyecto(proyecto);
    }

    /**
     * Metodo para consultar los seguimientos de u proyecto
     * @param PueatProyectos
     * @return
     */
    public PueatProyectos persistPueatProyectos(PueatProyectos pueatProyectos) throws IdeamException {
        return pueaaService.persistPueatProyectos(pueatProyectos);
    }
    
    /**
     * Metodo para persistir las preguntas de administracion de los pueaa
     * @param PueatProyectos
     * @return
     */
    public PueatAdmon persistPueatAdmon(PueatAdmon pueatAdmon) throws IdeamException {
        return pueaaService.persistPueatAdmon(pueatAdmon);
    }

}
