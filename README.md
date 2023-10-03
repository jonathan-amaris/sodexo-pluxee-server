# Sodexo Pluxee Server App
Este es un proyecto de aplicación de noticias desarrollado en Java. Sigue los pasos a continuación para configurar y ejecutar la aplicación en IntelliJ IDEA.

## Clonar el Repositorio Primero.

Clona este repositorio en tu máquina local usando Git: `bash git clone https://github.com/tu-usuario/sodexo-news-app.git`
Instalar o Abrir IntelliJ IDEA

Si aún no tienes IntelliJ IDEA instalado, descárgalo desde [el sitio web oficial](https://www.jetbrains.com/idea/download/?section=mac) e instálalo en tu sistema. Luego, ábrelo.

## Cargar el Proyecto en el Editor

1. Abre IntelliJ IDEA.
2. Selecciona "File" (Archivo) en el menú superior.
3. Luego, selecciona "Open" (Abrir) y navega hasta el directorio donde clonaste el repositorio (sodexo-news-app).
4. Haz clic en "OK" para abrir el proyecto. <br /> <br />
IntelliJ IDEA debería detectar automáticamente que estás trabajando con un proyecto Java y configurará la estructura adecuada para ti.

## Ejecutar la Aplicación

Una vez que hayas corregido cualquier problema en el archivo SodexoNewsAppApplication.java, puedes ejecutar la aplicación desde IntelliJ IDEA.

Abre la clase SodexoNewsAppApplication.java en IntelliJ IDEA.
Haz clic derecho en la clase y selecciona "Run" (Ejecutar) para ejecutar la aplicación.
¡Eso es todo! La aplicación debería ejecutarse y estar lista para su uso.

1. Puedes acceder desde tu navegador (ej: Google Chrome) o Postman usando la url `http://localhost:9090/api/v1/news/favorites`.
2. Puedes acceder a la base de datos en memoria H2 usando el siguiente url `http://localhost:9090/h2-ui/login.jsp`.

Nota: Para acceder a H2, debes colocar `Driver class: org.h2.Driver`, `JDBC URL: jdbc:h2:mem:testdb`, `User Name: sa` y password debe estar vacio.

## Problemas y Soporte

Si encuentras problemas o tienes preguntas, no dudes en escribirme `jonathan.araujo@amaris.com`.
