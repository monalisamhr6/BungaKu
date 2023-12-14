-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 28 Nov 2023 pada 07.34
-- Versi server: 10.4.28-MariaDB
-- Versi PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bloomy`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `produk`
--

CREATE TABLE `produk` (
  `id_produk` int(11) NOT NULL,
  `namaProduk` varchar(120) NOT NULL,
  `deskripsi` varchar(700) NOT NULL,
  `harga` double NOT NULL,
  `gambar` varchar(255) NOT NULL,
  `stok` int(11) NOT NULL,
  `kategori` varchar(120) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `produk`
--

INSERT INTO `produk` (`id_produk`, `namaProduk`, `deskripsi`, `harga`, `gambar`, `stok`, `kategori`) VALUES
(1, 'bunga 8', 'bunga 8', 7000, '65609fc822eae.png', 90, 'bunga mawar'),
(34, 'bunga 1', 'bunga 1', 5000, '656196b2909b0.png', 76, 'bunga 1'),
(35, 'bunga 2', 'bunga 2', 5000, '656196cbe5fb6.png', 76, 'bunga 1'),
(36, 'bunga 3', 'bunga 3', 6000, '656196e43013d.png', 76, 'bunga mawar'),
(37, 'bunga 4', 'bunga 4', 6000, '656196ef6d5ba.png', 76, 'bunga mawar'),
(38, 'bunga 5', 'bunga 5', 6000, '65619702d1822.png', 76, 'bunga buket'),
(39, 'bunga 6', 'bunga 6', 6000, '6561970b3ee16.png', 76, 'bunga buket'),
(40, '', '', 0, '6561ae3a2528a.png', 0, ''),
(41, 'bunga 7', 'bunga 7', 7000, '6561ae82ba3dd.png', 89, 'bunga mawar'),
(42, 'bunga 8', 'bunga 8', 7000, '', 0, ''),
(44, 'buket', 'buket', 0, '656237467d8a1.png', 0, 'bunga 1'),
(45, 'mona', 'mona', 2000, '6565684fed54f.png', 12, 'bunga mawar'),
(46, 'mona', 'mona', 2000, '6565687ef0978.png', 12, 'bunga mawar');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `produk`
--
ALTER TABLE `produk`
  ADD PRIMARY KEY (`id_produk`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `produk`
--
ALTER TABLE `produk`
  MODIFY `id_produk` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=47;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
