    var loadHandler = function(event){
        findIframeElement("mapFrame").contentWindow.deleteMarkers( );
        var zoom = 6;
        var allEmpty = true;
        for(var i = 0; i < attributes.maxMarkers; i++ ) {
            var latGra = findInputElement("it_lat_grados"+i).value.trim()==""?0:findInputElement("it_lat_grados"+i).value;
            var latMin = findInputElement("it_lat_min"+i).value.trim()==""?0:findInputElement("it_lat_min"+i).value;
            var latSec = findInputElement("it_lat_seg"+i).value.trim()==""?0:findInputElement("it_lat_seg"+i).value;
            var lonGra = findInputElement("it_long_grados"+i).value.trim()==""?0:findInputElement("it_long_grados"+i).value;
            var lonMin = findInputElement("it_long_min"+i).value.trim()==""?0:findInputElement("it_long_min"+i).value;
            var lonSec = findInputElement("it_long_seg"+i).value.trim()==""?0:findInputElement("it_long_seg"+i).value;

            var latDec = Math.abs(parseInt(latGra)) + ( parseInt(latMin) / 60 ) + ( parseFloat(latSec) / 3600 );
            var lonDec = Math.abs(parseInt(lonGra)) + ( parseInt(lonMin) / 60 ) + ( parseFloat(lonSec) / 3600 );

            if (parseInt(latGra) < 0)
                latDec = -1 * latDec;
            if (parseInt(lonGra) < 0)
                lonDec = -1 * lonDec;

            if(latDec != 0 || lonDec != 0)
                allEmpty = false;

            findIframeElement("mapFrame").contentWindow.loadPointADF(lonDec, latDec,zoom);
        }
        if(allEmpty) {
            findIframeElement("mapFrame").contentWindow.deleteMarkers();
            findIframeElement("mapFrame").contentWindow.emptyInputs();
        }
        if(event)
            if (event.isCancelable()) {
                event.cancel();
            }
    }
    
    var findIframeElement = function(id){
        var iframes = parent.document.getElementsByTagName("iframe");
        var frame;
        for(var i in iframes){ 
            if(iframes[i].id && iframes[i].id.indexOf(id)>-1){
                frame = iframes[i];
                break;
            }
        }
        return frame;
    }
                
    var findInputElement = function(id){
        var iframes = parent.document.getElementsByTagName("input");
        var frame;
        for(var i in iframes){ 
            if(iframes[i].id && iframes[i].id.indexOf(id)>-1){
                frame = iframes[i];
                break;
            }
        }
        return frame;
    }
    
    function jq( myid ) {
        return "#" + myid.replace( /(:|\.|\[|\]|,)/g, "\\$1" );
    }