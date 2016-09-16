package co.gov.ideam.sirh.archivos.model.Masivo.Validator;

import co.gov.ideam.sirh.archivos.model.AbstractValidator;
import co.gov.ideam.sirh.archivos.model.ColumnTO;

import java.util.ArrayList;

public class CmatSubteMedicionCalV2MasivoValidator extends AbstractValidator{
    public CmatSubteMedicionCalV2MasivoValidator() {
        this.columnas = new ArrayList<ColumnTO>();      
        ColumnTO idcontrolcargue= new ColumnTO ("Id Control Cargue",0); this.columnas.add(idcontrolcargue);
        ColumnTO codigorechazo= new ColumnTO ("Codigo Rechazo",1);      this.columnas.add(codigorechazo);
        ColumnTO IDregistro= new ColumnTO ("Id Registro",2);    this.columnas.add(IDregistro);
        ColumnTO idmuestra= new ColumnTO ("Id Muestra",3);      this.columnas.add(idmuestra);
        ColumnTO idpntoaguasubte= new ColumnTO ("Id Pnto Agua Subte",4);        this.columnas.add(idpntoaguasubte);
        ColumnTO descripcion= new ColumnTO ("Descripcion",5);   this.columnas.add(descripcion);
        ColumnTO laboratorio= new ColumnTO ("Laboratorio",6);   this.columnas.add(laboratorio);
        ColumnTO otrolaboratorio= new ColumnTO ("Otro Laboratorio",7);  this.columnas.add(otrolaboratorio);
        ColumnTO metodomuestreo= new ColumnTO ("Metodo Muestreo",8);    this.columnas.add(metodomuestreo);
        ColumnTO lugarmuestreo= new ColumnTO ("Lugar Muestreo",9);      this.columnas.add(lugarmuestreo);
        ColumnTO otrolugar= new ColumnTO ("Otro Lugar",10);     this.columnas.add(otrolugar);
        ColumnTO fecha= new ColumnTO ("Fecha",11);      this.columnas.add(fecha);
        ColumnTO hora= new ColumnTO ("Hora",12);        this.columnas.add(hora);
        ColumnTO miuto= new ColumnTO ("Miuto",13);      this.columnas.add(miuto);
        ColumnTO horario= new ColumnTO ("Horario",14);  this.columnas.add(horario);
        ColumnTO parametro= new ColumnTO ("Parametro",15);      this.columnas.add(parametro);
        ColumnTO signo= new ColumnTO ("Signo",16);      this.columnas.add(signo);
        ColumnTO unidadmedida= new ColumnTO ("Unidad Medida",17);       this.columnas.add(unidadmedida);
        ColumnTO valor= new ColumnTO ("Valor",18);      this.columnas.add(valor);
        ColumnTO valor2= new ColumnTO ("Valor 2",19);   this.columnas.add(valor2);
        ColumnTO valorunico= new ColumnTO ("Valor Unico",20);   this.columnas.add(valorunico);
        ColumnTO metododeterminacion= new ColumnTO ("Metodo Determinacion",21); this.columnas.add(metododeterminacion);
        ColumnTO limitedeteccion= new ColumnTO ("Limite Deteccion",22); this.columnas.add(limitedeteccion);
        ColumnTO parametroacreditado= new ColumnTO ("Parametro Acreditado",23); this.columnas.add(parametroacreditado);

    }
}
