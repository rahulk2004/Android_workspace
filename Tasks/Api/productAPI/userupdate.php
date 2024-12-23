<?php

	include('connect.php');
	
	$id = $_POST['id'];
	$name = $_POST['name'];
	$surname = $_POST['surname'];
	$email = $_POST['email'];
	$password = $_POST['password'];
	
	$Sql_Query = "UPDATE user2 SET name='$name' , surname ='$surname' , email= '$email', password= '$password' WHERE id = '$id'  ";
	
	if(mysqli_query($con,$Sql_Query))
	{
		echo'Record Updated Succesfully ';
	}
	else
	{
		echo'Something went wrong ';
	}
	
	mysqli_close($con);


?> 