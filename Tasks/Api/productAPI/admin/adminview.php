<?php

	include('connect.php');
	$sql = "select * from admin2";
	
	$r = mysqli_query($con,$sql);
	$response = array();
	
	while($value = mysqli_fetch_array($r))
	{
		$row["id"] = $value["id"];
		$row["name"] = $value["name"];
		$row["surname"] = $value["surname"];
		$row["email"] = $value["email"];
		$row["password"] = $value["password"];
		
		array_push($response,$row);
		
		
		
	}
	
	echo json_encode($response);
	
	



?>