# SporTEC

El objetivo de este proyecto es crear una aplicación en Android que consuma recursos de la plataforma **Firebase**.
## Características del sistema.
El sistema a implementar está relacionado con el deporte. Por medio de la aplicación se podrá hacer un seguimiento de noticias en cualquier lugar, en todo momento. También permite seguir eventos con toda la información al instante y resultados minuto a minuto en un solo lugar.
Se puede crear equipos con una comunidad estudiantil para reunirse a practicar un deporte, además se puede incentivar la competitividad a través de retos, los cuales quedarán registrados en el historial y al equipo subirán de nivel en la tabla de posiciones.
## Instalación.
- Se debe almacenar el archivo ejecutable SporTEC.apk en el dispositivo, ejecutarlo una vez hecho esto, brindarle los permisos necesarios y por ultimo lanzar el ejecutable almacenado en el cajon de aplicaciones con el nombre SporTEC.

## Requisitos del sistema (Hardware & Software).
- Sistema Operativo **Android** version minima 4.2 (Jelly Bean, API level 17) o hasta la version maxima 8.1 (Oreo, API level 27).
- Memoria RAM 1.5GB o superior.
- Espacio de almacenamiento 200MB.
- Pantalla de 4.5" o superior.
- Acceso a Internet.
- Camara digital.

## Conectar la aplicación con Firebase.

- El desarrollador debe crear una cuenta en Firebase.
- Debe crear un nuevo proyecto en Firebase que se llamara igual que el actual en desarrollo.
- El nombre de paquete a seleccionar lo puede obtener del archivo **Manifest.xml** (ej: `com.geraldpc.sportec`).
- Una vez creado, desde **Android Studio** dirigirse la pestaña **Tools -> Firebase**, ahí se desplegara un menú en la parte derecha de la aplicación donde podrá utilizar la característica de Firebase que necesite. Una vez seleccionada se debe seleccionar conectar con Firebase.
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
- 2. La direccion **URI de re-direccionamiento de OAuth**, debe aparecer entre los **URI de re-direccionamiento de OAuth** en la página de configuración de la app de Facebook, en el sitio [Facebook for Developers](https://developers.facebook.com/), en la configuración de **Product Settings > Facebook Login**.
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
mAuth.createUserWithEmailAndPassword(email, password)  .addOnCompleteListener(this,  new  OnCompleteListener<AuthResult>()  {  	
	@Override  
	public  void onComplete(@NonNull  Task<AuthResult> task)  {  
		if  (task.isSuccessful())  {  
			Log.d(TAG,  "createUserWithEmail:success");  
			FirebaseUser user = mAuth.getCurrentUser(); 
			updateUI(user);  
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
mAuth.signInWithEmailAndPassword(email, password)  .addOnCompleteListener(this,  new  OnCompleteListener<AuthResult>()  {  
	@Override  
	public  void onComplete(@NonNull  Task<AuthResult> task)  {  
		if  (task.isSuccessful())  {  
			Log.d(TAG,  "signInWithEmail:success");  
			FirebaseUser user = mAuth.getCurrentUser(); 
			updateUI(user);  
		} else  {  
			Log.w(TAG,  "signInWithEmail:failure", task.getException()); 
			Toast.makeText(EmailPasswordActivity.this,  "Authentication failed.",  
			Toast.LENGTH_SHORT).show(); updateUI(null);  
		}  
	}  
});
```


## Acceso a base de datos en tiempo real.

- Se debe agregar la dependencia para Firebase Authentication al archivo `build.gradle` (Module: app): 
``` implementation 'com.google.firebase:firebase-auth:11.0.4'``` y
`implementation 'com.google.firebase:firebase-core:11.0.4'`
> **Nota**: Esta versión de Firebase puede cambiar con el tiempo.
- No olvidar agregar el permiso de acceso a internet al archivo **Manifest.xml**: 
`<uses-permission  android:name="android.permission.INTERNET"/>`
- Se debe agregar la dependencia para Firebase database al archivo `build.gradle` (Module: app): 
``` 
implementation 'com.google.firebase:firebase-database:11.0.4'
``` 
> **Nota**: Notese que la versión de firebase database es la misma que para auth y core firebase.
> **Nota:** Según la configuración predeterminada, el acceso de lectura y de escritura a la base de datos está restringido, por lo que solo los usuarios autenticados pueden leer o escribir datos. Para comenzar sin configurar  Authentication, se puede  configurar las reglas de acceso público. Esto hace que los datos estén abiertos para todo el mundo, incluso las personas que no usan la app, así que se debe asegurar de volver a restringir la base de datos cuando se configure la autenticación.
### Referenciar la base de datos.
- Para leer la base de datos o escribir en ella, se necesita una instancia de firebase.database.Reference y se realiza así:
``` java
var database = firebase.database();
``` 
### Lectura y escritura de la base de datos.
- Para recuperar los datos de Firebase, se debe agregar un agente de escucha asíncrono a una firebase.database.Reference. El agente de escucha se activa una vez para el estado inicial de los datos y otra vez cuando los datos cambian.
- > **Nota:** Según la configuración predeterminada, el acceso de lectura y de escritura a la base de datos está restringido, por lo que solo los usuarios autenticados pueden leer o escribir datos. Para comenzar sin configurar  Authentication, se puede  configurar las reglas de acceso público. Esto hace que los datos estén abiertos para todo el mundo, incluso las personas que no usan la app, así que se debe asegurar de volver a restringir la base de datos cuando se configure la autenticación.
#### Escritura de datos.
Para ello se puede utilizar la función `set()` para guardar datos en una referencia que sea especificada, un ejemplo similar al utilizado en esta aplicación es:
```java
function writeUserData(userId, name, email, imageUrl)  { 
	firebase.database().ref('users/'  + userId).set({ 
		username: name, 
		email: email, 
		profile_picture : imageUrl 
		});  
	}
```
### Gestión de los datos (Borrado y actualización).
- #### Actualización.
- Para escribir de forma simultánea en elementos secundarios específicos de un nodo sin sobrescribir otros nodos secundarios, se usa el método **update()**.
- Al utilizar la funcion **update()**, se puede definir una ruta de acceso de la clave para actualizar valores secundarios de nivel inferior.
```java
function writeNewPost(uid, username, picture, title, body)  {
	//Esta va a ser la nueva entrada.
	var postData =  { author: username, uid: uid, body: body, title: title, starCount:  0, authorPic: picture };
	//Aca se obtiene una key para el nuevo post
	var newPostKey = firebase.database().ref().child('posts').push().key;
	var updates =  {};
	//*Aca se escribe el nuevo post de manera simultanea para 
	la lista de post y la lista de post por usuario*//
	updates['/posts/'  + newPostKey]  = postData;
	updates['/user-posts/'  + uid +  '/'  + newPostKey]  = postData;
	//Retorna
	return firebase.database().ref().update(updates);
}
```
- #### Borrado.
- Para borrar datos se necesita llamar a la función **remove()** haciendo una referencia a la ubicación de los datos.
>**Nota**: Para borrar se puede especificar **null** como el valor de otra operación de escritura, como **set()** o **update()**.

## Export a file

You can export the current file by clicking **Export to disk** in the menu. You can choose to export the file as plain Markdown, as HTML using a Handlebars template or as a PDF.


# Synchronization

