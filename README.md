# Software-Hosteleria-Java
Este producto software fue diseñado para gestionar una cadena hostelera, a través del cual podrás gestionar tus negocios, empleados y clientes. No solo desde la perspectiva empresarial de recursos humanos, también permite la gestión del servicio ofrecido como las comandas, productos en stock y carta.

El programa carece de interfaz gráfica porque está centrado en el desarrollo de las funcionalidades principales que hacen este software realidad, asimismo, la interacción con el programa se realiza a través de menús por consola.

# Código
El codigo Java se encuentra en dentro del directorio *Software-Hosteleria-Java/**Restaurante/src***, el cuál se divide en la arquitectura Modelo-Vista-Controlador.

# Database
Para entender mejor cómo se gestiona la información en la base de datos pongo a disposición los siguientes diagramas.

- **Diagrama ENTIDAD - RELACIÓN**:
![CantosGagoGonzalo-ENTIDAD_RELACION](https://user-images.githubusercontent.com/57359637/156929834-385ace52-7908-4a50-9dc5-f9006ab46992.png)

- **Diagrama RELACIONAL**: 
![CantosGagoGonzalo-RELACIONAL](https://user-images.githubusercontent.com/57359637/156929900-2ac4205b-5357-4893-a633-88aeddb33d49.png)

El programa ha sido desarrollado abstrayendo la parte de base de datos y el desarrollo en java de modo que, las funcionalidades CRUD han sido designadas en el SGBD Oracle en forma de funciones almacenados en los objetos paquetes.

Además cuenta con indices, secuencias, vistas y disparadores que realizan funcionalidades cruciales para el correcto funcionamiento de la aplicación.

Para la instalación de los componentes de la base de datos dirigirse a *Software-Hosteleria-Java/**Scripts SQL - Oracle*** , dentro del directorio encontrarás varias carpetas, recomiendo instalar los Scripts en el siguiente orden:

1. Tabla.
2. Secuencias.
3. Indices.
4. Vistas.
5. Paquetes.
6. Triggers.


 Este software ha sido desarrollado íntegramente por **© Gonzalo Cantos.**
