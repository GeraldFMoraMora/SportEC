# SporTEC

El objetivo de este proyecto es crear una aplicación en Android que consuma recursos de la plataforma **Firebase**.


## Autenticacion mediante el acceso con Facebook en Android.

- Se debe agregar la dependencia para Firebase Authentication al archivo `build.gradle` (Module: app): 
``` implementation 'com.google.firebase:firebase-auth:11.0.4'```
- Acceder al sitio web [Facebook for Developers](https://developers.facebook.com/) para obtener el **ID de la app** y el **Secreto de app** de la app .
> **Nota**: Este último se obtiene de la sección **Configuración -> información básica** en el apartado **Clave secreta de la aplicación**.
- Habilitar el acceso:
- 1. Se debe ingresar desde la pagina principal de Firebase, ir a la sección **Console** y luego seleccionar la propiedad Autenticación, esto le permitirá al desarrollador acceder a los usuarios creados, métodos de acceso, plantillas, entre otros. Recordar que lo que se quiere es habilitar el acceso con Facebook, por lo cual se selecciona en la sección **Metodos de acceso** la opción Facebook, desde ahí se pueden ingresar el **ID de la app** y el **Secreto de app** de la app. 
- 2. La direccion **URI de redireccionamiento de OAuth**, debe aparecer entre los **URI de redireccionamiento de OAuth** en la página de configuración de la app de Facebook, en el sitio [Facebook for Developers](https://developers.facebook.com/), en la configuración de **Product Settings > Facebook Login**.
