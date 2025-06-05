# Population API - Country API Service & Ingestion Service

Prueba técnica para desarrollar una sencilla aplicación backend de almacenamiento y consulta de datos poblacionales a nivel mundial 

Este repositorio contiene dos microservicios relacionados para gestionar datos de países y su población:

- **country-api-service**: API REST para CRUD y consulta de países.
- **ingestion-service**: Servicio encargado de ingerir datos externos de países desde la API pública [restcountries.com](https://restcountries.com) y almacenarlos en `country-api-service`.

---

## Requisitos

- Docker (https://docs.docker.com/get-docker/)
- Docker Compose (https://docs.docker.com/compose/install/)

---

## URLs importantes

- **API base URL** (para el servicio `country-api`):  
  `http://localhost:8080/api/v1/data/country`

- **Swagger UI** (documentación automática de la API):  
  `http://localhost:8080/swagger-ui/index.html`

---

## Configuración

Las configuraciones principales están definidas en los `application.properties` de cada servicio y en el archivo `docker-compose.yml`. El acceso a la base de datos MySQL y URLs base de cada servicio están configurados mediante variables de entorno en Docker Compose.

---

## Ejecución con Docker Compose

Para levantar todos los servicios (MySQL, country-api-service e ingestion-service) junto con su red interna:

### Paso 1: Compilar los servicios antes de levantar con Docker Compose

  Para que el `docker-compose.yml` funcione correctamente, debes generar primero los archivos `.jar` de los servicios `country-api-service` y `ingestion-service`. Esto genera el artefacto `app.jar` de cada servicio que los contenedores usarán para arrancar.

- #### Opción A: Compilar localmente con Maven instalado

  Desde la raíz del proyecto, ejecuta:
  
  ```bash
  mvn clean package -f country-api-service/pom.xml
  mvn clean package -f ingestion-service/pom.xml
  ```

- #### Opción B: Compilar localmente sin Maven instalado

  Desde la raíz del proyecto, ejecuta:
  
  - Linux:
    ```bash
    ./mvnw clean package -f country-api-service/pom.xml
    ./mvnw clean package -f ingestion-service/pom.xml
    ```
  - Windows:
    ```bash
    mvnw.cmd clean package -f country-api-service\pom.xml
    mvnw.cmd clean package -f ingestion-service\pom.xml
    ```
  
### Paso 2: Levantar los servicios con Docker Compose

  Desde la raíz del proyecto, ejecuta:
    
  ```bash
  docker-compose up --build
  ```
