<?php
// Configuración para MariaDB en XAMPP
$server = "localhost";
$user = "root";   // XAMPP siempre usa 'root' por defecto
$pass = "";       // XAMPP viene sin contraseña por defecto
$db = "shoetec";  // El nombre que le diste a la base de datos en phpMyAdmin

$conexion = new mysqli($server, $user, $pass, $db);

// Comprobar si la conexión falló
if ($conexion->connect_errno) {
    die("Error de conexión: " . $conexion->connect_error);
}

// IMPORTANTE: Configurar el conjunto de caracteres a UTF-8 para evitar errores con tildes y Ñ
$conexion->set_charset("utf8");

echo "Conexión establecida con éxito"; // Descomenta esta línea para probar en el navegador
?>