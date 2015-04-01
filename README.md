**Pour flyway-java:**

Installation :

Dans le répertoire mm-conf, renommez et éditez les fichiers :
 
 - database.properties.template pour y renseigner les accès vers la base de données  à partir de laquelle vous voulez faire la migration. Renommer le fichier database.properties.template  to database.properties
   
Exemple d'utilisation (depuis la racine du batch) : 

java -jar lib/flyway-sample-java-0.0.0-SNAPSHOT.jar -o migrate