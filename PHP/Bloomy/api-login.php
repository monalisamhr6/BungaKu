<?php

include 'koneksi.php';

$username = $_GET['username']; // Menggunakan $_GET karena di Kotlin Anda menggunakan Request.Method.GET
$password = $_GET['password'];

$cek = "SELECT * FROM pengunjung WHERE username = '$username' AND password = '$password'";
$msql = mysqli_query($koneksi, $cek);
$result = mysqli_num_rows($msql);

if (!empty($username) && !empty($password)) {
    if ($result > 0) {
        echo "Login Berhasil";
    } else {
        echo "Login Gagal";
    }
} else {
    echo "Ada Data Yang Masih Kosong";
}

?>