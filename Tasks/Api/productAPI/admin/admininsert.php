<?php

		include('connect.php');
		
		$name = $_POST["name"];
		$surname = $_POST["surname"];
		$email = $_POST["email"];
		$password = $_POST["password"];
		
		
		if($name=="" && $surname=="" && $email==""  && $password=="password")
		{
			echo'0';
		}
		else
		{
			$sql = "insert into admin2(name,surname,email,password) values ('$name','$surname','$email','$password')";
			mysqli_query($con,$sql);
			
		}
		


?>