# Shoe Tec: Digitalizando el Oficio de Zapatero Artesanal

Este proyecto nace como Trabajo de Fin de Ciclo Formativo DAM, con el objetivo de modernizar la gestión de un negocio de zapatería tradicional, conectando el servicio artesanal con las facilidades de la era digital. 

El sistema permite la gestión integral del negocio mediante tres interfaces interconectadas: una API centralizada, un sistema de administración para el profesional y una aplicación móvil para el cliente final.

## 🚀 Arquitectura del Sistema
El sistema sigue un modelo de arquitectura cliente-servidor basado en **REST API**, permitiendo la comunicación fluida entre los diferentes módulos.

*   **Backend:** Desarrollado en Java con **Spring Boot**, gestionando la lógica de negocio y las peticiones API.
*   **Servidor:** Desplegado bajo **Apache**.
*   **Base de Datos:** **MariaDB** para la persistencia de datos relacionales (clientes, pedidos, estados de reparación, trabajadores y materiales).
*   **Frontend (Administrador):** Aplicación de escritorio desarrollada en **JavaFX** para el control de inventario y pedidos desde el taller.
*   **Frontend (Cliente):** Aplicación nativa en **Android Studio** para realizar pedidos y consultar estado de reparaciones.

## 🛠 Tecnologías Utilizadas
*   **Lenguajes:** Java
*   **Frameworks:** Spring Boot, JavaFX
*   **Base de Datos:** MariaDB
*   **Entornos:** Eclipse IDE, Android Studio
*   **Servidor:** Apache

## 📋 Funcionalidades Principales
*   **Gestión de Pedidos:** Registro, seguimiento y actualización del estado de las reparaciones.
*   **Conexión en Tiempo Real:** La API garantiza que cuando un cliente realiza un pedido desde la App, aparece instantáneamente en el panel de control del zapatero.
*   **Interfaz de Escritorio:** Panel optimizado para el uso diario del profesional con herramientas de control rápidas.

## 📸 Demo
- **Panel Administrativo (JavaFX):**
  
  <img width="436" height="296" alt="image" src="https://github.com/user-attachments/assets/9b0dd8b8-1a4d-4e5b-b3a5-99a07a688f35" />
  <img width="431" height="292" alt="image" src="https://github.com/user-attachments/assets/5c49486d-ebfc-433a-a9e3-f062d457e861" />


- **App Cliente (Android):**
  
  <img width="262" height="507" alt="image" src="https://github.com/user-attachments/assets/dd27b807-9d93-4299-bb38-7b669b4d526c" />
  <img width="266" height="485" alt="image" src="https://github.com/user-attachments/assets/3238f1b6-a3ab-4af3-ad0f-a133de95a665" />


## 💡 Motivación
La profesión del zapatero es un oficio en riesgo de extinción debido a la competencia de plataformas de bajo coste y la falta de relevo generacional. Este proyecto busca demostrar cómo la tecnología puede actuar como un puente para preservar oficios tradicionales, optimizando su gestión y haciéndolos más accesibles al público actual.

---
*Desarrollado como Proyecto de Fin de Ciclo Formativo DAM.*
