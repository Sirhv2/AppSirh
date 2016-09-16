package co.gov.ideam.sirh.archivos.model.Masivo.Validator;

import co.gov.ideam.sirh.archivos.model.AbstractValidator;
import co.gov.ideam.sirh.archivos.model.ColumnTO;

import java.util.ArrayList;

public class CmatPersonaNaturalCaV1MasivoValidator extends AbstractValidator {
    public CmatPersonaNaturalCaV1MasivoValidator() {
        this.columnas = new ArrayList<ColumnTO>();
        ColumnTO idcontrolcargue = new ColumnTO("Id Control Cargue", 0);
        this.columnas.add(idcontrolcargue);
        ColumnTO codigorechazo = new ColumnTO("Codigo Rechazo", 1);
        this.columnas.add(codigorechazo);
        ColumnTO idregistro = new ColumnTO("Id Registro", 2);
        this.columnas.add(idregistro);
        ColumnTO tipopersona = new ColumnTO("Tipo Persona", 3);
        this.columnas.add(tipopersona);
        ColumnTO nombreusuario = new ColumnTO("Nombre Usuario", 4);
        this.columnas.add(nombreusuario);
        ColumnTO apellidousuario = new ColumnTO("Apellido Usuario", 5);
        this.columnas.add(apellidousuario);
        ColumnTO tipodoc = new ColumnTO("Tipo Doc", 6);
        this.columnas.add(tipodoc);
        ColumnTO numdoc = new ColumnTO("Num Doc", 7);
        this.columnas.add(numdoc);
        ColumnTO deptocorrespondencia =
            new ColumnTO("Depto Correspondencia", 8);
        this.columnas.add(deptocorrespondencia);
        ColumnTO municipiocorrespondencia =
            new ColumnTO("Municipio Correspondencia", 9);
        this.columnas.add(municipiocorrespondencia);
        ColumnTO direccioncorrespondencia =
            new ColumnTO("Direccion Correspondencia", 10);
        this.columnas.add(direccioncorrespondencia);
        ColumnTO email = new ColumnTO("E Mail", 11);
        this.columnas.add(email);
        ColumnTO tel = new ColumnTO("Tel", 12);
        this.columnas.add(tel);
        ColumnTO fax = new ColumnTO("Fax", 13);
        this.columnas.add(fax);
        ColumnTO nombrepredio = new ColumnTO("Nombre Predio", 14);
        this.columnas.add(nombrepredio);
        ColumnTO tipotenencia = new ColumnTO("Tipo Tenencia", 15);
        this.columnas.add(tipotenencia);
        ColumnTO deptopredio = new ColumnTO("Depto Predio", 16);
        this.columnas.add(deptopredio);
        ColumnTO municipiopredio = new ColumnTO("Municipio Predio", 17);
        this.columnas.add(municipiopredio);
        ColumnTO tipocentropoblado = new ColumnTO("Tipo Centro Poblado", 18);
        this.columnas.add(tipocentropoblado);
        ColumnTO nombrecentropoblado =
            new ColumnTO("Nombre Centro Poblado", 19);
        this.columnas.add(nombrecentropoblado);
        ColumnTO cedulacatastral = new ColumnTO("Cedula Catastral", 20);
        this.columnas.add(cedulacatastral);
        ColumnTO matriculainmobiliaria =
            new ColumnTO("Matricula Inmobiliaria", 21);
        this.columnas.add(matriculainmobiliaria);
        ColumnTO areatotalpredio = new ColumnTO("Area Total Predio", 22);
        this.columnas.add(areatotalpredio);
        ColumnTO direccionpredio = new ColumnTO("Direccion Predio", 23);
        this.columnas.add(direccionpredio);
        ColumnTO clasificacionsuelo = new ColumnTO("Clasificacion Suelo", 24);
        this.columnas.add(clasificacionsuelo);
        ColumnTO sistemaref = new ColumnTO("Sistema Ref", 25);
        this.columnas.add(sistemaref);
        ColumnTO gradlat = new ColumnTO("Grad Lat", 26);
        this.columnas.add(gradlat);
        ColumnTO minlat = new ColumnTO("Min Lat", 27);
        this.columnas.add(minlat);
        ColumnTO seglat = new ColumnTO("Seg Lat", 28);
        this.columnas.add(seglat);
        ColumnTO gradlog = new ColumnTO("Grad Log", 29);
        this.columnas.add(gradlog);
        ColumnTO minlog = new ColumnTO("Min Log", 30);
        this.columnas.add(minlog);
        ColumnTO seglog = new ColumnTO("Seg Log", 31);
        this.columnas.add(seglog);
        ColumnTO altitud = new ColumnTO("Altitud", 32);
        this.columnas.add(altitud);
        ColumnTO descripsitio = new ColumnTO("Descrip Sitio", 33);
        this.columnas.add(descripsitio);
        ColumnTO numexpediente = new ColumnTO("Num Expediente", 34);
        this.columnas.add(numexpediente);
        ColumnTO numresolucionasignacaudal =
            new ColumnTO("Num Resolucion Asigna Caudal", 35);
        this.columnas.add(numresolucionasignacaudal);
        ColumnTO fechaexperesolucion =
            new ColumnTO("Fecha Expe Resolucion", 36);
        this.columnas.add(fechaexperesolucion);
        ColumnTO fechadenotificacion =
            new ColumnTO("Fecha De Notificacion", 37);
        this.columnas.add(fechadenotificacion);
        ColumnTO caudalconcesionado = new ColumnTO("Caudal Concesionado", 38);
        this.columnas.add(caudalconcesionado);
        ColumnTO numresolucionaprobplanos =
            new ColumnTO("Num Resolucion Aprob Planos", 39);
        this.columnas.add(numresolucionaprobplanos);
        ColumnTO fechaexperesolaprobplanos =
            new ColumnTO("Fecha Expe Resol Aprob Planos", 40);
        this.columnas.add(fechaexperesolaprobplanos);
        ColumnTO fechanotifiaprobplanos =
            new ColumnTO("Fecha Notifi Aprob Planos", 41);
        this.columnas.add(fechanotifiaprobplanos);
        ColumnTO numresolucionaprobobra =
            new ColumnTO("Num Resolucion Aprob Obra", 42);
        this.columnas.add(numresolucionaprobobra);
        ColumnTO fechaexperesolaproobra =
            new ColumnTO("Fecha Expe Resol Apro Obra", 43);
        this.columnas.add(fechaexperesolaproobra);
        ColumnTO fechanotifiaprobobra =
            new ColumnTO("Fecha Notifi Aprob Obra", 44);
        this.columnas.add(fechanotifiaprobobra);
        ColumnTO vigenciadesde = new ColumnTO("Vigencia Desde", 45);
        this.columnas.add(vigenciadesde);
        ColumnTO vigenciahasta = new ColumnTO("Vigencia Hasta", 46);
        this.columnas.add(vigenciahasta);
        ColumnTO tipodefuente = new ColumnTO("Tipo De Fuente", 47);
        this.columnas.add(tipodefuente);
        ColumnTO areahidrográfica = new ColumnTO("Area Hidrográfica", 48);
        this.columnas.add(areahidrográfica);
        ColumnTO zonahidrográfica = new ColumnTO("Zona Hidrográfica", 49);
        this.columnas.add(zonahidrográfica);
        ColumnTO subzonahidrográfica =
            new ColumnTO("Subzona Hidrográfica", 50);
        this.columnas.add(subzonahidrográfica);
        ColumnTO tipo = new ColumnTO("Tipo", 51);
        this.columnas.add(tipo);
        ColumnTO fuente = new ColumnTO("Fuente", 52);
        this.columnas.add(fuente);
        ColumnTO tramo = new ColumnTO("Tramo", 53);
        this.columnas.add(tramo);
        ColumnTO identificadorpuntoaguassub =
            new ColumnTO("Identificador Punto Aguas Sub", 54);
        this.columnas.add(identificadorpuntoaguassub);
        ColumnTO provinciah = new ColumnTO("Provincia H", 55);
        this.columnas.add(provinciah);
        ColumnTO unidadh = new ColumnTO("Unidad H", 56);
        this.columnas.add(unidadh);
        ColumnTO aduccion = new ColumnTO("Aduccion", 57);
        this.columnas.add(aduccion);
        ColumnTO desarenador = new ColumnTO("Desarenador", 58);
        this.columnas.add(desarenador);
        ColumnTO ptap = new ColumnTO("Ptap", 59);
        this.columnas.add(ptap);
        ColumnTO reddistribucion = new ColumnTO("Red Distribucion", 60);
        this.columnas.add(reddistribucion);
        ColumnTO tanque = new ColumnTO("Tanque", 61);
        this.columnas.add(tanque);
        ColumnTO tipocaptacion = new ColumnTO("Tipo Captacion", 62);
        this.columnas.add(tipocaptacion);
        ColumnTO ofertatotal = new ColumnTO("Oferta Total", 63);
        this.columnas.add(ofertatotal);
        ColumnTO areacaptacion = new ColumnTO("Area Captacion", 64);
        this.columnas.add(areacaptacion);
        ColumnTO ofertadisponible = new ColumnTO("Oferta Disponible", 65);
        this.columnas.add(ofertadisponible);
        ColumnTO macromedicion = new ColumnTO("Macromedicion", 66);
        this.columnas.add(macromedicion);
        ColumnTO estadocap = new ColumnTO("Estado Cap", 67);
        this.columnas.add(estadocap);
        ColumnTO caudaldiseñocap = new ColumnTO("Caudal Diseño Cap", 68);
        this.columnas.add(caudaldiseñocap);
        ColumnTO continuidadservicio =
            new ColumnTO("Continuidad Servicio", 69);
        this.columnas.add(continuidadservicio);
        ColumnTO tieneservidumbre = new ColumnTO("Tiene Servidumbre", 70);
        this.columnas.add(tieneservidumbre);
        ColumnTO sistemarefcap = new ColumnTO("Sistema Ref Cap", 71);
        this.columnas.add(sistemarefcap);
        ColumnTO gradlatcap = new ColumnTO("Grad Lat Cap", 72);
        this.columnas.add(gradlatcap);
        ColumnTO minlatcap = new ColumnTO("Min Lat Cap", 73);
        this.columnas.add(minlatcap);
        ColumnTO seglatcap = new ColumnTO("Seg Lat Cap", 74);
        this.columnas.add(seglatcap);
        ColumnTO gradlogcap = new ColumnTO("Grad Log Cap", 75);
        this.columnas.add(gradlogcap);
        ColumnTO minlogcap = new ColumnTO("Min Log Cap", 76);
        this.columnas.add(minlogcap);
        ColumnTO seglogcap = new ColumnTO("Seg Log Cap", 77);
        this.columnas.add(seglogcap);
        ColumnTO altitudcap = new ColumnTO("Altitud Cap", 78);
        this.columnas.add(altitudcap);
        ColumnTO observacionescap = new ColumnTO("Observaciones Cap", 79);
        this.columnas.add(observacionescap);
        ColumnTO caudalconsumo = new ColumnTO("Caudal Consumo", 80);
        this.columnas.add(caudalconsumo);
        ColumnTO noperpermanentesconsumo =
            new ColumnTO("No Per Permanentes Consumo", 81);
        this.columnas.add(noperpermanentesconsumo);
        ColumnTO nopertransitoriasconsumo =
            new ColumnTO("No Per Transitorias Consumo", 82);
        this.columnas.add(nopertransitoriasconsumo);
        ColumnTO aprovechconsumo = new ColumnTO("Aprovech Consumo", 83);
        this.columnas.add(aprovechconsumo);
        ColumnTO tipoanimalabrev = new ColumnTO("Tipo Animal Abrev", 84);
        this.columnas.add(tipoanimalabrev);
        ColumnTO caudalabrev = new ColumnTO("Caudal  Abrev", 85);
        this.columnas.add(caudalabrev);
        ColumnTO noanimalesabrev = new ColumnTO("No Animales Abrev", 86);
        this.columnas.add(noanimalesabrev);
        ColumnTO tipoanimalpesca = new ColumnTO("Tipo Animal Pesca", 87);
        this.columnas.add(tipoanimalpesca);
        ColumnTO caudalpesca = new ColumnTO("Caudal  Pesca", 88);
        this.columnas.add(caudalpesca);
        ColumnTO noanimales = new ColumnTO("No Animales", 89);
        this.columnas.add(noanimales);
        ColumnTO tipocultivosilvicultura =
            new ColumnTO("Tipo Cultivo Silvicultura", 90);
        this.columnas.add(tipocultivosilvicultura);
        ColumnTO caudalsilvicultura = new ColumnTO("Caudal Silvicultura", 91);
        this.columnas.add(caudalsilvicultura);
        ColumnTO produccionsilvicultura =
            new ColumnTO("Produccion Silvicultura", 92);
        this.columnas.add(produccionsilvicultura);
        ColumnTO eficienciasilvicultura =
            new ColumnTO("Eficiencia Silvicultura", 93);
        this.columnas.add(eficienciasilvicultura);
        ColumnTO areasilvicultura = new ColumnTO("Area Silvicultura", 94);
        this.columnas.add(areasilvicultura);
        ColumnTO tipodeusootro = new ColumnTO("Tipo De Uso Otro", 95);
        this.columnas.add(tipodeusootro);
        ColumnTO decripcionotro = new ColumnTO("Decripcion Otro", 96);
        this.columnas.add(decripcionotro);
        ColumnTO caudalotro = new ColumnTO("Caudal Otro", 97);
        this.columnas.add(caudalotro);

    }
}
