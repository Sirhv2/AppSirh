/**
 * Created by Skaphe Tecnologías.
 */

/**
 * Objeto que modela los atributos de configuración de comportamiento de los scripts geográficos, en base a las
 * necesidades de cada página en la que serán utilizados dichos scripts.
 * @integer maxMarkers - Cantidad máxima esperada de puntos en el mapa, 0 para una cantidad ilimitada.
 */
var attributes= {
    maxMarkers: 2
};

var toggle = false;

$("[id*=mapFrame]",window.parent.document).height("21px");
$("[id*=mapFrame]",window.parent.document).width("79px");

$("#toggleButton").click(function(){
    if(toggle){
        $("[id*=mapFrame]",window.parent.document).height("21px");
        $("[id*=mapFrame]",window.parent.document).width("79px");
        $("[id*=toggleButton]").html('Abrir mapa');
    }
    else{
        $("[id*=mapFrame]",window.parent.document).height("410px");
        $("[id*=mapFrame]",window.parent.document).width("627px");
        $("[id*=toggleButton]").html('Cerrar mapa');
        loadHandler();
    }
    toggle = !toggle;
});