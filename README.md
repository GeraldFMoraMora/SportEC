# SporTEC

El objetivo de este proyecto es crear una aplicación en Android que consuma recursos de la plataforma **Firebase**.
## Características del sistema.
El sistema a implementar está relacionado con el deporte. Por medio de la aplicación se podrá hacer un seguimiento de noticias en cualquier lugar, en todo momento. También permite seguir eventos con toda la información al instante y resultados minuto a minuto en un solo lugar.
Se puede crear equipos con una comunidad estudiantil para reunirse a practicar un deporte, además se puede incentivar la competitividad a través de retos, los cuales quedarán registrados en el historial y al equipo subirán de nivel en la tabla de posiciones.

## Conectar la aplicación con Firebase.

- El desarrollador debe crear una cuenta en Firebase.
- Debe crear un nuevo proyecto en Firebase que se llamara igual que el actual en desarrollo.
- El nombre de paquete a seleccionar lo puede obtener del archivo **Manifest.xml** (ej: `com.geraldpc.sportec`).
- Una vez creado, desde **Android Studio** dirigirse la pestaña **Tools -> Firebase**, ahí se desplegara un menú en la parte derecha de la aplicación donde podrá utilizar la característica de Firebase que necesite. Una vez seleccionada se debe seleccionar conecatr con Firebase.
- Ahí se selecciona el proyecto creado desde la pagina principal y **listo!**, ya **Android Studio** se encargara de agregar el archivo **google-services.json** y dentro del archivo `build.gradle` (Project: Sportec) la dependencia `classpath 'com.google.gms:google-services:3.1.0'`.
- Se debe agregar la dependencia para Firebase Authentication al archivo `build.gradle` (Module: app): 
``` implementation 'com.google.firebase:firebase-auth:11.0.4'``` y
`implementation 'com.google.firebase:firebase-core:11.0.4'`
> **Nota**: Esta versión de Firebase puede cambiar con el tiempo.
- No olvidar agregar el permiso de acceso a internet al archivo **Manifest.xml**: 
`<uses-permission  android:name="android.permission.INTERNET"/>`


## Autenticación mediante el acceso con Facebook en Android.

- Se debe agregar la dependencia para Firebase Authentication al archivo `build.gradle` (Module: app): 
``` 
implementation 'com.google.firebase:firebase-auth:11.0.4'
```
> **Nota**: Esta versión de Firebase puede cambiar con el tiempo.
- Acceder al sitio web [Facebook for Developers](https://developers.facebook.com/) para obtener el **ID de la app** y el **Secreto de app** de la app .
> **Nota**: Este último se obtiene de la sección **Configuración -> información básica** en el apartado **Clave secreta de la aplicación**.
- Habilitar el acceso:
- 1. Se debe ingresar desde la pagina principal de Firebase, ir a la sección **Consola** y luego seleccionar la propiedad Autenticación, esto le permitirá al desarrollador acceder a los usuarios creados, métodos de acceso, plantillas, entre otros. Recordar que lo que se quiere es habilitar el acceso con Facebook, por lo cual se selecciona en la sección **Métodos de acceso** la opción Facebook, desde ahí se pueden ingresar el **ID de la app** y el **Secreto de app** de la app y se habilita el método. 
- 2. La direccion **URI de redireccionamiento de OAuth**, debe aparecer entre los **URI de redireccionamiento de OAuth** en la página de configuración de la app de Facebook, en el sitio [Facebook for Developers](https://developers.facebook.com/), en la configuración de **Product Settings > Facebook Login**.
- 3. Los Hashes de clave son generados mediante la herramienta de Java llamada **Keytool**, para ello se debe instalar [Java](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html) en caso de no tenerlo y buscar la ubicación de instalación y su carpeta bin para luego setear la sentencia que generara el código Hash de 28 dígitos. La sentencia que se debe ingresar al **cmd de Windows** o el **Terminal de Android Studio** es:
```
keytool -exportcert -alias androiddebugkey -keystore "C:\Users\USER\.android\debug.keystore" | "C:\Users\USER\openssl\bin\openssl" sha1 -binary | "C:\Users\USER\openssl\bin\openssl" base64
```
- 4. Se debe agregar la dependencia para Facebook en `build.gradle` (Module: app): 
``` 
implementation 'com.facebook.android:facebook-android-sdk:[4,5)'
```
- Agregar el botón que proporciona Facebook a el Layout a utilizar, el mismo es modificable, se adapta al lenguaje que posea configurado el dispositivo y ademas de ello trae los métodos necesarios para la autenticación y verificación de un usuario.
- No olvidar agregar el **Meta Data** al archivo **Manifest.xml**: 
```
<meta-data android:name="com.facebook.sdk.ApplicationId" android:value="@string/facebook_app_id" />
```




## Autenticación mediante el acceso con Google en Android.

- Se debe agregar la dependencia para Firebase Authentication al archivo `build.gradle` (Module: app): 
``` 
implementation 'com.google.firebase:firebase-auth:11.0.4'
```
> **Nota**: Esta versión de Firebase puede cambiar con el tiempo.
- Se debe agregar la dependencia para Google Authentication al archivo `build.gradle` (Module: app): 
```
compile 'com.google.android.gms:play-services-auth:11.0.4'
```
- Se debe asegurar en el administrador de SDK en la seccion **SDK TOOLS** que ya exista instalado **Google Play Services** para Android Studio. En caso contrario se debe instalar.
- Habilitar el acceso:
- 1. Se debe ingresar desde la pagina principal de Firebase, ir a la sección **Consola** y luego seleccionar la propiedad Autenticación, esto le permitirá al desarrollador acceder a los usuarios creados, métodos de acceso, plantillas, entre otros. Recordar que lo que se quiere es habilitar el acceso con Google, por lo cual se selecciona en la sección **Métodos de acceso** la opción **Google** y se habilita, los demás pasos ahí presentados son opcionales.

## Autenticación mediante el acceso con Correo en Android.

- Se debe agregar la dependencia para Firebase Authentication al archivo `build.gradle` (Module: app): 
``` 
implementation 'com.google.firebase:firebase-auth:11.0.4'
```
> **Nota**: Esta versión de Firebase puede cambiar con el tiempo.
- Habilitar el acceso:
- 1. Se debe ingresar desde la pagina principal de Firebase, ir a la sección **Consola** y luego seleccionar la propiedad Autenticación, esto le permitirá al desarrollador acceder a los usuarios creados, métodos de acceso, plantillas, entre otros. Recordar que lo que se quiere es habilitar el acceso con correo electrónico, por lo cual se selecciona en la sección **Métodos de acceso** la opción **Correo electrónico/contraseña** y se habilita.
### Crear una cuenta basada en Correo y contraseña.
- En el método `onCreate` de la actividad, se obtiene la instancia compartida del objeto 
```java
private  FirebaseAuth mAuth;  
// ...mAuth =  FirebaseAuth.getInstance();
```
- Cuando se inicia el activity se verifica que el usuario haya accedido:
```java
@Override  
public  void onStart()  {  super.onStart(); 
	FirebaseUser currentUser = mAuth.getCurrentUser(); 
	updateUI(currentUser);  
}
```
- Desde que un usuario accede a la aplicación, pasa la dirección de correo electrónico y la contraseña al método **createUserWithEmailAndPassword**:
```java
mAuth.createUserWithEmailAndPassword(email, password)  .addOnCompleteListener(this,  new  OnCompleteListener<AuthResult>()  {  @Override  public  void onComplete(@NonNull  Task<AuthResult> task)  {  if  (task.isSuccessful())  {  
	Log.d(TAG,  "createUserWithEmail:success");  
	FirebaseUser user = mAuth.getCurrentUser(); updateUI(user);  
	} else  {  
		Log.w(TAG,  "createUserWithEmail:failure",
		task.getException());
		Toast.makeText(EmailPasswordActivity.this,  "Authentication failed.",  
		Toast.LENGTH_SHORT).show(); updateUI(null);  
		}  
	}  
});
```
### Abrir una cuenta basada en Correo y contraseña.
- Desde que un usuario accede a la aplicación, pasa la dirección de correo electrónico y la contraseña al método **signInWithEmailAndPassword**:
```java
mAuth.signInWithEmailAndPassword(email, password)  .addOnCompleteListener(this,  new  OnCompleteListener<AuthResult>()  {  @Override  public  void onComplete(@NonNull  Task<AuthResult> task)  {  if  (task.isSuccessful())  {  
	Log.d(TAG,  "signInWithEmail:success");  
	FirebaseUser user = mAuth.getCurrentUser(); updateUI(user);  
	} else  {  
		Log.w(TAG,  "signInWithEmail:failure", task.getException()); 
		Toast.makeText(EmailPasswordActivity.this,  "Authentication failed.",  
		Toast.LENGTH_SHORT).show(); updateUI(null);  
		}  
	}  
});
```


## Delete a file

