# Rapport Personnel

## Projet en Internet Des Objets 
# SMARTLOCK 
## Membre : Abdeldjalil KOUACHI


J’ai intégré le groupe SMARTLOCK un peux en retard, comme j’avais un petit problème de gestion des options trimestrielles, donc ors de mon arrivé j’ai réussi à intégrer l’équipe de « Michel Davel  YABA BILONGO» et « Aïssata KEITA», l’idée de créer une serrure connecté à distance m’as vraiment fasciné, intéressé, et m’as donné envie à m’impliquer vivement dans ce projet d’internet des objets.
 Lors du partage des taches, ma première mission était de concevoir un prototypage de l’application.

## Prototypage des maquettes :

Ca consiste à la création d’un prototype qui ressemble au produit pré existant sur le marché, aucun codage n’était requis pour moi, mais plutôt il faut de l’inspiration et de l’originalité au même temps.
J’ai commencé par rassembler des idées sur : Quelles sont pages de parcours de l’application ? À quoi va ressembler chaque page ?, j’ai dessinais au brouillon une version basique  sur laquelle j’ajoute des nouveaux détails lors de chaque nouveau besoin qui se manifeste.  
Pour sculpter ces idées, j’ai commencé par chercher un outil en ligne qui soit gratuit et efficace à la fois, donc j’ai fini par m’appuyer sur l’outil Figma, ca m’as pris une journée entière pour me familiariser avec Figma et pouvoir terminer le prototype initial de notre application.  

## Outil Design utilisé  « Figma » :

•		Figma est un outil d’interface utilisateur en ligne gratuit pour créer, collaborer, prototyper et transférer.
•	Figma vit dans le navigateur et fonctionne sur Windows, Chrome, Mac et Linux.
•	Il est léger, rapide et n’a pas besoin d’être installé pour utiliser ou partager des fichiers.


![image 1](https://raw.githubusercontent.com/institut-galilee/2020-Smart-Lock/master/doc/Img%20lien%20rapport%20personnel/maquettes.png)

Figure 1 : Première version du Prototype de l’application SMARTLOCK

## Conception et génération de la base de donnée : « Diagramme de classe » + « Script SQL » :

Lors de la première présentation en classe, mon binôme « Aissata KEITA » avait réfléchit et conçu un diagramme de classe avec le peu d’information initial que nous avions au début, mais entamer la relation, il a fallut concevoir une architecture de base donnée, qui sera schématiser par un diagramme de classe unique et définitif.
 J’ai commencé tout d’abord sur une feuille de brouillon de lister touts nos acteur et toutes les actions possibles pour en déduire les variables qui sera impliquer ainsi que les relations entre ces différentes entités.
Pour obtenir le Script de création de la base de données, j’ai pensé à utiliser PhpMyAdmin que  j’ai toujours l’habitude d’utilisé et qui est l’un des outils d’administration MySQL les plus populaires, en particulier pour les services d’hébergement. Par contre, il fallut d’abord choisir le serveur qui me permettra de créer mon Script de la BDD, et assura par la suite la liaison entre l’application « Androïde Studio » et le « code Arduino ».
Lors de mes recherches sur internet sur internet et des conseils de mes deux camarades de l’équipe, j’ai décidé d’utiliser l’outil XAMPP.

Xampp : est un logiciel permettant de mettre en place un serveur Web local, un serveur FTP et un serveur de messagerie électronique, avec une installation simple et rapide.

J’ai donc créé les différentes tables de notre base de données selon les entités que j’ai définis auparavant, j’ai généré le Script SQL, je me suis servi de PhpMyAdmin pour obtenir un diagramme de classe généré automatiquement depuis la BDD, pour être bien sur que ca schématise tous les détails de nos tables, mieux que de faire un double travail en le faisant seul sur un autre site de création UML.

![image 1](https://raw.githubusercontent.com/institut-galilee/2020-Smart-Lock/master/doc/Img%20lien%20rapport%20personnel/diagramme%20de%20classe.jpg)

Figure 2 : Diagramme de classe Schématise les classes et les interfaces de notre système ainsi que les différentes relations entre celles-ci.

![image 1](https://raw.githubusercontent.com/institut-galilee/2020-Smart-Lock/master/doc/Img%20lien%20rapport%20personnel/script%20bdd.jpg)

Figure 3 : Script de création de la Base de Données.

# Création du serveur :
Pour permettre à l’application mobile d’utiliser les données issues du système de Verrouillage/Déverrouillage, nous avons besoin de développer une API.

## C’est quoi une API ?

Une API (Application Programming Interface) permet à deux applications de communiquer entre elles. Une API permet de rendre disponibles les données ou les fonctionnalités d’une application existante afin que d’autres applications les utilisent. 
Comment je l’ai développé ?

Pour mettre en place cette solution, j’ai utilisé le langage de programmation Python à cause de sa complicité parfaite avec Arduino, ceci aussi en s’inspirant du programme fait en TP2.
L’API développé en Python récupère la donnée envoyé depuis le programme Arduino par le moyen de la voie de communication série. En effet, en même temps que le programme Arduino demande au servomoteur de verrouiller (déverrouiller) la serrure, il écrit un message « verrouille » ou « déverrouille » dans la voie de communication série. Cette voie correspond à la voie de communication native laissée pour le débogage. 

Une fois que l’API récupère ce message, il enregistre dans une base de donnée, la même que celle qu’utilise l’application mobile pour stocker et récupérer ses informations. L’API enregistre alors la date de l’événement et le message qu’il a récupérer (verrouille/déverrouille) dans la table « historique ».

Les différentes librairies nécessaires au développement de l’API sont :

![image 1](https://raw.githubusercontent.com/institut-galilee/2020-Smart-Lock/master/doc/Img%20lien%20rapport%20personnel/Librairies%20API.png)


•	Serial pour récupérer les données dans la même voie série que Arduino
•	mysql.connector pour enregistrer les données dans une base de donnée MySQL car la base donnée que nous utilisons dans ce projet est une base de donnée MySQL
•	datetime pour récupérer la date courante correspondant à l’événement 

La fonction permettant d’enregistrer les données :

![image 1](https://raw.githubusercontent.com/institut-galilee/2020-Smart-Lock/master/doc/Img%20lien%20rapport%20personnel/fonction%20permettant%20d%E2%80%99enregistrer%20les%20donn%C3%A9es%C2%A0.png)

La récupération des données et l’appel de la fonction d’insertion :

![image 1](https://raw.githubusercontent.com/institut-galilee/2020-Smart-Lock/master/doc/Img%20lien%20rapport%20personnel/R%C3%A9cup%C3%A9ration%20des%20donn%C3%A9es%20et%20l%E2%80%99appel%20de%20la%20fonction%20d%E2%80%99insertion.png)

Le serveur étant en local, il doit être lancé au préalable pour pouvoir enregistrer les données. 

![image 1](https://raw.githubusercontent.com/institut-galilee/2020-Smart-Lock/master/doc/Img%20lien%20rapport%20personnel/lancement%20du%20serveur%20pour%20pouvoir%20enregistrer%20les%20donn%C3%A9es..png)


Visualisation des données insérées dans la base de données

![image 1](https://raw.githubusercontent.com/institut-galilee/2020-Smart-Lock/master/doc/Img%20lien%20rapport%20personnel/Visualisation%20des%20donn%C3%A9es%20ins%C3%A9r%C3%A9es%20dans%20la%20base%20de%20donn%C3%A9es.png)


# Rapport : 
Mon binôme « Michel Davel  » avait fait une version légère sur le rapport, il avait déposé sur gitHub entant que première version à améliorer selon les conseils et les critiques de l’enseignant.
J’ai donc repris le relais, pour objectifs de : 
•	Définir une belle introduction au sujet de serrures connectées.
•	Etablir un bon état de l’Art, permettant de répondre à toute question envisageable par un client qu’il soit professionnel ou amateur intéressé par la serrure connecté.
•	Déterminer les différentes phases de réalisation du notre projet, en décrivant chaque étape de concrétisation de notre idée objectif dans l’ordre depuis le prototypage jusqu’a le teste final du produit.
Etat de l’Art :
Pour la première fois durant mes études, je rencontre le concept de l’état de l’Art, j’ai commencé par chercher à comprendre à quoi ca consiste ? Comment l’appliquer sur notre produit « serrure connecté SmartLock » ?
J’ai parcouru des dizaines de sites internet, pour comprendre le marché de la serrure connecté, connaitre les types des serrures, les pionniers dans ce domaine, les entreprises qui monopolise le marché, les rapports Prix qualité …. .
En gros, j’ai suivi une certaine hiérarchie d’avancement basée pratiquement du général vers le détail, parmi ces points, les suivants :
•		Les différents types de serrures par systèmes de verrouillage
•	Les facteurs principaux qui déterminent l’adoption et la croissance économique du bisness des serrures connectées dans le marché. 
•	Critères de comparaison pour choisir sa serrure connectée ? 
•	Les 5 Meilleures Serrures Connectées de 2020

## Problème technique rencontré avec une liste des solutions possibles :

Nous avons eu un problème avec notre moteur cerveau, apparemment il n’était pas assez puissant pour donner une force de pousse permettant de faire bouger le Loquet (la serrure).
J’ai donc contacté un ancien camarade spécialisé en mécanique industrielle, pour me renseigner sur les différentes alternatives qu’on pourra rapidement mettre en place pour remplacer ou rendre plus puissant le cerveau moteur, nous avons échangé par Messager durant une heure, il m’as fourni beaucoup d’information concernant ce problème fréquent du à un absence de calcule, sous-estimation ou surestimation de force demandé pour actionner.    
J’ai donc fini après avoir en discuter avec mes camardes de groupe à déterminer 3 solutions envisageable dans le cas ou notre problème persiste sans aucun autre moyen de résolution.

