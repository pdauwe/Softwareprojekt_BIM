
#Formalisierung
- Ergebnis: Platzierung in diesem Zeitslot: Ja, Nein (1, 0)
- x1: Zeit Stundeplan
- x2: Zeit Dozent
- x3: Zeit Modul?
- x4: Zeitpräferenz Dozent (weich)

#Nebenbedingungen

- 
- 


#Info
- Vorplanung:: Vorher Modul und Raum zuordenen wegen Teilnehmerzahl bzw. Computerraum
- Für jedes Modul, Raum und Dozent gibt es eine Menge von Zeiten die die NICHT-Verfügbar-Zeit beschreibt
- diese Zeit(sperren) als zwei dim. boolean Array wie Stundenplan speichern
- Das Ergebnis der Berechnug ist eine Modul-Zeit-Kombination (Raum ergibt sich aus der Vorplanung, Dozent aus der Modul-Zuordnung) mit Zeitsperren-Arrays für Modul???
- Es gitb für jeden Stundenplan eine Menge von Modulen (Semester Module) die nacheinander abgearbeitet werden
- Zuerst wird ein zwar gültiger aber „schlechter“ Plan erstellt (der Urplan) und anschließend eine Optimierung dieses Planes vorgenommen.
- Der Eigentliche Stundenplan enthält Zeitsperren-Arrays von D

- **Ein Modul passt in einen Zeitslot (180min)**
- Alle Module haben 180min und füllen eine Zeitslot --> GdP(BIM1) werden zwei Module

#Umbumsen
- Lehrer --> Dozent
- Klasse --> Studiengang (z. b. BIM3)
- Räume --> Räume
- Fächer --> Modul
- Stunde --> Zeitslot
- Zeiten --> bei uns nicht: Ein Modul passt in einen Zeitslot
- Kurs --> hamma net


#bla
###Nebenbedingungen
- Verfügbarkeit Dozent (hart)
- [Dozenten haben so wenig Freistungen wie möglich]

###Berechnung
- Für einen Semester(z. B. WS13/14) werden alle Studiengänge(BIM1, BIM3, etc), alle Module, alle Dozenten(Zeitsperren und Zeitpräferenz), alle Räume, alle Zeitslots geladen.
- d. h. für uns: 6 Studiengänge(BIM1, BIM3, BIM5, BABM1, BABM3, BABM5) --> 36 Module --> x Dozenten --> 90 verfügbare Zeitslots
- Jedem Dozent wird eine Menge von Fächern zugeordnet.
- Jedem Modul ist eine Menge von Räumen zugeordnet, in denen das Modul gelehrt werden kann.
- Der Raumplan ist eine Menge von 15 Zeitslots, für die Aufnahme je eines Moduls.
- Alle Raumpläne zusammen sind *ARP* (steht für AlleRaumPläne)
- der Stundenplan ist eine Menge von Tupeln (Ein Raum, samt seines Raumplans)
- [Käse: Für alle Dozenten, Studiengänge, Räume sind die alle Zeitslots frei.]
- Eine Zeitslot besteht aus dem Studiengang, Dozent, Modul, Menge der möglich Räume
Seite.11 Exposé. Gehirnmatsch

