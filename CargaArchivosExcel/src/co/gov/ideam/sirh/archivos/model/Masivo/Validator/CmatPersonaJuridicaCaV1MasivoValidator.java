package co.gov.ideam.sirh.archivos.model.Masivo.Validator;

import co.gov.ideam.sirh.archivos.model.AbstractValidator;
import co.gov.ideam.sirh.archivos.model.ColumnTO;

import java.util.ArrayList;

public class CmatPersonaJuridicaCaV1MasivoValidator extends AbstractValidator {
    public CmatPersonaJuridicaCaV1MasivoValidator() {
        this.columnas = new ArrayList<ColumnTO>();
        ColumnTO idcontrolcargue = new ColumnTO("Id Control Cargue", 0);
        this.columnas.add(idcontrolcargue);
        ColumnTO codigorechazo = new ColumnTO("Codigo Rechazo", 1);
        this.columnas.add(codigorechazo);
        ColumnTO idregistro = new ColumnTO("Id Registro", 2);
        this.columnas.add(idregistro);
        ColumnTO tipousuario = new ColumnTO("Tipo Usuario", 3);
        this.columnas.add(tipousuario);
        ColumnTO razonsocial = new ColumnTO("Razon Social", 4);
        this.columnas.add(razonsocial);
        ColumnTO tipodocusuario = new ColumnTO("Tipo Doc Usuario", 5);
        this.columnas.add(tipodocusuario);
        ColumnTO numdocusuario = new ColumnTO("Num Doc Usuario", 6);
        this.columnas.add(numdocusuario);
        ColumnTO activadeco = new ColumnTO("Activad Eco", 7);
        this.columnas.add(activadeco);
        ColumnTO dircorrespondencia = new ColumnTO("Dir Correspondencia", 8);
        this.columnas.add(dircorrespondencia);
        ColumnTO email = new ColumnTO("E Mail", 9);
        this.columnas.add(email);
        ColumnTO telusuario = new ColumnTO("Tel Usuario", 10);
        this.columnas.add(telusuario);
        ColumnTO fax = new ColumnTO("Fax", 11);
        this.columnas.add(fax);
        ColumnTO nombrerprelegal = new ColumnTO("Nombre Rpre Legal", 12);
        this.columnas.add(nombrerprelegal);
        ColumnTO apellidorprelegal = new ColumnTO("Apellido Rpre Legal", 13);
        this.columnas.add(apellidorprelegal);
        ColumnTO tipodocrprelegal = new ColumnTO("Tipo Doc Rpre Legal", 14);
        this.columnas.add(tipodocrprelegal);
        ColumnTO numdocrprelegal = new ColumnTO("Num Doc Rpre Legal", 15);
        this.columnas.add(numdocrprelegal);
        ColumnTO deptorprelegal = new ColumnTO("Depto Rpre Legal", 16);
        this.columnas.add(deptorprelegal);
        ColumnTO municipiorprelegal = new ColumnTO("Municipio Rpre Legal", 17);
        this.columnas.add(municipiorprelegal);
        ColumnTO dircorrespondenciarprelegal =
            new ColumnTO("Dir Correspondencia Rpre Legal", 18);
        this.columnas.add(dircorrespondenciarprelegal);
        ColumnTO telrprelegal = new ColumnTO("Tel Rpre Legal", 19);
        this.columnas.add(telrprelegal);
        ColumnTO nombredelsitio = new ColumnTO("Nombre Del Sitio", 20);
        this.columnas.add(nombredelsitio);
        ColumnTO tipodetenencia = new ColumnTO("Tipo De Tenencia", 21);
        this.columnas.add(tipodetenencia);
        ColumnTO deptoempre = new ColumnTO("Depto Empre", 22);
        this.columnas.add(deptoempre);
        ColumnTO municipioempre = new ColumnTO("Municipio Empre", 23);
        this.columnas.add(municipioempre);
        ColumnTO tipocentropobladoempre =
            new ColumnTO("Tipo Centro Poblado Empre", 24);
        this.columnas.add(tipocentropobladoempre);
        ColumnTO nombrecentropobladoempre =
            new ColumnTO("Nombre Centro Poblado Empre", 25);
        this.columnas.add(nombrecentropobladoempre);
        ColumnTO cédulacatastralempre =
            new ColumnTO("Cédula Catastral Empre", 26);
        this.columnas.add(cédulacatastralempre);
        ColumnTO matrículacatastralempre =
            new ColumnTO("Matrícula Catastral Empre", 27);
        this.columnas.add(matrículacatastralempre);
        ColumnTO áreapredioempre = new ColumnTO("Área Predio Empre", 28);
        this.columnas.add(áreapredioempre);
        ColumnTO direccionpredioempre =
            new ColumnTO("Direccion Predio Empre", 29);
        this.columnas.add(direccionpredioempre);
        ColumnTO caudalconcesionadoempre =
            new ColumnTO("Caudal Concesionado Empre", 30);
        this.columnas.add(caudalconcesionadoempre);
        ColumnTO clasificacionsueloempre =
            new ColumnTO("Clasificacion Suelo Empre", 31);
        this.columnas.add(clasificacionsueloempre);
        ColumnTO sistemarefpredio = new ColumnTO("Sistema Ref Predio", 32);
        this.columnas.add(sistemarefpredio);
        ColumnTO gradlatpredio = new ColumnTO("Grad Lat Predio", 33);
        this.columnas.add(gradlatpredio);
        ColumnTO minlatpredio = new ColumnTO("Min Lat Predio", 34);
        this.columnas.add(minlatpredio);
        ColumnTO seglatpredio = new ColumnTO("Seg Lat Predio", 35);
        this.columnas.add(seglatpredio);
        ColumnTO gradlogpredio = new ColumnTO("Grad Log Predio", 36);
        this.columnas.add(gradlogpredio);
        ColumnTO minlogpredio = new ColumnTO("Min Log Predio", 37);
        this.columnas.add(minlogpredio);
        ColumnTO seglogpredio = new ColumnTO("Seg Log Predio", 38);
        this.columnas.add(seglogpredio);
        ColumnTO altitudpredio = new ColumnTO("Altitud Predio", 39);
        this.columnas.add(altitudpredio);
        ColumnTO descripsitiopredio = new ColumnTO("Descrip Sitio Predio", 40);
        this.columnas.add(descripsitiopredio);
        ColumnTO numexpediente = new ColumnTO("Num Expediente", 41);
        this.columnas.add(numexpediente);
        ColumnTO numresolucionasigcaudal =
            new ColumnTO("Num Resolucion Asig Caudal", 42);
        this.columnas.add(numresolucionasigcaudal);
        ColumnTO fechaexpedicionasigcaudal =
            new ColumnTO("Fecha Expedicion Asig Caudal", 43);
        this.columnas.add(fechaexpedicionasigcaudal);
        ColumnTO fechanumtifiasigcaudal =
            new ColumnTO("Fecha Numtifi Asig Caudal", 44);
        this.columnas.add(fechanumtifiasigcaudal);
        ColumnTO numresolucionaprobplanos =
            new ColumnTO("Num Resolucion Aprob Planos", 45);
        this.columnas.add(numresolucionaprobplanos);
        ColumnTO fechaexpedicionaprobplanos =
            new ColumnTO("Fecha Expedicion  Aprob Planos", 46);
        this.columnas.add(fechaexpedicionaprobplanos);
        ColumnTO fechanotifiaprobplanos =
            new ColumnTO("Fecha Notifi Aprob Planos", 47);
        this.columnas.add(fechanotifiaprobplanos);
        ColumnTO numresolucionobras = new ColumnTO("Num Resolucion Obras", 48);
        this.columnas.add(numresolucionobras);
        ColumnTO fechaexpedicionobras =
            new ColumnTO("Fecha Expedicion  Obras", 49);
        this.columnas.add(fechaexpedicionobras);
        ColumnTO fechanumtificacionobras =
            new ColumnTO("Fecha Numtificacion Obras", 50);
        this.columnas.add(fechanumtificacionobras);
        ColumnTO fechainicial = new ColumnTO("Fecha Inicial", 51);
        this.columnas.add(fechainicial);
        ColumnTO fechafinal = new ColumnTO("Fecha Final", 52);
        this.columnas.add(fechafinal);
        ColumnTO tipodefuente = new ColumnTO("Tipo De Fuente", 53);
        this.columnas.add(tipodefuente);
        ColumnTO departamento = new ColumnTO("Departamento", 54);
        this.columnas.add(departamento);
        ColumnTO municipio = new ColumnTO("Municipio", 55);
        this.columnas.add(municipio);
        ColumnTO tipocentropoblado = new ColumnTO("Tipo Centro Poblado", 56);
        this.columnas.add(tipocentropoblado);
        ColumnTO centropoblado = new ColumnTO("Centro Poblado", 57);
        this.columnas.add(centropoblado);
        ColumnTO areah = new ColumnTO("Area H", 58);
        this.columnas.add(areah);
        ColumnTO zonah = new ColumnTO("Zona H", 59);
        this.columnas.add(zonah);
        ColumnTO subzonah = new ColumnTO("Subzona H", 60);
        this.columnas.add(subzonah);
        ColumnTO tipo = new ColumnTO("Tipo", 61);
        this.columnas.add(tipo);
        ColumnTO fuente = new ColumnTO("Fuente", 62);
        this.columnas.add(fuente);
        ColumnTO tramo = new ColumnTO("Tramo", 63);
        this.columnas.add(tramo);
        ColumnTO idpuntoaguassub = new ColumnTO("Id Punto Aguas Sub", 64);
        this.columnas.add(idpuntoaguassub);
        ColumnTO provinciah = new ColumnTO("Provincia H", 65);
        this.columnas.add(provinciah);
        ColumnTO unidadh = new ColumnTO("Unidad H", 66);
        this.columnas.add(unidadh);
        ColumnTO aduccion = new ColumnTO("Aduccion", 67);
        this.columnas.add(aduccion);
        ColumnTO desarenador = new ColumnTO("Desarenador", 68);
        this.columnas.add(desarenador);
        ColumnTO ptap = new ColumnTO("Ptap", 69);
        this.columnas.add(ptap);
        ColumnTO reddistribucion = new ColumnTO("Red Distribucion", 70);
        this.columnas.add(reddistribucion);
        ColumnTO tanque = new ColumnTO("Tanque", 71);
        this.columnas.add(tanque);
        ColumnTO tipocaptacion = new ColumnTO("Tipo Captacion", 72);
        this.columnas.add(tipocaptacion);
        ColumnTO aforo = new ColumnTO("Aforo", 73);
        this.columnas.add(aforo);
        ColumnTO areacaptacion = new ColumnTO("Area Captacion ", 74);
        this.columnas.add(areacaptacion);
        ColumnTO ofertadisponible = new ColumnTO("Oferta Disponible", 75);
        this.columnas.add(ofertadisponible);
        ColumnTO macromedicion = new ColumnTO("Macromedicion", 76);
        this.columnas.add(macromedicion);
        ColumnTO estadocaptacion = new ColumnTO("Estado Captacion", 77);
        this.columnas.add(estadocaptacion);
        ColumnTO caudaldiseñocapmedido =
            new ColumnTO("Caudal Diseño Cap Medido", 78);
        this.columnas.add(caudaldiseñocapmedido);
        ColumnTO continuidadservicio =
            new ColumnTO("Continuidad Servicio", 79);
        this.columnas.add(continuidadservicio);
        ColumnTO servidumbre = new ColumnTO("Servidumbre", 80);
        this.columnas.add(servidumbre);
        ColumnTO sistemaref = new ColumnTO("Sistema Ref", 81);
        this.columnas.add(sistemaref);
        ColumnTO gradlat = new ColumnTO("Grad Lat", 82);
        this.columnas.add(gradlat);
        ColumnTO minlat = new ColumnTO("Min Lat", 83);
        this.columnas.add(minlat);
        ColumnTO seglat = new ColumnTO("Seg Lat", 84);
        this.columnas.add(seglat);
        ColumnTO gradlog = new ColumnTO("Grad Log", 85);
        this.columnas.add(gradlog);
        ColumnTO minlog = new ColumnTO("Min Log", 86);
        this.columnas.add(minlog);
        ColumnTO seglog = new ColumnTO("Seg Log", 87);
        this.columnas.add(seglog);
        ColumnTO altitud = new ColumnTO("Altitud", 88);
        this.columnas.add(altitud);
        ColumnTO descripsitio = new ColumnTO("Descrip Sitio", 89);
        this.columnas.add(descripsitio);
        ColumnTO caudalabaste = new ColumnTO("Caudal Abaste", 90);
        this.columnas.add(caudalabaste);
        ColumnTO numperpermanentesabaste =
            new ColumnTO("Num Per Permanentes Abaste", 91);
        this.columnas.add(numperpermanentesabaste);
        ColumnTO numpertransitoriasabaste =
            new ColumnTO("Num Per Transitorias Abaste", 92);
        this.columnas.add(numpertransitoriasabaste);
        ColumnTO aprovechabaste = new ColumnTO("Aprovech Abaste", 93);
        this.columnas.add(aprovechabaste);
        ColumnTO tipoanimalabrev = new ColumnTO("Tipo Animal Abrev", 94);
        this.columnas.add(tipoanimalabrev);
        ColumnTO caudalabrev = new ColumnTO("Caudal Abrev", 95);
        this.columnas.add(caudalabrev);
        ColumnTO numanimalesabrev = new ColumnTO("Num Animales Abrev", 96);
        this.columnas.add(numanimalesabrev);
        ColumnTO tipoanimalpesca = new ColumnTO("Tipo Animal Pesca", 97);
        this.columnas.add(tipoanimalpesca);
        ColumnTO caudalpesca = new ColumnTO("Caudalpesca", 98);
        this.columnas.add(caudalpesca);
        ColumnTO numanimalespesca = new ColumnTO("Num Animales Pesca", 99);
        this.columnas.add(numanimalespesca);
        ColumnTO tipocultivosilvicultura =
            new ColumnTO("Tipo Cultivo Silvicultura", 100);
        this.columnas.add(tipocultivosilvicultura);
        ColumnTO caudalsilvicultura = new ColumnTO("Caudal Silvicultura", 101);
        this.columnas.add(caudalsilvicultura);
        ColumnTO produccionsilvicultura =
            new ColumnTO("Produccion Silvicultura", 102);
        this.columnas.add(produccionsilvicultura);
        ColumnTO eficienciasilvicultura =
            new ColumnTO("Eficiencia Silvicultura", 103);
        this.columnas.add(eficienciasilvicultura);
        ColumnTO areasilvicultura = new ColumnTO("Area Silvicultura", 104);
        this.columnas.add(areasilvicultura);
        ColumnTO tipodeusootros = new ColumnTO("Tipo De Uso Otros", 105);
        this.columnas.add(tipodeusootros);
        ColumnTO decripcionotros = new ColumnTO("Decripcion Otros", 106);
        this.columnas.add(decripcionotros);
        ColumnTO caudalotros = new ColumnTO("Caudal Otros", 107);
        this.columnas.add(caudalotros);

    }
}
