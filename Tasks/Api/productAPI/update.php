<?php

// Including database connection
include('connect.php');

// Getting the product details from the POST request
$pid = $_POST['pid'];
$pname = $_POST['pname'];
$pprice = $_POST['pprice'];
$pdesc = $_POST['pdesc'];
$pstatus = $_POST['pstatus'];

// Default image if no new image is uploaded
$pimage = $_POST['pimage'];

// Check if a new image is uploaded
if (isset($_FILES['p_image']) && $_FILES['p_image']['error'] == 0) {
    // Define the upload path
    $upload_path = 'images/';

    // Get the file info and extension
    $fileinfo = pathinfo($_FILES["p_image"]["name"]);
    $extension = $fileinfo["extension"];
    $random = 'image_' . rand(1000,9999);

    // Create the new file path and URL
    $file_url = 'https://' . $_SERVER['SERVER_NAME'] . "/productAPI/" . $upload_path . $random . '.' . $extension;
    $file_path = $upload_path . $random . '.' . $extension;

    // Move the uploaded file to the server directory
    move_uploaded_file($_FILES["p_image"]["tmp_name"], $file_path);

    // Update the image URL
    $pimage = $file_url;
}

// SQL query to update product details
$Sql_Query = "UPDATE products2 SET pname='$pname', pprice='$pprice', pdesc='$pdesc', pstatus='$pstatus', pimage='$pimage' WHERE pid='$pid'";

// Execute the query
if (mysqli_query($con, $Sql_Query)) {
    echo 'Record Updated Successfully';
} else {
    echo 'Something went wrong';
}

// Closing the database connection
mysqli_close($con);
?>
