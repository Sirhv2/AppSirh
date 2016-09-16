package co.gov.ideam.sirh.archivos.model.Masivo.Validator;

import co.gov.ideam.sirh.archivos.model.AbstractValidator;
import co.gov.ideam.sirh.archivos.model.ColumnTO;

import java.util.ArrayList;

public class CmatFuniasMedicionNvlsV2MasivoValidator extends AbstractValidator {
    public CmatFuniasMedicionNvlsV2MasivoValidator() {
        this.columnas = new ArrayList<ColumnTO>();
        ColumnTO idcontrolcargue = new ColumnTO("Id Control Cargue", 0);
        this.columnas.add(idcontrolcargue);
        ColumnTO codigorechazo = new ColumnTO("Codigo Rechazo", 1);
        this.columnas.add(codigorechazo);
        ColumnTO IDregistro = new ColumnTO("Id Registro", 2);
        this.columnas.add(IDregistro);
        ColumnTO idptoaguasubterranea =
            new ColumnTO("Id Pto Agua Subterranea", 3);
        this.columnas.add(idptoaguasubterranea);
        ColumnTO fechainiciomedicion =
            new ColumnTO("Fecha Inicio Medicion", 4);
        this.columnas.add(fechainiciomedicion);
        ColumnTO hora = new ColumnTO("Hora", 5);
        this.columnas.add(hora);
        ColumnTO minuto = new ColumnTO("Minuto", 6);
        this.columnas.add(minuto);
        ColumnTO horario = new ColumnTO("Horario", 7);
        this.columnas.add(horario);
        ColumnTO condclimaticamuestreo =
            new ColumnTO("Cond Climatica Muestreo", 8);
        this.columnas.add(condclimaticamuestreo);
        ColumnTO profundidadmedida = new ColumnTO("Profundidad Medida", 9);
        this.columnas.add(profundidadmedida);
        ColumnTO cotaterrenopto = new ColumnTO("Cota Terreno Pto", 10);
        this.columnas.add(cotaterrenopto);
        ColumnTO metodomedida = new ColumnTO("Metodo Medida", 11);
        this.columnas.add(metodomedida);
        ColumnTO lugarmedicionnvlagua =
            new ColumnTO("Lugar Medicion Nvl Agua", 12);
        this.columnas.add(lugarmedicionnvlagua);
        ColumnTO nivelmedido = new ColumnTO("Nivel Medido", 13);
        this.columnas.add(nivelmedido);
        ColumnTO tiponivel = new ColumnTO("Tipo Nivel", 14);
        this.columnas.add(tiponivel);
        ColumnTO tiempobombeo = new ColumnTO("Tiempo Bombeo", 15);
        this.columnas.add(tiempobombeo);
        ColumnTO tiempodesdeapagadobomba =
            new ColumnTO("Tiempo Desde Apagado Bomba", 16);
        this.columnas.add(tiempodesdeapagadobomba);
        ColumnTO metodomedidabombeo = new ColumnTO("Metodo Medida Bombeo", 17);
        this.columnas.add(metodomedidabombeo);
        ColumnTO observaciones = new ColumnTO("Observaciones", 18);
        this.columnas.add(observaciones);

    }
}
