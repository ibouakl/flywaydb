**Pour flyway-java:**

Les fichiers de migration(**.sql**) sont dans **src/main/resources/db/migration**. Dans ce dossier , vous pouvez placer les fichiers **(.sql)** pour la migration.

Lancer mvn clean:install 

Installation :

Dans le répertoire mm-conf, renommez et éditez les fichiers :
 
 - database.properties.template pour y renseigner les accès vers la base de données  à partir de laquelle vous voulez faire la migration. Renommer le fichier database.properties.template  to database.properties
   
Exemple d'utilisation (depuis la racine du batch) : 

Pour migrate:
java -jar lib/flyway-sample-java-0.0.1-SNAPSHOT.jar -o migrate