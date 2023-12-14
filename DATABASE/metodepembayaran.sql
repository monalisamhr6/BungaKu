-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Waktu pembuatan: 28 Nov 2023 pada 07.33
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
-- Struktur dari tabel `metodepembayaran`
--

CREATE TABLE `metodepembayaran` (
  `tipepembayaran` varchar(100) NOT NULL,
  `IDpembayaran` int(100) NOT NULL,
  `aturpengiriman` varchar(100) NOT NULL,
  `waktu` varchar(225) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data untuk tabel `metodepembayaran`
--

INSERT INTO `metodepembayaran` (`tipepembayaran`, `IDpembayaran`, `aturpengiriman`, `waktu`) VALUES
('MBANKING', 1, 'Self Pickup', ''),
('COD', 2, 'Self Pickup', ''),
('M-Banking', 142, 'Pickup', '16.30'),
('COD', 143, 'Pickup', '14.20'),
('COD', 144, 'Pickup', '14.20'),
('COD', 145, 'Pickup', '18.30'),
('Cash On Delivery', 146, 'Pickup', '10.15'),
('Cash On Delivery', 147, 'Pickup', '10.15'),
('Cash On Delivery', 148, 'Pickup', '10.15'),
('Mobile Banking', 149, 'Pickup', '12.34');

--
-- Indexes for dumped tables
--

--
-- Indeks untuk tabel `metodepembayaran`
--
ALTER TABLE `metodepembayaran`
  ADD PRIMARY KEY (`IDpembayaran`);

--
-- AUTO_INCREMENT untuk tabel yang dibuang
--

--
-- AUTO_INCREMENT untuk tabel `metodepembayaran`
--
ALTER TABLE `metodepembayaran`
  MODIFY `IDpembayaran` int(100) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=150;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
