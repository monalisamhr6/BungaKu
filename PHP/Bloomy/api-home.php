<?php
include 'koneksi.php';

$sql = "SELECT * FROM produk";
$result = $koneksi->query($sql);

if ($result->num_rows > 0) {
    $response = array();

    while ($row = $result->fetch_assoc()) {
        $response[] = $row;
    }

    echo json_encode($response);
} else {
    echo "No products found";
}
    
$koneksi->close();
?>
