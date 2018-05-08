# SporTEC

El objetivo de este proyecto es crear una aplicación en Android que consuma recursos de la plataforma **Firebase**.

## Conectar la aplicación con Firebase.

- El desarrollador debe crear una cuenta en Firebase.
- Debe crear un nuevo proyecto en Firebase que se llamara igual que el actual en desarrollo.
- El nombre de paquete a seleccionar lo puede obtener del archivo **Manifest** (ej: `com.geraldpc.sportec`).
- Una vez creado, desde **Android Studio** dirigirse la pestaña **Tools -> Firebase**, ahí se desplegara un menú en la parte derecha de la aplicación donde podrá utilizar la característica de Firebase que necesite. Una vez seleccionada se debe seleccionar conecatr con Firebase.
- Ahí se selecciona el proyecto creado desde la pagina principal y **listo!**, ya **Android Studio** se encargara de agregar el archivo **google-services.json** y dentro del archivo `build.gradle` (Project: Sportec) la dependencia `classpath 'com.google.gms:google-services:3.1.0'`.
- Se debe agregar la dependencia para Firebase Authentication al archivo `build.gradle` (Module: app): 
``` implementation 'com.google.firebase:firebase-auth:11.0.4'``` y
`implementation 'com.google.firebase:firebase-core:11.0.4'`
> **Nota**: Esta versión de Firebase puede cambiar con el tiempo.


## Autenticacion mediante el acceso con Facebook en Android.

- Se debe agregar la dependencia para Firebase Authentication al archivo `build.gradle` (Module: app): 
``` implementation 'com.google.firebase:firebase-auth:11.0.4'```
> **Nota**: Esta versión de Firebase puede cambiar con el tiempo.
- Acceder al sitio web [Facebook for Developers](https://developers.facebook.com/) para obtener el **ID de la app** y el **Secreto de app** de la app .
> **Nota**: Este último se obtiene de la sección **Configuración -> información básica** en el apartado **Clave secreta de la aplicación**.
- Habilitar el acceso:
- 1. Se debe ingresar desde la pagina principal de Firebase, ir a la sección **Console** y luego seleccionar la propiedad Autenticación, esto le permitirá al desarrollador acceder a los usuarios creados, métodos de acceso, plantillas, entre otros. Recordar que lo que se quiere es habilitar el acceso con Facebook, por lo cual se selecciona en la sección **Metodos de acceso** la opción Facebook, desde ahí se pueden ingresar el **ID de la app** y el **Secreto de app** de la app. 
- 2. La direccion **URI de redireccionamiento de OAuth**, debe aparecer entre los **URI de redireccionamiento de OAuth** en la página de configuración de la app de Facebook, en el sitio [Facebook for Developers](https://developers.facebook.com/), en la configuración de **Product Settings > Facebook Login**.
- 3. Se debe agregar la dependencia para Facebook en `build.gradle` (Module: app): 
``` implementation 'com.facebook.android:facebook-android-sdk:[4,5)'```
- Agregar el botón que proporciona Facebook a el Layout a utilizar, el mismo es modificable, se adapta al lenguaje que posea configurado el dispositivo y ademas de ello trae los métodos necesarios para la autenticación y verificación de un usuario.
