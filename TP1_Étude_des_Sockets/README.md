# Rapport pour le TP1

UDP
---

1. Notez l’adresse IPv4 associée à l’interface ethernet de votre ordinateur avec la  	commande suivante:ip address show dev eth0

   **172.30.133.198/17**  

2. Lancez la commande suivante pour créer une socket UDP: socket udp

    **l'identifiant socket est 3**

3. Lancez la commande suivante pour afficher des informations sur la socket que vous 	venez de créer:status

   **Id	Proto	local address	Remote address 		TYPE	RWX ?**  
   **---------------------------------------------------------------------------**  
   **>3 UDP U   -		-	                ipv4   .W.**  

4. Utilisez la commande bind pour associer cette socket avec l’adresse IPv4 de votre 
   ordinateur et avec le port 3000

   **bind 3 172.30.133.198 3000**  

5. Envoyez un gentil message vers l’ordinateur de mon voisin avec la commande sendto

   **sendto 3 127.0.0.1 1400 "hello my friend"**  
 
6. Quelles informations votre voisin doit-il vous fournir pour que vous puissiez  
   lui   envoyer un message?

   **il doit demande me fournir son identificateur, adresse IP et son port**  

7. Créer une socket associée à toutes les adresses IP configurées sur l' 	
   ordinateur et laisser le système d’exploitation choisir lui-même un port libre.

   **bind 3 * 0**   	
	  
8. il est préférable de laisser le système choisir le port sur la machine qui envoie le message initial ?

   **Oui car choisir soi-même un numéro de port peut poser problème si ce numéro**   
   **est déjà utilisé par une autre socket**  

9. Supprimez la socket créée précédemment avec la commande

   **close 3**

10. Créez maintenant 2 sockets UDP et associez-les à toutes les adresses IP de votre 
    machine et à un port libre choisi par le système.
    **adresse locale est change d'un port aleatoire avec un astérisque(*)devant le port qui signifie l'adresse generique**  

12. Ouvrez un autre terminal et lancez la commande «ip address» pour connaître l’adresse IPv4 associée à l’interface de loopback
 
     **l'addresse 127.0.0.1**  

```
//premier socket

bind 3 * 0
sendto 3 127.0.0.1 55254 "comment allez vous ?"

//deuxieme socket

bind 3 * 0
sendto 3 127.0.0.1 42511 " Tres bien. Merci !"

```

15. -a) Vérifiez que l’adresse IP source et le port source correspondent à la socket sur laquelle les messages ont été envoyés.
      Vérifiez que l’adresse IP de destination et le port de destination correspondent à la socket sur laquelleles messages ont été reçus.

      **ça correspond bien scr port :42511  et  Dst port: 55254 tout avec ip adress de loopback 127.0.0.1**  

      
      b) Recopiez et complétez le diagramme
      
       **le diagram est joint**  

      c) combien de segments UDP ont été transmis?
    
      **62 bytes**

      d) l’efficacité du protocole

	**est 0.003 qui est le rapport entre la taille du message et la taille totale des données transmises pour vehiculer le message**  


TCP
___

4. Tentez d’établir une connexion depuis S1 vers S2 avec la commande
  **En tentant la connexion a echoué car la socket de destination était en ecoute passive**  

5. #fait

6.  Quelle est la socket «serveur» et la socket «client»?

    **le socket client soit s1 et le socket serveur soit s2**  

7. Tentez de nouveau d’établir une connexion depuis S1 vers S2.Que contatez-vous au niveau de Wireshark?

    **Rien qui s'affiche au niveau des 3 parties du wireshark**     

8. Avant de pouvoir envoyer un message, il faut que la demande de connexion soit acceptée par le destinataire. Pour cela, utilisez la commande:accept <id_socket>

   **maintant une connexion à partir du localhost (3000) est reçu  et une connexion avec un nouveau identifiant socket 4 est etabli**  

9. Lancez la commande status. Que constatez-vous?

   **un nouveau etablissement du connexion  avec un nouveau identifiant socket au sein de s2 :**  
   **4  TCP     127.0.0.1(4000)            127.0.0.1(3000)            ipv4  .W.** 
 
10. #fait

11. Analysez les paquets échangés sur Wireshark...

	a) Que se serait-il passé si le flag PSH (push) n’avait pas été activé par l’émetteur?

	**dans ce cas,les paquets seraient pas envoyé, donc une erreur en transmission**

	b) A quoi correspond le numéro de séquence du segment envoyé?

	**ça correspond au nombre de bits du message envoyés au destinataire plus un bit d'acknowledgement**  

	c)A quoi correspond  le numéro d’acquittement du segment reçu

	**ça correspond au numéro du prochain segment de sequence attendu**  

	d) Calculez la différence entre le numéro de séquence du paquet envoyé et le  numéro d’acquittement du paquet reçu. A quoi correspond le résultat de ce calcul?

	**ça correspond à la longeur des données(message) envoyées en bits**     

12. Lancez de nouveau la commande:
    Que contient le champ Recv-Q associé à la connexion précédemment établie

    **ça contient (20), la longueur de notre message reçu par le socket 4 destinatrice**  

13. Utilisez la commande suivante pour lire le message:

    **read 4 20, soit 4 le socket destinatrice du message donc l’identifiant renvoyé par la commande accept et 20 nombre d’octets à lire**  

14.  Lancez de nouveau la commande:
      ss -ant4
     Quelle est désormais la valeur du champ Recv-Q?

     **champ Recv-Q est devenu 0, qui veut dire que x nombre d'octets qui etait reçu est lu**  


15. Lancez la commande suivante sur S1:shutdown <id_socket> out
    
    **shutdown a mis la fin à la connexion dans le sens vers le port 4000 ainsi ça retourne flag PSH en flag FIN**
    **une erreur "Broken pipe" s'affiche en essayant d'envoyer un message**

16. Répondez au 1er message envoyé précédemment en envoyant «Tres bien. Merci!» (18 caractères). Attention au choix de la socket
    **Envoie de 18 caractères a reussi**

17. Utilisez maintenant la commande shutdown pour mettre fin à la connexion dans l’autre sens (depuis le serveur vers le client).
 **shutdown a mis la fin à la connexion dans le sens vers le port 4000, ainsi ça retourne flag PSH en flag FIN**

18. a) Recopiez le schéma ci-dessous et complétez-le
	**la reponse dans le fichier ci-joint (fichier "schemaTCP" )**
    b) Combien de segments TCP ont été transmis? Comparez avec UDP.
	**6 segments avec le message "hello"**
	**1 segment de difference entre udp et tcp à cause d'un bit qui gère d'acquittement**  
    c) Calculez l’efficacité du protocole, c’est-à-dire le rapport entre la taille du message en octets et la taille totale des données transmises pour véhiculer ce message. Comparez avec UDP.
	**l'efficacite de TCP est 0.0084 ( le rapport de 6 octets du taille de message et 71 octets de la taille totale des donnes )**
  **l'efficacite sur udp est plus élévé que sur tcp car l'efficatite de celui ci est 0.000042**


------------------------------------------------------------------



