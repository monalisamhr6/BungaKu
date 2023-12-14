<?php

include 'koneksi.php';

$st_check = $koneksi->prepare("select distinct kategori from produk");
$st_check->execute();
$rs = $st_check->get_result();
$arr = array();
while($row = $rs->fetch_assoc()){
    array_push($arr, $row);
}

echo json_encode($arr);
