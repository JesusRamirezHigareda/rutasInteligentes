-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Dec 07, 2016 at 05:50 AM
-- Server version: 10.1.16-MariaDB
-- PHP Version: 7.0.9

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `rutasinteligentes`
--
CREATE DATABASE IF NOT EXISTS `rutasinteligentes` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `rutasinteligentes`;

DELIMITER $$
--
-- Procedures
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_data` (IN `id_bus` VARCHAR(10) CHARSET utf8, OUT `longitude` VARCHAR(30) CHARSET utf8, OUT `latitude` VARCHAR(30) CHARSET utf8)  NO SQL
begin
 declare nume int;
   select num into nume from ghost;
        SELECT loc_longitude into longitude from locations where loc_unit_id = id_bus and loc_id = nume;
        
       SELECT loc_latitude into latitude from locations where loc_unit_id = id_bus and loc_id = nume;
      
      UPDATE ghost SET num = nume + 1;
    
   select latitude, longitude; 
end$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_new_location` (IN `license_plates` VARCHAR(10), IN `latitude` VARCHAR(25), IN `longitude` VARCHAR(25), IN `nowDate` DATETIME, OUT `result` INT)  begin 
    start transaction;
         	INSERT INTO locations(loc_unit_id, loc_latitude, loc_longitude, loc_datetime) VALUES (license_plates, latitude, longitude, nowDate);
        commit;
end$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `ghost`
--

CREATE TABLE `ghost` (
  `num` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ghost`
--

INSERT INTO `ghost` (`num`) VALUES
(2);

-- --------------------------------------------------------

--
-- Table structure for table `locations`
--

CREATE TABLE `locations` (
  `loc_id` int(11) NOT NULL,
  `loc_datetime` datetime NOT NULL,
  `loc_latitude` varchar(25) NOT NULL,
  `loc_longitude` varchar(25) NOT NULL,
  `loc_unit_id` varchar(15) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `locations`
--

INSERT INTO `locations` (`loc_id`, `loc_datetime`, `loc_latitude`, `loc_longitude`, `loc_unit_id`) VALUES
(1, '2016-12-01 07:56:02', '32.521308', '-116.947527', 'ABC-1234'),
(2, '2016-12-01 08:00:43', '32.519192', '-116.947244', 'ABC-1234'),
(3, '2016-12-01 08:00:43', '32.516949', '-116.939648', 'ABC-1234'),
(4, '2016-12-01 08:02:21', '32.517007', '-116.935241', 'ABC-1234'),
(5, '2016-12-01 08:00:43', '32.517275', '-116.933189', 'ABC-1234'),
(6, '2016-12-01 08:07:23', '32.520539', '-116.926864', 'ABC-1234'),
(7, '2016-12-01 08:08:20', '32.512809', '-116.924212', 'ABC-1234'),
(8, '2016-12-01 08:08:20', '32.510786', '-116.923484', 'ABC-1234'),
(9, '2016-12-01 08:10:40', '32.504159', '-116.921765', 'ABC-1234'),
(10, '2016-12-01 08:11:36', '32.501702', '-116.919868', 'ABC-1234'),
(11, '2016-12-01 08:11:36', '32.497883', '-116.917454', 'ABC-1234'),
(12, '2016-12-01 08:11:36', '32.498658', '-116.912367', 'ABC-1234'),
(13, '2016-12-01 08:13:50', '32.501517', '-116.898119', 'ABC-1234'),
(14, '2016-12-01 08:18:45', '32.501018', '-116.881794', 'ABC-1234'),
(15, '2016-12-01 08:20:09', '32.50229', '-116.877811', 'ABC-1234'),
(16, '2016-12-01 08:20:09', '32.497398', '-116.86171', 'ABC-1234'),
(17, '2016-12-01 08:22:01', '32.493561', '-116.849522', 'ABC-1234'),
(18, '2016-12-01 08:22:01', '32.490255', '-116.85041', 'ABC-1234'),
(19, '2016-12-01 08:22:01', '32.485703', '-116.850882', 'ABC-1234'),
(20, '2016-12-01 08:23:26', '32.482991', '-116.850122', 'ABC-1234'),
(21, '2016-12-01 08:23:33', '32.473825', '-116.847436', 'ABC-1234'),
(22, '2016-12-01 08:25:11', '32.469737 ', '-116.846483', 'ABC-1234'),
(23, '2016-12-01 08:25:11', '32.463288', '-116.845848', 'ABC-1234'),
(24, '2016-12-01 08:26:07', '32.46151', '-116.845106', 'ABC-1234'),
(25, '2016-12-01 08:26:07', '32.460696', '-116.843968', 'ABC-1234'),
(26, '2016-12-01 08:26:07', '32.458835', '-116.837694', 'ABC-1234'),
(27, '2016-12-01 08:27:24', '32.45827', '-116.836308', 'ABC-1234'),
(28, '2016-12-01 08:27:17', '32.45496', '-116.833446', 'ABC-1234'),
(29, '2016-12-01 08:27:24', '32.455959', '-116.829682', 'ABC-1234'),
(30, '2016-12-01 08:28:20', '32.456412 ', '-116.827289', 'ABC-1234'),
(31, '2016-12-01 09:43:41', '32.457334 ', '-116.827372', 'ABC-1234'),
(32, '2016-12-01 09:43:41', '32.458558', '-116.825991', 'ABC-1234');

-- --------------------------------------------------------

--
-- Table structure for table `route`
--

CREATE TABLE `route` (
  `rou_id` int(11) NOT NULL,
  `rou_description` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `route`
--

INSERT INTO `route` (`rou_id`, `rou_description`) VALUES
(1, 'Otay-Refugio');

-- --------------------------------------------------------

--
-- Table structure for table `unit`
--

CREATE TABLE `unit` (
  `uni_id` varchar(10) NOT NULL,
  `uni_description` varchar(30) NOT NULL,
  `uni_route_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `unit`
--

INSERT INTO `unit` (`uni_id`, `uni_description`, `uni_route_id`) VALUES
('ABC-1234', 'Camion Naranja Chico', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `locations`
--
ALTER TABLE `locations`
  ADD PRIMARY KEY (`loc_id`);

--
-- Indexes for table `route`
--
ALTER TABLE `route`
  ADD PRIMARY KEY (`rou_id`);

--
-- Indexes for table `unit`
--
ALTER TABLE `unit`
  ADD PRIMARY KEY (`uni_id`),
  ADD KEY `uni_route_id` (`uni_route_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `locations`
--
ALTER TABLE `locations`
  MODIFY `loc_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;
--
-- Constraints for dumped tables
--

--
-- Constraints for table `unit`
--
ALTER TABLE `unit`
  ADD CONSTRAINT `unit_ibfk_1` FOREIGN KEY (`uni_route_id`) REFERENCES `route` (`rou_id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
