**Pour le batch flyway-java:**

Les fichiers de migration(**.sql**) sont dans **src/main/resources/db/migration**. Dans ce dossier , vous pouvez placer les fichiers **(.sql)** pour la migration.

Les fichiers de migration(**.java**) sont dans **src/main/java/db/migration**. Dans ce package , vous pouvez placer les fichiers de migration **(.java)**.

* Lancer **mvn clean:install**  pour générer le ficher tar.gz.
* Décompresser le fichier tar.gz.
 

Installation :

Dans le répertoire mm-conf, renommez et éditez les fichiers :
 
 - database.properties.template pour y renseigner les accès vers la base de données  à partir de laquelle vous voulez faire la migration. Renommer le fichier database.properties.template  to database.properties
   
Exemple d'utilisation (depuis la racine du batch) : 

Pour migrate:

java -jar lib/flyway-sample-java-0.0.1-SNAPSHOT.jar -o migrate



**ATTENTION: 
**

Si la base de données n'est pas vide, il faut penser à créer la table metadata et l'initialiser avec la version qui répresente l'état actuel de la base de données.  Pour cela, il suffit de faire un sql dump pour la base de données et nommer la fichier V0_32_1__Init_database.sql, mettre ce fichier dans src/main/resources/migration/db puis lancer le commande baseline: 

**java -jar lib/flyway-sample-java-0.0.1-SNAPSHOT.jar -o baseline -blv 0.32.1**