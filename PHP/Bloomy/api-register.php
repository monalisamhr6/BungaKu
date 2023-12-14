<?php

include 'koneksi.php';

$username = $_POST['username'];
$password = $_POST['password'];
$nama = $_POST['nama'];
$email = $_POST['email'];

$queryRegister = "SELECT * FROM pengunjung WHERE username = '".$username."'";

$msql = mysqli_query($koneksi, $queryRegister);

$result = mysqli_num_rows($msql);

if(!empty($username) && !empty($password) && !empty($nama) && !empty($email)){
    if($result == 0){
        $regis = "INSERT INTO pengunjung (username, password, nama, email) 
        VALUES ('$username', '$password', '$nama', '$email')";

        $msqlRegis = mysqli_query($koneksi, $regis);

        echo "Daftar Berhasil Ditambahkan";
    }else{
        echo "Username Sudah Digunakan";
    }

}else{
    echo "Semua Data Harus Diisi";
}