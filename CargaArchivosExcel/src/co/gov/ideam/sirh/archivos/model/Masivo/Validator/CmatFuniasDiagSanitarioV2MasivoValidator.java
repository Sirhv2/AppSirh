package co.gov.ideam.sirh.archivos.model.Masivo.Validator;

import co.gov.ideam.sirh.archivos.model.AbstractValidator;
import co.gov.ideam.sirh.archivos.model.ColumnTO;
import co.gov.ideam.sirh.archivos.model.ModelConverter;

import java.util.ArrayList;

public class CmatFuniasDiagSanitarioV2MasivoValidator extends AbstractValidator {
    public CmatFuniasDiagSanitarioV2MasivoValidator() {
        this.columnas = new ArrayList<ColumnTO>();
        
        ColumnTO idcontrolcargue = new ColumnTO("Id Control Cargue", 0);
        this.columnas.add(idcontrolcargue);
        
        ColumnTO codigorechazo = new ColumnTO("Codigo Rechazo", 1);
        this.columnas.add(codigorechazo);
        
        ColumnTO IDregistro = new ColumnTO("Id Registro", 2);
        this.columnas.add(IDregistro);
        
        ColumnTO idptoaguasubt = new ColumnTO("Id Pto Agua Subt", 3);
        this.columnas.add(idptoaguasubt);
        
        ColumnTO letrina = new ColumnTO("Letrina", 4);
        this.columnas.add(letrina);
        
        ColumnTO distancialetrina = new ColumnTO("Distancia Letrina", 5);
        this.columnas.add(distancialetrina);
        
        ColumnTO charco = new ColumnTO("Charco", 6);
        this.columnas.add(charco);
        
        ColumnTO distanciacharco = new ColumnTO("Distancia Charco", 7);
        this.columnas.add(distanciacharco);
        
        ColumnTO basura = new ColumnTO("Basura", 8);
        this.columnas.add(basura);
        
        ColumnTO distanciabasura = new ColumnTO("Distancia Basura", 9);
        this.columnas.add(distanciabasura);
        
        ColumnTO bordegrieta = new ColumnTO("Borde Grieta", 10);
        this.columnas.add(bordegrieta);
        
        ColumnTO distanciabordegrieta =
            new ColumnTO("Distancia Borde Grieta", 11);
        this.columnas.add(distanciabordegrieta);
        
        ColumnTO cubierta = new ColumnTO("Cubierta", 12);
        this.columnas.add(cubierta);
        
        ColumnTO pisocemento = new ColumnTO("Piso Cemento", 13);
        this.columnas.add(pisocemento);
        
        ColumnTO sellosanitario = new ColumnTO("Sello Sanitario", 14);
        this.columnas.add(sellosanitario);
        
        ColumnTO cercoadecuado = new ColumnTO("Cerco Adecuado", 15);
        this.columnas.add(cercoadecuado);
        
        ColumnTO campoinfiltracion = new ColumnTO("Campo Infiltracion", 16);
        this.columnas.add(campoinfiltracion);
        
        ColumnTO distanciacampoinfil =
            new ColumnTO("Distancia Campo Infil", 17);
        this.columnas.add(distanciacampoinfil);
        
        ColumnTO cementerio = new ColumnTO("Cementerio", 18);
        this.columnas.add(cementerio);
        
        ColumnTO distanciacementerio =
            new ColumnTO("Distancia Cementerio", 19);
        this.columnas.add(distanciacementerio);
        
        ColumnTO estacionservicio = new ColumnTO("Estacion Servicio", 20);
        this.columnas.add(estacionservicio);
        
        ColumnTO distacioneds = new ColumnTO("Distacion Eds", 21);
        this.columnas.add(distacioneds);
        
        ColumnTO lagunasoxi = new ColumnTO("Lagunas Oxi", 22);
        this.columnas.add(lagunasoxi);
        
        ColumnTO distancialagunas = new ColumnTO("Distancia Lagunas", 23);
        this.columnas.add(distancialagunas);
        
        ColumnTO lavaderosmotor = new ColumnTO("Lavaderos Motor", 24);
        this.columnas.add(lavaderosmotor);
        
        ColumnTO distancialavaderos = new ColumnTO("Distancia Lavaderos", 25);
        this.columnas.add(distancialavaderos);
        
        ColumnTO plantassacrificio = new ColumnTO("Plantas Sacrificio", 26);
        this.columnas.add(plantassacrificio);
        
        ColumnTO distanciaplantassacri =
            new ColumnTO("Distancia Plantas Sacri", 27);
        this.columnas.add(distanciaplantassacri);
        
        ColumnTO pozosabandonados = new ColumnTO("Pozos Abandonados", 28);
        this.columnas.add(pozosabandonados);
        
        ColumnTO distanciapozos = new ColumnTO("Distancia Pozos", 29);
        this.columnas.add(distanciapozos);
        
        ColumnTO agricola = new ColumnTO("Agricola", 30);
        this.columnas.add(agricola);
        
        ColumnTO domestico = new ColumnTO("Domestico", 31);
        this.columnas.add(domestico);
        
        ColumnTO ganaderia = new ColumnTO("Ganaderia", 32);
        this.columnas.add(ganaderia);
        
        ColumnTO hospitalario = new ColumnTO("Hospitalario", 33);
        this.columnas.add(hospitalario);
        
        ColumnTO industrial = new ColumnTO("Industrial", 34);
        this.columnas.add(industrial);
        
        ColumnTO minero = new ColumnTO("Minero", 35);
        this.columnas.add(minero);
        
        ColumnTO botaderocieloabierto =
            new ColumnTO("Botadero Cielo Abierto", 36);
        this.columnas.add(botaderocieloabierto);
        
        ColumnTO compostaje = new ColumnTO("Compostaje", 37);
        this.columnas.add(compostaje);
        
        ColumnTO incineracion = new ColumnTO("Incineracion", 38);
        this.columnas.add(incineracion);
        
        ColumnTO reciclaje = new ColumnTO("Reciclaje", 39);
        this.columnas.add(reciclaje);


    }
}
