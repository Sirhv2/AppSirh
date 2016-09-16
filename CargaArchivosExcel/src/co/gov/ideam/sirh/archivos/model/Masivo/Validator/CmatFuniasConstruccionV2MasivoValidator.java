package co.gov.ideam.sirh.archivos.model.Masivo.Validator;

import co.gov.ideam.sirh.archivos.model.AbstractValidator;
import co.gov.ideam.sirh.archivos.model.ColumnTO;

import co.gov.ideam.sirh.archivos.model.validator.Obligatorio;

import java.util.ArrayList;

public class CmatFuniasConstruccionV2MasivoValidator extends AbstractValidator {
    public CmatFuniasConstruccionV2MasivoValidator() {
        this.columnas = new ArrayList<ColumnTO>();

        ColumnTO idcontrolcargue = new ColumnTO("id control cargue", 0);
        idcontrolcargue.addValidator(new Obligatorio());
        this.columnas.add(idcontrolcargue);
        
        ColumnTO codigorechazo = new ColumnTO("codigo rechazo", 1);
        codigorechazo.addValidator(new Obligatorio());
        this.columnas.add(codigorechazo);
        
        ColumnTO IDregistro = new ColumnTO("ID registro", 2);
        IDregistro.addValidator(new Obligatorio());
        this.columnas.add(IDregistro);
        
        ColumnTO idptoaguasubte = new ColumnTO("id pto agua subte", 3);
        this.columnas.add(idptoaguasubte);
        
        ColumnTO fechaconstruccion = new ColumnTO("fecha construccion", 4);
        this.columnas.add(fechaconstruccion);
        
        ColumnTO diametrointerior = new ColumnTO("diametro interior", 5);
        this.columnas.add(diametrointerior);
        
        ColumnTO diametroexterior = new ColumnTO("diametro exterior", 6);
        this.columnas.add(diametroexterior);
        
        ColumnTO diametroperforacion = new ColumnTO("diametro perforacion", 7);
        this.columnas.add(diametroperforacion);
        
        ColumnTO materialrevestimiento =
            new ColumnTO("material revestimiento", 8);
        this.columnas.add(materialrevestimiento);
        
        ColumnTO metodoconstruccion = new ColumnTO("metodo construccion", 9);
        this.columnas.add(metodoconstruccion);
        
        ColumnTO compañiaperforadora =
            new ColumnTO("compañia perforadora", 10);
        this.columnas.add(compañiaperforadora);
        
        ColumnTO colmatado = new ColumnTO("colmatado", 11);
        this.columnas.add(colmatado);
        
        ColumnTO colapsado = new ColumnTO("colapsado", 12);
        this.columnas.add(colapsado);
        
        ColumnTO profundidadentubado =
            new ColumnTO("profundidad entubado", 13);
        this.columnas.add(profundidadentubado);
        
        ColumnTO profundidadperforacion =
            new ColumnTO("profundidad perforacion", 14);
        this.columnas.add(profundidadperforacion);
        
        ColumnTO cantidadgravilla = new ColumnTO("cantidad gravilla", 15);
        this.columnas.add(cantidadgravilla);
        
        ColumnTO nivelengravillado = new ColumnTO("nivel engravillado", 16);
        this.columnas.add(nivelengravillado);
        
        ColumnTO diametropromedio = new ColumnTO("diametro promedio", 17);
        this.columnas.add(diametropromedio);
        
        ColumnTO embalse = new ColumnTO("embalse", 18);
        this.columnas.add(embalse);
        
        ColumnTO capacidadembalse = new ColumnTO("capacidad embalse", 19);
        this.columnas.add(capacidadembalse);
        
        ColumnTO tanque = new ColumnTO("tanque", 20);
        this.columnas.add(tanque);
        
        ColumnTO capacidadtanque = new ColumnTO("capacidad tanque", 21);
        this.columnas.add(capacidadtanque);
        
        ColumnTO alberca = new ColumnTO("alberca", 22);
        this.columnas.add(alberca);
        
        ColumnTO capacidadalberca = new ColumnTO("capacidad alberca", 23);
        this.columnas.add(capacidadalberca);
        
        ColumnTO otraconstruccion = new ColumnTO("otra construccion", 24);
        this.columnas.add(otraconstruccion);
        
        ColumnTO capacidadotraconstr =
            new ColumnTO("capacidad otra constr", 25);
        this.columnas.add(capacidadotraconstr);

    }
}
