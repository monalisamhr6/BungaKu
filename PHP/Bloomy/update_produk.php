<?php
include 'koneksi.php';

$id_produk = $_POST['id_produk']; // Ambil ID produk dari POST request
$namaProduk = $_POST['namaProduk'];
$harga = $_POST['harga'];
$deskripsi = $_POST['deskripsi'];
$stok = $_POST['stok'];

// ...
// Tambahkan logika untuk kategori, gambar, dll.
// ...

$query = $koneksi->prepare("UPDATE produk SET namaProduk=?, harga=?, deskripsi=?, stok=? WHERE id_produk=?");
$query->bind_param("sdsii", $namaProduk, $harga, $deskripsi, $stok, $id_produk);

$response = array();
if ($query->execute()) {
    $response['success'] = true;
    $response['message'] = "Produk berhasil diperbarui";
} else {
    $response['success'] = false;
    $response['message'] = "Gagal memperbarui produk";
}

echo json_encode($response);
?>
