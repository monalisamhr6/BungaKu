<?php

include 'koneksi.php';

// Ambil nilai dari parameter POST
$tipePembayaran = $_POST['tipePembayaran'];
$aturPengiriman = $_POST['aturPengiriman'];
$waktu = $_POST['waktu'];
$cartProducts = json_decode($_POST['cartProducts'], true); // Mengambil data produk dari keranjang

// Ambil nilai waktu sebagai string
if (!empty($tipePembayaran) && !empty($aturPengiriman) && !empty($waktu) && !empty($cartProducts)) {
    // Menyesuaikan kolom tipepembayaran untuk disimpan di database
    $tipePembayaranDatabase = ($tipePembayaran == "COD") ? "Cash On Delivery" : "Mobile Banking";

    // Menyesuaikan kolom aturpengiriman untuk disimpan di database
    $aturPengirimanDatabase = ($aturPengiriman == "delivery") ? "Delivery" : "Pickup";

    // Query untuk menyimpan data metode pembayaran ke dalam database
    $insertMetodePembayaran = "INSERT INTO metodepembayaran (tipepembayaran, aturpengiriman, waktu) 
        VALUES ('$tipePembayaranDatabase', '$aturPengirimanDatabase', '$waktu')";

    $msqlInsertMetodePembayaran = mysqli_query($koneksi, $insertMetodePembayaran);

    if ($msqlInsertMetodePembayaran) {
        // Jika metode pembayaran berhasil disimpan, simpan data produk ke dalam tabel metode_pembayaran
        $lastInsertedId = mysqli_insert_id($koneksi);

        foreach ($cartProducts as $product) {
            $namaProduk = $product['namaProduk'];
            $harga = $product['harga'];
            $quantity = $product['quantity'];

            // Query untuk menyimpan data produk ke dalam tabel metode_pembayaran
            $insertProduct = "INSERT INTO checkoutitem (id_produk, namaProduk, harga, quantity) 
                VALUES ('$lastInsertedId', '$namaProduk', '$harga', '$quantity')";
            
            $msqlInsertProduct = mysqli_query($koneksi, $insertProduct);

            if (!$msqlInsertProduct) {
                echo "Gagal menyimpan data produk. Error: " . mysqli_error($koneksi);
                exit;
            }
        }

        echo "Metode Pembayaran dan Data Produk Berhasil Ditambahkan";
    } else {
        echo "Gagal menambahkan metode pembayaran. Error: " . mysqli_error($koneksi);
    }
} else {
    echo "Semua Data Harus Diisi";
}

?>
