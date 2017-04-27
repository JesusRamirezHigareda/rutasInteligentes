<?php
	require_once('connection.php');
	require_once('exceptions.php');
	
	class Route extends Connection
	{
        private $rou_description;
		private $rou_id;
		
		//methods
		public function get_rou_description() { return $this->rou_description;}
		public function set_rou_description($value) { $this->rou_description = $value; }
		
		public function get_rou_id() { return $this->rou_id; }
		public function set_rou_id($value) { $this->rou_id = $value; }
		
		//Constructor
		function __construct()
		{
			//if no arguments received, create a new empty object
			if(func_num_args() == 0)
			{
				$this -> rou_description = '';
				$this -> rou_id = 0;
			}	
			//if one argument received create object with data
			if(func_num_args() == 1)
			{
				//receive arguments into an array
				$args = func_get_args();
				//id
				$rou_id = $args[0];
				//open connection to MySQL
				parent::open_connection();
				//query
				$query = "SELECT `rou_description`, `rou_id` FROM `route` WHERE rou_id = ?";
				//prepare command
				$command = parent::$connection ->prepare($query);
				//link parameters
				$command ->bind_param('i',$rou_id); //doble ss requiere dos parametros string, los cuales se solicitan en el query con ?, dentro del bind_param se ponen esos dos parametros que se necesitan (atributos.)
				//execute command
				$command -> execute();
				//link results to class attributes
				$command -> bind_result($this->rou_description,$this ->rou_id); //resultados que vas a obtener usr_id, usr_name
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