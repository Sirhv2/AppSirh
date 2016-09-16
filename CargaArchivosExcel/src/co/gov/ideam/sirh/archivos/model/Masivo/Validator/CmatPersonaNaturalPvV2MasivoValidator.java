package co.gov.ideam.sirh.archivos.model.Masivo.Validator;

import co.gov.ideam.sirh.archivos.model.AbstractValidator;
import co.gov.ideam.sirh.archivos.model.ColumnTO;

import java.util.ArrayList;

public class CmatPersonaNaturalPvV2MasivoValidator extends AbstractValidator {
    public CmatPersonaNaturalPvV2MasivoValidator() {
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
        ColumnTO numactadmautv = new ColumnTO("Num Act Adm Aut V", 34);
        this.columnas.add(numactadmautv);
        ColumnTO fechaexpactadm = new ColumnTO("Fecha Exp Act Adm", 35);
        this.columnas.add(fechaexpactadm);
        ColumnTO numexpediente = new ColumnTO("Num Expediente", 36);
        this.columnas.add(numexpediente);
        ColumnTO caudalautorizado = new ColumnTO("Caudal Autorizado", 37);
        this.columnas.add(caudalautorizado);
        ColumnTO evalambienvert = new ColumnTO("Eval Ambien Vert", 38);
        this.columnas.add(evalambienvert);
        ColumnTO numactpresplancump =
            new ColumnTO("Num Act Pres Plan Cump", 39);
        this.columnas.add(numactpresplancump);
        ColumnTO fechaexpactadmplcu =
            new ColumnTO("Fecha Exp Act Adm Plcu", 40);
        this.columnas.add(fechaexpactadmplcu);
        ColumnTO numactadmapruplcu = new ColumnTO("Num Act Adm Apru Plcu", 41);
        this.columnas.add(numactadmapruplcu);
        ColumnTO fechaexpaprobplan = new ColumnTO("Fecha Exp Aprob Plan", 42);
        this.columnas.add(fechaexpaprobplan);
        ColumnTO viginiplancumpl = new ColumnTO("Vig Ini Plan Cumpl", 43);
        this.columnas.add(viginiplancumpl);
        ColumnTO vigfinplancumpl = new ColumnTO("Vig Fin Plan Cumpl", 44);
        this.columnas.add(vigfinplancumpl);
        ColumnTO numresolotorpv = new ColumnTO("Num Resol Otor Pv", 45);
        this.columnas.add(numresolotorpv);
        ColumnTO fechaexppv = new ColumnTO("Fecha Exp Pv", 46);
        this.columnas.add(fechaexppv);
        ColumnTO vigenciainipv = new ColumnTO("Vigencia Ini Pv", 47);
        this.columnas.add(vigenciainipv);
        ColumnTO vigenciafinpv = new ColumnTO("Vigencia Fin Pv", 48);
        this.columnas.add(vigenciafinpv);
        ColumnTO numactadmaprplanos =
            new ColumnTO("Num Act Adm Apr Planos", 49);
        this.columnas.add(numactadmaprplanos);
        ColumnTO fechaexpaprobplanos =
            new ColumnTO("Fecha Exp Aprob Planos", 50);
        this.columnas.add(fechaexpaprobplanos);
        ColumnTO numactaprobobras = new ColumnTO("Num Act Aprob Obras", 51);
        this.columnas.add(numactaprobobras);
        ColumnTO fechaexpactaprobras =
            new ColumnTO("Fecha Exp Act Apr Obras", 52);
        this.columnas.add(fechaexpactaprobras);
        ColumnTO tipovertimiento = new ColumnTO("Tipo Vertimiento", 53);
        this.columnas.add(tipovertimiento);
        ColumnTO departamentoverti = new ColumnTO("Departamento Verti", 54);
        this.columnas.add(departamentoverti);
        ColumnTO municipioverti = new ColumnTO("Municipio Verti", 55);
        this.columnas.add(municipioverti);
        ColumnTO tipocentropobladopv =
            new ColumnTO("Tipo Centro Poblado Pv", 56);
        this.columnas.add(tipocentropobladopv);
        ColumnTO centropoblado = new ColumnTO("Centro Poblado", 57);
        this.columnas.add(centropoblado);
        ColumnTO areapv = new ColumnTO("Area Pv", 58);
        this.columnas.add(areapv);
        ColumnTO zonapv = new ColumnTO("Zona Pv", 59);
        this.columnas.add(zonapv);
        ColumnTO subzona = new ColumnTO("Subzona", 60);
        this.columnas.add(subzona);
        ColumnTO tipofuentepv = new ColumnTO("Tipo Fuente Pv", 61);
        this.columnas.add(tipofuentepv);
        ColumnTO fuentepv = new ColumnTO("Fuente Pv", 62);
        this.columnas.add(fuentepv);
        ColumnTO tramopv = new ColumnTO("Tramo Pv", 63);
        this.columnas.add(tramopv);
        ColumnTO tipoflujopv = new ColumnTO("Tipo Flujo Pv", 64);
        this.columnas.add(tipoflujopv);
        ColumnTO tiempodescargapv = new ColumnTO("Tiempo Descarga Pv", 65);
        this.columnas.add(tiempodescargapv);
        ColumnTO frecuenciapv = new ColumnTO("Frecuencia Pv", 66);
        this.columnas.add(frecuenciapv);
        ColumnTO caudaldiseñopv = new ColumnTO("Caudal Diseño Pv", 67);
        this.columnas.add(caudaldiseñopv);
        ColumnTO pretratamiento = new ColumnTO("Pretratamiento", 68);
        this.columnas.add(pretratamiento);
        ColumnTO primario = new ColumnTO("Primario", 69);
        this.columnas.add(primario);
        ColumnTO secundario = new ColumnTO("Secundario", 70);
        this.columnas.add(secundario);
        ColumnTO terciario = new ColumnTO("Terciario", 71);
        this.columnas.add(terciario);
        ColumnTO pretratamientootros =
            new ColumnTO("Pretratamiento Otros", 72);
        this.columnas.add(pretratamientootros);
        ColumnTO sistemarefpv = new ColumnTO("Sistema Ref Pv", 73);
        this.columnas.add(sistemarefpv);
        ColumnTO gradlatpv = new ColumnTO("Grad Lat Pv", 74);
        this.columnas.add(gradlatpv);
        ColumnTO minlatpv = new ColumnTO("Min Lat Pv", 75);
        this.columnas.add(minlatpv);
        ColumnTO seglatpv = new ColumnTO("Seg Lat Pv", 76);
        this.columnas.add(seglatpv);
        ColumnTO gradlogpv = new ColumnTO("Grad Log Pv", 77);
        this.columnas.add(gradlogpv);
        ColumnTO minlogpv = new ColumnTO("Min Log Pv", 78);
        this.columnas.add(minlogpv);
        ColumnTO seglogpv = new ColumnTO("Seg Log Pv", 79);
        this.columnas.add(seglogpv);
        ColumnTO altitudpv = new ColumnTO("Altitud Pv", 80);
        this.columnas.add(altitudpv);
        ColumnTO descripcionsitiopv = new ColumnTO("Descripcion Sitio Pv", 81);
        this.columnas.add(descripcionsitiopv);
        ColumnTO nombrepuntodescarga =
            new ColumnTO("Nombre Punto Descarga", 82);
        this.columnas.add(nombrepuntodescarga);
        ColumnTO nombrepuntoaguasarribav =
            new ColumnTO("Nombre Punto Aguas Arriba V", 83);
        this.columnas.add(nombrepuntoaguasarribav);
        ColumnTO nombrepuntoaguasabajov =
            new ColumnTO("Nombre Punto Aguas Abajo V", 84);
        this.columnas.add(nombrepuntoaguasabajov);

    }
}
