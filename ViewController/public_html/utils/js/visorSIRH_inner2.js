/**
 * Script que maneja los elementos y eventos dentro del mapa
 * @author Skaphe Tecnologías Sas
 * @version 0.1
 */

if( !attributes )
attributes = {maxMarkers:0};

/**
 * Variable que modela la fuente de tipo WMS según la documentación de OpenLayers 3
 */
var wmsSource = new ol.source.TileWMS({
    url: 'http://geoapps.ideam.gov.co:8080/geoserver/informacion_basica/wms',
    params: {'LAYERS': 'informacion_basica:Deptos'},
    serverType: 'geoserver'
});

/**
 * Variable que modela una capa de tipo WMS.
 */
var wmsLayer = new ol.layer.Tile({
  source: wmsSource
});

var wms_layer2 = new ol.layer.Tile({
    source: new ol.source.TileWMS({
        url: 'http://demo.boundlessgeo.com/geoserver/wms',
        params: {'LAYERS': 'ne:ne'}
    })
});

/**
 * Variable que modela la vista de un mapa
 */
var view = new ol.View({
    center: ol.proj.transform([-75.4, 4.4], 'EPSG:4326', 'EPSG:3857'),
    zoom: 4
});

/**
 * Variable que define un mapa con una capa por defecto.
 */
var map = new ol.Map({
        target: 'map',
        layers: [
          new ol.layer.Tile({
            source: new ol.source.OSM()
          }),
          wmsLayer/*,
          wms_layer2*/
        ],
        view: view
});

/**
 * Método que, dada una posición geográfica y un acercamiento, cambia la visualización del mapa centrándola
 * en las coordenadas recibidas y posicionando un pin en el punto señalado.
 * @param {long} lonDec Longitud decimal del punto a centrar en el mapa.
 * @param {long} latDec Latitud decimal del punto a centrar en el mapa.
 * @param {integer} zoom Acermiento esperado al momento de centrar el mapa en el punto entregado.
 */
var loadPointADF = function(lonDec,latDec,zoom){
    var vectorSource = new ol.source.Vector({
        // Crear un vector vacío
    });  
    // Declaración del ícono en las coordenadas dadas
    var iconFeature = new ol.Feature({
        geometry: new
        ol.geom.Point(ol.proj.transform([lonDec, latDec], 'EPSG:4326', 'EPSG:3857')),
        name: 'Posición',
        population: 4000,
        rainfall: 500
    });
    vectorSource.addFeature(iconFeature);
    // Creación del estilo del ícono creado anteriormente
    var iconStyle = new ol.style.Style({
        image: new ol.style.Icon( /** @type {olx.style.IconOptions} */ ({
            anchor: [0.5, 46],
            anchorXUnits: 'fraction',
            anchorYUnits: 'pixels',
            opacity: 1,
            src: '/Sirh/imgs/marker.png'
            }))
        }
    );

    // Agrega el vector creado con el ícono, a una capa de tipo Vector
    var vectorLayer = new ol.layer.Vector({
        source: vectorSource,
        style: iconStyle,
        title: "Marker"
    });

    if( attributes.maxMarkers == 0 || countMarkers() < attributes.maxMarkers ) {
        // Agrega la capa de tipo Vector al mapa
        map.addLayer(vectorLayer);
    }
    
    if(zoom)
    {
        if(lonDec == 0 && latDec == 0){
            lonDec = -74.4;
            latDec = 4.4;
        }
        map.getView().setCenter(ol.proj.transform([lonDec, latDec], 'EPSG:4326', 'EPSG:3857'));
        map.getView().setZoom(zoom);
    }
}

/**
 * Función que borra todos los marcadores del mapa
 */
function deleteMarkers() {
    // Remueve todas las capas excepto las de tipo "Tile"
    var layers = map.getLayers();
    var length = layers.getLength();
    for (var i = 0; i < length; i++) {
        var obj = layers.item(i);
        if (obj) {
            var lt = obj.get("title");
            if (lt && lt == "Marker") {
                map.removeLayer(obj);
                i--;
                length--;
            }
        }
    }
}

/**
 * Función que vacía todos los inputs en el padre
 */
function emptyInputs(){
    for(var i = 0; i < attributes.maxMarkers; i++) {
        findInputElement("it_lat_grados" + i).value = "";
        findInputElement("it_lat_min" + i).value = "";
        findInputElement("it_lat_seg" + i).value = "";
        findInputElement("it_long_grados" + i).value = "";
        findInputElement("it_long_min" + i).value = "";
        findInputElement("it_long_seg" + i).value = "";
    }

    //map.getView().setCenter(ol.proj.transform([-74.4, 4.4], 'EPSG:4326', 'EPSG:3857'));
    //map.getView().setZoom(4);
}

/**
 * Función que cuenta todos los marcadores del mapa
 */
function countMarkers() {
    // Remueve todas las capas excepto las de tipo "Tile"
    var layers = map.getLayers();
    var length = layers.getLength();
    var count = 0;
    for(var i = 0; i < length; i++){
        var obj = layers.item(i);
        if(obj) {
            var lt = obj.get("title");
            if (lt && lt == "Marker") {
                count++;
            }
        }
    }
    return count;
}

/**
 * Manipulación del evento generado al hacer clic en el mapa. Adicionalmente se agregan las coordenadas a un conjunto de cajas de texto
 * que se encuentran en la página que embeba el mapa que tiene este script.
 * @pre Es necesario que este script se ejecute en un iframe
 * @pre La página que embeba en un iframe el mapa que contiene este script, debe tener inputs que modelen minutos, grados y segundos de longitud y latitud
 *      definidos con un id de formato 'it_yyy_xxxxN' de manera que:
 *      'yyy' puede tomar los valores de 'lat' para latitud o 'long' para longitud.
 *      'xxxx' puede tomar los valores 'grados' para grados, 'min' para minutos o 'seg' para segundos.
 *      'N' puede tomar valores de 0 en adelante, según la cantidad de puntos que deba tener un formulario.
 * @post La función modela el evento de un solo clic con el mouse en el mapa y cambia los valores de los inputs relacionados a las coordenadas.
 * @post En caso de encontrar que la cantidad máxima definida de puntos ya se ha establecido, le muestra un mensaje al usuaio, informando que ya
 *      se han agregado todos los puntos esperados.
 * @param {object} evt Objeto que se crea con la información del evento generado
 */
map.on("singleclick", function(evt) {
    if( attributes.maxMarkers == 0 || countMarkers() < attributes.maxMarkers ) {
        var coord = ol.proj.transform(evt.coordinate, 'EPSG:3857', 'EPSG:4326');
        var lon = coord[0];
        var lat = coord[1];

        // Variable que modela el input al que se desea agregar la coordenada seleccionada en el mapa
        var actualInput = countMarkers();

        loadPointADF(lon, lat);

        var int;
        var rest;

        var viewResolution = /** @type {number} */ (view.getResolution());
        var url = wmsSource.getGetFeatureInfoUrl(
         evt.coordinate, viewResolution, 'EPSG:3857',
         {'INFO_FORMAT': 'text/javascript'}
         );

        console.log(url);

        if (url) {
            var parser = new ol.format.GeoJSON();
            $.ajax({
                url: url,
                dataType: 'jsonp',
                jsonpCallback: 'parseResponse'
            }).then(function(response) {
                var result = parser.readFeatures(response);
                if (result.length) {
                    console.log(result)
                } else {
                    console.log("No hay cosas D:")
                }
            });
        }

        // Conversión de latitud a Grados Minutos y Segundos
        int = parseInt(lat);
        if (int > 0)
            rest = lat - int;
        else if (int == 0)
            rest = Math.abs(lat);
        else
            rest = int - lat;

        findInputElement("it_lat_grados"+actualInput).value = int;
        rest = rest * 60;
        int = parseInt(rest);
        rest = rest - int;
        findInputElement("it_lat_min"+actualInput).value = int;

        rest = rest * 60;
        rest = Math.round(rest * 100000) / 100000;
        findInputElement("it_lat_seg"+actualInput).value = rest;

        // Conversión de longitud a Grados Minutos y Segundos
        int = parseInt(lon);
        if (int > 0)
            rest = lon - int;
        else if (int == 0)
            rest = Math.abs(lon);
        else
            rest = int - lon;

        findInputElement("it_long_grados"+actualInput).value = int;

        rest = rest * 60;
        int = parseInt(rest);
        rest = rest - int;
        findInputElement("it_long_min"+actualInput).value = int;

        rest = rest * 60;
        rest = Math.round(rest * 100000) / 100000;
        findInputElement("it_long_seg"+actualInput).value = rest;
    }
    else{
        alert("Ya agregó el máximo de puntos permitidos para este formulario.")
    }
});
    
/**
 * Método que llama al método "loadHandler()" que se encuentra definido en los scripts de una página padre
 * que contenga este mapa dentro de un iframe.
 */
var loadParent = function(){
    loadHandler();
};