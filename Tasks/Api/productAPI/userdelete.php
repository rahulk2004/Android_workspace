<?php

	include('connect.php');
	
	$id = $_POST['id'];
	
	$Sql_Query = "DELETE FROM user2 WHERE id = '$id' ";
	
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
