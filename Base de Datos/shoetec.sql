-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 09-04-2026 a las 09:41:08
-- Versión del servidor: 11.8.6-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `shoetec`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE `clientes` (
  `id_cliente` bigint(20) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `direccion` varchar(255) DEFAULT NULL,
  `localidad` varchar(255) DEFAULT NULL,
  `provincia` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `telefono` varchar(255) DEFAULT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `clientes`
--

INSERT INTO `clientes` (`id_cliente`, `nombre`, `direccion`, `localidad`, `provincia`, `email`, `telefono`, `password`) VALUES
(1, 'Juan Pérez Bellido', 'Calle Mayor 12', 'Madrid', 'Madrid', 'juan.perez@email.com', '600123456', '1234'),
(2, 'María García Contreras', 'Av. Constitución 5', 'Sevilla', 'Sevilla', 'm.garcia@email.com', '611987654', 'abcd'),
(3, 'Carlos Rodríguez Lopez', 'Plaza España 1', 'Barcelona', 'Barcelona', 'carlos.rod@email.com', '622445566', '5678'),
(6, 'Manuela Real Real', 'Calle Semana Santa 123', 'La Redondela', 'Huelva', 'manuela@msn.es', '660666060', '6969');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `historial_pedidos`
--

CREATE TABLE `historial_pedidos` (
  `id_historial` bigint(20) NOT NULL,
  `id_pedido_fk` bigint(20) NOT NULL,
  `estado` varchar(255) DEFAULT NULL,
  `fecha` varchar(255) DEFAULT NULL,
  `completo` tinyint(1) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_uca1400_ai_ci;

--
-- Volcado de datos para la tabla `historial_pedidos`
--

INSERT INTO `historial_pedidos` (`id_historial`, `id_pedido_fk`, `estado`, `fecha`, `completo`) VALUES
(1, 25, 'PEDIDO RECIBIDO', '09/04/2026 09:18', 1),
(2, 25, 'EN TALLER', '09/04/2026 09:21', 1),
(3, 25, 'ENTREGADO', '09/04/2026 09:22', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `logistica`
--

CREATE TABLE `logistica` (
  `id_logistica` bigint(20) NOT NULL,
  `tracking_id` varchar(50) DEFAULT NULL,
  `fecha_recogida` date DEFAULT NULL,
  `fecha_entrega` date DEFAULT NULL,
  `empresa_transporte` varchar(100) DEFAULT NULL,
  `id_pedido` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `logistica`
--

INSERT INTO `logistica` (`id_logistica`, `tracking_id`, `fecha_recogida`, `fecha_entrega`, `empresa_transporte`, `id_pedido`) VALUES
(1, 'ST-TRACK-001', '2026-03-20', '2026-03-22', 'MRW Express', 1),
(2, 'ST-TRACK-002', '2026-03-22', NULL, 'Correos Pack', 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `materiales`
--

CREATE TABLE `materiales` (
  `id_material` bigint(20) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `proveedor` varchar(255) DEFAULT NULL,
  `stock_actual` int(11) NOT NULL,
  `stock_minimo` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `materiales`
--

INSERT INTO `materiales` (`id_material`, `nombre`, `proveedor`, `stock_actual`, `stock_minimo`) VALUES
(1, 'Suela de Goma Vibran', 'Goma Top', 50, 10),
(2, 'Pegamento Industrial XL', 'Pega Top', 15, 5),
(3, 'Tinte Negro Cuero', 'Tinte Top', 20, 8),
(4, 'Hilo de Nylon Reforzado', 'Hilo Top', 100, 20),
(5, 'Cuero Vacuno', 'Curtidos Martinez', 30, 5);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pedidos`
--

CREATE TABLE `pedidos` (
  `id_pedido` bigint(20) NOT NULL,
  `fecha_creacion` varchar(255) DEFAULT NULL,
  `estado` varchar(255) DEFAULT NULL,
  `precio_total` double DEFAULT NULL,
  `id_cliente` bigint(20) DEFAULT NULL,
  `tipo_calzado` varchar(255) DEFAULT NULL,
  `servicio` varchar(255) DEFAULT NULL,
  `fecha_recogida` varchar(255) DEFAULT NULL,
  `franja_horaria` varchar(255) DEFAULT NULL,
  `observaciones` varchar(255) DEFAULT NULL,
  `direccion_entrega` varchar(255) DEFAULT NULL,
  `id_servicio` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `pedidos`
--

INSERT INTO `pedidos` (`id_pedido`, `fecha_creacion`, `estado`, `precio_total`, `id_cliente`, `tipo_calzado`, `servicio`, `fecha_recogida`, `franja_horaria`, `observaciones`, `direccion_entrega`, `id_servicio`) VALUES
(1, '2026-03-20 00:00:00', 'Entregado', 45.5, 1, 'Botas', 'Cambio de Suelas', '20/03/2026', 'Mañana', 'Ninguno', 'Calle Mayor 12', NULL),
(2, '2026-03-22 00:00:00', 'Entregado', 12, 2, 'Chanclas', 'Limpieza', '22/03/2026', 'Tarde', 'Bastante sucias', 'Av.Constitución 5', NULL),
(3, '2026-03-23 00:00:00', 'Entregado', 30, 3, 'Zapatillas', 'Tintado', '24/03/2026', 'Mañana', 'Ninguna', 'Plaza España 1', NULL),
(25, '2026-04-09 09:18:18', 'ENTREGADO', 15, 6, 'Zapatillas', 'Limpieza y Pulido', '2026-04-14', 'Mañana (09:00 - 14:00)', '', 'Calle Semana Santa 123', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pedido_materiales`
--

CREATE TABLE `pedido_materiales` (
  `id_pedido` bigint(20) NOT NULL,
  `id_material` bigint(20) NOT NULL,
  `cantidad_usada` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `pedido_materiales`
--

INSERT INTO `pedido_materiales` (`id_pedido`, `id_material`, `cantidad_usada`) VALUES
(1, 1, 2),
(1, 2, 1),
(2, 3, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pedido_servicios`
--

CREATE TABLE `pedido_servicios` (
  `id_pedido` bigint(20) NOT NULL,
  `id_servicio` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `pedido_servicios`
--

INSERT INTO `pedido_servicios` (`id_pedido`, `id_servicio`) VALUES
(1, 1),
(2, 2),
(3, 4);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `personal`
--

CREATE TABLE `personal` (
  `id_personal` bigint(20) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `rol` varchar(255) DEFAULT NULL,
  `especialidad` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `personal`
--

INSERT INTO `personal` (`id_personal`, `nombre`, `rol`, `especialidad`) VALUES
(1, 'Alberto Ruíz', 'Artesano', 'Suelas y Tacones'),
(2, 'Elena Belmonte', 'Administración', 'Gestión de Clientes'),
(3, 'Ricardo Sanz', 'Taller', 'Teñido y Piel'),
(4, 'Jose Perez', 'Almacen', 'Inventario');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `servicios`
--

CREATE TABLE `servicios` (
  `id_service` bigint(20) NOT NULL,
  `descripcion` varchar(255) NOT NULL,
  `precio` double NOT NULL,
  `id_material_consumible` int(11) DEFAULT NULL,
  `cantidad_consumible` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `servicios`
--

INSERT INTO `servicios` (`id_service`, `descripcion`, `precio`, `id_material_consumible`, `cantidad_consumible`) VALUES
(1, 'Reemplazo de Suelas', 20, NULL, NULL),
(2, 'Colocación de Tapetas', 15, NULL, NULL),
(3, 'Costura', 20, NULL, NULL),
(4, 'Cremallera y Hebillas', 15, NULL, NULL),
(5, 'Limpieza y Pulido', 15, NULL, NULL),
(6, 'Tratamiento del Color', 50, NULL, NULL),
(7, 'Estirado', 15, NULL, NULL),
(8, 'Impermeabilización', 25, NULL, NULL),
(9, 'Modificaciones', 40, NULL, NULL),
(10, 'Cambio de Plantillas', 15, NULL, NULL),
(11, 'Reemplazazo de Suelas de Botas', 55, NULL, NULL);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`id_cliente`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indices de la tabla `historial_pedidos`
--
ALTER TABLE `historial_pedidos`
  ADD PRIMARY KEY (`id_historial`),
  ADD KEY `fk_relacion_pedido` (`id_pedido_fk`);

--
-- Indices de la tabla `logistica`
--
ALTER TABLE `logistica`
  ADD PRIMARY KEY (`id_logistica`),
  ADD UNIQUE KEY `tracking_id` (`tracking_id`),
  ADD UNIQUE KEY `id_pedido` (`id_pedido`);

--
-- Indices de la tabla `materiales`
--
ALTER TABLE `materiales`
  ADD PRIMARY KEY (`id_material`);

--
-- Indices de la tabla `pedidos`
--
ALTER TABLE `pedidos`
  ADD PRIMARY KEY (`id_pedido`),
  ADD KEY `id_cliente` (`id_cliente`);

--
-- Indices de la tabla `pedido_materiales`
--
ALTER TABLE `pedido_materiales`
  ADD PRIMARY KEY (`id_pedido`,`id_material`),
  ADD KEY `id_material` (`id_material`);

--
-- Indices de la tabla `pedido_servicios`
--
ALTER TABLE `pedido_servicios`
  ADD PRIMARY KEY (`id_pedido`,`id_servicio`),
  ADD KEY `id_servicio` (`id_servicio`);

--
-- Indices de la tabla `personal`
--
ALTER TABLE `personal`
  ADD PRIMARY KEY (`id_personal`);

--
-- Indices de la tabla `servicios`
--
ALTER TABLE `servicios`
  ADD PRIMARY KEY (`id_service`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `clientes`
--
ALTER TABLE `clientes`
  MODIFY `id_cliente` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `historial_pedidos`
--
ALTER TABLE `historial_pedidos`
  MODIFY `id_historial` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `logistica`
--
ALTER TABLE `logistica`
  MODIFY `id_logistica` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `materiales`
--
ALTER TABLE `materiales`
  MODIFY `id_material` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `pedidos`
--
ALTER TABLE `pedidos`
  MODIFY `id_pedido` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;

--
-- AUTO_INCREMENT de la tabla `personal`
--
ALTER TABLE `personal`
  MODIFY `id_personal` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `servicios`
--
ALTER TABLE `servicios`
  MODIFY `id_service` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `historial_pedidos`
--
ALTER TABLE `historial_pedidos`
  ADD CONSTRAINT `fk_relacion_pedido` FOREIGN KEY (`id_pedido_fk`) REFERENCES `pedidos` (`id_pedido`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `logistica`
--
ALTER TABLE `logistica`
  ADD CONSTRAINT `logistica_ibfk_1` FOREIGN KEY (`id_pedido`) REFERENCES `pedidos` (`id_pedido`);

--
-- Filtros para la tabla `pedidos`
--
ALTER TABLE `pedidos`
  ADD CONSTRAINT `pedidos_ibfk_1` FOREIGN KEY (`id_cliente`) REFERENCES `clientes` (`id_cliente`);

--
-- Filtros para la tabla `pedido_materiales`
--
ALTER TABLE `pedido_materiales`
  ADD CONSTRAINT `pedido_materiales_ibfk_1` FOREIGN KEY (`id_pedido`) REFERENCES `pedidos` (`id_pedido`),
  ADD CONSTRAINT `pedido_materiales_ibfk_2` FOREIGN KEY (`id_material`) REFERENCES `materiales` (`id_material`);

--
-- Filtros para la tabla `pedido_servicios`
--
ALTER TABLE `pedido_servicios`
  ADD CONSTRAINT `pedido_servicios_ibfk_1` FOREIGN KEY (`id_pedido`) REFERENCES `pedidos` (`id_pedido`),
  ADD CONSTRAINT `pedido_servicios_ibfk_2` FOREIGN KEY (`id_servicio`) REFERENCES `servicios` (`id_service`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
