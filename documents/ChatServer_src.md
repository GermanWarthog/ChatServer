
> [!info]
> 
> Der Chat Server benutzt die Klassen Client und Server und erstellt eine Java Frame GUI bei der man Nachrichten an den Server senden kann. Dieser verarbeitet die Nachrichten anschließend und sendet diese an alle Teilnehmer im Netzwerk (per Port) zurück. 
> 

## Ordnerstruktur 

- :obs_folder: `src`
	- :obs_folder: `classes`
		- :obs_folder: `client` 
			- :luc_file_code:: `Chat.java`
			- :luc_file_code:: `Interface.java`
		- :obs_folder: `server`
			- :luc_file_code:: `ServerProcess.java`
	- :obs_folder: `imports` 
		- :luc_file_code:: `Client.java`
		- :luc_file_code:: `Server.java`
		- :luc_file_code:: `List.java`
	- :luc_file_code:: `Main.java`


## Client Scripts 

> [!info]
> 
> Der Client repräsentiert alles was local auf dem PC einer Person passiert. Hierbei werden der Chat und das Interface zum Senden der Nachrichten genutzt. 

![[1.png]]

Die Klasses Chat wird hierbeit als **Extention** der Klasse Client verwendet und stellt die Verbindung zum Server auf und das Interface, das genutzt wird um die Nachrichten anzuzeigen. 

![[2.png]]

Die Klasse "Chat" erbt von einer anderen Klasse und hat einen Konstruktor, der eine IP-Adresse und einen Port als Parameter erwartet.

Im Konstruktor wird zunächst die Oberfläche (Interface) für den Chat erstellt und dem Konstruktor die aktuelle Instanz von "Chat" übergeben.

Anschließend wird geprüft, ob eine Verbindung zum Server aufgebaut werden konnte. Sollte dies nicht der Fall sein, wird eine Fehlermeldung ausgegeben, die Oberfläche geschlossen und das Programm beendet.

![[3.png]]


Dieser Code definiert die Methode "`processMessage`", die eine Textnachricht als Eingabe erhält und dann bestimmte Aktionen ausführt.
Die Methode verarbeitet die Nachricht, indem sie zuerst darauf wartet, dass das "`chatInterface`" Objekt initialisiert wird, und dann prüft, ob die Nachricht mit "`TriggerClientEvent`" beginnt.
Wenn dies der Fall ist, wird das zweite Element der Nachricht analysiert, um eine entsprechende Aktion auszuführen. 
Nachdem das zweite Element "`Connected`" ist, wird eine Meldung ausgegeben, dass der Benutzer dem Chat-Server beigetreten ist. 
Falls kein Match gefunden wird, wird die Methode einfach beendet, andernfalls wird die ursprüngliche Nachricht an das "`chatInterface`" Objekt weitergeleitet, um sie anzuzeigen. 


> [!info]
> 
> Es wird gewartet ob eine Instanz vom GUI existiert, da der Server bei der Verbindung einer Client eine Bestätigungsnachricht sendet und dies beim Start des Programms dazu führt (aufgrund der asynchronen Processen), dass die Instanz des GUI's noch nicht existiert. Dementsprechend wird so verhindert, dass eine nicht-existierende Instanz eines Objekts verwendet wird.  


## Ablauf der Nachrichtenübertragung (Sicht des Clients)

**Willkommensnachricht bei beitreten des Chat Servers**
![[4.png]]

**Eingabe der Nachricht**
![[5.png]]

**Senden einer Nachricht**
![[7.png]]

**Anzeige der Übertragenen Nachricht**

![[6.png]]

---

## Server Script

![[ServerProcess.png]]

Diese Klasse "ServerProcess" erweitert die "Server"-Klasse und fügt spezifische Funktionen hinzu. Der Konstruktor erhält einen Port und eine Instanz der SQL-Klasse, um eine Verbindung zur Datenbank herzustellen.

Die Methode "processNewConnection" wird aufgerufen, wenn ein neuer Client eine Verbindung zum Server herstellt. Zunächst wird überprüft, ob der Server geöffnet ist. Wenn dies nicht der Fall ist, wird die Methode beendet. Ansonsten wird die Verbindung als erfolgreich bestätigt, indem eine Nachricht an den Client gesendet wird. Außerdem werden die vorherigen Chat-Nachrichten aus der Datenbank abgerufen und auf der Serverkonsole ausgegeben.

Die Methode "processMessage" wird aufgerufen, wenn eine Nachricht von einem Client empfangen wird. Die Methode sendet die Nachricht an alle verbundenen Clients weiter und fügt die empfangene Nachricht in die Datenbank ein.

Die Methode "processClosingConnection" wird aufgerufen, wenn eine Verbindung zu einem Client geschlossen wird. Eine Nachricht wird an alle verbundenen Clients gesendet, um den Grund für die Trennung zu informieren.

**Empfangene Nachrichten der Clients**

![[9.png]]

---

![[GUI.png]]



Dieser Code definiert eine Benutzeroberfläche für einen Chat-Client, die es Benutzern ermöglicht, Textnachrichten zu senden und empfangene Nachrichten anzuzeigen. Die "Interface" -Klasse enthält eine Konstruktor-Methode, die eine Chat-Server-Instanz und einen Benutzernamen als Eingabe erhält. Beim Aufruf des Konstruktors wird zunächst eine Willkommensnachricht an den Chat-Server gesendet, die den Benutzernamen enthält. Anschließend wird die Benutzeroberfläche erstellt, die aus einem Textfeld zum Eingeben von Nachrichten, einem "Senden"-Button und einem Bereich zum Anzeigen von Nachrichten besteht.

Wenn der "Senden"-Button gedrückt wird, wird der eingegebene Text an den Chat-Server gesendet und die Texteingabe wird gelöscht. Die "AddMessage" -Methode wird verwendet, um empfangene Nachrichten zum Anzeigen auf der Benutzeroberfläche hinzuzufügen. Die "CloseAll" -Methode wird aufgerufen, um die Benutzeroberfläche zu schließen, wenn der Chat-Client geschlossen wird.