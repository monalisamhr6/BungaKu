<?php

include 'koneksi.php';


    $namaProduk = $_POST['namaProduk'];
    $deskripsi = $_POST['deskripsi'];
    $harga = $_POST['harga'];
    $stok = $_POST['stok'];
    $kategori = $_POST['kategori'];
    $gambarBase64 = $_POST['gambar']; // Ambil string base64 dari parameter POST

    // Decode string base64 ke dalam bentuk data biner
    $gambarBinary = base64_decode($gambarBase64);

    // Proses upload gambar ke server dan dapatkan nama file
    $imageFileName = uploadImage($gambarBinary);

    // Simpan informasi produk dan nama file gambar ke database
    $query = "INSERT INTO produk (namaProduk, deskripsi, harga, gambar, stok, kategori) VALUES ('$namaProduk', '$deskripsi', '$harga', '$imageFileName', '$stok', '$kategori')";
    $result = mysqli_query($koneksi, $query);

    if ($result) {
        echo json_encode(array('response' => true, 'message' => 'Produk berhasil ditambahkan'));
    } else {
        echo json_encode(array('response' => false, 'message' => 'Gagal menambahkan produk'));
    }


function uploadImage($gambarBinary) {
    $uploadDir = "uploads/";
    $imageFileName = uniqid() . '.png'; // Nama file unik, bisa disesuaikan dengan kebutuhan
    $uploadedFile = $uploadDir . $imageFileName;

    // Simpan data biner gambar ke dalam file
    file_put_contents($uploadedFile, $gambarBinary);

    return $imageFileName;
}

?>
