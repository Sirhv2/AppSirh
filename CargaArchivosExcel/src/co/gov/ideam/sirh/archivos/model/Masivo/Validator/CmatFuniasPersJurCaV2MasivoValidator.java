package co.gov.ideam.sirh.archivos.model.Masivo.Validator;

import co.gov.ideam.sirh.archivos.model.AbstractValidator;
import co.gov.ideam.sirh.archivos.model.ColumnTO;

import java.util.ArrayList;

public class CmatFuniasPersJurCaV2MasivoValidator extends AbstractValidator {
    public CmatFuniasPersJurCaV2MasivoValidator() {
        this.columnas = new ArrayList<ColumnTO>();
        ColumnTO idcontrolcargue = new ColumnTO("Id Control Cargue", 0);
        this.columnas.add(idcontrolcargue);
        ColumnTO codigorechazo = new ColumnTO("Codigo Rechazo", 1);
        this.columnas.add(codigorechazo);
        ColumnTO IDregistro = new ColumnTO("Id Registro", 2);
        this.columnas.add(IDregistro);
        ColumnTO tipousuario = new ColumnTO("Tipo Usuario", 3);
        this.columnas.add(tipousuario);
        ColumnTO razonsocial = new ColumnTO("Razon Social", 4);
        this.columnas.add(razonsocial);
        ColumnTO tipodocumento = new ColumnTO("Tipo Documento", 5);
        this.columnas.add(tipodocumento);
        ColumnTO numdocumento = new ColumnTO("Num Documento", 6);
        this.columnas.add(numdocumento);
        ColumnTO actividadeconomica = new ColumnTO("Actividad Economica", 7);
        this.columnas.add(actividadeconomica);
        ColumnTO dircorrespondencia = new ColumnTO("Dir Correspondencia", 8);
        this.columnas.add(dircorrespondencia);
        ColumnTO email = new ColumnTO("E Mail", 9);
        this.columnas.add(email);
        ColumnTO telefono = new ColumnTO("Telefono", 10);
        this.columnas.add(telefono);
        ColumnTO fax = new ColumnTO("Fax", 11);
        this.columnas.add(fax);
        ColumnTO observacionesusuario =
            new ColumnTO("Observaciones Usuario", 12);
        this.columnas.add(observacionesusuario);
        ColumnTO nombrerpstelegal = new ColumnTO("Nombre Rpste Legal", 13);
        this.columnas.add(nombrerpstelegal);
        ColumnTO apellidorpstelegal = new ColumnTO("Apellido Rpste Legal", 14);
        this.columnas.add(apellidorpstelegal);
        ColumnTO tipodocrpstelegal = new ColumnTO("Tipo Doc Rpste Legal", 15);
        this.columnas.add(tipodocrpstelegal);
        ColumnTO numdocrpstelegal = new ColumnTO("Num Doc Rpste Legal", 16);
        this.columnas.add(numdocrpstelegal);
        ColumnTO deptorpstelegal = new ColumnTO("Depto Rpste Legal", 17);
        this.columnas.add(deptorpstelegal);
        ColumnTO municipiorpstelegal =
            new ColumnTO("Municipio Rpste Legal", 18);
        this.columnas.add(municipiorpstelegal);
        ColumnTO dirrpstelegal = new ColumnTO("Dir Rpste Legal", 19);
        this.columnas.add(dirrpstelegal);
        ColumnTO telefonorpstelegal = new ColumnTO("Telefono Rpste Legal", 20);
        this.columnas.add(telefonorpstelegal);
        ColumnTO nombresitio = new ColumnTO("Nombre Sitio", 21);
        this.columnas.add(nombresitio);
        ColumnTO tipotenencia = new ColumnTO("Tipo Tenencia", 22);
        this.columnas.add(tipotenencia);
        ColumnTO departamentopredio = new ColumnTO("Departamento Predio", 23);
        this.columnas.add(departamentopredio);
        ColumnTO municipiopredio = new ColumnTO("Municipio Predio", 24);
        this.columnas.add(municipiopredio);
        ColumnTO tipocentropoblado = new ColumnTO("Tipo Centro Poblado", 25);
        this.columnas.add(tipocentropoblado);
        ColumnTO nombrecentropoblado =
            new ColumnTO("Nombre Centro Poblado", 26);
        this.columnas.add(nombrecentropoblado);
        ColumnTO cedulacatastral = new ColumnTO("Cedula Catastral", 27);
        this.columnas.add(cedulacatastral);
        ColumnTO matriculainmobiliaria =
            new ColumnTO("Matricula Inmobiliaria", 28);
        this.columnas.add(matriculainmobiliaria);
        ColumnTO area = new ColumnTO("Area", 29);
        this.columnas.add(area);
        ColumnTO direccionpredio = new ColumnTO("Direccion Predio", 30);
        this.columnas.add(direccionpredio);
        ColumnTO caudalconcemp = new ColumnTO("Caudal Conc Emp", 31);
        this.columnas.add(caudalconcemp);
        ColumnTO clasificacionsuelo = new ColumnTO("Clasificacion Suelo", 32);
        this.columnas.add(clasificacionsuelo);
        ColumnTO sistemaref = new ColumnTO("Sistema Ref", 33);
        this.columnas.add(sistemaref);
        ColumnTO gradlatpredio = new ColumnTO("Grad Lat Predio", 34);
        this.columnas.add(gradlatpredio);
        ColumnTO minlatpredio = new ColumnTO("Min Lat  Predio", 35);
        this.columnas.add(minlatpredio);
        ColumnTO seglatpredio = new ColumnTO("Seg Lat Predio", 36);
        this.columnas.add(seglatpredio);
        ColumnTO gradlogpredio = new ColumnTO("Grad Log Predio", 37);
        this.columnas.add(gradlogpredio);
        ColumnTO minlogpredio = new ColumnTO("Min Log Predio", 38);
        this.columnas.add(minlogpredio);
        ColumnTO seglogpredio = new ColumnTO("Seg Log Predio", 39);
        this.columnas.add(seglogpredio);
        ColumnTO altitudpredio = new ColumnTO("Altitud Predio", 40);
        this.columnas.add(altitudpredio);
        ColumnTO descripcionsitiogeo =
            new ColumnTO("Descripcion Sitio Geo", 41);
        this.columnas.add(descripcionsitiogeo);
        ColumnTO numexpediente = new ColumnTO("Num Expediente", 42);
        this.columnas.add(numexpediente);
        ColumnTO numresolucion = new ColumnTO("Num Resolucion", 43);
        this.columnas.add(numresolucion);
        ColumnTO fechaexpedicion = new ColumnTO("Fecha Expedicion", 44);
        this.columnas.add(fechaexpedicion);
        ColumnTO tipocaudalconcesionado =
            new ColumnTO("Tipo Caudal Concesionado", 45);
        this.columnas.add(tipocaudalconcesionado);
        ColumnTO caudalconcesionado = new ColumnTO("Caudal Concesionado", 46);
        this.columnas.add(caudalconcesionado);
        ColumnTO fechanotificacion = new ColumnTO("Fecha Notificacion", 47);
        this.columnas.add(fechanotificacion);
        ColumnTO caudalconctempseca =
            new ColumnTO("Caudal Conc Temp Seca", 48);
        this.columnas.add(caudalconctempseca);
        ColumnTO caudalconctemphum = new ColumnTO("Caudal Conc Temp Hum", 49);
        this.columnas.add(caudalconctemphum);
        ColumnTO regimenhdiatempseca =
            new ColumnTO("Regimen H Dia Temp Seca", 49);
        this.columnas.add(regimenhdiatempseca);
        ColumnTO regimenhdiatemphum =
            new ColumnTO("Regimen H Dia Temp Hum", 49);
        this.columnas.add(regimenhdiatemphum);
        ColumnTO numresolucionaprob = new ColumnTO("Num Resolucion Aprob", 49);
        this.columnas.add(numresolucionaprob);
        ColumnTO fechaexpedicionaprob =
            new ColumnTO("Fecha Expedicion Aprob", 49);
        this.columnas.add(fechaexpedicionaprob);
        ColumnTO fechanotifiaprobplanos =
            new ColumnTO("Fecha Notifi Aprob Planos", 49);
        this.columnas.add(fechanotifiaprobplanos);
        ColumnTO numresolucionobras = new ColumnTO("Num Resolucion Obras", 49);
        this.columnas.add(numresolucionobras);
        ColumnTO fechaexpedicionobras =
            new ColumnTO("Fecha Expedicion Obras", 49);
        this.columnas.add(fechaexpedicionobras);
        ColumnTO fechanotificacionobras =
            new ColumnTO("Fecha Notificacion Obras", 49);
        this.columnas.add(fechanotificacionobras);
        ColumnTO vigenciadesde = new ColumnTO("Vigencia Desde", 49);
        this.columnas.add(vigenciadesde);
        ColumnTO vigenciahasta = new ColumnTO("Vigencia Hasta", 49);
        this.columnas.add(vigenciahasta);
        ColumnTO tipofuente = new ColumnTO("Tipo Fuente", 49);
        this.columnas.add(tipofuente);
        ColumnTO departamentocap = new ColumnTO("Departamento Cap", 49);
        this.columnas.add(departamentocap);
        ColumnTO municipiocap = new ColumnTO("Municipio Cap", 49);
        this.columnas.add(municipiocap);
        ColumnTO tipocentropobladocap =
            new ColumnTO("Tipo Centro Poblado Cap", 49);
        this.columnas.add(tipocentropobladocap);
        ColumnTO nombrecentropobladocap =
            new ColumnTO("Nombre Centro Poblado Cap", 49);
        this.columnas.add(nombrecentropobladocap);
        ColumnTO areacap = new ColumnTO("Area Cap", 49);
        this.columnas.add(areacap);
        ColumnTO zonacap = new ColumnTO("Zona Cap", 49);
        this.columnas.add(zonacap);
        ColumnTO subzonacap = new ColumnTO("Subzona Cap", 49);
        this.columnas.add(subzonacap);
        ColumnTO tipofuentecap = new ColumnTO("Tipo Fuente Cap", 49);
        this.columnas.add(tipofuentecap);
        ColumnTO fuentecap = new ColumnTO("Fuente Cap", 49);
        this.columnas.add(fuentecap);
        ColumnTO tramocap = new ColumnTO("Tramo Cap", 49);
        this.columnas.add(tramocap);
        ColumnTO idptoaguasubterranea =
            new ColumnTO("Id Pto Agua Subterranea", 49);
        this.columnas.add(idptoaguasubterranea);
        ColumnTO nombreptoaa = new ColumnTO("Nombre Pto Aa", 49);
        this.columnas.add(nombreptoaa);
        ColumnTO provinciacap = new ColumnTO("Provincia Cap", 49);
        this.columnas.add(provinciacap);
        ColumnTO sistemaacuifero = new ColumnTO("Sistema Acuifero", 49);
        this.columnas.add(sistemaacuifero);
        ColumnTO otrosistemaacuifero =
            new ColumnTO("Otro Sistema Acuifero", 49);
        this.columnas.add(otrosistemaacuifero);
        ColumnTO unidahidro = new ColumnTO("Unida Hidro", 49);
        this.columnas.add(unidahidro);
        ColumnTO otrasunidadeshidro = new ColumnTO("Otras Unidades Hidro", 49);
        this.columnas.add(otrasunidadeshidro);
        ColumnTO tipoptoaguasubte = new ColumnTO("Tipo Pto Agua Subte", 49);
        this.columnas.add(tipoptoaguasubte);
        ColumnTO tipomanantial = new ColumnTO("Tipo Manantial", 49);
        this.columnas.add(tipomanantial);
        ColumnTO otrotipomanantial = new ColumnTO("Otro Tipo Manantial", 49);
        this.columnas.add(otrotipomanantial);
        ColumnTO permanenciamanantial =
            new ColumnTO("Permanencia Manantial", 49);
        this.columnas.add(permanenciamanantial);
        ColumnTO mediosurgenciamanantial =
            new ColumnTO("Medio Surgencia Manantial", 49);
        this.columnas.add(mediosurgenciamanantial);
        ColumnTO otrasurgenciamanantial =
            new ColumnTO("Otra Surgencia Manantial", 49);
        this.columnas.add(otrasurgenciamanantial);
        ColumnTO condicionpto = new ColumnTO("Condicion Pto", 49);
        this.columnas.add(condicionpto);
        ColumnTO otracondicion = new ColumnTO("Otra Condicion", 49);
        this.columnas.add(otracondicion);
        ColumnTO haceparteredmonregasub =
            new ColumnTO("Hace Parte Red Mon Reg Asub", 49);
        this.columnas.add(haceparteredmonregasub);
        ColumnTO haceparteredbasicanac =
            new ColumnTO("Hace Parte Red Basica Nac", 49);
        this.columnas.add(haceparteredbasicanac);
        ColumnTO aduccion = new ColumnTO("Aduccion", 49);
        this.columnas.add(aduccion);
        ColumnTO desarenador = new ColumnTO("Desarenador", 49);
        this.columnas.add(desarenador);
        ColumnTO ptap = new ColumnTO("Ptap", 49);
        this.columnas.add(ptap);
        ColumnTO reddistribucion = new ColumnTO("Red Distribucion", 49);
        this.columnas.add(reddistribucion);
        ColumnTO tanque = new ColumnTO("Tanque", 49);
        this.columnas.add(tanque);
        ColumnTO ofertahidrica = new ColumnTO("Oferta Hidrica", 49);
        this.columnas.add(ofertahidrica);
        ColumnTO ofertatotal = new ColumnTO("Oferta Total", 49);
        this.columnas.add(ofertatotal);
        ColumnTO ofertadisponible = new ColumnTO("Oferta Disponible", 49);
        this.columnas.add(ofertadisponible);
        ColumnTO observacion = new ColumnTO("Observacion", 49);
        this.columnas.add(observacion);
        ColumnTO tipocaptacion = new ColumnTO("Tipo Captacion", 49);
        this.columnas.add(tipocaptacion);
        ColumnTO areacaptacion = new ColumnTO("Area Captacion", 49);
        this.columnas.add(areacaptacion);
        ColumnTO medicion = new ColumnTO("Medicion", 49);
        this.columnas.add(medicion);
        ColumnTO estadocaptacion = new ColumnTO("Estado Captacion", 49);
        this.columnas.add(estadocaptacion);
        ColumnTO continuidadservicio =
            new ColumnTO("Continuidad Servicio", 49);
        this.columnas.add(continuidadservicio);
        ColumnTO servidumbre = new ColumnTO("Servidumbre", 49);
        this.columnas.add(servidumbre);
        ColumnTO caudaldiseñocapmedido =
            new ColumnTO("Caudal Diseño Cap Medido", 49);
        this.columnas.add(caudaldiseñocapmedido);
        ColumnTO sistemarefcap = new ColumnTO("Sistema Ref Cap", 49);
        this.columnas.add(sistemarefcap);
        ColumnTO gradlatcap = new ColumnTO("Grad Lat Cap", 49);
        this.columnas.add(gradlatcap);
        ColumnTO minlatcap = new ColumnTO("Min Lat Cap", 49);
        this.columnas.add(minlatcap);
        ColumnTO seglatcap = new ColumnTO("Seg Lat Cap", 49);
        this.columnas.add(seglatcap);
        ColumnTO gradlogcap = new ColumnTO("Grad Log Cap", 49);
        this.columnas.add(gradlogcap);
        ColumnTO minlogcap = new ColumnTO("Min Log Cap", 49);
        this.columnas.add(minlogcap);
        ColumnTO seglogcap = new ColumnTO("Seg Log Cap", 49);
        this.columnas.add(seglogcap);
        ColumnTO cotaterrenoptoaguasub =
            new ColumnTO("Cota Terreno Pto Agua Sub", 49);
        this.columnas.add(cotaterrenoptoaguasub);
        ColumnTO metodomedidacota = new ColumnTO("Metodo Medida Cota", 49);
        this.columnas.add(metodomedidacota);
        ColumnTO descripcionsitiocap =
            new ColumnTO("Descripcion Sitio Cap", 49);
        this.columnas.add(descripcionsitiocap);
        ColumnTO caudalconsumohumano =
            new ColumnTO("Caudal Consumo Humano", 49);
        this.columnas.add(caudalconsumohumano);
        ColumnTO numpersonaspermanentes =
            new ColumnTO("Num Personas Permanentes", 49);
        this.columnas.add(numpersonaspermanentes);
        ColumnTO numpersonastransitorias =
            new ColumnTO("Num Personas Transitorias", 49);
        this.columnas.add(numpersonastransitorias);
        ColumnTO aprovechamiento = new ColumnTO("Aprovechamiento", 49);
        this.columnas.add(aprovechamiento);
        ColumnTO tipoanimalabrevaderos =
            new ColumnTO("Tipo Animal Abrevaderos", 49);
        this.columnas.add(tipoanimalabrevaderos);
        ColumnTO caudalabrevaderos = new ColumnTO("Caudal Abrevaderos", 49);
        this.columnas.add(caudalabrevaderos);
        ColumnTO numanimalesabrevaderos =
            new ColumnTO("Num Animales Abrevaderos", 49);
        this.columnas.add(numanimalesabrevaderos);
        ColumnTO tipoanimalpesca = new ColumnTO("Tipo Animal Pesca", 49);
        this.columnas.add(tipoanimalpesca);
        ColumnTO caudalpesca = new ColumnTO("Caudal Pesca", 49);
        this.columnas.add(caudalpesca);
        ColumnTO numanimalespesca = new ColumnTO("Num Animales Pesca", 49);
        this.columnas.add(numanimalespesca);
        ColumnTO tipocultivosilvicultura =
            new ColumnTO("Tipo Cultivo Silvicultura", 49);
        this.columnas.add(tipocultivosilvicultura);
        ColumnTO caudalsilvicultura = new ColumnTO("Caudal Silvicultura", 49);
        this.columnas.add(caudalsilvicultura);
        ColumnTO produccionsilvicultura =
            new ColumnTO("Produccion Silvicultura", 49);
        this.columnas.add(produccionsilvicultura);
        ColumnTO eficienciasilvicultura =
            new ColumnTO("Eficiencia Silvicultura", 49);
        this.columnas.add(eficienciasilvicultura);
        ColumnTO areasilvicultura = new ColumnTO("Area Silvicultura", 49);
        this.columnas.add(areasilvicultura);
        ColumnTO tipousootros = new ColumnTO("Tipo Uso Otros", 49);
        this.columnas.add(tipousootros);
        ColumnTO descripcionotros = new ColumnTO("Descripcion Otros", 49);
        this.columnas.add(descripcionotros);
        ColumnTO caudalotros = new ColumnTO("Caudal Otros", 49);
        this.columnas.add(caudalotros);

    }
}
