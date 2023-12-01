# RestfulAPI - Documentación

Este repositorio contiene una API Restful para gestionar clientes, gerentes y tiendas.

## Tecnologías Utilizadas

- **Spring Boot:** Framework de desarrollo de aplicaciones Java basado en Java Platform, Standard Edition (Java SE) que simplifica el desarrollo y despliegue de aplicaciones.

- **Spring Data JPA:** Parte del proyecto Spring Data que facilita el acceso a datos mediante la integración con JPA.

- **Hibernate:** Framework de mapeo objeto-relacional (ORM) que implementa JPA y facilita el trabajo con bases de datos relacionales en Java.

- **Lombok:** Biblioteca de Java que agiliza el desarrollo eliminando la necesidad de escribir ciertos métodos estándar, como getters y setters.

- **Maven:** Herramienta de gestión de proyectos y construcción de software.

- **PostgreSQL:** Sistema de gestión de bases de datos relacional de código abierto y potente.


## Tabla de Contenidos

1. [Endpoints](#endpoints)
    1. [Customer](#customer)
    2. [Manager](#manager)
    3. [Store](#store)
2. [Instalación](#instalación)
3. [Uso](#uso)
4. [Contribuir](#contribuir)
5. [Licencia](#licencia)

## Endpoints

### Customer

- **Obtener todos los clientes:**
  - Método: `GET`
  - Ruta: `/api/v1/customer/findAllCustomers`
  - Descripción: Obtiene la lista de todos los clientes.

- **Obtener cliente por ID:**
  - Método: `GET`
  - Ruta: `/api/v1/customer/findCustomerById/{id}`
  - Descripción: Obtiene un cliente por su ID.

- **Obtener cliente por correo electrónico:**
  - Método: `GET`
  - Ruta: `/api/v1/customer/findCustomerByEmail/{name}`
  - Descripción: Obtiene un cliente por su correo electrónico.

- **Guardar nuevo cliente:**
  - Método: `POST`
  - Ruta: `/api/v1/customer/saveCustomer`
  - Descripción: Guarda un nuevo cliente.

- **Actualizar cliente:**
  - Método: `PUT`
  - Ruta: `/api/v1/customer/updateCustomer/{id}`
  - Descripción: Actualiza un cliente existente.

- **Eliminar cliente:**
  - Método: `DELETE`
  - Ruta: `/api/v1/customer/deleteCustomer/{id}`
  - Descripción: Elimina un cliente por su ID.

### Manager

- **Obtener todos los gerentes:**
  - Método: `GET`
  - Ruta: `/api/v1/manager/findAllManagers`
  - Descripción: Obtiene la lista de todos los gerentes.

- **Obtener gerente por ID:**
  - Método: `GET`
  - Ruta: `/api/v1/manager/findManagerById/{id}`
  - Descripción: Obtiene un gerente por su ID.

- **Obtener gerente por nombre:**
  - Método: `GET`
  - Ruta: `/api/v1/manager/findManagerByName/{name}`
  - Descripción: Obtiene un gerente por su nombre.

- **Guardar nuevo gerente:**
  - Método: `POST`
  - Ruta: `/api/v1/manager/saveManager`
  - Descripción: Guarda un nuevo gerente.

- **Actualizar gerente:**
  - Método: `POST`
  - Ruta: `/api/v1/manager/updateManager/{id}`
  - Descripción: Actualiza un gerente existente.

- **Eliminar gerente:**
  - Método: `DELETE`
  - Ruta: `/api/v1/manager/deleteManager/{id}`
  - Descripción: Elimina un gerente por su ID.

### Store

- **Obtener todas las tiendas:**
  - Método: `GET`
  - Ruta: `/api/v1/store/findAllStores`
  - Descripción: Obtiene la lista de todas las tiendas.

- **Obtener tienda por ID:**
  - Método: `GET`
  - Ruta: `/api/v1/store/findStoreById/{id}`
  - Descripción: Obtiene una tienda por su ID.

- **Obtener tienda por nombre:**
  - Método: `GET`
  - Ruta: `/api/v1/store/findStoreByName/{name}`
  - Descripción: Obtiene una tienda por su nombre.

- **Guardar nueva tienda:**
  - Método: `POST`
  - Ruta: `/api/v1/store/saveStore`
  - Descripción: Guarda una nueva tienda.

- **Actualizar tienda:**
  - Método: `PUT`
  - Ruta: `/api/v1/store/updateStore/{id}`
  - Descripción: Actualiza una tienda existente.

- **Eliminar tienda:**
  - Método: `DELETE`
  - Ruta: `/api/v1/store/deleteStore/{id}`
  - Descripción: Elimina una tienda por su ID.

## Instalación

1. Clona el repositorio: `git clone https://github.com/JulianArrieta99/restfulapi.git`
2. Navega al directorio del proyecto: `cd restfulapi`

### Configuración y Ejecución

3. Abre un terminal y ejecuta el siguiente comando para construir y ejecutar la aplicación:
   ```bash
   ./mvnw spring-boot:run

## Uso

1. Configura el archivo de configuración según tus necesidades.
3. Accede a la API desde tu navegador o herramienta de desarrollo API como PostMan


