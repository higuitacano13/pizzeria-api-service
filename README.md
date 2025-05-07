# 🍕 Pizza Restaurant Service

Pizza Restaurant Service es una aplicación RESTful desarrollada en Java con Spring Boot, orientada a la gestión de ventas de una pizzeria. Implementa una arquitectura multicapa basada en principios de persistencia, servicios, controlador (MVC) y se conecta a una base de datos PostgreSQL para el almacenamiento persistente de datos.

# 🚀 Tecnologías utilizadas
- Spring Boot.
- Spring Data JPA.
- Spring Security.
- PostgreSQL.

# ⚙️ Funcionalidades

**1- Crear una nueva pizza**
- Permite almacenar una nueva pizza.
- Se valida que la pizza no exista en la base de datos.
- Si la pizza ya está registrado, retorna HTTP 409 (Conflict).
- En caso exitoso, retorna HTTP 201 (Created).

**2- Consultar todas las pizzas**
- Devuelve una lista de todos las pizzas registradas en formato JSON.
- Consulta paginada recibe el número de la página y cantidad de elementos a visualizar.
- Retorna HTTP 200 (OK).

**3- Consultar pizzas disponibles (paginado)**
- Devuelve una lista de todos las pizzas disponibles en formato JSON.
- Consulta paginada recibe el número de la página, cantidad de registros, valor y dirección de ordenamiento de elementos a visualizar.
- Retorna HTTP 200 (OK).

**4- Consultar pizza por nombre**
- Devuelve la información de la pizza correspondiente al nombre proporcionado.
- Si la pizza no existe, retorna HTTP 404 (Not Found).
- En caso exitoso, retorna HTTP 200 (OK).

**5- Consultar pizzas que contengan determinado ingrediente**
- Devuelve la información de las pizzas que contienen el ingrediente proporcionado.
- Si no hay pizzas que contengan el ingrediente proporcionado, retorna HTTP 404 (Not Found).
- En caso exitoso, retorna HTTP 200 (OK).

**6- Consultar pizzas que no contengan determinado ingrediente**
- Devuelve la información de las pizzas que no contienen el ingrediente proporcionado.
- Si no hay pizzas que no contengan el ingrediente proporcionado, retorna HTTP 404 (Not Found).
- En caso exitoso, retorna HTTP 200 (OK).

**7- Consultar pizza por Id**
- Devuelve la información de la pizza correspondiente al id proporcionado.
- Si la pizza no existe, retorna HTTP 404 (Not Found).
- En caso exitoso, retorna HTTP 200 (OK).

**8- Modificar información de la pizza**
- Permite actualizar la información de una pizza especificada.
- Se valida que la pizza exista en la base de datos.
- Si la pizza no existe, retorna HTTP 409 (Conflict).
- En caso exitoso, retorna HTTP 200 (Ok).

 **9- Eliminar pizza**
- Permite eliminar una pizza por un id proporcionado.
- Se valida que la pizza exista en la base de datos.
- Si la pizza no existe, retorna HTTP 409 (Conflict).
- En caso exitoso, retorna HTTP 200 (Ok).

**10- Consultar pizzas baratas**
- Devuelve la información de las pizzas con un precio menor al proporcionado.
- Si no hay pizzas por debajo del precio proporcionado, retorna HTTP 404 (Not Found).
- En caso exitoso, retorna HTTP 200 (OK).

**11- Modificar precio de la pizza**
- Permite actualizar el precio de una pizza especificada.
- Se valida que la pizza exista en la base de datos.
- Si la pizza no existe, retorna HTTP 400 (Bad Request).
- En caso exitoso, retorna HTTP 200 (Ok).

**12- Consultar cliente por número telefónico**
- Devuelve la información del cliente correspondiente al número telefónico proporcionado.
- Si el cliente no existe, retorna HTTP 404 (Not Found).
- En caso exitoso, retorna HTTP 200 (OK).

**13- Consultar todos los pedidos**
- Devuelve una lista de todos las ordenes o pedidos registrados en formato JSON.
- En caso contrario retorna HTTP 200 (OK).

**14- Consultar los pedidos del día actual**
- Devuelve una lista de todos las ordenes o pedidos del día actual en formato JSON.
- Si no hay ordenes con la fecha actual, retorna HTTP 204 (Not Content).
- En caso contrario retorna HTTP 200 (OK).

**15- Consultar los pedidos para llever**
- Devuelve una lista de todos las ordenes o pedidos del día actual en formato JSON.
- Si no hay ordenes con la fecha actual, retorna HTTP 204 (Not Content).
- En caso contrario retorna HTTP 200 (OK).

**16- Consultar los pedidos por cliente**
- Devuelve una lista de todos las ordenes o pedidos para el cliente proporcionado en formato JSON.
- Si no hay ordenes para el cliente proporcionado, retorna HTTP 204 (Not Content).
- En caso contrario retorna HTTP 200 (OK).
- 
**17- Consultar resumen del pedido**
- Devuelve el detalle de un pedido en formato JSON.
- Si el pedido no existe, retorna HTTP 400 (Bad Request).
- En caso contrario retorna HTTP 200 (OK).

**18- Realizar pedido aleatoreo (20% Off)**
- Retorna un valor booleano que indica sí el pedido se generó de manera correcta.
- Si el pedido no fue procesado, retorna HTTP 500 (Internal Server Error).
- En caso contrario retorna HTTP 200 (OK).
  
# ✅ Requisitos del entorno
  - Java 17+
  - Gradle
  - PostgreSQL 13+
  - IDE compatible (IntelliJ, Eclipse, VSCode)
