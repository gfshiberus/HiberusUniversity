# Proyecto Spring Cloud con Sleuth y Zipkin
Proyecto con Sleuth y Zipkin implementado.

# Guía de uso
1. ```git clone http://gitlab.hiberus.com/moodle/introSpringCloud-SleuthZipkin.git```.
2. ``mvn clean install`` para instalar las dependencias del proyecto.
3. ``docker-compose up --build`` para levantar Rabbitmq y Zipkin.
4. Arrancar desde el IDE el servicio Config Server.
5. Arrancar desde el IDE el servicio Eureka Server.
6. Arrancar desde el IDE el servicio Gateway Server.
7. Arrancar desde el IDE los servicios Usuarios y Prendas.


# Documentación
- Config server: http://localhost:8888/<nombre_servicio>/default
- Eureka server: http://localhost:8761/
- Gateway: http://localhost:9000/
- Usuarios: http://localhost:8080/swagger-ui.html
- Prendas: http://localhost:8081/swagger-ui.html

# Autor
Jorge Mora Campoy