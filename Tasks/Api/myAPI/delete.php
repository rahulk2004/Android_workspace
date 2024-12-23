<?php

	include('connection.php');
	
	$id = $_POST['id'];
	
	$Sql_Query = "DELETE FROM info WHERE id = '$id' ";
	
	if(mysqli_query($con,$Sql_Query))
	{
		echo'Record Deleted Successfully';
	}
	else
	{
		echo'something went wrong';
	}
	
	mysqli_close($con);
	
?>
