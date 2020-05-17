<?php
$db= new PDO("mysql:host=localhost;dbname=application_login","root","");
$results["error"]=false;
$results["message"]=[];




	if(isset($_POST)){
		if(!empty($_POST['nom']) && !empty($_POST['prenom'])&& !empty($_POST['pseudo'])&& !empty($_POST['email']) && !empty($_POST['password'])&& !empty($_POST['password2'])){
			
			$nom = $_POST['nom'];
			$prenom = $_POST['prenom'];
			$pseudo = $_POST['pseudo'];
			$email = $_POST['email'];
			$password = $_POST['password'];
			$password2 = $_POST['password2'];
			
			
			
			//Verification du nom
			if(strlen($nom) < 2 || !preg_match("/^[a-zA-Z0-9 _-]+$/", $nom) || strlen($nom)> 60){
				$results['error']=true;
				$results["message"]["nom"]= "Nom invalide";
			}
			
			//Verification du prenom
			if(strlen($prenom) < 2 || !preg_match("/^[a-zA-Z0-9 _-]+$/", $prenom) || strlen($prenom)> 60){
				$results['error']=true;
				$results["message"]["prenom"]= "Prenom invalide";
			}
			
			//verification du pseudo
			if(strlen($pseudo) < 2 || !preg_match("/^[a-zA-Z0-9 _-]+$/", $pseudo) || strlen($pseudo)> 60){
				$results['error']=true;
				$results["message"]["pseudo"]= "Pseudo invalide";
				
			}else{
				// Verifier que le pseudo n'existe pas
				$requete= $db->prepare("SELECT id FROM users WHERE pseudo = :pseudo");
				$requete->execute([':pseudo' => $pseudo]);
				$row = $requete->fetch();
				if($row){
					$results['error']=true;
					$results["message"]["pseudo"]= "Le pseudo est deja pris";
				
					
				}
				
			}
			// Verification de l'email
			if(!filter_var($email, FILTER_VALIDATE_EMAIL)){
				$results['error']=true;
				$results["message"]["email"]= "Email invalide";
				
			}else{
				// Verifier que l'email n'existe pas
				$requete= $db->prepare("SELECT id FROM users WHERE email = :email");
				$requete->execute([':email' => $email]);
				$row = $requete->fetch();
				if($row){
					$results['error']=true;
					$results["message"]["email"]= "L'email existe deja";
				
					
				}
			}
			// Verification du password
			if($password !== $password2){
				$results['error']=true;
				$results["message"]["password"]= "Les mots de passes doivent etre identiques";
				
			}
			if($results["error"] === false){
				$password = password_hash($password, PASSWORD_BCRYPT);
				
				//insertion 
				 $sql = $db->prepare("INSERT INTO users(nom,prenom,pseudo,email,password)VALUES(:nom, :prenom, :pseudo, :email, :password)");
				$sql->execute([":nom" => $nom ,":prenom" => $prenom, ":pseudo" => $pseudo, ":email" => $email, ":password" => $password]);
				if(!$sql){
					$results['error']=true;
					$results["message"]= "Erreur lors de l'insertion";
									
				}
			
			}
			
		}else{
			$results["error"]= true;
			$results["message"] = "Veuillez remplir tous les champs";
		}
		echo json_encode($results);
	}
?>