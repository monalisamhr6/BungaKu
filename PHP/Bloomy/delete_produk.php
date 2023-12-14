<?php

include 'koneksi.php';
$id_produk = $_POST['id_produk'];

    // Persiapkan statement SQL untuk menghapus produk
    $st = $koneksi->prepare("DELETE FROM produk WHERE id_produk = ?");
    $st->bind_param("s", $id_produk);

    // Eksekusi statement SQL
    if ($st->execute()) {
        // Jika penghapusan berhasil, kirim respons JSON ke aplikasi Android
        $response['success'] = true;
        $response['message'] = "Produk berhasil dihapus";
    } else {
        // Jika penghapusan gagal, kirim respons JSON ke aplikasi Android
        $response['success'] = false;
        $response['message'] = "Gagal menghapus produk";
    }

echo json_encode($response);