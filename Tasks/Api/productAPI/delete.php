<?php
	
	include('connect.php');
	
	$pid = $_POST['pid'];
	
	$Sql_Query = "DELETE FROM products2 WHERE pid = '$pid' ";
	
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