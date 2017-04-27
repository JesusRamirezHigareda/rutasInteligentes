<?php
	//allow access to API
	header('Access-Control-Allow-Origin: *');
	header('Access-Control-Allow-Headers: unit');
	header('Access-Control-Allow-Headers: l1');
	//use files
	require_once('classes/Locations.php');
	require_once('classes/unit.php');
		require_once('classes/route.php');
	//get headers
	$headers = getallheaders();
	//validate parameter and headers 
	if (isset($headers['unit']))
	{
			$c = new Unit($headers['unit']);
			$l1 = $headers['l1'];
			//end json
			$json = '{ "status" : 0 , 
			            "id" : "'.$c->get_uni_id().'",
						"description" : "'.$c->get_uni_description().'",';
						$r = new Route($c->get_uni_route_id());
						$json .= '
								"idRoute" : '.$r->get_rou_id().',
								"descriptionRoute" : "'.$r->get_rou_description().'",
						"Locations" : [';		
			//read makes
			$first = true;
			foreach(Unit::getLocations($headers['unit'],$headers['l1']) as $p)
			{
				
				if($first) $first = false; else $json .= ',';
				$json .= '{
										"id" : '.$p->get_loc_id().',
								  "latitude" :  "'.$p->get_loc_latitude().'",
								  "longitude" :  "'.$p->get_loc_longitude().'",
								  "datetime" :  "'.$p->get_loc_datetime().'"
				}';
																			
			}
			//end json
			$json .= '] }';
			//display json
			echo $json;
	}
	else
		echo '{ "status" : 1, "errorMessage" : "Invalid Parameters" }';
?>




