package co.gov.ideam.sirh.archivos.model;

import co.gov.ideam.sirh.archivos.model.validator.ListasOpcionalValidator;
import co.gov.ideam.sirh.archivos.model.validator.ListasValidator;
import co.gov.ideam.sirh.archivos.model.validator.Obligatorio;
import co.gov.ideam.sirh.util.ConstantesParametros;

import java.util.ArrayList;

public class FuniasConstruccionesValidator extends AbstractValidator{
    public FuniasConstruccionesValidator() {
        this.columnas = new ArrayList<ColumnTO>();

        ColumnTO identificador = new ColumnTO("Identificador Punto", 0);
        identificador.addValidator(new Obligatorio());
        this.columnas.add( identificador );

        ColumnTO fecha = new ColumnTO("Fecha Construccion", 1);
        fecha.addValidator(new Obligatorio());
        this.columnas.add( fecha );
        
        ColumnTO diametroInt = new ColumnTO("Diametro Interior", 2);
        //diametroInt.addValidator(new Obligatorio());
        this.columnas.add( diametroInt );
        
        ColumnTO diametroExt = new ColumnTO("Diametro Exterior", 3);
        //diametroExt.addValidator(new Obligatorio());
        this.columnas.add( diametroExt );
        
        ColumnTO diametroPer = new ColumnTO("Diametro Perforacion", 4);
        //diametroPer.addValidator(new Obligatorio());
        this.columnas.add( diametroPer );
        
        ColumnTO profundidad = new ColumnTO("Profundidad Perforacion", 5);
        //profundidad.addValidator(new Obligatorio());
        this.columnas.add( profundidad );
        
        ColumnTO compania = new ColumnTO("Compania Perforadora", 6);
        //profundidad.addValidator(new Obligatorio());
        this.columnas.add( compania );
        
        //ColumnTO material = new ColumnTO("Material Construccion", 7);
        //material.addValidator(new Obligatorio());
        //this.columnas.add( material );
        
        ColumnTO metodo = new ColumnTO("Metodo Construccion", 8);
        metodo.addValidator( new ListasOpcionalValidator(ConstantesParametros.
                                                         CATEGORIA_MET_CONSTRUCC, 
                                                         "Metodo Construccion") );
        this.columnas.add( metodo );
        
        ColumnTO materialRev = new ColumnTO("Material Revestimento", 9);
        materialRev.addValidator( new ListasOpcionalValidator(ConstantesParametros.
                                                         CATEGORIA_MATERIAL_REVESTIM, 
                                                         "Material Revestimento") );
        this.columnas.add( materialRev );
        
        ColumnTO colmatado = new ColumnTO("Esta colmatado", 10);
        //profundidad.addValidator(new Obligatorio());
        this.columnas.add( colmatado );
        
        ColumnTO colapsado = new ColumnTO("Esta colapsado", 11);
        //profundidad.addValidator(new Obligatorio());
        this.columnas.add( colapsado );
        
        
    }
}
