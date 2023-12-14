<?php
// Koneksi ke database
include 'koneksi.php';

// Query untuk mengambil data keranjang
$query = "SELECT * FROM keranjang";

// Eksekusi query
$result = mysqli_query($koneksi, $query);

// Membuat array untuk menyimpan data keranjang
$cartItems = array();

// Mengambil data dan menyimpannya ke dalam array
while ($row = mysqli_fetch_assoc($result)) {
    $cartItems[] = $row;
}

// Mengirim data dalam format JSON
echo json_encode($cartItems);

// Menutup koneksi ke database
mysqli_close($koneksi);
?>
