<?php
	require_once('connection.php');
	require_once('exceptions.php');
	
	class Locations extends Connection
	{
        private $loc_id;
		private $loc_datetime;
		private $loc_latitude;
		private $loc_longitude;
		private $loc_unit_id;
		
		//methods
		public function get_loc_id() { return $this->loc_id;}
		public function set_loc_id($value) { $this->loc_id = $value; }
		
		public function get_loc_datetime() { return $this->loc_datetime;}
		public function set_loc_datetime($value) { $this->loc_datetime = $value; }
		
		public function get_loc_latitude() { return $this->loc_latitude;}
		public function set_loc_latitude($value) { $this->loc_latitude = $value; }
		
		public function get_loc_longitude() { return $this->loc_longitude;}
		public function set_loc_longitude($value) { $this->loc_longitude = $value; }
		
		public function get_loc_unit_id() { return $this->loc_unit_id;}
		public function set_loc_unit_id($value) { $this->loc_unit_id = $value; }
		
		//Constructor
		function __construct()
		{
			//if no arguments received, create a new empty object
			if(func_num_args() == 0)
			{
				$this -> loc_id = 0;
				$this -> loc_datetime = '';
				$this -> loc_latitude = '';
				$this -> loc_longitud = '';
			    $this -> loc_unit_id  = '';
			}	
			//if one argument received create object with data
			if(func_num_args() == 1)
			{
				//receive arguments into an array
				$args = func_get_args();
				//id
				$loc_id = $args[0];
				//open connection to MySQL
				parent::open_connection();
				//query
				$query = "SELECT `loc_id`, `loc_datetime`, `loc_latitude`, `loc_longitude`, `loc_unit_id` FROM `locations` WHERE loc_id = ?";
				//prepare command
				$command = parent::$connection ->prepare($query);
				//link parameters
				$command ->bind_param('i',$loc_id); //doble ss requiere dos parametros string, los cuales se solicitan en el query con ?, dentro del bind_param se ponen esos dos parametros que se necesitan (atributos.)
				//execute command
				$command -> execute();
				//link results to class attributes
				$command -> bind_result($this->loc_id,$this ->loc_datetime,$this->loc_latitude,$this->loc_longitude,$this->loc_unit_id); //resultados que vas a obtener usr_id, usr_name
				//fetch data 
				$found = $command -> fetch();
				//close command 
				mysqli_stmt_close($command);
				//close connection
				parent::close_connection();
				//if not found throw exception
				if(!$found) throw(new RecordNotFoundException());
			}			
		}
    }
?>