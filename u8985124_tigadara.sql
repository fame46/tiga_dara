-- phpMyAdmin SQL Dump
-- version 4.9.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Feb 08, 2021 at 02:46 AM
-- Server version: 10.3.27-MariaDB
-- PHP Version: 7.3.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `u8985124_tigadara`
--

-- --------------------------------------------------------

--
-- Table structure for table `tb_meja`
--

CREATE TABLE `tb_meja` (
  `id` int(11) NOT NULL,
  `meja` int(11) NOT NULL,
  `created_on` datetime NOT NULL DEFAULT current_timestamp(),
  `created_by` varchar(100) NOT NULL,
  `modified_on` datetime DEFAULT NULL,
  `modified_by` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tb_meja`
--

INSERT INTO `tb_meja` (`id`, `meja`, `created_on`, `created_by`, `modified_on`, `modified_by`) VALUES
(1, 1, '2021-01-02 22:40:01', 'admin', NULL, NULL),
(3, 2, '2021-01-03 21:09:12', 'admin', NULL, NULL),
(4, 3, '2021-01-03 21:11:16', 'admin', NULL, NULL),
(5, 4, '2021-01-14 21:44:30', 'admin', NULL, NULL),
(6, 5, '2021-01-15 00:31:04', 'admin', NULL, NULL),
(7, 6, '2021-01-15 00:31:10', 'admin', NULL, NULL),
(8, 7, '2021-01-15 00:31:15', 'admin', NULL, NULL),
(9, 9, '2021-01-26 13:22:31', 'admin', NULL, NULL),
(10, 10, '2021-01-30 09:07:06', 'admin', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `tb_menu`
--

CREATE TABLE `tb_menu` (
  `id` int(11) NOT NULL,
  `nama_menu` varchar(100) NOT NULL,
  `harga` int(11) NOT NULL,
  `gambar` varchar(100) NOT NULL,
  `jenis` varchar(50) NOT NULL,
  `create_on` datetime NOT NULL DEFAULT current_timestamp(),
  `create_by` varchar(50) NOT NULL,
  `modified_on` datetime DEFAULT NULL,
  `modified_by` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tb_menu`
--

INSERT INTO `tb_menu` (`id`, `nama_menu`, `harga`, `gambar`, `jenis`, `create_on`, `create_by`, `modified_on`, `modified_by`) VALUES
(153, 'Sate Kambing', 35000, 'Sate Kambing.JPG', 'makanan', '2021-01-27 19:45:36', 'admin', NULL, NULL),
(154, 'Gulai Kambing', 24000, 'Gulai Kambing.JPG', 'makanan', '2021-01-27 19:46:05', 'admin', NULL, NULL),
(155, 'Nasi Goreng Kambing', 20000, 'Nasi Goreng Kambing.JPG', 'makanan', '2021-01-27 19:46:34', 'admin', NULL, NULL),
(156, 'Nasi Goreng Ayam', 13000, 'Nasi Goreng Ayam.JPG', 'makanan', '2021-01-27 19:46:53', 'admin', NULL, NULL),
(157, 'Tongseng', 27000, 'Tongseng.JPG', 'makanan', '2021-01-27 19:47:14', 'admin', NULL, NULL),
(158, 'Mi Goreng Komplit', 12000, 'Mi Goreng Komplit.JPG', 'makanan', '2021-01-27 19:47:48', 'admin', NULL, NULL),
(159, 'Mi Kuah Komplit', 11000, 'Mi Kuah Komplit.JPG', 'makanan', '2021-01-27 19:48:15', 'admin', NULL, NULL),
(160, 'Indomi Goreng', 10000, 'Indomi Goreng.JPG', 'makanan', '2021-01-27 19:50:39', 'admin', NULL, NULL),
(161, 'Indomie Kuah', 9000, 'Indomie Kuah.JPG', 'makanan', '2021-01-27 19:50:57', 'admin', NULL, NULL),
(162, 'Es Teh', 4000, 'Es Teh.JPG', 'minuman', '2021-01-27 19:51:21', 'admin', NULL, NULL),
(163, 'Es Jeruk', 8000, 'Es Jeruk.JPG', 'minuman', '2021-01-27 19:51:39', 'admin', NULL, NULL),
(164, 'Es Lemon Tea', 7000, 'Es Lemon Tea.JPG', 'minuman', '2021-01-27 19:52:01', 'admin', NULL, NULL),
(166, 'Lemon Tea Panas', 6000, 'Lemon Tea Panas.JPG', 'minuman', '2021-01-27 19:52:43', 'admin', NULL, NULL),
(168, 'Jus Mangga', 12000, 'Jus Mangga.JPG', 'minuman', '2021-01-27 19:53:30', 'admin', NULL, NULL),
(169, 'Soda Susu', 10000, 'Soda Susu.JPG', 'minuman', '2021-01-27 19:53:53', 'admin', NULL, NULL),
(170, 'Jus Alpukat', 13000, 'Jus Alpukat.JPG', 'minuman', '2021-01-27 19:54:13', 'admin', NULL, NULL),
(171, 'Air Mineral', 5000, 'Air Mineral.JPG', 'minuman', '2021-01-27 19:54:39', 'admin', NULL, NULL),
(172, 'Jeruk Panas', 6000, 'Jeruk Panas.JPG', 'minuman', '2021-01-27 19:54:59', 'admin', NULL, NULL),
(173, 'Sate Kambing Gofood', 42000, 'Sate Kambing Gofood.JPG', 'paket', '2021-01-27 19:56:01', 'admin', NULL, NULL),
(174, 'Gulai Kambing Gofood', 32000, 'Gulai Kambing Gofood.JPG', 'paket', '2021-01-27 19:56:51', 'admin', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `tb_pesanan`
--

CREATE TABLE `tb_pesanan` (
  `id` int(11) NOT NULL,
  `notrans` int(11) NOT NULL,
  `nama_menu` varchar(100) NOT NULL,
  `harga_menu` int(11) NOT NULL,
  `subTotal` int(11) NOT NULL,
  `jumlah` int(11) NOT NULL,
  `keterangan` varchar(100) NOT NULL,
  `atasnama` varchar(100) DEFAULT NULL,
  `status` int(11) NOT NULL,
  `nomeja` int(11) NOT NULL,
  `pelayan` varchar(100) DEFAULT NULL,
  `kasir` varchar(100) DEFAULT NULL,
  `dapur` varchar(100) DEFAULT NULL,
  `create_on` datetime NOT NULL DEFAULT current_timestamp(),
  `create_by` varchar(100) NOT NULL,
  `modified_on` datetime DEFAULT NULL,
  `modified_by` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tb_pesanan`
--

INSERT INTO `tb_pesanan` (`id`, `notrans`, `nama_menu`, `harga_menu`, `subTotal`, `jumlah`, `keterangan`, `atasnama`, `status`, `nomeja`, `pelayan`, `kasir`, `dapur`, `create_on`, `create_by`, `modified_on`, `modified_by`) VALUES
(52, 0, 'Sate', 40000, 40000, 1, 'di bungkus', 'mbokkene', 4, 7, 'pelayan', 'kasir', 'dapur', '2021-01-15 22:49:38', 'pelayan', '2021-01-15 22:52:42', 'pelayan'),
(53, 0, 'Sate', 40000, 40000, 1, 'makan di tempat', 'mbokkene', 4, 1, 'pelayan', 'kasir', 'dapur', '2021-01-15 22:52:12', 'pelayan', '2021-01-17 22:29:05', 'Kapten Ganang'),
(54, 0, 'Tongseng', 30000, 30000, 1, 'di bungkus', 'deva', 4, 3, 'pelayan', 'kasir', 'dapur', '2021-01-16 17:24:45', 'pelayan', '2021-01-17 22:29:17', 'Kapten Ganang'),
(55, 0, 'Sate Kambing', 45000, 45000, 1, 'di bungkus', 'deva', 4, 3, 'pelayan', 'kasir', 'dapur', '2021-01-16 17:24:50', 'pelayan', '2021-01-17 22:29:17', 'Kapten Ganang'),
(56, 0, 'Tongseng', 30000, 60000, 2, 'makan di tempat', 'juminten', 4, 3, 'pelayan', 'kasir', 'dapur', '2021-01-17 21:31:35', 'pelayan', '2021-01-17 22:29:08', 'Kapten Ganang'),
(57, 0, 'Sate Kambing', 45000, 45000, 1, 'makan di tempat', 'jaja', 4, 1, 'pelayan', 'kasir', 'dapur', '2021-01-17 21:32:47', 'pelayan', '2021-01-17 22:29:12', 'Kapten Ganang'),
(58, 0, 'Tongseng', 30000, 120000, 4, 'makan di tempat', 'ujang', 4, 1, 'pelayan', 'kasir', 'Cheff Yuni', '2021-01-17 21:44:05', 'pelayan', '2021-01-17 22:29:00', 'Kapten Ganang'),
(59, 0, 'Sate Kambing', 45000, 90000, 2, 'makan di tempat', 'ujang', 4, 1, 'pelayan', 'kasir', 'Cheff Yuni', '2021-01-17 21:44:13', 'pelayan', '2021-01-17 22:29:00', 'Kapten Ganang'),
(60, 0, 'Tongseng Kambing', 36000, 36000, 1, 'makan di tempat', 'Ruben', 4, 5, 'Kapten Ganang', 'Ayunda', 'Cheff Yuni', '2021-01-17 22:24:07', 'Kapten Ganang', '2021-01-17 22:28:56', 'Kapten Ganang'),
(61, 0, 'Nasi Goreng Kambing', 24000, 48000, 2, 'makan di tempat', 'Ruben', 4, 5, 'Kapten Ganang', 'Ayunda', 'Cheff Yuni', '2021-01-17 22:24:12', 'Kapten Ganang', '2021-01-17 22:28:56', 'Kapten Ganang'),
(62, 0, 'Sate Kambing', 45000, 45000, 1, 'makan di tempat', 'Ruben', 4, 5, 'Kapten Ganang', 'Ayunda', 'Cheff Yuni', '2021-01-17 22:24:18', 'Kapten Ganang', '2021-01-17 22:28:56', 'Kapten Ganang'),
(63, 0, 'Nasi Putih', 4500, 22500, 5, 'makan di tempat', 'Ruben', 4, 5, 'Kapten Ganang', 'Ayunda', 'Cheff Yuni', '2021-01-17 22:24:29', 'Kapten Ganang', '2021-01-17 22:28:56', 'Kapten Ganang'),
(64, 0, 'Es Jeruk', 10000, 10000, 1, 'makan di tempat', 'Ruben', 4, 5, 'Kapten Ganang', 'Ayunda', 'Cheff Yuni', '2021-01-17 22:24:34', 'Kapten Ganang', '2021-01-17 22:28:56', 'Kapten Ganang'),
(65, 0, 'Es Teh', 5000, 15000, 3, 'makan di tempat', 'Ruben', 4, 5, 'Kapten Ganang', 'Ayunda', 'Cheff Yuni', '2021-01-17 22:24:38', 'Kapten Ganang', '2021-01-17 22:28:56', 'Kapten Ganang'),
(66, 0, 'Kopi Caramel', 20000, 20000, 1, 'makan di tempat', 'Ruben', 4, 5, 'Kapten Ganang', 'Ayunda', 'Cheff Yuni', '2021-01-17 22:24:49', 'Kapten Ganang', '2021-01-17 22:28:56', 'Kapten Ganang'),
(67, 0, 'Tongseng Kambing', 36000, 36000, 1, 'makan di tempat', 'Juminten', 2, 1, 'Kapten Ganang', 'Ayunda', NULL, '2021-01-18 13:56:34', 'Kapten Ganang', '2021-01-18 13:57:58', 'Ayunda'),
(68, 0, 'Sate Ayam', 15000, 15000, 1, 'makan di tempat', 'Juminten', 2, 1, 'Kapten Ganang', 'Ayunda', NULL, '2021-01-18 13:56:38', 'Kapten Ganang', '2021-01-18 13:57:58', 'Ayunda'),
(69, 0, 'Nasi Putih', 4500, 9000, 2, 'makan di tempat', 'Juminten', 2, 1, 'Kapten Ganang', 'Ayunda', NULL, '2021-01-18 13:56:45', 'Kapten Ganang', '2021-01-18 13:57:58', 'Ayunda'),
(70, 0, 'Es Teh', 5000, 10000, 2, 'makan di tempat', 'Juminten', 2, 1, 'Kapten Ganang', 'Ayunda', NULL, '2021-01-18 13:56:57', 'Kapten Ganang', '2021-01-18 13:57:58', 'Ayunda'),
(71, 0, 'Nasi Goreng Kambing', 24000, 48000, 2, 'makan di tempat', 'agung', 4, 1, 'Kapten Ganang', 'Ayunda', 'Cheff Yuni', '2021-01-18 18:49:53', 'Kapten Ganang', '2021-01-18 18:52:07', 'Kapten Ganang'),
(72, 0, 'Nasi Putih', 4500, 9000, 2, 'makan di tempat', 'agung', 4, 1, 'Kapten Ganang', 'Ayunda', 'Cheff Yuni', '2021-01-18 18:49:57', 'Kapten Ganang', '2021-01-18 18:52:07', 'Kapten Ganang'),
(73, 0, 'Es Jeruk', 10000, 10000, 1, 'makan di tempat', 'agung', 4, 1, 'Kapten Ganang', 'Ayunda', 'Cheff Yuni', '2021-01-18 18:50:01', 'Kapten Ganang', '2021-01-18 18:52:07', 'Kapten Ganang'),
(74, 0, 'Es Teh', 5000, 5000, 1, 'makan di tempat', 'agung', 4, 1, 'Kapten Ganang', 'Ayunda', 'Cheff Yuni', '2021-01-18 18:50:06', 'Kapten Ganang', '2021-01-18 18:52:07', 'Kapten Ganang'),
(75, 0, 'Jus Mangga', 15000, 15000, 1, 'makan di tempat', 'agung', 4, 1, 'Kapten Ganang', 'Ayunda', 'Cheff Yuni', '2021-01-18 18:50:08', 'Kapten Ganang', '2021-01-18 18:52:07', 'Kapten Ganang'),
(76, 0, 'Sate Ayam', 15000, 15000, 1, 'makan di tempat', 'anto', 2, 3, 'Kapten Ganang', 'Ayunda', NULL, '2021-01-19 09:32:27', 'Kapten Ganang', '2021-01-19 09:33:46', 'Ayunda'),
(77, 0, 'Nasi Goreng Kambing', 24000, 24000, 1, 'makan di tempat', 'anto', 2, 3, 'Kapten Ganang', 'Ayunda', NULL, '2021-01-19 09:32:31', 'Kapten Ganang', '2021-01-19 09:33:46', 'Ayunda'),
(78, 0, 'Kopi Hitam', 8000, 8000, 1, 'makan di tempat', 'anto', 2, 3, 'Kapten Ganang', 'Ayunda', NULL, '2021-01-19 09:32:38', 'Kapten Ganang', '2021-01-19 09:33:46', 'Ayunda'),
(79, 0, 'Jus Mangga', 15000, 45000, 3, 'makan di tempat', 'anto', 2, 3, 'Kapten Ganang', 'Ayunda', NULL, '2021-01-19 09:32:41', 'Kapten Ganang', '2021-01-19 09:33:46', 'Ayunda'),
(80, 0, 'Nasi Putih', 4500, 13500, 3, 'makan di tempat', 'anto', 2, 3, 'Kapten Ganang', 'Ayunda', NULL, '2021-01-19 09:32:45', 'Kapten Ganang', '2021-01-19 09:33:46', 'Ayunda'),
(81, 0, 'Nasi Goreng Kambing', 24000, 24000, 1, 'makan di tempat', 'labib', 2, 5, 'Kapten Ganang', 'Ayunda', NULL, '2021-01-19 19:09:07', 'Kapten Ganang', '2021-01-19 19:09:53', 'Ayunda'),
(82, 0, 'Sate Kambing', 45000, 90000, 2, 'makan di tempat', 'labib', 2, 5, 'Kapten Ganang', 'Ayunda', NULL, '2021-01-19 19:09:11', 'Kapten Ganang', '2021-01-19 19:09:53', 'Ayunda'),
(83, 0, 'Jus Mangga', 15000, 15000, 1, 'makan di tempat', 'labib', 2, 5, 'Kapten Ganang', 'Ayunda', NULL, '2021-01-19 19:09:16', 'Kapten Ganang', '2021-01-19 19:09:53', 'Ayunda'),
(84, 0, 'Soda Gembira', 12000, 12000, 1, 'makan di tempat', 'labib', 2, 5, 'Kapten Ganang', 'Ayunda', NULL, '2021-01-19 19:09:20', 'Kapten Ganang', '2021-01-19 19:09:53', 'Ayunda'),
(85, 0, 'Tongseng Kambing', 36000, 36000, 1, 'makan di tempat', 'Safa', 2, 2, 'Kapten Ganang', 'Ayunda', NULL, '2021-01-21 19:23:16', 'Kapten Ganang', '2021-01-21 19:24:26', 'Ayunda'),
(86, 0, 'Es Jeruk', 10000, 10000, 1, 'makan di tempat', 'Safa', 2, 2, 'Kapten Ganang', 'Ayunda', NULL, '2021-01-21 19:23:22', 'Kapten Ganang', '2021-01-21 19:24:26', 'Ayunda'),
(93, 0, 'Tongseng Kambing', 36000, 72000, 2, 'makan di tempat', 'kucluk', 2, 3, 'Kapten Ganang', 'Ayunda', NULL, '2021-01-22 21:20:58', 'Kapten Ganang', '2021-01-23 20:46:45', 'Ayunda'),
(94, 2101220001, 'Tongseng Kambing', 36000, 36000, 1, 'makan di tempat', 'teta', 1, 1, 'a', NULL, NULL, '2021-01-22 21:42:15', 'a', '2021-01-22 23:27:00', 'a'),
(95, 2101220002, 'Sate Ayam', 15000, 15000, 1, 'di bungkus', 'tera', 2, 4, 'a', 'a', NULL, '2021-01-22 23:27:28', 'a', '2021-01-22 23:41:07', 'a'),
(96, 0, 'Jus Mangga', 15000, 15000, 1, 'makan di tempat', 'kucluk', 2, 3, 'Kapten Ganang', 'Ayunda', NULL, '2021-01-23 20:45:42', 'Kapten Ganang', '2021-01-23 20:46:45', 'Ayunda'),
(97, 0, 'Tongseng Kambing', 36000, 36000, 1, 'makan di tempat', 'jefri', 3, 1, 'Kapten Ganang', 'Ayunda', 'Cheff Yuni', '2021-01-26 13:00:35', 'Kapten Ganang', '2021-01-26 13:27:38', 'Cheff Yuni'),
(98, 0, 'Sate Ayam', 15000, 30000, 2, 'makan di tempat', 'Danang', 4, 4, 'Kapten Ganang', 'Ayunda', 'Cheff Yuni', '2021-01-26 13:23:58', 'Kapten Ganang', '2021-01-26 13:26:31', 'Kapten Ganang'),
(99, 0, 'Es Jeruk', 10000, 10000, 1, 'makan di tempat', 'Danang', 4, 4, 'Kapten Ganang', 'Ayunda', 'Cheff Yuni', '2021-01-26 13:24:05', 'Kapten Ganang', '2021-01-26 13:26:31', 'Kapten Ganang'),
(100, 0, 'Es Teh', 5000, 5000, 1, 'makan di tempat', 'Danang', 4, 4, 'Kapten Ganang', 'Ayunda', 'Cheff Yuni', '2021-01-26 13:24:06', 'Kapten Ganang', '2021-01-26 13:26:31', 'Kapten Ganang');

-- --------------------------------------------------------

--
-- Table structure for table `tb_user`
--

CREATE TABLE `tb_user` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(100) NOT NULL,
  `level` int(11) NOT NULL,
  `created_on` datetime NOT NULL DEFAULT current_timestamp(),
  `created_by` varchar(50) DEFAULT NULL,
  `modified_on` datetime DEFAULT NULL,
  `modifiedd_by` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `tb_user`
--

INSERT INTO `tb_user` (`id`, `username`, `password`, `level`, `created_on`, `created_by`, `modified_on`, `modifiedd_by`) VALUES
(1, 'admin', '21232f297a57a5a743894a0e4a801fc3', 0, '2021-01-02 14:12:30', NULL, NULL, NULL),
(9, 'Ayunda', '2df8c55d47933d5ba55c62996a9bb976', 1, '2021-01-17 22:15:46', 'admin', NULL, NULL),
(11, 'Cheff Yuni', '6b9d6ba55e4f27b1eb5ab5ca05d160a4', 3, '2021-01-17 22:16:41', 'admin', NULL, NULL),
(12, 'Kapten Ganang', 'f35108b61d697603192b0ffca8f11d7f', 2, '2021-01-17 22:17:07', 'admin', NULL, NULL),
(15, 'shandi', 'aa3af19baf5f303a84be784de70ac68f', 2, '2021-01-26 13:23:08', 'admin', NULL, NULL),
(16, 'Darto', '0e70df92e3c67179b7757a33099a3598', 2, '2021-01-30 09:05:44', 'admin', NULL, NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tb_meja`
--
ALTER TABLE `tb_meja`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tb_menu`
--
ALTER TABLE `tb_menu`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tb_pesanan`
--
ALTER TABLE `tb_pesanan`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tb_user`
--
ALTER TABLE `tb_user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tb_meja`
--
ALTER TABLE `tb_meja`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `tb_menu`
--
ALTER TABLE `tb_menu`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=175;

--
-- AUTO_INCREMENT for table `tb_pesanan`
--
ALTER TABLE `tb_pesanan`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=101;

--
-- AUTO_INCREMENT for table `tb_user`
--
ALTER TABLE `tb_user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
