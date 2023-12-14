<?php

include 'koneksi.php';

$st = $koneksi->prepare("SELECT id_produk,namaProduk,harga,qty,pengunjungId from temp_order INNER JOIN produk on produk.id_produk = temp_order.produkId WHERE pengunjungId=?");
$st->bind_param("s", $_GET["pengunjungId"]);
$st->execute();
$rs=$st->get_result();
$arr=array();
while($row = $rs->fetch_assoc()){
    array_push($arr, $row);
}

echo json_encode($arr);

