package co.gov.ideam.sirh.pueaa.business;

import co.gov.ideam.sirh.pueaa.model.PartCategorias;
import co.gov.ideam.sirh.pueaa.model.PartListas;
import co.gov.ideam.sirh.pueaa.model.PueatAdmon;
import co.gov.ideam.sirh.pueaa.model.PueatProyectoConcesiones;
import co.gov.ideam.sirh.pueaa.model.PueatProyectos;
import co.gov.ideam.sirh.pueaa.model.PueatPuea;
import co.gov.ideam.sirh.pueaa.model.PueatSeguimiento;

import co.gov.ideam.sirh.usuarios.agua.model.UsuarioAgua;
import co.gov.ideam.sirh.util.IdeamException;

import java.util.List;

import javax.ejb.Remote;

import javax.persistence.Query;

@Remote
public interface PueaaEJB {
    Object queryByRange(String jpqlStmt, int firstResult, int maxResults);
    
    PueatAdmon persistPueatAdmon(PueatAdmon pueatAdmon);

    PueatAdmon mergePueatAdmon(PueatAdmon pueatAdmon);
    
    List<PueatAdmon> getPueatProyectoConcesionesFindAll(PueatAdmon pueatAdmon);
    
    PueatSeguimiento persistPueatSeguimiento(PueatSeguimiento pueatSeguimiento);

    PueatSeguimiento mergePueatSeguimiento(PueatSeguimiento pueatSeguimiento);

    void removePueatSeguimiento(PueatSeguimiento pueatSeguimiento);

    List<PueatSeguimiento> getPueatSeguimientoFindAll();

    PueatProyectoConcesiones persistPueatProyectoConcesiones(PueatProyectoConcesiones pueatProyectoConcesiones);

    PueatProyectoConcesiones mergePueatProyectoConcesiones(PueatProyectoConcesiones pueatProyectoConcesiones);

    void removePueatProyectoConcesiones(PueatProyectoConcesiones pueatProyectoConcesiones);

    List<PueatProyectoConcesiones> getPueatProyectoConcesionesFindAll(PueatProyectos pueaaPryCreated);

    PueatProyectos persistPueatProyectos(PueatProyectos pueatProyectos);

    PueatProyectos mergePueatProyectos(PueatProyectos pueatProyectos);

    void removePueatProyectos(PueatProyectos pueatProyectos);

    List<PueatProyectos> getPueatProyectosFindAll();
    
    List<PueatProyectos> getPueatProyectosFindByPueaa(PueatPuea pueatPuea);

    PartCategorias persistPartCategorias(PartCategorias partCategorias);

    PartCategorias mergePartCategorias(PartCategorias partCategorias);

    void removePartCategorias(PartCategorias partCategorias);

    List<PartCategorias> getPartCategoriasFindAll();

    PueatPuea persistPueatPuea(PueatPuea pueatPuea);

    PueatPuea mergePueatPuea(PueatPuea pueatPuea);
    
    PueatProyectos mergePueaPry(PueatProyectos pueaaPry);

    void removePueatPuea(PueatPuea pueatPuea);

    List<PueatPuea> getPueatPueaFindAll(UsuarioAgua usuarioAgua);

    PartListas persistPartListas(PartListas partListas);

    PartListas mergePartListas(PartListas partListas);

    void removePartListas(PartListas partListas);

    List<PartListas> getPartListasFindAll();
    
    public List<PueatSeguimiento> getSeguimientosProyecto(PueatProyectos proyecto) throws IdeamException;
}
