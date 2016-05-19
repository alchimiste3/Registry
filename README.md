# Registry

On a 5 parties :

## Registry

Il faut lancer la rmi dans le bin sur le port 1100 : rmiregistry 1100

-Djava.rmi.server.useCodebaseOnly=false
-Djava.security.policy="security.txt"

## Server

Il faut lancer la rmi dans le bin sur le port 1101 : rmiregistry 1101

-Djava.rmi.server.hostname="10.188.232.162"
-Djava.rmi.server.codebase=http://localhost:2000/

## Client

-Djava.rmi.server.useCodebaseOnly=false
-Djava.security.policy="security.txt"

## http server

Dans le dossier qui contient les .java :
  On compile le server avec javac *.java
Dans le dossier du dessus :
  On lance le server http sur le port 2000: java classserver.ClassFileServer 2000 ../ServerAR/bin/
  

## activemq

Dans le dossier bin :
  On lance le scripte activemq : ./activemq start

