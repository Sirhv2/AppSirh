package co.gov.ideam.sirh.archivos.model;

import co.gov.ideam.sirh.parametros.business.ParametrosEJB;
import co.gov.ideam.sirh.parametros.model.Lista;
import co.gov.ideam.sirh.usuarios.agua.business.UsuariosAguaEJB;
import co.gov.ideam.sirh.usuarios.agua.model.Captacion;
import co.gov.ideam.sirh.usuarios.agua.model.CaptacionTO;
import co.gov.ideam.sirh.usuarios.agua.model.SubttFunias;
import co.gov.ideam.sirh.util.Constantes;
import co.gov.ideam.sirh.util.ConstantesParametros;
import co.gov.ideam.sirh.util.IdeamException;

public class FuniasDiagnosticoConverter extends ModelConverter{
    public FuniasDiagnosticoConverter() { 
        super();
    }

    public Object getModel(Object model)throws IdeamException {
        if(model instanceof SubttFunias){
            return this.getFunias( (SubttFunias)model );
        }
        return null;
    }
    
    private SubttFunias getFunias(SubttFunias funias)throws IdeamException{
        ParametrosEJB parametrosService = this.getParametrosService();
        UsuariosAguaEJB usuariosAguaService = this.getUsuariosAguaService();
        
        String identificador = row.getCellValue("Identificador Punto").toString();
        
        //verificar que exista una captación con el identificador del punto.
        if(identificador == null || identificador.trim().equalsIgnoreCase("")){
            //throw new IdeamException("Identificador de punto no valido.");
            return null;
        }else{
            CaptacionTO captacion = 
                usuariosAguaService.getCaptacion(identificador, funias.getAutoridad().getId());
            if(captacion == null){
                //throw new IdeamException("No se encuentra captación con el identificador de punto: "+identificador);
                return null;
            }
            funias.setIdCaptacion(captacion.getId());
        }
        
        //verificar si existe un funias para esa captación del mismo tipo
        SubttFunias existeFunias = usuariosAguaService.
            getFuniasByCaptacionTipoFunias(funias.getIdCaptacion(),
                                           ConstantesParametros.LISTA_GEOLOGIA_GEOMORFOLOGIA.intValue());
        if(existeFunias != null){
            funias = existeFunias;
        }
        
        
        //información en archivo

        String letrina = row.getCellValue("Letrina Cercana").toString();
        letrina = letrina.trim().toUpperCase();
        if(letrina.equalsIgnoreCase("SI")){
            funias.setLetrinaCercana(Constantes.TRUE_TO_INTEGER);
        }else{
            funias.setLetrinaCercana(Constantes.FALSE_TO_INTEGER);
        }
       
        String distanciaLetrina = row.getCellValue("Distancia Letrina").toString();
        distanciaLetrina = replaceFloatingSeparator(distanciaLetrina);
        funias.setDistanciaLetrina((distanciaLetrina!=null)
                                   ?Double.parseDouble(distanciaLetrina) :null);
        
        String charco = row.getCellValue("Charco Agua Estancada").toString();
        charco = charco.trim().toUpperCase();
        if(charco.equalsIgnoreCase("SI")){
            funias.setAguaEstancada(Constantes.TRUE_TO_INTEGER);
        }else{
            funias.setAguaEstancada(Constantes.FALSE_TO_INTEGER);
        }
        
        String distanciaCharco = row.getCellValue("Distancia Charco").toString();
        distanciaCharco = replaceFloatingSeparator(distanciaCharco);
        funias.setDistancia((distanciaCharco!=null)
                            ?Double.parseDouble(distanciaCharco) :null);
        
        String basura = row.getCellValue("Basura, Criaderos, Estiercol Alrededor").toString();
        basura = basura.trim().toUpperCase();
        if(basura.equalsIgnoreCase("SI")){
            funias.setCriaderoGanado(Constantes.TRUE_TO_INTEGER);
        }else{
            funias.setCriaderoGanado(Constantes.FALSE_TO_INTEGER);
        }
        
        String distanciaBasura = row.getCellValue("Distancia Basura, Criaderos, Estiercol Alrededor").toString();
        distanciaBasura = replaceFloatingSeparator(distanciaBasura);
        funias.setDistanciaCriadero((distanciaBasura!=null)
                                    ?Double.parseDouble(distanciaBasura) :null);
        
        String borde = row.getCellValue("Borde o Grieta de Filtración Superficial").toString();
        borde = borde.trim().toUpperCase();
        if(borde.equalsIgnoreCase("SI")){
            funias.setFiltracionAgua(Constantes.TRUE_TO_INTEGER);
        }else{
            funias.setFiltracionAgua(Constantes.FALSE_TO_INTEGER);
        }
        
        String distanciaBorde = row.getCellValue("Distancia Borde o Grieta de Filtración Superficial").toString();
        distanciaBorde = replaceFloatingSeparator(distanciaBorde);
        funias.setDistanciaFiltracion((distanciaBorde!=null)
                                    ?Double.parseDouble(distanciaBorde) :null);
        
        String cubierta = row.getCellValue("Cubierta Adecuada").toString();
        cubierta = cubierta.trim().toUpperCase();
        if(cubierta.equalsIgnoreCase("SI")){
            funias.setCubiertaAdecuada(Constantes.TRUE_TO_INTEGER);
        }else{
            funias.setCubiertaAdecuada(Constantes.FALSE_TO_INTEGER);
        }
        
        String sello = row.getCellValue("Sello Sanitario").toString();
        sello = sello.trim().toUpperCase();
        if(sello.equalsIgnoreCase("SI")){
            funias.setSelloSanitario(Constantes.TRUE_TO_INTEGER);
        }else{
            funias.setSelloSanitario(Constantes.FALSE_TO_INTEGER);
        }
        
        String piso = row.getCellValue("Piso Cemento").toString();
        piso = piso.trim().toUpperCase();
        if(piso.equalsIgnoreCase("SI")){
            funias.setPisoCemento(Constantes.TRUE_TO_INTEGER);
        }else{
            funias.setPisoCemento(Constantes.FALSE_TO_INTEGER);
        }
        
        String cerco = row.getCellValue("Cerco Instalado").toString();
        cerco = cerco.trim().toUpperCase();
        if(cerco.equalsIgnoreCase("SI")){
            funias.setCercoAdecuado(Constantes.TRUE_TO_INTEGER);
        }else{
            funias.setCercoAdecuado(Constantes.FALSE_TO_INTEGER);
        }
        
        return funias;
    }

    private int parseValor(String nombreColumna){
        String texto =
            row.getCellValue(nombreColumna).toString();
        Double valor = Double.parseDouble( texto );
        return valor.intValue();
    }

    private String replaceFloatingSeparator(String tmp){
        if(tmp!=null && !tmp.trim().equalsIgnoreCase("")){
            if(tmp.indexOf(",")!=-1)
            tmp = tmp.replaceAll(",",".");
        }else{
            tmp=null;
        }
        return tmp;
    }
}
