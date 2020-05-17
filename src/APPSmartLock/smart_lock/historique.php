<?php
$db= new PDO("mysql:host=localhost;dbname=application_login","root","");
$results["error"]=false;
$results["message"]=[];

if(!empty($_POST)){
		
		$pseudo = $_POST['pseudo'];
		$statut = $_POST['statut'];


		 $sql = $db->prepare("INSERT INTO historique(pseudo,statut)VALUES(:pseudo, :statut)");
				$sql->execute([":pseudo" => $pseudo, ":statut" => $statut]);
				if(!$sql){
					$results['error']=true;
					$results["message"]= "Erreur lors de l'insertion";
									
				}
			
		
	
	
	echo json_encode($results);
}
?>