-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 03, 2022 at 07:25 AM
-- Server version: 5.7.14
-- PHP Version: 7.0.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `stock_inventory`
--

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `id` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `description` mediumtext
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`id`, `name`, `description`) VALUES
(1, 'Video Game', 'All kind of game categories'),
(2, 'Software Package', 'Software Suit e.g Office, Adobe...'),
(3, 'Application', 'Popular app used for Social Communication'),
(4, 'Programming Software', 'IDE\'s'),
(5, 'Gadget', 'Games Hardware gadget'),
(6, 'Stationary', ''),
(7, 'Optical Disc', '');

-- --------------------------------------------------------

--
-- Table structure for table `stock`
--

CREATE TABLE `stock` (
  `idstock` int(11) NOT NULL,
  `itemCode` int(11) DEFAULT NULL,
  `itemName` varchar(45) NOT NULL,
  `category` varchar(45) NOT NULL,
  `initQty` int(11) NOT NULL,
  `qty` int(11) NOT NULL,
  `price` decimal(19,2) NOT NULL,
  `costPrice` decimal(19,2) NOT NULL,
  `markup` decimal(19,2) NOT NULL,
  `dateIn` date NOT NULL,
  `dateUpdated` datetime(3) NOT NULL,
  `updatedBy` varchar(45) NOT NULL,
  `totalSoldPrice` decimal(19,2) NOT NULL,
  `totalMarkup` decimal(19,2) NOT NULL,
  `capital` decimal(19,2) NOT NULL,
  `totalQtySold` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `stock`
--

INSERT INTO `stock` (`idstock`, `itemCode`, `itemName`, `category`, `initQty`, `qty`, `price`, `costPrice`, `markup`, `dateIn`, `dateUpdated`, `updatedBy`, `totalSoldPrice`, `totalMarkup`, `capital`, `totalQtySold`) VALUES
(1, 111, 'GTA V', 'Video Game', 20, 17, '10000.00', '9000.00', '1000.00', '2022-09-26', '2022-09-26 18:02:47.000', 'AYO', '30000.00', '3000.00', '27000.00', 3),
(2, 112, 'GTA IV', 'Video Game', 9, 9, '9800.00', '8900.00', '900.00', '2020-10-29', '2020-10-29 15:15:24.000', 'AYO', '0.00', '0.00', '0.00', 0),
(3, 113, 'GTA III', 'Video Game', 50, 43, '9550.00', '9000.00', '550.00', '2020-08-11', '2022-09-26 18:03:06.000', 'AYO', '0.00', '0.00', '0.00', 0),
(4, 114, 'GTA San Andrease', 'Video Game', 20, 20, '9300.00', '8500.00', '800.00', '2022-09-26', '2022-09-26 18:03:22.000', 'AYO', '0.00', '0.00', '0.00', 0),
(5, 115, 'GTA Vice City', 'Video Game', 20, 20, '7000.00', '6600.00', '400.00', '2022-09-26', '2022-09-26 17:54:31.000', 'AYO', '0.00', '0.00', '0.00', 0),
(6, 116, 'GTA Vice City Story', 'Video Game', 50, 50, '5600.00', '5000.00', '600.00', '2020-08-11', '2020-08-13 21:26:04.000', 'AYO', '0.00', '0.00', '0.00', 0),
(7, 117, 'Free Fire', 'Video Game', 105, 96, '6000.00', '5500.00', '500.00', '2020-10-01', '2020-10-01 12:24:23.000', 'AYO', '0.00', '0.00', '0.00', 0),
(8, 118, 'PUBG', 'Video Game', 100, 87, '6500.00', '5000.00', '1500.00', '2020-08-11', '2020-08-11 06:10:11.000', 'AYO', '0.00', '0.00', '0.00', 0),
(9, 119, 'NOVA', 'Video Game', 80, 80, '4700.00', '4000.00', '700.00', '2020-08-11', '2020-08-11 06:10:11.000', 'AYO', '0.00', '0.00', '0.00', 0),
(10, 110, 'Real Racing', 'Video Game', 90, 86, '4000.00', '3700.00', '300.00', '2020-08-11', '2020-08-11 06:10:11.000', 'AYO', '0.00', '0.00', '0.00', 0),
(13, 121, 'Modern Combat 3', 'Video Game', 80, 80, '3400.00', '2800.00', '600.00', '2020-08-11', '2020-08-11 06:10:11.000', 'AYO', '0.00', '0.00', '0.00', 0),
(14, 122, 'Modern Combat 4', 'Video Game', 100, 92, '3900.00', '3000.00', '900.00', '2020-08-11', '2020-08-11 06:10:11.000', 'AYO', '0.00', '0.00', '0.00', 0),
(15, 123, 'Modern Combat 5', 'Video Game', 90, 86, '4700.00', '3800.00', '900.00', '2020-08-11', '2020-08-13 08:44:20.000', 'AYO', '0.00', '0.00', '0.00', 0),
(19, 124, 'PES 2020', 'Video Game', 100, 99, '5000.00', '4300.00', '700.00', '2020-08-11', '2020-08-11 06:10:11.000', 'AYO', '0.00', '0.00', '0.00', 0),
(20, 125, 'MS Excel', 'Software Package', 50, 50, '1300.00', '800.00', '500.00', '2020-08-11', '2020-08-11 06:10:11.000', 'AYO', '0.00', '0.00', '0.00', 0),
(21, 126, 'MS Word', 'Software Package', 50, 50, '1000.00', '500.00', '500.00', '2020-08-11', '2020-08-11 06:38:17.000', 'AYO', '0.00', '0.00', '0.00', 0),
(22, 127, 'MS Powerpoint', 'Software Package', 50, 50, '1000.00', '500.00', '500.00', '2020-08-11', '2020-08-11 06:40:15.000', 'AYO', '0.00', '0.00', '0.00', 0),
(23, 128, 'MS Publisher', 'Software Package', 50, 50, '500.00', '350.00', '150.00', '2022-09-26', '2022-09-26 17:38:15.000', 'AYO', '0.00', '0.00', '0.00', 0),
(24, 129, 'MS Access', 'Software Package', 50, 50, '800.00', '500.00', '300.00', '2020-08-11', '2020-08-11 06:41:45.000', 'AYO', '0.00', '0.00', '0.00', 0),
(25, 130, 'MS Outlook', 'Software Package', 96, 96, '1000.00', '800.00', '200.00', '2022-09-26', '2022-09-26 17:09:49.000', 'AYO', '0.00', '0.00', '0.00', 0),
(26, 131, 'MS OneNote', 'Software Package', 50, 50, '300.00', '250.00', '50.00', '2020-08-11', '2020-08-11 06:42:31.000', 'AYO', '0.00', '0.00', '0.00', 0),
(27, 132, 'Adobe Photoshop', 'Software Package', 50, 50, '1900.00', '1100.00', '800.00', '2022-09-26', '2022-09-26 17:39:12.000', 'AYO', '0.00', '0.00', '0.00', 0),
(28, 133, 'Adobe Illustrator', 'Software Package', 80, 72, '1900.00', '1100.00', '800.00', '2020-08-11', '2020-08-11 06:44:10.000', 'AYO', '0.00', '0.00', '0.00', 0),
(29, 136, 'Adobe Ligthroom', 'Software Package', 80, 80, '700.00', '400.00', '300.00', '2020-08-11', '2020-08-11 06:44:49.000', 'AYO', '0.00', '0.00', '0.00', 0),
(30, 137, 'Adobe Dreamwork', 'Software Package', 80, 80, '600.00', '300.00', '300.00', '2020-08-11', '2020-08-11 06:45:41.000', 'AYO', '0.00', '0.00', '0.00', 0),
(31, 138, 'Whatsapp', 'Application', 100, 96, '100.00', '70.00', '30.00', '2020-08-11', '2020-08-11 06:46:07.000', 'AYO', '400.00', '120.00', '280.00', 4),
(32, 139, 'Facebook', 'Application', 100, 100, '100.00', '70.00', '30.00', '2020-08-11', '2020-08-11 06:46:37.000', 'AYO', '0.00', '0.00', '0.00', 0),
(33, 140, 'Instagram', 'Application', 90, 90, '130.00', '90.00', '40.00', '2020-08-11', '2020-08-11 06:47:16.000', 'AYO', '0.00', '0.00', '0.00', 0),
(34, 141, 'Twitter', 'Application', 85, 85, '140.00', '90.00', '50.00', '2020-08-11', '2020-08-11 06:48:20.000', 'AYO', '0.00', '0.00', '0.00', 0),
(35, 142, 'NetBeans', 'Programming Software', 70, 70, '100.00', '60.00', '40.00', '2020-08-11', '2020-08-11 06:49:18.000', 'AYO', '0.00', '0.00', '0.00', 0),
(36, 143, 'Pycharm', 'Programming Software', 70, 70, '100.00', '70.00', '30.00', '2020-08-11', '2020-08-11 06:49:47.000', 'AYO', '0.00', '0.00', '0.00', 0),
(37, 144, 'VSCode', 'Programming Software', 68, 68, '80.00', '50.00', '30.00', '2020-08-11', '2020-08-11 06:50:44.000', 'AYO', '0.00', '0.00', '0.00', 0),
(38, 145, 'Eclipse', 'Programming Software', 97, 97, '100.00', '70.00', '30.00', '2020-08-11', '2020-08-11 06:51:30.000', 'AYO', '0.00', '0.00', '0.00', 0),
(40, 146, 'PC Game Pad', 'Gadget', 30, 30, '2000.00', '1500.00', '500.00', '2020-08-11', '2020-08-11 06:52:27.000', 'AYO', '0.00', '0.00', '0.00', 0),
(42, 147, '3D Glass', 'Gadget', 30, 24, '19000.00', '13000.00', '6000.00', '2020-08-11', '2020-08-13 10:55:21.000', 'Job', '0.00', '0.00', '0.00', 0),
(43, 148, 'Mouse', 'Gadget', 20, 20, '1400.00', '800.00', '600.00', '2020-08-11', '2020-08-14 03:05:04.000', 'AYO', '0.00', '0.00', '0.00', 0),
(44, 149, 'Keyboard', 'Gadget', 30, 30, '1500.00', '900.00', '600.00', '2020-08-11', '2020-08-11 07:15:11.000', 'AYO', '0.00', '0.00', '0.00', 0),
(46, 150, 'Headset', 'Gadget', 29, 29, '4300.00', '3000.00', '1300.00', '2020-08-11', '2020-08-11 07:15:51.000', 'AYO', '0.00', '0.00', '0.00', 0),
(47, 151, 'IPhone 11 pro Max', 'Gadget', 30, 30, '450000.00', '400000.00', '50000.00', '2020-10-28', '2020-10-28 03:56:52.000', 'AYO', '0.00', '0.00', '0.00', 0),
(48, 152, 'Samsung A50s', 'Gadget', 20, 20, '93000.00', '86000.00', '7000.00', '2020-10-28', '2020-10-28 03:59:41.000', 'AYO', '0.00', '0.00', '0.00', 0),
(49, 153, 'BluRay 16gb', 'Optical Disc', 13, 13, '350.00', '280.00', '70.00', '2020-10-29', '2020-10-29 15:23:35.000', 'AYO', '0.00', '0.00', '0.00', 0);

-- --------------------------------------------------------

--
-- Table structure for table `stock_in`
--

CREATE TABLE `stock_in` (
  `idstock_in` int(11) NOT NULL,
  `itemCode` int(11) DEFAULT NULL,
  `itemName` varchar(45) NOT NULL,
  `category` varchar(45) NOT NULL,
  `initQty` int(11) NOT NULL,
  `newQty` int(11) NOT NULL,
  `costPrice` decimal(19,2) NOT NULL,
  `totalCostPrice` decimal(19,2) NOT NULL,
  `sellingPrice` decimal(19,2) NOT NULL,
  `markup` decimal(19,2) NOT NULL,
  `date` datetime(3) NOT NULL,
  `cashier` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `stock_in`
--

INSERT INTO `stock_in` (`idstock_in`, `itemCode`, `itemName`, `category`, `initQty`, `newQty`, `costPrice`, `totalCostPrice`, `sellingPrice`, `markup`, `date`, `cashier`) VALUES
(1, 112, 'GTA IV', 'Video Game', 0, 7, '19.00', '133.00', '27.00', '8.00', '2020-10-01 16:04:12.000', 'AYO'),
(2, 111, 'GTA V', 'Video Game', 7, 1, '9000.00', '9000.00', '10000.00', '1000.00', '2020-10-29 15:22:01.000', 'AYO'),
(3, 128, 'MS Publisher', 'Software Package', 47, 47, '350.00', '16450.00', '500.00', '150.00', '2022-09-26 17:09:08.000', 'AYO'),
(4, 130, 'MS Outlook', 'Software Package', 48, 48, '800.00', '38400.00', '1000.00', '200.00', '2022-09-26 17:09:49.000', 'AYO'),
(5, 132, 'Adobe Photoshop', 'Software Package', 70, 70, '1100.00', '77000.00', '1900.00', '800.00', '2022-09-26 17:13:21.000', 'AYO'),
(6, 132, 'Adobe Photoshop', 'Software Package', 140, 140, '1100.00', '154000.00', '1900.00', '800.00', '2022-09-26 17:13:46.000', 'AYO'),
(7, 128, 'MS Publisher', 'Software Package', 94, 94, '350.00', '32900.00', '500.00', '150.00', '2022-09-26 17:23:19.000', 'AYO'),
(8, 111, 'GTA V', 'Video Game', 8, 16, '9000.00', '144000.00', '10000.00', '1000.00', '2022-09-26 17:53:01.000', 'AYO'),
(9, 114, 'GTA San Andrease', 'Video Game', 94, 100, '8500.00', '850000.00', '9300.00', '800.00', '2022-09-26 17:53:23.000', 'AYO'),
(10, 115, 'GTA Vice City', 'Video Game', 75, 10, '6600.00', '66000.00', '7000.00', '400.00', '2022-09-26 17:54:32.000', 'AYO'),
(11, 111, 'GTA V', 'Video Game', 32, 20, '9000.00', '180000.00', '10000.00', '1000.00', '2022-09-26 18:02:47.000', 'AYO'),
(12, 114, 'GTA San Andrease', 'Video Game', 200, 20, '8500.00', '170000.00', '9300.00', '800.00', '2022-09-26 18:03:22.000', 'AYO');

-- --------------------------------------------------------

--
-- Table structure for table `transaction`
--

CREATE TABLE `transaction` (
  `idtransaction` int(11) NOT NULL,
  `itemCode` int(11) DEFAULT NULL,
  `itemName` varchar(45) NOT NULL,
  `category` varchar(45) NOT NULL,
  `qty` int(11) NOT NULL,
  `costPrice` decimal(19,2) NOT NULL,
  `unitPrice` decimal(19,2) NOT NULL,
  `totalPrice` decimal(19,2) NOT NULL,
  `date` datetime(3) NOT NULL,
  `cashier` varchar(45) NOT NULL,
  `markup` decimal(19,2) NOT NULL,
  `totalMarkup` decimal(19,2) NOT NULL,
  `discount` decimal(19,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `transaction`
--

INSERT INTO `transaction` (`idtransaction`, `itemCode`, `itemName`, `category`, `qty`, `costPrice`, `unitPrice`, `totalPrice`, `date`, `cashier`, `markup`, `totalMarkup`, `discount`) VALUES
(1, 111, 'GTA V', 'Video Game', 5, '20.00', '29.00', '130.00', '2020-09-08 19:22:45.000', 'AYO', '9.00', '45.00', '15.00'),
(2, 111, 'GTA V', 'Video Game', 3, '20.00', '29.00', '80.00', '2020-09-08 19:30:59.000', 'AYO', '9.00', '27.00', '7.00'),
(3, 123, 'Modern Combat 5', 'Video Game', 4, '20.00', '27.00', '100.00', '2020-09-08 19:35:02.000', 'AYO', '7.00', '28.00', '8.00'),
(4, 132, 'Adobe Photoshop', 'Software Package', 2, '11.00', '19.00', '38.00', '2020-09-08 21:12:15.000', 'AYO', '8.00', '16.00', '0.00'),
(5, 128, 'MS Publisher', 'Software Package', 3, '12.00', '18.00', '54.00', '2020-09-08 21:12:15.000', 'AYO', '6.00', '18.00', '0.00'),
(6, 115, 'GTA Vice City', 'Video Game', 5, '17.00', '25.00', '125.00', '2020-09-08 21:12:15.000', 'AYO', '8.00', '40.00', '0.00'),
(7, 132, 'Adobe Photoshop', 'Software Package', 8, '11.00', '19.00', '152.00', '2020-10-01 10:56:50.000', 'AYO', '8.00', '64.00', '0.00'),
(8, 124, 'PES 2020', 'Video Game', 1, '23.00', '30.00', '30.00', '2020-10-01 10:56:50.000', 'AYO', '7.00', '7.00', '0.00'),
(9, 133, 'Adobe Illustrator', 'Software Package', 8, '11.00', '19.00', '152.00', '2020-10-01 10:56:50.000', 'AYO', '8.00', '64.00', '0.00'),
(10, 147, '3D Glass', 'Gadget', 6, '13.70', '19.00', '114.00', '2020-10-01 11:21:30.000', 'Job', '5.30', '31.80', '0.00'),
(11, 111, 'GTA V', 'Video Game', 10, '20.00', '29.00', '200.00', '2020-10-01 12:22:58.000', 'AYO', '9.00', '0.00', '90.00'),
(12, 110, 'Real Racing', 'Video Game', 4, '20.00', '29.00', '116.00', '2020-10-01 12:22:58.000', 'AYO', '9.00', '36.00', '0.00'),
(13, 111, 'GTA V', 'Video Game', 81, '20.00', '29.00', '2349.00', '2020-10-01 13:00:31.000', 'AYO', '9.00', '729.00', '0.00'),
(14, 112, 'GTA IV', 'Video Game', 100, '19.00', '27.00', '2700.00', '2020-10-01 13:03:00.000', 'AYO', '8.00', '800.00', '0.00'),
(15, 130, 'MS Outlook', 'Software Package', 2, '700.00', '1000.00', '2000.00', '2020-10-29 15:11:41.000', 'AYO', '300.00', '600.00', '0.00'),
(16, 138, 'Whatsapp', 'Application', 4, '70.00', '100.00', '400.00', '2020-10-29 15:11:41.000', 'AYO', '30.00', '120.00', '0.00'),
(17, 111, 'GTA V', 'Video Game', 3, '9000.00', '10000.00', '30000.00', '2022-09-26 18:07:24.000', 'AYO', '1000.00', '3000.00', '0.00');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `usersID` int(11) NOT NULL,
  `name` varchar(45) NOT NULL,
  `address` varchar(45) NOT NULL,
  `phone_number` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `tag` varchar(45) NOT NULL,
  `date_created` datetime NOT NULL,
  `last_seen` datetime(3) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`usersID`, `name`, `address`, `phone_number`, `password`, `tag`, `date_created`, `last_seen`) VALUES
(1, 'AYO', 'Deep in Coding', '+2348164816500', '123456', 'Admin', '2020-08-11 06:10:11', '2022-09-26 18:09:13.000'),
(2, 'Job', 'Store', '08164816500', '123456', 'Employee', '2020-08-12 08:11:28', '2022-09-26 15:49:30.000');

-- --------------------------------------------------------

--
-- Table structure for table `withdrawals`
--

CREATE TABLE `withdrawals` (
  `idwithdrawals` int(11) NOT NULL,
  `itemCode` int(11) DEFAULT NULL,
  `itemName` varchar(45) NOT NULL,
  `category` varchar(45) NOT NULL,
  `qty` int(11) NOT NULL,
  `costPrice` decimal(19,2) NOT NULL,
  `unitPrice` decimal(19,2) NOT NULL,
  `totalPrice` decimal(19,2) NOT NULL,
  `date` datetime(3) NOT NULL,
  `cashier` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `withdrawals`
--

INSERT INTO `withdrawals` (`idwithdrawals`, `itemCode`, `itemName`, `category`, `qty`, `costPrice`, `unitPrice`, `totalPrice`, `date`, `cashier`) VALUES
(1, 114, 'GTA San Andrease', 'Video Game', 6, '19.00', '28.00', '168.00', '2020-10-01 10:58:52.000', 'AYO'),
(2, 117, 'Free Fire', 'Video Game', 9, '5500.00', '6000.00', '54000.00', '2020-10-29 15:32:22.000', 'AYO'),
(7, 122, 'Modern Combat 4', 'Video Game', 8, '3000.00', '3900.00', '31200.00', '2020-10-29 15:49:37.000', 'AYO'),
(8, 118, 'PUBG', 'Video Game', 13, '5000.00', '6500.00', '84500.00', '2020-10-29 15:51:47.000', 'AYO');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `name_UNIQUE` (`name`);

--
-- Indexes for table `stock`
--
ALTER TABLE `stock`
  ADD PRIMARY KEY (`idstock`),
  ADD UNIQUE KEY `itemName_UNIQUE` (`itemName`),
  ADD UNIQUE KEY `itemCode_UNIQUE` (`itemCode`),
  ADD KEY `cat1_idx` (`category`),
  ADD KEY `user1_idx` (`updatedBy`),
  ADD KEY `unitPrice_idx` (`price`),
  ADD KEY `costPrice_idx` (`costPrice`),
  ADD KEY `markup_idx` (`markup`);

--
-- Indexes for table `stock_in`
--
ALTER TABLE `stock_in`
  ADD PRIMARY KEY (`idstock_in`),
  ADD KEY `icode3_idx` (`itemCode`),
  ADD KEY `name_idx` (`itemName`),
  ADD KEY `user_idx` (`cashier`),
  ADD KEY `category_idx` (`category`);

--
-- Indexes for table `transaction`
--
ALTER TABLE `transaction`
  ADD PRIMARY KEY (`idtransaction`),
  ADD KEY `icode2_idx` (`itemCode`),
  ADD KEY `catego_idx` (`category`),
  ADD KEY `user4_idx` (`cashier`),
  ADD KEY `name4_idx` (`itemName`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`usersID`),
  ADD UNIQUE KEY `name_UNIQUE` (`name`);

--
-- Indexes for table `withdrawals`
--
ALTER TABLE `withdrawals`
  ADD PRIMARY KEY (`idwithdrawals`),
  ADD UNIQUE KEY `itemCode_UNIQUE` (`itemCode`),
  ADD KEY `user2_idx` (`cashier`),
  ADD KEY `categ_idx` (`category`),
  ADD KEY `name3` (`itemName`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
--
-- AUTO_INCREMENT for table `stock`
--
ALTER TABLE `stock`
  MODIFY `idstock` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=50;
--
-- AUTO_INCREMENT for table `stock_in`
--
ALTER TABLE `stock_in`
  MODIFY `idstock_in` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
--
-- AUTO_INCREMENT for table `transaction`
--
ALTER TABLE `transaction`
  MODIFY `idtransaction` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;
--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `usersID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `withdrawals`
--
ALTER TABLE `withdrawals`
  MODIFY `idwithdrawals` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `stock`
--
ALTER TABLE `stock`
  ADD CONSTRAINT `cat1` FOREIGN KEY (`category`) REFERENCES `category` (`name`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `user1` FOREIGN KEY (`updatedBy`) REFERENCES `users` (`name`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `stock_in`
--
ALTER TABLE `stock_in`
  ADD CONSTRAINT `category` FOREIGN KEY (`category`) REFERENCES `category` (`name`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `icode3` FOREIGN KEY (`itemCode`) REFERENCES `stock` (`itemCode`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `name` FOREIGN KEY (`itemName`) REFERENCES `stock` (`itemName`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `user` FOREIGN KEY (`cashier`) REFERENCES `users` (`name`) ON DELETE NO ACTION ON UPDATE CASCADE;

--
-- Constraints for table `transaction`
--
ALTER TABLE `transaction`
  ADD CONSTRAINT `catego` FOREIGN KEY (`category`) REFERENCES `category` (`name`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `icode2` FOREIGN KEY (`itemCode`) REFERENCES `stock` (`itemCode`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `name4` FOREIGN KEY (`itemName`) REFERENCES `stock` (`itemName`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `user4` FOREIGN KEY (`cashier`) REFERENCES `users` (`name`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `withdrawals`
--
ALTER TABLE `withdrawals`
  ADD CONSTRAINT `categ` FOREIGN KEY (`category`) REFERENCES `category` (`name`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `icode` FOREIGN KEY (`itemCode`) REFERENCES `stock` (`itemCode`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `name3` FOREIGN KEY (`itemName`) REFERENCES `stock` (`itemName`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `user2` FOREIGN KEY (`cashier`) REFERENCES `users` (`name`) ON DELETE NO ACTION ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
