<?php

include 'koneksi.php';


    $namaProduk = $_POST['namaProduk'];
    $harga = $_POST['harga'];
    // Ambil parameter lain yang dibutuhkan

    // Implementasi logika untuk menyimpan produk ke dalam tabel keranjang
    // Misalnya, menggunakan pernyataan SQL INSERT
    $st = $koneksi->prepare("INSERT INTO keranjang (namaProduk, harga) VALUES (?, ?)");
    $st->bind_param("sd", $namaProduk, $harga);
    if ($st->execute()) {
        echo json_encode(array('status' => 'success'));
    } else {
        echo json_encode(array('status' => 'failed'));
    }

?>
