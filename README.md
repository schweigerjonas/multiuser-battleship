# Multiuser-Version des Spiels "Schiffe Versenken"

Implementierung des Spieleklassikers "Schiffe Versenken" mit Client-Server-Architekur in Java. 

Das Projekt ist im Rahmen meines W-Seminars in der Oberstufe entstanden.

## Inhaltsverzeichnis

- [Über das Projekt](#über-das-projekt)
    - [Verwendete Technologien](#verwendete-technologien)
- [Erste Schritte](#erste-schritte)
    - [Voraussetzungen](#voraussetzungen)
    - [Installation](#installation)
- [Verwendung](#verwendung)
- [Danksagungen](#danksagungen)

## Über das Projekt

Dieses Projekt erlaubt es zwei Spielern lokal, im selben Netzwerk, gegeneinander das Spiel "Schiffe versenken" zu spielen.

### Verwendete Technologien

Programmiersprache: Java
Anmerkung: 
    Im Rahmen der Seminararbeit wurde mir das Paket "Fenster", welches eine Auswahl an Klassen zur Realisierung der Benutzeroberfläche  beinhaltet, sowie die Pakete "Server", und "Client" vorgegeben, die das Grundgerüst der Client-Server-Architektur beinhält, vorgegeben.

## Erste Schritte

- Download des Projekts durch **beide** Spieler
- Herausfinden der eigenen IP-Adresse
    im Terminal/Eingabeaufforderung: **ipconfig**
    Die IP-Adresse befindet sich unter dem Punkt "Ethernet adapter Ethernet" bei **IPv4 Address**

### Voraussetzungen

Enwicklungsumgebung zum Anpassen der IP-Adresse und Starten des Programms
Beispiele: Visual Studio Code

### Installation

1. Projekt downloaden
2. Projekt in Entwicklungsumgebung öffen
3. Datei "SVClient.java" öffnen
4. Folgenden Codeabschnitt anpassen:
```java
    public static void main(String[] args) {
        try {
            new SVClient("HIER IP-ADDRESSE EINFÜGEN");
        } catch (Exception e) {
            System.err.println("Error: " + e);
        }
     }
```
## Verwendung

1. Ein Spieler muss "SVServer.java" ausführen, dies startet des Server und den Client dieses Spielers
2. Der zweite Spieler muss auf seinem Rechner "SVClient.java" ausführen
3. Gespielt wird anhand der Instruktionen auf dem UI

## Danksagungen

Hiermit danke ich meinem Lehrer F.F. für das Bereitstellen der Pakete "Fenster", "Client" und "Server"