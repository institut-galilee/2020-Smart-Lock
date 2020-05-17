<?php
$connection = mysqli_connect("localhost", "root" ,"", "application_login");
$result = array();
$result['data'] = array();
$select= "SELECT * FROM historique";
$responce = mysqli_query($connection,$select);

while($row = mysqli_fetch_array($responce)){
	
	$index['id'] = $row['1'];
	$index['pseudo'] = $row['2'];
	$index['statut'] = $row['3'];
	
	array_push($result['data'], $index);
	
	
}
	$result["success"]="1";
	echo json_encode($result);
	
?>