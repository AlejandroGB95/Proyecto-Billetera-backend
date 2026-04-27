# 🚀 Billetera Virtual API - Full Stack Project

### 💳 Gestión financiera inteligente y eficiente
Una solución **Full Stack** para la gestión de finanzas personales, permitiendo el registro de usuarios, control de saldos y seguimiento de transacciones en tiempo real. Este proyecto está diseñado bajo una arquitectura desacoplada y optimizada para entornos de ejecución con recursos limitados.

---

## 📖 Tabla de Contenidos
* [Características](#-características)
* [Arquitectura](#-arquitectura)
* [Tecnologías Utilizadas](#-tecnologías-utilizadas)
* [Optimización de Recursos (JVM Tuning)](#-optimización-de-recursos-jvm-tuning)
* [Configuración del Proyecto](#-configuración-del-proyecto)
* [Endpoints de la API](#-endpoints-de-la-api)

---

## ✨ Características
* **Autenticación Segura:** Sistema de Login y Registro con persistencia de sesión.
* **Gestión de Saldo:** Actualización dinámica de fondos tras cada transacción.
* **Historial de Movimientos:** Registro detallado de ingresos y egresos.
* **Diseño Responsive:** Interfaz adaptada a dispositivos móviles y escritorio.
* **Despliegue Continuo (CI/CD):** Integración automática en Vercel (Frontend) y Railway (Backend).

---

## 🏗 Arquitectura
El sistema opera bajo un modelo de **Arquitectura Desacoplada**, facilitando el mantenimiento y escalabilidad independiente del Frontend y el Backend. La base de datos PostgreSQL actúa como el núcleo de persistencia para todas las operaciones.

---

## 🛠 Tecnologías Utilizadas

### **Backend (El Motor)**
* **Java 21:** Última versión LTS para un rendimiento superior.
* **Spring Boot 3:** Framework principal para la creación de la API REST.
* **Spring Data JPA / Hibernate:** Gestión de la persistencia y mapeo objeto-relacional.
* **PostgreSQL:** Motor de base de datos robusto y escalable.

### **Frontend (La Interfaz)**
* **HTML5 & CSS3:** Maquetación moderna y estilizado profesional.
* **JavaScript (ES6+):** Lógica dinámica y consumo eficiente de la API mediante Fetch.

---

## ⚡ Optimización de Recursos (JVM Tuning)
Este proyecto ha sido específicamente "tuneado" para ejecutarse de forma estable en el plan gratuito de **Railway**, optimizando el consumo de memoria y CPU:

### 🔧 Ajustes de Memoria (Environment Variables)
| Variable | Propósito |
| :--- | :--- |
| `-XX:+UseSerialGC` | Optimiza la limpieza de memoria para contenedores de un solo núcleo. |
| `-Xss256k` | Reduce el tamaño del stack de cada hilo para ahorrar RAM. |
| `-XX:MaxRAMPercentage=75.0` | Limita el uso de Java al 75% de la RAM disponible para evitar reinicios. |

### 🛠 Configuración de Conexiones (`application.properties`)
```properties
# Pool de conexiones eficiente con HikariCP
spring.datasource.hikari.maximum-pool-size=5
spring.datasource.hikari.minimum-idle=2
spring.datasource.hikari.idle-timeout=30000
spring.datasource.hikari.max-lifetime=1800000

👤 Autor
Alejandro García Benítez - https://www.linkedin.com/in/alejandro-garc%C3%ADa-ben%C3%ADtez-desarrolladorweb/?skipRedirect=true - https://github.com/alejandrogb95
