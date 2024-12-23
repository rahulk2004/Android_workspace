<?php

	include('connect.php');
	$sql = "select * from products2";
	
	$r = mysqli_query($con,$sql);
	$response = array();
	
	while($value = mysqli_fetch_array($r))
	{
		$row["pid"] = $value["pid"];
		$row["pname"] = $value["pname"];
		$row["pprice"] = $value["pprice"];
		$row["pdesc"] = $value["pdesc"];
		$row["pimage"] = $value["pimage"];
		$row["pstatus"] = $value["pstatus"];
		
		array_push($response,$row);
		
		
		
	}
	
	echo json_encode($response);
	
	


?>


