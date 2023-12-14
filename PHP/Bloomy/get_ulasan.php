<?php

include 'koneksi.php';

$st = $koneksi->prepare("select review,rating from ulasan ");
$st->execute();
$rs = $st->get_result();
$arr = array();
while($row = $rs->fetch_assoc()){
    array_push($arr, $row);
}

echo json_encode($arr);