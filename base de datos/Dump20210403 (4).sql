-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: la_yaquesita
-- ------------------------------------------------------
-- Server version	8.0.23

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `gastos`
--

DROP TABLE IF EXISTS `gastos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `gastos` (
  `idgastos` int NOT NULL,
  `concepto` varchar(45) NOT NULL,
  `cantidad` float NOT NULL,
  PRIMARY KEY (`idgastos`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `gastos`
--

LOCK TABLES `gastos` WRITE;
/*!40000 ALTER TABLE `gastos` DISABLE KEYS */;
/*!40000 ALTER TABLE `gastos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ingredientes`
--

DROP TABLE IF EXISTS `ingredientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ingredientes` (
  `idingredientes` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(45) NOT NULL,
  `abreviacion` varchar(45) NOT NULL,
  PRIMARY KEY (`idingredientes`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ingredientes`
--

LOCK TABLES `ingredientes` WRITE;
/*!40000 ALTER TABLE `ingredientes` DISABLE KEYS */;
INSERT INTO `ingredientes` VALUES (1,'Salchicha',''),(2,'Mayonesa',''),(3,'Mostaza',''),(4,'Catsup',''),(5,'Cebolla asada',''),(6,'Frijol',''),(7,'Tomate',''),(8,'Salsa jalapeño',''),(9,'Aguacate',''),(10,'Queso cotija','');
/*!40000 ALTER TABLE `ingredientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orden`
--

DROP TABLE IF EXISTS `orden`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orden` (
  `idorden` int NOT NULL AUTO_INCREMENT,
  `fecha` date NOT NULL,
  `total` float NOT NULL,
  `idusuario` int NOT NULL,
  PRIMARY KEY (`idorden`),
  KEY `fk_orden_usuarios1_idx` (`idusuario`),
  CONSTRAINT `fk_orden_usuarios1` FOREIGN KEY (`idusuario`) REFERENCES `usuarios` (`idusuario`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orden`
--

LOCK TABLES `orden` WRITE;
/*!40000 ALTER TABLE `orden` DISABLE KEYS */;
INSERT INTO `orden` VALUES (1,'2021-03-30',20,1),(2,'2021-03-31',30,1),(3,'2021-03-31',20,1),(4,'2021-03-31',20,1),(5,'2021-03-31',20,1),(6,'2021-03-31',20,1),(7,'2021-04-01',3,1),(8,'2021-04-01',8,1);
/*!40000 ALTER TABLE `orden` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orden_has_platillo`
--

DROP TABLE IF EXISTS `orden_has_platillo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orden_has_platillo` (
  `orden_idorden` int NOT NULL,
  `platillo_idplatillo` int NOT NULL,
  `cantidad` int NOT NULL,
  `notas` varchar(255) DEFAULT NULL,
  `precio` float NOT NULL,
  `id_orden_has_platillo` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id_orden_has_platillo`),
  KEY `fk_orden_has_platillo_platillo1_idx` (`platillo_idplatillo`),
  KEY `fk_orden_has_platillo_orden1_idx` (`orden_idorden`),
  CONSTRAINT `fk_orden_has_platillo_orden1` FOREIGN KEY (`orden_idorden`) REFERENCES `orden` (`idorden`),
  CONSTRAINT `fk_orden_has_platillo_platillo1` FOREIGN KEY (`platillo_idplatillo`) REFERENCES `platillo` (`idplatillo`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orden_has_platillo`
--

LOCK TABLES `orden_has_platillo` WRITE;
/*!40000 ALTER TABLE `orden_has_platillo` DISABLE KEYS */;
INSERT INTO `orden_has_platillo` VALUES (1,1,1,'Yaqui	$4.5 \n',4.5,1),(1,2,1,'Cuate	$5.5 \n	Salchicha\n	Tocino\n',5.5,2),(2,1,1,'Yaqui	$4.5 \n	Salchicha\n	Tocino\n',4.5,3),(2,2,1,'Cuate	$5.5 \n	Salchicha\n	Tocino\n',5.5,4),(3,1,1,'Yaqui	$4.5 \n	Salchicha\n	Tocino\n',4.5,5),(3,2,1,'Cuate	$5.5 \n	Salchicha\n	Tocino\n',5.5,6),(4,1,1,'Yaqui	$4.5 \n	Salchicha\n	Tocino\n',4.5,7),(4,2,1,'Cuate	$5.5 \n	Salchicha\n	Tocino\n',5.5,8),(5,1,1,'Yaqui	$4.5 \n	Salchicha\n	Tocino\n',4.5,9),(5,1,1,'Yaqui	$4.5 \n	Catsup\n	Cebolla\n	Cotija\n	Frijol\n	Guacamole\n	Mayonesa\n	Mostaza\n	Salchicha\n	Salsa Jalapeno\n	Tocino\n	Tomate\n',4.5,10),(5,2,1,'Cuate	$5.5 \n	Salchicha\n	Tocino\n',5.5,11),(5,2,1,'Cuate	$5.5 \n	Catsup\n	Cebolla\n	Cotija\n	Frijol\n	Guacamole\n	Mayonesa\n	Mostaza\n	Salchicha\n	Salsa Jalapeno\n	Tocino\n	Tomate\n',5.5,12),(6,1,1,'Yaqui	$4.5 \n	Salchicha\n	Tocino\n',4.5,13),(6,1,1,'Yaqui	$4.5 \n	Catsup\n	Cebolla\n	Cotija\n	Frijol\n	Guacamole\n	Mayonesa\n	Mostaza\n	Salchicha\n	Salsa Jalapeno\n	Tocino\n	Tomate\n',4.5,14),(6,2,1,'Cuate	$5.5 \n	Salchicha\n	Tocino\n',5.5,15),(6,2,1,'Cuate	$5.5 \n	Catsup\n	Cebolla\n	Cotija\n	Frijol\n	Guacamole\n	Mayonesa\n	Mostaza\n	Salchicha\n	Salsa Jalapeno\n	Tocino\n	Tomate\n',5.5,16),(7,13,1,'Coca L	$3.0 \n',3,17),(8,1,1,'Yaqui	$4.5 \nCT',4.5,18),(8,14,1,'Jarritos	$2.5 \n',2.5,19),(8,20,1,'Salsa Valiente	$1.0 \n',1,20);
/*!40000 ALTER TABLE `orden_has_platillo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `platillo`
--

DROP TABLE IF EXISTS `platillo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `platillo` (
  `idplatillo` int NOT NULL AUTO_INCREMENT,
  `tipoProducto` enum('EXTRA','BEBIDA','HOTDOG') NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `descripcion` varchar(350) DEFAULT NULL,
  `costo` float NOT NULL,
  PRIMARY KEY (`idplatillo`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `platillo`
--

LOCK TABLES `platillo` WRITE;
/*!40000 ALTER TABLE `platillo` DISABLE KEYS */;
INSERT INTO `platillo` VALUES (1,'HOTDOG','Yaqui','Original/Clásico',4.5),(2,'HOTDOG','Cuate','Doble Salchicha',5.5),(3,'HOTDOG','Golozo','Triple Salchicha',6.5),(4,'HOTDOG','QuesiDogo','Con cama de queso mozzarella derretido',5.5),(5,'HOTDOG','ChileDogo','Chile verde tatemado relleno con salchicha, queso mozzarella envuelto en tocino',6),(6,'HOTDOG','Keto','NO pan, NO frijoles, NO carbs, en cama de queso mozarella',5),(7,'HOTDOG','CheetoDogo','Catsup, mostaza,  cebolla asada, salsa chipotle, queso de nachos, tomate, queso cotija y mayonesa',5.5),(8,'HOTDOG','HawaiianDogo','Queso mozzarella, piña, jamón, salsa agridulce, cebolla asada, tomate, queso cotija, mayonesa de piña y salsa jalapeño.',6),(9,'HOTDOG','El valiente','Mostaza, piña, jamón, salsa agridulce, cebolla asada, salsa jalapeño, salsa valiente (super picante), chile serrano, queso cotija y mayonesa, EXTRA PICANTE.',5.5),(10,'HOTDOG','ChorizoDogo','Salchicha envuelta en tocino, mayonesa, mostaza, catsup, cebolla asada, frijol, tomate, salsa jalapeño, aguacate, queso cotija y chorizo.',5.5),(11,'HOTDOG','CharroDogo','Salchicha envuelta en tocino,queso mozzarella, chorizo, tomate,cebolla asada, mayonesa, catsup, mostaza, aguacate, salsa chipotle, jamón y tocino picado, frijoles, salsa verde (SUPER PICOSA)',6),(12,'HOTDOG','TostiDogo','NO PAN, en una bolsa de tostitos, 2 salchichas envueltas en tocinos, tomates, cebolla asada, frijol, aguacate, queso de nacho, mayonesa, catsup, mostaza, queso cotija.',5.5),(13,'BEBIDA','Coca L','Coca de botella mexicana de 355 ml',3),(14,'BEBIDA','Jarritos','Toroja, Mandarina, Tamarindo, Limón, Piña y Fresa',2.5),(15,'BEBIDA','Manzana y Sangría','Botella mexicana de 355 ml',2.5),(16,'BEBIDA','Soda de lata','Coca-Cola, Fanta, Sprite, Diet Coke y Coke Zero',1.5),(17,'BEBIDA','Botella de agua','500 ml',1),(18,'BEBIDA','Té verde','Arizona',2),(19,'EXTRA','Queso de Nachos','Plato pequeño de queso de nachos',1),(20,'EXTRA','Salsa Valiente','Plato pequeño de Salsa \"Valiente\"',1);
/*!40000 ALTER TABLE `platillo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `platillo_has_ingredientes`
--

DROP TABLE IF EXISTS `platillo_has_ingredientes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `platillo_has_ingredientes` (
  `ingredientes_idingredientes` int NOT NULL,
  `platillo_idplatillo` int NOT NULL,
  `id_platillo_has_ingredientes` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id_platillo_has_ingredientes`),
  KEY `fk_ingredientes_has_platillo_platillo1_idx` (`platillo_idplatillo`),
  KEY `fk_ingredientes_has_platillo_ingredientes1_idx` (`ingredientes_idingredientes`),
  CONSTRAINT `fk_ingredientes_has_platillo_ingredientes1` FOREIGN KEY (`ingredientes_idingredientes`) REFERENCES `ingredientes` (`idingredientes`),
  CONSTRAINT `fk_ingredientes_has_platillo_platillo1` FOREIGN KEY (`platillo_idplatillo`) REFERENCES `platillo` (`idplatillo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `platillo_has_ingredientes`
--

LOCK TABLES `platillo_has_ingredientes` WRITE;
/*!40000 ALTER TABLE `platillo_has_ingredientes` DISABLE KEYS */;
/*!40000 ALTER TABLE `platillo_has_ingredientes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuarios`
--

DROP TABLE IF EXISTS `usuarios`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuarios` (
  `idusuario` int NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `contraseña` varchar(55) NOT NULL,
  `puesto` enum('CAJERO','ADMINISTRADOR') NOT NULL,
  PRIMARY KEY (`idusuario`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuarios`
--

LOCK TABLES `usuarios` WRITE;
/*!40000 ALTER TABLE `usuarios` DISABLE KEYS */;
INSERT INTO `usuarios` VALUES (1,'Joel Valenzuela','123456','ADMINISTRADOR'),(2,'Valenzuela Joel','654321','CAJERO');
/*!40000 ALTER TABLE `usuarios` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-04-03  0:19:00
