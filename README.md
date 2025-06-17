# Population API - Country API Service & Ingestion Service

Prueba técnica para desarrollar una sencilla aplicación backend de almacenamiento y consulta de datos poblacionales a nivel mundial.
Desarrollada con _Java_ y _Spring Boot_, estructurando cada servicio con una arquitectura por capas.

Este repositorio contiene dos microservicios relacionados para gestionar datos de países y su población:

- **country-api-service**: API REST para CRUD y consulta de países. Encargado de exponer los endpoints y manejar la persistencia de los datos en una BD SQL (MySQL)
- **ingestion-service**: Servicio encargado de ingerir datos externos de países desde la API pública [restcountries.com](https://restcountries.com) y almacenarlos a través de `country-api-service`.

---

## URLs importantes

- **API base URL** (para el servicio `country-api`): http://localhost:8080/api/v1/data/country

- **Swagger UI** (documentación automática de la API): http://localhost:8080/swagger-ui/index.html

---

## Requisitos

- Docker (https://docs.docker.com/get-docker/)
- Docker Compose (https://docs.docker.com/compose/install/)

---

## Configuración

Las configuraciones principales están definidas en los `application.properties` de cada servicio y en el archivo `docker-compose.yml`. El acceso a la base de datos MySQL y URLs base de cada servicio están configurados mediante variables de entorno en Docker Compose.

---

## Ejecución con Docker Compose

Para levantar todos los servicios (MySQL, country-api-service e ingestion-service) junto con su red interna:

  Desde la raíz del proyecto, ejecuta:
    
  ```bash
  docker-compose up --build
  ```
  - Compila los ejecutables y genera las imágenes de country-api-service e ingestion-service
  - Levanta tres contenedores en orden:
    - 1º: Contenedor mysql-db (Base de datos)
    - 2º: Contenedor country-api (API)
    - 3º: Contenedor country-ingestion (Servicio de ingesta) [Una vez completada la ingesta, termina el proceso]
    
---