# app-rrhh-java
Aplicación del curso a distancia JAVA EMPRESARIAL

Entorno: Eclipse Photon Release (4.8.0)

JDK: 1.8.0_161

Git Utilizado: EGit - Git Integration for Eclipse 4.6.0

Base de datos: MySQL

Comentarios:

  * Se cambiaron la tabla ya que se necesita que el identificador del Empleado sea única y autoincrementable, por lo tanto usar el .sql que adjunto a la carpeta.
  * Para probar los testing que modifica la base de datos, siempre antes setear el autoincrement a 1 dentro de MySQL Workbench
  * Se tuvieron que corregir la clase ConexionJDBC ya que siempre tomaba valor nulo la conexión.
  * Faltó especificar que hay que liberar la conexión una vez realizado cada ejecución de la base de datos (en el ejemplo de crear Empleado no se liberó en ningún momento).
  * Usando eclipse con egit no surge ningún problema, es una alternativa al de NetBeans.
  
