Este proyecto es una API REST desarrollada en Java utilizando Spring Boot, conectada a una base de datos MySQL.

Puede ser consumida mediante Postman.

Para levantarla hay que configurar la base de datos, desde el archivo application.propertiessrc/main/resources/application.properties

![image](https://github.com/user-attachments/assets/3b29006a-4d67-4875-b9b2-27b43a8e9dfc)


Con la inicializacion se crearan las tablas y campos necesarias si no existiesen.

La Api cuenta con 5 endpoints en total.

Endpoint disponibles: 

CRUD de clientes.

Post  url: http://localhost:8080/api/clientes
      
  body: 
  
  ![image](https://github.com/user-attachments/assets/a9670fc2-b2db-4dde-9bed-0551b43ce8f4)

Get:  http://localhost:8080/api/clientes/{id}

Put:  http://localhost:8080/api/clientes/{id}

  body: 
  
  ![image](https://github.com/user-attachments/assets/a9670fc2-b2db-4dde-9bed-0551b43ce8f4)

Delete: http://localhost:8080/api/clientes/{id}

Creacion de Tickets:
Post:  http://localhost:8080/api/tickets
  
  body:

  ![image](https://github.com/user-attachments/assets/477543c7-2ad9-4290-bb91-01d5dab3e836)

Nota: 

Un ticket solo puede ser asignado a un cliente que ya tenga un ticket previamente registrado.

Para facilitar el testeo durante el desarrollo, podés desactivar esta validación mediante la propiedad:

validador.cliente.ticket=false

en el archivo src/main/resources/application.properties.

Esto permite crear tickets sin requerir un ticket previo para el cliente.



