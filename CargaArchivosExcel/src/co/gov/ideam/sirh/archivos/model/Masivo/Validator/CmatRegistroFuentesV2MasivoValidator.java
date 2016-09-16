package co.gov.ideam.sirh.archivos.model.Masivo.Validator;

import co.gov.ideam.sirh.archivos.model.AbstractValidator;
import co.gov.ideam.sirh.archivos.model.ColumnTO;

import java.util.ArrayList;

public class CmatRegistroFuentesV2MasivoValidator extends AbstractValidator {
    public CmatRegistroFuentesV2MasivoValidator() {
        this.columnas = new ArrayList<ColumnTO>();
        ColumnTO idcontrolcargue = new ColumnTO("Id Control Cargue", 0);
        this.columnas.add(idcontrolcargue);
        ColumnTO codigorechazo = new ColumnTO("Codigo Rechazo", 1);
        this.columnas.add(codigorechazo);
        ColumnTO IDregistro = new ColumnTO("Id Registro", 2);
        this.columnas.add(IDregistro);
        ColumnTO idfuente = new ColumnTO("Id Fuente", 3);
        this.columnas.add(idfuente);
        ColumnTO tipo = new ColumnTO("Tipo", 4);
        this.columnas.add(tipo);
        ColumnTO nombrefuente = new ColumnTO("Nombre Fuente", 5);
        this.columnas.add(nombrefuente);
        ColumnTO fuentecompartida = new ColumnTO("Fuente Compartida", 6);
        this.columnas.add(fuentecompartida);
        ColumnTO codigocuenca = new ColumnTO("Codigo Cuenca", 7);
        this.columnas.add(codigocuenca);
        ColumnTO descripcionfuente = new ColumnTO("Descripcion Fuente", 8);
        this.columnas.add(descripcionfuente);
        ColumnTO nombretramo = new ColumnTO("Nombre Tramo", 9);
        this.columnas.add(nombretramo);
        ColumnTO descripciontramo = new ColumnTO("Descripcion Tramo", 10);
        this.columnas.add(descripciontramo);
        ColumnTO longitudtramo = new ColumnTO("Longitud Tramo", 11);
        this.columnas.add(longitudtramo);
        ColumnTO tipoflujo = new ColumnTO("Tipo Flujo", 12);
        this.columnas.add(tipoflujo);
        ColumnTO area = new ColumnTO("Area", 13);
        this.columnas.add(area);
        ColumnTO zona = new ColumnTO("Zona", 14);
        this.columnas.add(zona);
        ColumnTO subzona = new ColumnTO("Subzona", 15);
        this.columnas.add(subzona);
        ColumnTO ofertahidricaañomedio =
            new ColumnTO("Oferta Hidrica Año Medio", 16);
        this.columnas.add(ofertahidricaañomedio);
        ColumnTO observaciones = new ColumnTO("Observaciones", 17);
        this.columnas.add(observaciones);
        ColumnTO sistemarefpi = new ColumnTO("Sistema Ref Pi", 18);
        this.columnas.add(sistemarefpi);
        ColumnTO gradlatpi = new ColumnTO("Grad Lat Pi", 19);
        this.columnas.add(gradlatpi);
        ColumnTO minlatpi = new ColumnTO("Min Lat Pi", 20);
        this.columnas.add(minlatpi);
        ColumnTO seglatpi = new ColumnTO("Seg Lat Pi", 21);
        this.columnas.add(seglatpi);
        ColumnTO gradlogpi = new ColumnTO("Grad Log Pi", 22);
        this.columnas.add(gradlogpi);
        ColumnTO minlogpi = new ColumnTO("Min Log Pi", 23);
        this.columnas.add(minlogpi);
        ColumnTO seglogpi = new ColumnTO("Seg Log Pi", 24);
        this.columnas.add(seglogpi);
        ColumnTO altitudpi = new ColumnTO("Altitud Pi", 25);
        this.columnas.add(altitudpi);
        ColumnTO sistemarefpf = new ColumnTO("Sistema Ref Pf", 26);
        this.columnas.add(sistemarefpf);
        ColumnTO gradlatpf = new ColumnTO("Grad Lat Pf", 27);
        this.columnas.add(gradlatpf);
        ColumnTO minlatpf = new ColumnTO("Min Lat Pf", 28);
        this.columnas.add(minlatpf);
        ColumnTO seglatpf = new ColumnTO("Seg Lat Pf", 29);
        this.columnas.add(seglatpf);
        ColumnTO gradlogpf = new ColumnTO("Grad Log Pf", 30);
        this.columnas.add(gradlogpf);
        ColumnTO minlogpf = new ColumnTO("Min Log Pf", 31);
        this.columnas.add(minlogpf);
        ColumnTO seglogpf = new ColumnTO("Seg Log Pf", 32);
        this.columnas.add(seglogpf);
        ColumnTO altitudpf = new ColumnTO("Altitud Pf", 33);
        this.columnas.add(altitudpf);
        ColumnTO departamento = new ColumnTO("Departamento", 34);
        this.columnas.add(departamento);
        ColumnTO municipio = new ColumnTO("Municipio", 35);
        this.columnas.add(municipio);

    }
}
