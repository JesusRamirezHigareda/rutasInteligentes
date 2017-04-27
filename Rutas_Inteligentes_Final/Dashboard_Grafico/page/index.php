<!DOCTYPE html>
<html>
  <head>
  <!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

  <script src="./jquery-2.2.3.min.js"></script>
  <script language="javascript" type="text/javascript" src="functions.js"></script>  
    <title>Map</title>
    <meta name="viewport" content="initial-scale=1.0">
    <meta charset="utf-8">
    <style>
      html, body {
        height: 93%;
		background-image:url("trafic.jpg");
		 background-repeat: no-repeat;
		background-attachment: fixed;
		background-position: center; 
        margin: 0;
        padding: 0;
      }
	  #divUnit,#unitId,#divData,#buttonLimpiar,footer{
		  float:left;
		  position:relative;
	  }
      #map {
		  top:100px;
		  width:95%;
		  margin-left:2%;
		  margin-bottom:2%;
          height: 80%;
      }
	  #divUnit{
		  width:95%;
		  margin-left:2%;
		  margin-top:2%;
	  }
	  #unitId{
		  width:30%;
	  }
	  #divData{
		  width:30%;
		  margin-left:2%;
		  margin-bottom:2%;
	  }
	  footer{
		 width: 95%;
    height: auto;
    background-color: black;
    text-align: center;
    position: absolute;
    bottom: 0;
	margin-left:2%;
	  }
    </style>
  </head>
  <body onLoad="setNumber();">
  <div id="divUnit" >
	<input class="form-control" id="unitId" placeholder="#placa de la unidad" type="text"  />
	<button class="btn btn-primary"  onclick="getLocations()">Localizar</button>
  </div>
  <div id="divData" style="display:none;">
		<h2 style="color:dodgerBlue;">Datos de la unidad</h2>
		<input id="unitId2" placeholder="#Id" type="text" class="form-control" />
		<input id="unitDescription"  placeholder="Descripcion" type="text" class="form-control" />
		<input id="unitRuteName"  placeholder="#Id de la ruta" type="text" class="form-control" />
		<button id="buttonLimpiar" type="button" onclick="location.reload();" class="btn btn-warning">Limpiar mapa</button>
	</div>
  <div id="map"></div>
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyBjBKcrrs3pz18wuE-u41gD8n-GOZsac1g&callback=initMap"
        async defer></script>
	<footer id="footer">@7A RutasInteligentes 2016</footer>
  </body>
</html>