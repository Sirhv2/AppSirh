package co.gov.ideam.sirh.pomca.view;

import co.gov.ideam.sirh.fuentes.model.FnttFuente;
import co.gov.ideam.sirh.pomca.model.Actividad;
import co.gov.ideam.sirh.pomca.model.Cuenca;
import co.gov.ideam.sirh.pomca.model.Indicador;
import co.gov.ideam.sirh.pomca.model.Pomca;
import co.gov.ideam.sirh.pomca.model.Programa;
import co.gov.ideam.sirh.pomca.model.Proyecto;

public class RegistroIndicador {

    private Cuenca cuenca;
    private Pomca pomca;
    private Programa programa;
    private Proyecto proyecto;
    private Actividad actividad;
    private Indicador indicador;
    private Long metaPeriodo;

    public RegistroIndicador() {
    }

    public RegistroIndicador(Cuenca cuenca, Pomca pomca, Programa programa,
                             Proyecto proyecto, Actividad actividad,
                             Indicador indicador, Long metaPeriodo) {
        super();
        this.cuenca = cuenca;
        this.pomca = pomca;
        this.programa = programa;
        this.proyecto = proyecto;
        this.actividad = actividad;
        this.indicador = indicador;
        this.metaPeriodo = metaPeriodo;
    }

    public void setCuenca(Cuenca cuenca) {
        this.cuenca = cuenca;
    }

    public Cuenca getCuenca() {
        return cuenca;
    }

    public void setPomca(Pomca pomca) {
        this.pomca = pomca;
    }

    public Pomca getPomca() {
        return pomca;
    }

    public void setPrograma(Programa programa) {
        this.programa = programa;
    }

    public Programa getPrograma() {
        return programa;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }

    public Actividad getActividad() {
        return actividad;
    }

    public void setIndicador(Indicador indicador) {
        this.indicador = indicador;
    }

    public Indicador getIndicador() {
        return indicador;
    }

    public void setMetaPeriodo(Long metaPeriodo) {
        this.metaPeriodo = metaPeriodo;
    }

    public Long getMetaPeriodo() {
        return metaPeriodo;
    }
}
