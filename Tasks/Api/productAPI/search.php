<?php
include 'connect.php'; 


$query = isset($_GET['query']) ? $_GET['query'] : '';
$query = mysqli_real_escape_string($con, $query);


$sql = "SELECT * FROM products2 WHERE pname LIKE '%$query%'";
$result = mysqli_query($con, $sql);

$data = array();


while ($row = mysqli_fetch_assoc($result)) {
    $data[] = $row;
}


header('Content-Type: application/json');
echo json_encode($data);
?>