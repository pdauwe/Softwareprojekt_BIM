# Zeitpräferenzen erfassen

## Funktionen

- dozentenlisteAnzeigen
- zeitpräferenzblöckeAnzeigen (falls vorhanden)
- zeitpräferenzenSpeichern

## Notizen
Immer wenn "Speichern" gedrückt wird, muss ein Hinweis erscheinen. Dieser muss darauf hinweisen, dass ein neuer Stundenplan erstellt werden muss, damit die Änderungen wirksam werden.


# Stundenplan erstellen

## Funktionen

- stundenplanErstellen
- stundenplanSpeichern

Parameter:

- studiengang
- dozenten <- hier sind die zeiten
- module

## Notizen

Stundenpläne werden für alle Semester einer Witterungszeit erstellt. Es wird einfach beim ersten Semester einer Witterungszeit angefangen und dann inkrementell erhöht bla. (BIM 1,3,5 -> bei 1 anfangen -> bis 5). Wenn 1 geplant ist bei Erstellung von 3 in 1 gucken, ob Dozent schon verbucht.

# Stundenplan anzeigen

## Funktionen

- stundenplanAnzeigen

Parameter:

- stundenplan

## Notizen

