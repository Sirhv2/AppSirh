package co.gov.ideam.sirh.archivos.model.Masivo.Validator;

import co.gov.ideam.sirh.archivos.model.AbstractValidator;
import co.gov.ideam.sirh.archivos.model.ColumnTO;

import java.util.ArrayList;

public class CmatPersonaNaturalPvV1MasivoValidator extends AbstractValidator {
    public CmatPersonaNaturalPvV1MasivoValidator() {
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
        ColumnTO tipodocusuario = new ColumnTO("Tipo Doc Usuario", 6);
        this.columnas.add(tipodocusuario);
        ColumnTO numdocusuario = new ColumnTO("Num Doc Usuario", 7);
        this.columnas.add(numdocusuario);
        ColumnTO deptousuario = new ColumnTO("Depto Usuario", 8);
        this.columnas.add(deptousuario);
        ColumnTO municipiousuario = new ColumnTO("Municipio Usuario", 9);
        this.columnas.add(municipiousuario);
        ColumnTO dircorrespondenciausuario =
            new ColumnTO("Dir Correspondencia Usuario", 10);
        this.columnas.add(dircorrespondenciausuario);
        ColumnTO emailusuario = new ColumnTO("E Mail Usuario", 11);
        this.columnas.add(emailusuario);
        ColumnTO telusuario = new ColumnTO("Tel Usuario", 12);
        this.columnas.add(telusuario);
        ColumnTO faxusuario = new ColumnTO("Fax Usuario", 13);
        this.columnas.add(faxusuario);
        ColumnTO nombrepredio = new ColumnTO("Nombre Predio", 14);
        this.columnas.add(nombrepredio);
        ColumnTO tipotenencia = new ColumnTO("Tipo Tenencia", 15);
        this.columnas.add(tipotenencia);
        ColumnTO deptopredio = new ColumnTO("Depto Predio", 16);
        this.columnas.add(deptopredio);
        ColumnTO municipiopredio = new ColumnTO("Municipio Predio", 17);
        this.columnas.add(municipiopredio);
        ColumnTO tipocentropobladopredio =
            new ColumnTO("Tipo Centro Poblado Predio", 18);
        this.columnas.add(tipocentropobladopredio);
        ColumnTO nombrecentropobladopredio =
            new ColumnTO("Nombre Centro Poblado Predio", 19);
        this.columnas.add(nombrecentropobladopredio);
        ColumnTO cedulacatastralpredio =
            new ColumnTO("Cedula Catastral Predio", 20);
        this.columnas.add(cedulacatastralpredio);
        ColumnTO matriculainmobiliariapredio =
            new ColumnTO("Matricula Inmobiliaria Predio", 21);
        this.columnas.add(matriculainmobiliariapredio);
        ColumnTO areatotaldelprediopredio =
            new ColumnTO("Area Total Del Predio Predio", 22);
        this.columnas.add(areatotaldelprediopredio);
        ColumnTO direcciondelpredio = new ColumnTO("Direccion Del Predio", 23);
        this.columnas.add(direcciondelpredio);
        ColumnTO clasificacionsuelo = new ColumnTO("Clasificacion Suelo", 24);
        this.columnas.add(clasificacionsuelo);
        ColumnTO numadmoniniciaautpv =
            new ColumnTO("Num Admon Inicia Aut Pv", 25);
        this.columnas.add(numadmoniniciaautpv);
        ColumnTO fechaexpediciónactoadmon =
            new ColumnTO("Fecha Expedición Acto Admon", 26);
        this.columnas.add(fechaexpediciónactoadmon);
        ColumnTO numexpediente = new ColumnTO("Num Expediente", 27);
        this.columnas.add(numexpediente);
        ColumnTO caudalautorizado = new ColumnTO("Caudal Autorizado ", 28);
        this.columnas.add(caudalautorizado);
        ColumnTO evaluaciónambientalpv =
            new ColumnTO("Evaluación Ambiental Pv", 29);
        this.columnas.add(evaluaciónambientalpv);
        ColumnTO numadmonsolicitaplancumpl =
            new ColumnTO("Num Admon Solicita Plan Cumpl", 30);
        this.columnas.add(numadmonsolicitaplancumpl);
        ColumnTO fechaexpeactoadmon =
            new ColumnTO("Fecha Expe Acto Admon", 31);
        this.columnas.add(fechaexpeactoadmon);
        ColumnTO numadmonaprubplancmplmint =
            new ColumnTO("Num Admon Aprub Plan Cmplmint", 32);
        this.columnas.add(numadmonaprubplancmplmint);
        ColumnTO fechaexpeactoadmonplcmo =
            new ColumnTO("Fecha Expe Acto Admon Plcmo", 33);
        this.columnas.add(fechaexpeactoadmonplcmo);
        ColumnTO fechanotifiaprobplncmplmnt =
            new ColumnTO("Fecha Notifi Aprob Pln Cmplmnt", 34);
        this.columnas.add(fechanotifiaprobplncmplmnt);
        ColumnTO vigenciainiplancmplimnt =
            new ColumnTO("Vigencia Ini Plan Cmplimnt", 35);
        this.columnas.add(vigenciainiplancmplimnt);
        ColumnTO vigenciafinplancmplmnt =
            new ColumnTO("Vigencia Fin Plan Cmplmnt", 36);
        this.columnas.add(vigenciafinplancmplmnt);
        ColumnTO numresolotorgapv = new ColumnTO("Num Resol Otorga Pv", 37);
        this.columnas.add(numresolotorgapv);
        ColumnTO fechaexpeactoadmonpv =
            new ColumnTO("Fecha Expe Acto Admon Pv", 38);
        this.columnas.add(fechaexpeactoadmonpv);
        ColumnTO vigenciainicialpv = new ColumnTO("Vigencia Inicial Pv", 39);
        this.columnas.add(vigenciainicialpv);
        ColumnTO vigenciafinalpv = new ColumnTO("Vigencia Final Pv", 40);
        this.columnas.add(vigenciafinalpv);
        ColumnTO numactoadmonaprobplanos =
            new ColumnTO("Num Acto Admon Aprob Planos", 41);
        this.columnas.add(numactoadmonaprobplanos);
        ColumnTO fechadeexpedactoadmon =
            new ColumnTO("Fecha De Exped Acto Admon", 42);
        this.columnas.add(fechadeexpedactoadmon);
        ColumnTO numadmonaprobobras =
            new ColumnTO("Num Admon Aprob Obras", 43);
        this.columnas.add(numadmonaprobobras);
        ColumnTO fechaexpedadmonaprobobras =
            new ColumnTO("Fecha Exped Admon Aprob Obras", 44);
        this.columnas.add(fechaexpedadmonaprobobras);
        ColumnTO fechanotifiaprobobras =
            new ColumnTO("Fecha Notifi Aprob Obras", 45);
        this.columnas.add(fechanotifiaprobobras);
        ColumnTO tipovertimiento = new ColumnTO("Tipo Vertimiento", 46);
        this.columnas.add(tipovertimiento);
        ColumnTO departamento = new ColumnTO("Departamento", 47);
        this.columnas.add(departamento);
        ColumnTO municipio = new ColumnTO("Municipio", 48);
        this.columnas.add(municipio);
        ColumnTO tipocentropoblado = new ColumnTO("Tipo Centro Poblado", 49);
        this.columnas.add(tipocentropoblado);
        ColumnTO centropoblado = new ColumnTO("Centro Poblado", 50);
        this.columnas.add(centropoblado);
        ColumnTO areah = new ColumnTO("Area H", 51);
        this.columnas.add(areah);
        ColumnTO zonah = new ColumnTO("Zona H", 52);
        this.columnas.add(zonah);
        ColumnTO subzonah = new ColumnTO("Subzona H", 53);
        this.columnas.add(subzonah);
        ColumnTO tipo = new ColumnTO("Tipo", 54);
        this.columnas.add(tipo);
        ColumnTO fuente = new ColumnTO("Fuente", 55);
        this.columnas.add(fuente);
        ColumnTO tramo = new ColumnTO("Tramo", 56);
        this.columnas.add(tramo);
        ColumnTO tipodeflujo = new ColumnTO("Tipo De Flujo", 57);
        this.columnas.add(tipodeflujo);
        ColumnTO tiempodedescarga = new ColumnTO("Tiempo De Descarga", 58);
        this.columnas.add(tiempodedescarga);
        ColumnTO frecuencia = new ColumnTO("Frecuencia", 59);
        this.columnas.add(frecuencia);
        ColumnTO caudaldiseñost = new ColumnTO("Caudal Diseño St", 60);
        this.columnas.add(caudaldiseñost);
        ColumnTO pretratamiento = new ColumnTO("Pretratamiento", 61);
        this.columnas.add(pretratamiento);
        ColumnTO primario = new ColumnTO("Primario", 62);
        this.columnas.add(primario);
        ColumnTO secundario = new ColumnTO("Secundario", 63);
        this.columnas.add(secundario);
        ColumnTO terciario = new ColumnTO("Terciario", 64);
        this.columnas.add(terciario);
        ColumnTO otros = new ColumnTO("Otros", 65);
        this.columnas.add(otros);
        ColumnTO sistemaref = new ColumnTO("Sistema Ref", 66);
        this.columnas.add(sistemaref);
        ColumnTO gradlat = new ColumnTO("Grad Lat", 67);
        this.columnas.add(gradlat);
        ColumnTO minlat = new ColumnTO("Min Lat", 68);
        this.columnas.add(minlat);
        ColumnTO seglat = new ColumnTO("Seg Lat", 69);
        this.columnas.add(seglat);
        ColumnTO gradlog = new ColumnTO("Grad Log", 70);
        this.columnas.add(gradlog);
        ColumnTO minlog = new ColumnTO("Min Log", 71);
        this.columnas.add(minlog);
        ColumnTO seglog = new ColumnTO("Seg Log", 72);
        this.columnas.add(seglog);
        ColumnTO altitud = new ColumnTO("Altitud ", 73);
        this.columnas.add(altitud);
        ColumnTO descripciondelsitio =
            new ColumnTO("Descripcion Del Sitio", 74);
        this.columnas.add(descripciondelsitio);

    }
}
