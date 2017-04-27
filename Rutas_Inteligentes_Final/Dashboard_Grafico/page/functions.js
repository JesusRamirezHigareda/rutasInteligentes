var urlServer = 'http://localhost:8080/proyectoarduino/'; //la raíz del sitio
var urlImages = 'http://localhost:8080/proyectoarduino/images/';
var x = new XMLHttpRequest();
var newSearch = true;
var map;
var arreglo = [];
var numeroDeArreglo = 0;
var interval ;
var arreglo2 = [];
var coordenadaX =32.5203450;
var coordenadaY =-116.8693880; 
var lineas;

//load index
function setNumber()
{
	sessionStorage.setItem('key', 0);
}
function getLocations()
{
	var p = document.getElementById("unitId").value;
	$('#divData').slideDown();
	document.getElementById('footer').style.bottom =  '-110px';
	 x.open('GET', urlServer + 'getLocations.php', true); //¿Qué tipo de petición es? - Se puede validar en POSTMAN, puede ser get o post - concatena el url del servidor con el nombre del documento.
	//headers
	var l1 = sessionStorage.getItem('key');
	var l2 = parseInt(l1) + 1;
	console.log(l2);
	x.setRequestHeader('unit', p); //nombre del header que se colocó en postman, el valor que le voy a enviar.
	x.setRequestHeader('l1', l1); //nombre del header que se colocó en postman, el valor que le voy a enviar.
	sessionStorage.setItem('key', l2);
	//send petition
	x.send();
	//event handler 
	x.onreadystatechange = function() //cuando cambie el estatus, invoca la función (se dispara la función cada vez que cambie el estatus)
	{
		//check status 
		if(x.readyState == 4 & x.status ==200) //si hay datos de regreso, si encontró el url
		{
			var a;
			var b;
			//display response
			//read response
			var data = x.responseText;
			//parse to JSON
			//console.log(x.responseText);
			var JSONdata = JSON.parse(data);	
			
			if(JSONdata.status == 0)
			{
				//llenar datos de la unidad
			 $( "#unitId2" ).val(JSONdata.id);
			 $( "#unitDescription" ).val(JSONdata.description);
			 $( "#unitRuteName" ).val(JSONdata.descriptionRoute);
			 document.getElementById('map').style.top = "0px";
			 //crear los marcadores en el mapa
			 for (var i = 0; i < JSONdata.Locations.length; i++)
				{ 
					gernera_marcador(JSONdata.Locations[i].latitude,JSONdata.Locations[i].longitude,JSONdata.Locations[i].id,JSONdata.Locations[i].datetime);
					a = JSONdata.Locations[i].latitude;
					b = JSONdata.Locations[i].longitude;
				}
				coordenadaX = a;
				coordenadaY = b;
			}
			else
			{
				alert('Access Denied');
			}
		}
	}	
	arreglo.length=0; 
	//arreglo2.length=0;
 //interval = setInterval(getLocations,5000);
 
 setTimeout(function(){ setMapOnAll(null); }, 9000);
setTimeout(function(){ getLocations(); }, 10000);
}		
	
		
      function initMap() {
        map = new google.maps.Map(document.getElementById('map'), {
          center: {lat:coordenadaX, lng: coordenadaY},
          zoom: 13
        });
      }
				
function gernera_marcador(lat,lng,id,date){
	var imagen = urlServer + "page/bus.png";
	var marcador = new google.maps.Marker({
		position: new google.maps.LatLng(lat,lng),
		map: map,
		title:"id ruta: "+ id+" fecha :" +date,
		animation: google.maps.Animation.DROP,
		icon: imagen,
		identificador: id
	});
	arreglo2.push(new google.maps.LatLng(lat, lng));
	arreglo.push(marcador);
	generarLinea();
}//close
function generarLinea()
{
	lineas = new google.maps.Polyline({        
    path: arreglo2,
    map: map, 
    strokeColor: '#44b044', 
    strokeWeight: 5,  
    strokeOpacity: 0.3, 
    clickable: false     });
}
	// Sets the map on all markers in the array.
function setMapOnAll(map) {
	   //  location.reload();
	  
	   for (var i = 0; i < arreglo.length; i++) {
          arreglo[i].setMap(map);
        } 
		//arreglo.length=0;
      }
function Unit(id, description, route)
{
	if (typeof id !== 'undefined') this.id = id;
	if (typeof description !== 'undefined') this.description = description;
	if (typeof route !== 'undefined') this.route = route;	
}
function Locations(id, description, route)
{
	if (typeof id !== 'undefined') this.id = id;
	if (typeof description !== 'undefined') this.description = description;
	if (typeof route !== 'undefined') this.route = route;	
}