<?php

include 'koneksi.php';

$username = $_GET['username'];

$query = "SELECT role FROM pengunjung WHERE username = '$username'";
$result = mysqli_query($koneksi, $query);

$response = array();

while ($row = mysqli_fetch_assoc($result)) {
    $response[] = $row;
}

echo json_encode($response);

?>
