Softwareprojekt_BIM "Stundenplan"
===================
Repository für das Softwareprojekt BIM Semester 5

Server Hochschule
================
	Betriebssystem: Windows 2008 R2 (64bit)

	Hostname: usrv06.zemit.wi.hs-osnabrueck.de
	IP: 131.173.88.204
	Remote-Zugang: RDP-Remotedesktop (Port 3389)

	Benutzer: administrator
	Passwort: SNNnab1PPe
	
	Extern erreichbare TCP/IP-Ports: 3389

Dantenbank-Server
=================
	Passwort: $tund3nplan
	
	
Meilensteine
=============
##Donnerstag, 13. November 2014 (12:00, CF107): Erster Überblick (20 min) – PL
Erwartet wird:
- Ein erster Projektplan, Risikoliste
- Ein erster fachlicher Überblick mit Geschäftsanwendungsfällen und wesentlichen Anforderungen
- Ein erster Überblick über die technische Architektur

##Donnerstag, 18. Dezember 2014 (12:00, CF107): Präsentation der Spezifikation (20min) – FCD
Erwartet wird:
- Aktualisierter Projektplan
- Fachkonzept

##Januar/Februar 2015 (Zeit und Ort folgen): Abschlusspräsentation (35min) – TCD / QB
Erwartet wird
- Präsentation der Anwendung
- Überblick über DV-Konzept
- Überblick über durchgeführte QS-Maßnahmen
 
Beschreibung
=============
##Fachlicher Hintergrund
Zu jedem Semester muss die Fakultät WiSo einen Stundenplan erstellen. Die grundsätzliche Herausforderung dabei liegt darin begründet, den zu planenden Modulen / Unterrichtseinheiten unter Berücksichtigung von den zeitlichen Präferenzen der Dozenten den geeigneten Raum zuzuordnen und dabei zu berücksichtigen, dass die Module einer Studiengruppe nicht zeitgleich stattfinden dürfen.

##Funktionale Anforderungen
Zentrale Anforderungen an die Anwendung sind:
- Das System verwaltet eine Reihe von Stammdaten, die exemplarisch einmal erfasst werden, sich dann jedoch kaum verändern und daher auch nicht über entsprechende Bildschirmmasken editiert werden können:
	- Die in den Prüfungsordnungen mehrerer Studiengänge aufgeführten Module sind als Stammdaten hinterlegt. Essentiell sind dabei 				 die Nr. des Moduls, die zeitliche Lage im Verlauf des Studiums sowie die Anforderungen an die Vorlesungsraum (z.B. ist ein Computer-Raum erforderlich bzw. die notwendige Größe des Vorlesungsraumes)
	- Die in der Fakultät vorhandenen Räumlichkeiten mit den entsprechenden Rahmenparametern (Größe, Lage, Art des Raumes, ....) ist in der Datenbank gespeichert.
	- Die Dozenten sind als Stammdaten hinterlegt.
- Jedes Semester werden für jeden Dozenten die aktuellen Zeitpräferenzen abgefragt. Die Eingabe erfolgt über eine individuelle Bildschirmmaske und gilt nur für das zu planende Semester.
- Sind alle vorhandenen Daten für das zu planende Semester vorhanden, werden die Daten entsprechend aufbereitet und in dem Format bereitgestellt, um mit einem geeigneten Algorithmus das mathematische Zuordnungsproblem zu lösen. Als Verfahren kommen u.a. Evolutionäre Algorithmen oder die ganzzahlige Programmierung zur Anwendung. I.d.R. existieren diverse Software-Bibliotheken sodass der Algorithmus selbst nicht entwickelt werden muss.
- Ist eine zulässige Lösung gefunden bzw. errechnet, wird diese gespeichert und kann am Bildschirm ausgegeben werden. Dabei wird je Studiengruppe (z.B: BIM 5. Semester WS 2014/15) grafisch ausgegeben.

##Nicht funktionale Anforderungen
- Alle Daten werden in einer relationalen Datenbank gespeichert
- Die Bedienung erfolgt über einen Browser.
- Die Anwendung wird mit mindestens 3 unterschiedlichen Schichten (Datenhaltung, Applikationslogik sowie GUI) implementiert. Die jeweiligen Schichten und Softwarekomponenten sind entsprechend über Schnittstellen entkoppelt.


Anforderungen aus dem Projektverlauf
===============
## Stammdaten:

Folgende Stammdaten werden benötigt:

	Siehe Notizen vom 02.10.2014
	
- 2-3 Semester
- 2 Studienprogramme 
- den vorherigen Anforderungen entsprechende Anzahl Dozenten
- 3-4 Räume
