package co.gov.ideam.sirh.archivos.model.Masivo.Validator;

import co.gov.ideam.sirh.archivos.model.AbstractValidator;
import co.gov.ideam.sirh.archivos.model.ColumnTO;

import java.util.ArrayList;

public class CmatPersonaNaturalCaV2MasivoValidator extends AbstractValidator {
    public CmatPersonaNaturalCaV2MasivoValidator() {
        this.columnas = new ArrayList<ColumnTO>();
        ColumnTO idcontrolcargue = new ColumnTO("Id Control Cargue", 0);
        this.columnas.add(idcontrolcargue);
        ColumnTO codigorechazo = new ColumnTO("Codigo Rechazo", 1);
        this.columnas.add(codigorechazo);
        ColumnTO IDregistro = new ColumnTO("Id Registro", 2);
        this.columnas.add(IDregistro);
        ColumnTO tipousuario = new ColumnTO("Tipo Usuario", 3);
        this.columnas.add(tipousuario);
        ColumnTO nombre = new ColumnTO("Nombre", 4);
        this.columnas.add(nombre);
        ColumnTO apellido = new ColumnTO("Apellido", 5);
        this.columnas.add(apellido);
        ColumnTO tipoidentificacion = new ColumnTO("Tipo Identificacion", 6);
        this.columnas.add(tipoidentificacion);
        ColumnTO numidentificacion = new ColumnTO("Num Identificacion", 7);
        this.columnas.add(numidentificacion);
        ColumnTO deptocorrespondencia =
            new ColumnTO("Depto Correspondencia", 8);
        this.columnas.add(deptocorrespondencia);
        ColumnTO municipiocorrespondencia =
            new ColumnTO("Municipio Correspondencia", 9);
        this.columnas.add(municipiocorrespondencia);
        ColumnTO dircorrespondencia = new ColumnTO("Dir Correspondencia", 10);
        this.columnas.add(dircorrespondencia);
        ColumnTO EMAIL = new ColumnTO("E Mail", 11);
        this.columnas.add(EMAIL);
        ColumnTO telefono = new ColumnTO("Telefono", 12);
        this.columnas.add(telefono);
        ColumnTO fax = new ColumnTO("Fax", 13);
        this.columnas.add(fax);
        ColumnTO nombrepredio = new ColumnTO("Nombre Predio", 14);
        this.columnas.add(nombrepredio);
        ColumnTO tipotenencia = new ColumnTO("Tipo Tenencia", 15);
        this.columnas.add(tipotenencia);
        ColumnTO departamentopredio = new ColumnTO("Departamento Predio", 16);
        this.columnas.add(departamentopredio);
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
        ColumnTO area = new ColumnTO("Area", 22);
        this.columnas.add(area);
        ColumnTO direccionpredio = new ColumnTO("Direccion Predio", 23);
        this.columnas.add(direccionpredio);
        ColumnTO clasificacionsuelo = new ColumnTO("Clasificacion Suelo", 24);
        this.columnas.add(clasificacionsuelo);
        ColumnTO sistemaref = new ColumnTO("Sistema Ref", 25);
        this.columnas.add(sistemaref);
        ColumnTO gradlatpredio = new ColumnTO("Grad Lat Predio", 26);
        this.columnas.add(gradlatpredio);
        ColumnTO minlatpredio = new ColumnTO("Min Lat  Predio", 27);
        this.columnas.add(minlatpredio);
        ColumnTO seglatpredio = new ColumnTO("Seg Lat Predio", 28);
        this.columnas.add(seglatpredio);
        ColumnTO gradlogpredio = new ColumnTO("Grad Log Predio", 29);
        this.columnas.add(gradlogpredio);
        ColumnTO minlogpredio = new ColumnTO("Min Log Predio", 30);
        this.columnas.add(minlogpredio);
        ColumnTO seglogpredio = new ColumnTO("Seg Log Predio", 31);
        this.columnas.add(seglogpredio);
        ColumnTO altitudpredio = new ColumnTO("Altitud Predio", 32);
        this.columnas.add(altitudpredio);
        ColumnTO observaciones = new ColumnTO("Observaciones", 33);
        this.columnas.add(observaciones);
        ColumnTO numexpediente = new ColumnTO("Num Expediente", 34);
        this.columnas.add(numexpediente);
        ColumnTO numresolucion = new ColumnTO("Num Resolucion", 35);
        this.columnas.add(numresolucion);
        ColumnTO fechaexpedicion = new ColumnTO("Fecha Expedicion", 36);
        this.columnas.add(fechaexpedicion);
        ColumnTO fechanotificacion = new ColumnTO("Fecha Notificacion", 37);
        this.columnas.add(fechanotificacion);
        ColumnTO caudalconcesionado = new ColumnTO("Caudal Concesionado", 38);
        this.columnas.add(caudalconcesionado);
        ColumnTO caudalconctempseca =
            new ColumnTO("Caudal Conc Temp Seca", 39);
        this.columnas.add(caudalconctempseca);
        ColumnTO caudalconctemphum = new ColumnTO("Caudal Conc Temp Hum", 40);
        this.columnas.add(caudalconctemphum);
        ColumnTO regimenhdiatempseca =
            new ColumnTO("Regimen H Dia Temp Seca", 41);
        this.columnas.add(regimenhdiatempseca);
        ColumnTO regimenhdiatemphum =
            new ColumnTO("Regimen H Dia Temp Hum", 42);
        this.columnas.add(regimenhdiatemphum);
        ColumnTO numresolucionaprob = new ColumnTO("Num Resolucion Aprob", 43);
        this.columnas.add(numresolucionaprob);
        ColumnTO fechaexpedicionaprob =
            new ColumnTO("Fecha Expedicion Aprob", 44);
        this.columnas.add(fechaexpedicionaprob);
        ColumnTO fechanotifiaprobplanos =
            new ColumnTO("Fecha Notifi Aprob Planos", 45);
        this.columnas.add(fechanotifiaprobplanos);
        ColumnTO numresolucionobras = new ColumnTO("Num Resolucion Obras", 46);
        this.columnas.add(numresolucionobras);
        ColumnTO fechaexpedicionobras =
            new ColumnTO("Fecha Expedicion Obras", 47);
        this.columnas.add(fechaexpedicionobras);
        ColumnTO fechanotificacionobras =
            new ColumnTO("Fecha Notificacion Obras", 48);
        this.columnas.add(fechanotificacionobras);
        ColumnTO vigenciadesde = new ColumnTO("Vigencia Desde", 49);
        this.columnas.add(vigenciadesde);
        ColumnTO vigenciahasta = new ColumnTO("Vigencia Hasta", 50);
        this.columnas.add(vigenciahasta);
        ColumnTO tipofuente = new ColumnTO("Tipo Fuente", 51);
        this.columnas.add(tipofuente);
        ColumnTO departamentocap = new ColumnTO("Departamento Cap", 52);
        this.columnas.add(departamentocap);
        ColumnTO municipiocap = new ColumnTO("Municipio Cap", 53);
        this.columnas.add(municipiocap);
        ColumnTO tipocentropobladocap =
            new ColumnTO("Tipo Centro Poblado Cap", 54);
        this.columnas.add(tipocentropobladocap);
        ColumnTO nombrecentropobladocap =
            new ColumnTO("Nombre Centro Poblado Cap", 55);
        this.columnas.add(nombrecentropobladocap);
        ColumnTO areacap = new ColumnTO("Area Cap", 56);
        this.columnas.add(areacap);
        ColumnTO zonacap = new ColumnTO("Zona Cap", 57);
        this.columnas.add(zonacap);
        ColumnTO subzonacap = new ColumnTO("Subzona Cap", 58);
        this.columnas.add(subzonacap);
        ColumnTO tipofuentecap = new ColumnTO("Tipo Fuente Cap", 59);
        this.columnas.add(tipofuentecap);
        ColumnTO fuentecap = new ColumnTO("Fuente Cap", 60);
        this.columnas.add(fuentecap);
        ColumnTO tramocap = new ColumnTO("Tramo Cap", 61);
        this.columnas.add(tramocap);
        ColumnTO aduccion = new ColumnTO("Aduccion", 62);
        this.columnas.add(aduccion);
        ColumnTO desarenador = new ColumnTO("Desarenador", 63);
        this.columnas.add(desarenador);
        ColumnTO ptap = new ColumnTO("Ptap", 64);
        this.columnas.add(ptap);
        ColumnTO reddistribucion = new ColumnTO("Red Distribucion", 65);
        this.columnas.add(reddistribucion);
        ColumnTO tanque = new ColumnTO("Tanque", 66);
        this.columnas.add(tanque);
        ColumnTO ofertahidrica = new ColumnTO("Oferta Hidrica", 67);
        this.columnas.add(ofertahidrica);
        ColumnTO ofertadisponible = new ColumnTO("Oferta Disponible", 68);
        this.columnas.add(ofertadisponible);
        ColumnTO observacion = new ColumnTO("Observacion", 69);
        this.columnas.add(observacion);
        ColumnTO tipocaptacion = new ColumnTO("Tipo Captacion", 70);
        this.columnas.add(tipocaptacion);
        ColumnTO areacaptacion = new ColumnTO("Area Captacion", 71);
        this.columnas.add(areacaptacion);
        ColumnTO medicion = new ColumnTO("Medicion", 72);
        this.columnas.add(medicion);
        ColumnTO estadocaptacion = new ColumnTO("Estado Captacion", 73);
        this.columnas.add(estadocaptacion);
        ColumnTO continuidadservicio =
            new ColumnTO("Continuidad Servicio", 74);
        this.columnas.add(continuidadservicio);
        ColumnTO servidumbre = new ColumnTO("Servidumbre", 75);
        this.columnas.add(servidumbre);
        ColumnTO caudaldiseñocapmedidoVARCHA =
            new ColumnTO("Caudal Diseño Cap Medidovarcha", 76);
        this.columnas.add(caudaldiseñocapmedidoVARCHA);
        ColumnTO sistemarefcap = new ColumnTO("Sistema Ref Cap", 77);
        this.columnas.add(sistemarefcap);
        ColumnTO gradlatcap = new ColumnTO("Grad Lat Cap", 78);
        this.columnas.add(gradlatcap);
        ColumnTO minlatcap = new ColumnTO("Min Lat Cap", 79);
        this.columnas.add(minlatcap);
        ColumnTO seglatcap = new ColumnTO("Seg Lat Cap", 80);
        this.columnas.add(seglatcap);
        ColumnTO gradlogcap = new ColumnTO("Grad Log Cap", 81);
        this.columnas.add(gradlogcap);
        ColumnTO minlogcap = new ColumnTO("Min Log Cap", 82);
        this.columnas.add(minlogcap);
        ColumnTO seglogcap = new ColumnTO("Seg Log Cap", 83);
        this.columnas.add(seglogcap);
        ColumnTO altitudcap = new ColumnTO("Altitud Cap", 84);
        this.columnas.add(altitudcap);
        ColumnTO descripcionsitiocap =
            new ColumnTO("Descripcion Sitio Cap", 85);
        this.columnas.add(descripcionsitiocap);
        ColumnTO caudalconsumohumano =
            new ColumnTO("Caudal Consumo Humano", 86);
        this.columnas.add(caudalconsumohumano);
        ColumnTO numpersonaspermanentes =
            new ColumnTO("Num Personas Permanentes", 87);
        this.columnas.add(numpersonaspermanentes);
        ColumnTO numpersonastransitorias =
            new ColumnTO("Num Personas Transitorias", 88);
        this.columnas.add(numpersonastransitorias);
        ColumnTO aprovechamiento = new ColumnTO("Aprovechamiento", 89);
        this.columnas.add(aprovechamiento);
        ColumnTO tipoanimalabrevaderos =
            new ColumnTO("Tipo Animal Abrevaderos", 90);
        this.columnas.add(tipoanimalabrevaderos);
        ColumnTO caudalabrevaderos = new ColumnTO("Caudal Abrevaderos", 91);
        this.columnas.add(caudalabrevaderos);
        ColumnTO numanimalesabrevaderos =
            new ColumnTO("Num Animales Abrevaderos", 92);
        this.columnas.add(numanimalesabrevaderos);
        ColumnTO tipoanimalpesca = new ColumnTO("Tipo Animal Pesca", 93);
        this.columnas.add(tipoanimalpesca);
        ColumnTO caudalpesca = new ColumnTO("Caudal Pesca", 94);
        this.columnas.add(caudalpesca);
        ColumnTO numanimalespesca = new ColumnTO("Num Animales Pesca", 95);
        this.columnas.add(numanimalespesca);
        ColumnTO tipocultivosilvicultura =
            new ColumnTO("Tipo Cultivo Silvicultura", 96);
        this.columnas.add(tipocultivosilvicultura);
        ColumnTO caudalsilvicultura = new ColumnTO("Caudal Silvicultura", 97);
        this.columnas.add(caudalsilvicultura);
        ColumnTO produccionsilvicultura =
            new ColumnTO("Produccion Silvicultura", 98);
        this.columnas.add(produccionsilvicultura);
        ColumnTO eficienciasilvicultura =
            new ColumnTO("Eficiencia Silvicultura", 99);
        this.columnas.add(eficienciasilvicultura);
        ColumnTO areasilvicultura = new ColumnTO("Area Silvicultura", 100);
        this.columnas.add(areasilvicultura);
        ColumnTO tipousootros = new ColumnTO("Tipo Uso Otros", 101);
        this.columnas.add(tipousootros);
        ColumnTO descripcionotros = new ColumnTO("Descripcion Otros", 102);
        this.columnas.add(descripcionotros);
        ColumnTO caudalotros = new ColumnTO("Caudal Otros", 103);
        this.columnas.add(caudalotros);
        ColumnTO nombrepuntocap = new ColumnTO("Nombre Punto Cap", 104);
        this.columnas.add(nombrepuntocap);
        ColumnTO nombrepuntoaguasarribacap =
            new ColumnTO("Nombre Punto Aguas Arriba Cap", 105);
        this.columnas.add(nombrepuntoaguasarribacap);
        ColumnTO nombrepuntoaguasabajocap =
            new ColumnTO("Nombre Punto Aguas Abajo Cap", 106);
        this.columnas.add(nombrepuntoaguasabajocap);

    }
}
