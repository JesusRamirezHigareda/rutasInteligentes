<?php
	require_once('connection.php');
	require_once('exceptions.php');
	require_once('Locations.php');
	
	class Unit extends Connection
	{
        private $uni_descripion;
		private $uni_id;
		private $uni_route_id;
		
		//methods
		public function get_uni_description() { return $this->uni_descripion;}
		public function set_uni_description($value) { $this->uni_descripion = $value; }
		
		public function get_uni_id() { return $this->uni_id; }
		public function set_uni_id($value) { $this->uni_id = $value; }
		
		public function get_uni_route_id() { return $this->uni_route_id; }
		public function set_uni_route_id($value) { $this->uni_route_id = $value; }
		
		//Constructor
		function __construct()
		{
			//if no arguments received, create a new empty object
			if(func_num_args() == 0)
			{
				$this -> uni_description = '';
				$this -> uni_id = "";
				$this -> uni_route_id= 0;
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
				$query = "SELECT `uni_id`, `uni_description`, `uni_route_id` FROM `unit` WHERE uni_id = ?";
				//prepare command
				$command = parent::$connection ->prepare($query);
				//link parameters
				$command ->bind_param('i',$loc_id); //doble ss requiere dos parametros string, los cuales se solicitan en el query con ?, dentro del bind_param se ponen esos dos parametros que se necesitan (atributos.)
				//execute command
				$command -> execute();
				//link results to class attributes
				$command -> bind_result($this->uni_id,$this ->uni_descripion,$this->uni_route_id); //resultados que vas a obtener usr_id, usr_name
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
        public function getLocations($id,$l1)
		{
		 //open connection to MySql
			parent::open_connection();
			//initialize arrays
			$ids = array(); //array for ids
			$list = array(); //array for objects
			//query
			$query = "SELECT loc_id FROM locations WHERE loc_unit_id = '".$id."' limit ".$l1." , 1";
			//prepare command
			$command = parent::$connection->prepare($query);
			//execute command
			$command->execute();
			//link results
			$command->bind_result($id);
			//fill ids array
			while ($command->fetch()) array_push($ids, $id);
			//close command
			mysqli_stmt_close($command);
			//close connection
			parent::close_connection();
			//fill object array
			for ($i=0; $i < count($ids); $i++) array_push($list, new Locations($ids[$i]));
			//return array
			return $list;			
		}
    }
?>