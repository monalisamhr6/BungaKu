<?php

$hostName = "localhost";
$userName = "root";
$password = "";
$dbName = "bloomy";

$koneksi = mysqli_connect($hostName, $userName, $password, $dbName);

if(mysqli_connect_errno()){
    echo "Gagal konek dengan database" . mysqli_connect_errno();
    
}