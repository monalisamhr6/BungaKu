<?php

include 'koneksi.php';

$st = $koneksi->prepare("insert into temp_order values(?,?,?)");
$st->bind_param("sii", $_GET["pengunjungId"], $_GET["produkId"], $_GET["qty"]);
$st->execute();
