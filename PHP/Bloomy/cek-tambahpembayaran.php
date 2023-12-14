<?php

include 'koneksi.php';

$tipePembayaran = $_GET['tipePembayaran'];
    $aturPengiriman = $_GET['aturPengiriman'];
    $waktu = $_GET['waktu'];

    // Menyesuaikan kolom tipepembayaran untuk disimpan di database
    $tipePembayaranDatabase = ($tipePembayaran == "COD") ? "Cash On Delivery" : "Mobile Banking";

    // Menyesuaikan kolom aturpengiriman untuk disimpan di database
    $aturPengirimanDatabase = ($aturPengiriman == "delivery") ? "Delivery" : "Pickup";

    // Query untuk menyimpan data ke dalam database tanpa menggunakan ID pembayaran
    $insertMetodePembayaran = "INSERT INTO metodepembayaran (tipepembayaran, aturpengiriman, waktu) 
    VALUES ('$tipePembayaranDatabase', '$aturPengirimanDatabase', '$waktu')";

    $resultInsert = mysqli_query($koneksi, $insertMetodePembayaran);

    if ($resultInsert) {
        echo "Metode Pembayaran Berhasil Ditambahkan";
    } else {
        echo "Gagal menambahkan metode pembayaran. Error: " . mysqli_error($koneksi);
    }


?>
