# tpExamenAppMoviles
Entrega de código para el examen de App Moviles


Para que la app funcione correctamente se debera agregar la dependencia Gson:

 En el archivo build.gradle de tu módulo (generalmente app).
 
Abre el archivo build.gradle que se encuentra en el módulo de tu aplicación (no el de nivel superior).

Agrega la siguiente línea en la sección dependencies:

groovy
dependencies {
    implementation 'com.google.code.gson:gson:2.10.1' // Asegúrate de usar la última versión
}

después se debe sincronizar el proyecto para que Gradle descargue la biblioteca Gson. Haciendo clic en "Sync Now". 


Integrantes: 

Fernández, Nicolás
Luiso, Ricardo
Moreno, Selena
Hurtado Guimarey, Gabriel
