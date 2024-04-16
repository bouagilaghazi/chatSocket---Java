## ChatSocket---Java 
Ce projet représente un client de chat basé sur Swing, une interface graphique pour les applications Java. Le client permet aux utilisateurs de se connecter à un serveur de chat distant et d'envoyer des messages à d'autres utilisateurs connectés.

#Fonctionnalités
Interface utilisateur conviviale permettant aux utilisateurs d'envoyer et de recevoir des messages.
Connexion à un serveur distant à l'aide d'une adresse IP et d'un port spécifiés.
Chaque utilisateur peut choisir un pseudo pour son identité dans le chat.

#Comment utiliser
Lancement du client : Exécutez l'application en lançant la classe ClientSwing. Une fenêtre de chat s'ouvrira, vous demandant de saisir votre pseudo.

Connexion au serveur : Entrez l'adresse IP et le port du serveur auquel vous souhaitez vous connecter. Cliquez sur "Send" pour envoyer votre message.

Envoi de messages : Saisissez votre message dans le champ de texte en bas de la fenêtre et appuyez sur "Enter" ou cliquez sur le bouton "Send" pour l'envoyer au chat.

Déconnexion : Pour quitter le chat, saisissez "exit" dans le champ de texte et appuyez sur "Enter" ou cliquez sur "Send". Cela vous déconnectera du serveur et fermera l'application.

#Technologies utilisées
Java Swing : Utilisé pour créer l'interface graphique du client de chat.
Sockets : Utilisés pour la communication réseau entre le client et le serveur
