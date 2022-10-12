-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 03, 2022 at 07:27 AM
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
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `stock`
--
ALTER TABLE `stock`
  MODIFY `idstock` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `stock_in`
--
ALTER TABLE `stock_in`
  MODIFY `idstock_in` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `transaction`
--
ALTER TABLE `transaction`
  MODIFY `idtransaction` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `usersID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `withdrawals`
--
ALTER TABLE `withdrawals`
  MODIFY `idwithdrawals` int(11) NOT NULL AUTO_INCREMENT;
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
