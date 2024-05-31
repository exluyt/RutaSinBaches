(https://classroom.github.com/assets/deadline-readme-button-24ddc0f5d75046c5622901739e7c5dd533143b0c8e959d652212380cedb1ea36.svg)](https://classroom.github.com/a/nnNBUCuR)
=======
<p align="center">
  <img src="src/vista/img/logo_peque.png" alt="Logo del Proyecto">
</p>

## Descripción del Proyecto

Este proyecto integrador tiene como objetivo principal la gestión de denuncias de problemas, permitiendo a los usuarios registrar incidencias y a los administradores moderarlas y resolverlas. La aplicación está diseñada para ser segura, accesible y fácil de usar, con un sistema de registro y login robusto y funcionalidades que facilitan el seguimiento y gestión de denuncias.

## Tabla de Contenidos

- [Registro de usuario](#registro-de-usuario)
- [Login](#login)
- [Denuncias](#denuncias)
- [Ayudas o Información](#ayudas-o-información)
- [Perfil del usuario](#perfil-del-usuario)
- [Usabilidad y accesibilidad](#usabilidad-y-accesibilidad)
- [Pantallas](#pantallas)
- [Tecnologías usadas](#tecnologías-usadas)
  
# Requisitos Finales Del Proyecto Integrador 
### Registro de usuario
RQ1: Durante el registro no intervendrán terceros, será mediante un autorregistro y
una prueba anti-robots (un captcha).
<br>
RQ2.Consentimiento de notificaciones y términos al terminar el registro (incluyendo
que eres mayor de 14 años)
<br>
RQ3:Después del registro, se mostrará una ventana de login.
<br>
RQ4.-Recuperar la contraseña a través de preguntas
<br>
RQ5-Contraseña segura=> la contraseña se guarda hasheada.
<br>
RQ6-Dos opciones a elegir si eres administrador o no y el administrador debe de
tener que introducir una contraseña específica.
<br>
RQ7- Campos: Nombre y apellidos, nickname (opción de generación automática y
debe ser único), CP, contraseña, y repetir contraseña para asegurarse de que está
bien escrita. Todos son obligatorios.

### Login
RQ8 -Ventana única para administrador en el cual al administrador se le envíe a una
sección reservada para los administradores los cuales podrán moderar las
incidencias, y por el otro lado a los usuarios se les enviaría a la sección para poner
las incidencias.
<br>
RQ9 - Para evitar fuerza bruta, cerrar el programa al tercer intento fallido.
<br>
RQ10 - Tener que introducir el nickname y la contraseña.(Ambos son obligatorios).
<br>
RQ11: Al iniciar la aplicación se mostrará la pantalla de login y desde esta podrás ir
al registro si todavía no lo has realizado, y en desde aquí a la pestaña del olvido de
contraseña (contestando a las preguntas seleccionadas en el registro).

### Denuncias
RQ12: : Para denunciar un problema, se deberá proporcionar la siguiente información: -
Foto - Ubicación (se generará automáticamente en la versión 2) - Dirección postal -
Descripción del problema - Categorias.
<br>
RQ13: El administrador, autorizará los problemas, definirá cuando los casos estén cerrados
confirmando la solución.
<br>
RQ14: Habrá un apartado donde se vean las denuncias marcadas como favoritas. Para que
se pueda hacer un especial seguimiento de ellas.
<br>
RQ15: Un usuario puede avisar de que el problema ya ha sido arreglado.
<br>
RQ16: El administrador podrá ver la información completa de las denuncias , es decir,
información de auditoría.
<br>
RQ17: Los usuarios podrán confirmar una denuncia ya existente.
<br>
RQ18: Sistema de filtrados: Para usuarios y administrador, los koalas las denuncias
tengan categorías o información que haga mas facil buscar publicaciones que estás
buscando(Usuario debe ponerlas).
<br>
RQ19: Si el usuario introduce una denuncia ya escrita (que coincida con el código
postal y mismo tipo de denuncia), la aplicación le preguntará si se refiere a una de
las publicadas, si es así podrá reconfirmarla.
<br>
RQ20: El panel de administrador mostrará estadísticas sobre los tipos de
problemas, las zonas y el tiempo de resolución de forma sencilla.
<br>
RQ21: Las denuncias pueden pasar por los siguientes estados: Nueva(solo visible
para el creador y el administrador), Publicada/Rechazada, el administrador puede
publicar y rechazar las denuncias(si es rechazada explicar el porqué), En proceso y
Finalizada.Cada nuevo estado se registrará la fecha.

### Ayudas o Información
RQ22: El usuario recibirá mensajes informativos de la funcionalidad de la aplicación
cuando entras por primera vez a esta.

### Perfil del usuario
RQ23: Poder editar tu perfil personal como foto, cambio de contraseña,código
postal, etc.

### Usabilidad y accesibilidad
RQ24: La aplicación debe ser fácil de usar, accesible y segura.

# Pantallas
P1  Login: RQ3, RQ8, RQ9, RQ10, RQ11, RQ24
<br>
P2  Registro Pág 1: RQ1, RQ6, RQ7, RQ24
<br>
P3  Registro Pág 2: RQ1, RQ2, RQ3, RQ4, RQ5, RQ7, RQ24
<br>
P4  Registro de admin: RQ6, RQ24
<br>
P5  Recuperacion contraseña 1: RQ4, RQ11, RQ24
<br>
P6  Recuperacion contraseña 2: RQ5, RQ24
<br>
P7  Pagina principal: RQ8, RQ12, RQ14, RQ15, RQ17, RQ18, RQ21, RQ22, RQ24
<br>
P8  Publicar denuncia:  RQ19, RQ24
<br>
P9  Pagina principal Admin: RQ8, RQ12, RQ13, RQ14, RQ16, RQ17, RQ18, RQ21, RQ22, RQ24
<br>
P10 Página estadísticas: RQ20, RQ22, RQ24
<br>
P11 Perfil: RQ22, RQ24
<br>
P12 Información personal: RQ22, RQ23, RQ24

# Tecnologías usadas: 
<p>
  <a href="https://www.eclipse.org" target="_blank">
    <img src="https://upload.wikimedia.org/wikipedia/commons/d/d0/Eclipse-Luna-Logo.svg" alt="Eclipse Logo" title="Eclipse" width="100" height="100">
  </a>
  <a href="https://www.java.com" target="_blank">
    <img src="https://upload.wikimedia.org/wikipedia/en/3/30/Java_programming_language_logo.svg" alt="Java Logo" title="Java" width="100" height="100">
  </a>
  <a href="https://git-scm.com" target="_blank">
    <img src="https://upload.wikimedia.org/wikipedia/commons/e/e0/Git-logo.svg" alt="Git Logo" title="Git" width="100" height="100">
  </a>
</p>

